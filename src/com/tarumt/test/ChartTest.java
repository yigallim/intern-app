package com.tarumt.test;

import com.tarumt.utility.pretty.Chart;

import com.tarumt.adt.list.ArrayToLinked;

public class ChartTest {
    public static void main(String[] args) {
        Chart.barChart(
                ArrayToLinked.asList("IT", "Marketing", "Engineering", "Healthcare", "Sales"),
                ArrayToLinked.asList(120, 80, 150, 95, 110),
                "Department Performance",
                100,
                '█',
                true
        );
    }
}
