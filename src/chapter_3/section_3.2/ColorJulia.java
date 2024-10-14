/*The Julia set for a given complex number c is a set of points re-
lated to the Mandelbrot function. Instead of ﬁxing z and varying c, we ﬁx c and vary
z. Those points z for which the modiﬁed Mandelbrot function stays bounded are in
the Julia set; those for which the sequence diverges to inﬁnity are not in the set. All
points z of interest lie in the 4-by-4 box centered at the origin. The Julia set for c is
connected if and only if c is in the Mandelbrot set! Write a program ColorJulia
that takes two command-line arguments a and b, and plots a color version of the
Julia set for c = a + bi, using the color-table method described in the previous ex-
ercise. */

import lib.Picture;
import lib.Complex;



public class ColorJulia {
    public static void main(String[] args) {
        double a = Double.parseDouble(args[0]); // Real part of c
        double b = Double.parseDouble(args[1]); // Imaginary part of c
        Complex c = new Complex(a, b);
        int n = 800; // image size
        Picture picture = new Picture(n, n);
        double range = 2.0; // Points of interest in the range of -2 to 2 for both real and imaginary parts

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                double re = -range + 2 * range * col / n;
                double im = -range + 2 * range * row / n;
                Complex z = new Complex(re, im);
                int iterations = mandelbrotIterations(z, c, 1000);
                picture.set(col, n - 1 - row, color(iterations));
            }
        }
        picture.show();
    }

    private static int mandelbrotIterations(Complex z, Complex c, int maxIter) {
        for (int t = 0; t < maxIter; t++) {
            if (z.abs() > 2.0) return t; // Escapes to infinity
            z = z.times(z).plus(c);
        }
        return maxIter; // Indicates bounded
    }

    private static java.awt.Color color(int iterations) {
        if (iterations == 1000) return java.awt.Color.BLACK; // Stays bounded
        return java.awt.Color.getHSBColor((float) iterations / 256, 0.75f, 0.75f);
    }
}
