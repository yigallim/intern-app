package com.tarumt.utility.validation;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class StringCondition extends Condition {

    public StringCondition() {
        this("Value must be a string");
    }

    public StringCondition(String customMessage) {
        addConstraint(new Constraint(value -> value instanceof String, customMessage));
    }

    public StringCondition min(int minLen) {
        return min(minLen, "Length must be >= " + minLen);
    }

    public StringCondition min(int minLen, String customMessage) {
        addConstraint(new Constraint(value -> {
            if (!(value instanceof String)) return false;
            return ((String) value).length() >= minLen;
        }, customMessage));
        return this;
    }

    public StringCondition max(int maxLen) {
        return max(maxLen, "Length must be <= " + maxLen);
    }

    public StringCondition max(int maxLen, String customMessage) {
        addConstraint(new Constraint(value -> {
            if (!(value instanceof String)) return false;
            return ((String) value).length() <= maxLen;
        }, customMessage));
        return this;
    }

    public StringCondition regex(Pattern pattern) {
        return regex(pattern, "String does not match pattern: " + pattern);
    }

    public StringCondition regex(Pattern pattern, String customMessage) {
        addConstraint(new Constraint(value -> {
            if (!(value instanceof String)) return false;
            return pattern.matcher((String) value).matches();
        }, customMessage));
        return this;
    }

    public StringCondition regex(String pattern) {
        return regex(pattern, "String does not match pattern: " + pattern);
    }

    public StringCondition regex(String pattern, String customMessage) {
        return regex(Pattern.compile(pattern), customMessage);
    }

    public StringCondition enumeration(String... allowedValues) {
        return enumeration(allowedValues, "Value must be one of: " + Arrays.asList(allowedValues));
    }

    public StringCondition enumeration(String[] allowedValues, String customMessage) {
        List<String> list = Arrays.asList(allowedValues);
        addConstraint(new Constraint(value -> {
            if (!(value instanceof String)) return false;
            return list.contains(value);
        }, customMessage));
        return this;
    }

    public StringCondition enumeration(List<String> allowedValues) {
        return enumeration(allowedValues, "Value must be one of: " + allowedValues);
    }

    public StringCondition enumeration(List<String> allowedValues, String customMessage) {
        addConstraint(new Constraint(value -> {
            if (!(value instanceof String)) return false;
            return allowedValues.contains(value);
        }, customMessage));
        return this;
    }
}