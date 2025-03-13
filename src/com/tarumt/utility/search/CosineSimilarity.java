package com.tarumt.utility.search;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class CosineSimilarity {
    public static Map<String, Integer> termFrequency(String s) {
        Map<String, Integer> tf = new HashMap<>();
        String[] words = s.toLowerCase().split("\\W+");
        for (String word : words) {
            if (word.isEmpty()) continue;
            boolean grouped = false;
            List<String> existingTokens = new ArrayList<>(tf.keySet());
            for (String token : existingTokens) {
                if (LevenshteinSimilarity.score(token, word) > getDynamicThreshold(token, word)) {
                    tf.put(token, tf.get(token) + 1);
                    grouped = true;
                    break;
                }
            }
            if (!grouped) {
                tf.put(word, 1);
            }
        }
        return tf;
    }

    public static double score(String s1, String s2) {
        Map<String, Integer> tf1 = termFrequency(s1);
        Map<String, Integer> tf2 = termFrequency(s2);

        double dotProduct = 0.0;
        for (String word1 : tf1.keySet()) {
            for (String word2 : tf2.keySet()) {
                if (LevenshteinSimilarity.score(word1, word2) > getDynamicThreshold(word1, word2)) {
                    dotProduct += tf1.get(word1) * tf2.get(word2);
                }
            }
        }

        double norm1 = 0.0;
        double norm2 = 0.0;
        for (int freq : tf1.values()) {
            norm1 += freq * freq;
        }
        for (int freq : tf2.values()) {
            norm2 += freq * freq;
        }
        if (norm1 == 0 || norm2 == 0) return 0.0;

        return Double.min(1.0, dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2)));
    }

    private static double getDynamicThreshold(String word1, String word2) {
        int minLen = Math.min(word1.length(), word2.length());
        if (minLen == 3) {
            return 0.4;
        } else if (minLen == 4) {
            return 0.5;
        } else {
            return 0.6;
        }
    }

}
