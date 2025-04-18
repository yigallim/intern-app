package com.tarumt.entity.qualification;

import com.tarumt.entity.JobPosting;

import java.util.Objects;

/**
 * @author Choo Zhen Hao
 */
public class SkillOption {
    private final JobPosting.Type skillType;
    private final String name;

    public SkillOption(JobPosting.Type skillType, String name) {
        this.skillType = skillType;
        this.name = name;
    }

    public JobPosting.Type getCategory() {
        return skillType;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        SkillOption that = (SkillOption) o;
        return skillType == that.skillType && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(skillType);
        result = 31 * result + Objects.hashCode(name);
        return result;
    }
}

