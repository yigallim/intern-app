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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;

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
                        new Menu.Choice("📈 Generate Report", service::report)
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

    public void displaySalaryChart(String[] labels, int[] counts) {
        
        // Convert arrays to Lists for the Chart utility
        List<String> categories = Arrays.asList(labels);
        List<Integer> values = Arrays.stream(counts).boxed().collect(Collectors.toList());
        
        // Use the Chart utility to display the bar chart
        Chart.barChart(
            categories,
            values,
            "Salary Range Distribution (Minimum Salary)",
            50,  // maxBarLength
            '█', // barChar
            true // showValues
        );
        
        System.out.println("Total Job Postings: " + jobPostings.size());
        input.clickAnythingToContinue();
    }
}
