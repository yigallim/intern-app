/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tarumt.entity.qualification;


public class SkillOption {
    private final Skill.SkillCategory category;
    private final String name;

    public SkillOption(Skill.SkillCategory category, String name) {
        this.category = category;
        this.name = name;
    }

    public Skill.SkillCategory getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }
}

