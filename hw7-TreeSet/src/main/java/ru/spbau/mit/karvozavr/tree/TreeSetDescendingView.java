package ru.spbau.mit.karvozavr.tree;

import org.jetbrains.annotations.NotNull;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;

class TreeSetDescendingView<E> extends AbstractSet<E> implements MyTreeSet<E> {

    final TreeSet<E> pater;

    TreeSetDescendingView(TreeSet<E> pater) {
        this.pater = pater;
    }

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
    public Iterator<E> iterator() {
        return pater.descendingIterator();
    }

    @Override
    public int size() {
        return pater.size();
    }
}
