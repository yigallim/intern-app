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

    public void viewRankedApplications() {
        List<JobApplication> applications = getEmployerJobApplications();
        if (applications.isEmpty()) {
            return;
        }

        // Group by JobPosting
        List<JobPosting> uniquePostings = new DoublyLinkedList<>();
        for (JobApplication app : applications) {
            JobPosting job = app.getJobPosting();
            if (!uniquePostings.contains(job)) {
                uniquePostings.add(job);
            }
        }

        for (JobPosting job : uniquePostings) {
            System.out.println("\n\uD83D\uDD0D Ranking Applicants for Job: " + job.getTitle());
            List<ApplicantScore> rankedApplicants = new DoublyLinkedList<>();

            for (JobApplication app : applications) {
                if (!app.getJobPosting().equals(job)) {
                    continue;
                }

                Applicant applicant = app.getApplicant();
                double totalScore = 0;
                boolean disqualified = false;

                // Education
                if (job.getEducationLevel() != null && applicant.getEducationLevel() != null) {
                    EducationLevel r = job.getEducationLevel();
                    EducationLevel a = applicant.getEducationLevel();

                    if (r.getDegreeLevel() != null && r.getDegreeLevel() != a.getDegreeLevel() && !r.isOptional()) {
                        disqualified = true;
                    } else if (r.getDegreeLevel() != null && r.getDegreeLevel() == a.getDegreeLevel()) {
                        totalScore += applyWeight(1.0, r.getImportance());
                    }

                    if (r.getFieldOfStudy() != null && r.getFieldOfStudy() != a.getFieldOfStudy() && !r.isOptional()) {
                        disqualified = true;
                    } else if (r.getFieldOfStudy() != null && r.getFieldOfStudy() == a.getFieldOfStudy()) {
                        totalScore += applyWeight(1.0, r.getImportance());
                    }

                    double cgpaScore = a.getCgpa() / r.getCgpa();
                    if (cgpaScore < 0.5 && !r.isOptional()) {
                        disqualified = true;
                    } else {
                        totalScore += applyWeight(cgpaScore, r.getImportance());
                    }
                }

                // WorkExp
                for (WorkExperience req : job.getWorkExperiences()) {
                    boolean matched = false;
                    for (WorkExperience exp : applicant.getWorkExperiences()) {
                        if (req.getIndustry().equals(exp.getIndustry())) {
                            double s = Math.min(exp.getYears(), req.getYears());
                            totalScore += applyWeight(s, req.getImportance());
                            matched = true;
                            break;
                        }
                    }
                    if (!matched && !req.isOptional()) {
                        disqualified = true;
                    }
                }

                // Languages
                for (LanguageProficiency req : job.getLanguageProficiencies()) {
                    boolean matched = false;
                    for (LanguageProficiency lang : applicant.getLanguageProficiencies()) {
                        if (req.getLanguage().equals(lang.getLanguage())) {
                            double s = req.scoreMatch(lang);
                            totalScore += applyWeight(s, req.getImportance());
                            matched = true;
                            break;
                        }
                    }
                    if (!matched && !req.isOptional()) {
                        disqualified = true;
                    }
                }

                // Skills
                for (Skill req : job.getSkills()) {
                    boolean matched = false;
                    for (Skill skill : applicant.getSkills()) {
                        if (req.getSkillName().equalsIgnoreCase(skill.getSkillName())) {
                            double s = req.scoreMatch(skill);
                            totalScore += applyWeight(s, req.getImportance());
                            matched = true;
                            break;
                        }
                    }
                    if (!matched && !req.isOptional()) {
                        disqualified = true;
                    }
                }
                // --- Location Match ---
                Location jobLocation = job.getCompany().getLocation();
                Location applicantLocation = applicant.getLocation();
                if (jobLocation != null && applicantLocation != null) {
                    double distance = jobLocation.distanceTo(applicantLocation); // in KM
                    double score;

                    // Example scoring: Full score within 30km, 0 beyond 150km
                    if (distance <= 30) {
                        score = 1.0;
                    } else if (distance >= 150) {
                        score = -10.0;
                    } else {
                        score = 1.0 - ((distance - 30) / 120.0);  // Linear drop-off
                    }

                    totalScore += score * 1.5; // e.g., use a fixed weight for location
                }
               
                if (!disqualified) {
                    rankedApplicants.add(new ApplicantScore(app, totalScore));
                }
            }

            rankedApplicants.sort((a, b) -> Double.compare(b.score, a.score));

            System.out.printf("| %-20s | %-30s | %-15s | %-6s |\n", "Applicant", "Email", "Phone", "Score");
            System.out.println("|----------------------|--------------------------------|-----------------|--------|");
            for (ApplicantScore match : rankedApplicants) {
                Applicant a = match.application.getApplicant();
                System.out.printf("| %-20s | %-30s | %-15s | %6.2f |\n",
                        a.getName(), a.getContactEmail(), a.getContactPhone(), match.score);
            }
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
