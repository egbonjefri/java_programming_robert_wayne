package lib;


/*
 * Implement a Digraph data type that represents directed
graphs, where the direction of edges is signiﬁcant: addEdge(v, w) means to add
an edge from v to w but not from w to v. Replace adjacentTo() with two methods:
one to give the set of vertices having edges directed to them from the argument
vertex, and the other to give the set of vertices having edges directed from them to
the argument vertex. Explain how PathFinder would need to be modiﬁed to ﬁnd
shortest paths in directed graphs.
 */

public class Digraph<T extends Comparable<T>> {
    private ST<T, SET<T>> outgoingEdges;  // Maps a vertex to the set of vertices it has directed edges to
    private ST<T, SET<T>> incomingEdges;  // Maps a vertex to the set of vertices with edges directed to it
    private int V;                         // number of vertices
    private int E;                         // number of edges

    // Constructor to initialize the directed graph with no vertices or edges
    public Digraph() {
        outgoingEdges = new ST<>();
        incomingEdges = new ST<>();
        this.V = 0;
        this.E = 0;
    }

    // Add a vertex to the graph if it doesn't already exist
    public void addVertex(T v) {
        if (!outgoingEdges.contains(v)) {
            outgoingEdges.put(v, new SET<>());
            incomingEdges.put(v, new SET<>());
            V++;  // increment vertex count
        }
    }

    // Add a directed edge from vertex v to vertex w
    public void addEdge(T v, T w) {
        addVertex(v); // Ensure both vertices exist
        addVertex(w);
        
        if (!outgoingEdges.get(v).contains(w)) {
            outgoingEdges.get(v).add(w);  // Add w to the outgoing edges of v
            incomingEdges.get(w).add(v);  // Add v to the incoming edges of w
            E++;  // increment edge count
        }
    }

    // Return the set of vertices that have edges directed from the given vertex v (outgoing)
    public SET<T> outgoingFrom(T v) {
        if (!outgoingEdges.contains(v)) return new SET<>(); // vertex doesn't exist
        return outgoingEdges.get(v);  // Return the set of vertices v has outgoing edges to
    }

    // Return the set of vertices that have edges directed to the given vertex v (incoming)
    public SET<T> incomingTo(T v) {
        if (!incomingEdges.contains(v)) return new SET<>(); // vertex doesn't exist
        return incomingEdges.get(v);  // Return the set of vertices that have edges directed to v
    }

    // Return the number of vertices in the graph
    public int vertexCount() {
        return V;
    }

    // Return the number of edges in the graph
    public int edgeCount() {
        return E;
    }

    // Return a string representation of the digraph
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





    public static void main(String[] args) {
        Digraph<Integer> digraph = new Digraph<>();
        
        digraph.addEdge(1, 2);
        digraph.addEdge(2, 3);
        digraph.addEdge(1, 3);
        digraph.addEdge(3, 4);

        System.out.println(digraph);  // Print the directed graph

        // Outgoing edges from vertex 1
        System.out.println("Outgoing from 1: " + digraph.outgoingFrom(1));

        // Incoming edges to vertex 3
        System.out.println("Incoming to 3: " + digraph.incomingTo(3));
    }
}
