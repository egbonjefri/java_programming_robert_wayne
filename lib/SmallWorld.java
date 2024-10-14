package lib;

/******************************************************************************
 *  Compilation:  javac SmallWorld.java
 *  Execution:    java SmallWorld filename delimiter
 *  Dependencies: Graph.java PathFinder.java StdOut.java In.java
 *  Data files:   https://introcs.cs.princeton.edu/java/45graph/tinyGraph.txt
 *
 *  %  java SmallWorld tinyGraph.txt " "
 *  number of vertices     =       5
 *  number of edges        =       7
 *  average degree         =   2.800
 *  maximum degree         =       4
 *  average degree         =   2.800
 *  average path length    =   1.300
 *  clustering coefficient =   0.767
 *
 ******************************************************************************/

 public class SmallWorld <T extends Comparable<T>> {

    // Compute the average degree of the graph
    public static <V extends Comparable<V>> double averageDegree(Graph<V> G) {
        return (double) 2 * G.E() / G.V();
    }

    // Compute the average path length between all pairs of vertices
    public static <V extends Comparable<V>> double averagePathLength(Graph<V> G) {
        int sum = 0;
        for (V v : G.vertices()) {
            PathFinder<V> pf = new PathFinder<>(G, v);
            for (V w : G.vertices()) {
                sum += pf.distanceTo(w);  // Add distance between vertex v and all other vertices
            }
        }
        return (double) sum / (G.V() * (G.V() - 1));  // Formula: total sum of distances / number of vertex pairs
    }

    // Compute the clustering coefficient for the graph
    public static <V extends Comparable<V>> double clusteringCoefficient(Graph<V> G) {
        double total = 0.0;
        
        // For each vertex in the graph
        for (V v : G.vertices()) {
            int degree = G.degree(v);
            int possible = degree * (degree - 1);  // Possible number of edges between neighbors
            int actual = 0;

            // Count actual edges between neighbors
            for (V u : G.adj(v)) {
                for (V w : G.adj(v)) {
                    if (G.hasEdge(u, w)) {
                        actual++;  // Found an edge between two neighbors
                    }
                }
            }

            // Adjust for double-counting of edges
            actual /= 2;

            // Add local clustering coefficient for vertex v
            if (possible > 0) {
                total += (double) actual / possible;
            } else {
                // If no possible connections, clustering coefficient is 0
                total += 0.0;
            }
        }
        return total / G.V();  // Return the average clustering coefficient for the graph
    }

    // Overloaded method to compute clustering coefficient based on vertices within distance k
    public static <V extends Comparable<V>> double clusteringCoefficient(Graph<V> G, int k) {
        double total = 0.0;

        // For each vertex in the graph
        for (V v : G.vertices()) {
            PathFinder<V> pf = new PathFinder<>(G, v);
            SET<V> neighborsWithinK = new SET<>();

            // Collect all vertices within distance k
            for (V w : G.vertices()) {
                if (pf.distanceTo(w) <= k && !v.equals(w)) {
                    neighborsWithinK.add(w);
                }
            }

            // Calculate possible number of edges between neighbors within distance k
            int possible = neighborsWithinK.size() * (neighborsWithinK.size() - 1);
            int actual = 0;

            // Count actual edges between neighbors
            for (V u : neighborsWithinK) {
                for (V w : neighborsWithinK) {
                    if (G.hasEdge(u, w)) {
                        actual++;
                    }
                }
            }

            // Adjust for double-counting of edges
            actual /= 2;

            // Add local clustering coefficient for vertex v
            if (possible > 0) {
                total += (double) actual / possible;
            } else {
                total += 0.0;  // If no possible connections, clustering coefficient is 0
            }
        }

        return total / G.V();  // Return the average clustering coefficient
    }

    // Return the maximum degree of any vertex
    public static <V extends Comparable<V>> int maxDegree(Graph<V> G) {
        int max = 0;
        for (V v : G.vertices()) {
            if (G.degree(v) > max) {
                max = G.degree(v);  // Track the maximum degree
            }
        }
        return max;
    }
    // Function to determine if the graph exhibits small-world characteristics
    public static <V extends Comparable<V>> boolean isSmallWorld(Graph<V> G) {
        int V = G.V();  // Number of vertices in the graph
        double logV = Math.log(V) / Math.log(2);  // Logarithm base 2 of V
        
        // Check if the graph is sparse: average degree < 20 * log(V)
        double avgDegree = averageDegree(G);
        if (avgDegree >= 20 * logV) {
            return false;
        }

        // Check if the graph has short average path length: avg path length < 10 * log(V)
        double avgPathLength = averagePathLength(G);
        if (avgPathLength >= 10 * logV) {
            return false;
        }

        // Check if the graph is locally clustered: clustering coefficient > 0.1
        double clusterCoeff = clusteringCoefficient(G);
        if (clusterCoeff <= 0.1) {
            return false;
        }

        // If all conditions are satisfied, the graph exhibits the small-world phenomenon
        return true;
    }

        /**
     * Compute the global clustering coefficient of the graph.
     * This is the ratio of the number of closed triplets (or 3 x triangles) 
     * to the total number of triplets (both open and closed) in the graph.
     *
     * @param G the input graph
     * @return the global clustering coefficient
     */
    public static <V extends Comparable<V>> double globalClusteringCoefficient(Graph<V> G) {
        int closedTriplets = 0;
        int totalTriplets = 0;

        for (V v : G.vertices()) {
            // Get all neighbors of v
            SET<V> neighbors = new SET<>();
            for (V w : G.adj(v)) {
                neighbors.add(w);
            }

            // Count the number of edges between neighbors
            for (V u : neighbors) {
                for (V w : neighbors) {
                    if (u.compareTo(w) < 0) {  // Avoid counting the same pair twice
                        totalTriplets++;
                        if (G.hasEdge(u, w)) {
                            closedTriplets++;
                        }
                    }
                }
            }
        }

        // The total number of triplets is counted three times (once for each vertex in the triplet)
        closedTriplets /= 3;

        // Avoid division by zero
        if (totalTriplets == 0) {
            return 0.0;
        }

        return (double) closedTriplets / totalTriplets;
    }
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Usage: java SmallWorld <filename> <delimiter>");
        }

        String filename = args[0];
        String delimiter = args[1];
        try {
            Graph<String> graph = new Graph<>(filename, delimiter, String.class);

            // Output graph statistics
            StdOut.printf("number of vertices     = %7d\n", graph.V());
            StdOut.printf("number of edges        = %7d\n", graph.E());
            StdOut.printf("average degree         = %7.3f\n", averageDegree(graph));
            StdOut.printf("maximum degree         = %7d\n", maxDegree(graph));
            StdOut.printf("average path length    = %7.3f\n", averagePathLength(graph));
            StdOut.printf("clustering coefficient = %7.3f\n", clusteringCoefficient(graph));

            // Check if the graph is a small-world
            if (isSmallWorld(graph)) {
                StdOut.println("This graph exhibits the small-world phenomenon.");
            } else {
                StdOut.println("This graph does not exhibit the small-world phenomenon.");
            }

        } catch (Exception e) {
            System.err.println("Error reading graph: " + e.getMessage());
        }
    }

}