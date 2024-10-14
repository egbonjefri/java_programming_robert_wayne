/*
 * Write a program that takes the name of an image ﬁle as a command-line
argument, and creates and shows three Picture objects, one that contains only the
red components, one for green, and one for blue.
 */

import lib.Picture;


import java.awt.Color;




public class SplitImage {
    public static void splitImage(Picture picture){
        int width = picture.width();
        int height = picture.height();

        Picture redImage = new Picture(width, height);
        Picture greenImage = new Picture(width, height);
        Picture blueImage = new Picture(width, height);


        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                Color color = picture.get(row, col);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                redImage.set(row, col, new Color(red, 0, 0));
                greenImage.set(row, col, new Color(0, 0, green));
                blueImage.set(row, col, new Color(0, blue, 0));
                
            }
        }
        redImage.show();
        greenImage.show();
        blueImage.show();
    }



    public static void main(String[] args){
        Picture picture = new Picture(args[0]);
        splitImage(picture);
    }
}
