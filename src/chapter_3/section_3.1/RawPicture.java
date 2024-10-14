/*
 * Write a library of static methods RawPicture with
read() and write() methods for saving and reading pictures from a ﬁle. The
write() method takes a Picture and the name of a ﬁle as arguments and writes
the picture to the speciﬁed ﬁle, using the following format: if the picture is w-by-
h, write w, then h, then w × h triples of integers representing the pixel color values,
in row-major order. The read() method takes the name of a picture ﬁle as an
argument and returns a Picture, which it creates by reading a picture from the
speciﬁed ﬁle, in the format just described. Note: Be aware that this will use up much
more disk space than necessary—the standard formats compress this information
so that it will not take up so much space.
 */

import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import lib.Picture;

public class RawPicture {

    public static Picture read(String filename) {
        try {
            File file = new File(filename);
            if (!file.isFile()) {
                throw new IllegalArgumentException("File does not exist: " + filename);
            }
            BufferedImage image = ImageIO.read(file);
            int width = image.getWidth();
            int height = image.getHeight();
            Picture picture = new Picture(width, height);
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    int color = image.getRGB(col, row); 
                    picture.setRGB(col, row, color); 
                }
            }
            return picture;
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Could not read image: " + filename, ioe);
        }
    }

    public static void write(Picture picture, String filename) {
        int width = picture.width();
        int height = picture.height();
        BufferedImage writeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color color = picture.get(col, row); 
                writeImage.setRGB(col, row, color.getRGB()); 
            }
        }

        File file = new File(filename);
        try {
            ImageIO.write(writeImage, "png", file); 
        } catch (IOException e) {
            throw new RuntimeException("Failed to write image to file: " + filename, e);
        }
    }

    // Main method for demonstration or testing
    public static void main(String[] args) {
        if (args.length > 0) {
            String filename = args[0];
            Picture picture = read(filename);
            picture.show();
            write(picture, "new_" + filename);
        } else {
            System.out.println("No filename provided.");
        }
    }
}
