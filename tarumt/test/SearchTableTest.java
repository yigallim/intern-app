package com.tarumt.test;

import com.tarumt.dao.Initializer;
import com.tarumt.entity.JobPosting;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.pretty.TabularPrint;
import com.tarumt.utility.search.FuzzySearch;
import com.tarumt.utility.validation.StringCondition;

import com.tarumt.adt.set.SetInterface;
import com.tarumt.adt.list.ListInterface;
import com.tarumt.adt.list.DoublyLinkedList;

public class SearchTableTest {
    public static void main(String[] args) {
        ListInterface<JobPosting> jobPostings = Initializer.getJobPostings();
        Input input = new Input();

        while (true) {
            String query = input.getString("query (or type 'exit' to quit) => ", new StringCondition());

            if (query.equalsIgnoreCase("exit")) {
                System.out.println("Exiting search...");
                break;
            }

            FuzzySearch.Result<JobPosting> result = FuzzySearch.searchList(JobPosting.class, jobPostings, query);
            SetInterface<String> matchSet = result.getMatches();
            ListInterface<String> matches = new DoublyLinkedList<>();
            for (String match: matchSet)
                matches.add(match);

            System.out.print("\nRelated Results => ");
            for (int i = 0; i < matches.size(); i++) {
                System.out.print(matches.get(i));
                if (i < matches.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();

            TabularPrint.printTabular(result.getSubList(), true, matchSet, "default");

        }
    }
}
