/*
 * Write a program that takes three double command-line arguments x, y,
and z, reads from standard input a sequence of point coordinates (xi, yi, zi), and
prints the coordinates of the point closest to (x, y, z). Recall that the square of the
distance between (x , y , z) and (xi , yi , zi ) is (x - xi )^2 + (y - yi )^2 + (z -zi )^2. For ef-
ﬁciency, do not use Math.sqrt().
 */
import lib.StdIn;
import lib.StdOut;

public class ClosestPointFinder {
    public static void main(String[] args) {
        // Check if three command-line arguments are provided
        if (args.length != 3) {
            StdOut.println("Usage: java ClosestPointFinder <x> <y> <z>");
            System.exit(1);
        }

        // Parse command-line arguments as doubles
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        double z = Double.parseDouble(args[2]);

        // Initialize variables to store the closest point and its squared distance
        double closestX = 0;
        double closestY = 0;
        double closestZ = 0;
        double minDistanceSquared = Double.POSITIVE_INFINITY;

        // Read points from standard input
        while (!StdIn.isEmpty()) {
            // Read coordinates of the current point
            double xi = StdIn.readDouble();
            double yi = StdIn.readDouble();
            double zi = StdIn.readDouble();

            // Calculate squared distance without using Math.sqrt()
            double distanceSquared = (x - xi) * (x - xi) + (y - yi) * (y - yi) + (z - zi) * (z - zi);

            // Update closest point if the current point is closer
            if (distanceSquared < minDistanceSquared) {
                minDistanceSquared = distanceSquared;
                closestX = xi;
                closestY = yi;
                closestZ = zi;
            }
        }

        // Print the coordinates of the closest point
            StdOut.printf("Closest point: (%.2f, %.2f, %.2f)%n", closestX, closestY, closestZ);
    }
}
