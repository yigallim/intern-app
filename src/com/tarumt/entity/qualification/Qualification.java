package com.tarumt.entity.qualification;

import com.tarumt.entity.BaseEntity;

public abstract class Qualification extends BaseEntity{
    private boolean optional;
    private Importance importance;

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
}
