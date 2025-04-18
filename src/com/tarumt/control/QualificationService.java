package com.tarumt.control;

import com.tarumt.boundary.QualificationUI;
import com.tarumt.entity.qualification.EducationLevel;
import com.tarumt.entity.qualification.LanguageProficiency;
import com.tarumt.entity.qualification.WorkExperience;
import com.tarumt.utility.common.Menu;
import com.tarumt.entity.qualification.Skill;

import com.tarumt.adt.list.List;
import com.tarumt.adt.list.DoublyLinkedList;

public class QualificationService {

    private final QualificationUI qualificationUI;
    private EducationLevel educationLevel;

    private final List<WorkExperience> workExperiences = new DoublyLinkedList<>();
    private final List<LanguageProficiency> languageProficiencies = new DoublyLinkedList<>();
    private final List<Skill> skills = new DoublyLinkedList<>();

    public QualificationService() {
        this.qualificationUI = new QualificationUI();
    }

    public void manageQualifications() {
        new Menu()
                .banner("================== QUALIFICATION MANAGEMENT ==================")
                .header("Select a Qualification Category")
                .choice(
                        new Menu.Choice("📚 Education & Academic", this::addEducation),
                        new Menu.Choice("💼 Work Experience", this::addWorkExperience),
                        new Menu.Choice("🗣 Language Proficiency", this::addLanguageProficiency),
                        new Menu.Choice("🎯 Skills", this::addSkills)
                )
                .exit("<Return>")
                .beforeEach(() -> System.out.println("============================================================"))
                .afterEach(() -> System.out.println("============================================================"))
                .run();
    }

    public void addEducation() {
        EducationLevel newEducation = qualificationUI.getEducationInput();
        if (newEducation == null) {
            System.out.println("❌ No education qualification was added.");
            return;
        }
        this.educationLevel = newEducation;
        System.out.println("✅ Education qualification updated successfully!");
    }

    public void addWorkExperience() {
        List<WorkExperience> workExperienceList = qualificationUI.getWorkExperienceInput();
        if (workExperienceList.isEmpty()) {
            System.out.println("❌ No work experience was added.");
            return;
        }
        workExperiences.addAll(workExperienceList);
        System.out.println("✅ Work experience added successfully!");
    }

    public void addLanguageProficiency() {
        List<LanguageProficiency> languageList = qualificationUI.getLanguageProficiencyInput();
        if (languageList.isEmpty()) {
            System.out.println("❌ No language proficiency was added.");
            return;
        }
        languageProficiencies.addAll(languageList);
        System.out.println("✅ Language proficiency added successfully!");
    }

    public void addSkills() {
        List<Skill> selectedSkills = qualificationUI.getSkillInput();

        if (selectedSkills != null && !selectedSkills.isEmpty()) {
            skills.addAll(selectedSkills);
            System.out.println("✅ Skills added successfully!");
        } else {
            System.out.println("❌ No skills added.");
        }
    }

    public void displayQualifications() {
        System.out.println("\n======================== QUALIFICATION SUMMARY ========================");
        qualificationUI.displayAllQualifications(educationLevel, workExperiences, languageProficiencies, skills);
        System.out.println("========================================================================\n");
    }
}
