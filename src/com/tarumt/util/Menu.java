package com.tarumt.util;

import com.tarumt.validation.Condition;
import com.tarumt.validation.IntegerCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class Menu {

    private final Input input = new Input();
    private final List<Choice> choices = new ArrayList<>();
    private String header;
    private String footer;
    private String exitLabel;
    private Runnable beforeEachRunnable;
    private Runnable afterEachRunnable;
    private BooleanSupplier terminationCondition;

    public Menu header(String header) {
        this.header = header;
        return this;
    }

    public Menu choice(Choice... choices) {
        this.choices.addAll(Arrays.asList(choices));
        return this;
    }

    public Menu footer(String footer) {
        this.footer = footer;
        return this;
    }

    public Menu exit(String exitLabel) {
        this.exitLabel = exitLabel;
        return this;
    }

    public Menu beforeEach(Runnable runnable) {
        this.beforeEachRunnable = runnable;
        return this;
    }

    public Menu afterEach(Runnable runnable) {
        this.afterEachRunnable = runnable;
        return this;
    }

    private void printMenu() {
        if (header != null) System.out.println(header);

        int index = 1;
        for (Choice choice : choices) {
            printLabel(index, choice.getLabel());
            index++;
        }
        printLabel(index, exitLabel);

        if (footer != null) System.out.println(footer);

        System.out.println();
    }

    private void printLabel(int index, String label) {
        System.out.println(index + ". " + label);
    }

    private List<Integer> getNumRange() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= choices.size() + 1; i++) {
            list.add(i);
        }
        return list;
    }

    public Menu terminate(BooleanSupplier condition) {
        this.terminationCondition = condition;
        return this;
    }

    public void run() {
        while (true) {
            if (choices.isEmpty()) {
                System.out.println("No available choices. Exiting menu.");
                return;
            }

            printMenu();
            IntegerCondition condition = new IntegerCondition().enumeration(getNumRange());
            int selection = input.withoutExitKey().getInt("Enter your choice => ", condition);

            if (selection == choices.size() + 1) {
                return;
            }

            Choice choice = choices.get(selection - 1);

            if (beforeEachRunnable != null) {
                beforeEachRunnable.run();
            }
            choice.execute();

            if (terminationCondition != null && terminationCondition.getAsBoolean()) {
                break;
            }

            if (afterEachRunnable != null) {
                afterEachRunnable.run();
            }
        }
    }

    public static class Choice {

        private final String label;
        private final Runnable action;

        public Choice(String label, Runnable action) {
            this.label = label;
            this.action = action;
        }

        public String getLabel() {
            return label;
        }

        public void execute() {
            action.run();
        }
    }
}
