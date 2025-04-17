package com.tarumt.dao;

import com.tarumt.entity.Applicant;
import com.tarumt.entity.Company;
import com.tarumt.entity.JobApplication;
import com.tarumt.entity.JobPosting;
import com.tarumt.entity.location.City;
import com.tarumt.entity.location.Location;

import java.time.LocalDate;

import com.tarumt.adt.list.ListInterface;
import com.tarumt.adt.list.DoublyLinkedList;

public class Initializer {
    private static final ListInterface<Company> companies = new DoublyLinkedList<>();
    private static final ListInterface<JobPosting> jobPostings = new DoublyLinkedList<>();
    private static final ListInterface<Applicant> applicants = new DoublyLinkedList<>();
    private static final ListInterface<JobApplication> jobApplications = new DoublyLinkedList<>();

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

        if (false) return;
        // region Init
        // 15 Companies (Hard-Coded, Adhering to Constraints, Using Malaysian Cities)
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

        companies.add(new Company("Test", "test test description", new Location(City.SETAPAK), "test@test.com", "0183862118"));

        // 75 Job Postings (5 per company, Hard-Coded, Qualifications set to null)
        // TechCorp (Company 0)
        jobPostings.add(new JobPosting("Software Engineer", companies.get(0), 5000, 8000, "Develop scalable web applications", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now().minusDays(5)));
        jobPostings.add(new JobPosting("Senior Developer", companies.get(0), 7000, 10000, "Lead development projects for clients", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now().minusDays(3)));
        jobPostings.add(new JobPosting("DevOps Engineer", companies.get(0), 6000, 9000, "Manage CI/CD pipelines and deployments", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now().minusDays(7)));
        jobPostings.add(new JobPosting("QA Engineer", companies.get(0), 4000, 6000, "Ensure product quality through testing", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.FILLED, LocalDate.now().minusDays(2), LocalDate.now().minusDays(2)));
        jobPostings.add(new JobPosting("UI/UX Designer", companies.get(0), 4500, 7000, "Design user interfaces for web apps", JobPosting.Type.DESIGN_ARCH, null, JobPosting.Status.FILLED, LocalDate.now().minusDays(4), LocalDate.now().minusDays(4)));

        // Innovatech (Company 1)
        jobPostings.add(new JobPosting("Data Scientist", companies.get(1), 6000, 9000, "Analyze complex datasets for insights", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now().minusDays(5)));
        jobPostings.add(new JobPosting("AI Engineer", companies.get(1), 7000, 11000, "Develop AI models for automation", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now().minusDays(3)));
        jobPostings.add(new JobPosting("ML Engineer", companies.get(1), 6500, 10000, "Implement ML algorithms for projects", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now().minusDays(7)));
        jobPostings.add(new JobPosting("Research Scientist", companies.get(1), 8000, 12000, "Conduct innovative research in tech", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now().minusDays(2)));
        jobPostings.add(new JobPosting("Product Manager", companies.get(1), 7000, 11000, "Manage product lifecycle and strategy", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now().minusDays(4)));

        // FinServe (Company 2)
        jobPostings.add(new JobPosting("Financial Analyst", companies.get(2), 5000, 8000, "Analyze financial data for insights", JobPosting.Type.BANK_FIN_SERV, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now().minusDays(5)));
        jobPostings.add(new JobPosting("Risk Manager", companies.get(2), 6000, 9000, "Assess financial risks for the company", JobPosting.Type.BANK_FIN_SERV, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now().minusDays(3)));
        jobPostings.add(new JobPosting("Accountant", companies.get(2), 4500, 7000, "Manage financial records and reports", JobPosting.Type.ACCOUNTING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now().minusDays(7)));
        jobPostings.add(new JobPosting("Investment Advisor", companies.get(2), 5500, 8500, "Provide investment advice to clients", JobPosting.Type.BANK_FIN_SERV, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now().minusDays(2)));
        jobPostings.add(new JobPosting("Compliance Officer", companies.get(2), 5000, 8000, "Ensure regulatory compliance", JobPosting.Type.BANK_FIN_SERV, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now().minusDays(4)));

        // HealthPlus (Company 3)
        jobPostings.add(new JobPosting("Medical Researcher", companies.get(3), 6000, 9000, "Conduct medical research studies", JobPosting.Type.HEALTH_MED, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now().minusDays(5)));
        jobPostings.add(new JobPosting("Clinical Analyst", companies.get(3), 5000, 8000, "Analyze clinical data for insights", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now().minusDays(3)));
        jobPostings.add(new JobPosting("Healthcare Manager", companies.get(3), 7000, 10000, "Manage healthcare operations", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now().minusDays(7)));
        jobPostings.add(new JobPosting("Health Consultant", companies.get(3), 5500, 8500, "Provide health consultancy services", JobPosting.Type.HEALTH_MED, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now().minusDays(2)));
        jobPostings.add(new JobPosting("Data Specialist", companies.get(3), 5000, 8000, "Handle healthcare data systems", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now().minusDays(4)));

        // EduTech (Company 4)
        jobPostings.add(new JobPosting("EdTech Developer", companies.get(4), 5000, 8000, "Develop educational software tools", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now().minusDays(5)));
        jobPostings.add(new JobPosting("Content Creator", companies.get(4), 4000, 6000, "Create educational content", JobPosting.Type.EDUC_TRAINING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now().minusDays(3)));
        jobPostings.add(new JobPosting("Instructional Designer", companies.get(4), 4500, 7000, "Design learning materials", JobPosting.Type.EDUC_TRAINING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now().minusDays(7)));
        jobPostings.add(new JobPosting("Tech Support", companies.get(4), 3500, 5000, "Provide tech support for platforms", JobPosting.Type.CALL_CUST_SVC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now().minusDays(2)));
        jobPostings.add(new JobPosting("Project Manager", companies.get(4), 6000, 9000, "Manage education tech projects", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now().minusDays(4)));

        // GreenEnergy (Company 5)
        jobPostings.add(new JobPosting("Energy Engineer", companies.get(5), 6000, 9000, "Design sustainable energy systems", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now().minusDays(5)));
        jobPostings.add(new JobPosting("Sustainability Analyst", companies.get(5), 5000, 8000, "Analyze sustainability metrics", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now().minusDays(3)));
        jobPostings.add(new JobPosting("Project Coordinator", companies.get(5), 4500, 7000, "Coordinate energy projects", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now().minusDays(7)));
        jobPostings.add(new JobPosting("Field Technician", companies.get(5), 4000, 6000, "Install energy systems", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now().minusDays(2)));
        jobPostings.add(new JobPosting("Research Analyst", companies.get(5), 5000, 8000, "Research green energy solutions", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now().minusDays(4)));

        // AutoDrive (Company 6)
        jobPostings.add(new JobPosting("Robotics Engineer", companies.get(6), 7000, 11000, "Develop autonomous vehicle systems", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now().minusDays(5)));
        jobPostings.add(new JobPosting("Software Developer", companies.get(6), 6000, 9000, "Code vehicle control software", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now().minusDays(3)));
        jobPostings.add(new JobPosting("Test Engineer", companies.get(6), 5000, 8000, "Test autonomous vehicle systems", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now().minusDays(7)));
        jobPostings.add(new JobPosting("Data Engineer", companies.get(6), 5500, 8500, "Manage vehicle data pipelines", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now().minusDays(2)));
        jobPostings.add(new JobPosting("Safety Analyst", companies.get(6), 5000, 8000, "Ensure vehicle safety standards", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now().minusDays(4)));

        // FoodieTech (Company 7)
        jobPostings.add(new JobPosting("App Developer", companies.get(7), 5000, 8000, "Develop food delivery apps", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now().minusDays(5)));
        jobPostings.add(new JobPosting("Marketing Specialist", companies.get(7), 4500, 7000, "Market food tech solutions", JobPosting.Type.MARKETING_COMM, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now().minusDays(3)));
        jobPostings.add(new JobPosting("Data Analyst", companies.get(7), 5000, 8000, "Analyze food industry trends", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now().minusDays(7)));
        jobPostings.add(new JobPosting("Logistics Manager", companies.get(7), 5500, 8500, "Manage food delivery logistics", JobPosting.Type.MANF_LOG, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now().minusDays(2)));
        jobPostings.add(new JobPosting("Customer Support", companies.get(7), 3500, 5000, "Support food app users", JobPosting.Type.CALL_CUST_SVC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now().minusDays(4)));

        // CyberShield (Company 8)
        jobPostings.add(new JobPosting("Security Analyst", companies.get(8), 6000, 9000, "Analyze cybersecurity threats", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now().minusDays(5)));
        jobPostings.add(new JobPosting("Penetration Tester", companies.get(8), 6500, 9500, "Test system vulnerabilities", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now().minusDays(3)));
        jobPostings.add(new JobPosting("Security Engineer", companies.get(8), 7000, 10000, "Design secure systems", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now().minusDays(7)));
        jobPostings.add(new JobPosting("Compliance Analyst", companies.get(8), 5000, 8000, "Ensure regulatory compliance", JobPosting.Type.LEGAL, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now().minusDays(2)));
        jobPostings.add(new JobPosting("Incident Responder", companies.get(8), 5500, 8500, "Respond to security incidents", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now().minusDays(4)));

        // QuantumSoft (Company 9)
        jobPostings.add(new JobPosting("Quantum Developer", companies.get(9), 8000, 12000, "Develop quantum computing software", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now().minusDays(5)));
        jobPostings.add(new JobPosting("Research Scientist", companies.get(9), 7500, 11000, "Research quantum algorithms", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now().minusDays(3)));
        jobPostings.add(new JobPosting("Software Engineer", companies.get(9), 6000, 9000, "Code quantum applications", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now().minusDays(7)));
        jobPostings.add(new JobPosting("Data Scientist", companies.get(9), 6500, 9500, "Analyze quantum computing data", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now().minusDays(2)));
        jobPostings.add(new JobPosting("Project Manager", companies.get(9), 7000, 10000, "Manage quantum projects", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now().minusDays(4)));

        // MediCare (Company 10)
        jobPostings.add(new JobPosting("Clinical Researcher", companies.get(10), 6000, 9000, "Conduct clinical research studies", JobPosting.Type.HEALTH_MED, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now().minusDays(5)));
        jobPostings.add(new JobPosting("Medical Analyst", companies.get(10), 5000, 8000, "Analyze medical data trends", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now().minusDays(3)));
        jobPostings.add(new JobPosting("Health Consultant", companies.get(10), 5500, 8500, "Provide medical consultancy", JobPosting.Type.HEALTH_MED, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now().minusDays(7)));
        jobPostings.add(new JobPosting("Data Specialist", companies.get(10), 5000, 8000, "Manage medical data systems", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now().minusDays(2)));
        jobPostings.add(new JobPosting("Operations Manager", companies.get(10), 6000, 9000, "Oversee medical operations", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now().minusDays(4)));

        // CloudSys (Company 11)
        jobPostings.add(new JobPosting("Cloud Engineer", companies.get(11), 6000, 9000, "Design cloud infrastructure", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now().minusDays(5)));
        jobPostings.add(new JobPosting("DevOps Specialist", companies.get(11), 6500, 9500, "Manage cloud deployments", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now().minusDays(3)));
        jobPostings.add(new JobPosting("Security Analyst", companies.get(11), 5500, 8500, "Secure cloud systems", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now().minusDays(7)));
        jobPostings.add(new JobPosting("Data Engineer", companies.get(11), 6000, 9000, "Build data pipelines in cloud", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now().minusDays(2)));
        jobPostings.add(new JobPosting("Cloud Architect", companies.get(11), 7000, 11000, "Architect cloud solutions", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now().minusDays(4)));

        // NeuroTech (Company 12)
        jobPostings.add(new JobPosting("Neuroscientist", companies.get(12), 7000, 11000, "Research neural networks", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now().minusDays(5)));
        jobPostings.add(new JobPosting("AI Developer", companies.get(12), 6500, 9500, "Develop AI for neuroscience", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now().minusDays(3)));
        jobPostings.add(new JobPosting("Data Scientist", companies.get(12), 6000, 9000, "Analyze neural data", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now().minusDays(7)));
        jobPostings.add(new JobPosting("Research Analyst", companies.get(12), 5500, 8500, "Support neuroscience research", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now().minusDays(2)));
        jobPostings.add(new JobPosting("Software Engineer", companies.get(12), 6000, 9000, "Code neuroscience tools", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now().minusDays(4)));

        // AgriGrow (Company 13)
        jobPostings.add(new JobPosting("Agri Engineer", companies.get(13), 5000, 8000, "Design farming technology", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now().minusDays(5)));
        jobPostings.add(new JobPosting("Data Analyst", companies.get(13), 4500, 7000, "Analyze agricultural data", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now().minusDays(3)));
        jobPostings.add(new JobPosting("Field Manager", companies.get(13), 4000, 6000, "Manage farming operations", JobPosting.Type.AGRICULTURE, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now().minusDays(7)));
        jobPostings.add(new JobPosting("Tech Specialist", companies.get(13), 4500, 7000, "Support agri-tech systems", JobPosting.Type.IT_COMM_TEC, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now().minusDays(2)));
        jobPostings.add(new JobPosting("Sustainability Lead", companies.get(13), 5000, 8000, "Lead sustainable farming", JobPosting.Type.AGRICULTURE, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now().minusDays(4)));

        // SpaceFront (Company 14)
        jobPostings.add(new JobPosting("Aerospace Engineer", companies.get(14), 7000, 11000, "Design aerospace systems", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(5), LocalDate.now().minusDays(5)));
        jobPostings.add(new JobPosting("Space Researcher", companies.get(14), 6500, 9500, "Research space technologies", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(3), LocalDate.now().minusDays(3)));
        jobPostings.add(new JobPosting("Test Engineer", companies.get(14), 6000, 9000, "Test aerospace components", JobPosting.Type.ENGINEERING, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(7), LocalDate.now().minusDays(7)));
        jobPostings.add(new JobPosting("Data Analyst", companies.get(14), 5500, 8500, "Analyze space mission data", JobPosting.Type.SCIENCE_TECH, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(2), LocalDate.now().minusDays(2)));
        jobPostings.add(new JobPosting("Project Manager", companies.get(14), 7000, 10000, "Manage space projects", JobPosting.Type.ADMIN_OFFICE, null, JobPosting.Status.OPEN, LocalDate.now().minusDays(4), LocalDate.now().minusDays(4)));

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
        applicants.add(new Applicant("Test", "test@test.com", "0183862118", JobPosting.Type.CALL_CUST_SVC, new Location(City.KUALA_LUMPUR)));
        // endregion

        //51 job applications
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
        jobApplications.add(new JobApplication(jobPostings.get(50), applicants.get(16), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 21)));
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

        jobApplications.add(new JobApplication(jobPostings.get(1), applicants.get(8), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 19)));
        jobApplications.add(new JobApplication(jobPostings.get(1), applicants.get(10), JobApplication.Status.OFFERED, LocalDate.of(2025, 3, 20)));
        jobApplications.add(new JobApplication(jobPostings.get(1), applicants.get(12), JobApplication.Status.ACCEPTED, LocalDate.of(2025, 3, 21)));
        jobApplications.add(new JobApplication(jobPostings.get(1), applicants.get(15), JobApplication.Status.WITHDRAWN, LocalDate.of(2025, 3, 22)));

        jobApplications.add(new JobApplication(jobPostings.get(2), applicants.get(17), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 23)));
        jobApplications.add(new JobApplication(jobPostings.get(2), applicants.get(19), JobApplication.Status.SHORTLISTED, LocalDate.of(2025, 3, 24)));
        jobApplications.add(new JobApplication(jobPostings.get(2), applicants.get(21), JobApplication.Status.INTERVIEWED, LocalDate.of(2025, 3, 25)));
        jobApplications.add(new JobApplication(jobPostings.get(2), applicants.get(23), JobApplication.Status.REJECTED, LocalDate.of(2025, 3, 26)));

        jobApplications.add(new JobApplication(jobPostings.get(3), applicants.get(25), JobApplication.Status.PENDING, LocalDate.of(2025, 3, 27)));
        jobApplications.add(new JobApplication(jobPostings.get(3), applicants.get(27), JobApplication.Status.OFFERED, LocalDate.of(2025, 3, 28)));
        jobApplications.add(new JobApplication(jobPostings.get(3), applicants.get(29), JobApplication.Status.ACCEPTED, LocalDate.of(2025, 3, 29)));
        jobApplications.add(new JobApplication(jobPostings.get(3), applicants.get(31), JobApplication.Status.WITHDRAWN, LocalDate.of(2025, 3, 30)));

        jobApplications.add(new JobApplication(jobPostings.get(4), applicants.get(33), JobApplication.Status.PENDING, LocalDate.of(2025, 4, 1)));
        jobApplications.add(new JobApplication(jobPostings.get(4), applicants.get(35), JobApplication.Status.SHORTLISTED, LocalDate.of(2025, 4, 2)));
        jobApplications.add(new JobApplication(jobPostings.get(4), applicants.get(37), JobApplication.Status.INTERVIEWED, LocalDate.of(2025, 4, 3)));
        jobApplications.add(new JobApplication(jobPostings.get(4), applicants.get(39), JobApplication.Status.REJECTED, LocalDate.of(2025, 4, 4)));
        
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


}
