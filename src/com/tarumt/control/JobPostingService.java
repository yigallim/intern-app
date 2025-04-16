package com.tarumt.control;

import com.tarumt.boundary.ApplicantUI;
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
import com.tarumt.utility.common.Strings;
import com.tarumt.utility.validation.ConditionFactory;
import com.tarumt.utility.validation.StringCondition;

import java.time.LocalDate;

import java.util.LinkedList; //
import java.util.List; //

public class JobPostingService implements Service {

    private List<JobPosting> jobPostings = new LinkedList<>();
    private final JobPostingUI jobPostingUI;
    private Applicant currentApplicant;
    private final List<JobApplication> jobApplications;

    public JobPostingService() {
        Input input = new Input();
        this.jobPostings = Initializer.getJobPostings();
        this.jobPostingUI = new JobPostingUI(input);
        this.jobApplications = Initializer.getJobApplication(); 
   
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
        Log.na();
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
        Log.na();
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
            
            int columns = 3;
            int colWidth = 50;

            for (int i = 0; i < jobPostings.size(); i++) {
                JobPosting jp = jobPostings.get(i);

                String label = String.format("%-4d) %-5s", i + 1, jp.getId());
                String content = Strings.truncate(jp.getTitle() + " - " + jp.getCompany().getName(),
                                                  colWidth - label.length() - 1);

                String cell = String.format("| %s%s", label, content);
                System.out.printf("%-" + colWidth + "s", cell);

                if ((i + 1) % columns == 0 || i == jobPostings.size() - 1) {
                    System.out.println();
                }
            }          

            List<String> validJobIds = new LinkedList<>();
            for (JobPosting jp : jobPostings) {
                validJobIds.add(jp.getId());
            }

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

        List<JobApplication> applicantApplications = new LinkedList<>();
        for (JobApplication ja : jobApplications) {
            if (ja.getApplicant().equals(currentApplicant)) {
                applicantApplications.add(ja);
            }
        }

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

        List<JobApplication> withdrawableApplications = new LinkedList<>();
        for (JobApplication ja : jobApplications) {
            if (ja.getApplicant().equals(currentApplicant) &&
                ja.getStatus() != JobApplication.Status.WITHDRAWN &&
                ja.getStatus() != JobApplication.Status.ACCEPTED &&
                ja.getStatus() != JobApplication.Status.REJECTED) {
                withdrawableApplications.add(ja);
            }
        }

        if (withdrawableApplications.isEmpty()) {
            System.out.println("No applications available to withdraw.");
            return;
        }

        System.out.println("Your Withdrawable Applications:");
        withdrawableApplications.forEach(ja -> System.out.printf(" [%s] %s - %s | Status: %s | Applied: %s\n",
            ja.getId(), ja.getJobPosting().getTitle(), ja.getJobPosting().getCompany().getName(),
            ja.getStatus(), ja.getApplicationDate()));

        List<String> validApplicationIds = new LinkedList<>();
        for (JobApplication ja : withdrawableApplications) {
            validApplicationIds.add(ja.getId());
        }

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
