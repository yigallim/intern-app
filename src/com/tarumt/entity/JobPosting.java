package com.tarumt.entity;

import com.tarumt.adt.DoublyLinkedList;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author mingzhe
 */
public class JobPosting {

    private String jobId;
    private JobTitle title;
    private Company company;
    private double salaryMin;
    private double salaryMax;
    private String description;
    private DoublyLinkedList<Qualification> qualifications;
    private LocalDate postingDate;
    private LocalDate closingDate;
    private Status status;

    public enum Status {
        OPEN(1, "Open"),
        CLOSED(2, "Closed"),
        FILLED(3, "Filled");

        private final int value;
        private final String displayName;

        Status(int value, String displayName) {
            this.value = value;
            this.displayName = displayName;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    public enum JobTitle {
        ACCOUNTING("Accounting"),
        ADMIN("Admin"),
        BANKING("Banking"),
        CONSTRUCTION("Construction"),
        EDUCATION("Education"),
        ENGINEERING("Engineering"),
        HEALTHCARE("Healthcare"),
        HOSPITALITY("Hospitality"),
        IT("IT"),
        MANAGEMENT("Management"),
        MARKETING("Marketing"),
        RETAIL("Retail"),
        SALES("Sales"),
        TRADES("Trades");

        private final String displayName;

        JobTitle(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public JobTitle getTitle() {
        return title;
    }

    public void setTitle(JobTitle title) {
        this.title = title;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public double getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(double salaryMin) {
        this.salaryMin = salaryMin;
    }

    public double getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(double salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DoublyLinkedList<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(DoublyLinkedList<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    public LocalDate getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(LocalDate postingDate) {
        this.postingDate = postingDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JobPosting that = (JobPosting) o;
        return Double.compare(that.salaryMin, salaryMin) == 0
                && Double.compare(that.salaryMax, salaryMax) == 0
                && jobId.equals(that.jobId)
                && title == that.title
                && company.equals(that.company)
                && description.equals(that.description)
                && qualifications.equals(that.qualifications)
                && postingDate.equals(that.postingDate)
                && closingDate.equals(that.closingDate)
                && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, title, company, salaryMin, salaryMax, description, qualifications, postingDate, closingDate, status);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Job Posting Details:\n");
        sb.append("  Job ID: ").append(jobId).append("\n");
        sb.append("  Title: ").append(title).append("\n");
        sb.append("  Company:\n");
        sb.append("    Name: ").append(company.getName()).append("\n");
        sb.append("    Description: ").append(company.getDescription()).append("\n");
        sb.append("    Contact Email: ").append(company.getContactEmail()).append("\n");
        sb.append("    Contact Phone: ").append(company.getContactPhone()).append("\n");
        sb.append("  Salary (Min-Max): ").append(salaryMin).append(" - ").append(salaryMax).append("\n");
        sb.append("  Description: ").append(description).append("\n");
        sb.append("  Qualifications:\n");
        if (qualifications != null) {
            for (Qualification qual : qualifications) {
                sb.append("    - ").append(qual).append("\n");
            }
        }
        sb.append("  Posting Date: ").append(postingDate).append("\n");
        sb.append("  Closing Date: ").append(closingDate).append("\n");
        sb.append("  Status: ").append(status).append("\n");
        return sb.toString();
    }

}
