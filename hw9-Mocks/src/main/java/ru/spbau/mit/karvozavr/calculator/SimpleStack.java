package ru.spbau.mit.karvozavr.calculator;

import java.util.ArrayList;

/**
 * Basic array-based stack implementation.
 *
 * @param <T> type of elements
 */
public class SimpleStack<T> implements Stack<T> {

    ArrayList<T> stack;

    /**
     * Trivial constructor
     */
    public SimpleStack() {
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

    @Override
    public T top() {
        return stack.get(stack.size() - 1);
    }

    @Override
    public void reverse() {
        SimpleStack<T> reversed = new SimpleStack<>();
        while (!isEmpty()) {
            reversed.push(pop());
        }
        stack = reversed.stack;
    }
}
