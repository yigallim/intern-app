package com.tarumt.test;

import com.tarumt.adt.set.HashSet;
import com.tarumt.entity.Company;
import com.tarumt.entity.JobPosting;
import com.tarumt.entity.location.City;
import com.tarumt.entity.location.Location;
import com.tarumt.utility.pretty.TabularPrint;

import java.time.LocalDate;

import com.tarumt.adt.list.List;
import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.entity.qualification.EducationLevel;
import com.tarumt.entity.qualification.LanguageProficiency;
import com.tarumt.entity.qualification.Qualification;
import com.tarumt.entity.qualification.Skill;
import com.tarumt.entity.qualification.WorkExperience;

public class ColorTest {

    // ANSI escape codes for colors
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    private static final List<JobPosting> jobPostings = new DoublyLinkedList<>();

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
        EducationLevel edu1 = new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.CS, EducationLevel.University.UM, 3.7, false, Qualification.Importance.HIGH);
        EducationLevel edu2 = new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.DS, EducationLevel.University.UKM, 3.9, false, Qualification.Importance.HIGH);
        EducationLevel edu3 = new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.MKT, EducationLevel.University.APU, 3.2, false, Qualification.Importance.MEDIUM);
        EducationLevel edu4 = new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.SE, EducationLevel.University.USM, 3.5, false, Qualification.Importance.HIGH);
        EducationLevel edu5 = new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.HOSP, EducationLevel.University.TAYLORS, 3.0, false, Qualification.Importance.LOW);

        List<WorkExperience> workExp1 = new DoublyLinkedList<>();
        workExp1.add(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 3, false, Qualification.Importance.MEDIUM));

        List<WorkExperience> workExp2 = new DoublyLinkedList<>();
        workExp2.add(new WorkExperience(WorkExperience.Industry.MARKETING_COMM, 2, false, Qualification.Importance.MEDIUM));

        List<WorkExperience> workExp3 = new DoublyLinkedList<>();
        workExp3.add(new WorkExperience(WorkExperience.Industry.ENGINEERING, 5, false, Qualification.Importance.HIGH));

        List<WorkExperience> workExp4 = new DoublyLinkedList<>();
        workExp4.add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 1, false, Qualification.Importance.LOW));

        List<WorkExperience> workExp5 = new DoublyLinkedList<>();
        workExp5.add(new WorkExperience(WorkExperience.Industry.SALES, 4, false, Qualification.Importance.MEDIUM));
        List<LanguageProficiency> langs1 = new DoublyLinkedList<>();
        langs1.add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.NATIVE, false, Qualification.Importance.LOW));

        List<LanguageProficiency> langs2 = new DoublyLinkedList<>();
        langs2.add(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.FLUENT, false, Qualification.Importance.MEDIUM));

        List<LanguageProficiency> langs3 = new DoublyLinkedList<>();
        langs3.add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.NATIVE, false, Qualification.Importance.LOW));

        List<LanguageProficiency> langs4 = new DoublyLinkedList<>();
        langs4.add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.INTERMEDIATE, false, Qualification.Importance.MEDIUM));

        List<LanguageProficiency> langs5 = new DoublyLinkedList<>();
        langs5.add(new LanguageProficiency(LanguageProficiency.Language.TM, LanguageProficiency.Proficiency.ELEMENTARY, false, Qualification.Importance.LOW));
        List<Skill> skills1 = new DoublyLinkedList<>();
        skills1.add(new Skill(JobPosting.Type.IT_COMM_TEC, "Java Programming", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.MEDIUM));

        List<Skill> skills2 = new DoublyLinkedList<>();
        skills2.add(new Skill(JobPosting.Type.MARKETING_COMM, "Content Creation", Skill.ProficiencyLevel.INTERMEDIATE, false, Qualification.Importance.MEDIUM));

        List<Skill> skills3 = new DoublyLinkedList<>();
        skills3.add(new Skill(JobPosting.Type.ENGINEERING, "CAD Drawing", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH));

        List<Skill> skills4 = new DoublyLinkedList<>();
        skills4.add(new Skill(JobPosting.Type.HEALTH_MED, "First Aid", Skill.ProficiencyLevel.EXPERT, false, Qualification.Importance.LOW));

        List<Skill> skills5 = new DoublyLinkedList<>();
        skills5.add(new Skill(JobPosting.Type.SALES, "Negotiation", Skill.ProficiencyLevel.INTERMEDIATE, false, Qualification.Importance.MEDIUM));

        // Mock Job Postings
//        jobPostings.add(new JobPosting("Software Engineer", company2, 5000, 7000, "Develop and maintain web applications.", JobPosting.Type.IT_COMM_TEC, new DoublyLinkedList<>(), JobPosting.Status.OPEN, LocalDate.now(), LocalDate.now()));
//        jobPostings.add(new JobPosting("Marketing Executive", company1, 4000, 5500, "Manage marketing campaigns and social media.", JobPosting.Type.MARKETING_COMM, new DoublyLinkedList<>(), JobPosting.Status.OPEN, LocalDate.now(), LocalDate.now()));
//        jobPostings.add(new JobPosting("Data Analyst", company2, 4500, 6000, "Analyze business data and generate insights.", JobPosting.Type.IT_COMM_TEC, new DoublyLinkedList<>(), JobPosting.Status.OPEN, LocalDate.now(), LocalDate.now()));
//        jobPostings.add(new JobPosting("Financial Analyst", company3, 6000, 8000, "Analyze financial data and create reports.", JobPosting.Type.BANK_FIN_SERV, new DoublyLinkedList<>(), JobPosting.Status.OPEN, LocalDate.now(), LocalDate.now()));
//        jobPostings.add(new JobPosting("HR Manager", company1, 7000, 9000, "Manage recruitment and employee relations.", JobPosting.Type.HR_RECRUIT, new DoublyLinkedList<>(), JobPosting.Status.OPEN, LocalDate.now(), LocalDate.now()));
//        jobPostings.add(new JobPosting("Electrical Engineer", company3, 5500, 7500, "Design electrical systems and ensure compliance.", JobPosting.Type.ENGINEERING, new DoublyLinkedList<>(), JobPosting.Status.OPEN, LocalDate.now(), LocalDate.now()));
//        jobPostings.add(new JobPosting("Graphic Designer", company2, 3500, 5000, "Create visual content for branding and marketing.", JobPosting.Type.OTHER, new DoublyLinkedList<>(), JobPosting.Status.OPEN, LocalDate.now(), LocalDate.now()));
//        jobPostings.add(new JobPosting("Sales Manager", company1, 5000, 7500, "Lead sales team and drive revenue growth.", JobPosting.Type.SALES, new DoublyLinkedList<>(), JobPosting.Status.OPEN, LocalDate.now(), LocalDate.now()));
//        jobPostings.add(new JobPosting("UX Designer", company2, 4500, 6500, "Design user experiences for digital products.", JobPosting.Type.IT_COMM_TEC, new DoublyLinkedList<>(), JobPosting.Status.OPEN, LocalDate.now(), LocalDate.now()));
//        jobPostings.add(new JobPosting("Environmental Scientist", company3, 4800, 6800, "Conduct environmental research and analysis.", JobPosting.Type.OTHER, new DoublyLinkedList<>(), JobPosting.Status.OPEN, LocalDate.now(), LocalDate.now()));
        jobPostings.add(new JobPosting("Sales Associate", company2, 4017, 7488, "Design cloud system architectures and pipelines.", JobPosting.Type.SALES, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
        jobPostings.add(new JobPosting("Marketing Executive", company1, 4593, 5989, "Design intuitive user experiences for products.", JobPosting.Type.CALL_CUST_SVC, edu2, workExp2, langs2, skills2, JobPosting.Status.OPEN, LocalDate.now().minusDays(8), LocalDate.now()));
        jobPostings.add(new JobPosting("Cloud Architect", company2, 5084, 8083, "Design intuitive user experiences for products.", JobPosting.Type.HR_RECRUIT, edu3, workExp3, langs3, skills3, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
        jobPostings.add(new JobPosting("Cybersecurity Specialist", company3, 3846, 5865, "Ensure the security of digital infrastructure.", JobPosting.Type.DESIGN_ARCH, edu5, workExp5, langs5, skills5, JobPosting.Status.OPEN, LocalDate.now().minusDays(8), LocalDate.now()));
        jobPostings.add(new JobPosting("Mechanical Engineer", company2, 5458, 7926, "Work on circuits and power systems.", JobPosting.Type.SALES, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
        jobPostings.add(new JobPosting("Sales Associate", company2, 3502, 4981, "Design intuitive user experiences for products.", JobPosting.Type.CALL_CUST_SVC, edu2, workExp2, langs2, skills2, JobPosting.Status.OPEN, LocalDate.now().minusDays(10), LocalDate.now()));
        jobPostings.add(new JobPosting("Mechanical Engineer", company1, 4006, 5475, "Design intuitive user experiences for products.", JobPosting.Type.IT_COMM_TEC, edu2, workExp2, langs2, skills2, JobPosting.Status.OPEN, LocalDate.now().minusDays(6), LocalDate.now()));
        jobPostings.add(new JobPosting("Electrical Engineer", company2, 5422, 7230, "Research and develop AI technologies.", JobPosting.Type.SALES, edu3, workExp3, langs3, skills3, JobPosting.Status.OPEN, LocalDate.now().minusDays(9), LocalDate.now()));
        jobPostings.add(new JobPosting("HR Manager", company2, 3173, 6741, "Design and improve mechanical products.", JobPosting.Type.ENGINEERING, edu3, workExp3, langs3, skills3, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
        jobPostings.add(new JobPosting("Customer Support Rep", company2, 4407, 6536, "Design cloud system architectures and pipelines.", JobPosting.Type.MARKETING_COMM, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(6), LocalDate.now()));
        jobPostings.add(new JobPosting("AI Researcher", company3, 4837, 5868, "Design intuitive user experiences for products.", JobPosting.Type.MARKETING_COMM, edu5, workExp5, langs5, skills5, JobPosting.Status.OPEN, LocalDate.now().minusDays(8), LocalDate.now()));

        // Print job postings in a table format
        HashSet<String> set = new HashSet<>();
        set.add("sales");
        TabularPrint.printTabular(jobPostings, true, set, "default");
    }
}
