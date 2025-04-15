package com.tarumt.entity.schedule;

import java.time.LocalDate;

class BookedSlot {
    private final LocalDate date;
    private final int slotIndex;

    public BookedSlot(LocalDate date, int slotIndex) {
        this.date = date;
        this.slotIndex = slotIndex;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getSlotIndex() {
        return slotIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        BookedSlot that = (BookedSlot) o;
        return slotIndex == that.slotIndex && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + slotIndex;
        return result;
    }

    @Override
    public String toString() {
        return "BookedSlot{" +
                "date=" + date +
                ", slotIndex=" + slotIndex +
                '}';
    }
}