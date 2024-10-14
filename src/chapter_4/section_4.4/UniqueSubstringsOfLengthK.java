import lib.ST;
import lib.StdOut;
import lib.StdIn;

/*
 * Write a program that takes an integer com-
mand-line argument k, reads in text from standard input, and calculates the num-
ber of unique substrings of length k that it contains. For example, if the input
is CGCCGGGCGCG, then there are ﬁve unique substrings of length 3: CGC, CGG, GCG,
GGC, and GGG. This calculation is useful in data compression. Hint : Use the string
method substring(i, i+k) to extract the ith substring and insert into a symbol
table. Test your program on a large genome from the booksite and on the ﬁrst 10
million digits of pi.
 */

public class UniqueSubstringsOfLengthK {

    public static void main(String[] args) {
        // Read the value of k from command-line argument
        if (args.length != 1) {
            StdOut.println("Please provide an integer k as a command-line argument.");
            return;
        }
        int k = Integer.parseInt(args[0]);

        // Read input from standard input
        String input = StdIn.readLine();

        // Check if input is long enough
        if (input.length() < k) {
            StdOut.println("The input string is too short to have any substrings of length " + k);
            return;
        }

        // Create a symbol table to store unique substrings
        ST<String, Integer> st = new ST<>();

        // Extract substrings of length k and insert into the symbol table
        for (int i = 0; i <= input.length() - k; i++) {
            String substring = input.substring(i, i + k);
            if (!st.contains(substring)) {
                st.put(substring, 1); // Only need to store presence, the value is arbitrary
            }
        }

        // Output the number of unique substrings
        StdOut.println("Number of unique substrings of length " + k + ": " + st.size());
    }
}
