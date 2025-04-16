package com.tarumt.test;

import com.tarumt.entity.interview.Schedule;

public class TestSchedule {
    public static void main(String[] args) {
        Schedule schedule = new Schedule();
        System.out.println(schedule.getCalendarView());
    }
}
