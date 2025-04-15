package com.tarumt.adt.set;

import com.tarumt.adt.Collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSet<E> implements Set<E> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node<E>[] buckets;

    private int size;

    private float loadFactor;

    private static class Node<E> {
        E element;
        Node<E> next;
        int hash;

        Node(E element, int hash, Node<E> next) {
            this.element = element;
            this.hash = hash;
            this.next = next;
        }
    }

    @SuppressWarnings("unchecked")
    public HashSet() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public HashSet(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public HashSet(int initialCapacity, float loadFactor) {
        this.buckets = (Node<E>[]) new Node[initialCapacity];
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    @SuppressWarnings("unchecked")
    public HashSet(Collection<? extends E> c) {
        this(Math.max((int) (c.size() / DEFAULT_LOAD_FACTOR) + 1, DEFAULT_INITIAL_CAPACITY), DEFAULT_LOAD_FACTOR);
        if (c == null) throw new NullPointerException("Collection cannot be null");
        addAll(c);
    }

    @Override
    public boolean add(E element) {
        if (element == null) {
            throw new NullPointerException("Cannot add null element to the set");
        }

        int hash = hash(element);

        int index = indexFor(hash, buckets.length);

        for (Node<E> node = buckets[index]; node != null; node = node.next) {
            if (hash == node.hash && (element.equals(node.element))) {
                return false;
            }
        }

        addEntry(element, hash, index);
        return true;
    }

    @Override
    public boolean remove(Object element) {
        if (element == null) {
            return false;
        }

        int hash = hash(element);

        int index = indexFor(hash, buckets.length);

        Node<E> prev = null;
        for (Node<E> node = buckets[index]; node != null; node = node.next) {
            if (hash == node.hash && (element.equals(node.element))) {
                if (prev == null) {
                    buckets[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
                return true;
            }
            prev = node;
        }

        return false;
    }

    @Override
    public boolean contains(Object element) {
        if (element == null) {
            return false;
        }

        int hash = hash(element);

        int index = indexFor(hash, buckets.length);

        for (Node<E> node = buckets[index]; node != null; node = node.next) {
            if (hash == node.hash && (element.equals(node.element))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = null;
        }
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new HashSetIterator();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E element : c) {
            if (add(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object element : c) {
            if (remove(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        for (int i = 0; i < buckets.length; i++) {
            for (Node<E> node = buckets[i]; node != null; node = node.next) {
                array[index++] = node.element;
            }
        }
        return array;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Set)) {
            return false;
        }

        Set<?> other = (Set<?>) o;
        if (other.size() != size()) {
            return false;
        }
        return containsAll(other);
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (int i = 0; i < buckets.length; i++) {
            for (Node<E> node = buckets[i]; node != null; node = node.next) {
                hashCode += node.element.hashCode();
            }
        }
        return hashCode;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        boolean first = true;

        for (int i = 0; i < buckets.length; i++) {
            for (Node<E> node = buckets[i]; node != null; node = node.next) {
                if (!first) {
                    sb.append(", ");
                }
                first = false;
                sb.append(node.element);
            }
        }

        sb.append("]");
        return sb.toString();
    }

    private void addEntry(E element, int hash, int index) {
        if (buckets.length == 0 || size >= buckets.length * loadFactor) {
            resize(Math.max(DEFAULT_INITIAL_CAPACITY, 2 * buckets.length));
            index = indexFor(hash, buckets.length);
        }

        buckets[index] = new Node<E>(element, hash, buckets[index]);
        size++;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {

        Node<E>[] newBuckets = (Node<E>[]) new Node[newCapacity];

        for (int i = 0; i < buckets.length; i++) {
            Node<E> node = buckets[i];

            while (node != null) {
                Node<E> next = node.next;

                int newIndex = indexFor(node.hash, newCapacity);

                node.next = newBuckets[newIndex];
                newBuckets[newIndex] = node;

                node = next;
            }
        }

        buckets = newBuckets;
    }

    private int hash(Object key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    private class HashSetIterator implements Iterator<E> {
        private int currentBucket;
        private Node<E> currentNode;
        private Node<E> lastReturned;
        private int elementsReturned;

        public HashSetIterator() {
            currentBucket = 0;
            currentNode = null;
            lastReturned = null;
            elementsReturned = 0;
            advanceToNextElement();
        }

        @Override
        public boolean hasNext() {
            return elementsReturned < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = currentNode;
            E element = currentNode.element;
            currentNode = currentNode.next;
            elementsReturned++;
            advanceToNextElement();
            return element;
        }

        private void advanceToNextElement() {
            while (currentNode == null && currentBucket < buckets.length) {
                currentNode = buckets[currentBucket++];
            }
        }
    }
}