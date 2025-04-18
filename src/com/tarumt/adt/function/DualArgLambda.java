package com.tarumt.adt.function;

/**
 * A functional interface that represents an operation that accepts two input arguments
 * and produces a result. This is similar to the standard BiFunction interface.
 *
 * @param <T> the type of the first input argument
 * @param <U> the type of the second input argument
 * @param <R> the type of the result
 */
public interface DualArgLambda<T, U, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t the first input argument
     * @param u the second input argument
     * @return the function result
     */
    R apply(T t, U u);
}