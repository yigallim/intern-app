package com.tarumt.entity.interview;

import com.tarumt.utility.common.Strings;
import com.tarumt.adt.list.ListInterface;
import com.tarumt.adt.list.DoublyLinkedList;

import java.time.*;

public class Schedule {
    private static final LocalTime START_TIME = LocalTime.of(9, 0); // 9 AM
    private static final Duration INTERVAL = Duration.ofMinutes(30); // 30-minute intervals
    private static final int NUM_SLOTS_PER_DAY = 18; // 9 AM to 6 PM = 9 hours * 2 slots/hour
    private static final int DAYS_AHEAD = 7;

    private final ListInterface<TimeSlot> bookedSlots = new DoublyLinkedList<>();


    // Book a time slot if available
    public boolean bookSlot(LocalDate date, int slotIndex) {
        if (slotIndex < 0 || slotIndex >= NUM_SLOTS_PER_DAY || !isWeekday(date)) {
            return false; // Invalid slot or not a weekday
        }
        TimeSlot slot = new TimeSlot(date, slotIndex);
        if (bookedSlots.contains(slot)) {
            return false; // Already booked
        }
        bookedSlots.add(slot);
        return true;
    }

    // Helper: Check if a date is a weekday (Monday to Friday)
    private boolean isWeekday(LocalDate date) {
        DayOfWeek dow = date.getDayOfWeek();
        return dow.getValue() >= DayOfWeek.MONDAY.getValue() &&
                dow.getValue() <= DayOfWeek.FRIDAY.getValue();
    }

    // Helper: Get start time for a slot index
    private LocalTime getSlotStartTime(int slotIndex) {
        return START_TIME.plusMinutes(30L * slotIndex);
    }

    // Helper: Find the next available slot index for today
    private int findNextSlotIndex(LocalTime currentTime) {
        for (int i = 0; i < NUM_SLOTS_PER_DAY; i++) {
            LocalTime slotStart = getSlotStartTime(i);
            if (slotStart.isAfter(currentTime)) {
                return i;
            }
        }
        return NUM_SLOTS_PER_DAY; // No slots available today
    }

    public String getCalendarView() {
        StringBuilder calendarView = new StringBuilder();
        final int columnWidth = 12; // Fixed width for each column
        final int totalColumns = DAYS_AHEAD + 1; // Time slot column + days

        // Create horizontal divider line without + at column intersections
        StringBuilder dividerLine = new StringBuilder();
        for (int i = 0; i < totalColumns; i++) {
            dividerLine.append(Strings.repeat("-", columnWidth + 3)); // +3 accounts for "| " and " "
        }
        String horizontalDivider = dividerLine.append("-").toString();

        // Add title as a header row that spans all columns
        calendarView.append("Available Time Slots (9 AM to 6 PM)\n");

        // Divider after title
        calendarView.append(horizontalDivider).append("\n");

        // Add the days header
        calendarView.append("| ").append(String.format("%-" + columnWidth + "s", "Time Slot")).append(" |");
        LocalDate today = LocalDate.now();
        for (int i = 0; i < DAYS_AHEAD; i++) {
            LocalDate date = today.plusDays(i);
            String dayHeader = date.getDayOfWeek().name().substring(0, 3) + " " +
                    date.getDayOfMonth() + "/" + date.getMonthValue();
            calendarView.append(" ").append(String.format("%-" + columnWidth + "s", dayHeader)).append(" |");
        }
        calendarView.append("\n");

        // Divider after column headers
        calendarView.append(horizontalDivider).append("\n");

        // Iterate through each time slot for the day
        for (int i = 0; i < NUM_SLOTS_PER_DAY; i++) {
            LocalTime slotStartTime = getSlotStartTime(i);
            LocalTime slotEndTime = slotStartTime.plus(INTERVAL);
            String timeSlot = String.format("%02d:%02d-%02d:%02d",
                    slotStartTime.getHour(), slotStartTime.getMinute(),
                    slotEndTime.getHour(), slotEndTime.getMinute());

            calendarView.append("| ").append(String.format("%-" + columnWidth + "s", timeSlot)).append(" |");

            // Check each day for availability
            for (int j = 0; j < DAYS_AHEAD; j++) {
                LocalDate date = today.plusDays(j);
                int slotIndex = i;

                // Check if it's a weekend
                if (!isWeekday(date)) {
                    // Instead of applying the color to the formatted string, we format the raw text first
                    String naText = "[NA]";
                    // Add the colored text directly with padding calculated manually
                    calendarView.append(" ").append(Strings.errorHighlight(naText));
                    // Add padding after the text to maintain column width
                    calendarView.append(Strings.repeat(" ", columnWidth - naText.length())).append(" |");
                    continue;
                }

                // Check if the slot is booked
                boolean isBooked = false;
                for (TimeSlot bookedSlot : bookedSlots) {
                    if (bookedSlot.getDate().isEqual(date) && bookedSlot.getSlotIndex() == slotIndex) {
                        isBooked = true;
                        break;
                    }
                }

                // Show the slot status with color, handling padding separately
                if (isBooked) {
                    String bookedText = "[BOOKED]";
                    calendarView.append(" ").append(Strings.errorHighlight(bookedText));
                    calendarView.append(Strings.repeat(" ", columnWidth - bookedText.length())).append(" |");
                } else {
                    String freeText = "[FREE]";
                    calendarView.append(" ").append(Strings.successHighlight(freeText));
                    calendarView.append(Strings.repeat(" ", columnWidth - freeText.length())).append(" |");
                }
            }
            calendarView.append("\n");
        }

        // Bottom border
        calendarView.append(horizontalDivider).append("\n");

        return calendarView.toString();
    }

}