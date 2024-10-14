/*
 * Use statistical sampling to estimate the average path
length and clustering coefﬁcient of a graph. For example, to estimate the clustering
coefﬁcient, pick trials random vertices and compute the average of the clustering
coefﬁcients of those vertices. The running time of your functions should be orders
of magnitude faster than the corresponding functions from SmallWorld.
 */

 import lib.ArrayList;
import lib.StdDraw;
import lib.SmallWorld;
import lib.GraphStatisticsSampler;
import lib.Graph;
import lib.StdRandom;

public class GraphComparisonPlotter {
    private static final int NUM_GRAPHS = 10;
    private static final int MIN_VERTICES = 10;
    private static final int MAX_VERTICES = 100;
    private static final int NUM_TRIALS = 100;

    public static void main(String[] args) {
        try {
            ArrayList<Graph<Integer>> graphs = generateRandomGraphs();
            ArrayList<Double> exactPathLengths = new ArrayList<>();
            ArrayList<Double> sampledPathLengths = new ArrayList<>();
            ArrayList<Double> exactClusteringCoefficients = new ArrayList<>();
            ArrayList<Double> sampledClusteringCoefficients = new ArrayList<>();

            for (int i = 0; i < graphs.size(); i++) {
                Graph<Integer> graph = graphs.get(i);
                try {
                    // Exact calculations
                    exactPathLengths.add(SmallWorld.averagePathLength(graph));
                    exactClusteringCoefficients.add(SmallWorld.clusteringCoefficient(graph));
            
                    // Sampled calculations
                    GraphStatisticsSampler sampler = new GraphStatisticsSampler(convertToAdjacencyMatrix(graph));
                    sampledPathLengths.add(sampler.estimateAveragePathLength(NUM_TRIALS));
                    sampledClusteringCoefficients.add(sampler.estimateClusteringCoefficient(NUM_TRIALS));
                } catch (Exception e) {
                    System.err.println("Error processing graph " + (i + 1) + ": " + e.getMessage());
                    e.printStackTrace();
                    System.err.println("Graph structure: " + graph.toString());
                    // Optionally, add placeholder values to keep the lists aligned
                    exactPathLengths.add(Double.NaN);
                    exactClusteringCoefficients.add(Double.NaN);
                    sampledPathLengths.add(Double.NaN);
                    sampledClusteringCoefficients.add(Double.NaN);
                }
            }
          plotResults(exactPathLengths, sampledPathLengths, exactClusteringCoefficients, sampledClusteringCoefficients);
          printResults(exactPathLengths, sampledPathLengths, exactClusteringCoefficients, sampledClusteringCoefficients);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static ArrayList<Graph<Integer>> generateRandomGraphs() {
        ArrayList<Graph<Integer>> graphs = new ArrayList<>();

        for (int i = 0; i < NUM_GRAPHS; i++) {
            int numVertices = StdRandom.uniformInt(MIN_VERTICES, MAX_VERTICES + 1);
            Graph<Integer> graph = new Graph<>(Integer.class);

            // Add vertices
            for (int j = 0; j < numVertices; j++) {
                graph.addVertex(j);
            }

            // Add edges to ensure connectivity
            for (int j = 1; j < numVertices; j++) {
                graph.addEdge(j - 1, j);
            }

            // Add additional random edges
            int maxAdditionalEdges = (numVertices * (numVertices - 1) / 2) - (numVertices - 1);
            int additionalEdges = StdRandom.uniformInt(maxAdditionalEdges + 1);

            for (int j = 0; j < additionalEdges; j++) {
                int v = StdRandom.uniformInt(numVertices);
                int w = StdRandom.uniformInt(numVertices);
                if (v != w && !graph.hasEdge(v, w)) {
                    graph.addEdge(v, w);
                }
            }

            graphs.add(graph);
        }

        return graphs;
    }

    private static int[][] convertToAdjacencyMatrix(Graph<Integer> graph) {
        int V = graph.V();
        int[][] matrix = new int[V][V];

        for (int v = 0; v < V; v++) {
            Integer vertex = graph.reverseMap.get(v);
            if (vertex != null) {
                for (int w : graph.adj(vertex)) {
                    int wIndex = graph.vertexIndex(w);
                    if (wIndex != -1) {
                        matrix[v][wIndex] = 1;
                        matrix[wIndex][v] = 1;
                    }
                }
            }
        }

        return matrix;
    }
 
    private static void plotResults(ArrayList<Double> exactPathLengths, ArrayList<Double> sampledPathLengths,
                                    ArrayList<Double> exactClusteringCoefficients, ArrayList<Double> sampledClusteringCoefficients) {
        int canvasWidth = 800;
        int canvasHeight = 600;
        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.setXscale(0, NUM_GRAPHS + 1);
        StdDraw.setYscale(0, 1.1); // Increased y-scale to accommodate legend

        // Plot average path lengths
        plotLineGraph(exactPathLengths, StdDraw.RED, "Exact Path Length");
        plotLineGraph(sampledPathLengths, StdDraw.BLUE, "Sampled Path Length");

        // Plot clustering coefficients
        plotLineGraph(exactClusteringCoefficients, StdDraw.GREEN, "Exact Clustering Coefficient");
        plotLineGraph(sampledClusteringCoefficients, StdDraw.MAGENTA, "Sampled Clustering Coefficient");

        // Add legend
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(NUM_GRAPHS / 2.0, 1.05, "Comparison of Exact vs Sampled Graph Metrics");
        StdDraw.textLeft(0.5, 1.02, "Exact Path Length");
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.line(0.2, 1.02, 0.4, 1.02);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.textLeft(0.5, 0.98, "Sampled Path Length");
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.line(0.2, 0.98, 0.4, 0.98);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.textLeft(NUM_GRAPHS / 2.0 + 0.5, 1.02, "Exact Clustering Coefficient");
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.line(NUM_GRAPHS / 2.0 + 0.2, 1.02, NUM_GRAPHS / 2.0 + 0.4, 1.02);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.textLeft(NUM_GRAPHS / 2.0 + 0.5, 0.98, "Sampled Clustering Coefficient");
        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.line(NUM_GRAPHS / 2.0 + 0.2, 0.98, NUM_GRAPHS / 2.0 + 0.4, 0.98);

        // Add axis labels
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(NUM_GRAPHS / 2.0, -0.05, "Graph Number");
        StdDraw.text(-0.5, 0.5, "Metric Value", 90);

        StdDraw.show();
    }

    private static void plotLineGraph(ArrayList<Double> data, java.awt.Color color, String label) {
        StdDraw.setPenColor(color);
        
        // Ensure data has enough points to plot
        int plotLimit = Math.min(NUM_GRAPHS - 1, data.size() - 1);
        
        for (int i = 0; i < plotLimit; i++) {
            // Safeguard to avoid index out of bounds
            if (i + 1 < data.size()) {
                StdDraw.line(i + 1, data.get(i), i + 2, data.get(i + 1));
            }
        }
    }
    

    private static void printResults(ArrayList<Double> exactPathLengths, ArrayList<Double> sampledPathLengths,
    ArrayList<Double> exactClusteringCoefficients, ArrayList<Double> sampledClusteringCoefficients) {
                System.out.println("Comparison of Exact vs Sampled Graph Metrics");
                System.out.println("---------------------------------------------");
                System.out.println("Graph\tExact Path\tSampled Path\tExact Clustering\tSampled Clustering");
                System.out.println("-----\t----------\t------------\t-----------------\t------------------");

            for (int i = 0; i < NUM_GRAPHS; i++) {
                if (i < exactPathLengths.size() && i < sampledPathLengths.size() &&
                    i < exactClusteringCoefficients.size() && i < sampledClusteringCoefficients.size()) {
                        System.out.printf("%d\t%.4f\t\t%.4f\t\t%.4f\t\t%.4f%n", i + 1, exactPathLengths.get(i),
                                sampledPathLengths.get(i), exactClusteringCoefficients.get(i), sampledClusteringCoefficients.get(i));
                            }
                        }
                }
}