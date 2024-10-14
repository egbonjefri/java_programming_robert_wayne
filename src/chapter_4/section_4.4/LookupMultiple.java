
/*
 * Modify Lookup to make a program LookupMultiple that handles multiple
values having the same key by storing all such values in a queue, as in Index, and
then printing them all on a get request, as follows:
% java LookupMultiple amino.csv 3 0
Leucine
TTA TTG CTT CTC CTA CTG
 */
import lib.ST;
import lib.In;
import lib.StdOut;
import lib.Queue;

public class LookupMultiple {
    public static void main(String[] args) {
        try {
            // Check for correct number of arguments
            if (args.length != 4) {
                throw new IllegalArgumentException("Usage: java LookupMultiple <filename> <valuePosition> <minOcc> <aminoString>");
            }

            String filename = args[0];
            int valuePosition = Integer.parseInt(args[1]);
            int minOcc = Integer.parseInt(args[2]);
            String aminoString = args[3];

            // Check for valid valuePosition
            if (valuePosition < 0) {
                throw new IllegalArgumentException("valuePosition must be non-negative");
            }

            ST<String, Queue<String>> dictionary = new ST<>();

            // Read and process the file
            In in = new In(filename);
            while (!in.isEmpty()) {
                String[] tokens = in.readLine().split(",");
                if (valuePosition < tokens.length) {
                    String key = tokens[valuePosition];
                    String value = tokens[0];
                    
                    if (!dictionary.contains(key)) {
                        dictionary.put(key, new Queue<String>());
                    }
                    dictionary.get(key).enqueue(value);
                }
            }

            // Look up the amino string
            if (dictionary.contains(aminoString)) {
                Queue<String> queue = dictionary.get(aminoString);
                if (queue.size() >= minOcc) {
                    StdOut.println(aminoString);
                    for (String str : queue) {
                        StdOut.println(str);
                    }
                } else {
                    StdOut.println("Not enough occurrences for " + aminoString);
                }
            } else {
                StdOut.println("No entries found for " + aminoString);
            }

        } catch (IllegalArgumentException e) {
            StdOut.println("Error: " + e.getMessage());
        }
    }
}

