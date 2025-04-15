package com.tarumt.adt;

import com.tarumt.adt.list.List;
import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.Arrays;

import java.util.Comparator;

public class Arrays {
    public static <T> List<T> asList(T... a) {
        List<T> list = new DoublyLinkedList<>();
        if (a != null) {
            for (T element : a) list.add(element);
        }
        return list;
    }

    public static <T> void sort(T[] a, Comparator<? super T> c) {
        if (a == null) {
            throw new NullPointerException("Array cannot be null");
        }
        if (a.length <= 1) {
            return;
        }
        Comparator<? super T> comparator = (c != null) ? c :
                (x, y) -> ((Comparable<? super T>) x).compareTo(y);

        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Object[a.length];
        mergeSort(a, aux, 0, a.length - 1, comparator);
    }

    private static <T> void mergeSort(T[] a, T[] aux, int low, int high, Comparator<? super T> c) {
        if (low >= high) {
            return;
        }

        int mid = low + (high - low) / 2;
        mergeSort(a, aux, low, mid, c);
        mergeSort(a, aux, mid + 1, high, c);
        merge(a, aux, low, mid, high, c);
    }

    private static <T> void merge(T[] a, T[] aux, int low, int mid, int high, Comparator<? super T> c) {
        for (int i = low; i <= high; i++) {
            aux[i] = a[i];
        }

        int i = low;
        int j = mid + 1;
        int k = low;

        while (i <= mid && j <= high) {
            if (c.compare(aux[i], aux[j]) <= 0) {
                a[k++] = aux[i++];
            } else {
                a[k++] = aux[j++];
            }
        }

        while (i <= mid) {
            a[k++] = aux[i++];
        }
    }
}