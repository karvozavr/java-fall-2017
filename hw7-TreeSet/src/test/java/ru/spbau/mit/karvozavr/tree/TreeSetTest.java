package ru.spbau.mit.karvozavr.tree;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class TreeSetTest {

    @Test
    void testAddBase() {
        TreeSet<Integer> tree = new TreeSet<>();

        for (int i = 0; i < 100; i++) {
            assertThat(tree.contains(i), is(false));
        }

        for (int i = 0; i < 100; i++) {
            tree.add(i);
        }

        for (int i = 0; i < 100; i++) {
            assertThat(tree.contains(i), is(true));
        }
    }

    @Test
    void testCreateComparator() {
        TreeSet<Integer> tree = new TreeSet<>(Collections.reverseOrder());

        for (int i = 0; i < 100; i++) {
            tree.add(i);
        }

        assertThat(tree.first(), is(99));
        assertThat(tree.last(), is(0));
    }

    @Test
    void testContainsBase() {
        final TreeSet<String> tree = new TreeSet<>();
        assertThat(tree.contains("Lol"), is(false));
        tree.add("Lol");
        assertThat(tree.contains("Lol"), is(true));
    }

    @Test
    void testSizeBase() {
        final TreeSet<Integer> tree = new TreeSet<>();

        for (int i = 0; i < 100; i++) {
            tree.add(i);
        }

        assertThat(tree.size(), is(100));
    }

    @Test
    void testClear() {
        final TreeSet<Integer> tree = new TreeSet<>();

        for (int i = 0; i < 100; i++) {
            tree.add(i);
        }

        assertThat(tree.size(), is(100));

        tree.clear();

        assertThat(tree.isEmpty(), is(true));
        assertThat(tree.first(), is(nullValue()));
        assertThat(tree.last(), is(nullValue()));
        assertThat(tree.iterator().hasNext(), is(false));
        assertThat(tree.descendingIterator().hasNext(), is(false));
    }

    @Test
    void testSizeEmpty() {
        final TreeSet<Integer> tree = new TreeSet<>();
        assertThat(tree.size(), is(0));
    }

    @Test
    void testIsEmpty() {
        final TreeSet<Integer> tree = new TreeSet<>();
        assertThat(tree.isEmpty(), is(true));
        tree.add(42);
        assertThat(tree.isEmpty(), is(false));
    }

    @Test
    void testRemove() {
        final TreeSet<Integer> tree = new TreeSet<>();

        for (int i = 0; i < 100; i++) {
            tree.add(i);
        }

        assertThat(tree.remove(25), is(true));
        assertThat(tree.remove(42), is(true));
        assertThat(tree.contains(25), is(false));
        assertThat(tree.contains(42), is(false));

        assertThat(tree.size(), is(98));

        for (int i = 0; i < 100; i++) {
            if (i != 25 && i != 42)
                assertThat(tree.contains(i), is(true));
        }
    }

    @Test
    void testLower() {
        TreeSet<Integer> tree = new TreeSet<>();

        for (int i = 0; i < 100; i++) {
            tree.add(i);
        }

        assertThat(tree.lower(25), is(24));
        assertThat(tree.lower(0), is(nullValue()));
        assertThat(tree.lower(100500), is(99));
    }

    @Test
    void testHigher() {
        TreeSet<Integer> tree = new TreeSet<>();

        for (int i = 0; i < 100; i++) {
            tree.add(i);
        }

        assertThat(tree.higher(25), is(26));
        assertThat(tree.higher(99), is(nullValue()));
        assertThat(tree.higher(-10), is(0));
    }

    @Test
    void testFloor() {
        TreeSet<Integer> tree = new TreeSet<>();

        for (int i = 0; i < 100; i++) {
            tree.add(i);
        }

        assertThat(tree.floor(25), is(25));
        assertThat(tree.floor(-1), is(nullValue()));
        assertThat(tree.floor(100500), is(99));
    }

    @Test
    void testCeiling() {
        TreeSet<Integer> tree = new TreeSet<>();

        for (int i = 0; i < 100; i++) {
            tree.add(i);
        }

        assertThat(tree.ceiling(25), is(25));
        assertThat(tree.ceiling(-1), is(0));
        assertThat(tree.ceiling(100500), is(nullValue()));
    }
}