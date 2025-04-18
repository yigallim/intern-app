/**
 * @author Lim Yuet Yang
 */

package com.tarumt.adt.list;

public class ArrayToLinked {
    public static <T> ListInterface<T> asList(T... a) {
        ListInterface<T> linkedList = new DoublyLinkedList<>();
        if (a != null) {
            for (T element : a) linkedList.add(element);
        }
        return linkedList;
    }
}