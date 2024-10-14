package generators;
/*import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;*/
import lib.StdOut;

public class RandomPointGenerator {
    public static void main(String[] args) {
        // Number of coordinates to generate
        int numCoordinates = 50;

        // File path
     //   String filePath = "input_coordinates.txt";

        // Generate random coordinates and write to file
     //   try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (int i = 0; i < numCoordinates; i++) {
                double x = round(randomInRange(-10, 10), 2);
                double y = round(randomInRange(-10, 10), 2);
                double z = round(randomInRange(-10, 10), 2);
              //  writer.printf("%.2f %.2f %.2f%n", x, y, z);
              StdOut.printf("%.2f %.2f %.2f%n", x, y, z);

            }
          //  System.out.println("File generated successfully: " + filePath);
     //   } catch (IOException e) {
      //      System.err.println("Error writing to file: " + e.getMessage());
      //  }
    }

    // Function to generate random number within a range
    private static double randomInRange(double min, double max) {
        return min + (max - min) * Math.random();
    }

    // Function to round a double value to a specified number of decimal places
    private static double round(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
}
