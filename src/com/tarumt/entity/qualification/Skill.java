package com.tarumt.entity.qualification;

public class Skill extends Qualification {

    private final SkillCategory category;
    private final String skillName;
    private final ProficiencyLevel proficiencyLevel;

    public enum ProficiencyLevel {
        BEGINNER, INTERMEDIATE, ADVANCED, EXPERT
    }

    public enum SkillCategory {
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

        SkillCategory(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    public Skill(SkillCategory category, String skillName, ProficiencyLevel proficiencyLevel, boolean optional, Importance importance) {
        this.category = category;
        this.skillName = skillName;
        this.proficiencyLevel = proficiencyLevel;
        setOptional(optional);
        setImportance(importance);
    }

    public SkillCategory getCategory() {
        return category;
    }

    public String getSkillName() {
        return skillName;
    }

    public ProficiencyLevel getProficiencyLevel() {
        return proficiencyLevel;
    }

    @Override
    public double score() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
        return category + " - " + skillName + " (" + proficiencyLevel + ")";
    }
}
