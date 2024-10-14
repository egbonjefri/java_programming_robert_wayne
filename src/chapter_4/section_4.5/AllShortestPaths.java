/*
 * Implement a PathFinder client AllShortestPaths that creates a Path-
Finder object for each vertex, with a test client that takes from standard input two-
vertex queries and prints the shortest path connecting them. Support a delimiter,
so that you can type the two-string queries on one line (separated by the delimiter)
and get as output a shortest path between them. Note : For movies.txt, the query
strings may both be performers, both be movies, or be a performer and a movie.
 */

import lib.ST;
import lib.StdIn;
import lib.StdOut;
import lib.Graph;
import lib.PathFinder;

public class AllShortestPaths {
       // A map to store PathFinder objects for each vertex
       private ST<String, PathFinder> allPaths = new ST<>();

       // Constructor that precomputes the shortest paths for all vertices in the graph
       public AllShortestPaths(Graph<String> G) {
           for (String v : G.vertices()) {
               allPaths.put(v, new PathFinder(G, v));
           }
       }
   
       // Get the shortest path between two vertices
       public Iterable<String> path(String from, String to) {
           if (!allPaths.contains(from)) return null;
           PathFinder pf = allPaths.get(from);
           if (!pf.hasPathTo(to)) return null;
           return pf.pathTo(to);
       }
   
       // Get the distance between two vertices
       public int distance(String from, String to) {
           if (!allPaths.contains(from)) return -1;
           PathFinder pf = allPaths.get(from);
           return pf.distanceTo(to);
       } 

       public static void main(String[] args) {
        String filename = args[0];
        String delimiter = args[1];
        
        // Create a graph from the input file
        Graph<String> G = new Graph<>(filename, delimiter, String.class);

        // Create an AllShortestPaths object to precompute paths
        AllShortestPaths allShortestPaths = new AllShortestPaths(G);

        // Read two-vertex queries from standard input
        while (!StdIn.isEmpty()) {
            String line = StdIn.readLine();
            String[] vertices = line.split(delimiter);
            if (vertices.length != 2) {
                StdOut.println("Please enter two vertices separated by the delimiter.");
                continue;
            }
            
            String from = vertices[0];
            String to = vertices[1];

            // Get the shortest path and distance
            Iterable<String> path = allShortestPaths.path(from, to);
            int distance = allShortestPaths.distance(from, to);

            if (path == null) {
                StdOut.println("No path found between " + from + " and " + to);
            } else {
                StdOut.println("Shortest path from " + from + " to " + to + ":");
                for (String v : path) {
                    StdOut.println("   " + v);
                }
                StdOut.println("Distance: " + distance);
            }
        }
    }
}
