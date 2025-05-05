/**
 * @author Lim Yuet Yang
 */

package com.tarumt.boundary;

import java.time.Instant;

import com.tarumt.control.AdminController;
import com.tarumt.utility.common.*;
import com.tarumt.utility.validation.ConditionFactory;
import com.tarumt.utility.validation.IntegerCondition;

public class AdminUI {

    private final Input input;

    public AdminUI(Input input) {
        this.input = input;
    }

    public void menu() {
        AdminController adminController = AdminController.getInstance();
        new Menu()
                .banner("Admin")
                .header(() -> "==> Welcome, Admin  |  " + Strings.formatDateTime(Context.getDateTime()) + " <==")
                .choice(
                        new Menu.Choice("📑 Manage Company", adminController::manageCompany),
                        new Menu.Choice("💼 Manage Job", adminController::manageJob),
                        new Menu.Choice("👤 Manage Applicant", adminController::manageApplicant),
                        new Menu.Choice("📅 Manage Interview & Applications", adminController::manageInterview),
                        new Menu.Choice("⏰ Modify Time", adminController::modifyTime))
                .exit("<Logout>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void modifyTimeMenu() {
        AdminController adminController = AdminController.getInstance();
        new Menu()
                .header("==> Time Modification <==")
                .choice(
                        new Menu.Choice("⏱️ Fast Forward Hours", adminController::modifyHours),
                        new Menu.Choice("📅 Fast Forward Days", adminController::modifyDays))
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public int getFastForwardDays() {
        System.out.println("<== Fast Forward Time (Days) [X to Exit] ==>");
        IntegerCondition condition = ConditionFactory.integer().min(1).max(28);
        return input.getInt("| Fast Forward Days (n) => ", condition);
    }

    public int getFastForwardHours() {
        System.out.println("<== Fast Forward Time (Hours) [ X to Exit ] ==>");
        IntegerCondition condition = ConditionFactory.integer().min(1).max(12);
        return input.getInt("|\n| Fast Forward Hours (n) ==> ", condition);
    }

    public void displayTimeModificationSuccess(int amount, String unit) {
        System.out.println();
        System.out.println("Time has been fast-forwarded by " + amount + " " + unit);
        System.out.println("Current system time: " + Instant.now(Context.getClock()));
        input.clickAnythingToContinue();
    }
}
