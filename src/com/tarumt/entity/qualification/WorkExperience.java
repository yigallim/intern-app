package com.tarumt.entity.qualification;

import com.tarumt.entity.BaseEntity;

public class WorkExperience extends Qualification {

    private Industry industry;
    private int years;

    public enum Industry {
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

        Industry(String displayName
        ) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    public WorkExperience(Industry industry, int years, boolean optional, Importance importance) {
        this.industry = industry;
        this.years = years;
        setOptional(optional);
        setImportance(importance);
    }

    public WorkExperience(Industry industry, int years) {
        this.industry = industry;
        this.years = years;
        setOptional(true); // default
        setImportance(Importance.LOW); // default
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public double scoreMatch(WorkExperience other) {
        if (other == null) {
            return 0;
        }
        if (this.industry != other.industry) {
            return 0;
        }
        return Math.min(1.0, (double) other.getYears() / this.getYears());
    }

    @Override
    public double score() {
        return years * 1.5; // Example scoring: each year gives 1.5 points
    }

    @Override
    public String toString() {
        return "WorkExperience{"
                + "industry='" + industry + '\''
                + ", years=" + years
                + '}';
    }

    @Override
    public String toShortString() {
        return industry + " - " + years + " yrs";
    }

    static {
        BaseEntity.registerPrefix(WorkExperience.class, "exp");
    }

}
