import lib.StdOut;


public class SelfAvoidingWalk {
    public static void main(String[] args){

        // Do trials random self-avoiding
        // walks in an n-by-n lattice.
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        int deadEnds = 0;
        //paths that end because all neighboring cells are visited
        int totalDeadEndLength = 0;
        //paths that reach the boundary of the lattice
        int totalEscapeLength = 0;
        int totalDeadEndArea = 0;

        for (int t = 0; t < trials; t++) {
        boolean[][] a = new boolean[n][n];
        //Start at the center of the grid
        int x = n/2, y = n/2;
        int pathLength = 0;
        int minX = x, maxX = x, minY = y, maxY = y;

        /*
         *  This condition ensures that the walk remains within the boundaries of the
            n×n lattice. The lattice is indexed from 0 to n−1 in both the x and y directions. 
            The condition x > 0 && x < n - 1 checks whether the current position is not on the vertical boundary
            of the lattice, and y > 0 && y < n - 1 does the same for the horizontal boundary. 
            If the walk reaches the edge of the lattice (i.e., x or y is 0 or n−1), it cannot continue and 
            therefore the walk should stop.
         */
        while (x > 0 && x < n-1 && y > 0 && y < n-1){ 

        if (a[x-1][y] && a[x+1][y] && a[x][y-1] && a[x][y+1]){ 
            deadEnds++; 
            totalDeadEndLength += pathLength;
            int width = maxX - minX + 1;
            int height = maxY - minY + 1;
            totalDeadEndArea += width * height;
            break; 
        }
        minX = Math.min(minX, x);
        maxX = Math.max(maxX, x);
        minY = Math.min(minY, y);
        maxY = Math.max(maxY, y);
        // Check for dead end and make a random move.
        a[x][y] = true;
        double r = Math.random();
        pathLength++;
        if (r < 0.25){ 
            if (!a[x+1][y]){
                 x++; }
            }
        else if (r < 0.50) { 
            if (!a[x-1][y]){
             x--; }
            }
        else if (r < 0.75) { 
            if (!a[x][y+1]){
                 y++; }
            }
        else if (r < 1.00) { 
            if (!a[x][y-1]){
                 y--; }
            }
            if (x == 0 || x == n - 1 || y == 0 || y == n - 1) {
                totalEscapeLength += pathLength;
            }
            }
        }
        double averageDeadEndLength = deadEnds == 0 ? 0 : (double) totalDeadEndLength / deadEnds;
        double averageDeadEndArea = deadEnds == 0 ? 0 : (double) totalDeadEndArea / deadEnds;

        double averageEscapeLength = trials - deadEnds == 0 ? 0 : (double) totalEscapeLength / (trials - deadEnds);
        double deadEndProbability = (double) deadEnds / trials;

        StdOut.println("Average length of dead-end paths: " + averageDeadEndLength);
        StdOut.println("Average length of escape paths: " + averageEscapeLength);
        StdOut.println("Dead-end probability: " + deadEndProbability);
        StdOut.println("Average area of enclosing rectangle for dead-end paths: " + averageDeadEndArea);

            }
}