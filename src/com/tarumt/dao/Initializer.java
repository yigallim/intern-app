package com.tarumt.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Log;

public class Initializer {
    private static final ListInterface<Company> companies = new DoublyLinkedList<>();
    private static final ListInterface<JobPosting> jobPostings = new DoublyLinkedList<>();
    private static final ListInterface<Applicant> applicants = new DoublyLinkedList<>();
    private static final ListInterface<JobApplication> jobApplications = new DoublyLinkedList<>();
    private static final ListInterface<Invitation> invitations = new DoublyLinkedList<>();
    private static final ListInterface<ScheduledInterview> scheduledInterviews = new DoublyLinkedList<>();
    private static final ListInterface<BlockedTimeSlot> blockedTimeSlots = new DoublyLinkedList<>();

    static {
        Initializer.init();
    }

    private static void init() {
        if (false) return;
        // region Init
        LocalDateTime now = Context.getDateTime();
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
        companies.add(new Company("Test", "Empty test company", new Location(City.SETAPAK), "test@test.com", "0119999999"));

        // 150 Job Postings (5 per company, Hard-Coded, Qualifications set to null)
        // TechCorp (Company 0)
        jobPostings.add(new JobPosting("Software Engineer", companies.get(0), 5000, 8000, "Develop scalable web applications", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Senior Developer", companies.get(0), 7000, 10000, "Lead development projects for clients", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("DevOps Engineer", companies.get(0), 6000, 9000, "Manage CI/CD pipelines and deployments", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("QA Engineer", companies.get(0), 4000, 6000, "Ensure product quality through testing", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("UI/UX Designer", companies.get(0), 4500, 7000, "Design user interfaces for web apps", JobPosting.Type.DESIGN_ARCH, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // Innovatech (Company 1)
        jobPostings.add(new JobPosting("Data Scientist", companies.get(1), 6000, 9000, "Analyze complex datasets for insights", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("AI Engineer", companies.get(1), 7000, 11000, "Develop AI models for automation", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("ML Engineer", companies.get(1), 6500, 10000, "Implement ML algorithms for projects", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Research Scientist", companies.get(1), 8000, 12000, "Conduct innovative research in tech", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Product Manager", companies.get(1), 7000, 11000, "Manage product lifecycle and strategy", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // FinServe (Company 2)
        jobPostings.add(new JobPosting("Financial Analyst", companies.get(2), 5000, 8000, "Analyze financial data for insights", JobPosting.Type.BANK_FIN_SERV, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Risk Manager", companies.get(2), 6000, 9000, "Assess financial risks for the company", JobPosting.Type.BANK_FIN_SERV, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Accountant", companies.get(2), 4500, 7000, "Manage financial records and reports", JobPosting.Type.ACCOUNTING, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Investment Advisor", companies.get(2), 5500, 8500, "Provide investment advice to clients", JobPosting.Type.BANK_FIN_SERV, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Compliance Officer", companies.get(2), 5000, 8000, "Ensure regulatory compliance", JobPosting.Type.BANK_FIN_SERV, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // HealthPlus (Company 3)
        jobPostings.add(new JobPosting("Medical Researcher", companies.get(3), 6000, 9000, "Conduct medical research studies", JobPosting.Type.HEALTH_MED, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Clinical Analyst", companies.get(3), 5000, 8000, "Analyze clinical data for insights", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Healthcare Manager", companies.get(3), 7000, 10000, "Manage healthcare operations", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Health Consultant", companies.get(3), 5500, 8500, "Provide health consultancy services", JobPosting.Type.HEALTH_MED, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Data Specialist", companies.get(3), 5000, 8000, "Handle healthcare data systems", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // EduTech (Company 4)
        jobPostings.add(new JobPosting("EdTech Developer", companies.get(4), 5000, 8000, "Develop educational software tools", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Content Creator", companies.get(4), 4000, 6000, "Create educational content", JobPosting.Type.EDUC_TRAINING, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Instructional Designer", companies.get(4), 4500, 7000, "Design learning materials", JobPosting.Type.EDUC_TRAINING, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Tech Support", companies.get(4), 3500, 5000, "Provide tech support for platforms", JobPosting.Type.CALL_CUST_SVC, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Project Manager", companies.get(4), 6000, 9000, "Manage education tech projects", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // GreenEnergy (Company 5)
        jobPostings.add(new JobPosting("Energy Engineer", companies.get(5), 6000, 9000, "Design sustainable energy systems", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Sustainability Analyst", companies.get(5), 5000, 8000, "Analyze sustainability metrics", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Project Coordinator", companies.get(5), 4500, 7000, "Coordinate energy projects", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Field Technician", companies.get(5), 4000, 6000, "Install energy systems", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Research Analyst", companies.get(5), 5000, 8000, "Research green energy solutions", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // AutoDrive (Company 6)
        jobPostings.add(new JobPosting("Robotics Engineer", companies.get(6), 7000, 11000, "Develop autonomous vehicle systems", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Software Developer", companies.get(6), 6000, 9000, "Code vehicle control software", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Test Engineer", companies.get(6), 5000, 8000, "Test autonomous vehicle systems", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Data Engineer", companies.get(6), 5500, 8500, "Manage vehicle data pipelines", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Safety Analyst", companies.get(6), 5000, 8000, "Ensure vehicle safety standards", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // FoodieTech (Company 7)
        jobPostings.add(new JobPosting("App Developer", companies.get(7), 5000, 8000, "Develop food delivery apps", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Marketing Specialist", companies.get(7), 4500, 7000, "Market food tech solutions", JobPosting.Type.MARKETING_COMM, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Data Analyst", companies.get(7), 5000, 8000, "Analyze food industry trends", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Logistics Manager", companies.get(7), 5500, 8500, "Manage food delivery logistics", JobPosting.Type.MANF_LOG, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Customer Support", companies.get(7), 3500, 5000, "Support food app users", JobPosting.Type.CALL_CUST_SVC, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // CyberShield (Company 8)
        jobPostings.add(new JobPosting("Security Analyst", companies.get(8), 6000, 9000, "Analyze cybersecurity threats", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Penetration Tester", companies.get(8), 6500, 9500, "Test system vulnerabilities", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Security Engineer", companies.get(8), 7000, 10000, "Design secure systems", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Compliance Analyst", companies.get(8), 5000, 8000, "Ensure regulatory compliance", JobPosting.Type.LEGAL, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Incident Responder", companies.get(8), 5500, 8500, "Respond to security incidents", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // QuantumSoft (Company 9)
        jobPostings.add(new JobPosting("Quantum Developer", companies.get(9), 8000, 12000, "Develop quantum computing software", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Research Scientist", companies.get(9), 7500, 11000, "Research quantum algorithms", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Software Engineer", companies.get(9), 6000, 9000, "Code quantum applications", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Data Scientist", companies.get(9), 6500, 9500, "Analyze quantum computing data", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Project Manager", companies.get(9), 7000, 10000, "Manage quantum projects", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // MediCare (Company 10)
        jobPostings.add(new JobPosting("Clinical Researcher", companies.get(10), 6000, 9000, "Conduct clinical research studies", JobPosting.Type.HEALTH_MED, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Medical Analyst", companies.get(10), 5000, 8000, "Analyze medical data trends", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Health Consultant", companies.get(10), 5500, 8500, "Provide medical consultancy", JobPosting.Type.HEALTH_MED, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Data Specialist", companies.get(10), 5000, 8000, "Manage medical data systems", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Operations Manager", companies.get(10), 6000, 9000, "Oversee medical operations", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // CloudSys (Company 11)
        jobPostings.add(new JobPosting("Cloud Engineer", companies.get(11), 6000, 9000, "Design cloud infrastructure", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("DevOps Specialist", companies.get(11), 6500, 9500, "Manage cloud deployments", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Security Analyst", companies.get(11), 5500, 8500, "Secure cloud systems", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Data Engineer", companies.get(11), 6000, 9000, "Build data pipelines in cloud", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Cloud Architect", companies.get(11), 7000, 11000, "Architect cloud solutions", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // NeuroTech (Company 12)
        jobPostings.add(new JobPosting("Neuroscientist", companies.get(12), 7000, 11000, "Research neural networks", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("AI Developer", companies.get(12), 6500, 9500, "Develop AI for neuroscience", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Data Scientist", companies.get(12), 6000, 9000, "Analyze neural data", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Research Analyst", companies.get(12), 5500, 8500, "Support neuroscience research", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Software Engineer", companies.get(12), 6000, 9000, "Code neuroscience tools", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // AgriGrow (Company 13)
        jobPostings.add(new JobPosting("Agri Engineer", companies.get(13), 5000, 8000, "Design farming technology", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Data Analyst", companies.get(13), 4500, 7000, "Analyze agricultural data", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Field Manager", companies.get(13), 4000, 6000, "Manage farming operations", JobPosting.Type.AGRICULTURE, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Tech Specialist", companies.get(13), 4500, 7000, "Support agri-tech systems", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Sustainability Lead", companies.get(13), 5000, 8000, "Lead sustainable farming", JobPosting.Type.AGRICULTURE, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // SpaceFront (Company 14)
        jobPostings.add(new JobPosting("Aerospace Engineer", companies.get(14), 7000, 11000, "Design aerospace systems", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Space Researcher", companies.get(14), 6500, 9500, "Research space technologies", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Test Engineer", companies.get(14), 6000, 9000, "Test aerospace components", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Data Analyst", companies.get(14), 5500, 8500, "Analyze space mission data", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Project Manager", companies.get(14), 7000, 10000, "Manage space projects", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // SmartRetail (Company 15)
        jobPostings.add(new JobPosting("Retail Software Engineer", companies.get(15), 5000, 8000, "Develop retail management software", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Retail Data Analyst", companies.get(15), 4500, 7000, "Analyze retail sales data", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("UX Designer", companies.get(15), 4000, 6500, "Design intuitive retail interfaces", JobPosting.Type.DESIGN_ARCH, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Customer Success Manager", companies.get(15), 5000, 7500, "Support retail clients", JobPosting.Type.CALL_CUST_SVC, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Marketing Coordinator", companies.get(15), 4000, 6000, "Plan retail marketing campaigns", JobPosting.Type.MARKETING_COMM, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // BioTech (Company 16)
        jobPostings.add(new JobPosting("Biotech Researcher", companies.get(16), 6000, 9000, "Conduct biotech experiments", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Lab Technician", companies.get(16), 4000, 6000, "Support lab operations", JobPosting.Type.HEALTH_MED, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Bioinformatics Specialist", companies.get(16), 5500, 8500, "Analyze biological data", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Regulatory Affairs Manager", companies.get(16), 6000, 9000, "Ensure compliance with regulations", JobPosting.Type.LEGAL, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Project Manager", companies.get(16), 6500, 9500, "Manage biotech projects", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // UrbanPlan (Company 17)
        jobPostings.add(new JobPosting("Urban Planner", companies.get(17), 5500, 8500, "Design sustainable urban layouts", JobPosting.Type.DESIGN_ARCH, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("GIS Analyst", companies.get(17), 5000, 7500, "Analyze geographic data", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Project Coordinator", companies.get(17), 4500, 7000, "Coordinate urban projects", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Sustainability Consultant", companies.get(17), 5000, 8000, "Advise on green urban solutions", JobPosting.Type.CONSULTING, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Civil Engineer", companies.get(17), 6000, 9000, "Design urban infrastructure", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // CleanTech (Company 18)
        jobPostings.add(new JobPosting("Environmental Engineer", companies.get(18), 5500, 8500, "Develop eco-friendly technologies", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Sustainability Analyst", companies.get(18), 4500, 7000, "Analyze environmental impact", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Field Technician", companies.get(18), 4000, 6000, "Install clean tech systems", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Research Scientist", companies.get(18), 6000, 9000, "Research sustainable solutions", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Operations Manager", companies.get(18), 6500, 9500, "Oversee clean tech operations", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // DataWave (Company 19)
        jobPostings.add(new JobPosting("Big Data Engineer", companies.get(19), 6000, 9000, "Build data processing pipelines", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("AI Analyst", companies.get(19), 5500, 8500, "Develop AI-driven insights", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Data Scientist", companies.get(19), 6000, 9000, "Analyze large datasets", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("DevOps Engineer", companies.get(19), 5500, 8500, "Manage data infrastructure", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Project Manager", companies.get(19), 6500, 9500, "Lead data projects", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // TravelTech (Company 20)
        jobPostings.add(new JobPosting("Travel App Developer", companies.get(20), 5000, 8000, "Develop travel booking apps", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Marketing Specialist", companies.get(20), 4500, 7000, "Promote travel tech solutions", JobPosting.Type.MARKETING_COMM, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Customer Support", companies.get(20), 3500, 5000, "Assist travel app users", JobPosting.Type.CALL_CUST_SVC, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Data Analyst", companies.get(20), 4500, 7000, "Analyze travel trends", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Product Manager", companies.get(20), 6000, 9000, "Manage travel tech products", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // SecureNet (Company 21)
        jobPostings.add(new JobPosting("Network Engineer", companies.get(21), 6000, 9000, "Design secure network systems", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Security Analyst", companies.get(21), 5500, 8500, "Monitor network security", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("IT Consultant", companies.get(21), 6000, 9000, "Advise on network solutions", JobPosting.Type.CONSULTING, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("System Administrator", companies.get(21), 5000, 7500, "Manage IT infrastructure", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Compliance Officer", companies.get(21), 5500, 8500, "Ensure network compliance", JobPosting.Type.LEGAL, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // EduPlatform (Company 22)
        jobPostings.add(new JobPosting("E-Learning Developer", companies.get(22), 5000, 8000, "Develop online learning platforms", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Content Specialist", companies.get(22), 4000, 6000, "Create educational content", JobPosting.Type.EDUC_TRAINING, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Instructional Designer", companies.get(22), 4500, 7000, "Design e-learning courses", JobPosting.Type.EDUC_TRAINING, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Tech Support", companies.get(22), 3500, 5000, "Support learning platforms", JobPosting.Type.CALL_CUST_SVC, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Project Manager", companies.get(22), 6000, 9000, "Manage e-learning projects", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // HealthTech (Company 23)
        jobPostings.add(new JobPosting("Health App Developer", companies.get(23), 5000, 8000, "Develop healthcare apps", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Clinical Data Analyst", companies.get(23), 4500, 7000, "Analyze health data", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Health Consultant", companies.get(23), 5500, 8500, "Provide health tech advice", JobPosting.Type.HEALTH_MED, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("QA Engineer", companies.get(23), 4000, 6000, "Test healthcare systems", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Operations Manager", companies.get(23), 6000, 9000, "Manage health tech operations", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // LogiSys (Company 24)
        jobPostings.add(new JobPosting("Logistics Software Engineer", companies.get(24), 5000, 8000, "Develop logistics software", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Supply Chain Analyst", companies.get(24), 4500, 7000, "Analyze supply chain data", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Logistics Manager", companies.get(24), 5500, 8500, "Manage logistics operations", JobPosting.Type.MANF_LOG, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Customer Support", companies.get(24), 3500, 5000, "Support logistics clients", JobPosting.Type.CALL_CUST_SVC, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Project Coordinator", companies.get(24), 5000, 7500, "Coordinate logistics projects", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // FinTech (Company 25)
        jobPostings.add(new JobPosting("FinTech Developer", companies.get(25), 6000, 9000, "Develop financial software", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Financial Analyst", companies.get(25), 5000, 8000, "Analyze financial trends", JobPosting.Type.BANK_FIN_SERV, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Compliance Specialist", companies.get(25), 5500, 8500, "Ensure financial compliance", JobPosting.Type.LEGAL, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Data Scientist", companies.get(25), 6000, 9000, "Analyze financial data", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Product Manager", companies.get(25), 6500, 9500, "Manage fintech products", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // GameStudio (Company 26)
        jobPostings.add(new JobPosting("Game Developer", companies.get(26), 5000, 8000, "Develop interactive games", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("3D Artist", companies.get(26), 4500, 7000, "Create game visuals", JobPosting.Type.DESIGN_ARCH, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("QA Tester", companies.get(26), 4000, 6000, "Test game functionality", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Game Designer", companies.get(26), 5000, 7500, "Design game mechanics", JobPosting.Type.DESIGN_ARCH, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Marketing Specialist", companies.get(26), 4500, 7000, "Promote games", JobPosting.Type.MARKETING_COMM, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // SmartHome (Company 27)
        jobPostings.add(new JobPosting("IoT Engineer", companies.get(27), 6000, 9000, "Develop smart home devices", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Software Developer", companies.get(27), 5000, 8000, "Code smart home software", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("UX Designer", companies.get(27), 4500, 7000, "Design smart home interfaces", JobPosting.Type.DESIGN_ARCH, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Customer Support", companies.get(27), 3500, 5000, "Support smart home users", JobPosting.Type.CALL_CUST_SVC, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Product Manager", companies.get(27), 6000, 9000, "Manage smart home products", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // EcoFarm (Company 28)
        jobPostings.add(new JobPosting("AgriTech Engineer", companies.get(28), 5000, 8000, "Develop farming technology", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Data Analyst", companies.get(28), 4500, 7000, "Analyze farm data", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Farm Manager", companies.get(28), 4000, 6000, "Manage farm operations", JobPosting.Type.AGRICULTURE, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("Sustainability Specialist", companies.get(28), 4500, 7000, "Promote sustainable farming", JobPosting.Type.AGRICULTURE, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Tech Support", companies.get(28), 4000, 6000, "Support farm tech systems", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // RoboTech (Company 29)
        jobPostings.add(new JobPosting("Robotics Engineer", companies.get(29), 6000, 9000, "Design robotic systems", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, now.minusDays(5), now.minusDays(5)));
        jobPostings.add(new JobPosting("Automation Specialist", companies.get(29), 5500, 8500, "Develop automation solutions", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, now.minusDays(3), now.minusDays(3)));
        jobPostings.add(new JobPosting("Software Engineer", companies.get(29), 5000, 8000, "Code robotics software", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(7), now.minusDays(7)));
        jobPostings.add(new JobPosting("QA Engineer", companies.get(29), 4500, 7000, "Test robotic systems", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, now.minusDays(2), now.minusDays(2)));
        jobPostings.add(new JobPosting("Project Manager", companies.get(29), 6000, 9000, "Manage robotics projects", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, now.minusDays(4), now.minusDays(4)));

        // 50 Applicants (Hard-Coded, Qualifications set to null, Using Malaysian Cities)
        applicants.add(new Applicant("Alice Johnson", "alice.johnson@email.com", "0123456789", JobPosting.Type.IT_COMM_TEC, new Location(City.JOHOR_BAHRU)));
        applicants.add(new Applicant("Bob Smith", "bob.smith@email.com", "0198765432", JobPosting.Type.SCIENCE_TECH, new Location(City.KUALA_LUMPUR)));
        applicants.add(new Applicant("Charlie Brown", "charlie.brown@email.com", "0129876543", JobPosting.Type.BANK_FIN_SERV, new Location(City.PENANG_HILL)));
        applicants.add(new Applicant("Daisy Williams", "daisy.williams@email.com", "0134567890", JobPosting.Type.HEALTH_MED, new Location(City.IPOH)));
        applicants.add(new Applicant("Ethan Lee", "ethan.lee@email.com", "0145678901", JobPosting.Type.DESIGN_ARCH, new Location(City.KUCHING)));
        applicants.add(new Applicant("Fiona Davis", "fiona.davis@email.com", "0156789012", JobPosting.Type.IT_COMM_TEC, new Location(City.KOTA_KINABALU)));
        applicants.add(new Applicant("George Wilson", "george.wilson@email.com", "0167890123", JobPosting.Type.SCIENCE_TECH, new Location(City.MELAKA)));
        applicants.add(new Applicant("Hannah Moore", "hannah.moore@email.com", "0178901234", JobPosting.Type.SALES, new Location(City.SEREMBAN)));
        applicants.add(new Applicant("Ian Taylor", "ian.taylor@email.com", "0189012345", JobPosting.Type.CALL_CUST_SVC, new Location(City.KUANTAN_PAHANG)));
        applicants.add(new Applicant("Julia Anderson", "julia.anderson@email.com", "0190123456", JobPosting.Type.IT_COMM_TEC, new Location(City.SUNGAI_PETANI)));
        applicants.add(new Applicant("Kevin White", "kevin.white@email.com", "01112345678", JobPosting.Type.HEALTH_MED, new Location(City.KOTA_BHARU)));
        applicants.add(new Applicant("Laura Martinez", "laura.martinez@email.com", "0122345678", JobPosting.Type.IT_COMM_TEC, new Location(City.SHAH_ALAM)));
        applicants.add(new Applicant("Mike Brown", "mike.brown@email.com", "0133456789", JobPosting.Type.SCIENCE_TECH, new Location(City.KULIM)));
        applicants.add(new Applicant("Nina Harris", "nina.harris@email.com", "0144567890", JobPosting.Type.BANK_FIN_SERV, new Location(City.TAIPING)));
        applicants.add(new Applicant("Oliver Clark", "oliver.clark@email.com", "0155678901", JobPosting.Type.DESIGN_ARCH, new Location(City.ALOR_SETAR)));
        applicants.add(new Applicant("Pamela Lewis", "pamela.lewis@email.com", "0166789012", JobPosting.Type.IT_COMM_TEC, new Location(City.JOHOR_BAHRU)));
        applicants.add(new Applicant("Quincy Adams", "quincy.adams@email.com", "0177890123", JobPosting.Type.SCIENCE_TECH, new Location(City.KUALA_LUMPUR)));
        applicants.add(new Applicant("Rachel Green", "rachel.green@email.com", "0188901234", JobPosting.Type.SALES, new Location(City.PENANG_HILL)));
        applicants.add(new Applicant("Sam Evans", "sam.evans@email.com", "0199012345", JobPosting.Type.HEALTH_MED, new Location(City.IPOH)));
        applicants.add(new Applicant("Tina Walker", "tina.walker@email.com", "01123456789", JobPosting.Type.DESIGN_ARCH, new Location(City.KUCHING)));
        applicants.add(new Applicant("Umar Khan", "umar.khan@email.com", "0124567890", JobPosting.Type.IT_COMM_TEC, new Location(City.KOTA_KINABALU)));
        applicants.add(new Applicant("Vera Scott", "vera.scott@email.com", "0135678901", JobPosting.Type.SCIENCE_TECH, new Location(City.MELAKA)));
        applicants.add(new Applicant("Will Turner", "will.turner@email.com", "0146789012", JobPosting.Type.BANK_FIN_SERV, new Location(City.SEREMBAN)));
        applicants.add(new Applicant("Xena Brooks", "xena.brooks@email.com", "0157890123", JobPosting.Type.CALL_CUST_SVC, new Location(City.KUANTAN_PAHANG)));
        applicants.add(new Applicant("Yara King", "yara.king@email.com", "0168901234", JobPosting.Type.IT_COMM_TEC, new Location(City.SUNGAI_PETANI)));
        applicants.add(new Applicant("Zack Miller", "zack.miller@email.com", "0179012345", JobPosting.Type.HEALTH_MED, new Location(City.KOTA_BHARU)));
        applicants.add(new Applicant("Amy Carter", "amy.carter@email.com", "0180123456", JobPosting.Type.IT_COMM_TEC, new Location(City.SHAH_ALAM)));
        applicants.add(new Applicant("Ben Foster", "ben.foster@email.com", "0191234567", JobPosting.Type.SCIENCE_TECH, new Location(City.KULIM)));
        applicants.add(new Applicant("Clara Hayes", "clara.hayes@email.com", "01134567890", JobPosting.Type.SALES, new Location(City.TAIPING)));
        applicants.add(new Applicant("Dan Price", "dan.price@email.com", "0125678901", JobPosting.Type.DESIGN_ARCH, new Location(City.ALOR_SETAR)));
        applicants.add(new Applicant("Ella Reed", "ella.reed@email.com", "0136789012", JobPosting.Type.IT_COMM_TEC, new Location(City.JOHOR_BAHRU)));
        applicants.add(new Applicant("Finn Cole", "finn.cole@email.com", "0147890123", JobPosting.Type.SCIENCE_TECH, new Location(City.KUALA_LUMPUR)));
        applicants.add(new Applicant("Gina Bell", "gina.bell@email.com", "0158901234", JobPosting.Type.BANK_FIN_SERV, new Location(City.PENANG_HILL)));
        applicants.add(new Applicant("Hank Ford", "hank.ford@email.com", "0169012345", JobPosting.Type.HEALTH_MED, new Location(City.IPOH)));
        applicants.add(new Applicant("Iris Lane", "iris.lane@email.com", "0170123456", JobPosting.Type.DESIGN_ARCH, new Location(City.KUCHING)));
        applicants.add(new Applicant("Jack Gray", "jack.gray@email.com", "0181234567", JobPosting.Type.IT_COMM_TEC, new Location(City.KOTA_KINABALU)));
        applicants.add(new Applicant("Kara Hill", "kara.hill@email.com", "0192345678", JobPosting.Type.SCIENCE_TECH, new Location(City.MELAKA)));
        applicants.add(new Applicant("Liam Ward", "liam.ward@email.com", "01145678901", JobPosting.Type.SALES, new Location(City.SEREMBAN)));
        applicants.add(new Applicant("Maya Ross", "maya.ross@email.com", "0126789012", JobPosting.Type.CALL_CUST_SVC, new Location(City.KUANTAN_PAHANG)));
        applicants.add(new Applicant("Nate Fox", "nate.fox@email.com", "0137890123", JobPosting.Type.IT_COMM_TEC, new Location(City.SUNGAI_PETANI)));
        applicants.add(new Applicant("Opal Dean", "opal.dean@email.com", "0148901234", JobPosting.Type.HEALTH_MED, new Location(City.KOTA_BHARU)));
        applicants.add(new Applicant("Paul Hart", "paul.hart@email.com", "0159012345", JobPosting.Type.IT_COMM_TEC, new Location(City.SHAH_ALAM)));
        applicants.add(new Applicant("Quinn Cole", "quinn.cole@email.com", "0160123456", JobPosting.Type.SCIENCE_TECH, new Location(City.KULIM)));
        applicants.add(new Applicant("Rita Lane", "rita.lane@email.com", "0171234567", JobPosting.Type.BANK_FIN_SERV, new Location(City.TAIPING)));
        applicants.add(new Applicant("Seth Owen", "seth.owen@email.com", "0182345678", JobPosting.Type.DESIGN_ARCH, new Location(City.ALOR_SETAR)));
        applicants.add(new Applicant("Tara Bell", "tara.bell@email.com", "0193456789", JobPosting.Type.IT_COMM_TEC, new Location(City.JOHOR_BAHRU)));
        applicants.add(new Applicant("Uma West", "uma.west@email.com", "01156789012", JobPosting.Type.SCIENCE_TECH, new Location(City.KUALA_LUMPUR)));
        applicants.add(new Applicant("Vince Ray", "vince.ray@email.com", "0127890123", JobPosting.Type.SALES, new Location(City.PENANG_HILL)));
        applicants.add(new Applicant("Wendy Fox", "wendy.fox@email.com", "0138901234", JobPosting.Type.HEALTH_MED, new Location(City.IPOH)));
        applicants.add(new Applicant("Xander Lee", "xander.lee@email.com", "0149012345", JobPosting.Type.DESIGN_ARCH, new Location(City.KUCHING)));
        applicants.add(new Applicant("Test", "test@test.com", "0119999999", JobPosting.Type.ADMIN_OFFICE, new Location(City.KUALA_LUMPUR)));
        // endregion

        // region Init
        //251 job applications
        // Applicant 0 (Alice Johnson)
        jobApplications.add(new JobApplication(jobPostings.get(12), applicants.get(0), JobApplication.Status.PENDING, now.minusDays(7).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(21), applicants.get(0), JobApplication.Status.SHORTLISTED, now.minusDays(6).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(2), applicants.get(0), JobApplication.Status.INTERVIEWED, now.minusDays(5).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(3), applicants.get(0), JobApplication.Status.OFFERED, now.minusDays(4).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(4), applicants.get(0), JobApplication.Status.ACCEPTED, now.minusDays(3).minusHours(6)));

        // Applicant 1 (Bob Smith)
        jobApplications.add(new JobApplication(jobPostings.get(1), applicants.get(1), JobApplication.Status.PENDING, now.minusDays(2).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(6), applicants.get(1), JobApplication.Status.SHORTLISTED, now.minusDays(1).minusHours(8)));
        jobApplications.add(new JobApplication(jobPostings.get(11), applicants.get(1), JobApplication.Status.REJECTED, now.minusDays(0).minusHours(9)));
        jobApplications.add(new JobApplication(jobPostings.get(16), applicants.get(1), JobApplication.Status.WITHDRAWN, now.minusDays(6).minusHours(10)));
        jobApplications.add(new JobApplication(jobPostings.get(21), applicants.get(1), JobApplication.Status.PENDING, now.minusDays(5).minusHours(11)));

        // Applicant 2 (Charlie Brown)
        jobApplications.add(new JobApplication(jobPostings.get(2), applicants.get(2), JobApplication.Status.OFFERED, now.minusDays(4).minusHours(12)));
        jobApplications.add(new JobApplication(jobPostings.get(7), applicants.get(2), JobApplication.Status.ACCEPTED, now.minusDays(3).minusHours(13)));
        jobApplications.add(new JobApplication(jobPostings.get(12), applicants.get(2), JobApplication.Status.INTERVIEWED, now.minusDays(2).minusHours(14)));
        jobApplications.add(new JobApplication(jobPostings.get(17), applicants.get(2), JobApplication.Status.PENDING, now.minusDays(1).minusHours(15)));
        jobApplications.add(new JobApplication(jobPostings.get(22), applicants.get(2), JobApplication.Status.SHORTLISTED, now.minusDays(0).minusHours(16)));

        // Applicant 3 (Daisy Williams)
        jobApplications.add(new JobApplication(jobPostings.get(3), applicants.get(3), JobApplication.Status.REJECTED, now.minusDays(7).minusHours(17)));
        jobApplications.add(new JobApplication(jobPostings.get(8), applicants.get(3), JobApplication.Status.WITHDRAWN, now.minusDays(6).minusHours(18)));
        jobApplications.add(new JobApplication(jobPostings.get(13), applicants.get(3), JobApplication.Status.OFFERED, now.minusDays(5).minusHours(19)));
        jobApplications.add(new JobApplication(jobPostings.get(18), applicants.get(3), JobApplication.Status.ACCEPTED, now.minusDays(4).minusHours(20)));
        jobApplications.add(new JobApplication(jobPostings.get(23), applicants.get(3), JobApplication.Status.INTERVIEWED, now.minusDays(3).minusHours(21)));

        // Applicant 4 (Ethan Lee)
        jobApplications.add(new JobApplication(jobPostings.get(4), applicants.get(4), JobApplication.Status.PENDING, now.minusDays(2).minusHours(22)));
        jobApplications.add(new JobApplication(jobPostings.get(9), applicants.get(4), JobApplication.Status.SHORTLISTED, now.minusDays(1).minusHours(23)));
        jobApplications.add(new JobApplication(jobPostings.get(14), applicants.get(4), JobApplication.Status.REJECTED, now.minusDays(0).minusHours(24)));
        jobApplications.add(new JobApplication(jobPostings.get(19), applicants.get(4), JobApplication.Status.WITHDRAWN, now.minusDays(7).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(24), applicants.get(4), JobApplication.Status.OFFERED, now.minusDays(6).minusHours(2)));

        // Applicant 5 (Fiona Davis)
        jobApplications.add(new JobApplication(jobPostings.get(5), applicants.get(5), JobApplication.Status.ACCEPTED, now.minusDays(5).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(10), applicants.get(5), JobApplication.Status.INTERVIEWED, now.minusDays(4).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(5), JobApplication.Status.PENDING, now.minusDays(3).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(5), JobApplication.Status.SHORTLISTED, now.minusDays(2).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(5), JobApplication.Status.REJECTED, now.minusDays(1).minusHours(7)));

        // Applicant 6 (George Wilson)
        jobApplications.add(new JobApplication(jobPostings.get(6), applicants.get(6), JobApplication.Status.WITHDRAWN, now.minusDays(0).minusHours(8)));
        jobApplications.add(new JobApplication(jobPostings.get(11), applicants.get(6), JobApplication.Status.OFFERED, now.minusDays(7).minusHours(9)));
        jobApplications.add(new JobApplication(jobPostings.get(16), applicants.get(6), JobApplication.Status.ACCEPTED, now.minusDays(6).minusHours(10)));
        jobApplications.add(new JobApplication(jobPostings.get(21), applicants.get(6), JobApplication.Status.INTERVIEWED, now.minusDays(5).minusHours(11)));
        jobApplications.add(new JobApplication(jobPostings.get(26), applicants.get(6), JobApplication.Status.PENDING, now.minusDays(4).minusHours(12)));

        // Applicant 7 (Hannah Moore)
        jobApplications.add(new JobApplication(jobPostings.get(7), applicants.get(7), JobApplication.Status.SHORTLISTED, now.minusDays(3).minusHours(13)));
        jobApplications.add(new JobApplication(jobPostings.get(12), applicants.get(7), JobApplication.Status.REJECTED, now.minusDays(2).minusHours(14)));
        jobApplications.add(new JobApplication(jobPostings.get(17), applicants.get(7), JobApplication.Status.WITHDRAWN, now.minusDays(1).minusHours(15)));
        jobApplications.add(new JobApplication(jobPostings.get(22), applicants.get(7), JobApplication.Status.OFFERED, now.minusDays(0).minusHours(16)));
        jobApplications.add(new JobApplication(jobPostings.get(27), applicants.get(7), JobApplication.Status.ACCEPTED, now.minusDays(7).minusHours(17)));

        // Applicant 8 (Ian Taylor)
        jobApplications.add(new JobApplication(jobPostings.get(8), applicants.get(8), JobApplication.Status.INTERVIEWED, now.minusDays(6).minusHours(18)));
        jobApplications.add(new JobApplication(jobPostings.get(13), applicants.get(8), JobApplication.Status.PENDING, now.minusDays(5).minusHours(19)));
        jobApplications.add(new JobApplication(jobPostings.get(18), applicants.get(8), JobApplication.Status.SHORTLISTED, now.minusDays(4).minusHours(20)));
        jobApplications.add(new JobApplication(jobPostings.get(23), applicants.get(8), JobApplication.Status.REJECTED, now.minusDays(3).minusHours(21)));
        jobApplications.add(new JobApplication(jobPostings.get(28), applicants.get(8), JobApplication.Status.WITHDRAWN, now.minusDays(2).minusHours(22)));

        // Applicant 9 (Julia Anderson)
        jobApplications.add(new JobApplication(jobPostings.get(9), applicants.get(9), JobApplication.Status.OFFERED, now.minusDays(1).minusHours(23)));
        jobApplications.add(new JobApplication(jobPostings.get(14), applicants.get(9), JobApplication.Status.ACCEPTED, now.minusDays(0).minusHours(24)));
        jobApplications.add(new JobApplication(jobPostings.get(19), applicants.get(9), JobApplication.Status.INTERVIEWED, now.minusDays(7).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(24), applicants.get(9), JobApplication.Status.PENDING, now.minusDays(6).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(29), applicants.get(9), JobApplication.Status.SHORTLISTED, now.minusDays(5).minusHours(3)));

        // Applicant 10 (Kevin White)
        jobApplications.add(new JobApplication(jobPostings.get(10), applicants.get(10), JobApplication.Status.REJECTED, now.minusDays(4).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(10), JobApplication.Status.WITHDRAWN, now.minusDays(3).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(10), JobApplication.Status.OFFERED, now.minusDays(2).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(10), JobApplication.Status.ACCEPTED, now.minusDays(1).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(10), JobApplication.Status.INTERVIEWED, now.minusDays(0).minusHours(8)));

        // Applicant 11 (Laura Martinez)
        jobApplications.add(new JobApplication(jobPostings.get(11), applicants.get(11), JobApplication.Status.PENDING, now.minusDays(7).minusHours(9)));
        jobApplications.add(new JobApplication(jobPostings.get(16), applicants.get(11), JobApplication.Status.SHORTLISTED, now.minusDays(6).minusHours(10)));
        jobApplications.add(new JobApplication(jobPostings.get(21), applicants.get(11), JobApplication.Status.REJECTED, now.minusDays(5).minusHours(11)));
        jobApplications.add(new JobApplication(jobPostings.get(26), applicants.get(11), JobApplication.Status.WITHDRAWN, now.minusDays(4).minusHours(12)));
        jobApplications.add(new JobApplication(jobPostings.get(31), applicants.get(11), JobApplication.Status.OFFERED, now.minusDays(3).minusHours(13)));

        // Applicant 12 (Mike Brown)
        jobApplications.add(new JobApplication(jobPostings.get(12), applicants.get(12), JobApplication.Status.ACCEPTED, now.minusDays(2).minusHours(14)));
        jobApplications.add(new JobApplication(jobPostings.get(17), applicants.get(12), JobApplication.Status.INTERVIEWED, now.minusDays(1).minusHours(15)));
        jobApplications.add(new JobApplication(jobPostings.get(22), applicants.get(12), JobApplication.Status.PENDING, now.minusDays(0).minusHours(16)));
        jobApplications.add(new JobApplication(jobPostings.get(27), applicants.get(12), JobApplication.Status.SHORTLISTED, now.minusDays(7).minusHours(17)));
        jobApplications.add(new JobApplication(jobPostings.get(32), applicants.get(12), JobApplication.Status.REJECTED, now.minusDays(6).minusHours(18)));

        // Applicant 13 (Nina Harris)
        jobApplications.add(new JobApplication(jobPostings.get(13), applicants.get(13), JobApplication.Status.WITHDRAWN, now.minusDays(5).minusHours(19)));
        jobApplications.add(new JobApplication(jobPostings.get(18), applicants.get(13), JobApplication.Status.OFFERED, now.minusDays(4).minusHours(20)));
        jobApplications.add(new JobApplication(jobPostings.get(23), applicants.get(13), JobApplication.Status.ACCEPTED, now.minusDays(3).minusHours(21)));
        jobApplications.add(new JobApplication(jobPostings.get(28), applicants.get(13), JobApplication.Status.INTERVIEWED, now.minusDays(2).minusHours(22)));
        jobApplications.add(new JobApplication(jobPostings.get(33), applicants.get(13), JobApplication.Status.PENDING, now.minusDays(1).minusHours(23)));

        // Applicant 14 (Oliver Clark)
        jobApplications.add(new JobApplication(jobPostings.get(14), applicants.get(14), JobApplication.Status.SHORTLISTED, now.minusDays(0).minusHours(24)));
        jobApplications.add(new JobApplication(jobPostings.get(19), applicants.get(14), JobApplication.Status.REJECTED, now.minusDays(7).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(24), applicants.get(14), JobApplication.Status.WITHDRAWN, now.minusDays(6).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(29), applicants.get(14), JobApplication.Status.OFFERED, now.minusDays(5).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(34), applicants.get(14), JobApplication.Status.ACCEPTED, now.minusDays(4).minusHours(4)));

        // Applicant 15 (Pamela Lewis)
        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(15), JobApplication.Status.INTERVIEWED, now.minusDays(3).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(15), JobApplication.Status.PENDING, now.minusDays(2).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(15), JobApplication.Status.SHORTLISTED, now.minusDays(1).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(15), JobApplication.Status.REJECTED, now.minusDays(0).minusHours(8)));
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(15), JobApplication.Status.WITHDRAWN, now.minusDays(7).minusHours(9)));

        // Applicant 16 (Quincy Adams)
        jobApplications.add(new JobApplication(jobPostings.get(16), applicants.get(16), JobApplication.Status.OFFERED, now.minusDays(6).minusHours(10)));
        jobApplications.add(new JobApplication(jobPostings.get(21), applicants.get(16), JobApplication.Status.ACCEPTED, now.minusDays(5).minusHours(11)));
        jobApplications.add(new JobApplication(jobPostings.get(26), applicants.get(16), JobApplication.Status.INTERVIEWED, now.minusDays(4).minusHours(12)));
        jobApplications.add(new JobApplication(jobPostings.get(31), applicants.get(16), JobApplication.Status.PENDING, now.minusDays(3).minusHours(13)));
        jobApplications.add(new JobApplication(jobPostings.get(36), applicants.get(16), JobApplication.Status.SHORTLISTED, now.minusDays(2).minusHours(14)));

        // Applicant 17 (Rachel Green)
        jobApplications.add(new JobApplication(jobPostings.get(17), applicants.get(17), JobApplication.Status.REJECTED, now.minusDays(1).minusHours(15)));
        jobApplications.add(new JobApplication(jobPostings.get(22), applicants.get(17), JobApplication.Status.WITHDRAWN, now.minusDays(0).minusHours(16)));
        jobApplications.add(new JobApplication(jobPostings.get(27), applicants.get(17), JobApplication.Status.OFFERED, now.minusDays(7).minusHours(17)));
        jobApplications.add(new JobApplication(jobPostings.get(32), applicants.get(17), JobApplication.Status.ACCEPTED, now.minusDays(6).minusHours(18)));
        jobApplications.add(new JobApplication(jobPostings.get(37), applicants.get(17), JobApplication.Status.INTERVIEWED, now.minusDays(5).minusHours(19)));

        // Applicant 18 (Sam Evans)
        jobApplications.add(new JobApplication(jobPostings.get(18), applicants.get(18), JobApplication.Status.PENDING, now.minusDays(4).minusHours(20)));
        jobApplications.add(new JobApplication(jobPostings.get(23), applicants.get(18), JobApplication.Status.SHORTLISTED, now.minusDays(3).minusHours(21)));
        jobApplications.add(new JobApplication(jobPostings.get(28), applicants.get(18), JobApplication.Status.REJECTED, now.minusDays(2).minusHours(22)));
        jobApplications.add(new JobApplication(jobPostings.get(33), applicants.get(18), JobApplication.Status.WITHDRAWN, now.minusDays(1).minusHours(23)));
        jobApplications.add(new JobApplication(jobPostings.get(38), applicants.get(18), JobApplication.Status.OFFERED, now.minusDays(0).minusHours(24)));

        // Applicant 19 (Tina Walker)
        jobApplications.add(new JobApplication(jobPostings.get(19), applicants.get(19), JobApplication.Status.ACCEPTED, now.minusDays(7).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(19), applicants.get(19), JobApplication.Status.ACCEPTED, now.minusDays(7).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(29), applicants.get(19), JobApplication.Status.PENDING, now.minusDays(5).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(34), applicants.get(19), JobApplication.Status.SHORTLISTED, now.minusDays(4).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(39), applicants.get(19), JobApplication.Status.REJECTED, now.minusDays(3).minusHours(5)));

        // Applicant 20 (Umar Khan)
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(20), JobApplication.Status.WITHDRAWN, now.minusDays(2).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(20), JobApplication.Status.OFFERED, now.minusDays(1).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(20), JobApplication.Status.ACCEPTED, now.minusDays(0).minusHours(8)));
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(20), JobApplication.Status.INTERVIEWED, now.minusDays(7).minusHours(9)));
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(20), JobApplication.Status.PENDING, now.minusDays(6).minusHours(10)));

        // Applicant 21 (Vera Scott)
        jobApplications.add(new JobApplication(jobPostings.get(21), applicants.get(21), JobApplication.Status.SHORTLISTED, now.minusDays(5).minusHours(11)));
        jobApplications.add(new JobApplication(jobPostings.get(26), applicants.get(21), JobApplication.Status.REJECTED, now.minusDays(4).minusHours(12)));
        jobApplications.add(new JobApplication(jobPostings.get(31), applicants.get(21), JobApplication.Status.WITHDRAWN, now.minusDays(3).minusHours(13)));
        jobApplications.add(new JobApplication(jobPostings.get(36), applicants.get(21), JobApplication.Status.OFFERED, now.minusDays(2).minusHours(14)));
        jobApplications.add(new JobApplication(jobPostings.get(41), applicants.get(21), JobApplication.Status.ACCEPTED, now.minusDays(1).minusHours(15)));

        // Applicant 22 (Will Turner)
        jobApplications.add(new JobApplication(jobPostings.get(22), applicants.get(22), JobApplication.Status.INTERVIEWED, now.minusDays(0).minusHours(16)));
        jobApplications.add(new JobApplication(jobPostings.get(27), applicants.get(22), JobApplication.Status.PENDING, now.minusDays(7).minusHours(17)));
        jobApplications.add(new JobApplication(jobPostings.get(32), applicants.get(22), JobApplication.Status.SHORTLISTED, now.minusDays(6).minusHours(18)));
        jobApplications.add(new JobApplication(jobPostings.get(37), applicants.get(22), JobApplication.Status.REJECTED, now.minusDays(5).minusHours(19)));
        jobApplications.add(new JobApplication(jobPostings.get(42), applicants.get(22), JobApplication.Status.WITHDRAWN, now.minusDays(4).minusHours(20)));

        // Applicant 23 (Xena Brooks)
        jobApplications.add(new JobApplication(jobPostings.get(23), applicants.get(23), JobApplication.Status.OFFERED, now.minusDays(3).minusHours(21)));
        jobApplications.add(new JobApplication(jobPostings.get(28), applicants.get(23), JobApplication.Status.ACCEPTED, now.minusDays(2).minusHours(22)));
        jobApplications.add(new JobApplication(jobPostings.get(33), applicants.get(23), JobApplication.Status.INTERVIEWED, now.minusDays(1).minusHours(23)));
        jobApplications.add(new JobApplication(jobPostings.get(38), applicants.get(23), JobApplication.Status.PENDING, now.minusDays(0).minusHours(24)));
        jobApplications.add(new JobApplication(jobPostings.get(43), applicants.get(23), JobApplication.Status.SHORTLISTED, now.minusDays(7).minusHours(1)));

        // Applicant 24 (Yara King)
        jobApplications.add(new JobApplication(jobPostings.get(24), applicants.get(24), JobApplication.Status.REJECTED, now.minusDays(6).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(29), applicants.get(24), JobApplication.Status.WITHDRAWN, now.minusDays(5).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(34), applicants.get(24), JobApplication.Status.OFFERED, now.minusDays(4).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(39), applicants.get(24), JobApplication.Status.ACCEPTED, now.minusDays(3).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(44), applicants.get(24), JobApplication.Status.INTERVIEWED, now.minusDays(2).minusHours(6)));

        // Applicant 25 (Zack Miller)
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(25), JobApplication.Status.PENDING, now.minusDays(1).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(25), JobApplication.Status.SHORTLISTED, now.minusDays(0).minusHours(8)));
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(25), JobApplication.Status.REJECTED, now.minusDays(7).minusHours(9)));
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(25), JobApplication.Status.WITHDRAWN, now.minusDays(6).minusHours(10)));
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(25), JobApplication.Status.OFFERED, now.minusDays(5).minusHours(11)));

        // Applicant 26 (Amy Carter)
        jobApplications.add(new JobApplication(jobPostings.get(26), applicants.get(26), JobApplication.Status.ACCEPTED, now.minusDays(7).minusHours(12)));
        jobApplications.add(new JobApplication(jobPostings.get(31), applicants.get(26), JobApplication.Status.INTERVIEWED, now.minusDays(6).minusHours(13)));
        jobApplications.add(new JobApplication(jobPostings.get(36), applicants.get(26), JobApplication.Status.PENDING, now.minusDays(5).minusHours(14)));
        jobApplications.add(new JobApplication(jobPostings.get(41), applicants.get(26), JobApplication.Status.SHORTLISTED, now.minusDays(4).minusHours(15)));
        jobApplications.add(new JobApplication(jobPostings.get(46), applicants.get(26), JobApplication.Status.REJECTED, now.minusDays(3).minusHours(16)));

        // Applicant 27 (Ben Foster)
        jobApplications.add(new JobApplication(jobPostings.get(27), applicants.get(27), JobApplication.Status.WITHDRAWN, now.minusDays(2).minusHours(17)));
        jobApplications.add(new JobApplication(jobPostings.get(32), applicants.get(27), JobApplication.Status.OFFERED, now.minusDays(1).minusHours(18)));
        jobApplications.add(new JobApplication(jobPostings.get(37), applicants.get(27), JobApplication.Status.ACCEPTED, now.minusDays(0).minusHours(19)));
        jobApplications.add(new JobApplication(jobPostings.get(42), applicants.get(27), JobApplication.Status.INTERVIEWED, now.minusDays(7).minusHours(20)));
        jobApplications.add(new JobApplication(jobPostings.get(47), applicants.get(27), JobApplication.Status.PENDING, now.minusDays(6).minusHours(21)));

        // Applicant 28 (Clara Hayes)
        jobApplications.add(new JobApplication(jobPostings.get(28), applicants.get(28), JobApplication.Status.SHORTLISTED, now.minusDays(5).minusHours(22)));
        jobApplications.add(new JobApplication(jobPostings.get(33), applicants.get(28), JobApplication.Status.REJECTED, now.minusDays(4).minusHours(23)));
        jobApplications.add(new JobApplication(jobPostings.get(38), applicants.get(28), JobApplication.Status.WITHDRAWN, now.minusDays(3).minusHours(24)));
        jobApplications.add(new JobApplication(jobPostings.get(43), applicants.get(28), JobApplication.Status.OFFERED, now.minusDays(2).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(48), applicants.get(28), JobApplication.Status.ACCEPTED, now.minusDays(1).minusHours(2)));

        // Applicant 29 (Dan Price)
        jobApplications.add(new JobApplication(jobPostings.get(29), applicants.get(29), JobApplication.Status.INTERVIEWED, now.minusDays(0).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(34), applicants.get(29), JobApplication.Status.PENDING, now.minusDays(7).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(39), applicants.get(29), JobApplication.Status.SHORTLISTED, now.minusDays(6).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(44), applicants.get(29), JobApplication.Status.REJECTED, now.minusDays(5).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(49), applicants.get(29), JobApplication.Status.WITHDRAWN, now.minusDays(4).minusHours(7)));

        // Applicant 30 (Ella Reed)
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(30), JobApplication.Status.OFFERED, now.minusDays(3).minusHours(8)));
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(30), JobApplication.Status.ACCEPTED, now.minusDays(2).minusHours(9)));
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(30), JobApplication.Status.INTERVIEWED, now.minusDays(1).minusHours(10)));
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(30), JobApplication.Status.PENDING, now.minusDays(0).minusHours(11)));
        jobApplications.add(new JobApplication(jobPostings.get(50), applicants.get(30), JobApplication.Status.SHORTLISTED, now.minusDays(7).minusHours(12)));

        // Applicant 31 (Finn Cole)
        jobApplications.add(new JobApplication(jobPostings.get(31), applicants.get(31), JobApplication.Status.REJECTED, now.minusDays(6).minusHours(13)));
        jobApplications.add(new JobApplication(jobPostings.get(36), applicants.get(31), JobApplication.Status.WITHDRAWN, now.minusDays(5).minusHours(14)));
        jobApplications.add(new JobApplication(jobPostings.get(41), applicants.get(31), JobApplication.Status.OFFERED, now.minusDays(4).minusHours(15)));
        jobApplications.add(new JobApplication(jobPostings.get(46), applicants.get(31), JobApplication.Status.ACCEPTED, now.minusDays(3).minusHours(16)));
        jobApplications.add(new JobApplication(jobPostings.get(51), applicants.get(31), JobApplication.Status.INTERVIEWED, now.minusDays(2).minusHours(17)));

        // Applicant 32 (Gina Bell)
        jobApplications.add(new JobApplication(jobPostings.get(32), applicants.get(32), JobApplication.Status.PENDING, now.minusDays(1).minusHours(18)));
        jobApplications.add(new JobApplication(jobPostings.get(37), applicants.get(32), JobApplication.Status.SHORTLISTED, now.minusDays(0).minusHours(19)));
        jobApplications.add(new JobApplication(jobPostings.get(42), applicants.get(32), JobApplication.Status.REJECTED, now.minusDays(7).minusHours(20)));
        jobApplications.add(new JobApplication(jobPostings.get(47), applicants.get(32), JobApplication.Status.WITHDRAWN, now.minusDays(6).minusHours(21)));
        jobApplications.add(new JobApplication(jobPostings.get(52), applicants.get(32), JobApplication.Status.OFFERED, now.minusDays(5).minusHours(22)));

        // Applicant 33 (Hank Ford)
        jobApplications.add(new JobApplication(jobPostings.get(33), applicants.get(33), JobApplication.Status.ACCEPTED, now.minusDays(4).minusHours(23)));
        jobApplications.add(new JobApplication(jobPostings.get(38), applicants.get(33), JobApplication.Status.INTERVIEWED, now.minusDays(3).minusHours(24)));
        jobApplications.add(new JobApplication(jobPostings.get(43), applicants.get(33), JobApplication.Status.PENDING, now.minusDays(2).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(48), applicants.get(33), JobApplication.Status.SHORTLISTED, now.minusDays(1).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(53), applicants.get(33), JobApplication.Status.REJECTED, now.minusDays(0).minusHours(3)));

        // Applicant 34 (Iris Lane)
        jobApplications.add(new JobApplication(jobPostings.get(34), applicants.get(34), JobApplication.Status.WITHDRAWN, now.minusDays(7).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(39), applicants.get(34), JobApplication.Status.OFFERED, now.minusDays(6).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(44), applicants.get(34), JobApplication.Status.ACCEPTED, now.minusDays(5).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(49), applicants.get(34), JobApplication.Status.INTERVIEWED, now.minusDays(4).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(54), applicants.get(34), JobApplication.Status.PENDING, now.minusDays(3).minusHours(8)));

        // Applicant 35 (Jack Gray)
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(35), JobApplication.Status.SHORTLISTED, now.minusDays(2).minusHours(9)));
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(35), JobApplication.Status.REJECTED, now.minusDays(1).minusHours(10)));
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(35), JobApplication.Status.WITHDRAWN, now.minusDays(0).minusHours(11)));
        jobApplications.add(new JobApplication(jobPostings.get(50), applicants.get(35), JobApplication.Status.OFFERED, now.minusDays(7).minusHours(12)));
        jobApplications.add(new JobApplication(jobPostings.get(55), applicants.get(35), JobApplication.Status.ACCEPTED, now.minusDays(6).minusHours(13)));

        // Applicant 36 (Kara Hill)
        jobApplications.add(new JobApplication(jobPostings.get(36), applicants.get(36), JobApplication.Status.INTERVIEWED, now.minusDays(5).minusHours(14)));
        jobApplications.add(new JobApplication(jobPostings.get(41), applicants.get(36), JobApplication.Status.PENDING, now.minusDays(4).minusHours(15)));
        jobApplications.add(new JobApplication(jobPostings.get(46), applicants.get(36), JobApplication.Status.SHORTLISTED, now.minusDays(3).minusHours(16)));
        jobApplications.add(new JobApplication(jobPostings.get(51), applicants.get(36), JobApplication.Status.REJECTED, now.minusDays(2).minusHours(17)));
        jobApplications.add(new JobApplication(jobPostings.get(56), applicants.get(36), JobApplication.Status.WITHDRAWN, now.minusDays(1).minusHours(18)));

        // Applicant 37 (Liam Ward)
        jobApplications.add(new JobApplication(jobPostings.get(37), applicants.get(37), JobApplication.Status.OFFERED, now.minusDays(0).minusHours(19)));
        jobApplications.add(new JobApplication(jobPostings.get(42), applicants.get(37), JobApplication.Status.ACCEPTED, now.minusDays(7).minusHours(20)));
        jobApplications.add(new JobApplication(jobPostings.get(47), applicants.get(37), JobApplication.Status.INTERVIEWED, now.minusDays(6).minusHours(21)));
        jobApplications.add(new JobApplication(jobPostings.get(52), applicants.get(37), JobApplication.Status.PENDING, now.minusDays(5).minusHours(22)));
        jobApplications.add(new JobApplication(jobPostings.get(57), applicants.get(37), JobApplication.Status.SHORTLISTED, now.minusDays(4).minusHours(23)));

        // Applicant 38 (Maya Ross)
        jobApplications.add(new JobApplication(jobPostings.get(38), applicants.get(38), JobApplication.Status.REJECTED, now.minusDays(3).minusHours(24)));
        jobApplications.add(new JobApplication(jobPostings.get(43), applicants.get(38), JobApplication.Status.WITHDRAWN, now.minusDays(2).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(48), applicants.get(38), JobApplication.Status.OFFERED, now.minusDays(1).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(53), applicants.get(38), JobApplication.Status.ACCEPTED, now.minusDays(0).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(58), applicants.get(38), JobApplication.Status.INTERVIEWED, now.minusDays(7).minusHours(4)));

        // Applicant 39 (Nate Fox)
        jobApplications.add(new JobApplication(jobPostings.get(39), applicants.get(39), JobApplication.Status.PENDING, now.minusDays(6).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(44), applicants.get(39), JobApplication.Status.SHORTLISTED, now.minusDays(5).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(49), applicants.get(39), JobApplication.Status.REJECTED, now.minusDays(4).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(54), applicants.get(39), JobApplication.Status.WITHDRAWN, now.minusDays(3).minusHours(8)));
        jobApplications.add(new JobApplication(jobPostings.get(59), applicants.get(39), JobApplication.Status.OFFERED, now.minusDays(2).minusHours(9)));

        // Applicant 40 (Opal Dean)
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(40), JobApplication.Status.ACCEPTED, now.minusDays(1).minusHours(10)));
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(40), JobApplication.Status.INTERVIEWED, now.minusDays(0).minusHours(11)));
        jobApplications.add(new JobApplication(jobPostings.get(50), applicants.get(40), JobApplication.Status.PENDING, now.minusDays(7).minusHours(12)));
        jobApplications.add(new JobApplication(jobPostings.get(55), applicants.get(40), JobApplication.Status.SHORTLISTED, now.minusDays(6).minusHours(13)));
        jobApplications.add(new JobApplication(jobPostings.get(60), applicants.get(40), JobApplication.Status.REJECTED, now.minusDays(5).minusHours(14)));

        // Applicant 41 (Paul Hart)
        jobApplications.add(new JobApplication(jobPostings.get(41), applicants.get(41), JobApplication.Status.WITHDRAWN, now.minusDays(4).minusHours(15)));
        jobApplications.add(new JobApplication(jobPostings.get(46), applicants.get(41), JobApplication.Status.OFFERED, now.minusDays(3).minusHours(16)));
        jobApplications.add(new JobApplication(jobPostings.get(51), applicants.get(41), JobApplication.Status.ACCEPTED, now.minusDays(2).minusHours(17)));
        jobApplications.add(new JobApplication(jobPostings.get(56), applicants.get(41), JobApplication.Status.INTERVIEWED, now.minusDays(1).minusHours(18)));
        jobApplications.add(new JobApplication(jobPostings.get(61), applicants.get(41), JobApplication.Status.PENDING, now.minusDays(0).minusHours(19)));

        // Applicant 42 (Quinn Cole)
        jobApplications.add(new JobApplication(jobPostings.get(42), applicants.get(42), JobApplication.Status.SHORTLISTED, now.minusDays(7).minusHours(20)));
        jobApplications.add(new JobApplication(jobPostings.get(47), applicants.get(42), JobApplication.Status.REJECTED, now.minusDays(6).minusHours(21)));
        jobApplications.add(new JobApplication(jobPostings.get(52), applicants.get(42), JobApplication.Status.WITHDRAWN, now.minusDays(5).minusHours(22)));
        jobApplications.add(new JobApplication(jobPostings.get(57), applicants.get(42), JobApplication.Status.OFFERED, now.minusDays(4).minusHours(23)));
        jobApplications.add(new JobApplication(jobPostings.get(62), applicants.get(42), JobApplication.Status.ACCEPTED, now.minusDays(3).minusHours(24)));

        // Applicant 43 (Rita Lane)
        jobApplications.add(new JobApplication(jobPostings.get(43), applicants.get(43), JobApplication.Status.INTERVIEWED, now.minusDays(2).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(48), applicants.get(43), JobApplication.Status.PENDING, now.minusDays(1).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(53), applicants.get(43), JobApplication.Status.SHORTLISTED, now.minusDays(0).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(58), applicants.get(43), JobApplication.Status.REJECTED, now.minusDays(7).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(63), applicants.get(43), JobApplication.Status.WITHDRAWN, now.minusDays(6).minusHours(5)));

        // Applicant 44 (Seth Owen)
        jobApplications.add(new JobApplication(jobPostings.get(44), applicants.get(44), JobApplication.Status.OFFERED, now.minusDays(5).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(49), applicants.get(44), JobApplication.Status.ACCEPTED, now.minusDays(4).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(54), applicants.get(44), JobApplication.Status.INTERVIEWED, now.minusDays(3).minusHours(8)));
        jobApplications.add(new JobApplication(jobPostings.get(59), applicants.get(44), JobApplication.Status.PENDING, now.minusDays(2).minusHours(9)));
        jobApplications.add(new JobApplication(jobPostings.get(64), applicants.get(44), JobApplication.Status.SHORTLISTED, now.minusDays(1).minusHours(10)));

        // Applicant 45 (Tara Bell)
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(45), JobApplication.Status.REJECTED, now.minusDays(0).minusHours(11)));
        jobApplications.add(new JobApplication(jobPostings.get(50), applicants.get(45), JobApplication.Status.WITHDRAWN, now.minusDays(7).minusHours(12)));
        jobApplications.add(new JobApplication(jobPostings.get(55), applicants.get(45), JobApplication.Status.OFFERED, now.minusDays(6).minusHours(13)));
        jobApplications.add(new JobApplication(jobPostings.get(60), applicants.get(45), JobApplication.Status.ACCEPTED, now.minusDays(5).minusHours(14)));
        jobApplications.add(new JobApplication(jobPostings.get(65), applicants.get(45), JobApplication.Status.INTERVIEWED, now.minusDays(4).minusHours(15)));

        // Applicant 46 (Uma West)
        jobApplications.add(new JobApplication(jobPostings.get(46), applicants.get(46), JobApplication.Status.PENDING, now.minusDays(3).minusHours(16)));
        jobApplications.add(new JobApplication(jobPostings.get(51), applicants.get(46), JobApplication.Status.SHORTLISTED, now.minusDays(2).minusHours(17)));
        jobApplications.add(new JobApplication(jobPostings.get(56), applicants.get(46), JobApplication.Status.REJECTED, now.minusDays(1).minusHours(18)));
        jobApplications.add(new JobApplication(jobPostings.get(61), applicants.get(46), JobApplication.Status.WITHDRAWN, now.minusDays(0).minusHours(19)));
        jobApplications.add(new JobApplication(jobPostings.get(66), applicants.get(46), JobApplication.Status.OFFERED, now.minusDays(7).minusHours(20)));

        // Applicant 47 (Vince Ray)
        jobApplications.add(new JobApplication(jobPostings.get(47), applicants.get(47), JobApplication.Status.ACCEPTED, now.minusDays(6).minusHours(21)));
        jobApplications.add(new JobApplication(jobPostings.get(52), applicants.get(47), JobApplication.Status.INTERVIEWED, now.minusDays(5).minusHours(22)));
        jobApplications.add(new JobApplication(jobPostings.get(57), applicants.get(47), JobApplication.Status.PENDING, now.minusDays(4).minusHours(23)));
        jobApplications.add(new JobApplication(jobPostings.get(62), applicants.get(47), JobApplication.Status.SHORTLISTED, now.minusDays(3).minusHours(24)));
        jobApplications.add(new JobApplication(jobPostings.get(67), applicants.get(47), JobApplication.Status.REJECTED, now.minusDays(2).minusHours(1)));

        // Applicant 48 (Wendy Fox)
        jobApplications.add(new JobApplication(jobPostings.get(48), applicants.get(48), JobApplication.Status.WITHDRAWN, now.minusDays(1).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(53), applicants.get(48), JobApplication.Status.OFFERED, now.minusDays(0).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(58), applicants.get(48), JobApplication.Status.ACCEPTED, now.minusDays(7).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(63), applicants.get(48), JobApplication.Status.INTERVIEWED, now.minusDays(6).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(68), applicants.get(48), JobApplication.Status.PENDING, now.minusDays(5).minusHours(6)));

        // Applicant 49 (Xander Lee)
        jobApplications.add(new JobApplication(jobPostings.get(49), applicants.get(49), JobApplication.Status.SHORTLISTED, now.minusDays(4).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(54), applicants.get(49), JobApplication.Status.REJECTED, now.minusDays(3).minusHours(8)));
        jobApplications.add(new JobApplication(jobPostings.get(59), applicants.get(49), JobApplication.Status.WITHDRAWN, now.minusDays(2).minusHours(9)));
        jobApplications.add(new JobApplication(jobPostings.get(64), applicants.get(49), JobApplication.Status.OFFERED, now.minusDays(1).minusHours(10)));
        jobApplications.add(new JobApplication(jobPostings.get(69), applicants.get(49), JobApplication.Status.ACCEPTED, now.minusDays(0).minusHours(11)));

        // Applicant 0
        jobApplications.add(new JobApplication(jobPostings.get(0), applicants.get(0), JobApplication.Status.SHORTLISTED, LocalDateTime.of(2025, 8, 6, 9, 0)));
        jobApplications.add(new JobApplication(jobPostings.get(5), applicants.get(0), JobApplication.Status.SHORTLISTED, LocalDateTime.of(2025, 8, 7, 10, 0)));
        jobApplications.add(new JobApplication(jobPostings.get(10), applicants.get(0), JobApplication.Status.SHORTLISTED, LocalDateTime.of(2025, 8, 8, 11, 0)));
        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(0), JobApplication.Status.SHORTLISTED, LocalDateTime.of(2025, 8, 9, 12, 0)));
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(0), JobApplication.Status.SHORTLISTED, LocalDateTime.of(2025, 8, 10, 13, 0)));

        // Applicant 1
        jobApplications.add(new JobApplication(jobPostings.get(0), applicants.get(1), JobApplication.Status.SHORTLISTED, now.minusDays(7).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(5), applicants.get(1), JobApplication.Status.SHORTLISTED, now.minusDays(6).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(10), applicants.get(1), JobApplication.Status.SHORTLISTED, now.minusDays(5).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(1), JobApplication.Status.SHORTLISTED, now.minusDays(4).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(1), JobApplication.Status.SHORTLISTED, now.minusDays(3).minusHours(5)));

        // Applicant 2
        jobApplications.add(new JobApplication(jobPostings.get(0), applicants.get(2), JobApplication.Status.SHORTLISTED, now.minusDays(2).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(5), applicants.get(2), JobApplication.Status.SHORTLISTED, now.minusDays(1).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(10), applicants.get(2), JobApplication.Status.SHORTLISTED, now.minusDays(0).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(2), JobApplication.Status.SHORTLISTED, now.minusDays(3).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(2), JobApplication.Status.SHORTLISTED, now.minusDays(2).minusHours(3)));

        // Applicant 3
        jobApplications.add(new JobApplication(jobPostings.get(0), applicants.get(3), JobApplication.Status.SHORTLISTED, now.minusDays(6).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(5), applicants.get(3), JobApplication.Status.SHORTLISTED, now.minusDays(5).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(10), applicants.get(3), JobApplication.Status.SHORTLISTED, now.minusDays(4).minusHours(0)));
        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(3), JobApplication.Status.SHORTLISTED, now.minusDays(3).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(3), JobApplication.Status.SHORTLISTED, now.minusDays(2).minusHours(5)));

        // Applicant 4
        jobApplications.add(new JobApplication(jobPostings.get(0), applicants.get(4), JobApplication.Status.SHORTLISTED, now.minusDays(1).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(5), applicants.get(4), JobApplication.Status.SHORTLISTED, now.minusDays(0).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(10), applicants.get(4), JobApplication.Status.SHORTLISTED, now.minusDays(7).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(4), JobApplication.Status.SHORTLISTED, now.minusDays(6).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(4), JobApplication.Status.SHORTLISTED, now.minusDays(5).minusHours(0)));

        // Applicant 5
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(5), JobApplication.Status.SHORTLISTED, now.minusDays(4).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(5), JobApplication.Status.SHORTLISTED, now.minusDays(3).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(5), JobApplication.Status.SHORTLISTED, now.minusDays(2).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(5), JobApplication.Status.SHORTLISTED, now.minusDays(1).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(5), JobApplication.Status.SHORTLISTED, now.minusDays(0).minusHours(3)));

        // Applicant 6
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(6), JobApplication.Status.SHORTLISTED, now.minusDays(7).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(6), JobApplication.Status.SHORTLISTED, now.minusDays(6).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(6), JobApplication.Status.SHORTLISTED, now.minusDays(5).minusHours(0)));
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(6), JobApplication.Status.SHORTLISTED, now.minusDays(4).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(6), JobApplication.Status.SHORTLISTED, now.minusDays(3).minusHours(5)));

        // Applicant 7
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(7), JobApplication.Status.SHORTLISTED, now.minusDays(2).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(7), JobApplication.Status.SHORTLISTED, now.minusDays(1).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(7), JobApplication.Status.SHORTLISTED, now.minusDays(0).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(7), JobApplication.Status.SHORTLISTED, now.minusDays(7).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(7), JobApplication.Status.SHORTLISTED, now.minusDays(6).minusHours(0)));

        // Applicant 8
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(8), JobApplication.Status.SHORTLISTED, now.minusDays(5).minusHours(7)));
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(8), JobApplication.Status.SHORTLISTED, now.minusDays(4).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(8), JobApplication.Status.SHORTLISTED, now.minusDays(3).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(8), JobApplication.Status.SHORTLISTED, now.minusDays(2).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(8), JobApplication.Status.SHORTLISTED, now.minusDays(1).minusHours(3)));

        // Applicant 9
        jobApplications.add(new JobApplication(jobPostings.get(25), applicants.get(9), JobApplication.Status.SHORTLISTED, now.minusDays(0).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(30), applicants.get(9), JobApplication.Status.SHORTLISTED, now.minusDays(7).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(35), applicants.get(9), JobApplication.Status.SHORTLISTED, now.minusDays(6).minusHours(0)));
        jobApplications.add(new JobApplication(jobPostings.get(40), applicants.get(9), JobApplication.Status.SHORTLISTED, now.minusDays(5).minusHours(6)));
        jobApplications.add(new JobApplication(jobPostings.get(45), applicants.get(9), JobApplication.Status.SHORTLISTED, now.minusDays(4).minusHours(5)));
        // endregion

        // 84 record
        ListInterface<JobApplication> shortlistedApplications = jobApplications.filter(app -> app.getStatus() == JobApplication.Status.SHORTLISTED);
        // 34 record
        ListInterface<JobApplication> interviewedApplications = jobApplications.filter(app -> app.getStatus() == JobApplication.Status.INTERVIEWED);
        // 36 record
        ListInterface<JobApplication> offeredApplications = jobApplications.filter(app -> app.getStatus() == JobApplication.Status.OFFERED);
        // 107 record
        ListInterface<JobApplication> terminatedApplications = jobApplications.filter(JobApplication::isTerminated);

        //        System.out.println(shortlistedApplications.size());
        //        System.out.println(interviewedApplications.size());
        //        System.out.println(offeredApplications.size());
        //        System.out.println(terminatedApplications.size());

        //        past or future interviews only refer to the current application's interview, doesn't affect other application
        //        PENDING, no interview at all
        //        SHORTLISTED, must have no past interview, future interview optional
        //        INTERVIEWED, must have past interview, future interview optional
        //        OFFERED, past interview optional, must have no future interview
        //        ACCEPTED, past interview optional, must have no future interview
        //        REJECTED, past interview optional, must have no future interview
        //        WITHDRAWN, past interview optional, must have no future interview

        // INTERVIEWED (34 records, must have past interviews, future optional)
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(2), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(2), now.minusDays(3).minusHours(5)), 8, now.minusDays(3).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(12), "Behavioral interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(12), now.minusDays(2).minusHours(4)), 7, now.minusDays(2).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(19), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(19), now.minusDays(4).minusHours(6)), 9, now.minusDays(4).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(26), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(26), now.minusDays(1).minusHours(3)), 6, now.minusDays(1).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(33), "Technical discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(33), now.minusDays(5).minusHours(7)), 8, now.minusDays(5).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(40), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(40), now.minusDays(2).minusHours(2)), 7, now.minusDays(2).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(47), "HR interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(47), now.minusDays(3).minusHours(8)), 9, now.minusDays(3).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(54), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(54), now.minusDays(4).minusHours(1)), 6, now.minusDays(4).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(61), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(61), now.minusDays(1).minusHours(9)), 8, now.minusDays(1).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(68), "Coding challenge", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(68), now.minusDays(5).minusHours(4)), 7, now.minusDays(5).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(75), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(75), now.minusDays(2).minusHours(5)), 9, now.minusDays(2).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(82), "Behavioral interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(82), now.minusDays(3).minusHours(6)), 6, now.minusDays(3).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(89), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(89), now.minusDays(4).minusHours(7)), 8, now.minusDays(4).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(103), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(103), now.minusDays(1).minusHours(8)), 7, now.minusDays(1).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(110), "HR interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(110), now.minusDays(5).minusHours(9)), 9, now.minusDays(5).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(117), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(117), now.minusDays(2).minusHours(1)), 6, now.minusDays(2).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(124), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(124), now.minusDays(3).minusHours(2)), 8, now.minusDays(3).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(131), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(131), now.minusDays(4).minusHours(3)), 7, now.minusDays(4).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(138), "Technical discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(138), now.minusDays(1).minusHours(4)), 9, now.minusDays(1).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(145), "Behavioral interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(145), now.minusDays(5).minusHours(5)), 6, now.minusDays(5).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(152), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(152), now.minusDays(2).minusHours(6)), 8, now.minusDays(2).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(159), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(159), now.minusDays(3).minusHours(7)), 7, now.minusDays(3).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(166), "HR interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(166), now.minusDays(4).minusHours(8)), 9, now.minusDays(4).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(173), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(173), now.minusDays(1).minusHours(9)), 6, now.minusDays(1).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(180), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(180), now.minusDays(5).minusHours(1)), 8, now.minusDays(5).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(187), "Coding challenge", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(187), now.minusDays(2).minusHours(2)), 7, now.minusDays(2).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(194), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(194), now.minusDays(3).minusHours(3)), 9, now.minusDays(3).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(201), "Behavioral interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(201), now.minusDays(4).minusHours(4)), 6, now.minusDays(4).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(208), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(208), now.minusDays(1).minusHours(5)), 8, now.minusDays(1).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(215), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(215), now.minusDays(5).minusHours(6)), 7, now.minusDays(5).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(222), "HR interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(222), now.minusDays(2).minusHours(7)), 9, now.minusDays(2).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(229), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(229), now.minusDays(3).minusHours(8)), 6, now.minusDays(3).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(236), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(236), now.minusDays(4).minusHours(9)), 8, now.minusDays(4).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(243), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(243), now.minusDays(1).minusHours(1)), 7, now.minusDays(1).minusHours(1)));

        // SHORTLISTED (84 records, no past interviews, future optional)
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(1), "Scheduled for technical", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(1), now.minusDays(1).minusHours(2)), now.minusDays(1).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(6), "Behavioral screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(6), now.minusDays(2).minusHours(3)), now.minusDays(2).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(14), "Coding interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(14), now.minusDays(3).minusHours(4)), now.minusDays(3).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(21), "Panel discussion", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(21), now.minusDays(4).minusHours(5)), now.minusDays(4).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(28), "Technical review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(28), now.minusDays(5).minusHours(6)), now.minusDays(5).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(35), "HR screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(35), now.minusDays(1).minusHours(7)), now.minusDays(1).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(42), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(42), now.minusDays(2).minusHours(8)), now.minusDays(2).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(49), "Case study", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(49), now.minusDays(3).minusHours(9)), now.minusDays(3).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(56), "Technical interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(56), now.minusDays(4).minusHours(1)), now.minusDays(4).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(63), "Behavioral interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(63), now.minusDays(5).minusHours(2)), now.minusDays(5).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(70), "Coding challenge", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(70), now.minusDays(1).minusHours(3)), now.minusDays(1).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(77), "Panel interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(77), now.minusDays(2).minusHours(4)), now.minusDays(2).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(84), "HR review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(84), now.minusDays(3).minusHours(5)), now.minusDays(3).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(91), "Technical screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(91), now.minusDays(4).minusHours(6)), now.minusDays(4).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(98), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(98), now.minusDays(5).minusHours(7)), now.minusDays(5).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(105), "Case study", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(105), now.minusDays(1).minusHours(8)), now.minusDays(1).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(112), "Behavioral screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(112), now.minusDays(2).minusHours(9)), now.minusDays(2).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(119), "Coding interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(119), now.minusDays(3).minusHours(1)), now.minusDays(3).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(126), "Panel discussion", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(126), now.minusDays(4).minusHours(2)), now.minusDays(4).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(133), "Technical review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(133), now.minusDays(5).minusHours(3)), now.minusDays(5).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(140), "HR screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(140), now.minusDays(1).minusHours(4)), now.minusDays(1).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(147), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(147), now.minusDays(2).minusHours(5)), now.minusDays(2).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(154), "Case study", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(154), now.minusDays(3).minusHours(6)), now.minusDays(3).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(161), "Technical interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(161), now.minusDays(4).minusHours(7)), now.minusDays(4).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(168), "Behavioral interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(168), now.minusDays(5).minusHours(8)), now.minusDays(5).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(175), "Coding challenge", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(175), now.minusDays(1).minusHours(9)), now.minusDays(1).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(182), "Panel interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(182), now.minusDays(2).minusHours(1)), now.minusDays(2).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(189), "HR review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(189), now.minusDays(3).minusHours(2)), now.minusDays(3).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(196), "Technical screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(196), now.minusDays(4).minusHours(3)), now.minusDays(4).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(203), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(203), now.minusDays(5).minusHours(4)), now.minusDays(5).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(210), "Case study", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(210), now.minusDays(1).minusHours(5)), now.minusDays(1).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(217), "Behavioral screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(217), now.minusDays(2).minusHours(6)), now.minusDays(2).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(224), "Coding interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(224), now.minusDays(3).minusHours(7)), now.minusDays(3).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(231), "Panel discussion", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(231), now.minusDays(4).minusHours(8)), now.minusDays(4).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(238), "Technical review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(238), now.minusDays(5).minusHours(9)), now.minusDays(5).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(245), "HR screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(245), now.minusDays(1).minusHours(1)), now.minusDays(1).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(250), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(250), now.minusDays(2).minusHours(2)), now.minusDays(2).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(251), "Technical interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(251), now.minusDays(3).minusHours(3)), now.minusDays(3).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(252), "Behavioral interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(252), now.minusDays(4).minusHours(4)), now.minusDays(4).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(253), "Coding challenge", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(253), now.minusDays(5).minusHours(5)), now.minusDays(5).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(254), "Panel interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(254), now.minusDays(1).minusHours(6)), now.minusDays(1).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(255), "HR review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(255), now.minusDays(2).minusHours(7)), now.minusDays(2).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(256), "Technical screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(256), now.minusDays(3).minusHours(8)), now.minusDays(3).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(257), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(257), now.minusDays(4).minusHours(9)), now.minusDays(4).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(258), "Case study", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(258), now.minusDays(5).minusHours(1)), now.minusDays(5).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(259), "Behavioral screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(259), now.minusDays(1).minusHours(2)), now.minusDays(1).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(260), "Coding interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(260), now.minusDays(2).minusHours(3)), now.minusDays(2).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(261), "Panel discussion", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(261), now.minusDays(3).minusHours(4)), now.minusDays(3).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(262), "Technical review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(262), now.minusDays(4).minusHours(5)), now.minusDays(4).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(263), "HR screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(263), now.minusDays(5).minusHours(6)), now.minusDays(5).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(264), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(264), now.minusDays(1).minusHours(7)), now.minusDays(1).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(265), "Case study", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(265), now.minusDays(2).minusHours(8)), now.minusDays(2).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(266), "Technical interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(266), now.minusDays(3).minusHours(9)), now.minusDays(3).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(267), "Behavioral interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(267), now.minusDays(4).minusHours(1)), now.minusDays(4).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(268), "Coding challenge", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(268), now.minusDays(5).minusHours(2)), now.minusDays(5).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(269), "Panel interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(269), now.minusDays(1).minusHours(3)), now.minusDays(1).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(270), "HR review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(270), now.minusDays(2).minusHours(4)), now.minusDays(2).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(271), "Technical screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(271), now.minusDays(3).minusHours(5)), now.minusDays(3).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(272), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(272), now.minusDays(4).minusHours(6)), now.minusDays(4).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(273), "Case study", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(273), now.minusDays(5).minusHours(7)), now.minusDays(5).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(274), "Behavioral screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(274), now.minusDays(1).minusHours(8)), now.minusDays(1).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(275), "Coding interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(275), now.minusDays(2).minusHours(9)), now.minusDays(2).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(276), "Panel discussion", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(276), now.minusDays(3).minusHours(1)), now.minusDays(3).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(277), "Technical review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(277), now.minusDays(4).minusHours(2)), now.minusDays(4).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(278), "HR screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(278), now.minusDays(5).minusHours(3)), now.minusDays(5).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(279), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(279), now.minusDays(1).minusHours(4)), now.minusDays(1).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(280), "Case study", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(280), now.minusDays(2).minusHours(5)), now.minusDays(2).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(281), "Technical interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(281), now.minusDays(3).minusHours(6)), now.minusDays(3).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(282), "Behavioral interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(282), now.minusDays(4).minusHours(7)), now.minusDays(4).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(283), "Coding challenge", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(283), now.minusDays(5).minusHours(8)), now.minusDays(5).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(284), "Panel interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(284), now.minusDays(1).minusHours(9)), now.minusDays(1).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(285), "HR review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(285), now.minusDays(2).minusHours(1)), now.minusDays(2).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(286), "Technical screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(286), now.minusDays(3).minusHours(2)), now.minusDays(3).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(287), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(287), now.minusDays(4).minusHours(3)), now.minusDays(4).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(288), "Case study", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(288), now.minusDays(5).minusHours(4)), now.minusDays(5).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(289), "Behavioral screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(289), now.minusDays(1).minusHours(5)), now.minusDays(1).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(290), "Coding interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(290), now.minusDays(2).minusHours(6)), now.minusDays(2).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(291), "Panel discussion", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(291), now.minusDays(3).minusHours(7)), now.minusDays(3).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(292), "Technical review", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(292), now.minusDays(4).minusHours(8)), now.minusDays(4).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(293), "HR screening", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(293), now.minusDays(5).minusHours(9)), now.minusDays(5).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(294), "System design", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(294), now.minusDays(1).minusHours(1)), now.minusDays(1).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(295), "Case study", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(295), now.minusDays(2).minusHours(2)), now.minusDays(2).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(296), "Technical interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(296), now.minusDays(3).minusHours(3)), now.minusDays(3).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(297), "Behavioral interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(297), now.minusDays(4).minusHours(4)), now.minusDays(4).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(298), "Coding challenge", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(298), now.minusDays(5).minusHours(5)), now.minusDays(5).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(299), "Panel interview", generateRandomFutureTimeSlot(scheduledInterviews, jobApplications.get(299), now.minusDays(1).minusHours(6)), now.minusDays(1).minusHours(6)));

        // OFFERED (36 records, past interviews optional, no future interviews)
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(3), "Final interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(3), now.minusDays(2).minusHours(1)), 8, now.minusDays(2).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(10), "Technical review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(10), now.minusDays(3).minusHours(2)), 7, now.minusDays(3).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(17), "HR discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(17), now.minusDays(4).minusHours(3)), 9, now.minusDays(4).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(24), "Behavioral interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(24), now.minusDays(1).minusHours(4)), 6, now.minusDays(1).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(31), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(31), now.minusDays(5).minusHours(5)), 8, now.minusDays(5).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(38), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(38), now.minusDays(2).minusHours(6)), 7, now.minusDays(2).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(45), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(45), now.minusDays(3).minusHours(7)), 9, now.minusDays(3).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(52), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(52), now.minusDays(4).minusHours(8)), 6, now.minusDays(4).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(59), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(59), now.minusDays(1).minusHours(9)), 8, now.minusDays(1).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(66), "Behavioral review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(66), now.minusDays(5).minusHours(1)), 7, now.minusDays(5).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(73), "Coding challenge", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(73), now.minusDays(2).minusHours(2)), 9, now.minusDays(2).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(80), "Panel discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(80), now.minusDays(3).minusHours(3)), 6, now.minusDays(3).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(87), "HR screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(87), now.minusDays(4).minusHours(4)), 8, now.minusDays(4).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(94), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(94), now.minusDays(1).minusHours(5)), 7, now.minusDays(1).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(101), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(101), now.minusDays(5).minusHours(6)), 9, now.minusDays(5).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(108), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(108), now.minusDays(2).minusHours(7)), 6, now.minusDays(2).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(115), "Behavioral interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(115), now.minusDays(3).minusHours(8)), 8, now.minusDays(3).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(122), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(122), now.minusDays(4).minusHours(9)), 7, now.minusDays(4).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(129), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(129), now.minusDays(1).minusHours(1)), 9, now.minusDays(1).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(136), "HR review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(136), now.minusDays(5).minusHours(2)), 6, now.minusDays(5).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(143), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(143), now.minusDays(2).minusHours(3)), 8, now.minusDays(2).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(150), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(150), now.minusDays(3).minusHours(4)), 7, now.minusDays(3).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(157), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(157), now.minusDays(4).minusHours(5)), 9, now.minusDays(4).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(164), "Behavioral review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(164), now.minusDays(1).minusHours(6)), 6, now.minusDays(1).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(171), "Coding challenge", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(171), now.minusDays(5).minusHours(7)), 8, now.minusDays(5).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(178), "Panel discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(178), now.minusDays(2).minusHours(8)), 7, now.minusDays(2).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(185), "HR screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(185), now.minusDays(3).minusHours(9)), 9, now.minusDays(3).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(192), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(192), now.minusDays(4).minusHours(1)), 6, now.minusDays(4).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(199), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(199), now.minusDays(1).minusHours(2)), 8, now.minusDays(1).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(206), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(206), now.minusDays(5).minusHours(3)), 7, now.minusDays(5).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(213), "Behavioral interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(213), now.minusDays(2).minusHours(4)), 9, now.minusDays(2).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(220), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(220), now.minusDays(3).minusHours(5)), 6, now.minusDays(3).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(227), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(227), now.minusDays(4).minusHours(6)), 8, now.minusDays(4).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(234), "HR review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(234), now.minusDays(1).minusHours(7)), 7, now.minusDays(1).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(241), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(241), now.minusDays(5).minusHours(8)), 9, now.minusDays(5).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(248), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(248), now.minusDays(2).minusHours(9)), 6, now.minusDays(2).minusHours(9)));

        // ACCEPTED (37 records, past interviews optional, no future interviews)
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(4), "Final discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(4), now.minusDays(3).minusHours(1)), 8, now.minusDays(3).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(11), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(11), now.minusDays(4).minusHours(2)), 7, now.minusDays(4).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(18), "HR review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(18), now.minusDays(1).minusHours(3)), 9, now.minusDays(1).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(25), "Behavioral screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(25), now.minusDays(5).minusHours(4)), 6, now.minusDays(5).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(32), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(32), now.minusDays(2).minusHours(5)), 8, now.minusDays(2).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(39), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(39), now.minusDays(3).minusHours(6)), 7, now.minusDays(3).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(46), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(46), now.minusDays(4).minusHours(7)), 9, now.minusDays(4).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(53), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(53), now.minusDays(1).minusHours(8)), 6, now.minusDays(1).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(60), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(60), now.minusDays(5).minusHours(9)), 8, now.minusDays(5).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(67), "Behavioral review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(67), now.minusDays(2).minusHours(1)), 7, now.minusDays(2).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(74), "Coding challenge", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(74), now.minusDays(3).minusHours(2)), 9, now.minusDays(3).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(81), "Panel discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(81), now.minusDays(4).minusHours(3)), 6, now.minusDays(4).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(88), "HR screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(88), now.minusDays(1).minusHours(4)), 8, now.minusDays(1).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(95), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(95), now.minusDays(5).minusHours(5)), 7, now.minusDays(5).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(96), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(96), now.minusDays(2).minusHours(6)), 9, now.minusDays(2).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(102), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(102), now.minusDays(3).minusHours(7)), 6, now.minusDays(3).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(109), "Behavioral interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(109), now.minusDays(4).minusHours(8)), 8, now.minusDays(4).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(116), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(116), now.minusDays(1).minusHours(9)), 7, now.minusDays(1).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(123), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(123), now.minusDays(5).minusHours(1)), 9, now.minusDays(5).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(130), "HR review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(130), now.minusDays(2).minusHours(2)), 6, now.minusDays(2).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(137), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(137), now.minusDays(3).minusHours(3)), 8, now.minusDays(3).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(144), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(144), now.minusDays(4).minusHours(4)), 7, now.minusDays(4).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(151), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(151), now.minusDays(1).minusHours(5)), 9, now.minusDays(1).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(158), "Behavioral review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(158), now.minusDays(5).minusHours(6)), 6, now.minusDays(5).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(165), "Coding challenge", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(165), now.minusDays(2).minusHours(7)), 8, now.minusDays(2).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(172), "Panel discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(172), now.minusDays(3).minusHours(8)), 7, now.minusDays(3).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(179), "HR screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(179), now.minusDays(4).minusHours(9)), 9, now.minusDays(4).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(186), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(186), now.minusDays(1).minusHours(1)), 6, now.minusDays(1).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(193), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(193), now.minusDays(5).minusHours(2)), 8, now.minusDays(5).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(200), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(200), now.minusDays(2).minusHours(3)), 7, now.minusDays(2).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(207), "Behavioral interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(207), now.minusDays(3).minusHours(4)), 9, now.minusDays(3).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(214), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(214), now.minusDays(4).minusHours(5)), 6, now.minusDays(4).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(221), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(221), now.minusDays(1).minusHours(6)), 8, now.minusDays(1).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(228), "HR review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(228), now.minusDays(5).minusHours(7)), 7, now.minusDays(5).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(235), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(235), now.minusDays(2).minusHours(8)), 9, now.minusDays(2).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(242), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(242), now.minusDays(3).minusHours(9)), 6, now.minusDays(3).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(249), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(249), now.minusDays(4).minusHours(1)), 8, now.minusDays(4).minusHours(1)));

        // REJECTED (35 records, past interviews optional, no future interviews)
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(7), "Initial screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(7), now.minusDays(1).minusHours(1)), 5, now.minusDays(1).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(15), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(15), now.minusDays(5).minusHours(3)), 4, now.minusDays(5).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(22), "Behavioral review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(22), now.minusDays(2).minusHours(4)), 6, now.minusDays(2).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(29), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(29), now.minusDays(3).minusHours(5)), 5, now.minusDays(3).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(36), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(36), now.minusDays(4).minusHours(6)), 4, now.minusDays(4).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(43), "HR screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(43), now.minusDays(1).minusHours(7)), 6, now.minusDays(1).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(50), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(50), now.minusDays(5).minusHours(8)), 5, now.minusDays(5).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(57), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(57), now.minusDays(2).minusHours(9)), 4, now.minusDays(2).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(64), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(64), now.minusDays(3).minusHours(1)), 6, now.minusDays(3).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(71), "Behavioral interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(71), now.minusDays(4).minusHours(2)), 5, now.minusDays(4).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(78), "Coding challenge", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(78), now.minusDays(1).minusHours(3)), 4, now.minusDays(1).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(85), "Panel discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(85), now.minusDays(5).minusHours(4)), 6, now.minusDays(5).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(92), "HR review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(92), now.minusDays(2).minusHours(5)), 5, now.minusDays(2).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(99), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(99), now.minusDays(3).minusHours(6)), 4, now.minusDays(3).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(106), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(106), now.minusDays(4).minusHours(7)), 6, now.minusDays(4).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(113), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(113), now.minusDays(1).minusHours(8)), 5, now.minusDays(1).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(120), "Behavioral screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(120), now.minusDays(5).minusHours(9)), 4, now.minusDays(5).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(127), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(127), now.minusDays(2).minusHours(1)), 6, now.minusDays(2).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(134), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(134), now.minusDays(3).minusHours(2)), 5, now.minusDays(3).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(141), "HR screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(141), now.minusDays(4).minusHours(3)), 4, now.minusDays(4).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(148), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(148), now.minusDays(1).minusHours(4)), 6, now.minusDays(1).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(155), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(155), now.minusDays(5).minusHours(5)), 5, now.minusDays(5).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(162), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(162), now.minusDays(2).minusHours(6)), 4, now.minusDays(2).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(169), "Behavioral review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(169), now.minusDays(3).minusHours(7)), 6, now.minusDays(3).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(176), "Coding challenge", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(176), now.minusDays(4).minusHours(8)), 5, now.minusDays(4).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(183), "Panel discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(183), now.minusDays(1).minusHours(9)), 4, now.minusDays(1).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(190), "HR review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(190), now.minusDays(5).minusHours(1)), 6, now.minusDays(5).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(197), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(197), now.minusDays(2).minusHours(2)), 5, now.minusDays(2).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(204), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(204), now.minusDays(3).minusHours(3)), 4, now.minusDays(3).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(211), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(211), now.minusDays(4).minusHours(4)), 6, now.minusDays(4).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(218), "Behavioral screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(218), now.minusDays(1).minusHours(5)), 5, now.minusDays(1).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(225), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(225), now.minusDays(5).minusHours(6)), 4, now.minusDays(5).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(232), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(232), now.minusDays(2).minusHours(7)), 6, now.minusDays(2).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(239), "HR screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(239), now.minusDays(3).minusHours(8)), 5, now.minusDays(3).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(246), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(246), now.minusDays(4).minusHours(9)), 4, now.minusDays(4).minusHours(9)));

        // WITHDRAWN (35 records, past interviews optional, no future interviews)
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(8), "Initial interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(8), now.minusDays(1).minusHours(1)), 5, now.minusDays(1).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(16), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(16), now.minusDays(5).minusHours(2)), 4, now.minusDays(5).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(23), "Behavioral review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(23), now.minusDays(2).minusHours(3)), 6, now.minusDays(2).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(30), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(30), now.minusDays(3).minusHours(4)), 5, now.minusDays(3).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(37), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(37), now.minusDays(4).minusHours(5)), 4, now.minusDays(4).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(44), "HR screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(44), now.minusDays(1).minusHours(6)), 6, now.minusDays(1).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(51), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(51), now.minusDays(5).minusHours(7)), 5, now.minusDays(5).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(58), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(58), now.minusDays(2).minusHours(8)), 4, now.minusDays(2).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(65), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(65), now.minusDays(3).minusHours(9)), 6, now.minusDays(3).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(72), "Behavioral screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(72), now.minusDays(4).minusHours(1)), 5, now.minusDays(4).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(79), "Coding challenge", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(79), now.minusDays(1).minusHours(2)), 4, now.minusDays(1).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(86), "Panel discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(86), now.minusDays(5).minusHours(3)), 6, now.minusDays(5).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(93), "HR review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(93), now.minusDays(2).minusHours(4)), 5, now.minusDays(2).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(100), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(100), now.minusDays(3).minusHours(5)), 4, now.minusDays(3).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(107), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(107), now.minusDays(4).minusHours(6)), 6, now.minusDays(4).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(114), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(114), now.minusDays(1).minusHours(7)), 5, now.minusDays(1).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(121), "Behavioral review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(121), now.minusDays(5).minusHours(8)), 4, now.minusDays(5).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(128), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(128), now.minusDays(2).minusHours(9)), 6, now.minusDays(2).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(135), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(135), now.minusDays(3).minusHours(1)), 5, now.minusDays(3).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(142), "HR screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(142), now.minusDays(4).minusHours(2)), 4, now.minusDays(4).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(149), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(149), now.minusDays(1).minusHours(3)), 6, now.minusDays(1).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(156), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(156), now.minusDays(5).minusHours(4)), 5, now.minusDays(5).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(163), "Technical interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(163), now.minusDays(2).minusHours(5)), 4, now.minusDays(2).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(170), "Behavioral screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(170), now.minusDays(3).minusHours(6)), 6, now.minusDays(3).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(177), "Coding challenge", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(177), now.minusDays(4).minusHours(7)), 5, now.minusDays(4).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(184), "Panel discussion", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(184), now.minusDays(1).minusHours(8)), 4, now.minusDays(1).minusHours(8)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(191), "HR review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(191), now.minusDays(5).minusHours(9)), 6, now.minusDays(5).minusHours(9)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(198), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(198), now.minusDays(2).minusHours(1)), 5, now.minusDays(2).minusHours(1)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(205), "Case study", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(205), now.minusDays(3).minusHours(2)), 4, now.minusDays(3).minusHours(2)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(212), "Technical screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(212), now.minusDays(4).minusHours(3)), 6, now.minusDays(4).minusHours(3)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(219), "Behavioral review", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(219), now.minusDays(1).minusHours(4)), 5, now.minusDays(1).minusHours(4)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(226), "Coding test", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(226), now.minusDays(5).minusHours(5)), 4, now.minusDays(5).minusHours(5)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(233), "Panel interview", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(233), now.minusDays(2).minusHours(6)), 6, now.minusDays(2).minusHours(6)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(240), "HR screening", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(240), now.minusDays(3).minusHours(7)), 5, now.minusDays(3).minusHours(7)));
        scheduledInterviews.add(new ScheduledInterview(jobApplications.get(247), "System design", generateRandomPastTimeSlot(scheduledInterviews, jobApplications.get(247), now.minusDays(4).minusHours(8)), 4, now.minusDays(4).minusHours(8)));
    }

    public static void main(String[] args) {
        validateScheduledInterviews(scheduledInterviews, JobApplication.Status.OFFERED);
    }

    private static final int MAX_SLOT_GENERATION_ATTEMPTS = 1000;
    private static final int SLOTS_PER_DAY = 18;
    private static final int MAX_DAYS_OFFSET = 7;

    public static TimeSlot generateRandomPastTimeSlot(ListInterface<ScheduledInterview> interviews, JobApplication jobApplication, LocalDateTime bookedAt) {
        final Random random = new Random();
        LocalDateTime now = Context.getDateTime();
        Company company = jobApplication.getJobPosting().getCompany();
        Applicant applicant = jobApplication.getApplicant();
        Set<TimeSlot> companyOccupiedSlots = new HashSet<>();
        Set<TimeSlot> applicantOccupiedSlots = new HashSet<>();

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
        Set<TimeSlot> companyOccupiedSlots = new HashSet<>();
        Set<TimeSlot> applicantOccupiedSlots = new HashSet<>();

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

    public static boolean validateScheduledInterviews(ListInterface<ScheduledInterview> interviewsToCheck, JobApplication.Status status) {
        boolean allValid = true;
        // Using Maps for efficient lookup of booked slots
        Map<TimeSlot, ScheduledInterview> companyBookings = new HashMap<>();
        Map<TimeSlot, ScheduledInterview> applicantBookings = new HashMap<>();

        Log.info("Starting validation for " + interviewsToCheck.size() + " scheduled interviews...");

        for (int i = 0; i < interviewsToCheck.size(); i++) {
            boolean currentInterviewInvalid = false;
            ScheduledInterview currentInterview = interviewsToCheck.get(i);
            JobApplication currentJobApp = currentInterview.getJobApplication();
            TimeSlot timeSlot = currentInterview.getTimeSlot();
            Company company = currentInterview.getJobApplication().getJobPosting().getCompany();
            Applicant applicant = currentInterview.getJobApplication().getApplicant();
            LocalDateTime bookedAt = currentInterview.getBookedAt();
            LocalDateTime interviewStart = timeSlot.getStartDateTime(); // Get the start time of the interview slot

            // 5. Check if interview is within 0-7 days from now
            LocalDateTime now = Context.getDateTime();
            if (interviewStart != null) {
                LocalDateTime pastBoundary = now.minusDays(MAX_DAYS_OFFSET);
                LocalDateTime futureBoundary = now.plusDays(MAX_DAYS_OFFSET);

                if (interviewStart.isBefore(pastBoundary) || interviewStart.isAfter(futureBoundary)) {
                    Log.error("Validation Error: Interview ID " + currentInterview.getId() +
                            " (App ID: " + currentJobApp.getId() + ")" +
                            " is not scheduled within the allowed timeframe (±7 days from now).");
                    Log.error("  - Current DateTime: " + now);
                    Log.error("  - Past Boundary: " + pastBoundary);
                    Log.error("  - Future Boundary: " + futureBoundary);
                    Log.error("  - Interview DateTime: " + interviewStart);
                    allValid = false;
                    currentInterviewInvalid = true;
                }
            }

            // 4. Check whether its weekend
            if (timeSlot.isWeekend()) {
                Log.error("Validation Error: Interview ID " + currentInterview.getId() +
                        " (App ID: " + currentJobApp.getId() + ")" + // Added App ID
                        " is scheduled on a weekend: " + timeSlot.getDate().getDayOfWeek() + " (" + timeSlot.getDate() + ")");
                allValid = false;
                currentInterviewInvalid = true;
                // Continue checking other rules for this interview
            }

            // 3. Check if the interview slot is at least 2 hours after the booking time
            if (bookedAt != null && interviewStart != null && !bookedAt.plusHours(2).isBefore(interviewStart)) {
                Log.error("Validation Error: Interview ID " + currentInterview.getId() +
                        " (App ID: " + currentJobApp.getId() + ")" + // Added App ID
                        " is scheduled less than 2 hours after booking.");
                Log.error("  - Booked At: " + bookedAt);
                Log.error("  - Interview Slot: " + timeSlot);
                allValid = false;
                currentInterviewInvalid = true;
            }

            // 6. check bookAt must be before now
            if (bookedAt != null && !bookedAt.isBefore(now)) {
                Log.error("Validation Error: Interview ID " + currentInterview.getId() +
                        " (App ID: " + currentJobApp.getId() + ")" +
                        " was booked after 'Context.now'");
                Log.error("  - Booked At: " + bookedAt);
                Log.error("  - Current Time: " + now);
                allValid = false;
                currentInterviewInvalid = true;
            }

            // 1. Check Company Overlap
            if (companyBookings.containsKey(timeSlot)) {
                ScheduledInterview conflictingInterview = companyBookings.get(timeSlot);
                if (conflictingInterview.getJobApplication().getJobPosting().getCompany().equals(company)) {
                    Log.error("Validation Error: Company Conflict! Company '" + company.toShortString() +
                            "' has multiple interviews booked for the same TimeSlot: " + timeSlot);
                    Log.error("  - Conflicting Interview 1 ID: " + conflictingInterview.getId() +
                            " (App ID: " + conflictingInterview.getJobApplication().getId() + // Added App ID
                            ", Applicant: " + conflictingInterview.getJobApplication().getApplicant().toShortString() + ")");
                    Log.error("  - Conflicting Interview 2 ID: " + currentInterview.getId() +
                            " (App ID: " + currentJobApp.getId() + // Added App ID
                            ", Applicant: " + applicant.toShortString() + ")");
                    allValid = false;
                    currentInterviewInvalid = true;
                }
            } else {
                companyBookings.put(timeSlot, currentInterview);
            }

            // 2. Check Applicant Overlap
            if (applicantBookings.containsKey(timeSlot)) {
                ScheduledInterview conflictingInterview = applicantBookings.get(timeSlot);
                if (conflictingInterview.getJobApplication().getApplicant().equals(applicant)) {
                    Log.error("Validation Error: Applicant Conflict! Applicant '" + applicant.toShortString() +
                            "' has multiple interviews booked for the same TimeSlot: " + timeSlot);
                    Log.error("  - Conflicting Interview 1 ID: " + conflictingInterview.getId() +
                            " (App ID: " + conflictingInterview.getJobApplication().getId() + // Added App ID
                            ", Company: " + conflictingInterview.getJobApplication().getJobPosting().getCompany().toShortString() + ")");
                    Log.error("  - Conflicting Interview 2 ID: " + currentInterview.getId() +
                            " (App ID: " + currentJobApp.getId() + // Added App ID
                            ", Company: " + company.toShortString() + ")");
                    allValid = false;
                    currentInterviewInvalid = true;
                }
            } else {
                applicantBookings.put(timeSlot, currentInterview);
            }

            // Check if the invalid interview's job application has the specified status
            if (currentInterviewInvalid && currentJobApp.getStatus() == status) {
                Log.info("Invalid interview ID " + currentInterview.getId() +
                        " has job application with status " + status +
                        " (App ID: " + currentJobApp.getId() + ")");
            }

            // Add a line gap after each invalid interview's messages
            if (currentInterviewInvalid) {
                Log.error("----------------------------------------");
            }
        }

        if (allValid) {
            Log.info("Validation successful: All scheduled interviews meet the specified criteria.");
        } else {
            Log.warn("Validation finished with errors. Please review the logs above.");
        }

        return allValid;
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
        return scheduledInterviews;
    }

    public static ListInterface<BlockedTimeSlot> getBlockedTimeSlots() {
        return blockedTimeSlots;
    }

    public static void printJobApplicationsIndexesByStatus(ListInterface<JobApplication> jobApplications) {
        ListInterface<Integer> pendingIndexes = new DoublyLinkedList<>();
        ListInterface<Integer> shortlistedIndexes = new DoublyLinkedList<>();
        ListInterface<Integer> interviewedIndexes = new DoublyLinkedList<>();
        ListInterface<Integer> offeredIndexes = new DoublyLinkedList<>();
        ListInterface<Integer> acceptedIndexes = new DoublyLinkedList<>();
        ListInterface<Integer> rejectedIndexes = new DoublyLinkedList<>();
        ListInterface<Integer> withdrawnIndexes = new DoublyLinkedList<>();

        // Iterate through jobApplications and collect indexes by status
        for (int i = 0; i < jobApplications.size(); i++) {
            JobApplication app = jobApplications.get(i);
            switch (app.getStatus()) {
                case PENDING:
                    pendingIndexes.add(i);
                    break;
                case SHORTLISTED:
                    shortlistedIndexes.add(i);
                    break;
                case INTERVIEWED:
                    interviewedIndexes.add(i);
                    break;
                case OFFERED:
                    offeredIndexes.add(i);
                    break;
                case ACCEPTED:
                    acceptedIndexes.add(i);
                    break;
                case REJECTED:
                    rejectedIndexes.add(i);
                    break;
                case WITHDRAWN:
                    withdrawnIndexes.add(i);
                    break;
            }
        }
        System.out.println("PENDING are: " + formatIndexes(pendingIndexes));
        System.out.println("SHORTLISTED are: " + formatIndexes(shortlistedIndexes));
        System.out.println("INTERVIEWED are: " + formatIndexes(interviewedIndexes));
        System.out.println("OFFERED are: " + formatIndexes(offeredIndexes));
        System.out.println("ACCEPTED are: " + formatIndexes(acceptedIndexes));
        System.out.println("REJECTED are: " + formatIndexes(rejectedIndexes));
        System.out.println("WITHDRAWN are: " + formatIndexes(withdrawnIndexes));
    }

    private static boolean validateScheduledInterviewsBookedAt(ListInterface<ScheduledInterview> interviews) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        boolean allValid = true;

        for (int i = 0; i < interviews.size(); i++) {
            ScheduledInterview interview = interviews.get(i);
            LocalDateTime bookedAt = interview.getBookedAt();
            LocalDateTime slotStart = interview.getTimeSlot().getStartDateTime();

            System.out.println("Interview " + i + ":");
            System.out.println("  Booked At: " + bookedAt.format(formatter));
            System.out.println("  Slot Date: " + interview.getTimeSlot().getDate());
            System.out.println("  Time Range: " + interview.getTimeSlot().getTimeRangeString());
            System.out.println("  Valid: " + (!bookedAt.plusHours(2).isAfter(slotStart)));
            System.out.println();
            if (bookedAt.plusHours(2).isAfter(slotStart)) {
                allValid = false;
            }
        }

        return allValid;
    }

    private static String formatIndexes(ListInterface<Integer> indexes) {
        if (indexes.isEmpty()) {
            return "None";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indexes.size(); i++) {
            sb.append(indexes.get(i));
            if (i < indexes.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}