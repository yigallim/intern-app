package com.tarumt.entity;

/**
 *
 * @author mingzhe
 */

public class Qualification {

    private String requirementType;
    private Object requirementValue;
    private boolean isMandatory;

    public Qualification(String requirementType, Object requirementValue, boolean isMandatory) {
        this.requirementType = requirementType;
        this.requirementValue = requirementValue;
        this.isMandatory = isMandatory;
    }
    
    // Automatic recognition and conversion of data types
    private Object parseRequirementValue(String value) {
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

    public String getRequirementType() {return requirementType;}

    public Object getRequirementValue() {return requirementValue;}

    public boolean isMandatory() {return isMandatory;}

    @Override
    public String toString() {
        return "Qualification{" + "requirementType=" + requirementType + ", requirementValue=" + requirementValue + ", isMandatory=" + isMandatory + '}';
    }
    
}
