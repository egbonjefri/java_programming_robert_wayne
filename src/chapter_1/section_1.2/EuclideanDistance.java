import lib.StdOut;
/*
 * Java program that calculates and prints the Euclidean distance
 */
public class EuclideanDistance {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java EuclideanDistance <x> <y>");
            return;
        }

        try {
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);

            double distance = calculateEuclideanDistance(x, y);
            StdOut.println("Euclidean distance from (" + x + ", " + y + ") to the origin (0, 0): " + distance);

        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid input. Please enter integers only.");
        }
    }

    private static double calculateEuclideanDistance(int x, int y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)); // Euclidean distance formula
    }
}
