import lib.Picture;
import lib.StdOut;

/*
 * Write a program that takes from the command line three integers between
0 and 255 that represent red, green, and blue values of a color and then creates and
shows a 256-by-256 Picture in which each pixel has that color.
 */

public class ColorPicture {
    public static void main(String[] args) {
        if (args.length != 3) {
            StdOut.println("Please provide three integers between 0 and 255 representing RGB values.");
            return;
        }

        int red = Integer.parseInt(args[0]);
        int green = Integer.parseInt(args[1]);
        int blue = Integer.parseInt(args[2]);

        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            StdOut.println("RGB values must be between 0 and 255.");
            return;
        }

        Picture picture = new Picture(256, 256);

        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {
                picture.set(x, y, new java.awt.Color(red, green, blue));
            }
        }

        picture.show();
    }
}
