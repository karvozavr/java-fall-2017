package ru.spbau.mit.karvozavr.calculator;

/**
 * Light stack interface.
 *
 * @param <T> type of stored elements
 */
public interface Stack<T> {

    /**
     * Returns size of stack.
     *
     * @return size of stack
     */
    int size();

    /**
     * Check if stack is empty.
     *
     * @return if stack is empty
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds element to the top of the stack.
     *
     * @param element to add
     */
    void push(T element);

    /**
     * Removes element from top of the stack
     *
     * @return removed element
     */
    T pop();

    /**
     * Returns element on top of the stack.
     *
     * @return element on top of the stack
     */
    default T top() {
        T element = pop();
        push(element);
        return element;
    }

    /**
     * Reverses stack.
     */
    void reverse();
}
