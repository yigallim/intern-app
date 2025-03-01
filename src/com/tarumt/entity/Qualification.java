package com.tarumt.entity;

public class Qualification {

    private String qualifyId;
    private String name;
    private int minimumYearsExperience;
    private int CGPA;
    private Level proficiencyLevel;

    public enum Level {
        BEGINEER(1, "Beginner"),
        INTERMEDIATE(2, "Beginner"),
        ADVANCED(3, "Beginner"),
        EXPERT(4, "Beginner");

        private final int value;
        private final String displayName;

        Level(int value, String displayName) {
            this.value = value;
            this.displayName = displayName;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return displayName;
        }

    }

    public Qualification(String qualifyId, String name, int minimumYearsExperience, int CGPA, Level proficiencyLevel) {
        this.qualifyId = qualifyId;
        this.name = name;
        this.minimumYearsExperience = minimumYearsExperience;
        this.CGPA = CGPA;
        this.proficiencyLevel = proficiencyLevel;
    }

    public String getQualifyId() {
        return qualifyId;
    }

    public String getName() {
        return name;
    }

    public int getMinimumYearsExperience() {
        return minimumYearsExperience;
    }

    public int getCGPA() {
        return CGPA;
    }

    public Level getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setQualifyId(String qualifyId) {
        this.qualifyId = qualifyId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMinimumYearsExperience(int minimumYearsExperience) {
        this.minimumYearsExperience = minimumYearsExperience;
    }

    public void setCGPA(int CGPA) {
        this.CGPA = CGPA;
    }

    public void setProficiencyLevel(Level proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }

    @Override
    public String toString() {
        return "Qualification{" + ", name=" + name + ", minimumYearsExperience=" + minimumYearsExperience + ", CGPA=" + CGPA + ", proficiencyLevel=" + proficiencyLevel + '}';
    }

}
