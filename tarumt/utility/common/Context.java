package com.tarumt.utility.common;

import com.tarumt.entity.Applicant;
import com.tarumt.entity.Company;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Context {
    private static Company company;
    private static Applicant applicant;
    private static Clock clock;

    static {
        clock = Clock.systemDefaultZone();
    }

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

    public static Clock getClock() {
        return clock;
    }

    public static void setClock(Clock clock) {
        Context.clock = clock;
    }

    public static void setClockTime(int hour, int minute) {
        LocalDateTime now = LocalDateTime.now(Clock.systemDefaultZone());
        LocalDate today = LocalDate.now(Clock.systemDefaultZone());
        LocalDateTime desiredDateTime = LocalDateTime.of(today, java.time.LocalTime.of(hour, minute));
        java.time.Duration offset = java.time.Duration.between(now, desiredDateTime);
        Context.clock = Clock.offset(Clock.systemDefaultZone(), offset);
    }

    public static LocalDate getDate() {
        return LocalDate.now(Context.clock);
    }

    public static LocalDateTime getDateTime() {
        return LocalDateTime.now(Context.clock);
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