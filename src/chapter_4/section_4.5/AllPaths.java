/*
 * Write a Graph client AllPaths whose constructor takes a Graph
as argument and supports operations to count or print all simple paths between
two given vertices s and t in the graph. A simple path is a path that does not repeat
any vertices. In two-dimensional grids, such paths are referred to as self-avoiding
walks (see SECTION 1.4). Enumerating paths is a fundamental problem in statistical
physics and theoretical chemistry—for example, to model the spatial arrangement
of linear polymer molecules in a solution. Warning : There might be exponentially
many paths.
 */
import lib.ArrayList;
import lib.Graph;
import lib.StdOut;

public class AllPaths {
    private boolean[] visited;
    private ArrayList<Integer> path;
    private Graph<Integer> graph;
    private int count;  // to store the number of simple paths

    // Constructor that takes a Graph as an argument
    public AllPaths(Graph<Integer> G) {
        this.graph = G;
        this.visited = new boolean[G.V()];
        this.path = new ArrayList<>();
        this.count = 0;
    }

    // Method to find and print all simple paths from s to t
    public void findAllPaths(int s, int t) {
        visited[s] = true;     // Mark the current vertex as visited
        path.add(s);           // Add current vertex to the path

        // If we have reached the destination vertex, print the path
        if (s == t) {
            printPath();
            count++;          // Increment the count of paths found
        } else {
            // Recur for all adjacent vertices that are not yet visited
            for (int neighbor : graph.adj(s)) {
                if (!visited[neighbor]) {
                    findAllPaths(neighbor, t);
                }
            }
        }

        // Backtrack: Remove current vertex from path and mark it unvisited
        path.remove(path.size() - 1);
        visited[s] = false;
    }

    // Helper method to print the current path
    private void printPath() {
        for (int v : path) {
            StdOut.print(v + " ");
        }
        StdOut.println();
    }

    // Method to get the count of all simple paths from s to t
    public int countPaths(int s, int t) {
        count = 0;
        findAllPaths(s, t);
        return count;
    }
    
    // Main for testing
    public static void main(String[] args) {
        Graph<Integer> G = new Graph<>(5);  // Example graph with 5 vertices
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(1, 3);
        G.addEdge(2, 3);
        G.addEdge(3, 4);

        AllPaths allPaths = new AllPaths(G);
        
        StdOut.println("All paths from 0 to 4:");
        allPaths.findAllPaths(0, 4);  // Print all paths from vertex 0 to vertex 4
        
        StdOut.println("Number of simple paths from 0 to 4: " + allPaths.countPaths(0, 4));
    }
}
