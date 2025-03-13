package com.tarumt.utility.pretty;

import com.tarumt.entity.BaseEntity;
import com.tarumt.utility.common.Strings;
import com.tarumt.utility.pretty.annotation.ColumnIndex;
import com.tarumt.utility.pretty.annotation.Computed;
import com.tarumt.utility.pretty.annotation.ExcludeKey;
import com.tarumt.utility.pretty.annotation.OutputLength;
import com.tarumt.utility.common.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;

public class TabularFieldReflection {
    public static Map<String, Integer> getAnnotatedFields(Class<?> clazz, String... excludeKeys) {
        Map<String, Integer> fieldMap = new LinkedHashMap<>();
        Set<String> excludeSet = Arrays.stream(excludeKeys)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
        final int DEFAULT_LENGTH = 20;
        Map<Integer, ColumnField> indexedFields = new LinkedHashMap<>();
        int currentIndex = 1;

        if (BaseEntity.class.isAssignableFrom(clazz)) {
            indexedFields.put(currentIndex++, new ColumnField("ID", 6, 1));
        }

        for (Field field : clazz.getDeclaredFields()) {
            String titleCaseName = Strings.camelCaseToTitleCase(field.getName());
            if (shouldExcludeField(field, excludeSet, titleCaseName)) continue;
            int length = field.isAnnotationPresent(OutputLength.class) ? field.getAnnotation(OutputLength.class).value() : DEFAULT_LENGTH;
            int columnIndex = field.isAnnotationPresent(ColumnIndex.class) ? field.getAnnotation(ColumnIndex.class).value() : currentIndex;
            shiftColumnsIfNeeded(indexedFields, columnIndex);
            indexedFields.put(columnIndex, new ColumnField(titleCaseName, length, columnIndex));
            currentIndex++;
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Computed.class)) continue;
            String columnName = method.getAnnotation(Computed.class).value();
            if (shouldExcludeMethod(method, excludeSet, columnName)) continue;
            int length = method.isAnnotationPresent(OutputLength.class) ? method.getAnnotation(OutputLength.class).value() : DEFAULT_LENGTH;
            int columnIndex = method.isAnnotationPresent(ColumnIndex.class) ? method.getAnnotation(ColumnIndex.class).value() : currentIndex;
            shiftColumnsIfNeeded(indexedFields, columnIndex);
            indexedFields.put(columnIndex, new ColumnField(columnName, length, columnIndex));
            currentIndex++;
        }

        indexedFields.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> fieldMap.put(entry.getValue().getName(), entry.getValue().getLength()));

        return fieldMap;
    }

    public static Map<String, Object> getFieldValues(Object obj, String... excludeKeys) {
        Map<String, Object> valuesMap = new LinkedHashMap<>();
        Set<String> excludeSet = Arrays.stream(excludeKeys)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
        Class<?> clazz = obj.getClass();

        if (obj instanceof BaseEntity) {
            valuesMap.put("ID", ((BaseEntity) obj).getId());
        }

        for (Field field : clazz.getDeclaredFields()) {
            String titleCaseName = Strings.camelCaseToTitleCase(field.getName());
            if (shouldExcludeField(field, excludeSet, titleCaseName)) continue;
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                valuesMap.put(titleCaseName, value);
            } catch (IllegalAccessException e) {
                Log.error("Error accessing field " + field.getName() + ": " + e.getMessage());
            }
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Computed.class)) continue;
            String columnName = method.getAnnotation(Computed.class).value();
            if (shouldExcludeMethod(method, excludeSet, columnName)) continue;
            method.setAccessible(true);
            try {
                Object value = method.invoke(obj);
                valuesMap.put(columnName, value);
            } catch (Exception e) {
                Log.error("Error invoking computed method " + method.getName() + ": " + e.getMessage());
            }
        }
        return valuesMap;
    }

    private static boolean shouldExcludeField(Field field, Set<String> excludeSet, String titleCaseName) {
        if (field.isAnnotationPresent(ExcludeKey.class)) {
            for (String key : field.getAnnotation(ExcludeKey.class).value()) {
                if (excludeSet.contains(key.toLowerCase())) return true;
            }
        }
        return excludeSet.contains(titleCaseName.toLowerCase());
    }

    private static boolean shouldExcludeMethod(Method method, Set<String> excludeSet, String columnName) {
        if (method.isAnnotationPresent(ExcludeKey.class)) {
            for (String key : method.getAnnotation(ExcludeKey.class).value()) {
                if (excludeSet.contains(key.toLowerCase())) return true;
            }
        }
        return excludeSet.contains(columnName.toLowerCase());
    }

    private static void shiftColumnsIfNeeded(Map<Integer, ColumnField> fields, int insertIndex) {
        Map<Integer, ColumnField> shiftedFields = new LinkedHashMap<>();
        for (Map.Entry<Integer, ColumnField> entry : fields.entrySet()) {
            int currentIndex = entry.getKey();
            ColumnField columnField = entry.getValue();
            if (currentIndex >= insertIndex) {
                shiftedFields.put(currentIndex + 1, new ColumnField(columnField.getName(), columnField.getLength(), currentIndex + 1));
            } else {
                shiftedFields.put(currentIndex, columnField);
            }
        }
        fields.clear();
        fields.putAll(shiftedFields);
    }

    private static class ColumnField {
        private final String name;
        private final int length;
        private final int index;

        public ColumnField(String name, int length, int index) {
            this.name = name;
            this.length = length;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public int getLength() {
            return length;
        }

        public int getIndex() {
            return index;
        }
    }
}