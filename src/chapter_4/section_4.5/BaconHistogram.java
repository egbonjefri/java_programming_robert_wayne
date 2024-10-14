import lib.StdIn;
import lib.StdDraw;
import lib.ST;
import lib.SET;
import lib.ArrayList;
import lib.Queue;


/*
 * Write a program BaconHistogram that prints a histogram of
Kevin Bacon numbers, indicating how many performers from movies.txt have a
Bacon number of 0, 1, 2, 3, …. Include a category for those who have an inﬁnite
number (not connected at all to Kevin Bacon)
 */
public class BaconHistogram {
    private static final String START_ACTOR = "Bacon, Kevin";
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 600;
    private static final int MARGIN = 50;

    public static void main(String[] args) {
        // Step 1: Read the file and build the graph
        ST<String, ArrayList<String>> moviesToActors = new ST<>();
        ST<String, SET<String>> graph = new ST<>();

        // Parse the file
        while (!StdIn.isEmpty()) {
            String line = StdIn.readLine();
            String[] parts = line.split("/");
            String movie = parts[0].trim();
            ArrayList<String> actors = new ArrayList<>();
            for (int i = 1; i < parts.length; i++) {
                actors.add(parts[i].trim());
                graph.putIfAbsent(parts[i].trim(), new SET<>());
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

        // Step 4: Create histogram
        ST<Integer, Integer> histogram = new ST<>();
        int maxBaconNumber = -1;
        int infiniteCount = 0;

        for (String actor : graph.keys()) {
            if (baconNumbers.contains(actor)) {
                int baconNumber = baconNumbers.get(actor);
                histogram.put(baconNumber, histogram.getOrDefault(baconNumber, 0) + 1);
                if (baconNumber > maxBaconNumber) {
                    maxBaconNumber = baconNumber;
                }
            } else {
                infiniteCount++;
            }
        }

        // Step 5: Draw histogram
        StdDraw.setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        StdDraw.setXscale(0, CANVAS_WIDTH);
        StdDraw.setYscale(0, CANVAS_HEIGHT);

        int maxCount = 0;
        for (int count : histogram.values()) {
            if (count > maxCount) maxCount = count;
        }
        if (infiniteCount > maxCount) maxCount = infiniteCount;

        double barWidth = (CANVAS_WIDTH - 2.0 * MARGIN) / (maxBaconNumber + 2); // +2 for infinite
        
        for (int i = 0; i <= maxBaconNumber; i++) {
            int count = histogram.getOrDefault(i, 0);
            drawBar(i, count, maxCount, barWidth);
        }
        drawBar(maxBaconNumber + 1, infiniteCount, maxCount, barWidth);

        // Draw x-axis labels
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i <= maxBaconNumber; i++) {
            double x = MARGIN + (i + 0.5) * barWidth;
            StdDraw.text(x, MARGIN / 2.0, String.valueOf(i));
        }
        StdDraw.text(MARGIN + ((maxBaconNumber + 1) + 0.5) * barWidth, MARGIN / 2.0, "Inf");

        // Draw title
        StdDraw.text(CANVAS_WIDTH / 2.0, CANVAS_HEIGHT - MARGIN / 2.0, "Kevin Bacon Number Histogram");

        // Draw y-axis label
        StdDraw.text(MARGIN / 2.0, CANVAS_HEIGHT / 2.0, "Number of Actors", 90);
    }

    private static void drawBar(int index, int count, int maxCount, double barWidth) {
        double x = MARGIN + index * barWidth;
        double y = MARGIN;
        double height = (CANVAS_HEIGHT - 2.0 * MARGIN) * count / maxCount;

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledRectangle(x + barWidth / 2, y + height / 2, barWidth / 2, height / 2);
        
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(x + barWidth / 2, y + height + MARGIN / 4.0, String.valueOf(count));
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