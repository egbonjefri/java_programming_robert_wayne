import lib.StdOut;
import lib.StdDraw;
import lib.StdArrayIO;
import lib.StdRandom;

public class RandomSurfer {
        public static void main(String[] args) { 
            // Simulate random surfer.
            //The number of trials or moves the surfer will make
        int trials = Integer.parseInt(args[0]);
        //The number of pages
      //  int n = StdIn.readInt();
        //The number of possible transitions (columns in the transition matrix)
     //   int m = StdIn.readInt();
        StdDraw.enableDoubleBuffering();
        double border = 1; // Extra border for labels
        StdDraw.setXscale(-border, trials + border);
        StdDraw.setYscale(-border, trials);
            // Read transition matrix.
       // double[][] p = new double[n][m];
       double[][] p = StdArrayIO.readDouble2D();
       /* 
        for (int i = 0; i < n; i++) {
            double rowSum = 0;
        for (int j = 0; j < n; j++) {
            //array holds the transition probabilities. 
            //It's filled from standard input where each p[i][j]
            // represents the probability of moving from page i to page j
        p[i][j] = StdIn.readDouble();
        rowSum += p[i][j];
        }
            // Normalize if the total isn't 1 (and isn't 0 to avoid division by zero)
    if (rowSum != 1 && rowSum != 0) {
        for (int j = 0; j < n; j++) {
            p[i][j] /= rowSum;
        }
    }
    }*/
        int page = 0;
        // keeps track of how often each page is visited.
        int[] freq = new int[trials];
        for (int t = 0; t < trials; t++){ 
            /*
             * For each trial: A random number r is generated.
             * The program iterates through the possible next pages (j)
             * and adds up the probabilities until the sum exceeds r. 
             * This selects the next page based on the transition probabilities.
             * The frequency for the visited page is incremented.
             */
        for (int j = 0; j < trials; j++){
            page = StdRandom.discrete(p[page]);
            freq[page]++;

        }

        StdDraw.clear();
        for (int i = 0; i < trials; i++){
        StdDraw.filledRectangle(i, freq[i]/2.0, 0.5, freq[i]/2.0);
        // Label for the x-axis representing page ranks
        StdDraw.text(i, 2, "P" + i);
        }
        StdDraw.show();
        StdDraw.pause(10);
}
        for (int i = 0; i < trials; i++)
            // Print page ranks.
        StdOut.printf("%8.5f", (double) freq[i] / trials);
        StdOut.println();
}
}
/*
 * If the total probability in the row p[page] is less than 1, 
 * there's a chance that the random number r falls into the gap 
 * between the total row probability and 1. In this case, the loop:
 * might complete without ever finding a j such that r < sum. 
 * The page variable won't be updated, and the surfer might "get stuck" 
 * on the current page, not reflecting the intended probabilities.
 */

 /*
  * If the total exceeds 1, it could prematurely select a next page, 
  distorting the intended probabilities and potentially causing some pages to never be selected.
  */

  /*
   * To fix this issue, you could normalize the transition probabilities 
   * for each page so that they sum to 1. This normalization should occur right 
   * after the transition matrix is read in.
   */