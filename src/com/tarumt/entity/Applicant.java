package com.tarumt.entity;

import com.tarumt.entity.location.Location;
import com.tarumt.entity.qualification.EducationLevel;
import com.tarumt.entity.qualification.LanguageProficiency;
import com.tarumt.entity.qualification.Qualification;
import com.tarumt.entity.qualification.WorkExperience;
import com.tarumt.utility.pretty.annotation.ExcludeKey;
import com.tarumt.utility.pretty.annotation.OutputLength;
import com.tarumt.utility.search.annotation.Fuzzy;
import com.tarumt.utility.validation.annotation.Max;
import com.tarumt.utility.validation.annotation.Min;
import com.tarumt.utility.validation.annotation.Regex;

import java.util.List;

public class Applicant extends BaseEntity {
    static {
        BaseEntity.registerPrefix(Applicant.class, "a");
    }

    @Min(3)
    @Max(30)
    @Fuzzy
    @OutputLength(20)
    private String name;
    @Fuzzy
    @Regex(pattern = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "Invalid email format")
    private String contactEmail;
    @Fuzzy
    @OutputLength(34)
    private JobPosting.Type desiredJobType;
    @Fuzzy
    @OutputLength(32)
    private Location location;
    @ExcludeKey("default")
    private EducationLevel educationLevel;
    @ExcludeKey("default")
    private List<WorkExperience> workExperiences;
    @ExcludeKey("default")
    private List<LanguageProficiency> languageProficiencies;

    public Applicant(String name, String contactEmail, JobPosting.Type desiredJobType, Location location) {
        this.name = name;
        this.contactEmail = contactEmail;
        this.desiredJobType = desiredJobType;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public JobPosting.Type getDesiredJobType() {
        return desiredJobType;
    }

    public void setDesiredJobType(JobPosting.Type desiredJobType) {
        this.desiredJobType = desiredJobType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public List<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(List<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }

    public List<LanguageProficiency> getLanguageProficiencies() {
        return languageProficiencies;
    }

    public void setLanguageProficiencies(List<LanguageProficiency> languageProficiencies) {
        this.languageProficiencies = languageProficiencies;
    }

    @Override
    public String toShortString() {
        return this.getId() + ", " + this.getName();
    }

    @Override
    public String toString() {
        return "Applicant\n" +
                "|  ID          => " + getId() + ",\n" +
                "|  Name        => " + name + ",\n" +
                "|  Email       => " + contactEmail + ",\n" +
                "|  Desired Job => " + (desiredJobType != null ? desiredJobType : "N/A") + ",\n" +
                "|  Location    => " + (location != null ? location.toString() : "N/A");
    }
}
