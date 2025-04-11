package com.tarumt.utility.search;

import com.tarumt.entity.BaseEntity;
import com.tarumt.utility.common.Strings;
import com.tarumt.utility.search.annotation.Fuzzy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class FuzzySearch {

    public static Set<String> findFuzzyMatches_v4(String query, String sentence) {
        return findFuzzyMatches_v4(query, sentence, 0.85, 0.65);
    }

    /**
     * Uses Damerau-Levenshtein and Jaro-Winkler based similarity to find fuzzy
     * matches. For each token in the sentence, if the Damerau-Levenshtein
     * similarity with any query token is at least dlThreshold, or if it fails
     * that then its Jaro-Winkler similarity is at least jwWinkler, then the
     * token is considered a match.
     *
     * @param query the search term (one or more words)
     * @param sentence the sentence to search through
     * @param dlThreshold the minimum Damerau-Levenshtein similarity threshold
     * @param jwThreshold the minimum Jaro-Winkler similarity threshold
     * @return a set of matched phrase(s) from the sentence, trimmed of
     * leading/trailing symbols.
     */
    public static Set<String> findFuzzyMatches_v4(String query, String sentence, double dlThreshold,
            double jwThreshold) {
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

        Set<String> results = new HashSet<>();

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
     * Performs a fuzzy search on the provided list of child-class instances
     * (extending BaseEntity) for any String fields or no-argument
     * String-returning methods annotated with @Fuzzy. Returns a FuzzyResult
     * containing the set of matching phrases and the list of entities that had
     * at least one match.
     *
     * @param clazz the child class (e.g. JobPosting.class)
     * @param entities the list of instances of that class
     * @param query the query string for fuzzy matching
     * @param <T> the type parameter extending BaseEntity
     * @return a FuzzyResult containing matching phrases and the corresponding
     * sub-list of entities
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
                            Set<String> matchesForField = findFuzzyMatches_v4(
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
                            Set<String> matchesForMethod = findFuzzyMatches_v4(
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
