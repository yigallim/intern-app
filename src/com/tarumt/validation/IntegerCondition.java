package com.tarumt.validation;

import java.util.List;

public class IntegerCondition extends Condition {

    public IntegerCondition() {
        this("Value must be an integer");
    }

    public IntegerCondition(String customMessage) {
        addConstraint(new Constraint(value -> {
            Double d = ValueParser.asDouble(value);
            if (d == null) return false;
            return d.intValue() == d.doubleValue();
        }, customMessage));
    }

    public IntegerCondition min(int minValue) {
        return min(minValue, "Value must be >= " + minValue);
    }

    public IntegerCondition min(int minValue, String customMessage) {
        addConstraint(new Constraint(value -> {
            Double d = ValueParser.asDouble(value);
            return (d != null && d >= minValue);
        }, customMessage));
        return this;
    }

    public IntegerCondition max(int maxValue) {
        return max(maxValue, "Value must be <= " + maxValue);
    }

    public IntegerCondition max(int maxValue, String customMessage) {
        addConstraint(new Constraint(value -> {
            Double d = ValueParser.asDouble(value);
            return (d != null && d <= maxValue);
        }, customMessage));
        return this;
    }

    public IntegerCondition enumeration(Integer... allowedValues) {
        return enumeration(allowedValues, "Value must be one of: ...");
    }

    public IntegerCondition enumeration(Integer[] allowedValues, String customMessage) {
        addConstraint(new Constraint(value -> {
            Integer valInt = ValueParser.asInteger(value);
            if (valInt == null) return false;

            for (Integer allowed : allowedValues) {
                if (valInt.equals(allowed)) {
                    return true;
                }
            }
            return false;
        }, customMessage));
        return this;
    }

    public IntegerCondition enumeration(List<Integer> allowedValues) {
        return enumeration(allowedValues, "Value must be one of: " + allowedValues);
    }

    public IntegerCondition enumeration(List<Integer> allowedValues, String customMessage) {
        addConstraint(new Constraint(value -> {
            Integer valInt = ValueParser.asInteger(value);
            if (valInt == null) return false;
            return allowedValues.contains(valInt);
        }, customMessage));
        return this;
    }
}