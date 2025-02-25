package com.tarumt.util;

import com.tarumt.pretty.EnumPrint;
import com.tarumt.validation.*;

import java.util.Scanner;
import java.util.function.BiFunction;

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

    public int getInt(String message, IntegerCondition condition) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (exitKeyEnabled && input.equalsIgnoreCase(exitKey)) {
                return INT_EXIT_VALUE;
            }

            try {
                condition.validate(input);
                return Integer.parseInt(input);
            } catch (ValidationException ex) {
                Log.error(ex.getMessage());
            }
            this.reloadExitKey();
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
                return Float.parseFloat(input);
            } catch (ValidationException ex) {
                Log.error(ex.getMessage());
            }
            this.reloadExitKey();
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
                return input;
            } catch (ValidationException ex) {
                Log.error(ex.getMessage());
            }
            this.reloadExitKey();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getEnum(String message, Class<T> enumType) {
        return getEnum(message, enumType, (min, max) -> "Choose an option [" + min + "-" + max + "] ==> ");
    }

    @SuppressWarnings("unchecked")
    public <T> T getEnum(String message, Class<T> enumType, BiFunction<Integer, Integer, String> customMessage) {
        Object[] constants = enumType.getEnumConstants();
        System.out.println(message);
        EnumPrint.multiColumnPrint(constants, 3, "| ", "");

        int min = 1, max = constants.length;
        IntegerCondition condition = ConditionFactory.integer().min(min).max(max);

        int choice = this.getInt(customMessage.apply(min, max), condition);

        if (exitKeyEnabled && choice == INT_EXIT_VALUE) {
            return null;
        }

        this.reloadExitKey();
        return (T) constants[choice - 1];
    }
}