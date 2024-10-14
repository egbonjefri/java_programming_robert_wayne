
/*
 * Write a static method areTriangular() that takes three double arguments
and returns true if they could be the sides of a triangle (none of them is greater
than or equal to the sum of the other two).
 */
public class AreTriangular {

        // Static method to check if three lengths can form a triangle
        public static boolean areTriangular(double a, double b, double c) {
            // Check the triangle inequality theorem
            return (a + b > c) && (a + c > b) && (b + c > a);
        }
    
        public static void main(String[] args) {
            // Check if exactly three arguments are provided
            if (args.length == 3) {
                try {
                    // Parse arguments to double values
                    double side1 = Double.parseDouble(args[0]);
                    double side2 = Double.parseDouble(args[1]);
                    double side3 = Double.parseDouble(args[2]);
    
                    // Check if the provided lengths can form a triangle
                    boolean result = areTriangular(side1, side2, side3);
    
                    // Output the result
                    if (result) {
                        System.out.println("The lengths " + side1 + ", " + side2 + ", and " + side3 + " can form a triangle.");
                    } else {
                        System.out.println("The lengths " + side1 + ", " + side2 + ", and " + side3 + " cannot form a triangle.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please provide valid numbers for the lengths of the sides.");
                }
            } else {
                System.out.println("Please provide exactly three numbers as arguments.");
            }
        }
    }
    

