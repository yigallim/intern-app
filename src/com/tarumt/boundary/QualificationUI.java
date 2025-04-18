package com.tarumt.boundary;

import com.tarumt.dao.Initializer;
import com.tarumt.entity.qualification.*;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.validation.DecimalCondition;
import com.tarumt.utility.validation.IntegerCondition;
import com.tarumt.utility.validation.StringCondition;
import com.tarumt.adt.list.List;
import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.Arrays;
import com.tarumt.entity.JobPosting;

public class QualificationUI {

    private final Input input;

    public QualificationUI() {
        this.input = new Input();
    }

    private <T extends Enum<T>> T getEnumSelection(Class<T> enumClass, String prompt) {
        T[] values = enumClass.getEnumConstants();
        IntegerCondition condition = new IntegerCondition().min(1).max(values.length);

        System.out.println("\n⇐ " + prompt + " ⇒");

        int columns = 3;
        for (int i = 0; i < values.length; i++) {
            System.out.printf("%-45s", String.format("%2d. %-40s", (i + 1), values[i]));
            if ((i + 1) % columns == 0 || i == values.length - 1) {
                System.out.println();
            }
        }

        int choice = input.getInt("\nSelect an option: ", condition);
        return values[choice - 1];
    }

    public EducationLevel getEducationInput() {
        DecimalCondition condition = new DecimalCondition().min(0.0).max(4.0);
        System.out.println("\n⇐ Education & Academic Qualification ⇒");

        EducationLevel.DegreeLevel degree = getEnumSelection(EducationLevel.DegreeLevel.class, "Select your highest level of education");
        EducationLevel.FieldOfStudy field = getEnumSelection(EducationLevel.FieldOfStudy.class, "Select your field of study");
        EducationLevel.University university = getEnumSelection(EducationLevel.University.class, "Select your university");
        double cgpa = input.getDouble("Enter your CGPA (0.00 - 4.00): ", condition);

        return new EducationLevel(degree, field, university, Math.round(cgpa * 100.0) / 100.0, false, Qualification.Importance.HIGH);
    }

    public List<WorkExperience> getWorkExperienceInput() {
        List<WorkExperience> workExperiences = new DoublyLinkedList<>();
        StringCondition condition = new StringCondition().enumeration("Y", "X", "y", "x");

        while (true) {
            System.out.println("\n⇐ Work Experience ⇒");
            WorkExperience.Industry industry = getEnumSelection(WorkExperience.Industry.class, "Select your industry");
            IntegerCondition yearsCondition = new IntegerCondition().min(0).max(50);
            int years = input.getInt("Enter years of experience (0 - 50): ", yearsCondition);

            workExperiences.add(new WorkExperience(industry, years, false, Qualification.Importance.MEDIUM));
            System.out.println("✅ Work Experience added successfully!");

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
            LanguageProficiency.Language language = getEnumSelection(LanguageProficiency.Language.class, "Select a language");
            LanguageProficiency.Proficiency proficiency = getEnumSelection(LanguageProficiency.Proficiency.class, "Select proficiency level");

            languageProficiencies.add(new LanguageProficiency(language, proficiency, false, Qualification.Importance.LOW));
            System.out.println("✅ Language Proficiency added successfully!");

            String continueChoice = input.getString("Continue to add another language proficiency? [Y / X] => ", condition);
            if (!continueChoice.equalsIgnoreCase("Y")) {
                break;
            }
        }
        return languageProficiencies;
    }

    public List<Skill> getSkillInput() {
        List<Skill> skills = new DoublyLinkedList<>();
        StringCondition condition = new StringCondition().enumeration("Y", "X", "y", "x");

        while (true) {
            System.out.println("\n⇐ Add a Skill ⇒");
            String cont = input.getString("Continue to add skill? [Y / X] => ", condition);
            if (!cont.equalsIgnoreCase("Y")) {
                break;
            }

            JobPosting.Type category = getEnumSelection(JobPosting.Type.class, "Select a skill category");
            List<SkillOption> availableSkills = Initializer.list.filter(opt -> opt.getCategory() == category);

            if (availableSkills.isEmpty()) {
                System.out.println("❌ No skills available for this category.");
                continue;
            }

            System.out.println("\nAvailable Skills:");
            for (int i = 0; i < availableSkills.size(); i++) {
                System.out.println((i + 1) + ". " + availableSkills.get(i).getName());
            }

            IntegerCondition skillChoiceCond = new IntegerCondition().min(1).max(availableSkills.size());
            int skillIndex = input.getInt("Select a skill: ", skillChoiceCond);
            String selectedSkillName = availableSkills.get(skillIndex - 1).getName();

            Skill.ProficiencyLevel proficiency = getEnumSelection(Skill.ProficiencyLevel.class, "Select proficiency level");

            Skill skill = new Skill(category, selectedSkillName, proficiency, true, Qualification.Importance.MEDIUM);
            skills.add(skill);

            System.out.println("✅ Skill added: " + selectedSkillName + " (" + proficiency + ")");
        }
        return skills;
    }

    public void displayAllQualifications(EducationLevel educationLevel, List<WorkExperience> workExperiences, List<LanguageProficiency> languageProficiencies, List<Skill> userSkills) {
        System.out.println("\n==================== 👤 QUALIFICATION SUMMARY ====================\n");

        if (educationLevel == null) {
            System.out.println("❌ No education qualifications added.\n");
        } else {
            System.out.println("🎓 Education:");
            System.out.printf("   📘 Degree: %-20s\n", educationLevel.getDegreeLevel());
            System.out.printf("   📚 Field of Study: %-15s\n", educationLevel.getFieldOfStudy());
            System.out.printf("   🏫 University: %-25s\n", educationLevel.getUniversity().getDisplayName());
            System.out.printf("   📊 CGPA: %.2f\n", educationLevel.getCgpa());
            System.out.println("--------------------------------------------------------------\n");
        }

        System.out.println("💼 Work Experience:");
        if (workExperiences.isEmpty()) {
            System.out.println("❌ No work experience added.\n");
        } else {
            for (WorkExperience work : workExperiences) {
                System.out.printf("   🏭 Industry: %-25s\n", work.getIndustry());
                System.out.printf("   ⏱️ Years: %-2d\n", work.getYears());
                System.out.println("--------------------------------------------------------------\n");
            }
        }

        System.out.println("🗣 Language Proficiency:");
        if (languageProficiencies.isEmpty()) {
            System.out.println("❌ No language proficiencies added.\n");
        } else {
            for (LanguageProficiency lang : languageProficiencies) {
                System.out.printf("   🌐 Language: %-20s\n", lang.getLanguage());
                System.out.printf("   🎯 Proficiency: %-15s\n", lang.getProficiency());
                System.out.println("--------------------------------------------------------------\n");
            }
        }

        System.out.println("🎯 Skills:");
        if (userSkills.isEmpty()) {
            System.out.println("❌ No skills added.\n");
        } else {
            for (Skill skill : userSkills) {
                System.out.printf("   🛠 Category: %-20s\n", skill.getSkillType());
                System.out.printf("   🔧 Skill: %-25s\n", skill.getSkillName());
                System.out.printf("   📈 Proficiency: %-15s\n", skill.getProficiencyLevel());
                System.out.println("--------------------------------------------------------------\n");
            }
        }
        System.out.println("===============================================================\n");
    }
}
