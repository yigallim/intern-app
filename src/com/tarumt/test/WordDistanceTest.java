package com.tarumt.test;

import com.tarumt.utility.search.JaroWinklerSimilarity;
import com.tarumt.utility.search.LevenshteinSimilarity;
import com.tarumt.utility.search.DamerauLevenshteinSimilarity;

import java.util.ArrayList;
import java.util.List;

public class WordDistanceTest {
    public static void main(String[] args) {
        List<Pair> wordPairs = getWordPairs();

        // Print Table Header
        System.out.printf("%-15s %-15s %-20s %-20s %-25s%n",
                "Word 1", "Word 2",
                "Levenshtein Score", "Jaro-Winkler Score",
                "Damerau-Levenshtein Score");
        System.out.println("---------------------------------------------------------------------------------------------");

        // Loop through word pairs and calculate similarity scores
        for (Pair pair : wordPairs) {
            double levScore = LevenshteinSimilarity.score(pair.getS1(), pair.getS2());
            double jwScore = JaroWinklerSimilarity.score(pair.getS1(), pair.getS2());
            double dlsScore = DamerauLevenshteinSimilarity.score(pair.getS1(), pair.getS2());

            // Print formatted row
            System.out.printf("%-15s %-15s %-20.4f %-20.4f %-25.4f%n",
                    pair.getS1(), pair.getS2(),
                    levScore, jwScore, dlsScore);
        }
    }
    public static List<Pair> getWordPairs() {
        List<Pair> pairs = new ArrayList<>();

        // 100 word pairs for testing distance algorithms
        pairs.add(new Pair("big", "bgi")); // Small difference
        pairs.add(new Pair("sun", "snu")); // Small difference
        pairs.add(new Pair("cloud", "clud")); // Small difference
        pairs.add(new Pair("cat", "hat")); // Small difference
        pairs.add(new Pair("dog", "dig")); // Small difference
        pairs.add(new Pair("book", "cook")); // Small difference
        pairs.add(new Pair("tree", "free")); // Small difference
        pairs.add(new Pair("kite", "bite")); // Small difference
        pairs.add(new Pair("sun", "son")); // Small difference
        pairs.add(new Pair("pen", "pin")); // Small difference
        pairs.add(new Pair("cap", "cup")); // Small difference
        pairs.add(new Pair("bat", "pat")); // Small difference
        pairs.add(new Pair("rat", "rot")); // Small difference
        pairs.add(new Pair("fish", "dish")); // Small difference
        pairs.add(new Pair("cake", "bake")); // Small difference
        pairs.add(new Pair("jump", "pump")); // Small difference
        pairs.add(new Pair("fast", "last")); // Small difference
        pairs.add(new Pair("lamp", "limp")); // Small difference
        pairs.add(new Pair("desk", "disk")); // Small difference
        pairs.add(new Pair("milk", "silk")); // Small difference
        pairs.add(new Pair("gold", "bold")); // Small difference
        pairs.add(new Pair("dark", "park")); // Small difference
        pairs.add(new Pair("wind", "kind")); // Small difference
        pairs.add(new Pair("listen", "silent")); // Anagram
        pairs.add(new Pair("triangle", "integral")); // Anagram
        pairs.add(new Pair("debitcard", "badcredit")); // Anagram
        pairs.add(new Pair("hello", "world")); // Completely different
        pairs.add(new Pair("apple", "banana")); // Different
        pairs.add(new Pair("computer", "science")); // Different
        pairs.add(new Pair("water", "fire")); // Different
        pairs.add(new Pair("music", "dance")); // Different
        pairs.add(new Pair("happy", "smile")); // Different meaning, similar context
        pairs.add(new Pair("big", "small")); // Opposite meaning
        pairs.add(new Pair("bright", "light")); // Similar meaning
        pairs.add(new Pair("run", "ran")); // Same root, different tense
        pairs.add(new Pair("eat", "ate")); // Same root, different tense
        pairs.add(new Pair("go", "went")); // Same root, different tense
        pairs.add(new Pair("kitten", "kitchen")); // Similar spelling
        pairs.add(new Pair("flower", "flour")); // Homophone
        pairs.add(new Pair("knight", "night")); // Homophone
        pairs.add(new Pair("sea", "see")); // Homophone
        pairs.add(new Pair("read", "red")); // Homophone
        pairs.add(new Pair("plane", "plain")); // Homophone
        pairs.add(new Pair("steal", "steel")); // Homophone
        pairs.add(new Pair("write", "right")); // Homophone
        pairs.add(new Pair("pair", "pear")); // Homophone
        pairs.add(new Pair("break", "brake")); // Homophone
        pairs.add(new Pair("mail", "male")); // Homophone
        pairs.add(new Pair("knew", "new")); // Homophone
        pairs.add(new Pair("through", "threw")); // Homophone
        pairs.add(new Pair("weight", "wait")); // Homophone
        pairs.add(new Pair("here", "hear")); // Homophone
        pairs.add(new Pair("hello", "hallo")); // Small edit
        pairs.add(new Pair("color", "colour")); // Regional spelling
        pairs.add(new Pair("organize", "organise")); // Regional spelling
        pairs.add(new Pair("theater", "theatre")); // Regional spelling
        pairs.add(new Pair("gray", "grey")); // Regional spelling
        pairs.add(new Pair("catalog", "catalogue")); // Regional spelling
        pairs.add(new Pair("traveling", "travelling")); // Regional spelling
        pairs.add(new Pair("mom", "mum")); // Regional spelling
        pairs.add(new Pair("check", "cheque")); // Regional spelling
        pairs.add(new Pair("apologize", "apologise")); // Regional spelling
        pairs.add(new Pair("cookie", "biscuit")); // Regional term
        pairs.add(new Pair("truck", "lorry")); // Regional term
        pairs.add(new Pair("apartment", "flat")); // Regional term
        pairs.add(new Pair("soccer", "football")); // Regional term
        pairs.add(new Pair("pants", "trousers")); // Regional term
        pairs.add(new Pair("elevator", "lift")); // Regional term
        pairs.add(new Pair("flashlight", "torch")); // Regional term
        pairs.add(new Pair("subway", "underground")); // Regional term
        pairs.add(new Pair("vacation", "holiday")); // Regional term
        pairs.add(new Pair("parkinglot", "carpark")); // Regional term
        pairs.add(new Pair("hello", "helo")); // Missing letter
        pairs.add(new Pair("world", "worlld")); // Extra letter
        pairs.add(new Pair("test", "tset")); // Transposition
        pairs.add(new Pair("stop", "spot")); // Transposition
        pairs.add(new Pair("live", "evil")); // Reversal
        pairs.add(new Pair("racecar", "racecar")); // Same word (palindrome)
        pairs.add(new Pair("madam", "madam")); // Same word (palindrome)
        pairs.add(new Pair("radar", "radar")); // Same word (palindrome)
        pairs.add(new Pair("string", "stirng")); // Transposition
        pairs.add(new Pair("table", "tablet")); // Extra letter
        pairs.add(new Pair("chair", "chai")); // Missing letter
        pairs.add(new Pair("window", "widow")); // Missing letter
        pairs.add(new Pair("school", "schol")); // Missing letter
        pairs.add(new Pair("bread", "beard")); // Transposition
        pairs.add(new Pair("smile", "miles")); // Anagram
        pairs.add(new Pair("notes", "stone")); // Anagram
        pairs.add(new Pair("angel", "angle")); // Anagram
        pairs.add(new Pair("post", "spot")); // Anagram
        pairs.add(new Pair("listen", "listens")); // Extra letter
        pairs.add(new Pair("silent", "silents")); // Extra letter
        pairs.add(new Pair("dorm", "form")); // Small difference
        pairs.add(new Pair("calm", "clam")); // Transposition
        pairs.add(new Pair("loop", "pool")); // Reversal
        pairs.add(new Pair("cloud", "could")); // Small difference
        pairs.add(new Pair("dream", "dread")); // Small difference
        pairs.add(new Pair("frost", "forts")); // Transposition
        pairs.add(new Pair("storm", "stomp")); // Small difference
        pairs.add(new Pair("blend", "bends")); // Small difference
        pairs.add(new Pair("smile", "smiled")); // Extra letter
        pairs.add(new Pair("laugh", "laughs")); // Extra letter

        return pairs;
    }

    public static class Pair {
        String s1;
        String s2;

        public Pair(String s1, String s2) {
            this.s1 = s1;
            this.s2 = s2;
        }

        public String getS1() {
            return s1;
        }

        public void setS1(String s1) {
            this.s1 = s1;
        }

        public String getS2() {
            return s2;
        }

        public void setS2(String s2) {
            this.s2 = s2;
        }
    }
}