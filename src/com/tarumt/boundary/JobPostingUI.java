package com.tarumt.boundary;

import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.control.JobPostingController;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.Company;
import com.tarumt.entity.JobPosting;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Menu;
import com.tarumt.utility.pretty.TabularPrint;
import com.tarumt.utility.search.FuzzySearch;
import com.tarumt.utility.validation.*;

import java.lang.reflect.Field;

import com.tarumt.adt.list.ListInterface;
import com.tarumt.adt.map.MapInterface;
import com.tarumt.adt.map.SimpleHashMap;
import com.tarumt.utility.common.Report;
import com.tarumt.utility.pretty.Chart;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JobPostingUI {

    private final Input input;

    public JobPostingUI(Input input) {
        this.input = input;
    }

    public void menu() {
        JobPostingController jobPostingController = JobPostingController.getInstance();

        new Menu()
                .header("==> Manage Job Posting <==")
                .choice(
                        new Menu.Choice("🆕 Create Job Posting", jobPostingController::create),
                        new Menu.Choice("📋 Display Job Posting", jobPostingController::read),
                        new Menu.Choice("🔍 Search Job Posting", jobPostingController::search),
                        new Menu.Choice("📂 Filter Job Posting", jobPostingController::filter),
                        new Menu.Choice("🔃 Update Job Posting", jobPostingController::update),
                        new Menu.Choice("❌ Delete Job Posting", jobPostingController::delete),
                        new Menu.Choice("📈 Generate Report", jobPostingController::displayMonthRangeSummaryReport)
                )
                .exit("<Return to Main Menu>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void printNextIDMsg() {
        System.out.println("| Job Posting ID => " + JobPosting.getNextId());
    }

    public void printCreateJobPostingMsg() {
        System.out.println("<== Create Job Posting [ X to Exit ] ==>");
    }

    public void printSuccessCreateJobPostingMsg() {
        System.out.println();
        Log.info("New job posting created");
    }

    public boolean continueToCreateJobPosting() {
        StringCondition condition = ConditionFactory
                .string()
                .regex("^[xy]$|^[XY]$", "Invalid input, please input X or Y");
        String xOrY = input.withoutExitKey().getString("Continue to add job posting? [ Y / X ] => ", condition);
        if (xOrY.equalsIgnoreCase("y")) {
            System.out.println();
            return true;
        }
        return false;
    }

    public void printAllJobs(ListInterface<JobPosting> jobPostings) {
        if (jobPostings == null || jobPostings.isEmpty()) {
            Log.info("No job postings to display");
            input.clickAnythingToContinue();
            return;
        }
        Log.info("Displaying " + jobPostings.size() + " job postings");
        String[] excludeKeys = Context.isEmployer() ? new String[]{"default", "employer"} : new String[]{"default"};
        TabularPrint.printTabular(jobPostings, true, excludeKeys);
        input.clickAnythingToContinue();
    }

    public void printSearchJobPostingMsg(ListInterface<JobPosting> jobPostings) {
        if (jobPostings == null || jobPostings.isEmpty()) {
            Log.info("No job postings to search");
            input.clickAnythingToContinue();
            return;
        }
        System.out.println("<== Search Job Posting [ X to Exit ] ==>");
    }

    public String getSearchJobPostingQuery() {
        StringCondition condition = ConditionFactory.string().min(1).max(50);
        return input.getString("| Search Keyword => ", condition);
    }

    public void printSearchResult(FuzzySearch.Result<JobPosting> result) {
        ListInterface<JobPosting> matchedJobs = result.getSubList();
        ListInterface<String> matches = result.getMatches();
        System.out.println();
        if (matchedJobs.isEmpty()) {
            Log.info("No job postings matched the search criteria");
        } else {
            System.out.println(matches.size() + " Relevant Results => " + matches + "\n");
            Log.info("Displaying " + matchedJobs.size() + " job postings");
            String[] excludeKeys = Context.isEmployer() ? new String[]{"default", "employer"} : new String[]{"default"};
            TabularPrint.printTabular(matchedJobs, true, matches, excludeKeys);
            input.clickAnythingToContinue();
        }
        System.out.println();
    }

    public void displayFilterMenu(ListInterface<JobPosting> jobs) {
        JobPostingController controller = JobPostingController.getInstance();
        System.out.println();
        new Menu()
                .header("<== Filter Job Postings ==>")
                .choice(
                        Context.isEmployer() ? null : new Menu.Choice("Filter by Company", controller::filterByCompany),
                        new Menu.Choice("Filter by Job Type", controller::filterByJobType),
                        new Menu.Choice("Filter by Salary Range", controller::filterBySalaryRange),
                        new Menu.Choice("Filter by Status", controller::filterByStatus),
                        new Menu.Choice("Filter by Date Range", controller::filterByDateRange),
                        new Menu.Choice("Sort by Title", controller::sortJobsByTitle),
                        new Menu.Choice("Sort by Salary", controller::sortJobsBySalary),
                        new Menu.Choice("Sort by Date", controller::sortJobsByDate)
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void displayFilteredJobs(ListInterface<JobPosting> filteredJobs) {
        if (filteredJobs.isEmpty()) {
            Log.info("No job postings match the criteria");
            input.clickAnythingToContinue();
            return;
        }
        Log.info("Found " + filteredJobs.size() + " job postings matching the criteria");
        String[] excludeKeys = Context.isEmployer() ? new String[]{"employer", "default"} : new String[]{"default"};
        TabularPrint.printTabular(filteredJobs, true, excludeKeys);
        input.clickAnythingToContinue();
    }

    public String getJobPostingTitle() {
        Field field = ValidationFieldReflection.getField(JobPosting.class, "title");
        StringCondition condition = (StringCondition) ConditionFactory.fromField(field);
        return input.getString("| Job Title => ", condition);
    }

    public Company getJobPostingCompany() {
        return input.getObjectFromList("|\n| Select Company => ", Initializer.getCompanies());
    }

    public String getSalaryRange() {
        StringCondition condition = (StringCondition) ConditionFactory.string()
                .regex("^(\\d+)-(\\d+)$", "Invalid format, please input in correct format <min>-<max> (e.g., 1500-3000), and no negative values")
                .custom(value -> {
                    String[] parts = ((String) value).split("-");
                    if (parts.length != 2) return false;
                    int min = Integer.parseInt(parts[0]);
                    int max = Integer.parseInt(parts[1]);
                    return min <= max;
                }, "Minimum salary must be less than or equals maximum salary");

        return input.getString("| Job Salary Range (e.g., 1500-3000) => ", condition);
    }

    public String getJobPostingDescription() {
        Field field = ValidationFieldReflection.getField(JobPosting.class, "description");
        StringCondition condition = (StringCondition) ConditionFactory.fromField(field);
        return input.getString("| Job Description => ", condition);
    }

    public JobPosting.Type getJobPostingType() {
        return input.getEnum("|\n| Job Type => ", JobPosting.Type.class, 38);
    }

    public JobPosting.Status getJobPostingStatus() {
        return input.getEnum("|\n| Job Status => ", JobPosting.Status.class, 20);
    }

    public void printUpdateFieldMessage(String fieldName) {
        System.out.println("<== Updating Job Posting '" + fieldName + "' [ X to Exit ] ==>");
    }

    public void printUpdateSuccessMessage(JobPosting jobPosting, String fieldName) {
        System.out.println();
        Log.info("Job Posting '" + fieldName + "' updated successfully");
        this.printOriginalJobValue(jobPosting);
        input.clickAnythingToContinue();
    }

    public void printUpdateJobMsg(ListInterface<JobPosting> jobPosting) {
        if (jobPosting == null || jobPosting.isEmpty()) {
            Log.info("No job postings to update");
            input.clickAnythingToContinue();
            return;
        }
        System.out.println("<== Update Job Posting [ X to Exit ] ==>");
    }

    public void updateJobMode(String id) {
        JobPostingController controller = JobPostingController.getInstance();
        System.out.println();
        new Menu()
                .header("Select Update Mode ==>")
                .choice(
                        new Menu.Choice("Update Job Title", () -> controller.updateJobTitle(id)),
                        new Menu.Choice("Update Job Company", () -> controller.updateJobCompany(id)),
                        new Menu.Choice("Update Salary Range", () -> controller.updateSalaryRange(id)),
                        new Menu.Choice("Update Description", () -> controller.updateDescription(id)),
                        new Menu.Choice("Update Job Type", () -> controller.updateJobType(id)),
                        new Menu.Choice("Update Status", () -> controller.updateStatus(id)),
                        new Menu.Choice("Update All Fields", () -> controller.updateAllField(id))
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void printOriginalJobValue(JobPosting jobPosting) {
        System.out.println("\n" + jobPosting);
    }

    public void deleteMenu(ListInterface<JobPosting> jobPostings) {
        JobPostingController controller = JobPostingController.getInstance();
        if (jobPostings == null || jobPostings.isEmpty()) {
            Log.info("No job posting to delete");
            input.clickAnythingToContinue();
            return;
        }
        new Menu()
                .header("<== Delete Job Posting ==>")
                .choice(
                        new Menu.Choice("Delete By Index", controller::deleteByIndex),
                        new Menu.Choice("Delete By Index Range", controller::deleteByRange),
                        new Menu.Choice("Delete By ID", controller::deleteById),
                        new Menu.Choice("Delete All", controller::deleteAll)
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .terminate(jobPostings::isEmpty)
                .run();
    }

    public void printDeleteByIndexMsg() {
        System.out.println("<== Delete By Index [ X to Exit ] ==>");
    }

    public int getJobPostingIndex(int size) {
        IntegerCondition condition = ConditionFactory.integer().min(1).max(size);
        return input.getInt("| Select Job Posting Index => ", condition);
    }

    public void printDeleteByIdMsg() {
        System.out.println("<== Delete By ID [ X to Exit ] ==>");
    }

    public void printSuccessDeleteMsg(String id) {
        System.out.println();
        Log.info("Deleted job posting ID => " + id);
    }

    public int getDeleteStartIndex(int size) {
        IntegerCondition condition = ConditionFactory.integer().min(1).max(size);
        return input.getInt("| Starting index => ", condition);
    }

    public int getDeleteEndIndex(int startIndex, int size) {
        IntegerCondition condition = ConditionFactory.integer().min(startIndex).max(size);
        return input.getInt("| Ending index => ", condition);
    }

    public void printDeleteByRangeMsg() {
        System.out.println("<== Delete By Index Range [ X to Exit ] ==>");
    }

    public void printSuccessDeleteByRangeMsg(int startIndex, int endIndex) {
        System.out.println();
        Log.info("Deleted job postings from index " + startIndex + " to " + endIndex);
    }

    public String getJobPostingId(ListInterface<String> ids) {
        StringCondition condition = ConditionFactory.string().enumeration(ids, "ID doesn't exists, try again");
        return input.getString("| Select Job Posting ID => ", condition);
    }

    public boolean confirmDelete() {
        return input.confirm("Confirm to delete job posting? [ Y / X ] => ");
    }

    public void printSuccessDeleteAllMsg() {
        System.out.println();
        Log.info("Deleted all job postings");
        input.clickAnythingToContinue();
    }

    public LocalDate[] getDateRange() {
        System.out.println("<== Filter by Date Range [ X to Exit ] ==>");

        LocalDate startDate = getDate("| Start Date (YYYY-MM-DD) => ");
        if (startDate == null) {
            return null;
        }

        LocalDate endDate = getDate("| End Date (YYYY-MM-DD) => ");
        if (endDate == null) {
            return null;
        }

        if (endDate.isBefore(startDate)) {
            System.out.println("Error: End date must be after start date");
            input.clickAnythingToContinue();
            return null;
        }

        return new LocalDate[]{startDate, endDate};
    }

    private LocalDate getDate(String prompt) {
        StringCondition condition = (StringCondition) ConditionFactory.string()
                .regex("^\\d{4}-\\d{2}-\\d{2}$", "The date format is not valid. Please use YYYY-MM-DD format.")
                .custom(value -> {
                    try {
                        LocalDate date = LocalDate.parse((String) value);

                        String[] parts = ((String) value).split("-");
                        int year = Integer.parseInt(parts[0]);
                        int month = Integer.parseInt(parts[1]);
                        int day = Integer.parseInt(parts[2]);

                        if (month < 1 || month > 12) {
                            return false;
                        }

                        int maxDays = date.getMonth().length(date.isLeapYear());
                        if (day < 1 || day > maxDays) {
                            return false;
                        }

                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }, "Invalid date. Please enter a valid date");

        String dateStr = input.getString(prompt, condition);
        if (dateStr == null || dateStr.equals(Input.STRING_EXIT_VALUE)) {
            return null;
        }

        try {
            return LocalDate.parse(dateStr);
        } catch (Exception e) {
            System.out.println("Error parsing date: " + e.getMessage());
            input.clickAnythingToContinue();
            return null;
        }
    }

    public void displayDateRangeError() {
        System.out.println("\nError: Start date must be before or equal to end date");
        input.clickAnythingToContinue();
    }

    public int[] getMonthRange() {
        System.out.println("\n=== Month Range Summary Report ===");
        System.out.println("Please specify the month range for the report:");

        int startMonth = getMonth("Enter start month (1-12): ");
        if (startMonth == Input.INT_EXIT_VALUE) {
            return null;
        }

        int startYear = getYear("Enter start year: ");
        if (startYear == Input.INT_EXIT_VALUE) {
            return null;
        }

        int endMonth = getMonth("Enter end month (1-12): ");
        if (endMonth == Input.INT_EXIT_VALUE) {
            return null;
        }

        int endYear = getYear("Enter end year: ");
        if (endYear == Input.INT_EXIT_VALUE) {
            return null;
        }

        return new int[]{startMonth, startYear, endMonth, endYear};
    }

    public int getMonth(String prompt) {
        IntegerCondition condition = ConditionFactory.integer().min(1).max(12);
        return input.getInt(prompt, condition);
    }

    public int getYear(String prompt) {
        IntegerCondition condition = ConditionFactory.integer().min(2000).max(2100);
        return input.getInt(prompt, condition);
    }

    public void displayNoDataMessage() {
        System.out.println("\nNo job application data found for the specified date range");
        input.clickAnythingToContinue();
    }

    public void displayMonthRangeSummaryReport(ListInterface<JobPostingController.MonthRangeSummaryData> reportData,
                                               int startMonth, int startYear, int endMonth, int endYear) {
        // Generate report header
        int width = 100;
        String module = "Internship Management System";
        String reportName;

        // Customize report name for employer
        if (Context.isEmployer()) {
            Company company = Context.getCompany();
            reportName = company.getName() + " - Month Range Summary Report (" + startYear + "-" + startMonth + " to " + endYear + "-" + endMonth + ")";
        } else {
            reportName = "Month Range Summary Report (" + startYear + "-" + startMonth + " to " + endYear + "-" + endMonth + ")";
        }

        String header = Report.buildReportHeader(width, module, reportName);

        // Print the header
        System.out.print(header);

        if (reportData.isEmpty()) {
            System.out.println("No data available for this date range.");

            // Print footer even when no data is available
            System.out.print(Report.buildReportFooter(width));
            input.clickAnythingToContinue();
            return;
        }

        // Convert data to MonthRangeSummaryReportRow objects for TabularPrint
        ListInterface<MonthRangeSummaryReportRow> tableRows = new DoublyLinkedList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < reportData.size(); i++) {
            JobPostingController.MonthRangeSummaryData data = reportData.get(i);
            tableRows.add(new MonthRangeSummaryReportRow(
                    data.getJobTitle(),
                    data.getCompanyName(),
                    data.getAppliedDate().format(dateFormatter),
                    data.getApplicantCount()
            ));
        }

        // Use TabularPrint to display a nicely formatted table
        TabularPrint.printTabular(tableRows, true);

        // Create a chart showing applications by company
        MapInterface<String, Integer> companyApplicationCounts = new SimpleHashMap<>();

        // Count applications by company
        for (int i = 0; i < reportData.size(); i++) {
            JobPostingController.MonthRangeSummaryData data = reportData.get(i);
            String companyName = data.getCompanyName();
            int count = data.getApplicantCount();

            if (companyApplicationCounts.containsKey(companyName)) {
                int currentCount = companyApplicationCounts.get(companyName);
                companyApplicationCounts.put(companyName, currentCount + count);
            } else {
                companyApplicationCounts.put(companyName, count);
            }
        }

        // Convert to lists for chart display
        ListInterface<String> companies = new DoublyLinkedList<>();
        ListInterface<Integer> counts = new DoublyLinkedList<>();

        // Get all keys from the map
        for (String company : companyApplicationCounts.keySet()) {
            companies.add(company);
        }

        // Get corresponding counts
        for (int i = 0; i < companies.size(); i++) {
            String company = companies.get(i);
            counts.add(companyApplicationCounts.get(company));
        }

        // Display bar chart
        if (!Context.isEmployer()) {
            System.out.println(Chart.barChart(
                    companies,
                    counts,
                    "Application Count by Company",
                    100, // maxBarLength
                    '█', // barChar
                    true // showValues
            ));
        }

        // Group by date
        MapInterface<LocalDate, Integer> dateApplicationCounts = new SimpleHashMap<>();

        // Count applications by date
        for (int i = 0; i < reportData.size(); i++) {
            JobPostingController.MonthRangeSummaryData data = reportData.get(i);
            LocalDate date = data.getAppliedDate();
            int count = data.getApplicantCount();

            if (dateApplicationCounts.containsKey(date)) {
                int currentCount = dateApplicationCounts.get(date);
                dateApplicationCounts.put(date, currentCount + count);
            } else {
                dateApplicationCounts.put(date, count);
            }
        }

        // Sort dates
        ListInterface<LocalDate> sortedDates = new DoublyLinkedList<>();
        for (LocalDate date : dateApplicationCounts.keySet()) {
            sortedDates.add(date);
        }

        // Sort the dates in ascending order
        sortedDates.sort((date1, date2) -> date1.compareTo(date2));

        // Convert to lists for chart display
        ListInterface<String> dates = new DoublyLinkedList<>();
        ListInterface<Integer> dateCounts = new DoublyLinkedList<>();

        for (int i = 0; i < sortedDates.size(); i++) {
            LocalDate date = sortedDates.get(i);
            dates.add(date.format(dateFormatter));
            dateCounts.add(dateApplicationCounts.get(date));
        }

        // Display bar chart
        System.out.println(Chart.barChart(
                dates,
                dateCounts,
                "Application Count by Date",
                100, // maxBarLength
                '█', // barChar
                true // showValues
        ));

        // Find job postings with most and least applications
        MapInterface<String, Integer> jobApplicationCounts = new SimpleHashMap<>();

        // Count applications by job title
        for (int i = 0; i < reportData.size(); i++) {
            JobPostingController.MonthRangeSummaryData data = reportData.get(i);
            String jobTitle = data.getJobTitle();
            int count = data.getApplicantCount();

            if (jobApplicationCounts.containsKey(jobTitle)) {
                int currentCount = jobApplicationCounts.get(jobTitle);
                jobApplicationCounts.put(jobTitle, currentCount + count);
            } else {
                jobApplicationCounts.put(jobTitle, count);
            }
        }

        // Find max and min counts
        int maxCount = Integer.MIN_VALUE;
        int minCount = Integer.MAX_VALUE;
        ListInterface<String> mostAppliedJobs = new DoublyLinkedList<>();
        ListInterface<String> leastAppliedJobs = new DoublyLinkedList<>();

        for (String job : jobApplicationCounts.keySet()) {
            int count = jobApplicationCounts.get(job);

            if (count > maxCount) {
                maxCount = count;
                mostAppliedJobs.clear();
                mostAppliedJobs.add(job);
            } else if (count == maxCount) {
                mostAppliedJobs.add(job);
            }

            if (count < minCount) {
                minCount = count;
                leastAppliedJobs.clear();
                leastAppliedJobs.add(job);
            } else if (count == minCount) {
                leastAppliedJobs.add(job);
            }
        }

        // Display most and least applied jobs
        System.out.println("\nJobs with the most applications (" + maxCount + "):");
        System.out.print("< ");
        for (int i = 0; i < mostAppliedJobs.size(); i++) {
            System.out.print(mostAppliedJobs.get(i));
            if (i < mostAppliedJobs.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" >");

        System.out.println("\nJobs with the least applications (" + minCount + "):");
        System.out.print("< ");
        for (int i = 0; i < leastAppliedJobs.size(); i++) {
            System.out.print(leastAppliedJobs.get(i));
            if (i < leastAppliedJobs.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" >");

        // Generate and print report footer
        System.out.print(Report.buildReportFooter(width));

        input.clickAnythingToContinue();
    }

    public static class MonthRangeSummaryReportRow {
        private String jobTitle;
        private String companyName;
        private String appliedDate;
        private int count;

        public MonthRangeSummaryReportRow(String jobTitle, String companyName, String appliedDate, int count) {
            this.jobTitle = jobTitle;
            this.companyName = companyName;
            this.appliedDate = appliedDate;
            this.count = count;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public String getCompanyName() {
            return companyName;
        }

        public String getAppliedDate() {
            return appliedDate;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return String.format("%-25s %-20s %-12s %d",
                    truncate(jobTitle, 25),
                    truncate(companyName, 20),
                    appliedDate,
                    count);
        }
    }

    private static String truncate(String value, int maxLength) {
        if (value == null) {
            return "";
        }

        if (value.length() <= maxLength) {
            return value;
        }

        return value.substring(0, maxLength - 3) + "...";
    }
}