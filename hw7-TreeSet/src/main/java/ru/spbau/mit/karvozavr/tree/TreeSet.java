package ru.spbau.mit.karvozavr.tree;

import org.jetbrains.annotations.NotNull;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;

public class TreeSet<E> extends AbstractSet<E> implements MyTreeSet<E> {

    private final Comparator<E> comparator;
    private Node<E> root = null;
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public TreeSet() {
        comparator = new DefaultComparator<>();
    }

    public TreeSet(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public MyTreeSet<E> descendingSet() {
        return null;
    }

    /**
     * Returns the first (lowest) element currently in this set.
     * @return the first (lowest) element currently in this set.
     */
    @Override
    public E first() {
        return head != null ? head.data : null;
    }

    /**
     * Returns the last (highest) element currently in this set.
     * @return the last (highest) element currently in this set
     */
    @Override
    public E last() {
        return tail != null ? tail.data : null;
    }

    @Override
    public E lower(E element) {
        return null;
    }

    @Override
    public E floor(E element) {
        return null;
    }

    @Override
    public E ceiling(E element) {
        return null;
    }

    @Override
    public E higher(E element) {
        return null;
    }

    @Override
    public boolean remove(Object element) {
        throw new UnsupportedOperationException();
    }

    /**
     * Check if the Tree contains element.
     *
     * @param element to search for
     * @return if the element in the Tree
     */
    @Override
    public boolean contains(Object element) {
        return containsRecursive(element, root);
    }

    /**
     * Check if the Tree contains element recursive implementation.
     *
     * @param element to search for
     * @param node    current node
     * @return if the element in the Tree
     */
    private boolean containsRecursive(Object element, Node<E> node) {
        return node != null && (element.equals(node.data) || containsRecursive(element, node.left) || containsRecursive(element, node.right));
    }

    /**
     * Add element to TreeSet.
     * @param element element to add
     * @return if the element already exist in TreeSet
     */
    @Override
    public boolean add(@NotNull E element) {
        if (root == null) {
            root = new Node<>(element);
            ++size;
            head = root;
            tail = root;
            return false;
        }

        Node<E> node = root;

        while (true) {
            if (element.equals(node.data)) {
                return true;
            }

            if (comparator.compare(element, node.data) <= 0) {
                if (node.left == null) {
                    node.insertBefore(element);
                    return false;
                } else {
                    node = node.left;
                }
            } else {
                if (node.right == null) {
                    node.insertAfter(element);
                    return false;
                } else {
                    node = node.right;
                }
            }
        }
    }

    /**
     * Default comparator based on Comparable elements.
     *
     * @param <T> element parameter, must implement Comparable<T>
     */
    private class DefaultComparator<T> implements Comparator<T> {

        @SuppressWarnings("unchecked")
        @Override
        public int compare(T lhs, T rhs) {
            try {
                return ((Comparable<T>) lhs).compareTo(rhs);
            } catch (ClassCastException exception) {
                throw new IllegalArgumentException("Elements must be comparable");
            }
        }
    }

    /**
     * Inner class of Binary Tree Node
     *
     * @param <T> type of stored element
     */
    private class Node<T> {
        private Node<T> left;
        private Node<T> right;
        private Node<T> prev;
        private Node<T> next;
        private T data;

        Node(@NotNull T element) {
            this.data = element;
        }

        /**
         * Insert new element after this node.
         * @param element element to insert
         */
        void insertAfter(T element) {
            right = new Node<>(element);
            ++TreeSet.this.size;
            right.prev = this;
            right.next = this.next;
            if (this.next != null) {
                this.next.prev = right;
            }
            this.next = right;

            if (tail.next != null) {
                tail = tail.next;
            }
        }

        /**
         * Insert new element before this node.
         * @param element element to insert
         */
        void insertBefore(T element) {
            left = new Node<>(element);
            ++TreeSet.this.size;
            left.next = this;
            left.prev = this.prev;
            if (this.prev != null) {
                this.prev.next = left;
            }
            this.prev = left;
            if (head.prev != null) {
                head = head.prev;
            }
        }
    }
}
