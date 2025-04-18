package com.tarumt.utility.matching;

import com.tarumt.adt.list.ListInterface;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.JobPosting;
import com.tarumt.entity.location.Location;
import com.tarumt.entity.qualification.*;

public class JobMatchingUtil {

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
