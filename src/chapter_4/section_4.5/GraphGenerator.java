import lib.Graph;
import lib.StdRandom;
import lib.ArrayList;
import lib.SmallWorld;
import lib.StdOut;

public class GraphGenerator {

       // Method to generate an n-by-n grid graph
       public static Graph<String> generateGridGraph(int n) {
        Graph<String> G = new Graph<>(n * n);  // Initialize a graph with n*n vertices
        
        // Create vertex labels based on their grid position
        String[][] vertices = new String[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                vertices[row][col] = row + "-" + col; // Label each vertex as "row-col"
            }
        }

        // Add edges to connect each vertex to its neighbors (above, below, left, right)
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // Connect to the vertex above, if it exists
                if (row > 0) {
                    G.addEdge(vertices[row][col], vertices[row - 1][col]);
                }
                // Connect to the vertex below, if it exists
                if (row < n - 1) {
                    G.addEdge(vertices[row][col], vertices[row + 1][col]);
                }
                // Connect to the vertex to the left, if it exists
                if (col > 0) {
                    G.addEdge(vertices[row][col], vertices[row][col - 1]);
                }
                // Connect to the vertex to the right, if it exists
                if (col < n - 1) {
                    G.addEdge(vertices[row][col], vertices[row][col + 1]);
                }
            }
        }

        return G;
    }
    public static Graph<Integer> generateLollipopGraph(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Both n and m must be positive integers");
        }
    
        Graph<Integer> G = new Graph<>(n + m);
    
        // Generate the complete graph part (the "candy")
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                G.addEdge(i, j);
            }
        }
    
        // Generate the path part (the "stick")
        for (int i = n; i < n + m - 1; i++) {
            G.addEdge(i, i + 1);
        }
    
        // Connect the complete graph to the path
        G.addEdge(n - 1, n);
    
        return G;
    }
    // Generate a random connected graph with V vertices and approximately E edges
    public static Graph<Integer> generateRandomConnectedGraph(int V, int E) {
        Graph<Integer> G = new Graph<>(V);

        // Step 1: Create a random spanning tree (to ensure the graph is connected)
        ArrayList<Integer> vertices = new ArrayList<>();
        vertices.add(0); // Start with vertex 0
        for (int v = 1; v < V; v++) {
            int randomVertex = vertices.get(StdRandom.uniformInt(vertices.size()));
            G.addEdge(v, randomVertex);
            vertices.add(v); // Add vertex v to the connected component
        }

        // Step 2: Add random edges until we reach approximately E edges
        while (G.E() < E) {
            int v = StdRandom.uniformInt(V);
            int w = StdRandom.uniformInt(V);
            if (v != w && !G.hasEdge(v, w)) {
                G.addEdge(v, w); // Add random edge between distinct vertices
            }
        }

        return G;
    }

    // Generate a 2-ring graph with random shortcuts
    public static Graph<Integer> generate2RingGraphWithShortcuts(int V, int shortcutCount) {
        Graph<Integer> G = new Graph<>(V);

        // Step 1: Create a 2-ring structure (each vertex connected to two neighbors)
        for (int v = 0; v < V; v++) {
            G.addEdge(v, (v + 1) % V); // Connect to the next vertex in the ring
            G.addEdge(v, (v + V - 1) % V); // Connect to the previous vertex in the ring
        }

        // Step 2: Add random shortcuts (non-adjacent vertices)
        for (int i = 0; i < shortcutCount; i++) {
            int v = StdRandom.uniformInt(V);
            int w = StdRandom.uniformInt(V);
            if (v != w && !G.hasEdge(v, w) && Math.abs(v - w) > 1 && Math.abs(v - w) < V - 1) {
                G.addEdge(v, w); // Add a random shortcut between two non-adjacent vertices
            }
        }

        return G;
    }
    // Method to generate a k-ring graph
    public static Graph<String> generateKRingGraph(int vertices, int k) {
        Graph<String> G = new Graph<>(vertices);  // Initialize an empty graph with the number of vertices
        
        // Create a list of vertex labels (1 to N)
        String[] vertexLabels = new String[vertices];
        for (int i = 0; i < vertices; i++) {
            vertexLabels[i] = String.valueOf(i);
        }
        
        // For each vertex, connect to its k neighbors on both sides in a circular manner
        for (int i = 0; i < vertices; i++) {
            for (int j = 1; j <= k; j++) {
                // Connect vertex i to (i + j) % vertices and (i - j + vertices) % vertices
                int nextNeighbor = (i + j) % vertices;
                int previousNeighbor = (i - j + vertices) % vertices;
                
                // Add edges in both directions to maintain symmetry
                G.addEdge(vertexLabels[i], vertexLabels[nextNeighbor]);
                G.addEdge(vertexLabels[i], vertexLabels[previousNeighbor]);
            }
        }

        return G;
    }
    public static void generateAndAnalyzeGraphs() {
        int graphCount = 500;
        int vertices = 1000;
        int edgesForConnected = 5000; // The number of edges for random connected graph
        int shortcutsForRing = 50; // Number of shortcuts for the 2-ring graph

        // For Random Connected Graphs
        double totalAvgDegreeConnected = 0.0;
        double totalAvgPathLengthConnected = 0.0;
        double totalClusterCoeffConnected = 0.0;

        // For 2-Ring Graphs with Shortcuts
        double totalAvgDegreeRing = 0.0;
        double totalAvgPathLengthRing = 0.0;
        double totalClusterCoeffRing = 0.0;

        // Generate and analyze 500 random connected graphs
        StdOut.println("Generating and analyzing 500 random connected graphs...");
        for (int i = 0; i < graphCount; i++) {
            Graph<Integer> G = generateRandomConnectedGraph(vertices, edgesForConnected);

            double avgDegree = SmallWorld.averageDegree(G);
            double avgPathLength = SmallWorld.averagePathLength(G);
            double clusterCoeff = SmallWorld.clusteringCoefficient(G);

            totalAvgDegreeConnected += avgDegree;
            totalAvgPathLengthConnected += avgPathLength;
            totalClusterCoeffConnected += clusterCoeff;

            if (i % 100 == 0) {
                StdOut.println("Processed " + (i + 1) + " random connected graphs");
            }
        }

        // Generate and analyze 500 2-ring graphs with random shortcuts
        StdOut.println("Generating and analyzing 500 2-ring graphs with random shortcuts...");
        for (int i = 0; i < graphCount; i++) {
            Graph<Integer> G = generate2RingGraphWithShortcuts(vertices, shortcutsForRing);

            double avgDegree = SmallWorld.averageDegree(G);
            double avgPathLength = SmallWorld.averagePathLength(G);
            double clusterCoeff = SmallWorld.clusteringCoefficient(G);

            totalAvgDegreeRing += avgDegree;
            totalAvgPathLengthRing += avgPathLength;
            totalClusterCoeffRing += clusterCoeff;

            if (i % 100 == 0) {
                StdOut.println("Processed " + (i + 1) + " 2-ring graphs");
            }
        }

        // Compute the averages for random connected graphs
        double avgDegreeConnected = totalAvgDegreeConnected / graphCount;
        double avgPathLengthConnected = totalAvgPathLengthConnected / graphCount;
        double avgClusterCoeffConnected = totalClusterCoeffConnected / graphCount;

        // Compute the averages for 2-ring graphs with shortcuts
        double avgDegreeRing = totalAvgDegreeRing / graphCount;
        double avgPathLengthRing = totalAvgPathLengthRing / graphCount;
        double avgClusterCoeffRing = totalClusterCoeffRing / graphCount;

        // Print the final results
        StdOut.println("\nAverage Metrics for Random Connected Graphs:");
        StdOut.printf("Average Degree: %.3f\n", avgDegreeConnected);
        StdOut.printf("Average Path Length: %.3f\n", avgPathLengthConnected);
        StdOut.printf("Clustering Coefficient: %.3f\n", avgClusterCoeffConnected);

        StdOut.println("\nAverage Metrics for 2-Ring Graphs with Random Shortcuts:");
        StdOut.printf("Average Degree: %.3f\n", avgDegreeRing);
        StdOut.printf("Average Path Length: %.3f\n", avgPathLengthRing);
        StdOut.printf("Clustering Coefficient: %.3f\n", avgClusterCoeffRing);
    }

    public static void main(String[] args) {
        Graph<String> randomConnectedGraph = generateGridGraph(10);
        randomConnectedGraph.drawGridGraph();
    }
}
