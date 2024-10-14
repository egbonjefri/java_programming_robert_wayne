import lib.StdOut;




public class DisplacementCalculator {

    // Gravitational constant
    private static final double GRAVITY = 9.80665;

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java DisplacementCalculator <initial_position> <initial_velocity> <time>");
            return;
        }

        try {
            double x0 = Double.parseDouble(args[0]); // Initial position (meters)
            double v0 = Double.parseDouble(args[1]); // Initial velocity (m/s)
            double t = Double.parseDouble(args[2]);   // Time (seconds)

            double displacement = calculateDisplacement(x0, v0, t);
            StdOut.println("Displacement after " + t + " seconds: " + displacement + " meters");

        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid input. Please enter numbers only.");
        }
    }

    private static double calculateDisplacement(double x0, double v0, double t) {
        return x0 + v0 * t - 0.5 * GRAVITY * Math.pow(t, 2);
    }
}
