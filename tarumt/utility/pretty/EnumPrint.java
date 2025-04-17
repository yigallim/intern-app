package com.tarumt.utility.pretty;

import com.tarumt.entity.BaseEntity;
import com.tarumt.utility.common.Strings;
import com.tarumt.adt.list.ListInterface;

import com.tarumt.adt.function.SingleArgLambda;

public class EnumPrint {

    public static void multiColumnPrint(Object[] constants, int columns) {
        multiColumnPrint(constants, columns, "", "", 0);
    }

    public static void multiColumnPrint(Object[] constants, int columns, String leading, String trailing) {
        multiColumnPrint(constants, columns, leading, trailing, 0);
    }

    public static void multiColumnPrint(Object[] constants, int columns, String leading, String trailing, int columnWidth) {

        multiColumnPrint(constants, columns, leading, trailing, columnWidth, item ->
                item instanceof BaseEntity ? ((BaseEntity) item).toShortString() : item.toString());
    }

    public static void multiColumnPrint(Object[] constants, int columns, String leading, String trailing, int columnWidth, SingleArgLambda<Object, String> formatter) {
        if (constants == null || constants.length == 0 || columns <= 0) {
            System.out.println("No values to display.");
            return;
        }

        if (columnWidth == 0) {
            int rows = (int) Math.ceil((double) constants.length / columns);
            columnWidth = rows > 3 ? 34 : 28;
        }
        int rows = (int) Math.ceil((double) constants.length / columns);

        for (int i = 0; i < rows; i++) {
            System.out.print(leading);

            for (int j = 0; j < columns; j++) {
                int index = i + j * rows;
                if (index < constants.length) {
                    Object item = constants[index];
                    String displayText = (index + 1) + ") " + formatter.apply(item);
                    System.out.printf("%-" + columnWidth + "s",
                            Strings.truncate(displayText, columnWidth + (j == columns - 1 ? columnWidth / 4 : -2)));
                }
            }
            System.out.println(trailing);
        }
    }

    public static <T> void multiColumnPrint(ListInterface<T> constants, int columns) {
        multiColumnPrint(constants, columns, "", "", 0);
    }

    public static <T> void multiColumnPrint(ListInterface<T> constants, int columns, String leading, String trailing) {
        multiColumnPrint(constants, columns, leading, trailing, 0);
    }

    public static <T> void multiColumnPrint(ListInterface<T> constants, int columns, String leading, String trailing, int columnWidth) {

        multiColumnPrint(constants, columns, leading, trailing, columnWidth, item ->
                item instanceof BaseEntity ? ((BaseEntity) item).toShortString() : item.toString());
    }

    public static <T> void multiColumnPrint(ListInterface<T> constants, int columns, String leading, String trailing, int columnWidth, SingleArgLambda<T, String> formatter) {
        if (constants == null || constants.isEmpty() || columns <= 0) {
            System.out.println("No values to display.");
            return;
        }

        if (columnWidth == 0) {
            int rows = (int) Math.ceil((double) constants.size() / columns);
            columnWidth = rows > 3 ? 34 : 28;
        }
        int rows = (int) Math.ceil((double) constants.size() / columns);

        for (int i = 0; i < rows; i++) {
            System.out.print(leading);

            for (int j = 0; j < columns; j++) {
                int index = i + j * rows;
                if (index < constants.size()) {
                    T item = constants.get(index);
                    String displayText = (index + 1) + ") " + formatter.apply(item);
                    System.out.printf("%-" + columnWidth + "s",
                            Strings.truncate(displayText, columnWidth + (j == columns - 1 ? columnWidth / 4 : -2)));
                }
            }
            System.out.println(trailing);
        }
    }
}