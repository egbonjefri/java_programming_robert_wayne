/*
Write a Matrix client that implements the version of Markov described
in SECTION 1.6 but is based on squaring the matrix, instead of iterating the vector–
matrix multiplication.
*/

import lib.Matrix;
import lib.StdOut;
import lib.StdIn;


public class MatrixClient {
       
        public static void main(String[] args){
         
                int trials = Integer.parseInt(args[0]); // number of squaring operations
                int n = StdIn.readInt(); // Size of the transition matrix
                StdIn.readInt(); // Read but ignore this input (for compatibility with original format)
        
                // Read transition matrix
                double[][] p = new double[n][n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        p[i][j] = StdIn.readDouble();
                    }
                }
        
                // Square the matrix as many times as specified by trials
                double[][] matrixPower = p;
                for (int t = 1; t < trials; t++) {
                    matrixPower = Matrix.multiply(matrixPower, p);
                }
        
                // The resulting matrixPower contains the probabilities after 'trials' number of steps
                // Assuming the surfer starts at page 0, print the probabilities of being on each page
                for (int i = 0; i < n; i++) {
                    StdOut.printf("%8.5f", matrixPower[0][i]);
                }
                StdOut.println();


}
}
