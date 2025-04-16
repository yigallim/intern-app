package com.tarumt.utility.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Strings {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public static String trimSymbols(String phrase) {
        return phrase.replaceAll("^[^a-zA-Z0-9]+", "").replaceAll("[^a-zA-Z0-9]+$", "");
    }

    public static String capitalizeWords(String[] words) {
        StringBuilder capitalized = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                capitalized.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }
        return capitalized.toString().trim();
    }

    public static String constantCaseToTitleCase(String str) {
        String[] words = str.toLowerCase().split("_");
        return capitalizeWords(words);
    }

    public static String camelCaseToTitleCase(String str) {
        String[] words = str.split("(?=[A-Z])");
        return capitalizeWords(words);
    }

    public static String removeTrailingPunctuation(String token) {
        return token.replaceAll("[^a-zA-Z0-9]+$", "");
    }

    public static String truncate(String text, int length) {
        if (text.length() > length) {
            return text.substring(0, length - 1) + "…";
        }
        return text;
    }

    public static void line(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void space(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(" ");
        }
    }

    public static String repeat(String str, int times) {
        if (str == null || times <= 0) {
            return "";
        }
        StringBuilder repeatedString = new StringBuilder();
        for (int i = 0; i < times; i++) {
            repeatedString.append(str);
        }
        return repeatedString.toString();
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : "N/A";
    }

    public static String warnHighlight(String text) {
        return ANSI_YELLOW + text + ANSI_RESET;
    }

    public static String infoHighlight(String text) {
        return ANSI_BLUE + text + ANSI_RESET;
    }

    public static String errorHighlight(String text) {
        return ANSI_RED + text + ANSI_RESET;
    }

    public static String successHighlight(String text) {
        return ANSI_GREEN + text + ANSI_RESET;
    }
}