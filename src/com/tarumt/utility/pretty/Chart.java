package com.tarumt.utility.pretty;

import com.tarumt.utility.common.Strings;

import java.util.List;

public class Chart {

    /**
     * Prints a CLI bar chart with a title, customizable maximum bar length,
     * custom bar character, and an option to display numeric values.
     *
     * @param categories   List of category labels.
     * @param values       List of integer values corresponding to each category.
     * @param title        The title of the chart.
     * @param maxBarLength Maximum length of the bar in characters.
     * @param barChar      The character to be used for the bars.
     * @param showValues   Whether to display the numeric value at the end of the bar.
     */
    public static void barChart(List<String> categories, List<Integer> values,
                                String title, int maxBarLength, char barChar, boolean showValues) {
        if (categories.size() != values.size()) {
            throw new IllegalArgumentException("Categories and values must have the same length.");
        }

        // Determine maximum value to normalize the bars.
        int maxValue = values.stream().max(Integer::compareTo).orElse(1);
        // Determine optimal category label width (at least 15 characters)
        int categoryWidth = Math.max(15, categories.stream().mapToInt(String::length).max().orElse(15));
        // Calculate the total width for the chart borders.
        int chartWidth = categoryWidth + maxBarLength + 10;

        // Print the title with a border.
        Strings.line(chartWidth);
        System.out.printf("%" + ((chartWidth + title.length() + 2) / 2) + "s%n", "📊 " + title);
        Strings.line(chartWidth);

        // Print each category's bar.
        for (int i = 0; i < categories.size(); i++) {
            String category = categories.get(i);
            int value = values.get(i);
            int barLength = (int) ((double) value / maxValue * maxBarLength);

            // Build the bar string using the custom bar character.
            StringBuilder bar = new StringBuilder();
            for (int j = 0; j < barLength; j++) {
                bar.append(barChar);
            }

            if (showValues) {
                System.out.printf("%-" + categoryWidth + "s | %-" + maxBarLength + "s | %d%n",
                        category, bar, value);
            } else {
                System.out.printf("%-" + categoryWidth + "s | %s%n", category, bar);
            }
        }
        Strings.line(chartWidth);
    }
}
