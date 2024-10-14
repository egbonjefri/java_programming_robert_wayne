package lib;

/*
 * Write a library Matrix that implements the following API:
public class Matrix
double dot(double[] a, double[] b)                      vector dot product
double[][] multiply(double[][] a, double[][] b)         matrix–matrix product
double[][] transpose(double[][] a)                      transpose
double[] multiply(double[][] a, double[] x)             matrix–vector product
double[] multiply(double[] x, double[][] a)             vector–matrix product
 */
public class Matrix {

    // Computes the dot product of two vectors.
    public static double dot(double[] a, double[] b) {
        if (a.length != b.length) throw new IllegalArgumentException("Vector lengths must be equal.");
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }

    // Computes the product of two matrices.
    public static double[][] multiply(double[][] a, double[][] b) {
        int aRows = a.length, aCols = a[0].length, bCols = b[0].length;
        if (aCols != b.length) throw new IllegalArgumentException("Matrix inner dimensions must agree.");
        double[][] result = new double[aRows][bCols];

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bCols; j++) {
                for (int k = 0; k < aCols; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    // Transposes a matrix.
    public static double[][] transpose(double[][] a) {
        int rows = a.length, cols = a[0].length;
        double[][] result = new double[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = a[i][j];
            }
        }
        return result;
    }

    // Multiplies a matrix and a vector.
    public static double[] multiply(double[][] a, double[] x) {
        int rows = a.length, cols = a[0].length;
        if (x.length != cols) throw new IllegalArgumentException("Matrix and vector dimensions must agree.");
        double[] result = new double[rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i] += a[i][j] * x[j];
            }
        }
        return result;
    }

    // Multiplies a vector and a matrix.
    public static double[] multiply(double[] x, double[][] a) {
        int aRows = a.length, aCols = a[0].length;
        if (x.length != aRows) throw new IllegalArgumentException("Matrix and vector dimensions must agree.");
        double[] result = new double[aCols];

        for (int j = 0; j < aCols; j++) {
            for (int i = 0; i < aRows; i++) {
                result[j] += x[i] * a[i][j];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int trials = Integer.parseInt(args[0]);
        double[][] p = StdArrayIO.readDouble2D();
        double[] ranks = new double[p.length];
        ranks[0] = 1.0;
        for (int t = 0; t < trials; t++)
        ranks = Matrix.multiply(ranks, p);
        StdArrayIO.print(ranks);
}
}
