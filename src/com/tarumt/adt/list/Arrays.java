package com.tarumt.adt.list;

public class Arrays {
    public static <T> ListInterface<T> asList(T... a) {
        ListInterface<T> list = new DoublyLinkedList<>();
        if (a != null) {
            for (T element : a) list.add(element);
        }
        return list;
    }
}