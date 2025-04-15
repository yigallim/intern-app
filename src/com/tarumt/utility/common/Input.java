package com.tarumt.utility.common;

import com.tarumt.utility.pretty.EnumPrint;
import com.tarumt.utility.validation.*;

import java.util.Scanner;
import java.util.function.BiFunction;

import com.tarumt.adt.list.List;

public class Input {
    private final Scanner scanner;
    private final String exitKey;
    private boolean exitKeyEnabled;
    public static final int INT_EXIT_VALUE = Integer.MIN_VALUE;
    public static final double DOUBLE_EXIT_VALUE = Double.MIN_VALUE;
    public static final String STRING_EXIT_VALUE = "__EXIT_89f5-7c3b-42d7-a6f3__";

    public Input(String exitKey) {
        this.scanner = new Scanner(System.in);
        this.exitKey = exitKey;
        this.exitKeyEnabled = true;
    }

    public Input() {
        this("x");
    }

    public Input withoutExitKey() {
        this.exitKeyEnabled = false;
        return this;
    }

    private void reloadExitKey() {
        this.exitKeyEnabled = true;
    }

    public void clickAnythingToContinue() {
        System.out.print("Press any key to continue...");
        scanner.nextLine();
    }

    public boolean confirm(String msg) {
        StringCondition condition = ConditionFactory.string().regex("^[xy]$|^[XY]$", "Invalid input, please input X or Y");
        return this.withoutExitKey().getString(msg, condition).equalsIgnoreCase("y");
    }

    public int getInt(String message, IntegerCondition condition) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (exitKeyEnabled && input.equalsIgnoreCase(exitKey)) {
                return INT_EXIT_VALUE;
            }

            try {
                condition.validate(input);
                this.reloadExitKey();
                return Integer.parseInt(input);
            } catch (ValidationException ex) {
                Log.error(ex.getMessage());
            }
        }
    }

    public double getDouble(String message, DecimalCondition condition) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (exitKeyEnabled && input.equalsIgnoreCase(exitKey)) {
                return DOUBLE_EXIT_VALUE;
            }

            try {
                condition.validate(input);
                this.reloadExitKey();
                return Float.parseFloat(input);
            } catch (ValidationException ex) {
                Log.error(ex.getMessage());
            }
        }
    }

    public String getString(String message, StringCondition condition) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (exitKeyEnabled && input.equalsIgnoreCase(exitKey)) {
                return STRING_EXIT_VALUE;
            }

            try {
                condition.validate(input);
                this.reloadExitKey();
                return input;
            } catch (ValidationException ex) {
                Log.error(ex.getMessage());
            }
            this.reloadExitKey();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getEnum(String message, Class<T> enumType) {
        return getEnum(message, enumType, (min, max) -> "Choose an option [" + min + "-" + max + "] => ", 32);
    }

    @SuppressWarnings("unchecked")
    public <T> T getEnum(String message, Class<T> enumType, int columnWidth) {
        return getEnum(message, enumType, (min, max) -> "Choose an option [" + min + "-" + max + "] => ", columnWidth);
    }

    @SuppressWarnings("unchecked")
    public <T> T getEnum(String message, Class<T> enumType, BiFunction<Integer, Integer, String> customMessage) {
        return getEnum(message, enumType, customMessage, 32);
    }

    @SuppressWarnings("unchecked")
    public <T> T getEnum(String message, Class<T> enumType, BiFunction<Integer, Integer, String> customMessage, int columnWidth) {
        Object[] constants = enumType.getEnumConstants();
        System.out.println(message);
        EnumPrint.multiColumnPrint(constants, 3, "| ", "", columnWidth);

        int min = 1, max = constants.length;
        IntegerCondition condition = ConditionFactory.integer().min(min).max(max);

        int choice = this.getInt(customMessage.apply(min, max), condition);

        if (exitKeyEnabled && choice == INT_EXIT_VALUE) {
            return null;
        }

        this.reloadExitKey();
        return (T) constants[choice - 1];
    }

    public <T> T getObjectFromList(String message, List<T> options) {
        return getObjectFromList(message, options, 32);
    }

    public <T> T getObjectFromList(String message, List<T> options, int columnWidth) {
        return getObjectFromList(message, options, (min, max) -> "Choose an option [" + min + "-" + max + "] => ", columnWidth);
    }

    public <T> T getObjectFromList(String message, List<T> options, BiFunction<Integer, Integer, String> customMessage) {
        return getObjectFromList(message, options, customMessage, 32);
    }

    public <T> T getObjectFromList(String message, List<T> options, BiFunction<Integer, Integer, String> customMessage, int columnWidth) {
        if (options == null || options.isEmpty()) {
            System.out.println(message);
            System.out.println("No options available!");
            return null;
        }

        System.out.println(message);
        EnumPrint.multiColumnPrint(options, 3, "| ", "", columnWidth);

        int min = 1, max = options.size();
        IntegerCondition condition = ConditionFactory.integer().min(min).max(max);

        int choice = this.getInt(customMessage.apply(min, max), condition);

        if (exitKeyEnabled && choice == INT_EXIT_VALUE) {
            return null;
        }

        this.reloadExitKey();
        return options.get(choice - 1);
    }
}