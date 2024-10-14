package generators;

import lib.StdOut;
import lib.StdRandom;
import lib.In;


public class RandomWord {
    public static void main(String[] args) {
        String champion = ""; // The current champion word
        int i = 0; // Counter for the number of words read
        In in = new In();
        while (!in.isEmpty()) {
            String word = in.readString();
            i++;

            // Use Knuth's method to select the word as the champion with probability 1/i
            if (StdRandom.uniformDouble() < 1.0 / i) {
                champion = word;
            }
        }


        // Print the surviving champion word
       StdOut.println("The randomly selected word is: " + champion);
    }
}
/*
 * The Knuth method
 * Start with an array of elements to be shuffled.
 * Begin from the last element in the array (index n-1).
 * Generate a random integer between 0 and the current index (inclusive).
 * Swap the element at the current index with the element at the randomly generated index.
 * Decrement the index and repeat steps 3-5 until reaching the first element (index 0).
*/
