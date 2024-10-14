import java.awt.Color;
import lib.StdDraw;

public class AnimatedHanoi {

    private static final int CANVAS_SIZE = 600;
    private static final double DISC_HEIGHT = 0.1;
    private static final double POLE_WIDTH = 0.02;
    private static final double POLE_HEIGHT = 1.5;
    private static final double BASE_Y = 0.1;
    private static final double DISC_GAP = 0.085; // Gap between discs

    public static void main(String[] args) {
        int n = 3; // Number of discs
        // Set size of window and scale
        StdDraw.setCanvasSize(CANVAS_SIZE, CANVAS_SIZE);
        StdDraw.setXscale(-1, 3);
        StdDraw.setYscale(0, n + 3);
        StdDraw.enableDoubleBuffering();
        
        int[] pole = new int[n + 1]; // pole[i] = pole (0, 1, 2) that disc i is on
        for (int i = 1; i <= n; i++) {
            pole[i] = 0; // Initialize all discs on pole 0
        }
        
        draw(pole, n);
        hanoi(n, 0, 2, 1, pole, n); // Using 0, 1, 2 for A, B, C
    }

    private static void hanoi(int n, int from, int to, int aux, int[] pole, int totalDiscs) {
        if (n == 1) {
            System.out.printf("Move disk 1 from peg %c to peg %c\n", (char)('A' + from), (char)('A' + to));
            pole[1] = to; // Move disk 1 to 'to' peg
            draw(pole, totalDiscs);
            return;
        }
        hanoi(n - 1, from, aux, to, pole, totalDiscs);
        System.out.printf("Move disk %d from peg %c to peg %c\n", n, (char)('A' + from), (char)('A' + to));
        pole[n] = to; // Move nth disk to 'to' peg
        draw(pole, totalDiscs);
        hanoi(n - 1, aux, to, from, pole, totalDiscs);
    }

    // Draw the current state of the Towers of Hanoi game
    public static void draw(int[] pole, int n) {
        StdDraw.clear();

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.002);

        // Draw 3 poles
        for (int i = 0; i < 3; i++)
            StdDraw.line(i, BASE_Y, i, POLE_HEIGHT + BASE_Y);

        // Draw N discs
        int[] discs = new int[3]; // discs[p] = # discs on pole p
        for (int i = n; i >= 1; i--) {
            Color color = Color.getHSBColor(1.0f * i / n, 0.7f, 0.7f);
            StdDraw.setPenColor(color);
            double size = DISC_GAP * (i / (double) n);
            int p = pole[i];
            double x = p + POLE_WIDTH / 2.0;
            double y = BASE_Y + (discs[p] * DISC_HEIGHT);
            StdDraw.filledRectangle(x, y, size * 5, DISC_HEIGHT / 2);
            discs[p]++;
        }

        StdDraw.show();
        StdDraw.pause(2500);
    }
}
