package com.tarumt;

import com.tarumt.boundary.ApplicantUI;
import com.tarumt.control.AdminService;
import com.tarumt.control.ApplicantService;
import com.tarumt.control.CompanyService;
import com.tarumt.dao.Initializer;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Menu;

public class InternApplicationProgram {

    public static void main(String[] args) {
        Log.info("Intern Application Program starting");
        System.out.println();

        Context.setClockTime(10, 0);
//        Context.setApplicant(Initializer.getApplicants().get(0));
//        new ApplicantUI(new Input()).accessMenu();
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
