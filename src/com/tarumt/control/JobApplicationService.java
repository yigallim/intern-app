package com.tarumt.control;

import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.list.List;
import com.tarumt.boundary.JobApplicationUI;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.Company;
import com.tarumt.entity.JobApplication;
import com.tarumt.entity.JobPosting;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;

import java.time.LocalDate;

public class JobApplicationService {

    private static JobApplicationService instance;
    private List<JobApplication> jobApplications = new DoublyLinkedList<>();
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

    private List<JobApplication> getEmployerJobApplications() {
        Company company = Context.getCompany();
        if (company == null) {
            return new DoublyLinkedList<>();
        }
        return jobApplications.filter(application ->
                application.getJobPosting().getCompany() != null &&
                        application.getJobPosting().getCompany().equals(company)
        );
    }

    private List<JobApplication> getApplicantJobApplications() {
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

    public void accessApplicant() {
        this.jobApplicationUI.accessApplicantMenu();
    }

    public void applyJobPosting() {
        // TODO : Filter duplicates (applied before), filter doesn't reach qualification
        while (true) {
            List<JobPosting> availableJobPostings = Initializer.getJobPostings().filter(jobPosting -> jobPosting.getStatus() == JobPosting.Status.OPEN);
            JobPosting jobPosting = jobApplicationUI.getApplyJobPostingChoice(availableJobPostings);
            if (jobPosting == null) {
                return;
            }
            JobApplication jobApplication = new JobApplication(jobPosting, Context.getApplicant(), JobApplication.Status.PENDING, LocalDate.now());
            jobApplications.add(jobApplication);
            jobApplicationUI.printSuccessApplyJobPostingMsg();
            if (!jobApplicationUI.continueToApplyJobPosting()) {
                return;
            }
        }
    }

    public void displayJobApplication() {
        if (Context.isEmployer()) {
            List<JobApplication> applications = this.getEmployerJobApplications();
            this.jobApplicationUI.printGroupedJobApplications(applications);
        } else if (Context.isApplicant()) {
            List<JobApplication> applications = this.getApplicantJobApplications();
            this.jobApplicationUI.printJobApplication(applications);
        }
    }

    public void displayOngoingJobApplication() {
        List<JobApplication> allApplications = this.getEmployerJobApplications();
        List<JobApplication> ongoingApplications = allApplications.filter(application ->
                application.getStatus() == JobApplication.Status.PENDING ||
                        application.getStatus() == JobApplication.Status.SHORTLISTED ||
                        application.getStatus() == JobApplication.Status.INTERVIEWED ||
                        application.getStatus() == JobApplication.Status.OFFERED
        );
        this.jobApplicationUI.printGroupedJobApplications(ongoingApplications);
    }

    public void displayTerminatedJobApplication() {
        List<JobApplication> allApplications = this.getEmployerJobApplications();
        List<JobApplication> terminatedApplications = allApplications.filter(application ->
                application.getStatus() == JobApplication.Status.ACCEPTED ||
                        application.getStatus() == JobApplication.Status.REJECTED ||
                        application.getStatus() == JobApplication.Status.WITHDRAWN
        );
        this.jobApplicationUI.printGroupedJobApplications(terminatedApplications);
    }

    public void withdrawJobApplication() {
        List<JobApplication> withdrawableJobApplications = getApplicantJobApplications().filter(application ->
                application.getStatus() == JobApplication.Status.PENDING ||
                        application.getStatus() == JobApplication.Status.SHORTLISTED ||
                        application.getStatus() == JobApplication.Status.INTERVIEWED ||
                        application.getStatus() == JobApplication.Status.OFFERED
        );

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
