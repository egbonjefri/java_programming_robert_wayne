/*
 * Write a static method sigmoid() that takes a double argument x and re-
turns the double value obtained from the formula 1 / (1 + e^-x).
 */
public class Sigmoid {

        // Static method to calculate the sigmoid of a given double value
        public static double sigmoid(double x) {
            return 1 / (1 + Math.exp(-x));
        }
    
        public static void main(String[] args) {
            // Check if an argument is provided
            if (args.length == 1) {
                try {
                    // Parse the argument to a double value
                    double x = Double.parseDouble(args[0]);
    
                    // Calculate and print the sigmoid of the provided value
                    System.out.println("The sigmoid of " + x + " is: " + sigmoid(x));
                } catch (NumberFormatException e) {
                    System.out.println("Please provide a valid number.");
                }
            } else {
                System.out.println("Please provide exactly one number as an argument.");
                return;
            }
        }
    
}
