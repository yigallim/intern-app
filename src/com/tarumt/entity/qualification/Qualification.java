package com.tarumt.entity.qualification;

import com.tarumt.entity.BaseEntity;

import java.util.Objects;

/**
 * @author Choo Zhen Hao
 */
public abstract class Qualification extends BaseEntity {

    private static final String PREFIX = "q";
    private static int counter = 1;

    private boolean optional;
    private Importance importance;

    public Qualification(boolean optional, Importance importance) {
        super(generateId());
        this.optional = optional;
        this.importance = importance;
    }

    private static String generateId() {
        String id = PREFIX + counter;
        counter++;
        return id;
    }

    public static String getNextId() {
        return PREFIX + counter;
    }

    public enum Importance {
        LOW, MEDIUM, HIGH
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public abstract double score();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Qualification that = (Qualification) o;
        return optional == that.optional && importance == that.importance;
    }

    @Override
    public int hashCode() {
        int result = Boolean.hashCode(optional);
        result = 31 * result + Objects.hashCode(importance);
        return result;
    }
}
