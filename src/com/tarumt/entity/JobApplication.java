package com.tarumt.entity;

import com.tarumt.utility.common.Strings;
import com.tarumt.utility.pretty.annotation.OutputLength;
import java.time.LocalDate;


/**
 *
 * @author HonYan
 */
public class JobApplication extends BaseEntity {
    
    static {
        BaseEntity.registerPrefix(JobApplication.class, "e");
    }
    
    private JobPosting jobPosting;
    private Applicant applicant;
    @OutputLength(8)
    private Status status;
    @OutputLength(12)
    private LocalDate applicationDate;

    public JobApplication(JobPosting jobPosting, Applicant applicant, Status status, LocalDate applicationDate) {
        this.jobPosting = jobPosting;
        this.applicant = applicant;
        this.status = status;
        this.applicationDate = applicationDate;
    }

    public enum Status {
        PENDING,
        REVIEWING,
        INTERVIEW,
        OFFERED,
        ACCEPTED,
        REJECTED,
        WITHDRAWN;

        @Override
        public String toString() {
            return Strings.constantCaseToTitleCase(this.name());
        }
    }        

    public JobPosting getJobPosting() {
        return jobPosting;
    }

    public void setJobPosting(JobPosting jobPosting) {
        this.jobPosting = jobPosting;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    @Override
    public String toShortString() {
        return this.getId() + ", " + this.getApplicant();
    }

    
    @Override
    public String toString() {
        return "Job Application\n" +
                "|  ID          => " + getId() + ",\n" +
                "|  Applicant        => " + applicant + ",\n" +
                "|  Job Posting       => " + jobPosting + ",\n" +
                "|  Status       => " + (status != null ? status.toString() : "N/A") + ",\n" +
                "|  Application Date   => " + (applicationDate != null ? applicationDate.toString() : "N/A");
    }
    
}

