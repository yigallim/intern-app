package com.tarumt.boundary;

import com.tarumt.adt.list.ListInterface;
import com.tarumt.control.InterviewService;
import com.tarumt.entity.Company;
import com.tarumt.entity.JobApplication;
import com.tarumt.entity.interview.ScheduledInterview;
import com.tarumt.entity.interview.TimeSlot;
import com.tarumt.entity.interview.Invitation;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Menu;
import com.tarumt.utility.pretty.TabularPrint;
import com.tarumt.utility.validation.ConditionFactory;
import com.tarumt.utility.validation.IntegerCondition;
import com.tarumt.utility.validation.StringCondition;
import com.tarumt.utility.validation.ValidationFieldReflection;

import java.lang.reflect.Field;

public class InterviewUI {
    private Input input;

    public InterviewUI(Input input) {
        this.input = input;
    }

    public void accessEmployerMenu() {
        InterviewService interviewService = InterviewService.getInstance();

        new Menu()
                .header("==> Manage Interviews <==")
                .choice(
                        new Menu.Choice("📨 Send Interview Invitation", interviewService::sendInvitation),
                        new Menu.Choice("📅 Display Incoming Interview", interviewService::displayIncomingInterview),
                        new Menu.Choice("📜 Display Past Interview", interviewService::displayPastInterview),
                        new Menu.Choice("⭐ Rate Completed Interviews", Log::na),
                        new Menu.Choice("🕒 View Availability", interviewService::viewAvailability),
                        new Menu.Choice("📝 Modify Availability", Log::na),
                        new Menu.Choice("❌ Cancel Invitation", interviewService::cancelInvitation),
                        new Menu.Choice("❌ Cancel Scheduled Interview", interviewService::cancelScheduledInterview)
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

    public void accessApplicantMenu() {
        InterviewService interviewService = InterviewService.getInstance();

        new Menu()
                .header("==> Manage Interviews <==")
                .choice(
                        new Menu.Choice("📨 Display Interview Invitation", interviewService::displayInvitation),
                        new Menu.Choice("✅ Accept Interview Invitation", interviewService::acceptInvitation),
                        new Menu.Choice("📅 Display Incoming Interview", interviewService::displayIncomingInterview),
                        new Menu.Choice("📜 Display Past Interview", interviewService::displayPastInterview),
                        new Menu.Choice("🔃 Reschedule Interview", interviewService::rescheduleInterview),
                        new Menu.Choice("❌ Cancel Scheduled Interview", interviewService::cancelScheduledInterview)
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
        String[] excludeKeys = Context.isEmployer() ? new String[]{"default", "employer"} : new String[]{"default", "applicant"};
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

    public void printApplicantTimeSlot(String calendarView) {
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
        String[] excludeKeys = Context.isEmployer() ? new String[]{"default", "employer"} : new String[]{"default", "applicant"};
        TabularPrint.printTabular(scheduledInterviews, true, excludeKeys);
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
        return input.confirm("Confirm to reschedule this interview? [ Y / N ] => ");
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
        return input.getObjectFromList("|\n| Select Interview To Cancel => ", scheduledInterview, 40, 2);
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
        TabularPrint.printTabular(invitations, true, "default", "employer");
        return input.getObjectFromList("|\n| Select Invitation To Cancel => ", invitations, 80, 2);
    }

    public boolean confirmCancelInvitation() {
        return input.confirm("Confirm to cancel this interview invitation? [ Y / N ] => ");
    }

    public void printSuccessCancelInvitationMsg() {
        System.out.println();
        Log.info("Interview invitation successfully cancelled");
        input.clickAnythingToContinue();
    }

}