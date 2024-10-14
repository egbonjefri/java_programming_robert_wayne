/*
 * Write a program that
displays the color study shown at right, which
gives Albers squares corresponding to each of
the 256 levels of blue (blue-to-white in row-
major order) and gray (black-to-white in col-
umn-major order) that were used to print this
book.
 */
import lib.StdDraw;


import java.awt.Color;


public class ColorStudy {

private static Color[] blueScheme = new Color[256];
private static Color[] grayScheme = new Color[256];
    // Method to generate and draw blue-to-white squares
public static void generateBlueToWhiteSquares() {
    for (int i = 0; i < 256; i++) {
        // Calculate the mix of blue and white
        int redComponent = 255 - i;
        int greenComponent = 255 - i;
        Color color = new Color(redComponent, greenComponent, 255);
        blueScheme[i] = color;
    }
}

// Method to generate and draw black-to-white (gray) squares
public static void generateBlackToWhiteSquares() {
    for (int i = 0; i < 256; i++) {
        // For gray, all RGB components are the same
        Color color = new Color(i, i, i);
        grayScheme[i] = color;
    }
}
public static void drawAlbersSquares() {
    int gridSize = 16; // Define the size of the grid (16x16)
    double squareSize = 0.0625; // Calculate the size of each square to fit in the canvas
    for (int row = 0; row < gridSize; row++) {
        for (int col = 0; col < gridSize; col++) {
            int index = row * gridSize + col;
            // Alternate between color schemes based on row and column
            if ((row + col) % 2 == 0) {
                StdDraw.setPenColor(blueScheme[index % 256]); // Use modulo to loop within the 256 colors
            } else {
                StdDraw.setPenColor(grayScheme[index % 256]);
            }
            // Calculate the position for each square
            double x = (col + 0.5) * squareSize;
            double y = 1 - (row + 0.5) * squareSize; 
            StdDraw.filledSquare(x, y, squareSize / 2); 
        }
    }
}
public static void main(String[] args) {
    generateBlueToWhiteSquares();
    generateBlackToWhiteSquares();
    StdDraw.setCanvasSize(600, 600); 
    drawAlbersSquares();
}
}
