package com.tarumt.control;

import com.tarumt.boundary.AdminUI;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;

import java.time.Clock;
import java.time.Duration;

public class AdminController {
    private static AdminController instance;
    private final AdminUI adminUI;

    private AdminController() {
        Input input = new Input();
        this.adminUI = new AdminUI(input);
    }

    public static AdminController getInstance() {
        if (instance == null) {
            instance = new AdminController();
        }
        return instance;
    }

    public void run() {
        this.adminUI.menu();
    }

    public void manageCompany() {
        CompanyController.getInstance().run();
    }

    public void manageJob() {
        JobPostingController.getInstance().run();
    }

    public void manageApplicant() {
        ApplicantController.getInstance().run();
    }

    public void manageInterview() {
        InterviewController.getInstance().run();
    }

    public void modifyTime() {
        this.adminUI.modifyTimeMenu();
    }

    public void modifyHours() {
        int fastForwardHours = adminUI.getFastForwardHours();
        if (fastForwardHours == Input.INT_EXIT_VALUE) return;
        Duration offset = Duration.ofHours(fastForwardHours);
        Clock clock = Clock.offset(Context.getClock(), offset);
        Context.setClock(clock);
        adminUI.displayTimeModificationSuccess(fastForwardHours, "hours");
    }

    public void modifyDays() {
        int fastForwardDays = adminUI.getFastForwardDays();
        if (fastForwardDays == Input.INT_EXIT_VALUE) return;
        Duration offset = Duration.ofDays(fastForwardDays);
        Clock clock = Clock.offset(Context.getClock(), offset);
        Context.setClock(clock);
        adminUI.displayTimeModificationSuccess(fastForwardDays, "days");
    }
}
