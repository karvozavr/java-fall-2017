package ru.spbau.mit.karvozavr.functional;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Some functional functions for collections
 */
public class Collections {

    /**
     * Map function to array
     *
     * @param function to map
     * @param array    data
     * @param <T>      input type
     * @param <R>      output type
     * @return list of function results
     */
    @SuppressWarnings("unchecked")
    public static <T, R> @NotNull List<R> map(@NotNull Function1<? super T, ? extends R> function, @NotNull Iterable<T> array) {
        List<R> list = new ArrayList<>();
        for (T x : array) {
            R apply = function.apply(x);
            list.add(apply);
        }
        return list;
    }

    /**
     * Filter array by predicate
     *
     * @param predicate filter
     * @param array     data
     * @param <T>       input type
     * @return list of function results
     */
    @SuppressWarnings("unchecked")
    public static <T> @NotNull List<T> filter(@NotNull Predicate<? super T> predicate, @NotNull Iterable<T> array) {
        List<T> list = new ArrayList<>();
        for (T t : array) {
            if (predicate.apply(t)) {
                list.add(t);
            }
        }
        return list;
    }

    /**
     * Take while predicate is true
     *
     * @param predicate filter
     * @param array     data
     * @param <T>       input type
     * @return array of function results
     */
    @SuppressWarnings("unchecked")
    public static <T> @NotNull List<T> takeWhile(@NotNull Predicate<? super T> predicate, @NotNull Iterable<T> array) {
        List<T> list = new ArrayList<>();
        for (T t : array) {
            if (predicate.apply(t)) {
                list.add(t);
            } else {
                break;
            }
        }

        return list;
    }

    /**
     * Take while predicate is false
     *
     * @param predicate filter
     * @param array     data
     * @param <T>       input type
     * @return array of function results
     */
    @SuppressWarnings("unchecked")
    public static <T> @NotNull List<T> takeUnless(@NotNull Predicate<? super T> predicate, @NotNull Iterable<T> array) {
        return takeWhile(predicate.not(), array);
    }

    /**
     * Fold left <a href="https://en.wikipedia.org/wiki/Fold_(higher-order_function))">Wiki</a>
     * foldl :: (b -> a -> b) -> b -> Foldable a -> b
     *
     * @param function   func
     * @param initial    ini
     * @param collection foldable
     * @param <T>        collection element type
     * @param <R>        return and initial value type
     * @return result of foldl on given collection
     */
    public static <T, R> @NotNull R foldl(@NotNull Function2<? super R, ? super T, ? extends R> function, R initial, @NotNull Collection<T> collection) {
        Iterator<T> iterator = collection.iterator();
        R result = initial;
        while (iterator.hasNext()) {
            result = function.apply(result, iterator.next());
        }

        return result;
    }

    /**
     * Fold right <a href="https://en.wikipedia.org/wiki/Fold_(higher-order_function))">Wiki</a>
     * foldr :: (a -> b -> b) -> b -> Foldable a -> b
     *
     * @param function   func
     * @param initial    ini
     * @param collection foldable
     * @param <T>        collection element type
     * @param <R>        return and initial value type
     * @return result of foldr on given collection
     */
    public static <T, R> @NotNull R foldr(@NotNull Function2<? super T, ? super R, ? extends R> function, R initial, @NotNull Collection<T> collection) {
        Iterator<T> iterator = collection.iterator();
        if (iterator.hasNext()) {
            return foldrHelper(function, function.apply(iterator.next(), initial), iterator);
        } else {
            return initial;
        }
    }

    /**
     * Fold right implementation <a href="https://en.wikipedia.org/wiki/Fold_(higher-order_function))">Wiki</a>
     *
     * @param function func
     * @param initial  ini
     * @param iterator collection iterator
     * @param <T>      collection element type
     * @param <U>      return and initial value type
     * @return result of foldr on given collection
     */
    private static <T, U> @NotNull U foldrHelper(@NotNull Function2<? super T, ? super U, ? extends U> function,
                                                 @NotNull U initial,
                                                 @NotNull Iterator<T> iterator) {
        if (iterator.hasNext()) {
            return foldrHelper(function, function.apply(iterator.next(), initial), iterator);
        } else {
            return initial;
        }
    }
}
