/*
 * Modify Index to make a program IndexByKeyword that takes a ﬁle name
from the command line and makes an index from standard input using only the
keywords in that ﬁle. Note : Using the same ﬁle for indexing and keywords should
give the same result as Index.
 */

import lib.In;
import lib.StdOut;
import lib.Queue;
import lib.ST;

public class IndexByKeyword {
    public static void main(String[] args) {
        if (args.length != 3) {
            StdOut.println("Usage: java IndexByKeyword <minlen> <minocc> <keyword_file>");
            return;
        }

        int minlen = Integer.parseInt(args[0]); // Minimum length
        int minocc = Integer.parseInt(args[1]); // Occurrence threshold
        String keywordFileName = args[2];      
        In in = new In(keywordFileName);


        // Read keywords from file
        String[] keywords = in.readAllStrings();
        if (keywords == null) {
            StdOut.println("Error reading keyword file.");
            return;
        }

        ST<String, Queue<Integer>> wordIndex = new ST<String, Queue<Integer>>();

        // Index words based on keywords
        for (int i = 0; i < keywords.length; i++) {
            String word = keywords[i];
            if (word.length() < minlen) continue;

                if (!wordIndex.contains(word)) {
                    wordIndex.put(word, new Queue<Integer>());
                }
                Queue<Integer> queue = wordIndex.get(word);
                queue.enqueue(i);
            }
        

        // Print words whose occurrence count exceeds the threshold
        for (String s : wordIndex.keys()) {
            Queue<Integer> queue = wordIndex.get(s);
            if (queue.size() >= minocc) {
                StdOut.println(s + ": " + queue);
            }
        }
}
}