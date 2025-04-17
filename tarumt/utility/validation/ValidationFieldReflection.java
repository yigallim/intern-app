package com.tarumt.utility.validation;

import java.lang.reflect.Field;

public class ValidationFieldReflection {
    private ValidationFieldReflection() {
    }

    public static Field getField(Class clazz, String field) {
        Field result;
        try {
            result = clazz.getDeclaredField(field);
        } catch (NoSuchFieldException e) {
            result = null;
        }
        return result;
    }
}
