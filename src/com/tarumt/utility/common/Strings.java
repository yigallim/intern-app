package com.tarumt.utility.common;

public class Strings {
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
}
