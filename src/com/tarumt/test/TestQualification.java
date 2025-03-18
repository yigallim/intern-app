package com.tarumt.test;

import com.tarumt.entity.qualification.EducationLevel;
import com.tarumt.entity.qualification.LanguageProficiency;
import com.tarumt.entity.qualification.WorkExperience;
import com.tarumt.utility.pretty.annotation.ExcludeKey;

import java.util.List;

public class TestQualification {
    public static void main(String[] args) {
        EducationLevel educationLevel = null;
        List<WorkExperience> workExperiences = null;
        List<LanguageProficiency> languageProficiencies = null;

        // input qualifications
        // ...

        System.out.println(educationLevel);
        System.out.println(workExperiences);
        System.out.println(languageProficiencies);

    }
}
