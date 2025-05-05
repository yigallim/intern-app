package com.tarumt.utility.matching;

import com.tarumt.adt.list.ListInterface;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.JobPosting;
import com.tarumt.entity.location.Location;
import com.tarumt.entity.qualification.*;

public class JobMatchingUtil {
    public static void showScoreBreakdown(JobPosting job, Applicant applicant) {
        System.out.println("🔍 Score Breakdown for Applicant: " + applicant.getName());

        double totalScore = 0.0;

        // --- Education ---
        EducationLevel jEdu = job.getEducationLevel();
        EducationLevel aEdu = applicant.getEducationLevel();
        double eduScore = 0.0;
        if (jEdu != null && aEdu != null) {
            if (jEdu.getDegreeLevel() == aEdu.getDegreeLevel()) {
                double partial = applyWeight(1.0, jEdu.getImportance());
                eduScore += partial;
                System.out.printf("🎓 Degree Match: %.2f\n", partial);
            }
            if (jEdu.getFieldOfStudy() == aEdu.getFieldOfStudy()) {
                double partial = applyWeight(1.0, jEdu.getImportance());
                eduScore += partial;
                System.out.printf("📚 Field Match: %.2f\n", partial);
            }
            double cgpaScore = aEdu.getCgpa() / jEdu.getCgpa();
            double weightedCgpa = applyWeight(cgpaScore, jEdu.getImportance());
            eduScore += weightedCgpa;
            System.out.printf("🎯 CGPA Match: %.2f (%.2f / %.2f) → %.2f\n", cgpaScore, aEdu.getCgpa(), jEdu.getCgpa(), weightedCgpa);
        }
        totalScore += eduScore;
        System.out.printf("✅ Total Education Score: %.2f\n", eduScore);

        // --- Work Experience ---
        double workScore = 0.0;
        ListInterface<WorkExperience> requiredExp = job.getWorkExperiences();
        ListInterface<WorkExperience> applicantExp = applicant.getWorkExperiences();
        if (requiredExp != null && applicantExp != null) {
            for (WorkExperience req : requiredExp) {
                for (WorkExperience exp : applicantExp) {
                    if (req.getIndustry() == exp.getIndustry()) {
                        double raw = Math.min(exp.getYears(), req.getYears());
                        double partial = applyWeight(raw, req.getImportance());
                        workScore += partial;
                        System.out.printf("💼 Work Exp Match [%s]: %.2f yrs → %.2f\n", req.getIndustry(), raw, partial);
                        break;
                    }
                }
            }
        }
        totalScore += workScore;
        System.out.printf("✅ Total Work Experience Score: %.2f\n", workScore);

        // --- Language Proficiency ---
        double langScore = 0.0;
        ListInterface<LanguageProficiency> requiredLangs = job.getLanguageProficiencies();
        ListInterface<LanguageProficiency> applicantLangs = applicant.getLanguageProficiencies();
        if (requiredLangs != null && applicantLangs != null) {
            for (LanguageProficiency req : requiredLangs) {
                for (LanguageProficiency lang : applicantLangs) {
                    if (req.getLanguage() == lang.getLanguage()) {
                        double raw = req.scoreMatch(lang);
                        double partial = applyWeight(raw, req.getImportance());
                        langScore += partial;
                        System.out.printf("🗣️ Language Match [%s]: %.2f → %.2f\n", req.getLanguage(), raw, partial);
                        break;
                    }
                }
            }
        }
        totalScore += langScore;
        System.out.printf("✅ Total Language Score: %.2f\n", langScore);

        // --- Skills ---
        double skillScore = 0.0;
        ListInterface<Skill> requiredSkills = job.getSkills();
        ListInterface<Skill> applicantSkills = applicant.getSkills();
        if (requiredSkills != null && applicantSkills != null) {
            for (Skill req : requiredSkills) {
                for (Skill s : applicantSkills) {
                    if (req.getSkillName().equalsIgnoreCase(s.getSkillName())) {
                        double raw = req.scoreMatch(s);
                        double partial = applyWeight(raw, req.getImportance());
                        skillScore += partial;
                        System.out.printf("🧠 Skill Match [%s]: %.2f → %.2f\n", req.getSkillName(), raw, partial);
                        break;
                    }
                }
            }
        }
        totalScore += skillScore;
        System.out.printf("✅ Total Skill Score: %.2f\n", skillScore);

        // --- Location ---
        double locScore = 0.0;
        Location jobLoc = job.getCompany().getLocation();
        Location appLoc = applicant.getLocation();
        if (jobLoc != null && appLoc != null) {
            double distance = jobLoc.distanceTo(appLoc);
            if (distance <= 20) {
                locScore = 1.0;
            } else if (distance <= 50) {
                locScore = 0.5;
            } else {
                locScore = 0.0;
            }
            System.out.printf("📍 Location Distance: %.2f km → Score: %.2f\n", distance, locScore);
        }
        totalScore += locScore;

        // === Total ===
        System.out.printf("📊 ✅ Final Total Score: %.2f\n", totalScore);
    }

    private static int getImportanceWeight(Qualification.Importance importance) {
        switch (importance) {
            case HIGH:
                return 3;
            case MEDIUM:
                return 2;
            case LOW:
                return 1;
            default:
                return 1;
        }
    }

    public static double calculateScore(JobPosting job, Applicant applicant) {
        double totalScore = 0.0;

        // --- Education ---
        EducationLevel jEdu = job.getEducationLevel();
        EducationLevel aEdu = applicant.getEducationLevel();
        if (jEdu != null && aEdu != null) {
            if (jEdu.getDegreeLevel() == aEdu.getDegreeLevel()) {
                totalScore += applyWeight(1.0, jEdu.getImportance());
            }
            if (jEdu.getFieldOfStudy() == aEdu.getFieldOfStudy()) {
                totalScore += applyWeight(1.0, jEdu.getImportance());
            }
            double cgpaScore = aEdu.getCgpa() / jEdu.getCgpa();
            totalScore += applyWeight(cgpaScore, jEdu.getImportance());
        }

        // --- Work Experience ---
        ListInterface<WorkExperience> requiredExp = job.getWorkExperiences();
        ListInterface<WorkExperience> applicantExp = applicant.getWorkExperiences();
        if (requiredExp != null && applicantExp != null) {
            for (WorkExperience req : requiredExp) {
                for (WorkExperience exp : applicantExp) {
                    if (req.getIndustry() == exp.getIndustry()) {
                        double raw = Math.min(exp.getYears(), req.getYears());
                        totalScore += applyWeight(raw, req.getImportance());
                        break;
                    }
                }
            }
        }

        // --- Language ---
        ListInterface<LanguageProficiency> requiredLangs = job.getLanguageProficiencies();
        ListInterface<LanguageProficiency> applicantLangs = applicant.getLanguageProficiencies();
        if (requiredLangs != null && applicantLangs != null) {
            for (LanguageProficiency req : requiredLangs) {
                for (LanguageProficiency lang : applicantLangs) {
                    if (req.getLanguage() == lang.getLanguage()) {
                        double raw = req.scoreMatch(lang);
                        totalScore += applyWeight(raw, req.getImportance());
                        break;
                    }
                }
            }
        }

        // --- Skills ---
        ListInterface<Skill> requiredSkills = job.getSkills();
        ListInterface<Skill> applicantSkills = applicant.getSkills();
        if (requiredSkills != null && applicantSkills != null) {
            for (Skill req : requiredSkills) {
                for (Skill s : applicantSkills) {
                    if (req.getSkillName().equalsIgnoreCase(s.getSkillName())) {
                        double raw = req.scoreMatch(s);
                        totalScore += applyWeight(raw, req.getImportance());
                        break;
                    }
                }
            }
        }

        // --- Location ---
        Location jobLoc = job.getCompany().getLocation();
        Location appLoc = applicant.getLocation();
        if (jobLoc != null && appLoc != null) {
            double distance = jobLoc.distanceTo(appLoc);
            double locScore;
            if (distance <= 20) {
                locScore = 1.0;
            } else if (distance > 20 && distance <= 50) {
                locScore = 0.5;
            } else {
                //locScore = 1.0 - ((distance - 30) / 120.0);  // Linear drop-off
                locScore = 0;
            }

            totalScore += locScore;
        }

        return totalScore;
    }

    public static boolean isQualified(JobPosting job, Applicant applicant) {
        // Check education strict requirement
        EducationLevel jobEdu = job.getEducationLevel();
        EducationLevel appEdu = applicant.getEducationLevel();
        if (jobEdu != null && appEdu != null) {
            if (!jobEdu.isOptional()) {
                if (jobEdu.getDegreeLevel() != appEdu.getDegreeLevel()
                        || jobEdu.getFieldOfStudy() != appEdu.getFieldOfStudy()
                        || appEdu.getCgpa() / jobEdu.getCgpa() < 0.5) {
                    return false;
                }
            }
        }

        // Check strict work experience
        ListInterface<WorkExperience> requiredExp = job.getWorkExperiences();
        ListInterface<WorkExperience> appExp = applicant.getWorkExperiences();
        if (requiredExp != null && appExp != null) {
            for (WorkExperience req : requiredExp) {
                if (!req.isOptional()) {
                    boolean matched = false;
                    for (WorkExperience exp : appExp) {
                        if (req.getIndustry().equals(exp.getIndustry())) {
                            matched = true;
                            break;
                        }
                    }
                    if (!matched) {
                        return false;
                    }
                }
            }
        }

        // Check strict language
        ListInterface<LanguageProficiency> requiredLang = job.getLanguageProficiencies();
        ListInterface<LanguageProficiency> appLang = applicant.getLanguageProficiencies();
        if (requiredLang != null && appLang != null) {
            for (LanguageProficiency req : requiredLang) {
                if (!req.isOptional()) {
                    boolean matched = false;
                    for (LanguageProficiency lang : appLang) {
                        if (req.getLanguage().equals(lang.getLanguage())) {
                            matched = true;
                            break;
                        }
                    }
                    if (!matched) {
                        return false;
                    }
                }
            }
        }

        // Check strict skill
        ListInterface<Skill> requiredSkills = job.getSkills();
        ListInterface<Skill> appSkills = applicant.getSkills();
        if (requiredSkills != null && appSkills != null) {
            for (Skill req : requiredSkills) {
                if (!req.isOptional()) {
                    boolean matched = false;
                    for (Skill skill : appSkills) {
                        if (req.getSkillName().equalsIgnoreCase(skill.getSkillName())) {
                            matched = true;
                            break;
                        }
                    }
                    if (!matched) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static double applyWeight(double score, Qualification.Importance importance) {
        switch (importance) {
            case HIGH:
                return score * 2.0;
            case MEDIUM:
                return score * 1.5;
            case LOW:
                return score;
            default:
                return 0;
        }
    }
}
