/*
 * A connected component in a graph is a maximal
set of vertices that are mutually connected. Write a Graph client CCFinder that
computes the connected components of a graph. Include a constructor that takes
a Graph as an argument and computes all of the connected components using
breadth-ﬁrst search. Include a method areConnected(v, w) that returns true if
v and w are in the same connected component and false otherwise. Also add a
method components() that returns the number of connected components.
 */

 import lib.Queue;
 import lib.Graph;
 import lib.StdOut;



 public class CCFinder {
    private boolean[] marked; // marked[v] = has vertex v been visited?
    private int[] id;         // id[v] = id of connected component containing v
    private int count;        // number of connected components

    public CCFinder(Graph<Integer> G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        count = 0;
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                bfs(G, v);
                count++;
            }
        }
    }

    private void bfs(Graph<Integer> G, int s) {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        id[s] = count;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    id[w] = count;
                    queue.enqueue(w);
                }
            }
        }
    }

    // Check if vertices v and w are in the same connected component
    public boolean areConnected(int v, int w) {
        return id[v] == id[w];
    }

    // Return the number of connected components
    public int components() {
        return count;
    }

    // Return the component id of vertex v
    public int id(int v) {
        return id[v];
    }

    public class Main {
        public static void main(String[] args) {
            Graph<Integer> G = new Graph<>(6);
            G.addEdge(0, 1);
            G.addEdge(0, 2);
            G.addEdge(3, 4);
    
            CCFinder ccFinder = new CCFinder(G);
            
            StdOut.println(ccFinder.areConnected(0, 1)); // true
            StdOut.println(ccFinder.areConnected(0, 3)); // false
            StdOut.println(ccFinder.components());       // 3
        }
    }
}