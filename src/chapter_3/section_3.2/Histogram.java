/*
 * Develop a version of Histogram that uses Draw, so that a client can create
multiple histograms. Add to the display a red vertical line showing the sample mean
and blue vertical lines at a distance of two standard deviations from the mean. Use a
test client that creates histograms for ﬂipping coins (Bernoulli trials) with a biased
coin that is heads with probability p, for p = 0.2, 0.4, 0.6. and 0.8, taking the number
of ﬂips and the number of trials from the command line, as in PROGRAM 3.2.3.
 */
import lib.StdRandom;
import lib.StdDraw;
import lib.StdStats;
import lib.Counter;

public class Histogram {
    private final double[] freq;   // Frequency of each outcome
    private double max;            // Maximum frequency observed
    private final int n;           // Number of possible outcomes (0 to n-1)
    private final Counter[] counters;

    public Histogram(int n) {
        this.n = n;
        this.freq = new double[n];
        this.max = 0;
        counters = new Counter[n];
        for (int i = 0; i < n; i++) {
            counters[i] = new Counter("Counter" + i);
        }
    }

    public void addDataPoint(int i) {
        if (i >= 0 && i < this.n) {
            this.freq[i]++;
            counters[i].increment();
            this.max = Math.max(this.max, this.freq[i]);
        }
    }

    public void draw() {
        double mean = calculateMean();
        double sd = calculateSD(mean);

        StdDraw.setYscale(-1, max + 1);
        StdDraw.setXscale(-1, n);
        StdStats.plotBars(freq);

        // Draw mean line in red
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.line(mean, 0, mean, max);

        // Draw standard deviation lines in blue
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.line(mean - 2 * sd, 0, mean - 2 * sd, max);
        StdDraw.line(mean + 2 * sd, 0, mean + 2 * sd, max);
    }

    private double calculateMean() {
        double sum = 0;
        double total = 0;
        for (int i = 0; i < n; i++) {
            sum += i * freq[i];
            total += freq[i];
        }
        return sum / total;
    }

    private double calculateSD(double mean) {
        double sum = 0;
        double total = 0;
        for (int i = 0; i < n; i++) {
            sum += freq[i] * Math.pow(i - mean, 2);
            total += freq[i];
        }
        return Math.sqrt(sum / total);
    }
    private static int simulateBiasedCoin(int n, double p) {
        int heads = 0;
        for (int i = 0; i < n; i++) {
            if (StdRandom.bernoulli(p)) heads++;
        }
        return heads;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);       // Number of coin flips
        int trials = Integer.parseInt(args[1]);  // Number of trials

        double[] probabilities = {0.2, 0.4, 0.6, 0.8};
        for (double p : probabilities) {
            Histogram histogram = new Histogram(n + 1);
            for (int t = 0; t < trials; t++) {
                histogram.addDataPoint(simulateBiasedCoin(n, p));
            }

            histogram.draw();
        }
    }
}
