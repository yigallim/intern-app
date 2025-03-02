package com.tarumt;

import com.tarumt.adt.DoublyLinkedList;
import com.tarumt.entity.Qualification;
import com.tarumt.entity.ApplicantSkill;
import com.tarumt.entity.JobPosting;
import com.tarumt.entity.Company;
import com.tarumt.entity.Location;
import com.tarumt.control.JobPostingController;
import com.tarumt.util.Input;
import com.tarumt.util.Menu;
import com.tarumt.validation.ConditionFactory;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Yeoh Ming Zhe
 */
public class JobPostingUI {

    private final JobPostingController controller;
    private final Input input;

    public JobPostingUI() {
        this.controller = new JobPostingController();
        this.input = new Input(); // Default exit key "x"
    }

    public void start() {
        Menu menu = new Menu()
                .header("\n=== Job Posting Management System ===")
                .footer("Enter your choice or 'x' to exit.")
                .exit("Exit");

        // Add menu choices
        menu.choice(
                new Menu.Choice("Create a Job Posting", this::createJobPosting),
                new Menu.Choice("Read a Job Posting", this::readJobPosting),
                new Menu.Choice("Update a Job Posting", this::updateJobPosting),
                new Menu.Choice("Delete a Job Posting", this::deleteJobPosting),
                new Menu.Choice("List All Job Postings", this::listAllJobPostings)
        );

        // Run the menu
        menu.run();
    }

    private DoublyLinkedList<Qualification> createQualifications() {
        DoublyLinkedList<Qualification> qualifications = new DoublyLinkedList<>();
        Input localInput = input.withoutExitKey();

        while (true) {
            String addMore = localInput.getString("Do you want to add a requirement? (yes/no): ",
                    ConditionFactory.string().enumeration(new String[]{"yes", "no"}, "Please enter 'yes' or 'no'"));
            if ("no".equalsIgnoreCase(addMore)) {
                break;
            }

            String requirementType = localInput.getString("Enter requirement type (e.g., CGPA, Experience, Programming Language): ",
                    ConditionFactory.string().min(1, "Requirement type cannot be empty"));
            String requirementValue = localInput.getString("Enter requirement value (e.g., 3.5, 5, Java): ",
                    ConditionFactory.string().min(1, "Requirement value cannot be empty"));
            String isMandatoryStr = localInput.getString("Is this requirement mandatory? (yes/no): ",
                    ConditionFactory.string().enumeration(new String[]{"yes", "no"}, "Please enter 'yes' or 'no'"));
            boolean isMandatory = "yes".equalsIgnoreCase(isMandatoryStr);

            Qualification qualification = new Qualification(requirementType, requirementValue, isMandatory);
            qualifications.add(qualification);
        }
        return qualifications;
    }

    private void createJobPosting() {
        Input localInput = input.withoutExitKey(); // Disable exit key for sub-inputs

        System.out.println("\nEnter Job Details:");
        String jobId = localInput.getString("Job ID: ", ConditionFactory.string().min(1, "Job ID cannot be empty"));
        JobPosting.JobTitle title = localInput.getEnum("Job Title (e.g., IT, ACCOUNTING, etc.): ", JobPosting.JobTitle.class);

        // Simplified company creation
        String companyName = localInput.getString("Company Name: ", ConditionFactory.string().min(1, "Company name cannot be empty"));
        Company company = new Company("C" + jobId, companyName, "Company Description", new Location(), "email@example.com", "123-456-7890");

        double salaryMin = localInput.getDouble("Minimum Salary: ", ConditionFactory.decimal().min(0));
        double salaryMax = localInput.getDouble("Maximum Salary: ", ConditionFactory.decimal().min(salaryMin));
        String description = localInput.getString("Description: ", ConditionFactory.string().min(1, "Description cannot be empty"));

        DoublyLinkedList<Qualification> qualifications = createQualifications();

        LocalDate postingLocalDate = getValidLocalDate("Posting Date (yyyy-MM-dd): ", localInput);
        LocalDate closingLocalDate = getValidLocalDate("Closing Date (yyyy-MM-dd): ", localInput);

        JobPosting.Status status = localInput.getEnum("Status (OPEN, CLOSED, FILLED): ", JobPosting.Status.class);

        if (controller.createJobPosting(jobId, title, company, salaryMin, salaryMax, description,
                qualifications, postingLocalDate, closingLocalDate, status)) {
            System.out.println("Job posting created successfully!");
        } else {
            System.out.println("Failed to create job posting.");
        }
    }

    private LocalDate getValidLocalDate(String message, Input localInput) {
        while (true) {
            String dateStr = localInput.getString(message,
                    ConditionFactory.string().regex("\\d{4}-\\d{2}-\\d{2}", "Invalid date format. Use yyyy-MM-dd."));
            try {
                return LocalDate.parse(dateStr);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Please try again.");
            }
        }
    }

    private void readJobPosting() {
        Input localInput = input.withoutExitKey();
        String jobId = localInput.getString("Enter Job ID to read: ", ConditionFactory.string().min(1, "Job ID cannot be empty"));
        JobPosting job = controller.readJobPosting(jobId);
        if (job != null) {
            System.out.println("Job Details: " + job);
        } else {
            System.out.println("Job not found.");
        }
    }

    private void updateJobPosting() {
        Input localInput = input.withoutExitKey();
        String jobId = localInput.getString("Enter Job ID to update: ", ConditionFactory.string().min(1, "Job ID cannot be empty"));
        JobPosting.JobTitle title = localInput.getEnum("New Job Title (e.g., IT, ACCOUNTING, etc.): ", JobPosting.JobTitle.class);

        // Simplified company update
        String companyName = localInput.getString("New Company Name: ", ConditionFactory.string().min(1, "Company name cannot be empty"));
        Company company = new Company("C" + jobId, companyName, "Updated Company Description", new Location(), "email@example.com", "123-456-7890");

        double salaryMin = localInput.getDouble("New Minimum Salary: ", ConditionFactory.decimal().min(0));
        double salaryMax = localInput.getDouble("New Maximum Salary: ", ConditionFactory.decimal().min(salaryMin));
        String description = localInput.getString("New Description: ", ConditionFactory.string().min(1, "Description cannot be empty"));

        DoublyLinkedList<Qualification> qualifications = createQualifications();

        LocalDate postingLocalDate = getValidLocalDate("New Posting Date (yyyy-MM-dd): ", localInput);
        LocalDate closingLocalDate = getValidLocalDate("New Closing Date (yyyy-MM-dd): ", localInput);

        JobPosting.Status status = localInput.getEnum("New Status (OPEN, CLOSED, FILLED): ", JobPosting.Status.class);

        if (controller.updateJobPosting(jobId, title, company, salaryMin, salaryMax, description,
                qualifications, postingLocalDate, closingLocalDate, status)) {
            System.out.println("Job posting updated successfully!");
        } else {
            System.out.println("Job not found or update failed.");
        }
    }

    private void deleteJobPosting() {
        Input localInput = input.withoutExitKey();
        String jobId = localInput.getString("Enter Job ID to delete: ", ConditionFactory.string().min(1, "Job ID cannot be empty"));
        if (controller.deleteJobPosting(jobId)) {
            System.out.println("Job posting deleted successfully!");
        } else {
            System.out.println("Job not found or deletion failed.");
        }
    }

    private void listAllJobPostings() {
        System.out.println("\n=== All Job Postings ===");
        System.out.println("------------------------");
        DoublyLinkedList<JobPosting> jobs = controller.getAllJobPostings();
        if (jobs.isEmpty()) {
            System.out.println("No job postings available.");
        } else {
            int count = 1;
            for (JobPosting job : jobs) {
                System.out.println("Job Posting #" + count + ":\n");
                System.out.println(job);
                System.out.println("------------------------");
                count++;
            }
        }
    }

    

    
}
