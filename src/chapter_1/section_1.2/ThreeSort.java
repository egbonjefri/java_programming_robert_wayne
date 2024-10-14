    /**
     * This program takes command-line arguments and prints them in ascending order.
     *
     * @param  args  an array of strings representing the command-line arguments
     */
import lib.StdOut;

public class ThreeSort {
    public static void main(String[] args) {
        if (args.length != 3) {
            StdOut.println("Please provide exactly three integer arguments.");
            return; // Exit if incorrect number of arguments
        }

        // Parse the command-line arguments into integers
        int num1 = Integer.parseInt(args[0]);
        int num2 = Integer.parseInt(args[1]);
        int num3 = Integer.parseInt(args[2]);

        // Find the minimum, maximum, and median (middle) values
        int min = Math.min(num1, Math.min(num2, num3));
        int max = Math.max(num1, Math.max(num2, num3));
        int median = num1 + num2 + num3 - min - max;

        // Print the sorted numbers
        StdOut.println(min);
        StdOut.println(median);
        StdOut.println(max);
    }
}
