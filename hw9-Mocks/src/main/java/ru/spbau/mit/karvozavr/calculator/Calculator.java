package ru.spbau.mit.karvozavr.calculator;

import org.jetbrains.annotations.NotNull;

import java.security.InvalidParameterException;

/**
 * Implementation of calculator that evaluates integer expressions in reverse polish notation (+ - * /).
 */
public class Calculator {

    private Stack<Object> expressionStack;

    /**
     * Constructor
     * @param stack with RPN expression
     */
    public Calculator(@NotNull Stack<Object> stack) {
        this.expressionStack = stack;
    }

    /**
     * evaluate expression on stack.
     * @return result of evaluation
     */
    public int evaluate() {
        Stack<Integer> valuesStack = new SimpleStack<>();
        while (!expressionStack.isEmpty()) {
            if (expressionStack.top() instanceof Operator) {
                Operator operator = (Operator) expressionStack.pop();
                try {
                    int operand2 = valuesStack.pop();
                    int operand1 = valuesStack.pop();
                    valuesStack.push(Operator.evaluate(operand1, operand2, operator));
                } catch (ClassCastException e) {
                    throw new InvalidParameterException("Expression is incorrect!");
                }
            } else {
                try {
                    valuesStack.push((Integer) expressionStack.pop());
                } catch (ClassCastException e) {
                    throw new InvalidParameterException("Expression is incorrect!");
                }
            }
        }

        if (valuesStack.size() != 1) {
            throw new InvalidParameterException("Expression is incorrect!");
        }

        return valuesStack.top();
    }



}
