
/*
 * Write a program Zoom that
takes the name of an image ﬁle and three numbers
s, x, and y as command-line arguments, and shows
an output image that zooms in on a portion of the
input image. The numbers are all between 0 and 1,
with s to be interpreted as a scale factor and (x, y) as
the relative coordinates of the point that is to be at
the center of the output image. Use this program to
zoom in on a relative or pet in some digital photo on
your computer. (If your photo came from an old cell
phone or camera, you may not be able to zoom in too
close without having visible artifacts from scaling.)
 */

import lib.Picture;
import lib.StdOut;

public class Zoom {
    public static void main(String[] args) {
        // Validate the command-line arguments
        if (args.length != 4) {
            StdOut.println("Usage: java Zoom <image file> <scale factor s> <x coord> <y coord>");
            return;
        }

        // Parse the command-line arguments
        String filename = args[0];
        double s = Double.parseDouble(args[1]);
        double x = Double.parseDouble(args[2]);
        double y = Double.parseDouble(args[3]);

        // Load the image
        Picture picture = new Picture(filename);

        // Calculate the zoomed area dimensions
        int width = (int) (picture.width() * s);
        int height = (int) (picture.height() * s);

        // Calculate the top-left corner of the zoomed area
        int x0 = (int) ((x * picture.width()) - (width / 2.0));
        int y0 = (int) ((y * picture.height()) - (height / 2.0));

        // Create a new picture to hold the zoomed area
        Picture zoomedPicture = new Picture(width, height);

        // Copy pixels from the original picture to the zoomed picture
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                int colSrc = col + x0;
                int rowSrc = row + y0;
                // Ensure the source coordinates are within the original picture
                if (colSrc >= 0 && colSrc < picture.width() && rowSrc >= 0 && rowSrc < picture.height()) {
                    zoomedPicture.set(col, row, picture.get(colSrc, rowSrc));
                }
            }
        }

        // Display the zoomed picture
        zoomedPicture.show();
    }
}
