import lib.StdIn;
import lib.StdOut;

/*
 * Write a static method readBoolean2D() that reads a two-dimensional
boolean matrix (with dimensions) from standard input and returns the resulting
two-dimensional array.
 */
public class MatrixReader {

    public static void main(String[] args) {
        boolean[][] matrix = readBoolean2D();
        for (boolean[] row : matrix) {
            for (boolean value : row) {
                StdOut.print(value + " ");
            }
            StdOut.println();
        }
    }

    public static boolean[][] readBoolean2D() {
        // Read the dimensions of the matrix
        int rows = StdIn.readInt();
        int cols = StdIn.readInt();

        // Initialize the matrix
        boolean[][] matrix = new boolean[rows][cols];

        // Fill the matrix with input values
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = StdIn.readBoolean();
            }
        }

        return matrix;
    }
}
