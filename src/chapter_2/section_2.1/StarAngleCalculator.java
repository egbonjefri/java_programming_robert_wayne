/*
 * Given two stars with angles of declination and right ascension (d1, a1) and
(d2, a2), the angle they subtend is given by the formula
2 arcsin((sin2(d/2) + cos (d1)cos(d2)sin2(a/2))1/2)
where a1 and a2 are angles between -180 and 180 degrees, d1 and d2 are angles
between -90 and 90 degrees, a = a2 - a1, and d = d2 - d1. Write a program to take
the declination and right ascension of two stars as command-line arguments and
print the angle they subtend. Hint : Be careful about converting from degrees to
radians
 */
public class StarAngleCalculator {
    private static double calculateAngle(double ra1, double dec1, double ra2, double dec2) {
        // Convert angles from degrees to radians
        // ra = right ascension
        // dec = declination
        ra1 = Math.toRadians(ra1);
        dec1 = Math.toRadians(dec1);
        ra2 = Math.toRadians(ra2);
        dec2 = Math.toRadians(dec2);
    
        // Calculate the differences
        double deltaRa = ra1 - ra2;
        double deltaDec = dec1 - dec2;
    
        /*
         * Apply the formula:
         * angle = 2 * arcsin(sqrt(sin^2(deltaDec/2) + cos(dec1)*cos(dec2)*sin^2(deltaRa/2)))
         *
         * Where:
         * - deltaDec is the difference in declination between the two stars
         * - deltaRa is the difference in right ascension between the two stars
         * - dec1 and dec2 are the declinations of the first and second stars, respectively
         * - ra1 and ra2 are the right ascensions of the first and second stars, respectively
         * 
         * This formula calculates the angular distance based on the haversine formula,
         * which is more numerically stable for small distances compared to the
         * spherical law of cosines.
         */
        double angle = 2 * Math.asin(Math.sqrt(Math.sin(deltaDec / 2) * Math.sin(deltaDec / 2) +
                                               Math.cos(dec1) * Math.cos(dec2) *
                                               Math.sin(deltaRa / 2) * Math.sin(deltaRa / 2)));
    
        // Convert the result back to degrees
        return Math.toDegrees(angle);
    }
    
    public static void main(String[] args) {
        // Ensure there are enough arguments
        if (args.length < 4) {
            System.out.println("Usage: java StarAngleCalculator <RA1> <Dec1> <RA2> <Dec2>");
            System.exit(1);
        }

        try {
            // Parse the command-line arguments
            double ra1 = Double.parseDouble(args[0]);
            double dec1 = Double.parseDouble(args[1]);
            double ra2 = Double.parseDouble(args[2]);
            double dec2 = Double.parseDouble(args[3]);

            // Calculate the angle
            double angle = calculateAngle(ra1, dec1, ra2, dec2);

            // Print the result
            System.out.printf("The angle subtended is: %.3f degrees%n", angle);
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numbers for right ascension and declination.");
        }
    }
}
