package com.tarumt.control;

import com.tarumt.boundary.QualificationUI;
import com.tarumt.entity.qualification.EducationLevel;
import com.tarumt.entity.qualification.LanguageProficiency;
import com.tarumt.entity.qualification.WorkExperience;
import com.tarumt.utility.common.Menu;
import com.tarumt.entity.qualification.Skill;
import com.tarumt.entity.qualification.Skill.SkillCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QualificationService {

    private final QualificationUI qualificationUI;
    private EducationLevel educationLevel;  // Store a single object instead of a list

    private final List<WorkExperience> workExperiences = new ArrayList<>();
    private final List<LanguageProficiency> languageProficiencies = new ArrayList<>();
    private final Map<SkillCategory, List<Skill>> skills = new HashMap<>();

    public QualificationService() {
        this.qualificationUI = new QualificationUI();
    }

    public void manageQualifications() {
        new Menu()
                .banner("Qualification Management")
                .header("Select a Qualification Category ⇒")
                .choice(
                        new Menu.Choice("📚 Education & Academic", this::addEducation),
                        new Menu.Choice("💼 Work Experience", this::addWorkExperience),
                        new Menu.Choice("🗣 Language Proficiency", this::addLanguageProficiency),
                        new Menu.Choice("🗣 Skill", this::addSkills)
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void addEducation() {
        EducationLevel newEducation = qualificationUI.getEducationInput();
        if (newEducation == null) {  // If user pressed X, do nothing
            System.out.println("❌ No education qualification was added.");
            return;
        }
        this.educationLevel = newEducation;  // Replace existing education level
        System.out.println("✅ Education qualification updated successfully!");
    }

    public void addWorkExperience() {
        List<WorkExperience> workExperienceList = qualificationUI.getWorkExperienceInput();  // Returns a List
        if (workExperienceList.isEmpty()) {  // If user pressed X, do nothing
            System.out.println("❌ No work experience was added.");
            return;
        }
        workExperiences.addAll(workExperienceList);  // Add all elements to the main list
        System.out.println("✅ Work experience added successfully!");
    }

    public void addLanguageProficiency() {
        List<LanguageProficiency> languageList = qualificationUI.getLanguageProficiencyInput();  // Returns a List
        if (languageList.isEmpty()) {  // If user pressed X, do nothing
            System.out.println("❌ No language proficiency was added.");
            return;
        }
        languageProficiencies.addAll(languageList);  // Add all elements to the main list
        System.out.println("✅ Language proficiency added successfully!");
    }

    public void addSkills() {
        List<Skill> selectedSkills = qualificationUI.getSkillInput(); // Get skills from user input

        if (selectedSkills != null && !selectedSkills.isEmpty()) {
            for (Skill skill : selectedSkills) {
                skills.computeIfAbsent(skill.getCategory(), k -> new ArrayList<>()).add(skill);
            }
            System.out.println("✅ Skills added successfully!");
        } else {
            System.out.println("❌ No skills added.");
        }
    }

    public void displayQualifications() {
        qualificationUI.displayAllQualifications(educationLevel, workExperiences, languageProficiencies, skills);
    }
}
