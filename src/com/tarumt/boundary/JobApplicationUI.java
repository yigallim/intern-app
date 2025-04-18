package com.tarumt.boundary;

import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.list.List;
import com.tarumt.control.JobApplicationService;
import com.tarumt.entity.JobApplication;
import com.tarumt.entity.JobPosting;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Menu;
import com.tarumt.utility.matching.MatchingReport;
import com.tarumt.utility.pretty.TabularPrint;
import com.tarumt.utility.validation.ConditionFactory;
import com.tarumt.utility.validation.StringCondition;
import com.tarumt.control.MatchingSearchService;
public class JobApplicationUI {
    private Input input;

    public JobApplicationUI(Input input) {
        this.input = input;
    }

    public void accessEmployerMenu() {
        JobApplicationService jobApplicationService = JobApplicationService.getInstance();
        MatchingSearchService searchService = new MatchingSearchService();
        new Menu()
                .header("==> Manage Job Application <==")
                .choice( // View Shortlist Applicant shall be in Schedule
                        new Menu.Choice("📄 Display All Application", jobApplicationService::displayJobApplication),
                        new Menu.Choice("🔄 Display Ongoing Application", jobApplicationService::displayOngoingJobApplication),
                        new Menu.Choice("🏁 Display Terminated Application", jobApplicationService::displayTerminatedJobApplication),
                        new Menu.Choice("🔍 View Matched Applications", jobApplicationService::viewMatchedApplications),
                        new Menu.Choice("📊 View Matched Report", MatchingReport::generateForCurrentCompany),
                        new Menu.Choice("🔍 Search applicant with certain skill",searchService::searchApplicantsBySkillFuzzy),
                        new Menu.Choice("✅ Shortlist Application", Log::na),
                        new Menu.Choice("❌ Reject Application", Log::na))
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
        System.out.println();
    }

    public void accessApplicantMenu() {
        JobApplicationService jobApplicationService = JobApplicationService.getInstance();

        new Menu()
                .header("==> Manage Job Application <==")
                .choice(
                        new Menu.Choice("📝 Apply Job Posting", jobApplicationService::applyJobPosting),
                        new Menu.Choice("📄 Display Job Application", jobApplicationService::displayJobApplication),
                        new Menu.Choice("❌ Withdraw Job Application", jobApplicationService::withdrawJobApplication))
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
        System.out.println();
    }

    public void printJobApplication(List<JobApplication> jobApplications) {
        if (jobApplications == null || jobApplications.isEmpty()) {
            Log.info("No job applications to display");
            return;
        }
        Log.info("Displaying " + jobApplications.size() + " job applications");
        TabularPrint.printTabular(jobApplications, true, "applicant");
        input.clickAnythingToContinue();
    }

    public JobPosting getApplyJobPostingChoice(List<JobPosting> jobPostings) {
        System.out.println("<== Apply Job Posting [ X to Exit ] ==>");
        return input.getObjectFromList("|\n| Select Job Posting To Apply ==>", jobPostings, 40);
    }

    public void printSuccessApplyJobPostingMsg() {
        System.out.println();
        Log.info("Job posting applied");
    }

    public boolean continueToApplyJobPosting() {
        StringCondition condition = ConditionFactory
                .string()
                .regex("^[xy]$|^[XY]$", "Invalid input, please input X or Y");
        String xOrY = input.withoutExitKey().getString("Continue to apply another job posting? [ Y / X ] => ",
                condition);
        if (xOrY.equalsIgnoreCase("y")) {
            System.out.println();
            return true;
        }
        return false;
    }

    public JobApplication getWithdrawJobApplicationChoice(List<JobApplication> jobApplications) {
        System.out.println("<== Withdraw Job Application [ X to Exit ] ==>");

        if (jobApplications.isEmpty()) {
            Log.info("No withdrawable job applications found");
            input.clickAnythingToContinue();
            return null;
        }

        return input.getObjectFromList("|\n| Select Job Application To Withdraw ==>", jobApplications, 40);
    }

    public boolean confirmWithdraw() {
        return input.confirm("Confirm to withdraw job application? [ Y / X ] => ");
    }

    public void printSuccessWithdrawJobApplicationMsg() {
        System.out.println();
        Log.info("Job application successfully withdrawn");
    }

    /**
     * Prints job applications grouped by job posting for employers
     *
     * @param jobApplications the list of job applications to display
     */
    public void printGroupedJobApplications(List<JobApplication> jobApplications) {
        if (jobApplications == null || jobApplications.isEmpty()) {
            Log.info("No job applications to display");
            return;
        }

        Log.info("Displaying " + jobApplications.size() + " job applications grouped by job posting");

        // Get unique job postings
        List<JobPosting> uniqueJobPostings = new DoublyLinkedList<>();

        // Find all unique job postings
        for (JobApplication application : jobApplications) {
            JobPosting currentPosting = application.getJobPosting();
            boolean found = false;

            for (JobPosting posting : uniqueJobPostings) {
                if (posting.getId().equals(currentPosting.getId())) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                uniqueJobPostings.add(currentPosting);
            }
        }

        // For each unique job posting, print its applications
        for (JobPosting jobPosting : uniqueJobPostings) {
            // Filter applications for this job posting
            List<JobApplication> applicationsForPosting = jobApplications.filter(
                    application -> application.getJobPosting().equals(jobPosting));

            if (!applicationsForPosting.isEmpty()) {
                System.out.println("\nJob Posting ID     => " + jobPosting.getId());
                System.out.println("Job Posting Title  => " + jobPosting.getTitle());
                System.out.println("Applications Count => " + applicationsForPosting.size());
                TabularPrint.printTabular(applicationsForPosting, true, "employer");
            }
        }

        input.clickAnythingToContinue();
    }
}
