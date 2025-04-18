package com.tarumt.utility.matching;

import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.list.ListInterface;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.Company;
import com.tarumt.entity.JobApplication;
import com.tarumt.entity.JobPosting;
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
        final int barChartWidth = 100;
        System.out.print(Report.buildReportHeader(width, "Job Matching", "Matching Report Summary"));

        ListInterface<JobPosting> jobPostings = Initializer.getJobPostings().filter(j -> j.getCompany().equals(company));
        ListInterface<JobApplication> jobApplications = Initializer.getJobApplications();

        for (JobPosting job : jobPostings) {
            ListInterface<ReportEntry> matched = new DoublyLinkedList<>();
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

            System.out.printf("%-3s | %-25s | %-35s | %-6s\n", "#", "Applicant", "Email", "Score");
            System.out.println("--------------------------------------------------------------------------------");
            int index = 1;
            ListInterface<String> names = new DoublyLinkedList<>();
            ListInterface<Integer> scores = new DoublyLinkedList<>();
            for (ReportEntry e : matched) {
                System.out.printf("%-3d | %-25s | %-35s | %6.2f\n",
                        index++, e.applicant.getName(), e.applicant.getContactEmail(), e.score);
                names.add(e.applicant.getName());
                scores.add((int) Math.round(e.score));
            }

            // Use pretty Chart to display bar chart
            Chart.barChart(names, scores, "Top Match Score Chart", 56, '#', true);
            System.out.println("\n");
        }

        System.out.print(Report.buildReportFooter(width));
    }

    public static class ReportEntry {
        private Applicant applicant;
        private double score;

        public ReportEntry(Applicant applicant, double score) {
            this.applicant = applicant;
            this.score = score;
        }

        public Applicant getApplicant() {
            return applicant;
        }

        public void setApplicant(Applicant applicant) {
            this.applicant = applicant;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }
    }
}
