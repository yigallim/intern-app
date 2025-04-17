package com.tarumt.entity.interview;

import com.tarumt.entity.BaseEntity;
import com.tarumt.entity.JobApplication;
import com.tarumt.utility.pretty.annotation.ColumnIndex;
import com.tarumt.utility.pretty.annotation.Computed;
import com.tarumt.utility.pretty.annotation.ExcludeKey;
import com.tarumt.utility.pretty.annotation.OutputLength;
import com.tarumt.utility.validation.annotation.Max;
import com.tarumt.utility.validation.annotation.Min;

import java.time.LocalDateTime;

public class Invitation extends BaseEntity {
    private static final String PREFIX = "i";
    private static int counter = 1;

    @ExcludeKey("default")
    private JobApplication jobApplication;
    @Min(5)
    @Max(50)
    @OutputLength(50)
    private String remarks;
    private LocalDateTime invitedAt;

    public Invitation(JobApplication jobApplication, String remarks, LocalDateTime invitedAt) {
        super(generateId());
        this.jobApplication = jobApplication;
        this.remarks = remarks;
        this.invitedAt = invitedAt;
    }

    private static String generateId() {
        String id = PREFIX + counter;
        counter++;
        return id;
    }

    public static String getNextId() {
        return PREFIX + counter;
    }

    @ColumnIndex(2)
    @OutputLength(14)
    @Computed("Application ID")
    private String computedJobApplicationId() {
        return this.jobApplication.getId();
    }

    @ColumnIndex(3)
    @OutputLength(30)
    @Computed("Job Posting")
    private String computedJobPosting() {
        return this.jobApplication.getJobPosting().toShortString();
    }

    @ExcludeKey("applicant")
    @ColumnIndex(4)
    @OutputLength(30)
    @Computed("Applicant")
    private String computedApplicant() {
        return this.jobApplication.getApplicant().toShortString();
    }

    @ExcludeKey("employer")
    @ColumnIndex(4)
    @OutputLength(26)
    @Computed("Company")
    private String computedCompany() {
        return this.jobApplication.getJobPosting().getCompany().toShortString();
    }

    public JobApplication getJobApplication() {
        return jobApplication;
    }

    public void setJobApplication(JobApplication jobApplication) {
        this.jobApplication = jobApplication;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDateTime getInvitedAt() {
        return invitedAt;
    }

    public void setInvitedAt(LocalDateTime invitedAt) {
        this.invitedAt = invitedAt;
    }

    @Override
    public String toShortString() {
        return this.getId() + " | Job: " + this.jobApplication.getJobPosting().toShortString() + " | Company: " + this.jobApplication.getJobPosting().getCompany().toShortString();
    }
}
