package com.mgaudin.sample.integration.connectors.api.models;

import lombok.Data;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class Violations {
    List<Violation> violations = new ArrayList<>();

    public Violations(Set<ConstraintViolation> violations) {
        for (ConstraintViolation violation : violations) {
            this.violations.add(new Violation(violation));
        }

    }

    @Data
    private class Violation {
        private Object invalidValue;

        private String path;

        private String message;

        public Violation(ConstraintViolation violation) {
            this.invalidValue = violation.getInvalidValue();
            this.path = violation.getPropertyPath().toString();
            this.message = violation.getMessage();
        }
    }
}
