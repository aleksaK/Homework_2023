package org.example.web.exceptions;

public class ConstraintsViolationError {

    private final String property;
    private final String message;

    public ConstraintsViolationError(String property, String message) {
        this.property = property;
        this.message = message;
    }

    public String getProperty() {
        return property;
    }

    public String getMessage() {
        return message;
    }

}