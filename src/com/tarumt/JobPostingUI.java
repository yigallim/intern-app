package com.tarumt;

import com.tarumt.control.JobPostingController;
import java.util.Scanner;
import com.tarumt.entity.JobPosting;
import com.tarumt.entity.Company;
import com.tarumt.entity.Location;
import com.tarumt.entity.Qualification;
import com.tarumt.adt.DoublyLinkedList;
import java.util.Date;
/**
 *
 * @author mingzhe
 */
public class JobPostingUI {
    
    private JobPostingController ctrl;
    private Scanner sc;
    
    public JobPostingUI() {
        ctrl = new JobPostingController();
        sc = new Scanner(System.in);
    }
    
    public void start() {
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    createJobPosting();
                    break;
                case 2:
                    readJobPosting();
                    break;
                case 3:
                    updateJobPosting();
                    break;
                case 4:
                    deleteJobPosting();
                    break;
                case 5:
                    listAllJobPostings();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n=== Job Posting Management System ===");
        System.out.println("1. Create a Job Posting");
        System.out.println("2. Read a Job Posting");
        System.out.println("3. Update a Job Posting");
        System.out.println("4. Delete a Job Posting");
        System.out.println("5. List All Job Postings");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void createJobPosting() {
        System.out.println("\nEnter Job Details:");
        System.out.print("Job ID: ");
        String jobId = sc.nextLine();
        System.out.print("Job Title (e.g., ACCOUNTING, IT, etc.): ");
        JobPosting.JobTitle title = JobPosting.JobTitle.valueOf(sc.nextLine().toUpperCase());
        
        // Simplified Company creation (you can expand this)
        System.out.print("Company Name: ");
        String companyName = sc.nextLine();
        Company company = new Company("C" + jobId, companyName, "Company Description", new Location(), "email@example.com", "123-456-7890");
        
        System.out.print("Salary Min: ");
        double salaryMin = Double.parseDouble(sc.nextLine());
        System.out.print("Salary Max: ");
        double salaryMax = Double.parseDouble(sc.nextLine());
        System.out.print("Description: ");
        String description = sc.nextLine();

        // Simplified Qualifications (you can expand this)
        DoublyLinkedList<Qualification> qualifications = new DoublyLinkedList<>();
        qualifications.add(new Qualification("Q1", "Java Developer", 2, 3, Qualification.Level.INTERMEDIATE));

        System.out.print("Posting Date (yyyy-MM-dd): ");
        Date postingDate = new Date(sc.nextLine()); // Simplified date parsing (use a proper date parser in production)
        System.out.print("Closing Date (yyyy-MM-dd): ");
        Date closingDate = new Date(sc.nextLine());
        System.out.print("Status (OPEN, CLOSED, FILLED): ");
        JobPosting.Status status = JobPosting.Status.valueOf(sc.nextLine().toUpperCase());

        if (ctrl.createJobPosting(jobId, title, company, salaryMin, salaryMax, description, qualifications, postingDate, closingDate, status)) {
            System.out.println("Job posting created successfully!");
        } else {
            System.out.println("Failed to create job posting.");
        }
    }

    private void readJobPosting() {
        System.out.print("\nEnter Job ID to read: ");
        String jobId = sc.nextLine();
        JobPosting job = ctrl.readJobPosting(jobId);
        if (job != null) {
            System.out.println("Job Details: " + job);
        } else {
            System.out.println("Job not found.");
        }
    }

    private void updateJobPosting() {
        System.out.print("\nEnter Job ID to update: ");
        String jobId = sc.nextLine();
        System.out.print("New Job Title (e.g., ACCOUNTING, IT, etc.): ");
        JobPosting.JobTitle title = JobPosting.JobTitle.valueOf(sc.nextLine().toUpperCase());
        
        // Simplified Company update (you can expand this)
        System.out.print("New Company Name: ");
        String companyName = sc.nextLine();
        Company company = new Company("C" + jobId, companyName, "Updated Company Description", new Location(), "email@example.com", "123-456-7890");
        
        System.out.print("New Salary Min: ");
        double salaryMin = Double.parseDouble(sc.nextLine());
        System.out.print("New Salary Max: ");
        double salaryMax = Double.parseDouble(sc.nextLine());
        System.out.print("New Description: ");
        String description = sc.nextLine();

        // Simplified Qualifications (you can expand this)
        DoublyLinkedList<Qualification> qualifications = new DoublyLinkedList<>();
        qualifications.add(new Qualification("Q1", "Java Developer", 2, 3, Qualification.Level.INTERMEDIATE));

        System.out.print("New Posting Date (yyyy-MM-dd): ");
        Date postingDate = new Date(sc.nextLine()); // Simplified date parsing
        System.out.print("New Closing Date (yyyy-MM-dd): ");
        Date closingDate = new Date(sc.nextLine());
        System.out.print("New Status (OPEN, CLOSED, FILLED): ");
        JobPosting.Status status = JobPosting.Status.valueOf(sc.nextLine().toUpperCase());

        if (ctrl.updateJobPosting(jobId, title, company, salaryMin, salaryMax, description, qualifications, postingDate, closingDate, status)) {
            System.out.println("Job posting updated successfully!");
        } else {
            System.out.println("Job not found or update failed.");
        }
    }

    private void deleteJobPosting() {
        System.out.print("\nEnter Job ID to delete: ");
        String jobId = sc.nextLine();
        if (ctrl.deleteJobPosting(jobId)) {
            System.out.println("Job posting deleted successfully!");
        } else {
            System.out.println("Job not found or deletion failed.");
        }
    }

    private void listAllJobPostings() {
        System.out.println("\nAll Job Postings:");
        DoublyLinkedList<JobPosting> jobs = ctrl.getAllJobPostings();
        if (jobs.isEmpty()) {
            System.out.println("No job postings available.");
        } else {
            for (JobPosting job : jobs) {
                System.out.println(job);
            }
        }
    }
}
