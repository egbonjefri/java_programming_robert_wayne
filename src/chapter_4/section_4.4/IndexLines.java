/*
 * Modify Index to make a program IndexLines that considers only consecu-
tive sequences of letters as keys (no punctuation or numbers) and uses line number
instead of word position as the value. This functionality is useful for programs, as
follows:
% java IndexLines 6 0 < Index.java
continue 12
enqueue 15
Integer 4 5 7 8 14
parseInt 4 5
println 22
 */
import lib.Queue;
import lib.StdOut;
import lib.StdIn;
import lib.ST;

public class IndexLines {
    public static void main(String[] args) {
        try {
            // Validate arguments
            if (args.length != 2) {
                throw new IllegalArgumentException("Usage: java IndexLines <minlen> <minocc>");
            }

            int minlen;
            int minocc;
            try {
                minlen = Integer.parseInt(args[0]); // minimum length
                minocc = Integer.parseInt(args[1]); // occurrence threshold
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Both arguments must be integers.");
            }

            ST<String, Queue<Integer>> wordIndex = new ST<String, Queue<Integer>>();

            for (int i = 1; !StdIn.isEmpty(); i++) {
                String[] words = StdIn.readLine().split("[^a-zA-Z]+");

                for (int j = 0; j < words.length; j++) {
                    if (words[j].length() < minlen) continue;
                    if (!wordIndex.contains(words[j])) {
                        wordIndex.put(words[j], new Queue<Integer>());
                    }
                    Queue<Integer> queue = wordIndex.get(words[j]);
                    queue.enqueue(i);
                }
            }

            // Print words whose occurrence count exceeds threshold.
            for (String s : wordIndex) {
                Queue<Integer> queue = wordIndex.get(s);
                if (queue.size() >= minocc) {
                    StdOut.println(s + ": " + queue);
                }
            }

        } catch (IllegalArgumentException e) {
            StdOut.println("Error: " + e.getMessage());
        } catch (Exception e) {
            StdOut.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
