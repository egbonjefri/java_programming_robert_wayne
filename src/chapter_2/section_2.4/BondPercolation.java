
import lib.StdDraw;
import lib.Percolates;

public class BondPercolation {

    // Entry method to fill the grid based on the isOpen status
    public static boolean[][] flow(boolean[][] isOpen) {
        if (isOpen == null || isOpen.length == 0) return new boolean[0][0];

        int n = isOpen.length;
        boolean[][] isFull = new boolean[n][];
        // Initialize isFull array with appropriate column sizes
        for (int i = 0; i < n; i++) {
            if (isOpen[i] != null) {
                isFull[i] = new boolean[isOpen[i].length];
            }
        }

        // Fill every site reachable from the top row.
        for (int j = 0; j < isOpen[0].length; j++) {
            dfs(0, j, isFull, isOpen);
        }
        return isFull;
    }

    // Depth-first search to find full path
    public static void dfs(int row, int col, boolean[][] visited, boolean[][] isOpen) {
        int n = isOpen.length;
        
        // Check and apply normalization only at the edges
        if (col == -1) {
            col = n - 1; // Wrap from left edge to right edge
        } else if (col == n) {
            col = 0; // Wrap from right edge to left edge
        }

        // Base case check after normalization
        if (!validate(row, col, isOpen, visited)) {
            return;
        }

        visited[row][col] = true;
        Percolates.visualizeGrid(isOpen, visited); 
        StdDraw.show();
        StdDraw.pause(1000); 
        // Explore adjacent cells, including wrapping edges
        dfs(row + 1, col, visited, isOpen); // Down
        dfs(row - 1, col, visited, isOpen); // Up
        dfs(row, col + 1, visited, isOpen); // Right (with wrapping)
        dfs(row, col - 1, visited, isOpen); // Left (with wrapping)
    }

    // Validate that (row, col) is a valid index and site can be visited
    public static boolean validate(int row, int col, boolean[][] grid, boolean[][] isFull) {
        // Base case for invalid row
        if (row < 0 || row >= grid.length) return false;

        // ensure row is not null and check for invalid column
        if (grid[row] == null || col < 0 || col >= grid[row].length) return false;

        // base cases for closed or already full sites
        return grid[row][col] && !isFull[row][col];
    }

    public static void main(String[] args) {
      //  boolean[][] isOpen = Percolates.generateRandomGrid(5, 5, 0.5);
      //  boolean[][] isFull = flow(isOpen);
    }
}
