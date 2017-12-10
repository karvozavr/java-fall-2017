package ru.spbau.mit.java.karvozavr;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClassPrinterTest {

    @Test
    public void printStructure() {
        ClassPrinter.printStructure(ClassPrinter.class);
    }
}