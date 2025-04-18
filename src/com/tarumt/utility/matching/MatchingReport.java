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

public class MatchingReport {

    public static void generateForCurrentCompany() {
        Company company = Context.getCompany();
        if (company == null) {
            Log.warn("No logged in company context.");
            return;
        }
        final int width = 80;
        final String module = "Intern Application Matching";
        final String reportName = "Job Applicant Matching Report";

        System.out.print(Report.buildReportHeader(width, module, reportName));
        System.out.println("\n");
        List<JobPosting> jobPostings = Initializer.getJobPostings().filter(j -> j.getCompany().equals(company));
        List<JobApplication> jobApplications = Initializer.getJobApplications();

        for (JobPosting job : jobPostings) {
            List<ReportEntry> matched = new DoublyLinkedList<>();
            int total = 0, qualified = 0;

            for (JobApplication app : jobApplications) {
                if (!app.getJobPosting().equals(job)) {
                    continue;
                }

                total++;
                Applicant applicant = app.getApplicant();
                double score = JobMatchingUtil.calculateScore(job, applicant);
                boolean isQualified = JobMatchingUtil.isQualified(job, applicant);

                if (isQualified) {
                    matched.add(new ReportEntry(applicant, score));
                    qualified++;
                }
            }

            matched.sort((a, b) -> Double.compare(b.score, a.score));

            System.out.println("📄 Matching Report for Job: " + job.getTitle());
            System.out.printf("\nTotal Applicants : %d\n", total);
            System.out.printf("Qualified        : %d\n", qualified);
            System.out.printf("Success Rate     : %.2f%%\n\n", total == 0 ? 0 : (qualified * 100.0 / total));
            double totalScore = 0.0;
            for (ReportEntry e : matched) {
                totalScore += e.score;
            }
            double avgScore = qualified == 0 ? 0.0 : totalScore / qualified;
            System.out.printf("\n📈 Average Score of Qualified Applicants: %.2f\n", avgScore);

            if (qualified > 0) {
                System.out.println();
                String header = String.format("| %-3s | %-20s | %-30s | %-6s |", "#", "Applicant", "Email", "Score");
                String line = "+" + "-".repeat(header.length() - 2) + "+";

                System.out.println(line);
                System.out.println(header);
                System.out.println(line);

                int index = 1;
                for (ReportEntry e : matched) {
                    System.out.printf("| %-3d | %-20s | %-30s | %6.2f |\n",
                            index++, e.applicant.getName(), e.applicant.getContactEmail(), e.score);
                }
                System.out.println(line);

                // Bar chart
                System.out.println("\n\n📊 Top Match Score Chart:\n");
                for (int i = 0; i < Math.min(5, matched.size()); i++) {
                    ReportEntry e = matched.get(i);
                    int barLength = (int) (e.score * 2);
                    System.out.printf("%-20s | %s %.2f\n", e.applicant.getName(), "#".repeat(barLength), e.score);
                }
            } else {
                System.out.println("\n[INFO] No qualified applicants for this job.");
            }

            System.out.println("\n================================================================================\n");
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
