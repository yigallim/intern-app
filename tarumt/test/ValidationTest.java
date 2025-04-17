package com.tarumt.test;

import com.tarumt.utility.validation.Condition;
import com.tarumt.utility.validation.ConditionFactory;

import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.list.Arrays;

public class ValidationTest {
    public static void main(String[] args) {
        intTest();
        System.out.println("\n\n");
        decimalTest();
        System.out.println("\n\n");
        stringTest();
    }

    static void intTest() {
        System.out.println("[Integer Test]");
        Condition condition = ConditionFactory.integer().min(1).max(5);

        // 1) Value = 1 (within 1..5)
        String msg1 = condition.safeValidate(1);
        System.out.println("Value = 1       => " + msg1);

        // 2) Value = 0 (below min=1)
        String msg2 = condition.safeValidate(0);
        System.out.println("Value = 0       => " + msg2);

        // 3) Value = 6 (above max=5)
        String msg3 = condition.safeValidate(6);
        System.out.println("Value = 6       => " + msg3);

        // 4) Value = "1" (string but parseable as integer=1)
        String msg4 = condition.safeValidate("1");
        System.out.println("Value = \"1\"     => " + msg4);

        // 5) Value = "0" (string but parseable as integer=0 => below min=1)
        String msg5 = condition.safeValidate("0");
        System.out.println("Value = \"0\"     => " + msg5);

        // 6) Value = 1.5 (double => not an integer)
        String msg6 = condition.safeValidate(1.5);
        System.out.println("Value = 1.5     => " + msg6);

        // 7) Value = 0.5 (double => not an integer)
        String msg7 = condition.safeValidate(0.5);
        System.out.println("Value = 0.5     => " + msg7);

        // 8) Value = "1.5" (string => parseable, but has decimal => not integer)
        String msg8 = condition.safeValidate("1.5");
        System.out.println("Value = \"1.5\"   => " + msg8);

        // 9) Value = "0.5" (string => parseable, but decimal => not integer)
        String msg9 = condition.safeValidate("0.5");
        System.out.println("Value = \"0.5\"   => " + msg9);

        // 9) Value = "0.5" (string => parseable, but decimal => not integer)
        String msg10 = condition.safeValidate("abc");
        System.out.println("Value = \"abc\"   => " + msg10);

        String msg11 = ConditionFactory.integer().enumeration(new DoublyLinkedList<Integer>(Arrays.asList(1, 2, 3, 4, 5))).safeValidate(1);
        System.out.println("Value = 1   => " + msg11);

        String msg12 = ConditionFactory.integer().enumeration(new DoublyLinkedList<Integer>(Arrays.asList(1, 2, 3, 4, 5))).safeValidate("1");
        System.out.println("Value = \"1\"   => " + msg12);
    }

    static void decimalTest() {
        System.out.println("[Decimal Test]");

        // Using floatType() => numeric parse is allowed, decimals are fine
        // min(0) => must be >= 0
        // max(5) => must be <= 5

        // 1) Value = 2 (integer but still parseable as double => OK)
        String dmsg1 = ConditionFactory.decimal().min(0).max(5).safeValidate(2);
        System.out.println("Value = 2       => " + dmsg1);

        // 2) Value = 4.5 (within 0..5)
        String dmsg2 = ConditionFactory.decimal().min(0).max(5).safeValidate(4.5);
        System.out.println("Value = 4.5     => " + dmsg2);

        // 3) Value = 5.0 (exactly max=5)
        String dmsg3 = ConditionFactory.decimal().min(0).max(5).safeValidate(5.0);
        System.out.println("Value = 5.0     => " + dmsg3);

        // 4) Value = 5.1 (above max=5)
        String dmsg4 = ConditionFactory.decimal().min(0).max(5).safeValidate(5.1);
        System.out.println("Value = 5.1     => " + dmsg4);

        // 5) Value = -1 (below min=0)
        String dmsg5 = ConditionFactory.decimal().min(0).max(5).safeValidate(-1);
        System.out.println("Value = -1      => " + dmsg5);

        // 6) Value = "4.5" (string parseable as double => within range)
        String dmsg6 = ConditionFactory.decimal().min(0).max(5).safeValidate("4.5");
        System.out.println("Value = \"4.5\"   => " + dmsg6);

        // 7) Value = "abc" (string not parseable)
        String dmsg7 = ConditionFactory.decimal().min(0).max(5).safeValidate("abc");
        System.out.println("Value = \"abc\"   => " + dmsg7);

        // 8) Value = 0 (on the boundary => >= 0)
        String dmsg8 = ConditionFactory.decimal().min(0).max(5).safeValidate(0);
        System.out.println("Value = 0       => " + dmsg8);

        // 9) Value = 5 (on the boundary => <= 5)
        String dmsg9 = ConditionFactory.decimal().min(0).max(5).safeValidate(5);
        System.out.println("Value = 5       => " + dmsg9);
    }

    static void stringTest() {
        System.out.println("[String Test]");

        // 1) Plain string check (default message)
        String s1 = ConditionFactory.string().safeValidate("Hello");
        System.out.println("Value = \"Hello\"         => " + s1);

        // 2) Passing a non-string should fail => "Value must be a string"
        String s2 = ConditionFactory.string().safeValidate(123);
        System.out.println("Value = 123              => " + s2);

        // 3) Overriding the default .string() error message
        String s3 = ConditionFactory.string("Must be a string (custom)!").safeValidate(123);
        System.out.println("Value = 123 (custom msg) => " + s3);

        // 4) .minLength and .maxLength checks
        //    Example: string length must be between 3 and 6
        Condition lengthCondition = ConditionFactory
                .string()
                .min(3, "String length must be >= 3 (override)")
                .max(6, "String length must be <= 6 (override)");

        String s4a = lengthCondition.safeValidate("Hi");
        System.out.println("Value = \"Hi\"            => " + s4a);

        String s4b = lengthCondition.safeValidate("Hello");
        System.out.println("Value = \"Hello\"         => " + s4b);

        String s4c = lengthCondition.safeValidate("HelloWorld");
        System.out.println("Value = \"HelloWorld\"     => " + s4c);

        // 5) Regex test: only uppercase letters, length at least 2
        Condition regexCondition = ConditionFactory
                .string()
                .min(2)
                .regex("^[A-Z]+$", "Must contain only uppercase letters");

        String s5a = regexCondition.safeValidate("ABC");
        System.out.println("Value = \"ABC\"           => " + s5a);

        String s5b = regexCondition.safeValidate("AbC");
        System.out.println("Value = \"AbC\"           => " + s5b);

        String s5c = regexCondition.safeValidate("A");
        System.out.println("Value = \"A\"             => " + s5c);

        // 6) Enumeration test: only "hello" or "hi" is allowed
        Condition enumCondition = ConditionFactory
                .string()
                .enumeration(Arrays.asList("hello", "hi"), "Only [hello, hi] allowed!");

        String s6a = enumCondition.safeValidate("hello");
        System.out.println("Value = \"hello\"         => " + s6a);

        String s6b = enumCondition.safeValidate("hi");
        System.out.println("Value = \"hi\"            => " + s6b);

        String s6c = enumCondition.safeValidate("bye");
        System.out.println("Value = \"bye\"           => " + s6c);
    }
}
