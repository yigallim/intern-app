package com.tarumt.adt.list;

import com.tarumt.adt.Collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;

public interface List<E> extends Collection<E> {
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

    E get(int index);

    E remove(int index);

    List<E> subList(int fromIndex, int toIndex);

    <R> List<R> map(Function<? super E, ? extends R> mapper);

    List<E> filter(Predicate<? super E> predicate);

    void forEach(Consumer<? super E> action);

    void sort(Comparator<? super E> comparator);

    List<E> sorted();

    List<E> sorted(Comparator<? super E> comparator);

    boolean anyMatch(Predicate<? super E> predicate);

    boolean allMatch(Predicate<? super E> predicate);

    Optional<E> min(Comparator<? super E> comparator);

    Optional<E> max(Comparator<? super E> comparator);

    Object[] toArray();

    boolean equals(Object o);

    int hashCode();
}