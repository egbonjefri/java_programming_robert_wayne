/******************************************************************************
 *  Compilation:  javac DrunkenTurtles.java
 *  Execution:    java DrunkenTurtles trials step
 *  Dependencies: Turtle.java
 *
 *  Plot the path of a drunken turtle.
 *
 *  % java DrunkenTurtles 20 5000 0.005
 *
 ******************************************************************************/

import lib.StdRandom;
import lib.Turtle;


 public class DrunkenTurtles {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);             // number of turtles
        int trials = Integer.parseInt(args[1]);        // number of steps
        double step = Double.parseDouble(args[2]);     // step size

        // create n turtles
        Turtle[] turtles = new Turtle[n];
        for (int i = 0; i < n; i++) {
            double x = StdRandom.uniformDouble(0.0, 1.0);
            double y = StdRandom.uniformDouble(0.0, 1.0);
            turtles[i] = new Turtle(x, y, 0.0);
        }

        // simulate the movement for specified number of steps
        for (int t = 0; t < trials; t++) {
            for (int i = 0; i < n; i++) {
                double randomAngle = StdRandom.uniformDouble(0.0, 360.0);
                turtles[i].turnLeft(randomAngle);
                turtles[i].goForward(step);
            }
        }
    }
}

