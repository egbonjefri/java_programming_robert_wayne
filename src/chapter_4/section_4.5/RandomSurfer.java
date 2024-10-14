import lib.MultiDigraph;
import lib.ST;
import lib.StdRandom;
import lib.ArrayList;
import lib.StdOut;


public class RandomSurfer <T extends Comparable<T>> {
    private MultiDigraph<T> graph;
    private ST<T, Integer> visitCounts;  // Track the number of times each vertex is visited

    public RandomSurfer(MultiDigraph<T> graph) {
        this.graph = graph;
        this.visitCounts = new ST<>();
    }

    // Initialize the visit counts for each vertex in the graph
    private void initializeVisitCounts() {
        for (T vertex : graph.outgoingEdges.keys()) {
            visitCounts.put(vertex, 0);
        }
    }

    // Perform the random surfer simulation for a given number of steps
    public void simulate(int steps, T startVertex) {
        initializeVisitCounts();
        T currentVertex = startVertex;

        for (int i = 0; i < steps; i++) {
            visitCounts.put(currentVertex, visitCounts.get(currentVertex) + 1);
            ArrayList<T> neighbors = graph.outgoingFrom(currentVertex);

            if (neighbors.isEmpty()) {
                break;  // no outgoing edges, stop
            }

            // Move to a random neighbor
            currentVertex = neighbors.get(StdRandom.uniformInt(neighbors.size()));
        }
    }

    // Return the number of times each vertex was visited
    public ST<T, Integer> getVisitCounts() {
        return visitCounts;
    }

    // Display the visit counts for each vertex
    public void printVisitCounts() {
        for (T entry : visitCounts.keys()) {
            StdOut.println(entry + " visited " + visitCounts.get(entry) + " times");
        }
    }

    // Reset the simulation
    public void reset() {
        visitCounts.clear();
    }
}
