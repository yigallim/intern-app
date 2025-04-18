/**
 * @author Leong Hon Yan
 */
package com.tarumt.entity;

import com.tarumt.entity.location.Location;
import com.tarumt.entity.qualification.EducationLevel;
import com.tarumt.entity.qualification.LanguageProficiency;
import com.tarumt.entity.qualification.Skill;
import com.tarumt.entity.qualification.WorkExperience;
import com.tarumt.utility.pretty.annotation.ExcludeKey;
import com.tarumt.utility.pretty.annotation.OutputLength;
import com.tarumt.utility.search.annotation.Fuzzy;
import com.tarumt.utility.validation.annotation.Max;
import com.tarumt.utility.validation.annotation.Min;
import com.tarumt.utility.validation.annotation.Regex;

import com.tarumt.adt.list.ListInterface;

import java.util.Objects;

public class Applicant extends BaseEntity {
    private static final String PREFIX = "a";
    private static int counter = 1;

    @Min(3)
    @Max(30)
    @Fuzzy
    @OutputLength(20)
    private String name;
    @Regex(pattern = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "Invalid email format")
    @Fuzzy
    @OutputLength(26)
    private String contactEmail;
    @Regex(pattern = "^(\\+?6?01)[02-46-9]-*[0-9]{7}$|^(\\+?6?01)[1]-*[0-9]{8}$", message = "Invalid phone number format")
    @Fuzzy
    @OutputLength(13)
    private String contactPhone;
    @Fuzzy
    @OutputLength(34)
    private JobPosting.Type desiredJobType;
    @Fuzzy
    @OutputLength(32)
    private Location location;
    @ExcludeKey("default")
    private EducationLevel educationLevel;
    @ExcludeKey("default")
    private ListInterface<WorkExperience> workExperiences;
    @ExcludeKey("default")
    private ListInterface<LanguageProficiency> languageProficiencies;
    @ExcludeKey("default")
    private ListInterface<Skill> skills;

    public Applicant(String name, String contactEmail, String contactPhone, JobPosting.Type desiredJobType, Location location) {
        super(generateId());
        this.name = name;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.desiredJobType = desiredJobType;
        this.location = location;
    }

    public Applicant(String name, String contactEmail, String contactPhone, JobPosting.Type desiredJobType,
                     Location location, EducationLevel educationLevel, ListInterface<WorkExperience> workExperiences,
                     ListInterface<LanguageProficiency> languageProficiencies, ListInterface<Skill> skills) {
        super(generateId());
        this.name = name;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.desiredJobType = desiredJobType;
        this.location = location;
        this.educationLevel = educationLevel;
        this.workExperiences = workExperiences;
        this.languageProficiencies = languageProficiencies;
        this.skills = skills;
    }

    private static String generateId() {
        String id = PREFIX + counter;
        counter++;
        return id;
    }

    public static String getNextId() {
        return PREFIX + counter;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Applicant.counter = counter;
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

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
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

    public ListInterface<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(ListInterface<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }

    public ListInterface<LanguageProficiency> getLanguageProficiencies() {
        return languageProficiencies;
    }

    public void setLanguageProficiencies(ListInterface<LanguageProficiency> languageProficiencies) {
        this.languageProficiencies = languageProficiencies;
    }

    public ListInterface<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ListInterface<Skill> skills) {
        this.skills = skills;
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
                "|  Phone       => " + contactPhone + ",\n" +
                "|  Desired Job => " + (desiredJobType != null ? desiredJobType : "N/A") + ",\n" +
                "|  Location    => " + (location != null ? location.toString() : "N/A");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Applicant applicant = (Applicant) o;
        return Objects.equals(name, applicant.name) && Objects.equals(contactEmail, applicant.contactEmail) && Objects.equals(contactPhone, applicant.contactPhone) && desiredJobType == applicant.desiredJobType && Objects.equals(location, applicant.location) && Objects.equals(educationLevel, applicant.educationLevel) && Objects.equals(workExperiences, applicant.workExperiences) && Objects.equals(languageProficiencies, applicant.languageProficiencies);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(contactEmail);
        result = 31 * result + Objects.hashCode(contactPhone);
        result = 31 * result + Objects.hashCode(desiredJobType);
        result = 31 * result + Objects.hashCode(location);
        result = 31 * result + Objects.hashCode(educationLevel);
        result = 31 * result + Objects.hashCode(workExperiences);
        result = 31 * result + Objects.hashCode(languageProficiencies);
        return result;
    }
}
