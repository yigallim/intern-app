package com.tarumt.control;

import com.tarumt.boundary.JobPostingUI;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.BaseEntity;
import com.tarumt.entity.Company;
import com.tarumt.entity.JobPosting;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.search.FuzzySearch;
import com.tarumt.entity.JobApplication;
import com.tarumt.utility.validation.ConditionFactory;
import com.tarumt.utility.validation.StringCondition;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import java.util.LinkedList;
import java.util.Map;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JobPostingService implements Service {

    private List<JobPosting> jobPostings = new LinkedList<>();
    private final JobPostingUI jobPostingUI;
    private Applicant currentApplicant;
    private final List<JobApplication> jobApplications;
    
    // Add a static reference to the current instance
    private static JobPostingService instance;

    public JobPostingService() {
        Input input = new Input();
        this.jobPostings = Initializer.getJobPostings();
        this.jobPostingUI = new JobPostingUI(input);
        this.jobApplications = Initializer.getJobApplication();
    }
    
    public static JobPostingService getInstance() {
        return instance;
    }

    @Override
    public void run() {
        this.jobPostingUI.menu(this);
    }

    @Override
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

    @Override
    public void read() {
        this.jobPostingUI.displayAllJobs(this.jobPostings);
    }

    @Override
    public void search() {
        while (true) {
            jobPostingUI.printSearchJobPostingMsg(jobPostings);
            if (this.jobPostings.isEmpty()) {
                return;
            }
            String query = jobPostingUI.getSearchJobPostingQuery();
            if (query.equals(Input.STRING_EXIT_VALUE)) {
                return;
            }
            FuzzySearch.Result<JobPosting> result = FuzzySearch.searchList(JobPosting.class, this.jobPostings, query);
            jobPostingUI.printSearchResult(result);
        }
    }

    @Override
    public void filter() {
        if (jobPostings.isEmpty()) {
            Log.info("No job postings available to filter");
            return;
        }
        
        // Create a doubly linked list to store filtered jobs
        LinkedList<JobPosting> filteredJobs = new LinkedList<>(jobPostings);
        
        // Display filter menu and process user selections
        jobPostingUI.displayFilterMenu(this, filteredJobs);
    }

    // Filter by company name
    public void filterByCompany(LinkedList<JobPosting> jobs) {
        Company selectedCompany = jobPostingUI.getJobPostingCompany();
        if (selectedCompany == null) return;
        
        LinkedList<JobPosting> filtered = new LinkedList<>();
        for (JobPosting job : jobs) {
            if (job.getCompany().equals(selectedCompany)) {
                filtered.add(job);
            }
        }
        
        jobPostingUI.displayFilteredJobs(filtered);
    }

    // Filter by job type
    public void filterByJobType(LinkedList<JobPosting> jobs) {
        JobPosting.Type selectedType = jobPostingUI.getJobPostingType();
        if (selectedType == null) return;
        
        LinkedList<JobPosting> filtered = new LinkedList<>();
        for (JobPosting job : jobs) {
            if (job.getType() == selectedType) {
                filtered.add(job);
            }
        }
        
        jobPostingUI.displayFilteredJobs(filtered);
    }

    // Filter by salary range
    public void filterBySalaryRange(LinkedList<JobPosting> jobs) {
        String salaryRange = jobPostingUI.getSalaryRange();
        if (salaryRange.equals(Input.STRING_EXIT_VALUE)) return;
        
        String[] parts = salaryRange.split("-");
        int minSalary = Integer.parseInt(parts[0]);
        int maxSalary = Integer.parseInt(parts[1]);
        
        LinkedList<JobPosting> filtered = new LinkedList<>();
        for (JobPosting job : jobs) {
            if (job.getSalaryMin() >= minSalary && job.getSalaryMax() <= maxSalary) {
                filtered.add(job);
            }
        }
        
        jobPostingUI.displayFilteredJobs(filtered);
    }

    // Filter by job status
    public void filterByStatus(LinkedList<JobPosting> jobs) {
        JobPosting.Status selectedStatus = jobPostingUI.getJobPostingStatus();
        if (selectedStatus == null) return;
        
        LinkedList<JobPosting> filtered = new LinkedList<>();
        for (JobPosting job : jobs) {
            if (job.getStatus() == selectedStatus) {
                filtered.add(job);
            }
        }
        
        jobPostingUI.displayFilteredJobs(filtered);
    }

    // Filter by date range
    public void filterByDateRange(LinkedList<JobPosting> jobs) {
        LocalDate[] dateRange = jobPostingUI.getDateRange();
        if (dateRange == null) return;
        
        LocalDate startDate = dateRange[0];
        LocalDate endDate = dateRange[1];
        
        LinkedList<JobPosting> filtered = new LinkedList<>();
        for (JobPosting job : jobs) {
            LocalDate createdAt = job.getCreatedAt();
            if (!createdAt.isBefore(startDate) && !createdAt.isAfter(endDate)) {
                filtered.add(job);
            }
        }
        
        jobPostingUI.displayFilteredJobs(filtered);
    }

    // Sort jobs using TimSort algorithm
    public void sortJobs(LinkedList<JobPosting> jobs, Comparator<JobPosting> comparator) {
        // Convert LinkedList to array for TimSort
        JobPosting[] jobArray = jobs.toArray(new JobPosting[0]);
        
        // Implement TimSort
        timSort(jobArray, comparator);
        
        // Clear the original list and add sorted elements
        jobs.clear();
        for (JobPosting job : jobArray) {
            jobs.add(job);
        }
    }

    // TimSort implementation
    private void timSort(JobPosting[] arr, Comparator<JobPosting> comparator) {
        int n = arr.length;
        int minRun = minRunLength(n);
        
        // Sort individual subarrays of size minRun
        for (int i = 0; i < n; i += minRun) {
            int end = Math.min(i + minRun - 1, n - 1);
            insertionSort(arr, i, end, comparator);
        }
        
        // Start merging from size minRun
        for (int size = minRun; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = Math.min(n - 1, left + size - 1);
                int right = Math.min(n - 1, left + 2 * size - 1);
                
                if (mid < right) {
                    merge(arr, left, mid, right, comparator);
                }
            }
        }
    }

    // Calculate minimum run length for TimSort
    private int minRunLength(int n) {
        int r = 0;
        while (n >= 32) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    // Insertion sort for small subarrays
    private void insertionSort(JobPosting[] arr, int left, int right, Comparator<JobPosting> comparator) {
        for (int i = left + 1; i <= right; i++) {
            JobPosting temp = arr[i];
            int j = i - 1;
            while (j >= left && comparator.compare(arr[j], temp) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    // Merge two sorted subarrays
    private void merge(JobPosting[] arr, int left, int mid, int right, Comparator<JobPosting> comparator) {
        int len1 = mid - left + 1;
        int len2 = right - mid;
        
        JobPosting[] leftArr = new JobPosting[len1];
        JobPosting[] rightArr = new JobPosting[len2];
        
        System.arraycopy(arr, left, leftArr, 0, len1);
        System.arraycopy(arr, mid + 1, rightArr, 0, len2);
        
        int i = 0, j = 0, k = left;
        
        while (i < len1 && j < len2) {
            if (comparator.compare(leftArr[i], rightArr[j]) <= 0) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        
        while (i < len1) {
            arr[k++] = leftArr[i++];
        }
        
        while (j < len2) {
            arr[k++] = rightArr[j++];
        }
    }

    @Override
    public void update() {
        jobPostingUI.printUpdateJobMsg(this.jobPostings);
        if (this.jobPostings.isEmpty()) {
            return;
        }

        List<String> ids = BaseEntity.getIds(jobPostings);
        String id = jobPostingUI.getJobPostingId("| Select Job Posting ID => ", ids);
        if (id.equals(Input.STRING_EXIT_VALUE)) {
            return;
        }

        JobPosting jobPosting = BaseEntity.getById(id, jobPostings);
        jobPostingUI.printOriginalJobValue(jobPosting);
        jobPostingUI.updateJobMode(this, jobPosting.getId());
    }

    @Override
    public void delete() {
        jobPostingUI.deleteMenu(this, this.jobPostings);
    }

    public void deleteByIndex() {
        jobPostingUI.printDeleteByIndexMsg();
        int index = jobPostingUI.getJobPostingIndex(this.jobPostings.size());
        if (index == Input.INT_EXIT_VALUE) {
            return;
        }
        if (jobPostingUI.confirmDelete()) {
            JobPosting jobPosting = jobPostings.remove(index - 1);
            jobPostingUI.printSuccessDeleteMsg(jobPosting.getId());
        }
    }

    public void deleteByRange() {
        jobPostingUI.printDeleteByRangeMsg();
        int startIndex = jobPostingUI.getDeleteStartIndex(this.jobPostings.size());
        if (startIndex == Input.INT_EXIT_VALUE) {
            return;
        }

        int endIndex = jobPostingUI.getDeleteEndIndex(startIndex, this.jobPostings.size());
        if (endIndex == Input.INT_EXIT_VALUE) {
            return;
        }

        if (endIndex >= startIndex) {
            if (jobPostingUI.confirmDelete()) {
                jobPostings.subList(startIndex - 1, endIndex).clear();
                jobPostingUI.printSuccessDeleteByRangeMsg(startIndex, endIndex);
            }
        }
    }

    public void deleteById() {
        jobPostingUI.printDeleteByIdMsg();
        List<String> ids = BaseEntity.getIds(jobPostings);
        String id = jobPostingUI.getJobPostingId("| Select Job Posting ID => ", ids);
        if (id.equals(Input.STRING_EXIT_VALUE)) {
            return;
        }

        if (jobPostingUI.confirmDelete()) {
            JobPosting jobPosting = BaseEntity.getById(id, jobPostings);
            jobPostings.remove(jobPosting);
            jobPostingUI.printSuccessDeleteMsg(jobPosting.getId());
        }
    }

    public void deleteAll() {
        if (jobPostingUI.confirmDelete()) {
            jobPostings.clear();
            jobPostingUI.printSuccessDeleteAllMsg();
        }
    }

    @Override
    public void report() {
        if (jobPostings.isEmpty()) {
            Log.info("No job postings available to generate report");
            return;
        }
        
        // Generate various report data
        List<Object> reportData = new LinkedList<>();
        
        // 1. Statistics on job type distribution
        List<JobTypeStatistic> jobTypeStats = generateJobTypeStats();
        reportData.add(jobTypeStats);
        
        // 2. Statistics on company job count
        List<CompanyJobStatistic> companyJobStats = generateCompanyJobStats();
        reportData.add(companyJobStats);
        
        // 3. Job status distribution
        List<StatusStatistic> statusStats = generateStatusStats();
        reportData.add(statusStats);
        
        // 4. Job posting trends by month
        List<TrendStatistic> postingTrends = generatePostingTrends();
        reportData.add(postingTrends);
        
        // 5. Application count statistics
        List<ApplicationStatistic> applicationStats = generateApplicationStats();
        reportData.add(applicationStats);
        
        // 6. Salary range statistics
        List<SalaryStatistic> salaryStats = generateSalaryStats();
        reportData.add(salaryStats);
        
        // Pass report data to UI layer for display
        jobPostingUI.displayReports(reportData);
    }
    
    // Method for month range summary report
    public void displayMonthRangeSummaryReport() {
        // Get month range from UI
        int[] monthRange = jobPostingUI.getMonthRange();
        if (monthRange == null) {
            return;  // User cancelled
        }
        
        int startMonth = monthRange[0];
        int startYear = monthRange[1];
        int endMonth = monthRange[2];
        int endYear = monthRange[3];
        
        // Validate date range
        if (startYear > endYear || (startYear == endYear && startMonth > endMonth)) {
            jobPostingUI.displayDateRangeError();
            return;
        }
        
        // Generate report data
        List<MonthRangeSummaryData> reportData = generateMonthRangeSummaryReport(startMonth, startYear, endMonth, endYear);
        
        if (reportData.isEmpty()) {
            jobPostingUI.displayNoDataMessage();
            return;
        }
        
        // Display the report data using UI
        jobPostingUI.displayMonthRangeSummaryReport(reportData, startMonth, startYear, endMonth, endYear);
    }
    
    // Inner class for job type statistics - make it public static
    public static class JobTypeStatistic {
        private JobPosting.Type type;
        private int count;
        
        public JobTypeStatistic(JobPosting.Type type, int count) {
            this.type = type;
            this.count = count;
        }
        
        public JobPosting.Type getType() {
            return type;
        }
        
        public int getCount() {
            return count;
        }
    }
    
    // Generate job type statistics
    private List<JobTypeStatistic> generateJobTypeStats() {
        List<JobTypeStatistic> typeStats = new LinkedList<>();
        
        // Initialize statistics for all job types
        for (JobPosting.Type type : JobPosting.Type.values()) {
            typeStats.add(new JobTypeStatistic(type, 0));
        }
        
        // Count jobs by type
        for (JobPosting job : jobPostings) {
            JobPosting.Type jobType = job.getType();
            for (JobTypeStatistic stat : typeStats) {
                if (stat.getType().equals(jobType)) {
                    stat.count++;
                    break;
                }
            }
        }
        
        return typeStats;
    }
    
    // Inner class for company job statistics - make it public static
    public static class CompanyJobStatistic {
        private String companyName;
        private int count;
        
        public CompanyJobStatistic(String companyName, int count) {
            this.companyName = companyName;
            this.count = count;
        }
        
        public String getCompanyName() {
            return companyName;
        }
        
        public int getCount() {
            return count;
        }
    }
    
    // Generate company job count statistics
    private List<CompanyJobStatistic> generateCompanyJobStats() {
        List<CompanyJobStatistic> companyStats = new LinkedList<>();
        
        for (JobPosting job : jobPostings) {
            String companyName = job.getCompany().getName();
            boolean found = false;
            
            // Check if company already exists in the list
            for (CompanyJobStatistic stat : companyStats) {
                if (stat.getCompanyName().equals(companyName)) {
                    stat.count++;
                    found = true;
                    break;
                }
            }
            
            // If company not found, add it to the list
            if (!found) {
                companyStats.add(new CompanyJobStatistic(companyName, 1));
            }
        }
        
        return companyStats;
    }
    
    // Inner class for status statistics - make it public static
    public static class StatusStatistic {
        private JobPosting.Status status;
        private int count;
        
        public StatusStatistic(JobPosting.Status status, int count) {
            this.status = status;
            this.count = count;
        }
        
        public JobPosting.Status getStatus() {
            return status;
        }
        
        public int getCount() {
            return count;
        }
    }
    
    // Generate job status statistics
    private List<StatusStatistic> generateStatusStats() {
        List<StatusStatistic> statusStats = new LinkedList<>();
        
        // Initialize statistics for all statuses
        for (JobPosting.Status status : JobPosting.Status.values()) {
            statusStats.add(new StatusStatistic(status, 0));
        }
        
        // Count jobs by status
        for (JobPosting job : jobPostings) {
            JobPosting.Status jobStatus = job.getStatus();
            for (StatusStatistic stat : statusStats) {
                if (stat.getStatus().equals(jobStatus)) {
                    stat.count++;
                    break;
                }
            }
        }
        
        return statusStats;
    }
    
    // Inner class for trend statistics - make it public static
    public static class TrendStatistic {
        private String monthYear;
        private int count;
        
        public TrendStatistic(String monthYear, int count) {
            this.monthYear = monthYear;
            this.count = count;
        }
        
        public String getMonthYear() {
            return monthYear;
        }
        
        public int getCount() {
            return count;
        }
    }
    
    // Generate job posting trends by month
    private List<TrendStatistic> generatePostingTrends() {
        List<TrendStatistic> trendStats = new LinkedList<>();
        
        // Count jobs by month and year
        for (JobPosting job : jobPostings) {
            LocalDate createdDate = job.getCreatedAt();
            String monthYear = createdDate.getMonth().toString() + " " + createdDate.getYear();
            boolean found = false;
            
            // Check if month-year already exists in the list
            for (TrendStatistic stat : trendStats) {
                if (stat.getMonthYear().equals(monthYear)) {
                    stat.count++;
                    found = true;
                    break;
                }
            }
            
            // If month-year not found, add it to the list
            if (!found) {
                trendStats.add(new TrendStatistic(monthYear, 1));
            }
        }
        
        // Sort by month and year
        trendStats.sort((a, b) -> {
            String[] partsA = a.getMonthYear().split(" ");
            String[] partsB = b.getMonthYear().split(" ");
            
            int yearA = Integer.parseInt(partsA[1]);
            int yearB = Integer.parseInt(partsB[1]);
            
            if (yearA != yearB) {
                return Integer.compare(yearA, yearB);
            }
            
            return a.getMonthYear().compareTo(b.getMonthYear());
        });
        
        return trendStats;
    }
    
    // Inner class for application statistics - make it public static
    public static class ApplicationStatistic {
        private String jobTitle;
        private String jobId;
        private int count;
        
        public ApplicationStatistic(String jobTitle, String jobId, int count) {
            this.jobTitle = jobTitle;
            this.jobId = jobId;
            this.count = count;
        }
        
        public String getJobTitle() {
            return jobTitle;
        }
        
        public String getJobId() {
            return jobId;
        }
        
        public int getCount() {
            return count;
        }
        
        public String getDisplayName() {
            return jobTitle + " (" + jobId + ")";
        }
    }
    
    // Generate application count statistics
    private List<ApplicationStatistic> generateApplicationStats() {
        List<ApplicationStatistic> applicationStats = new LinkedList<>();
        
        // Initialize statistics for all job postings
        for (JobPosting job : jobPostings) {
            applicationStats.add(new ApplicationStatistic(job.getTitle(), job.getId(), 0));
        }
        
        // Count applications for each job
        for (JobApplication application : jobApplications) {
            JobPosting job = application.getJobPosting();
            for (ApplicationStatistic stat : applicationStats) {
                if (stat.getJobId().equals(job.getId())) {
                    stat.count++;
                    break;
                }
            }
        }
        
        return applicationStats;
    }
    
    // Inner class for salary statistics - make it public static
    public static class SalaryStatistic {
        private String range;
        private int count;
        
        public SalaryStatistic(String range, int count) {
            this.range = range;
            this.count = count;
        }
        
        public String getRange() {
            return range;
        }
        
        public int getCount() {
            return count;
        }
    }
    
    // Inner class for month range summary report data
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
    
    // Method to generate month range summary report
    public List<MonthRangeSummaryData> generateMonthRangeSummaryReport(int startMonth, int startYear, int endMonth, int endYear) {
        List<MonthRangeSummaryData> summaryReport = new LinkedList<>();
        
        // Define date range
        LocalDate startDate = LocalDate.of(startYear, startMonth, 1);
        LocalDate endDate = YearMonth.of(endYear, endMonth).atEndOfMonth();
        
        // Filter applications in the date range
        List<JobApplication> filteredApplications = jobApplications.stream()
                .filter(app -> !app.getApplicationDate().isBefore(startDate) && !app.getApplicationDate().isAfter(endDate))
                .collect(Collectors.toList());
        
        // Group by job and date
        Map<JobPosting, Map<LocalDate, Long>> jobDateCounts = filteredApplications.stream()
                .collect(Collectors.groupingBy(
                    JobApplication::getJobPosting,
                    Collectors.groupingBy(JobApplication::getApplicationDate, Collectors.counting())
                ));
        
        // Convert to report data format
        for (Map.Entry<JobPosting, Map<LocalDate, Long>> jobEntry : jobDateCounts.entrySet()) {
            JobPosting job = jobEntry.getKey();
            
            for (Map.Entry<LocalDate, Long> dateEntry : jobEntry.getValue().entrySet()) {
                summaryReport.add(new MonthRangeSummaryData(
                    job.getTitle(),
                    job.getCompany().getName(),
                    dateEntry.getKey(),
                    dateEntry.getValue().intValue()
                ));
            }
        }
        
        // Sort by date
        summaryReport.sort(Comparator.comparing(MonthRangeSummaryData::getAppliedDate));
        
        return summaryReport;
    }

    // Generate salary range statistics
    private List<SalaryStatistic> generateSalaryStats() {
        List<SalaryStatistic> salaryStats = new LinkedList<>();
        
        // Define salary ranges
        int[] ranges = {0, 3000, 5000, 7000, 9000, 11000, Integer.MAX_VALUE};
        String[] labels = {"< 3,000", "3,000-4,999", "5,000-6,999", "7,000-8,999", "9,000-10,999", "> 11,000"};
        
        // Initialize statistics for all salary ranges
        for (String label : labels) {
            salaryStats.add(new SalaryStatistic(label, 0));
        }
        
        // Count jobs by salary range
        for (JobPosting job : jobPostings) {
            int minSalary = job.getSalaryMin();
            for (int i = 0; i < ranges.length - 1; i++) {
                if (minSalary >= ranges[i] && minSalary < ranges[i + 1]) {
                    salaryStats.get(i).count++;
                    break;
                }
            }
        }
        
        return salaryStats;
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

        LocalDate createdAt = LocalDate.now(), updatedAt = LocalDate.now();
        return new JobPosting(title, company, salaryMin, salaryMax, description, type, null, JobPosting.Status.OPEN, createdAt, updatedAt);
    }

    public void updateJobTitle(String id) {
        String fieldName = "Job Title";
        JobPosting jobPosting = BaseEntity.getById(id, jobPostings);
        if (jobPosting == null) {
            return;
        }

        jobPostingUI.printUpdateMessage(fieldName);
        String newJobTitle = jobPostingUI.getJobPostingTitle();
        if (newJobTitle.equals(Input.STRING_EXIT_VALUE)) {
            return;
        }
        jobPosting.setTitle(newJobTitle);
        jobPosting.setUpdatedAt(LocalDate.now());
        jobPostingUI.printUpdateSuccessMessage(jobPosting, fieldName);

    }

    public void updateJobCompany(String id) {
        String fieldName = "Job Company";
        JobPosting jobPosting = BaseEntity.getById(id, jobPostings);
        if (jobPosting == null) {
            return;
        }

        jobPostingUI.printUpdateMessage(fieldName);
        Company newCompany = jobPostingUI.getJobPostingCompany();
        if (newCompany == null) {
            return;
        }
        jobPosting.setCompany(newCompany);
        jobPosting.setUpdatedAt(LocalDate.now());
        jobPostingUI.printUpdateSuccessMessage(jobPosting, fieldName);
    }

    public void updateSalaryRange(String id) {
        String fieldName = "Salary Range";
        JobPosting jobPosting = BaseEntity.getById(id, jobPostings);
        if (jobPosting == null) {
            return;
        }

        jobPostingUI.printUpdateMessage(fieldName);
        String salaryRange = jobPostingUI.getSalaryRange();
        if (salaryRange.equals(Input.STRING_EXIT_VALUE)) {
            return;
        }
        String[] parts = salaryRange.split("-");
        int newMinSalary = Integer.parseInt(parts[0]);
        int newMaxSalary = Integer.parseInt(parts[1]);

        jobPosting.setSalaryMin(newMinSalary);
        jobPosting.setSalaryMax(newMaxSalary);
        jobPosting.setUpdatedAt(LocalDate.now());
        jobPostingUI.printUpdateSuccessMessage(jobPosting, fieldName);
    }

    public void updateDescription(String id) {
        String fieldName = "Description";
        JobPosting jobPosting = BaseEntity.getById(id, jobPostings);
        if (jobPosting == null) {
            return;
        }

        jobPostingUI.printUpdateMessage(fieldName);

        String newDescription = jobPostingUI.getJobPostingDescription();
        if (newDescription.equals(Input.STRING_EXIT_VALUE)) {
            return;
        }

        jobPosting.setDescription(newDescription);
        jobPosting.setUpdatedAt(LocalDate.now());

        jobPostingUI.printUpdateSuccessMessage(jobPosting, fieldName);
    }

    public void updateJobType(String id) {
        String fieldName = "Job Type";
        JobPosting jobPosting = BaseEntity.getById(id, jobPostings);
        if (jobPosting == null) {
            return;
        }

        jobPostingUI.printUpdateMessage(fieldName);

        JobPosting.Type newType = jobPostingUI.getJobPostingType();
        if (newType == null) {
            return;
        }
        jobPosting.setType(newType);
        jobPosting.setUpdatedAt(LocalDate.now());

        jobPostingUI.printUpdateSuccessMessage(jobPosting, fieldName);
    }

    public void updateStatus(String id) {
        String fieldName = "Status";
        JobPosting jobPosting = BaseEntity.getById(id, jobPostings);
        if (jobPosting == null) {
            return;
        }

        jobPostingUI.printUpdateMessage(fieldName);

        JobPosting.Status newStatus = jobPostingUI.getJobPostingStatus();
        if (newStatus == null) {
            return;
        }
        jobPosting.setStatus(newStatus);
        jobPosting.setUpdatedAt(LocalDate.now());

        jobPostingUI.printUpdateSuccessMessage(jobPosting, fieldName);
    }

    public void updateAllField(String id) {
        final String fieldName = "All Fields";
        JobPosting jobPosting = BaseEntity.getById(id, jobPostings);
        jobPostingUI.printUpdateMessage(fieldName);

        String newTitle = jobPostingUI.getJobPostingTitle();
        if (newTitle.equals(Input.STRING_EXIT_VALUE)) return;

        Company newCompany = null;
        if (Context.isAdmin())
            newCompany = jobPostingUI.getJobPostingCompany();

        String salaryRange = jobPostingUI.getSalaryRange();
        if (salaryRange.equals(Input.STRING_EXIT_VALUE)) return;
        String[] parts = salaryRange.split("-");
        int newMinSalary = Integer.parseInt(parts[0]);
        int newMaxSalary = Integer.parseInt(parts[1]);

        String newDescription = jobPostingUI.getJobPostingDescription();
        if (newDescription.equals(Input.STRING_EXIT_VALUE)) return;

        JobPosting.Type newType = jobPostingUI.getJobPostingType();
        if (newType == null) return;

        JobPosting.Status newStatus = jobPostingUI.getJobPostingStatus();
        if (newStatus == null) return;

        jobPosting.setTitle(newTitle);
        if (newCompany != null) jobPosting.setCompany(newCompany);
        jobPosting.setSalaryMin(newMinSalary);
        jobPosting.setSalaryMax(newMaxSalary);
        jobPosting.setDescription(newDescription);
        jobPosting.setType(newType);
        jobPosting.setStatus(newStatus);
        jobPosting.setUpdatedAt(LocalDate.now());
        jobPostingUI.printUpdateSuccessMessage(jobPosting, "All Fields");
    }

    public void applyJob() {
        System.out.println("\n=== Apply for a Job Posting ===");

        currentApplicant = Context.getApplicant();

        if (currentApplicant == null) {
            System.out.println("No applicant logged in. Please log in to apply.");
            return;
        }

        if (jobPostings.isEmpty()) {
            System.out.println("No job postings available.");
            return;
        }

        Input input = new Input();

        while (true) {
            System.out.println("Available Job Postings:");
            jobPostings.forEach(jp -> System.out.printf(" [%s] %s - %s\n",
                    jp.getId(), jp.getTitle(), jp.getCompany().getName()));

            List<String> validJobIds = jobPostings.stream()
                    .map(JobPosting::getId)
                    .collect(Collectors.toList());

            StringCondition jobIdCondition = ConditionFactory.string()
                    .min(1, "Job ID cannot be empty")
                    .enumeration(validJobIds, "Please enter a valid Job ID from the list above (or 'x' to cancel)");

            String jobId = input.getString("Enter Job ID to apply (or 'x' to cancel): ", jobIdCondition);
            if (jobId.equals(Input.STRING_EXIT_VALUE)) {
                System.out.println("Application process cancelled.");
                return;
            }

            JobPosting selectedJob = BaseEntity.getById(jobId, jobPostings);
            if (selectedJob == null) {
                System.out.println("Invalid Job ID.");
                continue;
            }

            if (selectedJob.getStatus() != JobPosting.Status.OPEN) {
                System.out.println("Sorry, this job is not open for applications.");
                continue;
            }

            boolean alreadyApplied = jobApplications.stream()
                    .anyMatch(ja -> ja.getApplicant().equals(currentApplicant) && ja.getJobPosting().equals(selectedJob));
            if (alreadyApplied) {
                System.out.println("You have already applied for this job.");
                continue;
            }

            boolean confirmed = input.confirm("Are you sure you want to apply for '" + selectedJob.getTitle() + "'? (y/x): ");
            if (!confirmed) {
                System.out.println("Application cancelled.");
                continue;
            }

            JobApplication jobApplication = new JobApplication(
                    selectedJob,
                    currentApplicant,
                    JobApplication.Status.PENDING,
                    LocalDate.now()
            );
            jobApplications.add(jobApplication);
            System.out.println("Successfully applied for '" + selectedJob.getTitle() + "'!");

            boolean applyAnother = input.confirm("Do you want to apply for another job? (y/x): ");
            if (!applyAnother) {
                System.out.println("Returning to menu...");
                return;
            }
            System.out.println("\n=== Apply for Another Job Posting ===");
        }
    }

    public void displayJobApplication() {
        System.out.println("\n=== Display Job Applications ===");

        if (currentApplicant == null) {
            System.out.println("No applicant logged in. Please log in to view applications.");
            return;
        }

        if (jobApplications.isEmpty()) {
            System.out.println("No job applications found in the system.");
            return;
        }

        Input input = new Input();

        List<JobApplication> applicantApplications = jobApplications.stream()
                .filter(ja -> ja.getApplicant().equals(currentApplicant))
                .collect(Collectors.toList());

        if (applicantApplications.isEmpty()) {
            System.out.println("You have not applied for any jobs yet.");
            return;
        }

        System.out.println("Your Job Applications:");
        applicantApplications.forEach(ja -> System.out.printf(" [%s] %s - %s | Status: %s | Applied: %s\n",
                ja.getId(), ja.getJobPosting().getTitle(), ja.getJobPosting().getCompany().getName(),
                ja.getStatus(), ja.getApplicationDate()));

        input.clickAnythingToContinue();
    }

    public void withdrawJobApplication() {
        System.out.println("\n=== Withdraw Job Application ===");

        if (currentApplicant == null) {
            System.out.println("No applicant logged in. Please log in to withdraw applications.");
            return;
        }

        if (jobApplications.isEmpty()) {
            System.out.println("No job applications found in the system.");
            return;
        }

        Input input = new Input();

        List<JobApplication> withdrawableApplications = jobApplications.stream()
                .filter(ja -> ja.getApplicant().equals(currentApplicant) &&
                        ja.getStatus() != JobApplication.Status.WITHDRAWN &&
                        ja.getStatus() != JobApplication.Status.ACCEPTED &&
                        ja.getStatus() != JobApplication.Status.REJECTED)
                .collect(Collectors.toList());

        if (withdrawableApplications.isEmpty()) {
            System.out.println("No applications available to withdraw.");
            return;
        }

        System.out.println("Your Withdrawable Applications:");
        withdrawableApplications.forEach(ja -> System.out.printf(" [%s] %s - %s | Status: %s | Applied: %s\n",
                ja.getId(), ja.getJobPosting().getTitle(), ja.getJobPosting().getCompany().getName(),
                ja.getStatus(), ja.getApplicationDate()));

        List<String> validApplicationIds = withdrawableApplications.stream()
                .map(JobApplication::getId)
                .collect(Collectors.toList());

        StringCondition applicationIdCondition = ConditionFactory.string()
                .min(1, "Application ID cannot be empty")
                .enumeration(validApplicationIds, "Please enter a valid Application ID from the list above (or 'x' to cancel)");

        String applicationId = input.getString("Enter Application ID to withdraw (or 'x' to cancel): ", applicationIdCondition);
        if (applicationId.equals(Input.STRING_EXIT_VALUE)) {
            System.out.println("Withdrawal process cancelled.");
            return;
        }

        JobApplication selectedApplication = BaseEntity.getById(applicationId, jobApplications);
        if (selectedApplication == null || !selectedApplication.getApplicant().equals(currentApplicant)) {
            System.out.println("Invalid Application ID or you don't have permission to withdraw this application.");
            return;
        }

        if (selectedApplication.getStatus() == JobApplication.Status.WITHDRAWN ||
                selectedApplication.getStatus() == JobApplication.Status.ACCEPTED ||
                selectedApplication.getStatus() == JobApplication.Status.REJECTED) {
            System.out.println("This application cannot be withdrawn due to its current status.");
            return;
        }

        boolean confirmed = input.confirm("Are you sure you want to withdraw application '" +
                selectedApplication.getJobPosting().getTitle() + "'? (y/x): ");
        if (!confirmed) {
            System.out.println("Withdrawal cancelled.");
            return;
        }

        selectedApplication.setStatus(JobApplication.Status.WITHDRAWN);
        selectedApplication.setApplicationDate(LocalDate.now());
        System.out.println("Application '" + selectedApplication.getJobPosting().getTitle() + "' successfully withdrawn!");
    }
}