package ru.spbau.mit.karvozavr.calculator;

import org.jetbrains.annotations.NotNull;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * Util to parse arithmetic expressions
 */
public class ExpressionParser {

    /**
     * Tokenize expression to array of tokens (number | operator)
     *
     * @param expression to tokenize
     * @return array of tokens (number | operator)
     */
    public static @NotNull Object[] tokenize(@NotNull String expression) {
        final String[] tokens = expression.split(" ", -1);
        final ArrayList<Object> parsedTokens = new ArrayList<>();
        for (String token : tokens) {
            final Object parsedToken = parseToken(token);
            if (parsedToken == null) {
                throw new InvalidParameterException("Incorrect expression!");
            }
            parsedTokens.add(parsedToken);
        }

        return parsedTokens.toArray();
    }

    /**
     * Parses single token.
     *
     * @param expression token to parse
     * @return parsed token
     */
    private static Object parseToken(@NotNull String expression) {
        Object result = parseNumber(expression);
        if (result == null)
            return parseOperation(expression);
        else
            return result;
    }

    /**
     * Parses single number token.
     *
     * @param expression token to parse
     * @return parsed token
     */
    private static Integer parseNumber(@NotNull String expression) {
        try {
            if (expression.matches("\\d+"))
                return Integer.parseInt(expression);
            else
                return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Parses single operator token.
     *
     * @param expression token to parse
     * @return parsed token
     */
    private static Operator parseOperation(@NotNull String expression) {
        if (expression.isEmpty()) {
            return null;
        }

        char head = expression.charAt(0);
        switch (head) {
            case '+':
                return Operator.PLUS;
            case '-':
                return Operator.MINUS;
            case '*':
                return Operator.MULTIPLY;
            case '/':
                return Operator.DIVIDE;
            case '(':
                return Operator.OPENING_BRACKET;
            case ')':
                return Operator.CLOSING_BRACKET;
            default:
                return null;
        }
    }

    /**
     * Translates expression to RPL
     *
     * @param expression to translate
     * @return stack with expression in RPL form
     */
    public static @NotNull Stack<Object> toReversePolishNotation(@NotNull String expression,
                                                                 @NotNull Stack<Operator> operatorStack,
                                                                 @NotNull Stack<Object> output) {
        Object[] tokens = tokenize(expression);
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
