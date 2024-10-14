/*
 * Implements John Horton Conway Game of Life with the following rules:
 * Any live cell with fewer than two live neighbors dies (underpopulation).
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies (overpopulation).
 * Any dead cell with exactly three live neighbors becomes a live cell (reproduction).
 */
import lib.StdDraw;
import lib.Percolates;

public class GameOfLife {
    public static final int rows = 5;
    public static final int cols = 5;

    /**
     * Simulates one generation of the Game of Life.
     * @param grid The current state of the game grid.
     * @return The new state of the game grid after one generation.
     */
    public static boolean[][] playGame(boolean[][] grid) {
        boolean[][] newBoard = new boolean[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int liveNeighbors = countLiveNeighbors(row, col, grid);

                // A dead cell with exactly three live neighbors becomes alive
                if (!grid[row][col] && liveNeighbors == 3) {
                    newBoard[row][col] = true;
                }
                // A live cell with less than two or more than three live neighbors dies
                else if (grid[row][col] && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    newBoard[row][col] = false;
                }
                // Otherwise, the cell maintains its current state
                else {
                    newBoard[row][col] = grid[row][col];
                }
            }
        }
        return newBoard;
    }

    /**
     * Counts the live neighbors of a cell.
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @param grid The game grid.
     * @return The number of live neighbors.
     */
    public static int countLiveNeighbors(int row, int col, boolean[][] grid) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // Skip the cell itself
                int newRow = row + i;
                int newCol = col + j;
                // Check if neighbor is within bounds and alive
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol]) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Visualizes the game grid.
     * @param board The game grid to visualize.
     */
    public static void visualizeGrid(boolean[][] board) {
        int n = board.length; 
        StdDraw.setXscale(-1, n);
        StdDraw.setYscale(-1, n);
        StdDraw.clear(StdDraw.GRAY);
        StdDraw.enableDoubleBuffering();
    
        double squareSize = 0.45; 
        double padding = 0.05; 
        // Adjusted scale factors to account for padding
        double scaleFactorX = 1.0 / (1 + padding);
        double scaleFactorY = 1.0 / (1 + padding);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < board[i].length; j++) {
                double x = j * scaleFactorX + scaleFactorX / 2 - 0.5;
                double y = (n - i - 1) * scaleFactorY + scaleFactorY / 2 - 0.5;
    
                // Color live cells differently than dead cells
                StdDraw.setPenColor(board[i][j] ? StdDraw.BLACK : StdDraw.WHITE);
                StdDraw.filledSquare(x, y, squareSize / 2); 
            }
        }
    
        StdDraw.show();
        StdDraw.pause(2000); // 1-second delay to observe the generation before moving to the next
    }

    public static void main(String[] args) {
        // Generate an initial random grid
        boolean[][] grid = Percolates.generateRandomGrid(rows, cols, 0.5);
        
        // Run the simulation for a set number of generations, visualizing each
        for(int i = 0; i < 10; i++) {
            grid = playGame(grid); // Compute the next generation
            visualizeGrid(grid); // Visualize the current state of the game grid
        }
    }
}
