package com.tarumt.entity;

import com.tarumt.entity.location.Location;
import com.tarumt.utility.pretty.annotation.OutputLength;
import com.tarumt.utility.validation.annotation.Max;
import com.tarumt.utility.validation.annotation.Min;
import com.tarumt.utility.validation.annotation.Regex;

import java.util.Objects;

public class Company extends BaseEntity {
    static {
        BaseEntity.registerPrefix(Company.class, "c");
    }

    @Min(1)
    @Max(30)
    @OutputLength(30)
    private String name;
    @Min(20)
    @Max(100)
    @OutputLength(70)
    private String description;
    @OutputLength(32)
    private Location location;
    @Regex(pattern = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "Invalid email format")
    @OutputLength(26)
    private String contactEmail;
    @Regex(pattern = "^(\\+?6?01)[02-46-9]-*[0-9]{7}$|^(\\+?6?01)[1]-*[0-9]{8}$", message = "Invalid phone number format")
    @OutputLength(13)
    private String contactPhone;

    public Company(String name, String description, Location location, String contactEmail, String contactPhone) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    @Override
    public String toString() {
        return "Company ==>\n" +
                "  Id => '" + getId() + "'\n" +
                "  Name => '" + name + "'\n" +
                "  Description => '" + description + "'\n" +
                "  Location => '" + location + "'\n" +
                "  Contact Email => '" + contactEmail + "'\n" +
                "  Contact Phone => '" + contactPhone + "'";
    }

    @Override
    public String toShortString() {
        return this.getId() + ", " + this.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Company company = (Company) o;
        return Objects.equals(name, company.name) &&
                Objects.equals(description, company.description) &&
                Objects.equals(location, company.location) &&
                Objects.equals(contactEmail, company.contactEmail) &&
                Objects.equals(contactPhone, company.contactPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, location, contactEmail, contactPhone);
    }
}