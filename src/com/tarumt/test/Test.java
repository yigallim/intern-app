package com.tarumt.test;

import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.list.ListInterface;
import com.tarumt.entity.interview.TimeSlot;
import com.tarumt.utility.common.Context;

public class Test {

    private static boolean isTimeSlotAvailable(TimeSlot timeSlot, ListInterface<TimeSlot> bookedSlots) {
        if (timeSlot == null || bookedSlots == null) {
            return false;
        }
        return timeSlot.isAvailable() && !isBooked(timeSlot, bookedSlots);
    }

    private static boolean isBooked(TimeSlot timeSlot, ListInterface<TimeSlot> bookedSlots) {
        if (timeSlot == null || bookedSlots == null) {
            return false;
        }
        return bookedSlots.contains(timeSlot);
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 24; i++) {
        Context.setClockTime(1, 30);
//            int slot = BookedSlot.getTodayStartSlot();
//            System.out.println("i = " + i);
//            System.out.println("slot = " + slot);
//            System.out.println(new BookedSlot(Context.getDate(), slot).getStartTime());
//            System.out.println();
//            System.out.println();
//        }

//        AtomicInteger availableCount = new AtomicInteger(0);
//        InterviewService.getInstance().iterateTimeSlots((bookedSlot, available) -> {
//            if (available) {
//                availableCount.incrementAndGet();
//            }
//            System.out.println(bookedSlot + " " + available);
//            System.out.println();
//        });
//        System.out.println("Total available slots: " + availableCount.get());

//        for (int i = 0; i < 18; i++) {
//            TimeSlot slot = new TimeSlot(Context.getDate(), i);
//            System.out.println(slot.getTimeRangeString() + ", " + slot.isAvailable());
//        }

        TimeSlot.generateWeekTimeSlot().forEach(System.out::println);
        boolean hasFreeSlot = TimeSlot.generateWeekTimeSlot().anyMatch(
                (timeSlot) -> isTimeSlotAvailable(timeSlot, new DoublyLinkedList<TimeSlot>())
        );
        System.out.println(hasFreeSlot);
    }
}
