import lib.StdOut;
/*
 * Java program that takes two double command-line arguments temperature
and velocity and prints the wind chill. Note :
The formula is not valid if T is larger than 50 in absolute value or if v is larger than
120 or less than 3.
 */

public class WindChillCalculator {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java WindChillCalculator <temperature> <wind_speed>");
            return;
        }

        try {
            double temperature = Double.parseDouble(args[0]);
            double windSpeed = Double.parseDouble(args[1]);

            // Check for valid input range
            if (temperature > 50 || windSpeed < 3 || windSpeed > 120) {
                System.err.println("Error: Invalid input values.");
                System.err.println("Temperature should be <= 50°F and wind speed should be >= 3 mph.");
                return;
            }

            double windChill = calculateWindChill(temperature, windSpeed);

            StdOut.printf("Wind chill: %.2f°F%n", windChill);

        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid input. Please enter numbers only.");
        }
    }

    private static double calculateWindChill(double temperature, double windSpeed) {
        return 35.74 + 0.6215 * temperature - 35.75 * Math.pow(windSpeed, 0.16) 
                      + 0.4275 * temperature * Math.pow(windSpeed, 0.16);
    }
}
