package ru.spbau.mit.karvozavr.matrix;

import org.junit.jupiter.api.Test;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.*;

class MatrixOperationsTest {

    @Test
    void testTransposeSmoke() {
        int[][] matrix = {{1}};
        int[][] expected = {{1}};
        MatrixOperations.transpose(matrix);
        assertArrayEquals(expected, matrix);
    }

    @Test
    void testTransposeBase() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] expected = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        MatrixOperations.transpose(matrix);
        assertArrayEquals(expected, matrix);
    }

    @Test
    void testTransposeEmpty() {
        int[][] matrix = null;
        MatrixOperations.transpose(matrix);
        assertEquals(null, matrix);
    }

    @Test
    void testSpiralOutputSmoke() {
        int[][] matrix = {{1}};
        assertEquals("1", MatrixOperations.spiralOutput(matrix));
    }

    @Test
    void testSpiralOutputBase() {
        int[][] matrix = {{21, 22, 23, 24, 25},
                {20, 7, 8, 9, 10},
                {19, 6, 1, 2, 11},
                {18, 5, 4, 3, 12},
                {17, 16, 15, 14, 13}};
        assertEquals(IntStream.rangeClosed(1, 25).mapToObj(Integer::toString).collect(Collectors.joining()),
                MatrixOperations.spiralOutput(matrix));
    }

    /**
     * Correct behavior on null is empty string.
     * Assume, that empty matrix is null.
     */
    @Test
    void testSpiralOutputEmpty() {
        int[][] matrix = null;
        assertEquals("", MatrixOperations.spiralOutput(matrix));
    }

    @Test
    void testSortColumnsSmoke() {
        int[][] matrix = {{2, 1}, {3, 4}};
        int[][] expected = {{1, 2}, {4, 3}};
        MatrixOperations.sortColumns(matrix);
        assertArrayEquals(expected, matrix);
    }

    @Test
    void testSortColumnsBase() {
        int[][] matrix = {{9, 2, 7, 3}, {4, 5, 6, 1}, {7, 8, 9, 1}, {1, 1, 1, 1}};
        int[][] expected = {{2, 3, 7, 9}, {5, 1, 6, 4}, {8, 1, 9, 7}, {1, 1, 1, 1}};
        MatrixOperations.sortColumns(matrix);
        assertArrayEquals(expected, matrix);
    }

    /**
     * Correct behavior on null is null.
     * Assume, that empty matrix is null.
     */
    @Test
    void testSortColumnsEmpty() {
        int[][] matrix = null;
        int[][] expected = null;
        MatrixOperations.sortColumns(matrix);
        assertArrayEquals(expected, matrix);
    }
}