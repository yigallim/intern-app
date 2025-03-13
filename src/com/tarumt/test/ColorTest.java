package com.tarumt.test;

import com.tarumt.entity.Company;
import com.tarumt.entity.JobPosting;
import com.tarumt.entity.location.City;
import com.tarumt.entity.location.Location;
import com.tarumt.utility.pretty.TabularPrint;

import java.time.LocalDate;
import java.util.*;

public class ColorTest {
    // ANSI escape codes for colors
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    private static final List<JobPosting> jobPostings = new LinkedList<>();

    public static void main(String[] args) {
        System.out.println(RED + "This is red text" + RESET);
        System.out.println(GREEN + "This is green text" + RESET);
        System.out.println(YELLOW + "This is yellow text" + RESET);
        System.out.println(BLUE + "This is blue text" + RESET);
        System.out.println(PURPLE + "This is purple text" + RESET);
        System.out.println(CYAN + "This is cyan text" + RESET);

        // Mock Companies
        Company company1 = new Company("Chipotle", "A fast-casual chain offering Mexican food made with fresh ingredients.", new Location(City.KUALA_LUMPUR), "support@chipotle.com", "60183567890");
        Company company2 = new Company("TechNova", "An innovative software company.", new Location(City.PETALING_JAYA), "hr@technova.com", "60195551234");
        Company company3 = new Company("GreenEnergy", "A renewable energy company focused on sustainability.", new Location(City.PENANG_HILL), "contact@greenenergy.com", "60182233445");

        // Mock Job Postings
        jobPostings.add(new JobPosting("Software Engineer", company2, 5000, 7000, "Develop and maintain web applications.", JobPosting.Type.IT_COMM_TEC, new LinkedList<>(), JobPosting.Status.OPEN, LocalDate.now(), LocalDate.now()));
        jobPostings.add(new JobPosting("Marketing Executive", company1, 4000, 5500, "Manage marketing campaigns and social media.", JobPosting.Type.MARKETING_COMM, new LinkedList<>(), JobPosting.Status.OPEN, LocalDate.now(), LocalDate.now()));
        jobPostings.add(new JobPosting("Data Analyst", company2, 4500, 6000, "Analyze business data and generate insights.", JobPosting.Type.IT_COMM_TEC, new LinkedList<>(), JobPosting.Status.OPEN, LocalDate.now(), LocalDate.now()));
        jobPostings.add(new JobPosting("Financial Analyst", company3, 6000, 8000, "Analyze financial data and create reports.", JobPosting.Type.BANK_FIN_SERV, new LinkedList<>(), JobPosting.Status.OPEN, LocalDate.now(), LocalDate.now()));
        jobPostings.add(new JobPosting("HR Manager", company1, 7000, 9000, "Manage recruitment and employee relations.", JobPosting.Type.HR_RECRUIT, new LinkedList<>(), JobPosting.Status.OPEN, LocalDate.now(), LocalDate.now()));
        jobPostings.add(new JobPosting("Electrical Engineer", company3, 5500, 7500, "Design electrical systems and ensure compliance.", JobPosting.Type.ENGINEERING, new LinkedList<>(), JobPosting.Status.OPEN, LocalDate.now(), LocalDate.now()));
        jobPostings.add(new JobPosting("Graphic Designer", company2, 3500, 5000, "Create visual content for branding and marketing.", JobPosting.Type.OTHER, new LinkedList<>(), JobPosting.Status.OPEN, LocalDate.now(), LocalDate.now()));
        jobPostings.add(new JobPosting("Sales Manager", company1, 5000, 7500, "Lead sales team and drive revenue growth.", JobPosting.Type.SALES, new LinkedList<>(), JobPosting.Status.OPEN, LocalDate.now(), LocalDate.now()));
        jobPostings.add(new JobPosting("UX Designer", company2, 4500, 6500, "Design user experiences for digital products.", JobPosting.Type.IT_COMM_TEC, new LinkedList<>(), JobPosting.Status.OPEN, LocalDate.now(), LocalDate.now()));
        jobPostings.add(new JobPosting("Environmental Scientist", company3, 4800, 6800, "Conduct environmental research and analysis.", JobPosting.Type.OTHER, new LinkedList<>(), JobPosting.Status.OPEN, LocalDate.now(), LocalDate.now()));

        // Print job postings in a table format
        HashSet<String> set = new HashSet<>();
        set.add("sales");
        TabularPrint.printTabular(jobPostings, true, set, "default");
    }
}
