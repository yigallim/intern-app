/**
 * @author Yeoh Ming Zhe
 */

package com.tarumt.control;

import com.tarumt.boundary.JobPostingUI;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.*;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.search.FuzzySearch;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.tarumt.adt.list.ListInterface;
import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.map.MapInterface;
import com.tarumt.adt.map.SimpleHashMap;
import java.time.YearMonth;

public class JobPostingController {

    private static JobPostingController instance;
    private ListInterface<JobPosting> jobPostings = new DoublyLinkedList<>();
    private final JobPostingUI jobPostingUI;
    private final ListInterface<JobApplication> jobApplications;

    private JobPostingController() {
        Input input = new Input();
        this.jobPostingUI = new JobPostingUI(input);
        this.jobPostings = Initializer.getJobPostings();
        this.jobApplications = Initializer.getJobApplications();
    }

    public static JobPostingController getInstance() {
        if (instance == null) {
            instance = new JobPostingController();
        }
        return instance;
    }

    private ListInterface<JobPosting> getEmployerJobPostings() {
        Company company = Context.getCompany();
        if (company == null) {
            return new DoublyLinkedList<>();
        }
        return jobPostings.filter(jobPosting ->
                jobPosting.getCompany() != null &&
                        jobPosting.getCompany().getId().equals(company.getId())
        );
    }

    public void run() {
        this.jobPostingUI.menu();
    }

    public void create() {
        while (true) {
            jobPostingUI.printCreateJobPostingMsg();
            jobPostingUI.printNextIDMsg();
            JobPosting job = this.getJobPosting();
            if (job == null) {
                return;
            }
            jobPostings.add(job);
            jobPostingUI.printSuccessCreateJobPostingMsg();
            if (!jobPostingUI.continueToCreateJobPosting()) {
                return;
            }
        }
    }

    public void read() {
        if (Context.isEmployer()) {
            this.jobPostingUI.printAllJobs(this.getEmployerJobPostings());
        } else {
            this.jobPostingUI.printAllJobs(this.jobPostings);
        }
    }

    public void search() {
        while (true) {
            ListInterface<JobPosting> accessiblePostings = Context.isEmployer() ?
                    this.getEmployerJobPostings() : this.jobPostings;

            jobPostingUI.printSearchJobPostingMsg(accessiblePostings);
            if (accessiblePostings.isEmpty()) {
                return;
            }
            String query = jobPostingUI.getSearchJobPostingQuery();
            if (query.equals(Input.STRING_EXIT_VALUE)) {
                return;
            }
            FuzzySearch.Result<JobPosting> result = FuzzySearch.searchList(JobPosting.class, accessiblePostings, query, Context.isEmployer() ? "employer" : null);
            jobPostingUI.printSearchResult(result);
        }
    }

    public void filter() {
        if (jobPostings.isEmpty()) {
            Log.info("No job postings available to filter");
            return;
        }
        ListInterface<JobPosting> filteredJobs = new DoublyLinkedList<>(jobPostings);
        jobPostingUI.displayFilterMenu(filteredJobs);
    }

    public void filterByCompany() {
        Company selectedCompany = jobPostingUI.getJobPostingCompany();
        if (selectedCompany == null) return;

        ListInterface<JobPosting> filtered = jobPostings.filter(job ->
                job.getCompany() != null &&
                        job.getCompany().getId().equals(selectedCompany.getId())
        );
        jobPostingUI.displayFilteredJobs(filtered);
    }

    public void filterByJobType() {
        JobPosting.Type selectedType = jobPostingUI.getJobPostingType();
        if (selectedType == null) return;

        ListInterface<JobPosting> filtered = getEmployerJobPostings().filter(job ->
                job.getType() == selectedType
        );
        jobPostingUI.displayFilteredJobs(filtered);
    }

    public void filterBySalaryRange() {
        String salaryRange = jobPostingUI.getSalaryRange();
        if (salaryRange.equals(Input.STRING_EXIT_VALUE)) return;

        String[] parts = salaryRange.split("-");
        int minSalary = Integer.parseInt(parts[0]);
        int maxSalary = Integer.parseInt(parts[1]);

        ListInterface<JobPosting> filtered = getEmployerJobPostings().filter(job ->
                job.getSalaryMin() >= minSalary && job.getSalaryMax() <= maxSalary
        );
        jobPostingUI.displayFilteredJobs(filtered);
    }

    public void filterByStatus() {
        JobPosting.Status selectedStatus = jobPostingUI.getJobPostingStatus();
        if (selectedStatus == null) return;

        ListInterface<JobPosting> filtered = getEmployerJobPostings().filter(job ->
                job.getStatus() == selectedStatus
        );
        jobPostingUI.displayFilteredJobs(filtered);
    }

    public void filterByDateRange() {
        LocalDate[] dateRange = jobPostingUI.getDateRange();
        if (dateRange == null) return;

        LocalDateTime startDateTime = dateRange[0].atStartOfDay();
        LocalDateTime endDateTime = dateRange[1].atTime(23, 59, 59);

        ListInterface<JobPosting> filtered = getEmployerJobPostings().filter(job ->
                !job.getCreatedAt().isBefore(startDateTime) &&
                        !job.getCreatedAt().isAfter(endDateTime)
        );
        jobPostingUI.displayFilteredJobs(filtered);
    }

    public void sortJobsByTitle() {
        ListInterface<JobPosting> sorted = new DoublyLinkedList<>(getEmployerJobPostings());
        sorted.sort((job1, job2) ->
                job1.getTitle().compareToIgnoreCase(job2.getTitle())
        );
        jobPostingUI.displayFilteredJobs(sorted);
    }

    public void sortJobsBySalary() {
        ListInterface<JobPosting> sorted = new DoublyLinkedList<>(getEmployerJobPostings());
        sorted.sort((job1, job2) -> {
            int avgSalary1 = (job1.getSalaryMin() + job1.getSalaryMax()) / 2;
            int avgSalary2 = (job2.getSalaryMin() + job2.getSalaryMax()) / 2;
            return Integer.compare(avgSalary1, avgSalary2);
        });
        jobPostingUI.displayFilteredJobs(sorted);
    }

    public void sortJobsByDate() {
        ListInterface<JobPosting> sorted = new DoublyLinkedList<>(getEmployerJobPostings());
        sorted.sort((job1, job2) ->
                job1.getCreatedAt().compareTo(job2.getCreatedAt())
        );
        jobPostingUI.displayFilteredJobs(sorted);
    }

    public void update() {
        ListInterface<JobPosting> accessiblePostings = Context.isEmployer() ?
                this.getEmployerJobPostings() : this.jobPostings;

        jobPostingUI.printUpdateJobMsg(accessiblePostings);
        if (accessiblePostings.isEmpty()) {
            return;
        }

        ListInterface<String> ids = BaseEntity.getIds(accessiblePostings);
        String id = jobPostingUI.getJobPostingId(ids);
        if (id.equals(Input.STRING_EXIT_VALUE)) {
            return;
        }

        JobPosting jobPosting = BaseEntity.getById(id, accessiblePostings);
        jobPostingUI.printOriginalJobValue(jobPosting);
        jobPostingUI.updateJobMode(jobPosting.getId());
    }

    public void delete() {
        ListInterface<JobPosting> accessiblePostings = Context.isEmployer() ?
                this.getEmployerJobPostings() : this.jobPostings;

        jobPostingUI.deleteMenu(accessiblePostings);
    }

    public void deleteByIndex() {
        ListInterface<JobPosting> accessiblePostings = Context.isEmployer() ?
                this.getEmployerJobPostings() : this.jobPostings;

        jobPostingUI.printDeleteByIndexMsg();
        int index = jobPostingUI.getJobPostingIndex(accessiblePostings.size());
        if (index == Input.INT_EXIT_VALUE) {
            return;
        }
        if (jobPostingUI.confirmDelete()) {
            JobPosting jobPosting = accessiblePostings.get(index - 1);
            jobPostings.remove(jobPosting);
            jobPostingUI.printSuccessDeleteMsg(jobPosting.getId());
        }
    }

    public void deleteByRange() {
        ListInterface<JobPosting> accessiblePostings = Context.isEmployer() ?
                this.getEmployerJobPostings() : this.jobPostings;

        jobPostingUI.printDeleteByRangeMsg();
        int startIndex = jobPostingUI.getDeleteStartIndex(accessiblePostings.size());
        if (startIndex == Input.INT_EXIT_VALUE) {
            return;
        }

        int endIndex = jobPostingUI.getDeleteEndIndex(startIndex, accessiblePostings.size());
        if (endIndex == Input.INT_EXIT_VALUE) {
            return;
        }

        if (endIndex >= startIndex) {
            if (jobPostingUI.confirmDelete()) {
                ListInterface<JobPosting> toRemove = accessiblePostings.subList(startIndex - 1, endIndex);
                jobPostings.removeAll(toRemove);
                jobPostingUI.printSuccessDeleteByRangeMsg(startIndex, endIndex);
            }
        }
    }

    public void deleteById() {
        ListInterface<JobPosting> accessiblePostings = Context.isEmployer() ?
                this.getEmployerJobPostings() : this.jobPostings;

        jobPostingUI.printDeleteByIdMsg();
        ListInterface<String> ids = BaseEntity.getIds(accessiblePostings);
        String id = jobPostingUI.getJobPostingId(ids);
        if (id.equals(Input.STRING_EXIT_VALUE)) {
            return;
        }

        if (jobPostingUI.confirmDelete()) {
            JobPosting jobPosting = BaseEntity.getById(id, accessiblePostings);
            jobPostings.remove(jobPosting);
            jobPostingUI.printSuccessDeleteMsg(jobPosting.getId());
        }
    }

    public void deleteAll() {
        if (Context.isEmployer()) {
            ListInterface<JobPosting> employerPostings = this.getEmployerJobPostings();
            if (jobPostingUI.confirmDelete()) {
                jobPostings.removeAll(employerPostings);
                jobPostingUI.printSuccessDeleteAllMsg();
            }
        } else {
            if (jobPostingUI.confirmDelete()) {
                jobPostings.clear();
                jobPostingUI.printSuccessDeleteAllMsg();
            }
        }
    }

    private JobPosting getJobPosting() {
        String title = jobPostingUI.getJobPostingTitle();
        if (title.equals(Input.STRING_EXIT_VALUE)) {
            return null;
        }

        Company company;

        if (Context.isEmployer()) {
            company = Context.getCompany();
        } else {
            company = jobPostingUI.getJobPostingCompany();
        }
        if (company == null) {
            return null;
        }

        String salaryRange = jobPostingUI.getSalaryRange();
        if (salaryRange.equals(Input.STRING_EXIT_VALUE)) {
            return null;
        }
        String[] parts = salaryRange.split("-");
        int salaryMin = Integer.parseInt(parts[0]);
        int salaryMax = Integer.parseInt(parts[1]);

        String description = jobPostingUI.getJobPostingDescription();
        if (description.equals(Input.STRING_EXIT_VALUE)) {
            return null;
        }

        JobPosting.Type type = jobPostingUI.getJobPostingType();
        if (type == null) {
            return null;
        }

        LocalDateTime createdAt = Context.getDateTime(), updatedAt = Context.getDateTime();
        return new JobPosting(title, company, salaryMin, salaryMax, description, type, JobPosting.Status.OPEN,
                createdAt, updatedAt);
    }

    public void updateJobTitle(String id) {
        String fieldName = "Job Title";
        JobPosting jobPosting = BaseEntity.getById(id, jobPostings);
        if (jobPosting == null) {
            return;
        }

        jobPostingUI.printUpdateFieldMessage(fieldName);
        String newJobTitle = jobPostingUI.getJobPostingTitle();
        if (newJobTitle.equals(Input.STRING_EXIT_VALUE)) {
            return;
        }
        jobPosting.setTitle(newJobTitle);
        jobPosting.setUpdatedAt(Context.getDateTime());
        jobPostingUI.printUpdateSuccessMessage(jobPosting, fieldName);

    }

    public void updateJobCompany(String id) {
        String fieldName = "Job Company";
        JobPosting jobPosting = BaseEntity.getById(id, jobPostings);
        if (jobPosting == null) {
            return;
        }

        jobPostingUI.printUpdateFieldMessage(fieldName);
        Company newCompany = jobPostingUI.getJobPostingCompany();
        if (newCompany == null) {
            return;
        }
        jobPosting.setCompany(newCompany);
        jobPosting.setUpdatedAt(Context.getDateTime());
        jobPostingUI.printUpdateSuccessMessage(jobPosting, fieldName);
    }

    public void updateSalaryRange(String id) {
        String fieldName = "Salary Range";
        JobPosting jobPosting = BaseEntity.getById(id, jobPostings);
        if (jobPosting == null) {
            return;
        }

        jobPostingUI.printUpdateFieldMessage(fieldName);
        String salaryRange = jobPostingUI.getSalaryRange();
        if (salaryRange.equals(Input.STRING_EXIT_VALUE)) {
            return;
        }
        String[] parts = salaryRange.split("-");
        int newMinSalary = Integer.parseInt(parts[0]);
        int newMaxSalary = Integer.parseInt(parts[1]);

        jobPosting.setSalaryMin(newMinSalary);
        jobPosting.setSalaryMax(newMaxSalary);
        jobPosting.setUpdatedAt(Context.getDateTime());
        jobPostingUI.printUpdateSuccessMessage(jobPosting, fieldName);
    }

    public void updateDescription(String id) {
        String fieldName = "Description";
        JobPosting jobPosting = BaseEntity.getById(id, jobPostings);
        if (jobPosting == null) {
            return;
        }

        jobPostingUI.printUpdateFieldMessage(fieldName);

        String newDescription = jobPostingUI.getJobPostingDescription();
        if (newDescription.equals(Input.STRING_EXIT_VALUE)) {
            return;
        }

        jobPosting.setDescription(newDescription);
        jobPosting.setUpdatedAt(Context.getDateTime());

        jobPostingUI.printUpdateSuccessMessage(jobPosting, fieldName);
    }

    public void updateJobType(String id) {
        String fieldName = "Job Type";
        JobPosting jobPosting = BaseEntity.getById(id, jobPostings);
        if (jobPosting == null) {
            return;
        }

        jobPostingUI.printUpdateFieldMessage(fieldName);

        JobPosting.Type newType = jobPostingUI.getJobPostingType();
        if (newType == null) {
            return;
        }
        jobPosting.setType(newType);
        jobPosting.setUpdatedAt(Context.getDateTime());

        jobPostingUI.printUpdateSuccessMessage(jobPosting, fieldName);
    }

    public void updateStatus(String id) {
        String fieldName = "Status";
        JobPosting jobPosting = BaseEntity.getById(id, jobPostings);
        if (jobPosting == null) {
            return;
        }

        jobPostingUI.printUpdateFieldMessage(fieldName);

        JobPosting.Status newStatus = jobPostingUI.getJobPostingStatus();
        if (newStatus == null) {
            return;
        }
        jobPosting.setStatus(newStatus);
        jobPosting.setUpdatedAt(Context.getDateTime());

        jobPostingUI.printUpdateSuccessMessage(jobPosting, fieldName);
    }

    public void updateAllField(String id) {
        final String fieldName = "All Fields";
        JobPosting jobPosting = BaseEntity.getById(id, jobPostings);
        jobPostingUI.printUpdateFieldMessage(fieldName);

        String newTitle = jobPostingUI.getJobPostingTitle();
        if (newTitle.equals(Input.STRING_EXIT_VALUE))
            return;

        Company newCompany = null;
        if (Context.isAdmin())
            newCompany = jobPostingUI.getJobPostingCompany();

        String salaryRange = jobPostingUI.getSalaryRange();
        if (salaryRange.equals(Input.STRING_EXIT_VALUE))
            return;
        String[] parts = salaryRange.split("-");
        int newMinSalary = Integer.parseInt(parts[0]);
        int newMaxSalary = Integer.parseInt(parts[1]);

        String newDescription = jobPostingUI.getJobPostingDescription();
        if (newDescription.equals(Input.STRING_EXIT_VALUE))
            return;

        JobPosting.Type newType = jobPostingUI.getJobPostingType();
        if (newType == null)
            return;

        JobPosting.Status newStatus = jobPostingUI.getJobPostingStatus();
        if (newStatus == null)
            return;

        jobPosting.setTitle(newTitle);
        if (newCompany != null)
            jobPosting.setCompany(newCompany);
        jobPosting.setSalaryMin(newMinSalary);
        jobPosting.setSalaryMax(newMaxSalary);
        jobPosting.setDescription(newDescription);
        jobPosting.setType(newType);
        jobPosting.setStatus(newStatus);
        jobPosting.setUpdatedAt(Context.getDateTime());
        jobPostingUI.printUpdateSuccessMessage(jobPosting, "All Fields");
    }

    public void displayMonthRangeSummaryReport() {

        int[] monthRange = jobPostingUI.getMonthRange();
        if (monthRange == null) {
            return;
        }

        int startMonth = monthRange[0];
        int startYear = monthRange[1];
        int endMonth = monthRange[2];
        int endYear = monthRange[3];

        if (startYear > endYear || (startYear == endYear && startMonth > endMonth)) {
            jobPostingUI.displayDateRangeError();
            return;
        }

        ListInterface<MonthRangeSummaryData> reportData = generateMonthRangeSummaryReport(startMonth, startYear, endMonth, endYear);

        if (reportData.isEmpty()) {
            jobPostingUI.displayNoDataMessage();
            return;
        }

        jobPostingUI.displayMonthRangeSummaryReport(reportData, startMonth, startYear, endMonth, endYear);
    }

    public static class MonthRangeSummaryData {
        private String jobTitle;
        private String companyName;
        private LocalDate appliedDate;
        private int applicantCount;

        public MonthRangeSummaryData(String jobTitle, String companyName, LocalDate appliedDate, int applicantCount) {
            this.jobTitle = jobTitle;
            this.companyName = companyName;
            this.appliedDate = appliedDate;
            this.applicantCount = applicantCount;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public String getCompanyName() {
            return companyName;
        }

        public LocalDate getAppliedDate() {
            return appliedDate;
        }

        public int getApplicantCount() {
            return applicantCount;
        }
    }

    public ListInterface<MonthRangeSummaryData> generateMonthRangeSummaryReport(int startMonth, int startYear, int endMonth, int endYear) {
        ListInterface<MonthRangeSummaryData> summaryReport = new DoublyLinkedList<>();

        // Define date range
        LocalDate startDate = LocalDate.of(startYear, startMonth, 1);
        LocalDate endDate = YearMonth.of(endYear, endMonth).atEndOfMonth();

        // Filter applications in the date range
        ListInterface<JobApplication> filteredApplications = new DoublyLinkedList<>();
        for (int i = 0; i < jobApplications.size(); i++) {
            JobApplication app = jobApplications.get(i);
            LocalDate applicationDate = app.getAppliedAt().toLocalDate();

            // For employer, only include applications for their job postings
            if (Context.isEmployer()) {
                Company loggedInCompany = Context.getCompany();
                if (app.getJobPosting().getCompany().getId().equals(loggedInCompany.getId()) &&
                        !applicationDate.isBefore(startDate) && !applicationDate.isAfter(endDate)) {
                    filteredApplications.add(app);
                }
            } else {
                // For admin, include all applications in the date range
                if (!applicationDate.isBefore(startDate) && !applicationDate.isAfter(endDate)) {
                    filteredApplications.add(app);
                }
            }
        }

        // Rest of the method remains the same
        // Group by job and date
        MapInterface<JobPosting, MapInterface<LocalDate, Integer>> jobDateCounts = new SimpleHashMap<>();

        // Count applications by job and date
        for (int i = 0; i < filteredApplications.size(); i++) {
            JobApplication app = filteredApplications.get(i);
            JobPosting job = app.getJobPosting();
            LocalDate date = app.getAppliedAt().toLocalDate();

            // Get or create the date map for this job
            MapInterface<LocalDate, Integer> dateCounts;
            if (jobDateCounts.containsKey(job)) {
                dateCounts = jobDateCounts.get(job);
            } else {
                dateCounts = new SimpleHashMap<>();
                jobDateCounts.put(job, dateCounts);
            }

            // Increment the count for this date
            int currentCount = dateCounts.getOrDefault(date, 0);
            dateCounts.put(date, currentCount + 1);
        }

        // Convert to report data format
        for (JobPosting job : jobDateCounts.keySet()) {
            MapInterface<LocalDate, Integer> dateCounts = jobDateCounts.get(job);

            for (LocalDate date : dateCounts.keySet()) {
                int count = dateCounts.get(date);
                summaryReport.add(new MonthRangeSummaryData(
                        job.getTitle(),
                        job.getCompany().getName(),
                        date,
                        count
                ));
            }
        }

        summaryReport.sort((data1, data2) ->
                data1.getAppliedDate().compareTo(data2.getAppliedDate())
        );

        return summaryReport;
    }
}