package ru.spbau.mit.karvozavr.functional;

import org.jetbrains.annotations.NotNull;

/**
 * One argument function
 *
 * @param <T> argument type
 * @param <R> return value type
 */
public interface Function1<T, R> {

    /**
     * Create composition of this (f) and other function (g)
     *
     * @param function g
     * @param <R2>     return type of g
     * @return g ○ f
     */

    default <R2> @NotNull Function1<T, R2> compose(@NotNull Function1<R, R2> function) {
        return argument -> function.apply(Function1.this.apply(argument));
    }

    /**
     * Apply function to argument
     *
     * @param argument argument of the function
     * @return return value of the function
     */
    R apply(T argument);
}
