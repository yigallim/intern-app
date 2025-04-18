/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tarumt.entity.qualification;

import com.tarumt.entity.JobPosting;


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
}

