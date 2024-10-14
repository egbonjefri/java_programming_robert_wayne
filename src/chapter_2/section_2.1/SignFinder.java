/*
 * Write a static method signum() that takes an int argument n and returns
-1 if n is less than 0, 0 if n is equal to 0, and +1 if n is greater than 0.
 */
public class SignFinder {

    // Static method to determine the sign of n
    public static int signum(int n) {
        if (n < 0) {
            return -1; // Return -1 if n is less than 0
        } else if (n == 0) {
            return 0; // Return 0 if n is equal to 0
        } else {
            return 1; // Return 1 if n is greater than 0
        }
    }

    public static void main(String[] args) {
        // Check if command line arguments are provided
        if (args.length > 0) {
            for (String arg : args) {
                try {
                    // Parse the argument as an integer
                    int number = Integer.parseInt(arg);
                    // Calculate and print the signum
                    System.out.println("The signum of " + number + " is: " + signum(number));
                } catch (NumberFormatException e) {
                    // If the argument is not a valid integer, print an error message
                    System.out.println("The argument \"" + arg + "\" is not a valid integer.");
                }
            }
        } else {
            System.out.println("Please provide one or more integers as command-line arguments.");
        }
    }
}
