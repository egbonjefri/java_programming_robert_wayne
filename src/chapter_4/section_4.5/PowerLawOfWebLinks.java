import lib.StdDraw;
import lib.ArrayList;
import java.util.Collections;
import lib.StdRandom;
import lib.ST;
import java.util.concurrent.atomic.AtomicInteger;

public class PowerLawOfWebLinks {
    private static final double EPSILON = 1e-10;
    private static final double HISTOGRAM_BAR_WIDTH = 0.4;
    private static final double AXIS_LABEL_OFFSET = 0.05;

    private final int totalPages;
    private final double uniformAttachmentProbability;
    private final ArrayList<AtomicInteger> incomingLinks;
    private final ArrayList<Integer> pages;
    private final AtomicInteger totalIncomingLinks;

    public PowerLawOfWebLinks(int totalPages, double uniformAttachmentProbability) {
        this.totalPages = totalPages;
        this.uniformAttachmentProbability = uniformAttachmentProbability;
        this.incomingLinks = new ArrayList<>();
        this.pages = new ArrayList<>();
        this.totalIncomingLinks = new AtomicInteger(0);

        // Initialize the first page
        this.incomingLinks.add(new AtomicInteger(0));
        this.pages.add(0);
    }

    public void simulate() {
        for (int i = 1; i < totalPages; i++) {
            double probability = StdRandom.uniformDouble();
            int linkedPage;

            if (probability < uniformAttachmentProbability + EPSILON) {
                linkedPage = StdRandom.uniformInt(i);
            } else {
                linkedPage = weightedRandomChoice();
            }

            synchronized (this) {
                this.incomingLinks.add(new AtomicInteger(0));
                this.pages.add(linkedPage);
                this.incomingLinks.get(linkedPage).incrementAndGet();
                this.totalIncomingLinks.incrementAndGet();
            }
        }
    }

    private int weightedRandomChoice() {
        int sum = totalIncomingLinks.get();
        
        if (sum <= 0) {
            return StdRandom.uniformInt(incomingLinks.size());
        }

        int target =StdRandom.uniformInt(sum);
        int cumulativeSum = 0;

        for (int i = 0; i < incomingLinks.size(); i++) {
            cumulativeSum += incomingLinks.get(i).get();
            if (cumulativeSum > target) {
                return i;
            }
        }

        throw new IllegalStateException("Weighted random choice failed unexpectedly");
    }

    public ST<Integer, Integer> generateHistogramData() {
        ST<Integer, Integer> histogram = new ST<>();

        for (AtomicInteger links : incomingLinks) {
            int linkCount = links.get();
            histogram.put(linkCount, histogram.getOrDefault(linkCount, 0) + 1);
        }

        return histogram;
    }

    // Method to plot histogram using StdDraw
    public void plotHistogramWithStdDraw() {
        ST<Integer, Integer> histogram = generateHistogramData();

        // Set up StdDraw canvas based on histogram size
        int maxIncomingLinks = histogram.max();
        int maxFrequency = Collections.max(histogram.values());

        StdDraw.setXscale(-1, maxIncomingLinks + 1);  // X-axis for number of incoming links
        StdDraw.setYscale(0, maxFrequency + 1);       // Y-axis for frequency
        StdDraw.setPenRadius(0.005);

        // Plot histogram bars
        for (Integer entry : histogram) {
            int k = entry;
            int frequency = histogram.get(entry);

            // Draw each bar: (x, y) is the center of the bar
            double x = k;
            double y = frequency / 2.0;
            double halfWidth = HISTOGRAM_BAR_WIDTH / 2;
            double halfHeight = frequency / 2.0;

            StdDraw.filledRectangle(x, y, halfWidth, halfHeight);
        }

        // Label axes
        StdDraw.text(maxIncomingLinks / 2.0, -AXIS_LABEL_OFFSET * maxFrequency, "Number of Incoming Links");
        StdDraw.text(-AXIS_LABEL_OFFSET * maxIncomingLinks, maxFrequency / 2.0, "Frequency", 90);
    }

    public static void main(String[] args) {
        int totalPages = args.length > 0 ? Integer.parseInt(args[0]) : 1000;
        double uniformAttachmentProbability = args.length > 1 ? Double.parseDouble(args[1]) : 0.3;

        PowerLawOfWebLinks simulation = new PowerLawOfWebLinks(totalPages, uniformAttachmentProbability);
        simulation.simulate();
        simulation.plotHistogramWithStdDraw();  // Plot the histogram using StdDraw
    }
}
