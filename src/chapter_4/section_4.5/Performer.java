/******************************************************************************
 *  Compilation:  javac SmallWorld.java
 *  Execution:    java SmallWorld filename delimiter
 *  Dependencies: Graph.java PathFinder.java StdOut.java In.java
 *  Data files:   https://introcs.cs.princeton.edu/java/45graph/tinyMovies.txt
 *                https://introcs.cs.princeton.edu/java/45graph/moviesG.txt
 *
 *  % java Performer tinyMovies.txt "/"
 *  number of vertices     =       5
 *  average degree         =   2.800
 *  average path length    =   1.300
 *  clustering coefficient =   0.767
 *
 *  % java Performer moviesG.txt "/"
 *  [ after a long time ]
 *  number of vertices     =   19044
 *  average degree         = 148.688
 *  average path length    =   3.494
 *  clustering coefficient =   0.911
 *
 * This program is a SmallWorld client takes the name of a movie-cast file and a delimiter as
command-line arguments and creates the associated performer–performer graph. It prints to
standard output the number of vertices, the average degree, the average path length, and the
clustering coefficient of this graph. It assumes that the performer–performer graph is connected
(see EXERCISE 4.5.29) so that the average page length is defined.
 ******************************************************************************/
import lib.SmallWorld;
import lib.Graph;
import lib.In;
import lib.StdOut;
/*
 * As mentioned in the text, an alternative way to
compute Kevin Bacon numbers is to build a graph where there is a vertex for each
performer (but not for each movie), and where two performers are adjacent if they
appear in a movie together (see PROGRAM 4.5.6). Calculate Kevin Bacon numbers
by running breadth-ﬁrst search on the performer–performer graph. Compare the
running time with the running time on movies.txt. Explain why this approach
is so much slower. Also explain what you would need to do to include the movies
along the path, as happens automatically with our implementation.
 */
import lib.ST;
import lib.SET;
import lib.Queue;

 public class Performer {
    // BFS to find the shortest path from startActor
    private static ST<String, Integer> bfs(Graph<String> graph, String startActor) {
        ST<String, Integer> baconNumbers = new ST<>();
        Queue<String> queue = new Queue<>();
        SET<String> visited = new SET<>();

        queue.enqueue(startActor);
        visited.add(startActor);
        baconNumbers.put(startActor, 0);

        while (!queue.isEmpty()) {
            String actor = queue.dequeue();
            int currentBaconNumber = baconNumbers.get(actor);

            for (String neighbor : graph.adj(actor)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    baconNumbers.put(neighbor, currentBaconNumber + 1);
                    queue.enqueue(neighbor);
                }
            }
        }

        return baconNumbers;
    }
    public static void main(String[] args) {
        String filename  = args[0];
        String delimiter = args[1];
        String startActor = "Bacon, Kevin"; // Starting point for Bacon numbers
        Graph<String> graph = new Graph<>(String.class);
        In in = new In(filename);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] names = line.split(delimiter);
            for (int i = 1; i < names.length; i++) {
                for (int j = i+1; j < names.length; j++) {
                    graph.addEdge(names[i], names[j]);
                }
            }
        }
        // Perform BFS from Kevin Bacon
        ST<String, Integer> baconNumbers = bfs(graph, startActor);

        // Find and print the maximum Bacon number
        int maxBaconNumber = -1;
        for (String actor : baconNumbers.keys()) {
            int baconNumber = baconNumbers.get(actor);
            if (baconNumber > maxBaconNumber) {
                maxBaconNumber = baconNumber;
            }
        }

        StdOut.println("The largest finite Bacon number is: " + maxBaconNumber);
   
        double degree  = SmallWorld.averageDegree(graph);
        double length  = SmallWorld.averagePathLength(graph);
        double cluster = SmallWorld.clusteringCoefficient(graph);
        StdOut.printf("number of vertices     = %7d\n", graph.V());
        StdOut.printf("average degree         = %7.3f\n", degree);
        StdOut.printf("average path length    = %7.3f\n", length);
        StdOut.printf("clustering coefficient = %7.3f\n", cluster);
    }
}
