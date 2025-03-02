package com.tarumt.control;

import com.tarumt.adt.DoublyLinkedList;
import com.tarumt.dao.JobPostingDAO;
import com.tarumt.entity.Qualification;
import com.tarumt.entity.ApplicantSkill;
import com.tarumt.entity.JobPosting;
import com.tarumt.entity.Company;

import java.time.LocalDate;

/**
 *
 * @author mingzhe
 */

public class JobPostingController {

    private DoublyLinkedList<JobPosting> jobPostings;
    private static JobPostingDAO daoInstance = null;

    public JobPostingController() {
        if (daoInstance == null) {
            daoInstance = new JobPostingDAO();
        }
        this.jobPostings = daoInstance.getJobPostings();

    }

    //Creating a new JobPosting
    public boolean createJobPosting(String jobId, JobPosting.JobTitle title, Company company,
            double salaryMin, double salaryMax, String description,
            DoublyLinkedList<Qualification> qualifications,
            LocalDate postingDate, LocalDate closingDate, JobPosting.Status status) {
        JobPosting job = new JobPosting();
        job.setJobId(jobId);
        job.setTitle(title);
        job.setCompany(company);
        job.setSalaryMin(salaryMin);
        job.setSalaryMax(salaryMax);
        job.setDescription(description);
        job.setQualifications(qualifications);
        job.setPostingDate(postingDate);
        job.setClosingDate(closingDate);
        job.setStatus(status);
        return jobPostings.add(job);
    }

    // Read (Retrieve) a JobPosting by jobId
    public JobPosting readJobPosting(String jobId) {
        for (JobPosting job : jobPostings) {
            if (job.getJobId().equals(jobId)) {
                return job;
            }
        }

        return null; //Job not found
    }

    // Update a JobPosting by jobId
    public boolean updateJobPosting(String jobId, JobPosting.JobTitle title, Company company,
            double salaryMin, double salaryMax, String description,
            DoublyLinkedList<Qualification> qualifications,
            LocalDate postingDate, LocalDate closingDate, JobPosting.Status status) {
        JobPosting job = readJobPosting(jobId);
        if (job != null) {
            job.setTitle(title);
            job.setCompany(company);
            job.setSalaryMin(salaryMin);
            job.setSalaryMax(salaryMax);
            job.setDescription(description);
            job.setQualifications(qualifications);
            job.setPostingDate(postingDate);
            job.setClosingDate(closingDate);
            job.setStatus(status);
            return true;
        }
        return false; // Job not found
    }

    public boolean deleteJobPosting(String jobId) {
        for (int i = 1; i <= jobPostings.getLength(); i++) {
            JobPosting job = jobPostings.getEntry(i);
            if (job.getJobId().equals(jobId)) {
                jobPostings.remove(i);
                return true;
            }
        }
        return false; // Job not found
    }

    // Get all job postings
    public DoublyLinkedList<JobPosting> getAllJobPostings() {
        return jobPostings;
    }

}
