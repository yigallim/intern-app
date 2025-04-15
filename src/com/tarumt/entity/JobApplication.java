package com.tarumt.entity;

import com.tarumt.utility.common.Strings;
import com.tarumt.utility.pretty.annotation.ColumnIndex;
import com.tarumt.utility.pretty.annotation.Computed;
import com.tarumt.utility.pretty.annotation.ExcludeKey;
import com.tarumt.utility.pretty.annotation.OutputLength;

import java.time.LocalDate;

public class JobApplication extends BaseEntity {
    static {
        BaseEntity.registerPrefix(JobApplication.class, "e");
    }

    @ExcludeKey("employer")
    @OutputLength(30)
    private JobPosting jobPosting;
    @ExcludeKey("applicant")
    @OutputLength(26)
    private Applicant applicant;
    @OutputLength(14)
    private Status status;
    @OutputLength(12)
    private LocalDate appliedAt;

    public JobApplication(JobPosting jobPosting, Applicant applicant, Status status, LocalDate appliedAt) {
        super();
        this.jobPosting = jobPosting;
        this.applicant = applicant;
        this.status = status;
        this.appliedAt = appliedAt;
    }

    public enum Status {
        PENDING,     // Not reviewed yet
        SHORTLISTED, // Waiting for interview
        INTERVIEWED, // Post interview, waiting for offer
        OFFERED,     // Employer offer job, waiting applicant to accept
        ACCEPTED,    // Both parties agreed employment
        REJECTED,    // Rejected by employer (at any stage)
        WITHDRAWN;   // Withdrawn by applicant (at any stage)

        @Override
        public String toString() {
            return Strings.constantCaseToTitleCase(this.name());
        }
    }

    @ExcludeKey("employer")
    @ColumnIndex(2)
    @OutputLength(26)
    @Computed("Company")
    private String computedCompany() {
        return this.jobPosting.getCompany().toShortString();
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

    public LocalDate getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDate appliedAt) {
        this.appliedAt = appliedAt;
    }
    

    @Override
    public String toShortString() {
        return this.getId() + ", " + this.getJobPosting().getTitle();
    }

    @Override
    public String toString() {
        return "Job Application\n" +
                "|  ID          => " + getId() + ",\n" +
                "|  Applicant   => " + applicant + ",\n" +
                "|  Job Posting => " + jobPosting + ",\n" +
                "|  Status      => " + (status != null ? status.toString() : "N/A") + ",\n" +
                "|  Applied At  => " + (appliedAt != null ? appliedAt.toString() : "N/A");
    }
}

