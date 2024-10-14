/*
 * Bollobás and Chung proposed a hybrid
model that combines a 2-ring on V vertices (V is even), plus a random matching.
A matching is a graph in which every vertex has degree 1. To generate a random
matching, shufﬂe the V vertices and add an edge between vertex i and vertex i+1
in the shufﬂed order. Determine the degree of each vertex for graphs in this model.
Using SmallWorld, estimate the average path length and local clustering coefﬁcient
for graphs generated according to this model for V = 1,000.
 */
import java.util.*;

public class BollobasChungGraphModel {
    private int V;  // Number of vertices (must be even)
    private List<List<Integer>> adj;  // Adjacency list representation

    public BollobasChungGraphModel(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        // Create the 2-ring structure
        build2Ring();
    }

    // Add edge between vertex v and w
    private void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v);
    }

    // Build the 2-ring structure
    private void build2Ring() {
        for (int i = 0; i < V; i++) {
            int next = (i + 1) % V;
            int prev = (i - 1 + V) % V;
            addEdge(i, next);
            addEdge(i, prev);
        }
    }

    // Perform a random matching on the vertices
    public void addRandomMatching() {
        List<Integer> vertices = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            vertices.add(i);
        }
        // Shuffle the vertices
        Collections.shuffle(vertices);
        // Add edges between consecutive vertices in the shuffled order
        for (int i = 0; i < V; i += 2) {
            int v = vertices.get(i);
            int w = vertices.get(i + 1);
            addEdge(v, w);
        }
    }

    // Print the degree of each vertex
    public void printDegrees() {
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + " has degree: " + adj.get(i).size());
        }
    }

    public static void main(String[] args) {
        int V = 10;  // Number of vertices (must be even)
        BollobasChungGraphModel graph = new BollobasChungGraphModel(V);
        
        // Add random matching
        graph.addRandomMatching();
        
        // Print the degree of each vertex
        graph.printDegrees();
    }
}
