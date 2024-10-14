/*
 * In the Tokyo subway system, routes are labeled by letters
and stops by numbers, such as G-8 or A-3. Stations allowing transfers are sets of
stops. Find a Tokyo subway map on the web, develop a simple ﬁle format, and
write a Graph client that reads a ﬁle and can answer shortest-path queries for the
Tokyo subway system. If you prefer, do the Paris subway system, where routes are
sequences of names and transfers are possible when two stations have the same
name.
 */


 import lib.Graph;
 import lib.In;
 import lib.ST;
 import java.util.*;
 
 public class SubwayGraph {
     private static final int INFINITY = Integer.MAX_VALUE;
     
     private final Graph<Integer> subwayGraph;
     private final ST<String, Integer> stationToIndex;
     private final ST<Integer, String> indexToStation;
 
     public SubwayGraph(String fileName) {
         this.stationToIndex = new ST<>();
         this.indexToStation = new ST<>();
         int stationCount = 0;
 
         // Initialize In for reading the file
         In in = new In(fileName);
         
         // First pass: count stations and create mappings
         while (in.hasNextLine()) {
             String line = in.readLine().trim();
             if (line.isEmpty()) continue;
 
             if (!line.startsWith("->")) {
                 String stationId = line.split(" ")[0];
                 if (!stationToIndex.contains(stationId)) {
                     stationToIndex.put(stationId, stationCount);
                     indexToStation.put(stationCount, stationId);
                     stationCount++;
                 }
             }
         }
 
         // Create the graph with the correct number of vertices
         subwayGraph = new Graph<>(stationCount);
 
         // Second pass: add edges
         in = new In(fileName); // Reset the file reader
         String currentStation = null;
         while (in.hasNextLine()) {
             String line = in.readLine().trim();
             if (line.isEmpty()) continue;
 
             if (!line.startsWith("->")) {
                 currentStation = line.split(" ")[0];
             } else {
                 String[] parts = line.split(" ");
                 String targetStation = parts[1];
                 int v = stationToIndex.get(currentStation);
                 int w = stationToIndex.get(targetStation);
                 subwayGraph.addEdge(v, w);
             }
         }
 
         in.close();
     }
 
     public List<String> shortestPath(String startStationId, String endStationId) {
         if (!stationToIndex.contains(startStationId) || !stationToIndex.contains(endStationId)) {
             throw new IllegalArgumentException("Invalid station ID");
         }
 
         int start = stationToIndex.get(startStationId);
         int end = stationToIndex.get(endStationId);
 
         // Array to track distances and previous stations
         int[] distances = new int[subwayGraph.V()];
         int[] previous = new int[subwayGraph.V()];
         boolean[] visited = new boolean[subwayGraph.V()];
 
         // Initialize distances
         Arrays.fill(distances, INFINITY);
         distances[start] = 0;
 
         // Priority queue for managing stations to visit
         PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> distances[a]));
         pq.add(start);
 
         while (!pq.isEmpty()) {
             int current = pq.poll();
             visited[current] = true;
 
             for (int neighbor : subwayGraph.adj(current)) {
                 if (visited[neighbor]) continue;
 
                 int newDist = distances[current] + 1; // Assuming all edges have weight 1
                 if (newDist < distances[neighbor]) {
                     distances[neighbor] = newDist;
                     previous[neighbor] = current;
                     pq.add(neighbor);
                 }
             }
         }
 
         // Build the shortest path
         List<String> path = new LinkedList<>();
         for (int at = end; at != start; at = previous[at]) {
             path.add(indexToStation.get(at));
         }
         path.add(indexToStation.get(start));
         Collections.reverse(path);
 
         return path.size() > 1 ? path : Collections.emptyList(); // Return empty if no path found
     }
 
     public static void main(String[] args) {
         String fileName = "output.txt";
         SubwayGraph subwayGraph = new SubwayGraph(fileName);
 
         String startStation = "A04"; 
         String endStation = "A01"; 
         List<String> shortestPath = subwayGraph.shortestPath(startStation, endStation);
 
         if (!shortestPath.isEmpty()) {
             System.out.println("Shortest path from " + startStation + " to " + endStation + ": " + shortestPath);
         } else {
             System.out.println("No path found from " + startStation + " to " + endStation);
         }
     }
 }
 