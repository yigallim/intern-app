package com.tarumt.boundary;

import com.tarumt.control.JobPostingService;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.BaseEntity;
import com.tarumt.entity.Company;
import com.tarumt.entity.JobPosting;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Menu;
import com.tarumt.utility.pretty.TabularPrint;
import com.tarumt.utility.pretty.Chart;
import com.tarumt.utility.search.FuzzySearch;
import com.tarumt.utility.validation.*;

import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.Set;
import java.time.LocalDate;
import java.util.*;

public class JobPostingUI {

    private final Input input;
    private List<JobPosting> jobPostings; // Add this field

    public JobPostingUI(Input input) {
        this.input = input;
        this.jobPostings = Initializer.getJobPostings(); // Initialize the field
    }

    public void menu(JobPostingService service) {
        // Set console encoding to UTF-8 (this helps with emoji display)
        System.setProperty("file.encoding", "UTF-8");

        new Menu()
                .banner("Job Posting")
                .header("==> Manage Job Posting <==")
                .choice(
                        new Menu.Choice("🆕 Create Job Posting", service::create),
                        new Menu.Choice("📋 Display Job Posting", service::read),
                        new Menu.Choice("🔍 Search Job Posting", service::search),
                        new Menu.Choice("📂 Filter Job Posting", service::filter),
                        new Menu.Choice("🔃 Update Job Posting", service::update),
                        new Menu.Choice("❌ Delete Job Posting", service::delete),
                        new Menu.Choice("📈 Generate Report", service::report),
                        new Menu.Choice("📊 Month Range Summary Report", service::displayMonthRangeSummaryReport)
                )
                .exit("<Return to Main Menu>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void printNextIDMsg() {
        System.out.println("| Job Posting ID => " + BaseEntity.getNextId(JobPosting.class));
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

    public void displayAllJobs(List<JobPosting> jobPostings) {
        if (jobPostings == null || jobPostings.isEmpty()) {
            Log.info("No job postings to display");
            return;
        }
        Log.info("Displaying " + jobPostings.size() + " job postings");
        TabularPrint.printTabular(jobPostings, true, "default");
        input.clickAnythingToContinue();
    }

    public void printSearchJobPostingMsg(List<JobPosting> jobPostings) {
        if (jobPostings == null || jobPostings.isEmpty()) {
            Log.info("No job postings to search");
            return;
        }
        System.out.println("<== Search Job Posting [ X to Exit ] ==>");
    }

    public String getSearchJobPostingQuery() {
        StringCondition condition = ConditionFactory.string().min(1).max(50);
        return input.getString("| Search Keyword => ", condition);
    }

    public void printSearchResult(FuzzySearch.Result<JobPosting> result) {
        List<JobPosting> matchedJobs = result.getSubList();
        Set<String> matches = result.getMatches();
        System.out.println();
        if (matchedJobs.isEmpty()) {
            Log.info("No job postings matched the search criteria");
        } else {
            System.out.println(matches.size() + " Relevant Results => " + matches + "\n");
            Log.info("Displaying " + matchedJobs.size() + " job postings");
            TabularPrint.printTabular(matchedJobs, true, matches, "default");
            input.clickAnythingToContinue();
        }
        System.out.println();
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
                    if (parts.length != 2) {
                        return false;
                    }
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

    public void printUpdateMessage(String fieldName) {
        System.out.println("<== Updating Job Posting '" + fieldName + "' [ X to Exit ] ==>");
    }

    public void printUpdateSuccessMessage(JobPosting jobPosting, String fieldName) {
        System.out.println();
        Log.info("Job Posting '" + fieldName + "' updated successfully");
        this.printOriginalJobValue(jobPosting);
        input.clickAnythingToContinue();
    }

    public void printUpdateJobMsg(List<JobPosting> jobPosting) {
        if (jobPosting == null || jobPosting.isEmpty()) {
            Log.info("No job postings to update");
            return;
        }
        System.out.println("<== Update Job Posting [ X to Exit ] ==>");
    }

    public void updateJobMode(JobPostingService service, String id) {
        System.out.println();
        new Menu()
                .header("Select Update Mode ==>")
                .choice(
                        new Menu.Choice("Update Job Title", () -> service.updateJobTitle(id)),
                        new Menu.Choice("Update Job Company", () -> service.updateJobCompany(id)),
                        new Menu.Choice("Update Salary Range", () -> service.updateSalaryRange(id)),
                        new Menu.Choice("Update Description", () -> service.updateDescription(id)),
                        new Menu.Choice("Update Job Type", () -> service.updateJobType(id)),
                        new Menu.Choice("Update Status", () -> service.updateStatus(id)),
                        new Menu.Choice("Update All Fields", () -> service.updateAllField(id))
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void printOriginalJobValue(JobPosting jobPosting) {
        System.out.println("\n" + jobPosting);
    }

    public void deleteMenu(JobPostingService service, List<JobPosting> jobPostings) {
        if (jobPostings == null || jobPostings.isEmpty()) {
            Log.info("No job posting to delete");
            return;
        }
        new Menu()
                .header("<== Delete Job Posting ==>")
                .choice(
                        new Menu.Choice("Delete By Index", service::deleteByIndex),
                        new Menu.Choice("Delete By Index Range", service::deleteByRange),
                        new Menu.Choice("Delete By ID", service::deleteById),
                        new Menu.Choice("Delete All", service::deleteAll)
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

    public String getJobPostingId(String msg, List<String> ids) {
        StringCondition condition = ConditionFactory.string().enumeration(ids, "ID doesn't exists, try again");
        return input.getString(msg, condition);
    }

    public boolean confirmDelete() {
        return input.confirm("Confirm to delete job posting? [ Y / X ] => ");
    }

    public void printSuccessDeleteAllMsg() {
        System.out.println();
        Log.info("Deleted all job postings");
    }

    @SuppressWarnings("unchecked")
    public void displayReports(List<Object> reportData) {

        // Display report options menu
        new Menu()
                .header("<== Job Posting Reports ==>")
                .choice(
                        new Menu.Choice("Job Type Distribution", () -> displayJobTypeReport(reportData)),
                        new Menu.Choice("Company Job Count", () -> displayCompanyJobReport(reportData)),
                        new Menu.Choice("Job Status Distribution", () -> displayStatusReport(reportData)),
                        new Menu.Choice("Posting Trends by Month", () -> displayTrendReport(reportData)),
                        new Menu.Choice("Application Count by Job", () -> displayApplicationReport(reportData)),
                        new Menu.Choice("Salary Distribution", () -> displaySalaryReport(reportData))
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    @SuppressWarnings("unchecked")
    private void displayJobTypeReport(List<Object> reportData) {
        List<JobPostingService.JobTypeStatistic> typeStats = (List<JobPostingService.JobTypeStatistic>) reportData.get(0);

        if (typeStats == null || typeStats.isEmpty()) {
            System.out.println("No job type statistics available.");
            return;
        }

        // Convert to arrays for chart display
        String[] labels = new String[typeStats.size()];
        int[] counts = new int[typeStats.size()];

        for (int i = 0; i < typeStats.size(); i++) {
            JobPostingService.JobTypeStatistic stat = typeStats.get(i);
            labels[i] = stat.getType().toString();
            counts[i] = stat.getCount();
        }

        // Display chart
        displayChart(labels, counts, "Job Type Distribution");
    }

    @SuppressWarnings("unchecked")
    private void displayCompanyJobReport(List<Object> reportData) {
        List<JobPostingService.CompanyJobStatistic> companyStats
                = (List<JobPostingService.CompanyJobStatistic>) reportData.get(1);

        if (companyStats == null || companyStats.isEmpty()) {
            System.out.println("No company job statistics available.");
            return;
        }

        // Sort by job count (descending)
        companyStats.sort((a, b) -> Integer.compare(b.getCount(), a.getCount()));

        // Convert to arrays for chart display
        String[] labels = new String[companyStats.size()];
        int[] counts = new int[companyStats.size()];

        for (int i = 0; i < companyStats.size(); i++) {
            JobPostingService.CompanyJobStatistic stat = companyStats.get(i);
            labels[i] = stat.getCompanyName();
            counts[i] = stat.getCount();
        }

        // Display chart
        displayChart(labels, counts, "Company Job Count Distribution");
    }

    @SuppressWarnings("unchecked")
    private void displayStatusReport(List<Object> reportData) {
        List<JobPostingService.StatusStatistic> statusStats
                = (List<JobPostingService.StatusStatistic>) reportData.get(2);

        if (statusStats == null || statusStats.isEmpty()) {
            System.out.println("No job status statistics available.");
            return;
        }

        // Convert to arrays for chart display
        String[] labels = new String[statusStats.size()];
        int[] counts = new int[statusStats.size()];

        for (int i = 0; i < statusStats.size(); i++) {
            JobPostingService.StatusStatistic stat = statusStats.get(i);
            labels[i] = stat.getStatus().toString();
            counts[i] = stat.getCount();
        }

        // Display chart
        displayChart(labels, counts, "Job Status Distribution");
    }

    @SuppressWarnings("unchecked")
    private void displayTrendReport(List<Object> reportData) {
        List<JobPostingService.TrendStatistic> trendStats
                = (List<JobPostingService.TrendStatistic>) reportData.get(3);

        if (trendStats == null || trendStats.isEmpty()) {
            System.out.println("No posting trend statistics available.");
            return;
        }

        // Convert to arrays for chart display
        String[] labels = new String[trendStats.size()];
        int[] counts = new int[trendStats.size()];

        for (int i = 0; i < trendStats.size(); i++) {
            JobPostingService.TrendStatistic stat = trendStats.get(i);
            labels[i] = stat.getMonthYear();
            counts[i] = stat.getCount();
        }

        // Display chart
        displayChart(labels, counts, "Job Posting Trends by Month");
    }

    @SuppressWarnings("unchecked")
    private void displayApplicationReport(List<Object> reportData) {
        List<JobPostingService.ApplicationStatistic> applicationStats
                = (List<JobPostingService.ApplicationStatistic>) reportData.get(4);

        if (applicationStats == null || applicationStats.isEmpty()) {
            System.out.println("No application statistics available.");
            return;
        }

        // Sort by application count (descending)
        applicationStats.sort((a, b) -> Integer.compare(b.getCount(), a.getCount()));

        // Convert to arrays for chart display
        String[] labels = new String[applicationStats.size()];
        int[] counts = new int[applicationStats.size()];

        for (int i = 0; i < applicationStats.size(); i++) {
            JobPostingService.ApplicationStatistic stat = applicationStats.get(i);
            labels[i] = stat.getDisplayName();
            counts[i] = stat.getCount();
        }

        // Display chart
        displayChart(labels, counts, "Application Count by Job");
    }

    @SuppressWarnings("unchecked")
    private void displaySalaryReport(List<Object> reportData) {
        List<JobPostingService.SalaryStatistic> salaryStats
                = (List<JobPostingService.SalaryStatistic>) reportData.get(5);

        if (salaryStats == null || salaryStats.isEmpty()) {
            System.out.println("No salary statistics available.");
            return;
        }

        // Convert to arrays for chart display
        String[] labels = new String[salaryStats.size()];
        int[] counts = new int[salaryStats.size()];

        for (int i = 0; i < salaryStats.size(); i++) {
            JobPostingService.SalaryStatistic stat = salaryStats.get(i);
            labels[i] = stat.getRange();
            counts[i] = stat.getCount();
        }

        // Display chart
        displayChart(labels, counts, "Salary Range Distribution");
    }

    // Generic chart display method
    private void displayChart(String[] labels, int[] counts, String title) {
        // Convert arrays to lists
        List<String> categories = Arrays.asList(labels);
        List<Integer> values = Arrays.stream(counts).boxed().collect(Collectors.toList());

        // Use Chart utility to display bar chart
        Chart.barChart(
                categories,
                values,
                title,
                50, // maxBarLength
                '█', // barChar
                true // showValues
        );

        System.out.println("Total Job Postings: " + jobPostings.size());
        input.clickAnythingToContinue();
    }

    // Helper class for the month range summary report
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

    // Get month range from user
    public int[] getMonthRange() {
        System.out.println("\n=== Month Range Summary Report ===");
        System.out.println("Please specify the month range for the report:");

        // Get start month and year
        int startMonth = getMonth("Enter start month (1-12): ");
        if (startMonth == Input.INT_EXIT_VALUE) {
            return null;
        }

        int startYear = getYear("Enter start year: ");
        if (startYear == Input.INT_EXIT_VALUE) {
            return null;
        }

        // Get end month and year
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

    // Helper method to get month input
    public int getMonth(String prompt) {
        IntegerCondition condition = ConditionFactory.integer().min(1).max(12);
        return input.getInt(prompt, condition);
    }

    // Helper method to get year input
    public int getYear(String prompt) {
        IntegerCondition condition = ConditionFactory.integer().min(2000).max(2100);
        return input.getInt(prompt, condition);
    }

    // Display date range error message
    public void displayDateRangeError() {
        System.out.println("\nError: Start date must be before or equal to end date");
        input.clickAnythingToContinue();
    }

    // Display no data message
    public void displayNoDataMessage() {
        System.out.println("\nNo job application data found for the specified date range");
        input.clickAnythingToContinue();
    }

    // Display month range summary report
    public void displayMonthRangeSummaryReport(List<JobPostingService.MonthRangeSummaryData> reportData,
            int startMonth, int startYear, int endMonth, int endYear) {
        System.out.println("\n=== Month Range Summary Report ===");
        System.out.printf("Date Range: %d-%d to %d-%d\n\n", startYear, startMonth, endYear, endMonth);

        if (reportData.isEmpty()) {
            System.out.println("No data available for this date range.");
            input.clickAnythingToContinue();
            return;
        }

        // Convert data to MonthRangeSummaryReportRow objects for TabularPrint
        List<MonthRangeSummaryReportRow> tableRows = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (JobPostingService.MonthRangeSummaryData data : reportData) {
            tableRows.add(new MonthRangeSummaryReportRow(
                    data.getJobTitle(),
                    data.getCompanyName(),
                    data.getAppliedDate().format(dateFormatter),
                    data.getApplicantCount()
            ));
        }

        // Use TabularPrint to display a nicely formatted table
        TabularPrint.printTabular(tableRows, true);

        // 2. Create a chart showing applications by company
        Map<String, Integer> companyApplicationCounts = reportData.stream()
                .collect(Collectors.groupingBy(
                        JobPostingService.MonthRangeSummaryData::getCompanyName,
                        Collectors.summingInt(JobPostingService.MonthRangeSummaryData::getApplicantCount)
                ));

        System.out.println("\n=== Applications by Company ===");

        // Convert to lists for chart display
        List<String> companies = new ArrayList<>(companyApplicationCounts.keySet());
        List<Integer> counts = companies.stream()
                .map(companyApplicationCounts::get)
                .collect(Collectors.toList());

        // Display bar chart
        Chart.barChart(
                companies,
                counts,
                "Application Count by Company",
                50, // maxBarLength
                '█', // barChar
                true // showValues
        );

        // 3. Create a chart showing applications by date
        System.out.println("\n=== Application Trend Over Time ===");

        // Group by date
        Map<LocalDate, Integer> dateApplicationCounts = reportData.stream()
                .collect(Collectors.groupingBy(
                        JobPostingService.MonthRangeSummaryData::getAppliedDate,
                        Collectors.summingInt(JobPostingService.MonthRangeSummaryData::getApplicantCount)
                ));

        // Sort dates
        List<LocalDate> sortedDates = new ArrayList<>(dateApplicationCounts.keySet());
        Collections.sort(sortedDates);

        // Convert to lists for chart display
        List<String> dates = sortedDates.stream()
                .map(date -> date.format(dateFormatter))
                .collect(Collectors.toList());

        List<Integer> dateCounts = sortedDates.stream()
                .map(dateApplicationCounts::get)
                .collect(Collectors.toList());

        // Display bar chart
        Chart.barChart(
                dates,
                dateCounts,
                "Application Count by Date",
                50, // maxBarLength
                '█', // barChar
                true // showValues
        );

        input.clickAnythingToContinue();
    }

    // Helper method to truncate strings if they're too long
    private static String truncate(String value, int maxLength) {
        if (value == null) {
            return "";
        }

        if (value.length() <= maxLength) {
            return value;
        }

        return value.substring(0, maxLength - 3) + "...";
    }

    // Display filter menu
    public void displayFilterMenu(JobPostingService service, LinkedList<JobPosting> jobs) {
        System.out.println();
        new Menu()
                .header("<== Filter Job Postings ==>")
                .choice(
                        new Menu.Choice("Filter by Company", () -> service.filterByCompany(jobs)),
                        new Menu.Choice("Filter by Job Type", () -> service.filterByJobType(jobs)),
                        new Menu.Choice("Filter by Salary Range", () -> service.filterBySalaryRange(jobs)),
                        new Menu.Choice("Filter by Status", () -> service.filterByStatus(jobs)),
                        new Menu.Choice("Filter by Date Range", () -> service.filterByDateRange(jobs)),
                        new Menu.Choice("Sort by Title", () -> {
                            service.sortJobs(jobs, Comparator.comparing(JobPosting::getTitle));
                            displayFilteredJobs(jobs, "Sorted by Title");
                        }),
                        new Menu.Choice("Sort by Salary", () -> {
                            service.sortJobs(jobs, Comparator.comparing(JobPosting::getSalaryMin));
                            displayFilteredJobs(jobs, "Sorted by Minimum Salary");
                        }),
                        new Menu.Choice("Sort by Date", () -> {
                            service.sortJobs(jobs, Comparator.comparing(JobPosting::getCreatedAt).reversed());
                            displayFilteredJobs(jobs, "Sorted by Date (Newest First)");
                        })
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    // Display filtered jobs
    public void displayFilteredJobs(List<JobPosting> filteredJobs, String filterCriteria) {
        System.out.println("\n=== Jobs " + filterCriteria + " ===");

        if (filteredJobs.isEmpty()) {
            Log.info("No job postings match the criteria");
            input.clickAnythingToContinue();
            return;
        }

        Log.info("Found " + filteredJobs.size() + " job postings matching the criteria");
        TabularPrint.printTabular(filteredJobs, true, "default");
        input.clickAnythingToContinue();
    }

    // Get date range for filtering
    public LocalDate[] getDateRange() {
        System.out.println("<== Filter by Date Range [ X to Exit ] ==>");

        // Get start date
        LocalDate startDate = getDate("| Start Date (YYYY-MM-DD) => ");
        if (startDate == null) {
            return null;
        }

        // Get end date
        LocalDate endDate = getDate("| End Date (YYYY-MM-DD) => ");
        if (endDate == null) {
            return null;
        }

        // Validate date range
        if (endDate.isBefore(startDate)) {
            System.out.println("Error: End date must be after start date");
            input.clickAnythingToContinue();
            return null;
        }

        return new LocalDate[]{startDate, endDate};
    }

    // Helper method: Get date input
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
}
