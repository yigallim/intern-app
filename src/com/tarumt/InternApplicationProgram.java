package com.tarumt;

import com.tarumt.control.AdminService;
import com.tarumt.control.ApplicantService;
import com.tarumt.control.CompanyService;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Menu;

import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

public class InternApplicationProgram {

    public static void main(String[] args) {
        Log.info("Intern Application Program starting");
        System.out.println();

        new Menu()
                .banner("Intern Application")
                .header("==> Intern Application Program <==")
                .choice(
                        new Menu.Choice("Access as Admin", AdminService.getInstance()::run),
                        new Menu.Choice("Access as Employer", CompanyService.getInstance()::accessEmployer),
                        new Menu.Choice("Access as Applicant", ApplicantService.getInstance()::accessApplicant))
                .exit("<Exit Program>")
                .beforeEach(System.out::println)
                .afterEach(() -> {
                    System.out.println();
                    Context.clearContext();
                })
                .run();

        System.out.println();
        Log.info("Intern Application Program shutting down");
    }
}
