import lib.StdIn;
import lib.StdOut;


public class RandomSurferHittingTime {
    public static void main(String[] args) {
        int trials = Integer.parseInt(args[0]);
        //The number of pages
        int n = StdIn.readInt();
        //The number of possible transitions (columns in the transition matrix)
        int m = StdIn.readInt();
              // Read transition matrix.
              double[][] p = new double[n][m];
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
          }

        int[] hits = new int[n];
        int[] totalMoves = new int[n];
        int[] movesSinceLastHit = new int[n];

        int page = 0; // Start at page 0
        for (int t = 0; t < trials; t++) {
            double r = Math.random();
            double sum = 0.0;

            // Find the next page based on the transition probabilities
            for (int j = 0; j < n; j++) {
                sum += p[page][j];
                if (r < sum) {
                    // Update hitting time calculations
                    hits[page]++;
                    totalMoves[page] += movesSinceLastHit[page];
                    movesSinceLastHit[page] = 0; // Reset for this page

                    // Reset the move counter for other pages
                    for (int k = 0; k < n; k++) {
                        if (k != page) movesSinceLastHit[k]++;
                    }

                    page = j;
                    break;
                }
            }
        }

        // Calculate and display hitting times and page ranks
        for (int i = 0; i < n; i++) {
            double hittingTime = hits[i] > 0 ? (double) totalMoves[i] / hits[i] : 0;
            double pageRank = (double) hits[i] / trials;
            StdOut.println("Page " + i + " - Hitting Time: " + hittingTime + ", Page Rank: " + pageRank);
        }
    }
}


/*
 * Pages with higher PageRank are generally expected to have lower hitting times since they are visited more frequently.
* Conversely, pages with lower PageRank might have higher hitting times as they are visited less often.
* However, the structure of the graph and the specific arrangement of links can lead to nuances in this relationship.
 */