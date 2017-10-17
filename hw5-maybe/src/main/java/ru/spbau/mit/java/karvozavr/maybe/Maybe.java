package ru.spbau.mit.java.karvozavr.maybe;

import ru.spbau.mit.java.karvozavr.maybe.exception.MaybeIsNothingException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class Maybe<U> {

    @Nullable
    private U value;

    private Maybe(U t) {
        value = t;
    }

    @NotNull
    public U get() throws MaybeIsNothingException {
        if (value == null) {
            throw new MaybeIsNothingException();
        }
        return value;
    }

    public boolean isPresent() {
        return value != null;
    }

    @NotNull
    public <T> Maybe<T> map(Function<U, T> mapper) {
        if (isPresent()) {
            return just(mapper.apply(value));
        } else {
            return nothing();
        }
    }

    @NotNull
    public static <T> Maybe<T> just(@NotNull T t) {
        return new Maybe<>(t);
    }

    @NotNull
    public static <T> Maybe<T> nothing() {
        return new Maybe<>(null);
    }
}
