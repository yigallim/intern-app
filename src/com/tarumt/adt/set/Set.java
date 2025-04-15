package com.tarumt.adt.set;

import com.tarumt.adt.Collection;

import java.util.Iterator;

public interface Set<E> extends Collection<E> {
    int size();

    boolean isEmpty();

    Iterator<E> iterator();

    boolean add(E e);

    boolean remove(Object o);

    boolean contains(Object o);

    boolean addAll(Collection<? extends E> c);

    boolean removeAll(Collection<?> c);

    boolean containsAll(Collection<?> c);

    void clear();

    Object[] toArray();

    boolean equals(Object o);

    int hashCode();
}