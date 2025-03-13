package com.tarumt.entity.qualification;

public abstract class Qualification {
    private boolean optional;
    private Importance importance;

    public enum Importance {
        LOW, MEDIUM, HIGH
    }

    public abstract boolean matches(Qualification other);

    public abstract boolean getScore(Qualification other);

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
}
