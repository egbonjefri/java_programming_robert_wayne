/*
 * java program that takes three double command-line
arguments x, y, and z and prints true if the values are strictly ascending or de-
scending ( x < y < z or x > y > z ), and false otherwise
 */

import lib.StdOut;
public class OrderCheck {
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        double z = Double.parseDouble(args[2]);
        boolean isAscending = x < y && y < z;
        boolean isDescending = x > y && y > z;
        boolean isOrdered = isAscending || isDescending;
        StdOut.println(isOrdered);
    }
}
