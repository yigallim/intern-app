package com.tarumt.pretty;

public class EnumPrint {
    public static void multiColumnPrint(Object[] constants, int columns) {
        multiColumnPrint(constants, columns, "", "");
    }

    public static void multiColumnPrint(Object[] constants, int columns, String leading, String trailing) {
        if (constants == null || constants.length == 0 || columns <= 0) {
            System.out.println("No values to display.");
            return;
        }

        int columnWidth = 30;
        int rows = (int) Math.ceil((double) constants.length / columns);

        for (int i = 0; i < rows; i++) {
            System.out.print(leading);

            for (int j = 0; j < columns; j++) {
                int index = i + j * rows;
                if (index < constants.length) {
                    String displayText = (index + 1) + ") " + constants[index];
                    System.out.printf("%-" + columnWidth + "s",
                            TabularPrint.truncate(displayText, columnWidth + (j == columns - 1 ? columnWidth / 4 : -2)));
                }
            }

            System.out.println(trailing);
        }
    }
}