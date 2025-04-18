/**
 * @author Lim Yuet Yang
 */
package com.tarumt.entity.interview;

import com.tarumt.entity.BaseEntity;
import com.tarumt.entity.JobApplication;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.pretty.annotation.ColumnIndex;
import com.tarumt.utility.pretty.annotation.Computed;
import com.tarumt.utility.pretty.annotation.ExcludeKey;
import com.tarumt.utility.pretty.annotation.OutputLength;
import com.tarumt.utility.search.annotation.Fuzzy;
import com.tarumt.utility.validation.annotation.Max;
import com.tarumt.utility.validation.annotation.Min;

import java.time.LocalDateTime;
import java.util.Objects;

public class ScheduledInterview extends BaseEntity {
    private static final String PREFIX = "s";
    private static int counter = 1;

    @ExcludeKey("default")
    private JobApplication jobApplication;
    @Min(5)
    @Max(50)
    @Fuzzy
    @OutputLength(50)
    private String remarks;
    @ExcludeKey("default")
    private TimeSlot timeSlot;
    @ExcludeKey("default")
    @Min(0)
    @Max(10)
    private int rating;
    @Fuzzy
    private LocalDateTime bookedAt;

    public ScheduledInterview(JobApplication jobApplication, String remarks, TimeSlot timeSlot, LocalDateTime bookedAt) {
        super(generateId());
        this.jobApplication = jobApplication;
        this.remarks = remarks;
        this.timeSlot = timeSlot;
        this.rating = -1;
        this.bookedAt = bookedAt;
    }

    public ScheduledInterview(JobApplication jobApplication, String remarks, TimeSlot timeSlot, int rating, LocalDateTime bookedAt) {
        super(generateId());
        this.jobApplication = jobApplication;
        this.remarks = remarks;
        this.timeSlot = timeSlot;
        this.rating = (rating >= -1 && rating <= 10) ? rating : -1;
        this.bookedAt = bookedAt;
    }

    private static String generateId() {
        String id = PREFIX + counter;
        counter++;
        return id;
    }

    @Fuzzy
    @ColumnIndex(2)
    @OutputLength(14)
    @Computed("Application ID")
    private String computedJobApplicationId() {
        return this.jobApplication.getId();
    }

    @Fuzzy
    @ColumnIndex(3)
    @OutputLength(30)
    @Computed("Job Posting")
    private String computedJobPosting() {
        return this.jobApplication.getJobPosting().toShortString();
    }

    @Fuzzy
    @ColumnIndex(4)
    @OutputLength(12)
    @Computed("Date")
    private String computedDate() {
        return this.timeSlot.getDate().toString();
    }

    @Fuzzy
    @ColumnIndex(5)
    @OutputLength(12)
    @Computed("Time")
    private String computedTime() {
        return this.timeSlot.getTimeRangeString();
    }

    @Fuzzy
    @ExcludeKey("applicant")
    @ColumnIndex(6)
    @OutputLength(30)
    @Computed("Applicant")
    private String computedApplicant() {
        return this.jobApplication.getApplicant().toShortString();
    }

    @Fuzzy
    @ExcludeKey("employer")
    @ColumnIndex(6)
    @OutputLength(26)
    @Computed("Company")
    private String computedCompany() {
        return this.jobApplication.getJobPosting().getCompany().toShortString();
    }

    @Fuzzy
    @ExcludeKey({"employer", "admin"})
    @ColumnIndex(7)
    @OutputLength(26)
    @Computed("Location")
    private String computedLocation() {
        return this.jobApplication.getJobPosting().getCompany().getLocation().toString();
    }

    @Fuzzy
    @ExcludeKey("applicant")
    @ColumnIndex(8)
    @OutputLength(6)
    @Computed("Rating")
    private String computedRating() {
        return this.rating == -1 ? "N/A" : String.valueOf(rating);
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
        StringBuilder sb = new StringBuilder(getId());

        if (timeSlot != null) {
            sb.append(" | ").append(timeSlot.getDate()).append(" ").append(timeSlot.getTimeRangeString());
        }
        if (Context.isEmployer()) {
            sb.append(" | Applicant: ").append(computedApplicant());
        } else if (Context.isApplicant()) {
            sb.append(" | Company: ").append(computedCompany());
        } else {
            sb.append(" | Job: ").append(computedJobPosting());
        }
        return sb.toString();
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        ScheduledInterview that = (ScheduledInterview) o;
        return rating == that.rating && Objects.equals(jobApplication, that.jobApplication) && Objects.equals(remarks, that.remarks) && Objects.equals(timeSlot, that.timeSlot) && Objects.equals(bookedAt, that.bookedAt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(jobApplication);
        result = 31 * result + Objects.hashCode(remarks);
        result = 31 * result + Objects.hashCode(timeSlot);
        result = 31 * result + rating;
        result = 31 * result + Objects.hashCode(bookedAt);
        return result;
    }
}