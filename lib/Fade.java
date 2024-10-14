
package lib;

import java.awt.Color;

public class Fade {

    public static Color combine(Color c1, Color c2, double alpha) {
        int r = (int) (alpha * c1.getRed() + (1 - alpha) * c2.getRed());
        int g = (int) (alpha * c1.getGreen() + (1 - alpha) * c2.getGreen());
        int b = (int) (alpha * c1.getBlue() + (1 - alpha) * c2.getBlue());
        return new Color(r, g, b);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]); // # of frames for the transition
        Picture picture1 = new Picture(args[1]); // begin picture
        Picture picture2 = new Picture(args[2]); // end picture

        if (picture1.width() != picture2.width() || picture1.height() != picture2.height()) {
            throw new IllegalArgumentException("Pictures must have the same dimensions.");
        }

        int width = picture1.width();
        int height = picture1.height();
        Picture picture = new Picture(width, height);

        for (int k = 0; k <= n; k++) {
            double alpha = 1.0 * k / n;
            for (int col = 0; col < width; col++) {
                for (int row = 0; row < height; row++) {
                    // Adjust alpha based on the row to create a vertical gradient effect
                    double verticalAlpha = calculateVerticalAlpha(row, height, alpha);

                    Color c1 = picture1.get(col, row);
                    Color c2 = picture2.get(col, row);
                    Color blendedColor = combine(c1, c2, verticalAlpha);
                    picture.set(col, row, blendedColor);
                }
            }
            picture.show();
            try {
                Thread.sleep(1000 / n); // Adjust sleep time based on the total number of frames
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted, Failed to complete operation");
            }
        }
    }

    // A method to calculate vertical alpha blending, creates a smooth transition effect
    private static double calculateVerticalAlpha(int row, int height, double alpha) {
        // Example of a top-to-bottom gradient transition effect
        double rowFactor = (double) row / height;
        return alpha * rowFactor + (1 - alpha) * (1 - rowFactor);
        // For a bottom-to-top transition, invert the rowFactor: use (1 - rowFactor) instead
    }
}
