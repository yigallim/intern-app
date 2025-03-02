package com.tarumt.dao;

import com.tarumt.entity.JobPosting;
import com.tarumt.entity.Company;
import com.tarumt.entity.Qualification;
import com.tarumt.adt.DoublyLinkedList;
import java.time.LocalDate;

/**
 *
 * @author mingzhe
 */

public class JobPostingDAO {

    // In-memory storage for JobPostings using DoublyLinkedList
    private final DoublyLinkedList<JobPosting> jobPostings;

    /**
     * Constructor initializes the DAO and adds predefined sample data.
     */
    public JobPostingDAO() {
        this.jobPostings = new DoublyLinkedList<>();
        addSampleData();
    }

    /**
     * Adds predefined sample JobPosting data to the DoublyLinkedList.
     */
    private void addSampleData() {
        // Sample Company 1 (without Location)
        Company company1 = new Company(
            "C001",
            "TechCorp",
            "Leading tech company specializing in software solutions",
            null, // Ignoring Location as requested
            "hr@techcorp.com",
            "+60123456789"
        );

        // Sample Qualifications for Job 1
        DoublyLinkedList<Qualification> qualifications1 = new DoublyLinkedList<>();
        qualifications1.add(new Qualification("Degree", "Computer Science", true));
        qualifications1.add(new Qualification("Experience", "3 years", true));

        // Sample JobPosting 1
        JobPosting job1 = new JobPosting();
        job1.setJobId("J001");
        job1.setTitle(JobPosting.JobTitle.IT);
        job1.setCompany(company1);
        job1.setSalaryMin(5000.0);
        job1.setSalaryMax(8000.0);
        job1.setDescription("Software Engineer position for developing enterprise applications.");
        job1.setQualifications(qualifications1);
        job1.setPostingDate(LocalDate.now());
        job1.setClosingDate(LocalDate.now().plusMonths(1));
        job1.setStatus(JobPosting.Status.OPEN);

        // Add JobPosting 1 to the DoublyLinkedList
        jobPostings.add(job1);

        // Sample Company 2 (without Location)
        Company company2 = new Company(
            "C002",
            "EduSolutions",
            "Education technology provider",
            null, // Ignoring Location as requested
            "careers@edusolutions.com",
            "+60198765432"
        );

        // Sample Qualifications for Job 2
        DoublyLinkedList<Qualification> qualifications2 = new DoublyLinkedList<>();
        qualifications2.add(new Qualification("Degree", "Education", true));
        qualifications2.add(new Qualification("Experience", "2 years", false));

        // Sample JobPosting 2
        JobPosting job2 = new JobPosting();
        job2.setJobId("J002");
        job2.setTitle(JobPosting.JobTitle.EDUCATION);
        job2.setCompany(company2);
        job2.setSalaryMin(4000.0);
        job2.setSalaryMax(6000.0);
        job2.setDescription("Educational consultant role for e-learning platforms.");
        job2.setQualifications(qualifications2);
        job2.setPostingDate(LocalDate.now());
        job2.setClosingDate(LocalDate.now().plusMonths(2));
        job2.setStatus(JobPosting.Status.OPEN);

        // Add JobPosting 2 to the DoublyLinkedList
        jobPostings.add(job2);
    }
    
    /**
     * Gets the DoublyLinkedList of predefined JobPostings.
     * @return The DoublyLinkedList containing all predefined JobPostings.
     */
    public DoublyLinkedList<JobPosting> getJobPostings() {
    DoublyLinkedList<JobPosting> copy = new DoublyLinkedList<>();
    for (JobPosting job : jobPostings) {
        if (!copy.contains(job)) { // // Prevent duplicate additions
            copy.add(job);
        }
    }
    return copy;
}
}