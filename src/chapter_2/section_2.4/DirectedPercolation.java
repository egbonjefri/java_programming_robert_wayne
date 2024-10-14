public class DirectedPercolation {
    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 0, 1, 0},
                {1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1},
                {1, 0, 0, 1, 1},
                {1, 1, 0, 0, 1},
        };
        
        System.out.println("Percolates: " + testPercolation(grid));
    }
    
    public static boolean testPercolation(int[][] grid) {
        int nRows = grid.length;
        int nCols = grid[0].length;
        
        // Mark the open sites in the top row as full if they are open
        for (int col = 0; col < nCols; col++) {
            if (grid[0][col] == 1) {
                grid[0][col] = 2; // Mark as full
            }
        }
        
        // Iterate from the second row to the last row
        for (int row = 1; row < nRows; row++) {
            for (int col = 0; col < nCols; col++) {
                // Check if current site is open and above site is full
                if (grid[row][col] == 1 && grid[row-1][col] == 2) {
                    fillSubrow(grid, row, col);
                }
            }
        }
        
        // Check for percolation: if any site in the bottom row is full
        for (int col = 0; col < nCols; col++) {
            if (grid[nRows-1][col] == 2) {
                return true; // Percolates
            }
        }
        
        return false; // Does not percolate
    }
    
    // Helper method to fill contiguous open sites in the current row
    private static void fillSubrow(int[][] grid, int row, int startCol) {
        int nCols = grid[row].length;
        
        // Fill to the right
        for (int col = startCol; col < nCols && grid[row][col] == 1; col++) {
            grid[row][col] = 2; // Mark as full
        }
        
        // Optionally, fill to the left from startCol-1 if required by problem definition
        for (int col = startCol - 1; col >= 0 && grid[row][col] == 1; col--) {
            grid[row][col] = 2; // Mark as full
        }
    }
}
