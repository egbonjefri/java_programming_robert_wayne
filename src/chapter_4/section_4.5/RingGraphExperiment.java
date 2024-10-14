import lib.StdDraw;
import lib.StdOut;
import lib.Queue;
import lib.StdRandom;
/*
 * Perform computational experiments to verify that the average path length
in a ring graph on V vertices is ~ 1/4 V. Then, repeat these experiments, but add
one random edge to the ring graph and verify that the average path length decreases
to ~3/16 V.
 */

 /*
  * A ring graph is a simple undirected cycle, 
  where each vertex is connected to exactly two other vertices (its neighbors).
  */


  
  public class RingGraphExperiment {
  
      // Method to compute average path length in a ring graph
      public static double averagePathLength(int V) {
          int totalDistance = 0;
          int pathCount = 0;
  
          for (int s = 0; s < V; s++) {
              // Run BFS from each vertex to calculate shortest paths
              int[] dist = bfs(V, s, null);
              for (int t = 0; t < V; t++) {
                  if (s != t) {
                      totalDistance += dist[t];
                      pathCount++;
                  }
              }
          }
  
          return (double) totalDistance / pathCount;
      }
  
      // Method to compute average path length in a ring graph with one random edge
      public static double averagePathLengthWithRandomEdge(int V) {
          int totalDistance = 0;
          int pathCount = 0;
  
          int[] randomEdge = (V >= 5) ? addRandomEdge(V) : null; // Skip for small graphs
  
          for (int s = 0; s < V; s++) {
              // Run BFS from each vertex to calculate shortest paths
              int[] dist = bfs(V, s, randomEdge);
              for (int t = 0; t < V; t++) {
                  if (s != t) {
                      totalDistance += dist[t];
                      pathCount++;
                  }
              }
          }
  
          return (double) totalDistance / pathCount;
      }
  
      // Perform BFS to compute shortest paths from source vertex
      private static int[] bfs(int V, int source, int[] randomEdge) {
          int[] dist = new int[V];
          boolean[] marked = new boolean[V];
          Queue<Integer> queue = new Queue<>();
  
          for (int i = 0; i < V; i++) {
              dist[i] = Integer.MAX_VALUE;
          }
  
          dist[source] = 0;
          marked[source] = true;
          queue.enqueue(source);
  
          while (!queue.isEmpty()) {
              int v = queue.dequeue();
              for (int neighbor : neighbors(v, V, randomEdge)) {
                  if (!marked[neighbor]) {
                      dist[neighbor] = dist[v] + 1;
                      marked[neighbor] = true;
                      queue.enqueue(neighbor);
                  }
              }
          }
  
          return dist;
      }
  
      // Return the neighbors of vertex v in a ring graph (with possible random edge)
      private static Iterable<Integer> neighbors(int v, int V, int[] randomEdge) {
          Queue<Integer> neighbors = new Queue<>();
          // Ring graph neighbors
          neighbors.enqueue((v - 1 + V) % V); // Previous neighbor
          neighbors.enqueue((v + 1) % V);     // Next neighbor
          // Add random edge if applicable
          if (randomEdge != null && (v == randomEdge[0] || v == randomEdge[1])) {
              neighbors.enqueue(v == randomEdge[0] ? randomEdge[1] : randomEdge[0]);
          }
          return neighbors;
      }
  
      // Add a random edge to the graph (ensure it's not adjacent to existing edges)
      private static int[] addRandomEdge(int V) {
          int u = StdRandom.uniformInt(V);
          int v;
          do {
              v = StdRandom.uniformInt(V);
          } while (v == u || v == (u + 1) % V || v == (u - 1 + V) % V); // Avoid adjacent vertices
          return new int[]{u, v};
      }
  
      public static void main(String[] args) {
          int maxVertices = 100; // Maximum number of vertices to experiment on
  
          // Set up StdDraw for plotting
          StdDraw.setXscale(0, maxVertices / 0.9);
          StdDraw.setYscale(0, maxVertices / 2.5); 
          StdDraw.setPenRadius(0.01);
  
          // Loop through different vertex counts and calculate average path lengths
          for (int V = 3; V <= maxVertices; V++) {
              double avgPathLengthRing = averagePathLength(V);
              double avgPathLengthWithRandomEdge = averagePathLengthWithRandomEdge(V);
              double theoreticalAvgRing = V / 4.0;
              double theoreticalAvgWithRandomEdge = 3.0 * V / 16.0;
  
              StdOut.printf("V = %d, Ring APL = %.2f, Ring+Random APL = %.2f\n", V, avgPathLengthRing, avgPathLengthWithRandomEdge);
              StdOut.printf("Theoretical Ring APL = %.2f, Theoretical Ring+Random APL = %.2f\n", theoreticalAvgRing, theoreticalAvgWithRandomEdge);
  
              // Plot the results
              StdDraw.setPenColor(StdDraw.BLUE);
              StdDraw.point(V, avgPathLengthRing);
              StdDraw.setPenColor(StdDraw.RED);
              StdDraw.point(V, avgPathLengthWithRandomEdge);
              StdDraw.setPenColor(StdDraw.GREEN);
              StdDraw.point(V, theoreticalAvgRing);
              StdDraw.setPenColor(StdDraw.MAGENTA);
              StdDraw.point(V, theoreticalAvgWithRandomEdge);
  
              // Calculate and print deviations
              double deviationRing = Math.abs(avgPathLengthRing - theoreticalAvgRing) / theoreticalAvgRing * 100;
              double deviationRandomEdge = Math.abs(avgPathLengthWithRandomEdge - theoreticalAvgWithRandomEdge) / theoreticalAvgWithRandomEdge * 100;
              StdOut.printf("Ring Deviation: %.2f%%, Ring+Random Deviation: %.2f%%\n\n", deviationRing, deviationRandomEdge);
          }
  
          // Add legend to the plot
          StdDraw.setPenColor(StdDraw.BLACK);
          StdDraw.text(maxVertices / 2, maxVertices / 2.8, "Blue: Ring APL");
          StdDraw.text(maxVertices / 2, maxVertices / 2.9, "Red: Ring+Random APL");
          StdDraw.text(maxVertices / 2, maxVertices / 3.0, "Green: Theoretical Ring APL");
          StdDraw.text(maxVertices / 2, maxVertices / 3.1, "Magenta: Theoretical Ring+Random APL");
      }
  }
  