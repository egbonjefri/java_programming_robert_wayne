/*
 * Modify Beckett (PROGRAM 2.3.3) to print the Gray code (not
just the sequence of bit positions that change).
 */

 import lib.StdDraw;
 import lib.StdOut;
public class Beckett {
    private static int depth = 0;
    private static double x = -0.1, y = 0.9, dy = 0.05;

    public static void moves(int n, boolean enter) {
        if (n == 0) return;
        depth++;
        moves(n-1, true);
        if (enter) StdOut.println("enter " + n);
        else StdOut.println("exit " + n);
        draw(n, enter); // Draw each call
        moves(n-1, false);
        depth--;
    }

    private static void draw(int n, boolean enter) {
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLACK);
        double x0 = x - 0.05 * depth;
        double x1 = x + 0.05 * depth;
        double y0 = y - dy * depth;
        double y1 = y - dy * (depth + 1);
        StdDraw.line(x0, y0, x1, y1); // Draw line for function call
        if (enter) StdDraw.textLeft(x1, y1, "enter " + n);
        else StdDraw.textLeft(x1, y1, "exit " + n);
    }

    // Generate and print Gray codes
    public static void printGrayCodes(int n) {
        String[] codes = generateGrayCodes(n);
        for (String code : codes) {
            StdOut.println(code);
        }
    }

    // Generate Gray codes for n bits
    private static String[] generateGrayCodes(int n) {
        if (n <= 0) {
            StdDraw.text(x, y, "0");
            return new String[]{"0"};
        } else if (n == 1) {
            StdDraw.text(x+0.9, y + 1, "0 1");
            return new String[]{"0", "1"};
        }
        String[] prevCodes = generateGrayCodes(n - 1);
        String[] codes = new String[prevCodes.length * 2];

        // Prefix 0 to the first half
        for (int i = 0; i < prevCodes.length; i++) {
            codes[i] = "0" + prevCodes[i];
            StdDraw.text(x+i+1.9+n, i+5, codes[i]);
        }

        // Prefix 1 to the second half in reverse order
        for (int i = 0; i < prevCodes.length; i++) {
            codes[codes.length - 1 - i] = "1" + prevCodes[i];
         //   StdDraw.text(x+i+5, i, codes[codes.length - 1 - i]);
        }

        return codes;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        // Initialize StdDraw canvas
        StdDraw.setCanvasSize(800, 600);
        StdDraw.setXscale(-0.1, 16);
        StdDraw.setYscale(0, 16); // 2^n + padding
      //  moves(n, true);

        // Print Gray codes
        StdOut.println("Gray codes for " + n + " bits:");
        printGrayCodes(n);
    }
}
