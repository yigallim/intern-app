/**
 * @author Lim Yuet Yang
 */
package com.tarumt.utility.validation;

import com.tarumt.adt.list.ListInterface;

public class DecimalCondition extends Condition {

    public DecimalCondition() {
        this("Value must be a floating-point number");
    }

    public DecimalCondition(String customMessage) {
        addConstraint(new Constraint(value -> {
            Double d = ValueParser.asDouble(value);
            return d != null;
        }, customMessage));
    }

    public DecimalCondition min(double minValue) {
        return min(minValue, "Value must be >= " + minValue);
    }

    public DecimalCondition min(double minValue, String customMessage) {
        addConstraint(new Constraint(value -> {
            Double d = ValueParser.asDouble(value);
            return (d != null && d >= minValue);
        }, customMessage));
        return this;
    }

    public DecimalCondition max(double maxValue) {
        return max(maxValue, "Value must be <= " + maxValue);
    }

    public DecimalCondition max(double maxValue, String customMessage) {
        addConstraint(new Constraint(value -> {
            Double d = ValueParser.asDouble(value);
            return (d != null && d <= maxValue);
        }, customMessage));
        return this;
    }

    public DecimalCondition enumeration(Double... allowedValues) {
        return enumeration(allowedValues, "Value must be one of: ...");
    }

    public DecimalCondition enumeration(Double[] allowedValues, String customMessage) {
        addConstraint(new Constraint(value -> {
            Double valDouble = ValueParser.asDouble(value);
            if (valDouble == null) return false;

            for (Double allowed : allowedValues) {
                if (Double.compare(valDouble, allowed) == 0) {
                    return true;
                }
            }
            return false;
        }, customMessage));
        return this;
    }

    public DecimalCondition enumeration(ListInterface<Double> allowedValues) {
        return enumeration(allowedValues, "Value must be one of: " + allowedValues);
    }

    public DecimalCondition enumeration(ListInterface<Double> allowedValues, String customMessage) {
        addConstraint(new Constraint(value -> {
            Double valDouble = ValueParser.asDouble(value);
            if (valDouble == null) return false;

            for (Double allowed : allowedValues) {
                if (Double.compare(valDouble, allowed) == 0) {
                    return true;
                }
            }
            return false;
        }, customMessage));
        return this;
    }
}