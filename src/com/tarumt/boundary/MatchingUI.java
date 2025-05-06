/**
 * @author Choo Zhen Hao
 */

package com.tarumt.boundary;

import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.list.ListInterface;
import com.tarumt.control.MatchingController;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.JobPosting;
import com.tarumt.entity.location.City;
import com.tarumt.entity.qualification.*;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Menu;
import com.tarumt.utility.pretty.TabularPrint;
import com.tarumt.utility.validation.DecimalCondition;
import com.tarumt.utility.validation.IntegerCondition;
import com.tarumt.utility.validation.StringCondition;

public class MatchingUI {
    private final Input input;

    public MatchingUI(Input input) {
        this.input = input;
    }

    private <T extends Enum<T>> T getEnumSelection(Class<T> enumClass, String prompt) {
        return input.getEnum("\n⇐ " + prompt + " ⇒", enumClass);
    }

    public void modifyQualificationMenu(JobPosting jobPosting) {
        MatchingController controller = MatchingController.getInstance();

        System.out.println();

        new Menu()
                .header("==> Modify Qualification <==")
                .choice(
                        new Menu.Choice("🎓 Set Education Level",
                                () -> controller.setEducationLevel(jobPosting)),
                        new Menu.Choice("🗣️ Set Language Proficiency",
                                () -> controller.setLanguageProficiency(jobPosting)),
                        new Menu.Choice("🛠️ Set Skill",
                                () -> controller.setSkill(jobPosting)),
                        new Menu.Choice("📁 Set Work Experience",
                                () -> controller.setWorkExperience(jobPosting))
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();

        System.out.println();
    }

    public void jobQualificationMenu() {
        MatchingController controller = MatchingController.getInstance();

        new Menu()
                .header("==> Manage Qualification <==")
                .choice(
                        new Menu.Choice("🎓 Modify Job Qualification", controller::modifyJobQualification),
                        new Menu.Choice("📄 Display Job Qualification", controller::displayJobQualification),
                        new Menu.Choice("🔍 Check Applicant Qualification", controller::checkApplicantQualification),
                        new Menu.Choice("🛠️ Search Applicant with Certain Skills", controller::searchApplicant),
                        new Menu.Choice("📊 View Matched Qualification", controller::viewMatchApplications),
                        new Menu.Choice("📈 View Matched Report", controller::viewMatchReport),
                        new Menu.Choice("📈 View Matched Report (Detailed)", controller::viewDetailedMatchReport)
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void applicantQualificationMenu() {
        MatchingController controller = MatchingController.getInstance();

        new Menu()
                .header("==> Manage Qualification <==")
                .choice(
                        new Menu.Choice("🎓 Modify Qualification", controller::modifyApplicantQualification),
                        new Menu.Choice("🗣️ Display Qualification", controller::displayApplicantQualification),
                        new Menu.Choice("🔍 Check Job Posting Qualification Requirement", controller::checkJobQualification)
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void modifyQualificationMenu(Applicant applicant) {
        MatchingController controller = MatchingController.getInstance();

        new Menu()
                .header("==> Add Qualification <==")
                .choice(
                        new Menu.Choice("🎓 Set Education Level",
                                () -> controller.setEducationLevel(applicant)),
                        new Menu.Choice("🗣️ Set Language Proficiency",
                                () -> controller.setLanguageProficiency(applicant)),
                        new Menu.Choice("🛠️ Set Skill",
                                () -> controller.setSkill(applicant)),
                        new Menu.Choice("📁 Set Work Experience",
                                () -> controller.setWorkExperience(applicant))
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public JobPosting getJobPostingToAddQualification(ListInterface<JobPosting> employerJobPostings, String title) {
        if (employerJobPostings.isEmpty()) {
            Log.info("No job posting found");
            input.clickAnythingToContinue();
            return null;
        }
        System.out.println("<== " + title + " [ X to Exit ] ==>");
        return input.getObjectFromList("|\n| Select Job Posting =>", employerJobPostings, 40);
    }

    public EducationLevel getEducationInput() {
        DecimalCondition condition = new DecimalCondition().min(0.0).max(4.0);
        System.out.println("\n⇐ Education & Academic Qualification ⇒");

        EducationLevel.DegreeLevel degree = getEnumSelection(EducationLevel.DegreeLevel.class, "Select your highest level of education");
        if (degree == null) return null;
        EducationLevel.FieldOfStudy field = getEnumSelection(EducationLevel.FieldOfStudy.class, "Select your field of study");
        if (field == null) return null;
        EducationLevel.University university = getEnumSelection(EducationLevel.University.class, "Select your university");
        if (university == null) return null;
        double cgpa = input.getDouble("Enter your CGPA (0.00 - 4.00): ", condition);
        if (cgpa == Input.DOUBLE_EXIT_VALUE) return null;

        if (Context.isEmployer()) {
            int optionalInput = getOptionalInput();
            if (optionalInput == Input.INT_EXIT_VALUE) return null;
            boolean optional = optionalInput == 1;
            Qualification.Importance importance = getImportanceInput();
            if (importance == null) return null;
            return new EducationLevel(degree, field, university, Math.round(cgpa * 100.0) / 100.0, optional, importance);
        } else {
            return new EducationLevel(degree, field, university, Math.round(cgpa * 100.0) / 100.0, false, Qualification.Importance.HIGH);
        }
    }

    public ListInterface<WorkExperience> getWorkExperienceInput() {
        ListInterface<WorkExperience> workExperiences = new DoublyLinkedList<>();
        StringCondition condition = new StringCondition().enumeration("Y", "X", "y", "x");

        while (true) {
            System.out.println("\n⇐ Work Experience ⇒");
            WorkExperience.Industry industry = getEnumSelection(WorkExperience.Industry.class, "Select your industry");
            if (industry == null) return workExperiences;

            IntegerCondition yearsCondition = new IntegerCondition().min(0).max(50);
            int years = input.getInt("Enter years of experience (0 - 50): ", yearsCondition);
            if (years == Input.INT_EXIT_VALUE) return workExperiences;

            boolean optional = false;
            Qualification.Importance importance = Qualification.Importance.MEDIUM;

            if (Context.isEmployer()) {
                int optionalInput = getOptionalInput();
                if (optionalInput == Input.INT_EXIT_VALUE) return workExperiences;
                optional = optionalInput == 1;
                importance = getImportanceInput();
                if (importance == null) return workExperiences;
            }

            workExperiences.add(new WorkExperience(industry, years, optional, importance));
            System.out.println("✅ Work Experience added successfully!");

            String continueChoice = input.getString("Continue to add another work experience? [Y / X] => ", condition);
            if (!continueChoice.equalsIgnoreCase("Y")) {
                break;
            }
        }
        return workExperiences;
    }

    public ListInterface<LanguageProficiency> getLanguageProficiencyInput() {
        ListInterface<LanguageProficiency> languageProficiencies = new DoublyLinkedList<>();
        StringCondition condition = new StringCondition().enumeration("Y", "X", "y", "x");

        while (true) {
            System.out.println("\n⇐ Language Proficiency ⇒");
            LanguageProficiency.Language language = getEnumSelection(LanguageProficiency.Language.class, "Select language");
            if (language == null) return languageProficiencies;

            LanguageProficiency.Proficiency proficiency = getEnumSelection(LanguageProficiency.Proficiency.class, "Select proficiency level");
            if (proficiency == null) return languageProficiencies;

            boolean optional = false;
            Qualification.Importance importance = Qualification.Importance.MEDIUM;

            if (Context.isEmployer()) {
                int optionalInput = getOptionalInput();
                if (optionalInput == Input.INT_EXIT_VALUE) return languageProficiencies;
                optional = optionalInput == 1;
                importance = getImportanceInput();
                if (importance == null) return languageProficiencies;
            }

            languageProficiencies.add(new LanguageProficiency(language, proficiency, optional, importance));
            System.out.println("✅ Language Proficiency added successfully!");

            String continueChoice = input.getString("Continue to add another language proficiency? [Y / X] => ", condition);
            if (!continueChoice.equalsIgnoreCase("Y")) {
                break;
            }
        }
        return languageProficiencies;
    }

    public ListInterface<Skill> getSkillInput() {
        ListInterface<Skill> skills = new DoublyLinkedList<>();
        StringCondition condition = new StringCondition().enumeration("Y", "X", "y", "x");

        while (true) {
            System.out.println("\n⇐ Add a Skill ⇒");
            JobPosting.Type category = getEnumSelection(JobPosting.Type.class, "Select a skill category");
            if (category == null) return skills;

            ListInterface<SkillOption> availableSkills = Initializer.getSkillOptions().filter(opt -> opt.getCategory() == category);

            SkillOption skillOption = input.getObjectFromList("Select a skill: ", availableSkills, 40, 2);
            if (skillOption == null) return skills;
            String selectedSkillName = skillOption.getName();

            Skill.ProficiencyLevel proficiency = getEnumSelection(Skill.ProficiencyLevel.class, "Select proficiency level");
            if (proficiency == null) return skills;

            boolean optional = false;
            Qualification.Importance importance = Qualification.Importance.MEDIUM;

            if (Context.isEmployer()) {
                int optionalInput = getOptionalInput();
                if (optionalInput == Input.INT_EXIT_VALUE) return skills;
                optional = optionalInput == 1;
                importance = getImportanceInput();
                if (importance == null) return skills;
            }
            skills.add(new Skill(category, selectedSkillName, proficiency, optional, importance));
            System.out.println("✅ Skill added: " + selectedSkillName + " (" + proficiency + ")");

            String cont = input.getString("Continue to add skill? [Y / X] => ", condition);
            if (!cont.equalsIgnoreCase("Y")) {
                break;
            }
        }
        return skills;
    }

    public void displayAllQualifications(EducationLevel educationLevel,
                                         ListInterface<WorkExperience> workExperiences,
                                         ListInterface<LanguageProficiency> languageProficiencies,
                                         ListInterface<Skill> userSkills) {

        System.out.println("\n==================== 👤 QUALIFICATION SUMMARY ====================\n");

        // 🎓 Education
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

        // 💼 Work Experience
        System.out.println("💼 Work Experience:");
        if (workExperiences == null || workExperiences.isEmpty()) {
            System.out.println("❌ No work experience added.\n");
        } else {
            for (WorkExperience work : workExperiences) {
                System.out.printf("   🏭 Industry: %-25s\n", work.getIndustry());
                System.out.printf("   ⏱️ Years: %-2d\n", work.getYears());
                System.out.println("--------------------------------------------------------------\n");
            }
        }

        // 🗣 Language Proficiency
        System.out.println("🗣 Language Proficiency:");
        if (languageProficiencies == null || languageProficiencies.isEmpty()) {
            System.out.println("❌ No language proficiencies added.\n");
        } else {
            for (LanguageProficiency lang : languageProficiencies) {
                System.out.printf("   🌐 Language: %-20s\n", lang.getLanguage());
                System.out.printf("   🎯 Proficiency: %-15s\n", lang.getProficiency());
                System.out.println("--------------------------------------------------------------\n");
            }
        }

        // 🎯 Skills
        System.out.println("🎯 Skills:");
        if (userSkills == null || userSkills.isEmpty()) {
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
        input.clickAnythingToContinue(); // Added pause
    }

    public void displayAllQualificationsWithDetails(EducationLevel educationLevel,
                                                    ListInterface<WorkExperience> workExperiences,
                                                    ListInterface<LanguageProficiency> languageProficiencies,
                                                    ListInterface<Skill> userSkills) {

        System.out.println("\n==================== 👤 DETAILED QUALIFICATION SUMMARY ====================\n");

        // 🎓 Education
        if (educationLevel == null) {
            System.out.println("❌ No education qualifications added.\n");
        } else {
            System.out.println("🎓 Education:");
            System.out.printf("   📘 Degree: %-20s\n", educationLevel.getDegreeLevel());
            System.out.printf("   📚 Field of Study: %-15s\n", educationLevel.getFieldOfStudy());
            System.out.printf("   🏫 University: %-25s\n", educationLevel.getUniversity().getDisplayName());
            System.out.printf("   📊 CGPA: %.2f\n", educationLevel.getCgpa());
            System.out.printf("   ❗ Importance: %-10s\n", educationLevel.getImportance());
            System.out.printf("   ❓ Optional: %-5s\n", educationLevel.isOptional() ? "Yes" : "No");
            System.out.println("------------------------------------------------------------------------\n");
        }

        // 💼 Work Experience
        System.out.println("💼 Work Experience:");
        if (workExperiences == null || workExperiences.isEmpty()) {
            System.out.println("❌ No work experience added.\n");
        } else {
            for (WorkExperience work : workExperiences) {
                System.out.printf("   🏭 Industry: %-25s\n", work.getIndustry());
                System.out.printf("   ⏱️ Years: %-2d\n", work.getYears());
                System.out.printf("   ❗ Importance: %-10s\n", work.getImportance());
                System.out.printf("   ❓ Optional: %-5s\n", work.isOptional() ? "Yes" : "No");
                System.out.println("------------------------------------------------------------------------\n");
            }
        }

        // 🗣 Language Proficiency
        System.out.println("🗣 Language Proficiency:");
        if (languageProficiencies == null || languageProficiencies.isEmpty()) {
            System.out.println("❌ No language proficiencies added.\n");
        } else {
            for (LanguageProficiency lang : languageProficiencies) {
                System.out.printf("   🌐 Language: %-20s\n", lang.getLanguage());
                System.out.printf("   🎯 Proficiency: %-15s\n", lang.getProficiency());
                System.out.printf("   ❗ Importance: %-10s\n", lang.getImportance());
                System.out.printf("   ❓ Optional: %-5s\n", lang.isOptional() ? "Yes" : "No");
                System.out.println("------------------------------------------------------------------------\n");
            }
        }

        // 🎯 Skills
        System.out.println("🎯 Skills:");
        if (userSkills == null || userSkills.isEmpty()) {
            System.out.println("❌ No skills added.\n");
        } else {
            for (Skill skill : userSkills) {
                System.out.printf("   🛠 Category: %-20s\n", skill.getSkillType());
                System.out.printf("   🔧 Skill: %-25s\n", skill.getSkillName());
                System.out.printf("   📈 Proficiency: %-15s\n", skill.getProficiencyLevel());
                System.out.printf("   ❗ Importance: %-10s\n", skill.getImportance());
                System.out.printf("   ❓ Optional: %-5s\n", skill.isOptional() ? "Yes" : "No");
                System.out.println("------------------------------------------------------------------------\n");
            }
        }

        System.out.println("========================================================================\n");
        input.clickAnythingToContinue(); // Added pause
    }

    public String getSearchQuery() {
        StringCondition condition = new StringCondition();
        return input.getString("Enter skill keyword to search applicants (e.g., java, network): ", condition);
    }

    public void printSearchResult(ListInterface<MatchingController.MatchedApplicant> matchedList, String keyword, ListInterface<String> matches) {
        if (matchedList.isEmpty()) {
            System.out.println("❌ No applicants found with skills matching: " + keyword);
            return;
        }
        TabularPrint.printTabular(matchedList, true, matches);
    }

    private int getOptionalInput() {
        StringCondition condition = new StringCondition().enumeration("Y", "N", "y", "n");
        String choice = input.withoutExitKey().getString("Is this qualification optional? [Y/N]: ", condition);
        if (choice.equalsIgnoreCase("Y")) {
            return 1;
        }
        if (choice.equalsIgnoreCase("N")) {
            return 0;
        }
        return -1;
    }

    private Qualification.Importance getImportanceInput() {
        return input.getEnum("\n⇐ Select importance level ⇒", Qualification.Importance.class);
    }

    public Applicant getApplicantChoice(ListInterface<Applicant> applicants) {
        return input.getObjectFromList("|\n| Select a applicant => ", applicants);
    }
}
