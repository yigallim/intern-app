package com.tarumt.pretty;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TabularPrint {

    private static final String RESET = "\u001B[0m";
    public static final String PURPLE = "\u001B[35m";

    private static final Pattern ANSI_PATTERN = Pattern.compile("\u001B\\[[;\\d]*m");

    public static <T> void printTabular(List<T> list, boolean index) {
        printTabular(list, index, null);
    }

    public static <T> void printTabular(List<T> list, boolean index, List<String> highlight) {
        if (list == null || list.isEmpty()) {
            System.out.println("No data to display.");
            return;
        }

        Class<?> clazz = list.get(0).getClass();
        Map<String, Integer> fields = TabularFieldReflection.getAnnotatedFields(clazz);

        int totalWidth = fields.values().stream().mapToInt(v -> v + 3).sum()
                + (index ? 7 : 0)
                + 1;

        printHorizontalLine(totalWidth);

        if (index) {
            System.out.printf("| %-4s ", "#");
        }
        for (Map.Entry<String, Integer> entry : fields.entrySet()) {
            System.out.printf("| %-" + entry.getValue() + "s ", entry.getKey());
        }
        System.out.println("|");

        printHorizontalLine(totalWidth);

        for (int i = 0; i < list.size(); i++) {
            T item = list.get(i);
            Map<String, String> values = TabularFieldReflection.getFieldValues(item);

            if (index) {
                System.out.printf("| %-4d ", i + 1);
            }

            for (Map.Entry<String, Integer> entry : fields.entrySet()) {
                int cellWidth = entry.getValue();
                String rawValue = values.getOrDefault(entry.getKey(), "N/A");

                String truncatedValue = truncate(rawValue, cellWidth);

                String highlightedValue = applyWordHighlighting(truncatedValue, highlight);

                printColorSafeCell(highlightedValue, cellWidth);
            }

            System.out.println("|");
        }
        printHorizontalLine(totalWidth);
    }

    private static void printColorSafeCell(String text, int cellWidth) {

        int visibleLength = stripAnsiCodes(text).length();

        System.out.print("| ");
        System.out.print(text);

        int padding = cellWidth - visibleLength;
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }

        System.out.print(" ");
    }

    private static String stripAnsiCodes(String input) {
        if (input == null) return "";
        return ANSI_PATTERN.matcher(input).replaceAll("");
    }

    private static void printHorizontalLine(int width) {
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static String truncate(String text, int length) {
        if (text.length() > length) {
            return text.substring(0, length - 1) + "…";
        }
        return text;
    }

    private static String applyWordHighlighting(String text, List<String> highlight) {
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