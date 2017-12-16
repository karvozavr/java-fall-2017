package ru.spbau.mit.karvozavr.functional;

import org.jetbrains.annotations.NotNull;

/**
 * Predicate of one argument
 *
 * @param <T> type of the argument
 */
@FunctionalInterface
public interface Predicate<T> extends Function1<T, Boolean> {

    /**
     * Logical or
     *
     * @param predicate g
     * @return f or g
     */
    default @NotNull Predicate<T> or(@NotNull Predicate<? super T> predicate) {
        return argument -> Predicate.this.apply(argument) || predicate.apply(argument);
    }

    /**
     * Logical and
     *
     * @param predicate g
     * @return f and g
     */
    default @NotNull Predicate<T> and(@NotNull Predicate<? super T> predicate) {
        return argument -> Predicate.this.apply(argument) && predicate.apply(argument);
    }

    /**
     * Logical not
     *
     * @return not f
     */
    default @NotNull Predicate<T> not() {
        return argument -> !Predicate.this.apply(argument);
    }

    /**
     * Constant true predicate
     */
    static <T> Predicate<T> alwaysTrue() {
        return argument -> true;
    }

    /**
     * Constant false predicate
     */
    static <T> Predicate<T> alwaysFalse() {
        return argument -> false;
    }
}
