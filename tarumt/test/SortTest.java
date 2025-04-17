package com.tarumt.test;

import com.tarumt.adt.list.ListInterface;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.JobPosting;
import com.tarumt.utility.pretty.TabularPrint;

import java.util.Comparator;

public class SortTest {
    public static void main(String[] args) {
        ListInterface<JobPosting> jobPostings = Initializer.getJobPostings();

        System.out.println("Before sorting:");
        TabularPrint.printTabular(jobPostings, true, "default"); // Assuming TabularPrint is available

        // Sort the list by job title
        jobPostings.sort(Comparator.comparing(JobPosting::getTitle));

        System.out.println("\nAfter sorting by title:");
        TabularPrint.printTabular(jobPostings, true, "default");
    }
}
