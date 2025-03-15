package com.tarumt.entity;

import com.tarumt.entity.location.Location;
import com.tarumt.entity.qualification.Qualification;
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
    private List<Qualification> qualifications;

    public Applicant(String name, String contactEmail, JobPosting.Type desiredJobType, Location location, List<Qualification> qualifications) {
        this.name = name;
        this.contactEmail = contactEmail;
        this.desiredJobType = desiredJobType;
        this.location = location;
        this.qualifications = qualifications;
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

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    @Override
    public String toShortString() {
        return this.getId() + ", " + this.getName();
    }
}
