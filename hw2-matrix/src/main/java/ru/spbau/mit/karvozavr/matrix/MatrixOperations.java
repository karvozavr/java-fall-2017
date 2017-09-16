package ru.spbau.mit.karvozavr.matrix;


import java.util.Arrays;
import java.util.Comparator;

/**
 * Some very important operations with matrices
 */
public class MatrixOperations {

    /**
     * Output matrix in a spiral way
     *
     * @param matrix array of dimension N * N
     */
    public static void spiralOutput(final int[][] matrix) {
        final int N = matrix.length;

        System.out.println(matrix[N / 2][N / 2]);
        for (int radius = 0; radius <= N / 2; radius++) {
            for (int i = N / 2 - radius + 1; i <= N / 2 + radius; ++i) {
                System.out.println(matrix[i][N / 2 + radius]);
            }

            for (int i = N / 2 + radius - 1; i >= N / 2 - radius; --i) {
                System.out.println(matrix[N / 2 + radius][i]);
            }

            for (int i = N / 2 + radius - 1; i >= N / 2 - radius; --i) {
                System.out.println(matrix[i][N / 2 - radius]);
            }

            for (int i = N / 2 - radius + 1; i <= N / 2 + radius; ++i) {
                System.out.println(matrix[N / 2 - radius][i]);
            }
        }
    }

    /**
     * Sort columns of a matrix by the first element
     * @param matrix 2-dimensional array
     * @return matrix with sorted columns
     */
    public static int[][] sortColumns(int[][] matrix) {
        transpose(matrix);
        Arrays.sort(matrix, Comparator.comparingInt(o -> o[0]));
        transpose(matrix);
        return matrix;
    }


    /**
     * Transpose matrix
     * @param matrix 2D array to be transposed
     */
    public static void transpose(int[][] matrix) {
        int swapBuffer;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                if (i != j) {
                    swapBuffer = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = swapBuffer;
                }
            }
        }
    }
}