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
    private final InterviewUI interviewUI;

    private InterviewController() {
        Input input = new Input();
        this.invitations = Initializer.getInvitations();
        this.scheduledInterviews = Initializer.getScheduledInterviews();
        this.blockedTimeSlots = Initializer.getBlockedTimeSlots();
        this.jobApplications = Initializer.getJobApplications();
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
        if (Context.isAdmin())
            invitations = this.invitations;

        this.interviewUI.printInvitation(invitations);
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

        if (!Context.isAdmin()) {
            String calendarView = getCalendarAvailabilityView(bookedSlots, blockedSlots, false);
            if (!interviews.isEmpty() || !blockedSlots.isEmpty()) {
                this.interviewUI.printCalendarTimeSlot(calendarView);
            }
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
        if (Context.isAdmin()) {
            pastInterviews = getAllScheduledInterviews()
                    .filter(interview -> interview.getTimeSlot().isPast());
        }

        this.interviewUI.printPastInterviews(pastInterviews);
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

            FuzzySearch.Result<ScheduledInterview> result = FuzzySearch.searchList(
                    ScheduledInterview.class,
                    accessibleInterviews,
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

    public void report() {
        this.interviewUI.printReportHeader(120);
        System.out.println(this.buildReportBody());
        this.interviewUI.printReportFooter(120);
    }

    public void rescheduleInterview() {
        // This is an applicant-only function
        Applicant applicant = Context.getApplicant();
        if (applicant == null) return; // Should not happen

        ListInterface<ScheduledInterview> applicantUpcomingInterviews = getApplicantScheduledInterviews()
                .filter(interview -> !interview.getTimeSlot().isPast());

        ScheduledInterview interviewToReschedule = interviewUI.getInterviewToReschedule(applicantUpcomingInterviews);
        if (interviewToReschedule == null) return;

        if (!interviewUI.confirmRescheduleInterview()) return;

        Company company = interviewToReschedule.getJobApplication().getJobPosting().getCompany();
        TimeSlot oldTimeSlot = interviewToReschedule.getTimeSlot();

        // Get company's blocked slots
        ListInterface<TimeSlot> companyBlockedSlots = getEmployerBlockedSlots(company).map(BlockedTimeSlot::getSlot);

        // Get company's other booked slots (excluding the one being rescheduled)
        ListInterface<TimeSlot> companyOtherBookedSlots = getEmployerScheduledInterviews(company)
                .filter(interview -> !interview.getTimeSlot().isPast() && !interview.getTimeSlot().equals(oldTimeSlot))
                .map(ScheduledInterview::getTimeSlot);

        // Get applicant's other booked slots (excluding the one being rescheduled)
        ListInterface<TimeSlot> applicantOtherBookedSlots = applicantUpcomingInterviews
                .filter(interview -> !interview.getTimeSlot().equals(oldTimeSlot))
                .map(ScheduledInterview::getTimeSlot);

        // Combine relevant unavailable slots
        ListInterface<TimeSlot> combinedBookedSlots = new DoublyLinkedList<>();
        combinedBookedSlots.merge(companyOtherBookedSlots);
        combinedBookedSlots.merge(applicantOtherBookedSlots);
        // No need to merge blocked slots if using overloaded methods below

        // Check availability considering other bookings and company blocks
        if (isWeekTimeSlotOccupied(combinedBookedSlots, companyBlockedSlots)) {
            interviewUI.printFullyOccupiedMsg(company); // Or a more specific message
            return;
        }

        // Get available slots considering other bookings and company blocks
        ListInterface<TimeSlot> availableTimeSlots = getAvailableTimeSlot(combinedBookedSlots, companyBlockedSlots);
        // Generate calendar view showing other bookings and company blocks
        String calendarView = getCalendarAvailabilityView(combinedBookedSlots, companyBlockedSlots, true);

        interviewUI.printAvailableTimeSlot(calendarView);
        int timeSlotInput = interviewUI.getTimeSlot(availableTimeSlots.size());
        if (timeSlotInput == Input.INT_EXIT_VALUE) return;

        TimeSlot newTimeSlot = availableTimeSlots.get(timeSlotInput - 1);

        // Update the interview's time slot
        interviewToReschedule.setTimeSlot(newTimeSlot);
        // Optionally update bookedAt timestamp?
        // interviewToReschedule.setBookedAt(Context.getDateTime());

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

    public String buildReportBody() {
        StringBuilder report = new StringBuilder();
        int width = 120;
        Company currentCompany = Context.getCompany();

        report.append(String.format("%-" + width + "s%n", "Company: " + currentCompany.getName() + ", " + currentCompany.toShortString()));
        report.append(Strings.repeat("-", width)).append("\n");

        ListInterface<JobPosting> jobPostings = new DoublyLinkedList<>();
        ListInterface<Integer> interviewCounts = new DoublyLinkedList<>();
        ListInterface<String> jobTitles = new DoublyLinkedList<>();

        for (ScheduledInterview interview : scheduledInterviews) {
            JobPosting jobPosting = interview.getJobApplication().getJobPosting();
            if (jobPosting.getCompany().equals(currentCompany) && !jobPostings.contains(jobPosting)) {
                jobPostings.add(jobPosting);
            }
        }

        report.append(String.format("%-10s %-47s %-40s %-20s%n",
                "Job ID", "Job Title", "Interviewed Applicants", "Interviewed Count"));
        report.append(Strings.repeat("-", width)).append("\n");

        ListInterface<String> uniqueApplicants = new DoublyLinkedList<>();
        ListInterface<String> uniqueInterviewedApplicants = new DoublyLinkedList<>();

        for (JobPosting jobPosting : jobPostings) {
            String jobId = jobPosting.getId();
            String jobTitle = jobPosting.getTitle();
            ListInterface<String> applicants = new DoublyLinkedList<>();
            int count = 0;

            for (ScheduledInterview interview : scheduledInterviews) {
                JobApplication app = interview.getJobApplication();
                if (app.getJobPosting().equals(jobPosting)) {
                    String applicantName = app.getApplicant().getName();
                    if (!applicants.contains(applicantName)) {
                        applicants.add(applicantName);
                        count++;
                        if (!uniqueInterviewedApplicants.contains(applicantName)) {
                            uniqueInterviewedApplicants.add(applicantName);
                        }
                    }
                }
            }

            jobTitles.add(jobTitle + " (" + count + ")");
            interviewCounts.add(count);

            report.append(String.format("%-10s %-47s %-40s %-20s%n",
                    jobId, jobTitle, applicants.isEmpty() ? "" : "a1, " + applicants.get(0), count));

            for (int i = 1; i < applicants.size(); i++) {
                String applicant = applicants.get(i);
                String applicantId = "a" + (i + 1);
                report.append(String.format("%-10s %-47s %-40s %-20s%n",
                        "", "", applicantId + ", " + applicant, ""));
            }

            report.append("\n");
        }

        for (JobApplication app : jobApplications) {
            if (app.getJobPosting().getCompany().equals(currentCompany)) {
                String applicantName = app.getApplicant().getName();
                if (!uniqueApplicants.contains(applicantName)) {
                    uniqueApplicants.add(applicantName);
                }
            }
        }

        int totalApplicants = uniqueApplicants.size();
        int totalInterviews = uniqueInterviewedApplicants.size();
        report.append(Strings.repeat("-", width)).append("\n");
        report.append(String.format("%-" + width + "s%n", "Total Job Count: " + totalApplicants));
        report.append(String.format("%-" + width + "s%n", "Total Applicant Count: " + totalApplicants));
        report.append(String.format("%-" + width + "s%n", "Total Interview Count: " + totalInterviews));
        report.append(Chart.barChart(jobTitles, interviewCounts, "Interview Counts by Job Posting", width, '█', true));
        report.append("\n");

        if (!jobPostings.isEmpty()) {
            JobPosting mostInterviewed = jobPostings.get(0);
            JobPosting leastInterviewed = jobPostings.get(0);
            int maxCount = interviewCounts.get(0);
            int minCount = interviewCounts.get(0);

            for (int i = 1; i < jobPostings.size(); i++) {
                int count = interviewCounts.get(i);
                if (count > maxCount) {
                    maxCount = count;
                    mostInterviewed = jobPostings.get(i);
                }
                if (count < minCount) {
                    minCount = count;
                    leastInterviewed = jobPostings.get(i);
                }
            }

            report.append(String.format("%-" + width + "s%n",
                    "Most Interviewed Job Posting (" + maxCount + "):"));
            report.append(String.format("%-" + width + "s%n",
                    "< " + mostInterviewed.toShortString() + " >"));
            report.append("\n");
            report.append(String.format("%-" + width + "s%n",
                    "Least Interviewed Job Posting (" + minCount + "):"));
            report.append(String.format("%-" + width + "s%n",
                    "< " + leastInterviewed.toShortString() + " >"));
        } else {
            report.append(String.format("%-" + width + "s%n", "No interviews available."));
        }

        return report.toString();
    }

}
