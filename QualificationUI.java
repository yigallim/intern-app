package com.tarumt.boundary;

import com.tarumt.entity.qualification.EducationLevel;
import com.tarumt.entity.qualification.LanguageProficiency;
import com.tarumt.entity.qualification.Qualification;
import com.tarumt.entity.qualification.WorkExperience;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.validation.DecimalCondition;
import com.tarumt.utility.validation.IntegerCondition;
import com.tarumt.utility.validation.StringCondition;

import java.util.List;
import java.util.ArrayList;

public class QualificationUI {

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

        double cgpa = input.getDouble("Enter your CGPA (0.00 - 4.00): ", condition);

        return new EducationLevel(degree, field, Math.round(cgpa * 100.0) / 100.0, false, Qualification.Importance.HIGH);
    }

    public List<WorkExperience> getWorkExperienceInput() {
        List<WorkExperience> workExperiences = new ArrayList<>();
        StringCondition condition = new StringCondition().enumeration("Y", "X","y","x");

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
        StringCondition condition = new StringCondition().enumeration("Y", "X","y","x");
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

    public void displayAllQualifications(EducationLevel educationLevel,
            List<WorkExperience> workExperiences,
            List<LanguageProficiency> languageProficiencies) {
        System.out.println("\n==================== 📚 EDUCATION QUALIFICATIONS ====================\n");

        if (educationLevel == null) {
            System.out.println("❌ No education qualifications added.\n");
        } else {
            System.out.printf("🎓 Degree: %s\n", educationLevel.getDegreeLevel());
            System.out.printf("📖 Field of Study: %s\n", educationLevel.getFieldOfStudy());
            System.out.printf("📊 CGPA: %.2f\n", educationLevel.getCgpa());
            System.out.printf("🔹 Importance: %s\n", educationLevel.getImportance());
            System.out.printf("🔘 Optional: %s\n", educationLevel.isOptional() ? "Yes" : "No");
            System.out.println("---------------------------------------\n");
        }

        System.out.println("==================== 💼 WORK EXPERIENCE ====================\n");
        if (workExperiences.isEmpty()) {
            System.out.println("❌ No work experience added.\n");
        } else {
            for (WorkExperience work : workExperiences) {
                System.out.printf("🏢 Industry: %s\n", work.getIndustry());
                System.out.printf("⏳ Years of Experience: %d\n", work.getYears());
                System.out.printf("🔹 Importance: %s\n", work.getImportance());
                System.out.printf("🔘 Optional: %s\n", work.isOptional() ? "Yes" : "No");
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
                System.out.printf("🔹 Importance: %s\n", lang.getImportance());
                System.out.printf("🔘 Optional: %s\n", lang.isOptional() ? "Yes" : "No");
                System.out.println("---------------------------------------\n");
            }
        }
    }
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
