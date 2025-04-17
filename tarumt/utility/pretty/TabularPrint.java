package com.tarumt.utility.pretty;

import com.tarumt.adt.list.Arrays;
import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.list.ListInterface;
import com.tarumt.adt.set.HashSet;
import com.tarumt.adt.set.SetInterface;
import com.tarumt.entity.BaseEntity;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Strings;
import com.tarumt.utility.pretty.annotation.ColumnIndex;
import com.tarumt.utility.pretty.annotation.Computed;
import com.tarumt.utility.pretty.annotation.ExcludeKey;
import com.tarumt.utility.pretty.annotation.OutputLength;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.lang.reflect.Modifier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TabularPrint {

    private static final String RESET = "\u001B[0m";
    public static final String PURPLE = "\u001B[35m";

    private static final Pattern ANSI_PATTERN = Pattern.compile("\u001B\\[[;\\d]*m");

    public static <T> void printTabular(ListInterface<T> list, boolean index, String... excludeKeys) {
        printTabular(list, index, null, excludeKeys);
    }

    public static <T> void printTabular(ListInterface<T> list, boolean index, SetInterface<String> highlight, String... excludeKeys) {
        if (list == null || list.isEmpty()) {
            return;
        }
        ListInterface<ColumnField> columnFields = TabularPrint.getColumnFields(list, excludeKeys);

        int totalWidth = 0;
        for (ColumnField cf : columnFields) {
            totalWidth += cf.getOutputLength() + 3;
        }
        totalWidth += (index ? 7 : 0) + 1;

        Strings.line(totalWidth);

        if (index) {
            System.out.printf("| %-4s ", "#");
        }
        for (ColumnField column : columnFields) {
            System.out.printf("| %-" + column.getOutputLength() + "s ", column.getName());
        }
        System.out.println("|");
        Strings.line(totalWidth);

        for (int i = 0; i < list.size(); i++) {
            if (index) {
                System.out.printf("| %-4d ", i + 1);
            }

            for (ColumnField column : columnFields) {
                int cellWidth = column.getOutputLength();
                Object fieldValue = column.getValues().get(i);
                String rawValue;

                if (fieldValue instanceof BaseEntity) {
                    rawValue = ((BaseEntity) fieldValue).toShortString();
                } else if (fieldValue != null) {
                    rawValue = fieldValue.toString();
                } else {
                    rawValue = "N/A";
                }

                String truncatedValue = Strings.truncate(rawValue, cellWidth);
                String highlightedValue = applyWordHighlighting(truncatedValue, highlight);
                printColorSafeCell(highlightedValue, cellWidth);
            }
            System.out.println("|");
        }
        Strings.line(totalWidth);
    }

    private static void printColorSafeCell(String text, int cellWidth) {
        int visibleLength = stripAnsiCodes(text).length();

        System.out.print("| ");
        System.out.print(text);

        int padding = cellWidth - visibleLength;
        Strings.space(padding);
        System.out.print(" ");
    }

    private static String stripAnsiCodes(String input) {
        if (input == null) return "";
        return ANSI_PATTERN.matcher(input).replaceAll("");
    }

    private static String applyWordHighlighting(String text, SetInterface<String> highlight) {
        if (highlight == null || highlight.isEmpty()) {
            return text;
        }

        ListInterface<String> sortedHighlights = new DoublyLinkedList<>();
        for (String h : highlight)
            sortedHighlights.add(h);
        sortedHighlights.sort((a, b) -> Integer.compare(b.length(), a.length()));

        ListInterface<String> quotedHighlights = sortedHighlights.map(Pattern::quote);
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (String quoted : quotedHighlights) {
            if (!first) {
                builder.append("|");
            }
            builder.append(quoted);
            first = false;
        }
        String patternString = builder.toString();
        Pattern pattern = Pattern.compile("(?i)(" + patternString + ")");
        Matcher matcher = pattern.matcher(text);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(sb, PURPLE + matcher.group(1) + RESET);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static <T> ListInterface<ColumnField> getColumnFields(ListInterface<T> list, String... excludeKeys) {
        SetInterface<String> excludeSet = new HashSet<>();
        Arrays.asList(excludeKeys).forEach((excludeKey) -> {
            excludeSet.add(excludeKey.toLowerCase());
        });

        final int DEFAULT_LENGTH = 20;

        if (list == null || list.isEmpty()) {
            return new DoublyLinkedList<>();
        }

        Class<?> clazz = list.get(0).getClass();
        ListInterface<ColumnField> tempFields = new DoublyLinkedList<>();
        int totalFields = 0;

        if (BaseEntity.class.isAssignableFrom(clazz)) {
            ListInterface<Object> idValues = new DoublyLinkedList<>();
            for (int i = 0; i < list.size(); i++) {
                idValues.add(((BaseEntity) list.get(i)).getId());
            }
            tempFields.add(new ColumnField("ID", 6, idValues));
            totalFields++;
        }

        for (Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            String fieldName = field.getName();
            if (shouldExcludeField(field, excludeSet, fieldName)) continue;

            int length = field.isAnnotationPresent(OutputLength.class) ?
                    field.getAnnotation(OutputLength.class).value() : DEFAULT_LENGTH;

            ListInterface<Object> values = new DoublyLinkedList<>();
            for (int i = 0; i < list.size(); i++) {
                try {
                    field.setAccessible(true);
                    values.add(field.get(list.get(i)));
                } catch (IllegalAccessException e) {
                    Log.error("Error accessing field " + fieldName + ": " + e.getMessage());
                    values.add(null);
                }
            }
            tempFields.add(new ColumnField(Strings.camelCaseToTitleCase(fieldName), length, values));
            totalFields++;
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Computed.class)) continue;
            String columnName = method.getAnnotation(Computed.class).value();
            if (shouldExcludeMethod(method, excludeSet, columnName)) continue;

            int length = method.isAnnotationPresent(OutputLength.class) ?
                    method.getAnnotation(OutputLength.class).value() : DEFAULT_LENGTH;

            ListInterface<Object> values = new DoublyLinkedList<>();
            for (int i = 0; i < list.size(); i++) {
                try {
                    method.setAccessible(true);
                    values.add(method.invoke(list.get(i)));
                } catch (Exception e) {
                    Log.error("Error invoking computed method " + method.getName() + ": " + e.getMessage());
                    values.add(null);
                }
            }
            tempFields.add(new ColumnField(columnName, length, values));
            totalFields++;
        }

        return reorderFields(clazz, tempFields, totalFields);
    }

    private static ListInterface<ColumnField> reorderFields(Class<?> clazz, ListInterface<ColumnField> tempFields, int totalFields) {
        ListInterface<ColumnField> orderedFields = new DoublyLinkedList<>(tempFields);
        ListInterface<ColumnField> finalFields = new DoublyLinkedList<>();

        for (int i = 0; i < tempFields.size(); i++) {
            finalFields.add(null);
        }

        for (Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            if (field.isAnnotationPresent(ColumnIndex.class)) {
                int index = field.getAnnotation(ColumnIndex.class).value();
                if (index >= 1 && index <= totalFields) {
                    String fieldName = Strings.camelCaseToTitleCase(field.getName());
                    for (ColumnField cf : tempFields) {
                        if (cf.getName().equals(fieldName)) {
                            finalFields.set(index - 1, cf);
                            orderedFields.remove(cf);
                            break;
                        }
                    }
                }
            }
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Computed.class) && method.isAnnotationPresent(ColumnIndex.class)) {
                int index = method.getAnnotation(ColumnIndex.class).value();
                if (index >= 1 && index <= totalFields) {
                    String columnName = method.getAnnotation(Computed.class).value();
                    for (ColumnField cf : tempFields) {
                        if (cf.getName().equals(columnName)) {
                            finalFields.set(index - 1, cf);
                            orderedFields.remove(cf);
                            break;
                        }
                    }
                }
            }
        }

        int remainingIndex = 0;
        for (ColumnField cf : orderedFields) {
            while (remainingIndex < finalFields.size() && finalFields.get(remainingIndex) != null) {
                remainingIndex++;
            }
            if (remainingIndex < finalFields.size()) {
                finalFields.set(remainingIndex, cf);
            }
        }

        return finalFields;
    }

    private static boolean shouldExcludeField(Field field, SetInterface<String> excludeSet, String titleCaseName) {
        if (field.isAnnotationPresent(ExcludeKey.class)) {
            for (String key : field.getAnnotation(ExcludeKey.class).value()) {
                if (excludeSet.contains(key.toLowerCase())) return true;
            }
        }
        return excludeSet.contains(titleCaseName.toLowerCase());
    }

    private static boolean shouldExcludeMethod(Method method, SetInterface<String> excludeSet, String columnName) {
        if (method.isAnnotationPresent(ExcludeKey.class)) {
            for (String key : method.getAnnotation(ExcludeKey.class).value()) {
                if (excludeSet.contains(key.toLowerCase())) return true;
            }
        }
        return excludeSet.contains(columnName.toLowerCase());
    }

    private static class ColumnField {
        private final String name;
        private final int outputLength;
        private final ListInterface<Object> values;

        public ColumnField(String name, int outputLength, ListInterface<Object> values) {
            this.name = name;
            this.outputLength = outputLength;
            this.values = values;
        }

        public String getName() {
            return name;
        }

        public int getOutputLength() {
            return outputLength;
        }

        public ListInterface<Object> getValues() {
            return values;
        }
    }
}