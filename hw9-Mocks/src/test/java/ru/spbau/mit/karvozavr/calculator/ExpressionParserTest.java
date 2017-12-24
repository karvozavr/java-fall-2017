package ru.spbau.mit.karvozavr.calculator;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserTest {

    @Test
    void testToPolish() {
        Stack<Object> stack =
                ExpressionParser.toReversePolishNotation("( 2 + 3 ) * 7 - 4", new SimpleStack<>(), new SimpleStack<>());

        ArrayList<Object> tokens = new ArrayList<>();

        while (!stack.isEmpty()) {
            tokens.add(stack.pop());
        }

        assertArrayEquals(new Object[]{2, 3, Operator.PLUS, 7, Operator.MULTIPLY, 4, Operator.MINUS}, tokens.toArray());
    }

    @Test
    void testParse() {
        Object[] tokens = {Operator.OPENING_BRACKET, 2, Operator.PLUS, 42, Operator.CLOSING_BRACKET};
        assertArrayEquals(tokens, ExpressionParser.tokenize("( 2 + 42 )"));
    }

    @Test
    void testException() {
        assertThrows(InvalidParameterException.class, () -> ExpressionParser.tokenize("( 2 + -42 lol kek )"));
    }
}