package com.event.util;

public class ValidationException extends Exception {
    public ValidationException() {
        super("Validation error occurred");
    }

    public ValidationException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Validation error: " + getMessage();
    }
}
