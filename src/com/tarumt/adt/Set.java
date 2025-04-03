package com.tarumt.adt;

/**
 * A Set Abstract Data Type (ADT) interface defining operations
 * that can be performed on a collection of unique elements.
 *
 * @param <E> the type of elements maintained by this set
 */
public interface Set<E> {
    
    /**
     * Adds the specified element to this set if it is not already present.
     * 
     * @param element element to be added to this set
     * @return true if this set did not already contain the specified element
     */
    boolean add(E element);
    
    /**
     * Removes the specified element from this set if it is present.
     * 
     * @param element element to be removed from this set, if present
     * @return true if this set contained the specified element
     */
    boolean remove(E element);
    
    /**
     * Returns true if this set contains the specified element.
     * 
     * @param element element whose presence in this set is to be tested
     * @return true if this set contains the specified element
     */
    boolean contains(E element);
    
    /**
     * Returns the number of elements in this set.
     * 
     * @return the number of elements in this set
     */
    int size();
    
    /**
     * Returns true if this set contains no elements.
     * 
     * @return true if this set contains no elements
     */
    boolean isEmpty();
    
    /**
     * Removes all of the elements from this set.
     */
    void clear();
    
    /**
     * Returns an array containing all of the elements in this set.
     * 
     * @return an array containing all the elements in this set
     */
    Object[] toArray();
}