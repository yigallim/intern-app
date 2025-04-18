
package com.tarumt.control;

import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.list.List;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.JobPosting;
import com.tarumt.entity.qualification.Skill;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.matching.JobMatchingUtil;
import com.tarumt.utility.pretty.TabularPrint;
import com.tarumt.utility.validation.StringCondition;
import com.tarumt.utility.search.FuzzySearch;
import com.tarumt.utility.search.FuzzySearch.Result;
import com.tarumt.adt.set.Set;

public class MatchingSearchService {

    private final Input input = new Input();

    public void searchJobPostingsByKeyword() {
        StringCondition condition = new StringCondition();
        String keyword = input.getString("Enter keyword to search jobs: ", condition).toLowerCase();
        List<JobPosting> allJobs = Initializer.getJobPostings();
        List<JobPosting> matchedJobs = allJobs.filter(job -> {
            boolean matchInTitle = job.getTitle().toLowerCase().contains(keyword);
            boolean matchInDesc = job.getDescription().toLowerCase().contains(keyword);
            boolean matchInSkills = job.getSkills().anyMatch(skill -> skill.getSkillName().toLowerCase().contains(keyword));
            boolean matchInEduField = job.getEducationLevel() != null
                    && job.getEducationLevel().getFieldOfStudy().name().toLowerCase().contains(keyword);
            return matchInTitle || matchInDesc || matchInSkills || matchInEduField;
        });

        if (matchedJobs.isEmpty()) {
            System.out.println("❌ No job postings matched your keyword.");
        } else {
            System.out.println("✅ Found " + matchedJobs.size() + " matching jobs:");
            TabularPrint.printTabular(matchedJobs, true);
        }
    }

    public void searchApplicantsByMatchingScore() {
        List<JobPosting> jobPostings = Initializer.getJobPostings();
        JobPosting job = input.getObjectFromList("Select Job Posting", jobPostings);

        if (job == null) {
            return;
        }

        List<Applicant> applicants = Initializer.getApplicants();
        List<ApplicantScore> matches = new DoublyLinkedList<>();

        for (Applicant applicant : applicants) {
            double score = JobMatchingUtil.calculateScore(job, applicant);
            if (JobMatchingUtil.isQualified(job, applicant)) {
                matches.add(new ApplicantScore(applicant, score));
            }
        }

        matches.sort((a, b) -> Double.compare(b.score, a.score));

        System.out.printf("\nTop Matches for Job: %s\n", job.getTitle());
        System.out.printf("| %-20s | %-30s | %-6s |\n", "Applicant", "Email", "Score");
        System.out.println("|----------------------|--------------------------------|--------|");
        for (ApplicantScore match : matches) {
            Applicant a = match.applicant;
            System.out.printf("| %-20s | %-30s | %6.2f |\n",
                    a.getName(), a.getContactEmail(), match.score);
        }
    }

    public void searchApplicantsBySkill() {
        StringCondition condition = new StringCondition();

        String keyword = input.getString("Enter skill keyword to search: ", condition).trim().toLowerCase();

        List<Applicant> applicants = Initializer.getApplicants();
        List<MatchedApplicant> matches = new DoublyLinkedList<>();

        for (Applicant a : applicants) {
            for (Skill skill : a.getSkills()) {
                if (skill.getSkillName().toLowerCase().contains(keyword)) {
                    matches.add(new MatchedApplicant(a, skill));
                    break;
                }
            }
        }

        if (matches.isEmpty()) {
            System.out.println("❌ No applicants found with the skill matching: " + keyword);
            return;
        }

        matches.sort((a, b) -> b.skill.getProficiencyLevel().compareTo(a.skill.getProficiencyLevel()));

        System.out.printf("\n🔍 Results for skill: \"%s\"\n", keyword);
        System.out.printf("| %-20s | %-30s | %-20s | %-12s |\n", "Applicant", "Email", "Skill", "Proficiency");
        System.out.println("|----------------------|--------------------------------|-----------------------|-------------|");

        for (MatchedApplicant match : matches) {
            Applicant a = match.applicant;
            Skill s = match.skill;
            System.out.printf("| %-20s | %-30s | %-20s | %-12s |\n",
                    a.getName(), a.getContactEmail(), s.getSkillName(), s.getProficiencyLevel());
        }
    }

    public void searchApplicantsBySkillFuzzy() {
        StringCondition condition = new StringCondition();
        String keyword = input.getString("Enter skill keyword to search applicants (e.g., java, network): ", condition);

        List<Applicant> allApplicants = Initializer.getApplicants();
        List<MatchedApplicant> matchedList = new DoublyLinkedList<>();

        for (Applicant applicant : allApplicants) {
            List<Skill> skills = applicant.getSkills();
            if (skills == null) {
                continue;
            }

            for (Skill skill : skills) {
                Set<String> matches = FuzzySearch.findFuzzyMatches_v4(keyword, skill.getSkillName());
                if (!matches.isEmpty()) {
                    matchedList.add(new MatchedApplicant(applicant, skill));
                    break; // Only first match per applicant
                }
            }
        }

        if (matchedList.isEmpty()) {
            System.out.println("❌ No applicants found with skills matching: " + keyword);
            return;
        }

        // Sort by Proficiency Level (highest first)
        matchedList.sort((a, b)
                -> b.skill.getProficiencyLevel().ordinal() - a.skill.getProficiencyLevel().ordinal()
        );

        System.out.printf("\n✅ Applicants with skill matching: \"%s\"\n", keyword);
        System.out.println("|------------------------------------------------------------------------------------------------|");

        System.out.printf("| %-20s | %-30s | %-20s | %-15s |\n", "Applicant", "Email", "Matched Skill", "Proficiency");
        System.out.println("|----------------------|--------------------------------|----------------------|-----------------|");

        for (MatchedApplicant match : matchedList) {
            System.out.printf("| %-20s | %-30s | %-20s | %-15s |\n",
                    match.applicant.getName(),
                    match.applicant.getContactEmail(),
                    match.skill.getSkillName(),
                    match.skill.getProficiencyLevel());
        }
        System.out.println("|------------------------------------------------------------------------------------------------|");

    }

    private static class MatchedApplicant {

        Applicant applicant;
        Skill skill;

        MatchedApplicant(Applicant applicant, Skill skill) {
            this.applicant = applicant;
            this.skill = skill;
        }
    }

    private static class ApplicantScore {

        Applicant applicant;
        double score;

        ApplicantScore(Applicant applicant, double score) {
            this.applicant = applicant;
            this.score = score;
        }
    }
}
