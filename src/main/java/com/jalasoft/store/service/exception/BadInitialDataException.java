package com.jalasoft.store.service.exception;

public class BadInitialDataException extends Exception {

    private static final String MESSAGE = "Bad data for setup initial state of application";

    public BadInitialDataException() {
        super(MESSAGE);
    }

    public BadInitialDataException(Throwable ex) {
        super(MESSAGE, ex);
    }

    public BadInitialDataException(String customMessage, Throwable ex) {
        super(customMessage, ex);
    }

    public BadInitialDataException(String customMessage) {
        super(customMessage);
    }
}
