/*
 * We can measure how good a center
Kevin Bacon is by computing each performer’s Hollywood number or average path
length. The Hollywood number of Kevin Bacon is the average Bacon number of all
the performers (in its connected component). The Hollywood number of another
performer is computed the same way, making that performer the source instead of
Kevin Bacon. Compute Kevin Bacon’s Hollywood number and ﬁnd a performer
with a better Hollywood number than Kevin Bacon. Find the performers (in the
same connected component as Kevin Bacon) with the best and worst Hollywood
numbers.
 */

import lib.StdOut;
import lib.StdIn;
import lib.ST;
import lib.SET;
import lib.ArrayList;
import lib.Queue;


public class CenterOfTheHollywoodUniverse {
    private static final String START_ACTOR = "Bacon, Kevin";

    public static void main(String[] args) {
        // Step 1: Read the file and build the graph
        ST<String, ArrayList<String>> moviesToActors = new ST<>();
        ST<String, SET<String>> graph = new ST<>();

        // Parse the file
        while (!StdIn.isEmpty()) {
            String line = StdIn.readLine();
            if (line == null || line.trim().isEmpty()) continue;
            String[] parts = line.split("/");
            String movie = parts[0].trim();
            ArrayList<String> actors = new ArrayList<>();
            
            for (int i = 1; i < parts.length; i++) {
                String actor = parts[i].trim();
                if (!actor.isEmpty()) {
                    actors.add(actor);
                    graph.putIfAbsent(actor, new SET<>());
                }
            }
            moviesToActors.put(movie, actors);
        }

        // Step 2: Build the graph by connecting actors who appeared in the same movie
        for (ArrayList<String> actors : moviesToActors.values()) {
            for (int i = 0; i < actors.size(); i++) {
                for (int j = i + 1; j < actors.size(); j++) {
                    String actor1 = actors.get(i);
                    String actor2 = actors.get(j);
                    graph.get(actor1).add(actor2);
                    graph.get(actor2).add(actor1);
                }
            }
        }

        // Step 3: Compute the Hollywood numbers for each actor in the connected component
        String bestPerformer = null;
        String worstPerformer = null;
        double bestHollywoodNumber = Double.POSITIVE_INFINITY;
        double worstHollywoodNumber = Double.NEGATIVE_INFINITY;

        for (String actor : graph.keys()) {
            double hollywoodNumber = computeHollywoodNumber(actor, graph);
            
            if (hollywoodNumber < bestHollywoodNumber) {
                bestHollywoodNumber = hollywoodNumber;
                bestPerformer = actor;
            }
            if (hollywoodNumber > worstHollywoodNumber) {
                worstHollywoodNumber = hollywoodNumber;
                worstPerformer = actor;
            }
            
            if (actor.equals(START_ACTOR)) {
                StdOut.println("Kevin Bacon's Hollywood number: " + hollywoodNumber);
            }
        }

        // Output performers with best and worst Hollywood numbers
        StdOut.println("Performer with the best Hollywood number: " + bestPerformer + " (" + bestHollywoodNumber + ")");
        StdOut.println("Performer with the worst Hollywood number: " + worstPerformer + " (" + worstHollywoodNumber + ")");
    }

    // Compute Hollywood number (average shortest path length) for a given actor
    private static double computeHollywoodNumber(String actor, ST<String, SET<String>> graph) {
        ST<String, Integer> distances = bfs(actor, graph);
        int totalDistance = 0;
        int count = 0;

        for (String otherActor : distances.keys()) {
            if (!otherActor.equals(actor)) {
                totalDistance += distances.get(otherActor);
                count++;
            }
        }

        // Return average path length
        return (double) totalDistance / count;
    }

    // BFS to find the shortest paths from startActor to all other actors
    private static ST<String, Integer> bfs(String startActor, ST<String, SET<String>> graph) {
        ST<String, Integer> distances = new ST<>();
        Queue<String> queue = new Queue<>();
        SET<String> visited = new SET<>();

        queue.enqueue(startActor);
        visited.add(startActor);
        distances.put(startActor, 0);

        while (!queue.isEmpty()) {
            String actor = queue.dequeue();
            int currentDistance = distances.get(actor);

            for (String neighbor : graph.get(actor)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    distances.put(neighbor, currentDistance + 1);
                    queue.enqueue(neighbor);
                }
            }
        }

        return distances;
    } 
}
