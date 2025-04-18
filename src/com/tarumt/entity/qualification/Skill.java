package com.tarumt.entity.qualification;

import com.tarumt.entity.JobPosting;
import com.tarumt.utility.search.annotation.Fuzzy;

import java.util.Objects;

/**
 * @author Choo Zhen Hao
 */
public class Skill extends Qualification {
    private final JobPosting.Type skillType;
    @Fuzzy
    private final String skillName;
    private final ProficiencyLevel proficiencyLevel;

    public enum ProficiencyLevel {
        BEGINNER, INTERMEDIATE, ADVANCED, EXPERT
    }

    public Skill(JobPosting.Type skillType, String skillName, ProficiencyLevel proficiencyLevel, boolean optional, Importance importance) {
        super(optional, importance);
        this.skillType = skillType;
        this.skillName = skillName;
        this.proficiencyLevel = proficiencyLevel;
        setOptional(optional);
        setImportance(importance);
    }

    public Skill(JobPosting.Type skillType, String skillName, ProficiencyLevel proficiencyLevel) {
        super(true, Importance.LOW);
        this.skillType = skillType;
        this.skillName = skillName;
        this.proficiencyLevel = proficiencyLevel;
    }

    public JobPosting.Type getSkillType() {
        return skillType;
    }

    public String getSkillName() {
        return skillName;
    }

    public ProficiencyLevel getProficiencyLevel() {
        return proficiencyLevel;
    }

    public double scoreMatch(Skill other) {
        if (other == null) {
            return 0;
        }
        if (!this.skillName.equalsIgnoreCase(other.skillName)) {
            return 0;
        }

        switch (other.getProficiencyLevel()) {
            case EXPERT:
                return 1.0;
            case ADVANCED:
                return 0.8;
            case INTERMEDIATE:
                return 0.5;
            case BEGINNER:
                return 0.2;
            default:
                return 0.0;
        }
    }

    @Override
    public double score() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
        return skillType + " - " + skillName + " (" + proficiencyLevel + ")";
    }

    @Override
    public String toShortString() {
        return skillName + " (" + proficiencyLevel + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Skill skill = (Skill) o;
        return skillType == skill.skillType && Objects.equals(skillName, skill.skillName) && proficiencyLevel == skill.proficiencyLevel;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(skillType);
        result = 31 * result + Objects.hashCode(skillName);
        result = 31 * result + Objects.hashCode(proficiencyLevel);
        return result;
    }
}
