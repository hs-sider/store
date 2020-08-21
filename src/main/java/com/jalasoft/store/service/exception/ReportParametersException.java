package com.jalasoft.store.service.exception;

public class ReportParametersException extends Exception {

    private static final String MESSAGE = "Bad data for setup initial state of application";

    public ReportParametersException() {
        super(MESSAGE);
    }

    public ReportParametersException(Throwable ex) {
        super(MESSAGE, ex);
    }

    public ReportParametersException(String customMessage, Throwable ex) {
        super(customMessage, ex);
    }

    public ReportParametersException(String customMessage) {
        super(customMessage);
    }
}
