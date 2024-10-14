package lib;


public class BooleanMatrix3D {

    // Method to generate a 3D boolean matrix
    public static boolean[][][] generateRandomBooleanMatrix3D(int depth, int rows, int cols) {
        boolean[][][] matrix = new boolean[depth][rows][cols];

        for (int d = 0; d < depth; d++) {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    matrix[d][r][c] = StdRandom.bernoulli(); // Randomly generates true or false
                }
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        int depth = 3; 
        int rows = 4; 
        int cols = 5; 

        boolean[][][] matrix = generateRandomBooleanMatrix3D(depth, rows, cols);

        // Print the 3D matrix using StdArrayIO
        for (int d = 0; d < depth; d++) {
            System.out.println("Depth: " + (d+1));
            StdArrayIO.print(matrix[d]);
        }
    }
}
