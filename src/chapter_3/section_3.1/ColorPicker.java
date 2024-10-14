/*
 * Write a program that takes as command-line arguments the name of an
image ﬁle and the pixel coordinates of a rectangle within the image; reads from
standard input a list of Color values (represented as triples of int values); and
serves as a ﬁlter, printing those color values for which all pixels in the rectangle are
background/foreground compatible. (Such a ﬁlter can be used to pick a color for
text to label an image.)
 */
import lib.StdIn;
import lib.Picture;


import java.awt.Color;

public class ColorPicker {

    public static double luminance(Color color){
        return 0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue();
    }

    public static boolean isCompatible(Color background, Color foreground){
        double lum_diff = Math.abs(luminance(foreground) - luminance(background));
        return lum_diff >= 128.0;
    }

    public static void filterColor(Picture picture, int x1, int y1, int x2, int y2){
        while (!StdIn.isEmpty()) {
            int r = StdIn.readInt();
            int g = StdIn.readInt();
            int b = StdIn.readInt();
            Color color = new Color(r, g, b);
            boolean compatible = true;
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    Color pixelColor = picture.get(x, y);
                    if (!isCompatible(pixelColor, color)) {
                        compatible = false;
                        break;
                    }
                }
                if (!compatible) break;
            }
            
            if (compatible) {
                System.out.println("Compatible Color: " + r + " " + g + " " + b);
            }
        }
    }
    public static void main(String[] args){
        if (args.length < 5) {
            System.out.println("Usage: java ColorPicker <image_file> <x1> <y1> <x2> <y2>");
            return;
        }
        try {
            Picture picture = new Picture(args[0]);
            int x1 = Integer.parseInt(args[1]);
            int y1 = Integer.parseInt(args[2]);
            int x2 = Integer.parseInt(args[3]);
            int y2 = Integer.parseInt(args[4]);
            filterColor(picture, x1, y1, x2, y2);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
