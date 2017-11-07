package ru.spbau.mit.karvozavr.functional;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Some functional functions for collections
 */
public class Collections {

    /**
     * Map function to array
     * @param function to map
     * @param array data
     * @param <T> input type
     * @param <R> output type
     * @return array of function results
     */
    @SuppressWarnings("unchecked")
    public static <T, R> @NotNull R[] map(@NotNull Function1<? super T, R> function, @NotNull Iterable<T> array) {
        List<R> list = new ArrayList<>();
        for (T x : array) {
            R apply = function.apply(x);
            list.add(apply);
        }
        return (R[]) list.toArray();
    }

    /**
     * Filter array by predicate
     * @param predicate filter
     * @param array data
     * @param <T> input type
     * @param <R> output type
     * @return array of function results
     */
    @SuppressWarnings("unchecked")
    public static <T, R> @NotNull R[] filter(@NotNull Predicate<? super T> predicate, @NotNull Iterable<T> array) {
        List<T> list = new ArrayList<>();
        for (T t : array) {
            if (predicate.apply(t)) {
                list.add(t);
            }
        }
        return (R[]) list.toArray();
    }

    /**
     * Take while predicate is true
     * @param predicate filter
     * @param array data
     * @param <T> input type
     * @param <R> output type
     * @return array of function results
     */
    @SuppressWarnings("unchecked")
    public static <T, R> @NotNull R[] takeWhile(@NotNull Predicate<? super T> predicate, @NotNull Iterable<T> array) {
        List<T> list = new ArrayList<>();
        for (T t : array) {
            if (predicate.apply(t)) {
                list.add(t);
            } else {
                break;
            }
        }

        return (R[]) list.toArray();
    }

    /**
     * Take while predicate is false
     * @param predicate filter
     * @param array data
     * @param <T> input type
     * @param <R> output type
     * @return array of function results
     */
    @SuppressWarnings("unchecked")
    public static <T, R> @NotNull R[] takeUnless(@NotNull Predicate<? super T> predicate, @NotNull Iterable<T> array) {
        List<T> list = new ArrayList<>();
        for (T t : array) {
            if (!predicate.apply(t)) {
                list.add(t);
            } else {
                break;
            }
        }

        return (R[]) list.toArray();
    }

    /**
     * Fold left <a href="https://en.wikipedia.org/wiki/Fold_(higher-order_function))">Wiki</a>
     * @param function func
     * @param initial ini
     * @param collection foldable
     * @param <T> collection element type
     * @param <U> return and initial value type
     * @return result of foldl on given collection
     */
    public static <T, U> @NotNull U foldl(@NotNull Function2<? super T, ? super U, ? extends U> function, U initial, @NotNull Collection<T> collection) {
        Iterator<T> iterator = collection.iterator();
        U result = initial;
        while (iterator.hasNext()) {
            result = function.apply(iterator.next(), result);
        }

        return result;
    }

    /**
     * Fold right <a href="https://en.wikipedia.org/wiki/Fold_(higher-order_function))">Wiki</a>
     * @param function func
     * @param initial ini
     * @param collection foldable
     * @param <T> collection element type
     * @param <U> return and initial value type
     * @return result of foldr on given collection
     */
    public static <T, U> @NotNull U foldr(@NotNull Function2<? super T, ? super U, ? extends U> function, U initial, @NotNull Collection<T> collection) {
        Iterator<T> iterator = collection.iterator();
        if (iterator.hasNext()) {
            return foldrHelper(function, function.apply(iterator.next(), initial), iterator);
        } else {
            return initial;
        }
    }

    /**
     * Fold right implementation <a href="https://en.wikipedia.org/wiki/Fold_(higher-order_function))">Wiki</a>
     * @param function func
     * @param initial ini
     * @param iterator collection iterator
     * @param <T> collection element type
     * @param <U> return and initial value type
     * @return result of foldr on given collection
     */
    private static <T, U> @NotNull U foldrHelper(@NotNull Function2<? super T, ? super U, ? extends U> function, @NotNull U initial, @NotNull Iterator<T> iterator) {
        if (iterator.hasNext()) {
            return foldrHelper(function, function.apply(iterator.next(), initial), iterator);
        } else {
            return initial;
        }
    }
}
