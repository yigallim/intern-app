package com.tarumt.boundary;

import com.tarumt.entity.qualification.EducationLevel;
import com.tarumt.entity.qualification.EducationLevel.University;
import com.tarumt.entity.qualification.LanguageProficiency;
import com.tarumt.entity.qualification.Qualification;
import com.tarumt.entity.qualification.WorkExperience;
import com.tarumt.entity.qualification.Skill;
import com.tarumt.entity.qualification.Skill.SkillCategory;

import com.tarumt.utility.common.Input;
import com.tarumt.utility.validation.DecimalCondition;
import com.tarumt.utility.validation.IntegerCondition;
import com.tarumt.utility.validation.StringCondition;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class QualificationUI {

    private static final Map<Skill.SkillCategory, List<String>> SKILL_OPTIONS = new HashMap<>();

    static {
        SKILL_OPTIONS.put(Skill.SkillCategory.IT_COMM_TEC, Arrays.asList(
                "Java Programming", "Python Development", "Web Development", "Cybersecurity",
                "Database Management", "Cloud Computing", "AI & Machine Learning", "Networking",
                "Software Testing", "Blockchain Development"));

        SKILL_OPTIONS.put(Skill.SkillCategory.ACCOUNTING, Arrays.asList(
                "Financial Analysis", "Bookkeeping", "Taxation", "Payroll Management",
                "Auditing", "Budgeting", "Cost Accounting", "Investment Analysis",
                "Risk Management", "Corporate Finance"));

        SKILL_OPTIONS.put(Skill.SkillCategory.ADMIN_OFFICE, Arrays.asList(
                "Office Management", "Scheduling & Calendar Management", "Data Entry",
                "Customer Service", "Microsoft Office Suite", "Document Preparation",
                "Business Correspondence", "Records Management", "Administrative Support",
                "Meeting Coordination"));

        SKILL_OPTIONS.put(Skill.SkillCategory.DESIGN_ARCH, Arrays.asList(
                "Graphic Design", "Interior Design", "3D Modeling", "AutoCAD",
                "Illustration", "Brand Identity Design", "Landscape Architecture",
                "UI/UX Design", "Sketching & Drafting", "Adobe Creative Suite"));

        SKILL_OPTIONS.put(Skill.SkillCategory.HR_RECRUIT, Arrays.asList(
                "Talent Acquisition", "Interviewing & Hiring", "Employee Relations",
                "Payroll Processing", "Performance Management", "HR Policies & Compliance",
                "Training & Development", "Workforce Planning", "Compensation & Benefits",
                "Diversity & Inclusion"));

        SKILL_OPTIONS.put(Skill.SkillCategory.CONSTRUCTION, Arrays.asList(
                "Project Management", "Blueprint Reading", "Carpentry", "Plumbing",
                "Electrical Installation", "Site Safety Management", "Welding",
                "Structural Engineering", "Masonry", "Construction Estimation"));

        SKILL_OPTIONS.put(Skill.SkillCategory.SCIENCE_TECH, Arrays.asList(
                "Laboratory Analysis", "Biotechnology", "Environmental Science",
                "Pharmaceutical Research", "Medical Technology", "Genetic Engineering",
                "Nanotechnology", "Forensic Science", "Data Science", "Scientific Writing"));

        SKILL_OPTIONS.put(Skill.SkillCategory.REAL_EST, Arrays.asList(
                "Property Valuation", "Real Estate Marketing", "Sales Negotiation",
                "Property Law", "Investment Analysis", "Property Management",
                "Land Development", "Commercial Leasing", "Market Trend Analysis",
                "Home Staging"));

        SKILL_OPTIONS.put(Skill.SkillCategory.AD_ARTS_MEDIA, Arrays.asList(
                "Copywriting", "Advertising Strategy", "Photography", "Videography",
                "Social Media Management", "Public Relations", "Creative Direction",
                "Scriptwriting", "Motion Graphics", "Brand Storytelling"));

        SKILL_OPTIONS.put(Skill.SkillCategory.CALL_CUST_SVC, Arrays.asList(
                "Customer Support", "Complaint Handling", "Call Center Operations",
                "CRM Software", "Sales Support", "Technical Support",
                "Conflict Resolution", "Live Chat Assistance", "Multilingual Support",
                "Customer Retention Strategies"));

        SKILL_OPTIONS.put(Skill.SkillCategory.CONSULTING, Arrays.asList(
                "Business Analysis", "Management Consulting", "Strategy Development",
                "Market Research", "Process Improvement", "Financial Advisory",
                "Corporate Training", "Risk Assessment", "Change Management",
                "Negotiation Skills"));

        SKILL_OPTIONS.put(Skill.SkillCategory.LEGAL, Arrays.asList(
                "Legal Research", "Contract Drafting", "Corporate Law",
                "Litigation Support", "Intellectual Property", "Employment Law",
                "Family Law", "Real Estate Law", "Compliance & Regulations",
                "Mediation & Dispute Resolution"));

        SKILL_OPTIONS.put(Skill.SkillCategory.EDUC_TRAINING, Arrays.asList(
                "Lesson Planning", "Curriculum Development", "E-Learning Development",
                "Classroom Management", "Student Engagement", "Special Education",
                "Language Instruction", "Training Program Design", "Public Speaking",
                "Tutoring"));

        SKILL_OPTIONS.put(Skill.SkillCategory.INSURANCE, Arrays.asList(
                "Risk Assessment", "Claims Processing", "Underwriting",
                "Policy Administration", "Customer Consultation", "Actuarial Analysis",
                "Regulatory Compliance", "Fraud Investigation", "Investment Planning",
                "Health & Life Insurance Advisory"));

        SKILL_OPTIONS.put(Skill.SkillCategory.BANK_FIN_SERV, Arrays.asList(
                "Financial Planning", "Investment Banking", "Retail Banking",
                "Wealth Management", "Risk Management", "Forex Trading",
                "Loans & Mortgage Advisory", "Credit Analysis", "Financial Compliance",
                "Portfolio Management"));

        SKILL_OPTIONS.put(Skill.SkillCategory.SALES, Arrays.asList(
                "Lead Generation", "Sales Negotiation", "Customer Acquisition",
                "B2B Sales", "Retail Sales", "CRM Software", "Sales Forecasting",
                "Cold Calling", "Account Management", "Consultative Selling"));

        SKILL_OPTIONS.put(Skill.SkillCategory.SPORT_RECREATION, Arrays.asList(
                "Personal Training", "Sports Coaching", "Event Management",
                "Sports Psychology", "Athletic Conditioning", "Recreational Therapy",
                "Outdoor Adventure Planning", "Strength Training", "Sports Broadcasting",
                "Yoga Instruction"));

        SKILL_OPTIONS.put(Skill.SkillCategory.MANF_LOG, Arrays.asList(
                "Supply Chain Management", "Warehouse Operations", "Quality Control",
                "Inventory Management", "Logistics Coordination", "Production Planning",
                "Material Handling", "Fleet Management", "Process Optimization",
                "Machine Operation"));

        SKILL_OPTIONS.put(Skill.SkillCategory.HEALTH_MED, Arrays.asList(
                "Nursing Care", "Medical Coding", "Physical Therapy",
                "Emergency Response", "Mental Health Counseling", "Radiology",
                "Pharmacy Management", "Surgical Assistance", "Medical Research",
                "Diet & Nutrition"));

        SKILL_OPTIONS.put(Skill.SkillCategory.HOSP_TOURISM, Arrays.asList(
                "Hotel Management", "Tour Guiding", "Event Planning",
                "Front Desk Operations", "Food & Beverage Management",
                "Housekeeping Supervision", "Catering", "Customer Service",
                "Travel Coordination", "Luxury Hospitality"));

        SKILL_OPTIONS.put(Skill.SkillCategory.MARKETING_COMM, Arrays.asList(
                "Social Media Marketing", "SEO Optimization", "Market Research",
                "Brand Management", "Content Creation", "Email Marketing",
                "Public Relations", "Advertising Campaigns", "Google Analytics",
                "Consumer Psychology"));

        SKILL_OPTIONS.put(Skill.SkillCategory.AGRICULTURE, Arrays.asList(
                "Crop Management", "Agribusiness Management", "Soil Science",
                "Pest Control", "Organic Farming", "Irrigation Management",
                "Agricultural Research", "Animal Husbandry", "Fisheries Management",
                "Food Processing"));

        SKILL_OPTIONS.put(Skill.SkillCategory.RETAIL_CONSUMER, Arrays.asList(
                "Point-of-Sale Systems", "Retail Sales", "Merchandising",
                "Customer Experience Management", "Inventory Control",
                "Store Management", "Product Knowledge", "Loss Prevention",
                "Retail Marketing", "E-Commerce Operations"));

        SKILL_OPTIONS.put(Skill.SkillCategory.ENGINEERING, Arrays.asList(
                "Mechanical Engineering", "Electrical Engineering", "Civil Engineering",
                "Structural Analysis", "CAD Software", "Robotics",
                "Manufacturing Processes", "Thermodynamics", "Automotive Engineering",
                "Project Management"));

        SKILL_OPTIONS.put(Skill.SkillCategory.COMM_SVC_DEV, Arrays.asList(
                "Social Work", "Community Outreach", "Program Development",
                "Non-Profit Management", "Volunteer Coordination",
                "Fundraising Strategies", "Public Policy Advocacy",
                "Mental Health Support", "Elderly Care", "Disaster Relief"));

        SKILL_OPTIONS.put(Skill.SkillCategory.OTHER, Arrays.asList(
                "Creative Problem Solving", "Entrepreneurship", "Time Management",
                "Multitasking", "Critical Thinking", "Negotiation", "Adaptability",
                "Teamwork", "Leadership", "Public Speaking"));
    }
    private final Input input;

    public QualificationUI() {
        this.input = new Input();
    }

    // 🔹 Generalized method for displaying enums
    private <T extends Enum<T>> T getEnumSelection(Class<T> enumClass, String prompt) {
        T[] values = enumClass.getEnumConstants();
        IntegerCondition condition = new IntegerCondition().min(1).max(values.length);

        // Display options
        System.out.println("\n⇐ " + prompt + " ⇒");
        for (int i = 0; i < values.length; i++) {
            System.out.println((i + 1) + ". " + values[i]);
        }

        int choice = input.getInt("Select an option: ", condition);
        return values[choice - 1];
    }

    public EducationLevel getEducationInput() {
        DecimalCondition condition = new DecimalCondition().min(0.0).max(4.0);
        System.out.println("\n⇐ Education & Academic Qualification ⇒");

        EducationLevel.DegreeLevel degree = getEnumSelection(EducationLevel.DegreeLevel.class, "Select your highest level of education");
        EducationLevel.FieldOfStudy field = getEnumSelection(EducationLevel.FieldOfStudy.class, "Select your field of study");
        // Step 3: Select University (Using Enum)
        University university = getEnumSelection(University.class, "Select your university");
        double cgpa = input.getDouble("Enter your CGPA (0.00 - 4.00): ", condition);

        return new EducationLevel(degree, field, university, Math.round(cgpa * 100.0) / 100.0, false, Qualification.Importance.HIGH);
    }

    public List<WorkExperience> getWorkExperienceInput() {
        List<WorkExperience> workExperiences = new ArrayList<>();
        StringCondition condition = new StringCondition().enumeration("Y", "X", "y", "x");

        while (true) {
            System.out.println("\n⇐ Work Experience ⇒");
            WorkExperience.Industry industry = getEnumSelection(WorkExperience.Industry.class, "Select your industry");

            IntegerCondition yearsCondition = new IntegerCondition().min(0).max(50);
            int years = input.getInt("Enter years of experience (0 - 50): ", yearsCondition);

            workExperiences.add(new WorkExperience(industry, years, false, Qualification.Importance.MEDIUM));
            System.out.println("✅ Work Experience added successfully!\n");

            // Ask if the user wants to add another
            String continueChoice = input.getString("Continue to add another work experience? [Y / X] => ", condition);
            if (!continueChoice.equalsIgnoreCase("Y")) {
                break;
            }
        }

        return workExperiences;
    }

    public List<LanguageProficiency> getLanguageProficiencyInput() {
        List<LanguageProficiency> languageProficiencies = new ArrayList<>();
        StringCondition condition = new StringCondition().enumeration("Y", "X", "y", "x");
        while (true) {
            System.out.println("\n⇐ Language Proficiency ⇒");
            LanguageProficiency.Language language = getEnumSelection(LanguageProficiency.Language.class, "Select a language");
            LanguageProficiency.Proficiency proficiency = getEnumSelection(LanguageProficiency.Proficiency.class, "Select proficiency level");

            languageProficiencies.add(new LanguageProficiency(language, proficiency, false, Qualification.Importance.LOW));
            System.out.println("✅ Language Proficiency added successfully!\n");

            // Ask if the user wants to add another
            String continueChoice = input.getString("Continue to add another language proficiency? [Y / X] => ", condition);
            if (!continueChoice.equalsIgnoreCase("Y")) {
                break;
            }
        }

        return languageProficiencies;
    }

    public List<Skill> getSkillInput() {
        List<Skill> skills = new ArrayList<>();
        IntegerCondition iCondition = new IntegerCondition().enumeration(1, 0);
        IntegerCondition sCondition = new IntegerCondition();
        while (true) {
            System.out.println("\n⇐ Add a Skill ⇒");
            int exitOption = input.getInt("Press 0 to exit or 1 to continue: ", iCondition);
            if (exitOption == 0) {
                return skills;  // Exit if user chooses X
            }

            // Step 1: Select a Category
            Skill.SkillCategory category = getEnumSelection(Skill.SkillCategory.class, "Select a skill category");

            // Step 2: Choose a Skill from the Category
            List<String> availableSkills = SKILL_OPTIONS.getOrDefault(category, Collections.emptyList());

            if (availableSkills.isEmpty()) {
                System.out.println("❌ No skills available for this category.");
                continue;
            }

            System.out.println("\nAvailable skills:");
            for (int i = 0; i < availableSkills.size(); i++) {
                System.out.println((i + 1) + ". " + availableSkills.get(i));
            }
            sCondition = new IntegerCondition().max(availableSkills.size());
            int skillChoice = input.getInt("Select a skill by number: ", sCondition);
            String selectedSkill = availableSkills.get(skillChoice - 1);

            // Step 3: Choose Proficiency Level
            Skill.ProficiencyLevel proficiency = getEnumSelection(Skill.ProficiencyLevel.class, "Select proficiency level");

            // Step 4: Add the Skill to the List
            skills.add(new Skill(category, selectedSkill, proficiency, false, Qualification.Importance.MEDIUM));
            System.out.println("✅ Skill added successfully!");
        }
    }

    public void displayAllQualifications(
            EducationLevel educationLevel,
            List<WorkExperience> workExperiences,
            List<LanguageProficiency> languageProficiencies,
            Map<SkillCategory, List<Skill>> userSkills) {

        if (educationLevel == null) {
            System.out.println("❌ No education qualifications added.\n");
        } else {
            System.out.println("==================== 🎓 Education ====================\n");

            System.out.printf("🎓 Degree: %s\n", educationLevel.getDegreeLevel());
            System.out.printf("📖 Field of Study: %s\n", educationLevel.getFieldOfStudy());
            System.out.printf("📊 CGPA: %.2f\n", educationLevel.getCgpa());
            System.out.println(" - University: " + educationLevel.getUniversity().getDisplayName());
           // System.out.printf("🔹 Importance: %s\n", educationLevel.getImportance());
            //System.out.printf("🔘 Optional: %s\n", educationLevel.isOptional() ? "Yes" : "No");
            System.out.println("---------------------------------------\n");
        }

        System.out.println("==================== 💼 WORK EXPERIENCE ====================\n");
        if (workExperiences.isEmpty()) {
            System.out.println("❌ No work experience added.\n");
        } else {
            for (WorkExperience work : workExperiences) {
                System.out.printf("🏢 Industry: %s\n", work.getIndustry());
                System.out.printf("⏳ Years of Experience: %d\n", work.getYears());
               // System.out.printf("🔹 Importance: %s\n", work.getImportance());
               // System.out.printf("🔘 Optional: %s\n", work.isOptional() ? "Yes" : "No");
                System.out.println("---------------------------------------\n");
            }
        }

        System.out.println("==================== 🗣 LANGUAGE PROFICIENCY ====================\n");
        if (languageProficiencies.isEmpty()) {
            System.out.println("❌ No language proficiencies added.\n");
        } else {
            for (LanguageProficiency lang : languageProficiencies) {
                System.out.printf("🗣 Language: %s\n", lang.getLanguage());
                System.out.printf("📈 Proficiency Level: %s\n", lang.getProficiency());
                //System.out.printf("🔹 Importance: %s\n", lang.getImportance());
               // System.out.printf("🔘 Optional: %s\n", lang.isOptional() ? "Yes" : "No");
                System.out.println("---------------------------------------\n");
            }
        }

        // 🔥 NEW: Display Skills
        System.out.println("==================== 🎯 SKILLS ====================\n");
        if (userSkills.isEmpty()) {
            System.out.println("❌ No skills added.\n");
        } else {
            for (Map.Entry<SkillCategory, List<Skill>> entry : userSkills.entrySet()) {
                SkillCategory category = entry.getKey();
                List<Skill> skills = entry.getValue();

                //System.out.println("📌 Category: " + category.name());
                if (skills.isEmpty()) {
                    System.out.println("  ❌ No skills selected.\n");
                } else {
                    for (Skill skill : skills) {
                        System.out.println("  - " + skill);
                    }
                    System.out.println(); // Add space for readability
                }
            }
        }
    }

//    public void displayAllQualifications(EducationLevel educationLevel,
//            List<WorkExperience> workExperiences,
//            List<LanguageProficiency> languageProficiencies) {
//        System.out.println("\n==================== 📚 EDUCATION QUALIFICATIONS ====================\n");
//
//        if (educationLevel == null) {
//            System.out.println("❌ No education qualifications added.\n");
//        } else {
//            System.out.printf("🎓 Degree: %s\n", educationLevel.getDegreeLevel());
//            System.out.printf("📖 Field of Study: %s\n", educationLevel.getFieldOfStudy());
//            System.out.printf("📊 CGPA: %.2f\n", educationLevel.getCgpa());
//            System.out.printf("🔹 Importance: %s\n", educationLevel.getImportance());
//            System.out.printf("🔘 Optional: %s\n", educationLevel.isOptional() ? "Yes" : "No");
//            System.out.println("---------------------------------------\n");
//        }
//
//        System.out.println("==================== 💼 WORK EXPERIENCE ====================\n");
//        if (workExperiences.isEmpty()) {
//            System.out.println("❌ No work experience added.\n");
//        } else {
//            for (WorkExperience work : workExperiences) {
//                System.out.printf("🏢 Industry: %s\n", work.getIndustry());
//                System.out.printf("⏳ Years of Experience: %d\n", work.getYears());
//                System.out.printf("🔹 Importance: %s\n", work.getImportance());
//                System.out.printf("🔘 Optional: %s\n", work.isOptional() ? "Yes" : "No");
//                System.out.println("---------------------------------------\n");
//            }
//        }
//
//        System.out.println("==================== 🗣 LANGUAGE PROFICIENCY ====================\n");
//        if (languageProficiencies.isEmpty()) {
//            System.out.println("❌ No language proficiencies added.\n");
//        } else {
//            for (LanguageProficiency lang : languageProficiencies) {
//                System.out.printf("🗣 Language: %s\n", lang.getLanguage());
//                System.out.printf("📈 Proficiency Level: %s\n", lang.getProficiency());
//                System.out.printf("🔹 Importance: %s\n", lang.getImportance());
//                System.out.printf("🔘 Optional: %s\n", lang.isOptional() ? "Yes" : "No");
//                System.out.println("---------------------------------------\n");
//            }
//        }
//    }
}

//package com.tarumt.boundary;
//
//import com.tarumt.entity.qualification.EducationLevel;
//import com.tarumt.entity.qualification.LanguageProficiency;
//import com.tarumt.entity.qualification.Qualification;
//import com.tarumt.entity.qualification.WorkExperience;
//import com.tarumt.utility.common.Input;
//import com.tarumt.utility.validation.DecimalCondition;
//import com.tarumt.utility.validation.IntegerCondition;
//import java.util.List;
//
//public class QualificationUI {
//
//    private final Input input;
//
//    public QualificationUI() {
//        this.input = new Input();
//    }
//
//    // 🔹 Generalized method for displaying enums
//    private <T extends Enum<T>> T getEnumSelection(Class<T> enumClass, String prompt) {
//        T[] values = enumClass.getEnumConstants();
//        IntegerCondition condition = new IntegerCondition().min(1).max(values.length);
//
//        // Display options
//        System.out.println("⇐ " + prompt + " ⇒");
//        for (int i = 0; i < values.length; i++) {
//            System.out.println((i + 1) + ". " + values[i]);
//        }
//
//        int choice = input.getInt("Select an option: ", condition);
//        return values[choice - 1];
//    }
//
//    public EducationLevel getEducationInput() {
//        DecimalCondition condition = new DecimalCondition().min(0.0).max(4.0);
//        System.out.println("⇐ Education & Academic Qualification ⇒");
//        EducationLevel.DegreeLevel degree = getEnumSelection(EducationLevel.DegreeLevel.class, "Select your highest level of education");
//        EducationLevel.FieldOfStudy field = getEnumSelection(EducationLevel.FieldOfStudy.class, "Select your field of study");
//
//        double cgpa = input.getDouble("Enter your CGPA: ", condition);
//        return new EducationLevel(degree, field, cgpa, false, Qualification.Importance.HIGH);
//    }
//
//    public WorkExperience getWorkExperienceInput() {
//        System.out.println("⇐ Work Experience ⇒");
//        WorkExperience.Industry industry = getEnumSelection(WorkExperience.Industry.class, "Select your industry");
//
//        IntegerCondition yearsCondition = new IntegerCondition().min(0).max(50);
//        int years = input.getInt("Enter years of experience: ", yearsCondition);
//        return new WorkExperience(industry, years, false, Qualification.Importance.MEDIUM);
//    }
//
//    public LanguageProficiency getLanguageProficiencyInput() {
//        System.out.println("⇐ Language Proficiency ⇒");
//        LanguageProficiency.Language language = getEnumSelection(LanguageProficiency.Language.class, "Select a language");
//        LanguageProficiency.Proficiency proficiency = getEnumSelection(LanguageProficiency.Proficiency.class, "Select proficiency level");
//
//        return new LanguageProficiency(language, proficiency, false, Qualification.Importance.LOW);
//    }
//
//    public void displayAllQualifications(List<EducationLevel> educationLevels,
//            List<WorkExperience> workExperiences,
//            List<LanguageProficiency> languageProficiencies) {
//        System.out.println("\n==================== 📚 EDUCATION QUALIFICATIONS ====================\n");
//        if (educationLevels.isEmpty()) {
//            System.out.println("❌ No education qualifications added.\n");
//        } else {
//            for (EducationLevel edu : educationLevels) {
//                System.out.printf("🎓 Degree: %s\n", edu.getDegreeLevel());
//                System.out.printf("📖 Field of Study: %s\n", edu.getFieldOfStudy());
//                System.out.printf("📊 CGPA: %.2f\n", edu.getCgpa()); // Display CGPA with 2 decimal places
//                System.out.printf("🔹 Importance: %s\n", edu.getImportance());
//                System.out.printf("🔘 Optional: %s\n", edu.isOptional() ? "Yes" : "No");
//                System.out.println("---------------------------------------\n");
//            }
//        }
//
//        System.out.println("==================== 💼 WORK EXPERIENCE ====================\n");
//        if (workExperiences.isEmpty()) {
//            System.out.println("❌ No work experience added.\n");
//        } else {
//            for (WorkExperience work : workExperiences) {
//                System.out.printf("🏢 Industry: %s\n", work.getIndustry());
//                System.out.printf("⏳ Years of Experience: %d\n", work.getYears());
//                System.out.printf("🔹 Importance: %s\n", work.getImportance());
//                System.out.printf("🔘 Optional: %s\n", work.isOptional() ? "Yes" : "No");
//                System.out.println("---------------------------------------\n");
//            }
//        }
//
//        System.out.println("==================== 🗣 LANGUAGE PROFICIENCY ====================\n");
//        if (languageProficiencies.isEmpty()) {
//            System.out.println("❌ No language proficiencies added.\n");
//        } else {
//            for (LanguageProficiency lang : languageProficiencies) {
//                System.out.printf("🗣 Language: %s\n", lang.getLanguage());
//                System.out.printf("📈 Proficiency Level: %s\n", lang.getProficiency());
//                System.out.printf("🔹 Importance: %s\n", lang.getImportance());
//                System.out.printf("🔘 Optional: %s\n", lang.isOptional() ? "Yes" : "No");
//                System.out.println("---------------------------------------\n");
//            }
//        }
//    }
//}
