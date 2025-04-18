package com.tarumt.entity.qualification;

import com.tarumt.entity.BaseEntity;
import com.tarumt.entity.JobPosting;
import com.tarumt.utility.search.annotation.Fuzzy;

public class Skill extends Qualification {

    private final JobPosting.Type skillType;
    @Fuzzy
    private final String skillName;
    private final ProficiencyLevel proficiencyLevel;

    public enum ProficiencyLevel {
        BEGINNER, INTERMEDIATE, ADVANCED, EXPERT
    }

//    public enum SkillCategory {
//        ADMIN_OFFICE("Admin & Office Support"),
//        ACCOUNTING("Accounting"),
//        DESIGN_ARCH("Design & Architecture"),
//        HR_RECRUIT("HR & Recruitment"),
//        CONSTRUCTION("Construction"),
//        SCIENCE_TECH("Science & Technology"),
//        REAL_EST("Real Estate & Property"),
//        AD_ARTS_MEDIA("Advertising, Arts & Media"),
//        CALL_CUST_SVC("Call Centre & Customer Service"),
//        CONSULTING("Consulting & Strategy"),
//        LEGAL("Legal"),
//        EDUC_TRAINING("Education & Training"),
//        INSURANCE("Insurance & Superannuation"),
//        BANK_FIN_SERV("Banking & Financial Services"),
//        SALES("Sales"),
//        SPORT_RECREATION("Sport & Recreation"),
//        MANF_LOG("Manufacturing & Logistics"),
//        IT_COMM_TEC("Information & Communication Tech"),
//        HEALTH_MED("Healthcare & Medical"),
//        HOSP_TOURISM("Hospitality & Tourism"),
//        MARKETING_COMM("Marketing & Communications"),
//        AGRICULTURE("Agriculture"),
//        RETAIL_CONSUMER("Retail & Consumer Products"),
//        ENGINEERING("Engineering"),
//        COMM_SVC_DEV("Community Services & Development"),
//        OTHER("Others");
//
//        private final String displayName;
//
//        SkillCategory(String displayName) {
//            this.displayName = displayName;
//        }
//
//        @Override
//        public String toString() {
//            return displayName;
//        }
//    }
    public Skill(JobPosting.Type skillType, String skillName, ProficiencyLevel proficiencyLevel, boolean optional, Importance importance) {
        this.skillType = skillType;
        this.skillName = skillName;
        this.proficiencyLevel = proficiencyLevel;
        setOptional(optional);
        setImportance(importance);
    }

    public Skill(JobPosting.Type skillType, String skillName, ProficiencyLevel proficiencyLevel) {
        this.skillType = skillType;
        this.skillName = skillName;
        this.proficiencyLevel = proficiencyLevel;
        setOptional(true); // default
        setImportance(Importance.LOW); // default
    }

    public JobPosting.Type getSkillType() {
        return skillType;
    }

    public String getSkillName() {
        return skillName;
    }

    public ProficiencyLevel getProficiencyLevel() {
        return proficiencyLevel;
    }

    public double scoreMatch(Skill other) {
        if (other == null) {
            return 0;
        }
        if (!this.skillName.equalsIgnoreCase(other.skillName)) {
            return 0;
        }

        switch (other.getProficiencyLevel()) {
            case EXPERT:
                return 1.0;
            case ADVANCED:
                return 0.8;
            case INTERMEDIATE:
                return 0.5;
            case BEGINNER:
                return 0.2;
            default:
                return 0.0;
        }
    }

    @Override
    public double score() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
        return skillType + " - " + skillName + " (" + proficiencyLevel + ")";
    }

    @Override
    public String toShortString() {
        return skillName + " (" + proficiencyLevel + ")";
    }

    static {
        BaseEntity.registerPrefix(Skill.class, "skl");
    }

}
