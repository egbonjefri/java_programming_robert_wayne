import lib.StdOut;



public class RandomWalker {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java RandomWalker <r>");
            return;
        }

        int r = Integer.parseInt(args[0]);
        int x = 0; // Initial x-coordinate
        int y = 0; // Initial y-coordinate
        int steps = 0; // Number of steps taken

        StdOut.println("(" + x + ", " + y + ")"); // Print starting point

        while (Math.abs(x) + Math.abs(y) < r) {
            double random = Math.random(); // Generate a random number between 0 and 1

            if (random < 0.25) {
                x++;
            } else if (random < 0.5 && random > 0.25) {
                x--; 
            } else if (random < 0.75 && random > 0.5) {
                y++; 
            } else {
                y--; 
            }

            steps++;
            StdOut.println("(" + x + ", " + y + ")");
        }

        StdOut.println("Total number of steps taken: " + steps);
    }
}






