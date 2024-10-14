import lib.StdOut;
/*
 * java program that converts from Cartesian
to polar coordinates. Accepts two double command-
line arguments x and y and prints the polar coordinates r and theta.
 */


public class CartesianToPolar {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java CartesianToPolar <x> <y>");
            return;
        }

        try {
            double x = Double.parseDouble(args[0]);
            double y = Double.parseDouble(args[1]);

            double r = calculateRadius(x, y);
            double thetaRadians = Math.atan2(y, x);
            double thetaDegrees = Math.toDegrees(thetaRadians);

            StdOut.printf("Polar coordinates: r = %.2f, theta = %.2f degrees%n", r, thetaDegrees);

        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid input. Please enter numbers only.");
        }
    }

    private static double calculateRadius(double x, double y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
