package com.tarumt.test;

import com.tarumt.utility.pretty.banana.BananaUtils;
import com.tarumt.utility.pretty.banana.Font;
import com.tarumt.utility.pretty.banana.Layout;

public class BananaTest {
    public static void main(String[] args) {
        System.out.println(BananaUtils.bananaify("ABCDEFGHIJKLMNOP", Font.ANSI_SHADOW));
        System.out.println(BananaUtils.bananaify("QRSTUVWXYZ", Font.ANSI_SHADOW));
        System.out.println(BananaUtils.bananaify("abcdefghijklmnop", Font.ANSI_SHADOW));
        System.out.println(BananaUtils.bananaify("qrstuvwxyz", Font.ANSI_SHADOW));

//        for (Font font : Font.values()) {
//            System.out.println(font.getName());
//            System.out.println(BananaUtils.bananaify("Hello, World!", font));
//            System.out.println();
//        }
    }
}
