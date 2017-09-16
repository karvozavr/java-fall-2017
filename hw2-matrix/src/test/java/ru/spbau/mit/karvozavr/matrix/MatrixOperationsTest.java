package ru.spbau.mit.karvozavr.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixOperationsTest {
    @Test
    void testSpiralOutput() {
        int[][] matrix = {{21, 22, 23, 24, 25},
                {20, 7, 8, 9, 10},
                {19, 6, 1, 2, 11},
                {18, 5, 4, 3, 12},
                {17, 16, 15, 14, 13}};
        MatrixOperations.spiralOutput(matrix);
    }

    @Test
    void sortColumns() {
    }

}