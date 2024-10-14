   /*
   *
   * The probability density function (PDF) and cumulative distribution function (CDF) 
   * help us determine probabilities and ranges of probabilities when data follows a 
   * normal distribution. The CDF is the integration, from left to right, of the PDF. 
   * Using these two normal distribution functions, we can calculate different types of 
   * probability estimates from our normally distributed data.
   * 
   * 
   * Add to Gaussian (PROGRAM 2.1.2) an implementation of the three-argument
   * static method pdf(x, mu, sigma) speciﬁed in the API that computes the Gaussian
   * probability density function with a given mean μ and standard deviation σ, based
   * on the formula phi(x,μ,σ) = phi(( x - μ) /σ)/σ. Also add an implementation of the
   * associated cumulative distribution function cdf(z, mu, sigma), based on the for-
   * mula phi(z, ,μ,σ) =  phi(( z - μ) /σ)/σ
    */ 
    package lib;
 
    public class Gaussian{
     /*
     * the probability density function (PDF) is a function whose value at any given sample (or point)
     * in the sample space (the set of possible values taken by the random variable)
     * can be interpreted as providing a relative likelihood that the value of the 
     * random variable would be equal to that sample
     *  This function approximates the pdf for a normal distribution y=1/(sqrt(2*PI)) * e^(-x^2/2) 
     */
    public static double pdf(double x) {
        return Math.exp(-x*x / 2) / Math.sqrt(2 * Math.PI);
    }
    // return pdf(x, mu, sigma) = Gaussian pdf with mean mu and stddev sigma
    public static double pdf(double x, double mu, double sigma) {
        return pdf((x - mu) / sigma) / sigma;
    }

        public static double cdf(double z){
  
            if (z < -8.0) return 0.0;
            if (z > 8.0) return 1.0;
            double sum = 0.0;
            // the terms are derived from the expansion of the exponential function, 
            //which is involved in the (PDF).
            //to find the CDF, we integrate the PDF
            // The first derivative of the CDF (the PDF) will give you a term involving z,
            // which is the linear term. When you integrate the PDF to get the CDF, 
            //the linear term in the PDF becomes a quadratic term in the CDF
            double term = z;

            for (int i = 3; sum != sum + term; i += 2){
            sum = sum + term;
            term = term * z * z / i;
            }
            return 0.5 + pdf(z) * sum;
                }
// return cdf(z, mu, sigma) = Gaussian cdf with mean mu and stddev sigma
    public static double cdf(double z, double mu, double sigma) {
        return cdf((z - mu) / sigma);
    }

    /**
     * Computes the inverse of the Cumulative Distribution Function (CDF)
     * @param probability The probability for which to find the corresponding value
     * @return The value x for which CDF(x) = probability
     */
    public static double inverseCDF(double probability) {
        double minPossibleValue = -8;
        double maxPossibleValue = 8;
        double precision = 0.00000001;
        
        return bisectionSearch(probability, precision, minPossibleValue, maxPossibleValue);
    }

    /**
     * Performs a bisection search to find the value x for which CDF(x) = targetProbability
     * @param targetProbability The target probability we're searching for
     * @param precision The desired precision of the result
     * @param lowerBound The lower bound of the search interval
     * @param upperBound The upper bound of the search interval
     * @return The value x for which CDF(x) = targetProbability
     */
    private static double bisectionSearch(double targetProbability, double precision,
                                          double lowerBound, double upperBound) {
        double midpoint = lowerBound + (upperBound - lowerBound) / 2;

        // If we've reached the desired precision, return the midpoint
        if (upperBound - lowerBound < precision) {
            return midpoint;
        }

        // If CDF(midpoint) > targetProbability, search in the lower half
        if (cdf(midpoint) > targetProbability) {
            return bisectionSearch(targetProbability, precision, lowerBound, midpoint);
        } 
        // Otherwise, search in the upper half
        else {
            return bisectionSearch(targetProbability, precision, midpoint, upperBound);
        }
    }

    // Method to plot the Gaussian distribution and shade the area below value z.
    public static void plotDistribution(double z) {
        StdDraw.setCanvasSize(1024, 512);
        StdDraw.setXscale(-5, 5);
        StdDraw.setYscale(-0.1, 0.5);
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLACK);
        // Draw x and y axes
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.line(-3, 0, 3, 0); // X-axis
        //using linear transformation to scale z values
        double zScaled = -3+(z*6);
        // Labeling x-axis with a range of values
        int xAxisStart = -3;
        int xAxisEnd = 3;
        double labelYOffset = -0.05;
        for (int x = xAxisStart; x <= xAxisEnd; x++) {
            StdDraw.text(x, labelYOffset, String.valueOf(x));
        }

        StdDraw.text(-0.2, 0.45, "Normal Distribution"); // Y-axis Label

        // Draw the curve of the Gaussian distribution
        for (double x = -3; x <= 3; x += 0.002) {
            StdDraw.line(x, 0, x, pdf(x));
        }
        
         // Shade the area under the curve up to z
         StdDraw.setPenColor(StdDraw.GRAY);
         for (double x = -3; x <= zScaled; x += 0.002) {
             StdDraw.filledRectangle(x, pdf(x) / 2, 0.001, pdf(x) / 2);
         }


        // Draw a vertical line at the point z
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.line(zScaled, 0, zScaled, pdf(zScaled));

        // Label z point on x-axis
        StdDraw.text(zScaled, 0.05,  String.format("Φ(%.2f)", z));
        
     }
 
        public static void main(String[] args){
            double x = Double.parseDouble(args[0]);
            double mu = Double.parseDouble(args[1]);
            double sigma = Double.parseDouble(args[2]);
            double cdfValue = cdf((x - mu) / sigma);
            StdOut.printf("%.3f\n", cdfValue);
            plotDistribution(cdfValue);

}
}
// Z-score Formula:
// Z = (X - μ) / σ
// Where:
// Z = Z-score
// X = Value to be standardized
// μ = Mean of the data
// σ = Standard deviation of the data

    /* 
   *  In the Gaussian CDF Taylor series, the loop starts at i=3 because 
   * the initial linear term (z^1) is already accounted for. term = z;
   * The loop then calculates higher-order terms, 
   * starting with the first significant odd power (z^3) after the linear term, 
   * contributing to the accuracy of the CDF approximation.
   * in the series expansion after the linear term. 
   * The loop increments by 2 each time, adding only odd powers of z
   * to efficiently converge to the accurate value of the CDF, 
   * especially for z values close to 0.
    */
    /*
     * The CDF is the integral of the PDF. In Gaussian, the PDF's Taylor series
     *  contains even powers of z (like z^2, z^4, etc.). When integrating the PDF
     *  to get the CDF, each term's power increases by one due to the integration rule: 
     * ∫x^n dx = x^(n+1)/(n+1) + C,where C is the constant of integration. 
     * Thus, even powers in the PDF's series (like z^2) become odd powers in the CDF's series
     *  (like z^3),and their coefficients adjust accordingly. 
     * This is why the Taylor series for the CDF starts with terms one power higher
     *  than the PDF's series,reflecting the cumulative nature of the CDF as the integral of the PDF.

     */
          /*
             * cumulative distribution function calculates the probability 
             * that a random variable will be less 
             * than or equal to a particular value z-score. 
                // Z-score Definition:
             * The Z-score, also known as a standard score, 
             * indicates how many standard deviations an element is from the mean.
             * It is a numerical measurement that describes a value's relationship
             * to the mean of a group of values.
             * The function is approximated for values of z that are not too extreme
             *  (between -8.0 and 8.0).
             * it's rare to encounter data that follows a normal distribution with values far beyond
             *  ±8 standard deviations from the mean
             */