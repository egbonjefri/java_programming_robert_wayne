import lib.Charge;
import lib.Picture;


public class ChargeClient {
    public static void main(String[] args) {
        Charge[] a = new Charge[3];
        a[0] = new Charge(0.4, 0.6, 50);
        a[1] = new Charge(0.5, 0.5, -5);
        a[2] = new Charge(0.6, 0.6, 50);

        int width = 500;
        int height = 500;
        Picture picture = new Picture(width, height);

        for (int t = 0; t < 100; t++) {
            // Compute the picture for each time step
            computePicture(picture, a);
            picture.show();

            // Decrease the charge of a[1]
            a[1].increaseCharge(-2.0);

            // Pause for a short time to see the changes
            try {
                Thread.sleep(100); // 100 milliseconds pause
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void computePicture(Picture picture, Charge[] charges) {
        int width = picture.width();
        int height = picture.height();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double x = (double) i / width;
                double y = (double) j / height;
                double potential = 0.0;

                for (Charge charge : charges) {
                    potential += charge.potentialAt(x, y);
                }

                // Normalize potential for visualization
                int colorValue = getColorValue(potential);
                picture.set(i, height - j - 1, new java.awt.Color(colorValue, colorValue, colorValue));
            }
        }
    }

    private static int getColorValue(double potential) {
        double scale = 1e10; // Scaling factor to visualize potential
        int value = (int) (Math.abs(potential) / scale);
        if (value > 255) value = 255; // Clamp to max color value
        return 255 - value; // Invert to make higher potentials darker
    }
}