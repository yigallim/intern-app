package com.tarumt.test;

import com.tarumt.adt.set.HashSet;
import com.tarumt.adt.set.Set;

public class SetTest {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();

        set.add("haha");
        set.add("haha");
        set.add("hahA");

        for (String s: set) {
            System.out.println(s);
        }

        System.out.println(set);
    }
}