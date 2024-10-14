package generators;
import lib.StdOut;

public class RandomMassPositionGenerator {
    public static void main(String[] args) {
        // Check if a command-line argument is provided
        if (args.length != 1) {
            StdOut.println("Usage: java RandomMassPositionGenerator <n>");
            System.exit(1);
        }

        // Parse the command-line argument as an integer
        int n = Integer.parseInt(args[0]);

        // Print n random masses and (x, y) positions
        for (int i = 0; i < n; i++) {
            double mass = round(randomInRange(1.0, 10.0), 2);
            double x = round(randomInRange(-10.0, 10.0), 2);
            double y = round(randomInRange(-10.0, 10.0), 2);

            StdOut.printf("%.2f %.2f %.2f%n", mass, x, y);
        }
    }

    // Function to generate random number within a range
    private static double randomInRange(double min, double max) {
        return min + (max - min) * Math.random();
    }

    // Function to round a double value to a specified number of decimal places
    private static double round(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
}
