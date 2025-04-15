package com.tarumt.utility.validation;

import com.tarumt.adt.list.List;
import com.tarumt.adt.list.DoublyLinkedList;

import java.util.function.Predicate;

public class Condition {

    protected final List<Constraint> constraints = new DoublyLinkedList<>();

    public void validate(Object value) throws ValidationException {
        for (Constraint constraint : constraints) {
            if (!constraint.test(value)) {
                throw new ValidationException(constraint.getErrorMessage());
            }
        }
    }

    public String safeValidate(Object value) {
        for (Constraint constraint : constraints) {
            if (!constraint.test(value)) {
                return constraint.getErrorMessage();
            }
        }
        return null;
    }

    protected void addConstraint(Constraint constraint) {
        constraints.add(constraint);
    }

    public Condition custom(Predicate<Object> predicate, String customMessage) {
        addConstraint(new Constraint(predicate, customMessage));
        return this;
    }

    public static class Constraint {
        private final Predicate<Object> predicate;
        private final String errorMessage;

        public Constraint(Predicate<Object> predicate, String errorMessage) {
            this.predicate = predicate;
            this.errorMessage = errorMessage;
        }

        public boolean test(Object value) {
            return predicate.test(value);
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

}
