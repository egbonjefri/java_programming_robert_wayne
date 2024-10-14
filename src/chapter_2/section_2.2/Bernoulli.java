/*
 * Modify Bernoulli to animate the bar graph, replotting it
after each experiment, so that you can watch it converge to the Gaussian distribu-
tion. Then add a command-line argument and an overloaded binomial() imple-
mentation to allow you to specify the probability p that a biased coin comes up
heads, and run experiments to get a feeling for the distribution corresponding to a
biased coin. Be sure to try values of p that are close to 0 and close to 1.
 */

 import lib.StdRandom;
 import lib.StdStats;
 import lib.Gaussian;


public class Bernoulli{
    // Simulate flipping a coin n times; return # heads.
    public static int binomial(int n){ 
    int heads = 0;
    for (int i = 0; i < n; i++)
    if (StdRandom.bernoulli(0.5)) heads++;
    return heads;
    }
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]); // number of flips per trial
        int trials = Integer.parseInt(args[1]); // number of trials
        int[] freq = new int[n + 1]; // freq[] experimental results

        for (int t = 0; t < trials; t++) {
            // Increment the frequency for the observed number of heads
            freq[binomial(n)]++;
        }
 
            // Normalize the frequencies to probabilities
            double[] norm = new double[n + 1];
            for (int i = 0; i <= n; i++) {
                norm[i] = (double) freq[i] / trials;
            }

            // Plot the normalized results as bars
            StdStats.plotBars(norm);

            // Calculate Gaussian distribution model
            double mean = n / 2.0;
            double stddev = Math.sqrt(n) / 2.0;
            double[] phi = new double[n + 1];
            for (int i = 0; i <= n; i++) {
                phi[i] = Gaussian.pdf(i, mean, stddev);
            }

            // Plot Gaussian model as lines
            StdStats.plotLines(phi);
        }
    }
