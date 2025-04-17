package com.tarumt.test;

import com.tarumt.utility.search.LevenshteinSimilarity;

public class Test {
    public static void main(String[] args) {

        System.out.println(lengthFactor(1));
        System.out.println(lengthFactor(2));
        System.out.println(lengthFactor(3));
        System.out.println(lengthFactor(4));
        System.out.println(lengthFactor(5));
        System.out.println(lengthFactor(6));
        System.out.println(lengthFactor(7));
        System.out.println(lengthFactor(8));

        System.out.println(LevenshteinSimilarity.score("big", "bgi"));
        System.out.println(LevenshteinSimilarity.score("sco", "score"));
        System.out.println(LevenshteinSimilarity.score("good", "doog"));
    }

    public static double lengthFactor(int maxLen) {
        return (maxLen > 1) ? (1 + (1.6 * Math.exp(-0.5 * (maxLen - 1)))) : 1;
    }
}
