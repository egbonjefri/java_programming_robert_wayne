/*
 * Write a program that takes k words from the com-
mand line, reads in a sequence of words from standard input, and identiﬁes the
smallest interval of text that contains all of the k words (not necessarily in the same
order). You do not need to consider partial words.
Hint : For each index i, ﬁnd the smallest interval [i, j] that contains the k query
words. Keep a count of the number of times each of the k query words appears.
Given [i, j], compute [i+1, j'] by decrementing the counter for word i. Then, gradu-
ally increase j until the interval contains at least one copy of each of the k words (or,
equivalently, word i).
 */

import lib.StdOut;
import lib.ST;
import lib.StdIn;
import java.awt.Font;
import lib.StdDraw;


public class MultipleWordSearch {
    
    public static void main(String[] args) {
        if (args.length == 0) {
            StdOut.println("Please provide at least one query word.");
            return;
        }

        StringBuilder input = new StringBuilder();
        while (!StdIn.isEmpty()) {
            input.append(StdIn.readLine()).append(" ");
        }
        
        String[] words = input.toString().split("\\s+"); // Split input into words

        // Initialize the drawing canvas
        StdDraw.setCanvasSize(800, 600);
        StdDraw.setXscale(0, words.length + 2); // Set scale according to the number of words
        StdDraw.setYscale(0, 10);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 15));


        // Map to store the frequency of the k query words
        ST<String, Integer> queryCountMap = new ST<>();
        for (String query : args) {
            queryCountMap.put(query, 0);
        }
        int requiredWords = args.length;

        // Sliding window variables
        int minLength = Integer.MAX_VALUE;
        int minStart = -1, minEnd = -1;
        int start = 0, end = 0;
        int matchedWords = 0;

        // Sliding window technique
        while (end < words.length) {
            String endWord = words[end];
            // If end word is in the query list, update its count
            if (queryCountMap.contains(endWord)) {
                int count = queryCountMap.get(endWord);
                queryCountMap.put(endWord, count + 1);

                // If the count reaches 1, we have found one occurrence of the word
                if (count == 0) {
                    matchedWords++;
                }
            }

            // Check if all k query words are matched
            while (matchedWords == requiredWords) {
                // Update the smallest interval if the current window is smaller
                if (end - start + 1 < minLength) {
                    minLength = end - start + 1;
                    minStart = start;
                    minEnd = end;
                }

                // Try shrinking the window from the start
                String startWord = words[start];
                if (queryCountMap.contains(startWord)) {
                    int count = queryCountMap.get(startWord);
                    queryCountMap.put(startWord, count - 1);

                    // If the count drops to zero, we no longer have a complete set of words
                    if (count == 1) {
                        matchedWords--;
                    }
                }
                start++;
                drawWords(words, start, end); // Redraw after shrinking the window
            }

            // Expand the window by moving the end pointer
            end++;
        }

        // Output the smallest interval
        if (minStart != -1) {
            StdOut.println("Smallest interval: [" + minStart + ", " + minEnd + "]");
            StdOut.print("Words: ");
            for (int i = minStart; i <= minEnd; i++) {
                StdOut.print(words[i] + " ");
            }
        } else {
            StdOut.println("No interval found containing all query words.");
        }
    }
        // Method to draw the words and current sliding window
        private static void drawWords(String[] words, int start, int end) {
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
        
            // Draw words as rectangles and text
            for (int i = 0; i < words.length; i++) {
                double x = i + 1; // X position for the word
                StdDraw.rectangle(x, 5, 0.5, 0.5);
                StdDraw.text(x, 5, words[i]);
            }
        
            // Highlight the sliding window in blue
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.rectangle((start + end) / 2.0 + 1, 5, (end - start + 1) / 2.0, 0.5);
        
            // Show the drawn content
            StdDraw.show();
        
            // Slow down the animation by adding a longer pause
            StdDraw.pause(5000); // Pause for 1000 milliseconds (1 second) for a slower animation
        }
        
}
