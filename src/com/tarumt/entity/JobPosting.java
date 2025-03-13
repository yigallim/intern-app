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

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class JobPosting extends BaseEntity {
    static {
        BaseEntity.registerPrefix(JobPosting.class, "j");
    }

    @Min(3)
    @Max(30)
    @Fuzzy(weightLevenshtein = 0.7, weightCosine = 0.3)
    @OutputLength(28)
    private String title;
    private Company company;
    @ExcludeKey("default")
    private int salaryMin;
    @ExcludeKey("default")
    private int salaryMax;
    @Min(20)
    @Max(100)
    @Fuzzy(weightLevenshtein = 0.4, weightCosine = 0.6)
    @OutputLength(60)
    private String description;
    @OutputLength(30)
    private Type type;
    @ExcludeKey("default")
    private List<Qualification> qualifications;
    @OutputLength(8)
    private Status status;
    @OutputLength(12)
    private LocalDate createdAt;
    @OutputLength(12)
    private LocalDate updatedAt;

    public JobPosting(String title, Company company, int salaryMin, int salaryMax, String description, Type type, List<Qualification> qualifications, Status status, LocalDate createdAt, LocalDate updatedAt) {
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
        OPEN,
        CLOSED,
        FILLED;

        @Override
        public String toString() {
            return Strings.constantCaseToTitleCase(this.name());
        }
    }

    @OutputLength(14)
    @ColumnIndex(3)
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

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Job Posting Details:\n");
        sb.append("  Job ID: ").append(getId()).append("\n");
        sb.append("  Title: ").append(title).append("\n");
        sb.append("  Company: \n").append(company.toString());
        sb.append("  Salary (Min-Max): ").append(salaryMin).append(" - ").append(salaryMax).append("\n");
        sb.append("  Description: ").append(description).append("\n");
        sb.append("  Type: ").append(type).append("\n");
        sb.append("  Qualifications:\n");
        if (qualifications != null) {
            for (Qualification qual : qualifications) {
                sb.append("    - ").append(qual).append("\n");
            }
        }
        sb.append("  Status: ").append(status).append("\n");
        sb.append("  Created At: ").append(createdAt).append("\n");
        sb.append("  Updated At: ").append(updatedAt);
        return sb.toString();
    }

    @Override
    public String toShortString() {
        return this.getId() + ", " + this.getTitle();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        JobPosting that = (JobPosting) o;
        return Double.compare(that.salaryMin, salaryMin) == 0 && Double.compare(that.salaryMax, salaryMax) == 0 && Objects.equals(title, that.title) && Objects.equals(company, that.company) && Objects.equals(description, that.description) && type == that.type && Objects.equals(qualifications, that.qualifications) && status == that.status && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }
}