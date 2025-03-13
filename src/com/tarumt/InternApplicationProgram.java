package com.tarumt;

import com.tarumt.control.AdminService;
import com.tarumt.control.ApplicantService;
import com.tarumt.control.CompanyService;
import com.tarumt.utility.common.Log;

public class InternApplicationProgram {

    private static final AdminService adminService = new AdminService();
    private static final CompanyService companyService = new CompanyService();
    private static final ApplicantService applicantService = new ApplicantService();

    public static void main(String[] args) {
        Log.info("Intern Application Program starting");
        System.out.println();

        adminService.run();
//        companyService.accessEmployer();
//        applicantService.accessApplicant();

//        new Menu()
//                .banner("Intern Application")
//                .header("==> Intern Application Program <==")
//                .choice(
//                        new Menu.Choice("Access as Admin", adminService::run),
//                        new Menu.Choice("Access as Employer", companyService::accessEmployer),
//                        new Menu.Choice("Access as Applicant", applicantService::accessApplicant)
//                )
//                .exit("<Exit Program>")
//                .beforeEach(System.out::println)
//                .afterEach(() -> {
//                    System.out.println();
//                    Context.clearContext();
//                })
//                .run();

        System.out.println();
        Log.info("Intern Application Program shutting down");
    }
}
