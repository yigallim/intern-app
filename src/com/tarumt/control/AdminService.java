package com.tarumt.control;

import com.tarumt.boundary.AdminUI;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Menu;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

public class AdminService {
    private static AdminService instance;
    private final AdminUI adminUI;

    private AdminService() {
        Input input = new Input();
        this.adminUI = new AdminUI(input);
    }

    public static AdminService getInstance() {
        if (instance == null) {
            instance = new AdminService();
        }
        return instance;
    }

    public void run() {
        this.adminUI.menu();
    }

    public void manageCompany() {
        CompanyService.getInstance().run();
    }

    public void manageJob() {
        JobPostingService.getInstance().run();
    }

    public void manageApplicant() {
        ApplicantService.getInstance().run();
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
