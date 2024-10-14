/*
 * Java program that multiplies two square boolean matrices, using the or
operation instead of + and the and operation instead of *.
 */
public class Multiply2DBoolean {
    public static boolean[][] multiplyMatrices(boolean[][] matrixA, boolean[][] matrixB) {
        int size = matrixA.length;
        boolean[][] resultMatrix = new boolean[size][size];

        // Multiply matrices using OR and AND operations
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boolean result = false;
                for (int k = 0; k < size; k++) {
                    // Perform OR operation between matrixA[i][k] and matrixB[k][j]
                    // Perform AND operation between matrixA[i][k] and matrixB[k][j]
                    result |= matrixA[i][k] && matrixB[k][j];
                }
                resultMatrix[i][j] = result;
            }
        }

        return resultMatrix;
    }
}
