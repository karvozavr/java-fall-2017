package ru.spbau.mit.karvozavr.functional;

import org.jetbrains.annotations.NotNull;

/**
 * Two argument function
 *
 * @param <T> first argument type
 * @param <U> second argument type
 * @param <R> return value type
 */
@FunctionalInterface
public interface Function2<T, U, R> {

    /**
     * Create composition of this (f) and other function (g)
     *
     * @param function g
     * @param <R2>     return type of g
     * @return function g ○ f
     */
    default  <R2> @NotNull Function2<T, U, R2> compose(@NotNull Function1<R, R2> function) {
        return (argument1, argument2) -> function.apply(Function2.this.apply(argument1, argument2));
    }

    /**
     * Bind first argument
     *
     * @param argument to bind
     * @return function f (argument, U)
     */
    default @NotNull Function1<U, R> bind1(T argument) {
        return  (argument2) -> Function2.this.apply(argument, argument2);
    }

    /**
     * Bind second argument
     *
     * @param argument to bind
     * @return function f (T, argument)
     */
    default @NotNull Function1<T, R> bind2(U argument) {
        return (argument1) -> Function2.this.apply(argument1, argument);
    }

    /**
     * Carry the function and apply first argument
     *
     * @param argument first argument
     * @return function f (argument, U)
     */
    default @NotNull Function1<U, R> curry(T argument) {
        return argument2 -> Function2.this.apply(argument, argument2);
    }

    /**
     * Apply function to argument
     *
     * @param argument1 first argument of the function
     * @param argument2 second argument of the function
     * @return return value of the function
     */
    R apply(T argument1, U argument2);
}
