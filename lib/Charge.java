package lib;

import java.util.Objects;

public class Charge {
    private final double rx, ry;   // position
    private double q;              // charge

    public Charge(double x0, double y0, double q0) {
        rx = x0;
        ry = y0;
        q  = q0;
    }

    public double getX() {
        return this.rx;
    }

    public double getY() {
        return this.ry;
    }

    public double getQ() {
        return this.q;
    }

    public void increaseCharge(double delta) {
        this.q += delta;
    }

    /**
     * Calculate the electrostatic potential at a point (x, y) due to this charge.
     * Formula: V = k * q / sqrt((x - rx)^2 + (y - ry)^2)
     * where k is the electrostatic constant, q is the charge,
     * (rx, ry) is the position of the charge, and (x, y) is the query point.
     */
    public double potentialAt(double x, double y) {
        double k = 8.99e09; // electrostatic constant
        // delta distances to query point
        double dx = x - rx;
        double dy = y - ry;
        return k * q / Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        return q + " at (" + rx + ", " + ry + ")";
    }

    /*
     * Override the equals() method for Charge (PROGRAM 3.2.6) so that two
Charge objects are equal if they have identical position and charge value.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Charge that = (Charge) object;
        return Double.compare(that.rx, rx) == 0 &&
               Double.compare(that.ry, ry) == 0 &&
               Double.compare(that.q, q) == 0;
    }
/*
 * Override
the hashCode() method using the Objects.hash() technique described in this
section.
 */
    @Override
    public int hashCode() {
        return Objects.hash(rx, ry, q);
    }

    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        Charge c1 = new Charge(0.51, 0.63, 21.3);
        Charge c2 = new Charge(0.13, 0.94, 81.9);
        StdOut.println(c1);
        StdOut.println(c2);
        double v1 = c1.potentialAt(x, y);
        double v2 = c2.potentialAt(x, y);
        StdOut.println(v1 + v2);
    }
}
