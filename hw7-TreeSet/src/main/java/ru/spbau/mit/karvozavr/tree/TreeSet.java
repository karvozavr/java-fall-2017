package ru.spbau.mit.karvozavr.tree;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TreeSet<E> extends AbstractSet<E> implements MyTreeSet<E> {

    private final Comparator<E> comparator;
    private Node root = null;
    private Node head = null;
    private Node tail = null;
    private int size = 0;
    private long modCount = 0;

    /**
     * Create Comparable based set.
     */
    public TreeSet() {
        comparator = new DefaultComparator<>();
    }


    /**
     * Create custom Comparator based set.
     */
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
        return new ReversedIterator();
    }

    /**
     * Returns a reverse order view of the elements contained in this set.
     *
     * @return a reverse order view of the elements contained in this set
     */
    @Override
    public MyTreeSet<E> descendingSet() {
        return new TreeSetDescendingView();
    }

    /**
     * Returns number of elements in this set.
     *
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
        return head != null ? head.value : null;
    }

    /**
     * Returns the last (highest) element currently in this set.
     *
     * @return the last (highest) element currently in this set
     */
    @Override
    public E last() {
        return tail != null ? tail.value : null;
    }

    /**
     * Returns element less than given.
     *
     * @param element to look for
     * @return element less than given
     */
    @Override
    public E lower(E element) {
        Node node = root;
        while (node != null) {
            if (comparator.compare(element, node.value) > 0) {
                if (node.right != null)
                    node = node.right;
                else
                    return node.value;
            } else {
                if (node.left != null)
                    node = node.left;
                else if (node.prev != null)
                    return node.prev.value;
                else
                    return null;
            }
        }

        return null;
    }

    /**
     * Returns element equal or less than given.
     *
     * @param element to look for
     * @return element equal or less than given
     */
    @Override
    public E floor(E element) {
        if (comparator.compare(element, first()) < 0) {
            return null;
        }

        Node node = getNode(element);
        if (node != null) {
            return element;
        }


        return lower(element);
    }

    /**
     * Returns element equal or greater than given.
     *
     * @param element to look for
     * @return element equal or greater than given
     */
    @Override
    public E ceiling(E element) {
        if (comparator.compare(element, last()) > 0) {
            return null;
        }

        Node node = getNode(element);
        if (node != null) {
            return element;
        }


        return higher(element);
    }

    /**
     * Returns element greater than given.
     *
     * @param element to look for
     * @return element greater than given
     */
    @Override
    public E higher(E element) {
        Node node = root;
        while (node != null) {
            if (comparator.compare(element, node.value) < 0) {
                if (node.left != null)
                    node = node.left;
                else
                    return node.value;
            } else {
                if (node.right != null)
                    node = node.right;
                else if (node.next != null)
                    return node.next.value;
                else
                    return null;
            }
        }

        return null;
    }

    /**
     * Clears set.
     */
    @Override
    public void clear() {
        root = null;
        head = null;
        tail = null;
        size = 0;
        modCount = 0;
    }

    /**
     * Remove element from the set.
     *
     * @param element to remove
     * @return if the element found
     */
    @Override
    public boolean remove(Object element) {
        Node nodeToRemove = getNode(element);

        if (nodeToRemove != null) {
            nodeToRemove.remove();
            return true;
        }

        return false;
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

        if (root == null) {
            ++modCount;
            ++size;
            root = new Node(element);
            head = root;
            tail = root;
            return false;
        }

        Node node = root;

        while (true) {
            if (comparator.compare(element, node.value) <= 0) {
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
    private Node getNode(Object element) {
        if (root == null) {
            return null;
        }

        final E actualElement = (E) element;

        Node node = root;

        while (true) {
            if (actualElement.equals(node.value)) {
                return node;
            }
            if (comparator.compare(actualElement, node.value) <= 0) {
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
     * @param <T> element parameter, must implement Comparable<E>
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

    /**
     * Node iterator abstraction.
     */
    private abstract class AbstractNodeIterator implements Iterator<E> {

        Node next;
        Node lastReturned = null;
        long expectedModCount;

        public AbstractNodeIterator() {
            expectedModCount = modCount;
            next = head;
        }

        @Override
        public final boolean hasNext() {
            return next != null;
        }

        Node nextNode() {
            Node node = next;
            if (next == null)
                throw new NoSuchElementException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            next = node.next;
            lastReturned = node;
            return node;
        }

        Node prevNode() {
            Node node = next;
            if (next == null)
                throw new NoSuchElementException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            next = node.prev;
            lastReturned = node;
            return node;
        }

        @Override
        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            lastReturned.remove();
            lastReturned = null;
            expectedModCount = modCount;
        }
    }

    /**
     * Forward iterator on elements of TreeSet
     */
    private class ForwardIterator extends AbstractNodeIterator implements Iterator<E> {

        @Override
        public E next() {
            return nextNode().value;
        }
    }

    /**
     * Reverse iterator on elements of TreeSet
     */
    private class ReversedIterator extends AbstractNodeIterator implements Iterator<E> {

        @Override
        public E next() {
            return prevNode().value;
        }
    }

    /**
     * Inner class of Binary Tree Node.
     */
    private class Node {
        private Node left;
        private Node right;
        private Node prev;
        private Node next;
        private Node parent;
        private E value;

        public Node(E element) {
            value = element;
        }

        /**
         * Inserts new element after this node.
         *
         * @param element element to insert
         */
        public void insertAfter(E element) {
            ++modCount;
            ++size;

            right = new Node(element);
            right.parent = this;
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
        public void insertBefore(E element) {
            ++modCount;
            ++size;

            left = new Node(element);
            left.parent = this;
            left.next = this;
            left.prev = this.prev;

            if (this.prev != null)
                this.prev.next = left;

            this.prev = left;

            if (head.prev != null)
                head = head.prev;
        }

        /**
         * Removes node from set.
         */
        public void remove() {
            ++modCount;
            --size;

            if (this == tail)
                tail = tail.prev;

            if (this == head)
                head = head.next;

            if (right == null) {
                if (parent != null) {
                    if (parent.left == this)
                        parent.left = left;
                    else
                        parent.right = left;
                }

                left.parent = parent;

                if (this == root)
                    root = left;
            } else {
                if (parent != null) {
                    if (parent.left == this)
                        parent.left = next;
                    else
                        parent.right = next;
                }

                if (next != null) {
                    next.left = left;
                    next.parent.left = null;
                    next.parent = parent;
                }

                if (left != null)
                    left.parent = next;

                if (this == root)
                    root = next;
            }

            if (next != null)
                next.prev = prev;

            if (prev != null)
                prev.next = next;
        }
    }

    /**
     * View of this set in descending order.
     */
    private class TreeSetDescendingView extends AbstractSet<E> implements MyTreeSet<E> {

        final TreeSet<E> pater;

        TreeSetDescendingView() {
            this.pater = TreeSet.this;
        }

        @NotNull
        @Override
        public Iterator<E> descendingIterator() {
            return pater.iterator();
        }

        @Override
        public MyTreeSet<E> descendingSet() {
            return pater;
        }

        @Override
        public E first() {
            return pater.last();
        }

        @Override
        public E last() {
            return pater.first();
        }

        @Override
        public E lower(E e) {
            return pater.higher(e);
        }

        @Override
        public E floor(E e) {
            return pater.ceiling(e);
        }

        @Override
        public E ceiling(E e) {
            return pater.floor(e);
        }

        @Override
        public E higher(E e) {
            return pater.lower(e);
        }

        @Override
        public boolean remove(Object o) {
            return pater.remove(o);
        }

        @Override
        public boolean contains(Object o) {
            return pater.contains(o);
        }

        @NotNull
        @Override
        public Iterator<E> iterator() {
            return pater.descendingIterator();
        }

        @Override
        public int size() {
            return pater.size();
        }
    }
}
