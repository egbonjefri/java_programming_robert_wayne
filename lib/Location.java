package lib;
/*
 * Create a data type Location that represents a location on Earth using lati-
tudes and longitudes. Include a method distanceTo() that computes distances
using the great-circle distance (see EXERCISE 1.2.33).
 */

public class Location {

    private double latitude;
    private double longitude;
    private static final double EARTH_RADIUS = 6371; // Earth's radius in kilometers

    public Location() {
        // Initialize to 0,0 if no arguments are provided
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    // Constructor to set latitude and longitude directly
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Generate a random location on the surface of the Earth
    public void generateRandomLocation() {
        // Generate random latitude (-90 to 90) and longitude (-180 to 180)
        this.latitude = -90 + 180 * StdRandom.uniformDouble();
        this.longitude = -180 + 360 * StdRandom.uniformDouble();
    }

// Parse a location string in the format "latitude N/S, longitude E/W"
public void parse(String location) {
    String[] parts = location.split(",\\s*");
    if (parts.length == 2) {
        try {
            String[] latTokens = parts[0].split("\\s+");
            String[] lonTokens = parts[1].split("\\s+");
            
            double latValue = Double.parseDouble(latTokens[0]);
            double lonValue = Double.parseDouble(lonTokens[0]);
            
            if (latTokens.length == 2 && latTokens[1].equalsIgnoreCase("S")) {
                latValue = -latValue; // Southern hemisphere is negative
            }
            if (lonTokens.length == 2 && lonTokens[1].equalsIgnoreCase("W")) {
                lonValue = -lonValue; // Western hemisphere is negative
            }
        // Validate latitude and longitude ranges after parsing
        if (!isValidLatitude(latValue) || !isValidLongitude(lonValue)) {
            throw new IllegalArgumentException("Invalid latitude or longitude values");
        }
            this.latitude = latValue;
            this.longitude = lonValue;
            StdOut.println("Longitude: " + this.longitude + " Latitude: " + this.latitude);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            StdOut.println("Invalid format for location string.");
        }
    } else {
        System.out.println("Invalid format for location string.");
    }
}
    // Helper method to check for valid latitude range (-90 to 90)
    private boolean isValidLatitude(double latitude) {
        return latitude >= -90 && latitude <= 90;
    }

    // Helper method to check for valid longitude range (-180 to 180)
    private boolean isValidLongitude(double longitude) {
        return longitude >= -180 && longitude <= 180;
    }

    // Compute the great circle distance between two locations
    public double distance(Location that) {
        // Convert latitude and longitude from degrees to radians
        double lat1 = Math.toRadians(this.latitude);
        double lon1 = Math.toRadians(this.longitude);
        double lat2 = Math.toRadians(that.latitude);
        double lon2 = Math.toRadians(that.longitude);

        // Compute differences in latitude and longitude
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        // Haversine formula to calculate great circle distance
        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;

        return distance;
    }
    //main method for testing

    public static void main(String args[]){
        Location location = new Location();

        location.parse("25.344 N, 63.5532 W");
    }
}
