
import lib.Picture;

import java.awt.Color;





public class TileImage {
    /**
 * Tiles an image by replicating it m times vertically and n times horizontally.
 * 
 * The approach works by iterating over every pixel in the original image and copying
 * it to the corresponding locations in the output image, which is sized to accommodate
 * the tiled pattern. For each pixel at (col, row) in the original image, this pixel is
 * set at every corresponding (col + width * j, row + height * i) position in the output image,
 * where i ranges from 0 to m-1 and j ranges from 0 to n-1.
 *
 * This direct mapping ensures that the entire original image is replicated m*n times
 * in the output image, creating the desired tiling effect.
 *
 * @param picture The original Picture object to be tiled.
 * @param n The number of times the image is replicated horizontally.
 * @param m The number of times the image is replicated vertically.
 */
    public static void tile(Picture picture, int n, int m) {
        int width = picture.width();
        int height = picture.height();
        Picture output = new Picture(n * width, m * height);
    
        // Loop over each pixel of the original image
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                Color color = picture.get(col, row);
                // Copy this pixel to all corresponding positions in the output image
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        output.set(width * j + col, height * i + row, color);
                    }
                }
            }
        }
    
        // Display the tiled image
        output.show();
    }
    
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java TileImage <image_path> <n> <m>");
            return;
        }
        Picture picture = new Picture(args[0]);
        int n = Integer.parseInt(args[1]);
        int m = Integer.parseInt(args[2]);
    
        tile(picture, n, m);
    }
}
