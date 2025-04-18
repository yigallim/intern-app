package com.tarumt.test;

import com.tarumt.control.InterviewController;
import com.tarumt.dao.Initializer;

public class Temp2 {
    public static void main(String[] args) {
        System.out.println(InterviewController.getInstance().buildJobApplicationStatusTable(Initializer.getJobApplications()));
    }
}
