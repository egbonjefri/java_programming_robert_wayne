/*
 * Write a Digraph client TransitiveClosure whose con-
structor takes a Digraph as an argument and whose method isReachable(v, w)
returns true if there exists some directed path from v to w, and false otherwise.
Hint : Run breadth-ﬁrst search from each vertex.
 */
import lib.Digraph;
import lib.Queue;

public class TransitiveClosure {
    private boolean[][] reachable;  // reachable[v][w] = is there a path from v to w?
    private Digraph<Integer> graph;

    // Constructor: Compute the transitive closure of the digraph using BFS
    public TransitiveClosure(Digraph<Integer> G) {
        int V = G.vertexCount();
        reachable = new boolean[V][V];  // Initialize reachability matrix
        this.graph = G;

        // Perform BFS from each vertex to find reachability
        for (int v = 0; v < V; v++) {
            bfs(v);  // Start BFS from each vertex
        }
    }

    // Helper method: Breadth-first search to mark reachable vertices from source vertex v
    private void bfs(int source) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(source);
        reachable[source][source] = true;  // Mark the source as reachable from itself

        while (!queue.isEmpty()) {
            int v = queue.dequeue();  // Dequeue a vertex
            for (int w : graph.outgoingFrom(v)) {  // Explore neighbors
                if (!reachable[source][w]) {  // If w has not been marked reachable from source
                    reachable[source][w] = true;  // Mark reachable
                    queue.enqueue(w);  // Enqueue for further exploration
                }
            }
        }
    }

    // Method: Check if there is a path from vertex v to vertex w
    public boolean isReachable(int v, int w) {
        return reachable[v][w];
    }
}
