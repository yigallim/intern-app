package com.tarumt.entity;

import com.tarumt.entity.location.Location;
import com.tarumt.utility.pretty.annotation.OutputLength;
import com.tarumt.utility.search.annotation.Fuzzy;
import com.tarumt.utility.validation.annotation.Max;
import com.tarumt.utility.validation.annotation.Min;
import com.tarumt.utility.validation.annotation.Regex;

public class Company extends BaseEntity {
    private static final String PREFIX = "c";
    private static int counter = 1;

    @Min(1)
    @Max(30)
    @Fuzzy
    @OutputLength(30)
    private String name;
    @Min(20)
    @Max(100)
    @Fuzzy
    @OutputLength(70)
    private String description;
    @Fuzzy
    @OutputLength(32)
    private Location location;
    @Regex(pattern = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "Invalid email format")
    @Fuzzy
    @OutputLength(26)
    private String contactEmail;
    @Regex(pattern = "^(\\+?6?01)[02-46-9]-*[0-9]{7}$|^(\\+?6?01)[1]-*[0-9]{8}$", message = "Invalid phone number format")
    @Fuzzy
    @OutputLength(13)
    private String contactPhone;

    public Company(String name, String description, Location location, String contactEmail, String contactPhone) {
        super(generateId());
        this.name = name;
        this.description = description;
        this.location = location;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    private static String generateId() {
        String id = PREFIX + counter;
        counter++;
        return id;
    }

    public static String getNextId() {
        return PREFIX + counter;
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
    public String toShortString() {
        return this.getId() + ", " + this.getName();
    }

    @Override
    public String toString() {
        return "Company\n" +
                "|  ID            => " + getId() + ",\n" +
                "|  Name          => " + name + ",\n" +
                "|  Description   => " + description + ",\n" +
                "|  Location      => " + (location != null ? location.toString() : "N/A") + ",\n" +
                "|  Contact Email => " + contactEmail + ",\n" +
                "|  Contact Phone => " + contactPhone;
    }
}