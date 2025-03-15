package com.tarumt.boundary;

import com.tarumt.control.AdminService;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Menu;

public class AdminUI {
    private Input input;

    public AdminUI(Input input) {
        this.input = input;
    }

    public void menu(AdminService service) {
        new Menu()
                .banner("Admin")
                .header("==> Welcome, Admin <==")
                .choice(
                        new Menu.Choice("📑 Company Management", service::manageCompany),
                        new Menu.Choice("💼 Job Management", service::manageJob),
                        new Menu.Choice("👤 Applicant Management", service::manageApplicant),
                        new Menu.Choice("🔎 Matching Engine", Log::na)
                )
                .exit("<Logout>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

}
