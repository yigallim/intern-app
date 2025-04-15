package com.tarumt.utility.search;

import com.tarumt.adt.set.HashSet;
import com.tarumt.entity.BaseEntity;

import com.tarumt.utility.common.Strings;
import com.tarumt.utility.search.annotation.Fuzzy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.tarumt.adt.list.ListInterface;
import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.set.SetInterface;

public class FuzzySearch {

    public static SetInterface<String> findFuzzyMatches_v4(String query, String sentence) {
        return findFuzzyMatches_v4(query, sentence, 0.85, 0.65);
    }

    public static SetInterface<String> findFuzzyMatches_v4(String query, String sentence, double dlThreshold,
                                                           double jwThreshold) {

        String[] queryTokens = query.split("\\s+");
        String[] sentenceTokens = sentence.split("\\s+");

        ListInterface<Integer> matchedIndices = new DoublyLinkedList<>();

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

        SetInterface<String> results = new HashSet<>();

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

        ListInterface<ListInterface<Integer>> groups = new DoublyLinkedList<>();
        ListInterface<Integer> currentGroup = new DoublyLinkedList<>();
        currentGroup.add(matchedIndices.get(0));

        for (int i = 1; i < matchedIndices.size(); i++) {
            int prev = matchedIndices.get(i - 1);
            int curr = matchedIndices.get(i);
            if (curr - prev <= allowedGap) {
                currentGroup.add(curr);
            } else {
                groups.add(currentGroup);
                currentGroup = new DoublyLinkedList<>();
                currentGroup.add(curr);
            }
        }
        groups.add(currentGroup);

        for (ListInterface<Integer> group : groups) {
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

    public static <T extends BaseEntity> Result<T> searchList(Class<T> clazz, ListInterface<T> entities, String query,
                                                              String... excludeKeys) {
        SetInterface<String> results = new HashSet<>();
        ListInterface<T> matchedEntities = new DoublyLinkedList<>();

        for (T instance : entities) {
            boolean entityMatched = false;

            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Fuzzy.class)) {

                    if (shouldExcludeField(field, excludeKeys)) {
                        continue;
                    }

                    field.setAccessible(true);
                    try {
                        Object valueObj = field.get(instance);
                        if (valueObj != null) {
                            String valueStr = valueObj.toString();
                            Fuzzy fuzzyAnnotation = field.getAnnotation(Fuzzy.class);
                            SetInterface<String> matchesForField = findFuzzyMatches_v4(
                                    query,
                                    valueStr,
                                    fuzzyAnnotation.dlThreshold(),
                                    fuzzyAnnotation.jwThreshold());
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

                    if (shouldExcludeMethod(method, excludeKeys)) {
                        continue;
                    }

                    method.setAccessible(true);
                    try {
                        Object resultObj = method.invoke(instance);
                        if (resultObj != null) {
                            String resultStr = resultObj.toString();
                            Fuzzy fuzzyAnnotation = method.getAnnotation(Fuzzy.class);
                            SetInterface<String> matchesForMethod = findFuzzyMatches_v4(
                                    query,
                                    resultStr,
                                    fuzzyAnnotation.dlThreshold(),
                                    fuzzyAnnotation.jwThreshold());
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

    private static boolean shouldExcludeField(Field field, String... excludeKeys) {
        if (excludeKeys == null || excludeKeys.length == 0) {
            return false;
        }

        if (field.isAnnotationPresent(com.tarumt.utility.pretty.annotation.ExcludeKey.class)) {
            com.tarumt.utility.pretty.annotation.ExcludeKey annotation = field
                    .getAnnotation(com.tarumt.utility.pretty.annotation.ExcludeKey.class);
            String[] annotationKeys = annotation.value();

            for (String excludeKey : excludeKeys) {
                for (String annotationKey : annotationKeys) {
                    if (excludeKey != null && excludeKey.equals(annotationKey)) {
                        return true;
                    }
                }
            }
        }

        String fieldName = field.getName();
        for (String excludeKey : excludeKeys) {
            if (fieldName.equals(excludeKey)) {
                return true;
            }
        }

        return false;
    }

    private static boolean shouldExcludeMethod(Method method, String... excludeKeys) {
        if (excludeKeys == null || excludeKeys.length == 0) {
            return false;
        }

        if (method.isAnnotationPresent(com.tarumt.utility.pretty.annotation.ExcludeKey.class)) {
            com.tarumt.utility.pretty.annotation.ExcludeKey annotation = method
                    .getAnnotation(com.tarumt.utility.pretty.annotation.ExcludeKey.class);
            String[] annotationKeys = annotation.value();

            for (String excludeKey : excludeKeys) {
                for (String annotationKey : annotationKeys) {
                    if (excludeKey != null && excludeKey.equals(annotationKey)) {
                        return true;
                    }
                }
            }
        }

        String methodName = method.getName();

        if (methodName.startsWith("get") && methodName.length() > 3) {
            methodName = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
        }

        for (String excludeKey : excludeKeys) {
            if (methodName.equals(excludeKey)) {
                return true;
            }
        }
        return false;
    }

    public static class Result<T extends BaseEntity> {
        SetInterface<String> matches;
        ListInterface<T> subList;

        public Result(SetInterface<String> matches, ListInterface<T> subList) {
            this.matches = matches;
            this.subList = subList;
        }

        public SetInterface<String> getMatches() {
            return matches;
        }

        public void setMatches(SetInterface<String> matches) {
            this.matches = matches;
        }

        public ListInterface<T> getSubList() {
            return subList;
        }

        public void setSubList(ListInterface<T> subList) {
            this.subList = subList;
        }
    }
}