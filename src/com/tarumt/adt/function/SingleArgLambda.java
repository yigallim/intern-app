/**
 * @author Lim Yuet Yang
 */

package com.tarumt.adt.function;

/**
 * A single argument functional interface for use in composition,
 * similar to Function interface in Java standard library.
 *
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 */
public interface SingleArgLambda<T, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T t);
}