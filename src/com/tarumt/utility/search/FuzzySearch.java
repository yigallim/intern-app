package com.tarumt.utility.search;

import com.tarumt.entity.BaseEntity;
import com.tarumt.utility.common.Strings;
import com.tarumt.utility.search.annotation.Fuzzy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class FuzzySearch {
    public static double combinedScore(String query, String text,
                                       double weightLevenshtein,
                                       double weightCosine) {
        query = query.toLowerCase();
        text = text.toLowerCase();

        double scoreLev = LevenshteinSimilarity.score(query, text);
        double scoreCos = CosineSimilarity.score(query, text);

        return Double.min(1.0, (weightLevenshtein * scoreLev +
                weightCosine * scoreCos));
    }

    public static List<String> findFuzzyMatches_v1(String query, String sentence,
                                                   double threshold,
                                                   double weightLevenshtein,
                                                   double weightCosine) {
        return findFuzzyMatches_v1(query, sentence, threshold, weightLevenshtein, weightCosine, false);
    }

    public static List<String> findFuzzyMatches_v1(String query, String sentence,
                                                   double threshold,
                                                   double weightLevenshtein,
                                                   double weightCosine,
                                                   boolean debug) {
        List<String> matches = new ArrayList<>();
        String[] queryTokens = query.trim().split("\\s+");
        int n = queryTokens.length;

        if (n == 0) return matches;

        String[] sentenceTokens = sentence.split("\\s+");
        String queryString = String.join(" ", queryTokens);

        if (n == 1) {
            weightLevenshtein = 1.0;
            weightCosine = 0.0;
        }

        if (debug) {
            System.out.println("\n=== Debug Mode: Fuzzy Search ===");
            System.out.println("Query: " + query);
            System.out.println("Sentence: " + sentence);
            System.out.println("Threshold: " + threshold);
            System.out.println("Levenshtein Weight: " + weightLevenshtein);
            System.out.println("Cosine Weight: " + weightCosine);
            System.out.println("Query Tokens: " + Arrays.toString(queryTokens));
            System.out.println("Sentence Tokens: " + Arrays.toString(sentenceTokens));
            System.out.println("--------------------------------");
        }
        for (int i = 0; i <= sentenceTokens.length - n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (j > 0) sb.append(" ");
                sb.append(Strings.removeTrailingPunctuation(sentenceTokens[i + j]));
            }
            String phrase = sb.toString();
            double levScore = LevenshteinSimilarity.score(queryString, phrase);
            double cosScore = CosineSimilarity.score(queryString, phrase);
            double combinedScore = combinedScore(queryString, phrase, weightLevenshtein, weightCosine);

            if (debug) {
                System.out.println("Phrase: " + phrase);
                System.out.println("Levenshtein Score: " + levScore);
                System.out.println("Cosine Score: " + cosScore);
                System.out.println("Final Combined Score: " + combinedScore);
                System.out.println("Meets Threshold? " + (combinedScore >= threshold));
                System.out.println("--------------------------------");
            }

            if (combinedScore >= threshold) matches.add(phrase);
        }

        if (debug) {
            System.out.println("Final Matches: " + matches);
            System.out.println("================================");
        }

        return matches;
    }

    public static List<String> findFuzzyMatches_v2(String query, String sentence, double threshold) {
        List<String> matches = new ArrayList<>();
        query = query.trim();
        if (query.isEmpty()) {
            return matches;
        }

        String[] queryTokens = query.split("\\s+");
        String[] sentenceTokens = sentence.split("\\s+");

        if (queryTokens.length == 1) {
            Set<String> uniqueMatches = new HashSet<>();
            for (String token : sentenceTokens) {
                double sim = CosineSimilarity.score(query, token);
                if (sim >= threshold) {
                    uniqueMatches.add(token);
                }
            }
            matches.addAll(uniqueMatches);
            return matches;
        }

        int qLen = queryTokens.length;

        int minCandidateLength = 1;
        int maxCandidateLength = qLen + 4;

        double bestScore = -1.0;
        List<String> bestCandidates = new ArrayList<>();

        for (int start = 0; start < sentenceTokens.length; start++) {

            for (int L = minCandidateLength; L <= maxCandidateLength; L++) {
                if (start + L > sentenceTokens.length) {
                    break;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = start; i < start + L; i++) {
                    if (i > start) {
                        sb.append(" ");
                    }
                    sb.append(sentenceTokens[i]);
                }
                String candidate = sb.toString();
                double sim = CosineSimilarity.score(query, candidate);

                if (sim >= threshold) {
                    if (sim > bestScore) {
                        bestScore = sim;
                        bestCandidates.clear();
                        bestCandidates.add(candidate);
                    } else if (sim == bestScore) {
                        bestCandidates.add(candidate);
                    }
                }
            }
        }

        matches.addAll(bestCandidates);
        return matches;
    }

    public static List<String> findFuzzyMatches_v3(String query, String sentence) {
        return FuzzySearch.findFuzzyMatches_v3(query, sentence, 0.6);
    }

    /**
     * Uses Damerau-Levenshtein based similarity to find fuzzy matches.
     *
     * @param query     the search term (one or more words)
     * @param sentence  the sentence to search through
     * @param threshold the minimum similarity score required
     * @return a list of matched phrase(s) from the sentence, with leading and trailing symbols removed
     */
    public static List<String> findFuzzyMatches_v3(String query, String sentence, double threshold) {
        String[] queryTokens = query.split("\\s+");
        String[] sentenceTokens = sentence.split("\\s+");

        List<Integer> matchedIndices = new ArrayList<>();

        for (int i = 0; i < sentenceTokens.length; i++) {
            String token = sentenceTokens[i];
            double bestScore = 0.0;
            for (String qt : queryTokens) {
                double sim = DamerauLevenshteinSimilarity.score(qt, token);
                if (sim > bestScore) {
                    bestScore = sim;
                }
            }
            if (bestScore >= threshold) {
                matchedIndices.add(i);
            }
        }

        List<String> results = new ArrayList<>();

        if (queryTokens.length == 1) {
            for (int index : matchedIndices) {
                results.add(Strings.trimSymbols(sentenceTokens[index]));
            }
            return results;
        }

        int allowedGap = queryTokens.length;
        if (matchedIndices.isEmpty()) {
            return results;
        }

        List<List<Integer>> groups = new ArrayList<>();
        List<Integer> currentGroup = new ArrayList<>();
        currentGroup.add(matchedIndices.get(0));

        for (int i = 1; i < matchedIndices.size(); i++) {
            int prev = matchedIndices.get(i - 1);
            int curr = matchedIndices.get(i);
            if (curr - prev <= allowedGap) {
                currentGroup.add(curr);
            } else {
                groups.add(currentGroup);
                currentGroup = new ArrayList<>();
                currentGroup.add(curr);
            }
        }
        groups.add(currentGroup);

        for (List<Integer> group : groups) {
            int start = group.get(0);
            int end = group.get(group.size() - 1);
            StringBuilder sb = new StringBuilder();
            for (int i = start; i <= end; i++) {
                sb.append(sentenceTokens[i]);
                if (i < end) {
                    sb.append(" ");
                }
            }
            results.add(Strings.trimSymbols(sb.toString()));
        }
        return results;
    }

    public static List<String> findFuzzyMatches_v4(String query, String sentence) {
        return FuzzySearch.findFuzzyMatches_v4(query, sentence, 0.85, 0.65);
    }

    /**
     * Uses Damerau-Levenshtein and Jaro-Winkler based similarity to find fuzzy matches.
     * For each token in the sentence, if the Damerau-Levenshtein similarity with any query token
     * is at least dlThreshold, or if it fails that then its Jaro-Winkler similarity is at least jwWinkler,
     * then the token is considered a match.
     *
     * @param query      the search term (one or more words)
     * @param sentence   the sentence to search through
     * @param dlThreshold the minimum Damerau-Levenshtein similarity threshold
     * @param jwThreshold  the minimum Jaro-Winkler similarity threshold
     * @return a list of matched phrase(s) from the sentence, trimmed of leading/trailing symbols.
     */
    public static List<String> findFuzzyMatches_v4(String query, String sentence, double dlThreshold, double jwThreshold) {
        // Tokenize both query and sentence
        String[] queryTokens = query.split("\\s+");
        String[] sentenceTokens = sentence.split("\\s+");

        List<Integer> matchedIndices = new ArrayList<>();

        for (int i = 0; i < sentenceTokens.length; i++) {
            String token = sentenceTokens[i];
            boolean isMatch = false;
            for (String qt : queryTokens) {
                double dlSim = DamerauLevenshteinSimilarity.score(qt, token);
                if (dlSim > dlThreshold) {
                    isMatch = true;
                    break;
                } else {
                    double jwSim = JaroWinklerSimilarity.score(qt, token);
                    if (jwSim > jwThreshold) {
                        isMatch = true;
                        break;
                    }
                }
            }
            if (isMatch) {
                matchedIndices.add(i);
            }
        }

        List<String> results = new ArrayList<>();

        if (queryTokens.length == 1) {
            for (int index : matchedIndices) {
                results.add(Strings.trimSymbols(sentenceTokens[index]));
            }
            return results;
        }

        int allowedGap = queryTokens.length;

        if (matchedIndices.isEmpty()) {
            return results;
        }

        List<List<Integer>> groups = new ArrayList<>();
        List<Integer> currentGroup = new ArrayList<>();
        currentGroup.add(matchedIndices.get(0));

        for (int i = 1; i < matchedIndices.size(); i++) {
            int prev = matchedIndices.get(i - 1);
            int curr = matchedIndices.get(i);
            if (curr - prev <= allowedGap) {
                currentGroup.add(curr);
            } else {
                groups.add(currentGroup);
                currentGroup = new ArrayList<>();
                currentGroup.add(curr);
            }
        }
        groups.add(currentGroup);

        for (List<Integer> group : groups) {
            int start = group.get(0);
            int end = group.get(group.size() - 1);
            StringBuilder sb = new StringBuilder();
            for (int i = start; i <= end; i++) {
                sb.append(sentenceTokens[i]);
                if (i < end) {
                    sb.append(" ");
                }
            }
            results.add(Strings.trimSymbols(sb.toString()));
        }
        return results;
    }

    /**
     * Performs a fuzzy search on the provided list of child-class instances (extending BaseEntity)
     * for any String fields or no-argument String-returning methods annotated with @Fuzzy.
     * Returns a FuzzyResult containing the list of matching phrases and the list of entities that
     * had at least one match.
     *
     * @param clazz    the child class (e.g. JobPosting.class)
     * @param entities the list of instances of that class
     * @param query    the query string for fuzzy matching
     * @param <T>      the type parameter extending BaseEntity
     * @return a FuzzyResult containing matching phrases and the corresponding sub-list of entities
     */
    public static <T extends BaseEntity> Result<T> searchList(Class<T> clazz, List<T> entities, String query) {
        Set<String> results = new HashSet<>();
        List<T> matchedEntities = new ArrayList<>();

        for (T instance : entities) {
            boolean entityMatched = false;

            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Fuzzy.class)) {
                    field.setAccessible(true);
                    try {
                        Object valueObj = field.get(instance);
                        if (valueObj != null) {

                            String valueStr = valueObj.toString();
                            Fuzzy fuzzyAnnotation = field.getAnnotation(Fuzzy.class);
                            List<String> matchesForField = findFuzzyMatches_v4(
                                    query,
                                    valueStr,
                                    fuzzyAnnotation.dlThreshold(),
                                    fuzzyAnnotation.jwThreshold()
                            );
                            if (!matchesForField.isEmpty()) {
                                results.addAll(matchesForField);
                                entityMatched = true;
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Fuzzy.class) && method.getParameterCount() == 0) {
                    method.setAccessible(true);
                    try {
                        Object resultObj = method.invoke(instance);
                        if (resultObj != null) {

                            String resultStr = resultObj.toString();
                            Fuzzy fuzzyAnnotation = method.getAnnotation(Fuzzy.class);
                            List<String> matchesForMethod = findFuzzyMatches_v4(
                                    query,
                                    resultStr,
                                    fuzzyAnnotation.dlThreshold(),
                                    fuzzyAnnotation.jwThreshold()
                            );
                            if (!matchesForMethod.isEmpty()) {
                                results.addAll(matchesForMethod);
                                entityMatched = true;
                            }
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (entityMatched) {
                matchedEntities.add(instance);
            }
        }
        return new Result<>(results, matchedEntities);
    }

    public static class Result<T extends BaseEntity> {
        Set<String> matches;
        List<T> subList;

        public Result(Set<String> matches, List<T> subList) {
            this.matches = matches;
            this.subList = subList;
        }

        public Set<String> getMatches() {
            return matches;
        }

        public void setMatches(Set<String> matches) {
            this.matches = matches;
        }

        public List<T> getSubList() {
            return subList;
        }

        public void setSubList(List<T> subList) {
            this.subList = subList;
        }
    }
}
