package com.jalasoft.store.service.logger;

import java.util.Date;

public class StoreLogger {

    private static String SEVERITY_INFO = "INFO";
    private static String SEVERITY_WARNING = "WARNING";
    private static String SEVERITY_ERROR = "ERROR";

    private static StoreLogger STORE_LOGGER;

    private StoreLogger() {
    }

    public static StoreLogger getInstance() {
        if (STORE_LOGGER == null) {
            STORE_LOGGER = new StoreLogger();
        }
        return STORE_LOGGER;
    }

    public void info(String message) {
        doLog(SEVERITY_INFO, message);
    }

    public void warning(String message) {
        doLog(SEVERITY_WARNING, message);
    }

    public void error(String message) {
        doLog(SEVERITY_ERROR, message);
    }

    private void doLog(String severity, String message) {
        String formattedMessage = String.format("%s %s: %s", new Date(), severity, message);
        if (SEVERITY_ERROR.equalsIgnoreCase(severity)) {
            System.err.println(formattedMessage);
        } else {
            System.out.println(formattedMessage);
        }
    }
}
