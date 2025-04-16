package com.tarumt.control;

import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.list.ListInterface;
import com.tarumt.boundary.InterviewUI;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.Company;
import com.tarumt.entity.JobApplication;
import com.tarumt.entity.interview.TimeSlot;
import com.tarumt.entity.interview.ScheduledInterview;
import com.tarumt.entity.interview.Invitation;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Strings;

import java.time.*;

public class InterviewService {
    private static InterviewService instance;
    private ListInterface<Invitation> invitations = new DoublyLinkedList<>();
    private ListInterface<ScheduledInterview> scheduledInterviews = new DoublyLinkedList<>();
    private ListInterface<JobApplication> jobApplications = new DoublyLinkedList<>();
    private final InterviewUI interviewUI;

    private InterviewService() {
        Input input = new Input();
        this.invitations = Initializer.getInvitations();
        this.scheduledInterviews = Initializer.getScheduledInterviews();
        this.jobApplications = Initializer.getJobApplications();
        this.interviewUI = new InterviewUI(input);
        updateInterviewedStatus();
    }

    public static InterviewService getInstance() {
        if (instance == null) {
            instance = new InterviewService();
        }
        return instance;
    }

    public void updateInterviewedStatus() {
        for (ScheduledInterview interview : scheduledInterviews) {
            if (interview.getTimeSlot().isPast()) {
                JobApplication application = interview.getJobApplication();
                if (application != null && application.isReadyForInterview()) {
                    application.setStatus(JobApplication.Status.INTERVIEWED);
                }
            }
        }
    }

    private ListInterface<ScheduledInterview> getAllScheduledInterviews() {
        updateInterviewedStatus();
        return scheduledInterviews;
    }

    private ListInterface<JobApplication> getAllJobApplications() {
        updateInterviewedStatus();
        return jobApplications;
    }

    private ListInterface<Invitation> getApplicantInvitations() {
        Applicant applicant = Context.getApplicant();
        if (applicant == null) {
            return new DoublyLinkedList<>();
        }
        return invitations.filter(invitation -> invitation.getJobApplication().getApplicant() != null
                && invitation.getJobApplication().getApplicant().equals(applicant));
    }


    private ListInterface<Invitation> getEmployerInvitations() {
        Company company = Context.getCompany();
        if (company == null) {
            return new DoublyLinkedList<>();
        }
        return invitations.filter(invitation -> {
            JobApplication app = invitation.getJobApplication();
            return app != null && app.getJobPosting() != null && company.equals(app.getJobPosting().getCompany());
        });
    }

    private ListInterface<ScheduledInterview> getEmployerScheduledInterviews() {
        Company company = Context.getCompany();
        return getAllScheduledInterviews().filter(
                scheduledInterview -> scheduledInterview.getJobApplication().getJobPosting().getCompany()
                        .equals(company));
    }

    private ListInterface<ScheduledInterview> getEmployerScheduledInterviews(Company company) {
        return getAllScheduledInterviews()
                .filter(interview -> interview.getJobApplication().getJobPosting().getCompany() != null
                        && interview.getJobApplication().getJobPosting().getCompany().equals(company));
    }

    private ListInterface<JobApplication> getEmployerJobApplications() {
        Company company = Context.getCompany();
        if (company == null) {
            return new DoublyLinkedList<>();
        }
        return getAllJobApplications().filter(application -> application.getJobPosting().getCompany() != null
                && application.getJobPosting().getCompany().equals(company));
    }

    private ListInterface<ScheduledInterview> getApplicantScheduledInterviews() {
        Applicant applicant = Context.getApplicant();
        return getAllScheduledInterviews()
                .filter(scheduledInterview -> scheduledInterview.getJobApplication().getApplicant().equals(applicant));
    }

    public void accessEmployer() {
        this.interviewUI.accessEmployerMenu();
    }

    public void sendInvitation() {
        ListInterface<JobApplication> applications = getEmployerJobApplications()
                .filter(JobApplication::isReadyForInterview);

        if (applications == null || applications.isEmpty()) {
            this.interviewUI.printNoApplicationToInviteMsg();
            return;
        }

        JobApplication application = this.interviewUI.getReadyForInterviewApplication(applications);
        if (application == null)
            return;
        System.out.println("|");
        String remarks = this.interviewUI.getRemarks();
        if (remarks.equals(Input.STRING_EXIT_VALUE))
            return;

        Invitation invitation = new Invitation(application, remarks, Context.getDateTime());
        this.invitations.add(invitation);
        this.interviewUI.printSuccessInviteMsg();
    }

    public void accessApplicant() {
        this.interviewUI.accessApplicantMenu();
    }

    public void displayInvitation() {
        ListInterface<Invitation> invitations = new DoublyLinkedList<>();
        if (Context.isEmployer()) {
            invitations = getEmployerInvitations();
        }
        if (Context.isApplicant()) {
            invitations = getApplicantInvitations();
        }
        this.interviewUI.printInvitation(invitations);
    }

    public void acceptInvitation() {
        ListInterface<Invitation> applicantInvitations = getApplicantInvitations();
        Invitation invitation = this.interviewUI.getInvitationToAccept(applicantInvitations);
        if (invitation == null)
            return;

        Company invitingCompany = invitation.getJobApplication().getJobPosting().getCompany();
        ListInterface<ScheduledInterview> employerScheduledInterviews = this
                .getEmployerScheduledInterviews(invitingCompany);
        ListInterface<ScheduledInterview> applicantScheduledInterviews = this.getApplicantScheduledInterviews();
        ListInterface<TimeSlot> employerBookedSlots = employerScheduledInterviews.map(ScheduledInterview::getTimeSlot);
        ListInterface<TimeSlot> applicantBookedSlots = applicantScheduledInterviews
                .map(ScheduledInterview::getTimeSlot);
        employerBookedSlots.merge(applicantBookedSlots);

        if (this.isWeekTimeSlotOccupied(employerBookedSlots)) {
            this.interviewUI.printFullyOccupiedMsg(invitingCompany);
            return;
        }

        ListInterface<TimeSlot> availableTimeSlots = getAvailableTimeSlot(employerBookedSlots);
        String calendarView = getCalendarAvailabilityView(employerBookedSlots, true);

        this.interviewUI.printAvailableTimeSlot(calendarView);
        int timeSlotInput = this.interviewUI.getTimeSlot(availableTimeSlots.size());
        if (timeSlotInput == Input.INT_EXIT_VALUE)
            return;

        TimeSlot timeSlot = availableTimeSlots.get(timeSlotInput - 1);

        ScheduledInterview scheduledInterview = new ScheduledInterview(invitation.getJobApplication(),
                invitation.getRemarks(), timeSlot, 0, Context.getDateTime());
        this.invitations.remove(invitation);
        scheduledInterviews.add(scheduledInterview);

        this.interviewUI.printSuccessAcceptInvitationMsg(timeSlot);
    }

    private boolean isBooked(TimeSlot timeSlot, ListInterface<TimeSlot> bookedSlots) {
        if (timeSlot == null || bookedSlots == null) {
            return false;
        }
        return bookedSlots.contains(timeSlot);
    }

    private boolean isTimeSlotAvailable(TimeSlot timeSlot, ListInterface<TimeSlot> bookedSlots) {
        if (timeSlot == null || bookedSlots == null) {
            return false;
        }
        return timeSlot.isAvailable() && !isBooked(timeSlot, bookedSlots);
    }

    private boolean isWeekTimeSlotOccupied(ListInterface<TimeSlot> bookedSlots) {
        boolean hasFreeSlot;
        if (bookedSlots == null) {
            hasFreeSlot = TimeSlot.generateWeekTimeSlot().anyMatch(TimeSlot::isAvailable);
        } else {
            hasFreeSlot = TimeSlot.generateWeekTimeSlot().anyMatch(
                    (timeSlot) -> isTimeSlotAvailable(timeSlot, bookedSlots));
        }
        return !hasFreeSlot;
    }

    private ListInterface<TimeSlot> getAvailableTimeSlot(ListInterface<TimeSlot> bookedSlots) {
        return TimeSlot.generateWeekTimeSlot().filter(
                (timeSlot -> isTimeSlotAvailable(timeSlot, bookedSlots)));
    }

    public void displayIncomingInterview() {
        ListInterface<ScheduledInterview> interviews = new DoublyLinkedList<>();
        if (Context.isEmployer()) {
            interviews = getEmployerScheduledInterviews()
                    .filter(interview -> !interview.getTimeSlot().isPast());
        }
        if (Context.isApplicant()) {
            interviews = getApplicantScheduledInterviews()
                    .filter(interview -> !interview.getTimeSlot().isPast());
        }

        ListInterface<TimeSlot> timeSlots = interviews.map(ScheduledInterview::getTimeSlot);
        String calendarView = getCalendarAvailabilityView(timeSlots, false);
        if (!interviews.isEmpty()) {
            this.interviewUI.printApplicantTimeSlot(calendarView);
        }
        this.interviewUI.printScheduledInterviews(interviews);
    }

    public void displayPastInterview() {
        ListInterface<ScheduledInterview> pastInterviews = new DoublyLinkedList<>();

        if (Context.isEmployer()) {
            pastInterviews = getEmployerScheduledInterviews()
                    .filter(interview -> interview.getTimeSlot().isPast());
        }
        if (Context.isApplicant()) {
            pastInterviews = getApplicantScheduledInterviews()
                    .filter(interview -> interview.getTimeSlot().isPast());
        }

        this.interviewUI.printScheduledInterviews(pastInterviews);
    }

    public void viewAvailability() {
        ListInterface<ScheduledInterview> employerInterviews = getEmployerScheduledInterviews();
        ListInterface<TimeSlot> bookedSlots = employerInterviews.map(ScheduledInterview::getTimeSlot);

        String calendarView = getCalendarAvailabilityView(bookedSlots, false);
        this.interviewUI.printApplicantTimeSlot(calendarView);
    }

    public String getCalendarAvailabilityView(ListInterface<TimeSlot> bookedSlots, boolean showIndex) {
        final int NUM_SLOTS_PER_DAY = 18;
        final int DAYS_AHEAD = 7;

        StringBuilder calendarView = new StringBuilder();
        final int columnWidth = 14;
        final int totalColumns = DAYS_AHEAD + 1;

        StringBuilder dividerLine = new StringBuilder();
        for (int i = 0; i < totalColumns; i++) {
            dividerLine.append(Strings.repeat("-", columnWidth + 3));
        }
        String horizontalDivider = dividerLine.append("-").toString();
        calendarView.append(horizontalDivider).append("\n");
        calendarView.append("| ").append(String.format("%-" + columnWidth + "s", "Time Slot")).append(" |");

        LocalDate today = Context.getDate();

        for (int i = 0; i < DAYS_AHEAD; i++) {
            LocalDate date = today.plusDays(i);
            String dayHeader = date.getDayOfWeek().name().substring(0, 3) + " " + date.getDayOfMonth() + "/"
                    + date.getMonthValue();
            calendarView.append(" ").append(String.format("%-" + columnWidth + "s", dayHeader)).append(" |");
        }
        calendarView.append("\n");
        calendarView.append(horizontalDivider).append("\n");

        String[][] slotDisplay = new String[NUM_SLOTS_PER_DAY][DAYS_AHEAD];
        int freeIndex = 1;

        for (int day = 0; day < DAYS_AHEAD; day++) {
            LocalDate date = today.plusDays(day);
            for (int slot = 0; slot < NUM_SLOTS_PER_DAY; slot++) {
                TimeSlot testSlot = new TimeSlot(date, slot);
                boolean na = !testSlot.isAvailable();
                boolean isBooked = isBooked(testSlot, bookedSlots);

                if (na) {
                    String naText = "[NA]";
                    slotDisplay[slot][day] = Strings.errorHighlight(naText) +
                            Strings.repeat(" ", columnWidth - naText.length());
                } else if (isBooked) {
                    String bookedText = "[BOOKED]";
                    slotDisplay[slot][day] = Strings.warnHighlight(bookedText) +
                            Strings.repeat(" ", columnWidth - bookedText.length());
                } else {
                    String freeText = showIndex
                            ? String.format("[FREE] > %d", freeIndex++)
                            : "[FREE]";
                    slotDisplay[slot][day] = Strings.successHighlight(freeText) +
                            Strings.repeat(" ", columnWidth - freeText.length());
                }
            }
        }

        for (int slot = 0; slot < NUM_SLOTS_PER_DAY; slot++) {
            LocalTime slotStartTime = TimeSlot.getSlotStartTime(slot);
            LocalTime slotEndTime = TimeSlot.getSlotEndTime(slot);
            String timeSlot = String.format("%02d:%02d-%02d:%02d", slotStartTime.getHour(), slotStartTime.getMinute(),
                    slotEndTime.getHour(), slotEndTime.getMinute());
            calendarView.append("| ").append(String.format("%-" + columnWidth + "s", timeSlot)).append(" |");

            for (int day = 0; day < DAYS_AHEAD; day++) {
                calendarView.append(" ").append(slotDisplay[slot][day]).append(" |");
            }

            calendarView.append("\n");
        }

        calendarView.append(horizontalDivider).append("\n");
        return calendarView.toString();
    }


    public void cancelScheduledInterview() {
        ListInterface<ScheduledInterview> upcomingInterviews = new DoublyLinkedList<>();

        if (Context.isApplicant()) {
            upcomingInterviews = getApplicantScheduledInterviews()
                    .filter(interview -> !interview.getTimeSlot().isPast());
        }
        if (Context.isEmployer()) {
            upcomingInterviews = getEmployerScheduledInterviews()
                    .filter(interview -> !interview.getTimeSlot().isPast());
        }

        ScheduledInterview interviewToCancel = interviewUI.getInterviewToCancel(upcomingInterviews);
        if (interviewToCancel == null) {
            return;
        }

        if (interviewUI.confirmCancelInterview()) {
            scheduledInterviews.remove(interviewToCancel);
            interviewToCancel.getJobApplication().setStatus(JobApplication.Status.SHORTLISTED);
            interviewUI.printSuccessCancelInterviewMsg();
        }
    }

    public void cancelInvitation() {
        ListInterface<Invitation> employerInvitations = getEmployerInvitations();

        Invitation invitationToCancel = interviewUI.getInvitationToCancel(employerInvitations);
        if (invitationToCancel == null) {
            return;
        }

        if (interviewUI.confirmCancelInvitation()) {
            invitations.remove(invitationToCancel);

            JobApplication application = invitationToCancel.getJobApplication();
            application.setStatus(JobApplication.Status.SHORTLISTED);
            interviewUI.printSuccessCancelInvitationMsg();
        }
    }
}
