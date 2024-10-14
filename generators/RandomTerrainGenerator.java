package generators;

import lib.StdOut;
import lib.StdRandom;


public class RandomTerrainGenerator {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java RandomTerrainGenerator <rows> <cols>");
            return;
        }

        int rows = Integer.parseInt(args[0]);
        int cols = Integer.parseInt(args[1]);

        int[][] terrain = generateRandomTerrain(rows, cols);
        printTerrain(terrain);
    }

    private static int[][] generateRandomTerrain(int rows, int cols) {
        int[][] terrain = new int[rows][cols];

        // Generate random elevation values for the terrain
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                terrain[i][j] = StdRandom.uniformInt(100); // Adjust the range based on your needs
            }
        }

        return terrain;
    }

    private static void printTerrain(int[][] terrain) {
        // Print the terrain with elevation values
        for (int i = 0; i < terrain.length; i++) {
            for (int j = 0; j < terrain[i].length; j++) {
                StdOut.print(terrain[i][j] + " ");
            }
            StdOut.println();
        }
    }
}
