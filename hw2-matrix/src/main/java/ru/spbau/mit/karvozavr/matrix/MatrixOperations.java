package ru.spbau.mit.karvozavr.matrix;


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


        return matrix;
    }
}