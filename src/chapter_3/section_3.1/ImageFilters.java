
import lib.Picture;
import lib.StdRandom;


import java.awt.Color;

public class ImageFilters {
    /*
 * Write a program that takes two command-line ar-
rotate 30 degrees
guments (the name of an image ﬁle and a real number theta) and rotates the
image theta degrees counterclockwise. To rotate, copy the color of each pixel (si ,
s j) in the source image to a target pixel (ti , t j) whose coordinates are given
by the following formulas:
ti = (s_i - c_i)cos(theta) - (s_j - c_j)sin(theta) + c_i
tj = (s_i - c_i)sin(theta) - (s_j - c_j)cos(theta) + c_i
swirl filter
where (c i, c j) is the center of the image.
 */
    public static void rotate(Picture picture, double theta){
        int width = picture.width();
        int height = picture.height();

        Picture target = new Picture(width, height);
        int c1 = width / 2;
        int c2 = height / 2;

        for(int col = 0; col < width; col++){
            for( int row = 0; row < height; row++){
                Color color = picture.get(col, row);
                // Calculate the target pixel coordinates (ti, tj)
                int t1 = (int) Math.round((col - c1) * Math.cos(theta) - (row - c2) * Math.sin(theta) + c1);
                int t2 = (int) Math.round((col - c1) * Math.sin(theta) + (row - c2) * Math.cos(theta) + c2);
                if (t1 >= 0 && t1 < width && t2 >= 0 && t2 < height) {
                    target.set(t1, t2, color);
                }                
            }
        }

        target.show();
    }

    /*
     * Write a program that takes the name of an image ﬁle as
a command-line argument and applies a glass filter: set each pixel p to the
color of a random neighboring pixel (whose pixel coordinates both differ
from p’s coordinates by at most 5).
     */
    public static void glass(Picture picture, int radius) {
        int width = picture.width();
        int height = picture.height();
        Picture target = new Picture(width, height);

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                // Randomly select a neighboring pixel within 'radius' distance
                int randCol = col + StdRandom.uniformInt(2 * radius + 1) - radius;
                int randRow = row + StdRandom.uniformInt(2 * radius + 1) - radius;

                // Bounds checking to make sure the selected pixel is within the image
                if (randCol < 0) randCol = 0;
                if (randRow < 0) randRow = 0;
                if (randCol >= width) randCol = width - 1;
                if (randRow >= height) randRow = height - 1;

                // Get the color of the randomly selected neighboring pixel
                Color color = picture.get(randCol, randRow);

                // Set the current pixel to the color of the randomly selected neighbor
                target.set(col, row, color);
            }
        }

        target.show();
    }

    /*
     * Write a ﬁlter like those in the previous two exercises
that creates a wave effect, by copying the color of each pixel (s_i , s_j) in the
source image to a target pixel (t_i , t_j), where t_i = s_i and t_j = s_j + 20sin(2pi * s_j / 64).
Add code to take the amplitude (20 in the accompanying ﬁgure) and the
frequency (64 in the accompanying ﬁgure) as command-line arguments.
Experiment with various values of these parameters.
     */
    public static void wave(Picture picture, double amplitude, double frequency){
        int width = picture.width();
        int height = picture.height();

        Picture target = new Picture(width, height);

        for(int col = 0; col < width; col++){
            for( int row = 0; row < height; row++){
                Color color = picture.get(col, row);
                // Calculate the target pixel coordinates (ti, tj)
                int t1 = col;
                int t2 = (int) Math.round(row + (amplitude * Math.sin((2 * col * Math.PI) / frequency)));
                if (t2 >= 0 && t2 < height) {
                    target.set(t1, t2, color);
                }
                               
            }
        }

        target.show();
    }

    /*
     * Creating a swirl effect is similar to rotation, except that
the angle changes as a function of distance to the center of the image. Use
the same formulas as in the previous exercise, but compute theta as a function
of (s_i , s_j), speciﬁcally pi/256 times the distance to the center.
     */
    public static void swirl(Picture picture, double baseTheta) {
        int width = picture.width();
        int height = picture.height();

        Picture target = new Picture(width, height);

        int c1 = width / 2;
        int c2 = height / 2;

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                double distance = Math.sqrt(Math.pow(col - c1, 2) + Math.pow(row - c2, 2));
                double theta = (baseTheta / 256.0) * distance;
                // Convert theta from degrees to radians
                double radTheta = Math.toRadians(theta);

                // Calculate the target pixel coordinates (ti, tj) with dynamic theta
                int t1 = (int) Math.round((col - c1) * Math.cos(radTheta) - (row - c2) * Math.sin(radTheta) + c1);
                int t2 = (int) Math.round((col - c1) * Math.sin(radTheta) + (row - c2) * Math.cos(radTheta) + c2);

                // Bounds checking
                if (t1 >= 0 && t1 < width && t2 >= 0 && t2 < height) {
                    Color color = picture.get(col, row);
                    target.set(t1, t2, color);
                }
            }
        }
        // Assuming there's a method to save or display the 'target' picture
        target.show();
    }

    public static void main(String[] args) {
        String filename = args[0];
        Picture picture = new Picture(filename);
     //   double baseTheta = Double.parseDouble(args[1]); 
        double amplitude = Double.parseDouble(args[1]);
        double frequency = Double.parseDouble(args[2]);
        wave(picture, amplitude, frequency);
    }
}
