package com.tarumt.adt.map;


public interface MapInterface<K, V> {
    int size();
    boolean isEmpty();
    V put(K key, V value);
    V get(K key);
    V remove(K key);
    boolean containsKey(K key);
    void clear();
    V getOrDefault(K key, V defaultValue);
}