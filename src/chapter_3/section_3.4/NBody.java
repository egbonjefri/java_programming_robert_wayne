
import lib.StdDraw;
import lib.Vector;

import java.awt.Color;


public class NBody {
        private Vector r;           // position
        private Vector v;           // velocity
        private final double mass;  // mass
        private final double G = 6.67430e-11; // Gravitational constant

        public NBody(Vector r, Vector v, double mass) {
            this.r = r;
            this.v = v;
            this.mass = mass;
        }
    
        public void move(Vector f, double dt) {
            Vector a = f.scale(1 / mass);
            v = v.plus(a.scale(dt));
            r = r.plus(v.scale(dt));
           
        }
    
        public Vector forceFrom(NBody b) {
            NBody a = this;
            Vector delta = b.r.minus(a.r);
            double dist = delta.magnitude();
            double magnitude = (G * a.mass * b.mass) / (dist * dist);
            return delta.direction().scale(magnitude);
        }
        public Vector forceTo(NBody b) {
            return b.forceFrom(this); 
        }
        public void draw(Color color, double radius, NBody largerBody) {
            double x1 = r.cartesian(0); // x-coordinate of this body
            double y1 = r.cartesian(1); // y-coordinate of this body
            double x2 = largerBody.r.cartesian(0); // x-coordinate of the larger body
            double y2 = largerBody.r.cartesian(1); // y-coordinate of the larger body
        
            // Calculate the distance between the two bodies
            double dx = x2 - x1;
            double dy = y2 - y1;
            double distance = Math.sqrt(dx * dx + dy * dy);
        
            distance = Math.max(distance, 1);
            double angle = Math.atan2(dy ,dx);

                // Calculate the orbital velocity
                double orbitalVelocity = Math.sqrt(G * largerBody.mass / distance);

                // Calculate the new velocity components
                double newVx = orbitalVelocity * Math.cos(angle - Math.PI / 2);
                double newVy = orbitalVelocity * Math.sin(angle - Math.PI / 2);
        
                // Update the velocity of the smaller body
                v = new Vector(new double[]{newVx, newVy});
                // Update the position of the smaller body with the current velocity
            r = r.minus(v);
            StdDraw.setPenColor(color);
            StdDraw.setPenRadius(0.025 * radius);
            StdDraw.point(x1, y1);
        }
        
        
 
        public static void main(String[] args) {
            double dt = 0.01; // time step
            double mass = 100000; // Mass of each body
            Vector initialVelocity1 = new Vector(new double[]{0, 0}); // Initial velocity of body 1
            Vector initialPosition1 = new Vector(new double[]{2, 2}); // Initial position of body 1
            Vector initialPosition2 = new Vector(new double[]{6, 6}); // Initial position of body 2
            Vector initialVelocity2 = new Vector(new double[]{0, 0}); // Initial velocity of body 2
    
           
            double xScale = 30; 
            double yScale = 30; 

            // Scale the masses according to the x and y scales
            double scaledMass = mass * (xScale + yScale) / 2;

            // Create the bodies with scaled masses
            NBody body1 = new NBody(initialPosition1, initialVelocity1, scaledMass);
            NBody body2 = new NBody(initialPosition2, initialVelocity2, scaledMass * 10000);

            // Set x and y scales
            StdDraw.setXscale(-30, xScale);
            StdDraw.setYscale(-30, yScale);
        
            StdDraw.enableDoubleBuffering();

       
            
            // Simulation loop
            while(true) {
                Vector force = body1.forceFrom(body2); // Calculate force between body1 and body2
                body1.move(force, dt); // Update position and velocity of body1 based on the force
                body2.move(force.scale(-1), dt); // Update position and velocity of body2 with opposite force (Newton's third law)
                StdDraw.clear();
                body1.draw(Color.RED, 1, body2);
                body2.draw(Color.BLUE, 4, body1);
                StdDraw.show();
                StdDraw.pause(50);

            }
            
        }
    
    
}
