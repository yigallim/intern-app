/**
 * @author Lim Yuet Yang
 */

package com.tarumt.adt.list;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;

import com.tarumt.adt.function.SingleArgLambda;
import com.tarumt.adt.function.TestLambda;

public interface ListInterface<E> extends Iterable<E> {
    int size();

    boolean isEmpty();

    Iterator<E> iterator();

    boolean add(E e);

    boolean remove(Object o);

    boolean contains(Object o);

    boolean merge(ListInterface<? extends E> c);

    boolean removeAll(ListInterface<?> c);

    void clear();

    E get(int index);

    E remove(int index);

    E set(int index, E element);

    ListInterface<E> subList(int fromIndex, int toIndex);

    <R> ListInterface<R> map(SingleArgLambda<? super E, ? extends R> mapper);

    ListInterface<E> filter(TestLambda<? super E> testLambda);

    void sort(Comparator<? super E> comparator);

    boolean anyMatch(TestLambda<? super E> testLambda);

    Optional<E> max(Comparator<? super E> comparator);
}