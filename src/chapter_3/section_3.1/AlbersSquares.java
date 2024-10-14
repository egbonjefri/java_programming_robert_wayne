/*
 * Modify AlbersSquares (PROGRAM 3.1.2) to take nine command-line argu-
ments that specify three colors and then draws the six squares showing all the Albers
squares with the large square in each color and the small square in each different
color.
 */
import lib.StdDraw;

import java.awt.Color;


public class AlbersSquares {


    public static void main(String[] args) {
        if (args.length != 9) {
            System.err.println("Usage: java AlberSquares <red1> <green1> <blue1> <red2> <green2> <blue2> <red3> <green3> <blue3>");
            return;
        }

        try {
            double squareSize = 0.2; // Size of each square
            double centerX = 0.25;   // Center X-coordinate for both squares
            double centerY = 0.25;   // Center Y-coordinate for both squares
            double increment = 0.0;

            // Extract and validate color values
            Color firstColor = extractColor(args, 0, "First Square");
            Color secondColor = extractColor(args, 3, "Second Square");
            Color thirdColor = extractColor(args, 6, "Third Square");

            Color[] colors = {firstColor, secondColor, thirdColor};

            // Draw the squares
            StdDraw.enableDoubleBuffering(); // Enable double buffering for smoother drawing
            StdDraw.clear();                 // Clear the canvas

            for(int i = 0; i < colors.length; i++){
                int r = (int) Math.floor(Math.random()*colors.length);
                while(colors[r] == colors[i]){
                     r = (int) Math.floor(Math.random()*colors.length); 
                }
            
            //Left Square
            // Draw first square (larger)
            drawSquare(colors[i], centerX, (centerY + increment), squareSize);
            // Draw second square (smaller on top)
            drawSquare(colors[r], centerX, (centerY + increment), squareSize / 2);
            
            //Right Square
            // Draw first square (larger)
            drawSquare(colors[r], centerX * 3, (centerY + increment), squareSize);
            // Draw second square (smaller on top)
            drawSquare(colors[i], centerX * 3, (centerY + increment), squareSize / 2);
            increment += 0.25;
            }

            StdDraw.show(); // Display the final image
        } catch (NumberFormatException e) {
            System.err.println("Invalid color value provided: " + e.getMessage());
            System.exit(1);
        }
    }

    private static Color extractColor(String[] args, int startIndex, String colorName) throws NumberFormatException {
        int red = Integer.parseInt(args[startIndex]);
        int green = Integer.parseInt(args[startIndex + 1]);
        int blue = Integer.parseInt(args[startIndex + 2]);

        validateColorComponent(red, colorName, "Red");
        validateColorComponent(green, colorName, "Green");
        validateColorComponent(blue, colorName, "Blue");

        return new Color(red, green, blue);
    }

    private static void validateColorComponent(int value, String colorName, String component) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException(colorName + " " + component + " value must be between 0 and 255.");
        }
    }

    private static void drawSquare(Color color, double centerX, double centerY, double size) {
        StdDraw.setPenColor(color);
        StdDraw.filledSquare(centerX, centerY, size / 2);
    }
}


