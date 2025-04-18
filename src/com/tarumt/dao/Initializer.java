package com.tarumt.dao;

import com.tarumt.adt.Arrays;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.Company;
import com.tarumt.entity.JobApplication;
import com.tarumt.entity.JobPosting;
import com.tarumt.entity.location.City;
import com.tarumt.entity.location.Location;

import java.time.LocalDate;

import com.tarumt.adt.list.List;
import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.entity.qualification.EducationLevel;
import com.tarumt.entity.qualification.LanguageProficiency;
import com.tarumt.entity.qualification.Qualification;
import com.tarumt.entity.qualification.Skill;
import com.tarumt.entity.qualification.SkillOption;
import com.tarumt.entity.qualification.WorkExperience;

public class Initializer {

    private static final List<Company> companies = new DoublyLinkedList<>();
    private static final List<JobPosting> jobPostings = new DoublyLinkedList<>();
    private static final List<Applicant> applicants = new DoublyLinkedList<>();
    private static final List<JobApplication> jobApplications = new DoublyLinkedList<>();
    public static final List<SkillOption> list = new DoublyLinkedList<>();

    static {
        Initializer.init();
    }

    private static void init() {
        try {
            Class.forName("com.tarumt.entity.Applicant");
            Class.forName("com.tarumt.entity.Company");
            Class.forName("com.tarumt.entity.JobPosting");
            Class.forName("com.tarumt.entity.JobApplication");
        } catch (ClassNotFoundException e) {
            System.exit(0);
        }

        if (false) {
            return;
        }
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

        // region Init
        // 15 Companies (Hard-Coded, Adhering to Constraints, Using Malaysian Cities)
        companies.add(new Company("TechCorp", "Leading tech company focused on innovation", new Location(City.SETAPAK), "contact@techcorp.com", "0123456789"));
        companies.add(new Company("Innovatech", "Innovative tech solutions for the future", new Location(City.KUALA_LUMPUR), "info@innovatech.com", "0198765432"));
        companies.add(new Company("FinServe", "Reliable financial services for all sectors", new Location(City.PENANG_HILL), "support@finserve.com", "0123487654"));
        companies.add(new Company("HealthPlus", "Healthcare solutions for better living", new Location(City.IPOH), "hello@healthplus.com", "0129876543"));
        companies.add(new Company("EduTech", "Education tech for modern learning", new Location(City.KUCHING), "contact@edutech.com", "0134567890"));
        companies.add(new Company("GreenEnergy", "Sustainable energy for a better planet", new Location(City.KOTA_KINABALU), "info@greenenergy.com", "0145678901"));
        companies.add(new Company("AutoDrive", "Autonomous vehicles for the future", new Location(City.MELAKA), "contact@autodrive.com", "0156789012"));
        companies.add(new Company("FoodieTech", "Tech solutions for the food industry", new Location(City.SEREMBAN), "support@foodietech.com", "0167890123"));
        companies.add(new Company("CyberShield", "Cybersecurity for a safer digital world", new Location(City.KUANTAN_PAHANG), "info@cybershield.com", "0178901234"));
        companies.add(new Company("QuantumSoft", "Quantum computing software solutions", new Location(City.SUNGAI_PETANI), "contact@quantumsoft.com", "0189012345"));
        companies.add(new Company("MediCare", "Medical research for better healthcare", new Location(City.KOTA_BHARU), "contact@medicare.com", "0190123456"));
        companies.add(new Company("CloudSys", "Cloud solutions for scalable growth", new Location(City.SHAH_ALAM), "info@cloudsys.com", "0191234567"));
        companies.add(new Company("NeuroTech", "Neuroscience and AI research solutions", new Location(City.KULIM), "contact@neurotech.com", "0192345678"));
        companies.add(new Company("AgriGrow", "Agricultural tech for sustainable farming", new Location(City.TAIPING), "support@agrigrow.com", "0193456789"));
        companies.add(new Company("SpaceFront", "Aerospace innovation for space exploration", new Location(City.ALOR_SETAR), "hello@spacefront.com", "0194567890"));

        // region Job Postings (with qualifications)
//        jobPostings.add(new JobPosting("Sales Associate", companies.get(0), 4017, 7488, "Design cloud system architectures and pipelines.", JobPosting.Type.SALES, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
//        jobPostings.add(new JobPosting("Marketing Executive", companies.get(0), 4593, 5989, "Design intuitive user experiences for products.", JobPosting.Type.CALL_CUST_SVC, edu2, workExp2, langs2, skills2, JobPosting.Status.OPEN, LocalDate.now().minusDays(8), LocalDate.now()));
//        jobPostings.add(new JobPosting("Cloud Architect", companies.get(0), 5084, 8083, "Design intuitive user experiences for products.", JobPosting.Type.HR_RECRUIT, edu3, workExp3, langs3, skills3, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("Cybersecurity Specialist", companies.get(0), 3846, 5865, "Ensure the security of digital infrastructure.", JobPosting.Type.DESIGN_ARCH, edu5, workExp5, langs5, skills5, JobPosting.Status.OPEN, LocalDate.now().minusDays(8), LocalDate.now()));
//        jobPostings.add(new JobPosting("Mechanical Engineer", companies.get(0), 5458, 7926, "Work on circuits and power systems.", JobPosting.Type.SALES, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
//        jobPostings.add(new JobPosting("Sales Associate", companies.get(1), 3502, 4981, "Design intuitive user experiences for products.", JobPosting.Type.CALL_CUST_SVC, edu2, workExp2, langs2, skills2, JobPosting.Status.OPEN, LocalDate.now().minusDays(10), LocalDate.now()));
//        jobPostings.add(new JobPosting("Mechanical Engineer", companies.get(1), 4006, 5475, "Design intuitive user experiences for products.", JobPosting.Type.IT_COMM_TEC, edu2, workExp2, langs2, skills2, JobPosting.Status.OPEN, LocalDate.now().minusDays(6), LocalDate.now()));
//        jobPostings.add(new JobPosting("Electrical Engineer", companies.get(1), 5422, 7230, "Research and develop AI technologies.", JobPosting.Type.SALES, edu3, workExp3, langs3, skills3, JobPosting.Status.OPEN, LocalDate.now().minusDays(9), LocalDate.now()));
//        jobPostings.add(new JobPosting("HR Manager", companies.get(1), 3173, 6741, "Design and improve mechanical products.", JobPosting.Type.ENGINEERING, edu3, workExp3, langs3, skills3, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("Customer Support Rep", companies.get(1), 4407, 6536, "Design cloud system architectures and pipelines.", JobPosting.Type.MARKETING_COMM, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(6), LocalDate.now()));
//        jobPostings.add(new JobPosting("AI Researcher", companies.get(2), 4837, 5868, "Design intuitive user experiences for products.", JobPosting.Type.MARKETING_COMM, edu5, workExp5, langs5, skills5, JobPosting.Status.OPEN, LocalDate.now().minusDays(8), LocalDate.now()));
//        jobPostings.add(new JobPosting("Mechanical Engineer", companies.get(2), 4338, 6548, "Ensure the security of digital infrastructure.", JobPosting.Type.ACCOUNTING, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(1), LocalDate.now()));
//        jobPostings.add(new JobPosting("Legal Advisor", companies.get(2), 5793, 9349, "Handle client queries and issues professionally.", JobPosting.Type.SCIENCE_TECH, edu1, workExp1, langs1, skills1, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now()));
//        jobPostings.add(new JobPosting("Software Engineer", companies.get(2), 4566, 6441, "Design cloud system architectures and pipelines.", JobPosting.Type.HR_RECRUIT, edu1, workExp1, langs1, skills1, JobPosting.Status.OPEN, LocalDate.now().minusDays(6), LocalDate.now()));
//        jobPostings.add(new JobPosting("Mechanical Engineer", companies.get(2), 4538, 5751, "Coordinate logistics and supply chains.", JobPosting.Type.ADMIN_OFFICE, edu2, workExp2, langs2, skills2, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now()));
//        jobPostings.add(new JobPosting("Finance Analyst", companies.get(3), 4148, 7290, "Coordinate logistics and supply chains.", JobPosting.Type.MARKETING_COMM, edu2, workExp2, langs2, skills2, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("Data Analyst", companies.get(3), 3711, 6756, "Work on circuits and power systems.", JobPosting.Type.SALES, edu3, workExp3, langs3, skills3, JobPosting.Status.OPEN, LocalDate.now().minusDays(6), LocalDate.now()));
//        jobPostings.add(new JobPosting("Finance Analyst", companies.get(3), 5177, 8397, "Drive sales strategies and customer acquisition.", JobPosting.Type.CALL_CUST_SVC, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
//        jobPostings.add(new JobPosting("Marketing Executive", companies.get(3), 5738, 7696, "Oversee HR operations and policy.", JobPosting.Type.ENGINEERING, edu3, workExp3, langs3, skills3, JobPosting.Status.OPEN, LocalDate.now().minusDays(6), LocalDate.now()));
//        jobPostings.add(new JobPosting("Operations Coordinator", companies.get(3), 4570, 7857, "Design and improve mechanical products.", JobPosting.Type.SALES, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("Operations Coordinator", companies.get(4), 5588, 7662, "Analyze financial data and forecasts.", JobPosting.Type.SCIENCE_TECH, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(9), LocalDate.now()));
//        jobPostings.add(new JobPosting("Customer Support Rep", companies.get(4), 4965, 6229, "Design and improve mechanical products.", JobPosting.Type.CALL_CUST_SVC, edu2, workExp2, langs2, skills2, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("HR Manager", companies.get(4), 3617, 7437, "Ensure the security of digital infrastructure.", JobPosting.Type.SALES, edu3, workExp3, langs3, skills3, JobPosting.Status.OPEN, LocalDate.now().minusDays(10), LocalDate.now()));
//        jobPostings.add(new JobPosting("Software Engineer", companies.get(4), 3493, 6893, "Provide legal advice and compliance monitoring.", JobPosting.Type.ADMIN_OFFICE, edu3, workExp3, langs3, skills3, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("Operations Coordinator", companies.get(4), 5573, 8492, "Handle client queries and issues professionally.", JobPosting.Type.ADMIN_OFFICE, edu5, workExp5, langs5, skills5, JobPosting.Status.OPEN, LocalDate.now().minusDays(6), LocalDate.now()));
//        jobPostings.add(new JobPosting("Cloud Architect", companies.get(5), 3570, 6851, "Design intuitive user experiences for products.", JobPosting.Type.CALL_CUST_SVC, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(6), LocalDate.now()));
//        jobPostings.add(new JobPosting("HR Manager", companies.get(5), 3382, 5736, "Drive sales strategies and customer acquisition.", JobPosting.Type.SCIENCE_TECH, edu5, workExp5, langs5, skills5, JobPosting.Status.OPEN, LocalDate.now().minusDays(8), LocalDate.now()));
//        jobPostings.add(new JobPosting("Legal Advisor", companies.get(5), 3280, 4523, "Drive sales strategies and customer acquisition.", JobPosting.Type.DESIGN_ARCH, edu3, workExp3, langs3, skills3, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("Legal Advisor", companies.get(5), 4668, 8048, "Work on circuits and power systems.", JobPosting.Type.HR_RECRUIT, edu5, workExp5, langs5, skills5, JobPosting.Status.OPEN, LocalDate.now().minusDays(8), LocalDate.now()));
//        jobPostings.add(new JobPosting("Cybersecurity Specialist", companies.get(5), 5713, 9368, "Provide legal advice and compliance monitoring.", JobPosting.Type.IT_COMM_TEC, edu1, workExp1, langs1, skills1, JobPosting.Status.OPEN, LocalDate.now().minusDays(1), LocalDate.now()));
//        jobPostings.add(new JobPosting("Cloud Architect", companies.get(6), 3177, 6646, "Design and improve mechanical products.", JobPosting.Type.IT_COMM_TEC, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now()));
//        jobPostings.add(new JobPosting("Sales Associate", companies.get(6), 3326, 5917, "Handle client queries and issues professionally.", JobPosting.Type.SCIENCE_TECH, edu1, workExp1, langs1, skills1, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("Mechanical Engineer", companies.get(6), 5575, 9185, "Coordinate logistics and supply chains.", JobPosting.Type.SALES, edu2, workExp2, langs2, skills2, JobPosting.Status.OPEN, LocalDate.now().minusDays(9), LocalDate.now()));
//        jobPostings.add(new JobPosting("Software Engineer", companies.get(6), 4831, 6829, "Coordinate logistics and supply chains.", JobPosting.Type.IT_COMM_TEC, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now()));
//        jobPostings.add(new JobPosting("Operations Coordinator", companies.get(6), 3505, 4595, "Provide legal advice and compliance monitoring.", JobPosting.Type.SCIENCE_TECH, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(8), LocalDate.now()));
//        jobPostings.add(new JobPosting("AI Researcher", companies.get(7), 3891, 5140, "Ensure the security of digital infrastructure.", JobPosting.Type.SCIENCE_TECH, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("Sales Associate", companies.get(7), 5785, 9242, "Design and improve mechanical products.", JobPosting.Type.CALL_CUST_SVC, edu2, workExp2, langs2, skills2, JobPosting.Status.OPEN, LocalDate.now().minusDays(6), LocalDate.now()));
//        jobPostings.add(new JobPosting("Finance Analyst", companies.get(7), 3907, 6777, "Provide legal advice and compliance monitoring.", JobPosting.Type.SALES, edu3, workExp3, langs3, skills3, JobPosting.Status.OPEN, LocalDate.now().minusDays(8), LocalDate.now()));
//        jobPostings.add(new JobPosting("UX Designer", companies.get(7), 3921, 7043, "Manage marketing campaigns and branding.", JobPosting.Type.ENGINEERING, edu1, workExp1, langs1, skills1, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("Customer Support Rep", companies.get(7), 3151, 6928, "Design intuitive user experiences for products.", JobPosting.Type.DESIGN_ARCH, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(8), LocalDate.now()));
//        jobPostings.add(new JobPosting("Cybersecurity Specialist", companies.get(8), 4993, 7842, "Research and develop AI technologies.", JobPosting.Type.HR_RECRUIT, edu5, workExp5, langs5, skills5, JobPosting.Status.OPEN, LocalDate.now().minusDays(8), LocalDate.now()));
//        jobPostings.add(new JobPosting("AI Researcher", companies.get(8), 3926, 5104, "Analyze market and operational data for insight.", JobPosting.Type.ENGINEERING, edu3, workExp3, langs3, skills3, JobPosting.Status.OPEN, LocalDate.now().minusDays(6), LocalDate.now()));
//        jobPostings.add(new JobPosting("Customer Support Rep", companies.get(8), 4401, 7535, "Design cloud system architectures and pipelines.", JobPosting.Type.SALES, edu1, workExp1, langs1, skills1, JobPosting.Status.OPEN, LocalDate.now().minusDays(1), LocalDate.now()));
//        jobPostings.add(new JobPosting("Finance Analyst", companies.get(8), 5559, 8266, "Design cloud system architectures and pipelines.", JobPosting.Type.SCIENCE_TECH, edu2, workExp2, langs2, skills2, JobPosting.Status.OPEN, LocalDate.now().minusDays(9), LocalDate.now()));
//        jobPostings.add(new JobPosting("Operations Coordinator", companies.get(8), 5810, 7487, "Design and improve mechanical products.", JobPosting.Type.DESIGN_ARCH, edu3, workExp3, langs3, skills3, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
//        jobPostings.add(new JobPosting("Marketing Executive", companies.get(9), 5073, 8974, "Work on circuits and power systems.", JobPosting.Type.MARKETING_COMM, edu1, workExp1, langs1, skills1, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("Legal Advisor", companies.get(9), 4639, 6674, "Research and develop AI technologies.", JobPosting.Type.SALES, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(9), LocalDate.now()));
//        jobPostings.add(new JobPosting("UX Designer", companies.get(9), 5879, 9658, "Drive sales strategies and customer acquisition.", JobPosting.Type.DESIGN_ARCH, edu3, workExp3, langs3, skills3, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("Marketing Executive", companies.get(9), 3850, 5522, "Design intuitive user experiences for products.", JobPosting.Type.ACCOUNTING, edu5, workExp5, langs5, skills5, JobPosting.Status.OPEN, LocalDate.now().minusDays(10), LocalDate.now()));
//        jobPostings.add(new JobPosting("HR Manager", companies.get(9), 3095, 5496, "Manage marketing campaigns and branding.", JobPosting.Type.DESIGN_ARCH, edu5, workExp5, langs5, skills5, JobPosting.Status.OPEN, LocalDate.now().minusDays(1), LocalDate.now()));
//        jobPostings.add(new JobPosting("HR Manager", companies.get(10), 5679, 8765, "Oversee HR operations and policy.", JobPosting.Type.HR_RECRUIT, edu2, workExp2, langs2, skills2, JobPosting.Status.OPEN, LocalDate.now().minusDays(8), LocalDate.now()));
//        jobPostings.add(new JobPosting("Cloud Architect", companies.get(10), 4467, 7878, "Coordinate logistics and supply chains.", JobPosting.Type.DESIGN_ARCH, edu3, workExp3, langs3, skills3, JobPosting.Status.OPEN, LocalDate.now().minusDays(6), LocalDate.now()));
//        jobPostings.add(new JobPosting("Legal Advisor", companies.get(10), 4033, 6133, "Provide legal advice and compliance monitoring.", JobPosting.Type.HR_RECRUIT, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("UX Designer", companies.get(10), 3349, 6380, "Responsible for developing scalable software systems.", JobPosting.Type.HR_RECRUIT, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("Legal Advisor", companies.get(10), 5874, 6915, "Research and develop AI technologies.", JobPosting.Type.CALL_CUST_SVC, edu1, workExp1, langs1, skills1, JobPosting.Status.OPEN, LocalDate.now().minusDays(1), LocalDate.now()));
//        jobPostings.add(new JobPosting("Sales Associate", companies.get(11), 4773, 6940, "Coordinate logistics and supply chains.", JobPosting.Type.MARKETING_COMM, edu2, workExp2, langs2, skills2, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("Legal Advisor", companies.get(11), 4816, 6970, "Design intuitive user experiences for products.", JobPosting.Type.MARKETING_COMM, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(9), LocalDate.now()));
//        jobPostings.add(new JobPosting("Electrical Engineer", companies.get(11), 5793, 7833, "Design cloud system architectures and pipelines.", JobPosting.Type.ACCOUNTING, edu2, workExp2, langs2, skills2, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("Operations Coordinator", companies.get(11), 5458, 6957, "Research and develop AI technologies.", JobPosting.Type.DESIGN_ARCH, edu1, workExp1, langs1, skills1, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("Data Analyst", companies.get(11), 5977, 9303, "Oversee HR operations and policy.", JobPosting.Type.ACCOUNTING, edu1, workExp1, langs1, skills1, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now()));
//        jobPostings.add(new JobPosting("Cloud Architect", companies.get(12), 5236, 6876, "Research and develop AI technologies.", JobPosting.Type.CALL_CUST_SVC, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(6), LocalDate.now()));
//        jobPostings.add(new JobPosting("Electrical Engineer", companies.get(12), 4666, 7666, "Ensure the security of digital infrastructure.", JobPosting.Type.SCIENCE_TECH, edu3, workExp3, langs3, skills3, JobPosting.Status.OPEN, LocalDate.now().minusDays(1), LocalDate.now()));
//        jobPostings.add(new JobPosting("Cybersecurity Specialist", companies.get(12), 4637, 7271, "Manage marketing campaigns and branding.", JobPosting.Type.ACCOUNTING, edu3, workExp3, langs3, skills3, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now()));
//        jobPostings.add(new JobPosting("Cybersecurity Specialist", companies.get(12), 5596, 7878, "Provide legal advice and compliance monitoring.", JobPosting.Type.IT_COMM_TEC, edu1, workExp1, langs1, skills1, JobPosting.Status.OPEN, LocalDate.now().minusDays(9), LocalDate.now()));
//        jobPostings.add(new JobPosting("Customer Support Rep", companies.get(12), 5296, 7459, "Work on circuits and power systems.", JobPosting.Type.IT_COMM_TEC, edu5, workExp5, langs5, skills5, JobPosting.Status.OPEN, LocalDate.now().minusDays(6), LocalDate.now()));
//        jobPostings.add(new JobPosting("Customer Support Rep", companies.get(13), 5011, 8004, "Oversee HR operations and policy.", JobPosting.Type.ACCOUNTING, edu3, workExp3, langs3, skills3, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("Operations Coordinator", companies.get(13), 3482, 5465, "Design and improve mechanical products.", JobPosting.Type.DESIGN_ARCH, edu1, workExp1, langs1, skills1, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("Legal Advisor", companies.get(13), 4596, 5881, "Manage marketing campaigns and branding.", JobPosting.Type.ACCOUNTING, edu1, workExp1, langs1, skills1, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("Sales Associate", companies.get(13), 5353, 6440, "Provide legal advice and compliance monitoring.", JobPosting.Type.MARKETING_COMM, edu4, workExp4, langs4, skills4, JobPosting.Status.OPEN, LocalDate.now().minusDays(10), LocalDate.now()));
//        jobPostings.add(new JobPosting("Finance Analyst", companies.get(13), 3181, 5013, "Drive sales strategies and customer acquisition.", JobPosting.Type.SALES, edu1, workExp1, langs1, skills1, JobPosting.Status.OPEN, LocalDate.now().minusDays(6), LocalDate.now()));
//        jobPostings.add(new JobPosting("Finance Analyst", companies.get(14), 3395, 5433, "Ensure the security of digital infrastructure.", JobPosting.Type.HR_RECRUIT, edu2, workExp2, langs2, skills2, JobPosting.Status.OPEN, LocalDate.now().minusDays(8), LocalDate.now()));
//        jobPostings.add(new JobPosting("UX Designer", companies.get(14), 3158, 6814, "Analyze financial data and forecasts.", JobPosting.Type.IT_COMM_TEC, edu2, workExp2, langs2, skills2, JobPosting.Status.OPEN, LocalDate.now().minusDays(10), LocalDate.now()));
//        jobPostings.add(new JobPosting("Cloud Architect", companies.get(14), 4387, 5552, "Oversee HR operations and policy.", JobPosting.Type.HR_RECRUIT, edu2, workExp2, langs2, skills2, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
//        jobPostings.add(new JobPosting("Sales Associate", companies.get(14), 5529, 7534, "Design cloud system architectures and pipelines.", JobPosting.Type.HR_RECRUIT, edu2, workExp2, langs2, skills2, JobPosting.Status.OPEN, LocalDate.now().minusDays(8), LocalDate.now()));
        jobPostings.add(new JobPosting(
                "Registered Nurse", companies.get(0), 6000, 8000,
                "Provide patient care and support in hospital settings.",
                JobPosting.Type.HEALTH_MED,
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.USM, 3.5, true, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 1, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT, true, Qualification.Importance.MEDIUM)),
                Arrays.asList(new Skill(JobPosting.Type.HEALTH_MED, "Patient Care", Skill.ProficiencyLevel.BEGINNER, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN, LocalDate.now().minusDays(10), LocalDate.now()
        ));
        jobPostings.add(new JobPosting(
                "Assistant Nurse", companies.get(0), 3000, 3500,
                "Lawyer side relationship rule item son mouth bag lay.", JobPosting.Type.HEALTH_MED,
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.UNIMAS, 3.0, true, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 1, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.ELEMENTARY, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.IT_COMM_TEC, "Network Admin", Skill.ProficiencyLevel.ADVANCED, true, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 4, 12),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Art therapist", companies.get(1), 4750, 7429,
                "Friend finally should data condition country.", JobPosting.Type.IT_COMM_TEC,
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.CS, EducationLevel.University.UM, 3.62, true, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.ENGINEERING, 1, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.INTERMEDIATE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.HEALTH_MED, "First Aid", Skill.ProficiencyLevel.ADVANCED, true, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 1, 16),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Loss adjuster, chartered", companies.get(2), 5231, 7479,
                "Phone white decision what new top stand civil.", JobPosting.Type.SCIENCE_TECH,
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.EE, EducationLevel.University.USM, 2.6, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.BANK_FIN_SERV, 8, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.BANK_FIN_SERV, "Accounting", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 3, 13),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Company secretary", companies.get(3), 4104, 5370,
                "Accept how similar not cover subject.", JobPosting.Type.ENGINEERING,
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.PHAR, EducationLevel.University.UTM, 3.97, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.SCIENCE_TECH, 5, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.INTERMEDIATE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.SCIENCE_TECH, "Lab Safety", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 3, 20),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Fast food restaurant manager", companies.get(4), 5067, 6096,
                "Son two hold perhaps series appear east peace up.", JobPosting.Type.IT_COMM_TEC,
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.CS, EducationLevel.University.UM, 3.96, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 9, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.IT_COMM_TEC, "Cloud", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 4, 9),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Horticultural consultant", companies.get(5), 5826, 7040,
                "Full radio my wall your I artist big feel its.", JobPosting.Type.SCIENCE_TECH,
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.PHAR, EducationLevel.University.UM, 3.0, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.BANK_FIN_SERV, 8, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.INTERMEDIATE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.HEALTH_MED, "First Aid", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 3, 2),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Film/video editor", companies.get(6), 3018, 4989,
                "Short message management old chair put technology clearly senior.", JobPosting.Type.HEALTH_MED,
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.DS, EducationLevel.University.UM, 3.2, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 10, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.INTERMEDIATE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.IT_COMM_TEC, "Network Admin", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 1, 29),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Community arts worker", companies.get(7), 3913, 5986,
                "Site though though modern describe.", JobPosting.Type.IT_COMM_TEC,
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.BIO, EducationLevel.University.UKM, 2.95, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.ENGINEERING, 5, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.HEALTH_MED, "First Aid", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 2, 22),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Tax inspector", companies.get(8), 5864, 7406,
                "Free actually box cover moment result decide hope.", JobPosting.Type.BANK_FIN_SERV,
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.AI, EducationLevel.University.UNIMAS, 3.6, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 5, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.NATIVE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.HEALTH_MED, "Patient Care", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 2, 28),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Civil Service administrator", companies.get(9), 5808, 8182,
                "Question charge word poor various although knowledge any.", JobPosting.Type.ENGINEERING,
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.CY, EducationLevel.University.UTM, 3.6, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.ENGINEERING, 2, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.INTERMEDIATE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.HEALTH_MED, "Surgery Prep", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 2, 10),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Public relations account executive", companies.get(10), 4093, 5581,
                "Yet value church although deep.", JobPosting.Type.ENGINEERING,
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.ME, EducationLevel.University.UPM, 3.38, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.ENGINEERING, 1, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.INTERMEDIATE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.HEALTH_MED, "First Aid", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 1, 11),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Chemical engineer", companies.get(11), 4329, 5656,
                "Edge reach run already international late.", JobPosting.Type.HEALTH_MED,
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.ME, EducationLevel.University.UTM, 3.71, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 10, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.INTERMEDIATE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.BANK_FIN_SERV, "Taxation", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 1, 10),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Programme researcher, broadcasting/film/video", companies.get(12), 5745, 7046,
                "Create often economy certainly decade include us our wonder husband.", JobPosting.Type.HEALTH_MED,
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.SE, EducationLevel.University.UKM, 3.6, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 5, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.BANK_FIN_SERV, "Taxation", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 2, 28),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Gaffer", companies.get(13), 5388, 7905,
                "Couple your hospital even even.", JobPosting.Type.HEALTH_MED,
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.CE, EducationLevel.University.UIAM, 3.26, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.SCIENCE_TECH, 6, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.ENGINEERING, "Project Planning", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 1, 19),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Industrial buyer", companies.get(14), 5540, 8369,
                "View yeah next discussion act.", JobPosting.Type.HEALTH_MED,
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.ME, EducationLevel.University.UIAM, 2.52, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.BANK_FIN_SERV, 5, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.BANK_FIN_SERV, "Taxation", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 2, 21),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Producer, radio", companies.get(1), 5714, 7455,
                "Rock contain with age pay.", JobPosting.Type.ENGINEERING,
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.CY, EducationLevel.University.USM, 3.13, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.ENGINEERING, 7, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.INTERMEDIATE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.ENGINEERING, "Project Planning", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 2, 13),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Management consultant", companies.get(2), 4718, 5920,
                "Century through about same region medical company him land defense.", JobPosting.Type.ENGINEERING,
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.SE, EducationLevel.University.UTM, 2.85, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 8, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.HEALTH_MED, "Surgery Prep", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 3, 18),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Designer, multimedia", companies.get(3), 4274, 5609,
                "Successful near establish personal plan carry once once book feeling.", JobPosting.Type.IT_COMM_TEC,
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.BIO, EducationLevel.University.UKM, 2.58, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.BANK_FIN_SERV, 7, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.INTERMEDIATE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.SCIENCE_TECH, "Scientific Writing", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 2, 3),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Clinical psychologist", companies.get(4), 4984, 6747,
                "Pattern another sound involve goal whose attorney traditional indicate not.", JobPosting.Type.SCIENCE_TECH,
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.IT, EducationLevel.University.USM, 3.98, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.SCIENCE_TECH, 1, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.INTERMEDIATE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.HEALTH_MED, "Surgery Prep", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 3, 14),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Quarry manager", companies.get(5), 3908, 5803,
                "Ready family pull never student.", JobPosting.Type.IT_COMM_TEC,
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.IT, EducationLevel.University.UKM, 2.7, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 9, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.FLUENT, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.BANK_FIN_SERV, "Auditing", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 1, 17),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Data processing manager", companies.get(6), 3615, 5258,
                "Who never chair voice group not.", JobPosting.Type.ENGINEERING,
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.IT, EducationLevel.University.UM, 3.21, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 8, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.INTERMEDIATE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.IT_COMM_TEC, "Python", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 4, 8),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Civil Service administrator", companies.get(7), 3880, 5326,
                "Successful break trip six trip example scene sister give.", JobPosting.Type.ENGINEERING,
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.EE, EducationLevel.University.UIAM, 3.63, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.SCIENCE_TECH, 5, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.NATIVE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.BANK_FIN_SERV, "Accounting", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 2, 3),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Animal nutritionist", companies.get(8), 3991, 6163,
                "Different both become light.", JobPosting.Type.BANK_FIN_SERV,
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.UKM, 3.8, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 7, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.BANK_FIN_SERV, "Taxation", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 1, 30),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Associate Professor", companies.get(9), 4020, 6667,
                "Glass local manage western provide media eight painting model.", JobPosting.Type.HEALTH_MED,
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.UTeM, 3.06, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.ENGINEERING, 1, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.ENGINEERING, "Project Planning", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 3, 20),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Clinical biochemist", companies.get(10), 3393, 4923,
                "Always official discussion worry action development.", JobPosting.Type.BANK_FIN_SERV,
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.IS, EducationLevel.University.UTeM, 3.5, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.SCIENCE_TECH, 8, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.NATIVE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.ENGINEERING, "AutoCAD", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 1, 24),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "IT sales professional", companies.get(11), 5933, 8692,
                "Physical billion figure term join per.", JobPosting.Type.IT_COMM_TEC,
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.BIO, EducationLevel.University.UTeM, 3.4, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 7, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.BANK_FIN_SERV, "Investment Analysis", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 3, 21),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Journalist, magazine", companies.get(12), 3622, 5367,
                "Church others positive check court.", JobPosting.Type.IT_COMM_TEC,
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.ME, EducationLevel.University.UNIMAS, 3.19, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.BANK_FIN_SERV, 1, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.INTERMEDIATE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.HEALTH_MED, "Surgery Prep", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 2, 10),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Pharmacologist", companies.get(13), 4894, 7400,
                "Because page fire check book.", JobPosting.Type.HEALTH_MED,
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.PHAR, EducationLevel.University.USM, 3.53, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.BANK_FIN_SERV, 1, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.NATIVE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.HEALTH_MED, "Surgery Prep", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 3, 24),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Administrator, sports", companies.get(14), 3942, 5922,
                "Training class sea such among my.", JobPosting.Type.IT_COMM_TEC,
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.IT, EducationLevel.University.UTeM, 3.5, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.BANK_FIN_SERV, 6, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.FLUENT, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.SCIENCE_TECH, "Data Recording", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 2, 10),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Maintenance engineer", companies.get(0), 3460, 5238,
                "Bar art write choice.", JobPosting.Type.ENGINEERING,
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.PHAR, EducationLevel.University.UTM, 3.19, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 6, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.NATIVE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.BANK_FIN_SERV, "Taxation", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 1, 20),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Press photographer", companies.get(1), 5152, 6549,
                "Day effect well hundred travel hot.", JobPosting.Type.IT_COMM_TEC,
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.CS, EducationLevel.University.UM, 3.33, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.ENGINEERING, 6, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.NATIVE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.HEALTH_MED, "Pharmacology", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 4, 10),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Lecturer, further education", companies.get(2), 5669, 6738,
                "Road drive mind appear indeed.", JobPosting.Type.BANK_FIN_SERV,
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.SE, EducationLevel.University.UTeM, 2.56, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 5, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.FLUENT, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.SCIENCE_TECH, "Scientific Writing", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Management consultant", companies.get(3), 3820, 6384,
                "College pattern have similar million identify.", JobPosting.Type.BANK_FIN_SERV,
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.BIO, EducationLevel.University.UTM, 2.56, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.SCIENCE_TECH, 7, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.NATIVE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.BANK_FIN_SERV, "Risk Analysis", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 3, 1),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Advertising copywriter", companies.get(4), 4913, 6112,
                "Stuff although material write so green upon election ok.", JobPosting.Type.BANK_FIN_SERV,
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.CE, EducationLevel.University.UNIMAS, 2.98, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 6, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.NATIVE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.ENGINEERING, "AutoCAD", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 2, 28),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Teaching laboratory technician", companies.get(5), 4548, 5997,
                "Return eye huge foot try scene future similar pay east material.", JobPosting.Type.SCIENCE_TECH,
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.IS, EducationLevel.University.UNIMAS, 2.82, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 3, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.INTERMEDIATE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.HEALTH_MED, "Patient Care", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 4, 3),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Chief Strategy Officer", companies.get(6), 3634, 4877,
                "Deep baby ball past stop strong despite way friend reality.", JobPosting.Type.BANK_FIN_SERV,
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.SE, EducationLevel.University.UKM, 3.82, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.BANK_FIN_SERV, 3, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.INTERMEDIATE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.SCIENCE_TECH, "Lab Safety", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 2, 2),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Therapist, nutritional", companies.get(7), 5879, 7211,
                "Live ready through receive meeting technology second.", JobPosting.Type.HEALTH_MED,
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.IT, EducationLevel.University.UKM, 3.41, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.BANK_FIN_SERV, 7, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.NATIVE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.IT_COMM_TEC, "Network Admin", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 3, 30),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Minerals surveyor", companies.get(8), 4453, 6127,
                "Without idea always white wall movement seek.", JobPosting.Type.IT_COMM_TEC,
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.IS, EducationLevel.University.UKM, 3.48, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.ENGINEERING, 1, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.INTERMEDIATE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.BANK_FIN_SERV, "Accounting", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 3, 14),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Child psychotherapist", companies.get(9), 4840, 7440,
                "Physical whole look everyone show need their.", JobPosting.Type.BANK_FIN_SERV,
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.CE, EducationLevel.University.UTM, 2.83, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.BANK_FIN_SERV, 9, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.NATIVE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.SCIENCE_TECH, "Microscopy", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 3, 21),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Archivist", companies.get(10), 5095, 8055,
                "Range travel consumer before make president place morning fund.", JobPosting.Type.SCIENCE_TECH,
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.BIO, EducationLevel.University.UIAM, 2.56, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.BANK_FIN_SERV, 4, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.FLUENT, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.SCIENCE_TECH, "Lab Safety", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 4, 9),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Designer, furniture", companies.get(11), 3963, 6419,
                "Media how why concern none style so security.", JobPosting.Type.SCIENCE_TECH,
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.IS, EducationLevel.University.UKM, 2.76, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 10, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.NATIVE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.HEALTH_MED, "Surgery Prep", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 1, 10),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Catering manager", companies.get(12), 3019, 5381,
                "Happy those explain act star outside speech some majority.", JobPosting.Type.ENGINEERING,
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.ME, EducationLevel.University.USM, 2.71, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.ENGINEERING, 7, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.INTERMEDIATE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.ENGINEERING, "Project Planning", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 2, 22),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Seismic interpreter", companies.get(13), 3180, 5431,
                "List day politics hope agent husband.", JobPosting.Type.IT_COMM_TEC,
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.ME, EducationLevel.University.USM, 2.87, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 3, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.NATIVE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.BANK_FIN_SERV, "Taxation", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 1, 4),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Clinical psychologist", companies.get(14), 3793, 6590,
                "Whatever central structure shake drive list common finally modern home.", JobPosting.Type.HEALTH_MED,
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.CY, EducationLevel.University.UPM, 3.82, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 6, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.ENGINEERING, "Circuit Design", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 1, 12),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Neurosurgeon", companies.get(0), 5803, 7223,
                "Represent quality break number sing allow section after into him.", JobPosting.Type.BANK_FIN_SERV,
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.BIO, EducationLevel.University.UTM, 2.83, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.SCIENCE_TECH, 1, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.INTERMEDIATE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.BANK_FIN_SERV, "Investment Analysis", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 4, 7),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Advertising account planner", companies.get(1), 3369, 6315,
                "Avoid prove environment mind clear.", JobPosting.Type.IT_COMM_TEC,
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.CE, EducationLevel.University.UNIMAS, 2.95, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.SCIENCE_TECH, 6, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.FLUENT, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.ENGINEERING, "CAD", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 2, 5),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Warden/ranger", companies.get(2), 4252, 6436,
                "Various sell happen when go everything instead world hit.", JobPosting.Type.HEALTH_MED,
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.CE, EducationLevel.University.UTM, 3.93, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.SCIENCE_TECH, 7, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.NATIVE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.IT_COMM_TEC, "Network Admin", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 3, 31),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Horticultural therapist", companies.get(3), 3695, 6694,
                "Almost head student probably lose effort catch reflect perhaps organization.", JobPosting.Type.HEALTH_MED,
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.PHAR, EducationLevel.University.UPM, 3.68, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 9, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.NATIVE, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.BANK_FIN_SERV, "Accounting", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 1, 26),
                LocalDate.of(2025, 4, 14)
        ));

        jobPostings.add(new JobPosting(
                "Horticulturist, amenity", companies.get(4), 5100, 6565,
                "Baby but somebody think.", JobPosting.Type.BANK_FIN_SERV,
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.SE, EducationLevel.University.USM, 3.8, false, Qualification.Importance.HIGH),
                Arrays.asList(new WorkExperience(WorkExperience.Industry.BANK_FIN_SERV, 3, false, Qualification.Importance.MEDIUM)),
                Arrays.asList(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.FLUENT, false, Qualification.Importance.LOW)),
                Arrays.asList(new Skill(JobPosting.Type.ENGINEERING, "AutoCAD", Skill.ProficiencyLevel.ADVANCED, false, Qualification.Importance.HIGH)),
                JobPosting.Status.OPEN,
                LocalDate.of(2025, 1, 29),
                LocalDate.of(2025, 4, 14)
        ));

        // 75 Job Postings (5 per company, Hard-Coded, Qualifications set to null)
//        // TechCorp (Company 0)
//        jobPostings.add(new JobPosting("Software Engineer", companies.get(0), 5000, 8000, "Develop scalable web applications", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("Senior Developer", companies.get(0), 7000, 10000, "Lead development projects for clients", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("DevOps Engineer", companies.get(0), 6000, 9000, "Manage CI/CD pipelines and deployments", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
//        jobPostings.add(new JobPosting("QA Engineer", companies.get(0), 4000, 6000, "Ensure product quality through testing", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.FILLED, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("UI/UX Designer", companies.get(0), 4500, 7000, "Design user interfaces for web apps", JobPosting.Type.DESIGN_ARCH, null, JobPosting.Status.FILLED, LocalDate.now().minusDays(4), LocalDate.now()));
//
//        // Innovatech (Company 1)
//        jobPostings.add(new JobPosting("Data Scientist", companies.get(1), 6000, 9000, "Analyze complex datasets for insights", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("AI Engineer", companies.get(1), 7000, 11000, "Develop AI models for automation", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("ML Engineer", companies.get(1), 6500, 10000, "Implement ML algorithms for projects", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
//        jobPostings.add(new JobPosting("Research Scientist", companies.get(1), 8000, 12000, "Conduct innovative research in tech", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("Product Manager", companies.get(1), 7000, 11000, "Manage product lifecycle and strategy", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now()));
//
//        // FinServe (Company 2)
//        jobPostings.add(new JobPosting("Financial Analyst", companies.get(2), 5000, 8000, "Analyze financial data for insights", JobPosting.Type.BANK_FIN_SERV, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("Risk Manager", companies.get(2), 6000, 9000, "Assess financial risks for the company", JobPosting.Type.BANK_FIN_SERV, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("Accountant", companies.get(2), 4500, 7000, "Manage financial records and reports", JobPosting.Type.ACCOUNTING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
//        jobPostings.add(new JobPosting("Investment Advisor", companies.get(2), 5500, 8500, "Provide investment advice to clients", JobPosting.Type.BANK_FIN_SERV, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("Compliance Officer", companies.get(2), 5000, 8000, "Ensure regulatory compliance", JobPosting.Type.BANK_FIN_SERV, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now()));
//
//        // HealthPlus (Company 3)
//        jobPostings.add(new JobPosting("Medical Researcher", companies.get(3), 6000, 9000, "Conduct medical research studies", JobPosting.Type.HEALTH_MED, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("Clinical Analyst", companies.get(3), 5000, 8000, "Analyze clinical data for insights", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("Healthcare Manager", companies.get(3), 7000, 10000, "Manage healthcare operations", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
//        jobPostings.add(new JobPosting("Health Consultant", companies.get(3), 5500, 8500, "Provide health consultancy services", JobPosting.Type.HEALTH_MED, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("Data Specialist", companies.get(3), 5000, 8000, "Handle healthcare data systems", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now()));
//
//        // EduTech (Company 4)
//        jobPostings.add(new JobPosting("EdTech Developer", companies.get(4), 5000, 8000, "Develop educational software tools", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("Content Creator", companies.get(4), 4000, 6000, "Create educational content", JobPosting.Type.EDUC_TRAINING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("Instructional Designer", companies.get(4), 4500, 7000, "Design learning materials", JobPosting.Type.EDUC_TRAINING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
//        jobPostings.add(new JobPosting("Tech Support", companies.get(4), 3500, 5000, "Provide tech support for platforms", JobPosting.Type.CALL_CUST_SVC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("Project Manager", companies.get(4), 6000, 9000, "Manage education tech projects", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now()));
//
//        // GreenEnergy (Company 5)
//        jobPostings.add(new JobPosting("Energy Engineer", companies.get(5), 6000, 9000, "Design sustainable energy systems", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("Sustainability Analyst", companies.get(5), 5000, 8000, "Analyze sustainability metrics", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("Project Coordinator", companies.get(5), 4500, 7000, "Coordinate energy projects", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
//        jobPostings.add(new JobPosting("Field Technician", companies.get(5), 4000, 6000, "Install energy systems", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("Research Analyst", companies.get(5), 5000, 8000, "Research green energy solutions", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now()));
//
//        // AutoDrive (Company 6)
//        jobPostings.add(new JobPosting("Robotics Engineer", companies.get(6), 7000, 11000, "Develop autonomous vehicle systems", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("Software Developer", companies.get(6), 6000, 9000, "Code vehicle control software", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("Test Engineer", companies.get(6), 5000, 8000, "Test autonomous vehicle systems", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
//        jobPostings.add(new JobPosting("Data Engineer", companies.get(6), 5500, 8500, "Manage vehicle data pipelines", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("Safety Analyst", companies.get(6), 5000, 8000, "Ensure vehicle safety standards", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now()));
//
//        // FoodieTech (Company 7)
//        jobPostings.add(new JobPosting("App Developer", companies.get(7), 5000, 8000, "Develop food delivery apps", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("Marketing Specialist", companies.get(7), 4500, 7000, "Market food tech solutions", JobPosting.Type.MARKETING_COMM, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("Data Analyst", companies.get(7), 5000, 8000, "Analyze food industry trends", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
//        jobPostings.add(new JobPosting("Logistics Manager", companies.get(7), 5500, 8500, "Manage food delivery logistics", JobPosting.Type.MANF_LOG, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("Customer Support", companies.get(7), 3500, 5000, "Support food app users", JobPosting.Type.CALL_CUST_SVC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now()));
//
//        // CyberShield (Company 8)
//        jobPostings.add(new JobPosting("Security Analyst", companies.get(8), 6000, 9000, "Analyze cybersecurity threats", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("Penetration Tester", companies.get(8), 6500, 9500, "Test system vulnerabilities", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("Security Engineer", companies.get(8), 7000, 10000, "Design secure systems", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
//        jobPostings.add(new JobPosting("Compliance Analyst", companies.get(8), 5000, 8000, "Ensure regulatory compliance", JobPosting.Type.LEGAL, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("Incident Responder", companies.get(8), 5500, 8500, "Respond to security incidents", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now()));
//
//        // QuantumSoft (Company 9)
//        jobPostings.add(new JobPosting("Quantum Developer", companies.get(9), 8000, 12000, "Develop quantum computing software", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("Research Scientist", companies.get(9), 7500, 11000, "Research quantum algorithms", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("Software Engineer", companies.get(9), 6000, 9000, "Code quantum applications", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
//        jobPostings.add(new JobPosting("Data Scientist", companies.get(9), 6500, 9500, "Analyze quantum computing data", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("Project Manager", companies.get(9), 7000, 10000, "Manage quantum projects", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now()));
//
//        // MediCare (Company 10)
//        jobPostings.add(new JobPosting("Clinical Researcher", companies.get(10), 6000, 9000, "Conduct clinical research studies", JobPosting.Type.HEALTH_MED, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("Medical Analyst", companies.get(10), 5000, 8000, "Analyze medical data trends", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("Health Consultant", companies.get(10), 5500, 8500, "Provide medical consultancy", JobPosting.Type.HEALTH_MED, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
//        jobPostings.add(new JobPosting("Data Specialist", companies.get(10), 5000, 8000, "Manage medical data systems", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("Operations Manager", companies.get(10), 6000, 9000, "Oversee medical operations", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now()));
//
//        // CloudSys (Company 11)
//        jobPostings.add(new JobPosting("Cloud Engineer", companies.get(11), 6000, 9000, "Design cloud infrastructure", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("DevOps Specialist", companies.get(11), 6500, 9500, "Manage cloud deployments", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("Security Analyst", companies.get(11), 5500, 8500, "Secure cloud systems", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
//        jobPostings.add(new JobPosting("Data Engineer", companies.get(11), 6000, 9000, "Build data pipelines in cloud", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("Cloud Architect", companies.get(11), 7000, 11000, "Architect cloud solutions", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now()));
//
//        // NeuroTech (Company 12)
//        jobPostings.add(new JobPosting("Neuroscientist", companies.get(12), 7000, 11000, "Research neural networks", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("AI Developer", companies.get(12), 6500, 9500, "Develop AI for neuroscience", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("Data Scientist", companies.get(12), 6000, 9000, "Analyze neural data", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
//        jobPostings.add(new JobPosting("Research Analyst", companies.get(12), 5500, 8500, "Support neuroscience research", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("Software Engineer", companies.get(12), 6000, 9000, "Code neuroscience tools", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now()));
//
//        // AgriGrow (Company 13)
//        jobPostings.add(new JobPosting("Agri Engineer", companies.get(13), 5000, 8000, "Design farming technology", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("Data Analyst", companies.get(13), 4500, 7000, "Analyze agricultural data", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("Field Manager", companies.get(13), 4000, 6000, "Manage farming operations", JobPosting.Type.AGRICULTURE, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
//        jobPostings.add(new JobPosting("Tech Specialist", companies.get(13), 4500, 7000, "Support agri-tech systems", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("Sustainability Lead", companies.get(13), 5000, 8000, "Lead sustainable farming", JobPosting.Type.AGRICULTURE, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now()));
//
//        // SpaceFront (Company 14) 
//        jobPostings.add(new JobPosting("Aerospace Engineer", companies.get(14), 7000, 11000, "Design aerospace systems", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now()));
//        jobPostings.add(new JobPosting("Space Researcher", companies.get(14), 6500, 9500, "Research space technologies", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now()));
//        jobPostings.add(new JobPosting("Test Engineer", companies.get(14), 6000, 9000, "Test aerospace components", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now()));
//        jobPostings.add(new JobPosting("Data Analyst", companies.get(14), 5500, 8500, "Analyze space mission data", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now()));
//        jobPostings.add(new JobPosting("Project Manager", companies.get(14), 7000, 10000, "Manage space projects", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now()));
//        // Applicants
//        applicants.add(new Applicant("Angela Lopez", "angelalopez@example.com", "0126875274", JobPosting.Type.CALL_CUST_SVC, new Location(City.JOHOR_BAHRU), edu1, workExp3, langs2, skills2));
//        applicants.add(new Applicant("Jeffrey Jones", "jeffreyjones@yahoo.com", "0172723013", JobPosting.Type.CALL_CUST_SVC, new Location(City.ALOR_SETAR), edu4, workExp2, langs5, skills2));
//        applicants.add(new Applicant("Scott Garcia", "scottgarcia@yahoo.com", "0115705683", JobPosting.Type.SCIENCE_TECH, new Location(City.KULIM), edu1, workExp2, langs4, skills5));
//        applicants.add(new Applicant("Crystal Conner", "crystalconner@yahoo.com", "0137346114", JobPosting.Type.SCIENCE_TECH, new Location(City.MELAKA), edu2, workExp2, langs3, skills4));
//        applicants.add(new Applicant("Misty Wilkins", "mistywilkins@example.com", "0130217269", JobPosting.Type.CALL_CUST_SVC, new Location(City.SEREMBAN), edu5, workExp2, langs5, skills1));
//        applicants.add(new Applicant("Ellen Johnson", "ellenjohnson@yahoo.com", "0154773537", JobPosting.Type.IT_COMM_TEC, new Location(City.KUALA_LUMPUR), edu3, workExp3, langs4, skills4));
//        applicants.add(new Applicant("Bradley Wood", "bradleywood@example.com", "0142480634", JobPosting.Type.IT_COMM_TEC, new Location(City.TAIPING), edu3, workExp2, langs2, skills3));
//        applicants.add(new Applicant("Brandon Ross", "brandonross@hotmail.com", "0121727639", JobPosting.Type.SALES, new Location(City.IPOH), edu5, workExp5, langs4, skills5));
//        applicants.add(new Applicant("Patrick Thompson", "patrickthompson@example.com", "0143367993", JobPosting.Type.DESIGN_ARCH, new Location(City.KOTA_KINABALU), edu3, workExp4, langs3, skills3));
//        applicants.add(new Applicant("Stephen Bell", "stephenbell@example.com", "0129421336", JobPosting.Type.SCIENCE_TECH, new Location(City.PENANG_HILL), edu4, workExp4, langs3, skills3));
//        applicants.add(new Applicant("Julie Gonzalez", "juliegonzalez@hotmail.com", "0115192586", JobPosting.Type.SCIENCE_TECH, new Location(City.KUALA_LUMPUR), edu4, workExp3, langs1, skills5));
//        applicants.add(new Applicant("Matthew Stanley", "matthewstanley@example.com", "0174692046", JobPosting.Type.HEALTH_MED, new Location(City.TAIPING), edu2, workExp4, langs1, skills2));
//        applicants.add(new Applicant("Melissa Robinson", "melissarobinson@gmail.com", "0118657014", JobPosting.Type.HEALTH_MED, new Location(City.SHAH_ALAM), edu3, workExp3, langs1, skills1));
//        applicants.add(new Applicant("Kristen Smith", "kristensmith@example.com", "0172979922", JobPosting.Type.IT_COMM_TEC, new Location(City.KUCHING), edu1, workExp4, langs1, skills1));
//        applicants.add(new Applicant("Brett Johnston", "brettjohnston@yahoo.com", "0189545912", JobPosting.Type.CALL_CUST_SVC, new Location(City.JOHOR_BAHRU), edu2, workExp3, langs2, skills4));
//        applicants.add(new Applicant("Vanessa Hill", "vanessahill@example.com", "0119371276", JobPosting.Type.SCIENCE_TECH, new Location(City.SEREMBAN), edu2, workExp3, langs3, skills1));
//        applicants.add(new Applicant("Samuel Gilbert", "samuelgilbert@gmail.com", "0189894232", JobPosting.Type.DESIGN_ARCH, new Location(City.KUCHING), edu3, workExp5, langs2, skills4));
//        applicants.add(new Applicant("Stephanie Garcia", "stephaniegarcia@example.com", "0128413387", JobPosting.Type.HEALTH_MED, new Location(City.SUNGAI_PETANI), edu5, workExp5, langs3, skills3));
//        applicants.add(new Applicant("Michael Simmons", "michaelsimmons@example.com", "0162234599", JobPosting.Type.IT_COMM_TEC, new Location(City.KULIM), edu5, workExp1, langs2, skills2));
//        applicants.add(new Applicant("Eric Adams", "ericadams@example.com", "0138346265", JobPosting.Type.SALES, new Location(City.SHAH_ALAM), edu1, workExp1, langs1, skills4));
//        applicants.add(new Applicant("Veronica Chapman", "veronicachapman@example.com", "0174319326", JobPosting.Type.HEALTH_MED, new Location(City.IPOH), edu3, workExp5, langs5, skills2));
//        applicants.add(new Applicant("Claudia Crawford", "claudiacrawford@example.com", "0121601893", JobPosting.Type.IT_COMM_TEC, new Location(City.SEREMBAN), edu3, workExp4, langs3, skills5));
//        applicants.add(new Applicant("Jennifer Nichols", "jennifernichols@example.com", "0125692122", JobPosting.Type.BANK_FIN_SERV, new Location(City.PENANG_HILL), edu3, workExp2, langs3, skills3));
//        applicants.add(new Applicant("Nancy Ellis", "nancyellis@example.com", "0124068746", JobPosting.Type.HEALTH_MED, new Location(City.KUALA_LUMPUR), edu4, workExp1, langs1, skills3));
//        applicants.add(new Applicant("Paul Wong", "paul@example.com", "0178492938", JobPosting.Type.DESIGN_ARCH, new Location(City.TAIPING), edu1, workExp2, langs2, skills4));
//        applicants.add(new Applicant("Allison Richards", "allisonrichards@example.com", "0127789261", JobPosting.Type.SCIENCE_TECH, new Location(City.ALOR_SETAR), edu2, workExp2, langs2, skills1));
//        applicants.add(new Applicant("Megan Williams", "meganwilliams@example.com", "0132731066", JobPosting.Type.HEALTH_MED, new Location(City.KUANTAN_PAHANG), edu4, workExp3, langs1, skills2));
//        applicants.add(new Applicant("Emily Matthews", "emilymatthews@example.com", "0185391872", JobPosting.Type.SALES, new Location(City.MELAKA), edu1, workExp1, langs5, skills5));
//        applicants.add(new Applicant("Anthony Campbell", "anthonycampbell@example.com", "0117745281", JobPosting.Type.CALL_CUST_SVC, new Location(City.JOHOR_BAHRU), edu3, workExp4, langs4, skills4));
//        applicants.add(new Applicant("Christine Dixon", "christinedixon@example.com", "0139264093", JobPosting.Type.HEALTH_MED, new Location(City.KOTA_KINABALU), edu2, workExp2, langs2, skills3));
//        applicants.add(new Applicant("Jason Diaz", "jasondiaz@example.com", "0124735477", JobPosting.Type.DESIGN_ARCH, new Location(City.KUCHING), edu4, workExp3, langs1, skills2));
//        applicants.add(new Applicant("Angela Lopez", "angelalopez@example.com", "0126875274", JobPosting.Type.CALL_CUST_SVC, new Location(City.JOHOR_BAHRU), edu1, workExp3, langs2, skills2));
//        applicants.add(new Applicant("Jeffrey Jones", "jeffreyjones@yahoo.com", "0172723013", JobPosting.Type.CALL_CUST_SVC, new Location(City.ALOR_SETAR), edu4, workExp2, langs5, skills2));
//        applicants.add(new Applicant("Scott Garcia", "scottgarcia@yahoo.com", "0115705683", JobPosting.Type.SCIENCE_TECH, new Location(City.KULIM), edu1, workExp2, langs4, skills5));
//        applicants.add(new Applicant("Crystal Conner", "crystalconner@yahoo.com", "0137346114", JobPosting.Type.SCIENCE_TECH, new Location(City.MELAKA), edu2, workExp2, langs3, skills4));
//        applicants.add(new Applicant("Misty Wilkins", "mistywilkins@example.com", "0130217269", JobPosting.Type.CALL_CUST_SVC, new Location(City.SEREMBAN), edu5, workExp2, langs5, skills1));
//        applicants.add(new Applicant("Ellen Johnson", "ellenjohnson@yahoo.com", "0154773537", JobPosting.Type.IT_COMM_TEC, new Location(City.KUALA_LUMPUR), edu3, workExp3, langs4, skills4));
//        applicants.add(new Applicant("Bradley Wood", "bradleywood@example.com", "0142480634", JobPosting.Type.IT_COMM_TEC, new Location(City.TAIPING), edu3, workExp2, langs2, skills3));
//        applicants.add(new Applicant("Brandon Ross", "brandonross@hotmail.com", "0121727639", JobPosting.Type.SALES, new Location(City.IPOH), edu5, workExp5, langs4, skills5));
//        applicants.add(new Applicant("Patrick Thompson", "patrickthompson@example.com", "0143367993", JobPosting.Type.DESIGN_ARCH, new Location(City.KOTA_KINABALU), edu3, workExp4, langs3, skills3));
//        applicants.add(new Applicant("Stephen Bell", "stephenbell@example.com", "0129421336", JobPosting.Type.SCIENCE_TECH, new Location(City.PENANG_HILL), edu4, workExp4, langs3, skills3));
//        applicants.add(new Applicant("Julie Gonzalez", "juliegonzalez@hotmail.com", "0115192586", JobPosting.Type.SCIENCE_TECH, new Location(City.KUALA_LUMPUR), edu4, workExp3, langs1, skills5));
//        applicants.add(new Applicant("Matthew Stanley", "matthewstanley@example.com", "0174692046", JobPosting.Type.HEALTH_MED, new Location(City.TAIPING), edu2, workExp4, langs1, skills2));
//        applicants.add(new Applicant("Melissa Robinson", "melissarobinson@gmail.com", "0118657014", JobPosting.Type.HEALTH_MED, new Location(City.SHAH_ALAM), edu3, workExp3, langs1, skills1));
//        applicants.add(new Applicant("Kristen Smith", "kristensmith@example.com", "0172979922", JobPosting.Type.IT_COMM_TEC, new Location(City.KUCHING), edu1, workExp4, langs1, skills1));
//        applicants.add(new Applicant("Brett Johnston", "brettjohnston@yahoo.com", "0189545912", JobPosting.Type.CALL_CUST_SVC, new Location(City.JOHOR_BAHRU), edu2, workExp3, langs2, skills4));
//        applicants.add(new Applicant("Vanessa Hill", "vanessahill@example.com", "0119371276", JobPosting.Type.SCIENCE_TECH, new Location(City.SEREMBAN), edu2, workExp3, langs3, skills1));
//        applicants.add(new Applicant("Samuel Gilbert", "samuelgilbert@gmail.com", "0189894232", JobPosting.Type.DESIGN_ARCH, new Location(City.KUCHING), edu3, workExp5, langs2, skills4));
//        applicants.add(new Applicant("Stephanie Garcia", "stephaniegarcia@example.com", "0128413387", JobPosting.Type.HEALTH_MED, new Location(City.SUNGAI_PETANI), edu5, workExp5, langs3, skills3));
//        applicants.add(new Applicant("Michael Simmons", "michaelsimmons@example.com", "0162234599", JobPosting.Type.IT_COMM_TEC, new Location(City.KULIM), edu5, workExp1, langs2, skills2));
//        applicants.add(new Applicant("Eric Adams", "ericadams@example.com", "0138346265", JobPosting.Type.SALES, new Location(City.SHAH_ALAM), edu1, workExp1, langs1, skills4));
//        applicants.add(new Applicant("Veronica Chapman", "veronicachapman@example.com", "0174319326", JobPosting.Type.HEALTH_MED, new Location(City.IPOH), edu3, workExp5, langs5, skills2));
//        applicants.add(new Applicant("Claudia Crawford", "claudiacrawford@example.com", "0121601893", JobPosting.Type.IT_COMM_TEC, new Location(City.SEREMBAN), edu3, workExp4, langs3, skills5));
//        applicants.add(new Applicant("Jennifer Nichols", "jennifernichols@example.com", "0125692122", JobPosting.Type.BANK_FIN_SERV, new Location(City.PENANG_HILL), edu3, workExp2, langs3, skills3));
//        applicants.add(new Applicant("Nancy Ellis", "nancyellis@example.com", "0124068746", JobPosting.Type.HEALTH_MED, new Location(City.KUALA_LUMPUR), edu4, workExp1, langs1, skills3));
//        applicants.add(new Applicant("Douglas Jordan", "douglasjordan@example.com", "0178492938", JobPosting.Type.DESIGN_ARCH, new Location(City.TAIPING), edu1, workExp4, langs4, skills4));
//        applicants.add(new Applicant("Allison Richards", "allisonrichards@example.com", "0127789261", JobPosting.Type.SCIENCE_TECH, new Location(City.ALOR_SETAR), edu2, workExp2, langs2, skills1));
//        applicants.add(new Applicant("Megan Williams", "meganwilliams@example.com", "0132731066", JobPosting.Type.HEALTH_MED, new Location(City.KUANTAN_PAHANG), edu4, workExp3, langs1, skills2));
//        applicants.add(new Applicant("Emily Matthews", "emilymatthews@example.com", "0185391872", JobPosting.Type.SALES, new Location(City.MELAKA), edu1, workExp1, langs5, skills5));
//        applicants.add(new Applicant("Anthony Campbell", "anthonycampbell@example.com", "0117745281", JobPosting.Type.CALL_CUST_SVC, new Location(City.JOHOR_BAHRU), edu3, workExp4, langs4, skills4));
//        applicants.add(new Applicant("Christine Dixon", "christinedixon@example.com", "0139264093", JobPosting.Type.HEALTH_MED, new Location(City.KOTA_KINABALU), edu2, workExp2, langs2, skills3));
//        applicants.add(new Applicant("Jason Diaz", "jasondiaz@example.com", "0124735477", JobPosting.Type.DESIGN_ARCH, new Location(City.KUCHING), edu4, workExp3, langs1, skills2));
        applicants.add(new Applicant(
                "Nur Aisyah", "aisyah01@example.com", "01112345678", JobPosting.Type.HEALTH_MED,
                new Location(City.KUALA_LUMPUR),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.USM, 4.0),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 2));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Patient Care", Skill.ProficiencyLevel.ADVANCED));
            }
        }
        ));

        applicants.add(new Applicant(
                "Lim Wei Ling", "weiling.lim@example.com", "01234567890", JobPosting.Type.HEALTH_MED,
                new Location(City.KOTA_BHARU),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.USM, 3.2),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 2));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Patient Care", Skill.ProficiencyLevel.INTERMEDIATE));
            }
        }
        ));

        applicants.add(new Applicant(
                "Siti Noor", "siti.noor@example.com", "01723456789", JobPosting.Type.HEALTH_MED,
                new Location(City.KUCHING),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.UNIMAS, 3.8),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Network Admin", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant(
                "Siti Aminah", "aminah02@example.com", "01234567890", JobPosting.Type.HEALTH_MED,
                new Location(City.PETALING_JAYA),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.USM, 3.6),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 2));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Patient Care", Skill.ProficiencyLevel.ADVANCED));
            }
        }
        ));

// 2. Assistant Nurse (HEALTH_MED)
        applicants.add(new Applicant(
                "Aminah Binti", "aminah03@example.com", "01311111111", JobPosting.Type.HEALTH_MED,
                new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.UNIMAS, 3.2),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Network Admin", Skill.ProficiencyLevel.ADVANCED));
            }
        }
        ));
        applicants.add(new Applicant(
                "Ming Wei", "mingwei04@example.com", "01422222222", JobPosting.Type.HEALTH_MED,
                new Location(City.MUAR),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.UNIMAS, 3.1),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 4));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Network Admin", Skill.ProficiencyLevel.ADVANCED));
            }
        }
        ));

// 3. Maintenance engineer (ENGINEERING)
        applicants.add(new Applicant(
                "John Doe", "johndoe05@example.com", "01433333333", JobPosting.Type.ENGINEERING,
                new Location(City.KLANG),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.PHAR, EducationLevel.University.UTM, 3.5),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 7));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.BANK_FIN_SERV, "Taxation", Skill.ProficiencyLevel.ADVANCED));
            }
        }
        ));
        applicants.add(new Applicant(
                "Jane Smith", "janesmith06@example.com", "01444444444", JobPosting.Type.ENGINEERING,
                new Location(City.SHAH_ALAM),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.PHAR, EducationLevel.University.UTM, 3.2),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 6));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.BANK_FIN_SERV, "Taxation", Skill.ProficiencyLevel.ADVANCED));
            }
        }
        ));

// 4. Neurosurgeon (BANK_FIN_SERV)
        applicants.add(new Applicant(
                "Adam Lee", "adamlee07@example.com", "01455555555", JobPosting.Type.BANK_FIN_SERV,
                new Location(City.KUALA_LUMPUR),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.BIO, EducationLevel.University.UTM, 3.0),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.SCIENCE_TECH, 2));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.BANK_FIN_SERV, "Investment Analysis", Skill.ProficiencyLevel.ADVANCED));
            }
        }
        ));
        applicants.add(new Applicant(
                "Chloe Tan", "chloetan08@example.com", "01466666666", JobPosting.Type.BANK_FIN_SERV,
                new Location(City.SEREMBAN),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.BIO, EducationLevel.University.UTM, 2.9),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.SCIENCE_TECH, 1));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.BANK_FIN_SERV, "Investment Analysis", Skill.ProficiencyLevel.ADVANCED));
            }
        }
        ));
        applicants.add(new Applicant(
                "Farid Hakim", "farid.hakim@example.com", "01398765432", JobPosting.Type.HEALTH_MED,
                new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.UNIMAS, 3.0),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 4));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Network Admin", Skill.ProficiencyLevel.INTERMEDIATE));
            }
        }
        ));

        applicants.add(new Applicant(
                "Tan Hui Min", "huimin.tan@example.com", "0189999999", JobPosting.Type.HEALTH_MED,
                new Location(City.PENANG_HILL),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.USM, 3.9),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 1));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Patient Care", Skill.ProficiencyLevel.INTERMEDIATE));
            }
        }
        ));

        applicants.add(new Applicant("Allison Hill", "applicant1@example.com", "012-1419610", JobPosting.Type.DESIGN_ARCH, new Location(City.KUANTAN_PAHANG),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.SE, EducationLevel.University.USM, 3.6),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 2));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.KR, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.DESIGN_ARCH, "AutoCAD", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Noah Rhodes", "applicant2@example.com", "011-2571945", JobPosting.Type.ACCOUNTING, new Location(City.KUALA_LUMPUR),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.UM, 3.34),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 7));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "QuickBooks", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Angie Henderson", "applicant3@example.com", "015-1109031", JobPosting.Type.ACCOUNTING, new Location(City.SUNGAI_PETANI),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.CE, EducationLevel.University.UTM, 2.73),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 2));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Tax Filing", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Daniel Wagner", "applicant4@example.com", "016-6770619", JobPosting.Type.DESIGN_ARCH, new Location(City.MELAKA),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.ACC, EducationLevel.University.UCSI, 3.3),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HR_RECRUIT, 2));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.KR, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.DESIGN_ARCH, "Revit", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Cristian Santos", "applicant5@example.com", "016-4226067", JobPosting.Type.IT_COMM_TEC, new Location(City.KUANTAN_PAHANG),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.ARCH, EducationLevel.University.UPM, 3.66),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 4));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Database Management", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Connie Lawrence", "applicant6@example.com", "018-7120868", JobPosting.Type.DESIGN_ARCH, new Location(City.KUALA_LUMPUR),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.SE, EducationLevel.University.UTM, 3.55),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 10));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.FR, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.DESIGN_ARCH, "UI Design", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Abigail Shaffer", "applicant7@example.com", "014-3741438", JobPosting.Type.HR_RECRUIT, new Location(City.KOTA_KINABALU),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.ARCH, EducationLevel.University.TAYLORS, 2.83),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 1));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HR_RECRUIT, "HR Policies", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Gina Moore", "applicant8@example.com", "017-5491946", JobPosting.Type.ACCOUNTING, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.ACC, EducationLevel.University.UiTM, 2.82),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HR_RECRUIT, 7));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.FR, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Budgeting", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Gabrielle Davis", "applicant9@example.com", "015-3342608", JobPosting.Type.HEALTH_MED, new Location(City.KUALA_LUMPUR),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.AI, EducationLevel.University.SUNWAY, 3.14),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 7));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Medical Terminology", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Ryan Munoz", "applicant10@example.com", "019-9279821", JobPosting.Type.IT_COMM_TEC, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.DS, EducationLevel.University.USM, 3.69),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HR_RECRUIT, 10));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Machine Learning", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Monica Herrera", "applicant11@example.com", "018-9877065", JobPosting.Type.HEALTH_MED, new Location(City.IPOH),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.ARCH, EducationLevel.University.UKM, 3.52),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.FR, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "CPR", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Jamie Arnold", "applicant12@example.com", "015-8294150", JobPosting.Type.HR_RECRUIT, new Location(City.KUALA_LUMPUR),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.ACC, EducationLevel.University.UTM, 3.96),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.ACCOUNTING, 9));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HR_RECRUIT, "Compliance", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Lisa Hensley", "applicant13@example.com", "014-3564217", JobPosting.Type.ACCOUNTING, new Location(City.IPOH),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.MED, EducationLevel.University.UM, 3.4),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HR_RECRUIT, 1));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Payroll", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Michele Williams", "applicant14@example.com", "014-1971823", JobPosting.Type.HEALTH_MED, new Location(City.KUALA_LUMPUR),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.IT, EducationLevel.University.UCSI, 3.72),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 3));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Phlebotomy", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Dylan Miller", "applicant15@example.com", "013-5446912", JobPosting.Type.HEALTH_MED, new Location(City.MELAKA),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.SE, EducationLevel.University.TAYLORS, 3.63),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.ACCOUNTING, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Health Records", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Brian Ramirez", "applicant16@example.com", "019-8574680", JobPosting.Type.ACCOUNTING, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.IT, EducationLevel.University.UiTM, 2.53),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 4));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.KR, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Bookkeeping", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Holly Wood", "applicant17@example.com", "012-1987737", JobPosting.Type.IT_COMM_TEC, new Location(City.KUALA_LUMPUR),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.CE, EducationLevel.University.UKM, 3.27),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 8));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Software Testing", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Derek Zuniga", "applicant18@example.com", "018-5076817", JobPosting.Type.HR_RECRUIT, new Location(City.SUNGAI_PETANI),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.SE, EducationLevel.University.UKM, 2.65),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HR_RECRUIT, 6));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HR_RECRUIT, "Conflict Resolution", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Lisa Jackson", "applicant19@example.com", "011-2651177", JobPosting.Type.HR_RECRUIT, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.IT, EducationLevel.University.UPM, 2.79),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 8));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HR_RECRUIT, "Onboarding", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Carla Gray", "applicant20@example.com", "015-8761611", JobPosting.Type.IT_COMM_TEC, new Location(City.KUALA_LUMPUR),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.MED, EducationLevel.University.UKM, 2.58),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 1));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Web Development", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Margaret Hawkins DDS", "applicant21@example.com", "017-9147706", JobPosting.Type.ACCOUNTING, new Location(City.KOTA_KINABALU),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.CS, EducationLevel.University.USM, 3.07),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HR_RECRUIT, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Accounts Payable", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Patty Perez", "applicant22@example.com", "019-9164991", JobPosting.Type.ACCOUNTING, new Location(City.KUALA_LUMPUR),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.SE, EducationLevel.University.UM, 3.37),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 1));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.FR, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Bookkeeping", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Ethan Adams", "applicant23@example.com", "011-8999183", JobPosting.Type.HEALTH_MED, new Location(City.MELAKA),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.CS, EducationLevel.University.TAYLORS, 2.62),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.ACCOUNTING, 2));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.KR, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Patient Care", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Tommy Walter", "applicant24@example.com", "017-3011363", JobPosting.Type.ACCOUNTING, new Location(City.MELAKA),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.UM, 3.43),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HR_RECRUIT, 10));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.KR, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Payroll", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Matthew Foster", "applicant25@example.com", "014-6271130", JobPosting.Type.DESIGN_ARCH, new Location(City.KUALA_LUMPUR),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.DS, EducationLevel.University.UTM, 3.19),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 1));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.DESIGN_ARCH, "SketchUp", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Judy Baker", "applicant26@example.com", "019-4576135", JobPosting.Type.DESIGN_ARCH, new Location(City.MELAKA),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.CE, EducationLevel.University.UKM, 3.82),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.DESIGN_ARCH, "UI Design", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Justin Baker", "applicant27@example.com", "015-9874152", JobPosting.Type.HEALTH_MED, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.ARCH, EducationLevel.University.UKM, 3.91),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.ACCOUNTING, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Phlebotomy", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Stephanie Ross", "applicant28@example.com", "013-5569244", JobPosting.Type.HEALTH_MED, new Location(City.IPOH),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.ACC, EducationLevel.University.UiTM, 2.81),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 9));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "First Aid", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Zachary Hicks", "applicant29@example.com", "012-8106422", JobPosting.Type.DESIGN_ARCH, new Location(City.SUNGAI_PETANI),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.CS, EducationLevel.University.UiTM, 3.66),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 3));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.FR, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.DESIGN_ARCH, "UI Design", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Anthony Rodriguez", "applicant30@example.com", "017-1162230", JobPosting.Type.IT_COMM_TEC, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.MED, EducationLevel.University.UM, 3.75),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 9));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Web Development", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Rebecca Henderson", "applicant31@example.com", "011-6171716", JobPosting.Type.IT_COMM_TEC, new Location(City.IPOH),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.SE, EducationLevel.University.UPM, 3.5),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 9));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Cybersecurity", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("James Ferrell", "applicant32@example.com", "013-3970445", JobPosting.Type.IT_COMM_TEC, new Location(City.KOTA_KINABALU),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.ACC, EducationLevel.University.UiTM, 3.67),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HR_RECRUIT, 4));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Python Development", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Tricia Valencia", "applicant33@example.com", "017-1649691", JobPosting.Type.HR_RECRUIT, new Location(City.SUNGAI_PETANI),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.SE, EducationLevel.University.UCSI, 3.02),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.ACCOUNTING, 4));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HR_RECRUIT, "Performance Reviews", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Nathan Maldonado", "applicant34@example.com", "016-5674183", JobPosting.Type.IT_COMM_TEC, new Location(City.SUNGAI_PETANI),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.CE, EducationLevel.University.TAYLORS, 3.1),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 6));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Database Management", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Debra Davidson", "applicant35@example.com", "013-5453786", JobPosting.Type.IT_COMM_TEC, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.BBA, EducationLevel.University.UiTM, 3.59),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 7));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.KR, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Machine Learning", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Jeffrey Chavez", "applicant36@example.com", "014-5273534", JobPosting.Type.HR_RECRUIT, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.MED, EducationLevel.University.TAYLORS, 3.53),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.ACCOUNTING, 6));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HR_RECRUIT, "HR Policies", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Sherri Baker", "applicant37@example.com", "016-3090865", JobPosting.Type.DESIGN_ARCH, new Location(City.KUANTAN_PAHANG),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.AI, EducationLevel.University.UUM, 2.99),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 9));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.DESIGN_ARCH, "Illustrator", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Cassandra Gaines", "applicant38@example.com", "017-3919694", JobPosting.Type.HEALTH_MED, new Location(City.MELAKA),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.BBA, EducationLevel.University.TAYLORS, 3.75),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Radiology Basics", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Elizabeth Fowler", "applicant39@example.com", "016-8801207", JobPosting.Type.HR_RECRUIT, new Location(City.KOTA_KINABALU),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.MED, EducationLevel.University.UCSI, 3.69),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.ACCOUNTING, 2));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HR_RECRUIT, "Interviewing", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Brittany Farmer", "applicant40@example.com", "014-6207974", JobPosting.Type.ACCOUNTING, new Location(City.KUALA_LUMPUR),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.CS, EducationLevel.University.UM, 2.87),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HR_RECRUIT, 10));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Accounts Payable", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Paula Moreno", "applicant41@example.com", "014-7442089", JobPosting.Type.HR_RECRUIT, new Location(City.KOTA_KINABALU),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.DS, EducationLevel.University.UM, 3.84),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 7));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HR_RECRUIT, "Compliance", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Fred Smith", "applicant42@example.com", "018-1842524", JobPosting.Type.ACCOUNTING, new Location(City.MELAKA),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.FIN, EducationLevel.University.USM, 3.7),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 9));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.KR, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Financial Reporting", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Sherry Decker", "applicant43@example.com", "019-8159521", JobPosting.Type.HEALTH_MED, new Location(City.SUNGAI_PETANI),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.DS, EducationLevel.University.UCSI, 3.18),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.ACCOUNTING, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.KR, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Patient Care", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Anthony Humphrey", "applicant44@example.com", "015-8379837", JobPosting.Type.DESIGN_ARCH, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.AI, EducationLevel.University.UiTM, 2.98),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 2));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.DESIGN_ARCH, "Interior Design", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Angelica Tucker", "applicant45@example.com", "017-3563646", JobPosting.Type.ACCOUNTING, new Location(City.KUANTAN_PAHANG),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.BBA, EducationLevel.University.UUM, 3.0),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HR_RECRUIT, 7));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Accounts Payable", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Philip Cannon", "applicant46@example.com", "017-1327691", JobPosting.Type.HEALTH_MED, new Location(City.SUNGAI_PETANI),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.FIN, EducationLevel.University.UM, 3.91),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 7));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Health Records", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("John Pierce", "applicant47@example.com", "014-5579094", JobPosting.Type.HR_RECRUIT, new Location(City.KOTA_KINABALU),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.BBA, EducationLevel.University.UiTM, 3.5),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HR_RECRUIT, 3));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HR_RECRUIT, "HRIS Systems", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Shane Henderson", "applicant48@example.com", "019-1452421", JobPosting.Type.HEALTH_MED, new Location(City.KOTA_KINABALU),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.ARCH, EducationLevel.University.UM, 2.63),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HR_RECRUIT, 3));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "First Aid", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Joshua Blair", "applicant49@example.com", "015-7360307", JobPosting.Type.ACCOUNTING, new Location(City.IPOH),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.CE, EducationLevel.University.UiTM, 3.64),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HR_RECRUIT, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Tax Filing", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Eric Carney", "applicant50@example.com", "018-1325197", JobPosting.Type.HEALTH_MED, new Location(City.KUANTAN_PAHANG),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.CE, EducationLevel.University.UPM, 3.48),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 1));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "First Aid", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Jessica Holmes", "applicant51@example.com", "013-5002120", JobPosting.Type.HR_RECRUIT, new Location(City.KUALA_LUMPUR),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.UPM, 3.2),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 6));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HR_RECRUIT, "Onboarding", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Danny Morgan", "applicant52@example.com", "015-2813596", JobPosting.Type.IT_COMM_TEC, new Location(City.MELAKA),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.UUM, 3.09),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.ACCOUNTING, 2));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.KR, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Python Development", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Crystal Robinson", "applicant53@example.com", "015-3030966", JobPosting.Type.HEALTH_MED, new Location(City.SUNGAI_PETANI),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.CE, EducationLevel.University.TAYLORS, 3.14),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 2));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.KR, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "First Aid", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Mark Perez", "applicant54@example.com", "017-9224219", JobPosting.Type.HR_RECRUIT, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.ARCH, EducationLevel.University.UCSI, 3.56),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HR_RECRUIT, 3));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.FR, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HR_RECRUIT, "HRIS Systems", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Shannon Jones", "applicant55@example.com", "019-9111742", JobPosting.Type.HR_RECRUIT, new Location(City.KOTA_KINABALU),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.AI, EducationLevel.University.UiTM, 3.78),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HR_RECRUIT, "Conflict Resolution", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Michael Lewis", "applicant56@example.com", "017-6643798", JobPosting.Type.HR_RECRUIT, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.DS, EducationLevel.University.UCSI, 2.82),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 6));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HR_RECRUIT, "Compliance", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Timothy Duncan", "applicant57@example.com", "011-9667566", JobPosting.Type.IT_COMM_TEC, new Location(City.KUALA_LUMPUR),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.ACC, EducationLevel.University.UUM, 3.23),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.ACCOUNTING, 8));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.FR, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Networking", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Richard Aguirre", "applicant58@example.com", "011-2561176", JobPosting.Type.ACCOUNTING, new Location(City.IPOH),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.ACC, EducationLevel.University.UPM, 2.96),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 6));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Accounts Payable", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Brent Jordan", "applicant59@example.com", "019-6549757", JobPosting.Type.HR_RECRUIT, new Location(City.IPOH),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.AI, EducationLevel.University.UTM, 2.85),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.ACCOUNTING, 6));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HR_RECRUIT, "Payroll Processing", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("John Daniel", "applicant60@example.com", "014-9123504", JobPosting.Type.HEALTH_MED, new Location(City.IPOH),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.UTM, 3.97),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.ACCOUNTING, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Medical Terminology", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Victoria Garcia", "applicant61@example.com", "015-1237370", JobPosting.Type.HEALTH_MED, new Location(City.KUANTAN_PAHANG),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.AI, EducationLevel.University.UM, 3.96),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.FR, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Health Records", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Gerald Hensley", "applicant62@example.com", "012-1205779", JobPosting.Type.DESIGN_ARCH, new Location(City.MELAKA),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.FIN, EducationLevel.University.UCSI, 3.01),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.DESIGN_ARCH, "SketchUp", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Connor West", "applicant63@example.com", "017-9250138", JobPosting.Type.HEALTH_MED, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.DS, EducationLevel.University.USM, 3.72),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 2));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Phlebotomy", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Donald Wright", "applicant64@example.com", "017-4786401", JobPosting.Type.HEALTH_MED, new Location(City.SUNGAI_PETANI),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.FIN, EducationLevel.University.UCSI, 2.95),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 7));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Radiology Basics", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Jessica Callahan", "applicant65@example.com", "012-4486102", JobPosting.Type.ACCOUNTING, new Location(City.KUANTAN_PAHANG),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.ARCH, EducationLevel.University.UKM, 2.74),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.ACCOUNTING, 9));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Bookkeeping", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Amber Kidd", "applicant66@example.com", "017-8558197", JobPosting.Type.HEALTH_MED, new Location(City.KUANTAN_PAHANG),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.AI, EducationLevel.University.UM, 2.85),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 8));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Nursing Skills", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Tracy House", "applicant67@example.com", "014-8132648", JobPosting.Type.HEALTH_MED, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.ARCH, EducationLevel.University.USM, 3.86),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.ACCOUNTING, 2));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Nursing Skills", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Carol Tucker", "applicant68@example.com", "015-8367772", JobPosting.Type.HR_RECRUIT, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.ACC, EducationLevel.University.UUM, 3.91),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 9));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HR_RECRUIT, "Interviewing", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("James Brown", "applicant69@example.com", "011-8248238", JobPosting.Type.DESIGN_ARCH, new Location(City.KUANTAN_PAHANG),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.AI, EducationLevel.University.UM, 2.64),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 10));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.DESIGN_ARCH, "Revit", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("William Baker", "applicant70@example.com", "011-3939212", JobPosting.Type.HEALTH_MED, new Location(City.KOTA_KINABALU),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.AI, EducationLevel.University.USM, 4.0),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HR_RECRUIT, 8));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Clinical Procedures", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Jordan Henderson", "applicant71@example.com", "017-6591986", JobPosting.Type.IT_COMM_TEC, new Location(City.IPOH),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.CE, EducationLevel.University.UUM, 3.54),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 7));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.KR, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Networking", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Whitney Peters", "applicant72@example.com", "012-6276722", JobPosting.Type.DESIGN_ARCH, new Location(City.IPOH),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.BBA, EducationLevel.University.TAYLORS, 3.74),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 9));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.DESIGN_ARCH, "AutoCAD", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Brittney Phillips", "applicant73@example.com", "014-9697969", JobPosting.Type.HEALTH_MED, new Location(City.IPOH),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.ARCH, EducationLevel.University.UCSI, 3.64),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.ACCOUNTING, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.KR, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Nursing Skills", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Lauren Daniels", "applicant74@example.com", "018-9132114", JobPosting.Type.IT_COMM_TEC, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.SE, EducationLevel.University.USM, 2.97),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 9));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Cybersecurity", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Amy Silva", "applicant75@example.com", "012-8741810", JobPosting.Type.ACCOUNTING, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.ACC, EducationLevel.University.UTM, 3.26),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 7));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Auditing", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Sarah Moore", "applicant76@example.com", "018-3426689", JobPosting.Type.ACCOUNTING, new Location(City.KOTA_KINABALU),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.MED, EducationLevel.University.USM, 3.8),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 7));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Bookkeeping", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Angela Lopez", "applicant77@example.com", "015-6008935", JobPosting.Type.HEALTH_MED, new Location(City.SUNGAI_PETANI),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.ARCH, EducationLevel.University.UCSI, 3.8),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HR_RECRUIT, 9));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Clinical Procedures", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Megan Young", "applicant78@example.com", "019-7328534", JobPosting.Type.DESIGN_ARCH, new Location(City.KOTA_KINABALU),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.ACC, EducationLevel.University.UPM, 3.36),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.ACCOUNTING, 7));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.DESIGN_ARCH, "UX Design", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Steve Sanchez", "applicant79@example.com", "017-7477326", JobPosting.Type.ACCOUNTING, new Location(City.KUANTAN_PAHANG),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.CS, EducationLevel.University.USM, 3.25),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 6));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Tax Filing", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Lisa Barnes", "applicant80@example.com", "019-8665981", JobPosting.Type.ACCOUNTING, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.ARCH, EducationLevel.University.USM, 2.61),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 6));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.KR, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Tax Filing", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Kelly Donovan", "applicant81@example.com", "016-9952696", JobPosting.Type.DESIGN_ARCH, new Location(City.KOTA_KINABALU),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.MED, EducationLevel.University.UM, 3.43),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.ACCOUNTING, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.DESIGN_ARCH, "Illustrator", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Mike Allen", "applicant82@example.com", "012-2686231", JobPosting.Type.ACCOUNTING, new Location(City.KOTA_KINABALU),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.CS, EducationLevel.University.UM, 2.99),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Accounts Payable", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Michael Evans", "applicant83@example.com", "013-5096953", JobPosting.Type.HR_RECRUIT, new Location(City.MELAKA),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.ARCH, EducationLevel.University.USM, 2.75),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 10));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HR_RECRUIT, "Conflict Resolution", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Paul Jones", "applicant84@example.com", "013-4895475", JobPosting.Type.DESIGN_ARCH, new Location(City.KOTA_KINABALU),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.AI, EducationLevel.University.UM, 3.85),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HR_RECRUIT, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.FR, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.DESIGN_ARCH, "SketchUp", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Zachary Ferrell", "applicant85@example.com", "018-6797629", JobPosting.Type.DESIGN_ARCH, new Location(City.MELAKA),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.ACC, EducationLevel.University.UTM, 3.19),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 4));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.DESIGN_ARCH, "SketchUp", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Daniel Baker", "applicant86@example.com", "014-7398231", JobPosting.Type.DESIGN_ARCH, new Location(City.MELAKA),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.AI, EducationLevel.University.UTM, 2.53),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HR_RECRUIT, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.DESIGN_ARCH, "Revit", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Shirley Suarez", "applicant87@example.com", "018-5801541", JobPosting.Type.ACCOUNTING, new Location(City.SUNGAI_PETANI),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.CE, EducationLevel.University.UPM, 3.45),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.FR, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Tax Filing", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Christopher Bass", "applicant88@example.com", "011-6183033", JobPosting.Type.HR_RECRUIT, new Location(City.SUNGAI_PETANI),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.UiTM, 3.6),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HR_RECRUIT, "Onboarding", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Lisa Archer", "applicant89@example.com", "014-3217273", JobPosting.Type.HEALTH_MED, new Location(City.SUNGAI_PETANI),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.MED, EducationLevel.University.TAYLORS, 3.87),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.ACCOUNTING, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Clinical Procedures", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Aimee Montoya", "applicant90@example.com", "012-8857296", JobPosting.Type.ACCOUNTING, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.ARCH, EducationLevel.University.UUM, 3.95),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 6));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Bookkeeping", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Matthew Mcmillan", "applicant91@example.com", "015-3073474", JobPosting.Type.DESIGN_ARCH, new Location(City.KOTA_KINABALU),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.UUM, 3.73),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 2));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.FR, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.DESIGN_ARCH, "UX Design", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Brian Rodriguez", "applicant92@example.com", "011-6503032", JobPosting.Type.ACCOUNTING, new Location(City.MELAKA),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.ARCH, EducationLevel.University.UCSI, 3.86),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 7));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Bookkeeping", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Denise Jacobs", "applicant93@example.com", "011-6106390", JobPosting.Type.IT_COMM_TEC, new Location(City.KOTA_KINABALU),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.SE, EducationLevel.University.TAYLORS, 2.7),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HR_RECRUIT, 6));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.FR, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Software Testing", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Christina Walters", "applicant94@example.com", "013-7960607", JobPosting.Type.IT_COMM_TEC, new Location(City.KUANTAN_PAHANG),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.UUM, 3.91),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 1));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.FR, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Cybersecurity", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("John Whitehead", "applicant95@example.com", "018-8460758", JobPosting.Type.DESIGN_ARCH, new Location(City.KUALA_LUMPUR),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.ARCH, EducationLevel.University.UiTM, 3.32),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 1));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.DESIGN_ARCH, "Interior Design", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Anna Henderson", "applicant96@example.com", "012-8628891", JobPosting.Type.ACCOUNTING, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.CS, EducationLevel.University.UM, 3.68),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.ACCOUNTING, 3));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.KR, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Tax Filing", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Aaron Wise", "applicant97@example.com", "019-4475523", JobPosting.Type.ACCOUNTING, new Location(City.MELAKA),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.CE, EducationLevel.University.USM, 3.68),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 1));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Budgeting", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Deborah Figueroa", "applicant98@example.com", "019-5205279", JobPosting.Type.ACCOUNTING, new Location(City.SUNGAI_PETANI),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.ARCH, EducationLevel.University.UM, 2.7),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 4));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.KR, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Bookkeeping", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Jessica Smith", "applicant99@example.com", "013-5452128", JobPosting.Type.ACCOUNTING, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.MED, EducationLevel.University.UKM, 3.62),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HR_RECRUIT, 8));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Financial Reporting", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Stephen Mckee", "applicant100@example.com", "019-4716988", JobPosting.Type.IT_COMM_TEC, new Location(City.MELAKA),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.AI, EducationLevel.University.UCSI, 3.47),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 1));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.ELEMENTARY));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Machine Learning", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Sandra Aguilar", "applicant101@example.com", "012-9225346", JobPosting.Type.HR_RECRUIT, new Location(City.KUANTAN_PAHANG),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.IT, EducationLevel.University.UiTM, 3.41),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 3));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HR_RECRUIT, "Performance Reviews", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Cameron Parker", "applicant102@example.com", "019-5947563", JobPosting.Type.HEALTH_MED, new Location(City.KOTA_KINABALU),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.BBA, EducationLevel.University.UKM, 3.69),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 9));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.FR, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Medical Coding", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Rebecca Valencia", "applicant103@example.com", "018-4833486", JobPosting.Type.DESIGN_ARCH, new Location(City.KOTA_KINABALU),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.BBA, EducationLevel.University.UUM, 3.59),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 7));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.DESIGN_ARCH, "Photoshop", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Christine Wright", "applicant104@example.com", "013-8956894", JobPosting.Type.IT_COMM_TEC, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.IT, EducationLevel.University.UUM, 2.64),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 3));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.KR, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Software Testing", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Richard Henson", "applicant105@example.com", "019-6530541", JobPosting.Type.IT_COMM_TEC, new Location(City.KUANTAN_PAHANG),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.CE, EducationLevel.University.UUM, 3.8),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.KR, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Cloud Computing", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Marc Moore", "applicant106@example.com", "012-9512969", JobPosting.Type.ACCOUNTING, new Location(City.KUALA_LUMPUR),
                new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.SE, EducationLevel.University.UKM, 3.03),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 6));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "QuickBooks", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Sharon Cherry", "applicant107@example.com", "014-8199311", JobPosting.Type.HEALTH_MED, new Location(City.SUNGAI_PETANI),
                new EducationLevel(EducationLevel.DegreeLevel.SC, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.TAYLORS, 2.54),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.DESIGN_ARCH, 1));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Nursing Skills", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant("Sierra Johnson", "applicant108@example.com", "016-6889195", JobPosting.Type.ACCOUNTING, new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.UUM, 2.6),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 2));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.FR, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.ACCOUNTING, "Accounts Payable", Skill.ProficiencyLevel.EXPERT));
            }
        }
        ));
        applicants.add(new Applicant(
                "Bin Chong", "bin01@example.com", "01112345678", JobPosting.Type.HEALTH_MED,
                new Location(City.KUALA_LUMPUR),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.APU, 3.6),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 2));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Patient Care", Skill.ProficiencyLevel.BEGINNER));
            }
        }
        ));
        applicants.add(new Applicant(
                "Siti Aminah", "aminah02@example.com", "01234567890", JobPosting.Type.HEALTH_MED,
                new Location(City.PETALING_JAYA),
                new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.USM, 3.6),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 2));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.HEALTH_MED, "Patient Care", Skill.ProficiencyLevel.ADVANCED));
            }
        }
        ));
        applicants.add(new Applicant(
                "Aminah Binti", "aminah03@example.com", "01311111111", JobPosting.Type.HEALTH_MED,
                new Location(City.JOHOR_BAHRU),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.UNIMAS, 3.2),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 5));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Network Admin", Skill.ProficiencyLevel.ADVANCED));
            }
        }
        ));
        applicants.add(new Applicant(
                "Ming Wei", "mingwei04@example.com", "01422222222", JobPosting.Type.HEALTH_MED,
                new Location(City.MUAR),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.NUR, EducationLevel.University.UNIMAS, 3.1),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 4));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.IT_COMM_TEC, "Network Admin", Skill.ProficiencyLevel.ADVANCED));
            }
        }
        ));
        applicants.add(new Applicant(
                "John Doe", "johndoe05@example.com", "01433333333", JobPosting.Type.ENGINEERING,
                new Location(City.KLANG),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.PHAR, EducationLevel.University.UTM, 3.5),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 7));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.BANK_FIN_SERV, "Taxation", Skill.ProficiencyLevel.ADVANCED));
            }
        }
        ));
        applicants.add(new Applicant(
                "Jane Smith", "janesmith06@example.com", "01444444444", JobPosting.Type.ENGINEERING,
                new Location(City.SHAH_ALAM),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.PHAR, EducationLevel.University.UTM, 3.2),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 6));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.NATIVE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.BANK_FIN_SERV, "Taxation", Skill.ProficiencyLevel.ADVANCED));
            }
        }
        ));
        applicants.add(new Applicant(
                "Adam Lee", "adamlee07@example.com", "01455555555", JobPosting.Type.BANK_FIN_SERV,
                new Location(City.KUALA_LUMPUR),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.BIO, EducationLevel.University.UTM, 3.0),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.SCIENCE_TECH, 2));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.BANK_FIN_SERV, "Investment Analysis", Skill.ProficiencyLevel.ADVANCED));
            }
        }
        ));
        applicants.add(new Applicant(
                "Chloe Tan", "chloetan08@example.com", "01466666666", JobPosting.Type.BANK_FIN_SERV,
                new Location(City.SEREMBAN),
                new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.BIO, EducationLevel.University.UTM, 2.9),
                new DoublyLinkedList<WorkExperience>() {
            {
                add(new WorkExperience(WorkExperience.Industry.SCIENCE_TECH, 1));
            }
        },
                new DoublyLinkedList<LanguageProficiency>() {
            {
                add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.INTERMEDIATE));
            }
        },
                new DoublyLinkedList<Skill>() {
            {
                add(new Skill(JobPosting.Type.BANK_FIN_SERV, "Investment Analysis", Skill.ProficiencyLevel.ADVANCED));
            }
        }
        ));
        jobApplications.add(new JobApplication(jobPostings.get(0), applicants.get(0), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 1)));
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(19), JobApplication.Status.WITHDRAWN, LocalDate.of(2025, 3, 29)));
        jobApplications.add(new JobApplication(jobPostings.get(28), applicants.get(35), JobApplication.Status.OFFERED, LocalDate.of(2025, 3, 9)));
        jobApplications.add(new JobApplication(jobPostings.get(14), applicants.get(20), JobApplication.Status.SHORTLISTED, LocalDate.of(2025, 3, 28)));
        jobApplications.add(new JobApplication(jobPostings.get(33), applicants.get(6), JobApplication.Status.REJECTED, LocalDate.of(2025, 3, 14)));
        jobApplications.add(new JobApplication(jobPostings.get(48), applicants.get(10), JobApplication.Status.PENDING, LocalDate.of(2025, 4, 12)));
        jobApplications.add(new JobApplication(jobPostings.get(17), applicants.get(2), JobApplication.Status.INTERVIEWED, LocalDate.of(2025, 3, 18)));
        jobApplications.add(new JobApplication(jobPostings.get(11), applicants.get(4), JobApplication.Status.ACCEPTED, LocalDate.of(2025, 4, 2)));
        jobApplications.add(new JobApplication(jobPostings.get(7), applicants.get(26), JobApplication.Status.OFFERED, LocalDate.of(2025, 3, 7)));
        jobApplications.add(new JobApplication(jobPostings.get(10), applicants.get(0), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 1)));
        jobApplications.add(new JobApplication(jobPostings.get(21), applicants.get(9), JobApplication.Status.REJECTED, LocalDate.of(2025, 3, 25)));
        jobApplications.add(new JobApplication(jobPostings.get(5), applicants.get(14), JobApplication.Status.SHORTLISTED, LocalDate.of(2025, 4, 10)));
        jobApplications.add(new JobApplication(jobPostings.get(36), applicants.get(23), JobApplication.Status.WITHDRAWN, LocalDate.of(2025, 3, 19)));
        jobApplications.add(new JobApplication(jobPostings.get(19), applicants.get(8), JobApplication.Status.ACCEPTED, LocalDate.of(2025, 3, 11)));
        jobApplications.add(new JobApplication(jobPostings.get(46), applicants.get(33), JobApplication.Status.INTERVIEWED, LocalDate.of(2025, 3, 17)));
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(12), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 3)));
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(38), JobApplication.Status.OFFERED, LocalDate.of(2025, 3, 27)));
        jobApplications.add(new JobApplication(jobPostings.get(3), applicants.get(1), JobApplication.Status.SHORTLISTED, LocalDate.of(2025, 3, 16)));
        jobApplications.add(new JobApplication(jobPostings.get(31), applicants.get(29), JobApplication.Status.INTERVIEWED, LocalDate.of(2025, 3, 22)));
        jobApplications.add(new JobApplication(jobPostings.get(42), applicants.get(11), JobApplication.Status.REJECTED, LocalDate.of(2025, 3, 8)));
        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(5), JobApplication.Status.SHORTLISTED, LocalDate.of(2025, 3, 20)));
        jobApplications.add(new JobApplication(jobPostings.get(2), applicants.get(18), JobApplication.Status.OFFERED, LocalDate.of(2025, 3, 24)));
        jobApplications.add(new JobApplication(jobPostings.get(6), applicants.get(24), JobApplication.Status.ACCEPTED, LocalDate.of(2025, 4, 1)));
        jobApplications.add(new JobApplication(jobPostings.get(37), applicants.get(41), JobApplication.Status.INTERVIEWED, LocalDate.of(2025, 3, 12)));
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(13), JobApplication.Status.REJECTED, LocalDate.of(2025, 3, 5)));
        jobApplications.add(new JobApplication(jobPostings.get(12), applicants.get(22), JobApplication.Status.WITHDRAWN, LocalDate.of(2025, 3, 26)));
        jobApplications.add(new JobApplication(jobPostings.get(23), applicants.get(28), JobApplication.Status.SHORTLISTED, LocalDate.of(2025, 3, 13)));
//        jobApplications.add(new JobApplication(jobPostings.get(50), applicants.get(16), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 21)));
        jobApplications.add(new JobApplication(jobPostings.get(10), applicants.get(30), JobApplication.Status.OFFERED, LocalDate.of(2025, 3, 10)));
        jobApplications.add(new JobApplication(jobPostings.get(38), applicants.get(32), JobApplication.Status.ACCEPTED, LocalDate.of(2025, 3, 4)));
        jobApplications.add(new JobApplication(jobPostings.get(18), applicants.get(25), JobApplication.Status.SHORTLISTED, LocalDate.of(2025, 3, 6)));
        jobApplications.add(new JobApplication(jobPostings.get(13), applicants.get(34), JobApplication.Status.REJECTED, LocalDate.of(2025, 3, 30)));
        jobApplications.add(new JobApplication(jobPostings.get(27), applicants.get(17), JobApplication.Status.WITHDRAWN, LocalDate.of(2025, 3, 2)));
        jobApplications.add(new JobApplication(jobPostings.get(1), applicants.get(36), JobApplication.Status.INTERVIEWED, LocalDate.of(2025, 3, 31)));
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(15), JobApplication.Status.ACCEPTED, LocalDate.of(2025, 3, 15)));
        jobApplications.add(new JobApplication(jobPostings.get(8), applicants.get(31), JobApplication.Status.SHORTLISTED, LocalDate.of(2025, 3, 23)));
        jobApplications.add(new JobApplication(jobPostings.get(22), applicants.get(37), JobApplication.Status.REJECTED, LocalDate.of(2025, 3, 18)));
        jobApplications.add(new JobApplication(jobPostings.get(9), applicants.get(39), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 28)));
        jobApplications.add(new JobApplication(jobPostings.get(26), applicants.get(7), JobApplication.Status.OFFERED, LocalDate.of(2025, 3, 17)));
        jobApplications.add(new JobApplication(jobPostings.get(47), applicants.get(43), JobApplication.Status.WITHDRAWN, LocalDate.of(2025, 4, 5)));
        jobApplications.add(new JobApplication(jobPostings.get(34), applicants.get(40), JobApplication.Status.SHORTLISTED, LocalDate.of(2025, 3, 6)));
        jobApplications.add(new JobApplication(jobPostings.get(16), applicants.get(21), JobApplication.Status.ACCEPTED, LocalDate.of(2025, 4, 4)));
        jobApplications.add(new JobApplication(jobPostings.get(39), applicants.get(45), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 1)));
        jobApplications.add(new JobApplication(jobPostings.get(43), applicants.get(27), JobApplication.Status.REJECTED, LocalDate.of(2025, 3, 30)));
        jobApplications.add(new JobApplication(jobPostings.get(4), applicants.get(44), JobApplication.Status.SHORTLISTED, LocalDate.of(2025, 3, 22)));
        jobApplications.add(new JobApplication(jobPostings.get(29), applicants.get(42), JobApplication.Status.OFFERED, LocalDate.of(2025, 4, 3)));
        jobApplications.add(new JobApplication(jobPostings.get(49), applicants.get(46), JobApplication.Status.INTERVIEWED, LocalDate.of(2025, 3, 9)));
        jobApplications.add(new JobApplication(jobPostings.get(24), applicants.get(48), JobApplication.Status.ACCEPTED, LocalDate.of(2025, 3, 11)));
        jobApplications.add(new JobApplication(jobPostings.get(32), applicants.get(47), JobApplication.Status.SHORTLISTED, LocalDate.of(2025, 3, 19)));
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(49), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 26)));

        jobApplications.add(new JobApplication(jobPostings.get(0), applicants.get(2), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 15)));
        jobApplications.add(new JobApplication(jobPostings.get(0), applicants.get(3), JobApplication.Status.SHORTLISTED, LocalDate.of(2025, 3, 16)));
        jobApplications.add(new JobApplication(jobPostings.get(0), applicants.get(5), JobApplication.Status.INTERVIEWED, LocalDate.of(2025, 3, 17)));
        jobApplications.add(new JobApplication(jobPostings.get(0), applicants.get(7), JobApplication.Status.REJECTED, LocalDate.of(2025, 3, 18)));
        jobApplications.add(new JobApplication(jobPostings.get(0), applicants.get(5), JobApplication.Status.INTERVIEWED, LocalDate.of(2025, 3, 17)));
        jobApplications.add(new JobApplication(jobPostings.get(0), applicants.get(7), JobApplication.Status.REJECTED, LocalDate.of(2025, 3, 18)));

        jobApplications.add(new JobApplication(jobPostings.get(1), applicants.get(8), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 19)));
        jobApplications.add(new JobApplication(jobPostings.get(1), applicants.get(10), JobApplication.Status.OFFERED, LocalDate.of(2025, 3, 20)));
        jobApplications.add(new JobApplication(jobPostings.get(1), applicants.get(12), JobApplication.Status.ACCEPTED, LocalDate.of(2025, 3, 21)));
        jobApplications.add(new JobApplication(jobPostings.get(1), applicants.get(15), JobApplication.Status.WITHDRAWN, LocalDate.of(2025, 3, 22)));
        jobApplications.add(new JobApplication(jobPostings.get(1), applicants.get(114), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 16)));
        jobApplications.add(new JobApplication(jobPostings.get(1), applicants.get(115), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 24)));

        jobApplications.add(new JobApplication(jobPostings.get(2), applicants.get(17), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 23)));
        jobApplications.add(new JobApplication(jobPostings.get(2), applicants.get(19), JobApplication.Status.SHORTLISTED, LocalDate.of(2025, 3, 24)));
        jobApplications.add(new JobApplication(jobPostings.get(2), applicants.get(21), JobApplication.Status.INTERVIEWED, LocalDate.of(2025, 3, 25)));
        jobApplications.add(new JobApplication(jobPostings.get(2), applicants.get(23), JobApplication.Status.REJECTED, LocalDate.of(2025, 3, 26)));
        jobApplications.add(new JobApplication(jobPostings.get(2), applicants.get(116), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 10)));
        jobApplications.add(new JobApplication(jobPostings.get(2), applicants.get(117), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 26)));

        jobApplications.add(new JobApplication(jobPostings.get(3), applicants.get(25), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 27)));
        jobApplications.add(new JobApplication(jobPostings.get(3), applicants.get(27), JobApplication.Status.OFFERED, LocalDate.of(2025, 3, 28)));
        jobApplications.add(new JobApplication(jobPostings.get(3), applicants.get(29), JobApplication.Status.ACCEPTED, LocalDate.of(2025, 3, 29)));
        jobApplications.add(new JobApplication(jobPostings.get(3), applicants.get(31), JobApplication.Status.WITHDRAWN, LocalDate.of(2025, 3, 30)));
        jobApplications.add(new JobApplication(jobPostings.get(3), applicants.get(118), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 22)));
        jobApplications.add(new JobApplication(jobPostings.get(3), applicants.get(119), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 30)));

        jobApplications.add(new JobApplication(jobPostings.get(4), applicants.get(33), JobApplication.Status.PENDING, LocalDate.of(2025, 4, 1)));
        jobApplications.add(new JobApplication(jobPostings.get(4), applicants.get(35), JobApplication.Status.SHORTLISTED, LocalDate.of(2025, 4, 2)));
        jobApplications.add(new JobApplication(jobPostings.get(4), applicants.get(37), JobApplication.Status.INTERVIEWED, LocalDate.of(2025, 4, 3)));
        jobApplications.add(new JobApplication(jobPostings.get(4), applicants.get(39), JobApplication.Status.REJECTED, LocalDate.of(2025, 4, 4)));

        // IT & Communication Tech
        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.IT_COMM_TEC, "Java Programming"),
                new SkillOption(JobPosting.Type.IT_COMM_TEC, "Python Development"),
                new SkillOption(JobPosting.Type.IT_COMM_TEC, "Web Development"),
                new SkillOption(JobPosting.Type.IT_COMM_TEC, "Cybersecurity"),
                new SkillOption(JobPosting.Type.IT_COMM_TEC, "Database Management"),
                new SkillOption(JobPosting.Type.IT_COMM_TEC, "Cloud Computing"),
                new SkillOption(JobPosting.Type.IT_COMM_TEC, "Machine Learning"),
                new SkillOption(JobPosting.Type.IT_COMM_TEC, "Networking"),
                new SkillOption(JobPosting.Type.IT_COMM_TEC, "DevOps"),
                new SkillOption(JobPosting.Type.IT_COMM_TEC, "Software Testing")
        ));

// Accounting
        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.ACCOUNTING, "Bookkeeping"),
                new SkillOption(JobPosting.Type.ACCOUNTING, "Tax Filing"),
                new SkillOption(JobPosting.Type.ACCOUNTING, "Budgeting"),
                new SkillOption(JobPosting.Type.ACCOUNTING, "Auditing"),
                new SkillOption(JobPosting.Type.ACCOUNTING, "Payroll"),
                new SkillOption(JobPosting.Type.ACCOUNTING, "Accounts Receivable"),
                new SkillOption(JobPosting.Type.ACCOUNTING, "Accounts Payable"),
                new SkillOption(JobPosting.Type.ACCOUNTING, "Financial Reporting"),
                new SkillOption(JobPosting.Type.ACCOUNTING, "Cost Accounting"),
                new SkillOption(JobPosting.Type.ACCOUNTING, "QuickBooks")
        ));

// Design & Architecture
        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.DESIGN_ARCH, "AutoCAD"),
                new SkillOption(JobPosting.Type.DESIGN_ARCH, "SketchUp"),
                new SkillOption(JobPosting.Type.DESIGN_ARCH, "3D Modeling"),
                new SkillOption(JobPosting.Type.DESIGN_ARCH, "Interior Design"),
                new SkillOption(JobPosting.Type.DESIGN_ARCH, "Urban Planning"),
                new SkillOption(JobPosting.Type.DESIGN_ARCH, "Photoshop"),
                new SkillOption(JobPosting.Type.DESIGN_ARCH, "Illustrator"),
                new SkillOption(JobPosting.Type.DESIGN_ARCH, "UX Design"),
                new SkillOption(JobPosting.Type.DESIGN_ARCH, "UI Design"),
                new SkillOption(JobPosting.Type.DESIGN_ARCH, "Revit")
        ));

// HR & Recruitment
        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.HR_RECRUIT, "Recruitment"),
                new SkillOption(JobPosting.Type.HR_RECRUIT, "Interviewing"),
                new SkillOption(JobPosting.Type.HR_RECRUIT, "Onboarding"),
                new SkillOption(JobPosting.Type.HR_RECRUIT, "Payroll Processing"),
                new SkillOption(JobPosting.Type.HR_RECRUIT, "Employee Engagement"),
                new SkillOption(JobPosting.Type.HR_RECRUIT, "HR Policies"),
                new SkillOption(JobPosting.Type.HR_RECRUIT, "Performance Reviews"),
                new SkillOption(JobPosting.Type.HR_RECRUIT, "Conflict Resolution"),
                new SkillOption(JobPosting.Type.HR_RECRUIT, "Compliance"),
                new SkillOption(JobPosting.Type.HR_RECRUIT, "HRIS Systems")
        ));

// Admin & Office Support
        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.ADMIN_OFFICE, "Data Entry"),
                new SkillOption(JobPosting.Type.ADMIN_OFFICE, "Office Management"),
                new SkillOption(JobPosting.Type.ADMIN_OFFICE, "Receptionist Duties"),
                new SkillOption(JobPosting.Type.ADMIN_OFFICE, "Scheduling"),
                new SkillOption(JobPosting.Type.ADMIN_OFFICE, "Microsoft Office"),
                new SkillOption(JobPosting.Type.ADMIN_OFFICE, "Filing Systems"),
                new SkillOption(JobPosting.Type.ADMIN_OFFICE, "Customer Support"),
                new SkillOption(JobPosting.Type.ADMIN_OFFICE, "Travel Planning"),
                new SkillOption(JobPosting.Type.ADMIN_OFFICE, "Clerical Support"),
                new SkillOption(JobPosting.Type.ADMIN_OFFICE, "Minute Taking")
        ));

// Marketing & Communications
        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.MARKETING_COMM, "Content Creation"),
                new SkillOption(JobPosting.Type.MARKETING_COMM, "SEO"),
                new SkillOption(JobPosting.Type.MARKETING_COMM, "Social Media"),
                new SkillOption(JobPosting.Type.MARKETING_COMM, "Email Marketing"),
                new SkillOption(JobPosting.Type.MARKETING_COMM, "Market Research"),
                new SkillOption(JobPosting.Type.MARKETING_COMM, "Copywriting"),
                new SkillOption(JobPosting.Type.MARKETING_COMM, "Campaign Planning"),
                new SkillOption(JobPosting.Type.MARKETING_COMM, "Brand Management"),
                new SkillOption(JobPosting.Type.MARKETING_COMM, "Google Ads"),
                new SkillOption(JobPosting.Type.MARKETING_COMM, "Influencer Marketing")
        ));

// Engineering
        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.ENGINEERING, "CAD Design"),
                new SkillOption(JobPosting.Type.ENGINEERING, "Thermodynamics"),
                new SkillOption(JobPosting.Type.ENGINEERING, "Electrical Systems"),
                new SkillOption(JobPosting.Type.ENGINEERING, "Mechanical Analysis"),
                new SkillOption(JobPosting.Type.ENGINEERING, "Control Systems"),
                new SkillOption(JobPosting.Type.ENGINEERING, "Project Management"),
                new SkillOption(JobPosting.Type.ENGINEERING, "Circuit Design"),
                new SkillOption(JobPosting.Type.ENGINEERING, "Materials Testing"),
                new SkillOption(JobPosting.Type.ENGINEERING, "Fluid Dynamics"),
                new SkillOption(JobPosting.Type.ENGINEERING, "SolidWorks")
        ));

// Healthcare & Medical
        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.HEALTH_MED, "First Aid"),
                new SkillOption(JobPosting.Type.HEALTH_MED, "CPR"),
                new SkillOption(JobPosting.Type.HEALTH_MED, "Medical Terminology"),
                new SkillOption(JobPosting.Type.HEALTH_MED, "Patient Care"),
                new SkillOption(JobPosting.Type.HEALTH_MED, "Nursing Skills"),
                new SkillOption(JobPosting.Type.HEALTH_MED, "Clinical Procedures"),
                new SkillOption(JobPosting.Type.HEALTH_MED, "Medical Coding"),
                new SkillOption(JobPosting.Type.HEALTH_MED, "Health Records"),
                new SkillOption(JobPosting.Type.HEALTH_MED, "Phlebotomy"),
                new SkillOption(JobPosting.Type.HEALTH_MED, "Radiology Basics")
        ));

// Community Services & Development
        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.COMM_SVC_DEV, "Social Work"),
                new SkillOption(JobPosting.Type.COMM_SVC_DEV, "Crisis Intervention"),
                new SkillOption(JobPosting.Type.COMM_SVC_DEV, "Counseling"),
                new SkillOption(JobPosting.Type.COMM_SVC_DEV, "Case Management"),
                new SkillOption(JobPosting.Type.COMM_SVC_DEV, "Public Speaking"),
                new SkillOption(JobPosting.Type.COMM_SVC_DEV, "Conflict Resolution"),
                new SkillOption(JobPosting.Type.COMM_SVC_DEV, "Empathy"),
                new SkillOption(JobPosting.Type.COMM_SVC_DEV, "Community Engagement"),
                new SkillOption(JobPosting.Type.COMM_SVC_DEV, "Volunteer Coordination"),
                new SkillOption(JobPosting.Type.COMM_SVC_DEV, "Report Writing")
        ));

// Sales
        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.SALES, "Customer Prospecting"),
                new SkillOption(JobPosting.Type.SALES, "Closing Deals"),
                new SkillOption(JobPosting.Type.SALES, "CRM Usage"),
                new SkillOption(JobPosting.Type.SALES, "Cold Calling"),
                new SkillOption(JobPosting.Type.SALES, "Negotiation"),
                new SkillOption(JobPosting.Type.SALES, "Upselling"),
                new SkillOption(JobPosting.Type.SALES, "Pipeline Management"),
                new SkillOption(JobPosting.Type.SALES, "Client Retention"),
                new SkillOption(JobPosting.Type.SALES, "Sales Forecasting"),
                new SkillOption(JobPosting.Type.SALES, "B2B Sales")
        ));
        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.CONSTRUCTION, "Blueprint Reading"),
                new SkillOption(JobPosting.Type.CONSTRUCTION, "Concrete Work"),
                new SkillOption(JobPosting.Type.CONSTRUCTION, "Scaffolding"),
                new SkillOption(JobPosting.Type.CONSTRUCTION, "Site Safety"),
                new SkillOption(JobPosting.Type.CONSTRUCTION, "Masonry"),
                new SkillOption(JobPosting.Type.CONSTRUCTION, "Plumbing"),
                new SkillOption(JobPosting.Type.CONSTRUCTION, "Electrical Wiring"),
                new SkillOption(JobPosting.Type.CONSTRUCTION, "Roofing"),
                new SkillOption(JobPosting.Type.CONSTRUCTION, "Carpentry"),
                new SkillOption(JobPosting.Type.CONSTRUCTION, "Site Supervision")
        ));

        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.SCIENCE_TECH, "Lab Safety"),
                new SkillOption(JobPosting.Type.SCIENCE_TECH, "Microscopy"),
                new SkillOption(JobPosting.Type.SCIENCE_TECH, "Data Analysis"),
                new SkillOption(JobPosting.Type.SCIENCE_TECH, "Research Writing"),
                new SkillOption(JobPosting.Type.SCIENCE_TECH, "Experiment Design"),
                new SkillOption(JobPosting.Type.SCIENCE_TECH, "Scientific Modelling"),
                new SkillOption(JobPosting.Type.SCIENCE_TECH, "Data Collection"),
                new SkillOption(JobPosting.Type.SCIENCE_TECH, "SPSS"),
                new SkillOption(JobPosting.Type.SCIENCE_TECH, "Lab Equipment Handling"),
                new SkillOption(JobPosting.Type.SCIENCE_TECH, "Hypothesis Testing")
        ));

        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.REAL_EST, "Property Valuation"),
                new SkillOption(JobPosting.Type.REAL_EST, "Real Estate Law"),
                new SkillOption(JobPosting.Type.REAL_EST, "Client Networking"),
                new SkillOption(JobPosting.Type.REAL_EST, "Property Listings"),
                new SkillOption(JobPosting.Type.REAL_EST, "Open House Hosting"),
                new SkillOption(JobPosting.Type.REAL_EST, "Sales Agreements"),
                new SkillOption(JobPosting.Type.REAL_EST, "CRM Software"),
                new SkillOption(JobPosting.Type.REAL_EST, "Investment Analysis"),
                new SkillOption(JobPosting.Type.REAL_EST, "Lease Management"),
                new SkillOption(JobPosting.Type.REAL_EST, "Market Trend Analysis")
        ));

        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.AD_ARTS_MEDIA, "Graphic Design"),
                new SkillOption(JobPosting.Type.AD_ARTS_MEDIA, "Video Editing"),
                new SkillOption(JobPosting.Type.AD_ARTS_MEDIA, "Photography"),
                new SkillOption(JobPosting.Type.AD_ARTS_MEDIA, "Script Writing"),
                new SkillOption(JobPosting.Type.AD_ARTS_MEDIA, "Voice Over"),
                new SkillOption(JobPosting.Type.AD_ARTS_MEDIA, "Brand Design"),
                new SkillOption(JobPosting.Type.AD_ARTS_MEDIA, "Animation"),
                new SkillOption(JobPosting.Type.AD_ARTS_MEDIA, "Storyboarding"),
                new SkillOption(JobPosting.Type.AD_ARTS_MEDIA, "Motion Graphics"),
                new SkillOption(JobPosting.Type.AD_ARTS_MEDIA, "Podcast Editing")
        ));

        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.CALL_CUST_SVC, "Phone Etiquette"),
                new SkillOption(JobPosting.Type.CALL_CUST_SVC, "CRM Systems"),
                new SkillOption(JobPosting.Type.CALL_CUST_SVC, "Problem Solving"),
                new SkillOption(JobPosting.Type.CALL_CUST_SVC, "Live Chat Support"),
                new SkillOption(JobPosting.Type.CALL_CUST_SVC, "Customer Onboarding"),
                new SkillOption(JobPosting.Type.CALL_CUST_SVC, "Ticketing Systems"),
                new SkillOption(JobPosting.Type.CALL_CUST_SVC, "Escalation Handling"),
                new SkillOption(JobPosting.Type.CALL_CUST_SVC, "Time Management"),
                new SkillOption(JobPosting.Type.CALL_CUST_SVC, "Empathy"),
                new SkillOption(JobPosting.Type.CALL_CUST_SVC, "Call Handling")
        ));

        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.CONSULTING, "Business Strategy"),
                new SkillOption(JobPosting.Type.CONSULTING, "Market Research"),
                new SkillOption(JobPosting.Type.CONSULTING, "Presentation Skills"),
                new SkillOption(JobPosting.Type.CONSULTING, "Financial Modelling"),
                new SkillOption(JobPosting.Type.CONSULTING, "Client Communication"),
                new SkillOption(JobPosting.Type.CONSULTING, "SWOT Analysis"),
                new SkillOption(JobPosting.Type.CONSULTING, "Data Analytics"),
                new SkillOption(JobPosting.Type.CONSULTING, "Problem Solving"),
                new SkillOption(JobPosting.Type.CONSULTING, "Risk Assessment"),
                new SkillOption(JobPosting.Type.CONSULTING, "Stakeholder Engagement")
        ));

        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.LEGAL, "Legal Research"),
                new SkillOption(JobPosting.Type.LEGAL, "Contract Drafting"),
                new SkillOption(JobPosting.Type.LEGAL, "Litigation Support"),
                new SkillOption(JobPosting.Type.LEGAL, "Legal Compliance"),
                new SkillOption(JobPosting.Type.LEGAL, "Case Filing"),
                new SkillOption(JobPosting.Type.LEGAL, "Legal Writing"),
                new SkillOption(JobPosting.Type.LEGAL, "Court Procedures"),
                new SkillOption(JobPosting.Type.LEGAL, "Intellectual Property"),
                new SkillOption(JobPosting.Type.LEGAL, "Legal Software"),
                new SkillOption(JobPosting.Type.LEGAL, "Due Diligence")
        ));

        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.EDUC_TRAINING, "Lesson Planning"),
                new SkillOption(JobPosting.Type.EDUC_TRAINING, "Teaching"),
                new SkillOption(JobPosting.Type.EDUC_TRAINING, "Public Speaking"),
                new SkillOption(JobPosting.Type.EDUC_TRAINING, "Curriculum Design"),
                new SkillOption(JobPosting.Type.EDUC_TRAINING, "Assessment Design"),
                new SkillOption(JobPosting.Type.EDUC_TRAINING, "Learning Management Systems"),
                new SkillOption(JobPosting.Type.EDUC_TRAINING, "Tutoring"),
                new SkillOption(JobPosting.Type.EDUC_TRAINING, "Training Delivery"),
                new SkillOption(JobPosting.Type.EDUC_TRAINING, "Educational Tech"),
                new SkillOption(JobPosting.Type.EDUC_TRAINING, "Student Evaluation")
        ));

        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.INSURANCE, "Policy Explanation"),
                new SkillOption(JobPosting.Type.INSURANCE, "Risk Assessment"),
                new SkillOption(JobPosting.Type.INSURANCE, "Client Consultation"),
                new SkillOption(JobPosting.Type.INSURANCE, "Claims Handling"),
                new SkillOption(JobPosting.Type.INSURANCE, "Insurance Law"),
                new SkillOption(JobPosting.Type.INSURANCE, "Underwriting"),
                new SkillOption(JobPosting.Type.INSURANCE, "Fraud Detection"),
                new SkillOption(JobPosting.Type.INSURANCE, "CRM Tools"),
                new SkillOption(JobPosting.Type.INSURANCE, "Customer Retention"),
                new SkillOption(JobPosting.Type.INSURANCE, "Policy Customization")
        ));

        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.BANK_FIN_SERV, "Banking Operations"),
                new SkillOption(JobPosting.Type.BANK_FIN_SERV, "Credit Analysis"),
                new SkillOption(JobPosting.Type.BANK_FIN_SERV, "Financial Advising"),
                new SkillOption(JobPosting.Type.BANK_FIN_SERV, "Loan Processing"),
                new SkillOption(JobPosting.Type.BANK_FIN_SERV, "Risk Management"),
                new SkillOption(JobPosting.Type.BANK_FIN_SERV, "Anti-Money Laundering"),
                new SkillOption(JobPosting.Type.BANK_FIN_SERV, "Teller Operations"),
                new SkillOption(JobPosting.Type.BANK_FIN_SERV, "Account Management"),
                new SkillOption(JobPosting.Type.BANK_FIN_SERV, "Portfolio Management"),
                new SkillOption(JobPosting.Type.BANK_FIN_SERV, "Compliance")
        ));

        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.SPORT_RECREATION, "Fitness Training"),
                new SkillOption(JobPosting.Type.SPORT_RECREATION, "Team Management"),
                new SkillOption(JobPosting.Type.SPORT_RECREATION, "Event Planning"),
                new SkillOption(JobPosting.Type.SPORT_RECREATION, "Injury Prevention"),
                new SkillOption(JobPosting.Type.SPORT_RECREATION, "Nutrition Planning"),
                new SkillOption(JobPosting.Type.SPORT_RECREATION, "Sports Coaching"),
                new SkillOption(JobPosting.Type.SPORT_RECREATION, "First Aid"),
                new SkillOption(JobPosting.Type.SPORT_RECREATION, "Youth Engagement"),
                new SkillOption(JobPosting.Type.SPORT_RECREATION, "Public Speaking"),
                new SkillOption(JobPosting.Type.SPORT_RECREATION, "Game Officiating")
        ));

        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.MANF_LOG, "Supply Chain"),
                new SkillOption(JobPosting.Type.MANF_LOG, "Inventory Control"),
                new SkillOption(JobPosting.Type.MANF_LOG, "Quality Inspection"),
                new SkillOption(JobPosting.Type.MANF_LOG, "Warehouse Management"),
                new SkillOption(JobPosting.Type.MANF_LOG, "Forklift Operation"),
                new SkillOption(JobPosting.Type.MANF_LOG, "Safety Standards"),
                new SkillOption(JobPosting.Type.MANF_LOG, "Lean Manufacturing"),
                new SkillOption(JobPosting.Type.MANF_LOG, "Process Optimization"),
                new SkillOption(JobPosting.Type.MANF_LOG, "Production Planning"),
                new SkillOption(JobPosting.Type.MANF_LOG, "Shipping Logistics")
        ));

        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.HOSP_TOURISM, "Guest Services"),
                new SkillOption(JobPosting.Type.HOSP_TOURISM, "Reservation Management"),
                new SkillOption(JobPosting.Type.HOSP_TOURISM, "Tour Planning"),
                new SkillOption(JobPosting.Type.HOSP_TOURISM, "Travel Booking"),
                new SkillOption(JobPosting.Type.HOSP_TOURISM, "Customer Service"),
                new SkillOption(JobPosting.Type.HOSP_TOURISM, "Event Coordination"),
                new SkillOption(JobPosting.Type.HOSP_TOURISM, "Front Desk Operations"),
                new SkillOption(JobPosting.Type.HOSP_TOURISM, "Housekeeping"),
                new SkillOption(JobPosting.Type.HOSP_TOURISM, "Food & Beverage"),
                new SkillOption(JobPosting.Type.HOSP_TOURISM, "Cultural Awareness")
        ));

        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.AGRICULTURE, "Crop Management"),
                new SkillOption(JobPosting.Type.AGRICULTURE, "Irrigation"),
                new SkillOption(JobPosting.Type.AGRICULTURE, "Fertilizer Application"),
                new SkillOption(JobPosting.Type.AGRICULTURE, "Harvesting"),
                new SkillOption(JobPosting.Type.AGRICULTURE, "Pest Control"),
                new SkillOption(JobPosting.Type.AGRICULTURE, "Farm Equipment Operation"),
                new SkillOption(JobPosting.Type.AGRICULTURE, "Animal Husbandry"),
                new SkillOption(JobPosting.Type.AGRICULTURE, "Agribusiness"),
                new SkillOption(JobPosting.Type.AGRICULTURE, "Soil Testing"),
                new SkillOption(JobPosting.Type.AGRICULTURE, "Sustainable Practices")
        ));

        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.RETAIL_CONSUMER, "Cash Handling"),
                new SkillOption(JobPosting.Type.RETAIL_CONSUMER, "POS Operation"),
                new SkillOption(JobPosting.Type.RETAIL_CONSUMER, "Inventory Tracking"),
                new SkillOption(JobPosting.Type.RETAIL_CONSUMER, "Customer Service"),
                new SkillOption(JobPosting.Type.RETAIL_CONSUMER, "Sales Techniques"),
                new SkillOption(JobPosting.Type.RETAIL_CONSUMER, "Visual Merchandising"),
                new SkillOption(JobPosting.Type.RETAIL_CONSUMER, "Stock Replenishment"),
                new SkillOption(JobPosting.Type.RETAIL_CONSUMER, "Product Knowledge"),
                new SkillOption(JobPosting.Type.RETAIL_CONSUMER, "Loss Prevention"),
                new SkillOption(JobPosting.Type.RETAIL_CONSUMER, "Returns Handling")
        ));

        list.addAll(Arrays.asList(
                new SkillOption(JobPosting.Type.OTHER, "Time Management"),
                new SkillOption(JobPosting.Type.OTHER, "Problem Solving"),
                new SkillOption(JobPosting.Type.OTHER, "Team Collaboration"),
                new SkillOption(JobPosting.Type.OTHER, "Creative Thinking"),
                new SkillOption(JobPosting.Type.OTHER, "Adaptability"),
                new SkillOption(JobPosting.Type.OTHER, "Critical Thinking"),
                new SkillOption(JobPosting.Type.OTHER, "Work Ethic"),
                new SkillOption(JobPosting.Type.OTHER, "Conflict Resolution"),
                new SkillOption(JobPosting.Type.OTHER, "Digital Literacy"),
                new SkillOption(JobPosting.Type.OTHER, "Multitasking")
        ));
    }

    public static List<Company> getCompanies() {
        return companies;
    }

    public static List<JobPosting> getJobPostings() {
        return jobPostings;
    }

    public static List<Applicant> getApplicants() {
        return applicants;
    }

    public static List<JobApplication> getJobApplications() {
        return jobApplications;
    }
}
