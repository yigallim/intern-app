package com.tarumt.entity;

/**
 *
 * @author mingzhe
 */
public class ApplicantSkill {
    private String skillType; 
    private Object skillValue; 

    public ApplicantSkill(String skillType, String value) {
        this.skillType = skillType;
        this.skillValue = parseSkillValue(value); 
    }

    // Automatic recognition and conversion of data types
    private Object parseSkillValue(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e2) {
                return value;
            }
        }
    }

    // Getters
    public String getSkillType() {
        return skillType;
    }

    public Object getSkillValue() {
        return skillValue;
    }

    @Override
    public String toString() {
        return "ApplicantSkill{type='" + skillType + "', value=" + skillValue + "}";
    }
}
