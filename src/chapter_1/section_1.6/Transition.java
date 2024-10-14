
import lib.StdIn;
import lib.StdOut;

public class Transition {
    public static void main(String[] args) {
        int n = StdIn.readInt();
        int[][] counts = new int[n][n];
        int[] outDegrees = new int[n];

        while (!StdIn.isEmpty()) {
            int i = StdIn.readInt();
            int j = StdIn.readInt();
            if (counts[i][j] == 0) {
                outDegrees[i]++;
                counts[i][j] ++;
            }
        }

        StdOut.println(n + " " + n);
        for (int i = 0; i < n; i++) {
            // Check if the page has no outgoing links (dangling node)
            if (outDegrees[i] == 0) {
                // Assign equal probability to transition to any page
                for (int j = 0; j < n; j++) {
                    StdOut.printf("%8.5f", 1.0 / n);
                }
            } else {
                // Calculate probabilities as before
                for (int j = 0; j < n; j++) {
                    double p = 0.9 * counts[i][j] / outDegrees[i] + 0.1 / n;
                    StdOut.printf("%8.5f", p);
                }
            }
            StdOut.println();
        }
    }
}
/*
 * By adding links from page 23 to every other page, 
 * you essentially increase the importance of page 23 in the graph. 
 * The PageRank algorithm interprets outbound links as 'votes' or endorsements. 
 * With page 23 linking to every page, it's as if it's endorsing every other page, 
 * which might increase its perceived importance. 
 * Dilution of Page 23's Outbound Influence: Although page 23 will have links to every other page,
 * each individual link's influence might be diluted because its rank is now divided among more pages. 
 * The impact of page 23's link to any single page will be smaller.
 * Potential Increase in Ranks of Other Pages: Pages that previously had few or 
 * no inbound links might see an increase in their rank due to the new link from page 23.
 * Overall Network Effect: The changes might have a cascading effect throughout the network. 
 * Pages linked by those that page 23 now links to might also see changes in their ranks, albeit to a lesser degree.
 */

/*
 * If you add links to page 23 from every other page, rather than from page 23 to every other page,
 * the effects on the PageRank calculation would be quite different.
 * Increased Rank of Page 23: Page 23 will likely see a significant increase in its PageRank. 
 * This is because PageRank operates on the principle that a page is important if it 
 * is linked to by other important pages. By having every other page link to it, 
 * page 23 is effectively being 'endorsed' by the entire network, boosting its perceived importance.
 * 
 * Dilution of Rank for Other Pages: The pages linking to page 23 might see a slight
 * decrease in their own ranks. This is because their rank is now being distributed to more pages, 
 * including page 23. The effect will be more pronounced for pages that didn't 
 * have many outgoing links before the addition.
 * 
 * Reduced Influence of Existing Links: For pages with many outgoing links, 
 * the addition of one more link (to page 23) might not dramatically change 
 * their outgoing influence, but it does mean that the influence they exert through each link is slightly diluted.
 * 
 * Potential Cascading Effects: The increase in PageRank for page 23 might have secondary effects. 
 * Pages that page 23 links to might see a small increase in their ranks due 
 * to receiving a link from a now-higher-ranked page.
 * 
 */