import java.awt.Font;
import lib.StdDraw;
/*
 * Write a program that takes a string command-line argument s and displays
it in banner style on the screen, moving from left to right and wrapping back to the
beginning of the string as the end is reached. Add a second command-line argu-
ment to control the speed.
 */
public class BannerDisplay {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java BannerDisplay <text> <speed>");
            return;
        }

        String text = args[0];
        int speed = Integer.parseInt(args[1]);

        displayBanner(text, speed);
    }

    private static void displayBanner(String text, int speed) {
        int canvasWidth = 800;
        int canvasHeight = 200;

        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.setXscale(0, canvasWidth);
        StdDraw.setYscale(0, canvasHeight);

        Font font = new Font("Arial", Font.PLAIN, 30);
        StdDraw.setFont(font);

        int delay = 1000 / speed; // Delay in milliseconds

        while (true) {
            for (int i = 0; i < canvasWidth + text.length() * 30; i++) {
                StdDraw.clear();
                int x = i % (canvasWidth + text.length() * 30);
                StdDraw.text(x, canvasHeight / 2, text);
                StdDraw.show();
                sleep(delay);
            }
        }
    }

    private static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
