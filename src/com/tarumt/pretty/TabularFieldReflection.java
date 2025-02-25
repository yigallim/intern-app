package com.tarumt.pretty;

import com.tarumt.pretty.annotation.OutputLength;
import com.tarumt.util.Log;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class TabularFieldReflection {
    public static Map<String, Integer> getAnnotatedFields(Class<?> clazz) {
        Map<String, Integer> fieldMap = new LinkedHashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(OutputLength.class)) {
                String titleCaseName = convertToTitleCase(field.getName());
                int length = field.getAnnotation(OutputLength.class).value();
                fieldMap.put(titleCaseName, length);
            }
        }
        return fieldMap;
    }

    public static Map<String, String> getFieldValues(Object obj) {
        Map<String, String> valuesMap = new LinkedHashMap<>();
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(OutputLength.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    valuesMap.put(convertToTitleCase(field.getName()), (value != null) ? value.toString() : "N/A");
                } catch (IllegalAccessException ex) {
                    Log.error("Error accessing field " + field.getName() + ": " + ex.getMessage());
                }
            }
        }
        return valuesMap;
    }

    private static String convertToTitleCase(String camelCase) {
        String[] words = camelCase.split("(?=[A-Z])");
        StringBuilder titleCase = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                titleCase.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return titleCase.toString().trim();
    }
}
