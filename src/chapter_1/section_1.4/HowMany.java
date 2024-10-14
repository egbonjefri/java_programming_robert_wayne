/*
 * a simple Java program that takes command-line arguments and prints the number of arguments entered.
 */

import lib.StdOut;

public class HowMany {
    public static void main(String[] args) {
        int numArgs = args.length;
        System.out.print("You entered " + numArgs + " command-line argument");
        if (numArgs != 1) {  // Pluralize if there's more than one argument
        StdOut.println("s.");
        } else {
            StdOut.println(".");
        }
    }
}
