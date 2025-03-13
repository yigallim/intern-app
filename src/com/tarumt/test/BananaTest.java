package com.tarumt.test;

import com.tarumt.utility.pretty.banana.BananaUtils;
import com.tarumt.utility.pretty.banana.Font;
import com.tarumt.utility.pretty.banana.Layout;

public class BananaTest {
    public static void main(String[] args) {
        System.out.println(BananaUtils.bananaify("Intern Application >>>"));
        System.out.println(BananaUtils.bananaify("Employer >>>"));
        System.out.println(BananaUtils.bananaify("Applicant >>>"));
        System.out.println(BananaUtils.bananaify("Chipotle >>>"));

        System.out.println(BananaUtils.bananaify("Intern Application >>>", Font.ANSI_SHADOW, Layout.DEFAULT, Layout.SMUSH_R));
        System.out.println(BananaUtils.bananaify("Employer >>>", Layout.FITTED, Layout.FITTED));
        System.out.println(BananaUtils.bananaify("Applicant >>>", Layout.FITTED, Layout.FITTED));
        System.out.println(BananaUtils.bananaify("Chipotle >>>", Layout.SMUSH_R, Layout.FITTED));

        for (Font font : Font.values()) {
            System.out.println(font.getName());
            System.out.println(BananaUtils.bananaify("Hello, World!", font));
            System.out.println();
        }
    }
}
