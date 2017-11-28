package ru.spbau.mit.karvozavr.calculator;

import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.karvozavr.calculator.enums.Operator;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * Util to parse arithmetic expressions
 */
public class ExpressionParser {

    /**
     * Tokenize expression to array of tokens (number | operator)
     * @param expression to tokenize
     * @return array of tokens (number | operator)
     */
    public static @NotNull Object[] tokenize(@NotNull String expression) {
        final String[] tokens = expression.split(" ", -1);
        final ArrayList<Object> parsedTokens = new ArrayList<>();
        for (String token : tokens) {
            final Object parsedToken;
            parsedToken = parseToken(token);
            if (parsedToken == null) {
                throw new InvalidParameterException("Incorrect expression!");
            }
            parsedTokens.add(parsedToken);
        }

        return parsedTokens.toArray();
    }

    /**
     * Parses single token.
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
     * @param expression token to parse
     * @return parsed token
     */
    private static Integer parseNumber(@NotNull String expression) {
        try {
            return Integer.parseInt(expression);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Parses single operator token.
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
}
