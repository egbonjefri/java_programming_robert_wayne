import lib.Graph;
import lib.In;
import lib.StdOut;

public class GraphClient {   
    public static void main(String[] args) {
        if (args.length < 1) {
            StdOut.println("Usage: java GraphClient <filename>");
            return;
        }

        String filename = args[0];
        In in = new In(filename);

        // Count the number of vertices
        int V = 0;
        while (in.hasNextLine()) {
            String line = in.readLine().trim();
            if (!line.isEmpty() && Character.isDigit(line.charAt(0))) {
                V++;
            }
        }

        // Reset the file reader
        in = new In(filename);

        // Create a new graph instance
        Graph<Integer> graph = new Graph<>(V, Integer.class);

        // Read edges from the file
        while (in.hasNextLine()) {
            String line = in.readLine().trim();
            if (!line.isEmpty() && Character.isDigit(line.charAt(0))) {
                String[] parts = line.split(":\\s*|\\s+");
                int vertex = Integer.parseInt(parts[0]);
                for (int i = 1; i < parts.length; i++) {
                    int adjVertex = Integer.parseInt(parts[i]);
                    graph.addEdge(vertex, adjVertex);
                }
            }
        }
        // Draw the graph
        graph.drawGraph();
    
        StdOut.println(graph);
    }
    
}
