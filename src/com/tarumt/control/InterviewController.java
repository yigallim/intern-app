/**
 * @author Lim Yuet Yang
 */

package com.tarumt.control;

import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.list.ListInterface;
import com.tarumt.boundary.InterviewUI;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.Company;
import com.tarumt.entity.JobApplication;
import com.tarumt.entity.JobPosting;
import com.tarumt.entity.interview.TimeSlot;
import com.tarumt.entity.interview.ScheduledInterview;
import com.tarumt.entity.interview.Invitation;
import com.tarumt.entity.interview.BlockedTimeSlot;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Strings;
import com.tarumt.utility.pretty.Chart;
import com.tarumt.utility.search.FuzzySearch;

import java.time.*;

public class InterviewController {
    private static InterviewController instance;
    private ListInterface<Invitation> invitations = new DoublyLinkedList<>();
    private ListInterface<ScheduledInterview> scheduledInterviews = new DoublyLinkedList<>();
    private ListInterface<BlockedTimeSlot> blockedTimeSlots = new DoublyLinkedList<>();
    private ListInterface<JobApplication> jobApplications = new DoublyLinkedList<>();
    private ListInterface<JobPosting> jobPostings = new DoublyLinkedList<>();
    private final InterviewUI interviewUI;

    private InterviewController() {
        Input input = new Input();
        this.invitations = Initializer.getInvitations();
        this.scheduledInterviews = Initializer.getScheduledInterviews();
        this.blockedTimeSlots = Initializer.getBlockedTimeSlots();
        this.jobApplications = Initializer.getJobApplications();
        this.jobPostings = Initializer.getJobPostings();
        this.interviewUI = new InterviewUI(input);
        updateInterviewedStatus();
    }

    public static InterviewController getInstance() {
        if (instance == null) {
            instance = new InterviewController();
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

    private ListInterface<JobApplication> getEmployerJobApplications(Company company) {
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

    private ListInterface<BlockedTimeSlot> getEmployerBlockedSlots(Company company) {
        if (company == null) {
            return new DoublyLinkedList<>();
        }
        return blockedTimeSlots.filter(uts -> uts.getCompany().equals(company));
    }

    private ListInterface<JobPosting> getEmployerJobPostings() {
        Company company = Context.getCompany();
        if (company == null) {
            return new DoublyLinkedList<>();
        }
        return jobPostings.filter(job -> job.getCompany().equals(company));
    }

    private ListInterface<JobPosting> getEmployerJobPostings(Company company) {
        if (company == null) {
            return new DoublyLinkedList<>();
        }
        return jobPostings.filter(job -> job.getCompany().equals(company));
    }

    public void run() {
        this.interviewUI.menu();
    }

    public void displayAllInterview() {
        ListInterface<ScheduledInterview> interviews = getAllScheduledInterviews();
        this.interviewUI.printAllInterviews(interviews);
    }

    public void accessEmployer() {
        this.interviewUI.accessEmployerMenu();
    }

    public void accessEmployerInvitation() {
        this.interviewUI.accessEmployerInvitationMenu();
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
        if (Context.isAdmin()) {
            invitations = this.invitations;
        }

        ListInterface<Invitation> sortedInvitations = new DoublyLinkedList<>(invitations);
        sortedInvitations.sort((inv1, inv2) -> inv1.getInvitedAt().compareTo(inv2.getInvitedAt()));
        this.interviewUI.printInvitation(sortedInvitations);
    }

    public void acceptInvitation() {
        ListInterface<Invitation> applicantInvitations = getApplicantInvitations();
        Invitation invitation = this.interviewUI.getInvitationToAccept(applicantInvitations);
        if (invitation == null)
            return;

        Company invitingCompany = invitation.getJobApplication().getJobPosting().getCompany();
        ListInterface<TimeSlot> employerBlockedSlots = getEmployerBlockedSlots(invitingCompany)
                .map(BlockedTimeSlot::getSlot);
        ListInterface<ScheduledInterview> employerScheduledInterviews = this
                .getEmployerScheduledInterviews(invitingCompany)
                .filter(interview -> !interview.getTimeSlot().isPast());
        ListInterface<ScheduledInterview> applicantScheduledInterviews = this.getApplicantScheduledInterviews()
                .filter(interview -> !interview.getTimeSlot().isPast());

        ListInterface<TimeSlot> employerBookedSlots = employerScheduledInterviews.map(ScheduledInterview::getTimeSlot);
        ListInterface<TimeSlot> applicantBookedSlots = applicantScheduledInterviews
                .map(ScheduledInterview::getTimeSlot);
        ListInterface<TimeSlot> combinedBookedSlots = new DoublyLinkedList<>();
        combinedBookedSlots.merge(employerBookedSlots);
        combinedBookedSlots.merge(applicantBookedSlots);

        if (this.isWeekTimeSlotOccupied(combinedBookedSlots, employerBlockedSlots)) {
            this.interviewUI.printFullyOccupiedMsg(invitingCompany);
            return;
        }

        ListInterface<TimeSlot> availableTimeSlots = getAvailableTimeSlot(combinedBookedSlots, employerBlockedSlots);
        String calendarView = getCalendarAvailabilityView(combinedBookedSlots, employerBlockedSlots, true);

        this.interviewUI.printAvailableTimeSlot(calendarView);
        int timeSlotInput = this.interviewUI.getTimeSlot(availableTimeSlots.size());
        if (timeSlotInput == Input.INT_EXIT_VALUE)
            return;

        TimeSlot timeSlot = availableTimeSlots.get(timeSlotInput - 1);

        ScheduledInterview scheduledInterview = new ScheduledInterview(invitation.getJobApplication(),
                invitation.getRemarks(), timeSlot, -1, Context.getDateTime());
        this.invitations.remove(invitation);
        scheduledInterviews.add(scheduledInterview);

        this.interviewUI.printSuccessAcceptInvitationMsg(timeSlot);
    }

    public void displayIncomingInterview() {
        ListInterface<ScheduledInterview> interviews = new DoublyLinkedList<>();
        ListInterface<TimeSlot> bookedSlots = new DoublyLinkedList<>();
        ListInterface<TimeSlot> blockedSlots = new DoublyLinkedList<>();

        if (Context.isEmployer()) {
            Company company = Context.getCompany();
            interviews = getEmployerScheduledInterviews(company)
                    .filter(interview -> !interview.getTimeSlot().isPast());
            bookedSlots = interviews.map(ScheduledInterview::getTimeSlot);
            blockedSlots = getEmployerBlockedSlots(company).map(BlockedTimeSlot::getSlot);
        }

        if (Context.isApplicant()) {
            interviews = getApplicantScheduledInterviews()
                    .filter(interview -> !interview.getTimeSlot().isPast());
            bookedSlots = interviews.map(ScheduledInterview::getTimeSlot);
        }

        if (Context.isAdmin()) {
            interviews = getAllScheduledInterviews()
                    .filter(interview -> !interview.getTimeSlot().isPast());
        }

        ListInterface<ScheduledInterview> sortedInterviews = new DoublyLinkedList<>(interviews);
        sortedInterviews.sort((a, b) -> a.getTimeSlot().getStartDateTime().compareTo(b.getTimeSlot().getStartDateTime()));

        if (!Context.isAdmin()) {
            String calendarView = getCalendarAvailabilityView(bookedSlots, blockedSlots, false);
            if (!interviews.isEmpty() || !blockedSlots.isEmpty()) {
                this.interviewUI.printCalendarTimeSlot(calendarView);
            }
        }

        this.interviewUI.printScheduledInterviews(sortedInterviews);
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
        if (Context.isAdmin()) {
            pastInterviews = getAllScheduledInterviews()
                    .filter(interview -> interview.getTimeSlot().isPast());
        }

        ListInterface<ScheduledInterview> sortedPast = new DoublyLinkedList<>(pastInterviews);
        sortedPast.sort((a, b) -> a.getTimeSlot().getStartDateTime().compareTo(b.getTimeSlot().getStartDateTime()));
        this.interviewUI.printPastInterviews(sortedPast);
    }

    public void searchAllInterview() {
        while (true) {
            ListInterface<ScheduledInterview> accessibleInterviews = new DoublyLinkedList<>();
            String[] excludeKeys = null;

            if (Context.isEmployer()) {
                accessibleInterviews = getEmployerScheduledInterviews();
                excludeKeys = new String[]{"employer", "default"};
            } else if (Context.isApplicant()) {
                accessibleInterviews = getApplicantScheduledInterviews();
                excludeKeys = new String[]{"applicant", "default"};
            } else {
                accessibleInterviews = getAllScheduledInterviews();
                excludeKeys = new String[]{"default"};
            }

            interviewUI.printSearchInterviewMsg(accessibleInterviews);
            if (accessibleInterviews.isEmpty()) {
                return;
            }

            String query = interviewUI.getSearchInterviewQuery();
            if (query.equals(Input.STRING_EXIT_VALUE)) {
                return;
            }
            ListInterface<ScheduledInterview> sortedInterviews = new DoublyLinkedList<>(accessibleInterviews);
            sortedInterviews.sort((a, b) -> a.getTimeSlot().getStartDateTime().compareTo(b.getTimeSlot().getStartDateTime()));
            FuzzySearch.Result<ScheduledInterview> result = FuzzySearch.searchList(
                    ScheduledInterview.class,
                    sortedInterviews,
                    query,
                    excludeKeys
            );

            interviewUI.printSearchResult(result);
        }
    }

    public void rateCompletedInterviews() {
        ListInterface<ScheduledInterview> pastInterviews = getEmployerScheduledInterviews()
                .filter(interview -> interview.getTimeSlot().isPast());

        ListInterface<ScheduledInterview> interviewsToRate = pastInterviews.filter(interview -> interview.getRating() == -1);
        ScheduledInterview interviewToRate = interviewUI.getInterviewToRate(interviewsToRate);
        if (interviewToRate == null) {
            return;
        }

        int rating = interviewUI.getRatingInput();
        if (rating == Input.INT_EXIT_VALUE) {
            return;
        }

        interviewToRate.setRating(rating);
        interviewUI.printSuccessRateInterviewMsg(interviewToRate);
    }

    public void viewAvailability() {
        Company company = Context.getCompany();
        ListInterface<ScheduledInterview> employerInterviews = getEmployerScheduledInterviews(company);
        ListInterface<TimeSlot> bookedSlots = employerInterviews.map(ScheduledInterview::getTimeSlot);
        ListInterface<TimeSlot> blockedSlots = getEmployerBlockedSlots(company).map(BlockedTimeSlot::getSlot);

        String calendarView = getCalendarAvailabilityView(bookedSlots, blockedSlots, false);
        this.interviewUI.printCalendarTimeSlot(calendarView);
    }

    public void modifyAvailability() {
        this.interviewUI.modifyAvailabilityMenu();
    }

    public void addBlockedSlot() {
        Company company = Context.getCompany();
        if (company == null) return;

        ListInterface<ScheduledInterview> employerScheduledInterviews = getEmployerScheduledInterviews(company)
                .filter(interview -> !interview.getTimeSlot().isPast());
        ListInterface<TimeSlot> bookedSlots = employerScheduledInterviews.map(ScheduledInterview::getTimeSlot);
        ListInterface<TimeSlot> blockedSlots = getEmployerBlockedSlots(company).map(BlockedTimeSlot::getSlot);

        if (isWeekTimeSlotOccupied(bookedSlots, blockedSlots)) {
            interviewUI.printFullyOccupiedMsg(company);
            return;
        }

        ListInterface<TimeSlot> availableTimeSlots = getAvailableTimeSlot(bookedSlots, blockedSlots);
        String calendarView = getCalendarAvailabilityView(bookedSlots, blockedSlots, true);

        interviewUI.printAvailableTimeSlot(calendarView);
        int timeSlotInput = interviewUI.getTimeSlot(availableTimeSlots.size());
        if (timeSlotInput == Input.INT_EXIT_VALUE) return;

        TimeSlot slotToBlock = availableTimeSlots.get(timeSlotInput - 1);

        if (isBlocked(slotToBlock, blockedSlots)) {
            interviewUI.printSlotAlreadyBlockedMsg(slotToBlock);
            return;
        }

        BlockedTimeSlot blockedTimeSlot = new BlockedTimeSlot(company, slotToBlock);
        blockedTimeSlots.add(blockedTimeSlot);
        interviewUI.printSuccessAddBlockedSlotMsg(slotToBlock);
    }

    public void displayBlockedSlot() {
        Company company = Context.getCompany();
        ListInterface<BlockedTimeSlot> companyBlockedSlots = getEmployerBlockedSlots(company);
        interviewUI.printBlockedSlots(companyBlockedSlots);
    }

    public void removeBlockedSlot() {
        Company company = Context.getCompany();
        if (company == null) return;

        ListInterface<BlockedTimeSlot> companyBlockedSlots = getEmployerBlockedSlots(company);
        if (companyBlockedSlots.isEmpty()) {
            interviewUI.printNoBlockedSlotsMsg();
            return;
        }

        BlockedTimeSlot slotToRemove = interviewUI.getBlockedSlotToRemove(companyBlockedSlots);
        if (slotToRemove == null) return;

        if (interviewUI.confirmRemoveBlockedSlot(slotToRemove.getSlot())) {
            blockedTimeSlots.remove(slotToRemove);
            interviewUI.printSuccessRemoveBlockedSlotMsg(slotToRemove.getSlot());
        }
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

    public void interviewReport() {
        int previousDays = interviewUI.getPreviousDay();
        if (previousDays == Input.INT_EXIT_VALUE) return;
        this.interviewUI.printReport(buildInterviewReportBody(previousDays));
    }

    public void recruitmentReport() {
        int previousDays = interviewUI.getPreviousDay();
        if (previousDays == Input.INT_EXIT_VALUE) return;
        this.interviewUI.printReport(buildRecruitmentReportBody(previousDays));
    }

    public void rescheduleInterview() {

        Applicant applicant = Context.getApplicant();
        if (applicant == null) return;

        ListInterface<ScheduledInterview> applicantUpcomingInterviews = getApplicantScheduledInterviews()
                .filter(interview -> !interview.getTimeSlot().isPast());

        ScheduledInterview interviewToReschedule = interviewUI.getInterviewToReschedule(applicantUpcomingInterviews);
        if (interviewToReschedule == null) return;

        if (!interviewUI.confirmRescheduleInterview()) return;

        Company company = interviewToReschedule.getJobApplication().getJobPosting().getCompany();
        TimeSlot oldTimeSlot = interviewToReschedule.getTimeSlot();

        ListInterface<TimeSlot> companyBlockedSlots = getEmployerBlockedSlots(company).map(BlockedTimeSlot::getSlot);

        ListInterface<TimeSlot> companyOtherBookedSlots = getEmployerScheduledInterviews(company)
                .filter(interview -> !interview.getTimeSlot().isPast() && !interview.getTimeSlot().equals(oldTimeSlot))
                .map(ScheduledInterview::getTimeSlot);

        ListInterface<TimeSlot> applicantOtherBookedSlots = applicantUpcomingInterviews
                .filter(interview -> !interview.getTimeSlot().equals(oldTimeSlot))
                .map(ScheduledInterview::getTimeSlot);

        ListInterface<TimeSlot> combinedBookedSlots = new DoublyLinkedList<>();
        combinedBookedSlots.merge(companyOtherBookedSlots);
        combinedBookedSlots.merge(applicantOtherBookedSlots);

        if (isWeekTimeSlotOccupied(combinedBookedSlots, companyBlockedSlots)) {
            interviewUI.printFullyOccupiedMsg(company);
            return;
        }

        ListInterface<TimeSlot> availableTimeSlots = getAvailableTimeSlot(combinedBookedSlots, companyBlockedSlots);

        String calendarView = getCalendarAvailabilityView(combinedBookedSlots, companyBlockedSlots, true);

        interviewUI.printAvailableTimeSlot(calendarView);
        int timeSlotInput = interviewUI.getTimeSlot(availableTimeSlots.size());
        if (timeSlotInput == Input.INT_EXIT_VALUE) return;

        TimeSlot newTimeSlot = availableTimeSlots.get(timeSlotInput - 1);

        interviewToReschedule.setTimeSlot(newTimeSlot);

        interviewUI.printSuccessRescheduleMsg(newTimeSlot);
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

    private boolean isBlocked(TimeSlot timeSlot, ListInterface<TimeSlot> blockedSlots) {
        if (timeSlot == null || blockedSlots == null) {
            return false;
        }
        return blockedSlots.contains(timeSlot);
    }

    private boolean isBooked(TimeSlot timeSlot, ListInterface<TimeSlot> bookedSlots) {
        if (timeSlot == null || bookedSlots == null) {
            return false;
        }
        return bookedSlots.contains(timeSlot);
    }

    private boolean isTimeSlotAvailable(TimeSlot timeSlot, ListInterface<TimeSlot> bookedSlots, ListInterface<TimeSlot> blockedSlots) {
        if (timeSlot == null) {
            return false;
        }
        return timeSlot.isAvailable() && !isBooked(timeSlot, bookedSlots) && !isBlocked(timeSlot, blockedSlots);
    }

    private boolean isWeekTimeSlotOccupied(ListInterface<TimeSlot> bookedSlots, ListInterface<TimeSlot> blockedSlots) {
        return !TimeSlot.generateWeekTimeSlot().anyMatch(
                (timeSlot) -> isTimeSlotAvailable(timeSlot, bookedSlots, blockedSlots)
        );
    }

    private boolean isWeekTimeSlotOccupied(ListInterface<TimeSlot> timeSlots) {
        return !TimeSlot.generateWeekTimeSlot().anyMatch(
                (timeSlot) -> timeSlot.isAvailable() && !timeSlots.contains(timeSlot)
        );
    }

    private ListInterface<TimeSlot> getAvailableTimeSlot(ListInterface<TimeSlot> bookedSlots, ListInterface<TimeSlot> blockedSlots) {
        return TimeSlot.generateWeekTimeSlot().filter(
                (timeSlot -> isTimeSlotAvailable(timeSlot, bookedSlots, blockedSlots))
        );
    }

    private ListInterface<TimeSlot> getAvailableTimeSlot(ListInterface<TimeSlot> timeSlots) {
        return TimeSlot.generateWeekTimeSlot().filter(
                (timeSlot -> timeSlot.isAvailable() && !timeSlots.contains(timeSlot))
        );
    }

    private String getCalendarAvailabilityView(ListInterface<TimeSlot> bookedSlots, ListInterface<TimeSlot> blockedSlots, boolean showIndex) {
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
                boolean isBlocked = isBlocked(testSlot, blockedSlots);

                if (na) {
                    String naText = "[NA]";
                    slotDisplay[slot][day] = Strings.errorHighlight(naText) +
                            Strings.repeat(" ", columnWidth - naText.length());
                } else if (isBooked) {
                    String bookedText = "[BOOKED]";
                    slotDisplay[slot][day] = Strings.warnHighlight(bookedText) +
                            Strings.repeat(" ", columnWidth - bookedText.length());
                } else if (isBlocked) {
                    String blockedText = "[BLOCKED]";
                    slotDisplay[slot][day] = Strings.errorHighlight(blockedText) +
                            Strings.repeat(" ", columnWidth - blockedText.length());
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

    public String buildJobInterviewCountTable(Company company, int width, int previousDays) {
        StringBuilder report = new StringBuilder();

        ListInterface<JobPosting> jobPostings = getEmployerJobPostings(company);
        if (jobPostings.isEmpty()) return null;

        LocalDate today = Context.getDateTime().toLocalDate();
        LocalDate startDate = today.minusDays(previousDays);
        ListInterface<ScheduledInterview> pastScheduledInterviews = getEmployerScheduledInterviews(company)
                .filter(interview -> {
                    LocalDate interviewDate = interview.getTimeSlot().getDate();
                    return interview.getTimeSlot().isPast() &&
                            !interviewDate.isBefore(startDate) &&
                            !interviewDate.isAfter(today);
                });
        if (pastScheduledInterviews.isEmpty()) return null;
        ListInterface<String> uniqueInterviewedApplicants = new DoublyLinkedList<>();
        ListInterface<Integer> interviewCounts = new DoublyLinkedList<>();
        ListInterface<String> jobTitles = new DoublyLinkedList<>();

        report.append(String.format("%-" + width + "s%n", "Company: " + company.toShortString()));
        report.append(Strings.repeat("-", width)).append("\n");

        report.append(String.format("%-10s %-47s %-40s %-20s%n",
                "Job ID", "Job Title", "Interviewed Applicants", "Interviewed Count"));
        report.append(Strings.repeat("-", width)).append("\n");

        for (JobPosting jobPosting : jobPostings) {
            String jobId = jobPosting.getId();
            String jobTitle = jobPosting.getTitle();
            ListInterface<String> applicants = new DoublyLinkedList<>();
            int count = 0;

            for (ScheduledInterview interview : pastScheduledInterviews) {
                JobApplication app = interview.getJobApplication();
                if (app.getJobPosting().equals(jobPosting)) {
                    String applicantInfo = app.getApplicant().toShortString();
                    if (!applicants.contains(applicantInfo)) {
                        applicants.add(applicantInfo);
                        count++;
                        if (!uniqueInterviewedApplicants.contains(applicantInfo)) {
                            uniqueInterviewedApplicants.add(applicantInfo);
                        }
                    }
                }
            }

            if (count > 0) {
                jobTitles.add(jobTitle + " (" + count + ")");
                interviewCounts.add(count);

                report.append(String.format("%-10s %-47s %-40s %-20s%n",
                        jobId, jobTitle, applicants.isEmpty() ? "" : applicants.get(0), count));

                for (int i = 1; i < applicants.size(); i++) {
                    report.append(String.format("%-10s %-47s %-40s %-20s%n",
                            "", "", applicants.get(i), ""));
                }
                report.append("\n");
            }
        }
        return report.toString();
    }

    public String buildInterviewReportBody(int previousDays) {
        StringBuilder report = new StringBuilder();
        int width = 120;
        report.append(String.format("%" + width + "s\n", "Report Generated For Past (" + previousDays + ") Days"));

        ListInterface<ScheduledInterview> pastInterviews = new DoublyLinkedList<>();
        ListInterface<JobPosting> jobPostings = new DoublyLinkedList<>();

        LocalDate today = Context.getDateTime().toLocalDate();
        LocalDate startDate = today.minusDays(previousDays);

        if (Context.isEmployer()) {
            Company company = Context.getCompany();
            pastInterviews = getEmployerScheduledInterviews(company).filter(interview -> {
                LocalDate interviewDate = interview.getTimeSlot().getDate();
                return interview.getTimeSlot().isPast() &&
                        !interviewDate.isBefore(startDate) &&
                        !interviewDate.isAfter(today);
            });
            jobPostings = getEmployerJobPostings(company);

            String jobInterviewTable = buildJobInterviewCountTable(company, width, previousDays);
            if (jobInterviewTable == null) {
                return report.toString();
            }
            report.append(jobInterviewTable);
        }
        if (Context.isAdmin()) {
            pastInterviews = getAllScheduledInterviews().filter(interview -> {
                LocalDate interviewDate = interview.getTimeSlot().getDate();
                return interview.getTimeSlot().isPast() &&
                        !interviewDate.isBefore(startDate) &&
                        !interviewDate.isAfter(today);
            });
            jobPostings = this.jobPostings;

            ListInterface<Company> companies = Initializer.getCompanies();
            for (Company company : companies) {
                String jobInterviewTable = buildJobInterviewCountTable(company, width, previousDays);
                if (jobInterviewTable == null) {
                    continue;
                }
                report.append(jobInterviewTable);
            }
        }
        if (pastInterviews.size() < 3 || jobPostings.isEmpty()) {
            return null;
        }
        report.append(Strings.repeat("-", width));
        report.append("\nTotal Interview Count: " + pastInterviews.size() + "\n");
        String interviewCountBarChart = buildInterviewCountBarChart(pastInterviews, jobPostings);
        if (interviewCountBarChart == null) {
            return report.toString();
        }
        report.append(interviewCountBarChart);

        String mostAndLeastInterviews = getJobsWithMostAndLeastInterviews(pastInterviews, jobPostings);
        if (mostAndLeastInterviews == null) {
            return report.toString();
        }
        report.append(mostAndLeastInterviews);

        return report.toString();
    }

    public String getJobsWithMostAndLeastInterviews(ListInterface<ScheduledInterview> pastInterviews, ListInterface<JobPosting> jobPostings) {
        class JobInterviewCount {
            JobPosting jobPosting;
            int interviewCount;

            JobInterviewCount(JobPosting jobPosting, int interviewCount) {
                this.jobPosting = jobPosting;
                this.interviewCount = interviewCount;
            }
        }

        ListInterface<JobInterviewCount> jobCounts = new DoublyLinkedList<>();
        StringBuilder result = new StringBuilder();
        result.append("\n");

        for (int i = 0; i < jobPostings.size(); i++) {
            JobPosting job = jobPostings.get(i);
            jobCounts.add(new JobInterviewCount(job, 0));
        }

        for (int i = 0; i < pastInterviews.size(); i++) {
            ScheduledInterview interview = pastInterviews.get(i);
            JobPosting job = interview.getJobApplication().getJobPosting();

            for (int j = 0; j < jobCounts.size(); j++) {
                JobInterviewCount jic = jobCounts.get(j);
                if (jic.jobPosting == job) {
                    jic.interviewCount++;
                    jobCounts.set(j, jic);
                    break;
                }
            }
        }

        int maxCount = 0;
        int minCount = Integer.MAX_VALUE;

        for (int i = 0; i < jobCounts.size(); i++) {
            int count = jobCounts.get(i).interviewCount;
            if (count > maxCount) {
                maxCount = count;
            }
            if (count < minCount) {
                minCount = count;
            }
        }

        result.append("Job(s) with the most past interviews (").append(maxCount).append(" interviews):\n");
        int maxDisplayed = 0;
        boolean hasMaxJobs = false;
        for (int i = 0; i < jobCounts.size() && maxDisplayed < 3; i++) {
            JobInterviewCount jic = jobCounts.get(i);
            if (jic.interviewCount == maxCount) {
                if (maxDisplayed == 0) {
                    result.append("< ");
                } else {
                    result.append(" | ");
                }
                result.append(jic.jobPosting.toShortString());
                maxDisplayed++;
                hasMaxJobs = true;
            }
        }
        if (hasMaxJobs) {
            result.append(" >");
        }
        result.append("\n");

        result.append("\nJob(s) with the least past interviews (").append(minCount).append(" interviews):\n");
        int minDisplayed = 0;
        boolean hasMinJobs = false;
        for (int i = 0; i < jobCounts.size() && minDisplayed < 3; i++) {
            JobInterviewCount jic = jobCounts.get(i);
            if (jic.interviewCount == minCount) {
                if (minDisplayed == 0) {
                    result.append("< ");
                } else {
                    result.append(" | ");
                }
                result.append(jic.jobPosting.toShortString());
                minDisplayed++;
                hasMinJobs = true;
            }
        }
        if (hasMinJobs) {
            result.append(" >");
        }
        result.append("\n");

        return result.toString();
    }

    public String buildInterviewCountBarChart(ListInterface<ScheduledInterview> pastInterviews, ListInterface<JobPosting> jobPostings) {
        class JobInterviewCount {
            JobPosting jobPosting;
            int interviewCount;

            JobInterviewCount(JobPosting jobPosting, int interviewCount) {
                this.jobPosting = jobPosting;
                this.interviewCount = interviewCount;
            }
        }

        ListInterface<JobInterviewCount> jobCounts = new DoublyLinkedList<>();

        for (int i = 0; i < jobPostings.size(); i++) {
            JobPosting job = jobPostings.get(i);
            jobCounts.add(new JobInterviewCount(job, 0));
        }

        for (int i = 0; i < pastInterviews.size(); i++) {
            ScheduledInterview interview = pastInterviews.get(i);
            JobPosting job = interview.getJobApplication().getJobPosting();

            for (int j = 0; j < jobCounts.size(); j++) {
                JobInterviewCount jic = jobCounts.get(j);
                if (jic.jobPosting == job) {
                    jic.interviewCount++;
                    jobCounts.set(j, jic);
                    break;
                }
            }
        }

        jobCounts.sort((jic1, jic2) -> Integer.compare(jic2.interviewCount, jic1.interviewCount));
        ListInterface<String> categories = new DoublyLinkedList<>();
        ListInterface<Integer> values = new DoublyLinkedList<>();

        for (int i = 0; i < jobCounts.size(); i++) {
            JobInterviewCount jic = jobCounts.get(i);

            String category = jic.jobPosting.getTitle() + " (" + jic.interviewCount + ")";
            categories.add(category);
            values.add(jic.interviewCount);
        }

        String chart = Chart.barChart(
                categories,
                values,
                "Past Interviews per Job Posting",
                120,
                '█',
                true
        );

        return chart;
    }

    private String buildRecruitmentReportBody(int previousDays) {
        StringBuilder report = new StringBuilder();
        int width = 120;
        report.append(String.format("%" + width + "s", "Report Generated For Past (" + previousDays + ") Days"));

        if (Context.isEmployer()) {
            report.append(buildRecruitmentTable(Context.getCompany()));
        }
        if (Context.isAdmin()) {
            ListInterface<Company> companies = Initializer.getCompanies();
            for (Company company : companies) {
                report.append(buildRecruitmentTable(company));
            }
        }
        report.append(buildCountByApplicationStatus());
        report.append("\n");
        report.append(buildRecruitmentBarChart());
        report.append("\n");
        report.append(getJobsWithMostAndLeastAccepted());
        return report.toString();
    }

    public String getJobsWithMostAndLeastAccepted() {
        ListInterface<JobPosting> jobs = new DoublyLinkedList<>();
        ListInterface<JobApplication> applications = new DoublyLinkedList<>();

        if (Context.isEmployer()) {
            jobs = getEmployerJobPostings();
            applications = getEmployerJobApplications();
        }
        if (Context.isAdmin()) {
            jobs = new DoublyLinkedList<>(jobPostings);
            applications = getAllJobApplications();
        }

        class JobAccepted {
            JobPosting job;
            int acceptedCount;

            JobAccepted(JobPosting job, int count) {
                this.job = job;
                this.acceptedCount = count;
            }
        }

        ListInterface<JobAccepted> jobAcceptedList = new DoublyLinkedList<>();
        for (int i = 0; i < jobs.size(); i++) {
            JobPosting job = jobs.get(i);
            int accepted = applications.filter(app ->
                    app.getJobPosting().equals(job) &&
                            app.getStatus() == JobApplication.Status.ACCEPTED).size();
            jobAcceptedList.add(new JobAccepted(job, accepted));
        }

        int max = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < jobAcceptedList.size(); i++) {
            int count = jobAcceptedList.get(i).acceptedCount;
            if (count > max) max = count;
            if (count < min) min = count;
        }

        ListInterface<JobPosting> maxJobs = new DoublyLinkedList<>();
        ListInterface<JobPosting> minJobs = new DoublyLinkedList<>();

        for (int i = 0; i < jobAcceptedList.size(); i++) {
            JobAccepted entry = jobAcceptedList.get(i);
            if (entry.acceptedCount == max) {
                maxJobs.add(entry.job);
            }
            if (entry.acceptedCount == min) {
                minJobs.add(entry.job);
            }
        }
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Job(s) with the most ACCEPTED applicants (%d accepted):\n", max));
        if (!maxJobs.isEmpty()) {
            sb.append("< ");
            for (int i = 0; i < maxJobs.size() && i < 5; i++) {
                JobPosting job = maxJobs.get(i);
                sb.append(job.getId()).append(", ").append(job.getTitle());
                if (i < maxJobs.size() - 1 && i < 4) sb.append(" | ");
            }
            if (maxJobs.size() > 5) {
                sb.append(" ...");
            }
            sb.append(" >\n\n");
        }

        sb.append(String.format("Job(s) with the least ACCEPTED applicants (%d accepted):\n", min));
        if (!minJobs.isEmpty()) {
            sb.append("< ");
            for (int i = 0; i < minJobs.size() && i < 5; i++) {
                JobPosting job = minJobs.get(i);
                sb.append(job.getId()).append(", ").append(job.getTitle());
                if (i < minJobs.size() - 1 && i < 4) sb.append(" | ");
            }
            if (minJobs.size() > 5) {
                sb.append(" ...");
            }
            sb.append(" >\n");
        }

        return sb.toString();
    }

    public String buildRecruitmentBarChart() {
        class Entry {
            String category;
            int count;

            Entry(String category, int count) {
                this.category = category;
                this.count = count;
            }
        }

        ListInterface<Entry> entries = new DoublyLinkedList<>();

        if (Context.isEmployer()) {
            Company company = Context.getCompany();
            ListInterface<JobPosting> jobPostings = getEmployerJobPostings(company);
            ListInterface<JobApplication> applications = getEmployerJobApplications(company);

            for (int i = 0; i < jobPostings.size(); i++) {
                JobPosting job = jobPostings.get(i);
                String title = job.getTitle();
                int acceptedCount = applications.filter(app ->
                        app.getJobPosting().equals(job) &&
                                app.getStatus() == JobApplication.Status.ACCEPTED).size();

                entries.add(new Entry(title, acceptedCount));
            }
        }

        if (Context.isAdmin()) {
            ListInterface<Company> companies = Initializer.getCompanies();
            for (Company company : companies) {
                ListInterface<JobPosting> jobPostings = getEmployerJobPostings(company);
                ListInterface<JobApplication> applications = getEmployerJobApplications(company);

                for (int i = 0; i < jobPostings.size(); i++) {
                    JobPosting job = jobPostings.get(i);
                    String title = company.toShortString() + " - " + job.getTitle();
                    int acceptedCount = applications.filter(app ->
                            app.getJobPosting().equals(job) &&
                                    app.getStatus() == JobApplication.Status.ACCEPTED).size();

                    entries.add(new Entry(title, acceptedCount));
                }
            }
        }

        // Sort entries in descending order of count
        entries.sort((e1, e2) -> Integer.compare(e2.count, e1.count));

        // Split back into separate category and count lists
        ListInterface<String> acceptedCategories = new DoublyLinkedList<>();
        ListInterface<Integer> acceptedCounts = new DoublyLinkedList<>();

        for (int i = 0; i < entries.size(); i++) {
            Entry entry = entries.get(i);
            acceptedCategories.add(entry.category);
            acceptedCounts.add(entry.count);
        }

        return Chart.barChart(
                acceptedCategories,
                acceptedCounts,
                "Accepted Applications by Job",
                120,
                '█',
                true
        );
    }

    public String buildCountByApplicationStatus() {
        ListInterface<JobApplication> applications = new DoublyLinkedList<>();

        if (Context.isEmployer()) {
            applications = getEmployerJobApplications();
        }
        if (Context.isAdmin()) {
            applications = getAllJobApplications();
        }

        int accepted = applications.filter(app -> app.getStatus() == JobApplication.Status.ACCEPTED).size();
        int rejected = applications.filter(app -> app.getStatus() == JobApplication.Status.REJECTED).size();
        int withdrawn = applications.filter(app -> app.getStatus() == JobApplication.Status.WITHDRAWN).size();
        return String.format(
                "Total ACCEPTED Count: %d\nTotal REJECTED Count: %d\nTotal WITHDRAWN Count: %d",
                accepted, rejected, withdrawn
        );
    }

    public String buildRecruitmentTable(Company company) {
        StringBuilder report = new StringBuilder();
        ListInterface<JobApplication> applications = getEmployerJobApplications(company);
        if (applications.isEmpty()) return report.toString();
        report.append("\nCompany: " + company.toShortString() + "\n");
        report.append(this.buildJobApplicationStatusTable(applications));
        return report.toString();
    }

    public String buildJobApplicationStatusTable(ListInterface<JobApplication> applications) {
        StringBuilder sb = new StringBuilder();
        sb.append("------------------------------------------------------------------------------------------------------------------------\n");
        sb.append(String.format("%-10s %-46s %-40s %-20s\n", "Job ID", "Job Title", "Applicants", "Status"));
        sb.append("------------------------------------------------------------------------------------------------------------------------\n");

        ListInterface<JobApplication> terminatedApplications = applications.filter(JobApplication::isTerminated);
        DoublyLinkedList<String> processedJobIds = new DoublyLinkedList<>();

        for (JobApplication app1 : terminatedApplications) {
            String jobId = app1.getJobPosting().getId();
            if (processedJobIds.contains(jobId)) {
                continue;
            }

            processedJobIds.add(jobId);
            String jobTitle = app1.getJobPosting().getTitle();

            ListInterface<JobApplication> matchingApps = terminatedApplications.filter(app2 ->
                    app2.getJobPosting().getId().equals(jobId)
            );
            boolean isFirstRow = true;
            for (JobApplication app : matchingApps) {
                String jobIdCol = isFirstRow ? String.format("%-10s ", jobId) : String.format("%-10s ", "");
                String jobTitleCol = isFirstRow ? String.format("%-46s ", jobTitle) : String.format("%-46s ", "");
                String applicantCol = String.format("%-40s ", app.getApplicant().toShortString());
                String statusCol = String.format("%-20s", app.getStatus().toString());

                sb.append(jobIdCol)
                        .append(jobTitleCol)
                        .append(applicantCol)
                        .append(statusCol)
                        .append("\n");

                isFirstRow = false;
            }

            sb.append("\n");
        }

        sb.append("------------------------------------------------------------------------------------------------------------------------\n");
        return sb.toString();
    }
}