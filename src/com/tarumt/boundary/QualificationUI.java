package com.tarumt.boundary;

import com.tarumt.dao.Initializer;

import com.tarumt.entity.qualification.EducationLevel;
import com.tarumt.entity.qualification.EducationLevel.University;
import com.tarumt.entity.qualification.LanguageProficiency;
import com.tarumt.entity.qualification.Qualification;
import com.tarumt.entity.qualification.WorkExperience;
import com.tarumt.entity.qualification.Skill;
import com.tarumt.entity.qualification.Skill.SkillCategory;
import com.tarumt.entity.qualification.SkillOption;

import com.tarumt.utility.common.Input;
import com.tarumt.utility.validation.DecimalCondition;
import com.tarumt.utility.validation.IntegerCondition;
import com.tarumt.utility.validation.StringCondition;


import com.tarumt.adt.list.List;
import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.Arrays;

public class QualificationUI {

    private static final List<SkillOption> SKILL_OPTIONS = Initializer.list;

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

        EducationLevel.DegreeLevel degree = getEnumSelection(EducationLevel.DegreeLevel.class,
                "Select your highest level of education");
        EducationLevel.FieldOfStudy field = getEnumSelection(EducationLevel.FieldOfStudy.class,
                "Select your field of study");
        // Step 3: Select University (Using Enum)
        University university = getEnumSelection(University.class,
                "Select your university");
        double cgpa = input.getDouble("Enter your CGPA (0.00 - 4.00): ", condition);

        return new EducationLevel(degree, field, university, Math.round(cgpa * 100.0) / 100.0, false, Qualification.Importance.HIGH);
    }

    public List<WorkExperience> getWorkExperienceInput() {
        List<WorkExperience> workExperiences = new DoublyLinkedList<>();
        StringCondition condition = new StringCondition().enumeration("Y", "X", "y", "x");

        while (true) {
            System.out.println("\n⇐ Work Experience ⇒");
            WorkExperience.Industry industry = getEnumSelection(WorkExperience.Industry.class,
                    "Select your industry");

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
        List<LanguageProficiency> languageProficiencies = new DoublyLinkedList<>();
        StringCondition condition = new StringCondition().enumeration("Y", "X", "y", "x");
        while (true) {
            System.out.println("\n⇐ Language Proficiency ⇒");
            LanguageProficiency.Language language = getEnumSelection(LanguageProficiency.Language.class,
                    "Select a language");
            LanguageProficiency.Proficiency proficiency = getEnumSelection(LanguageProficiency.Proficiency.class,
                    "Select proficiency level");

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
        List<Skill> skills = new DoublyLinkedList<>();
        IntegerCondition yesNoCondition = new IntegerCondition().enumeration(0, 1);

        while (true) {
            System.out.println("\n⇐ Add a Skill ⇒");
            int proceed = input.getInt("Press 1 to continue, 0 to exit: ", yesNoCondition);
            if (proceed == 0) {
                break;
            }

            // Step 1: Select Category
            Skill.SkillCategory category = getEnumSelection(Skill.SkillCategory.class, "Select a skill category");

            // Step 2: Get all skills in that category from Initializer
            List<SkillOption> availableSkills = Initializer.list.filter(opt -> opt.getCategory() == category);

            if (availableSkills.isEmpty()) {
                System.out.println("❌ No skills available for this category.");
                continue;
            }

            // Step 3: Show skill options
            System.out.println("\nAvailable Skills:");
            for (int i = 0; i < availableSkills.size(); i++) {
                System.out.println((i + 1) + ". " + availableSkills.get(i).getName());
            }

            // Step 4: Let user pick a skill
            IntegerCondition skillChoiceCond = new IntegerCondition().min(1).max(availableSkills.size());
            int skillIndex = input.getInt("Select a skill: ", skillChoiceCond);
            String selectedSkillName = availableSkills.get(skillIndex - 1).getName();

            // Step 5: Select proficiency
            Skill.ProficiencyLevel proficiency = getEnumSelection(Skill.ProficiencyLevel.class, "Select proficiency level");

            // Step 6: Create Skill and add to list
            Skill skill = new Skill(category, selectedSkillName, proficiency, true, Qualification.Importance.MEDIUM);
            skills.add(skill);

            System.out.println("✅ Skill added: " + selectedSkillName + " (" + proficiency + ")");
        }
        return skills;

    }

    public void displayAllQualifications(
            EducationLevel educationLevel,
            List<WorkExperience> workExperiences,
            List<LanguageProficiency> languageProficiencies,
            List<Skill> userSkills) {

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
            for (Skill skill : userSkills) {
                System.out.printf("🛠 Category: %s\n", skill.getCategory());
                System.out.printf("🔹 Skill: %s\n", skill.getSkillName());
                System.out.printf("📈 Proficiency: %s\n", skill.getProficiencyLevel());
                //System.out.printf("🔘 Optional: %s\n", skill.isOptional() ? "Yes" : "No");
               // System.out.printf("⭐ Importance: %s\n", skill.getImportance());
                System.out.println("---------------------------------------\n");
            }
        }
    }
}
