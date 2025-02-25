package com.tarumt.validation;

public final class ValueParser {

    private ValueParser() {
    }

    public static Double asDouble(Object value) {
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        if (value instanceof String) {
            try {
                return Double.valueOf((String) value);
            } catch (NumberFormatException ex) {
                return null;
            }
        }
        return null;
    }

    public static Integer asInteger(Object value) {
        Double d = asDouble(value);
        if (d == null) return null;

        if (d.intValue() == d.doubleValue()) {
            return d.intValue();
        }
        return null;
    }
}
