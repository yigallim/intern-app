package com.tarumt.validation.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Regex {
    String pattern();

    String message() default "";
}
