package com.tarumt.utility.search;

public class LevenshteinSimilarity {
    public static int levenshteinDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int prev;
        int[] curr = new int[n + 1];

        for (int j = 0; j <= n; j++)
            curr[j] = j;

        for (int i = 1; i <= m; i++) {
            prev = curr[0];
            curr[0] = i;
            for (int j = 1; j <= n; j++) {
                int temp = curr[j];
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    curr[j] = prev;
                else
                    curr[j] = 1 + Math.min(Math.min(curr[j - 1], prev), curr[j]);
                prev = temp;
            }
        }
        return curr[n];
    }

    public static double scoreDeperecated(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        int dist = levenshteinDistance(s1, s2);
        int maxLen = Math.max(s1.length(), s2.length());
        if (maxLen == 0) {
            return 1.0;
        }
        return Double.min(1.0, (1.0 - ((double) dist / maxLen)) * 1.15);
    }

    public static double score(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        int dist = levenshteinDistance(s1, s2);
        int maxLen = Math.max(s1.length(), s2.length());
        if (maxLen == 0) {
            return 1.0;
        }
        double lengthFactor = (maxLen > 1) ? (1 + (0.5 * Math.exp(-0.3 * (maxLen - 1)))) : 1;
        double similarity = (1.0 - ((double) dist / maxLen));
        return Double.min(1.0, similarity * lengthFactor);
    }
}
