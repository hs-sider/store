package com.jalasoft.store;

import com.jalasoft.store.service.logger.StoreLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoggerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void checkSystemOutput() {
        System.out.print("Normal Output");
        assertEquals("Normal Output", outContent.toString());
    }

    @Test
    public void checkSystemErr() {
        System.err.print("Error Output");
        assertEquals("Error Output", errContent.toString());
    }

    @Test
    public void generateInfoLog() {
        String message = "Body message for info log";
        StoreLogger logger = StoreLogger.getInstance();
        logger.info(message);
        String consoleOutput = outContent.toString();
        assertTrue(consoleOutput.contains("INFO"));
        assertTrue(consoleOutput.contains(message));
    }

    @Test
    public void generateWarningLog() {
        String message = "Body message for warning log";
        StoreLogger logger = StoreLogger.getInstance();
        logger.warning(message);
        String consoleOutput = outContent.toString();
        assertTrue(consoleOutput.contains("WARNING"));
        assertTrue(consoleOutput.contains(message));
    }

    @Test
    public void generateErrorLog() {
        String message = "Body message for error log";
        StoreLogger logger = StoreLogger.getInstance();
        logger.error(message);
        String consoleErrorOutput = errContent.toString();
        assertTrue(consoleErrorOutput.contains("ERROR"));
        assertTrue(consoleErrorOutput.contains(message));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
