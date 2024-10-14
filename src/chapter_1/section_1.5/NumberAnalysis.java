import lib.StdOut;
import lib.StdIn;
/*
 * Write a program that reads in a sequence of real numbers between -1 and
+1 and prints their average magnitude, average power, and the number of zero
crossings. The average magnitude is the average of the absolute values of the data
values. The average power is the average of the squares of the data values. The num-
ber of zero crossings is the number of times a data value transitions from a strictly
negative number to a strictly positive number, or vice versa. These three statistics
are widely used to analyze digital signals.
 */
public class NumberAnalysis {
    public static void main(String[] args) {

        // Read all real numbers from standard input
        double[] numbers = StdIn.readAllDoubles();

        // Initialize variables for calculations
        double sumMagnitude = 0;
        double sumPower = 0;
        int zeroCrossings = 0;

        // Ensure there are at least two numbers for comparison
        if (numbers.length < 2) {
            System.out.println("Insufficient data for analysis. Please provide at least two real numbers.");
            System.exit(1);
        }

        // Iterate over the numbers array to perform analysis
        for (int i = 0; i < numbers.length - 1; i++) {
            double currentNumber = numbers[i];
            double nextNumber = numbers[i + 1];

            // Update average magnitude and average power
            sumMagnitude += Math.abs(currentNumber);
            sumPower += Math.pow(currentNumber, 2);

            // Update zero crossings count
            if ((currentNumber < 0 && nextNumber > 0) || (currentNumber > 0 && nextNumber < 0)) {
                zeroCrossings++;
            }
        }

        // Calculate averages
        double avgMagnitude = sumMagnitude / numbers.length;
        double avgPower = sumPower / numbers.length;

        // Print the results
        StdOut.printf("Average Magnitude: %.4f%n", avgMagnitude);
        StdOut.printf("Average Power: %.4f%n", avgPower);
        StdOut.println("Number of Zero Crossings: " + zeroCrossings);
    }
}
