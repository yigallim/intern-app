/**
 * @author Lim Yuet Yang
 */

package com.tarumt.adt.function;

/**
 * Represents a supplier of results.
 * This is a functional interface whose functional method is get().
 *
 * @param <T> the type of results supplied by this supplier
 */
public interface ProviderLambda<T> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    T get();
}