package com.tarumt.utility.common;

import com.tarumt.entity.Applicant;
import com.tarumt.entity.Company;

public class Context {
    private static Company company;
    private static Applicant applicant;

    public static Company getCompany() {
        return company;
    }

    public static void setCompany(Company company) {
        Context.company = company;
    }

    public static Applicant getApplicant() {
        return applicant;
    }

    public static void setApplicant(Applicant applicant) {
        Context.applicant = applicant;
    }

    public static boolean isEmployer() {
        return Context.company != null && Context.applicant == null;
    }

    public static boolean isApplicant() {
        return Context.company == null && Context.applicant != null;
    }

    public static boolean isAdmin() {
        return Context.company == null && Context.applicant == null;
    }

    public static void clearContext() {
        Context.company = null;
        Context.applicant = null;
    }
}
