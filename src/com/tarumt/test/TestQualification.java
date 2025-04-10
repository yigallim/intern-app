package com.tarumt.test;

import com.tarumt.control.QualificationService;
import com.tarumt.utility.common.Menu;

public class TestQualification {
    public static void main(String[] args) {
        QualificationService service = new QualificationService();

        new Menu()
                .banner("Qualification Management")
                .header("Select an option:")
                .choice(
                        new Menu.Choice("➕ Add Qualifications", service::manageQualifications),
                        new Menu.Choice("📋 View Qualifications", service::displayQualifications)
                )
                .exit("<Exit Program>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }
}
