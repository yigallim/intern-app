package com.tarumt.dao;

import com.tarumt.entity.Applicant;
import com.tarumt.entity.Company;
import com.tarumt.entity.JobApplication;
import com.tarumt.entity.JobPosting;
import com.tarumt.entity.interview.Invitation;
import com.tarumt.entity.interview.ScheduledInterview;
import com.tarumt.entity.location.City;
import com.tarumt.entity.location.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.tarumt.adt.list.ListInterface;
import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.utility.common.Context;

public class Initializer {
    private static final ListInterface<Company> companies = new DoublyLinkedList<>();
    private static final ListInterface<JobPosting> jobPostings = new DoublyLinkedList<>();
    private static final ListInterface<Applicant> applicants = new DoublyLinkedList<>();
    private static final ListInterface<JobApplication> jobApplications = new DoublyLinkedList<>();
    private static final ListInterface<Invitation> invitations = new DoublyLinkedList<>();
    private static final ListInterface<ScheduledInterview> scheduledInterviews = new DoublyLinkedList<>();

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

        //251 job applications
        // Applicant 0 (Alice Johnson)
        jobApplications.add(new JobApplication(jobPostings.get(0), applicants.get(0), JobApplication.Status.PENDING, now.minusDays(7).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(5), applicants.get(0), JobApplication.Status.SHORTLISTED, now.minusDays(6).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(10), applicants.get(0), JobApplication.Status.INTERVIEWED, now.minusDays(5).minusHours(4)));
        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(0), JobApplication.Status.OFFERED, now.minusDays(4).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(0), JobApplication.Status.ACCEPTED, now.minusDays(3).minusHours(6)));

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

        // Applicant 0
        jobApplications.add(new JobApplication(jobPostings.get(0), applicants.get(0), JobApplication.Status.SHORTLISTED, now.minusDays(7).minusHours(5)));
        jobApplications.add(new JobApplication(jobPostings.get(5), applicants.get(0), JobApplication.Status.SHORTLISTED, now.minusDays(6).minusHours(3)));
        jobApplications.add(new JobApplication(jobPostings.get(10), applicants.get(0), JobApplication.Status.SHORTLISTED, now.minusDays(5).minusHours(2)));
        jobApplications.add(new JobApplication(jobPostings.get(15), applicants.get(0), JobApplication.Status.SHORTLISTED, now.minusDays(4).minusHours(1)));
        jobApplications.add(new JobApplication(jobPostings.get(20), applicants.get(0), JobApplication.Status.SHORTLISTED, now.minusDays(3).minusHours(4)));

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

//        PENDING, no interview at all
//        SHORTLISTED, can have interview in future
//        INTERVIEWED, can have past interviews
//        OFFERED, can have past interviews
//        ACCEPTED, can have no interview, past interviews
//        REJECTED, can have no interview, past interviews
//        WITHDRAWN, can have no interview, past interview

        invitations.add(new Invitation(jobApplications.get(250), "1st round technical interview", now));
        invitations.add(new Invitation(jobApplications.get(252), "1st round technical interview", now));
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
}