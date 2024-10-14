package generators;

import lib.StdRandom;

public class RandomGridGenerator {
          public static boolean[][] generateRandomGrid(int rows, int columns, double openProbability) {
        // Create a new boolean grid of specified size
        boolean[][] grid = new boolean[rows][columns];
      
        // Fill the grid with random boolean values based on open probability
        for (int i = 0; i < rows; i++) {
          for (int j = 0; j < columns; j++) {
            grid[i][j] = StdRandom.bernoulli(openProbability);
          }
        }
      
        // Return the generated random grid
        return grid;
    }
}
