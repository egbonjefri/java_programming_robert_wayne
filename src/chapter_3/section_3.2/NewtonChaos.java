/*
 * The polynomial f(z) = z^4 - 1 has four roots:
at 1, -1, i, and -i. We can ﬁnd the roots using Newton’s method in the complex
plane: z_k+1 = z_k - f(z_k) / f'(z_k).
 Here, f(z) = z^4 - 1 and f'(z) = 4z3. The method
converges to one of the four roots, depending on the starting point z0. Write a
Complex and Picture client NewtonChaos that takes a command-line argument n
and creates an n-by-n picture corresponding to the square of size 2 centered at the
origin. Color each pixel white, red, green, or blue according to which of the four
roots the corresponding complex number converges (black if no convergence after
100 iterations).
 */
import lib.Picture;
import lib.Complex;

public class NewtonChaos {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]); // size of the picture n-by-n
        double x0 = -1.0, x1 = 1.0, y0 = -1.0, y1 = 1.0;
        int maxIter = 100;
        Picture picture = new Picture(n, n);

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                double re = x0 + col * (x1 - x0) / n;
                double im = y0 + row * (y1 - y0) / n;
                Complex z = new Complex(re, im);
                Complex root = findRoot(z, maxIter);
                if (root == null) {
                    picture.set(col, row, java.awt.Color.BLACK);
                } else {
                    picture.set(col, row, getColor(root));
                }
            }
        }
        picture.show();
    }

    private static Complex findRoot(Complex z, int maxIter) {
        Complex[] roots = {new Complex(1, 0), new Complex(-1, 0),
                           new Complex(0, 1), new Complex(0, -1)};
        for (int i = 0; i < maxIter; i++) {
            Complex f = z.times(z).times(z).times(z).minus(new Complex(1, 0));
            Complex df = z.times(z).times(z).times(new Complex(4, 0));
            if (df.abs() == 0) break;
            z = z.minus(f.divides(df));
            for (Complex root : roots) {
                if (z.minus(root).abs() <= 0.001) return root;
            }
        }
        return null;
    }

    private static java.awt.Color getColor(Complex root) {
        if (root.equals(new Complex(1, 0))) {
            return java.awt.Color.WHITE;
        } else if (root.equals(new Complex(-1, 0))) {
            return java.awt.Color.RED;
        } else if (root.equals(new Complex(0, 1))) {
            return java.awt.Color.GREEN;
        } else if (root.equals(new Complex(0, -1))) {
            return java.awt.Color.BLUE;
        } else {
            return java.awt.Color.BLACK; // should not happen
        }
    }
}
