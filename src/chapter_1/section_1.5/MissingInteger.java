import lib.StdOut;
import lib.StdIn;

/*
 * Write a program that takes an integer command-line argument n, reads in
n-1 distinct integers between 1 and n, and determines the missing value.
 */

public class MissingInteger {

    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("Please provide a single integer argument.");
            return;
        }

        int n = Integer.parseInt(args[0]);

        // Create a boolean array to keep track of the numbers read
        boolean[] numbersPresent = new boolean[n + 1];

        // Read n-1 integers
        StdOut.println("Please enter " + (n - 1) + " distinct integers between 1 and " + n + ":");
        for (int i = 0; i < n - 1; i++) {
            int number = StdIn.readInt();
            numbersPresent[number] = true;
        }

    

        // Determine the missing number
        for (int i = 1; i <= n; i++) {
            if (!numbersPresent[i]) {
                StdOut.println("The missing number is: " + i);
                return;
            }
        }
    }
}

