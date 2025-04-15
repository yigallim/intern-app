package com.tarumt.control;

import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.list.ListInterface;
import com.tarumt.boundary.JobApplicationUI;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.Company;
import com.tarumt.entity.JobApplication;
import com.tarumt.entity.JobPosting;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;

public class JobApplicationService {

    private static JobApplicationService instance;
    private ListInterface<JobApplication> jobApplications = new DoublyLinkedList<>();
    private final JobApplicationUI jobApplicationUI;

    private JobApplicationService() {
        Input input = new Input();
        this.jobApplications = Initializer.getJobApplications();
        this.jobApplicationUI = new JobApplicationUI(input);
    }

    public static JobApplicationService getInstance() {
        if (instance == null) {
            instance = new JobApplicationService();
        }
        return instance;
    }

    private ListInterface<JobApplication> getEmployerJobApplications() {
        Company company = Context.getCompany();
        if (company == null) {
            return new DoublyLinkedList<>();
        }
        return jobApplications.filter(application ->
                application.getJobPosting().getCompany() != null &&
                        application.getJobPosting().getCompany().equals(company)
        );
    }

    private ListInterface<JobApplication> getApplicantJobApplications() {
        Applicant applicant = Context.getApplicant();
        if (applicant == null) {
            return new DoublyLinkedList<>();
        }
        return jobApplications.filter(application ->
                application.getApplicant() != null &&
                        application.getApplicant().equals(applicant)
        );
    }

    public void accessEmployer() {
        this.jobApplicationUI.accessEmployerMenu();
    }

    public void displayJobApplication() {
        if (Context.isEmployer()) {
            ListInterface<JobApplication> applications = this.getEmployerJobApplications();
            ListInterface<JobPosting> uniqueJobPosting = getUniqueJobPostings(jobApplications);
            this.jobApplicationUI.printGroupedJobApplications(applications, uniqueJobPosting);
        } else if (Context.isApplicant()) {
            ListInterface<JobApplication> applications = this.getApplicantJobApplications();
            this.jobApplicationUI.printJobApplication(applications);
        }
    }

    public void displayOngoingJobApplication() {
        ListInterface<JobApplication> allApplications = this.getEmployerJobApplications();
        ListInterface<JobApplication> ongoingApplications = allApplications.filter(JobApplication::isOngoing);
        ListInterface<JobPosting> uniqueJobPosting = getUniqueJobPostings(jobApplications);
        this.jobApplicationUI.printGroupedJobApplications(ongoingApplications, uniqueJobPosting);
    }

    public void displayTerminatedJobApplication() {
        ListInterface<JobApplication> allApplications = this.getEmployerJobApplications();
        ListInterface<JobApplication> terminatedApplications = allApplications.filter(JobApplication::isTerminated);
        ListInterface<JobPosting> uniqueJobPosting = getUniqueJobPostings(jobApplications);
        this.jobApplicationUI.printGroupedJobApplications(terminatedApplications, uniqueJobPosting);
    }

    private ListInterface<JobPosting> getUniqueJobPostings(ListInterface<JobApplication> jobApplications) {
        ListInterface<JobPosting> uniqueJobPostings = new DoublyLinkedList<>();
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
        return uniqueJobPostings;
    }

    public void shortlistApplication() {
        ListInterface<JobApplication> pendingApplications = getEmployerJobApplications().filter(
                application -> application.getStatus() == JobApplication.Status.PENDING);

        JobApplication jobApplication = jobApplicationUI.getShortlistApplicationChoice(pendingApplications, null);
        if (jobApplication == null) {
            return;
        }

        if (jobApplicationUI.confirmShortlist()) {
            jobApplication.setStatus(JobApplication.Status.SHORTLISTED);
            jobApplicationUI.printSuccessShortlistApplicationMsg();
        }
    }

    public void rejectApplication() {
        ListInterface<JobApplication> ongoingApplications = getEmployerJobApplications().filter(JobApplication::isOngoing);

        JobApplication jobApplication = jobApplicationUI.getRejectApplicationChoice(ongoingApplications);
        if (jobApplication == null) {
            return;
        }

        if (jobApplicationUI.confirmReject()) {
            jobApplication.setStatus(JobApplication.Status.REJECTED);
            jobApplicationUI.printSuccessRejectApplicationMsg();
        }
    }

    public void accessApplicant() {
        this.jobApplicationUI.accessApplicantMenu();
    }

    public void applyJobPosting() {
        // TODO : Filter duplicates (applied before), filter doesn't reach qualification
        while (true) {
            ListInterface<JobPosting> availableJobPostings = Initializer.getJobPostings().filter(jobPosting -> jobPosting.getStatus() == JobPosting.Status.OPEN);
            JobPosting jobPosting = jobApplicationUI.getApplyJobPostingChoice(availableJobPostings);
            if (jobPosting == null) {
                return;
            }
            JobApplication jobApplication = new JobApplication(jobPosting, Context.getApplicant(), JobApplication.Status.PENDING, Context.getDate());
            jobApplications.add(jobApplication);
            jobApplicationUI.printSuccessApplyJobPostingMsg();
            if (!jobApplicationUI.continueToApplyJobPosting()) {
                return;
            }
        }
    }

    public void withdrawJobApplication() {
        ListInterface<JobApplication> withdrawableJobApplications = getApplicantJobApplications().filter(JobApplication::isOngoing);
        JobApplication jobApplication = jobApplicationUI.getWithdrawJobApplicationChoice(withdrawableJobApplications);
        if (jobApplication == null) {
            return;
        }

        if (jobApplicationUI.confirmWithdraw()) {
            jobApplication.setStatus(JobApplication.Status.WITHDRAWN);
            jobApplicationUI.printSuccessWithdrawJobApplicationMsg();
        }
    }
}
