/*
 * A vector ﬁeld associates a vector with every point in a Euclid-
ean space. Write a version of Potential (EXERCISE 3.2.23) that takes as input a grid
size n, computes the Vector value of the potential due to the point charges at each
point in an n-by-n grid of evenly spaced points, and draws the unit vector in the di-
rection of the accumulated ﬁeld at each point. (Modify Charge to return a Vector.)
 */
import lib.StdDraw;
import lib.Vector;
import lib.Charge;
import lib.In;



import java.util.ArrayList;

public class VectorField {
    private ArrayList<Vector> vectorPotential;

    public VectorField() {
        this.vectorPotential = new ArrayList<>();
    }

    public void calculatePotentialVectorAt(int x, int y, Charge[] charges) {
        for (Charge charge : charges) {
            double dx = charge.getX() - x;
            double dy = charge.getY() - y;
            double distanceSquared = dx * dx + dy * dy;
            double distance = Math.sqrt(distanceSquared);
            double angle = Math.atan2(dy, dx);
            double potential = charge.getQ() / distance; 
            double potentialX = potential * Math.cos(angle);
            double potentialY = potential * Math.sin(angle);
            double[] array1 = new double[2];
            array1[0] = potentialX;
            array1[1] = potentialY;
            Vector vector =  new Vector(array1);
            vectorPotential.add(vector);
        }
    }

    public static void drawUnitVectors(VectorField field, int n) {
        StdDraw.setXscale(0, n); 
        StdDraw.setYscale(0, n);
    
        for (int i = 0; i < field.vectorPotential.size(); i++) {
            for (int j = 0; j < field.vectorPotential.get(i).length(); j++) {
                Vector vector = field.vectorPotential.get(i);
                Vector unitVector = vector.direction();
                double x = j + 0.5; 
                double y = i + 0.5;
                StdDraw.line(x, y, x + unitVector.cartesian(i)/2 , y + unitVector.cartesian(j)/2); 
            }
        }
    }

    public static void main(String[] args){
        In file = new In(args[0]);
        int n = file.readInt(); // Grid size
        double[] charges = file.readAllDoubles();
        Charge[] particles = new Charge[n];

        for (int i = 0, j = 0; i < n; i++, j += 3) {
            particles[i] = new Charge(charges[j], charges[j+1], charges[j+2]);
        } 

        VectorField field = new VectorField();

        // Calculate potential at each point
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                field.calculatePotentialVectorAt(j, i, particles);
            }
        }

        // Draw unit vectors
        drawUnitVectors(field, n);
    }
}
