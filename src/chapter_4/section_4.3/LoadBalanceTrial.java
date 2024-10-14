import lib.Queue;
import lib.RandomQueue;
import lib.StdOut;


/*
 * Modify LoadBalance to print the average
queue length and the maximum queue length instead of plotting the histogram,
and use it to run simulations for 1 million items on 100,000 queues. Print the aver-
age value of the maximum queue length for 100 trials each with sample sizes 1, 2, 3,
and 4. Do your experiments validate the conclusion drawn in the text about using
a sample of size 2?
 */
public class LoadBalanceTrial {
    public static void main(String[] args) {
        int m = 100000; // Number of queues
        int n = 1000000; // Number of items
        int trials = 100; // Number of trials
        int[] sampleSizes = {1, 2, 3, 4}; // Sample sizes to test

        for (int s : sampleSizes) {
            double totalMaxLength = 0;

            for (int trial = 0; trial < trials; trial++) {
                RandomQueue<Queue<Integer>> servers = new RandomQueue<Queue<Integer>>();

                for (int i = 0; i < m; i++) {
                    servers.enqueue(new Queue<Integer>());
                }

                for (int j = 0; j < n; j++) {
                    Queue<Integer> min = servers.sample();
                    for (int k = 1; k < s; k++) {
                        Queue<Integer> queue = servers.sample();
                        if (queue.size() < min.size()) min = queue;
                    }
                    min.enqueue(j);
                }

                int maxLength = 0;
                long totalLength = 0;
                for (Queue<Integer> queue : servers) {
                    int size = queue.size();
                    totalLength += size;
                    if (size > maxLength) maxLength = size;
                }

                double avgLength = (double) totalLength / m;
                totalMaxLength += maxLength;

                StdOut.printf("Trial %d - Sample size %d: Avg queue length = %.2f, Max queue length = %d%n", 
                                  trial + 1, s, avgLength, maxLength);
            }

            double avgMaxLength = totalMaxLength / trials;
            StdOut.printf("Average max queue length for sample size %d: %.2f%n%n", s, avgMaxLength);
        }
    }
}