package com.tarumt.adt;
/**
 * A HashSet implementation of the Set interface.
 * This implementation uses a hash table for storing elements,
 * providing constant-time performance for basic operations.
 * 
 * @param <E> the type of elements maintained by this set
 */
public class HashSet<E> implements Set<E> {
    
    // Default initial capacity of the hash table
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    
    // Default load factor
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    
    // The hash table buckets
    private Node<E>[] buckets;
    
    // Current size of the set
    private int size;
    
    // Load factor - determines when to resize
    private float loadFactor;
    
    // Node class for the hash table linked list
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
    
    /**
     * Constructs an empty HashSet with default capacity and load factor.
     */
    @SuppressWarnings("unchecked")
    public HashSet() {
        this.buckets = (Node<E>[]) new Node[DEFAULT_INITIAL_CAPACITY];
        this.size = 0;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }
    
    /**
     * Constructs an empty HashSet with specified initial capacity and default load factor.
     * 
     * @param initialCapacity the initial capacity of the hash table
     */
    @SuppressWarnings("unchecked")
    public HashSet(int initialCapacity) {
        this.buckets = (Node<E>[]) new Node[initialCapacity];
        this.size = 0;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }
    
    /**
     * Constructs an empty HashSet with specified initial capacity and load factor.
     * 
     * @param initialCapacity the initial capacity of the hash table
     * @param loadFactor the load factor threshold
     */
    @SuppressWarnings("unchecked")
    public HashSet(int initialCapacity, float loadFactor) {
        this.buckets = (Node<E>[]) new Node[initialCapacity];
        this.size = 0;
        this.loadFactor = loadFactor;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(E element) {
        if (element == null) {
            throw new NullPointerException("Cannot add null element to the set");
        }
        
        // Calculate hash code
        int hash = hash(element);
        
        // Calculate bucket index
        int index = indexFor(hash, buckets.length);
        
        // Check if element already exists
        for (Node<E> node = buckets[index]; node != null; node = node.next) {
            if (hash == node.hash && (element.equals(node.element))) {
                return false; // Element already exists
            }
        }
        
        // Add element to bucket
        addEntry(element, hash, index);
        return true;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(E element) {
        if (element == null) {
            return false;
        }
        
        // Calculate hash code
        int hash = hash(element);
        
        // Calculate bucket index
        int index = indexFor(hash, buckets.length);
        
        // Find and remove element
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
        
        return false; // Element not found
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(E element) {
        if (element == null) {
            return false;
        }
        
        // Calculate hash code
        int hash = hash(element);
        
        // Calculate bucket index
        int index = indexFor(hash, buckets.length);
        
        // Search for element
        for (Node<E> node = buckets[index]; node != null; node = node.next) {
            if (hash == node.hash && (element.equals(node.element))) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = null;
        }
        size = 0;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        
        for (int j = 0; j < buckets.length; j++) {
            for (Node<E> node = buckets[j]; node != null; node = node.next) {
                result[i++] = node.element;
            }
        }
        
        return result;
    }
    
    /**
     * Adds a new entry with the specified element, hash, and index.
     */
    private void addEntry(E element, int hash, int index) {
        // Check if resize is needed
        if (size >= buckets.length * loadFactor) {
            resize(2 * buckets.length);
            // Recalculate index after resize
            index = indexFor(hash, buckets.length);
        }
        
        // Create new node at the beginning of the chain
        buckets[index] = new Node<E>(element, hash, buckets[index]);
        size++;
    }
    
    /**
     * Resizes the hash table to the given new capacity.
     */
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        // Create new buckets array
        Node<E>[] newBuckets = (Node<E>[]) new Node[newCapacity];
        
        // Transfer all elements to the new buckets
        for (int i = 0; i < buckets.length; i++) {
            Node<E> node = buckets[i];
            
            while (node != null) {
                Node<E> next = node.next;
                
                // Calculate new index in the larger array
                int newIndex = indexFor(node.hash, newCapacity);
                
                // Link to new chain
                node.next = newBuckets[newIndex];
                newBuckets[newIndex] = node;
                
                node = next;
            }
        }
        
        buckets = newBuckets;
    }
    
    /**
     * Returns hash code for the given object, applying additional hash function.
     */
    private int hash(Object key) {
        int h = key.hashCode();
        // This is a simplification of the additional hash function used in Java's HashMap
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
    
    /**
     * Returns the bucket index for the given hash and length.
     */
    private int indexFor(int hash, int length) {
        // Bitwise AND with (length-1) is equivalent to modulo length when length is a power of 2
        return hash & (length - 1);
    }
    
    /**
     * Returns a string representation of this set.
     */
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
}