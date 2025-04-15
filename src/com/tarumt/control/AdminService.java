package com.tarumt.control;

import com.tarumt.boundary.AdminUI;
import com.tarumt.utility.common.Input;

public class AdminService {
    private static AdminService instance;
    private final AdminUI adminUI;

    private AdminService() {
        this.adminUI = new AdminUI();
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
}
