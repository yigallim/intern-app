/**
 * @author Lim Yuet Yang
 */
package com.tarumt.entity;

import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Strings;
import com.tarumt.utility.pretty.annotation.ColumnIndex;
import com.tarumt.utility.pretty.annotation.Computed;
import com.tarumt.utility.pretty.annotation.ExcludeKey;
import com.tarumt.utility.pretty.annotation.OutputLength;

import java.time.LocalDateTime;
import java.util.Objects;

public class JobApplication extends BaseEntity {
    private static final String PREFIX = "e";
    private static int counter = 1;

    @ExcludeKey("employer")
    @OutputLength(30)
    private JobPosting jobPosting;
    @ExcludeKey("applicant")
    @OutputLength(26)
    private Applicant applicant;
    @OutputLength(14)
    private Status status;
    private LocalDateTime appliedAt;
    @ExcludeKey("default")
    private LocalDateTime terminatedAt;

    public JobApplication(JobPosting jobPosting, Applicant applicant, Status status, LocalDateTime appliedAt) {
        super(generateId());
        this.jobPosting = jobPosting;
        this.applicant = applicant;
        this.status = status;
        this.appliedAt = appliedAt;
        this.terminatedAt = null;
    }

    public JobApplication(JobPosting jobPosting, Applicant applicant, Status status, LocalDateTime appliedAt, LocalDateTime terminatedAt) {
        super(generateId());
        this.jobPosting = jobPosting;
        this.applicant = applicant;
        this.status = status;
        this.appliedAt = appliedAt;
        this.terminatedAt = terminatedAt;
    }

    private static String generateId() {
        String id = PREFIX + counter;
        counter++;
        return id;
    }

    public static String getNextId() {
        return PREFIX + counter;
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
    @ColumnIndex(3)
    @OutputLength(26)
    @Computed("Company")
    private String computedCompany() {
        return this.jobPosting.getCompany().toShortString();
    }

    // TODO : computed score

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

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    public LocalDateTime getTerminatedAt() {
        return terminatedAt;
    }

    public void setTerminatedAt(LocalDateTime terminatedAt) {
        this.terminatedAt = terminatedAt;
    }

    public boolean isOngoing() {
        return status == Status.PENDING ||
                status == Status.SHORTLISTED ||
                status == Status.INTERVIEWED ||
                status == Status.OFFERED;
    }

    public boolean isTerminated() {
        return status == Status.ACCEPTED ||
                status == Status.REJECTED ||
                status == Status.WITHDRAWN;
    }

    public boolean isReadyForInterview() {
        return status == Status.SHORTLISTED || status == Status.INTERVIEWED;
    }

    @Override
    public String toShortString() {
        StringBuilder sb = new StringBuilder(getId())
                .append(" | Job: ").append(jobPosting.toShortString());
        if (Context.isEmployer()) {
            sb.append(" | Applicant: ").append(applicant.toShortString());
        } else if (Context.isApplicant()) {
            sb.append(" | Company: ").append(computedCompany());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Job Application\n" +
                "|  ID          => " + getId() + ",\n" +
                "|  Applicant   => " + (applicant != null ? applicant.toShortString() : "N/A") + ",\n" +
                "|  Company     => " + (jobPosting != null ? jobPosting.getCompany().toShortString() : "N/A") + ",\n" +
                "|  Job Posting => " + (jobPosting != null ? jobPosting.toShortString() : "N/A") + ",\n" +
                "|  Status      => " + (status != null ? status.toString() : "N/A") + ",\n" +
                "|  Applied At  => " + Strings.formatDateTime(appliedAt);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        JobApplication that = (JobApplication) o;
        return Objects.equals(jobPosting, that.jobPosting) && Objects.equals(applicant, that.applicant) && status == that.status && Objects.equals(appliedAt, that.appliedAt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(jobPosting);
        result = 31 * result + Objects.hashCode(applicant);
        result = 31 * result + Objects.hashCode(status);
        result = 31 * result + Objects.hashCode(appliedAt);
        return result;
    }
}

