package ru.spbau.mit.karvozavr.tree;

import org.junit.jupiter.api.Test;

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
    void testContainsBase() {
        final TreeSet<String> tree = new  TreeSet<>();
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
}