package com.tarumt.test;

import com.tarumt.entity.schedule.InterviewSchedule;

public class TestSchedule {
    public static void main(String[] args) {
        InterviewSchedule schedule = new InterviewSchedule();
        System.out.println(schedule.getCalendarView());
    }
}
