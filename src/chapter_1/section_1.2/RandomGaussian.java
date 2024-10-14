/*
prints a random number r drawn from the Gaussian distribution
*/

import lib.StdRandom;
import lib.StdOut;

public class RandomGaussian {
    /**
     * Generates a random number from a Gaussian distribution using the Box-Muller transform.
     *
     * @return a random double value from a Gaussian distribution with mean 0 and standard deviation 1
     */
    public static double gaussian() {
        double u1 = StdRandom.uniformDouble();
        double u2 =  StdRandom.uniformDouble();
        double randStdNormal = Math.sqrt(-2.0 * Math.log(u1)) * Math.cos(2.0 * Math.PI * u2);
        return randStdNormal;
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            double randGaussian = gaussian();
            StdOut.println(randGaussian);
        }
    }
}

