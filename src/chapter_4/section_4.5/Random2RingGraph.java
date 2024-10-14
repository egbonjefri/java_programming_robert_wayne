import lib.StdDraw;
import lib.StdRandom;
import lib.StdOut;
import lib.ST;
import lib.ArrayList;
import java.util.Arrays;

/*
 * Write a program that plots average path length versus the number of ran-
dom edges as random shortcuts are added to a 2-ring graph on 1,000 vertices.
 */
public class Random2RingGraph {

    private int V;
    private ST<Integer, ArrayList<Integer>> adj;

    public Random2RingGraph(int V) {
        this.V = V;
        adj = new ST<>();
        
        // First, initialize ArrayList for each vertex
        for (int v = 0; v < V; v++) {
            adj.put(v, new ArrayList<>());
        }
        
        // Then, add edges
        for (int v = 0; v < V; v++) {
            // Add edges to the two nearest neighbors (2-ring)
            addEdge(v, (v + 1) % V);
            addEdge(v, (v + V - 1) % V);
        }
    }
    // Add an edge between vertices v and w
    private void addEdge(int v, int w) {
        if (!adj.get(v).contains(w)) {
            adj.get(v).add(w);
            adj.get(w).add(v);
        }
    }

    // Calculate the average path length between all pairs of vertices using Floyd-Warshall algorithm:
    public double averagePathLength() {
        int[][] dist = new int[V][V];
        
        // Initialize distances
        for (int i = 0; i < V; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
            dist[i][i] = 0;
            for (int j : adj.get(i)) {
                dist[i][j] = 1;
            }
        }
        
        // Floyd-Warshall algorithm
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        // Calculate average
        long sum = 0;
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                sum += dist[i][j];
            }
        }
        return (double) sum / (V * (V - 1) / 2);
    }

    // Add a random edge between two vertices not already connected
    public void addRandomEdge() {
        while (true) {
            int v = StdRandom.uniformInt(V);
            int w = StdRandom.uniformInt(V);
            if (v != w && !adj.get(v).contains(w)) {
                addEdge(v, w);
                break;
            }
        }
    }

    public static void main(String[] args) {
        int V = 1000; // Number of vertices
        Random2RingGraph graph = new Random2RingGraph(V);

        // Set up the drawing canvas
        StdDraw.setXscale(0, 500);
        StdDraw.setYscale(0, 10);
        StdDraw.setPenRadius(0.01);

        // Plot average path length versus the number of random edges
        for (int edgesAdded = 0; edgesAdded <= 500; edgesAdded++) {
            if (edgesAdded > 0) {
                graph.addRandomEdge();
            }
            double avgPathLength = graph.averagePathLength();
            StdOut.println("Edges added: " + edgesAdded + " - Avg path length: " + avgPathLength);

            // Plot the point
            StdDraw.point(edgesAdded, avgPathLength);
        }
    }
}
