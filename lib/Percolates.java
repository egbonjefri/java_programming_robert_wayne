package lib;
/**
 * Proof by induction that in the recursive depth-first search (DFS) approach,
 * a site is always reached if it's connected to the top row:
 * 
 * Base Case (i = 0):
 *  - Any site directly in the top row (i = 0) is connected to the top by definition.
 *  - The code checks if the site is open (isOpen[i][j]) before marking it full (isFull[i][j] = true). 
 *    If it's closed, the recursion stops, but that doesn't affect our goal of reaching connected sites.
 *  - If the site is open, the function calls itself recursively for its down neighbor (flow(isOpen, isFull, i+1, j)). 
 *    Since this neighbor is directly below an open site in the top row, it's also connected to the top.
 *
 * Inductive Hypothesis:
 *  - Assume for some k (0 <= k < n), the function reaches any site in row k that's connected to an open site in the top row.
 *
 * Inductive Step:
 *  - We want to prove that the function reaches any site in row k+1 connected to the top:
 *    1. Consider a site (k+1, j) in row k+1.
 *    2. If it's closed (!isOpen[k+1][j]), the recursion stops, but it doesn't affect reaching connected sites.
 *    3. If it's open (isOpen[k+1][j]), the function explores its neighbors:
 *       - Down (flow(isOpen, isFull, k+2, j)): This neighbor is directly below an open site in row k+1, 
 *         which by the inductive hypothesis, is connected to the top.
 *       - Right (flow(isOpen, isFull, k+1, j+1)), Left (flow(isOpen, isFull, k+1, j-1)): 
 *         These neighbors are in the same row (k+1). If either is connected to the top 
 *         (open path from top to that neighbor), the function will eventually reach them and mark (k+1, j) 
 *         as full due to the open connection.
 *       - Up (flow(isOpen, isFull, k, j)): This case doesn't apply here as we're exploring sites from top to bottom.
 *
 * Therefore, if a site in row k+1 is connected to the top (through a path of open sites), the function will eventually 
 * reach it by exploring its neighbors and following the connections established by the inductive hypothesis.
 *
 */

public class Percolates {
    public static int trials = 0;
    public static int rows = 0;
    public static int columns = 0;
    public static double p = 0.0;
    public static double gap = 0.0;
    public static double err = 0.0;

    // Compute full sites for vertical percolation.
    /*
    public static boolean[][] flow(boolean[][] isOpen) {
        int n = isOpen.length;
        boolean[][] isFull = new boolean[n][n];
        
        // Initialize the top row
        for (int j = 0; j < n; j++){
            isFull[0][j] = isOpen[0][j];
        }
        // Flow down
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // vertical percolation
                isFull[i][j] = isOpen[i][j] && isFull[i-1][j];
                //isFull[i][j] = isOpen[i][j] && (isFull[i - 1][j] || (j > 0 && isFull[i][j - 1]) || (j < n - 1 && isFull[i][j + 1]));

                // A cell (i, j) is considered full only if:
                // It's open (isOpen[i][j])
                // The cell directly above it (i-1, j) is full (isFull[i-1][j])
                // This propagates the "full" state downwards, building connections from open top cells


            }
        }
        
        return isFull;
    }


 *     public static boolean[][] flow(boolean[][] isOpen) {
        int n = isOpen.length;
        boolean[][] isFull = new boolean[n][n];
        
        Stack<int[]> stack = new Stack<>();
        
        // Initialize stack with the top row open sites
        for (int j = 0; j < n; j++) {
            if (isOpen[0][j]) {
                stack.push(new int[]{0, j});
                isFull[0][j] = true;
            }
        }
        
        // Iterative DFS using stack
        //DFS(startNode):
  Initialize a stack and push startNode onto it
  While the stack is not empty:
    Pop a node from the stack
    If the node has not been visited:
      Mark the node as visited
      For each neighbor of the node:
        Push the unvisited neighbor onto the stack
        ji
        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int i = current[0];
            int j = current[1];
            
            // Check and add unvisited neighbors to the stack
            if (i > 0 && isOpen[i-1][j] && !isFull[i-1][j]) {
                stack.push(new int[]{i-1, j});
                isFull[i-1][j] = true;
            }
            if (i < n-1 && isOpen[i+1][j] && !isFull[i+1][j]) {
                stack.push(new int[]{i+1, j});
                isFull[i+1][j] = true;
            }
            if (j > 0 && isOpen[i][j-1] && !isFull[i][j-1]) {
                stack.push(new int[]{i, j-1});
                isFull[i][j-1] = true;
            }
            if (j < n-1 && isOpen[i][j+1] && !isFull[i][j+1]) {
                stack.push(new int[]{i, j+1});
                isFull[i][j+1] = true;
            }
        }
        
        return isFull;
    }
 */

    // test whether a system percolates in the general case 
    // when any path starting at the top and ending at the bottom
    //not just a vertical one will do the job
    // given an n-by-n matrix of open sites, return an n-by-n matrix
    // of sites reachable from the top

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
            flow(isOpen, isFull, 0, j);
        }
        return isFull;
    }

    // determine set of full sites using depth first search
    //DFS(node):
 // Mark node as visited
 // For each neighbor of node:
 //   If neighbor is not visited:
 //     DFS(neighbor)
    // DFS to mark full sites
    public static void flow(boolean[][] isOpen, boolean[][] isFull, int i, int j) {
        // base case for invalid row
        if (i < 0 || i >= isOpen.length) return;

        // ensure row is not null and check for invalid column
        if (isOpen[i] == null || j < 0 || j >= isOpen[i].length) return;

        if (!isOpen[i][j] || isFull[i][j]) return; // base cases for closed or already full sites

        isFull[i][j] = true; // mark as full
    // Visualize current state of the grid
      visualizeGrid(isOpen, isFull); 
      StdDraw.show();
      StdDraw.pause(1000); 
        // Recursive DFS calls for neighboring sites
        flow(isOpen, isFull, i+1, j);   // down
        flow(isOpen, isFull, i, j+1);   // right
        flow(isOpen, isFull, i, j-1);   // left
        flow(isOpen, isFull, i-1, j);   // up
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

        public static void plotBars(int trials){
            int[] freq = new int[2]; // Since outcome is binary (percolates or not), only two frequencies needed
            for (int t = 0; t < trials; t++) {
                boolean[][] isOpen = generateRandomGrid(5, 2, 0.5);
                if (percolates(isOpen)) freq[1]++;
                else freq[0]++;
            }
                    // Normalize frequencies to probabilities
        double[] norm = new double[2];
        for (int i = 0; i < 2; i++) {
            norm[i] = (double) freq[i] / trials;
        }

        // Plot normalized frequencies as bars
        StdStats.plotBars(norm);

            // Calculate Gaussian distribution model
            double mean = 2 / 2.0;
            double stddev = Math.sqrt(2) / 2.0;
            double[] phi = new double[2];
        for (int i = 0; i < 2; i++) {
            phi[i] = Gaussian.pdf(i, mean, stddev);
        }

        // Plot Gaussian model as lines
        StdStats.plotLines(phi);
        }


        // visualize the grid

        public static void visualizeGrid(boolean[][] isOpen, boolean[][] isFull) {
            int n = isOpen.length; // Assume both isOpen and isFull are the same size
        
            StdDraw.setXscale(-1, n);
            StdDraw.setYscale(-1, n);
            StdDraw.clear(StdDraw.GRAY);
            StdDraw.enableDoubleBuffering();
        
            double squareSize = 0.45; // Define square size
            double padding = 0.05; // Define padding
        
            // Adjusted scale factors to account for padding
            double scaleFactorX = 1.0 / (1 + padding);
            double scaleFactorY = 1.0 / (1 + padding);
        
            // Iterate through each cell and draw based on isOpen and isFull flags
            for (int i = 0; i < n; i++) {
                int rowLength = isOpen[i].length; // Length of the current row
                for (int j = 0; j < rowLength; j++) {
                    double x = j * scaleFactorX + scaleFactorX / 2 - 0.5;
                    double y = (n - i - 1) * scaleFactorY + scaleFactorY / 2 - 0.5;
        
                    // Determine color based on the site's state
                    if (isFull[i][j]) {
                        StdDraw.setPenColor(StdDraw.BOOK_BLUE); // Full sites in blue
                    } else if (isOpen[i][j]) {
                        StdDraw.setPenColor(StdDraw.WHITE); // Open but not full sites in white
                    } else {
                        StdDraw.setPenColor(StdDraw.BLACK); // Blocked sites in black
                    }
        
                    StdDraw.filledSquare(x, y, squareSize / 2); // Draw the site
                }
            }
        
            StdDraw.show();
            StdDraw.pause(100); 
        }
      public static void printGrid(boolean[][] isOpen, boolean[][] isFull){
        int n = isOpen.length; 
        for (int i = 0; i < n; i++) {
            int rowLength = isOpen[i].length; // Length of the current row
            for (int j = 0; j < rowLength; j++) {
                if (isFull[i][j]) {
                   StdOut.print("* ");
                } else if (isOpen[i][j]) {
                    StdOut.print("1 ");
                } else {
                    StdOut.print("0 ");
                }
            }
            StdOut.println();
        }

      }
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


        // do specified number of trials and return fraction that percolate
        public static double evaluate(int rows, int columns, double p, int trials) {
            int count = 0;
            for (int t = 0; t < trials; t++) {
                boolean[][] isOpen = generateRandomGrid(rows, columns, p);
                if (percolates(isOpen))
                    count++;
            }
            return (double) count / trials;
        }
    
      
            // recursive curve plotting
    public static void curve(double x0, double y0, double x1, double y1) {
     //   double gap = 0.01;
     //   double err = 0.0025;
        double xm = (x0 + x1) / 2;
        double ym = (y0 + y1) / 2;
        double fxm = evaluate(rows, columns, p, trials);
        if (x1 - x0 < gap || Math.abs(ym - fxm) < err) {
            StdDraw.line(x0, y0, x1, y1);
            return;
        }
        curve(x0, y0, xm, fxm);
        StdDraw.filledCircle(xm, fxm, 0.005);
        curve(x0, y0, xm, fxm);
    }



    public static void main(String[] args) {
        if (args.length != 6) {
            System.out.println("Usage: java Percolates <rows> <columns> <p> <trials> <gap> <err>");
            return;
        }

        rows = Integer.parseInt(args[0]);
        columns = Integer.parseInt(args[1]);
        p = Double.parseDouble(args[2]);
        trials = Integer.parseInt(args[3]);
        gap = Double.parseDouble(args[4]);
        err = Double.parseDouble(args[5]);
        boolean[][] isOpen = generateRandomGrid(rows, columns, p);
        boolean[][] isFull = flow(isOpen);
        printGrid(isOpen, isFull);
    //    plotBars(100);
    }
}

/**
 *
 * A system with site vacancy probability p vertically percolates with probability 1 - (1 - p^n)^n
 *
 *
 * Why this probability expression?
 *
 * 1. **Single Row Percolation:** Probability of a path through a single row is p^n (all sites open).
 * 2. **No Path in a Single Row:** Probability of no path in a single row is 1 - p^n (complement of path existing).
 * 3. **All Rows Blocked:** Probability of all n rows blocked is (1 - p^n)^n (each row independent).
 * 4. **At Least One Path Exists:** Percolation probability is the complement of all blocked: 1 - (1 - p^n)^n (at least one open row).
 *
 */
