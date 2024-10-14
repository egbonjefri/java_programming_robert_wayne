
import lib.StdOut;

/*
 *  Java program that takes an angle in degrees from the command line, 
 * converts it to radians, and then checks the Pythagorean identity: cos²θ + sin²θ = 1² = 1
 */
public class PythagoreanIdentity {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java PythagoreanIdentityCheck <angle_in_degrees>");
            return;
        }

        try {
            double degrees = Double.parseDouble(args[0]);
            double radians = Math.toRadians(degrees);

            double cosSquared = Math.pow(Math.cos(radians), 2);
            double sinSquared = Math.pow(Math.sin(radians), 2);
            double sum = cosSquared + sinSquared;

            // Account for potential floating-point inaccuracies
            final double TOLERANCE = 1e-10; 
            boolean isIdentityValid = Math.abs(sum - 1.0) < TOLERANCE;

            StdOut.println("For angle " + degrees + " degrees:");
            StdOut.println("cos^2(theta) + sin^2(theta) = " + sum);
            StdOut.println("Pythagorean identity valid: " + isIdentityValid);

        } catch (NumberFormatException e) {
            System.err.println("Invalid input: Please enter a valid number for the angle.");
        }
    }  
}
