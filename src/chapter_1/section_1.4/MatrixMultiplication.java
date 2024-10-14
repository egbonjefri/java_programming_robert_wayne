import lib.StdOut;
/*
 * code fragment to multiply two rectangular matrices that are not
necessarily square. Note: For the dot product to be well deﬁned, the number of col-
umns in the ﬁrst matrix must be equal to the number of rows in the second matrix.
 */
public class MatrixMultiplication {
    public static void main(String args[]) {
        double[][] a = {{0.70, 0.20, 0.10}, {0.3, 0.6, 0.10}, {0.50, 0.10, 0.40}};
        double[][] b = {{0.20, 0.30, 0.50}, {0.1, 0.2, 0.10}, {0.10, 0.30, 0.40}}; 

        if (a.length != b[0].length) {
            throw new IllegalArgumentException("Array sizes must match.");
        }
        double[][] c = new double[a.length][b.length];
        
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < b.length; j++){
              for(int k = 0; k < b.length; k++){
                   c[i][j] += a[i][k] * b[k][j]; 
              }
            }
            
        }

       for (double[] row : c) {
            for (double score : row) {
            StdOut.printf("%.2f \t", score);
            }
            StdOut.println();
        }
        
    }
}
