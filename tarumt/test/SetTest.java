package com.tarumt.test;

import com.tarumt.adt.list.ListInterface;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.JobApplication;
import com.tarumt.utility.pretty.TabularPrint;

public class SetTest {
    public static void main(String[] args) {
        ListInterface<JobApplication> test = Initializer.getJobApplications();
        TabularPrint.printTabular(test, true, "default");
    }
}