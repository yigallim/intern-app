/**
 * @author Lim Yuet Yang
 */
package com.tarumt.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

import com.tarumt.adt.list.ArrayToLinked;
import com.tarumt.adt.set.HashSet;
import com.tarumt.adt.set.SetInterface;
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
    static final Random random = new Random();
    static final LocalDateTime now = Context.getDateTime();
    private static final ListInterface<Company> c = new DoublyLinkedList<>();
    private static final ListInterface<JobPosting> jobPostings = new DoublyLinkedList<>();
    private static final ListInterface<Applicant> applicants = new DoublyLinkedList<>();
    private static final ListInterface<JobApplication> jobApplications = new DoublyLinkedList<>();
    private static final ListInterface<Invitation> invitations = new DoublyLinkedList<>();
    private static final ListInterface<ScheduledInterview> scheduledInterviews = new DoublyLinkedList<>();
    private static final ListInterface<BlockedTimeSlot> blockedTimeSlots = new DoublyLinkedList<>();
    public static final ListInterface<SkillOption> skillOptions = new DoublyLinkedList<>();

    static {
        Initializer.init();
        Initializer.initSkill();
    }

    public static ListInterface<EducationLevel> educationLevels() {
        ListInterface<EducationLevel> educationLevels = new DoublyLinkedList<>();

        educationLevels.add(new EducationLevel(
                EducationLevel.DegreeLevel.BD,
                EducationLevel.FieldOfStudy.CS,
                EducationLevel.University.UM,
                3.8,
                false,
                Qualification.Importance.HIGH
        ));

        educationLevels.add(new EducationLevel(
                EducationLevel.DegreeLevel.MD,
                EducationLevel.FieldOfStudy.DS,
                EducationLevel.University.UKM,
                3.9,
                true,
                Qualification.Importance.MEDIUM
        ));

        educationLevels.add(new EducationLevel(
                EducationLevel.DegreeLevel.D,
                EducationLevel.FieldOfStudy.SE,
                EducationLevel.University.POLITEKNIK_MALAYSIA,
                3.5,
                true,
                Qualification.Importance.LOW
        ));

        educationLevels.add(new EducationLevel(
                EducationLevel.DegreeLevel.PhD,
                EducationLevel.FieldOfStudy.AI,
                EducationLevel.University.MONASH_MY,
                4.0,
                true,
                Qualification.Importance.MEDIUM
        ));

        return educationLevels;
    }

    public static ListInterface<LanguageProficiency> languageProficiencies() {
        ListInterface<LanguageProficiency> languageProficiencies = new DoublyLinkedList<>();

        languageProficiencies.add(new LanguageProficiency(
                LanguageProficiency.Language.EN,
                LanguageProficiency.Proficiency.FLUENT,
                false,
                Qualification.Importance.HIGH
        ));

        languageProficiencies.add(new LanguageProficiency(
                LanguageProficiency.Language.MY,
                LanguageProficiency.Proficiency.NATIVE,
                true,
                Qualification.Importance.MEDIUM
        ));

        languageProficiencies.add(new LanguageProficiency(
                LanguageProficiency.Language.CH,
                LanguageProficiency.Proficiency.INTERMEDIATE,
                true,
                Qualification.Importance.LOW
        ));

        languageProficiencies.add(new LanguageProficiency(
                LanguageProficiency.Language.JP,
                LanguageProficiency.Proficiency.ELEMENTARY,
                true,
                Qualification.Importance.LOW
        ));

        return languageProficiencies;
    }

    public static ListInterface<Skill> skills() {
        ListInterface<Skill> skills = new DoublyLinkedList<>();

        skills.add(new Skill(
                JobPosting.Type.IT_COMM_TEC,
                "Java Programming",
                Skill.ProficiencyLevel.EXPERT,
                false,
                Qualification.Importance.HIGH
        ));

        skills.add(new Skill(
                JobPosting.Type.IT_COMM_TEC,
                "Python Development",
                Skill.ProficiencyLevel.ADVANCED,
                true,
                Qualification.Importance.MEDIUM
        ));

        skills.add(new Skill(
                JobPosting.Type.IT_COMM_TEC,
                "Web Development",
                Skill.ProficiencyLevel.INTERMEDIATE,
                true,
                Qualification.Importance.MEDIUM
        ));

        skills.add(new Skill(
                JobPosting.Type.IT_COMM_TEC,
                "Cybersecurity",
                Skill.ProficiencyLevel.BEGINNER,
                true,
                Qualification.Importance.LOW
        ));

        return skills;
    }

    public static ListInterface<WorkExperience> workExperiences() {
        ListInterface<WorkExperience> workExperiences = new DoublyLinkedList<>();

        workExperiences.add(new WorkExperience(
                WorkExperience.Industry.IT_COMM_TEC,
                5,
                false,
                Qualification.Importance.HIGH
        ));

        workExperiences.add(new WorkExperience(
                WorkExperience.Industry.CONSULTING,
                3,
                true,
                Qualification.Importance.MEDIUM
        ));

        workExperiences.add(new WorkExperience(
                WorkExperience.Industry.EDUC_TRAINING,
                2,
                true,
                Qualification.Importance.LOW
        ));

        workExperiences.add(new WorkExperience(
                WorkExperience.Industry.BANK_FIN_SERV,
                4,
                true,
                Qualification.Importance.MEDIUM
        ));

        workExperiences.add(new WorkExperience(
                WorkExperience.Industry.HEALTH_MED,
                6,
                false,
                Qualification.Importance.HIGH
        ));

        workExperiences.add(new WorkExperience(
                WorkExperience.Industry.MARKETING_COMM,
                3,
                true,
                Qualification.Importance.MEDIUM
        ));

        workExperiences.add(new WorkExperience(
                WorkExperience.Industry.ENGINEERING,
                7,
                false,
                Qualification.Importance.HIGH
        ));

        workExperiences.add(new WorkExperience(
                WorkExperience.Industry.HOSP_TOURISM,
                2,
                true,
                Qualification.Importance.LOW
        ));

        workExperiences.add(new WorkExperience(
                WorkExperience.Industry.RETAIL_CONSUMER,
                4,
                true,
                Qualification.Importance.MEDIUM
        ));

        workExperiences.add(new WorkExperience(
                WorkExperience.Industry.SCIENCE_TECH,
                5,
                true,
                Qualification.Importance.MEDIUM
        ));

        return workExperiences;
    }

    private static void initSkill() {
        // IT & Communications Technology
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.IT_COMM_TEC, "Java Programming"),
                new SkillOption(JobPosting.Type.IT_COMM_TEC, "Python Development"),
                new SkillOption(JobPosting.Type.IT_COMM_TEC, "Web Development"),
                new SkillOption(JobPosting.Type.IT_COMM_TEC, "Cybersecurity"),
                new SkillOption(JobPosting.Type.IT_COMM_TEC, "Cloud Computing")
        ));

// Accounting
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.ACCOUNTING, "Bookkeeping"),
                new SkillOption(JobPosting.Type.ACCOUNTING, "Tax Filing"),
                new SkillOption(JobPosting.Type.ACCOUNTING, "Auditing"),
                new SkillOption(JobPosting.Type.ACCOUNTING, "Financial Reporting"),
                new SkillOption(JobPosting.Type.ACCOUNTING, "QuickBooks")
        ));

// Design & Architecture
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.DESIGN_ARCH, "AutoCAD"),
                new SkillOption(JobPosting.Type.DESIGN_ARCH, "3D Modeling"),
                new SkillOption(JobPosting.Type.DESIGN_ARCH, "Interior Design"),
                new SkillOption(JobPosting.Type.DESIGN_ARCH, "UX Design"),
                new SkillOption(JobPosting.Type.DESIGN_ARCH, "Revit")
        ));

// HR & Recruitment
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.HR_RECRUIT, "Recruitment"),
                new SkillOption(JobPosting.Type.HR_RECRUIT, "Onboarding"),
                new SkillOption(JobPosting.Type.HR_RECRUIT, "Employee Engagement"),
                new SkillOption(JobPosting.Type.HR_RECRUIT, "HR Policies"),
                new SkillOption(JobPosting.Type.HR_RECRUIT, "Performance Reviews")
        ));

// Admin & Office Support
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.ADMIN_OFFICE, "Data Entry"),
                new SkillOption(JobPosting.Type.ADMIN_OFFICE, "Office Management"),
                new SkillOption(JobPosting.Type.ADMIN_OFFICE, "Microsoft Office"),
                new SkillOption(JobPosting.Type.ADMIN_OFFICE, "Customer Support"),
                new SkillOption(JobPosting.Type.ADMIN_OFFICE, "Scheduling")
        ));

// Marketing & Communications
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.MARKETING_COMM, "Content Creation"),
                new SkillOption(JobPosting.Type.MARKETING_COMM, "SEO"),
                new SkillOption(JobPosting.Type.MARKETING_COMM, "Social Media"),
                new SkillOption(JobPosting.Type.MARKETING_COMM, "Email Marketing"),
                new SkillOption(JobPosting.Type.MARKETING_COMM, "Copywriting")
        ));

// Engineering
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.ENGINEERING, "CAD Design"),
                new SkillOption(JobPosting.Type.ENGINEERING, "Electrical Systems"),
                new SkillOption(JobPosting.Type.ENGINEERING, "Mechanical Analysis"),
                new SkillOption(JobPosting.Type.ENGINEERING, "Project Management"),
                new SkillOption(JobPosting.Type.ENGINEERING, "SolidWorks")
        ));

// Healthcare & Medical
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.HEALTH_MED, "First Aid"),
                new SkillOption(JobPosting.Type.HEALTH_MED, "Patient Care"),
                new SkillOption(JobPosting.Type.HEALTH_MED, "Nursing Skills"),
                new SkillOption(JobPosting.Type.HEALTH_MED, "Medical Coding"),
                new SkillOption(JobPosting.Type.HEALTH_MED, "Phlebotomy")
        ));

// Community Services & Development
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.COMM_SVC_DEV, "Social Work"),
                new SkillOption(JobPosting.Type.COMM_SVC_DEV, "Counseling"),
                new SkillOption(JobPosting.Type.COMM_SVC_DEV, "Case Management"),
                new SkillOption(JobPosting.Type.COMM_SVC_DEV, "Community Engagement"),
                new SkillOption(JobPosting.Type.COMM_SVC_DEV, "Volunteer Coordination")
        ));

// Sales
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.SALES, "Customer Prospecting"),
                new SkillOption(JobPosting.Type.SALES, "Closing Deals"),
                new SkillOption(JobPosting.Type.SALES, "CRM Usage"),
                new SkillOption(JobPosting.Type.SALES, "Negotiation"),
                new SkillOption(JobPosting.Type.SALES, "Client Retention")
        ));

// Construction
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.CONSTRUCTION, "Blueprint Reading"),
                new SkillOption(JobPosting.Type.CONSTRUCTION, "Site Safety"),
                new SkillOption(JobPosting.Type.CONSTRUCTION, "Plumbing"),
                new SkillOption(JobPosting.Type.CONSTRUCTION, "Carpentry"),
                new SkillOption(JobPosting.Type.CONSTRUCTION, "Site Supervision")
        ));

// Science & Technology
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.SCIENCE_TECH, "Lab Safety"),
                new SkillOption(JobPosting.Type.SCIENCE_TECH, "Data Analysis"),
                new SkillOption(JobPosting.Type.SCIENCE_TECH, "Research Writing"),
                new SkillOption(JobPosting.Type.SCIENCE_TECH, "Experiment Design"),
                new SkillOption(JobPosting.Type.SCIENCE_TECH, "Hypothesis Testing")
        ));

// Real Estate
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.REAL_EST, "Property Valuation"),
                new SkillOption(JobPosting.Type.REAL_EST, "Property Listings"),
                new SkillOption(JobPosting.Type.REAL_EST, "Sales Agreements"),
                new SkillOption(JobPosting.Type.REAL_EST, "Investment Analysis"),
                new SkillOption(JobPosting.Type.REAL_EST, "Market Trend Analysis")
        ));

// Advertising, Arts & Media
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.AD_ARTS_MEDIA, "Graphic Design"),
                new SkillOption(JobPosting.Type.AD_ARTS_MEDIA, "Video Editing"),
                new SkillOption(JobPosting.Type.AD_ARTS_MEDIA, "Photography"),
                new SkillOption(JobPosting.Type.AD_ARTS_MEDIA, "Animation"),
                new SkillOption(JobPosting.Type.AD_ARTS_MEDIA, "Motion Graphics")
        ));

// Call Center & Customer Service
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.CALL_CUST_SVC, "Phone Etiquette"),
                new SkillOption(JobPosting.Type.CALL_CUST_SVC, "Problem Solving"),
                new SkillOption(JobPosting.Type.CALL_CUST_SVC, "Customer Onboarding"),
                new SkillOption(JobPosting.Type.CALL_CUST_SVC, "Ticketing Systems"),
                new SkillOption(JobPosting.Type.CALL_CUST_SVC, "Empathy")
        ));

// Consulting
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.CONSULTING, "Business Strategy"),
                new SkillOption(JobPosting.Type.CONSULTING, "Market Research"),
                new SkillOption(JobPosting.Type.CONSULTING, "Financial Modelling"),
                new SkillOption(JobPosting.Type.CONSULTING, "Data Analytics"),
                new SkillOption(JobPosting.Type.CONSULTING, "Problem Solving")
        ));

// Legal
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.LEGAL, "Legal Research"),
                new SkillOption(JobPosting.Type.LEGAL, "Contract Drafting"),
                new SkillOption(JobPosting.Type.LEGAL, "Legal Compliance"),
                new SkillOption(JobPosting.Type.LEGAL, "Legal Writing"),
                new SkillOption(JobPosting.Type.LEGAL, "Due Diligence")
        ));

// Education & Training
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.EDUC_TRAINING, "Lesson Planning"),
                new SkillOption(JobPosting.Type.EDUC_TRAINING, "Teaching"),
                new SkillOption(JobPosting.Type.EDUC_TRAINING, "Curriculum Design"),
                new SkillOption(JobPosting.Type.EDUC_TRAINING, "Tutoring"),
                new SkillOption(JobPosting.Type.EDUC_TRAINING, "Student Evaluation")
        ));

// Insurance
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.INSURANCE, "Policy Explanation"),
                new SkillOption(JobPosting.Type.INSURANCE, "Risk Assessment"),
                new SkillOption(JobPosting.Type.INSURANCE, "Claims Handling"),
                new SkillOption(JobPosting.Type.INSURANCE, "Underwriting"),
                new SkillOption(JobPosting.Type.INSURANCE, "Customer Retention")
        ));

// Banking & Financial Services
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.BANK_FIN_SERV, "Banking Operations"),
                new SkillOption(JobPosting.Type.BANK_FIN_SERV, "Credit Analysis"),
                new SkillOption(JobPosting.Type.BANK_FIN_SERV, "Financial Advising"),
                new SkillOption(JobPosting.Type.BANK_FIN_SERV, "Loan Processing"),
                new SkillOption(JobPosting.Type.BANK_FIN_SERV, "Risk Management")
        ));

// Sport & Recreation
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.SPORT_RECREATION, "Fitness Training"),
                new SkillOption(JobPosting.Type.SPORT_RECREATION, "Sports Coaching"),
                new SkillOption(JobPosting.Type.SPORT_RECREATION, "Injury Prevention"),
                new SkillOption(JobPosting.Type.SPORT_RECREATION, "Event Planning"),
                new SkillOption(JobPosting.Type.SPORT_RECREATION, "Nutrition Planning")
        ));

// Manufacturing & Logistics
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.MANF_LOG, "Supply Chain"),
                new SkillOption(JobPosting.Type.MANF_LOG, "Inventory Control"),
                new SkillOption(JobPosting.Type.MANF_LOG, "Warehouse Management"),
                new SkillOption(JobPosting.Type.MANF_LOG, "Lean Manufacturing"),
                new SkillOption(JobPosting.Type.MANF_LOG, "Shipping Logistics")
        ));

// Hospitality & Tourism
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.HOSP_TOURISM, "Guest Services"),
                new SkillOption(JobPosting.Type.HOSP_TOURISM, "Reservation Management"),
                new SkillOption(JobPosting.Type.HOSP_TOURISM, "Customer Service"),
                new SkillOption(JobPosting.Type.HOSP_TOURISM, "Front Desk Operations"),
                new SkillOption(JobPosting.Type.HOSP_TOURISM, "Event Coordination")
        ));

// Agriculture
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.AGRICULTURE, "Crop Management"),
                new SkillOption(JobPosting.Type.AGRICULTURE, "Irrigation"),
                new SkillOption(JobPosting.Type.AGRICULTURE, "Pest Control"),
                new SkillOption(JobPosting.Type.AGRICULTURE, "Farm Equipment Operation"),
                new SkillOption(JobPosting.Type.AGRICULTURE, "Sustainable Practices")
        ));

// Retail & Consumer
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.RETAIL_CONSUMER, "Cash Handling"),
                new SkillOption(JobPosting.Type.RETAIL_CONSUMER, "Customer Service"),
                new SkillOption(JobPosting.Type.RETAIL_CONSUMER, "Sales Techniques"),
                new SkillOption(JobPosting.Type.RETAIL_CONSUMER, "Inventory Tracking"),
                new SkillOption(JobPosting.Type.RETAIL_CONSUMER, "Visual Merchandising")
        ));

// Other
        skillOptions.merge(ArrayToLinked.asList(
                new SkillOption(JobPosting.Type.OTHER, "Time Management"),
                new SkillOption(JobPosting.Type.OTHER, "Problem Solving"),
                new SkillOption(JobPosting.Type.OTHER, "Team Collaboration"),
                new SkillOption(JobPosting.Type.OTHER, "Adaptability"),
                new SkillOption(JobPosting.Type.OTHER, "Critical Thinking")
        ));
    }

    private static void init() {
        if (false) return;
        // region Init
        // 30 Companies (Hard-Coded, Adhering to Constraints, Using Malaysian Cities)
        c.add(new Company("TechCorp", "Leading tech company focused on innovation", new Location(City.JOHOR_BAHRU), "contact@techcorp.com", "0123456789"));
        c.add(new Company("Innovatech", "Innovative tech solutions for the future", new Location(City.KUALA_LUMPUR), "info@innovatech.com", "0198765432"));
        c.add(new Company("FinServe", "Reliable financial services for all sectors", new Location(City.PENANG_HILL), "support@finserve.com", "0123487654"));
        c.add(new Company("HealthPlus", "Healthcare solutions for better living", new Location(City.IPOH), "hello@healthplus.com", "0129876543"));
        c.add(new Company("EduTech", "Education tech for modern learning", new Location(City.KUCHING), "contact@edutech.com", "0134567890"));
        c.add(new Company("GreenEnergy", "Sustainable energy for a better planet", new Location(City.KOTA_KINABALU), "info@greenenergy.com", "0145678901"));
        c.add(new Company("AutoDrive", "Autonomous vehicles for the future", new Location(City.MELAKA), "contact@autodrive.com", "0156789012"));
        c.add(new Company("FoodieTech", "Tech solutions for the food industry", new Location(City.SEREMBAN), "support@foodietech.com", "0167890123"));
        c.add(new Company("CyberShield", "Cybersecurity for a safer digital world", new Location(City.KUANTAN_PAHANG), "info@cybershield.com", "0178901234"));
        c.add(new Company("QuantumSoft", "Quantum computing software solutions", new Location(City.SUNGAI_PETANI), "contact@quantumsoft.com", "0189012345"));
        c.add(new Company("MediCare", "Medical research for better healthcare", new Location(City.KOTA_BHARU), "contact@medicare.com", "0190123456"));
        c.add(new Company("CloudSys", "Cloud solutions for scalable growth", new Location(City.SHAH_ALAM), "info@cloudsys.com", "0191234567"));
        c.add(new Company("NeuroTech", "Neuroscience and AI research solutions", new Location(City.KULIM), "contact@neurotech.com", "0192345678"));
        c.add(new Company("AgriGrow", "Agricultural tech for sustainable farming", new Location(City.TAIPING), "support@agrigrow.com", "0193456789"));
        c.add(new Company("SpaceFront", "Aerospace innovation for space exploration", new Location(City.ALOR_SETAR), "hello@spacefront.com", "0194567890"));
        c.add(new Company("SmartRetail", "Innovative retail technology solutions for modern businesses", new Location(City.PETALING_JAYA), "info@smartretail.com", "0125678901"));
        c.add(new Company("BioTech", "Biotechnology advancements for health and agriculture", new Location(City.SUNGAI_BULOH), "contact@biotech.com", "0136789012"));
        c.add(new Company("UrbanPlan", "Smart city planning and urban development solutions", new Location(City.PUTRAJAYA), "support@urbanplan.com", "0147890123"));
        c.add(new Company("CleanTech", "Eco-friendly technology for sustainable industries", new Location(City.BANDAR_SERI_ISKANDAR), "hello@cleantech.com", "0158901234"));
        c.add(new Company("DataWave", "Big data analytics and AI-driven insights", new Location(City.CYBERJAYA), "info@datawave.com", "0169012345"));
        c.add(new Company("TravelTech", "Technology solutions for the travel and tourism industry", new Location(City.LANGKAWI), "contact@traveltech.com", "0170123456"));
        c.add(new Company("SecureNet", "Advanced network security and IT infrastructure", new Location(City.KUALA_TERENGGANU), "support@securenet.com", "0181234567"));
        c.add(new Company("EduPlatform", "Online learning platforms for global education", new Location(City.BUKIT_MERTAJAM), "info@eduplatform.com", "0192345678"));
        c.add(new Company("HealthTech", "Digital health solutions for patient care", new Location(City.SIBU), "contact@healthtech.com", "0123456780"));
        c.add(new Company("LogiSys", "Logistics and supply chain management technology", new Location(City.PASIR_GUDANG), "support@logisys.com", "0134567891"));
        c.add(new Company("FinTech", "Financial technology for seamless transactions", new Location(City.KLIA), "info@fintech.com", "0145678902"));
        c.add(new Company("GameStudio", "Interactive gaming and virtual reality experiences", new Location(City.MIRI), "contact@gamestudio.com", "0156789013"));
        c.add(new Company("SmartHome", "IoT solutions for connected home automation", new Location(City.SEREMBAN), "hello@smarthome.com", "0167890124"));
        c.add(new Company("EcoFarm", "Sustainable agriculture and precision farming tech", new Location(City.KULAI), "support@ecofarm.com", "0178901235"));
        c.add(new Company("RoboTech", "Robotics and automation for industrial efficiency", new Location(City.BATU_PAHAT), "info@robotech.com", "0189012346"));
        c.add(new Company("Test", "Empty test company", new Location(City.SETAPAK), "test@test.com", "0119999999"));

        // 150 Job Postings (5 per company, Hard-Coded, Qualifications set to null)s
        // TechCorp (Company 0)

        jobPostings.add(new JobPosting("Software Engineer", c.get(0), 5000, 8000, "Develop scalable web applications", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Senior Developer", c.get(0), 7000, 10000, "Lead development projects for clients", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("DevOps Engineer", c.get(0), 6000, 9000, "Manage CI/CD pipelines and deployments", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("QA Engineer", c.get(0), 4000, 6000, "Ensure product quality through testing", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("UI/UX Designer", c.get(0), 4500, 7000, "Design user interfaces for web apps", JobPosting.Type.DESIGN_ARCH, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // Innovatech (Company 1)
        jobPostings.add(new JobPosting("Data Scientist", c.get(1), 6000, 9000, "Analyze complex datasets for insights", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("AI Engineer", c.get(1), 7000, 11000, "Develop AI models for automation", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("ML Engineer", c.get(1), 6500, 10000, "Implement ML algorithms for projects", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Research Scientist", c.get(1), 8000, 12000, "Conduct innovative research in tech", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Product Manager", c.get(1), 7000, 11000, "Manage product lifecycle and strategy", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // FinServe (Company 2)
        jobPostings.add(new JobPosting("Financial Analyst", c.get(2), 5000, 8000, "Analyze financial data for insights", JobPosting.Type.BANK_FIN_SERV, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Risk Manager", c.get(2), 6000, 9000, "Assess financial risks for the company", JobPosting.Type.BANK_FIN_SERV, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Accountant", c.get(2), 4500, 7000, "Manage financial records and reports", JobPosting.Type.ACCOUNTING, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Investment Advisor", c.get(2), 5500, 8500, "Provide investment advice to clients", JobPosting.Type.BANK_FIN_SERV, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Compliance Officer", c.get(2), 5000, 8000, "Ensure regulatory compliance", JobPosting.Type.BANK_FIN_SERV, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // HealthPlus (Company 3)
        jobPostings.add(new JobPosting("Medical Researcher", c.get(3), 6000, 9000, "Conduct medical research studies", JobPosting.Type.HEALTH_MED, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Clinical Analyst", c.get(3), 5000, 8000, "Analyze clinical data for insights", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Healthcare Manager", c.get(3), 7000, 10000, "Manage healthcare operations", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Health Consultant", c.get(3), 5500, 8500, "Provide health consultancy services", JobPosting.Type.HEALTH_MED, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Data Specialist", c.get(3), 5000, 8000, "Handle healthcare data systems", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // EduTech (Company 4)
        jobPostings.add(new JobPosting("EdTech Developer", c.get(4), 5000, 8000, "Develop educational software tools", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Content Creator", c.get(4), 4000, 6000, "Create educational content", JobPosting.Type.EDUC_TRAINING, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Instructional Designer", c.get(4), 4500, 7000, "Design learning materials", JobPosting.Type.EDUC_TRAINING, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Tech Support", c.get(4), 3500, 5000, "Provide tech support for platforms", JobPosting.Type.CALL_CUST_SVC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Project Manager", c.get(4), 6000, 9000, "Manage education tech projects", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // GreenEnergy (Company 5)
        jobPostings.add(new JobPosting("Energy Engineer", c.get(5), 6000, 9000, "Design sustainable energy systems", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Sustainability Analyst", c.get(5), 5000, 8000, "Analyze sustainability metrics", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Project Coordinator", c.get(5), 4500, 7000, "Coordinate energy projects", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Field Technician", c.get(5), 4000, 6000, "Install energy systems", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Research Analyst", c.get(5), 5000, 8000, "Research green energy solutions", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // AutoDrive (Company 6)
        jobPostings.add(new JobPosting("Robotics Engineer", c.get(6), 7000, 11000, "Develop autonomous vehicle systems", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Software Developer", c.get(6), 6000, 9000, "Code vehicle control software", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Test Engineer", c.get(6), 5000, 8000, "Test autonomous vehicle systems", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Data Engineer", c.get(6), 5500, 8500, "Manage vehicle data pipelines", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Safety Analyst", c.get(6), 5000, 8000, "Ensure vehicle safety standards", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // FoodieTech (Company 7)
        jobPostings.add(new JobPosting("App Developer", c.get(7), 5000, 8000, "Develop food delivery apps", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Marketing Specialist", c.get(7), 4500, 7000, "Market food tech solutions", JobPosting.Type.MARKETING_COMM, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Data Analyst", c.get(7), 5000, 8000, "Analyze food industry trends", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Logistics Manager", c.get(7), 5500, 8500, "Manage food delivery logistics", JobPosting.Type.MANF_LOG, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Customer Support", c.get(7), 3500, 5000, "Support food app users", JobPosting.Type.CALL_CUST_SVC, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // CyberShield (Company 8)
        jobPostings.add(new JobPosting("Security Analyst", c.get(8), 6000, 9000, "Analyze cybersecurity threats", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Penetration Tester", c.get(8), 6500, 9500, "Test system vulnerabilities", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Security Engineer", c.get(8), 7000, 10000, "Design secure systems", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Compliance Analyst", c.get(8), 5000, 8000, "Ensure regulatory compliance", JobPosting.Type.LEGAL, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Incident Responder", c.get(8), 5500, 8500, "Respond to security incidents", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // QuantumSoft (Company 9)
        jobPostings.add(new JobPosting("Quantum Developer", c.get(9), 8000, 12000, "Develop quantum computing software", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Research Scientist", c.get(9), 7500, 11000, "Research quantum algorithms", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Software Engineer", c.get(9), 6000, 9000, "Code quantum applications", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Data Scientist", c.get(9), 6500, 9500, "Analyze quantum computing data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Project Manager", c.get(9), 7000, 10000, "Manage quantum projects", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // MediCare (Company 10)
        jobPostings.add(new JobPosting("Clinical Researcher", c.get(10), 6000, 9000, "Conduct clinical research studies", JobPosting.Type.HEALTH_MED, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Medical Analyst", c.get(10), 5000, 8000, "Analyze medical data trends", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Health Consultant", c.get(10), 5500, 8500, "Provide medical consultancy", JobPosting.Type.HEALTH_MED, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Data Specialist", c.get(10), 5000, 8000, "Manage medical data systems", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Operations Manager", c.get(10), 6000, 9000, "Oversee medical operations", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // CloudSys (Company 11)
        jobPostings.add(new JobPosting("Cloud Engineer", c.get(11), 6000, 9000, "Design cloud infrastructure", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("DevOps Specialist", c.get(11), 6500, 9500, "Manage cloud deployments", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Security Analyst", c.get(11), 5500, 8500, "Secure cloud systems", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Data Engineer", c.get(11), 6000, 9000, "Build data pipelines in cloud", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Cloud Architect", c.get(11), 7000, 11000, "Architect cloud solutions", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // NeuroTech (Company 12)
        jobPostings.add(new JobPosting("Neuroscientist", c.get(12), 7000, 11000, "Research neural networks", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("AI Developer", c.get(12), 6500, 9500, "Develop AI for neuroscience", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Data Scientist", c.get(12), 6000, 9000, "Analyze neural data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Research Analyst", c.get(12), 5500, 8500, "Support neuroscience research", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Software Engineer", c.get(12), 6000, 9000, "Code neuroscience tools", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // AgriGrow (Company 13)
        jobPostings.add(new JobPosting("Agri Engineer", c.get(13), 5000, 8000, "Design farming technology", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Data Analyst", c.get(13), 4500, 7000, "Analyze agricultural data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Field Manager", c.get(13), 4000, 6000, "Manage farming operations", JobPosting.Type.AGRICULTURE, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Tech Specialist", c.get(13), 4500, 7000, "Support agri-tech systems", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Sustainability Lead", c.get(13), 5000, 8000, "Lead sustainable farming", JobPosting.Type.AGRICULTURE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // SpaceFront (Company 14)
        jobPostings.add(new JobPosting("Aerospace Engineer", c.get(14), 7000, 11000, "Design aerospace systems", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Space Researcher", c.get(14), 6500, 9500, "Research space technologies", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Test Engineer", c.get(14), 6000, 9000, "Test aerospace components", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Data Analyst", c.get(14), 5500, 8500, "Analyze space mission data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Project Manager", c.get(14), 7000, 10000, "Manage space projects", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // SmartRetail (Company 15)
        jobPostings.add(new JobPosting("Retail Software Engineer", c.get(15), 5000, 8000, "Develop retail management software", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Retail Data Analyst", c.get(15), 4500, 7000, "Analyze retail sales data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("UX Designer", c.get(15), 4000, 6500, "Design intuitive retail interfaces", JobPosting.Type.DESIGN_ARCH, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Customer Success Manager", c.get(15), 5000, 7500, "Support retail clients", JobPosting.Type.CALL_CUST_SVC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Marketing Coordinator", c.get(15), 4000, 6000, "Plan retail marketing campaigns", JobPosting.Type.MARKETING_COMM, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // BioTech (Company 16)
        jobPostings.add(new JobPosting("Biotech Researcher", c.get(16), 6000, 9000, "Conduct biotech experiments", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Lab Technician", c.get(16), 4000, 6000, "Support lab operations", JobPosting.Type.HEALTH_MED, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Bioinformatics Specialist", c.get(16), 5500, 8500, "Analyze biological data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Regulatory Affairs Manager", c.get(16), 6000, 9000, "Ensure compliance with regulations", JobPosting.Type.LEGAL, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Project Manager", c.get(16), 6500, 9500, "Manage biotech projects", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // UrbanPlan (Company 17)
        jobPostings.add(new JobPosting("Urban Planner", c.get(17), 5500, 8500, "Design sustainable urban layouts", JobPosting.Type.DESIGN_ARCH, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("GIS Analyst", c.get(17), 5000, 7500, "Analyze geographic data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Project Coordinator", c.get(17), 4500, 7000, "Coordinate urban projects", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Sustainability Consultant", c.get(17), 5000, 8000, "Advise on green urban solutions", JobPosting.Type.CONSULTING, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Civil Engineer", c.get(17), 6000, 9000, "Design urban infrastructure", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // CleanTech (Company 18)
        jobPostings.add(new JobPosting("Environmental Engineer", c.get(18), 5500, 8500, "Develop eco-friendly technologies", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Sustainability Analyst", c.get(18), 4500, 7000, "Analyze environmental impact", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Field Technician", c.get(18), 4000, 6000, "Install clean tech systems", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Research Scientist", c.get(18), 6000, 9000, "Research sustainable solutions", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Operations Manager", c.get(18), 6500, 9500, "Oversee clean tech operations", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // DataWave (Company 19)
        jobPostings.add(new JobPosting("Big Data Engineer", c.get(19), 6000, 9000, "Build data processing pipelines", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("AI Analyst", c.get(19), 5500, 8500, "Develop AI-driven insights", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Data Scientist", c.get(19), 6000, 9000, "Analyze large datasets", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("DevOps Engineer", c.get(19), 5500, 8500, "Manage data infrastructure", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Project Manager", c.get(19), 6500, 9500, "Lead data projects", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // TravelTech (Company 20)
        jobPostings.add(new JobPosting("Travel App Developer", c.get(20), 5000, 8000, "Develop travel booking apps", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Marketing Specialist", c.get(20), 4500, 7000, "Promote travel tech solutions", JobPosting.Type.MARKETING_COMM, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Customer Support", c.get(20), 3500, 5000, "Assist travel app users", JobPosting.Type.CALL_CUST_SVC, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Data Analyst", c.get(20), 4500, 7000, "Analyze travel trends", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Product Manager", c.get(20), 6000, 9000, "Manage travel tech products", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // SecureNet (Company 21)
        jobPostings.add(new JobPosting("Network Engineer", c.get(21), 6000, 9000, "Design secure network systems", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Security Analyst", c.get(21), 5500, 8500, "Monitor network security", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("IT Consultant", c.get(21), 6000, 9000, "Advise on network solutions", JobPosting.Type.CONSULTING, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("System Administrator", c.get(21), 5000, 7500, "Manage IT infrastructure", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Compliance Officer", c.get(21), 5500, 8500, "Ensure network compliance", JobPosting.Type.LEGAL, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // EduPlatform (Company 22)
        jobPostings.add(new JobPosting("E-Learning Developer", c.get(22), 5000, 8000, "Develop online learning platforms", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Content Specialist", c.get(22), 4000, 6000, "Create educational content", JobPosting.Type.EDUC_TRAINING, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Instructional Designer", c.get(22), 4500, 7000, "Design e-learning courses", JobPosting.Type.EDUC_TRAINING, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Tech Support", c.get(22), 3500, 5000, "Support learning platforms", JobPosting.Type.CALL_CUST_SVC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Project Manager", c.get(22), 6000, 9000, "Manage e-learning projects", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // HealthTech (Company 23)
        jobPostings.add(new JobPosting("Health App Developer", c.get(23), 5000, 8000, "Develop healthcare apps", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Clinical Data Analyst", c.get(23), 4500, 7000, "Analyze health data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Health Consultant", c.get(23), 5500, 8500, "Provide health tech advice", JobPosting.Type.HEALTH_MED, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("QA Engineer", c.get(23), 4000, 6000, "Test healthcare systems", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Operations Manager", c.get(23), 6000, 9000, "Manage health tech operations", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // LogiSys (Company 24)
        jobPostings.add(new JobPosting("Logistics Software Engineer", c.get(24), 5000, 8000, "Develop logistics software", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Supply Chain Analyst", c.get(24), 4500, 7000, "Analyze supply chain data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Logistics Manager", c.get(24), 5500, 8500, "Manage logistics operations", JobPosting.Type.MANF_LOG, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Customer Support", c.get(24), 3500, 5000, "Support logistics clients", JobPosting.Type.CALL_CUST_SVC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Project Coordinator", c.get(24), 5000, 7500, "Coordinate logistics projects", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // FinTech (Company 25)
        jobPostings.add(new JobPosting("FinTech Developer", c.get(25), 6000, 9000, "Develop financial software", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Financial Analyst", c.get(25), 5000, 8000, "Analyze financial trends", JobPosting.Type.BANK_FIN_SERV, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Compliance Specialist", c.get(25), 5500, 8500, "Ensure financial compliance", JobPosting.Type.LEGAL, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Data Scientist", c.get(25), 6000, 9000, "Analyze financial data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Product Manager", c.get(25), 6500, 9500, "Manage fintech products", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // GameStudio (Company 26)
        jobPostings.add(new JobPosting("Game Developer", c.get(26), 5000, 8000, "Develop interactive games", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("3D Artist", c.get(26), 4500, 7000, "Create game visuals", JobPosting.Type.DESIGN_ARCH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("QA Tester", c.get(26), 4000, 6000, "Test game functionality", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Game Designer", c.get(26), 5000, 7500, "Design game mechanics", JobPosting.Type.DESIGN_ARCH, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Marketing Specialist", c.get(26), 4500, 7000, "Promote games", JobPosting.Type.MARKETING_COMM, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // SmartHome (Company 27)
        jobPostings.add(new JobPosting("IoT Engineer", c.get(27), 6000, 9000, "Develop smart home devices", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Software Developer", c.get(27), 5000, 8000, "Code smart home software", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("UX Designer", c.get(27), 4500, 7000, "Design smart home interfaces", JobPosting.Type.DESIGN_ARCH, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Customer Support", c.get(27), 3500, 5000, "Support smart home users", JobPosting.Type.CALL_CUST_SVC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Product Manager", c.get(27), 6000, 9000, "Manage smart home products", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // EcoFarm (Company 28)
        jobPostings.add(new JobPosting("AgriTech Engineer", c.get(28), 5000, 8000, "Develop farming technology", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Data Analyst", c.get(28), 4500, 7000, "Analyze farm data", JobPosting.Type.SCIENCE_TECH, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Farm Manager", c.get(28), 4000, 6000, "Manage farm operations", JobPosting.Type.AGRICULTURE, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("Sustainability Specialist", c.get(28), 4500, 7000, "Promote sustainable farming", JobPosting.Type.AGRICULTURE, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Tech Support", c.get(28), 4000, 6000, "Support farm tech systems", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(4), md(4)));

        // RoboTech (Company 29)
        jobPostings.add(new JobPosting("Robotics Engineer", c.get(29), 6000, 9000, "Design robotic systems", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(5), md(5)));
        jobPostings.add(new JobPosting("Automation Specialist", c.get(29), 5500, 8500, "Develop automation solutions", JobPosting.Type.ENGINEERING, s(), re(), rw(), rl(), ra(), md(3), md(3)));
        jobPostings.add(new JobPosting("Software Engineer", c.get(29), 5000, 8000, "Code robotics software", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(7), md(7)));
        jobPostings.add(new JobPosting("QA Engineer", c.get(29), 4500, 7000, "Test robotic systems", JobPosting.Type.IT_COMM_TEC, s(), re(), rw(), rl(), ra(), md(2), md(2)));
        jobPostings.add(new JobPosting("Project Manager", c.get(29), 6000, 9000, "Manage robotics projects", JobPosting.Type.ADMIN_OFFICE, s(), re(), rw(), rl(), ra(), md(4), md(4)));

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
        applicants.add(new Applicant("Test", "test@test.com", "0119999999", t(), r(), re(), rw(), rl(), rs()));
        // endregion

        // region Init
        //251 job applications
        // Applicant 0 (Alice Johnson)
        jobApplications.add(new JobApplication(jobPostings.get(12), applicants.get(0), JobApplication.Status.PENDING, md(7).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(21), applicants.get(0), JobApplication.Status.SHORTLISTED, md(6).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(2), applicants.get(0), JobApplication.Status.INTERVIEWED, md(5).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(3), applicants.get(0), JobApplication.Status.OFFERED, md(4).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(4), applicants.get(0), JobApplication.Status.ACCEPTED, md(3).minusHours(6)));

        // Applicant 1 (Bob Smith)
        jobApplications.add(new JobApplication(jobPostings.get(1), applicants.get(1), JobApplication.Status.PENDING, md(2).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(6), applicants.get(1), JobApplication.Status.SHORTLISTED, md(1).minusHours(8)));
        jobApplications.add(new JobApplication(jobPostings.get(11), applicants.get(1), JobApplication.Status.REJECTED, md(0).minusHours(9)));
        jobApplications.add(new JobApplication(jobPostings.get(16), applicants.get(1), JobApplication.Status.WITHDRAWN, md(6).minusHours(10)));
        jobApplications.add(new JobApplication(jobPostings.get(21), applicants.get(1), JobApplication.Status.PENDING, md(5).minusHours(11)));

        // Applicant 2 (Charlie Brown)
        jobApplications.add(new JobApplication(jobPostings.get(2), applicants.get(2), JobApplication.Status.OFFERED, md(4).minusHours(12)));
        jobApplications.add(new JobApplication(jobPostings.get(7), applicants.get(2), JobApplication.Status.ACCEPTED, md(3).minusHours(13)));
        jobApplications.add(new JobApplication(jobPostings.get(12), applicants.get(2), JobApplication.Status.INTERVIEWED, md(2).minusHours(14)));
        jobApplications.add(new JobApplication(jobPostings.get(17), applicants.get(2), JobApplication.Status.PENDING, md(1).minusHours(15)));
        jobApplications.add(new JobApplication(jobPostings.get(22), applicants.get(2), JobApplication.Status.SHORTLISTED, md(0).minusHours(16)));

        // Applicant 3 (Daisy Williams)
        jobApplications.add(new JobApplication(jobPostings.get(3), applicants.get(3), JobApplication.Status.REJECTED, md(7).minusHours(17)));
        jobApplications.add(new JobApplication(jobPostings.get(8), applicants.get(3), JobApplication.Status.WITHDRAWN, md(6).minusHours(18)));
        jobApplications.add(new JobApplication(jobPostings.get(13), applicants.get(3), JobApplication.Status.OFFERED, md(5).minusHours(19)));
        jobApplications.add(new JobApplication(jobPostings.get(18), applicants.get(3), JobApplication.Status.ACCEPTED, md(4).minusHours(20)));
        jobApplications.add(new JobApplication(jobPostings.get(23), applicants.get(3), JobApplication.Status.INTERVIEWED, md(3).minusHours(21)));

        // Applicant 4 (Ethan Lee)
        jobApplications.add(new JobApplication(jobPostings.get(4), applicants.get(4), JobApplication.Status.PENDING, md(2).minusHours(22)));
        jobApplications.add(new JobApplication(jobPostings.get(9), applicants.get(4), JobApplication.Status.SHORTLISTED, md(1).minusHours(23)));
        jobApplications.add(new JobApplication(jobPostings.get(14), applicants.get(4), JobApplication.Status.REJECTED, md(0).minusHours(24)));
        jobApplications.add(new JobApplication(jobPostings.get(19), applicants.get(4), JobApplication.Status.WITHDRAWN, md(7).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(24), applicants.get(4), JobApplication.Status.OFFERED, md(6).minusHours(2)));

        // Applicant 5 (Fiona Davis)
        jobApplications.add(new JobApplication(jobPostings.get(5), applicants.get(5), JobApplication.Status.ACCEPTED, md(5).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(10), applicants.get(5), JobApplication.Status.INTERVIEWED, md(4).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(5), JobApplication.Status.PENDING, md(3).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(5), JobApplication.Status.SHORTLISTED, md(2).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(5), JobApplication.Status.REJECTED, md(1).minusHours(7)));

        // Applicant 6 (George Wilson)
        jobApplications.add(new JobApplication(jobPostings.get(6), applicants.get(6), JobApplication.Status.WITHDRAWN, md(0).minusHours(8)));
        jobApplications.add(new JobApplication(jobPostings.get(11), applicants.get(6), JobApplication.Status.OFFERED, md(7).minusHours(9)));
        jobApplications.add(new JobApplication(jobPostings.get(16), applicants.get(6), JobApplication.Status.ACCEPTED, md(6).minusHours(10)));
        jobApplications.add(new JobApplication(jobPostings.get(21), applicants.get(6), JobApplication.Status.INTERVIEWED, md(5).minusHours(11)));
        jobApplications.add(new JobApplication(jobPostings.get(26), applicants.get(6), JobApplication.Status.PENDING, md(4).minusHours(12)));

        // Applicant 7 (Hannah Moore)
        jobApplications.add(new JobApplication(jobPostings.get(7), applicants.get(7), JobApplication.Status.SHORTLISTED, md(3).minusHours(13)));
        jobApplications.add(new JobApplication(jobPostings.get(12), applicants.get(7), JobApplication.Status.REJECTED, md(2).minusHours(14)));
        jobApplications.add(new JobApplication(jobPostings.get(17), applicants.get(7), JobApplication.Status.WITHDRAWN, md(1).minusHours(15)));
        jobApplications.add(new JobApplication(jobPostings.get(22), applicants.get(7), JobApplication.Status.OFFERED, md(0).minusHours(16)));
        jobApplications.add(new JobApplication(jobPostings.get(27), applicants.get(7), JobApplication.Status.ACCEPTED, md(7).minusHours(17)));

        // Applicant 8 (Ian Taylor)
        jobApplications.add(new JobApplication(jobPostings.get(8), applicants.get(8), JobApplication.Status.INTERVIEWED, md(6).minusHours(18)));
        jobApplications.add(new JobApplication(jobPostings.get(13), applicants.get(8), JobApplication.Status.PENDING, md(5).minusHours(19)));
        jobApplications.add(new JobApplication(jobPostings.get(18), applicants.get(8), JobApplication.Status.SHORTLISTED, md(4).minusHours(20)));
        jobApplications.add(new JobApplication(jobPostings.get(23), applicants.get(8), JobApplication.Status.REJECTED, md(3).minusHours(21)));
        jobApplications.add(new JobApplication(jobPostings.get(28), applicants.get(8), JobApplication.Status.WITHDRAWN, md(2).minusHours(22)));

        // Applicant 9 (Julia Anderson)
        jobApplications.add(new JobApplication(jobPostings.get(9), applicants.get(9), JobApplication.Status.OFFERED, md(1).minusHours(23)));
        jobApplications.add(new JobApplication(jobPostings.get(14), applicants.get(9), JobApplication.Status.ACCEPTED, md(0).minusHours(24)));
        jobApplications.add(new JobApplication(jobPostings.get(19), applicants.get(9), JobApplication.Status.INTERVIEWED, md(7).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(24), applicants.get(9), JobApplication.Status.PENDING, md(6).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(29), applicants.get(9), JobApplication.Status.SHORTLISTED, md(5).minusHours(3)));

        // Applicant 10 (Kevin White)
        jobApplications.add(new JobApplication(jobPostings.get(10), applicants.get(10), JobApplication.Status.REJECTED, md(4).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(10), JobApplication.Status.WITHDRAWN, md(3).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(10), JobApplication.Status.OFFERED, md(2).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(10), JobApplication.Status.ACCEPTED, md(1).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(10), JobApplication.Status.INTERVIEWED, md(0).minusHours(8)));

        // Applicant 11 (Laura Martinez)
        jobApplications.add(new JobApplication(jobPostings.get(11), applicants.get(11), JobApplication.Status.PENDING, md(7).minusHours(9)));
        jobApplications.add(new JobApplication(jobPostings.get(16), applicants.get(11), JobApplication.Status.SHORTLISTED, md(6).minusHours(10)));
        jobApplications.add(new JobApplication(jobPostings.get(21), applicants.get(11), JobApplication.Status.REJECTED, md(5).minusHours(11)));
        jobApplications.add(new JobApplication(jobPostings.get(26), applicants.get(11), JobApplication.Status.WITHDRAWN, md(4).minusHours(12)));
        jobApplications.add(new JobApplication(jobPostings.get(31), applicants.get(11), JobApplication.Status.OFFERED, md(3).minusHours(13)));

        // Applicant 12 (Mike Brown)
        jobApplications.add(new JobApplication(jobPostings.get(12), applicants.get(12), JobApplication.Status.ACCEPTED, md(2).minusHours(14)));
        jobApplications.add(new JobApplication(jobPostings.get(17), applicants.get(12), JobApplication.Status.INTERVIEWED, md(1).minusHours(15)));
        jobApplications.add(new JobApplication(jobPostings.get(22), applicants.get(12), JobApplication.Status.PENDING, md(0).minusHours(16)));
        jobApplications.add(new JobApplication(jobPostings.get(27), applicants.get(12), JobApplication.Status.SHORTLISTED, md(7).minusHours(17)));
        jobApplications.add(new JobApplication(jobPostings.get(32), applicants.get(12), JobApplication.Status.REJECTED, md(6).minusHours(18)));

        // Applicant 13 (Nina Harris)
        jobApplications.add(new JobApplication(jobPostings.get(13), applicants.get(13), JobApplication.Status.WITHDRAWN, md(5).minusHours(19)));
        jobApplications.add(new JobApplication(jobPostings.get(18), applicants.get(13), JobApplication.Status.OFFERED, md(4).minusHours(20)));
        jobApplications.add(new JobApplication(jobPostings.get(23), applicants.get(13), JobApplication.Status.ACCEPTED, md(3).minusHours(21)));
        jobApplications.add(new JobApplication(jobPostings.get(28), applicants.get(13), JobApplication.Status.INTERVIEWED, md(2).minusHours(22)));
        jobApplications.add(new JobApplication(jobPostings.get(33), applicants.get(13), JobApplication.Status.PENDING, md(1).minusHours(23)));

        // Applicant 14 (Oliver Clark)
        jobApplications.add(new JobApplication(jobPostings.get(14), applicants.get(14), JobApplication.Status.SHORTLISTED, md(0).minusHours(24)));
        jobApplications.add(new JobApplication(jobPostings.get(19), applicants.get(14), JobApplication.Status.REJECTED, md(7).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(24), applicants.get(14), JobApplication.Status.WITHDRAWN, md(6).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(29), applicants.get(14), JobApplication.Status.OFFERED, md(5).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(34), applicants.get(14), JobApplication.Status.ACCEPTED, md(4).minusHours(4)));

        // Applicant 15 (Pamela Lewis)
        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(15), JobApplication.Status.INTERVIEWED, md(3).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(15), JobApplication.Status.PENDING, md(2).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(15), JobApplication.Status.SHORTLISTED, md(1).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(15), JobApplication.Status.REJECTED, md(0).minusHours(8)));
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(15), JobApplication.Status.WITHDRAWN, md(7).minusHours(9)));

        // Applicant 16 (Quincy Adams)
        jobApplications.add(new JobApplication(jobPostings.get(16), applicants.get(16), JobApplication.Status.OFFERED, md(6).minusHours(10)));
        jobApplications.add(new JobApplication(jobPostings.get(21), applicants.get(16), JobApplication.Status.ACCEPTED, md(5).minusHours(11)));
        jobApplications.add(new JobApplication(jobPostings.get(26), applicants.get(16), JobApplication.Status.INTERVIEWED, md(4).minusHours(12)));
        jobApplications.add(new JobApplication(jobPostings.get(31), applicants.get(16), JobApplication.Status.PENDING, md(3).minusHours(13)));
        jobApplications.add(new JobApplication(jobPostings.get(36), applicants.get(16), JobApplication.Status.SHORTLISTED, md(2).minusHours(14)));

        // Applicant 17 (Rachel Green)
        jobApplications.add(new JobApplication(jobPostings.get(17), applicants.get(17), JobApplication.Status.REJECTED, md(1).minusHours(15)));
        jobApplications.add(new JobApplication(jobPostings.get(22), applicants.get(17), JobApplication.Status.WITHDRAWN, md(0).minusHours(16)));
        jobApplications.add(new JobApplication(jobPostings.get(27), applicants.get(17), JobApplication.Status.OFFERED, md(7).minusHours(17)));
        jobApplications.add(new JobApplication(jobPostings.get(32), applicants.get(17), JobApplication.Status.ACCEPTED, md(6).minusHours(18)));
        jobApplications.add(new JobApplication(jobPostings.get(37), applicants.get(17), JobApplication.Status.INTERVIEWED, md(5).minusHours(19)));

        // Applicant 18 (Sam Evans)
        jobApplications.add(new JobApplication(jobPostings.get(18), applicants.get(18), JobApplication.Status.PENDING, md(4).minusHours(20)));
        jobApplications.add(new JobApplication(jobPostings.get(23), applicants.get(18), JobApplication.Status.SHORTLISTED, md(3).minusHours(21)));
        jobApplications.add(new JobApplication(jobPostings.get(28), applicants.get(18), JobApplication.Status.REJECTED, md(2).minusHours(22)));
        jobApplications.add(new JobApplication(jobPostings.get(33), applicants.get(18), JobApplication.Status.WITHDRAWN, md(1).minusHours(23)));
        jobApplications.add(new JobApplication(jobPostings.get(38), applicants.get(18), JobApplication.Status.OFFERED, md(0).minusHours(24)));

        // Applicant 19 (Tina Walker)
        jobApplications.add(new JobApplication(jobPostings.get(19), applicants.get(19), JobApplication.Status.ACCEPTED, md(7).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(19), applicants.get(19), JobApplication.Status.ACCEPTED, md(7).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(29), applicants.get(19), JobApplication.Status.PENDING, md(5).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(34), applicants.get(19), JobApplication.Status.SHORTLISTED, md(4).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(39), applicants.get(19), JobApplication.Status.REJECTED, md(3).minusHours(5)));

        // Applicant 20 (Umar Khan)
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(20), JobApplication.Status.WITHDRAWN, md(2).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(20), JobApplication.Status.OFFERED, md(1).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(20), JobApplication.Status.ACCEPTED, md(0).minusHours(8)));
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(20), JobApplication.Status.INTERVIEWED, md(7).minusHours(9)));
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(20), JobApplication.Status.PENDING, md(6).minusHours(10)));

        // Applicant 21 (Vera Scott)
        jobApplications.add(new JobApplication(jobPostings.get(21), applicants.get(21), JobApplication.Status.SHORTLISTED, md(5).minusHours(11)));
        jobApplications.add(new JobApplication(jobPostings.get(26), applicants.get(21), JobApplication.Status.REJECTED, md(4).minusHours(12)));
        jobApplications.add(new JobApplication(jobPostings.get(31), applicants.get(21), JobApplication.Status.WITHDRAWN, md(3).minusHours(13)));
        jobApplications.add(new JobApplication(jobPostings.get(36), applicants.get(21), JobApplication.Status.OFFERED, md(2).minusHours(14)));
        jobApplications.add(new JobApplication(jobPostings.get(41), applicants.get(21), JobApplication.Status.ACCEPTED, md(1).minusHours(15)));

        // Applicant 22 (Will Turner)
        jobApplications.add(new JobApplication(jobPostings.get(22), applicants.get(22), JobApplication.Status.INTERVIEWED, md(0).minusHours(16)));
        jobApplications.add(new JobApplication(jobPostings.get(27), applicants.get(22), JobApplication.Status.PENDING, md(7).minusHours(17)));
        jobApplications.add(new JobApplication(jobPostings.get(32), applicants.get(22), JobApplication.Status.SHORTLISTED, md(6).minusHours(18)));
        jobApplications.add(new JobApplication(jobPostings.get(37), applicants.get(22), JobApplication.Status.REJECTED, md(5).minusHours(19)));
        jobApplications.add(new JobApplication(jobPostings.get(42), applicants.get(22), JobApplication.Status.WITHDRAWN, md(4).minusHours(20)));

        // Applicant 23 (Xena Brooks)
        jobApplications.add(new JobApplication(jobPostings.get(23), applicants.get(23), JobApplication.Status.OFFERED, md(3).minusHours(21)));
        jobApplications.add(new JobApplication(jobPostings.get(28), applicants.get(23), JobApplication.Status.ACCEPTED, md(2).minusHours(22)));
        jobApplications.add(new JobApplication(jobPostings.get(33), applicants.get(23), JobApplication.Status.INTERVIEWED, md(1).minusHours(23)));
        jobApplications.add(new JobApplication(jobPostings.get(38), applicants.get(23), JobApplication.Status.PENDING, md(0).minusHours(24)));
        jobApplications.add(new JobApplication(jobPostings.get(43), applicants.get(23), JobApplication.Status.SHORTLISTED, md(7).minusHours(1)));

        // Applicant 24 (Yara King)
        jobApplications.add(new JobApplication(jobPostings.get(24), applicants.get(24), JobApplication.Status.REJECTED, md(6).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(29), applicants.get(24), JobApplication.Status.WITHDRAWN, md(5).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(34), applicants.get(24), JobApplication.Status.OFFERED, md(4).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(39), applicants.get(24), JobApplication.Status.ACCEPTED, md(3).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(44), applicants.get(24), JobApplication.Status.INTERVIEWED, md(2).minusHours(6)));

        // Applicant 25 (Zack Miller)
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(25), JobApplication.Status.PENDING, md(1).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(25), JobApplication.Status.SHORTLISTED, md(0).minusHours(8)));
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(25), JobApplication.Status.REJECTED, md(7).minusHours(9)));
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(25), JobApplication.Status.WITHDRAWN, md(6).minusHours(10)));
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(25), JobApplication.Status.OFFERED, md(5).minusHours(11)));

        // Applicant 26 (Amy Carter)
        jobApplications.add(new JobApplication(jobPostings.get(26), applicants.get(26), JobApplication.Status.ACCEPTED, md(7).minusHours(12)));
        jobApplications.add(new JobApplication(jobPostings.get(31), applicants.get(26), JobApplication.Status.INTERVIEWED, md(6).minusHours(13)));
        jobApplications.add(new JobApplication(jobPostings.get(36), applicants.get(26), JobApplication.Status.PENDING, md(5).minusHours(14)));
        jobApplications.add(new JobApplication(jobPostings.get(41), applicants.get(26), JobApplication.Status.SHORTLISTED, md(4).minusHours(15)));
        jobApplications.add(new JobApplication(jobPostings.get(46), applicants.get(26), JobApplication.Status.REJECTED, md(3).minusHours(16)));

        // Applicant 27 (Ben Foster)
        jobApplications.add(new JobApplication(jobPostings.get(27), applicants.get(27), JobApplication.Status.WITHDRAWN, md(2).minusHours(17)));
        jobApplications.add(new JobApplication(jobPostings.get(32), applicants.get(27), JobApplication.Status.OFFERED, md(1).minusHours(18)));
        jobApplications.add(new JobApplication(jobPostings.get(37), applicants.get(27), JobApplication.Status.ACCEPTED, md(0).minusHours(19)));
        jobApplications.add(new JobApplication(jobPostings.get(42), applicants.get(27), JobApplication.Status.INTERVIEWED, md(7).minusHours(20)));
        jobApplications.add(new JobApplication(jobPostings.get(47), applicants.get(27), JobApplication.Status.PENDING, md(6).minusHours(21)));

        // Applicant 28 (Clara Hayes)
        jobApplications.add(new JobApplication(jobPostings.get(28), applicants.get(28), JobApplication.Status.SHORTLISTED, md(5).minusHours(22)));
        jobApplications.add(new JobApplication(jobPostings.get(33), applicants.get(28), JobApplication.Status.REJECTED, md(4).minusHours(23)));
        jobApplications.add(new JobApplication(jobPostings.get(38), applicants.get(28), JobApplication.Status.WITHDRAWN, md(3).minusHours(24)));
        jobApplications.add(new JobApplication(jobPostings.get(43), applicants.get(28), JobApplication.Status.OFFERED, md(2).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(48), applicants.get(28), JobApplication.Status.ACCEPTED, md(1).minusHours(2)));

        // Applicant 29 (Dan Price)
        jobApplications.add(new JobApplication(jobPostings.get(29), applicants.get(29), JobApplication.Status.INTERVIEWED, md(0).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(34), applicants.get(29), JobApplication.Status.PENDING, md(7).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(39), applicants.get(29), JobApplication.Status.SHORTLISTED, md(6).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(44), applicants.get(29), JobApplication.Status.REJECTED, md(5).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(49), applicants.get(29), JobApplication.Status.WITHDRAWN, md(4).minusHours(7)));

        // Applicant 30 (Ella Reed)
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(30), JobApplication.Status.OFFERED, md(3).minusHours(8)));
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(30), JobApplication.Status.ACCEPTED, md(2).minusHours(9)));
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(30), JobApplication.Status.INTERVIEWED, md(1).minusHours(10)));
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(30), JobApplication.Status.PENDING, md(0).minusHours(11)));
        jobApplications.add(new JobApplication(jobPostings.get(50), applicants.get(30), JobApplication.Status.SHORTLISTED, md(7).minusHours(12)));

        // Applicant 31 (Finn Cole)
        jobApplications.add(new JobApplication(jobPostings.get(31), applicants.get(31), JobApplication.Status.REJECTED, md(6).minusHours(13)));
        jobApplications.add(new JobApplication(jobPostings.get(36), applicants.get(31), JobApplication.Status.WITHDRAWN, md(5).minusHours(14)));
        jobApplications.add(new JobApplication(jobPostings.get(41), applicants.get(31), JobApplication.Status.OFFERED, md(4).minusHours(15)));
        jobApplications.add(new JobApplication(jobPostings.get(46), applicants.get(31), JobApplication.Status.ACCEPTED, md(3).minusHours(16)));
        jobApplications.add(new JobApplication(jobPostings.get(51), applicants.get(31), JobApplication.Status.INTERVIEWED, md(2).minusHours(17)));

        // Applicant 32 (Gina Bell)
        jobApplications.add(new JobApplication(jobPostings.get(32), applicants.get(32), JobApplication.Status.PENDING, md(1).minusHours(18)));
        jobApplications.add(new JobApplication(jobPostings.get(37), applicants.get(32), JobApplication.Status.SHORTLISTED, md(0).minusHours(19)));
        jobApplications.add(new JobApplication(jobPostings.get(42), applicants.get(32), JobApplication.Status.REJECTED, md(7).minusHours(20)));
        jobApplications.add(new JobApplication(jobPostings.get(47), applicants.get(32), JobApplication.Status.WITHDRAWN, md(6).minusHours(21)));
        jobApplications.add(new JobApplication(jobPostings.get(52), applicants.get(32), JobApplication.Status.OFFERED, md(5).minusHours(22)));

        // Applicant 33 (Hank Ford)
        jobApplications.add(new JobApplication(jobPostings.get(33), applicants.get(33), JobApplication.Status.ACCEPTED, md(4).minusHours(23)));
        jobApplications.add(new JobApplication(jobPostings.get(38), applicants.get(33), JobApplication.Status.INTERVIEWED, md(3).minusHours(24)));
        jobApplications.add(new JobApplication(jobPostings.get(43), applicants.get(33), JobApplication.Status.PENDING, md(2).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(48), applicants.get(33), JobApplication.Status.SHORTLISTED, md(1).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(53), applicants.get(33), JobApplication.Status.REJECTED, md(0).minusHours(3)));

        // Applicant 34 (Iris Lane)
        jobApplications.add(new JobApplication(jobPostings.get(34), applicants.get(34), JobApplication.Status.WITHDRAWN, md(7).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(39), applicants.get(34), JobApplication.Status.OFFERED, md(6).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(44), applicants.get(34), JobApplication.Status.ACCEPTED, md(5).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(49), applicants.get(34), JobApplication.Status.INTERVIEWED, md(4).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(54), applicants.get(34), JobApplication.Status.PENDING, md(3).minusHours(8)));

        // Applicant 35 (Jack Gray)
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(35), JobApplication.Status.SHORTLISTED, md(2).minusHours(9)));
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(35), JobApplication.Status.REJECTED, md(1).minusHours(10)));
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(35), JobApplication.Status.WITHDRAWN, md(0).minusHours(11)));
        jobApplications.add(new JobApplication(jobPostings.get(50), applicants.get(35), JobApplication.Status.OFFERED, md(7).minusHours(12)));
        jobApplications.add(new JobApplication(jobPostings.get(55), applicants.get(35), JobApplication.Status.ACCEPTED, md(6).minusHours(13)));

        // Applicant 36 (Kara Hill)
        jobApplications.add(new JobApplication(jobPostings.get(36), applicants.get(36), JobApplication.Status.INTERVIEWED, md(5).minusHours(14)));
        jobApplications.add(new JobApplication(jobPostings.get(41), applicants.get(36), JobApplication.Status.PENDING, md(4).minusHours(15)));
        jobApplications.add(new JobApplication(jobPostings.get(46), applicants.get(36), JobApplication.Status.SHORTLISTED, md(3).minusHours(16)));
        jobApplications.add(new JobApplication(jobPostings.get(51), applicants.get(36), JobApplication.Status.REJECTED, md(2).minusHours(17)));
        jobApplications.add(new JobApplication(jobPostings.get(56), applicants.get(36), JobApplication.Status.WITHDRAWN, md(1).minusHours(18)));

        // Applicant 37 (Liam Ward)
        jobApplications.add(new JobApplication(jobPostings.get(37), applicants.get(37), JobApplication.Status.OFFERED, md(0).minusHours(19)));
        jobApplications.add(new JobApplication(jobPostings.get(42), applicants.get(37), JobApplication.Status.ACCEPTED, md(7).minusHours(20)));
        jobApplications.add(new JobApplication(jobPostings.get(47), applicants.get(37), JobApplication.Status.INTERVIEWED, md(6).minusHours(21)));
        jobApplications.add(new JobApplication(jobPostings.get(52), applicants.get(37), JobApplication.Status.PENDING, md(5).minusHours(22)));
        jobApplications.add(new JobApplication(jobPostings.get(57), applicants.get(37), JobApplication.Status.SHORTLISTED, md(4).minusHours(23)));

        // Applicant 38 (Maya Ross)
        jobApplications.add(new JobApplication(jobPostings.get(38), applicants.get(38), JobApplication.Status.REJECTED, md(3).minusHours(24)));
        jobApplications.add(new JobApplication(jobPostings.get(43), applicants.get(38), JobApplication.Status.WITHDRAWN, md(2).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(48), applicants.get(38), JobApplication.Status.OFFERED, md(1).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(53), applicants.get(38), JobApplication.Status.ACCEPTED, md(0).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(58), applicants.get(38), JobApplication.Status.INTERVIEWED, md(7).minusHours(4)));

        // Applicant 39 (Nate Fox)
        jobApplications.add(new JobApplication(jobPostings.get(39), applicants.get(39), JobApplication.Status.PENDING, md(6).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(44), applicants.get(39), JobApplication.Status.SHORTLISTED, md(5).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(49), applicants.get(39), JobApplication.Status.REJECTED, md(4).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(54), applicants.get(39), JobApplication.Status.WITHDRAWN, md(3).minusHours(8)));
        jobApplications.add(new JobApplication(jobPostings.get(59), applicants.get(39), JobApplication.Status.OFFERED, md(2).minusHours(9)));

        // Applicant 40 (Opal Dean)
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(40), JobApplication.Status.ACCEPTED, md(1).minusHours(10)));
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(40), JobApplication.Status.INTERVIEWED, md(0).minusHours(11)));
        jobApplications.add(new JobApplication(jobPostings.get(50), applicants.get(40), JobApplication.Status.PENDING, md(7).minusHours(12)));
        jobApplications.add(new JobApplication(jobPostings.get(55), applicants.get(40), JobApplication.Status.SHORTLISTED, md(6).minusHours(13)));
        jobApplications.add(new JobApplication(jobPostings.get(60), applicants.get(40), JobApplication.Status.REJECTED, md(5).minusHours(14)));

        // Applicant 41 (Paul Hart)
        jobApplications.add(new JobApplication(jobPostings.get(41), applicants.get(41), JobApplication.Status.WITHDRAWN, md(4).minusHours(15)));
        jobApplications.add(new JobApplication(jobPostings.get(46), applicants.get(41), JobApplication.Status.OFFERED, md(3).minusHours(16)));
        jobApplications.add(new JobApplication(jobPostings.get(51), applicants.get(41), JobApplication.Status.ACCEPTED, md(2).minusHours(17)));
        jobApplications.add(new JobApplication(jobPostings.get(56), applicants.get(41), JobApplication.Status.INTERVIEWED, md(1).minusHours(18)));
        jobApplications.add(new JobApplication(jobPostings.get(61), applicants.get(41), JobApplication.Status.PENDING, md(0).minusHours(19)));

        // Applicant 42 (Quinn Cole)
        jobApplications.add(new JobApplication(jobPostings.get(42), applicants.get(42), JobApplication.Status.SHORTLISTED, md(7).minusHours(20)));
        jobApplications.add(new JobApplication(jobPostings.get(47), applicants.get(42), JobApplication.Status.REJECTED, md(6).minusHours(21)));
        jobApplications.add(new JobApplication(jobPostings.get(52), applicants.get(42), JobApplication.Status.WITHDRAWN, md(5).minusHours(22)));
        jobApplications.add(new JobApplication(jobPostings.get(57), applicants.get(42), JobApplication.Status.OFFERED, md(4).minusHours(23)));
        jobApplications.add(new JobApplication(jobPostings.get(62), applicants.get(42), JobApplication.Status.ACCEPTED, md(3).minusHours(24)));

        // Applicant 43 (Rita Lane)
        jobApplications.add(new JobApplication(jobPostings.get(43), applicants.get(43), JobApplication.Status.INTERVIEWED, md(2).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(48), applicants.get(43), JobApplication.Status.PENDING, md(1).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(53), applicants.get(43), JobApplication.Status.SHORTLISTED, md(0).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(58), applicants.get(43), JobApplication.Status.REJECTED, md(7).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(63), applicants.get(43), JobApplication.Status.WITHDRAWN, md(6).minusHours(5)));

        // Applicant 44 (Seth Owen)
        jobApplications.add(new JobApplication(jobPostings.get(44), applicants.get(44), JobApplication.Status.OFFERED, md(5).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(49), applicants.get(44), JobApplication.Status.ACCEPTED, md(4).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(54), applicants.get(44), JobApplication.Status.INTERVIEWED, md(3).minusHours(8)));
        jobApplications.add(new JobApplication(jobPostings.get(59), applicants.get(44), JobApplication.Status.PENDING, md(2).minusHours(9)));
        jobApplications.add(new JobApplication(jobPostings.get(64), applicants.get(44), JobApplication.Status.SHORTLISTED, md(1).minusHours(10)));

        // Applicant 45 (Tara Bell)
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(45), JobApplication.Status.REJECTED, md(0).minusHours(11)));
        jobApplications.add(new JobApplication(jobPostings.get(50), applicants.get(45), JobApplication.Status.WITHDRAWN, md(7).minusHours(12)));
        jobApplications.add(new JobApplication(jobPostings.get(55), applicants.get(45), JobApplication.Status.OFFERED, md(6).minusHours(13)));
        jobApplications.add(new JobApplication(jobPostings.get(60), applicants.get(45), JobApplication.Status.ACCEPTED, md(5).minusHours(14)));
        jobApplications.add(new JobApplication(jobPostings.get(65), applicants.get(45), JobApplication.Status.INTERVIEWED, md(4).minusHours(15)));

        // Applicant 46 (Uma West)
        jobApplications.add(new JobApplication(jobPostings.get(46), applicants.get(46), JobApplication.Status.PENDING, md(3).minusHours(16)));
        jobApplications.add(new JobApplication(jobPostings.get(51), applicants.get(46), JobApplication.Status.SHORTLISTED, md(2).minusHours(17)));
        jobApplications.add(new JobApplication(jobPostings.get(56), applicants.get(46), JobApplication.Status.REJECTED, md(1).minusHours(18)));
        jobApplications.add(new JobApplication(jobPostings.get(61), applicants.get(46), JobApplication.Status.WITHDRAWN, md(0).minusHours(19)));
        jobApplications.add(new JobApplication(jobPostings.get(66), applicants.get(46), JobApplication.Status.OFFERED, md(7).minusHours(20)));

        // Applicant 47 (Vince Ray)
        jobApplications.add(new JobApplication(jobPostings.get(47), applicants.get(47), JobApplication.Status.ACCEPTED, md(6).minusHours(21)));
        jobApplications.add(new JobApplication(jobPostings.get(52), applicants.get(47), JobApplication.Status.INTERVIEWED, md(5).minusHours(22)));
        jobApplications.add(new JobApplication(jobPostings.get(57), applicants.get(47), JobApplication.Status.PENDING, md(4).minusHours(23)));
        jobApplications.add(new JobApplication(jobPostings.get(62), applicants.get(47), JobApplication.Status.SHORTLISTED, md(3).minusHours(24)));
        jobApplications.add(new JobApplication(jobPostings.get(67), applicants.get(47), JobApplication.Status.REJECTED, md(2).minusHours(1)));

        // Applicant 48 (Wendy Fox)
        jobApplications.add(new JobApplication(jobPostings.get(48), applicants.get(48), JobApplication.Status.WITHDRAWN, md(1).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(53), applicants.get(48), JobApplication.Status.OFFERED, md(0).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(58), applicants.get(48), JobApplication.Status.ACCEPTED, md(7).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(63), applicants.get(48), JobApplication.Status.INTERVIEWED, md(6).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(68), applicants.get(48), JobApplication.Status.PENDING, md(5).minusHours(6)));

        // Applicant 49 (Xander Lee)
        jobApplications.add(new JobApplication(jobPostings.get(49), applicants.get(49), JobApplication.Status.SHORTLISTED, md(4).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(54), applicants.get(49), JobApplication.Status.REJECTED, md(3).minusHours(8)));
        jobApplications.add(new JobApplication(jobPostings.get(59), applicants.get(49), JobApplication.Status.WITHDRAWN, md(2).minusHours(9)));
        jobApplications.add(new JobApplication(jobPostings.get(64), applicants.get(49), JobApplication.Status.OFFERED, md(1).minusHours(10)));
        jobApplications.add(new JobApplication(jobPostings.get(69), applicants.get(49), JobApplication.Status.ACCEPTED, md(0).minusHours(11)));

        // Applicant 0
        jobApplications.add(new JobApplication(jobPostings.get(0), applicants.get(0), JobApplication.Status.SHORTLISTED, LocalDateTime.of(2025, 8, 6, 9, 0)));
        jobApplications.add(new JobApplication(jobPostings.get(5), applicants.get(0), JobApplication.Status.SHORTLISTED, LocalDateTime.of(2025, 8, 7, 10, 0)));
        jobApplications.add(new JobApplication(jobPostings.get(10), applicants.get(0), JobApplication.Status.SHORTLISTED, LocalDateTime.of(2025, 8, 8, 11, 0)));
        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(0), JobApplication.Status.SHORTLISTED, LocalDateTime.of(2025, 8, 9, 12, 0)));
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(0), JobApplication.Status.SHORTLISTED, LocalDateTime.of(2025, 8, 10, 13, 0)));

        // Applicant 1
        jobApplications.add(new JobApplication(jobPostings.get(0), applicants.get(1), JobApplication.Status.SHORTLISTED, md(7).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(5), applicants.get(1), JobApplication.Status.SHORTLISTED, md(6).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(10), applicants.get(1), JobApplication.Status.SHORTLISTED, md(5).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(1), JobApplication.Status.SHORTLISTED, md(4).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(1), JobApplication.Status.SHORTLISTED, md(3).minusHours(5)));

        // Applicant 2
        jobApplications.add(new JobApplication(jobPostings.get(0), applicants.get(2), JobApplication.Status.SHORTLISTED, md(2).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(5), applicants.get(2), JobApplication.Status.SHORTLISTED, md(1).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(10), applicants.get(2), JobApplication.Status.SHORTLISTED, md(0).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(2), JobApplication.Status.SHORTLISTED, md(3).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(2), JobApplication.Status.SHORTLISTED, md(2).minusHours(3)));

        // Applicant 3
        jobApplications.add(new JobApplication(jobPostings.get(0), applicants.get(3), JobApplication.Status.SHORTLISTED, md(6).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(5), applicants.get(3), JobApplication.Status.SHORTLISTED, md(5).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(10), applicants.get(3), JobApplication.Status.SHORTLISTED, md(4).minusHours(0)));
        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(3), JobApplication.Status.SHORTLISTED, md(3).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(3), JobApplication.Status.SHORTLISTED, md(2).minusHours(5)));

        // Applicant 4
        jobApplications.add(new JobApplication(jobPostings.get(0), applicants.get(4), JobApplication.Status.SHORTLISTED, md(1).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(5), applicants.get(4), JobApplication.Status.SHORTLISTED, md(0).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(10), applicants.get(4), JobApplication.Status.SHORTLISTED, md(7).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(4), JobApplication.Status.SHORTLISTED, md(6).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(4), JobApplication.Status.SHORTLISTED, md(5).minusHours(0)));

        // Applicant 5
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(5), JobApplication.Status.SHORTLISTED, md(4).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(5), JobApplication.Status.SHORTLISTED, md(3).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(5), JobApplication.Status.SHORTLISTED, md(2).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(5), JobApplication.Status.SHORTLISTED, md(1).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(5), JobApplication.Status.SHORTLISTED, md(0).minusHours(3)));

        // Applicant 6
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(6), JobApplication.Status.SHORTLISTED, md(7).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(6), JobApplication.Status.SHORTLISTED, md(6).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(6), JobApplication.Status.SHORTLISTED, md(5).minusHours(0)));
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(6), JobApplication.Status.SHORTLISTED, md(4).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(6), JobApplication.Status.SHORTLISTED, md(3).minusHours(5)));

        // Applicant 7
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(7), JobApplication.Status.SHORTLISTED, md(2).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(7), JobApplication.Status.SHORTLISTED, md(1).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(7), JobApplication.Status.SHORTLISTED, md(0).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(7), JobApplication.Status.SHORTLISTED, md(7).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(7), JobApplication.Status.SHORTLISTED, md(6).minusHours(0)));

        // Applicant 8
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(8), JobApplication.Status.SHORTLISTED, md(5).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(8), JobApplication.Status.SHORTLISTED, md(4).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(8), JobApplication.Status.SHORTLISTED, md(3).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(8), JobApplication.Status.SHORTLISTED, md(2).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(8), JobApplication.Status.SHORTLISTED, md(1).minusHours(3)));

        // Applicant 9
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(9), JobApplication.Status.SHORTLISTED, md(0).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(9), JobApplication.Status.SHORTLISTED, md(7).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(9), JobApplication.Status.SHORTLISTED, md(6).minusHours(0)));
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(9), JobApplication.Status.SHORTLISTED, md(5).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(9), JobApplication.Status.SHORTLISTED, md(4).minusHours(5)));
        // endregion

        // INTERVIEWED (34 records, must have past interviews, future optional)
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(2), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(2), md(3).minusHours(5)), 8, md(3).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(12), "Behavioral interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(12), md(2).minusHours(4)), 7, md(2).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(19), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(19), md(4).minusHours(6)), 9, md(4).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(26), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(26), md(1).minusHours(3)), 6, md(1).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(33), "Technical discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(33), md(5).minusHours(7)), 8, md(5).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(40), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(40), md(2).minusHours(2)), 7, md(2).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(47), "HR interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(47), md(3).minusHours(8)), 9, md(3).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(54), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(54), md(4).minusHours(1)), 6, md(4).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(61), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(61), md(1).minusHours(9)), 8, md(1).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(68), "Coding challenge", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(68), md(5).minusHours(4)), 7, md(5).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(75), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(75), md(2).minusHours(5)), 9, md(2).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(82), "Behavioral interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(82), md(3).minusHours(6)), 6, md(3).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(89), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(89), md(4).minusHours(7)), 8, md(4).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(103), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(103), md(1).minusHours(8)), 7, md(1).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(110), "HR interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(110), md(5).minusHours(9)), 9, md(5).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(117), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(117), md(2).minusHours(1)), 6, md(2).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(124), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(124), md(3).minusHours(2)), 8, md(3).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(131), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(131), md(4).minusHours(3)), 7, md(4).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(138), "Technical discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(138), md(1).minusHours(4)), 9, md(1).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(145), "Behavioral interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(145), md(5).minusHours(5)), 6, md(5).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(152), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(152), md(2).minusHours(6)), 8, md(2).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(159), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(159), md(3).minusHours(7)), 7, md(3).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(166), "HR interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(166), md(4).minusHours(8)), 9, md(4).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(173), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(173), md(1).minusHours(9)), 6, md(1).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(180), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(180), md(5).minusHours(1)), 8, md(5).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(187), "Coding challenge", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(187), md(2).minusHours(2)), 7, md(2).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(194), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(194), md(3).minusHours(3)), 9, md(3).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(201), "Behavioral interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(201), md(4).minusHours(4)), 6, md(4).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(208), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(208), md(1).minusHours(5)), 8, md(1).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(215), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(215), md(5).minusHours(6)), 7, md(5).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(222), "HR interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(222), md(2).minusHours(7)), 9, md(2).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(229), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(229), md(3).minusHours(8)), 6, md(3).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(236), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(236), md(4).minusHours(9)), 8, md(4).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(243), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(243), md(1).minusHours(1)), 7, md(1).minusHours(1)));

        // SHORTLISTED (84 records, no past interviews, future optional)
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(1), "Scheduled for technical", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(1), md(1).minusHours(2)), md(1).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(6), "Behavioral screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(6), md(2).minusHours(3)), md(2).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(14), "Coding interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(14), md(3).minusHours(4)), md(3).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(21), "Panel discussion", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(21), md(4).minusHours(5)), md(4).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(28), "Technical review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(28), md(5).minusHours(6)), md(5).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(35), "HR screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(35), md(1).minusHours(7)), md(1).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(42), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(42), md(2).minusHours(8)), md(2).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(49), "Case study", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(49), md(3).minusHours(9)), md(3).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(56), "Technical interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(56), md(4).minusHours(1)), md(4).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(63), "Behavioral interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(63), md(5).minusHours(2)), md(5).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(70), "Coding challenge", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(70), md(1).minusHours(3)), md(1).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(77), "Panel interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(77), md(2).minusHours(4)), md(2).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(84), "HR review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(84), md(3).minusHours(5)), md(3).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(91), "Technical screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(91), md(4).minusHours(6)), md(4).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(98), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(98), md(5).minusHours(7)), md(5).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(105), "Case study", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(105), md(1).minusHours(8)), md(1).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(112), "Behavioral screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(112), md(2).minusHours(9)), md(2).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(119), "Coding interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(119), md(3).minusHours(1)), md(3).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(126), "Panel discussion", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(126), md(4).minusHours(2)), md(4).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(133), "Technical review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(133), md(5).minusHours(3)), md(5).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(140), "HR screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(140), md(1).minusHours(4)), md(1).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(147), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(147), md(2).minusHours(5)), md(2).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(154), "Case study", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(154), md(3).minusHours(6)), md(3).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(161), "Technical interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(161), md(4).minusHours(7)), md(4).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(168), "Behavioral interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(168), md(5).minusHours(8)), md(5).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(175), "Coding challenge", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(175), md(1).minusHours(9)), md(1).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(182), "Panel interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(182), md(2).minusHours(1)), md(2).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(189), "HR review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(189), md(3).minusHours(2)), md(3).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(196), "Technical screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(196), md(4).minusHours(3)), md(4).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(203), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(203), md(5).minusHours(4)), md(5).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(210), "Case study", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(210), md(1).minusHours(5)), md(1).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(217), "Behavioral screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(217), md(2).minusHours(6)), md(2).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(224), "Coding interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(224), md(3).minusHours(7)), md(3).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(231), "Panel discussion", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(231), md(4).minusHours(8)), md(4).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(238), "Technical review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(238), md(5).minusHours(9)), md(5).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(245), "HR screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(245), md(1).minusHours(1)), md(1).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(250), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(250), md(2).minusHours(2)), md(2).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(251), "Technical interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(251), md(3).minusHours(3)), md(3).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(252), "Behavioral interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(252), md(4).minusHours(4)), md(4).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(253), "Coding challenge", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(253), md(5).minusHours(5)), md(5).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(254), "Panel interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(254), md(1).minusHours(6)), md(1).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(255), "HR review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(255), md(2).minusHours(7)), md(2).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(256), "Technical screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(256), md(3).minusHours(8)), md(3).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(257), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(257), md(4).minusHours(9)), md(4).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(258), "Case study", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(258), md(5).minusHours(1)), md(5).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(259), "Behavioral screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(259), md(1).minusHours(2)), md(1).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(260), "Coding interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(260), md(2).minusHours(3)), md(2).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(261), "Panel discussion", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(261), md(3).minusHours(4)), md(3).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(262), "Technical review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(262), md(4).minusHours(5)), md(4).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(263), "HR screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(263), md(5).minusHours(6)), md(5).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(264), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(264), md(1).minusHours(7)), md(1).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(265), "Case study", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(265), md(2).minusHours(8)), md(2).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(266), "Technical interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(266), md(3).minusHours(9)), md(3).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(267), "Behavioral interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(267), md(4).minusHours(1)), md(4).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(268), "Coding challenge", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(268), md(5).minusHours(2)), md(5).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(269), "Panel interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(269), md(1).minusHours(3)), md(1).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(270), "HR review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(270), md(2).minusHours(4)), md(2).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(271), "Technical screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(271), md(3).minusHours(5)), md(3).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(272), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(272), md(4).minusHours(6)), md(4).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(273), "Case study", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(273), md(5).minusHours(7)), md(5).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(274), "Behavioral screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(274), md(1).minusHours(8)), md(1).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(275), "Coding interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(275), md(2).minusHours(9)), md(2).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(276), "Panel discussion", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(276), md(3).minusHours(1)), md(3).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(277), "Technical review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(277), md(4).minusHours(2)), md(4).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(278), "HR screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(278), md(5).minusHours(3)), md(5).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(279), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(279), md(1).minusHours(4)), md(1).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(280), "Case study", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(280), md(2).minusHours(5)), md(2).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(281), "Technical interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(281), md(3).minusHours(6)), md(3).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(282), "Behavioral interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(282), md(4).minusHours(7)), md(4).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(283), "Coding challenge", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(283), md(5).minusHours(8)), md(5).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(284), "Panel interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(284), md(1).minusHours(9)), md(1).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(285), "HR review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(285), md(2).minusHours(1)), md(2).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(286), "Technical screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(286), md(3).minusHours(2)), md(3).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(287), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(287), md(4).minusHours(3)), md(4).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(288), "Case study", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(288), md(5).minusHours(4)), md(5).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(289), "Behavioral screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(289), md(1).minusHours(5)), md(1).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(290), "Coding interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(290), md(2).minusHours(6)), md(2).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(291), "Panel discussion", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(291), md(3).minusHours(7)), md(3).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(292), "Technical review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(292), md(4).minusHours(8)), md(4).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(293), "HR screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(293), md(5).minusHours(9)), md(5).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(294), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(294), md(1).minusHours(1)), md(1).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(295), "Case study", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(295), md(2).minusHours(2)), md(2).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(296), "Technical interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(296), md(3).minusHours(3)), md(3).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(297), "Behavioral interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(297), md(4).minusHours(4)), md(4).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(298), "Coding challenge", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(298), md(5).minusHours(5)), md(5).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(299), "Panel interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(299), md(1).minusHours(6)), md(1).minusHours(6)));

        // OFFERED (36 records, past interviews optional, no future interviews)
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(3), "Final interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(3), md(2).minusHours(1)), 8, md(2).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(10), "Technical review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(10), md(3).minusHours(2)), 7, md(3).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(17), "HR discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(17), md(4).minusHours(3)), 9, md(4).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(24), "Behavioral interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(24), md(1).minusHours(4)), 6, md(1).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(31), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(31), md(5).minusHours(5)), 8, md(5).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(38), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(38), md(2).minusHours(6)), 7, md(2).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(45), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(45), md(3).minusHours(7)), 9, md(3).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(52), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(52), md(4).minusHours(8)), 6, md(4).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(59), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(59), md(1).minusHours(9)), 8, md(1).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(66), "Behavioral review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(66), md(5).minusHours(1)), 7, md(5).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(73), "Coding challenge", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(73), md(2).minusHours(2)), 9, md(2).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(80), "Panel discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(80), md(3).minusHours(3)), 6, md(3).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(87), "HR screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(87), md(4).minusHours(4)), 8, md(4).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(94), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(94), md(1).minusHours(5)), 7, md(1).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(101), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(101), md(5).minusHours(6)), 9, md(5).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(108), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(108), md(2).minusHours(7)), 6, md(2).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(115), "Behavioral interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(115), md(3).minusHours(8)), 8, md(3).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(122), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(122), md(4).minusHours(9)), 7, md(4).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(129), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(129), md(1).minusHours(1)), 9, md(1).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(136), "HR review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(136), md(5).minusHours(2)), 6, md(5).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(143), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(143), md(2).minusHours(3)), 8, md(2).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(150), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(150), md(3).minusHours(4)), 7, md(3).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(157), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(157), md(4).minusHours(5)), 9, md(4).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(164), "Behavioral review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(164), md(1).minusHours(6)), 6, md(1).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(171), "Coding challenge", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(171), md(5).minusHours(7)), 8, md(5).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(178), "Panel discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(178), md(2).minusHours(8)), 7, md(2).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(185), "HR screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(185), md(3).minusHours(9)), 9, md(3).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(192), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(192), md(4).minusHours(1)), 6, md(4).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(199), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(199), md(1).minusHours(2)), 8, md(1).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(206), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(206), md(5).minusHours(3)), 7, md(5).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(213), "Behavioral interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(213), md(2).minusHours(4)), 9, md(2).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(220), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(220), md(3).minusHours(5)), 6, md(3).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(227), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(227), md(4).minusHours(6)), 8, md(4).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(234), "HR review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(234), md(1).minusHours(7)), 7, md(1).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(241), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(241), md(5).minusHours(8)), 9, md(5).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(248), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(248), md(2).minusHours(9)), 6, md(2).minusHours(9)));

        // ACCEPTED (37 records, past interviews optional, no future interviews)
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(4), "Final discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(4), md(3).minusHours(1)), 8, md(3).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(11), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(11), md(4).minusHours(2)), 7, md(4).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(18), "HR review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(18), md(1).minusHours(3)), 9, md(1).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(25), "Behavioral screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(25), md(5).minusHours(4)), 6, md(5).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(32), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(32), md(2).minusHours(5)), 8, md(2).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(39), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(39), md(3).minusHours(6)), 7, md(3).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(46), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(46), md(4).minusHours(7)), 9, md(4).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(53), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(53), md(1).minusHours(8)), 6, md(1).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(60), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(60), md(5).minusHours(9)), 8, md(5).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(67), "Behavioral review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(67), md(2).minusHours(1)), 7, md(2).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(74), "Coding challenge", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(74), md(3).minusHours(2)), 9, md(3).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(81), "Panel discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(81), md(4).minusHours(3)), 6, md(4).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(88), "HR screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(88), md(1).minusHours(4)), 8, md(1).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(95), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(95), md(5).minusHours(5)), 7, md(5).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(96), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(96), md(2).minusHours(6)), 9, md(2).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(102), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(102), md(3).minusHours(7)), 6, md(3).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(109), "Behavioral interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(109), md(4).minusHours(8)), 8, md(4).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(116), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(116), md(1).minusHours(9)), 7, md(1).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(123), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(123), md(5).minusHours(1)), 9, md(5).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(130), "HR review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(130), md(2).minusHours(2)), 6, md(2).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(137), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(137), md(3).minusHours(3)), 8, md(3).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(144), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(144), md(4).minusHours(4)), 7, md(4).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(151), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(151), md(1).minusHours(5)), 9, md(1).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(158), "Behavioral review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(158), md(5).minusHours(6)), 6, md(5).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(165), "Coding challenge", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(165), md(2).minusHours(7)), 8, md(2).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(172), "Panel discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(172), md(3).minusHours(8)), 7, md(3).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(179), "HR screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(179), md(4).minusHours(9)), 9, md(4).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(186), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(186), md(1).minusHours(1)), 6, md(1).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(193), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(193), md(5).minusHours(2)), 8, md(5).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(200), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(200), md(2).minusHours(3)), 7, md(2).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(207), "Behavioral interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(207), md(3).minusHours(4)), 9, md(3).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(214), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(214), md(4).minusHours(5)), 6, md(4).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(221), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(221), md(1).minusHours(6)), 8, md(1).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(228), "HR review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(228), md(5).minusHours(7)), 7, md(5).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(235), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(235), md(2).minusHours(8)), 9, md(2).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(242), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(242), md(3).minusHours(9)), 6, md(3).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(249), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(249), md(4).minusHours(1)), 8, md(4).minusHours(1)));

        // REJECTED (35 records, past interviews optional, no future interviews)
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(7), "Initial screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(7), md(1).minusHours(1)), 5, md(1).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(15), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(15), md(5).minusHours(3)), 4, md(5).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(22), "Behavioral review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(22), md(2).minusHours(4)), 6, md(2).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(29), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(29), md(3).minusHours(5)), 5, md(3).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(36), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(36), md(4).minusHours(6)), 4, md(4).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(43), "HR screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(43), md(1).minusHours(7)), 6, md(1).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(50), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(50), md(5).minusHours(8)), 5, md(5).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(57), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(57), md(2).minusHours(9)), 4, md(2).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(64), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(64), md(3).minusHours(1)), 6, md(3).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(71), "Behavioral interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(71), md(4).minusHours(2)), 5, md(4).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(78), "Coding challenge", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(78), md(1).minusHours(3)), 4, md(1).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(85), "Panel discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(85), md(5).minusHours(4)), 6, md(5).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(92), "HR review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(92), md(2).minusHours(5)), 5, md(2).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(99), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(99), md(3).minusHours(6)), 4, md(3).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(106), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(106), md(4).minusHours(7)), 6, md(4).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(113), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(113), md(1).minusHours(8)), 5, md(1).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(120), "Behavioral screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(120), md(5).minusHours(9)), 4, md(5).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(127), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(127), md(2).minusHours(1)), 6, md(2).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(134), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(134), md(3).minusHours(2)), 5, md(3).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(141), "HR screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(141), md(4).minusHours(3)), 4, md(4).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(148), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(148), md(1).minusHours(4)), 6, md(1).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(155), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(155), md(5).minusHours(5)), 5, md(5).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(162), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(162), md(2).minusHours(6)), 4, md(2).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(169), "Behavioral review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(169), md(3).minusHours(7)), 6, md(3).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(176), "Coding challenge", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(176), md(4).minusHours(8)), 5, md(4).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(183), "Panel discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(183), md(1).minusHours(9)), 4, md(1).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(190), "HR review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(190), md(5).minusHours(1)), 6, md(5).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(197), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(197), md(2).minusHours(2)), 5, md(2).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(204), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(204), md(3).minusHours(3)), 4, md(3).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(211), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(211), md(4).minusHours(4)), 6, md(4).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(218), "Behavioral screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(218), md(1).minusHours(5)), 5, md(1).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(225), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(225), md(5).minusHours(6)), 4, md(5).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(232), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(232), md(2).minusHours(7)), 6, md(2).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(239), "HR screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(239), md(3).minusHours(8)), 5, md(3).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(246), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(246), md(4).minusHours(9)), 4, md(4).minusHours(9)));

        // WITHDRAWN (35 records, past interviews optional, no future interviews)
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(8), "Initial interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(8), md(1).minusHours(1)), 5, md(1).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(16), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(16), md(5).minusHours(2)), 4, md(5).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(23), "Behavioral review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(23), md(2).minusHours(3)), 6, md(2).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(30), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(30), md(3).minusHours(4)), 5, md(3).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(37), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(37), md(4).minusHours(5)), 4, md(4).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(44), "HR screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(44), md(1).minusHours(6)), 6, md(1).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(51), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(51), md(5).minusHours(7)), 5, md(5).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(58), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(58), md(2).minusHours(8)), 4, md(2).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(65), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(65), md(3).minusHours(9)), 6, md(3).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(72), "Behavioral screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(72), md(4).minusHours(1)), 5, md(4).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(79), "Coding challenge", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(79), md(1).minusHours(2)), 4, md(1).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(86), "Panel discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(86), md(5).minusHours(3)), 6, md(5).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(93), "HR review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(93), md(2).minusHours(4)), 5, md(2).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(100), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(100), md(3).minusHours(5)), 4, md(3).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(107), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(107), md(4).minusHours(6)), 6, md(4).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(114), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(114), md(1).minusHours(7)), 5, md(1).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(121), "Behavioral review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(121), md(5).minusHours(8)), 4, md(5).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(128), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(128), md(2).minusHours(9)), 6, md(2).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(135), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(135), md(3).minusHours(1)), 5, md(3).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(142), "HR screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(142), md(4).minusHours(2)), 4, md(4).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(149), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(149), md(1).minusHours(3)), 6, md(1).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(156), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(156), md(5).minusHours(4)), 5, md(5).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(163), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(163), md(2).minusHours(5)), 4, md(2).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(170), "Behavioral screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(170), md(3).minusHours(6)), 6, md(3).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(177), "Coding challenge", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(177), md(4).minusHours(7)), 5, md(4).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(184), "Panel discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(184), md(1).minusHours(8)), 4, md(1).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(191), "HR review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(191), md(5).minusHours(9)), 6, md(5).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(198), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(198), md(2).minusHours(1)), 5, md(2).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(205), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(205), md(3).minusHours(2)), 4, md(3).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(212), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(212), md(4).minusHours(3)), 6, md(4).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(219), "Behavioral review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(219), md(1).minusHours(4)), 5, md(1).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(226), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(226), md(5).minusHours(5)), 4, md(5).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(233), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(233), md(2).minusHours(6)), 6, md(2).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(240), "HR screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(240), md(3).minusHours(7)), 5, md(3).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(247), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(247), md(4).minusHours(8)), 4, md(4).minusHours(8)));
    }

    private static final int MAX_SLOT_GENERATION_ATTEMPTS = 1000;
    private static final int SLOTS_PER_DAY = 18;
    private static final int MAX_DAYS_OFFSET = 7;

    public static TimeSlot generateRandomPastTimeSlot(ListInterface<ScheduledInterview> interviews, JobApplication jobApplication, LocalDateTime bookedAt) {
        final Random random = new Random();
        Company company = jobApplication.getJobPosting().getCompany();
        Applicant applicant = jobApplication.getApplicant();
        SetInterface<TimeSlot> companyOccupiedSlots = new HashSet<>();
        SetInterface<TimeSlot> applicantOccupiedSlots = new HashSet<>();

        for (int i = 0; i < interviews.size(); i++) {
            ScheduledInterview interview = interviews.get(i);
            if (interview.getJobApplication().getJobPosting().getCompany().equals(company)) {
                companyOccupiedSlots.add(interview.getTimeSlot());
            }
            if (interview.getJobApplication().getApplicant().equals(applicant)) {
                applicantOccupiedSlots.add(interview.getTimeSlot());
            }
        }

        for (int attempt = 0; attempt < MAX_SLOT_GENERATION_ATTEMPTS; attempt++) {
            int daysAgo = random.nextInt(MAX_DAYS_OFFSET + 1);
            LocalDate candidateDate = now.toLocalDate().minusDays(daysAgo);
            int slotIndex = random.nextInt(SLOTS_PER_DAY);

            TimeSlot candidateSlot = new TimeSlot(candidateDate, slotIndex);
            LocalDateTime candidateStartDateTime = candidateSlot.getStartDateTime();

            if (!candidateStartDateTime.isBefore(now)) {
                continue;
            }

            if (candidateSlot.isWeekend()) {
                continue;
            }

            if (!bookedAt.plusHours(2).isBefore(candidateStartDateTime)) {
                continue;
            }

            if (companyOccupiedSlots.contains(candidateSlot)) {
                continue;
            }

            if (applicantOccupiedSlots.contains(candidateSlot)) {
                continue;
            }

            return candidateSlot;
        }

        Log.warn("Could not generate a valid PAST TimeSlot for AppID " + jobApplication.getId() + " after " + MAX_SLOT_GENERATION_ATTEMPTS + " attempts.");
        return null;
    }

    public static TimeSlot generateRandomFutureTimeSlot(ListInterface<ScheduledInterview> interviews, JobApplication jobApplication, LocalDateTime bookedAt) {
        final Random random = new Random();
        LocalDateTime now = Context.getDateTime();
        LocalDateTime minStartTime = now.plusHours(2);
        Company company = jobApplication.getJobPosting().getCompany();
        Applicant applicant = jobApplication.getApplicant();
        SetInterface<TimeSlot> companyOccupiedSlots = new HashSet<>();
        SetInterface<TimeSlot> applicantOccupiedSlots = new HashSet<>();

        for (int i = 0; i < interviews.size(); i++) {
            ScheduledInterview interview = interviews.get(i);
            if (interview.getJobApplication().getJobPosting().getCompany().equals(company)) {
                companyOccupiedSlots.add(interview.getTimeSlot());
            }
            if (interview.getJobApplication().getApplicant().equals(applicant)) {
                applicantOccupiedSlots.add(interview.getTimeSlot());
            }
        }

        for (int attempt = 0; attempt < MAX_SLOT_GENERATION_ATTEMPTS; attempt++) {
            int daysAhead = random.nextInt(MAX_DAYS_OFFSET);
            LocalDate candidateDate = now.toLocalDate().plusDays(daysAhead);
            int slotIndex = random.nextInt(SLOTS_PER_DAY);

            TimeSlot candidateSlot = new TimeSlot(candidateDate, slotIndex);
            LocalDateTime candidateStartDateTime = candidateSlot.getStartDateTime();

            if (candidateStartDateTime.isBefore(minStartTime)) {
                continue;
            }

            if (candidateSlot.isWeekend()) {
                continue;
            }

            if (!bookedAt.plusHours(2).isBefore(candidateStartDateTime)) {
                continue;
            }

            if (companyOccupiedSlots.contains(candidateSlot)) {
                continue;
            }

            if (applicantOccupiedSlots.contains(candidateSlot)) {
                continue;
            }

            return candidateSlot;
        }

        Log.warn("Could not generate a valid FUTURE TimeSlot for AppID " + jobApplication.getId() + " after " + MAX_SLOT_GENERATION_ATTEMPTS + " attempts.");
        return null;
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
        ListInterface<EducationLevel> educationLevels = educationLevels();
        return educationLevels.get(random.nextInt(educationLevels.size()));
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
        int originalSize = original.size();

        ListInterface<Skill> singleSkillList = new DoublyLinkedList<>();

        if (originalSize > 0) {
            int index = random.nextInt(originalSize);
            singleSkillList.add(original.get(index));
        }

        return singleSkillList;
    }
    
    public static ListInterface<Skill> rs() {
        ListInterface<Skill> original = skills();
        int originalSize = original.size();

        ListInterface<Skill> randomSkills = new DoublyLinkedList<>();

        int count = Math.min(3, originalSize);

        while (randomSkills.size() < count) {
            int index = random.nextInt(originalSize);
            Skill candidate = original.get(index);

            boolean exists = false;
            for (int i = 0; i < randomSkills.size(); i++) {
                if (randomSkills.get(i).equals(candidate)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                randomSkills.add(candidate);
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

    public static JobPosting.Status s() {
        return JobPosting.Status.OPEN;
    }

    public static LocalDateTime md(int minus) {
        return now.minusDays(minus);
    }

    public static ListInterface<Company> getCompanies() {
        return c;
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
        return scheduledInterviews;
    }

    public static ListInterface<BlockedTimeSlot> getBlockedTimeSlots() {
        return blockedTimeSlots;
    }

    public static ListInterface<SkillOption> getSkillOptions() {
        return skillOptions;
    }
}