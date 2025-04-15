package com.tarumt.adt.list;

import com.tarumt.adt.Arrays;
import com.tarumt.adt.Collection;

import java.util.Objects;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class DoublyLinkedList<E> implements List<E> {
    private class Node {
        E element;
        Node prev;
        Node next;

        Node(E element, Node prev, Node next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public DoublyLinkedList(Collection<? extends E> c) {
        this();
        if (c == null) throw new NullPointerException("Collection cannot be null");
        addAll(c);
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
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator();
    }

    @Override
    public boolean add(E e) {
        Node newNode = new Node(e, tail, null);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node current = head;
        while (current != null) {
            if (Objects.equals(o, current.element)) {
                unlink(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        Node current = head;
        while (current != null) {
            if (Objects.equals(o, current.element)) {
                return true;
            }
            current = current.next;
        }
        return false;
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
        Iterator<?> it = c.iterator();
        while (it.hasNext()) {
            if (remove(it.next())) {
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
    public void clear() {
        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.prev = null;
            current.next = null;
            current.element = null;
            current = next;
        }
        head = tail = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node current;
        if (index < size / 2) {

            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {

            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current.element;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node current;
        if (index < size / 2) {

            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {

            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }

        E element = current.element;
        unlink(current);
        return element;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", toIndex: " + toIndex + ", Size: " + size);
        }

        DoublyLinkedList<E> subList = new DoublyLinkedList<>();
        Node current = head;

        for (int i = 0; i < fromIndex; i++) {
            current = current.next;
        }

        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(current.element);
            current = current.next;
        }

        return subList;
    }

    @Override
    public <R> List<R> map(Function<? super E, ? extends R> mapper) {
        if (mapper == null) throw new NullPointerException();
        DoublyLinkedList<R> result = new DoublyLinkedList<>();
        Node current = head;
        while (current != null) {
            result.add(mapper.apply(current.element));
            current = current.next;
        }
        return result;
    }

    @Override
    public List<E> filter(Predicate<? super E> predicate) {
        if (predicate == null) throw new NullPointerException();
        DoublyLinkedList<E> result = new DoublyLinkedList<>();
        Node current = head;
        while (current != null) {
            if (predicate.test(current.element)) {
                result.add(current.element);
            }
            current = current.next;
        }
        return result;
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        if (action == null) throw new NullPointerException();
        Node current = head;
        while (current != null) {
            action.accept(current.element);
            current = current.next;
        }
    }

    @Override
    public void sort(Comparator<? super E> c) {
        if (c == null) throw new NullPointerException("Comparator cannot be null");
        if (size <= 1) return;
        Object[] array = toArray();
        Arrays.sort(array, (a, b) -> c.compare((E) a, (E) b));
        clear();
        for (Object item : array) {
            add((E) item);
        }
    }

    @Override
    public List<E> sorted() {
        if (isEmpty()) return new DoublyLinkedList<>();
        if (!(head.element instanceof Comparable)) {
            throw new ClassCastException("Elements must be Comparable");
        }
        @SuppressWarnings("unchecked") Comparator<E> comparator = (a, b) -> ((Comparable<? super E>) a).compareTo(b);
        return sorted(comparator);
    }

    @Override
    public List<E> sorted(Comparator<? super E> comparator) {
        if (comparator == null) throw new NullPointerException();
        Object[] array = toArray();

        Arrays.sort(array, (a, b) -> comparator.compare((E) a, (E) b));
        DoublyLinkedList<E> result = new DoublyLinkedList<>();
        for (Object item : array) {
            result.add((E) item);
        }
        return result;
    }

    @Override
    public boolean anyMatch(Predicate<? super E> predicate) {
        if (predicate == null) throw new NullPointerException();
        Node current = head;
        while (current != null) {
            if (predicate.test(current.element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean allMatch(Predicate<? super E> predicate) {
        if (predicate == null) throw new NullPointerException();
        Node current = head;
        while (current != null) {
            if (!predicate.test(current.element)) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    @Override
    public Optional<E> min(Comparator<? super E> comparator) {
        if (isEmpty()) {
            return Optional.empty();
        }
        if (comparator == null) {
            throw new NullPointerException();
        }
        Node current = head;
        E min = current.element;
        current = current.next;
        while (current != null) {
            if (comparator.compare(current.element, min) < 0) {
                min = current.element;
            }
            current = current.next;
        }
        return Optional.ofNullable(min);
    }

    @Override
    public Optional<E> max(Comparator<? super E> comparator) {
        if (isEmpty()) {
            return Optional.empty();
        }
        if (comparator == null) {
            throw new NullPointerException();
        }
        Node current = head;
        E max = current.element;
        current = current.next;
        while (current != null) {
            if (comparator.compare(current.element, max) > 0) {
                max = current.element;
            }
            current = current.next;
        }
        return Optional.ofNullable(max);
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int index = 0;
        Node current = head;
        while (current != null) {
            result[index++] = current.element;
            current = current.next;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoublyLinkedList)) return false;
        DoublyLinkedList<?> other = (DoublyLinkedList<?>) o;
        if (size != other.size) return false;
        Iterator<E> it1 = this.iterator();
        Iterator<?> it2 = other.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            if (!it1.next().equals(it2.next())) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (E e : this) {
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        }
        return hashCode;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node current = head;
        while (current != null) {
            sb.append(current.element);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private void unlink(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        node.element = null;
        size--;
    }

    private class DoublyLinkedListIterator implements Iterator<E> {
        private Node current = head;
        private Node lastReturned = null;
        private boolean canRemove = false;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            lastReturned = current;
            current = current.next;
            canRemove = true;
            return lastReturned.element;
        }

        @Override
        public void remove() {
            if (!canRemove) throw new IllegalStateException();
            unlink(lastReturned);
            lastReturned = null;
            canRemove = false;
        }
    }
}   