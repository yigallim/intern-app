package com.tarumt.control;

import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.list.List;
import com.tarumt.boundary.JobApplicationUI;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.Company;
import com.tarumt.entity.JobApplication;
import com.tarumt.entity.JobPosting;
import com.tarumt.entity.location.Location;
import com.tarumt.entity.qualification.EducationLevel;
import com.tarumt.entity.qualification.LanguageProficiency;
import com.tarumt.entity.qualification.Qualification;
import com.tarumt.entity.qualification.Skill;
import com.tarumt.entity.qualification.WorkExperience;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.matching.JobMatchingUtil;

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
        return jobApplications.filter(application
                -> application.getJobPosting().getCompany() != null
                && application.getJobPosting().getCompany().equals(company)
        );
    }

    private List<JobApplication> getApplicantJobApplications() {
        Applicant applicant = Context.getApplicant();
        if (applicant == null) {
            return new DoublyLinkedList<>();
        }
        return jobApplications.filter(application
                -> application.getApplicant() != null
                && application.getApplicant().equals(applicant)
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

            // 🔽 Sort by score before display
            applications.sort((a, b) -> {
                double scoreA = JobMatchingUtil.calculateScore(a.getJobPosting(), a.getApplicant());
                double scoreB = JobMatchingUtil.calculateScore(b.getJobPosting(), b.getApplicant());
                return Double.compare(scoreB, scoreA);
            });

            this.jobApplicationUI.printGroupedJobApplications(applications);
        } else if (Context.isApplicant()) {
            List<JobApplication> applications = this.getApplicantJobApplications();
            this.jobApplicationUI.printJobApplication(applications);
        }
    }

    public void displayOngoingJobApplication() {
        List<JobApplication> allApplications = this.getEmployerJobApplications();
        List<JobApplication> ongoingApplications = allApplications.filter(application
                -> application.getStatus() == JobApplication.Status.PENDING
                || application.getStatus() == JobApplication.Status.SHORTLISTED
                || application.getStatus() == JobApplication.Status.INTERVIEWED
                || application.getStatus() == JobApplication.Status.OFFERED
        );
        this.jobApplicationUI.printGroupedJobApplications(ongoingApplications);
    }

    public void displayTerminatedJobApplication() {
        List<JobApplication> allApplications = this.getEmployerJobApplications();
        List<JobApplication> terminatedApplications = allApplications.filter(application
                -> application.getStatus() == JobApplication.Status.ACCEPTED
                || application.getStatus() == JobApplication.Status.REJECTED
                || application.getStatus() == JobApplication.Status.WITHDRAWN
        );
        this.jobApplicationUI.printGroupedJobApplications(terminatedApplications);
    }

    public void withdrawJobApplication() {
        List<JobApplication> withdrawableJobApplications = getApplicantJobApplications().filter(application
                -> application.getStatus() == JobApplication.Status.PENDING
                || application.getStatus() == JobApplication.Status.SHORTLISTED
                || application.getStatus() == JobApplication.Status.INTERVIEWED
                || application.getStatus() == JobApplication.Status.OFFERED
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

    public void viewMatchedApplications() {
        List<JobApplication> applications = getEmployerJobApplications();
        if (applications.isEmpty()) {
            Log.info("No job applications found for this employer.");
            return;
        }

        List<JobPosting> uniquePostings = new DoublyLinkedList<>();
        for (JobApplication app : applications) {
            JobPosting job = app.getJobPosting();
            if (!uniquePostings.contains(job)) {
                uniquePostings.add(job);
            }
        }
       
        for (JobPosting job : uniquePostings) {
            System.out.println("\n🔍 Ranking Applicants for Job: " + job.getTitle());
            System.out.println("------------------------------------------------------------------------------------");

            List<ApplicantScore> rankedApplicants = new DoublyLinkedList<>();
            for (JobApplication app : applications) {
                if (!app.getJobPosting().equals(job)) {
                    continue;
                }

                Applicant applicant = app.getApplicant();
                if (JobMatchingUtil.isQualified(job, applicant)) {
                    double score = JobMatchingUtil.calculateScore(job, applicant);
                    rankedApplicants.add(new ApplicantScore(app, score));
                }
            }

            rankedApplicants.sort((a, b) -> Double.compare(b.score, a.score));

            if (rankedApplicants.isEmpty()) {
                Log.info("No qualified applicants found.");
                continue;
            }

            System.out.printf("| %-20s | %-30s | %-15s | %-6s |\n", "Applicant", "Email", "Phone", "Score");
            System.out.println("|----------------------|--------------------------------|-----------------|--------|");

            for (ApplicantScore match : rankedApplicants) {
                Applicant a = match.application.getApplicant();
                System.out.printf("| %-20s | %-30s | %-15s | %6.2f |\n",
                        a.getName(), a.getContactEmail(), a.getContactPhone(), match.score);
            }

            System.out.println("------------------------------------------------------------------------------------");
        }
    }

    private static class ApplicantScore {

        JobApplication application;
        double score;

        ApplicantScore(JobApplication application, double score) {
            this.application = application;
            this.score = score;
        }
    }

    private double applyWeight(double score, Qualification.Importance importance) {
        switch (importance) {
            case HIGH:
                return score * 2.0;
            case MEDIUM:
                return score * 1.5;
            case LOW:
                return score;
            default:
                return 0;
        }
    }

}
