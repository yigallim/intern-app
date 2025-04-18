/**
 * @author Lim Yuet Yang
 */
package com.tarumt.entity.interview;

import com.tarumt.entity.Company;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        BlockedTimeSlot that = (BlockedTimeSlot) o;
        return Objects.equals(company, that.company) && Objects.equals(slot, that.slot);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(company);
        result = 31 * result + Objects.hashCode(slot);
        return result;
    }
}
