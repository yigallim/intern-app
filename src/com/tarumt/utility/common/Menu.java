package com.tarumt.utility.common;

import com.tarumt.adt.Arrays;
import com.tarumt.utility.pretty.banana.BananaUtils;
import com.tarumt.utility.pretty.banana.Layout;
import com.tarumt.utility.validation.IntegerCondition;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import com.tarumt.adt.list.List;
import com.tarumt.adt.list.DoublyLinkedList;

public class Menu {

    private final Input input = new Input();
    private final List<Choice> choices = new DoublyLinkedList<>();
    private Supplier<String> bannerSupplier = () -> "";
    private Supplier<String> headerSupplier = () -> "";
    private Supplier<String> footerSupplier = () -> "";
    private Supplier<String> exitLabelSupplier;
    private Runnable beforeEachRunnable;
    private Runnable afterEachRunnable;
    private BooleanSupplier terminationCondition;

    public Menu banner(String banner) {
        this.bannerSupplier = () -> banner;
        return this;
    }

    public Menu banner(Supplier<String> bannerSupplier) {
        this.bannerSupplier = bannerSupplier;
        return this;
    }

    public Menu header(String header) {
        String currentHeader = this.headerSupplier.get();
        this.headerSupplier = () -> currentHeader + header;
        return this;
    }

    public Menu header(Supplier<String> headerSupplier) {
        String currentHeader = this.headerSupplier.get();
        this.headerSupplier = () -> currentHeader + headerSupplier.get();
        return this;
    }

    public Menu choice(Choice... choices) {
        this.choices.addAll(Arrays.asList(choices));
        return this;
    }

    public Menu footer(String footer) {
        this.footerSupplier = () -> footer;
        return this;
    }

    public Menu footer(Supplier<String> footerSupplier) {
        this.footerSupplier = footerSupplier;
        return this;
    }

    public Menu exit(String exitLabel) {
        this.exitLabelSupplier = () -> exitLabel;
        return this;
    }

    public Menu exit(Supplier<String> exitLabelSupplier) {
        this.exitLabelSupplier = exitLabelSupplier;
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
        String banner = bannerSupplier.get();
        String header = headerSupplier.get();
        String footer = footerSupplier.get();
        String exitLabel = exitLabelSupplier.get();

        if (banner != null && !banner.isEmpty())
            System.out.println(BananaUtils.bananaify(banner + " >>>"));
        if (header != null && !header.isEmpty())
            System.out.println(header);

        int index = 1;
        for (Choice choice : choices) {
            printLabel(index, choice.getLabel());
            index++;
        }
        printLabel(index, exitLabel);

        if (footer != null && !footer.isEmpty())
            System.out.println(footer);

        System.out.println();
    }

    private void printLabel(int index, String label) {
        System.out.println(index + ". " + label);
    }

    private List<Integer> getNumRange() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
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

    public static class ExitMenuException extends RuntimeException {
        public ExitMenuException() {
            super();
        }
    }
}
