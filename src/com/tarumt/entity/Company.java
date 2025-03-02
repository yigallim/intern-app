package com.tarumt.entity;

public class Company {

    private String id;
    private String name;
    private String description;
    private Location location;
    private String contactEmail;
    private String contactPhone;

    public Company(String id, String name, String description, Location location, String contactEmail, String contactPhone) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "Company{" + ", name=" + name + ", description=" + description + ", location=" + location + ", contactEmail=" + contactEmail + ", contactPhone=" + contactPhone + '}';
    }
    
    
}
