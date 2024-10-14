package lib;



import java.util.NoSuchElementException;

/******************************************************************************
 *  Compilation:  javac Graph.java
 *  Execution:    java Graph input.txt
 *  Dependencies: Bag.java Stack.java In.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/41graph/tinyG.txt
 *                https://algs4.cs.princeton.edu/41graph/mediumG.txt
 *                https://algs4.cs.princeton.edu/41graph/largeG.txt
 *
 *  A graph, implemented using an array of sets.
 *  Parallel edges and self-loops allowed.
 *
 *  % java Graph tinyG.txt
 *  13 vertices, 13 edges
 *  0: 6 2 1 5
 *  1: 0
 *  2: 0
 *  3: 5 4
 *  4: 5 6 3
 *  5: 3 4 0
 *  6: 0 4
 *  7: 8
 *  8: 7
 *  9: 11 10 12
 *  10: 9
 *  11: 9 12
 *  12: 11 9
 *
 *  % java Graph mediumG.txt
 *  250 vertices, 1273 edges
 *  0: 225 222 211 209 204 202 191 176 163 160 149 114 97 80 68 59 58 49 44 24 15
 *  1: 220 203 200 194 189 164 150 130 107 72
 *  2: 141 110 108 86 79 51 42 18 14
 *  ...
 *
 ******************************************************************************/

 /**
  *  The {@code Graph} class represents an undirected graph of vertices
  *  named 0 through <em>V</em> – 1.
  *  It supports the following two primary operations: add an edge to the graph,
  *  iterate over all of the vertices adjacent to a vertex. It also provides
  *  methods for returning the degree of a vertex, the number of vertices
  *  <em>V</em> in the graph, and the number of edges <em>E</em> in the graph.
  *  Parallel edges and self-loops are permitted.
  *  By convention, a self-loop <em>v</em>-<em>v</em> appears in the
  *  adjacency list of <em>v</em> twice and contributes two to the degree
  *  of <em>v</em>.
  *  <p>
  *  This implementation uses an <em>adjacency-lists representation</em>, which
  *  is a vertex-indexed array of {@link Bag} objects.
  *  It uses &Theta;(<em>E</em> + <em>V</em>) space, where <em>E</em> is
  *  the number of edges and <em>V</em> is the number of vertices.
  *  All instance methods take &Theta;(1) time. (Though, iterating over
  *  the vertices returned by {@link #adj(int)} takes time proportional
  *  to the degree of the vertex.)
  *  Constructing an empty graph with <em>V</em> vertices takes
  *  &Theta;(<em>V</em>) time; constructing a graph with <em>E</em> edges
  *  and <em>V</em> vertices takes &Theta;(<em>E</em> + <em>V</em>) time.
  *  <p>
  *  For additional documentation, see
  *  <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a>
  *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
  *
  *  @author Robert Sedgewick
  *  @author Kevin Wayne
  */


  public class Graph<T extends Comparable<T>> {
      private static final String NEWLINE = System.getProperty("line.separator");
    
      private int V;
      private int E;
      private Bag<T>[] adj;
      private ST<T, Integer> vertexMap;
      public ST<Integer, T> reverseMap;
      private int vertexCounter;  // Counter for unique vertex indexing
      private Class<T> type; // Type to handle Integer or String vertices
    
      @SuppressWarnings("unchecked")
      public Graph(int V, Class<T> type) {
          if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
          this.V = V;
          this.E = 0;
          this.vertexCounter = 0;
          this.adj = (Bag<T>[]) new Bag[V];
          this.vertexMap = new ST<>();
          this.reverseMap = new ST<>();
          this.type = type; // Initialize the type for vertex parsing
          
          for (int v = 0; v < V; v++) {
              adj[v] = new Bag<T>();
          }
      }
      @SuppressWarnings("unchecked")
      public Graph(int V) {
          if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
          this.V = V;
          this.E = 0;
          this.vertexCounter = 0;
          this.adj = (Bag<T>[]) new Bag[V];
          this.vertexMap = new ST<>();
          this.reverseMap = new ST<>();
          
          for (int v = 0; v < V; v++) {
              adj[v] = new Bag<T>();
          }
      }
      /**
       * Default constructor for an empty graph.
       */
      @SuppressWarnings("unchecked")
      public Graph(Class<T> type) {
          this.type = type;
          this.V = 10; // Start with a small default size, or pass it as a parameter
          this.E = 0;
          this.adj = (Bag<T>[]) new Bag[V]; // Initialize the adjacency list array
          this.vertexMap = new ST<>();
          this.reverseMap = new ST<>();
          this.vertexCounter = 0;
      
          // Initialize each Bag in the adj array for V vertices
          for (int i = 0; i < V; i++) {
              adj[i] = new Bag<>();  // Initialize adjacency list for each vertex
          }
      }
      
    
      /**
       * Constructor that loads a graph from a file with a given delimiter.
       */
      @SuppressWarnings("unchecked")
      public Graph(String filename, String delimiter, Class<T> type) {
          In in = new In(filename);
          this.type = type;  // Initialize type for vertex parsing
          try {
              this.V = in.readInt();
              if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
              
              this.adj = (Bag<T>[]) new Bag[V];
              this.vertexMap = new ST<>();
              this.reverseMap = new ST<>();
              this.vertexCounter = 0;
  
              for (int v = 0; v < V; v++) {
                  adj[v] = new Bag<T>();
              }
  
              int E = in.readInt();
              if (E < 0) throw new IllegalArgumentException("Number of edges must be non-negative");
  
              for (int i = 0; i < E; i++) {
                  String line = in.readLine().trim();
                  String[] parts = line.split(delimiter);
                  if (parts.length != 2) {
                      throw new IllegalArgumentException("Invalid edge: each line must contain exactly two vertices");
                  }
  
                  T v = parseVertex(parts[0]);
                  T w = parseVertex(parts[1]);
  
                  addEdge(v, w);
              }
          } catch (NoSuchElementException e) {
              throw new IllegalArgumentException("Invalid input format", e);
          } finally {
              in.close();
          }
      }
    /**
 * Copy constructor. Creates a new graph that is an independent copy of the given graph G.
 */
@SuppressWarnings("unchecked")
public Graph(Graph<T> G) {
    this.V = G.V;  // Copy the number of vertices
    this.E = G.E;  // Copy the number of edges
    this.vertexCounter = G.vertexCounter;  // Copy the vertex counter
    this.type = G.type;  // Copy the vertex type (String, Integer, etc.)

    // Create new instances of the symbol tables and adjacency list
    this.vertexMap = new ST<>();
    this.reverseMap = new ST<>();
    this.adj = (Bag<T>[]) new Bag[V];

    // Copy all vertices from the original graph
    for (T vertex : G.vertexMap.keys()) {
        int index = G.vertexMap.get(vertex);
        this.vertexMap.put(vertex, index);
        this.reverseMap.put(index, vertex);
    }

    // Initialize the adjacency lists and copy edges
    for (int v = 0; v < V; v++) {
        this.adj[v] = new Bag<T>();  // Initialize the new adjacency list
        for (T w : G.adj[v]) {
            this.adj[v].add(w);  // Copy each edge
        }
    }
}

      /**
       * Parse the vertex based on the type.
       */
      @SuppressWarnings("unchecked")
      private T parseVertex(String s) {
          if (type.equals(Integer.class)) {
              return (T) Integer.valueOf(s); // Parse as Integer
          } else if (type.equals(String.class)) {
              return (T) s; // Parse as String
          } else {
              throw new IllegalArgumentException("Unsupported vertex type: " + s);
          }
      }
  
/**
 * Add a new vertex to the graph.
 * @return true if vertex was added, false if it already existed
 */
@SuppressWarnings("unchecked")
private void resizeAdj(int newSize) {
    Bag<T>[] newAdj = (Bag<T>[]) new Bag[newSize];
    for (int i = 0; i < Math.min(vertexCounter, adj.length); i++) {
        newAdj[i] = adj[i];  // Copy over existing adjacency lists
    }
    // Initialize any new positions in the array
    for (int i = adj.length; i < newSize; i++) {
        newAdj[i] = new Bag<>();
    }
    adj = newAdj;
}

public boolean addVertex(T vertex) {
    if (vertex == null) {
        throw new IllegalArgumentException("Vertex cannot be null");
    }
    if (!vertexMap.contains(vertex)) {
        if (vertexCounter >= V) {
            int newSize = Math.max(V * 2, vertexCounter + 1);
            resizeAdj(newSize);  // Resize to at least accommodate the new vertex
            V = newSize;  // Update V to reflect the new capacity
        }
        vertexMap.put(vertex, vertexCounter);
        reverseMap.put(vertexCounter, vertex);
        if (vertexCounter < adj.length) {
            adj[vertexCounter] = new Bag<>();  // Initialize adjacency list for the new vertex
        }
        vertexCounter++;
        return true;
    }
    return false;
}
/**
 * Map a vertex to its index.
 * @throws IllegalArgumentException if vertex is null or not found
 */
public int vertexIndex(T vertex) {
    if (vertex == null) {
        throw new IllegalArgumentException("Vertex cannot be null");
    }
    Integer index = vertexMap.get(vertex);
    if (index == null) {
        throw new IllegalArgumentException("Vertex not found: " + vertex);
    }
    return index;
}

/**
 * Add an edge between two vertices.
 * @throws IllegalArgumentException if either vertex is null
 */
public void addEdge(T v, T w) {
    // Add vertices if they don't exist
    addVertex(v);
    addVertex(w);

    // At this point, we're guaranteed that both vertices exist
    int vIndex = vertexIndex(v);
    int wIndex = vertexIndex(w);

    // Add edges in both directions for undirected graph
    adj[vIndex].add(w);
    adj[wIndex].add(v);
    E++;
}

    /**
     * Adds random long-range edges between random vertices.
     * 
     * @param numEdges The number of long-range edges to add
     * @param random A Random object for generating random numbers
     */
    public void addRandomLongRangeEdges(int numEdges) {
        if (numEdges < 0) {
            throw new IllegalArgumentException("Number of edges must be non-negative");
        }

        int totalVertices = vertexMap.size();
        if (totalVertices < 2) {
            throw new IllegalStateException("Graph must have at least 2 vertices to add long-range edges");
        }

        for (int i = 0; i < numEdges; i++) {
            // Select two random vertices
            T v = getRandomVertex();
            T w = getRandomVertex();

            // Ensure we're not creating a self-loop
            while (v.equals(w)) {
                w = getRandomVertex();
            }

            // Add the edge if it doesn't already exist
            if (!hasEdge(v, w)) {
                addEdge(v, w);
            }
        }
    }

    /**
     * Helper method to get a random vertex from the graph.
     */
    private T getRandomVertex() {
        int randomIndex = StdRandom.uniformInt(vertexMap.size());
        return reverseMap.get(randomIndex);
    }
      /**
       * Return vertices adjacent to vertex `v`.
       */
      public Iterable<T> adj(T v) {
          int vIndex = vertexIndex(v);
          return adj[vIndex];
      }
  
      public int V() {
          return V;
      }
  
      public int E() {
          return E;
      }
  
      public Iterable<T> vertices() {
          return vertexMap.keys();
      }
  
      public boolean hasEdge(T v, T w) {
          validateVertex(v);
          validateVertex(w);
          return adj[vertexIndex(v)].contains(w);
      }
  /**
 * Returns true if the given vertex exists in the graph, false otherwise.
 */
    public boolean hasVertex(T vertex) {
        return vertexMap.contains(vertex);
    }
      private void validateVertex(T v) {
          if (!vertexMap.contains(v)) {
              throw new IllegalArgumentException("Vertex " + v + " does not exist.");
          }
      }
  
      /**
       * Degree of vertex `v`.
       */
      public int degree(T v) {
          validateVertex(v);
          return adj[vertexIndex(v)].size();
      }
  
    /**
     * Check if the graph is bipartite using BFS.
     * 
     * @return true if the graph is bipartite, false otherwise
     */
    public boolean isBipartite() {
        ST<T, Integer> color = new ST<>();  // 0 or 1 to represent two colors

        for (T start : vertices()) {  // For each connected component
            if (!color.contains(start)) {
                Queue<T> queue = new Queue<>();
                queue.enqueue(start);
                color.put(start, 0);  // Start coloring with 0

                while (!queue.isEmpty()) {
                    T v = queue.dequeue();

                    for (T w : adj(v)) {
                        if (!color.contains(w)) {
                            color.put(w, 1 - color.get(v));  // Alternate color
                            queue.enqueue(w);
                        } else if (color.get(w).equals(color.get(v))) {
                            return false;  // Same color for adjacent vertices => not bipartite
                        }
                    }
                }
            }
        }

        return true;  // If we can successfully color the graph with 2 colors
    }
    public void drawGraph() {
        // Setup canvas size
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(-1, 1);
        StdDraw.setYscale(-1, 1);

        // Assign positions to vertices (using a circular layout)
        ST<T, double[]> positions = assignVertexPositions();

        // Draw edges
        for (T v : vertexMap.keys()) {
            double[] posV = positions.get(v);
            int vIndex = vertexMap.get(v);
            for (T w : adj[vIndex]) {
                double[] posW = positions.get(w);
                StdDraw.line(posV[0], posV[1], posW[0], posW[1]);  // Draw edge
            }
        }

        // Draw vertices
        for (T v : positions.keys()) {
            double[] pos = positions.get(v);
            StdDraw.filledCircle(pos[0], pos[1], 0.05);  // Draw vertex as a circle
            StdDraw.text(pos[0], pos[1] + 0.08, v.toString());  // Label the vertex
        }
    }
    public void drawGridGraph() {
        // Setup canvas size
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(-0.1, 1.1);
        StdDraw.setYscale(-0.1, 1.1);
    
        // Determine grid dimensions
        int gridSize = (int) Math.ceil(Math.sqrt(vertexMap.size()));
    
        // Assign positions to vertices (using a grid layout)
        ST<T, double[]> positions = assignGridPositions(gridSize);
    
        // Draw edges
        for (T v : vertexMap.keys()) {
            double[] posV = positions.get(v);
            int vIndex = vertexMap.get(v);
            for (T w : adj[vIndex]) {
                double[] posW = positions.get(w);
                StdDraw.line(posV[0], posV[1], posW[0], posW[1]);  // Draw edge
            }
        }
    
        // Draw vertices
        for (T v : positions.keys()) {
            double[] pos = positions.get(v);
            StdDraw.filledCircle(pos[0], pos[1], 0.02);  // Draw vertex as a circle
            StdDraw.text(pos[0], pos[1] + 0.04, v.toString());  // Label the vertex
        }
    }
    
    private ST<T, double[]> assignGridPositions(int gridSize) {
        ST<T, double[]> positions = new ST<>();
        int row = 0, col = 0;
        for (T v : vertexMap.keys()) {
            double x = (double) col / (gridSize - 1);
            double y = 1 - (double) row / (gridSize - 1);  // Invert y to start from top
            positions.put(v, new double[]{x, y});
            col++;
            if (col == gridSize) {
                col = 0;
                row++;
            }
        }
        return positions;
    }


    /**
     * Assigns positions to each vertex on a circle.
     *
     * @return A map from each vertex to its (x, y) position.
     */
    private ST<T, double[]> assignVertexPositions() {
        ST<T, double[]> positions = new ST<>();
        int N = vertexMap.size();  // Number of vertices
        int i = 0;

        // Arrange vertices in a circle
        for (T vertex : vertexMap.keys()) {
            double angle = 2 * Math.PI * i / N;  // Evenly space the vertices
            double x = 0.7 * Math.cos(angle);  // Scale radius to fit canvas
            double y = 0.7 * Math.sin(angle);
            positions.put(vertex, new double[]{x, y});
            i++;
        }
        return positions;
    }

      /**
       * String representation of the graph.
       */
      public String toString() {
          StringBuilder s = new StringBuilder();
          s.append(V + " vertices, " + E + " edges " + NEWLINE);
          for (int v = 0; v < V; v++) {
              T vertex = reverseMap.get(v);
              s.append(vertex + ": ");
              for (T w : adj[v]) {
                  s.append(w + " ");
              }
              s.append(NEWLINE);
          }
          return s.toString();
      }
  

  }
  
 
 