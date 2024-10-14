/*
 * In the classic Erdös–Renyi random
graph model, we build a random graph on V vertices by including each possible
edge with probability p, independently of the other edges. Compose a Graph client
to verify the following properties:
• Connectivity thresholds: If p < 1/V and V is large, then most of the con-
nected components are small, with the largest being logarithmic in size. If
p > 1/V, then there is almost surely a giant component containing almost
all vertices. If p < ln V / V, the graph is disconnected with high probability;
if p > ln V / V, the graph is connected with high probability.
• Distribution of degrees: The distribution of degrees follows a binomial
distribution, centered on the average, so most vertices have similar degrees.
The probability that a vertex is adjacent to k other vertices decreases expo-
nentially in k.
• No hubs: The maximum vertex degree when p is a constant is at most loga-
rithmic in V.
• No local clustering: The cluster coefﬁcient is close to 0 if the graph is sparse
and connected. Random graphs are not small-world graphs.
• Short path lengths: If p > ln V / V, then the diameter of the graph (see
EXERCISE 4.5.40) is logarithmic.
 */
import lib.ST;
import lib.ArrayList;
import lib.StdRandom;
import java.util.Arrays;
import lib.StdOut;
import lib.Queue;

public class ERGraph {
    private final int V; // Number of vertices
    private final double p; // Edge probability
    private final ST<Integer, ArrayList<Integer>> adj; // Adjacency list

    // Constructor for the Erdös–Renyi graph model
    public ERGraph(int V, double p) {
        this.V = V;
        this.p = p;
        this.adj = new ST<>();
        generateGraph();
    }

    // Generate graph according to the Erdös–Renyi model
    private void generateGraph() {
        for (int i = 0; i < V; i++) {
            adj.put(i, new ArrayList<>());
        }
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (StdRandom.uniformDouble() < p) {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }
    }

    // Property 1: Connectivity Thresholds
    public boolean isConnected() {
        // Use BFS/DFS to check if all vertices are reachable
        boolean[] visited = new boolean[V];
        dfs(0, visited);
        for (boolean b : visited) {
            if (!b) return false;
        }
        return true;
    }

    private void dfs(int v, boolean[] visited) {
        visited[v] = true;
        for (int w : adj.get(v)) {
            if (!visited[w]) {
                dfs(w, visited);
            }
        }
    }

    // Property 2: Degree Distribution
    public int[] degreeDistribution() {
        int[] degrees = new int[V];
        for (int v = 0; v < V; v++) {
            degrees[v] = adj.get(v).size();
        }
        return degrees;
    }

    // Property 3: Maximum Degree (no hubs)
    public int maxDegree() {
        return Arrays.stream(degreeDistribution()).max().orElse(0);
    }

    // Property 4: Clustering Coefficient (no local clustering)
    public double clusteringCoefficient() {
        double totalCoefficient = 0;
        for (int v = 0; v < V; v++) {
            ArrayList<Integer> neighbors = adj.get(v);
            int k = neighbors.size();
            if (k < 2) continue; // No clustering for low degree vertices

            int linksBetweenNeighbors = 0;
            for (int i = 0; i < k; i++) {
                for (int j = i + 1; j < k; j++) {
                    if (adj.get(neighbors.get(i)).contains(neighbors.get(j))) {
                        linksBetweenNeighbors++;
                    }
                }
            }
            totalCoefficient += (2.0 * linksBetweenNeighbors) / (k * (k - 1));
        }
        return totalCoefficient / V;
    }

    // Property 5: Diameter (logarithmic path lengths)
    public int diameter() {
        int maxDiameter = 0;
        for (int v = 0; v < V; v++) {
            int[] distances = bfs(v);
            int maxDist = Arrays.stream(distances).max().orElse(0);
            maxDiameter = Math.max(maxDiameter, maxDist);
        }
        return maxDiameter;
    }

    private int[] bfs(int source) {
        int[] dist = new int[V];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(source);
        dist[source] = 0;

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : adj.get(v)) {
                if (dist[w] == -1) {
                    dist[w] = dist[v] + 1;
                    queue.enqueue(w);
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int V = 1000; // Number of vertices
        double p = 2.0 / V; // Edge probability

        ERGraph graph = new ERGraph(V, p);

        // Verify connectivity thresholds
        StdOut.println("Is connected: " + graph.isConnected());

        // Check degree distribution
        StdOut.println("Max degree: " + graph.maxDegree());

        // Clustering coefficient
        StdOut.println("Clustering coefficient: " + graph.clusteringCoefficient());

        // Diameter
        StdOut.println("Graph diameter: " + graph.diameter());
    }
}
