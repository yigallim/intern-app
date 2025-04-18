/**
 * @author Yeoh Ming Zhe
 */

package com.tarumt.adt.set;

import java.util.Iterator;

public interface SetInterface<E>  extends Iterable<E> {
    int size();

    boolean isEmpty();

    Iterator<E> iterator();

    boolean add(E e);

    boolean remove(Object o);

    boolean contains(Object o);

    boolean addAll(SetInterface<? extends E> c);

    boolean removeAll(SetInterface<?> c);

    boolean containsAll(SetInterface<?> c);

    void clear();

    boolean equals(Object o);

    int hashCode();
}