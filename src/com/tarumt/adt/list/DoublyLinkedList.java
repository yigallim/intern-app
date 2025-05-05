/**
 * @author Lim Yuet Yang
 */

package com.tarumt.adt.list;

import java.util.Objects;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.tarumt.adt.function.SingleArgLambda;
import com.tarumt.adt.function.TestLambda;

public class DoublyLinkedList<E> implements ListInterface<E> {
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

    public DoublyLinkedList(ListInterface<? extends E> c) {
        this();
        if (c == null) throw new NullPointerException("Collection cannot be null");
        merge(c);
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
    public boolean merge(ListInterface<? extends E> c) {
        boolean modified = false;
        for (E element : c) {
            if (add(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean removeAll(ListInterface<?> c) {
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
    public E set(int index, E element) {
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

        E oldElement = current.element;
        current.element = element;
        return oldElement;
    }

    @Override
    public ListInterface<E> subList(int fromIndex, int toIndex) {
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
    public <R> ListInterface<R> map(SingleArgLambda<? super E, ? extends R> mapper) {
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
    public ListInterface<E> filter(TestLambda<? super E> testLambda) {
        if (testLambda == null) throw new NullPointerException();
        DoublyLinkedList<E> result = new DoublyLinkedList<>();
        Node current = head;
        while (current != null) {
            if (testLambda.test(current.element)) {
                result.add(current.element);
            }
            current = current.next;
        }
        return result;
    }

    /**
     * @author Yeoh Ming Zhe
     */
    @Override
    public void sort(Comparator<? super E> c) {
        if (c == null) throw new NullPointerException("Comparator cannot be null");
        if (size <= 1) return;
        if (size < 64) {
            insertionSort(c);
            return;
        }
        int minRun = calculateMinRun(size);

        for (int i = 0; i < size; i += minRun) {
            int end = Math.min(i + minRun - 1, size - 1);
            insertionSortRange(i, end, c);
        }

        for (int runSize = minRun; runSize < size; runSize *= 2) {
            for (int left = 0; left < size; left += 2 * runSize) {
                int mid = left + runSize - 1;
                int right = Math.min(left + 2 * runSize - 1, size - 1);

                if (mid < right) {
                    merge(left, mid, right, c);
                }
            }
        }
    }

    private int calculateMinRun(int n) {
        int r = 0;
        while (n >= 64) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    private void insertionSort(Comparator<? super E> c) {
        if (head == null || head.next == null) return;

        Node current = head.next;
        while (current != null) {
            E key = current.element;
            Node j = current.prev;

            while (j != null && c.compare(j.element, key) > 0) {
                j.next.element = j.element;
                j = j.prev;
            }

            if (j == null) {
                head.element = key;
            } else {
                j.next.element = key;
            }

            current = current.next;
        }
    }

    private void insertionSortRange(int start, int end, Comparator<? super E> c) {
        Node startNode = getNodeAt(start);
        Node endNode = getNodeAt(end);

        Node current = startNode.next;
        Node lastSorted = startNode;

        while (current != endNode.next && current != null) {
            E key = current.element;
            Node j = current.prev;

            while (j != null && j != startNode.prev && c.compare(j.element, key) > 0) {
                j.next.element = j.element;
                j = j.prev;
            }

            if (j == startNode.prev) {
                startNode.element = key;
            } else {
                j.next.element = key;
            }
            lastSorted = current;
            current = current.next;
        }
    }

    private Node getNodeAt(int index) {
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
        return current;
    }

    private void merge(int left, int mid, int right, Comparator<? super E> c) {
        DoublyLinkedList<E> temp = new DoublyLinkedList<>();

        Node leftNode = getNodeAt(left);
        Node rightNode = getNodeAt(mid + 1);
        Node leftEnd = getNodeAt(mid);
        Node rightEnd = getNodeAt(right);

        while (leftNode != leftEnd.next && rightNode != rightEnd.next && leftNode != null && rightNode != null) {
            if (c.compare(leftNode.element, rightNode.element) <= 0) {
                temp.add(leftNode.element);
                leftNode = leftNode.next;
            } else {
                temp.add(rightNode.element);
                rightNode = rightNode.next;
            }
        }
        while (leftNode != leftEnd.next && leftNode != null) {
            temp.add(leftNode.element);
            leftNode = leftNode.next;
        }
        while (rightNode != rightEnd.next && rightNode != null) {
            temp.add(rightNode.element);
            rightNode = rightNode.next;
        }
        Node current = getNodeAt(left);
        for (E element : temp) {
            current.element = element;
            current = current.next;
        }
    }

    @Override
    public boolean anyMatch(TestLambda<? super E> testLambda) {
        if (testLambda == null) throw new NullPointerException();
        Node current = head;
        while (current != null) {
            if (testLambda.test(current.element)) {
                return true;
            }
            current = current.next;
        }
        return false;
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