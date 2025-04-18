package com.tarumt.test;

import com.tarumt.control.InterviewController;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.Company;

public class ReportTest {
    public static void main(String[] args) {
        InterviewController controller = InterviewController.getInstance();
        Company company = Initializer.getCompanies().get(0);
        String temp = controller.buildJobInterviewCountTable(company, 120);
        System.out.println(temp);
    }
}
