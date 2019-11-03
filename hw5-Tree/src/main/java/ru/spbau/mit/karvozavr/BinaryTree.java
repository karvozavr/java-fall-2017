package ru.spbau.mit.karvozavr;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Binary search tree implementation without balancing.
 * Invariant: left child is less or equal then current node.
 *
 * @param <T> type of stored elements
 */
public class BinaryTree<T extends Comparable<T>> {

    @Nullable
    private Node<T> root;
    private int size;

    /**
     * size getter.
     *
     * @return size of the Tree
     */
    public int size() {
        return size;
    }

    /**
     * Add element to the Tree.
     *
     * @param element to add
     */
    public void add(@NotNull T element) {
        if (root == null) {
            root = new Node<>(element);
            ++size;
            return;
        }
        Node<T> node = root;
        while (true) {
            if (element.compareTo(node.data) <= 0) {
                if (node.left == null) {
                    node.left = new Node<>(element);
                    ++size;
                    return;
                } else {
                    node = node.left;
                }
            } else {
                if (node.right == null) {
                    node.right = new Node<>(element);
                    ++size;
                    return;
                } else {
                    node = node.right;
                }
            }
        }
    }

    /**
     * Check if the Tree contains element.
     *
     * @param element to search for
     * @return if the element in the Tree
     */
    public boolean contains(@NotNull T element) {
        return containsRecursive(element, root);
    }

    /**
     * Check if the Tree contains element recursive implementation.
     *
     * @param element to search for
     * @param node    current node
     * @return if the element in the Tree
     */
    @Contract("_, null -> false")
    private boolean containsRecursive(T element, Node<T> node) {
        return node != null && (element.compareTo(node.data) == 0
                || containsRecursive(element, node.left)
                || containsRecursive(element, node.right));
    }

    /**
     * Nested class of Binary Tree Node
     *
     * @param <T> type of stored element
     */
    private static class Node<T> {
        private Node<T> left;
        private Node<T> right;
        private T data;

        public Node(@NotNull T element) {
            this.data = element;
        }
    }
}
