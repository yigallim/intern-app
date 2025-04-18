package com.tarumt.utility.matching;

import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.list.List;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.*;
import com.tarumt.entity.location.Location;
import com.tarumt.entity.qualification.*;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Report;
import com.tarumt.utility.pretty.Chart;

public class MatchingReport {

    public static void generateForCurrentCompany() {
        Company company = Context.getCompany();
        if (company == null) {
            Log.warn("No logged in company context.");
            return;
        }

        final int width = 80;
        System.out.print(Report.buildReportHeader(width, "Job Matching", "Matching Report Summary"));

        List<JobPosting> jobPostings = Initializer.getJobPostings().filter(j -> j.getCompany().equals(company));
        List<JobApplication> jobApplications = Initializer.getJobApplications();

        for (JobPosting job : jobPostings) {
            List<ReportEntry> matched = new DoublyLinkedList<>();
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
                    matched.add(new ReportEntry(applicant, score));
                    qualified++;
                    scoreSum += score;
                }
            }

            matched.sort((a, b) -> Double.compare(b.score, a.score));
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

            System.out.printf("%-3s | %-20s | %-30s | %-6s\n", "#", "Applicant", "Email", "Score");
            System.out.println("----------------------------------------------------------------------");
            int index = 1;
            List<String> names = new DoublyLinkedList<>();
            List<Integer> scores = new DoublyLinkedList<>();
            for (ReportEntry e : matched) {
                System.out.printf("%-3d | %-20s | %-30s | %6.2f\n",
                        index++, e.applicant.getName(), e.applicant.getContactEmail(), e.score);
                names.add(e.applicant.getName());
                scores.add((int) Math.round(e.score));
            }

            // Use pretty Chart to display bar chart
            Chart.barChart(names, scores, "Top Match Score Chart", 25, '#', true);
        }

        System.out.print(Report.buildReportFooter(width));
    }

    private static class ReportEntry {

        Applicant applicant;
        double score;

        ReportEntry(Applicant applicant, double score) {
            this.applicant = applicant;
            this.score = score;
        }
    }
}
