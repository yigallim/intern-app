package com.tarumt.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    private static final String LOG_DIR = "logs";
    private static final String LOG_FILE = LOG_DIR + File.separator + "app.log";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    static {
        File dir = new File(LOG_DIR);
        if (!dir.exists()) {
            boolean dirCreated = dir.mkdirs();
            if (!dirCreated) {
                System.err.println("[ERROR] Failed to create logs directory.");
            }
        }

        File file = new File(LOG_FILE);
        try {
            if (!file.exists()) {
                boolean fileCreated = file.createNewFile();
                if (!fileCreated) {
                    System.err.println("[ERROR] Failed to create log file.");
                }
            }
        } catch (IOException ex) {
            System.err.println("[ERROR] Failed to create log file: " + ex.getMessage());
        }
    }

    public static void info(String log) {
        writeLog("INFO", log);
    }

    public static void warn(String log) {
        writeLog("WARN", log);
    }

    public static void error(String log) {
        writeLog("ERROR", log);
    }

    private static void writeLog(String level, String message) {

        String consoleLog = String.format("[%s] %s", level, message);
        System.out.println(consoleLog);

        String timestamp = LocalDateTime.now().format(FORMATTER);
        String fileLog = String.format("%s [%s] %s", timestamp, level, message);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(fileLog);
            writer.newLine();
            writer.flush();
        } catch (IOException ex) {
            System.err.println("[ERROR] Failed to write log: " + ex.getMessage());
        }
    }
}