package lib;
/*
 * Modify your Digraph class from the previous exercise
to make a MultiDigraph class that allows parallel edges. For a test client, run a
random- surfer simulation that matches RandomSurfer (
 */
public class MultiDigraph <T extends Comparable<T>> {
    public ST<T, ArrayList<T>> outgoingEdges;  // Maps a vertex to the list of vertices it has directed edges to (allows parallel edges)
    public ST<T, ArrayList<T>> incomingEdges;  // Maps a vertex to the list of vertices that have directed edges to it
    private int V;                          // number of vertices
    private int E;                          // number of edges

    // Constructor to initialize the directed graph with no vertices or edges
    public MultiDigraph() {
        outgoingEdges = new ST<>();
        incomingEdges = new ST<>();
        this.V = 0;
        this.E = 0;
    }

    // Add a vertex to the graph if it doesn't already exist
    public void addVertex(T v) {
        if (!outgoingEdges.contains(v)) {
            outgoingEdges.put(v, new ArrayList<>());
            incomingEdges.put(v, new ArrayList<>());
            V++;  // increment vertex count
        }
    }

    // Add a directed edge from vertex v to vertex w (allows parallel edges)
    public void addEdge(T v, T w) {
        addVertex(v); // Ensure both vertices exist
        addVertex(w);
        
        outgoingEdges.get(v).add(w);  // Add w to the outgoing edges of v (allows multiple occurrences)
        incomingEdges.get(w).add(v);  // Add v to the incoming edges of w
        E++;  // increment edge count
    }

    // Return the list of vertices that have edges directed from the given vertex v (outgoing edges)
    public ArrayList<T> outgoingFrom(T v) {
        if (!outgoingEdges.contains(v)) return new ArrayList<>(); // vertex doesn't exist
        return outgoingEdges.get(v);  // Return the list of vertices v has outgoing edges to
    }

    // Return the list of vertices that have edges directed to the given vertex v (incoming edges)
    public ArrayList<T> incomingTo(T v) {
        if (!incomingEdges.contains(v)) return new ArrayList<>(); // vertex doesn't exist
        return incomingEdges.get(v);  // Return the list of vertices that have edges directed to v
    }

    // Return the number of vertices in the graph
    public int vertexCount() {
        return V;
    }

    // Return the number of edges in the graph
    public int edgeCount() {
        return E;
    }

    // Return a string representation of the multi-digraph
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(V + " vertices, " + E + " edges\n");
        for (T v : outgoingEdges.keys()) {
            sb.append(v + ": ");
            for (T w : outgoingEdges.get(v)) {
                sb.append(w + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
