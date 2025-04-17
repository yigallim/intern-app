package com.tarumt.control;

import com.tarumt.boundary.JobPostingUI;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.*;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.search.FuzzySearch;

import java.time.LocalDateTime;

import com.tarumt.adt.list.ListInterface;
import com.tarumt.adt.list.DoublyLinkedList;

public class JobPostingService implements Service {

    private static JobPostingService instance;
    private ListInterface<JobPosting> jobPostings = new DoublyLinkedList<>();
    private final JobPostingUI jobPostingUI;

    private JobPostingService() {
        Input input = new Input();
        this.jobPostings = Initializer.getJobPostings();
        this.jobPostingUI = new JobPostingUI(input);
    }

    public static JobPostingService getInstance() {
        if (instance == null) {
            instance = new JobPostingService();
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

    @Override
    public void run() {
        this.jobPostingUI.menu();
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
        if (Context.isEmployer()) {
            this.jobPostingUI.printAllJobs(this.getEmployerJobPostings());
        } else {
            this.jobPostingUI.printAllJobs(this.jobPostings);
        }
    }

    @Override
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

    @Override
    public void filter() {
        Log.na();
    }

    @Override
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

    @Override
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

        LocalDateTime createdAt = Context.getDateTime(), updatedAt = Context.getDateTime();
        return new JobPosting(title, company, salaryMin, salaryMax, description, type, null, JobPosting.Status.OPEN,
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
}
