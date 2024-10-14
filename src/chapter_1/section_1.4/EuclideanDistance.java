
import java.util.Arrays;

public class EuclideanDistance {
        /**
     * Computes the Euclidean distance between two points in n-dimensional space.
     *
     * @param  a  the coordinates of the first point
     * @param  b  the coordinates of the second point
     * @return    the Euclidean distance between the two points
     * @throws IllegalArgumentException if the vectors do not have the same length
     */
    public static double computeDistance(double[] a, double[] b) {
        double distanceSquared = 0;
        if (a.length != b.length) {
            throw new IllegalArgumentException("Vectors must have the same length");
        }
        for (int i = 0; i < a.length; i++) {
            double diff = a[i] - b[i];
            distanceSquared += diff * diff;
        }
        return Math.sqrt(distanceSquared);
    }
    
    public static void main(String[] args) {
        double[] a = {1, 2, 3};
        double[] b = {4, 5, 6};
        double distance = computeDistance(a, b);
        System.out.println("Euclidean distance from " + Arrays.toString(a) + " to " + Arrays.toString(b) + ": " + distance);
    }
}
