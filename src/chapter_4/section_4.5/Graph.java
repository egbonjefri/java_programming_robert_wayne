import lib.ST;
import lib.Queue;
import lib.SET;

public class Graph<T extends Comparable<T>> {
    private static final String NEWLINE = System.getProperty("line.separator");

    private int V;  // Number of vertices
    private int E;  // Number of edges
    private Queue<T>[] adj;  // Adjacency lists, using queues to allow parallel edges and self-loops
    private ST<T, Integer> vertexMap;  // Map from vertex to index
    private ST<Integer, T> reverseMap;  // Map from index to vertex
    private int vertexCounter;  // Used to assign indices to new vertices
    private Class<T> type;  // Class type for vertex parsing

    @SuppressWarnings("unchecked")
    public Graph(int initialCapacity, Class<T> type) {
        this.V = 0;  // No vertices initially
        this.E = 0;
        this.adj = (Queue<T>[]) new Queue[initialCapacity];  // Initial capacity
        this.vertexMap = new ST<>();
        this.reverseMap = new ST<>();
        this.vertexCounter = 0;
        this.type = type;  // Store the type of vertex (e.g., String, Integer)
    }

    /**
     * Adds a new vertex to the graph.
     */
    public void addVertex(T vertex) {
        if (vertexMap.contains(vertex)) return;  // Vertex already exists

        adj[vertexCounter] = new Queue<>();  // Initialize the queue for adjacency list
        vertexMap.put(vertex, vertexCounter);  // Map the vertex to an index
        reverseMap.put(vertexCounter, vertex);  // Reverse map index to vertex
        vertexCounter++;
        V++;  // Increment vertex count
    }

    /**
     * Adds an edge between two vertices v and w, allowing self-loops and parallel edges.
     */
    public void addEdge(T v, T w) {
        if (!vertexMap.contains(v)) addVertex(v);  // Add vertex v if not present
        if (!vertexMap.contains(w)) addVertex(w);  // Add vertex w if not present

        int vIndex = vertexMap.get(v);
        int wIndex = vertexMap.get(w);

        adj[vIndex].enqueue(w);  // Add w to v's adjacency list (allowing self-loops and parallel edges)
        adj[wIndex].enqueue(v);  // Add v to w's adjacency list (for undirected graphs)
        E++;  // Increment edge count
    }
    /**
     * Removes the edge between two vertices v and w, if present.
     */
    public void remove(T v, T w) {
        if (!vertexMap.contains(v) || !vertexMap.contains(w)) {
            throw new IllegalArgumentException("One or both vertices not found: " + v + ", " + w);
        }

        int vIndex = vertexMap.get(v);
        int wIndex = vertexMap.get(w);

        // Remove w from v's adjacency list
        if (adj[vIndex].remove(w)) {
            E--;  // Decrease edge count only if the edge was actually removed
        }

        // Remove v from w's adjacency list (since this is an undirected graph)
        if (adj[wIndex].remove(v)) {
            E--;  // Decrease edge count only if the edge was actually removed
        }
    }
    /**
     * Returns true if the graph contains the vertex v.
     */
    public boolean hasVertex(T v) {
        return vertexMap.contains(v);
    }

    /**
     * Returns the vertices adjacent to vertex v.
     */
    public Iterable<T> adj(T v) {
        if (!vertexMap.contains(v)) throw new IllegalArgumentException("Vertex not found: " + v);
        return adj[vertexMap.get(v)];
    }

    /**
     * Returns the number of vertices in the graph.
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in the graph.
     */
    public int E() {
        return E;
    }
   /**
     * Returns the induced subgraph containing only the specified vertices and the edges between them.
     * 
     * @param vertexSet the set of vertices to include in the subgraph
     * @return the induced subgraph
     */
    @SuppressWarnings("unchecked")
    public Graph<T> subgraph(SET<T> vertexSet) {
        // Create a new graph with the same type parameter T
        Graph<T> subgraph = new Graph<>(vertexSet.size(), (Class<T>) vertexSet.iterator().next().getClass());

        // Add vertices from the vertexSet to the new subgraph
        for (T vertex : vertexSet) {
            if (vertexMap.contains(vertex)) {
                subgraph.addVertex(vertex);
            }
        }

        // Add edges between vertices that exist in the vertexSet
        for (T v : vertexSet) {
            if (vertexMap.contains(v)) {
                for (T w : adj(v)) {
                    if (vertexSet.contains(w)) {
                        subgraph.addEdge(v, w);  // Add the edge only if both vertices are in the set
                    }
                }
            }
        }

        return subgraph;
    }
    /**
     * Returns a string representation of the graph.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < vertexCounter; v++) {
            T vertex = reverseMap.get(v);
            s.append(vertex + ": ");
            for (T w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    // Main method for testing the graph
    public static void main(String[] args) {
        Graph<String> G = new Graph<>(5, String.class);
        G.addVertex("A");
        G.addVertex("B");
        G.addVertex("C");

        // Adding edges, including self-loops and parallel edges
        G.addEdge("A", "B");
        G.addEdge("A", "C");
        G.addEdge("B", "C");
        G.addEdge("A", "A");  // Self-loop
        G.addEdge("B", "B");  // Self-loop
        G.addEdge("A", "B");  // Parallel edge

        System.out.println(G);
    }
}
