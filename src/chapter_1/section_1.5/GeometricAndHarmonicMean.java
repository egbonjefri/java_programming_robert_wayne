
import lib.StdOut;
import lib.StdIn;

/*
 * Write a program that reads in positive ﬂoating-point numbers from standard input
 *  and prints their geometric and harmonic means. 
 * The geometric mean of n positive numbers x1, x2, ..., xn is (x1 * x2 * ... * xn)^1/n.
 *  The harmonic mean is n / (1/x1 + 1/x2 + ... + 1/xn). Hint : For the geometric mean, consider taking loga-
rithms to avoid overﬂow.
 */
public class GeometricAndHarmonicMean {

    public static void main(String[] args) {
        double product = 1.0;
        double harmonicSum = 0.0;
        int count = 0;

        StdOut.println("Enter positive floating-point numbers (Ctrl+D to end):");

        while (!StdIn.isEmpty()) {
            double number = StdIn.readDouble();
            if (number <= 0) {
                System.out.println("Only positive numbers are allowed. Please enter a positive number.");
                continue;
            }
            product *= number;
            harmonicSum += 1.0 / number;
            count++;
        }

        if (count == 0) {
            StdOut.println("No valid input provided.");
            return;
        }

        double geometricMean = Math.pow(product, 1.0 / count);
        double harmonicMean = count / harmonicSum;

        StdOut.println("Geometric Mean: " + geometricMean);
        StdOut.println("Harmonic Mean: " + harmonicMean);
    }

}
