package com.tarumt.test;

import com.tarumt.utility.pretty.Chart;

import com.tarumt.adt.list.Arrays;

public class ChartTest {
    public static void main(String[] args) {
        Chart.barChart(
                Arrays.asList("IT", "Marketing", "Engineering", "Healthcare", "Sales"),
                Arrays.asList(120, 80, 150, 95, 110),
                "Department Performance",
                100,
                '█',
                true
        );
    }
}
