package com.tarumt.entity;
import com.tarumt.adt.DoublyLinkedList;
import java.util.Date;

public class JobPosting{

    private String jobId;
    private JobTitle title;
    private Company company;
    private double salaryMin;
    private double salaryMax;
    private String description;
    private DoublyLinkedList<Qualification> qualifications;
    private Date postingDate;
    private Date closingDate;
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

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "JobPosting{" + ", title=" + title + ", company=" + company + ", salaryMin=" + salaryMin + ", salaryMax=" + salaryMax + ", description=" + description + ", qualifications=" + qualifications + ", postingDate=" + postingDate + ", closingDate=" + closingDate + ", status=" + status + '}';
    }
    
    

    
}
