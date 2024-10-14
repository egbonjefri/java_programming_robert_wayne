import lib.StdIn;
import lib.StdOut;

/*
 * Given the positions and masses of a sequence of objects, write a program
to compute their center-of-mass, or centroid. The centroid is the average position of
the n objects, weighted by mass. If the positions and masses are given by (xi , yi, mi ),
then the centroid (x, y, m) is given by
m = m1 + m2 + ... + mn
x = (m1 x1 + ... + mn xn) / m
y = (m1 y1 + ... + mn yn ) / m
 */
public class CentroidCalculator {
    public static void main(String[] args) {
        double totalMass = 0;
        double totalWeightedX = 0;
        double totalWeightedY = 0;

        // Input positions and masses for each object
        while (!StdIn.isEmpty()) {
            double mass = StdIn.readDouble();
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();

            // Update total mass and weighted coordinates
            totalMass += mass;
            totalWeightedX += mass * x;
            totalWeightedY += mass * y;
        }

        // Calculate the centroid
        double centroidX = totalWeightedX / totalMass;
        double centroidY = totalWeightedY / totalMass;

        // Print the centroid
        StdOut.printf("Centroid: (%.2f, %.2f)%n", centroidX, centroidY);
    }
}


// java RandomMassPositionGenerator 5 | java CentroidCalculator