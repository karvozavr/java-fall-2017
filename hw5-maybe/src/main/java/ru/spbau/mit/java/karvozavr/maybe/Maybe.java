package ru.spbau.mit.java.karvozavr.maybe;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.java.karvozavr.maybe.exception.MaybeIsNothingException;

import java.util.function.Function;

/**
 * Maybe class. (Similar to Haskell Prelude.Maybe)
 *
 * @param <U> type of stored value
 */
public class Maybe<U> {

    /**
     * Value storage.
     * Stores null (Nothing) or value (Just).
     */
    @Nullable
    private U value;

    /**
     * Nothing object.
     * It's actually identical for any type U.
     */
    private static Maybe<?> nothing = new Maybe<>(null);

    /**
     * Initialize value. Constructs correct Maybe.
     *
     * @param t value
     */
    private Maybe(@Nullable U t) {
        value = t;
    }

    /**
     * Get value if this is not Nothing.
     *
     * @return value
     * @throws MaybeIsNothingException if this is Nothing
     */
    @NotNull
    public U get() throws MaybeIsNothingException {
        if (value == null) {
            throw new MaybeIsNothingException();
        }
        return value;
    }

    /**
     * Check if this is a Just value.
     *
     * @return if this is a Just value
     */
    public boolean isPresent() {
        return value != null;
    }

    /**
     * Apply function to the stored value, if it is not Nothing.
     *
     * @param mapper function to apply
     * @param <T>    type of function return value
     * @return new Maybe that stores result of a function
     */
    @NotNull
    public <T> Maybe<T> map(@NotNull Function<? super U, ? extends T> mapper) {
        if (isPresent()) {
            return just(mapper.apply(value));
        } else {
            return nothing();
        }
    }

    /**
     * The only way to create Just value object.
     *
     * @param t   value to store in Maybe
     * @param <T> type of value
     * @return new Maybe with stored value
     */
    @NotNull
    public static <T> Maybe<T> just(@NotNull T t) {
        return new Maybe<>(t);
    }

    /**
     * The only way to create Nothing object.
     *
     * @param <T> type of hypothetically value
     * @return new Maybe Nothing
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public static <T> Maybe<T> nothing() {
        return (Maybe<T>) nothing;
    }
}
