package com.tarumt.boundary;

import com.tarumt.control.AdminService;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Menu;

public class AdminUI {

    public void menu() {
        AdminService adminService = AdminService.getInstance();
        new Menu()
                .banner("Admin")
                .header("==> Welcome, Admin <==")
                .choice(
                        new Menu.Choice("📑 Company Management", adminService::manageCompany),
                        new Menu.Choice("💼 Job Management", adminService::manageJob),
                        new Menu.Choice("👤 Applicant Management", adminService::manageApplicant),
                        new Menu.Choice("🔎 Matching Engine", Log::na))
                .exit("<Logout>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }
}
