package ru.spbau.mit.karvozavr.calculator.enums;

import java.security.InvalidParameterException;

public enum Operator {
    PLUS(3),
    MINUS(3),
    MULTIPLY(4),
    DIVIDE(4),
    OPENING_BRACKET,
    CLOSING_BRACKET;

    public final int precedence;

    Operator() {
        this.precedence = -1;
    }

    Operator(int precedence) {
        this.precedence = precedence;
    }

    public static int Evaluate(int leftOperand, int rightOperand, Operator operator) {
        switch (operator) {
            case PLUS:
                return leftOperand + rightOperand;
            case MINUS:
                return leftOperand - rightOperand;
            case MULTIPLY:
                return leftOperand * rightOperand;
            case DIVIDE:
                return leftOperand / rightOperand;
            default:
                throw new InvalidParameterException("Incorrect binary operation!");
        }
    }
}
