/*
 * Write a program that takes the name of an image ﬁle as a command-line
argument and ﬂips the image horizontally.
 */
import lib.Picture;


import java.awt.Color;


public class FlipImage {
    
    public static void flipImage(Picture picture){
        int width = picture.width();
        int height = picture.height();

        Picture flippedImage = new Picture(width, height);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color color = picture.get(row, col);
                // Calculate the new column position for the flipped image
                int newCol = width - 1 - col;
                // Set the pixel color in the new position of the flipped image
                flippedImage.set(row, newCol, color);
            }
        }
        flippedImage.show();
    }



    public static void main(String[] args){
        Picture picture = new Picture(args[0]);
        flipImage(picture);
    }
}
