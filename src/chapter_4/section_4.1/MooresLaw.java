import java.awt.Color;
import lib.StdDraw;
/*
 * Write a program MooresLaw that takes a command-line argument n and
outputs the increase in processor speed over a decade if microprocessors double
every n months. How much will processor speed increase over the next decade if
speeds double every n = 15 months? 24 months?
 */
public class MooresLaw {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java MooresLaw <n>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int monthsInDecade = 10 * 12;

        double[] months = new double[monthsInDecade];
        double[] speeds = new double[monthsInDecade];

        for (int i = 0; i < monthsInDecade; i++) {
            months[i] = i;
            speeds[i] = Math.pow(2, i / (double) n);
        }

        // Set the scale of the drawing window
        StdDraw.setXscale(0, monthsInDecade);
        StdDraw.setYscale(0, Math.pow(2, monthsInDecade / (double) n));
        StdDraw.setPenColor(Color.BLUE);

        // Plot the data
        for (int i = 0; i < monthsInDecade - 1; i++) {
            StdDraw.line(months[i], speeds[i], months[i+1], speeds[i+1]);
        }
    }
}
