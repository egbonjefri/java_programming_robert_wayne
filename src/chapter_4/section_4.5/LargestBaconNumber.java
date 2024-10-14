import lib.StdOut;
import lib.StdIn;
import lib.ST;
import lib.SET;
import lib.ArrayList;
import lib.Queue;

public class LargestBaconNumber {
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

        // Step 3: Find the Bacon number using BFS
        ST<String, Integer> baconNumbers = bfs(START_ACTOR, graph);

        // Step 4: Find the largest finite Bacon number
        int maxBaconNumber = -1;
        ArrayList<String> actorsWithMaxBacon = new ArrayList<>();
        
        for (String actor : baconNumbers.keys()) {
            int number = baconNumbers.get(actor);
            if (number > maxBaconNumber) {
                maxBaconNumber = number;
                actorsWithMaxBacon.clear();
                actorsWithMaxBacon.add(actor);
            } else if (number == maxBaconNumber) {
                actorsWithMaxBacon.add(actor);
            }
        }

        // Output the largest finite Bacon number and actors
        StdOut.println("The largest finite Kevin Bacon number is: " + maxBaconNumber);
        StdOut.println("Actors with this Bacon number:");
        for (String actor : actorsWithMaxBacon) {
            StdOut.println(actor);
        }
    }

    // BFS to find the shortest path from startActor
    private static ST<String, Integer> bfs(String startActor, ST<String, SET<String>> graph) {
        ST<String, Integer> baconNumbers = new ST<>();
        Queue<String> queue = new Queue<>();
        SET<String> visited = new SET<>();

        queue.enqueue(startActor);
        visited.add(startActor);
        baconNumbers.put(startActor, 0);

        while (!queue.isEmpty()) {
            String actor = queue.dequeue();
            int currentBaconNumber = baconNumbers.get(actor);

            for (String neighbor : graph.get(actor)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    baconNumbers.put(neighbor, currentBaconNumber + 1);
                    queue.enqueue(neighbor);
                }
            }
        }

        return baconNumbers;
    }
}
