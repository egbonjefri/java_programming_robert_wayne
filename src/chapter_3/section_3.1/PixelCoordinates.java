/*
 * Write a program that takes the name of an image ﬁle as a command-line
argument and prints the pixel coordinates of the lower-left corner and the upper-
right corner of the smallest bounding box (rectangle parallel to the x- and y-axes)
that contains all of the non-white pixels.
 */
import lib.StdOut;
import lib.Picture;

import java.awt.Color;



public class PixelCoordinates {
    public static void getCoordinates(Picture picture){
        int width = picture.width();
        int height = picture.height();

        int min_X = width;
        int min_Y = height;
        int max_X = 0;
        int max_Y = 0;

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                Color color = picture.get(col, row);
                if(!isWhite(color)){
                    min_X = Math.min(min_X, col);
                    max_X = Math.max(max_X, col);
                    min_Y = Math.min(min_Y, row);
                    max_Y = Math.max(max_Y, row);
                }
            }
        }
        
        StdOut.println("Lower Coordinates are : " + min_X + " " + min_Y);
        StdOut.println("Upper Coordinates are : " + max_X + " " + max_Y);
    }

    public static boolean isWhite(Color color) {
        // tolerance level to account for pixels that are almost white but not exactly
        final int tolerance = 10;
        return color.getRed() >= 255 - tolerance &&
                color.getGreen() >= 255 - tolerance &&
                color.getBlue() >= 255 - tolerance;
    }

    public static void main(String[] args){
        if (args.length < 1) {
            System.out.println("Usage: java PixelCoordinates <image_file>");
            return;
        }
        try {
            Picture picture = new Picture(args[0]);
            getCoordinates(picture);
        } catch (Exception e) {
            System.out.println("Error loading image.");
        }
    }
    
}
