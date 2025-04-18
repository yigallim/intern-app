package com.tarumt.boundary;

import com.tarumt.adt.list.ListInterface;
import com.tarumt.control.JobApplicationController;
import com.tarumt.entity.JobApplication;
import com.tarumt.entity.JobPosting;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Menu;
import com.tarumt.utility.common.Strings;
import com.tarumt.utility.pretty.TabularPrint;
import com.tarumt.utility.validation.ConditionFactory;
import com.tarumt.utility.validation.StringCondition;

public class JobApplicationUI {
    private final Input input;

    public JobApplicationUI(Input input) {
        this.input = input;
    }

    public void accessEmployerMenu() {
        JobApplicationController jobApplicationController = JobApplicationController.getInstance();

        new Menu()
                .header("==> Manage Job Application <==")
                .choice(
                        new Menu.Choice("📄 Display All Application", jobApplicationController::displayJobApplication),
                        new Menu.Choice("🔄 Display Ongoing Application", jobApplicationController::displayOngoingJobApplication),
                        new Menu.Choice("🏁 Display Terminated Application", jobApplicationController::displayTerminatedJobApplication),
                        new Menu.Choice("🔍 View Matched Applications", Log::na),
                        new Menu.Choice("✅ Shortlist Application", jobApplicationController::shortlistApplication),
                        new Menu.Choice("🎉 Offer Application", jobApplicationController::offerApplication),
                        new Menu.Choice("❌ Reject Application", jobApplicationController::rejectApplication)
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
        System.out.println();

    }

    public JobApplication getShortlistApplicationChoice(ListInterface<JobApplication> jobApplications, ListInterface<JobApplication> recommended) {

        if (jobApplications.isEmpty()) {
            Log.info("No pending applications found to shortlist");
            input.clickAnythingToContinue();
            return null;
        }

        System.out.println("<== Shortlist Job Application [ X to Exit ] ==>");

        return input.getObjectFromList("|\n| Select Job Application To Shortlist =>", jobApplications, 80, 2);
    }

    public boolean confirmShortlist() {
        return input.confirm("Confirm to shortlist this application? [ Y / X ] => ");
    }

    public void printSuccessShortlistApplicationMsg() {
        System.out.println();
        Log.info("Application successfully shortlisted");
    }

    public JobApplication getOfferApplicationChoice(ListInterface<JobApplication> jobApplications) {
        if (jobApplications.isEmpty()) {
            Log.info("No applications found eligible to offer");
            input.clickAnythingToContinue();
            return null;
        }

        System.out.println("<== Offer Job Application [ X to Exit ] ==>");
        System.out.println(Strings.warnHighlight("| Recommended ==> "));
        return input.getObjectFromList("|\n| Select Job Application To Offer =>", jobApplications, 80, 2);
    }

    public void printCannotOfferApplicationWarning() {
        System.out.println();
        Log.warn("Cannot offer application: An upcoming interview is already scheduled.");
        Log.warn("Please cancel or conduct the scheduled interview first if you wish to offer this application.");
        input.clickAnythingToContinue();
    }

    public void printOfferWithInvitationWarning() {
        System.out.println();
        Log.warn("Warning: Offering this application will also cancel any pending interview invitations associated with it.");
    }

    public boolean confirmOffer() {
        return input.confirm("Confirm to offer this application? [ Y / X ] => ");
    }

    public void printSuccessOfferApplicationMsg() {
        System.out.println();
        Log.info("Application successfully offered");
    }

    public JobApplication getRejectApplicationChoice(ListInterface<JobApplication> jobApplications) {
        if (jobApplications.isEmpty()) {
            Log.info("No applications found to reject");
            input.clickAnythingToContinue();
            return null;
        }

        System.out.println("<== Reject Job Application [ X to Exit ] ==>");
        System.out.println(Strings.warnHighlight("| Recommended ==> "));
        return input.getObjectFromList("|\n| Select Job Application To Reject =>", jobApplications, 80, 2);
    }

    public void printCannotRejectApplicationWarning() {
        System.out.println();
        Log.warn("Cannot reject application: An upcoming interview is already scheduled.");
        Log.warn("Please cancel the scheduled interview first if you wish to reject this application.");
        input.clickAnythingToContinue();
    }

    public void printRejectWithInvitationWarning() {
        System.out.println();
        Log.warn("Warning: Rejecting this application will also cancel any pending interview invitations associated with it.");
    }

    public boolean confirmReject() {
        return input.confirm("Confirm to reject this application? [ Y / X ] => ");
    }

    public void printSuccessRejectApplicationMsg() {
        System.out.println();
        Log.info("Application successfully rejected");
    }

    public void accessApplicantMenu() {
        JobApplicationController jobApplicationController = JobApplicationController.getInstance();

        new Menu()
                .header("==> Manage Job Application <==")
                .choice(
                        new Menu.Choice("📝 Apply Job Posting", jobApplicationController::applyJobPosting),
                        new Menu.Choice("📄 Display Job Application", jobApplicationController::displayJobApplication),
                        new Menu.Choice("🎉 Accept Offered Job Application", jobApplicationController::acceptJobApplication),
                        new Menu.Choice("❌ Withdraw Job Application", jobApplicationController::withdrawJobApplication)
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();

        System.out.println();
    }

    public void printJobApplication(ListInterface<JobApplication> jobApplications) {
        if (jobApplications == null || jobApplications.isEmpty()) {
            Log.info("No job applications to display");
            input.clickAnythingToContinue();
            return;
        }
        Log.info("Displaying " + jobApplications.size() + " job applications");
        TabularPrint.printTabular(jobApplications, true, "applicant");
        input.clickAnythingToContinue();
    }

    public JobPosting getApplyJobPostingChoice(ListInterface<JobPosting> jobPostings) {
        if (jobPostings.isEmpty()) {
            Log.info("No applicable job posting found");
            input.clickAnythingToContinue();
            return null;
        }

        System.out.println("<== Apply Job Posting [ X to Exit ] ==>");
        return input.getObjectFromList("|\n| Select Job Posting To Apply =>", jobPostings, 40);
    }

    public void printSuccessApplyJobPostingMsg() {
        System.out.println();
        Log.info("Job posting applied");
    }

    public boolean continueToApplyJobPosting() {
        StringCondition condition = ConditionFactory.string().regex("^[xy]$|^[XY]$", "Invalid input, please input X or Y");
        String xOrY = input.withoutExitKey().getString("Continue to apply another job posting? [ Y / X ] => ", condition);
        if (xOrY.equalsIgnoreCase("y")) {
            System.out.println();
            return true;
        }
        return false;
    }

    public JobApplication getAcceptJobApplicationChoice(ListInterface<JobApplication> offeredApplications) {
        if (offeredApplications.isEmpty()) {
            Log.info("No job offers available to accept");
            input.clickAnythingToContinue();
            return null;
        }

        System.out.println("<== Accept Job Offer [ X to Exit ] ==>");
        return input.getObjectFromList("|\n| Select Job Offer To Accept =>", offeredApplications, 80, 2);
    }

    public boolean confirmAccept() {
        return input.confirm("Confirm to accept this job offer? [ Y / X ] => ");
    }

    public void printSuccessAcceptJobApplicationMsg(JobApplication acceptedApplication) {
        System.out.println();
        Log.info("Congratulations! You have successfully accepted the offer for:");
        Log.info("  Job Accepted => " + acceptedApplication.getJobPosting().toShortString());
        Log.info("  Company => " + acceptedApplication.getJobPosting().getCompany().toShortString());
        input.clickAnythingToContinue();
    }

    public JobApplication getWithdrawJobApplicationChoice(ListInterface<JobApplication> jobApplications) {
        if (jobApplications.isEmpty()) {
            Log.info("No withdrawable job applications found");
            input.clickAnythingToContinue();
            return null;
        }

        System.out.println("<== Withdraw Job Application [ X to Exit ] ==>");
        return input.getObjectFromList("|\n| Select Job Application To Withdraw =>", jobApplications, 80, 2);
    }

    public boolean confirmWithdraw() {
        return input.confirm("Confirm to withdraw job application? [ Y / X ] => ");
    }

    public void printSuccessWithdrawJobApplicationMsg() {
        System.out.println();
        Log.info("Job application successfully withdrawn");
    }

    public void printCannotWithdrawScheduledInterviewWarning() {
        System.out.println();
        Log.warn("Cannot withdraw application: An upcoming interview is already scheduled.");
        Log.warn("Please cancel the scheduled interview first if you wish to withdraw.");
        input.clickAnythingToContinue();
    }

    public void printWithdrawWithInvitationWarning() {
        System.out.println();
        Log.warn("Warning: Withdrawing this application will also cancel any pending interview invitations associated with it.");
    }

    public void printGroupedJobApplications(ListInterface<JobApplication> jobApplications, ListInterface<JobPosting> uniqueJobPostings) {
        if (jobApplications == null || jobApplications.isEmpty()) {
            Log.info("No job applications to display");
            input.clickAnythingToContinue();
            return;
        }

        Log.info("Displaying " + jobApplications.size() + " job applications grouped by job posting");
        for (JobPosting jobPosting : uniqueJobPostings) {
            ListInterface<JobApplication> applicationsForPosting = jobApplications.filter(application -> application.getJobPosting().equals(jobPosting));

            if (!applicationsForPosting.isEmpty()) {
                System.out.println("\nJob Posting ID => " + jobPosting.getId() + "  |  Title => " + jobPosting.getTitle() + "  |  Count => " + applicationsForPosting.size());
                TabularPrint.printTabular(applicationsForPosting, true, "employer");
            }
        }
        input.clickAnythingToContinue();
    }

}
