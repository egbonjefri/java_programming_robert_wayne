import lib.Graph;
import lib.Queue;
/*
 * The eccentricity of a vertex is the greatest distance between it and
any other vertex. The diameter of a graph is the greatest distance between any two
vertices (the maximum eccentricity of any vertex). Write a Graph client Diameter
that can compute the eccentricity of a vertex and the diameter of a graph. Use it to
ﬁnd the diameter of the performer–performer graph associated with movies.txt.
 */

import lib.ST;

public class Diameter {
    private Graph<Integer> graph;  

    public Diameter(Graph<Integer> graph) {
        this.graph = graph;
    }

    // Method to calculate eccentricity of a given vertex
    public int eccentricity(Integer vertex) {
        ST<Integer, Integer> distances = bfs(vertex);  // Perform BFS to get distances
        int maxDistance = 0;
        for (int dist : distances.values()) {
            if (dist > maxDistance) {
                maxDistance = dist;
            }
        }
        return maxDistance;
    }

    // Method to compute the diameter of the graph
    public int diameter() {
        int maxEccentricity = 0;
        for (Integer vertex : graph.vertices()) {  // Iterate over all vertices
            int ecc = eccentricity(vertex);
            if (ecc > maxEccentricity) {
                maxEccentricity = ecc;
            }
        }
        return maxEccentricity;
    }

    // Helper method: Perform BFS and return distances from the source vertex
    private ST<Integer, Integer> bfs(Integer source) {
        ST<Integer, Integer> distTo = new ST<>();
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(source);
        distTo.put(source, 0);  // Distance to source is 0

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            int currentDist = distTo.get(v);

            for (Integer neighbor : graph.adj(v)) {
                if (!distTo.contains(neighbor)) {
                    distTo.put(neighbor, currentDist + 1);
                    queue.enqueue(neighbor);
                }
            }
        }
        return distTo;
    }
}
