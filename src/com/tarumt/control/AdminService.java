package com.tarumt.control;

import com.tarumt.boundary.AdminUI;
import com.tarumt.utility.common.Input;

public class AdminService {
    private final AdminUI adminUI;
    private final CompanyService companyService;
    private final JobPostingService jobPostingService;
    private final ApplicantService applicantService;

    public AdminService() {
        Input input = new Input();
        this.adminUI = new AdminUI(input);
        this.companyService = new CompanyService();
        this.jobPostingService = new JobPostingService();
        this.applicantService = new ApplicantService();
    }

    public void run() {
        this.adminUI.menu(this);
    }

    public void manageCompany() {
        this.companyService.run();
    }

    public void manageJob() {
        this.jobPostingService.run();
    }

    public void manageApplicant() {
        this.applicantService.run();
    }
}
