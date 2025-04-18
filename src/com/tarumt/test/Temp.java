package com.tarumt.test;

import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.list.ListInterface;
import com.tarumt.utility.common.Strings;
import com.tarumt.utility.pretty.Chart;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Temp {
    public static void main(String[] args) {
        String divider = Strings.repeat("=", 120);
        String subDivider = Strings.repeat("-", 120);

        System.out.println(divider);
        System.out.println("                               TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY");
        System.out.println("                                              INTERVIEW SCHEDULING MODULE");
        System.out.println("                                   --------------------------------------------------");
        System.out.println("                                             PAST INTERVIEW ANALYSIS REPORT");
        System.out.println(subDivider);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE yyyy-MM-dd HH:mm:ss");
        System.out.println("Generated at: " + now.format(formatter));
        System.out.println(subDivider);
        System.out.println("                                                                                      Report Generated For Past (7) Days");
        System.out.println("Company: c1, TechCorp");
        System.out.println(subDivider);
        System.out.printf("%-10s %-46s %-40s %-20s\n", "Job ID", "Job Title", "Applicants", "Status");
        System.out.println(subDivider);

        System.out.printf("%-10s %-46s %-40s %-20s\n", "j3", "DevOps Engineer", "a1, Alice Johnson", "ACCEPTED");
        System.out.printf("%-10s %-46s %-40s %-20s\n", "", "", "a3, Charlie Brown", "REJECTED");
        System.out.println();
        System.out.printf("%-10s %-46s %-40s %-20s\n", "j4", "QA Engineer", "a1, Alice Johnson", "ACCEPTED");
        System.out.printf("%-10s %-46s %-40s %-20s\n", "", "", "a4, Daisy Williams", "ACCEPTED");
        System.out.println();
        System.out.printf("%-10s %-46s %-40s %-20s\n", "j5", "UI/UX Designer", "a1, Alice Johnson", "WITHDRAWN");

        System.out.println(subDivider);
        System.out.println("Total ACCEPTED Count: 3");
        System.out.println("Total REJECTED Count: 1");
        System.out.println("Total WITHDRAWN Count: 1");



        // Bar chart for acceptance count per job
        ListInterface<String> acceptedCategories = new DoublyLinkedList<>();
        acceptedCategories.add("DevOps Engineer");
        acceptedCategories.add("QA Engineer");
        acceptedCategories.add("UI/UX Designer");

        ListInterface<Integer> acceptedCounts = new DoublyLinkedList<>();
        acceptedCounts.add(1); // DevOps: Alice
        acceptedCounts.add(2); // QA: Alice, Daisy
        acceptedCounts.add(0); // UI/UX: None

        String acceptedChart = Chart.barChart(acceptedCategories, acceptedCounts, "Acceptance Count per Job Posting", 120, '█', true);
        System.out.print(acceptedChart);

        System.out.println("Job(s) with the most ACCEPTED applicants (2 accepted):");
        System.out.println("< j4, QA Engineer >");
        System.out.println();
        System.out.println("Job(s) with the least ACCEPTED applicants (0 accepted):");
        System.out.println("< j5, UI/UX Designer | j1, Software Engineer | j2, Senior Developer >");
        System.out.println();
        System.out.println(subDivider);
        System.out.println("                                                     END OF REPORT");
        System.out.println(divider);
    }
}
