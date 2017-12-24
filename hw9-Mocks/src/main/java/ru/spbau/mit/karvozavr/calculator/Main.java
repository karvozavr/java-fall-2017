package ru.spbau.mit.karvozavr.calculator;

import java.security.InvalidParameterException;

/**
 * Main class of the application.
 */
public class Main {

    public static void main(String[] args) {
        if (args.length <= 1) {
            System.err.println("Not enough arguments: expression not found.");
            System.exit(1);
        }

        String expression = args[1];
        Calculator calculator =
                new Calculator(ExpressionParser.toReversePolishNotation(expression, new SimpleStack<>(), new SimpleStack<>()));

        try {
            System.out.println("Result is: ");
            System.out.println(calculator.evaluate());
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

}
