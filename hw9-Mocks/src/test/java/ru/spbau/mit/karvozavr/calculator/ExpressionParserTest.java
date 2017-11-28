package ru.spbau.mit.karvozavr.calculator;

import org.junit.jupiter.api.Test;
import ru.spbau.mit.karvozavr.calculator.enums.Operator;

import java.security.InvalidParameterException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserTest {

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