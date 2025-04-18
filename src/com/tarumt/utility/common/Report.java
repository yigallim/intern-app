/**
 * @author Lim Yuet Yang
 */
package com.tarumt.utility.common;

import java.time.LocalDateTime;

public class Report {
    public static String buildReportHeader(int width, String module, String reportName) {
        LocalDateTime dateTime = Context.getDateTime();
        String dayOfWeek = Strings.constantCaseToTitleCase(dateTime.getDayOfWeek().toString());

        StringBuilder header = new StringBuilder();
        header.append(Strings.repeat("=", width)).append("\n");
        header.append("\n");
        header.append(Strings.center("TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY", width)).append("\n");
        header.append(Strings.center(module.toUpperCase(), width)).append("\n");
        header.append(Strings.center(Strings.repeat("-", 50), width)).append("\n");
        header.append(Strings.center(reportName.toUpperCase(), width)).append("\n");
        header.append(Strings.repeat("-", width)).append("\n");
        header.append("Generated at: ").append(dayOfWeek).append(" ").append(Strings.formatDateTime(dateTime)).append("\n");
        header.append(Strings.repeat("-", width));
        return header.toString();
    }

    public static String buildReportFooter(int width) {
        StringBuilder footer = new StringBuilder();
        footer.append(Strings.repeat("-", width)).append("\n");
        footer.append(Strings.center("END OF REPORT", width)).append("\n");
        footer.append(Strings.repeat("=", width)).append("\n");

        return footer.toString();
    }
}
