import lib.StdOut;
public class Checkerboard {
    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("Usage: java Checkerboard <n>");
            return;
        }

        int n = Integer.parseInt(args[0]);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i + j) % 2 == 0) {
                    StdOut.print("* "); // Print asterisk for even positions
                } else {
                    StdOut.print("  "); // Print space for odd positions
                }
            }
            StdOut.println(); // Move to the next row
        }
    }
}
