package com.tarumt.utility.validation;

import com.tarumt.adt.function.TestLambda;
import com.tarumt.adt.list.ListInterface;
import com.tarumt.adt.list.DoublyLinkedList;

public class Condition {

    protected final ListInterface<Constraint> constraints = new DoublyLinkedList<>();

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

    public Condition custom(TestLambda<Object> testLambda, String customMessage) {
        addConstraint(new Constraint(testLambda, customMessage));
        return this;
    }

    public static class Constraint {
        private final TestLambda<Object> testLambda;
        private final String errorMessage;

        public Constraint(TestLambda<Object> testLambda, String errorMessage) {
            this.testLambda = testLambda;
            this.errorMessage = errorMessage;
        }

        public boolean test(Object value) {
            return testLambda.test(value);
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

}
