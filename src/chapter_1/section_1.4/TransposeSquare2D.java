import lib.StdArrayIO;
/*
 * a code fragment to transpose a square two-dimensional array in place
without creating a second array.
 */
public class TransposeSquare2D {
    public static void transposeSquareMatrix(int[][] matrix) {
        int n = matrix.length; // Get the size of the square matrix
    
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {  
                // Swap elements across the main diagonal
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        StdArrayIO.print(matrix);
        transposeSquareMatrix(matrix);
        StdArrayIO.print(matrix);
    }
}
