/*
 * Write a program that takes the
names of several image ﬁles as command-line argu-
ments and displays them in a slide show (one every
two seconds), using a fade effect to black and a fade
from black between images.
 */
import lib.Picture;
import lib.Fade;
import java.awt.Color;

public class Slideshow {

    // Method to fade an image to or from black
    private static void fadeToFromBlack(Picture picture) {
        int width  = picture.width();
        int height = picture.height();
        int frames = 15; // Number of frames in the fade effect
        Picture black = new Picture(width, height);
        picture.show();
            // Add a pause
            try {
                Thread.sleep(2000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
                System.out.println("Thread was interrupted, Failed to complete operation");
            }
        for (int k = 0; k <= frames; k++) {
            double alpha = 1.0 * k / frames;
            for (int col = 0; col < width; col++) {
                for (int row = 0; row < height; row++) {
                    Color c1 = picture.get(col, row);
                    Color c2 = black.get(col, row);
                    picture.set(col, row, Fade.combine(c2, c1, alpha));
                }
            }
            picture.show();

            // Add a pause
            try {
                Thread.sleep(100); // pauses for 1 second
            } catch (InterruptedException e) {
                // This is how you can handle an InterruptedException, but depending on your specific needs, you might want to do something else here.
                Thread.currentThread().interrupt(); // set the interrupt flag
                System.out.println("Thread was interrupted, Failed to complete operation");
            }

        } 

    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < args.length; i++) {
            Picture picture = new Picture(args[i]);
            fadeToFromBlack(picture);         
        }
    }
}
