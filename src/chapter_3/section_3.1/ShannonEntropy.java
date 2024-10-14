/*
 * The Shannon entropy mea-
sures the information content of an input
string and plays a cornerstone role in infor-
mation theory and data compression. Given a
string of n characters, let fc be the frequency
of occurrence of character c. The quantity
pc = fc / n is an estimate of the probability that
c would be in the string if it were a random
string, and the entropy is deﬁned to be the sum of the quantity -p_clog_2p_c , over all
characters that appear in the string. The entropy is said to measure the information
content of a string: if each character appears the same number times, the entropy is
at its minimum value among strings of a given length. Write a program that takes
the name of a ﬁle as a command-line argument and prints the entropy of the text
in that ﬁle. Run your program on a web page that you read regularly, a recent paper
that you wrote, and the fruit ﬂy genome found on the website.
 */
import lib.In;
import lib.StdOut;

import java.util.HashMap;
import java.util.Map;

public class ShannonEntropy {

    public static void main(String[] args) {
        if (args.length < 1) {
            StdOut.println("Usage: java ShannonEntropy <filename>");
            return;
        }

        String filename = args[0];
        In in = new In(filename);
        if (!in.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        String content = in.readAll();
        Map<Character, Integer> charCounts = new HashMap<>();
        int totalChars = content.length();

        /**
 * For each character in the content string, this loop updates the character count in the charCounts map.
 * It uses the getOrDefault method to handle characters that are encountered multiple times.
 *
 * - content.toCharArray(): Converts the string content into a character array for iteration.
 * - for (char c : content.toCharArray()): Iterates through each character in the array.
 * - charCounts.getOrDefault(c, 0): Retrieves the current count of the character 'c' from the map.
 *   If 'c' has not been encountered before (i.e., it is not yet a key in the map), this method returns 0.
 *   This ensures that every character is counted correctly, starting from 0 if it's the first occurrence.
 * - charCounts.put(c, charCounts.getOrDefault(c, 0) + 1): Increments the count of character 'c' by 1.
 *   If 'c' was encountered for the first time, it adds 'c' to the map with a count of 1 (0 + 1).
 *   If 'c' was already in the map, it updates the map by increasing its count by 1.
 *
 * Example: For the text "hello":
 * 1. 'h' is encountered first and added to the map with a count of 1.
 * 2. 'e' is then encountered and also added with a count of 1.
 * 3. 'l' is encountered next. The first occurrence adds 'l' with a count of 1. 
 *    Upon encountering 'l' the second time, the count is updated to 2.
 * 4. 'o' is encountered last and added with a count of 1.
 *
 * This method accurately counts the occurrences of each character, no matter how many times they appear in the text.
 */
        for (char c : content.toCharArray()) {
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }

        if (totalChars > 0) {
            double entropy = calculateEntropy(charCounts, totalChars);
            // Print the calculated Shannon entropy
            System.out.println("Shannon Entropy: " + entropy);
        } else {
            System.out.println("File is empty or does not contain readable content.");
        }
    }

    /**
     * Calculates the Shannon entropy of the text.
     * Shannon entropy formula: H = -sum(p(x) * log2(p(x)))
     * where p(x) is the probability of character x.
     *
     * @param charCounts a map of character counts
     * @param totalChars total number of characters in the text
     * @return the Shannon entropy
     */
    private static double calculateEntropy(Map<Character, Integer> charCounts, int totalChars) {
        double entropy = 0.0;
        for (int count : charCounts.values()) {
            double probability = (double) count / totalChars;
            entropy -= probability * (Math.log(probability) / Math.log(2));
        }
        return entropy;
    }
}
