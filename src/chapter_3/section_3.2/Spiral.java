/******************************************************************************
 *  Compilation:  javac Spiral.java
 *  Execution:    java Spiral
 *  Dependencies: Turtle.java
 *
 *  Plots a logarithmic spiral.
 *
 *  % java Spiral 0.1 0.1
 *
 *
 *
 *
 ******************************************************************************/

import lib.Turtle;

public class Spiral {

    public static void main(String[] args) {
       // Check if two arguments are provided
       if (args.length < 2) {
        System.out.println("Usage: java LogarithmicSpiral <a> <k>");
        return;
    }
            /* Parameters for the logarithmic spiral
           The logarithmic spiral formula in polar coordinates is r = a * e^(k * phi),
           where 'a' and 'k' are constants, 'e' is the base of the natural logarithm,
           'r' is the radius from the origin, and 'phi' is the angular coordinate (in radians).
        */
        double a = Double.parseDouble(args[0]); // Base size of the spiral
        double k = Double.parseDouble(args[1]); // Determines the tightness of the spiral

        /*
           To ensure the entire spiral fits within the canvas view, we calculate the maximum radius (r_max)
           that the spiral will have within 5 loops (phi = 2 * PI * 5). Given a = 0.1 and k = 0.1,
           r_max can be calculated as r_max = a * e^(k * 2 * PI * 5). This helps us to set the scale appropriately.
        */

        // Calculate maximum expected radius for setting canvas size and scale
        double phiMax = 2 * Math.PI * 5;
        double rMax = a * Math.exp(k * phiMax);


        
        // Create a turtle at the center of the canvas, starting at (0, 0) for simplicity
        Turtle turtle = new Turtle(0, 0, 0);
        turtle.setCanvasSize(800, 800);    
        turtle.setXscale(-rMax-100, rMax+100);     
        turtle.setYscale(-rMax-100, rMax+100);  
        // Total angle to cover for the spiral (approximately 5 loops)
        double totalAngle = 2 * Math.PI * 5;
        // Increment for the angle to create a smooth spiral
        double deltaAngle = 0.01;
        
        for(double phi = 0; phi <= totalAngle; phi += deltaAngle) {
            // Calculate the radius for the current angle using the logarithmic spiral formula
            double r = a * Math.exp(k * phi);
            // Convert the angle increment to degrees since the Turtle class uses degrees
            double deltaDegrees = Math.toDegrees(deltaAngle);
                    
            turtle.goForward(r);
            turtle.turnLeft(deltaDegrees);
            }
}
 }
