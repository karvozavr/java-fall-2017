package ru.spbau.mit.karvozavr.tree;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<E> extends AbstractSet<E> implements MyTreeSet<E> {

    private final Comparator<E> comparator;
    private Node<E> root = null;
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;
    private BigInteger modCount = BigInteger.ZERO;

    public TreeSet() {
        comparator = new DefaultComparator<>();
    }

    public TreeSet(@NotNull Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * Returns an iterator over the elements in this set in ascending order.
     *
     * @return an iterator over the elements in this set in ascending order
     */
    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new ForwardIterator();
    }

    /**
     * Returns an iterator over the elements in this set in descending order.
     *
     * @return an iterator over the elements in this set in descending order
     */
    @NotNull
    @Override
    public Iterator<E> descendingIterator() {
        return new ReverseIterator();
    }

    /**
     * Returns a reverse order view of the elements contained in this set.
     *
     * @return a reverse order view of the elements contained in this set
     */
    @Override
    public MyTreeSet<E> descendingSet() {
        return new TreeSetDescendingView<>(this);
    }

    /**
     * Returns number of elements in this set.
     * @return number of elements in this set
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Returns the first (lowest) element currently in this set.
     *
     * @return the first (lowest) element currently in this set
     */
    @Override
    public E first() {
        return head != null ? head.data : null;
    }

    /**
     * Returns the last (highest) element currently in this set.
     *
     * @return the last (highest) element currently in this set
     */
    @Override
    public E last() {
        return tail != null ? tail.data : null;
    }

    @Override
    public E lower(E element) {
        Node<E> node = root;
        while (node != null) {
            if (comparator.compare(element, node.data) > 0) {
                if (node.right == null) {
                    return node.data;
                }

                node = node.right;
            } else {
                if (node.right == null) {

                }

                node = node.right;
            }
        }

        return null;
    }

    @Override
    public E floor(E element) {
        if (comparator.compare(element, head.data) < 0) {
            return null;
        }

        Node<E> node = getNode(element);
        if (node != null) {
            return element;
        }


        return lower(element);
    }

    @Override
    public E ceiling(E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E higher(E element) {
        throw new UnsupportedOperationException();
    }

    // TODO FIXME
    @Override
    public boolean remove(Object element) {
        throw new UnsupportedOperationException();
    }

    /**
     * Checks if the Tree contains element.
     *
     * @param element to look for
     * @return if the element in the Tree
     */
    @Override
    public boolean contains(Object element) {
        return getNode(element) != null;
    }

    /**
     * Adds element to TreeSet.
     *
     * @param element element to add
     * @return if the element already exist in TreeSet
     */
    @Override
    public boolean add(@NotNull E element) {
        if (contains(element)) {
            return true;
        }

        modCount.add(BigInteger.ONE);
        ++size;
        if (root == null) {
            root = new Node<>(element);
            head = root;
            tail = root;
            return false;
        }

        Node<E> node = root;

        while (true) {
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
     * Returns Node with given element if it is in TreeSet, null otherwise.
     *
     * @param element to look for
     * @return Node with given element if it is in TreeSet, null otherwise
     */
    @SuppressWarnings("unchecked")
    private Node<E> getNode(Object element) {
        if (root == null) {
            return null;
        }

        final E actualElement = (E) element;

        Node<E> node = root;

        while (true) {
            if (actualElement.equals(node.data)) {
                return node;
            }
            if (comparator.compare(actualElement, node.data) <= 0) {
                if (node.left == null) {
                    return null;
                } else {
                    node = node.left;
                }
            } else {
                if (node.right == null) {
                    return null;
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
    private static class DefaultComparator<T> implements Comparator<T> {

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

    private abstract class NodeIterator implements Iterator<E> {

        Node<E> next;
        Node<E> lastReturned = null;
        BigInteger expectedModCount;

        public NodeIterator() {
            expectedModCount = modCount;
            next = first();
        }
    }

    /**
     * Forward iterator on elements of TreeSet
     */
    private class ForwardIterator implements Iterator<E> {

        private Node<E> currentNode = null;

        public ForwardIterator() {
            currentNode = head;
        }

        @Override
        public boolean hasNext() {
            return currentNode.next != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E result = currentNode.data;
            currentNode = currentNode.next;
            return result;
        }

        @Override
        public void remove() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }


        }
    }

    /**
     * Reverse iterator on elements of TreeSet
     */
    private class ReverseIterator implements Iterator<E> {

        private Node<E> currentNode = null;

        public ReverseIterator() {
            currentNode = tail;
        }

        @Override
        public boolean hasNext() {
            return currentNode.prev != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E result = currentNode.data;
            currentNode = currentNode.prev;
            return result;
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

        public Node(@NotNull T element) {
            this.data = element;
        }

        /**
         * Inserts new element after this node.
         *
         * @param element element to insert
         */
        public void insertAfter(T element) {
            right = new Node<>(element);
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
         * Inserts new element before this node.
         *
         * @param element element to insert
         */
        public void insertBefore(T element) {
            left = new Node<>(element);
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

        // TODO FIXME
        public void remove() {
            if (this == tail) {
                tail = tail.prev;
            }

            if (this == head) {
                head = head.next;
            }

            if (next != null) {
                next.prev = prev;
            }

            if (prev != null) {
                prev.next = next;
            }
        }
    }
}
