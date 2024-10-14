import java.util.LinkedList;
import lib.StdDraw;
import lib.StdIn;

/*
 * Write a program that takes a command-line argument m
and produces a bar graph of the m most recent double values on standard input.
Use the same animation technique that we used for BouncingBall (PROGRAM 1.5.6):
erase, redraw, show, and wait brieﬂy. Each time your program reads a new number,
it should redraw the whole bar graph. Since most of the picture does not change as
it is redrawn slightly to the left, your program will produce the effect of a ﬁxed-size
window dynamically sliding over the input values. Use your program to plot a huge
time-variant data ﬁle, such as stock prices.
 */
public class AnimatedPlots {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Animated Graph <m>");
            return;
        }
        int m = Integer.parseInt(args[0]); // Number of recent values to display
        LinkedList<Double> values = new LinkedList<>();

        StdDraw.setCanvasSize(800, 600);
        StdDraw.setXscale(-2, m+2);
        StdDraw.setYscale(-2, 200); 
        StdDraw.enableDoubleBuffering();

        while (!StdIn.isEmpty()) {
                double value = StdIn.readDouble();
                values.addFirst(value);
                if (values.size() > m) {
                    values.removeLast();
                }

                StdDraw.clear();
                for (int i = 0; i < values.size(); i++) {
                    double x = i + 0.5;
                    double y = values.get(i) / 2.0;
                    double rw = 0.5;
                    double rh = values.get(i) / 2.0;
                    StdDraw.filledRectangle(x, y, rw, rh);
                }

                StdDraw.show();
                StdDraw.pause(100); 
            }
        }
    }

