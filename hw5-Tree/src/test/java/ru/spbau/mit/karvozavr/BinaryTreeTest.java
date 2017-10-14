package ru.spbau.mit.karvozavr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    @Test
    void testContainsBase() {
        BinaryTree<String> tree = new BinaryTree<>();
        assertFalse(tree.contains("Lol"));
        tree.add("Lol");
        assertTrue(tree.contains("Lol"));
    }

    @Test
    void testAddBase() {
        BinaryTree<Integer> tree = new BinaryTree<>();

        for (int i = 0; i < 100; i++) {
            tree.add(i);
        }

        for (int i = 0; i < 100; i++) {
            assertTrue(tree.contains(i));
        }
    }

    @Test
    void testSizeBase() {
        BinaryTree<Integer> tree = new BinaryTree<>();

        for (int i = 0; i < 100; i++) {
            tree.add(i);
        }

       assertEquals(100, tree.size());
    }

    @Test
    void testSizeEmpty() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        assertEquals(0, tree.size());
    }

}