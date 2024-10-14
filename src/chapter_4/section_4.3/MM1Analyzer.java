import lib.MM1Simulation;
import lib.ArrayList;
import lib.StdOut;
import lib.StdDraw;

public class MM1Analyzer {
    public static void main(String[] args) {
        double arrivalRate = 0.5;
        double serviceRate = 1.0;
        int maxCustomers = 10000;

        MM1Simulation simulation = new MM1Simulation(arrivalRate, serviceRate, maxCustomers);

        analyzeAndPlot(simulation.new MM1Queue(), "MM1Queue");
        analyzeAndPlot(simulation.new MM1Stack(), "MM1Stack");
        analyzeAndPlot(simulation.new MM1RandomQueue(), "MM1RandomQueue");
    }

    private static void analyzeAndPlot(MM1Simulation.MM1Base model, String modelName) {
        ArrayList<Double> waitingTimes = model.simulate();
        
        double avgWaitingTime = model.getAverageWaitingTime();
        double avgNumberInSystem = model.getAverageNumberInSystem();
        double actualArrivalRate = model.getArrivalRate();

        StdOut.println(modelName + " Results:");
        StdOut.println("Average Waiting Time: " + avgWaitingTime);
        StdOut.println("Average Number in System: " + avgNumberInSystem);
        StdOut.println("Actual Arrival Rate: " + actualArrivalRate);
        StdOut.println("Little's Law (L = λW): " + (actualArrivalRate * avgWaitingTime));
        StdOut.println("Observed L: " + avgNumberInSystem);

        double standardDeviation = calculateStandardDeviation(waitingTimes);
        StdOut.println("Standard Deviation of Waiting Times: " + standardDeviation);

        plotHistogram(waitingTimes, modelName);
    }

    private static double calculateStandardDeviation(ArrayList<Double> values) {
        double mean = values.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        double variance = values.stream().mapToDouble(v -> Math.pow(v - mean, 2)).average().orElse(0);
        return Math.sqrt(variance);
    }

    public static void plotHistogram(ArrayList<Double> waitingTimes, String modelName) {
        // Number of bins for the histogram
        int numBins = 30;

        // Determine the range of the data
        double min = waitingTimes.stream().mapToDouble(Double::doubleValue).min().orElse(0);
        double max = waitingTimes.stream().mapToDouble(Double::doubleValue).max().orElse(1);

        // Width of each bin
        double binWidth = (max - min) / numBins;

        // Array to hold frequencies
        int[] frequencies = new int[numBins];

        // Count frequencies
        for (double time : waitingTimes) {
            int binIndex = (int) ((time - min) / binWidth);
            if (binIndex == numBins) binIndex--; // Handle edge case where time == max
            frequencies[binIndex]++;
        }

        // Set up StdDraw for plotting
        StdDraw.setCanvasSize(800, 600);
        StdDraw.setXscale(min, max);
        StdDraw.setYscale(0, maxFrequency(frequencies) + 1);
        StdDraw.setPenRadius(0.005);

        // Plot the histogram
        for (int i = 0; i < numBins; i++) {
            double x = min + i * binWidth + binWidth / 2;
            double y = frequencies[i] / 2.0;
            double halfWidth = binWidth / 2;
            double halfHeight = frequencies[i] / 2.0;

            StdDraw.filledRectangle(x, y, halfWidth, halfHeight);
        }

        // Add title and labels
        StdDraw.textLeft(min, maxFrequency(frequencies) + 0.5, modelName + " Waiting Times Histogram");
        StdDraw.textLeft(min, maxFrequency(frequencies) + 0.3, "Frequency");
        StdDraw.textLeft(max, -0.5, "Waiting Time");
    }

    private static int maxFrequency(int[] frequencies) {
        int max = 0;
        for (int frequency : frequencies) {
            if (frequency > max) {
                max = frequency;
            }
        }
        return max;
    }
}