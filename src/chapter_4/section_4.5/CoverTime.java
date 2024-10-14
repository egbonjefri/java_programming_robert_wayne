import generators.GraphGenerator;
import lib.Graph;
import lib.StdDraw;
import lib.StdRandom;
import lib.SET;
import lib.ArrayList;
import lib.StdOut;

public class CoverTime {

    private static class Result {
        int vertices;
        double avgCoverTime;

        Result(int vertices, double avgCoverTime) {
            this.vertices = vertices;
            this.avgCoverTime = avgCoverTime;
        }
    }

    public static void main(String[] args) {
        int maxVertices = 100;
        int trials = 10;

        // Adjust the scale to make the plot more visible
        StdDraw.setXscale(0, maxVertices);
        StdDraw.setYscale(0, 20000); // Adjust this value based on your observed maximum cover time
        StdDraw.setPenRadius(0.01);

        ArrayList<Result> completeResults = simulateAndPlot(maxVertices, trials, "Complete", StdDraw.RED);
        ArrayList<Result> ringResults = simulateAndPlot(maxVertices, trials, "Ring", StdDraw.BLUE);
        ArrayList<Result> lollipopResults = simulateAndPlot(maxVertices, trials, "Lollipop", StdDraw.GREEN);

        addReferenceLines(maxVertices);
        addLegend(maxVertices);

        // Print results to stdout
        System.out.println("Complete Graph Results:");
        printResults(completeResults);
        System.out.println("\nRing Graph Results:");
        printResults(ringResults);
        System.out.println("\nLollipop Graph Results:");
        printResults(lollipopResults);
    }

    private static ArrayList<Result> simulateAndPlot(int maxVertices, int trials, String graphType, java.awt.Color color) {
        ArrayList<Result> results = new ArrayList<>();
        StdDraw.setPenColor(color);
        for (int V = 10; V <= maxVertices; V += 5) {
            long totalCoverTime = 0;
            for (int t = 0; t < trials; t++) {
                Graph<Integer> G = generateGraph(V, graphType);
                totalCoverTime += performRandomWalk(G);
            }
            double avgCoverTime = (double) totalCoverTime / trials;
            StdDraw.point(V, avgCoverTime);
            results.add(new Result(V, avgCoverTime));
        }
        return results;
    }

    private static void printResults(ArrayList<Result> results) {
        StdOut.println("Vertices | Avg Cover Time");
        StdOut.println("---------|----------------");
        for (Result result : results) {
            StdOut.printf("%-9d| %.2f%n", result.vertices, result.avgCoverTime);
        }
    }

    private static Graph<Integer> generateGraph(int V, String graphType) {
        switch (graphType) {
            case "Complete":
                return GraphGenerator.generateRandomConnectedGraph(V, V * (V - 1) / 2);
            case "Ring":
                return GraphGenerator.generateKRingGraph(V, 1);
            case "Lollipop":
                return GraphGenerator.generateLollipopGraph(V / 2, V / 2);
            default:
                throw new IllegalArgumentException("Unknown graph type");
        }
    }

    private static int performRandomWalk(Graph<Integer> G) {
        SET<Integer> visited = new SET<>();
        int currentVertex = StdRandom.uniformInt(G.V());
        int steps = 0;

        while (visited.size() < G.V()) {
            visited.add(currentVertex);
            Iterable<Integer> neighbors = G.adj(currentVertex);
            int[] neighborArray = new int[G.degree(currentVertex)];
            int index = 0;
            for (int neighbor : neighbors) {
                neighborArray[index++] = neighbor;
            }
            currentVertex = neighborArray[StdRandom.uniformInt(neighborArray.length)];
            steps++;
        }
        return steps;
    }

    private static void addReferenceLines(int maxVertices) {
        StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);

        // V log V
        for (int V = 10; V <= maxVertices; V += 5) {
            double y = V * Math.log(V);
            StdDraw.point(V, Math.min(y, 20000)); // Limit to the max y-value
        }

        // V^2
        for (int V = 10; V <= maxVertices; V += 5) {
            double y = V * V;
            StdDraw.point(V, Math.min(y, 20000)); // Limit to the max y-value
        }

        // We'll remove the V^3 line as it would be off the scale for most of the graph
    }

    private static void addLegend(int maxVertices) {
        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(10, 19000);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(20, 19000, "Complete");

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.point(10, 18000);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(20, 18000, "Ring");

        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.point(10, 17000);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(20, 17000, "Lollipop");

        StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.point(10, 16000);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(20, 16000, "Reference Lines");
    }
}