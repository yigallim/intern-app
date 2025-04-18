/**
 * @author Lim Yuet Yang
 */
package com.tarumt.utility.pretty;

import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.utility.common.Strings;
import com.tarumt.adt.list.ListInterface;

public class Chart {

    /**
     * Generates a CLI bar chart as a string with a title, customizable total chart width,
     * custom bar character, and an option to display numeric values at the end of the bar.
     *
     * @param categories    List of category labels.
     * @param values        List of integer values corresponding to each category.
     * @param title         The title of the chart.
     * @param totalChartWidth Total width of the chart in characters.
     * @param barChar       The character to be used for the bars.
     * @param showValues    Whether to display the numeric value at the end of the bar.
     * @return A string representation of the bar chart.
     */
    public static String barChart(ListInterface<String> categories, ListInterface<Integer> values,
                                  String title, int totalChartWidth, char barChar, boolean showValues) {
        if (categories.size() != values.size()) {
            throw new IllegalArgumentException("Categories and values must have the same length.");
        }

        StringBuilder output = new StringBuilder();

        // Determine maximum value to normalize the bars.
        int maxValue = values.max(Integer::compareTo).orElse(1);
        // Determine optimal category label width (at least 15 characters)
        ListInterface<Integer> lengths = new DoublyLinkedList<>();
        for (String s : categories) {
            // Remove any trailing "(number)" from category for length calculation
            String cleanedCategory = s.replaceAll("\\s*\\(\\d+\\)$", "");
            lengths.add(cleanedCategory.length());
        }
        int categoryWidth = Math.max(15, lengths.max(Integer::compareTo).orElse(15));
        // Calculate maximum bar length based on total chart width
        int maxBarLength = totalChartWidth - categoryWidth - 10;
        if (maxBarLength < 10) {
            maxBarLength = 10;
        }

        // Append the title with a border.
        output.append(Strings.repeat("-", totalChartWidth)).append("\n");
        output.append(String.format("%" + ((totalChartWidth + title.length() + 2) / 2) + "s%n", "📊 " + title));
        output.append(Strings.repeat("-", totalChartWidth)).append("\n");

        // Append each category's bar.
        for (int i = 0; i < categories.size(); i++) {
            String category = categories.get(i);
            // Remove any trailing "(number)" from category for display
            String cleanedCategory = category.replaceAll("\\s*\\(\\d+\\)$", "");
            int value = values.get(i);
            int barLength = (int) ((double) value / maxValue * maxBarLength);

            // Build the bar string using the custom bar character.
            StringBuilder bar = new StringBuilder();
            for (int j = 0; j < barLength; j++) {
                bar.append(barChar);
            }

            if (showValues) {
                output.append(String.format("%-" + categoryWidth + "s | %-" + maxBarLength + "s | %d%n",
                        cleanedCategory, bar, value));
            } else {
                output.append(String.format("%-" + categoryWidth + "s | %s%n", cleanedCategory, bar));
            }
        }
        output.append(Strings.repeat("-", totalChartWidth)).append("\n");

        return output.toString();
    }
}