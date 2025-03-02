package com.tarumt;

import com.tarumt.util.Log;

public class InternApplicationProgram {

    public static void main(String[] args) {
        Log.info("Intern Application Program starting");
        System.out.println();

        // Start here
        // Create and start the Job Posting UI
        JobPostingUI jobUI = new JobPostingUI();
        jobUI.start();
        
        
        System.out.println();
        Log.info("Intern Application Program shutting down");
    }
}
