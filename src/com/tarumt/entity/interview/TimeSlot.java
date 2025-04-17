package com.tarumt.entity.interview;

import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.list.ListInterface;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.validation.annotation.Max;
import com.tarumt.utility.validation.annotation.Min;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class TimeSlot {
    private static final LocalTime START_TIME = LocalTime.of(9, 0);
    private static final Duration INTERVAL = Duration.ofMinutes(30);

    private final LocalDate date;
    // The index of the time slot on the given date.
    // The slot index ranges from 0 to 17, corresponding to 30-minute intervals from 9 AM to 6 PM.
    // Slot 0 represents 9:00 AM - 9:30 AM, Slot 1 represents 9:30 AM - 10:00 AM, and so on.
    private final int slotIndex;

    public TimeSlot(LocalDate date, int slotIndex) {
        this.date = date;
        this.slotIndex = slotIndex;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getSlotIndex() {
        return slotIndex;
    }

    public LocalTime getStartTime() {
        return START_TIME.plus(INTERVAL.multipliedBy(slotIndex));
    }

    public LocalTime getEndTime() {
        return getStartTime().plus(INTERVAL);
    }

    public LocalDateTime getStartDateTime() {
        return LocalDateTime.of(date, getStartTime());
    }

    public LocalDateTime getEndDateTime() {
        return LocalDateTime.of(date, getEndTime());
    }

    public boolean isPast() {
        return getEndDateTime().isBefore(Context.getDateTime());
    }

    public String getTimeRangeString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return getStartTime().format(formatter) + "-" + getEndTime().format(formatter);
    }

    public static LocalTime getSlotStartTime(int slot) {
        return START_TIME.plus(INTERVAL.multipliedBy(slot));
    }

    public static LocalTime getSlotEndTime(int slot) {
        return TimeSlot.getSlotStartTime(slot).plus(INTERVAL);
    }

    public boolean isAvailable() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return false;
        }

        if (isPast()) {
            return false;
        }

        LocalDate today = Context.getDateTime().toLocalDate();
        if (date.equals(today)) {
            int todayStartSlot = getTodayStartSlot();
            return todayStartSlot != -1 && slotIndex >= todayStartSlot;
        }
        return true;
    }

    public static int getTodayStartSlot() {
        LocalTime now = Context.getDateTime().toLocalTime();
        LocalTime earliestTime = now.plusHours(2);

        if (earliestTime.isBefore(now)) {
            return -1;
        }

        earliestTime = LocalTime.of(earliestTime.getHour(), 0);

        if (earliestTime.isAfter(LocalTime.of(17, 30)) || earliestTime.equals(LocalTime.of(18, 0))) {
            return -1;
        }

        if (earliestTime.isBefore(START_TIME)) {
            earliestTime = START_TIME;
        }

        long minutesDifference = Duration.between(START_TIME, earliestTime).toMinutes();
        return (int) (minutesDifference / INTERVAL.toMinutes());
    }

    public static ListInterface<TimeSlot> generateWeekTimeSlot() {
        ListInterface<TimeSlot> timeSlots = new DoublyLinkedList<>();
        LocalDate startDate = Context.getDateTime().toLocalDate();
        for (int day = 0; day < 7; day++) {
            LocalDate currentDate = startDate.plusDays(day);
            for (int slotIndex = 0; slotIndex < 18; slotIndex++) {
                TimeSlot timeSlot = new TimeSlot(currentDate, slotIndex);
                timeSlots.add(timeSlot);
            }
        }
        return timeSlots;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TimeSlot that = (TimeSlot) o;
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
        return "Time Slot\n" +
                "|  Date        => " + date + ",\n" +
                "|  Time Range  => " + getTimeRangeString();
    }
}