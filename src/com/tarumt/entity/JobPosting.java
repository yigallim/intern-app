package com.tarumt.entity;

import com.tarumt.entity.qualification.Qualification;
import com.tarumt.utility.common.Strings;
import com.tarumt.utility.pretty.annotation.ColumnIndex;
import com.tarumt.utility.pretty.annotation.Computed;
import com.tarumt.utility.pretty.annotation.ExcludeKey;
import com.tarumt.utility.pretty.annotation.OutputLength;
import com.tarumt.utility.search.annotation.Fuzzy;
import com.tarumt.utility.validation.annotation.Max;
import com.tarumt.utility.validation.annotation.Min;

import java.time.LocalDateTime;
import com.tarumt.adt.list.ListInterface;

// TODO : CLOSED job cant be updated or displayed, FILLED job can be reopen and displayed
public class JobPosting extends BaseEntity {
    private static final String PREFIX = "j";
    private static int counter = 1;

    @Min(3)
    @Max(30)
    @Fuzzy
    @OutputLength(28)
    private String title;
    @Fuzzy
    @ExcludeKey("employer")
    private Company company;
    @ExcludeKey("default")
    private int salaryMin;
    @ExcludeKey("default")
    private int salaryMax;
    @Min(20)
    @Max(100)
    @Fuzzy
    @OutputLength(60)
    private String description;
    @Fuzzy
    @OutputLength(34)
    private Type type;
    @ExcludeKey("default")
    private ListInterface<Qualification> qualifications;
    @OutputLength(8)
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public JobPosting(String title, Company company, int salaryMin, int salaryMax, String description, Type type, ListInterface<Qualification> qualifications, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(generateId());
        this.title = title;
        this.company = company;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.description = description;
        this.type = type;
        this.qualifications = qualifications;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private static String generateId() {
        String id = PREFIX + counter;
        counter++;
        return id;
    }

    public static String getNextId() {
        return PREFIX + counter;
    }

    public enum Type {
        ADMIN_OFFICE("Admin & Office Support"),
        ACCOUNTING("Accounting"),
        DESIGN_ARCH("Design & Architecture"),
        HR_RECRUIT("HR & Recruitment"),
        CONSTRUCTION("Construction"),
        SCIENCE_TECH("Science & Technology"),
        REAL_EST("Real Estate & Property"),
        AD_ARTS_MEDIA("Advertising, Arts & Media"),
        CALL_CUST_SVC("Call Centre & Customer Service"),
        CONSULTING("Consulting & Strategy"),
        LEGAL("Legal"),
        EDUC_TRAINING("Education & Training"),
        INSURANCE("Insurance & Superannuation"),
        BANK_FIN_SERV("Banking & Financial Services"),
        SALES("Sales"),
        SPORT_RECREATION("Sport & Recreation"),
        MANF_LOG("Manufacturing & Logistics"),
        IT_COMM_TEC("Information & Communication Tech"),
        HEALTH_MED("Healthcare & Medical"),
        HOSP_TOURISM("Hospitality & Tourism"),
        MARKETING_COMM("Marketing & Communications"),
        AGRICULTURE("Agriculture"),
        RETAIL_CONSUMER("Retail & Consumer Products"),
        ENGINEERING("Engineering"),
        COMM_SVC_DEV("Community Services & Development"),
        OTHER("Others");

        private final String displayName;

        Type(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    public enum Status {
        OPEN, // Applicant able to view and apply
        FILLED; // Applicant able to view, can't apply

        @Override
        public String toString() {
            return Strings.constantCaseToTitleCase(this.name());
        }
    }

    @OutputLength(14)
    @ColumnIndex(4)
    @Computed("Salary")
    private String computedSalaryRange() {
        if (salaryMin == salaryMax) return Integer.toString(this.salaryMin);
        else return this.salaryMin + "-" + this.salaryMax;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(int salaryMin) {
        this.salaryMin = salaryMin;
    }

    public int getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(int salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ListInterface<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(ListInterface<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toShortString() {
        return this.getId() + ", " + this.getTitle();
    }

    @Override
    public String toString() {
        return "Job Posting\n" +
                "|  ID           => " + getId() + ",\n" +
                "|  Title        => " + title + ",\n" +
                "|  Company      => " + (company != null ? company.toShortString() : "N/A") + ",\n" +
                "|  Salary Range => " + computedSalaryRange() + ",\n" +
                "|  Description  => " + description + ",\n" +
                "|  Type         => " + (type != null ? type.toString() : "N/A") + ",\n" +
                "|  Status       => " + (status != null ? status.toString() : "N/A") + ",\n" +
                "|  Created At   => " + Strings.formatDateTime(createdAt) + ",\n" +
                "|  Updated At   => " + Strings.formatDateTime(updatedAt);
    }
}