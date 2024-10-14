/*
 * Develop a graph model for percolation, and write a
Graph client that performs the same computation as Percolation (PROGRAM 2.4.5).
Estimate the percolation threshold for triangular, square, and hexagonal grids.
 */

 /*
  * In a graph-based approach, percolation can be modeled as follows:

- Each site in the grid is a vertex in the graph.
- An edge exists between two vertices if the corresponding sites are adjacent and both are "open."
- The system percolates if there exists a path from any vertex in the top row to any vertex in the bottom row.
  */



import lib.PathFinder;
import lib.Graph;
import lib.ArrayList;
import lib.StdRandom;
import lib.StdDraw;
import lib.StdOut;



// Previous Graph and PathFinder classes remain the same...

// Abstract class for different grid types with drawing capabilities
abstract class GridPercolation {
    protected boolean[][] open;
    protected int n;
    protected Graph<Integer> graph;
    protected int numberOfOpenSites;
    protected double cellSize;
    
    public GridPercolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Grid size must be positive");
        this.n = n;
        open = new boolean[n][n];
        numberOfOpenSites = 0;
        cellSize = 1.0 / n;
        initializeDrawing();
    }
    
    protected void initializeDrawing() {
        StdDraw.setScale(-0.05, 1.05);
        StdDraw.clear();
        StdDraw.enableDoubleBuffering();
    }
    
    public void open(int row, int col) {
        validateIndices(row, col);
        if (!open[row][col]) {
            open[row][col] = true;
            numberOfOpenSites++;
            updateGraph(row, col);
            draw();
        }
    }
    
    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        return open[row][col];
    }
    
    protected abstract void updateGraph(int row, int col);
    protected abstract boolean percolates();
    protected abstract void draw();
    
    protected void validateIndices(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n)
            throw new IndexOutOfBoundsException("Invalid indices");
    }
}

// Square grid implementation
class SquareGridPercolation extends GridPercolation {
    public SquareGridPercolation(int n) {
        super(n);
        graph = new Graph<>(n * n);
    }
    
/**
 * Converts grid coordinates to vertex number
 */
private int coordToVertex(int row, int col) {
    return row * n + col;
}

/**
 * Validates if the given coordinates are within the grid bounds
 */
private boolean isValidPosition(int row, int col) {
    return row >= 0 && row < n && col >= 0 && col < n;
}

/**
 * Ensures a vertex exists in the graph before operations
 */
private void ensureVertexExists(int vertexNum) {
    if (!graph.hasVertex(vertexNum)) {
        graph.addVertex(vertexNum);
    }
}

/**
 * Updates the graph connections for a cell in the grid
 * @param row The row coordinate of the cell
 * @param col The column coordinate of the cell
 */
@Override
protected void updateGraph(int row, int col) {
    if (!isValidPosition(row, col)) {
        throw new IllegalArgumentException(
            String.format("Invalid position: row=%d, col=%d", row, col));
    }

    // Define the four directions: North, South, East, West
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    int currentVertex = coordToVertex(row, col);
    ensureVertexExists(currentVertex);  // Ensure current vertex exists

    // Check and connect to each neighbor
    for (int[] dir : directions) {
        int newRow = row + dir[0];
        int newCol = col + dir[1];
        
        if (isValidPosition(newRow, newCol) && isOpen(newRow, newCol)) {
            int neighborVertex = coordToVertex(newRow, newCol);
            ensureVertexExists(neighborVertex);  // Ensure neighbor vertex exists
            
            try {
                graph.addEdge(currentVertex, neighborVertex);
            } catch (IllegalArgumentException e) {
                StdOut.printf(
                    "Failed to add edge between (%d,%d) and (%d,%d): %s%n", 
                    row, col, newRow, newCol, e.getMessage());
            }
        }
    }
}

/**
 * Debugging helper to validate connections
 */
protected void validateConnection(int row1, int col1, int row2, int col2) {
    int v1 = coordToVertex(row1, col1);
    int v2 = coordToVertex(row2, col2);
    
    if (!graph.hasVertex(v1)) {
        StdOut.printf("Vertex for position (%d,%d) doesn't exist%n", row1, col1);
    }
    if (!graph.hasVertex(v2)) {
        StdOut.printf("Vertex for position (%d,%d) doesn't exist%n", row2, col2);
    }
}
    @Override
    protected void draw() {
        StdDraw.clear();
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                double x = col * cellSize + cellSize/2;
                double y = (n-1-row) * cellSize + cellSize/2;
                
                if (isOpen(row, col)) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                } else {
                    StdDraw.setPenColor(StdDraw.BLACK);
                }
                StdDraw.filledSquare(x, y, cellSize/2);
                StdDraw.setPenColor(StdDraw.GRAY);
                StdDraw.square(x, y, cellSize/2);
            }
        }
        StdDraw.show();
    }
    
    @Override
    public boolean percolates() {
        ArrayList<Integer> topRow = new ArrayList<>();
        ArrayList<Integer> bottomRow = new ArrayList<>();
        
        for (int col = 0; col < n; col++) {
            if (isOpen(0, col)) topRow.add(0 * n + col);
            if (isOpen(n-1, col)) bottomRow.add((n-1) * n + col);
        }
        
        if (topRow.isEmpty() || bottomRow.isEmpty()) return false;
        PathFinder<Integer> pf = new PathFinder<>(graph, topRow, bottomRow);
        return pf.hasPath();
    }
}


// Triangular grid implementation
class TriangularGridPercolation extends GridPercolation {
    public TriangularGridPercolation(int n) {
        super(n);
        graph = new Graph<>(n * n);
    }
/**
 * Converts grid coordinates to vertex number
 */
private int coordToVertex(int row, int col) {
    return row * n + col;
}

/**
 * Validates if the given coordinates are within the grid bounds
 */
private boolean isValidPosition(int row, int col) {
    return row >= 0 && row < n && col >= 0 && col < n;
}

/**
 * Ensures a vertex exists in the graph before operations
 */
private void ensureVertexExists(int vertexNum) {
    if (!graph.hasVertex(vertexNum)) {
        graph.addVertex(vertexNum);
    }
}

/**
 * Gets the appropriate directions based on cell position
 */
private int[][] getDirections(int row, int col) {
    if ((row + col) % 2 == 0) {
        // Even sum positions - connect down-left and down-right
        return new int[][]{
            {-1, 0},  // North
            {1, 0},   // South
            {0, -1},  // West
            {0, 1},   // East
            {1, -1},  // Down-left
            {1, 1}    // Down-right
        };
    } else {
        // Odd sum positions - connect up-left and up-right
        return new int[][]{
            {-1, 0},  // North
            {1, 0},   // South
            {0, -1},  // West
            {0, 1},   // East
            {-1, -1}, // Up-left
            {-1, 1}   // Up-right
        };
    }
}

/**
 * Updates the graph connections for a cell in the alternating grid
 * @param row The row coordinate of the cell
 * @param col The column coordinate of the cell
 */
@Override
protected void updateGraph(int row, int col) {
    if (!isValidPosition(row, col)) {
        throw new IllegalArgumentException(
            String.format("Invalid position: row=%d, col=%d", row, col));
    }

    int currentVertex = coordToVertex(row, col);
    ensureVertexExists(currentVertex);  // Ensure current vertex exists

    // Get appropriate directions based on position
    int[][] directions = getDirections(row, col);
    
    // Check and connect to each neighbor
    for (int[] dir : directions) {
        int newRow = row + dir[0];
        int newCol = col + dir[1];
        
        if (isValidPosition(newRow, newCol) && isOpen(newRow, newCol)) {
            int neighborVertex = coordToVertex(newRow, newCol);
            ensureVertexExists(neighborVertex);  // Ensure neighbor vertex exists
            
            try {
                graph.addEdge(currentVertex, neighborVertex);
            } catch (IllegalArgumentException e) {
                // Log the error but don't halt execution
                StdOut.printf(
                    "Failed to add edge between (%d,%d) and (%d,%d): %s%n", 
                    row, col, newRow, newCol, e.getMessage());
            }
        }
    }
}

/**
 * Debugging helper to print connection pattern for a cell
 */
protected void debugConnections(int row, int col) {
    if (!isValidPosition(row, col)) {
        System.err.println("Invalid position for debug");
        return;
    }
    
    StdOut.printf("Debugging connections for cell (%d,%d):%n", row, col);
    StdOut.printf("Cell type: %s%n", (row + col) % 2 == 0 ? "EVEN" : "ODD");
    
    int[][] directions = getDirections(row, col);
    for (int[] dir : directions) {
        int newRow = row + dir[0];
        int newCol = col + dir[1];
        
        if (isValidPosition(newRow, newCol)) {
            StdOut.printf("  Potential connection to (%d,%d): %s%n",
                newRow, newCol,
                isOpen(newRow, newCol) ? "OPEN" : "CLOSED");
        }
    }
}
    
    @Override
    protected void draw() {
        StdDraw.clear();
        double height = Math.sqrt(3) / 2.0;
        
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                double x = col * cellSize + cellSize/2;
                double y = (n-1-row) * height * cellSize + cellSize/2;
                
                if ((row + col) % 2 == 0) {
                    drawTriangle(x, y, cellSize, true, isOpen(row, col));
                } else {
                    drawTriangle(x, y, cellSize, false, isOpen(row, col));
                }
            }
        }
        StdDraw.show();
    }
    
    private void drawTriangle(double x, double y, double size, boolean pointUp, boolean isOpen) {
        double height = Math.sqrt(3) / 2.0 * size;
        double[] xPoints = new double[3];
        double[] yPoints = new double[3];
        
        if (pointUp) {
            xPoints[0] = x;
            yPoints[0] = y + height/2;
            xPoints[1] = x - size/2;
            yPoints[1] = y - height/2;
            xPoints[2] = x + size/2;
            yPoints[2] = y - height/2;
        } else {
            xPoints[0] = x;
            yPoints[0] = y - height/2;
            xPoints[1] = x - size/2;
            yPoints[1] = y + height/2;
            xPoints[2] = x + size/2;
            yPoints[2] = y + height/2;
        }
        
        if (isOpen) {
            StdDraw.setPenColor(StdDraw.WHITE);
        } else {
            StdDraw.setPenColor(StdDraw.BLACK);
        }
        StdDraw.filledPolygon(xPoints, yPoints);
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.polygon(xPoints, yPoints);
    }
    
    @Override
    public boolean percolates() {
        // Similar to square grid implementation
        ArrayList<Integer> topRow = new ArrayList<>();
        ArrayList<Integer> bottomRow = new ArrayList<>();
        
        for (int col = 0; col < n; col++) {
            if (isOpen(0, col)) topRow.add(0 * n + col);
            if (isOpen(n-1, col)) bottomRow.add((n-1) * n + col);
        }
        
        if (topRow.isEmpty() || bottomRow.isEmpty()) return false;
        PathFinder<Integer> pf = new PathFinder<>(graph, topRow, bottomRow);
        return pf.hasPath();
    }
}

// Hexagonal grid implementation
class HexagonalGridPercolation extends GridPercolation {
    public HexagonalGridPercolation(int n) {
        super(n);
        graph = new Graph<>(n * n);
    }
    
/**
 * Represents a vertex in the hex grid
 */
private void ensureVertexExists(int vertexNum) {
    if (!graph.hasVertex(vertexNum)) {
        graph.addVertex(vertexNum);
    }
}

/**
 * Validates if the given coordinates are within the grid bounds
 */
private boolean isValidPosition(int row, int col) {
    return row >= 0 && row < n && col >= 0 && col < n;
}

/**
 * Converts grid coordinates to vertex number
 */
private int coordToVertex(int row, int col) {
    return row * n + col;
}

/**
 * Updates the graph connections for a given hex cell
 */
protected void updateGraph(int row, int col) {
    if (!isValidPosition(row, col) || !isOpen(row, col)) {
        return;  // Skip if position is invalid or cell is not open
    }

    // Define directions based on column parity (even/odd)
    int[][] directions = (col % 2 == 0) 
        ? new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}}  // even column
        : new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {1, -1}, {1, 1}};   // odd column

    int currentVertex = coordToVertex(row, col);
    ensureVertexExists(currentVertex);  // Ensure current vertex exists

    // Check and connect to each neighbor
    for (int[] dir : directions) {
        int newRow = row + dir[0];
        int newCol = col + dir[1];

        if (isValidPosition(newRow, newCol) && isOpen(newRow, newCol)) {
            int neighborVertex = coordToVertex(newRow, newCol);
            ensureVertexExists(neighborVertex);  // Ensure neighbor vertex exists
            try {
                graph.addEdge(currentVertex, neighborVertex);
            } catch (IllegalArgumentException e) {
                StdOut.printf("Failed to add edge between (%d,%d) and (%d,%d): %s%n", 
                    row, col, newRow, newCol, e.getMessage());
            }
        }
    }
}

/**
 * Helper method to check if all vertices in a path exist
 */
protected boolean validateVertexPath(int... vertices) {
    for (int vertex : vertices) {
        if (!graph.hasVertex(vertex)) {
            return false;
        }
    }
    return true;
}
    @Override
    protected void draw() {
        StdDraw.clear();
        double height = Math.sqrt(3) / 2.0;
        
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                double x = col * (3.0/4) * cellSize + cellSize/2;
                double y = (n-1-row) * height * cellSize + 
                          (col % 2 == 0 ? 0 : height * cellSize/2) + cellSize/2;
                
                drawHexagon(x, y, cellSize/2, isOpen(row, col));
            }
        }
        StdDraw.show();
    }
    
    private void drawHexagon(double x, double y, double size, boolean isOpen) {
        int sides = 6;
        double angle = 2 * Math.PI / sides;
        double[] xPoints = new double[sides];
        double[] yPoints = new double[sides];
        
        for (int i = 0; i < sides; i++) {
            xPoints[i] = x + size * Math.cos(angle * i);
            yPoints[i] = y + size * Math.sin(angle * i);
        }
        
        if (isOpen) {
            StdDraw.setPenColor(StdDraw.WHITE);
        } else {
            StdDraw.setPenColor(StdDraw.BLACK);
        }
        StdDraw.filledPolygon(xPoints, yPoints);
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.polygon(xPoints, yPoints);
    }
    
    @Override
    public boolean percolates() {
        // Similar to other implementations
        ArrayList<Integer> topRow = new ArrayList<>();
        ArrayList<Integer> bottomRow = new ArrayList<>();
        
        for (int col = 0; col < n; col++) {
            if (isOpen(0, col)) topRow.add(0 * n + col);
            if (isOpen(n-1, col)) bottomRow.add((n-1) * n + col);
        }
        
        if (topRow.isEmpty() || bottomRow.isEmpty()) return false;
        PathFinder<Integer> pf = new PathFinder<>(graph, topRow, bottomRow);
        return pf.hasPath();
    }
}

// Updated PercolationStats to include all grid types and visualization
class PercolationStats {
    private static final int TRIALS = 10;
    private double[] thresholds;
    private final String gridType;
    
    public PercolationStats(int n, String gridType) {
        this.gridType = gridType;
        thresholds = new double[TRIALS];
        
        for (int t = 0; t < TRIALS; t++) {
            GridPercolation perc = createGrid(n, gridType);
            
            while (!perc.percolates()) {
                int row = StdRandom.uniformInt(n);
                int col = StdRandom.uniformInt(n);
                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                    StdDraw.pause(50); // Add delay for visualization
                }
            }
            
            thresholds[t] = (double) perc.numberOfOpenSites / (n * n);
            StdDraw.pause(1000); // Pause to show final state
        }
    }
    
    private GridPercolation createGrid(int n, String gridType) {
        switch (gridType.toLowerCase()) {
            case "square":
                return new SquareGridPercolation(n);
            case "triangular":
                return new TriangularGridPercolation(n);
            case "hexagonal":
                return new HexagonalGridPercolation(n);
            default:
                throw new IllegalArgumentException("Unknown grid type: " + gridType);
        }
    }
    
    public double mean() {
        double sum = 0;
        for (double threshold : thresholds) {
            sum += threshold;
        }
        return sum / TRIALS;
    }
    
    public double stddev() {
        double mean = mean();
        double sum = 0;
        for (double threshold : thresholds) {
            sum += (threshold - mean) * (threshold - mean);
        }
        return Math.sqrt(sum / (TRIALS - 1));
    }
    
    public void plotResults() {
        StdDraw.clear();
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, TRIALS/4);
        
        // Create histogram
        int[] counts = new int[20]; // 20 bins
        for (double threshold : thresholds) {
            int bin = (int) (threshold * 20);
            if (bin == 20) bin--; // Handle edge case
            counts[bin]++;
        }
        
        // Draw bars
        double binWidth = 1.0/20;
        for (int i = 0; i < counts.length; i++) {
            double x = i * binWidth + binWidth/2;
            double y = counts[i]/2.0;
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledRectangle(x, y, binWidth/2, counts[i]/2.0);
        }
        
        // Draw mean line
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.005);
        double mean = mean();
        StdDraw.line(mean, 0, mean, TRIALS/4);
        
        StdDraw.show();
    }
}

public class PercolationSimulation {
    public static void main(String[] args) {
        int n = 20; // grid size
        String[] gridTypes = {"square", "triangular", "hexagonal"};
        
        StdOut.println("Percolation threshold estimates:");
        
        for (String gridType : gridTypes) {
            StdOut.printf("Running simulation for %s grid...%n", gridType);
            PercolationStats stats = new PercolationStats(n, gridType);
            StdOut.printf("%s grid: %.4f ± %.4f%n", 
                gridType, stats.mean(), stats.stddev());
            
            // Plot results
            stats.plotResults();
            StdDraw.pause(5000); // Pause to show results before next simulation
        }
    }
}