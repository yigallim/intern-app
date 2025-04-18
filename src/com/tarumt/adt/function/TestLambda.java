/**
 * @author Lim Yuet Yang
 */

package com.tarumt.adt.function;

/**
 * Represents a predicate (boolean-valued function) of one argument.
 * This is a functional interface whose functional method is test(T).
 *
 * @param <T> the type of the input to the predicate
 */
public interface TestLambda<T> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t the input argument
     * @return true if the input argument matches the predicate, otherwise false
     */
    boolean test(T t);
}