package ru.spbau.mit.karvozavr.calculator;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import ru.spbau.mit.karvozavr.calculator.enums.Operator;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

class CalculatorTest {

    @Test
    void testEvaluateWithSimpleStack() {
        Stack<Object> stack = new SimpleStack<>();
        Object[] tokens = ExpressionParser.tokenize("2 3 + 4 *");
        for (int i = tokens.length - 1; i >= 0; --i) {
            stack.push(tokens[i]);
        }
        Calculator calculator = new Calculator(stack);
        assertThat(calculator.evaluate(), is(20));
    }

    @Test
    void testToPolish() {
        Stack<Object> stack = Calculator.toReversePolishNotation("( 2 + 3 ) * 7 - 4");
        ArrayList<Object> tokens = new ArrayList<>();

        while (!stack.isEmpty()) {
            tokens.add(stack.pop());
        }

        assertArrayEquals(new Object[]{2, 3, Operator.PLUS, 7, Operator.MULTIPLY, 4, Operator.MINUS}, tokens.toArray());
    }

    /**
     * Evaluate "2 3 + 4 *"
     */
    @Test
    void testEvaluateWithMockedStack() {
        Stack<Object> stack = mock(Stack.class);

        when(stack.isEmpty())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);

        when(stack.top())
                .thenReturn(2)
                .thenReturn(3)
                .thenReturn(Operator.PLUS)
                .thenReturn(4)
                .thenReturn(Operator.MULTIPLY)
                .thenThrow(new RuntimeException("This method has to be called only 5 times!"));

        when(stack.pop())
                .thenReturn(2)
                .thenReturn(3)
                .thenReturn(Operator.PLUS)
                .thenReturn(4)
                .thenReturn(Operator.MULTIPLY)
                .thenThrow(new RuntimeException("This method has to be called only 5 times!"));


        Calculator calculator = new Calculator(stack);
        assertThat(calculator.evaluate(), is(20));
    }
}