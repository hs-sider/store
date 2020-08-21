package com.jalasoft.store.service.exception;

public class InvalidProductCodeException extends Exception {

    private static final String MESSAGE = "Invalid Product code";

    public InvalidProductCodeException() {
        super(MESSAGE);
    }

    public InvalidProductCodeException(Throwable ex) {
        super(MESSAGE, ex);
    }

    public InvalidProductCodeException(String customMessage, Throwable ex) {
        super(customMessage, ex);
    }

    public InvalidProductCodeException(String customMessage) {
        super(customMessage);
    }
}
