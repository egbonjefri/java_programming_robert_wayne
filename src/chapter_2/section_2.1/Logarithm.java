/*
 * Write a static method lg() that takes a double argument n and returns the
base-2 logarithm of n. You may use Java’s Math library.
2.1.10 Write a static method lg() that takes an int argument n and returns the
largest integer not larger than the base-2 logarithm of n. Do not use the Math library.
 */
public class Logarithm {

    // Static method to calculate the base-2 logarithm of n
    public static int lg(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input must be greater than 0.");
        }

        int log = -1; // Initialize log to -1 because we count the 0th position as well
        while (n > 1) {
            n /= 2; // Divide n by 2
            log++;  // Increment the count (logarithm)
        }
        return log; // The largest integer not larger than the base-2 log of n
    }
    // Overloaded method for doubles
    public static int lg(double n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input must be a positive number.");
        }

        // Calculate the base-2 logarithm and take the floor to find the largest integer not larger than the log
        return (int) (Math.log(n) / Math.log(2));
    }
    public static void main(String[] args) {
        // Check if an argument is provided
        if (args.length > 0) {
            try {
                // Try to parse the argument as an integer
                int numberInt = Integer.parseInt(args[0]);
                // Call the int version of lg() if parsing is successful
                System.out.println("The largest integer not larger than the base-2 logarithm of " + numberInt + " (int) is: " + lg(numberInt));
            } catch (NumberFormatException e1) {
                try {
                    // If integer parsing fails, try to parse the argument as a double
                    double numberDouble = Double.parseDouble(args[0]);
                    // Call the double version of lg() if parsing is successful
                    System.out.println("The largest integer not larger than the base-2 logarithm of " + numberDouble + " (double) is: " + lg(numberDouble));
                } catch (NumberFormatException e2) {
                    // If both parsing attempts fail, print an error message
                    System.out.println("Please provide a valid integer or double number.");
                }
            }
        } else {
            System.out.println("Please provide a number as a command-line argument.");
        }
    }
}
