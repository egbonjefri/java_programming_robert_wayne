/*
 * Suppose that a terrain is represented by a two-dimen-
sional grid of elevation values (in meters). A peak is a grid point whose four neigh-
boring cells (left, right, up, and down) have strictly lower elevation values. Write a
program Peaks that reads a terrain from standard input and then computes and
prints the number of peaks in the terrain.
 */
import lib.StdIn;
import lib.StdOut;

public class Peaks {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Peaks <rows> <cols>");
            return;
        }

        int rows = Integer.parseInt(args[0]);
        int cols = Integer.parseInt(args[1]);
        int[][] terrain = readTerrain(rows, cols);
        int numPeaks = countPeaks(terrain);
        StdOut.println("Number of peaks: " + numPeaks);
    }

    private static int[][] readTerrain(int rows, int columns) {

        // Create a 2D array to represent the terrain
        int[][] terrain = new int[rows][columns];

        // Read elevation values from standard input
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                terrain[i][j] = StdIn.readInt();
            }
        }

        return terrain;
    }

    private static int countPeaks(int[][] terrain) {
        int numPeaks = 0;
        int rows = terrain.length;
        int cols = terrain[0].length;

        // Check each grid point for being a peak
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (isPeak(terrain, i, j)) {
                    numPeaks++;
                }
            }
        }

        return numPeaks;
    }

    private static boolean isPeak(int[][] terrain, int i, int j) {
        int elevation = terrain[i][j];
        return elevation > terrain[i - 1][j] &&
               elevation > terrain[i + 1][j] &&
               elevation > terrain[i][j - 1] &&
               elevation > terrain[i][j + 1];
    }
}
