package com.tarumt;

import com.tarumt.control.AdminController;
import com.tarumt.control.ApplicantController;
import com.tarumt.control.CompanyController;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Menu;

public class InternApplicationProgram {

    public static void main(String[] args) {
        Log.info("Intern Application Program starting");
        System.out.println();

//        Context.setClockTime(10, 0);
//        Context.setApplicant(Initializer.getApplicants().get(0));
//        new ApplicantUI(new Input()).accessMenu();

        new Menu()
                .banner("Intern Application")
                .header("==> Intern Application Program <==")
                .choice(
                        new Menu.Choice("Access as Admin", AdminController.getInstance()::run),
                        new Menu.Choice("Access as Employer", CompanyController.getInstance()::accessEmployer),
                        new Menu.Choice("Access as Applicant", ApplicantController.getInstance()::accessApplicant))
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
