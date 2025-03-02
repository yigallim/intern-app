package com.tarumt.adt;

/**
 * DoublyLinkedList implementation
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements ListInterface<T>, Iterable<T> {

    private Node firstNode;
    private Node lastNode;
    private int numberOfEntries;

    public DoublyLinkedList() {
        clear();
    }

    @Override
    public final void clear() {
        firstNode = null;
        lastNode = null;
        numberOfEntries = 0;
    }

    public DoublyLinkedList(DoublyLinkedList<T> other) {
        if (other == null) {
            firstNode = null;
            lastNode = null;
            numberOfEntries = 0;
            return;
        }
        firstNode = null;
        lastNode = null;
        numberOfEntries = 0;
        for (T item : other) {
            if (item != null) {
                copyFrom(other);
            }
        }
    }

    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);

        if (isEmpty()) {
            firstNode = newNode;
        } else {
            lastNode.next = newNode;
            newNode.previous = lastNode;
        }

        lastNode = newNode;
        numberOfEntries++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            Node newNode = new Node(newEntry);

            if (isEmpty() || (newPosition == numberOfEntries + 1)) {
                add(newEntry);
            } else if (newPosition == 1) {
                newNode.next = firstNode;
                firstNode.previous = newNode;
                firstNode = newNode;
                numberOfEntries++;
            } else {
                Node nodeBefore = getNodeAt(newPosition - 1);
                Node nodeAfter = nodeBefore.next;
                newNode.next = nodeAfter;
                newNode.previous = nodeBefore;
                nodeBefore.next = newNode;
                nodeAfter.previous = newNode;
                numberOfEntries++;
            }
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public T remove(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            if (givenPosition == 1) {
                result = firstNode.data;
                firstNode = firstNode.next;
                if (firstNode != null) {
                    firstNode.previous = null;
                } else {
                    lastNode = null;
                }
            } else if (givenPosition == numberOfEntries) {
                result = lastNode.data;
                lastNode = lastNode.previous;
                lastNode.next = null;
            } else {
                Node nodeToRemove = getNodeAt(givenPosition);
                result = nodeToRemove.data;
                Node nodeBefore = nodeToRemove.previous;
                Node nodeAfter = nodeToRemove.next;
                nodeBefore.next = nodeAfter;
                nodeAfter.previous = nodeBefore;
            }

            numberOfEntries--;
        }

        return result;
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            Node desiredNode = getNodeAt(givenPosition);
            desiredNode.data = newEntry;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public T getEntry(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = getNodeAt(givenPosition).data;
        }

        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                found = true;
            } else {
                currentNode = currentNode.next;
            }
        }

        return found;
    }

    @Override
    public int getLength() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];

        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null)) {
            result[index] = currentNode.data;
            currentNode = currentNode.next;
            index++;
        }

        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new DoublyLinkedListIterator();
    }

    private Node getNodeAt(int givenPosition) {
        Node currentNode;

        // Decide whether to traverse the list from front or back based on position
        if (givenPosition <= numberOfEntries / 2) {
            // Start from front
            currentNode = firstNode;
            for (int counter = 1; counter < givenPosition; counter++) {
                currentNode = currentNode.next;
            }
        } else {
            // Start from back
            currentNode = lastNode;
            for (int counter = numberOfEntries; counter > givenPosition; counter--) {
                currentNode = currentNode.previous;
            }
        }

        return currentNode;
    }

    // Additional methods for the Job Posting module
    private class DoublyLinkedListIterator implements Iterator<T> {

        private Node nextNode;

        private DoublyLinkedListIterator() {
            nextNode = firstNode;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                Node returnNode = nextNode;
                nextNode = nextNode.next;
                return returnNode.data;
            } else {
                throw new NoSuchElementException("Illegal call to next(); iterator is after end of list.");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<T> iterator = iterator();
        boolean first = true;
        while (iterator.hasNext()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(iterator.next());
            first = false;
        }
        sb.append("]");
        return sb.toString();
    }

    private void copyFrom(DoublyLinkedList<T> other) {
        for (T item : other) {
            if (item != null) { // Skip null items
                add(item);
            }
        }
    }

    private class Node {

        private T data;
        private Node next;
        private Node previous;

        private Node(T data) {
            this(data, null, null);
        }

        private Node(T data, Node next, Node previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
    }
}
