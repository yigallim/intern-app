
package com.tarumt.control;

import com.tarumt.boundary.JobPostingUI;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.BaseEntity;
import com.tarumt.entity.Company;
import com.tarumt.entity.JobPosting;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.search.FuzzySearch;

import java.time.LocalDate;

import java.util.LinkedList;
import java.util.List;

public class JobPostingService implements Service {

    private List<JobPosting> jobPostings = new LinkedList<>();
    private final JobPostingUI jobPostingUI;

    public JobPostingService() {
        Input input = new Input();
        this.jobPostings = Initializer.getJobPostings();
        this.jobPostingUI = new JobPostingUI(input);
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
            if (job == null) return;
            jobPostings.add(job);
            jobPostingUI.printSuccessCreateJobPostingMsg();
            if (!jobPostingUI.continueToCreateJobPosting()) return;
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
            if (this.jobPostings.isEmpty()) return;
            String query = jobPostingUI.getSearchJobPostingQuery();
            if (query.equals(Input.STRING_EXIT_VALUE)) return;
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
        if (this.jobPostings.isEmpty()) return;

        List<String> ids = BaseEntity.getIds(jobPostings);
        String id = jobPostingUI.getJobPostingId("| Select Job Posting ID => ", ids);
        if (id.equals(Input.STRING_EXIT_VALUE)) return;

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
        if (index == Input.INT_EXIT_VALUE) return;
        if (jobPostingUI.confirmDelete()) {
            JobPosting jobPosting = jobPostings.remove(index - 1);
            jobPostingUI.printSuccessDeleteMsg(jobPosting.getId());
        }
    }

    public void deleteByRange() {
        jobPostingUI.printDeleteByRangeMsg();
        int startIndex = jobPostingUI.getDeleteStartIndex(this.jobPostings.size());
        if (startIndex == Input.INT_EXIT_VALUE) return;

        int endIndex = jobPostingUI.getDeleteEndIndex(startIndex, this.jobPostings.size());
        if (endIndex == Input.INT_EXIT_VALUE) return;

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
        if (id.equals(Input.STRING_EXIT_VALUE)) return;

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
        if (title.equals(Input.STRING_EXIT_VALUE)) return null;

        Company company;

        if (Context.isEmployer()) {
            company = Context.getCompany();
        } else {
            company = jobPostingUI.getJobPostingCompany();
        }
        if (company == null) return null;

        String salaryRange = jobPostingUI.getSalaryRange();
        if (salaryRange.equals(Input.STRING_EXIT_VALUE)) return null;
        String[] parts = salaryRange.split("-");
        int salaryMin = Integer.parseInt(parts[0]);
        int salaryMax = Integer.parseInt(parts[1]);

        String description = jobPostingUI.getJobPostingDescription();
        if (description.equals(Input.STRING_EXIT_VALUE)) return null;

        JobPosting.Type type = jobPostingUI.getJobPostingType();
        if (type == null) return null;

        LocalDate createdAt = LocalDate.now(), updatedAt = LocalDate.now();
        return new JobPosting(title, company, salaryMin, salaryMax, description, type, null, JobPosting.Status.OPEN, createdAt, updatedAt);
    }

    public void updateJobTitle(String id) {
    }

    public void updateSalaryRange(String id) {
    }

    public void updateDescription(String id) {
    }

    public void updateJobType(String id) {
    }

    public void updateStatus(String id) {
    }

    public void updateAllField(String id) {
    }

}
