/*
 * Electric potential visualization.
 *  Write a program Potential that creates an array of
charged particles from values given on standard
input (each charged particle is speciﬁed by its x-
coordinate, y-coordinate, and charge value) and
produces a visualization of the electric potential in
the unit square. To do so, sample points in the unit
square. For each sampled point, compute the elec-
tric potential at that point (by summing the electric
potentials due to each charged particle) and plot
the corresponding point in a shade of gray propor-
tional to the electric potential.
 */
import lib.Picture;
import lib.In;
import lib.Charge;
import java.awt.Color;

public class Potential {

    // Calculate the potential at each point in the unit square
    public static double[][] potential(int resolution, Charge[] particles) {
    // Initialize grid with resolution x resolution size
    double[][] potentialGrid = new double[resolution][resolution];
    Picture picture = new Picture(potentialGrid.length, potentialGrid[0].length);
    double stepSize = 1.0 / (resolution - 1); // Step size for each point
    double potential = 0.0;
    // Loop through each point in the grid
    for (int i = 0; i < resolution; i++) {
      for (int j = 0; j < resolution; j++) {
        double x = i * stepSize;
        double y = j * stepSize;
        potential = 0;
       for(int k = 0; k < particles.length; k++){
            potential += particles[k].potentialAt(x , y);
       }
       potentialGrid[i][j] = 128 + potential / 2.0e10;
       int t = 0;
       if      (potentialGrid[i][j] <   0) t = 0;
       else if (potentialGrid[i][j] > 255) t = 255;
       else                      t = (int) potentialGrid[i][j];
       Color c = Color.getHSBColor(t / 255.0f, 0.9f, 0.9f);
       picture.set(i, j, c);
      }
    }
    picture.show();
    return potentialGrid;
    }

    public static void main(String[] args){
        In file = new In(args[0]);
        int n = file.readInt();
        double[] charges = file.readAllDoubles();
         
        Charge[] particles = new Charge[n];

        for (int i = 0, j = 0; i < n; i++, j += 3) {
            particles[i] = new Charge(charges[j], charges[j+1], charges[j+2]);
        }
        
        double[][] potentialGrid = potential(1000, particles);
    }
}
