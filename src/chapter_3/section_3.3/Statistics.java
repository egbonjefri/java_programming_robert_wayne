
import lib.StdIn;
import lib.StdOut;

import java.util.ArrayList;
/*
 * Develop a data type for maintaining statistics for a set of real
numbers. Provide a method to add data points and methods that return the num-
ber of points, the mean, the standard deviation, and the variance. Develop two
implementations: one whose instance values are the number of points, the sum
of the values, and the sum of the squares of the values, and another that keeps an
array containing all the points. For simplicity, you may take the maximum number
of points in the constructor. Your ﬁrst implementation is likely to be faster and use
substantially less space, but is also likely to be susceptible to roundoff error. See the
booksite for a well-engineered alternative.
 */


public class Statistics {
    private ArrayList<Double> data;

    public Statistics(){
        this.data = new ArrayList<>();
    }

    public void addDataPoint(double x){
        this.data.add(x);
    }

    public double mean() {
        double xbar = 0.0;
        for(double x: this.data){
            xbar += x;
        }
        return xbar / this.data.size();
    }
    
    public double variance(){
        double mean = this.mean();
        double sumOfSquares = 0.0;
        for (double value : this.data) {
            sumOfSquares += (value - mean) * (value - mean);
        }
        return sumOfSquares / this.data.size() - 1;
    }

    public double stddev(){
        return Math.sqrt(this.variance());
    }

    public static void main(String[] args) {
        Statistics dataset = new Statistics();
        while (!StdIn.isEmpty()) {
            double x = StdIn.readDouble();
            dataset.addDataPoint(x);
        }
        double mean   = dataset.mean();
        double stddev = dataset.stddev();
        double lo = mean - 1.96 * stddev;
        double hi = mean + 1.96 * stddev;

        // print results
        StdOut.println("mean             = " + mean);
        StdOut.println("sample stddev    = " + stddev);
        StdOut.print("95% approximate confidence interval = ");
        StdOut.println("[ " + lo + ", " + hi + " ]");
    }
}
