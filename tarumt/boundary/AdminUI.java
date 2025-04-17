package com.tarumt.boundary;

import com.tarumt.control.AdminService;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Menu;
import com.tarumt.utility.validation.ConditionFactory;
import com.tarumt.utility.validation.IntegerCondition;

public class AdminUI {

    private final Input input;

    public AdminUI(Input input) {
        this.input = input;
    }

    public void menu() {
        AdminService adminService = AdminService.getInstance();
        new Menu()
                .banner("Admin")
                .header(() -> "==> Welcome, Admin  |  " + Context.getDate() + " <==")
                .choice(
                        new Menu.Choice("📑 Company Management", adminService::manageCompany),
                        new Menu.Choice("💼 Job Management", adminService::manageJob),
                        new Menu.Choice("👤 Applicant Management", adminService::manageApplicant),
                        new Menu.Choice("🔎 Matching Engine", Log::na),
                        new Menu.Choice("⏰ Modify Time", adminService::modifyTime)
                )
                .exit("<Logout>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public int getFastForwardDays() {
        System.out.println("<== Modify Time [X to Exit] ==>");
        IntegerCondition condition = ConditionFactory.integer().min(1).max(28);
        return input.getInt("| Fast Forward Days (n) => ", condition);
    }
}
