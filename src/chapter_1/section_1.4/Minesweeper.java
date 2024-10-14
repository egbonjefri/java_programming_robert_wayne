
import lib.StdOut;
import lib.StdRandom;
/*
 * Write a program that takes three command-line arguments
m, n, and p and produces an m-by-n boolean array where each element is occupied
with probability p. In the minesweeper game, occupied cells represent bombs and
empty cells represent safe cells. Print out the array using an asterisk for bombs
and a period for safe cells. Then, create an integer two-dimensional array with the
number of neighboring bombs (above, below, left, right, or diagonal).
* * . . .
. . . . .
. * . . .
* * 1 0 0
3 3 2 0 0
1 * 1 0 0
Write your code so that you have as few special cases as possible to deal with, by
using an (m+2)-by-(n+2) boolean array.
 */
public class Minesweeper {
    public static void main(String args[]) {
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        double p = Double.parseDouble(args[2]);
        boolean[][] array1 = new boolean[m][n];
        int[][] array2 = new int[m][n];
    
        // Initialize array1 with p bombs
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (StdRandom.uniformDouble() < p) {
                    array1[i][j] = true;
                }
            }
        }
    
        // Calculate the number of neighboring bombs for each cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Iterate over the rows surrounding the current cell (i, j). 
                /*
                 * Math.max(i - 1, 0) ensures that ii does not go below 0, 
                 * which would be outside the bounds of the array.
                 * For example, if i is 0 (meaning the current cell is in the first row),
                 *  i - 1 would be -1, but Math.max ensures that ii starts from 0
                 */
                for (int ii = Math.max(i - 1, 0); ii <= Math.min(i + 1, m - 1); ii++) {
                    for (int jj = Math.max(j - 1, 0); jj <= Math.min(j + 1, n - 1); jj++) {
                        // Check if cell (ii, jj) has a bomb and is not the current cell (i, j) itself.
                        if (array1[ii][jj] && !(ii == i && jj == j)) {
                            array2[i][j]++;
                        }
                    }
                }
            }
        }
    
        // Print the bomb positions
        StdOut.println("Bomb Positions :");
        for (boolean[] row : array1) {
            for (boolean cell : row) {
                StdOut.print(cell ? "* " : ". ");
            }
            StdOut.println();
        }
    
        // Print the count of neighboring bombs
        StdOut.println("Count of Neighboring Bombs :");
        for (int[] row : array2) {
            for (int cell : row) {
                StdOut.print(cell + " ");
            }
            StdOut.println();
        }
    }
}
