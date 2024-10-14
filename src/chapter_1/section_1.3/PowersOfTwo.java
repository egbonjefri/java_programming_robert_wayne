
    /**
     * Java program that takes an integer argument 'n' from the
     * command line and prints all positive powers of 2 less than or equal to 'n'.
     *
     */

import lib.StdOut;


public class PowersOfTwo {

    public static void main(String[] args) {
        if (args.length == 0) {
            StdOut.println("Please provide an integer argument 'n'.");
            return;
        }

        try {
            int n = Integer.parseInt(args[0]);

            // Handle negative input gracefully
            if (n < 0) {
                StdOut.println("No positive powers of 2 are less than or equal to a negative number.");
                return;
            }
            
            // Efficient bit-shifting approach for positive inputs
            int power = 1;
            while (power <= n) {
                StdOut.println(power);
                power <<= 1; // Equivalent to power *= 2
            }
        } catch (NumberFormatException e) {
            StdOut.println("Invalid input. Please enter an integer.");
        }
    }
}
