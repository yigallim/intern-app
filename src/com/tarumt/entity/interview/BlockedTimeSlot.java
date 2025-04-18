package com.tarumt.entity.interview;

import com.tarumt.entity.Company;

public class BlockedTimeSlot {
    private Company company;
    private TimeSlot slot;

    public BlockedTimeSlot(Company company, TimeSlot slot) {
        this.company = company;
        this.slot = slot;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public TimeSlot getSlot() {
        return slot;
    }

    public void setSlot(TimeSlot slot) {
        this.slot = slot;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-11s ",
                slot != null ? slot.getDate().toString() : "N/A",
                slot != null ? slot.getTimeRangeString() : "N/A");
    }
}
