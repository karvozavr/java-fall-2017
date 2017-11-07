package ru.spbau.mit.karvozavr.functional;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Collection test not hot
 *
 * 2 + 2 = 4, -1 = 3 Boom, Skrrrr
 */
class CollectionsTest {

    @Test
    void testMap() {
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            array.add(i);
        }
        Object[] array2 = Collections.map(x -> 2 * x, array);
        for (int i = 0; i < 10; i++) {
            array.set(i, array.get(i) * 2);
        }
        assertArrayEquals(array2, array.toArray());
    }

    @Test
    void testFilter() {
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            array.add(i);
        }
        Object[] array2 = Collections.filter(x -> x % 2 == 0, array);
        array = new ArrayList<>();
        for (int i = 0; i < 10; i += 2) {
            array.add(i);
        }
        assertArrayEquals(array2, array.toArray());
    }

    @Test
    void testTakeWhile() {
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            array.add(i);
        }
        Object[] array2 = Collections.takeWhile(x -> x < 5, array);
        array = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            array.add(i);
        }
        assertArrayEquals(array2, array.toArray());
    }

    @Test
    void testTakeUnless() {
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            array.add(i);
        }
        Object[] array2 = Collections.takeUnless(x -> x >= 5, array);
        array = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            array.add(i);
        }
        assertArrayEquals(array2, array.toArray());
    }

    @Test
    void testTakeFoldr() {
        ArrayList<Integer> array = new ArrayList<>();
        int actual = 0;
        for (int i = 0; i < 10; i++) {
            array.add(i);
            if (i % 2 != 0)
                actual += i;
            else
                actual -= i;
        }
        Integer result = Collections.foldr((Integer a, Integer b) -> a - b, 0, array);

        assertThat(result, equalTo(actual));
    }

    @Test
    void testTakeFoldl() {
        ArrayList<Integer> array = new ArrayList<>();
        int actual = 0;
        for (int i = 0; i < 10; i++) {
            array.add(i);
            actual -= i;
        }
        Integer result = Collections.foldl((Integer a, Integer b) -> a - b, 0, array);

        assertThat(result, equalTo(actual));
    }
}