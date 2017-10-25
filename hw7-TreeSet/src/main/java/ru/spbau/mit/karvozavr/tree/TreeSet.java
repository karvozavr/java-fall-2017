package ru.spbau.mit.karvozavr.tree;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;

public class TreeSet<E> extends AbstractSet<E> implements MyTreeSet<E> {

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


    private final Comparator<E> comparator;

    TreeSet() {
        comparator = new DefaultComparator<>();
    }

    TreeSet(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public MyTreeSet<E> descendingSet() {
        return null;
    }

    @Override
    public E first() {
        return null;
    }

    @Override
    public E last() {
        return null;
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
    public boolean add(E element) {
        return false;
    }
}
