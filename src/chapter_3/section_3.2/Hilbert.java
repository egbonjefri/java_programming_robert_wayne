/*
 * A space-filling curve is a continuous curve in the unit square
that passes through every point. Write a recursive Turtle client that produces these
recursive patterns, which approach a space-ﬁlling curve that was deﬁned by the
mathematician David Hilbert at the end of the 19th century.
Partial answer: Design a pair of mutually recursive methods: hilbert(), which
traverses a Hilbert curve, and treblih(), which traverses a Hilbert curve in reverse
order. See the booksite for details.
 */
import lib.Turtle;

public class Hilbert {
    private Turtle turtle;

    public Hilbert(int n) {
        this.turtle = new Turtle(0.5, 0.5, 0.0);;
        double max = Math.pow(2, n);
        turtle.setXscale(-1, max);
        turtle.setYscale(0, max);
    }

    // Hilbert curve
    private void hilbert(int n, double size, int angle) {
        if (n == 0) return;
        turtle.turnLeft(angle);
        treblih(n-1, size, angle);
        turtle.goForward(size);
        turtle.turnLeft(-angle);
        hilbert(n-1, size, angle);
        turtle.goForward(size);
        hilbert(n-1, size, angle);
        turtle.turnLeft(-angle);
        turtle.goForward(size);
        treblih(n-1, size, angle);
        turtle.turnLeft(angle);
    }

    // evruc trebliH
    public void treblih(int n, double size, int angle) {
        if (n == 0) return;
        turtle.turnLeft(-angle);
        hilbert(n-1, size, angle);
        turtle.goForward(size);
        turtle.turnLeft(angle);
        treblih(n-1, size, angle);
        turtle.goForward(size);
        treblih(n-1, size, angle);
        turtle.turnLeft(angle);
        turtle.goForward(size);
        hilbert(n-1, size, angle);
        turtle.turnLeft(-angle);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int angle = 90;
        Hilbert curve = new Hilbert(n);
        double size = 1.0;  
        curve.hilbert(n, size, angle); 
    }
}
