    /**
     *In 1769 Leonhard Euler formulated a generalized version of Fermat’s Last Theorem, 
     conjecturing that at least n nth powers are needed to obtain a sum that is itself an nth power, for n > 2. 
     Write a program to disprove Euler’s conjecture (which stood until 1967), using a quintuply nested
     loop to ﬁnd four positive integers whose 5th power sums to the 5th power of another positive integer. 
     That is, ﬁnd a, b, c, d, and e such that a^5 + b^5 + c^5 + d^5 = e^5.
     */
import lib.StdOut;

public class EulerSumOfPowers {
        public static void main(String[] args) {
            int maxLimit = 200; // Set a limit for searching positive integers
    
            for (int a = 1; a <= maxLimit; a++) {
                for (int b = a; b <= maxLimit; b++) {
                    for (int c = b; c <= maxLimit; c++) {
                        for (int d = c; d <= maxLimit; d++) {
                            int sumOfFifthPowers = a * a * a * a * a + b * b * b * b * b + c * c * c * c * c + d * d * d * d * d;
    
                            int e = (int) Math.pow(sumOfFifthPowers, 1.0 / 5); // Calculate the fifth root
    
                            if (e <= maxLimit && sumOfFifthPowers == e * e * e * e * e) {
                                StdOut.println("Counterexample found:");
                                StdOut.println("a = " + a);
                                StdOut.println("b = " + b);
                                StdOut.println("c = " + c);
                                StdOut.println("d = " + d);
                                StdOut.println("e = " + e);
                                return; 
                            }
                        }
                    }
                }
            }
    
            StdOut.println("No counterexample found within the specified limit.");
        }

    
}
