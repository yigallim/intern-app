/**
 * @author Lim Yuet Yang
 */
package com.tarumt.utility.common;

import com.tarumt.adt.list.ArrayToLinked;
import com.tarumt.adt.function.ProviderLambda;
import com.tarumt.utility.pretty.banana.BananaUtils;
import com.tarumt.utility.validation.IntegerCondition;

import java.util.Objects;

import com.tarumt.adt.list.ListInterface;
import com.tarumt.adt.list.DoublyLinkedList;

public class Menu {
    private final Input input = new Input();
    private final ListInterface<Choice> choices = new DoublyLinkedList<>();
    private ProviderLambda<String> bannerProvider = () -> "";
    private ProviderLambda<String> headerProvider = () -> "";
    private ProviderLambda<String> footerProvider = () -> "";
    private ProviderLambda<String> exitLabelProvider;
    private Runnable beforeEachRunnable;
    private Runnable afterEachRunnable;
    private ProviderLambda<Boolean> terminationCondition;

    public Menu banner(String banner) {
        this.bannerProvider = () -> banner;
        return this;
    }

    public Menu banner(ProviderLambda<String> bannerProvider) {
        this.bannerProvider = bannerProvider;
        return this;
    }

    public Menu header(String header) {
        String currentHeader = this.headerProvider.get();
        this.headerProvider = () -> currentHeader + header;
        return this;
    }

    public Menu header(ProviderLambda<String> headerProvider) {
        String currentHeader = this.headerProvider.get();
        this.headerProvider = () -> currentHeader + headerProvider.get();
        return this;
    }

    public Menu choice(Choice... choices) {
        this.choices.merge(ArrayToLinked.asList(choices).filter(Objects::nonNull));
        return this;
    }

    public Menu footer(String footer) {
        this.footerProvider = () -> footer;
        return this;
    }

    public Menu footer(ProviderLambda<String> footerProvider) {
        this.footerProvider = footerProvider;
        return this;
    }

    public Menu exit(String exitLabel) {
        this.exitLabelProvider = () -> exitLabel;
        return this;
    }

    public Menu exit(ProviderLambda<String> exitLabelProvider) {
        this.exitLabelProvider = exitLabelProvider;
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
        String banner = bannerProvider.get();
        String header = headerProvider.get();
        String footer = footerProvider.get();
        String exitLabel = exitLabelProvider.get();

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

    private ListInterface<Integer> getNumRange() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        for (int i = 1; i <= choices.size() + 1; i++) {
            list.add(i);
        }
        return list;
    }

    public Menu terminate(ProviderLambda<Boolean> condition) {
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

            if (terminationCondition != null && terminationCondition.get()) {
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
