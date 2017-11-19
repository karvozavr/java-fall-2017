package ru.spbau.mit.karvozavr.calculator;

import java.util.ArrayList;

/**
 * Basic array-based stack implementation.
 * @param <T> type of elements
 */
public class BasicStack<T> implements Stack<T> {

    ArrayList<T> stack;

    /**
     * Trivial constructor
     */
    public BasicStack() {
        stack = new ArrayList<>();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public void push(T element) {
        stack.add(element);
    }

    @Override
    public T pop() {
        return stack.remove(stack.size() - 1);
    }
}
