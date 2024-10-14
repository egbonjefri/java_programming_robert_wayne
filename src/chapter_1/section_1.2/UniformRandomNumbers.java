    /**
     * Generates a specified number of random numbers between 0 and 1, calculates their average,
     * and finds the minimum and maximum values. Prints the random numbers, average, minimum,
     * and maximum to the console.
     *
     * @param  args  the command-line arguments (not used)
     */
import lib.StdRandom;
import lib.StdOut;

public class UniformRandomNumbers {
    public static void main(String[] args) {
        int numRandoms = Integer.parseInt(args[0]);
        double[] randNums = new double[numRandoms];
        double sum = 0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        
        for(int i = 0; i < numRandoms; i++) {
            double randNum = StdRandom.uniformDouble();
            randNums[i] = randNum;
            sum += randNum;
            min = Math.min(min, randNum);
            max = Math.max(max, randNum);
        }
        
        double average = sum / numRandoms;
        
        StdOut.println("Random numbers:");
        for(double num : randNums) {
            StdOut.print(num + " ");
        }
        StdOut.println();
        
        StdOut.println("Average: " + average);
        StdOut.println("Minimum: " + min);
        StdOut.println("Maximum: " + max);
    }
}
