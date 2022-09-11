package com.dineshb.springboot.testing.exception;

public class NonUniqueEmployeeException extends RuntimeException {

    public NonUniqueEmployeeException(String message) {
        super(message);
    }

    public NonUniqueEmployeeException(String message, Throwable t) {
        super(message, t);
    }
}
