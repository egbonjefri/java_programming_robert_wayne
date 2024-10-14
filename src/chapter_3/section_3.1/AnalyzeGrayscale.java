/*
 * Write a program that takes the name of a grayscale image ﬁle as a
command-line argument and uses StdDraw to plot a histogram of the frequency of
occurrence of each of the 256 grayscale intensities.
 */

import lib.Picture;
import lib.StdStats;

import java.awt.Color;

public class AnalyzeGrayscale {
    public static int[] analyzeImage(Picture picture){
        int width = picture.width();
        int height = picture.height();
        int[] intensities = new int[256];
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                Color color = picture.get(row, col);
                // since grayscale image, r=g=b
                int myColor = color.getRed();
                intensities[myColor] += 1;
            }
        }
        return intensities;
    }

    public static void main(String[] args){
        Picture picture = new Picture(args[0]);
        int[] intensities = analyzeImage(picture);
        StdStats.plotBars(intensities);
    }
    
}
