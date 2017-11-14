package ru.spbau.mit.karvozavr.tree;

import org.jetbrains.annotations.NotNull;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;

class TreeSetDescendingView<T> extends AbstractSet<T> implements MyTreeSet<T> {

    final TreeSet<T> pater;

    TreeSetDescendingView(TreeSet<T> pater) {
        this.pater = pater;
    }

    @Override
    public Iterator<T> descendingIterator() {
        return pater.iterator();
    }

    @Override
    public MyTreeSet<T> descendingSet() {
        return pater;
    }

    @Override
    public T first() {
        return pater.last();
    }

    @Override
    public T last() {
        return pater.first();
    }

    @Override
    public T lower(T e) {
        return pater.higher(e);
    }

    @Override
    public T floor(T e) {
        return pater.ceiling(e);
    }

    @Override
    public T ceiling(T e) {
        return pater.floor(e);
    }

    @Override
    public T higher(T e) {
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

    @Override
    public Iterator<T> iterator() {
        return pater.descendingIterator();
    }

    @Override
    public int size() {
        return pater.size();
    }
}
