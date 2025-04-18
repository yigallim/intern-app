package com.tarumt.test;


import com.tarumt.control.InterviewController;
import com.tarumt.dao.Initializer;
import com.tarumt.utility.common.Context;

public class ReportTest2 {

    public static void main(String[] args) {
        Context.setCompany(Initializer.getCompanies().get(0));
        InterviewController.getInstance().buildMostandLeastInterviewedJobPosting();
    }
}