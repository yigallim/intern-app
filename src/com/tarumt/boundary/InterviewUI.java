/**
 * @author Lim Yuet Yang
 */

package com.tarumt.boundary;

import com.tarumt.adt.list.ListInterface;
import com.tarumt.control.InterviewController;
import com.tarumt.control.JobApplicationController;
import com.tarumt.entity.Company;
import com.tarumt.entity.JobApplication;
import com.tarumt.entity.interview.ScheduledInterview;
import com.tarumt.entity.interview.TimeSlot;
import com.tarumt.entity.interview.Invitation;
import com.tarumt.entity.interview.BlockedTimeSlot;

import com.tarumt.utility.common.*;
import com.tarumt.utility.pretty.TabularPrint;
import com.tarumt.utility.search.FuzzySearch;
import com.tarumt.utility.validation.*;

import java.lang.reflect.Field;

public class InterviewUI {
    private final Input input;

    public InterviewUI(Input input) {
        this.input = input;
    }

    public void menu() {
        InterviewController interviewController = InterviewController.getInstance();
        JobApplicationController applicationController = JobApplicationController.getInstance();
        new Menu()
                .header("==> Manage Interviews & Applications <==")
                .choice(
                        new Menu.Choice("📄 Display Job Applications", applicationController::displayJobApplication),
                        new Menu.Choice("📨 Display Interview Invitation", interviewController::displayInvitation),
                        new Menu.Choice("📅 Display All Interview", interviewController::displayAllInterview),
                        new Menu.Choice("📜 Display Past Interview", interviewController::displayPastInterview),
                        new Menu.Choice("⏳ Display Future Interview", interviewController::displayIncomingInterview),
                        new Menu.Choice("🔎 Search All Interview", interviewController::searchAllInterview),
                        new Menu.Choice("📝 Interview Report", interviewController::interviewReport),
                        new Menu.Choice("📝 Recruitment Report", interviewController::recruitmentReport)
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
        System.out.println();
    }

    public void printAllInterviews(ListInterface<ScheduledInterview> scheduledInterviews) {
        if (scheduledInterviews == null || scheduledInterviews.isEmpty()) {
            Log.info("No interview to display");
            input.clickAnythingToContinue();
            return;
        }

        Log.info("Displaying " + scheduledInterviews.size() + " interviews");
        TabularPrint.printTabular(scheduledInterviews, true, "default", "admin");
        input.clickAnythingToContinue();
    }

    public void accessEmployerMenu() {
        InterviewController interviewController = InterviewController.getInstance();

        new Menu()
                .header("==> Manage Interviews <==")
                .choice(
                        new Menu.Choice("📨 Manage Interview Invitations", interviewController::accessEmployerInvitation),
                        new Menu.Choice("📅 Display Incoming Interview", interviewController::displayIncomingInterview),
                        new Menu.Choice("📜 Display Past Interview", interviewController::displayPastInterview),
                        new Menu.Choice("🔎 Search All Interview", interviewController::searchAllInterview),
                        new Menu.Choice("⭐ Rate Completed Interviews", interviewController::rateCompletedInterviews),
                        new Menu.Choice("🕒 View Availability", interviewController::viewAvailability),
                        new Menu.Choice("📝 Modify Availability", interviewController::modifyAvailability),
                        new Menu.Choice("❌ Cancel Scheduled Interview", interviewController::cancelScheduledInterview),
                        new Menu.Choice("📝 Interview Report", interviewController::interviewReport),
                        new Menu.Choice("📝 Recruitment Report", interviewController::recruitmentReport)
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
        System.out.println();
    }

    public void accessEmployerInvitationMenu() {
        InterviewController interviewController = InterviewController.getInstance();

        new Menu()
                .header("==> Manage Interview Invitations <==")
                .choice(
                        new Menu.Choice("📨 Send Interview Invitation", interviewController::sendInvitation),
                        new Menu.Choice("🔎 Display Interview Invitation", interviewController::displayInvitation),
                        new Menu.Choice("❌ Cancel Invitation", interviewController::cancelInvitation)
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
        System.out.println();
    }

    public void printNoApplicationToInviteMsg() {
        Log.info("No applications available to invite");
        input.clickAnythingToContinue();
    }

    public JobApplication getReadyForInterviewApplication(ListInterface<JobApplication> applications) {
        System.out.println("<== Send Interview Invitation [ X to Exit ] ==>");
        return input.getObjectFromList("|\n| Select Job Application To Invite => ", applications, 80, 2);
    }

    public void printSearchInterviewMsg(ListInterface<ScheduledInterview> interviews) {
        if (interviews == null || interviews.isEmpty()) {
            Log.info("No interviews to search");
            input.clickAnythingToContinue();
            return;
        }
        System.out.println("<== Search All Interviews [ X to Exit ] ==>");
    }

    public String getSearchInterviewQuery() {
        StringCondition condition = ConditionFactory.string().min(1).max(50);
        return input.getString("| Search Keyword => ", condition);
    }

    public void printSearchResult(FuzzySearch.Result<ScheduledInterview> result) {
        ListInterface<ScheduledInterview> matchedInterviews = result.getSubList();
        ListInterface<String> matches = result.getMatches();
        System.out.println();

        if (matchedInterviews.isEmpty()) {
            Log.info("No interviews matched the search criteria");
        } else {
            System.out.println(matches.size() + " Relevant Results => " + matches + "\n");
            Log.info("Displaying " + matchedInterviews.size() + " interviews");

            String[] excludeKeys;
            if (Context.isEmployer()) {
                excludeKeys = new String[]{"default", "employer"};
            } else if (Context.isApplicant()) {
                excludeKeys = new String[]{"default", "applicant"};
            } else {
                excludeKeys = new String[]{"default", "admin"};
            }

            TabularPrint.printTabular(matchedInterviews, true, matches, excludeKeys);
        }

        input.clickAnythingToContinue();
        System.out.println();
    }

    public ScheduledInterview getInterviewToRate(ListInterface<ScheduledInterview> completedInterviews) {
        if (completedInterviews == null || completedInterviews.isEmpty()) {
            Log.info("No completed interviews available to rate.");
            input.clickAnythingToContinue();
            return null;
        }
        System.out.println("<== Rate Completed Interview [ X to Exit ] ==>");
        return input.getObjectFromList("|\n| Select Interview To Rate => ", completedInterviews, 80, 2);
    }

    public int getRatingInput() {
        IntegerCondition condition = ConditionFactory.integer().min(0).max(10);
        return input.getInt("| Enter Rating (0 - 10) => ", condition);
    }

    public void printSuccessRateInterviewMsg(ScheduledInterview interview) {
        System.out.println();
        Log.info("Successfully rated interview " + interview.getId() + " for applicant " + interview.getJobApplication().getApplicant().toShortString() + " with a score of " + interview.getRating());
        input.clickAnythingToContinue();
    }

    public void modifyAvailabilityMenu() {
        InterviewController interviewController = InterviewController.getInstance();

        new Menu()
                .header("==> Modify Availability <==")
                .choice(
                        new Menu.Choice("➕ Add Blocked Slot", interviewController::addBlockedSlot),
                        new Menu.Choice("👁️ Display Blocked Slot", interviewController::displayBlockedSlot),
                        new Menu.Choice("❌ Remove Blocked Slot", interviewController::removeBlockedSlot)
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
        System.out.println();
    }

    public void printBlockedSlots(ListInterface<BlockedTimeSlot> blockedSlots) {
        if (blockedSlots == null || blockedSlots.isEmpty()) {
            printNoBlockedSlotsMsg();
            return;
        }

        Log.info("Displaying " + blockedSlots.size() + " blocked time slots:");
        System.out.println();

        String header = String.format("| %-3s | %-15s | %-25s |", "No.", "Date", "Time Range");
        String divider = String.format(Strings.repeat("-", 53));

        System.out.println(divider);
        System.out.println(header);
        System.out.println(divider);

        for (int i = 0; i < blockedSlots.size(); i++) {
            BlockedTimeSlot uts = blockedSlots.get(i);
            System.out.printf("| %-3d | %-15s | %-25s |\n", i + 1, uts.getSlot().getDate(), uts.getSlot().getTimeRangeString());
        }

        System.out.println(divider);
        input.clickAnythingToContinue();
    }

    public BlockedTimeSlot getBlockedSlotToRemove(ListInterface<BlockedTimeSlot> blockedSlots) {
        System.out.println("<== Remove Blocked Slot [ X to Exit ] ==>");
        return input.getObjectFromList("Select a blocked slot to remove => ", blockedSlots, 46, 2);
    }

    public boolean confirmRemoveBlockedSlot(TimeSlot slot) {
        return input.confirm("Confirm removing block for slot: " + slot.getDate() + " " + slot.getTimeRangeString() + "? [ Y / N ] => ");
    }

    public void printSuccessAddBlockedSlotMsg(TimeSlot slot) {
        System.out.println();
        Log.info("Successfully blocked time slot: " + slot.getDate() + " " + slot.getTimeRangeString());
        input.clickAnythingToContinue();
    }

    public void printSlotAlreadyBlockedMsg(TimeSlot slot) {
        System.out.println();
        Log.warn("Time slot " + slot.getDate() + " " + slot.getTimeRangeString() + " is already blocked.");
        input.clickAnythingToContinue();
    }

    public void printNoBlockedSlotsMsg() {
        Log.info("You have no manually blocked time slots.");
        input.clickAnythingToContinue();
    }

    public void printSuccessRemoveBlockedSlotMsg(TimeSlot slot) {
        System.out.println();
        Log.info("Successfully unblocked time slot: " + slot.getDate() + " " + slot.getTimeRangeString());
        input.clickAnythingToContinue();
    }

    public void accessApplicantMenu() {
        InterviewController interviewController = InterviewController.getInstance();

        new Menu()
                .header("==> Manage Interviews <==")
                .choice(
                        new Menu.Choice("📨 Display Interview Invitation", interviewController::displayInvitation),
                        new Menu.Choice("✅ Accept Interview Invitation", interviewController::acceptInvitation),
                        new Menu.Choice("📅 Display Incoming Interview", interviewController::displayIncomingInterview),
                        new Menu.Choice("📜 Display Past Interview", interviewController::displayPastInterview),
                        new Menu.Choice("🔎 Search All Interview", interviewController::searchAllInterview),
                        new Menu.Choice("🔃 Reschedule Interview", interviewController::rescheduleInterview),
                        new Menu.Choice("❌ Cancel Scheduled Interview", interviewController::cancelScheduledInterview)
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
        System.out.println();
    }

    public String getRemarks() {
        Field field = ValidationFieldReflection.getField(Invitation.class, "remarks");
        StringCondition condition = (StringCondition) ConditionFactory.fromField(field);
        return input.getString("| Remarks => ", condition);
    }

    public void printSuccessInviteMsg() {
        System.out.println();
        Log.info("Interview invitation sent");
    }

    public void printInvitation(ListInterface<Invitation> invitations) {
        if (invitations == null || invitations.isEmpty()) {
            Log.info("No invitation to display");
            input.clickAnythingToContinue();
            return;
        }

        Log.info("Displaying " + invitations.size() + " invitations");
        String[] excludeKeys;
        if (Context.isEmployer()) {
            excludeKeys = new String[]{"default", "employer"};
        } else if (Context.isApplicant()) {
            excludeKeys = new String[]{"default", "applicant"};
        } else {
            excludeKeys = new String[]{"default"};
        }
        TabularPrint.printTabular(invitations, true, excludeKeys);
        input.clickAnythingToContinue();
    }

    public Invitation getInvitationToAccept(ListInterface<Invitation> invitations) {
        if (invitations == null || invitations.isEmpty()) {
            Log.info("No invitation to accept");
            input.clickAnythingToContinue();
            return null;
        }
        System.out.println("<== Accept Interview Invitation [X to Exit] ==>");
        return input.getObjectFromList("|\n| Select Invitation To Accept => ", invitations, 80, 2);
    }

    public void printFullyOccupiedMsg(Company company) {
        System.out.println();
        Log.warn("Company => " + company.toShortString() + " <= timeslot is fully occupied, try again next day.");
    }

    public void printAvailableTimeSlot(String calendarAvailabilityView) {
        System.out.println();
        Log.info("Available Time Slots (9 AM to 6 PM)");
        System.out.println(calendarAvailabilityView);
    }

    public int getTimeSlot(int slotCount) {
        IntegerCondition condition = ConditionFactory.integer().min(1).max(slotCount);
        System.out.println("| Available Time Slots (0 - " + slotCount + ")");
        System.out.println("|");
        return input.getInt("| Select a Time Slot => ", condition);
    }

    public void printSuccessAcceptInvitationMsg(TimeSlot timeSlot) {
        System.out.println();
        Log.info("Successfully accepted the interview invitation");
        Log.info("The interview is scheduled for:");
        System.out.println(timeSlot);
    }

    public void printCalendarTimeSlot(String calendarView) {
        Log.info("Your Scheduled Interview Time Slots (9 AM to 6 PM)");
        System.out.println(calendarView);
    }

    public void printScheduledInterviews(ListInterface<ScheduledInterview> scheduledInterviews) {
        if (scheduledInterviews == null || scheduledInterviews.isEmpty()) {
            Log.info("No scheduled interview to display");
            input.clickAnythingToContinue();
            return;
        }

        Log.info("Displaying " + scheduledInterviews.size() + " scheduled interviews");
        String[] excludeKeys;
        if (Context.isEmployer()) {
            excludeKeys = new String[]{"default", "employer"};
        } else if (Context.isApplicant()) {
            excludeKeys = new String[]{"default", "applicant"};
        } else {
            excludeKeys = new String[]{"default", "admin"};
        }
        TabularPrint.printTabular(scheduledInterviews, true, excludeKeys);
        input.clickAnythingToContinue();
    }

    public void printPastInterviews(ListInterface<ScheduledInterview> pastInterviews) {
        if (pastInterviews == null || pastInterviews.isEmpty()) {
            Log.info("No past interview to display");
            input.clickAnythingToContinue();
            return;
        }

        Log.info("Displaying " + pastInterviews.size() + " past interviews");
        String[] excludeKeys;
        if (Context.isEmployer()) {
            excludeKeys = new String[]{"default", "employer"};
        } else if (Context.isApplicant()) {
            excludeKeys = new String[]{"default", "applicant"};
        } else {
            excludeKeys = new String[]{"default", "admin"};
        }
        TabularPrint.printTabular(pastInterviews, true, excludeKeys);
        input.clickAnythingToContinue();
    }

    public ScheduledInterview getInterviewToReschedule(ListInterface<ScheduledInterview> scheduledInterviews) {
        if (scheduledInterviews == null || scheduledInterviews.isEmpty()) {
            printNoInterviewToRescheduleMsg();
            return null;
        }
        System.out.println("<== Reschedule Interview [ X to Exit ] ==>");
        return input.getObjectFromList("|\n| Select Interview To Reschedule => ", scheduledInterviews, 80, 2);
    }

    public void printNoInterviewToRescheduleMsg() {
        Log.info("No upcoming interviews available to reschedule.");
        input.clickAnythingToContinue();
    }

    public boolean confirmRescheduleInterview() {
        return input.confirm("Confirm to reschedule this interview? [ Y / X ] => ");
    }

    public void printSuccessRescheduleMsg(TimeSlot newTimeSlot) {
        System.out.println();
        Log.info("Interview successfully rescheduled.");
        Log.info("The interview is now scheduled for:");
        System.out.println(newTimeSlot);
        input.clickAnythingToContinue();
    }

    public ScheduledInterview getInterviewToCancel(ListInterface<ScheduledInterview> scheduledInterview) {
        if (scheduledInterview == null || scheduledInterview.isEmpty()) {
            Log.info("No scheduled interviews to cancel");
            input.clickAnythingToContinue();
            return null;
        }
        System.out.println("<== Cancel Scheduled Interview [ X to Exit ] ==>");
        return input.getObjectFromList("|\n| Select Interview To Cancel => ", scheduledInterview, 80, 2);
    }

    public boolean confirmCancelInterview() {
        return input.confirm("Confirm to cancel scheduled interview? [ Y / X ] => ");
    }

    public void printSuccessCancelInterviewMsg() {
        System.out.println();
        Log.info("Scheduled interview successfully cancelled");
        input.clickAnythingToContinue();
    }

    public Invitation getInvitationToCancel(ListInterface<Invitation> invitations) {
        if (invitations == null || invitations.isEmpty()) {
            Log.info("No pending invitations to cancel");
            input.clickAnythingToContinue();
            return null;
        }
        System.out.println("<== Cancel Interview Invitation [ X to Exit ] ==>");
        return input.getObjectFromList("|\n| Select Invitation To Cancel => ", invitations, 80, 2);
    }

    public boolean confirmCancelInvitation() {
        return input.confirm("Confirm to cancel this interview invitation? [ Y / X ] => ");
    }

    public void printSuccessCancelInvitationMsg() {
        System.out.println();
        Log.info("Interview invitation successfully cancelled");
        input.clickAnythingToContinue();
    }

    public void printReport(String body) {
        System.out.println();
        if (body == null) {
            Log.info("Not enough record to generate record");
            return;
        }
        System.out.println(Report.buildReportHeader(120, "INTERVIEW SCHEDULING MODULE", "PAST INTERVIEW ANALYSIS REPORT"));
        System.out.println(body);
        System.out.println(Report.buildReportFooter(120));
        input.clickAnythingToContinue();
    }

    public int getPreviousDay() {
        System.out.println("<== Generate Report [ X to Exit ] ==>");
        System.out.println("|");
        IntegerCondition condition = ConditionFactory.integer().min(1).max(365);
        return input.getInt("| Past Days (n) => ", condition);
    }
}