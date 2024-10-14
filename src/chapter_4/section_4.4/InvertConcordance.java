/*
 * A concordance is an alphabetical list of the words in a text that gives all word
positions where each word appears. Thus, java Index 0 0 produces a concor-
dance. In a famous incident, one group of researchers tried to establish credibility
while keeping details of the Dead Sea Scrolls secret from others by making public
a concordance. Write a program InvertConcordance that takes a command-line
argument n, reads a concordance from standard input, and prints the ﬁrst n words
of the corresponding text on standard output.
 */
import lib.StdOut;
import lib.ST;
import lib.StdIn;

public class InvertConcordance {
    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("Usage: java InvertConcordance <n>");
            System.exit(1);
        }

        int n = Integer.parseInt(args[0]);
        ST<String, Integer> wordPositions = new ST<>();

        while (!StdIn.isEmpty()) {
            String line = StdIn.readLine();
            String[] parts = line.split(":");
            if (parts.length != 2) continue;

            String word = parts[0].trim();
            String[] positions = parts[1].trim().split("\\s*->\\s*");

            for (String pos : positions) {
                int position = Integer.parseInt(pos);
                wordPositions.put(word, position);
            }
        }
        int count = 0;
        for (String s : wordPositions) {
            if (count >= n) break;
            StdOut.println(s);
            count++;
        }
        StdOut.println();
    }
}