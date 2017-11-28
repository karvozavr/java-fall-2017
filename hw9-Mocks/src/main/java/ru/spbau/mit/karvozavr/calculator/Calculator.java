package ru.spbau.mit.karvozavr.calculator;

import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.karvozavr.calculator.enums.Operator;

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

    public int evaluate() {
        Stack<Integer> valuesStack = new SimpleStack<>();
        while (!expressionStack.isEmpty()) {
            if (expressionStack.top() instanceof Operator) {
                Operator operator = (Operator) expressionStack.pop();
                try {
                    int operand2 = valuesStack.pop();
                    int operand1 = valuesStack.pop();
                    valuesStack.push(Operator.Evaluate(operand1, operand2, operator));
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


    /**
     * Translates expression to RPL
     * @param expression to translate
     * @return stack with expression in RPL form
     */
    public static @NotNull Stack<Object> toReversePolishNotation(@NotNull String expression) {
        Stack<Object> output = new SimpleStack<>();
        Stack<Operator> operatorStack = new SimpleStack<>();
        Object[] tokens = ExpressionParser.tokenize(expression);
        for (Object token : tokens) {
            if (token instanceof Integer) {
                output.push(token);
            } else if (token instanceof Operator) {
                if (token == Operator.OPENING_BRACKET) {
                    operatorStack.push((Operator) token);
                    continue;
                }

                if (token == Operator.CLOSING_BRACKET) {
                    while (!operatorStack.isEmpty() && operatorStack.top() != Operator.OPENING_BRACKET)
                        output.push(operatorStack.pop());
                    if (!operatorStack.isEmpty())
                        operatorStack.pop();
                    continue;
                }

                while (!operatorStack.isEmpty() && operatorStack.top().precedence >= ((Operator) token).precedence)
                    output.push(operatorStack.pop());
                operatorStack.push((Operator) token);
            } else {
                throw new InvalidParameterException("Invalid expression!");
            }
        }

        while (!operatorStack.isEmpty()) {
            output.push(operatorStack.pop());
        }

        output.reverse();
        return output;
    }
}
