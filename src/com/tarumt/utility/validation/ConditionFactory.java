/**
 * @author Lim Yuet Yang
 */
package com.tarumt.utility.validation;

import com.tarumt.utility.validation.annotation.Max;
import com.tarumt.utility.validation.annotation.Min;
import com.tarumt.utility.validation.annotation.Regex;
import com.tarumt.utility.validation.annotation.Enumeration;

import java.lang.reflect.Field;

import com.tarumt.adt.list.ListInterface;
import com.tarumt.adt.list.DoublyLinkedList;

public final class ConditionFactory {

    private ConditionFactory() {
    }

    public static IntegerCondition integer() {
        return new IntegerCondition();
    }

    public static IntegerCondition integer(String customMessage) {
        return new IntegerCondition(customMessage);
    }

    public static DecimalCondition decimal() {
        return new DecimalCondition();
    }

    public static DecimalCondition decimal(String customMessage) {
        return new DecimalCondition(customMessage);
    }

    public static StringCondition string() {
        return new StringCondition();
    }

    public static StringCondition string(String customMessage) {
        return new StringCondition(customMessage);
    }

    public static Condition fromField(Field field) {
        Condition condition = getCondition(field);

        Min minAnn = field.getAnnotation(Min.class);
        Max maxAnn = field.getAnnotation(Max.class);
        if (minAnn != null) {
            applyMin(condition, minAnn.value(), minAnn.message());
        }
        if (maxAnn != null) {
            applyMax(condition, maxAnn.value(), maxAnn.message());
        }

        Regex regexAnn = field.getAnnotation(Regex.class);
        if (regexAnn != null) {
            applyRegex(condition, regexAnn.pattern(), regexAnn.message());
        }

        Enumeration enumerationAnn = field.getAnnotation(Enumeration.class);
        if (enumerationAnn != null) {
            applyEnumeration(condition, enumerationAnn.value(), enumerationAnn.message());
        }

        return condition;
    }

    private static Condition getCondition(Field field) {
        Class<?> fieldType = field.getType();

        if (fieldType.equals(String.class)) {
            return new StringCondition();
        } else if (fieldType.equals(int.class) || fieldType.equals(Integer.class)) {
            return new IntegerCondition();
        } else if (fieldType.equals(float.class) || fieldType.equals(Float.class)
                || fieldType.equals(double.class) || fieldType.equals(Double.class)) {
            return new DecimalCondition();
        } else {
            throw new IllegalArgumentException("Unsupported field type: " + fieldType.getName());
        }
    }

    private static void applyMin(Condition condition, int minValue, String customMessage) {
        if (condition instanceof IntegerCondition) {
            IntegerCondition integerCondition = (IntegerCondition) condition;
            if (!customMessage.isEmpty()) {
                integerCondition.min(minValue, customMessage);
            } else {
                integerCondition.min(minValue);
            }
        } else if (condition instanceof DecimalCondition) {
            DecimalCondition decimalCondition = (DecimalCondition) condition;
            if (!customMessage.isEmpty()) {
                decimalCondition.min(minValue, customMessage);
            } else {
                decimalCondition.min(minValue);
            }
        } else if (condition instanceof StringCondition) {
            StringCondition stringCondition = (StringCondition) condition;
            if (!customMessage.isEmpty()) {
                stringCondition.min(minValue, customMessage);
            } else {
                stringCondition.min(minValue);
            }
        }
    }

    private static void applyMax(Condition condition, int maxValue, String customMessage) {
        if (condition instanceof IntegerCondition) {
            IntegerCondition integerCondition = (IntegerCondition) condition;
            if (!customMessage.isEmpty()) {
                integerCondition.max(maxValue, customMessage);
            } else {
                integerCondition.max(maxValue);
            }
        } else if (condition instanceof DecimalCondition) {
            DecimalCondition decimalCondition = (DecimalCondition) condition;
            if (!customMessage.isEmpty()) {
                decimalCondition.max(maxValue, customMessage);
            } else {
                decimalCondition.max(maxValue);
            }
        } else if (condition instanceof StringCondition) {
            StringCondition stringCondition = (StringCondition) condition;
            if (!customMessage.isEmpty()) {
                stringCondition.max(maxValue, customMessage);
            } else {
                stringCondition.max(maxValue);
            }
        }
    }

    private static void applyRegex(Condition condition, String pattern, String message) {
        if (!(condition instanceof StringCondition)) {
            throw new IllegalArgumentException("@Regex is only valid for String fields");
        }
        StringCondition stringCondition = (StringCondition) condition;
        if (!message.isEmpty()) {
            stringCondition.regex(pattern, message);
        } else {

            stringCondition.regex(pattern);
        }
    }

    private static void applyEnumeration(Condition condition, String[] allowedValues, String message) {
        if (condition instanceof StringCondition) {

            StringCondition stringCondition = (StringCondition) condition;
            if (!message.isEmpty()) {
                stringCondition.enumeration(allowedValues, message);
            } else {
                stringCondition.enumeration(allowedValues);
            }
        } else if (condition instanceof IntegerCondition) {
            IntegerCondition integerCondition = (IntegerCondition) condition;
            ListInterface<Integer> values = new DoublyLinkedList<>();
            for (String val : allowedValues) {
                try {
                    values.add(Integer.valueOf(val));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(
                            "Cannot parse enumeration value '" + val + "' as Integer");
                }
            }
            if (!message.isEmpty()) {
                integerCondition.enumeration(values, message);
            } else {
                integerCondition.enumeration(values);
            }
        } else if (condition instanceof DecimalCondition) {
            DecimalCondition decimalCondition = (DecimalCondition) condition;
            ListInterface<Double> values = new DoublyLinkedList<>();
            for (String val : allowedValues) {
                try {
                    values.add(Double.valueOf(val));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(
                            "Cannot parse enumeration value '" + val + "' as Double");
                }
            }
            if (!message.isEmpty()) {
                decimalCondition.enumeration(values, message);
            } else {
                decimalCondition.enumeration(values);
            }
        } else {
            throw new IllegalArgumentException("@Enumeration not supported for condition: "
                    + condition.getClass().getName());
        }
    }
}