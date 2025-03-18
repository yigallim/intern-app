package com.tarumt.adt;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

public interface Collection<E> extends Iterable<E> {
    int size();

    boolean isEmpty();

    Iterator<E> iterator();

    boolean add(E e);

    boolean remove(Object o);

    boolean contains(Object o);

    boolean addAll(Collection<? extends E> c);

    boolean removeAll(Collection<?> c);

    boolean containsAll(Collection<?> c);

    boolean retainAll(Collection<?> c);

    void clear();

    Object[] toArray();

    <T> T[] toArray(T[] a);

    default boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<E> each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }

    boolean equals(Object o);

    int hashCode();
}

