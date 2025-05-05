/**
 * @author Choo Zhen Hao
 */

package com.tarumt.control;

import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.list.ListInterface;
import com.tarumt.boundary.MatchingUI;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.Company;
import com.tarumt.entity.JobApplication;
import com.tarumt.entity.JobPosting;
import com.tarumt.entity.interview.ScheduledInterview;
import com.tarumt.entity.qualification.EducationLevel;
import com.tarumt.entity.qualification.LanguageProficiency;
import com.tarumt.entity.qualification.Skill;
import com.tarumt.entity.qualification.WorkExperience;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Report;
import com.tarumt.utility.matching.JobMatchingUtil;
import com.tarumt.utility.matching.MatchingReport;
import com.tarumt.utility.pretty.Chart;
import com.tarumt.utility.pretty.annotation.OutputLength;
import com.tarumt.utility.search.FuzzySearch;

public class MatchingController {
    private static MatchingController instance;
    private ListInterface<JobPosting> jobPostings = new DoublyLinkedList<>();
    private ListInterface<JobApplication> jobApplications = new DoublyLinkedList<>();
    private ListInterface<ScheduledInterview> scheduledInterviews = new DoublyLinkedList<>();
    private ListInterface<Applicant> applicants = new DoublyLinkedList<>();
    private final MatchingUI matchingUI;

    private MatchingController() {
        Input input = new Input();
        this.matchingUI = new MatchingUI(input);
        this.jobPostings = Initializer.getJobPostings();
        this.jobApplications = Initializer.getJobApplications();
        this.scheduledInterviews = Initializer.getScheduledInterviews();
        this.applicants = Initializer.getApplicants();
    }

    public static MatchingController getInstance() {
        if (instance == null) {
            instance = new MatchingController();
        }
        return instance;
    }

    private ListInterface<JobPosting> getEmployerJobPostings() {
        Company company = Context.getCompany();
        if (company == null) {
            return new DoublyLinkedList<>();
        }
        return jobPostings.filter(jobPosting ->
                jobPosting.getCompany() != null &&
                        jobPosting.getCompany().getId().equals(company.getId())
        );
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

    private ListInterface<JobApplication> getAllJobApplications() {
        updateInterviewedStatus();
        return jobApplications;
    }

    private ListInterface<JobApplication> getEmployerJobApplications() {
        Company company = Context.getCompany();
        if (company == null) {
            return new DoublyLinkedList<>();
        }
        return getAllJobApplications().filter(application ->
                application.getJobPosting().getCompany() != null &&
                        application.getJobPosting().getCompany().equals(company)
        );
    }

    public void manageJobQualification() {
        this.matchingUI.jobQualificationMenu();
    }

    public void manageApplicantQualification() {
        this.matchingUI.applicantQualificationMenu();
    }

    public void setEducationLevel(JobPosting jobPosting) {
        EducationLevel educationLevel = matchingUI.getEducationInput();
        if (educationLevel != null) {
            jobPosting.setEducationLevel(educationLevel);
            System.out.println("✅ Education requirement set for job posting.");
        }
    }

    public void setEducationLevel(Applicant applicant) {
        EducationLevel educationLevel = matchingUI.getEducationInput();
        if (educationLevel != null) {
            applicant.setEducationLevel(educationLevel);
            System.out.println("✅ Education requirement set for job posting.");
        }
    }

    public void setLanguageProficiency(JobPosting jobPosting) {
        ListInterface<LanguageProficiency> qualifications = matchingUI.getLanguageProficiencyInput();
        if (qualifications != null && !qualifications.isEmpty()) {
            jobPosting.setLanguageProficiencies(qualifications);
            System.out.println("✅ Language proficiency requirements set for job posting.");
        }
    }

    public void setLanguageProficiency(Applicant applicant) {
        ListInterface<LanguageProficiency> qualifications = matchingUI.getLanguageProficiencyInput();
        if (qualifications != null && !qualifications.isEmpty()) {
            applicant.setLanguageProficiencies(qualifications);
            System.out.println("✅ Language proficiency updated for applicant.");
        }
    }

    public void setSkill(JobPosting jobPosting) {
        ListInterface<Skill> qualifications = matchingUI.getSkillInput();
        if (qualifications != null && !qualifications.isEmpty()) {
            jobPosting.setSkills(qualifications);
            System.out.println("✅ Skills requirement set for job posting.");
        }
    }

    public void setSkill(Applicant applicant) {
        ListInterface<Skill> qualifications = matchingUI.getSkillInput();
        if (qualifications != null && !qualifications.isEmpty()) {
            applicant.setSkills(qualifications);
            System.out.println("✅ Skills updated for applicant.");
        }
    }

    public void setWorkExperience(JobPosting jobPosting) {
        ListInterface<WorkExperience> qualifications = matchingUI.getWorkExperienceInput();
        if (qualifications != null && !qualifications.isEmpty()) {
            jobPosting.setWorkExperiences(qualifications);
            System.out.println("✅ Work experience requirement set for job posting.");
        }
    }

    public void setWorkExperience(Applicant applicant) {
        ListInterface<WorkExperience> qualifications = matchingUI.getWorkExperienceInput();
        if (qualifications != null && !qualifications.isEmpty()) {
            applicant.setWorkExperiences(qualifications);
            System.out.println("✅ Work experience updated for applicant.");
        }
    }

    public void modifyJobQualification() {
        JobPosting job = matchingUI.getJobPostingToAddQualification(getEmployerJobPostings(), "Modify Job Posting Qualification Requirement");
        if (job == null) return;
        matchingUI.modifyQualificationMenu(job);
    }

    public void displayJobQualification() {
        JobPosting job = matchingUI.getJobPostingToAddQualification(getEmployerJobPostings(), "Display Job Posting Qualification Requirement");
        if (job == null) return;
        this.matchingUI.displayAllQualificationsWithDetails(job.getEducationLevel(), job.getWorkExperiences(), job.getLanguageProficiencies(), job.getSkills());
    }

    public void modifyApplicantQualification() {
        Applicant applicant = Context.getApplicant();
        if (applicant == null) return;
        matchingUI.modifyQualificationMenu(applicant);
    }

    public void displayApplicantQualification() {
        Applicant applicant = Context.getApplicant();
        if (applicant == null) return;
        this.matchingUI.displayAllQualifications(applicant.getEducationLevel(), applicant.getWorkExperiences(), applicant.getLanguageProficiencies(), applicant.getSkills());
    }

    public void checkJobQualification() {
        JobPosting job = matchingUI.getJobPostingToAddQualification(this.jobPostings, "Check Job Posting Qualification Requirement");
        if (job == null) return;
        this.matchingUI.displayAllQualificationsWithDetails(job.getEducationLevel(), job.getWorkExperiences(), job.getLanguageProficiencies(), job.getSkills());
    }

    public void searchApplicant() {
        String keyword = this.matchingUI.getSearchQuery();
        if (keyword == Input.STRING_EXIT_VALUE) return;
        ListInterface<Applicant> allApplicants = Initializer.getApplicants();
        ListInterface<MatchedApplicant> matchedList = new DoublyLinkedList<>();
        ListInterface<String> matches = new DoublyLinkedList<>();
        for (Applicant applicant : allApplicants) {
            ListInterface<Skill> skills = applicant.getSkills();
            if (skills == null) {
                continue;
            }

            for (Skill skill : skills) {
                matches = FuzzySearch.findFuzzyMatches(keyword, skill.getSkillName());
                if (!matches.isEmpty()) {
                    matchedList.add(new MatchedApplicant(applicant, skill));
                    break;
                }
            }
        }
        matchedList.sort((a, b)
                -> b.skill.getProficiencyLevel().ordinal() - a.skill.getProficiencyLevel().ordinal()
        );
        this.matchingUI.printSearchResult(matchedList, keyword, matches);
    }

    public void viewMatchApplications() {
        ListInterface<JobApplication> applications = getEmployerJobApplications();
        if (applications.isEmpty()) {
            Log.info("No job applications found for this employer.");
            return;
        }

        ListInterface<JobPosting> uniquePostings = new DoublyLinkedList<>();
        for (JobApplication app : applications) {
            JobPosting job = app.getJobPosting();
            if (!uniquePostings.contains(job)) {
                uniquePostings.add(job);
            }
        }

        for (JobPosting job : uniquePostings) {
            System.out.println("\n🔍 Ranking Applicants for Job: " + job.getTitle());
            System.out.println("------------------------------------------------------------------------------------");

            ListInterface<ApplicantScore> rankedApplicants = new DoublyLinkedList<>();
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

    public void viewMatchReport() {
        Company company = Context.getCompany();
        if (company == null) {
            Log.warn("No logged in company context.");
            return;
        }

        final int width = 80;
        System.out.print(Report.buildReportHeader(width, "Job Matching", "Matching Report Summary"));
        System.out.println();

        ListInterface<JobPosting> jobPostings = Initializer.getJobPostings().filter(j -> j.getCompany().equals(company));
        ListInterface<JobApplication> jobApplications = Initializer.getJobApplications();

        for (JobPosting job : jobPostings) {
            ListInterface<MatchingReport.ReportEntry> matched = new DoublyLinkedList<>();
            int total = 0;
            int qualified = 0;
            double scoreSum = 0;

            for (JobApplication application : jobApplications) {
                if (!application.getJobPosting().equals(job)) {
                    continue;
                }

                Applicant applicant = application.getApplicant();
                total++;
                double score = JobMatchingUtil.calculateScore(job, applicant);

                if (JobMatchingUtil.isQualified(job, applicant)) {
                    matched.add(new MatchingReport.ReportEntry(applicant, score));
                    qualified++;
                    scoreSum += score;
                }
            }

            matched.sort((a, b) -> Double.compare(b.getScore(), a.getScore()));
            System.out.println("📄 Matching Report for Job: " + job.getTitle());
            System.out.println("Total Applicants : " + total);
            System.out.println("Qualified        : " + qualified);
            double rate = total == 0 ? 0 : (qualified * 100.0 / total);
            System.out.printf("Success Rate     : %.2f%%\n", rate);
            double avgScore = qualified == 0 ? 0 : scoreSum / qualified;
            System.out.printf("Average Score    : %.2f\n", avgScore);

            if (matched.isEmpty()) {
                Log.info("No qualified applicants for this job.");
                System.out.println();
                continue;
            }

            System.out.printf("%-3s | %-25s | %-35s | %-6s\n", "#", "Applicant", "Email", "Score");
            System.out.println("--------------------------------------------------------------------------------");
            int index = 1;
            ListInterface<String> names = new DoublyLinkedList<>();
            ListInterface<Integer> scores = new DoublyLinkedList<>();
            for (MatchingReport.ReportEntry e : matched) {
                System.out.printf("%-3d | %-25s | %-35s | %6.2f\n",
                        index++, e.getApplicant().getName(), e.getApplicant().getContactEmail(), e.getScore());
                // TODO
                JobMatchingUtil.showScoreBreakdown(job, e.getApplicant());
                names.add(e.getApplicant().getName());
                scores.add((int) Math.round(e.getScore()));
            }
            System.out.println(Chart.barChart(names, scores, "Top Match Score Chart", width, '█', true));
            System.out.println("\n");
        }

        System.out.print(Report.buildReportFooter(width));
    }

    public void checkApplicantQualification() {
        Applicant applicant = matchingUI.getApplicantChoice(this.applicants);
        if (applicant == null) {
            return;
        }

        System.out.println("\n📋 Qualification Details for Applicant: " + applicant.getName());
        matchingUI.displayAllQualifications(
                applicant.getEducationLevel(),
                applicant.getWorkExperiences(),
                applicant.getLanguageProficiencies(),
                applicant.getSkills()
        );
    }

    public static class MatchedApplicant {
        @OutputLength(40)
        Applicant applicant;
        @OutputLength(50)
        Skill skill;

        MatchedApplicant(Applicant applicant, Skill skill) {
            this.applicant = applicant;
            this.skill = skill;
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

}
