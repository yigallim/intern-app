/**
 * @author Lim Yuet Yang
 */
package com.tarumt.utility.search;

public class JaroWinklerSimilarity {

    public static double jaroDistance(String s1, String s2) {
        if (s1.equals(s2)) return 1.0;

        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 == 0 || len2 == 0) return 0.0;

        int matchDistance = Math.max(len1, len2) / 2 - 1;
        boolean[] s1Matches = new boolean[len1];
        boolean[] s2Matches = new boolean[len2];

        int matches = 0;
        int transpositions = 0;

        // Count matches within match distance
        for (int i = 0; i < len1; i++) {
            int start = Math.max(0, i - matchDistance);
            int end = Math.min(i + matchDistance + 1, len2);

            for (int j = start; j < end; j++) {
                if (!s2Matches[j] && s1.charAt(i) == s2.charAt(j)) {
                    s1Matches[i] = true;
                    s2Matches[j] = true;
                    matches++;
                    break;
                }
            }
        }

        if (matches == 0) return 0.0;

        // Count transpositions
        int k = 0;
        for (int i = 0; i < len1; i++) {
            if (s1Matches[i]) {
                while (!s2Matches[k]) k++;
                if (s1.charAt(i) != s2.charAt(k)) transpositions++;
                k++;
            }
        }

        double jaro = ((matches / (double) len1) +
                (matches / (double) len2) +
                ((matches - transpositions / 2.0) / matches)) / 3.0;

        return jaro;
    }

    public static double score(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        double jaro = jaroDistance(s1, s2);

        int prefixLength = 0;
        while (prefixLength < Math.min(4, Math.min(s1.length(), s2.length())) &&
                s1.charAt(prefixLength) == s2.charAt(prefixLength)) {
            prefixLength++;
        }

        double winklerBoost = prefixLength * 0.1 * (1 - jaro);
        return Double.min(1.0, jaro + winklerBoost);
    }
}
