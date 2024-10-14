package lib;

public class LinearRegression {
    private final double slope;
    private final double intercept;
    private final double r2;
    private final double svar0;
    private final double svar1;

    public LinearRegression(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("Array lengths are not equal");
        }
        int n = x.length;

        // First pass: compute means of x and y
        double sumX = 0.0, sumY = 0.0;
        for (int i = 0; i < n; i++) {
            sumX += x[i];
            sumY += y[i];
        }
        double meanX = sumX / n;
        double meanY = sumY / n;

        // Second pass: compute slope and intercept
        double sumXX = 0.0, sumXY = 0.0;
        for (int i = 0; i < n; i++) {
            double dx = x[i] - meanX;
            double dy = y[i] - meanY;
            sumXX += dx * dx;
            sumXY += dx * dy;
        }
        slope = sumXY / sumXX;
        intercept = meanY - slope * meanX;

        // Third pass: compute r^2
        double sumYY = 0.0;
        for (int i = 0; i < n; i++) {
            double dy = y[i] - meanY;
            sumYY += dy * dy;
        }
        double rss = 0.0;  // residual sum of squares
        double tss = sumYY;  // total sum of squares
        for (int i = 0; i < n; i++) {
            double fit = slope * x[i] + intercept;
            rss += (fit - y[i]) * (fit - y[i]);
        }
        r2 = 1.0 - rss / tss;

        // More statistical analysis
        double svar = rss / (n - 2);
        svar1 = svar / sumXX;
        svar0 = svar / n + meanX * meanX * svar1;
    }

    public double intercept() {
        return intercept;
    }

    public double slope() {
        return slope;
    }

    public double R2() {
        return r2;
    }

    public double interceptStdErr() {
        return Math.sqrt(svar0);
    }

    public double slopeStdErr() {
        return Math.sqrt(svar1);
    }

    @Override
    public String toString() {
        return String.format("y = %.2f * x + %.2f  (R^2 = %.3f)", slope, intercept, r2);
    }
}
