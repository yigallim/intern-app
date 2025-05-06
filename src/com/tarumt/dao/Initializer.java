package com.tarumt.dao;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.tarumt.adt.list.ArrayToLinked;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.Company;
import com.tarumt.entity.JobApplication;
import com.tarumt.entity.JobPosting;
import com.tarumt.entity.interview.Invitation;
import com.tarumt.entity.interview.ScheduledInterview;
import com.tarumt.entity.interview.TimeSlot;
import com.tarumt.entity.interview.BlockedTimeSlot;
import com.tarumt.entity.location.City;
import com.tarumt.entity.location.Location;

import com.tarumt.adt.list.ListInterface;
import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.entity.qualification.*;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Log;

public class Initializer {
    static final Random random = new Random(41);
    private static final ListInterface<Company> companies = new DoublyLinkedList<>();
    private static final ListInterface<JobPosting> jobPostings = new DoublyLinkedList<>();
    private static final ListInterface<Applicant> applicants = new DoublyLinkedList<>();
    private static final ListInterface<JobApplication> jobApplications = new DoublyLinkedList<>();
    private static final ListInterface<Invitation> invitations = new DoublyLinkedList<>();
    private static final ListInterface<ScheduledInterview> interviews = new DoublyLinkedList<>();
    private static final ListInterface<BlockedTimeSlot> blockedTimeSlots = new DoublyLinkedList<>();
    public static final ListInterface<SkillOption> skillOptions = new DoublyLinkedList<>();

    static {
        Initializer.init();
        Initializer.initSkill();
    }

    public static ListInterface<EducationLevel> educationLevels() {
        ListInterface<EducationLevel> educationLevels = new DoublyLinkedList<>();

        educationLevels.add(new EducationLevel(EducationLevel.DegreeLevel.BD, EducationLevel.FieldOfStudy.CS, EducationLevel.University.UM, 3.8, false, Qualification.Importance.HIGH));

        educationLevels.add(new EducationLevel(EducationLevel.DegreeLevel.MD, EducationLevel.FieldOfStudy.DS, EducationLevel.University.UKM, 3.9, true, Qualification.Importance.MEDIUM));

        educationLevels.add(new EducationLevel(EducationLevel.DegreeLevel.D, EducationLevel.FieldOfStudy.SE, EducationLevel.University.POLITEKNIK_MALAYSIA, 3.5, true, Qualification.Importance.LOW));

        educationLevels.add(new EducationLevel(EducationLevel.DegreeLevel.PhD, EducationLevel.FieldOfStudy.AI, EducationLevel.University.MONASH_MY, 4.0, true, Qualification.Importance.MEDIUM));

        return educationLevels;
    }

    public static ListInterface<LanguageProficiency> languageProficiencies() {
        ListInterface<LanguageProficiency> languageProficiencies = new DoublyLinkedList<>();

        languageProficiencies.add(new LanguageProficiency(LanguageProficiency.Language.EN, LanguageProficiency.Proficiency.FLUENT, false, Qualification.Importance.HIGH));

        languageProficiencies.add(new LanguageProficiency(LanguageProficiency.Language.MY, LanguageProficiency.Proficiency.NATIVE, true, Qualification.Importance.MEDIUM));

        languageProficiencies.add(new LanguageProficiency(LanguageProficiency.Language.CH, LanguageProficiency.Proficiency.INTERMEDIATE, true, Qualification.Importance.LOW));

        languageProficiencies.add(new LanguageProficiency(LanguageProficiency.Language.JP, LanguageProficiency.Proficiency.ELEMENTARY, true, Qualification.Importance.LOW));

        return languageProficiencies;
    }

    public static ListInterface<Skill> skills() {
        ListInterface<Skill> skills = new DoublyLinkedList<>();

        skills.add(new Skill(JobPosting.Type.IT_COMM_TEC, "Java Programming", Skill.ProficiencyLevel.EXPERT, false, Qualification.Importance.HIGH));

        skills.add(new Skill(JobPosting.Type.IT_COMM_TEC, "Python Development", Skill.ProficiencyLevel.ADVANCED, true, Qualification.Importance.MEDIUM));

        skills.add(new Skill(JobPosting.Type.IT_COMM_TEC, "Web Development", Skill.ProficiencyLevel.INTERMEDIATE, true, Qualification.Importance.MEDIUM));

        skills.add(new Skill(JobPosting.Type.IT_COMM_TEC, "Cybersecurity", Skill.ProficiencyLevel.BEGINNER, true, Qualification.Importance.LOW));

        return skills;
    }

    public static ListInterface<WorkExperience> workExperiences() {
        ListInterface<WorkExperience> workExperiences = new DoublyLinkedList<>();

        workExperiences.add(new WorkExperience(WorkExperience.Industry.IT_COMM_TEC, 5, false, Qualification.Importance.HIGH));

        workExperiences.add(new WorkExperience(WorkExperience.Industry.CONSULTING, 3, true, Qualification.Importance.MEDIUM));

        workExperiences.add(new WorkExperience(WorkExperience.Industry.EDUC_TRAINING, 2, true, Qualification.Importance.LOW));

        workExperiences.add(new WorkExperience(WorkExperience.Industry.BANK_FIN_SERV, 4, true, Qualification.Importance.MEDIUM));

        workExperiences.add(new WorkExperience(WorkExperience.Industry.HEALTH_MED, 6, false, Qualification.Importance.HIGH));

        workExperiences.add(new WorkExperience(WorkExperience.Industry.MARKETING_COMM, 3, true, Qualification.Importance.MEDIUM));

        workExperiences.add(new WorkExperience(WorkExperience.Industry.ENGINEERING, 7, false, Qualification.Importance.HIGH));

        workExperiences.add(new WorkExperience(WorkExperience.Industry.HOSP_TOURISM, 2, true, Qualification.Importance.LOW));

        workExperiences.add(new WorkExperience(WorkExperience.Industry.RETAIL_CONSUMER, 4, true, Qualification.Importance.MEDIUM));

        workExperiences.add(new WorkExperience(WorkExperience.Industry.SCIENCE_TECH, 5, true, Qualification.Importance.MEDIUM));

        return workExperiences;
    }

    private static void initSkill() {
        // IT & Communications Technology
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.IT_COMM_TEC, "Java Programming"), new SkillOption(JobPosting.Type.IT_COMM_TEC, "Python Development"), new SkillOption(JobPosting.Type.IT_COMM_TEC, "Web Development"), new SkillOption(JobPosting.Type.IT_COMM_TEC, "Cybersecurity"), new SkillOption(JobPosting.Type.IT_COMM_TEC, "Cloud Computing")));

// Accounting
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.ACCOUNTING, "Bookkeeping"), new SkillOption(JobPosting.Type.ACCOUNTING, "Tax Filing"), new SkillOption(JobPosting.Type.ACCOUNTING, "Auditing"), new SkillOption(JobPosting.Type.ACCOUNTING, "Financial Reporting"), new SkillOption(JobPosting.Type.ACCOUNTING, "QuickBooks")));

// Design & Architecture
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.DESIGN_ARCH, "AutoCAD"), new SkillOption(JobPosting.Type.DESIGN_ARCH, "3D Modeling"), new SkillOption(JobPosting.Type.DESIGN_ARCH, "Interior Design"), new SkillOption(JobPosting.Type.DESIGN_ARCH, "UX Design"), new SkillOption(JobPosting.Type.DESIGN_ARCH, "Revit")));

// HR & Recruitment
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.HR_RECRUIT, "Recruitment"), new SkillOption(JobPosting.Type.HR_RECRUIT, "Onboarding"), new SkillOption(JobPosting.Type.HR_RECRUIT, "Employee Engagement"), new SkillOption(JobPosting.Type.HR_RECRUIT, "HR Policies"), new SkillOption(JobPosting.Type.HR_RECRUIT, "Performance Reviews")));

// Admin & Office Support
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.ADMIN_OFFICE, "Data Entry"), new SkillOption(JobPosting.Type.ADMIN_OFFICE, "Office Management"), new SkillOption(JobPosting.Type.ADMIN_OFFICE, "Microsoft Office"), new SkillOption(JobPosting.Type.ADMIN_OFFICE, "Customer Support"), new SkillOption(JobPosting.Type.ADMIN_OFFICE, "Scheduling")));

// Marketing & Communications
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.MARKETING_COMM, "Content Creation"), new SkillOption(JobPosting.Type.MARKETING_COMM, "SEO"), new SkillOption(JobPosting.Type.MARKETING_COMM, "Social Media"), new SkillOption(JobPosting.Type.MARKETING_COMM, "Email Marketing"), new SkillOption(JobPosting.Type.MARKETING_COMM, "Copywriting")));

// Engineering
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.ENGINEERING, "CAD Design"), new SkillOption(JobPosting.Type.ENGINEERING, "Electrical Systems"), new SkillOption(JobPosting.Type.ENGINEERING, "Mechanical Analysis"), new SkillOption(JobPosting.Type.ENGINEERING, "Project Management"), new SkillOption(JobPosting.Type.ENGINEERING, "SolidWorks")));

// Healthcare & Medical
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.HEALTH_MED, "First Aid"), new SkillOption(JobPosting.Type.HEALTH_MED, "Patient Care"), new SkillOption(JobPosting.Type.HEALTH_MED, "Nursing Skills"), new SkillOption(JobPosting.Type.HEALTH_MED, "Medical Coding"), new SkillOption(JobPosting.Type.HEALTH_MED, "Phlebotomy")));

// Community Services & Development
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.COMM_SVC_DEV, "Social Work"), new SkillOption(JobPosting.Type.COMM_SVC_DEV, "Counseling"), new SkillOption(JobPosting.Type.COMM_SVC_DEV, "Case Management"), new SkillOption(JobPosting.Type.COMM_SVC_DEV, "Community Engagement"), new SkillOption(JobPosting.Type.COMM_SVC_DEV, "Volunteer Coordination")));

// Sales
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.SALES, "Customer Prospecting"), new SkillOption(JobPosting.Type.SALES, "Closing Deals"), new SkillOption(JobPosting.Type.SALES, "CRM Usage"), new SkillOption(JobPosting.Type.SALES, "Negotiation"), new SkillOption(JobPosting.Type.SALES, "Client Retention")));

// Construction
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.CONSTRUCTION, "Blueprint Reading"), new SkillOption(JobPosting.Type.CONSTRUCTION, "Site Safety"), new SkillOption(JobPosting.Type.CONSTRUCTION, "Plumbing"), new SkillOption(JobPosting.Type.CONSTRUCTION, "Carpentry"), new SkillOption(JobPosting.Type.CONSTRUCTION, "Site Supervision")));

// Science & Technology
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.SCIENCE_TECH, "Lab Safety"), new SkillOption(JobPosting.Type.SCIENCE_TECH, "Data Analysis"), new SkillOption(JobPosting.Type.SCIENCE_TECH, "Research Writing"), new SkillOption(JobPosting.Type.SCIENCE_TECH, "Experiment Design"), new SkillOption(JobPosting.Type.SCIENCE_TECH, "Hypothesis Testing")));

// Real Estate
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.REAL_EST, "Property Valuation"), new SkillOption(JobPosting.Type.REAL_EST, "Property Listings"), new SkillOption(JobPosting.Type.REAL_EST, "Sales Agreements"), new SkillOption(JobPosting.Type.REAL_EST, "Investment Analysis"), new SkillOption(JobPosting.Type.REAL_EST, "Market Trend Analysis")));

// Advertising, Arts & Media
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.AD_ARTS_MEDIA, "Graphic Design"), new SkillOption(JobPosting.Type.AD_ARTS_MEDIA, "Video Editing"), new SkillOption(JobPosting.Type.AD_ARTS_MEDIA, "Photography"), new SkillOption(JobPosting.Type.AD_ARTS_MEDIA, "Animation"), new SkillOption(JobPosting.Type.AD_ARTS_MEDIA, "Motion Graphics")));

// Call Center & Customer Service
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.CALL_CUST_SVC, "Phone Etiquette"), new SkillOption(JobPosting.Type.CALL_CUST_SVC, "Problem Solving"), new SkillOption(JobPosting.Type.CALL_CUST_SVC, "Customer Onboarding"), new SkillOption(JobPosting.Type.CALL_CUST_SVC, "Ticketing Systems"), new SkillOption(JobPosting.Type.CALL_CUST_SVC, "Empathy")));

// Consulting
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.CONSULTING, "Business Strategy"), new SkillOption(JobPosting.Type.CONSULTING, "Market Research"), new SkillOption(JobPosting.Type.CONSULTING, "Financial Modelling"), new SkillOption(JobPosting.Type.CONSULTING, "Data Analytics"), new SkillOption(JobPosting.Type.CONSULTING, "Problem Solving")));

// Legal
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.LEGAL, "Legal Research"), new SkillOption(JobPosting.Type.LEGAL, "Contract Drafting"), new SkillOption(JobPosting.Type.LEGAL, "Legal Compliance"), new SkillOption(JobPosting.Type.LEGAL, "Legal Writing"), new SkillOption(JobPosting.Type.LEGAL, "Due Diligence")));

// Education & Training
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.EDUC_TRAINING, "Lesson Planning"), new SkillOption(JobPosting.Type.EDUC_TRAINING, "Teaching"), new SkillOption(JobPosting.Type.EDUC_TRAINING, "Curriculum Design"), new SkillOption(JobPosting.Type.EDUC_TRAINING, "Tutoring"), new SkillOption(JobPosting.Type.EDUC_TRAINING, "Student Evaluation")));

// Insurance
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.INSURANCE, "Policy Explanation"), new SkillOption(JobPosting.Type.INSURANCE, "Risk Assessment"), new SkillOption(JobPosting.Type.INSURANCE, "Claims Handling"), new SkillOption(JobPosting.Type.INSURANCE, "Underwriting"), new SkillOption(JobPosting.Type.INSURANCE, "Customer Retention")));

// Banking & Financial Services
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.BANK_FIN_SERV, "Banking Operations"), new SkillOption(JobPosting.Type.BANK_FIN_SERV, "Credit Analysis"), new SkillOption(JobPosting.Type.BANK_FIN_SERV, "Financial Advising"), new SkillOption(JobPosting.Type.BANK_FIN_SERV, "Loan Processing"), new SkillOption(JobPosting.Type.BANK_FIN_SERV, "Risk Management")));

// Sport & Recreation
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.SPORT_RECREATION, "Fitness Training"), new SkillOption(JobPosting.Type.SPORT_RECREATION, "Sports Coaching"), new SkillOption(JobPosting.Type.SPORT_RECREATION, "Injury Prevention"), new SkillOption(JobPosting.Type.SPORT_RECREATION, "Event Planning"), new SkillOption(JobPosting.Type.SPORT_RECREATION, "Nutrition Planning")));

// Manufacturing & Logistics
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.MANF_LOG, "Supply Chain"), new SkillOption(JobPosting.Type.MANF_LOG, "Inventory Control"), new SkillOption(JobPosting.Type.MANF_LOG, "Warehouse Management"), new SkillOption(JobPosting.Type.MANF_LOG, "Lean Manufacturing"), new SkillOption(JobPosting.Type.MANF_LOG, "Shipping Logistics")));

// Hospitality & Tourism
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.HOSP_TOURISM, "Guest Services"), new SkillOption(JobPosting.Type.HOSP_TOURISM, "Reservation Management"), new SkillOption(JobPosting.Type.HOSP_TOURISM, "Customer Service"), new SkillOption(JobPosting.Type.HOSP_TOURISM, "Front Desk Operations"), new SkillOption(JobPosting.Type.HOSP_TOURISM, "Event Coordination")));

// Agriculture
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.AGRICULTURE, "Crop Management"), new SkillOption(JobPosting.Type.AGRICULTURE, "Irrigation"), new SkillOption(JobPosting.Type.AGRICULTURE, "Pest Control"), new SkillOption(JobPosting.Type.AGRICULTURE, "Farm Equipment Operation"), new SkillOption(JobPosting.Type.AGRICULTURE, "Sustainable Practices")));

// Retail & Consumer
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.RETAIL_CONSUMER, "Cash Handling"), new SkillOption(JobPosting.Type.RETAIL_CONSUMER, "Customer Service"), new SkillOption(JobPosting.Type.RETAIL_CONSUMER, "Sales Techniques"), new SkillOption(JobPosting.Type.RETAIL_CONSUMER, "Inventory Tracking"), new SkillOption(JobPosting.Type.RETAIL_CONSUMER, "Visual Merchandising")));

// Other
        skillOptions.merge(ArrayToLinked.asList(new SkillOption(JobPosting.Type.OTHER, "Time Management"), new SkillOption(JobPosting.Type.OTHER, "Problem Solving"), new SkillOption(JobPosting.Type.OTHER, "Team Collaboration"), new SkillOption(JobPosting.Type.OTHER, "Adaptability"), new SkillOption(JobPosting.Type.OTHER, "Critical Thinking")));
    }

    public static void init() {
        if (false) return;
        companies.clear();
        jobPostings.clear();
        applicants.clear();
        jobApplications.clear();
        invitations.clear();
        interviews.clear();
        blockedTimeSlots.clear();
        skillOptions.clear();

        // region Company + JobPosting
        // 30 Companies (Hard-Coded, Adhering to Constraints, Using Malaysian Cities)
        companies.add(new Company("TechCorp", "Leading tech company focused on innovation", new Location(City.JOHOR_BAHRU), "contact@techcorp.com", "0123456789"));
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
        companies.add(new Company("SmartRetail", "Innovative retail technology solutions for modern businesses", new Location(City.PETALING_JAYA), "info@smartretail.com", "0125678901"));
        companies.add(new Company("BioTech", "Biotechnology advancements for health and agriculture", new Location(City.SUNGAI_BULOH), "contact@biotech.com", "0136789012"));
        companies.add(new Company("UrbanPlan", "Smart city planning and urban development solutions", new Location(City.PUTRAJAYA), "support@urbanplan.com", "0147890123"));
        companies.add(new Company("CleanTech", "Eco-friendly technology for sustainable industries", new Location(City.BANDAR_SERI_ISKANDAR), "hello@cleantech.com", "0158901234"));
        companies.add(new Company("DataWave", "Big data analytics and AI-driven insights", new Location(City.CYBERJAYA), "info@datawave.com", "0169012345"));
        companies.add(new Company("TravelTech", "Technology solutions for the travel and tourism industry", new Location(City.LANGKAWI), "contact@traveltech.com", "0170123456"));
        companies.add(new Company("SecureNet", "Advanced network security and IT infrastructure", new Location(City.KUALA_TERENGGANU), "support@securenet.com", "0181234567"));
        companies.add(new Company("EduPlatform", "Online learning platforms for global education", new Location(City.BUKIT_MERTAJAM), "info@eduplatform.com", "0192345678"));
        companies.add(new Company("HealthTech", "Digital health solutions for patient care", new Location(City.SIBU), "contact@healthtech.com", "0123456780"));
        companies.add(new Company("LogiSys", "Logistics and supply chain management technology", new Location(City.PASIR_GUDANG), "support@logisys.com", "0134567891"));
        companies.add(new Company("FinTech", "Financial technology for seamless transactions", new Location(City.KLIA), "info@fintech.com", "0145678902"));
        companies.add(new Company("GameStudio", "Interactive gaming and virtual reality experiences", new Location(City.MIRI), "contact@gamestudio.com", "0156789013"));
        companies.add(new Company("SmartHome", "IoT solutions for connected home automation", new Location(City.SEREMBAN), "hello@smarthome.com", "0167890124"));
        companies.add(new Company("EcoFarm", "Sustainable agriculture and precision farming tech", new Location(City.KULAI), "support@ecofarm.com", "0178901235"));
        companies.add(new Company("RoboTech", "Robotics and automation for industrial efficiency", new Location(City.BATU_PAHAT), "info@robotech.com", "0189012346"));

        // 150 Job Postings (5 per company, Hard-Coded, Qualifications set to null)s
        // TechCorp (Company 0)

        jobPostings.add(new JobPosting("Software Engineer", companies.get(0), 5000, 8000, "Develop scalable web applications", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Senior Developer", companies.get(0), 7000, 10000, "Lead development projects for clients", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("DevOps Engineer", companies.get(0), 6000, 9000, "Manage CI/CD pipelines and deployments", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("QA Engineer", companies.get(0), 4000, 6000, "Ensure product quality through testing", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("UI/UX Designer", companies.get(0), 4500, 7000, "Design user interfaces for web apps", JobPosting.Type.DESIGN_ARCH, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // Innovatech (Company 1)
        jobPostings.add(new JobPosting("Data Scientist", companies.get(1), 6000, 9000, "Analyze complex datasets for insights", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("AI Engineer", companies.get(1), 7000, 11000, "Develop AI models for automation", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("ML Engineer", companies.get(1), 6500, 10000, "Implement ML algorithms for projects", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Research Scientist", companies.get(1), 8000, 12000, "Conduct innovative research in tech", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Product Manager", companies.get(1), 7000, 11000, "Manage product lifecycle and strategy", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // FinServe (Company 2)
        jobPostings.add(new JobPosting("Financial Analyst", companies.get(2), 5000, 8000, "Analyze financial data for insights", JobPosting.Type.BANK_FIN_SERV, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Risk Manager", companies.get(2), 6000, 9000, "Assess financial risks for the company", JobPosting.Type.BANK_FIN_SERV, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Accountant", companies.get(2), 4500, 7000, "Manage financial records and reports", JobPosting.Type.ACCOUNTING, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Investment Advisor", companies.get(2), 5500, 8500, "Provide investment advice to clients", JobPosting.Type.BANK_FIN_SERV, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Compliance Officer", companies.get(2), 5000, 8000, "Ensure regulatory compliance", JobPosting.Type.BANK_FIN_SERV, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // HealthPlus (Company 3)
        jobPostings.add(new JobPosting("Medical Researcher", companies.get(3), 6000, 9000, "Conduct medical research studies", JobPosting.Type.HEALTH_MED, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Clinical Analyst", companies.get(3), 5000, 8000, "Analyze clinical data for insights", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Healthcare Manager", companies.get(3), 7000, 10000, "Manage healthcare operations", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Health Consultant", companies.get(3), 5500, 8500, "Provide health consultancy services", JobPosting.Type.HEALTH_MED, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Data Specialist", companies.get(3), 5000, 8000, "Handle healthcare data systems", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // EduTech (Company 4)
        jobPostings.add(new JobPosting("EdTech Developer", companies.get(4), 5000, 8000, "Develop educational software tools", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Content Creator", companies.get(4), 4000, 6000, "Create educational content", JobPosting.Type.EDUC_TRAINING, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Instructional Designer", companies.get(4), 4500, 7000, "Design learning materials", JobPosting.Type.EDUC_TRAINING, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Tech Support", companies.get(4), 3500, 5000, "Provide tech support for platforms", JobPosting.Type.CALL_CUST_SVC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Project Manager", companies.get(4), 6000, 9000, "Manage education tech projects", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // GreenEnergy (Company 5)
        jobPostings.add(new JobPosting("Energy Engineer", companies.get(5), 6000, 9000, "Design sustainable energy systems", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Sustainability Analyst", companies.get(5), 5000, 8000, "Analyze sustainability metrics", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Project Coordinator", companies.get(5), 4500, 7000, "Coordinate energy projects", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Field Technician", companies.get(5), 4000, 6000, "Install energy systems", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Research Analyst", companies.get(5), 5000, 8000, "Research green energy solutions", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // AutoDrive (Company 6)
        jobPostings.add(new JobPosting("Robotics Engineer", companies.get(6), 7000, 11000, "Develop autonomous vehicle systems", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Software Developer", companies.get(6), 6000, 9000, "Code vehicle control software", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Test Engineer", companies.get(6), 5000, 8000, "Test autonomous vehicle systems", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Data Engineer", companies.get(6), 5500, 8500, "Manage vehicle data pipelines", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Safety Analyst", companies.get(6), 5000, 8000, "Ensure vehicle safety standards", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // FoodieTech (Company 7)
        jobPostings.add(new JobPosting("App Developer", companies.get(7), 5000, 8000, "Develop food delivery apps", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Marketing Specialist", companies.get(7), 4500, 7000, "Market food tech solutions", JobPosting.Type.MARKETING_COMM, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Data Analyst", companies.get(7), 5000, 8000, "Analyze food industry trends", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Logistics Manager", companies.get(7), 5500, 8500, "Manage food delivery logistics", JobPosting.Type.MANF_LOG, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Customer Support", companies.get(7), 3500, 5000, "Support food app users", JobPosting.Type.CALL_CUST_SVC, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // CyberShield (Company 8)
        jobPostings.add(new JobPosting("Security Analyst", companies.get(8), 6000, 9000, "Analyze cybersecurity threats", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Penetration Tester", companies.get(8), 6500, 9500, "Test system vulnerabilities", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Security Engineer", companies.get(8), 7000, 10000, "Design secure systems", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Compliance Analyst", companies.get(8), 5000, 8000, "Ensure regulatory compliance", JobPosting.Type.LEGAL, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Incident Responder", companies.get(8), 5500, 8500, "Respond to security incidents", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // QuantumSoft (Company 9)
        jobPostings.add(new JobPosting("Quantum Developer", companies.get(9), 8000, 12000, "Develop quantum computing software", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Research Scientist", companies.get(9), 7500, 11000, "Research quantum algorithms", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Software Engineer", companies.get(9), 6000, 9000, "Code quantum applications", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Data Scientist", companies.get(9), 6500, 9500, "Analyze quantum computing data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Project Manager", companies.get(9), 7000, 10000, "Manage quantum projects", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // MediCare (Company 10)
        jobPostings.add(new JobPosting("Clinical Researcher", companies.get(10), 6000, 9000, "Conduct clinical research studies", JobPosting.Type.HEALTH_MED, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Medical Analyst", companies.get(10), 5000, 8000, "Analyze medical data trends", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Health Consultant", companies.get(10), 5500, 8500, "Provide medical consultancy", JobPosting.Type.HEALTH_MED, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Data Specialist", companies.get(10), 5000, 8000, "Manage medical data systems", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Operations Manager", companies.get(10), 6000, 9000, "Oversee medical operations", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // CloudSys (Company 11)
        jobPostings.add(new JobPosting("Cloud Engineer", companies.get(11), 6000, 9000, "Design cloud infrastructure", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("DevOps Specialist", companies.get(11), 6500, 9500, "Manage cloud deployments", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Security Analyst", companies.get(11), 5500, 8500, "Secure cloud systems", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Data Engineer", companies.get(11), 6000, 9000, "Build data pipelines in cloud", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Cloud Architect", companies.get(11), 7000, 11000, "Architect cloud solutions", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // NeuroTech (Company 12)
        jobPostings.add(new JobPosting("Neuroscientist", companies.get(12), 7000, 11000, "Research neural networks", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("AI Developer", companies.get(12), 6500, 9500, "Develop AI for neuroscience", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Data Scientist", companies.get(12), 6000, 9000, "Analyze neural data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Research Analyst", companies.get(12), 5500, 8500, "Support neuroscience research", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Software Engineer", companies.get(12), 6000, 9000, "Code neuroscience tools", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // AgriGrow (Company 13)
        jobPostings.add(new JobPosting("Agri Engineer", companies.get(13), 5000, 8000, "Design farming technology", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Data Analyst", companies.get(13), 4500, 7000, "Analyze agricultural data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Field Manager", companies.get(13), 4000, 6000, "Manage farming operations", JobPosting.Type.AGRICULTURE, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Tech Specialist", companies.get(13), 4500, 7000, "Support agri-tech systems", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Sustainability Lead", companies.get(13), 5000, 8000, "Lead sustainable farming", JobPosting.Type.AGRICULTURE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // SpaceFront (Company 14)
        jobPostings.add(new JobPosting("Aerospace Engineer", companies.get(14), 7000, 11000, "Design aerospace systems", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Space Researcher", companies.get(14), 6500, 9500, "Research space technologies", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Test Engineer", companies.get(14), 6000, 9000, "Test aerospace components", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Data Analyst", companies.get(14), 5500, 8500, "Analyze space mission data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Project Manager", companies.get(14), 7000, 10000, "Manage space projects", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // SmartRetail (Company 15)
        jobPostings.add(new JobPosting("Retail Software Engineer", companies.get(15), 5000, 8000, "Develop retail management software", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Retail Data Analyst", companies.get(15), 4500, 7000, "Analyze retail sales data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("UX Designer", companies.get(15), 4000, 6500, "Design intuitive retail interfaces", JobPosting.Type.DESIGN_ARCH, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Customer Success Manager", companies.get(15), 5000, 7500, "Support retail clients", JobPosting.Type.CALL_CUST_SVC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Marketing Coordinator", companies.get(15), 4000, 6000, "Plan retail marketing campaigns", JobPosting.Type.MARKETING_COMM, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // BioTech (Company 16)
        jobPostings.add(new JobPosting("Biotech Researcher", companies.get(16), 6000, 9000, "Conduct biotech experiments", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Lab Technician", companies.get(16), 4000, 6000, "Support lab operations", JobPosting.Type.HEALTH_MED, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Bioinformatics Specialist", companies.get(16), 5500, 8500, "Analyze biological data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Regulatory Affairs Manager", companies.get(16), 6000, 9000, "Ensure compliance with regulations", JobPosting.Type.LEGAL, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Project Manager", companies.get(16), 6500, 9500, "Manage biotech projects", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // UrbanPlan (Company 17)
        jobPostings.add(new JobPosting("Urban Planner", companies.get(17), 5500, 8500, "Design sustainable urban layouts", JobPosting.Type.DESIGN_ARCH, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("GIS Analyst", companies.get(17), 5000, 7500, "Analyze geographic data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Project Coordinator", companies.get(17), 4500, 7000, "Coordinate urban projects", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Sustainability Consultant", companies.get(17), 5000, 8000, "Advise on green urban solutions", JobPosting.Type.CONSULTING, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Civil Engineer", companies.get(17), 6000, 9000, "Design urban infrastructure", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // CleanTech (Company 18)
        jobPostings.add(new JobPosting("Environmental Engineer", companies.get(18), 5500, 8500, "Develop eco-friendly technologies", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Sustainability Analyst", companies.get(18), 4500, 7000, "Analyze environmental impact", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Field Technician", companies.get(18), 4000, 6000, "Install clean tech systems", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Research Scientist", companies.get(18), 6000, 9000, "Research sustainable solutions", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Operations Manager", companies.get(18), 6500, 9500, "Oversee clean tech operations", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // DataWave (Company 19)
        jobPostings.add(new JobPosting("Big Data Engineer", companies.get(19), 6000, 9000, "Build data processing pipelines", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("AI Analyst", companies.get(19), 5500, 8500, "Develop AI-driven insights", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Data Scientist", companies.get(19), 6000, 9000, "Analyze large datasets", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("DevOps Engineer", companies.get(19), 5500, 8500, "Manage data infrastructure", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Project Manager", companies.get(19), 6500, 9500, "Lead data projects", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // TravelTech (Company 20)
        jobPostings.add(new JobPosting("Travel App Developer", companies.get(20), 5000, 8000, "Develop travel booking apps", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Marketing Specialist", companies.get(20), 4500, 7000, "Promote travel tech solutions", JobPosting.Type.MARKETING_COMM, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Customer Support", companies.get(20), 3500, 5000, "Assist travel app users", JobPosting.Type.CALL_CUST_SVC, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Data Analyst", companies.get(20), 4500, 7000, "Analyze travel trends", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Product Manager", companies.get(20), 6000, 9000, "Manage travel tech products", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // SecureNet (Company 21)
        jobPostings.add(new JobPosting("Network Engineer", companies.get(21), 6000, 9000, "Design secure network systems", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Security Analyst", companies.get(21), 5500, 8500, "Monitor network security", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("IT Consultant", companies.get(21), 6000, 9000, "Advise on network solutions", JobPosting.Type.CONSULTING, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("System Administrator", companies.get(21), 5000, 7500, "Manage IT infrastructure", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Compliance Officer", companies.get(21), 5500, 8500, "Ensure network compliance", JobPosting.Type.LEGAL, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // EduPlatform (Company 22)
        jobPostings.add(new JobPosting("E-Learning Developer", companies.get(22), 5000, 8000, "Develop online learning platforms", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Content Specialist", companies.get(22), 4000, 6000, "Create educational content", JobPosting.Type.EDUC_TRAINING, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Instructional Designer", companies.get(22), 4500, 7000, "Design e-learning courses", JobPosting.Type.EDUC_TRAINING, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Tech Support", companies.get(22), 3500, 5000, "Support learning platforms", JobPosting.Type.CALL_CUST_SVC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Project Manager", companies.get(22), 6000, 9000, "Manage e-learning projects", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // HealthTech (Company 23)
        jobPostings.add(new JobPosting("Health App Developer", companies.get(23), 5000, 8000, "Develop healthcare apps", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Clinical Data Analyst", companies.get(23), 4500, 7000, "Analyze health data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Health Consultant", companies.get(23), 5500, 8500, "Provide health tech advice", JobPosting.Type.HEALTH_MED, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("QA Engineer", companies.get(23), 4000, 6000, "Test healthcare systems", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Operations Manager", companies.get(23), 6000, 9000, "Manage health tech operations", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // LogiSys (Company 24)
        jobPostings.add(new JobPosting("Logistics Software Engineer", companies.get(24), 5000, 8000, "Develop logistics software", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Supply Chain Analyst", companies.get(24), 4500, 7000, "Analyze supply chain data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Logistics Manager", companies.get(24), 5500, 8500, "Manage logistics operations", JobPosting.Type.MANF_LOG, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Customer Support", companies.get(24), 3500, 5000, "Support logistics clients", JobPosting.Type.CALL_CUST_SVC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Project Coordinator", companies.get(24), 5000, 7500, "Coordinate logistics projects", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // FinTech (Company 25)
        jobPostings.add(new JobPosting("FinTech Developer", companies.get(25), 6000, 9000, "Develop financial software", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Financial Analyst", companies.get(25), 5000, 8000, "Analyze financial trends", JobPosting.Type.BANK_FIN_SERV, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Compliance Specialist", companies.get(25), 5500, 8500, "Ensure financial compliance", JobPosting.Type.LEGAL, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Data Scientist", companies.get(25), 6000, 9000, "Analyze financial data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Product Manager", companies.get(25), 6500, 9500, "Manage fintech products", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // GameStudio (Company 26)
        jobPostings.add(new JobPosting("Game Developer", companies.get(26), 5000, 8000, "Develop interactive games", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("3D Artist", companies.get(26), 4500, 7000, "Create game visuals", JobPosting.Type.DESIGN_ARCH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("QA Tester", companies.get(26), 4000, 6000, "Test game functionality", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Game Designer", companies.get(26), 5000, 7500, "Design game mechanics", JobPosting.Type.DESIGN_ARCH, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Marketing Specialist", companies.get(26), 4500, 7000, "Promote games", JobPosting.Type.MARKETING_COMM, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // SmartHome (Company 27)
        jobPostings.add(new JobPosting("IoT Engineer", companies.get(27), 6000, 9000, "Develop smart home devices", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Software Developer", companies.get(27), 5000, 8000, "Code smart home software", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("UX Designer", companies.get(27), 4500, 7000, "Design smart home interfaces", JobPosting.Type.DESIGN_ARCH, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Customer Support", companies.get(27), 3500, 5000, "Support smart home users", JobPosting.Type.CALL_CUST_SVC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Product Manager", companies.get(27), 6000, 9000, "Manage smart home products", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // EcoFarm (Company 28)
        jobPostings.add(new JobPosting("AgriTech Engineer", companies.get(28), 5000, 8000, "Develop farming technology", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Data Analyst", companies.get(28), 4500, 7000, "Analyze farm data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Farm Manager", companies.get(28), 4000, 6000, "Manage farm operations", JobPosting.Type.AGRICULTURE, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Sustainability Specialist", companies.get(28), 4500, 7000, "Promote sustainable farming", JobPosting.Type.AGRICULTURE, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Tech Support", companies.get(28), 4000, 6000, "Support farm tech systems", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // RoboTech (Company 29)
        jobPostings.add(new JobPosting("Robotics Engineer", companies.get(29), 6000, 9000, "Design robotic systems", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Automation Specialist", companies.get(29), 5500, 8500, "Develop automation solutions", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Software Engineer", companies.get(29), 5000, 8000, "Code robotics software", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("QA Engineer", companies.get(29), 4500, 7000, "Test robotic systems", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Project Manager", companies.get(29), 6000, 9000, "Manage robotics projects", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // 50 Applicants (Hard-Coded, Qualifications set to null, Using Malaysian Cities)
        applicants.add(new Applicant("Alice Johnson", "alice.johnson@email.com", "0123456789", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Bob Smith", "bob.smith@email.com", "0198765432", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Charlie Brown", "charlie.brown@email.com", "0129876543", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Daisy Williams", "daisy.williams@email.com", "0134567890", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Ethan Lee", "ethan.lee@email.com", "0145678901", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Fiona Davis", "fiona.davis@email.com", "0156789012", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("George Wilson", "george.wilson@email.com", "0167890123", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Hannah Moore", "hannah.moore@email.com", "0178901234", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Ian Taylor", "ian.taylor@email.com", "0189012345", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Julia Anderson", "julia.anderson@email.com", "0190123456", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Kevin White", "kevin.white@email.com", "01112345678", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Laura Martinez", "laura.martinez@email.com", "0122345678", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Mike Brown", "mike.brown@email.com", "0133456789", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Nina Harris", "nina.harris@email.com", "0144567890", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Oliver Clark", "oliver.clark@email.com", "0155678901", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Pamela Lewis", "pamela.lewis@email.com", "0166789012", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Quincy Adams", "quincy.adams@email.com", "0177890123", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Rachel Green", "rachel.green@email.com", "0188901234", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Sam Evans", "sam.evans@email.com", "0199012345", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Tina Walker", "tina.walker@email.com", "01123456789", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Umar Khan", "umar.khan@email.com", "0124567890", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Vera Scott", "vera.scott@email.com", "0135678901", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Will Turner", "will.turner@email.com", "0146789012", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Xena Brooks", "xena.brooks@email.com", "0157890123", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Yara King", "yara.king@email.com", "0168901234", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Zack Miller", "zack.miller@email.com", "0179012345", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Amy Carter", "amy.carter@email.com", "0180123456", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Ben Foster", "ben.foster@email.com", "0191234567", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Clara Hayes", "clara.hayes@email.com", "01134567890", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Dan Price", "dan.price@email.com", "0125678901", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Ella Reed", "ella.reed@email.com", "0136789012", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Finn Cole", "finn.cole@email.com", "0147890123", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Gina Bell", "gina.bell@email.com", "0158901234", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Hank Ford", "hank.ford@email.com", "0169012345", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Iris Lane", "iris.lane@email.com", "0170123456", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Jack Gray", "jack.gray@email.com", "0181234567", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Kara Hill", "kara.hill@email.com", "0192345678", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Liam Ward", "liam.ward@email.com", "01145678901", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Maya Ross", "maya.ross@email.com", "0126789012", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Nate Fox", "nate.fox@email.com", "0137890123", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Opal Dean", "opal.dean@email.com", "0148901234", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Paul Hart", "paul.hart@email.com", "0159012345", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Quinn Cole", "quinn.cole@email.com", "0160123456", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Rita Lane", "rita.lane@email.com", "0171234567", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Seth Owen", "seth.owen@email.com", "0182345678", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Tara Bell", "tara.bell@email.com", "0193456789", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Uma West", "uma.west@email.com", "01156789012", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Vince Ray", "vince.ray@email.com", "0127890123", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Wendy Fox", "wendy.fox@email.com", "0138901234", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Xander Lee", "xander.lee@email.com", "0149012345", t(), r(), re(), rw(), rl(), rs()));
        // endregion

        int jobPostingsSize = jobPostings.size();
        double[] weights = new double[jobPostingsSize];
        double skew = 2.0;
        for (int i = 0; i < jobPostingsSize; i++) {
            weights[i] = Math.pow(random.nextDouble(), skew) + 0.1;
        }
        for (Applicant applicant : applicants) {
            boolean hasAccepted = false;
            int applicationCount = (int) Math.round(random.nextGaussian() * 1.5 + 6);
            applicationCount = Math.max(3, Math.min(9, applicationCount));

            for (int j = 0; j < applicationCount; j++) {
                JobApplication.Status status;
                do {
                    status = generateStatus(random);
                } while (status == JobApplication.Status.ACCEPTED && hasAccepted);

                if (status == JobApplication.Status.ACCEPTED) {
                    hasAccepted = true;
                }

                int idx = pickWeightedIndex(weights, random);
                JobPosting jp = jobPostings.get(idx);
                LocalDateTime appliedAt = appDate();
                jobApplications.add(new JobApplication(jp, applicant, status, appliedAt));
            }
        }

        // region Old Hardcoded JobApplication
        // 251 job applications
        // Applicant 0 (Alice Johnson)
//        jobApplications.add(new JobApplication(jobPostings.get(12), applicants.get(0), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(21), applicants.get(0), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(2), applicants.get(0), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(3), applicants.get(0), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(4), applicants.get(0), accept(), appDate()));
//
//        // Applicant 1 (Bob Smith)
//        jobApplications.add(new JobApplication(jobPostings.get(1), applicants.get(1), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(6), applicants.get(1), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(11), applicants.get(1), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(16), applicants.get(1), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(21), applicants.get(1), pending(), appDate()));
//
//        // Applicant 2 (Charlie Brown)
//        jobApplications.add(new JobApplication(jobPostings.get(2), applicants.get(2), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(7), applicants.get(2), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(12), applicants.get(2), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(17), applicants.get(2), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(22), applicants.get(2), shortlist(), appDate()));
//
//        // Applicant 3 (Daisy Williams)
//        jobApplications.add(new JobApplication(jobPostings.get(3), applicants.get(3), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(8), applicants.get(3), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(13), applicants.get(3), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(18), applicants.get(3), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(23), applicants.get(3), interview(), appDate()));
//
//        // Applicant 4 (Ethan Lee)
//        jobApplications.add(new JobApplication(jobPostings.get(4), applicants.get(4), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(9), applicants.get(4), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(14), applicants.get(4), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(19), applicants.get(4), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(24), applicants.get(4), offer(), appDate()));
//
//        // Applicant 5 (Fiona Davis)
//        jobApplications.add(new JobApplication(jobPostings.get(5), applicants.get(5), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(10), applicants.get(5), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(5), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(5), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(5), rejected(), appDate()));
//
//        // Applicant 6 (George Wilson)
//        jobApplications.add(new JobApplication(jobPostings.get(6), applicants.get(6), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(11), applicants.get(6), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(16), applicants.get(6), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(21), applicants.get(6), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(26), applicants.get(6), pending(), appDate()));
//
//        // Applicant 7 (Hannah Moore)
//        jobApplications.add(new JobApplication(jobPostings.get(7), applicants.get(7), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(12), applicants.get(7), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(17), applicants.get(7), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(22), applicants.get(7), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(27), applicants.get(7), accept(), appDate()));
//
//        // Applicant 8 (Ian Taylor)
//        jobApplications.add(new JobApplication(jobPostings.get(8), applicants.get(8), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(13), applicants.get(8), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(18), applicants.get(8), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(23), applicants.get(8), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(28), applicants.get(8), withdrawn(), appDate()));
//
//        // Applicant 9 (Julia Anderson)
//        jobApplications.add(new JobApplication(jobPostings.get(9), applicants.get(9), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(14), applicants.get(9), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(19), applicants.get(9), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(24), applicants.get(9), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(29), applicants.get(9), shortlist(), appDate()));
//
//        // Applicant 10 (Kevin White)
//        jobApplications.add(new JobApplication(jobPostings.get(10), applicants.get(10), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(10), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(10), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(10), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(10), interview(), appDate()));
//
//        // Applicant 11 (Laura Martinez)
//        jobApplications.add(new JobApplication(jobPostings.get(11), applicants.get(11), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(16), applicants.get(11), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(21), applicants.get(11), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(26), applicants.get(11), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(31), applicants.get(11), offer(), appDate()));
//
//        // Applicant 12 (Mike Brown)
//        jobApplications.add(new JobApplication(jobPostings.get(12), applicants.get(12), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(17), applicants.get(12), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(22), applicants.get(12), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(27), applicants.get(12), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(32), applicants.get(12), rejected(), appDate()));
//
//        // Applicant 13 (Nina Harris)
//        jobApplications.add(new JobApplication(jobPostings.get(13), applicants.get(13), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(18), applicants.get(13), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(23), applicants.get(13), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(28), applicants.get(13), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(33), applicants.get(13), pending(), appDate()));
//
//        // Applicant 14 (Oliver Clark)
//        jobApplications.add(new JobApplication(jobPostings.get(14), applicants.get(14), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(19), applicants.get(14), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(24), applicants.get(14), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(29), applicants.get(14), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(34), applicants.get(14), accept(), appDate()));
//
//        // Applicant 15 (Pamela Lewis)
//        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(15), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(15), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(15), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(15), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(15), withdrawn(), appDate()));
//
//        // Applicant 16 (Quincy Adams)
//        jobApplications.add(new JobApplication(jobPostings.get(16), applicants.get(16), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(21), applicants.get(16), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(26), applicants.get(16), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(31), applicants.get(16), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(36), applicants.get(16), shortlist(), appDate()));
//
//        // Applicant 17 (Rachel Green)
//        jobApplications.add(new JobApplication(jobPostings.get(17), applicants.get(17), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(22), applicants.get(17), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(27), applicants.get(17), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(32), applicants.get(17), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(37), applicants.get(17), interview(), appDate()));
//
//        // Applicant 18 (Sam Evans)
//        jobApplications.add(new JobApplication(jobPostings.get(18), applicants.get(18), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(23), applicants.get(18), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(28), applicants.get(18), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(33), applicants.get(18), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(38), applicants.get(18), offer(), appDate()));
//
//        // Applicant 19 (Tina Walker)
//        jobApplications.add(new JobApplication(jobPostings.get(19), applicants.get(19), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(19), applicants.get(19), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(29), applicants.get(19), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(34), applicants.get(19), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(39), applicants.get(19), rejected(), appDate()));
//
//        // Applicant 20 (Umar Khan)
//        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(20), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(20), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(20), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(20), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(20), pending(), appDate()));
//
//        // Applicant 21 (Vera Scott)
//        jobApplications.add(new JobApplication(jobPostings.get(21), applicants.get(21), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(26), applicants.get(21), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(31), applicants.get(21), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(36), applicants.get(21), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(41), applicants.get(21), accept(), appDate()));
//
//        // Applicant 22 (Will Turner)
//        jobApplications.add(new JobApplication(jobPostings.get(22), applicants.get(22), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(27), applicants.get(22), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(32), applicants.get(22), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(37), applicants.get(22), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(42), applicants.get(22), withdrawn(), appDate()));
//
//        // Applicant 23 (Xena Brooks)
//        jobApplications.add(new JobApplication(jobPostings.get(23), applicants.get(23), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(28), applicants.get(23), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(33), applicants.get(23), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(38), applicants.get(23), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(43), applicants.get(23), shortlist(), appDate()));
//
//        // Applicant 24 (Yara King)
//        jobApplications.add(new JobApplication(jobPostings.get(24), applicants.get(24), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(29), applicants.get(24), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(34), applicants.get(24), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(39), applicants.get(24), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(44), applicants.get(24), interview(), appDate()));
//
//        // Applicant 25 (Zack Miller)
//        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(25), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(25), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(25), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(25), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(25), offer(), appDate()));
//
//        // Applicant 26 (Amy Carter)
//        jobApplications.add(new JobApplication(jobPostings.get(26), applicants.get(26), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(31), applicants.get(26), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(36), applicants.get(26), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(41), applicants.get(26), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(46), applicants.get(26), rejected(), appDate()));
//
//        // Applicant 27 (Ben Foster)
//        jobApplications.add(new JobApplication(jobPostings.get(27), applicants.get(27), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(32), applicants.get(27), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(37), applicants.get(27), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(42), applicants.get(27), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(47), applicants.get(27), pending(), appDate()));
//
//        // Applicant 28 (Clara Hayes)
//        jobApplications.add(new JobApplication(jobPostings.get(28), applicants.get(28), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(33), applicants.get(28), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(38), applicants.get(28), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(43), applicants.get(28), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(48), applicants.get(28), accept(), appDate()));
//
//        // Applicant 29 (Dan Price)
//        jobApplications.add(new JobApplication(jobPostings.get(29), applicants.get(29), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(34), applicants.get(29), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(39), applicants.get(29), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(44), applicants.get(29), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(49), applicants.get(29), withdrawn(), appDate()));
//
//        // Applicant 30 (Ella Reed)
//        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(30), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(30), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(30), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(30), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(50), applicants.get(30), shortlist(), appDate()));
//
//        // Applicant 31 (Finn Cole)
//        jobApplications.add(new JobApplication(jobPostings.get(31), applicants.get(31), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(36), applicants.get(31), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(41), applicants.get(31), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(46), applicants.get(31), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(51), applicants.get(31), interview(), appDate()));
//
//        // Applicant 32 (Gina Bell)
//        jobApplications.add(new JobApplication(jobPostings.get(32), applicants.get(32), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(37), applicants.get(32), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(42), applicants.get(32), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(47), applicants.get(32), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(52), applicants.get(32), offer(), appDate()));
//
//        // Applicant 33 (Hank Ford)
//        jobApplications.add(new JobApplication(jobPostings.get(33), applicants.get(33), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(38), applicants.get(33), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(43), applicants.get(33), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(48), applicants.get(33), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(53), applicants.get(33), rejected(), appDate()));
//
//        // Applicant 34 (Iris Lane)
//        jobApplications.add(new JobApplication(jobPostings.get(34), applicants.get(34), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(39), applicants.get(34), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(44), applicants.get(34), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(49), applicants.get(34), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(54), applicants.get(34), pending(), appDate()));
//
//        // Applicant 35 (Jack Gray)
//        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(35), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(35), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(35), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(50), applicants.get(35), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(55), applicants.get(35), accept(), appDate()));
//
//        // Applicant 36 (Kara Hill)
//        jobApplications.add(new JobApplication(jobPostings.get(36), applicants.get(36), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(41), applicants.get(36), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(46), applicants.get(36), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(51), applicants.get(36), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(56), applicants.get(36), withdrawn(), appDate()));
//
//        // Applicant 37 (Liam Ward)
//        jobApplications.add(new JobApplication(jobPostings.get(37), applicants.get(37), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(42), applicants.get(37), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(47), applicants.get(37), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(52), applicants.get(37), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(57), applicants.get(37), shortlist(), appDate()));
//
//        // Applicant 38 (Maya Ross)
//        jobApplications.add(new JobApplication(jobPostings.get(38), applicants.get(38), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(43), applicants.get(38), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(48), applicants.get(38), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(53), applicants.get(38), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(58), applicants.get(38), interview(), appDate()));
//
//        // Applicant 39 (Nate Fox)
//        jobApplications.add(new JobApplication(jobPostings.get(39), applicants.get(39), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(44), applicants.get(39), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(49), applicants.get(39), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(54), applicants.get(39), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(59), applicants.get(39), offer(), appDate()));
//
//        // Applicant 40 (Opal Dean)
//        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(40), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(40), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(50), applicants.get(40), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(55), applicants.get(40), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(60), applicants.get(40), rejected(), appDate()));
//
//        // Applicant 41 (Paul Hart)
//        jobApplications.add(new JobApplication(jobPostings.get(41), applicants.get(41), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(46), applicants.get(41), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(51), applicants.get(41), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(56), applicants.get(41), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(61), applicants.get(41), pending(), appDate()));
//
//        // Applicant 42 (Quinn Cole)
//        jobApplications.add(new JobApplication(jobPostings.get(42), applicants.get(42), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(47), applicants.get(42), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(52), applicants.get(42), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(57), applicants.get(42), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(62), applicants.get(42), accept(), appDate()));
//
//        // Applicant 43 (Rita Lane)
//        jobApplications.add(new JobApplication(jobPostings.get(43), applicants.get(43), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(48), applicants.get(43), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(53), applicants.get(43), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(58), applicants.get(43), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(63), applicants.get(43), withdrawn(), appDate()));
//
//        // Applicant 44 (Seth Owen)
//        jobApplications.add(new JobApplication(jobPostings.get(44), applicants.get(44), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(49), applicants.get(44), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(54), applicants.get(44), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(59), applicants.get(44), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(64), applicants.get(44), shortlist(), appDate()));
//
//        // Applicant 45 (Tara Bell)
//        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(45), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(50), applicants.get(45), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(55), applicants.get(45), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(60), applicants.get(45), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(65), applicants.get(45), interview(), appDate()));
//
//        // Applicant 46 (Uma West)
//        jobApplications.add(new JobApplication(jobPostings.get(46), applicants.get(46), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(51), applicants.get(46), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(56), applicants.get(46), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(61), applicants.get(46), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(66), applicants.get(46), offer(), appDate()));
//
//        // Applicant 47 (Vince Ray)
//        jobApplications.add(new JobApplication(jobPostings.get(47), applicants.get(47), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(52), applicants.get(47), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(57), applicants.get(47), pending(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(62), applicants.get(47), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(67), applicants.get(47), rejected(), appDate()));
//
//        // Applicant 48 (Wendy Fox)
//        jobApplications.add(new JobApplication(jobPostings.get(48), applicants.get(48), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(53), applicants.get(48), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(58), applicants.get(48), accept(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(63), applicants.get(48), interview(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(68), applicants.get(48), pending(), appDate()));
//
//        // Applicant 49 (Xander Lee)
//        jobApplications.add(new JobApplication(jobPostings.get(49), applicants.get(49), shortlist(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(54), applicants.get(49), rejected(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(59), applicants.get(49), withdrawn(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(64), applicants.get(49), offer(), appDate()));
//        jobApplications.add(new JobApplication(jobPostings.get(69), applicants.get(49), accept(), appDate()));
//
          //endregion

        ListInterface<Integer> appWithPast = computeAppsWithPast();
        ListInterface<Integer> appWithFuture = computeAppsWithFuture();
        ListInterface<String> remarks = new DoublyLinkedList<>();

        // region Remarks
        remarks.add("Technical skills assessment");
        remarks.add("Behavioral fit interview");
        remarks.add("Coding challenge session");
        remarks.add("Panel discussion round");
        remarks.add("HR final review");
        remarks.add("System design critique");
        remarks.add("Case study analysis");
        remarks.add("Technical depth interview");
        remarks.add("Behavioral strengths evaluation");
        remarks.add("Coding test round");
        remarks.add("Panel team interview");
        remarks.add("HR screening call");
        remarks.add("Technical problem solving");
        remarks.add("System architecture review");
        remarks.add("Problem solving session");
        remarks.add("Team fit assessment");
        remarks.add("Whiteboard coding test");
        remarks.add("Leadership skills interview");
        remarks.add("Project overview discussion");
        remarks.add("Algorithm design challenge");
        remarks.add("Cultural fit evaluation");
        remarks.add("Mock presentation review");
        remarks.add("Technical deep dive");
        remarks.add("Soft skills assessment");
        remarks.add("Data structures test");
        remarks.add("Product design review");
        remarks.add("Stakeholder alignment meeting");
        remarks.add("Coding pair programming");
        remarks.add("Behavioral scenario analysis");
        remarks.add("System scalability discussion");
        remarks.add("Career goals review");
        remarks.add("API design evaluation");
        remarks.add("Technical aptitude test");
        remarks.add("Team collaboration interview");
        remarks.add("Code review session");
        remarks.add("Performance optimization challenge");
        remarks.add("Cross-functional team interview");
        remarks.add("Database design test");
        remarks.add("Situational judgment interview");
        remarks.add("Technical case study");
        remarks.add("Values alignment session");
        remarks.add("System integration review");
        remarks.add("Peer feedback interview");
        remarks.add("Debugging skills challenge");
        remarks.add("Product strategy session");
        remarks.add("Technical skills evaluation");
        remarks.add("HR final discussion");
        remarks.add("Architecture design review");
        remarks.add("Behavioral depth interview");
        remarks.add("Coding simulation test");
        remarks.add("Panel review session");
        remarks.add("System design analysis");
        remarks.add("Leadership potential assessment");
        remarks.add("Technical knowledge test");
        remarks.add("Team dynamics interview");
        remarks.add("Code optimization challenge");
        remarks.add("Product vision discussion");
        remarks.add("Technical presentation round");
        remarks.add("Behavioral adaptability interview");
        remarks.add("System reliability review");
        remarks.add("Cultural alignment session");
        remarks.add("Algorithm optimization test");
        remarks.add("Stakeholder collaboration meeting");
        remarks.add("Coding accuracy challenge");
        remarks.add("Technical vision session");
        remarks.add("Behavioral impact interview");
        remarks.add("System robustness review");
        remarks.add("Team synergy assessment");
        remarks.add("Code quality evaluation");
        remarks.add("Product scalability session");
        remarks.add("Technical strategy interview");
        remarks.add("Behavioral growth discussion");
        remarks.add("System optimization review");
        remarks.add("Cultural contribution interview");
        remarks.add("Data modeling test");
        remarks.add("Cross-team collaboration interview");
        remarks.add("Technical innovation session");
        remarks.add("Behavioral resilience interview");
        remarks.add("System security review");
        remarks.add("Values fit assessment");
        remarks.add("Algorithm design test");
        remarks.add("Stakeholder alignment discussion");
        remarks.add("Coding pressure test");
        remarks.add("Technical leadership interview");
        remarks.add("System performance review");
        remarks.add("Team integration session");
        remarks.add("Code reliability check");
        remarks.add("Product innovation discussion");
        remarks.add("Technical execution interview");
        remarks.add("Behavioral strengths interview");
        remarks.add("System efficiency review");
        remarks.add("Team alignment discussion");
        remarks.add("Code accuracy assessment");
        remarks.add("Product roadmap session");
        remarks.add("Technical troubleshooting interview");
        remarks.add("Behavioral fit assessment");
        remarks.add("System resilience review");
        remarks.add("Cultural impact interview");
        remarks.add("Algorithm performance test");
        // endregion

        for (Integer i : appWithPast) {
            String remark = remarks.get(random.nextInt(remarks.size()));
            ScheduledInterview interview = genPastSchedule(jobApplications.get(i), remark);
            interviews.add(interview);

            JobApplication application = jobApplications.get(i);
            if (application.isTerminated()) {
                LocalDateTime terminatedAt = getRandomDateTimeBetween(interview.getTimeSlot().getEndDateTime(), Context.getDateTime());
                application.setTerminatedAt(terminatedAt);
            }
        }

        for (Integer i : appWithFuture) {
            String remark = remarks.get(random.nextInt(remarks.size()));
            ScheduledInterview interview = genFutureSchedule(jobApplications.get(i), remark);
            interviews.add(interview);
        }

        companies.add(new Company("Test 1", "Empty test company 1", new Location(City.SETAPAK), "test1@employer.com", "0119999999"));
        companies.add(new Company("Test 2", "Empty test company 2", new Location(City.KUALA_LUMPUR), "test2@employer.com", "0118888888"));
        companies.add(new Company("Test 3", "Empty test company 3", new Location(City.PENANG_HILL), "test3@employer.com", "011777777"));

        jobPostings.add(new JobPosting("AI Engineer", companies.get(0), 1000, 1500, "Build and deploy AI models for intelligent systems", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(14), md(5)));
        jobPostings.add(new JobPosting("LLM Specialist", companies.get(0), 2000, 2200, "Fine-tune and optimize large language models", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(30), md(3)));
        jobPostings.add(new JobPosting("Data Analyst", companies.get(1), 1200, 1600, "Analyze datasets to support data-driven decisions", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(20), md(4)));
        jobPostings.add(new JobPosting("Cloud Engineer", companies.get(1), 1800, 2100, "Maintain and optimize cloud infrastructure services", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(25), md(3)));

        applicants.add(new Applicant("Test 1", "test1@applicant.com", "0112222222", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Test 2", "test2@applicant.com", "0113333333", t(), r(), re(), rw(), rl(), rs()));
        applicants.add(new Applicant("Test 3", "test3@applicant.com", "0114444444", t(), r(), re(), rw(), rl(), rs()));


    }

    public static LocalDateTime getRandomDateTimeBetween(LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start datetime must be before end datetime");
        }
        long startEpoch = start.toEpochSecond(ZoneOffset.UTC);
        long endEpoch = end.toEpochSecond(ZoneOffset.UTC);
        long randomEpoch = ThreadLocalRandom.current().nextLong(startEpoch, endEpoch + 1);
        return LocalDateTime.ofEpochSecond(randomEpoch, 0, ZoneOffset.UTC);
    }

    /*
     Rules for Past Interviews:
     Application Date (appliedAt) < Book Date (bookedAt)
     Booked Date (bookedAt) < TimeSlot (startSlot), 3 hours gap
     TimeSlot (endSlot) < now
     TimeSlot shall not be collapsed with occupied one, is not weekend

     Rules for Future Interviews:
     Application Date (appliedAt) < Book Date (bookedAt)
     Booked Date (bookedAt) < now, 1 hour gap
     now < TimeSlot (start), 2 hour gap
     TimeSlot shall be within MAX_DAYS_OFFSET = 7, for example today day 1, then Timeslot shall be within day 1 - 7
     TimeSlot shall not be collapsed with occupied one, is not weekend
    */
    private static final int MAX_SLOT_GENERATION_ATTEMPTS = 1000;
    private static final int SLOTS_PER_DAY = 18;
    private static final int MAX_DAYS_OFFSET = 7;
    private static StringBuilder logBuilder = new StringBuilder();

    public static LocalDateTime appDate() {
        // if weekday, minus 7 - 14
        // if weekend, minus 10 - 17
        // if monday, minus 11 - 17
        // if tuesday, minus 12 - 17
        LocalDateTime now = Context.getDateTime();
        int daysAgo = 5 + random.nextInt(15);
        DayOfWeek dow = now.getDayOfWeek();
        int hoursAgo = random.nextInt(24);
        return now.minusDays(daysAgo).minusHours(hoursAgo);
    }

    public static LocalDateTime genBookedAt(LocalDateTime appDate) {
        LocalDateTime now = Context.getDateTime();
        long hoursBetween = ChronoUnit.HOURS.between(appDate, now);
        int randomOffset = hoursBetween > 0
                ? 1 + random.nextInt((int) hoursBetween)
                : 1;
        int daysOffset = randomOffset / 24;
        int hoursOffset = randomOffset % 24;
        return appDate.plusDays(daysOffset).plusHours(hoursOffset);
    }

    public static ListInterface<TimeSlot> getAvailablePastTimeSlots(
            LocalDateTime bookedAt,
            ListInterface<TimeSlot> occupiedSlots) {

        ListInterface<TimeSlot> availableSlots = new DoublyLinkedList<>();
        LocalDateTime now = Context.getDateTime();
        LocalDateTime latestAllowed = now.minusHours(2);

        LocalDate startDate = bookedAt.toLocalDate();
        LocalDate endDate = latestAllowed.toLocalDate();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            for (int slotIndex = 0; slotIndex < SLOTS_PER_DAY; slotIndex++) {
                TimeSlot slot = new TimeSlot(date, slotIndex);
                LocalDateTime slotStart = slot.getStartDateTime();
                LocalDateTime slotEnd = slot.getEndDateTime();

                if (slot.isWeekend()) continue;
                if (!slotStart.isAfter(bookedAt.plusHours(3))) continue;
                if (!slotEnd.isBefore(now.minusHours(2))) continue;
                if (occupiedSlots.contains(slot)) continue;

                availableSlots.add(slot);
            }
        }

        return availableSlots;
    }

    public static ListInterface<TimeSlot> getAvailableFutureTimeSlots(
            LocalDateTime bookedAt,
            ListInterface<TimeSlot> occupiedSlots) {
        ListInterface<TimeSlot> availableSlots = new DoublyLinkedList<>();
        LocalDateTime now = Context.getDateTime();

        if (!bookedAt.isBefore(now.minusHours(1))) {
            return availableSlots;
        }

        LocalDate startDate = now.toLocalDate();
        LocalDate endDate = now.plusDays(MAX_DAYS_OFFSET - 1).toLocalDate();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            for (int slotIndex = 0; slotIndex < SLOTS_PER_DAY; slotIndex++) {
                TimeSlot slot = new TimeSlot(date, slotIndex);
                LocalDateTime slotStart = slot.getStartDateTime();
                if (!slotStart.isAfter(now.plusHours(2))) continue;
                if (slot.isWeekend()) continue;
                if (occupiedSlots.contains(slot)) continue;
                availableSlots.add(slot);
            }
        }
        return availableSlots;
    }

    public static ListInterface<TimeSlot> getOccupiedSlots(JobApplication jobApplication) {
        Company company = jobApplication.getJobPosting().getCompany();
        Applicant applicant = jobApplication.getApplicant();
        ListInterface<TimeSlot> companyOccupiedSlots = new DoublyLinkedList<>();
        ListInterface<TimeSlot> applicantOccupiedSlots = new DoublyLinkedList<>();

        for (int i = 0; i < interviews.size(); i++) {
            ScheduledInterview interview = interviews.get(i);
            if (interview.getJobApplication().getJobPosting().getCompany().equals(company)) {
                companyOccupiedSlots.add(interview.getTimeSlot());
            }
            if (interview.getJobApplication().getApplicant().equals(applicant)) {
                applicantOccupiedSlots.add(interview.getTimeSlot());
            }
        }
        companyOccupiedSlots.merge(applicantOccupiedSlots);
        return companyOccupiedSlots;
    }

    public static ScheduledInterview genPastSchedule(JobApplication jobApplication, String remark) {
        LocalDateTime appDate = jobApplication.getAppliedAt();
        LocalDateTime bookedAt = null;
        TimeSlot timeslot = null;
        int rate = random.nextInt(8) + 3;

        for (int attempt = 0; attempt < MAX_SLOT_GENERATION_ATTEMPTS; attempt++) {
            bookedAt = genBookedAt(appDate);
            ListInterface<TimeSlot> occupiedTimeSlot = getOccupiedSlots(jobApplication);
            ListInterface<TimeSlot> availableTimeSlot = getAvailablePastTimeSlots(bookedAt, occupiedTimeSlot);

            if (availableTimeSlot.isEmpty()) continue;
            timeslot = availableTimeSlot.get(random.nextInt(availableTimeSlot.size()));
            break;
        }
        if (timeslot == null) {
            Log.warn("Could not generate a valid PAST TimeSlot for AppID " + jobApplication.getId() + " after " + MAX_SLOT_GENERATION_ATTEMPTS + " attempts.");
            Log.warn(jobApplication.toString());
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println("=== Schedule Generation Log ===");

            System.out.println("Applied At : " + appDate.format(dateTimeFormatter) + " (" + appDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + ")");
            System.out.println("Booked At  : " + bookedAt.format(dateTimeFormatter) + " (" + bookedAt.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + ")");
            System.out.println("Tried Timeslots :");
            System.out.println(logBuilder.toString());
            System.out.println("===============================");
        }

        return new ScheduledInterview(jobApplication, remark, timeslot, rate, bookedAt);
    }

    public static ScheduledInterview genFutureSchedule(JobApplication jobApplication, String remark) {
        LocalDateTime appDate = jobApplication.getAppliedAt();
        LocalDateTime bookedAt = null;
        TimeSlot timeslot = null;

        for (int attempt = 0; attempt < MAX_SLOT_GENERATION_ATTEMPTS; attempt++) {
            bookedAt = genBookedAt(appDate);
            ListInterface<TimeSlot> occupiedTimeSlot = getOccupiedSlots(jobApplication);
            ListInterface<TimeSlot> availableTimeSlot = getAvailableFutureTimeSlots(bookedAt, occupiedTimeSlot);
            if (availableTimeSlot.isEmpty()) continue;
            timeslot = availableTimeSlot.get(random.nextInt(availableTimeSlot.size()));
            break;
        }
        if (timeslot == null) {
            Log.warn("Could not generate a valid FUTURE TimeSlot for AppID " + jobApplication.getId() + " after " + MAX_SLOT_GENERATION_ATTEMPTS + " attempts.");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println("=== Schedule Generation Log ===");
            System.out.println("Applied At : " + appDate.format(dateTimeFormatter) + " (" + appDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + ")");
            System.out.println("Booked At  : " + bookedAt.format(dateTimeFormatter) + " (" + bookedAt.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + ")");
            System.out.println("===============================");
        }
        return new ScheduledInterview(jobApplication, remark, timeslot, bookedAt);
    }

    public static LocalDateTime md(int minus) {
        LocalDateTime now = Context.getDateTime();
        DayOfWeek dow = now.getDayOfWeek();
        if (dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY) {
            now = now.minusDays(3);
        }
        return now.minusDays(minus);
    }

    public static Location r() {
        City[] cities = City.values();
        City randomCity = cities[random.nextInt(cities.length)];
        return new Location(randomCity);
    }

    public static JobPosting.Type t() {
        JobPosting.Type[] types = JobPosting.Type.values();
        return types[random.nextInt(types.length)];
    }

    public static EducationLevel re() {
        ListInterface<EducationLevel> list = educationLevels();
        return list.get(random.nextInt(list.size()));
    }

    public static ListInterface<LanguageProficiency> rl() {
        ListInterface<LanguageProficiency> original = languageProficiencies();
        LanguageProficiency randomItem = original.get(random.nextInt(original.size()));

        ListInterface<LanguageProficiency> singleItemList = new DoublyLinkedList<>();
        singleItemList.add(randomItem);

        return singleItemList;
    }

    public static ListInterface<Skill> ra() {
        ListInterface<Skill> original = skills();
        Skill randomItem = original.get(random.nextInt(original.size()));

        ListInterface<Skill> singleSkillList = new DoublyLinkedList<>();
        singleSkillList.add(randomItem);

        return singleSkillList;
    }

    public static ListInterface<Skill> rs() {
        ListInterface<Skill> original = skills();
        int originalSize = original.size();

        ListInterface<Skill> randomSkills = new DoublyLinkedList<>();
        int count = Math.min(3, originalSize);

        while (randomSkills.size() < count) {
            Skill skill = original.get(random.nextInt(originalSize));
            if (!randomSkills.contains(skill)) {
                randomSkills.add(skill);
            }
        }
        return randomSkills;
    }

    public static ListInterface<WorkExperience> rw() {
        ListInterface<WorkExperience> original = workExperiences();
        WorkExperience randomItem = original.get(random.nextInt(original.size()));

        ListInterface<WorkExperience> singleItemList = new DoublyLinkedList<>();
        singleItemList.add(randomItem);

        return singleItemList;
    }

    private static JobApplication.Status pending() {
        return JobApplication.Status.PENDING;
    }

    private static JobApplication.Status shortlist() {
        return JobApplication.Status.SHORTLISTED;
    }

    private static JobApplication.Status interview() {
        return JobApplication.Status.INTERVIEWED;
    }

    private static JobApplication.Status offer() {
        return JobApplication.Status.OFFERED;
    }

    private static JobApplication.Status accept() {
        return JobApplication.Status.ACCEPTED;
    }

    private static JobApplication.Status rejected() {
        return JobApplication.Status.REJECTED;
    }

    private static JobApplication.Status withdrawn() {
        return JobApplication.Status.WITHDRAWN;
    }

    private static JobApplication.Status generateStatus(Random random) {
        int r = random.nextInt(100); // 0 - 99

        if (r < 15) return pending();          // 0 - 14
        else if (r < 30) return shortlist();   // 15 - 29
        else if (r < 45) return interview();   // 30 - 44
        else if (r < 50) return offer();       // 45 - 50
        else if (r < 80) return accept();      // 50 - 79
        else if (r < 90) return rejected();    // 80 - 90
        else return withdrawn();              // 90 - 99
    }

    public static ListInterface<Integer> computeAppsWithPast() {
        ListInterface<Integer> interviewed = new DoublyLinkedList<>();
        ListInterface<Integer> shortlisted = new DoublyLinkedList<>();
        ListInterface<Integer> offered = new DoublyLinkedList<>();
        ListInterface<Integer> accepted = new DoublyLinkedList<>();
        ListInterface<Integer> rejected = new DoublyLinkedList<>();
        ListInterface<Integer> withdrawn = new DoublyLinkedList<>();
        populateStatusLists(jobApplications,
                interviewed,
                shortlisted,
                offered,
                accepted,
                rejected,
                withdrawn);
        ListInterface<Integer> appWithPast = new DoublyLinkedList<>();
        appWithPast.merge(offered);
        appWithPast.merge(accepted);
        appWithPast.merge(rejected);
        appWithPast.merge(withdrawn);

        return appWithPast;
    }

    public static ListInterface<Integer> computeAppsWithFuture() {
        ListInterface<Integer> interviewed = new DoublyLinkedList<>();
        ListInterface<Integer> shortlisted = new DoublyLinkedList<>();
        ListInterface<Integer> offered = new DoublyLinkedList<>();
        ListInterface<Integer> accepted = new DoublyLinkedList<>();
        ListInterface<Integer> rejected = new DoublyLinkedList<>();
        ListInterface<Integer> withdrawn = new DoublyLinkedList<>();
        populateStatusLists(jobApplications,
                interviewed,
                shortlisted,
                offered,
                accepted,
                rejected,
                withdrawn);
        ListInterface<Integer> appWithFuture = new DoublyLinkedList<>();
        appWithFuture.merge(interviewed);
        appWithFuture.merge(shortlisted);

        return appWithFuture;
    }

    public static void populateStatusLists(ListInterface<JobApplication> jobApplications, ListInterface<Integer> interviewed, ListInterface<Integer> shortlisted, ListInterface<Integer> offered, ListInterface<Integer> accepted, ListInterface<Integer> rejected, ListInterface<Integer> withdrawn) {
        interviewed.clear();
        shortlisted.clear();
        offered.clear();
        accepted.clear();
        rejected.clear();
        withdrawn.clear();

        for (int i = 0; i < jobApplications.size(); i++) {
            JobApplication application = jobApplications.get(i);
            if (application != null) {
                JobApplication.Status status = application.getStatus();
                switch (status) {
                    case INTERVIEWED:
                        interviewed.add(i);
                        break;
                    case SHORTLISTED:
                        shortlisted.add(i);
                        break;
                    case OFFERED:
                        offered.add(i);
                        break;
                    case ACCEPTED:
                        accepted.add(i);
                        break;
                    case REJECTED:
                        rejected.add(i);
                        break;
                    case WITHDRAWN:
                        withdrawn.add(i);
                        break;
                    case PENDING:
                        break;
                }
            }
        }
    }

    public static JobPosting.Status s() {
        return JobPosting.Status.OPEN;
    }

    public static ListInterface<Company> getCompanies() {
        return companies;
    }

    public static ListInterface<JobPosting> getJobPostings() {
        return jobPostings;
    }

    public static ListInterface<Applicant> getApplicants() {
        return applicants;
    }

    public static ListInterface<JobApplication> getJobApplications() {
        return jobApplications;
    }

    public static ListInterface<Invitation> getInvitations() {
        return invitations;
    }

    public static ListInterface<ScheduledInterview> getScheduledInterviews() {
        return interviews;
    }

    public static ListInterface<BlockedTimeSlot> getBlockedTimeSlots() {
        return blockedTimeSlots;
    }

    public static ListInterface<SkillOption> getSkillOptions() {
        return skillOptions;
    }

    private static int pickWeightedIndex(double[] weights, Random rand) {
        double total = 0;
        for (double w : weights) total += w;
        double r = rand.nextDouble() * total;
        double cum = 0;
        for (int i = 0; i < weights.length; i++) {
            cum += weights[i];
            if (r < cum) return i;
        }
        return weights.length - 1;
    }
}