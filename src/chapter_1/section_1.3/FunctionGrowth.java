/**
 * Prints a table of values for different functions of n, where n is a power of 2.
 * The table includes n, log n, n log e n, n^2, n^3, and 2^n.
 *
 * @param  args  the command-line arguments (not used)
 */

import lib.StdOut;

public class FunctionGrowth {

    public static void main(String[] args) {
        // Print table headers with tab alignment
        StdOut.println("n\tlog n\tn\tn log e n\tn^2\tn^3\t2^n"); 
        
        // Loop through powers of 2
        for (int n = 16; n <= 2048; n *= 2) { 
            
            double logN = Math.log10(n);             // Base-10 logarithm
            double nLogE = n * Math.log(n);        // n * natural logarithm of n
            double nSquared = Math.pow(n, 2);      // n squared
            double nCubed = Math.pow(n, 3);        // n cubed
            double twoToN = Math.pow(2, n);        // 2 raised to the power n
            
          
            StdOut.printf("%d\t%.2f\t%d\t%.2f\t%.0f\t%.0f\t%.0f%n", 
                              n, logN, n, nLogE, nSquared, nCubed, twoToN);
        }
    }
}
