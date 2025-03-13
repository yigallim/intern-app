package com.tarumt.utility.pretty;

import com.tarumt.entity.BaseEntity;
import com.tarumt.utility.common.Strings;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TabularPrint {

    private static final String RESET = "\u001B[0m";
    public static final String PURPLE = "\u001B[35m";

    private static final Pattern ANSI_PATTERN = Pattern.compile("\u001B\\[[;\\d]*m");

    public static <T> void printTabular(List<T> list, boolean index, String... excludeKeys) {
        printTabular(list, index, null, excludeKeys);
    }

    public static <T> void printTabular(List<T> list, boolean index, Set<String> highlight, String... excludeKeys) {
        if (list == null || list.isEmpty()) {
            return;
        }

        Class<?> clazz = list.get(0).getClass();
        Map<String, Integer> fields = TabularFieldReflection.getAnnotatedFields(clazz, excludeKeys);

        int totalWidth = fields.values().stream().mapToInt(v -> v + 3).sum()
                + (index ? 7 : 0)
                + 1;

        Strings.line(totalWidth);

        if (index) {
            System.out.printf("| %-4s ", "#");
        }
        for (Map.Entry<String, Integer> entry : fields.entrySet()) {
            System.out.printf("| %-" + entry.getValue() + "s ", entry.getKey());
        }
        System.out.println("|");

        Strings.line(totalWidth);

        for (int i = 0; i < list.size(); i++) {
            T item = list.get(i);
            Map<String, Object> values = TabularFieldReflection.getFieldValues(item, excludeKeys);

            if (index) {
                System.out.printf("| %-4d ", i + 1);
            }

            for (Map.Entry<String, Integer> entry : fields.entrySet()) {
                int cellWidth = entry.getValue();
                Object fieldValue = values.get(entry.getKey());
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

    private static String applyWordHighlighting(String text, Set<String> highlight) {
        if (highlight == null || highlight.isEmpty()) {
            return text;
        }

        for (String word : highlight) {
            Pattern pattern = Pattern.compile("(?i)" + Pattern.quote(word));
            Matcher matcher = pattern.matcher(text);
            StringBuffer highlightedText = new StringBuffer();

            while (matcher.find()) {
                matcher.appendReplacement(highlightedText,
                        PURPLE + matcher.group() + RESET);
            }
            matcher.appendTail(highlightedText);
            text = highlightedText.toString();
        }
        return text;
    }
}