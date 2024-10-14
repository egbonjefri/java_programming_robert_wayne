package lib;

import java.util.Arrays;

public class GraphStatisticsSampler {
    private int[][] adjacencyMatrix;
    private int numVertices;

    public GraphStatisticsSampler(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.numVertices = adjacencyMatrix.length;
    }

    public double estimateAveragePathLength(int numTrials) {
        double totalPathLength = 0;
        for (int i = 0; i < numTrials; i++) {
            int start = StdRandom.uniformInt(numVertices);
            int end = StdRandom.uniformInt(numVertices);
            totalPathLength += shortestPath(start, end);
        }
        return totalPathLength / numTrials;
    }

    private int shortestPath(int start, int end) {
        boolean[] visited = new boolean[numVertices];
        Queue<Integer> queue = new Queue<>();
        int[] distance = new int[numVertices];
        Arrays.fill(distance, -1);

        queue.enqueue(start);
        visited[start] = true;
        distance[start] = 0;

        while (!queue.isEmpty()) {
            int current = queue.dequeue();
            if (current == end) {
                return distance[current];
            }
            for (int neighbor = 0; neighbor < numVertices; neighbor++) {
                if (adjacencyMatrix[current][neighbor] == 1 && !visited[neighbor]) {
                    queue.enqueue(neighbor);
                    visited[neighbor] = true;
                    distance[neighbor] = distance[current] + 1;
                }
            }
        }
        return -1; // No path found
    }

    public double estimateClusteringCoefficient(int numTrials) {
        double totalCoefficient = 0;
        for (int i = 0; i < numTrials; i++) {
            int vertex = StdRandom.uniformInt(numVertices);
            totalCoefficient += calculateClusteringCoefficient(vertex);
        }
        return totalCoefficient / numTrials;
    }

    private double calculateClusteringCoefficient(int vertex) {
        ArrayList<Integer> neighbors = getNeighbors(vertex);
        int possibleConnections = neighbors.size() * (neighbors.size() - 1) / 2;
        if (possibleConnections == 0) {
            return 0;
        }

        int actualConnections = 0;
        for (int i = 0; i < neighbors.size(); i++) {
            for (int j = i + 1; j < neighbors.size(); j++) {
                if (adjacencyMatrix[neighbors.get(i)][neighbors.get(j)] == 1) {
                    actualConnections++;
                }
            }
        }

        return (double) actualConnections / possibleConnections;
    }

    private ArrayList<Integer> getNeighbors(int vertex) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            if (adjacencyMatrix[vertex][i] == 1) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    public static void main(String[] args) {
        // Example usage
        int[][] adjacencyMatrix = {
            {0, 1, 1, 0},
            {1, 0, 1, 1},
            {1, 1, 0, 1},
            {0, 1, 1, 0}
        };

        GraphStatisticsSampler sampler = new GraphStatisticsSampler(adjacencyMatrix);
        
        int numTrials = 1000;
        double avgPathLength = sampler.estimateAveragePathLength(numTrials);
        double clusteringCoeff = sampler.estimateClusteringCoefficient(numTrials);

        StdOut.println("Estimated average path length: " + avgPathLength);
        StdOut.println("Estimated clustering coefficient: " + clusteringCoeff);
    }
}