
import lib.Vector;

public class Particle {
    private Vector position;
    private double mass;
    private Vector velocity;
/*
 * Create a data type for a three-dimensional particle with position (rx, ry, rz),
mass (m), and velocity (vx , vy , vz). Include a method to return its kinetic energy,
which equals 1/2 * m* (vx^2 + vy^2 + vz^2). Use Vector (PROGRAM 3.3.3).
 */
    public Particle(double[] position, double mass, double[] velocity) {
        this.position = new Vector(position);
        this.mass = mass;
        this.velocity = new Vector(velocity);
        //this.momentum = new Vector(momentum);

    }
/*
 * If you know your physics, develop an alternate implementation for your
data type from the previous exercise based on using the momentum (px, py, pz) as
an instance variable.
 */
    public double kineticEnergy() {
        // Calculate kinetic energy using the formula: 1/2 * m * (vx^2 + vy^2 + vz^2)
        double vSquared = velocity.dot(velocity);
        return 0.5 * mass * vSquared;
        /*
         * // Calculate kinetic energy using the formula: KE = (p^2) / (2m)
        * double momentumMagnitudeSquared = momentum.dot(momentum);
        * return 0.5 * momentumMagnitudeSquared / mass;
         */
    }

    // Getters and setters
    public Vector getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = new Vector(position);
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(double[] velocity) {
        this.velocity = new Vector(velocity);
    }

    @Override
    public String toString() {
        return "Particle{" +
                "position=" + position +
                ", mass=" + mass +
                ", velocity=" + velocity +
                '}';
    }

    public static void main(String[] args) {
        double[] position = new double[]{1.0, 2.0, 3.0};
        double[] velocity = new double[]{4.0, 5.0, 6.0};
        double mass = 10.0;
        Particle particle = new Particle(position, mass, velocity);
        System.out.println("Particle: " + particle);
        System.out.println("Kinetic Energy: " + particle.kineticEnergy());
    }
}
