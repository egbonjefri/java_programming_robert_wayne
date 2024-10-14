/******************************************************************************
 *  Compilation:  javac PathFinder.java
 *  Execution:    java Pathfinder input.txt delimiter source
 *  Dependencies: Queue.java Stack.java Graph.java
 *  Data files:   https://introcs.cs.princeton.edu/java/45graph/routes.txt
 *                https://introcs.cs.princeton.edu/java/45graph/movies.txt
 *
 *  Runs breadth first search algorithm from source s on a graph G.
 *  After preprocessing the graph, can process shortest path queries
 *  from s to any vertex t.
 *
 *  % java PathFinder routes.txt " " JFK
 *  LAX
 *     JFK
 *     ORD
 *     PHX
 *     LAX
 *  distance 3
 *  MCO
 *     JFK
 *     MCO
 *  distance 1
 *  DFW
 *     JFK
 *     ORD
 *     DFW
 *  distance 2
 *
 ******************************************************************************/
package lib;

public class PathFinder<V extends Comparable<V>> {

    private ST<V, V> prev = new ST<>();     // prev[v] = previous vertex on shortest path from s to v
    private ST<V, Integer> dist = new ST<>(); // dist[v] = length of shortest path from s to v
    private boolean[] marked;               // to avoid cycles in DFS
    private boolean hasPath;                // flag for DFS path checking

    // Constructor for DFS path existence check between sources and targets
    public PathFinder(Graph<Integer> G, ArrayList<Integer> sources, ArrayList<Integer> targets) {
        marked = new boolean[G.V()];  // initialize marked array for DFS
        for (int s : sources) {
            if (!hasPath) {           // stop after first path found
                dfs(G, s, targets);
            }
        }
    }

    // DFS for checking if there is a path between any of the source vertices and target vertices
    private void dfs(Graph<Integer> G, int v, ArrayList<Integer> targets) {
        marked[v] = true;
        if (targets.contains(v)) {
            hasPath = true;
            return; // stop DFS after finding a target
        }
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w, targets);  // recursive DFS call
            }
        }
    }

    // Check if any path exists between the sources and targets (used in DFS)
    public boolean hasPath() {
        return hasPath;
    }

    // Constructor for BFS shortest path from a single source vertex
    public PathFinder(Graph<V> G, V s) {
        Queue<V> queue = new Queue<>();
        queue.enqueue(s);
        dist.put(s, 0);  // distance to source is 0

        // Perform BFS, exploring all neighbors
        while (!queue.isEmpty()) {
            V v = queue.dequeue();
            for (V w : G.adj(v)) {
                if (!dist.contains(w)) { // if vertex hasn't been visited
                    queue.enqueue(w);
                    dist.put(w, 1 + dist.get(v));  // distance update
                    prev.put(w, v);                // track the previous vertex
                }
            }
        }
    }

    // Check if there is a path to the given vertex in the BFS tree
    public boolean hasPathTo(V v) {
        return dist.contains(v);
    }

    // Get the distance from the source vertex to v (or return max if not reachable)
    public int distanceTo(V v) {
        if (!hasPathTo(v)) return Integer.MAX_VALUE;
        return dist.get(v);
    }

    // Return the shortest path from the source to the given vertex as an Iterable
    public Iterable<V> pathTo(V v) {
        Stack<V> path = new Stack<>();
        while (v != null && dist.contains(v)) {
            path.push(v);
            v = prev.get(v);  // trace back using the prev map
        }
        return path;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String delimiter = args[1];
        Graph<String> G = new Graph<>(filename, delimiter, String.class);
        String s = args[2];
        PathFinder<String> pf = new PathFinder<>(G, s);
        while (!StdIn.isEmpty()) {
            String t = StdIn.readLine();
            for (String v : pf.pathTo(t)) {
                StdOut.println("   " + v);
            }
            StdOut.println("distance " + pf.distanceTo(t));
        }
    }
}