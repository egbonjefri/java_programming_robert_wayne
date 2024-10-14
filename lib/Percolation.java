package lib;

public class Percolation {
    private static boolean[][] grid;
    private static int n;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Grid size must be greater than 0");
        }
        this.n = n;
        this.grid = new boolean[n][n];
        // Initially, all sites are closed (false)
    }

    public static boolean isOpen(int row, int col) {
        validateIndices(row, col);
        return grid[row][col];
    }

    public static void open(int row, int col) {
        validateIndices(row, col);
        grid[row][col] = true;
        // Additional logic for union-find operations would go here
    }

    private static void validateIndices(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IndexOutOfBoundsException("Row and column indices must be between 0 and " + (n-1));
        }
    }

    // Check if the system percolates
    public static boolean percolates(boolean[][] isOpen) {
        boolean[][] isFull = flow(isOpen);
        if (isOpen == null || isOpen.length == 0) return false;
        int n = isOpen.length;

        // Check if any site in the last row is full
        if (isOpen[n - 1] != null) {
            for (int j = 0; j < isOpen[n - 1].length; j++) {
                if (isFull[n - 1][j]) {
                    return true;
                }
            }
        }

        return false;
    }
    public static void flow(boolean[][] isOpen, boolean[][] isFull, int i, int j) {
        // base case for invalid row
        if (i < 0 || i >= isOpen.length) return;

        // ensure row is not null and check for invalid column
        if (isOpen[i] == null || j < 0 || j >= isOpen[i].length) return;

        if (!isOpen[i][j] || isFull[i][j]) return; // base cases for closed or already full sites

        isFull[i][j] = true; // mark as full
  
        // Recursive DFS calls for neighboring sites
        flow(isOpen, isFull, i+1, j);   // down
        flow(isOpen, isFull, i, j+1);   // right
        flow(isOpen, isFull, i, j-1);   // left
        flow(isOpen, isFull, i-1, j);   // up
    }
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
            flow(isOpen, isFull, 0, j);
        }
        return isFull;
    }

}