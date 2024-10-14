/*
 * Write a static method sqrt() that takes a double argument and returns the
square root of that number. Use Newton’s method (see PROGRAM 1.3.6) to compute
the result.
 */


public class NewtonRaphson {

        // Static method to calculate square root using Newton's method
        public static double sqrt(double c) {
            if (c < 0) {
                return Double.NaN; // Return NaN for negative numbers
            }
            double epsilon = 1e-15; // Error tolerance
            double t = c; // Initial estimate
    
            // Repeat until the desired accuracy is achieved
            while (Math.abs(t - c/t) > epsilon * t) {
                t = (c/t + t) / 2.0;
            }
            return t;
        }
    
        public static void main(String[] args) {
            // Check if an argument is provided
            if (args.length == 1) {
                try {
                    // Parse the argument to a double value
                    double x = Double.parseDouble(args[0]);
    
            System.out.println("The square root of " + x + " is: " + sqrt(x));
                } catch (NumberFormatException e) {
                    System.out.println("Please provide a valid number.");
                }
            } else {
                System.out.println("Please provide exactly one number as an argument.");
            }
        }
    
    
}
