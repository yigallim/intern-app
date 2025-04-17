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

public class ScheduledInterview extends BaseEntity {
    private static final String PREFIX = "s";
    private static int counter = 1;

    @ExcludeKey("default")
    private JobApplication jobApplication;
    @Min(5)
    @Max(50)
    @OutputLength(50)
    private String remarks;
    @ExcludeKey("default")
    private TimeSlot timeSlot;
    @ExcludeKey("applicant")
    @Min(0)
    @Max(10)
    @OutputLength(6)
    private int rating;
    private LocalDateTime bookedAt;

    public ScheduledInterview(JobApplication jobApplication, String remarks, TimeSlot timeSlot, int rating, LocalDateTime bookedAt) {
        super(generateId());
        this.jobApplication = jobApplication;
        this.remarks = remarks;
        this.timeSlot = timeSlot;
        this.rating = rating;
        this.bookedAt = bookedAt;
    }

    private static String generateId() {
        String id = PREFIX + counter;
        counter++;
        return id;
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

    @ColumnIndex(4)
    @OutputLength(12)
    @Computed("Date")
    private String computedDate() {
        return this.timeSlot.getDate().toString();
    }

    @ColumnIndex(5)
    @OutputLength(12)
    @Computed("Time")
    private String computedTime() {
        return this.timeSlot.getTimeRangeString();
    }

    @ExcludeKey("applicant")
    @ColumnIndex(6)
    @OutputLength(30)
    @Computed("Applicant")
    private String computedApplicant() {
        return this.jobApplication.getApplicant().toShortString();
    }

    @ExcludeKey("employer")
    @ColumnIndex(6)
    @OutputLength(26)
    @Computed("Company")
    private String computedCompany() {
        return this.jobApplication.getJobPosting().getCompany().toShortString();
    }

    public static String getNextId() {
        return PREFIX + counter;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        ScheduledInterview.counter = counter;
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

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(LocalDateTime bookedAt) {
        this.bookedAt = bookedAt;
    }

    @Override
    public String toShortString() {
        if (timeSlot != null) {
            return getId() + ", " + timeSlot.getDate().toString() + " " + timeSlot.getTimeRangeString();
        }
        return getId();
    }

    @Override
    public String toString() {
        return "ScheduledInterview{" +
                "jobApplication=" + jobApplication.toShortString() +
                ", remarks='" + remarks + '\'' +
                ", timeSlot=" + timeSlot +
                ", rating=" + rating +
                ", bookedAt=" + bookedAt +
                '}';
    }
}