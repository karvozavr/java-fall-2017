package ru.spbau.mit.karvozavr.functional;

import org.jetbrains.annotations.NotNull;

/**
 * Predicate of one argument
 * @param <T> type of the argument
 */
public interface Predicate<T> extends Function1<T, Boolean> {

    /**
     * Logical or
     * @param predicate g
     * @return f or g
     */
    default @NotNull Predicate<T> or(@NotNull Predicate<T> predicate) {
        return argument -> Predicate.this.apply(argument) || predicate.apply(argument);
    }

    /**
     * Logical and
     * @param predicate g
     * @return f and g
     */
    default @NotNull Predicate<T> and(@NotNull Predicate<T> predicate) {
        return argument -> Predicate.this.apply(argument) && predicate.apply(argument);
    }

    /**
     * Logical not
     * @return not f
     */
    default @NotNull Predicate<T> not() {
        return argument -> !Predicate.this.apply(argument);
    }

    /**
     * Constant true predicate
     */
    Predicate ALWAYS_TRUE = argument -> true;

    /**
     * Constant false predicate
     */
    Predicate ALWAYS_FALSE = argument -> false;
}
