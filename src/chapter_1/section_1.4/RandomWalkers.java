

import lib.StdOut;
import lib.StdRandom;



public class RandomWalkers {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);  // Grid size
        int numWalkers = n;  // Number of walkers

        // Initialize the grid
        boolean[][] visited = new boolean[n][n];
        int unvisitedCells = n * n;
        
        // Start positions of walkers (center of the grid)
        int[][] positions = new int[numWalkers][2];
        for (int i = 0; i < numWalkers; i++) {
            positions[i][0] = n / 2;
            positions[i][1] = n / 2;
        }

        int steps = 0;

        while (unvisitedCells > 0) {
            for (int i = 0; i < numWalkers; i++) {
                // Random move
                int direction = StdRandom.uniformInt(4);
                switch (direction) {
                    case 0: positions[i][0]--; break; // Left
                    case 1: positions[i][0]++; break; // Right
                    case 2: positions[i][1]--; break; // Up
                    case 3: positions[i][1]++; break; // Down
                }

                // Ensure walkers stay within the grid
                positions[i][0] = (positions[i][0] + n) % n;
                positions[i][1] = (positions[i][1] + n) % n;

                // Mark cell as visited
                if (!visited[positions[i][0]][positions[i][1]]) {
                    visited[positions[i][0]][positions[i][1]] = true;
                    unvisitedCells--;
                }
            }
            steps++;
        }

        StdOut.println("Steps taken: " + steps);
    }
}
