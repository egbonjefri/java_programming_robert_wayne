import lib.StdDraw;
/**
 * This program generates a Hadamard Matrix of order n, where n is a power of 2.
 * A Hadamard Matrix is a square matrix whose entries are either +1 or -1 and whose rows are mutually orthogonal.
 * that is when you calculate the inner (dot) product of two distinct rows, 
 * each corresponding element multiplication will either be +1 (+1 * +1) or -1 (+1 * -1 or -1 * +1).
 * Due to the presence of both positive and negative entries, the sum of these element-wise products will always be zero.
 * 
 * 
 * This implementation uses a recursive approach based on the Sylvester's construction method, which constructs
 * the Hadamard matrix of order 2n from the Hadamard matrix of order n.
 * 
 * Time Complexity: O(n^2 * log n).
 * The time complexity of generating a Hadamard Matrix using this recursive method can be analyzed as follows:
 * Each recursion level reduces the problem size by a factor of 2 (n becomes n/2). At each level of recursion,
 * the method performs operations (mainly the for-loop copying and sign inverting) proportional to the square
 * of the current problem size (n^2). Since the depth of the recursion is log n (because we halve n at each step),
 * and at each level we perform n^2 operations, the total time complexity is O(n^2 * log n).
 *
 * Why the algorithm works:
 * The base case of the recursion is a 1x1 matrix with a single entry of 1. For any larger matrix of size n (where n is
 * a power of 2), the matrix is divided into four quadrants. The top-left quadrant is a Hadamard matrix of size n/2,
 * which is obtained by recursive call. The top-right and bottom-left quadrants are copies of this matrix, and the
 * bottom-right quadrant is the negated form of the top-left quadrant. This structure ensures that the rows and columns
 * of the resulting matrix are orthogonal, fulfilling the properties of a Hadamard matrix. The copying and negating
 * operations ensure that each row is orthogonal to every other row, and each column is orthogonal to every other column,
 * which is the defining characteristic of a Hadamard matrix.
 */



public class HadamardMatrix {
 /**
     * Generates a Hadamard matrix of size n x n.
     * Note: n must be a power of 2.
     *
     * @param n the size of the matrix, must be a power of 2.
     * @return a Hadamard matrix represented as a boolean array.
     * @throws IllegalArgumentException if n is not a power of 2.
     */
    public static boolean[][] generateHadamardMatrix(int n) {
        // Check if n is a power of 2
        if (!isPowerOf2(n)) {
            throw new IllegalArgumentException("n must be a power of 2");
        }

        // Base case for n = 1
        boolean[][] H = new boolean[n][n];
        if (n == 1) {
            H[0][0] = true; // Set the single element to true (+1)
            return H;
        }

        // Recursive construction based on Hadamard matrix properties
        boolean[][] H_prev = generateHadamardMatrix(n / 2);

        // Construct the new matrix H from H_prev
        for (int i = 0; i < n / 2; i++) {
            System.arraycopy(H_prev[i], 0, H[i], 0, n / 2); // Copy top-left quadrant
            System.arraycopy(H_prev[i], 0, H[i + n / 2], 0, n / 2); // Copy to bottom-left quadrant
        }

        // Fill right half based on top-left quadrant with negation for top-right and bottom-right quadrants
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                H[i][j + n / 2] = H[i][j]; // Copy top-right quadrant
                H[i + n / 2][j + n / 2] = !H[i][j]; // Negate for bottom-right quadrant
            }
        }
        
        return H;
    }

    /**
     * Visualizes a boolean grid (matrix) using StdDraw library. True values are represented with one color and false with another.
     * This method adjusts for padding between squares for clearer visualization.
     *
     * @param grid The boolean matrix to visualize.
     */
    public static void visualizeGrid(boolean[][] grid) {
        int n = grid.length; 
        StdDraw.setXscale(-1, n);
        StdDraw.setYscale(-1, n);
        StdDraw.clear(StdDraw.GRAY);
        StdDraw.enableDoubleBuffering();
    
        double squareSize = 0.45; // Define square size for visual clarity
        double padding = 0.05; // Define padding between squares
    
        // Adjusted scale factors to account for padding
        double scaleFactorX = 1.0 / (1 + padding);
        double scaleFactorY = 1.0 / (1 + padding);
    
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double x = j * scaleFactorX + scaleFactorX / 2 - 0.5;
                double y = (n - i - 1) * scaleFactorY + scaleFactorY / 2 - 0.5;
    
                // Determine color based on the matrix value
                if (grid[i][j]) {
                    StdDraw.setPenColor(StdDraw.BOOK_BLUE); 
                } else {
                    StdDraw.setPenColor(StdDraw.BLACK); 
                }
    
                StdDraw.filledSquare(x, y, squareSize / 2); // Draw each square
            }
        }
        StdDraw.show();
    }

    /**
     * Checks if a given integer is a power of 2 using bitwise operations.
     *
     * @param n the integer to check.
     * @return true if n is a power of 2, false otherwise.
     */
    private static boolean isPowerOf2(int n) {
        return (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        int n = 8; // Example size, must be a power of 2
        boolean[][] H = generateHadamardMatrix(n);
        visualizeGrid(H); // Visualize the generated Hadamard matrix
    }
}

