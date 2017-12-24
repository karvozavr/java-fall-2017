package ru.spbau.mit.karvozavr.calculator;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculatorTest {

    /**
     * evaluate "2 3 + 4 *"
     */
    @Test
    void testEvaluateWithMockedStack() {
        Stack<Object> stack = mock(Stack.class);

        when(stack.isEmpty())
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true);

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

    @Test
    void testEvaluateExceptionEmpty() {
        Stack<Object> stack = mock(Stack.class);
        when(stack.isEmpty()).thenReturn(true);
        Calculator calculator = new Calculator(stack);
        assertThrows(InvalidParameterException.class, calculator::evaluate);
    }

    @Test
    void testEvaluateException() {
        Stack<Object> stack = mock(Stack.class);
        when(stack.isEmpty()).thenReturn(false);

        when(stack.isEmpty())
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true);

        when(stack.top())
                .thenReturn(2)
                .thenReturn(3)
                .thenReturn(Operator.PLUS)
                .thenReturn(4)
                .thenThrow(new RuntimeException("This method has to be called only 5 times!"));

        when(stack.pop())
                .thenReturn(2)
                .thenReturn(3)
                .thenReturn(Operator.PLUS)
                .thenReturn(4)
                .thenThrow(new RuntimeException("This method has to be called only 5 times!"));

        Calculator calculator = new Calculator(stack);
        assertThrows(InvalidParameterException.class, calculator::evaluate);
    }
}