package com.tarumt.utility.pretty.banana;

public class SimpleMap<K, V> {

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Entry<K, V>[] buckets;
    private int size;
    private final float loadFactor;

    public SimpleMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public SimpleMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public SimpleMap(int initialCapacity, float loadFactor) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Load factor must be positive");
        }

        this.buckets = (Entry<K, V>[]) new Entry[initialCapacity];
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(K key) {
        return key == null ? 0 : (key.hashCode() & 0x7FFFFFFF);
    }

    private int indexFor(K key) {
        return hash(key) % buckets.length;
    }

    public V put(K key, V value) {
        int index = indexFor(key);

        for (Entry<K, V> e = buckets[index]; e != null; e = e.next) {
            if (equals(key, e.key)) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }

        buckets[index] = new Entry<>(key, value, buckets[index]);
        size++;

        if (size > buckets.length * loadFactor) {
            resize();
        }

        return null;
    }

    public V get(K key) {
        int index = indexFor(key);

        for (Entry<K, V> e = buckets[index]; e != null; e = e.next) {
            if (equals(key, e.key)) {
                return e.value;
            }
        }

        return null;
    }

    public V remove(K key) {
        int index = indexFor(key);
        Entry<K, V> prev = null;
        Entry<K, V> curr = buckets[index];

        while (curr != null) {
            if (equals(key, curr.key)) {
                if (prev == null) {
                    buckets[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                size--;
                return curr.value;
            }
            prev = curr;
            curr = curr.next;
        }

        return null;
    }

    public boolean containsKey(K key) {
        int index = indexFor(key);

        for (Entry<K, V> e = buckets[index]; e != null; e = e.next) {
            if (equals(key, e.key)) {
                return true;
            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    public void clear() {
        this.buckets = (Entry<K, V>[]) new Entry[buckets.length];
        this.size = 0;
    }

    public V getOrDefault(K key, V defaultValue) {
        V value = get(key);
        return value != null ? value : defaultValue;
    }

    private boolean equals(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null || o2 == null) {
            return false;
        }
        return o1.equals(o2);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K, V>[] oldBuckets = buckets;
        int newCapacity = buckets.length * 2;
        Entry<K, V>[] newBuckets = (Entry<K, V>[]) new Entry[newCapacity];

        for (Entry<K, V> oldEntry : oldBuckets) {
            for (Entry<K, V> e = oldEntry; e != null; ) {
                Entry<K, V> next = e.next;
                int newIndex = (hash(e.key) % newCapacity);
                e.next = newBuckets[newIndex];
                newBuckets[newIndex] = e;
                e = next;
            }
        }

        buckets = newBuckets;
    }
}