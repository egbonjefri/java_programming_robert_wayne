    /**
     * The Mercator projection is a cylindrical map projection that 
     * preserves angles and shapes locally, but distorts areas as you move away from the equator.
     * It maps latitude and longitude to rectangular coordinates (x, y). 
     * It is widely used—for example, in nautical charts and in the maps that
     * you print from the web.

// Equations:
// x = R * (λ - λ₀)
// y = R * ln[tan(π/4 + φ/2)]

// Where:
// R is the radius of the Earth
// λ is the longitude of the point to be mapped
// λ₀ is the longitude of the central meridian of the map
// φ is the latitude of the point to be mapped
// ln is the natural logarithm

     * This program Calculates the Mercator projection coordinates for a given latitude and longitude.
     *
     * @param  args  the command-line arguments containing the center longitude, latitude, and longitude
     * @return       void
     */
import lib.StdOut;

public class MercatorProjection {
    public static void main(String[] args) {
        // Check if three command-line arguments are provided
        if (args.length != 3) {
            StdOut.println("Usage: java MercatorProjection <center_longitude> <latitude> <longitude>");
            System.exit(1);
        }

        // Parse command-line arguments
        double centerLongitude = Double.parseDouble(args[0]);
        double latitude = Math.toRadians(Double.parseDouble(args[1]));  // Convert to radians
        double longitude = Math.toRadians(Double.parseDouble(args[2])); // Convert to radians

        // Calculate Mercator projection coordinates
        double x = longitude - Math.toRadians(centerLongitude);
        double y = 0.5 * Math.log((1 + Math.sin(latitude)) / (1 - Math.sin(latitude)));

        // Print the Mercator projection coordinates
        StdOut.printf("Mercator Projection Coordinates (x, y): (%.6f, %.6f)\n", x, y);
    }
}
