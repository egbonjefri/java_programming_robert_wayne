import lib.SET;
import lib.In;
import lib.StdOut;
import lib.StdIn;
import java.util.Arrays;
import java.util.List;

/*
 * Write a SET client SpellChecker that takes as a command-
line argument the name of a ﬁle containing a dictionary of words, and then reads
strings from standard input and prints any string that is not in the dictionary. You
can ﬁnd a dictionary ﬁle on the booksite. Extra credit : Augment your program to
handle common sufﬁxes such as -ing or -ed.

 *  Data files:   https://introcs.cs.princeton.edu/java/data/words.utf-8.txt

 */


public class SpellChecker {
    private static final List<String> COMMON_SUFFIXES = Arrays.asList("ing", "ed", "s", "es", "er", "est");

    public static void main(String[] args) {
        SET<String> dictionary = new SET<>();

        // Read in dictionary of words
        try {
            In dict = new In(args[0]);
            while (!dict.isEmpty()) {
                String word = dict.readString().toLowerCase();
                dictionary.add(word);
            }
            StdOut.println("Done reading dictionary");
        } catch (ArrayIndexOutOfBoundsException e) {
            StdOut.println("Error: Please provide a dictionary file as a command-line argument.");
            return;
        } catch (IllegalArgumentException e) {
            StdOut.println("Error: Unable to read the dictionary file. Please check if the file exists and is readable.");
            return;
        }

        // Read strings from standard input and print out if not in dictionary
        StdOut.println("Enter words, and I'll print out the misspelled ones (type 'exit' to quit):");
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString().toLowerCase();
            if (word.equals("exit")) break;
            
            if (!isSpelledCorrectly(word, dictionary)) {
                StdOut.println("Misspelled word: " + word);
                suggestCorrections(word, dictionary);
            }
        }
    }

    private static boolean isSpelledCorrectly(String word, SET<String> dictionary) {
        if (dictionary.contains(word)) return true;
        
        // Check word without common suffixes
        for (String suffix : COMMON_SUFFIXES) {
            if (word.endsWith(suffix)) {
                String stem = word.substring(0, word.length() - suffix.length());
                if (dictionary.contains(stem)) return true;
            }
        }
        
        return false;
    }

    private static void suggestCorrections(String word, SET<String> dictionary) {
        StdOut.print("Suggestions: ");
        boolean foundSuggestion = false;

        // Simple edit distance of 1
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                String suggestion = word.substring(0, i) + c + word.substring(i + 1);
                if (dictionary.contains(suggestion)) {
                    StdOut.print(suggestion + " ");
                    foundSuggestion = true;
                }
            }
        }

        if (!foundSuggestion) {
            StdOut.print("No close matches found.");
        }
        StdOut.println();
    }
}