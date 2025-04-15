package com.tarumt.utility.search;

public class DamerauLevenshteinSimilarity {

    /**
     * Computes the Damerau-Levenshtein distance between two strings.
     * This algorithm considers insertions, deletions, substitutions, and transpositions.
     *
     * @param s1 the first string
     * @param s2 the second string
     * @return the Damerau-Levenshtein distance
     */
    public static int damerauLevenshteinDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];

        // Initialize first row and column.
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill the dynamic programming table.
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1,    // Deletion
                                dp[i][j - 1] + 1),    // Insertion
                        dp[i - 1][j - 1] + cost       // Substitution
                );

                // Check for transposition.
                if (i > 1 && j > 1
                        && s1.charAt(i - 1) == s2.charAt(j - 2)
                        && s1.charAt(i - 2) == s2.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 2][j - 2] + cost);
                }
            }
        }

        return dp[m][n];
    }

    /**
     * Computes a similarity score based on the Damerau-Levenshtein distance.
     * A length factor is applied to boost similarity for shorter strings.
     *
     * @param s1 the first string
     * @param s2 the second string
     * @return similarity score in the range [0,1]
     */
    public static double score(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        int dist = damerauLevenshteinDistance(s1, s2);
        int maxLen = Math.max(s1.length(), s2.length());
        if (maxLen == 0) {
            return 1.0;
        }
        // Apply a length factor similar to LevenshteinSimilarity.
        double lengthFactor = (maxLen > 1) ? (1 + (0.5 * Math.exp(-0.3 * (maxLen - 1)))) : 1;
        double similarity = (1.0 - ((double) dist / maxLen));
        return Double.min(1.0, similarity * lengthFactor);
    }
}
