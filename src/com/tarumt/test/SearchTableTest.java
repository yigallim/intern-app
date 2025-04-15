package com.tarumt.test;

import com.tarumt.dao.Initializer;
import com.tarumt.entity.JobPosting;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.pretty.TabularPrint;
import com.tarumt.utility.search.FuzzySearch;
import com.tarumt.utility.validation.StringCondition;

import com.tarumt.adt.set.Set;
import com.tarumt.adt.list.List;
import com.tarumt.adt.list.DoublyLinkedList;

public class SearchTableTest {
    public static void main(String[] args) {
        List<JobPosting> jobPostings = Initializer.getJobPostings();
        Input input = new Input();

        while (true) {
            String query = input.getString("query (or type 'exit' to quit) => ", new StringCondition());

            if (query.equalsIgnoreCase("exit")) {
                System.out.println("Exiting search...");
                break;
            }

            FuzzySearch.Result<JobPosting> result = FuzzySearch.searchList(JobPosting.class, jobPostings, query);
            Set<String> matchSet = result.getMatches();
            List<String> matches = new DoublyLinkedList<>(matchSet);

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
