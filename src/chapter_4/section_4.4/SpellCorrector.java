import lib.SET;
import lib.In;
import lib.StdOut;
import java.util.Arrays;
import java.util.List;
import lib.ST;


/*
 * Write an ST client SpellCorrector that serves as a ﬁl-
ter that replaces commonly misspelled words on standard input with a suggest-
ed replacement, printing the result to standard output. Take as a command-line
argument the name of a ﬁle that contains common misspellings and corrections.
You can ﬁnd an example on the booksite.
 *  Dependencies: ST.java In.java
 *  Data files:   https://introcs.cs.princeton.edu/java/44st/misspellings.txt
 *                https://introcs.cs.princeton.edu/java/44st/document.txt
 *
 */
public class SpellCorrector {
    private static final List<String> COMMON_SUFFIXES = Arrays.asList("ing", "ed", "s", "es", "er", "est");

    public static void main(String[] args) {
        if (args.length != 3) {
            StdOut.println("Usage: java SpellCorrector <dictionary> <misspellings> <input>");
            return;
        }

        SET<String> dictionary = new SET<>();
        ST<String, String> misspellings = new ST<>();

        // Read dictionary
        try {
            In dictFile = new In(args[0]);
            while (!dictFile.isEmpty()) {
                dictionary.add(dictFile.readString().toLowerCase());
            }
            StdOut.println("Dictionary loaded.");
        } catch (Exception e) {
            StdOut.println("Error reading dictionary file: " + e.getMessage());
            return;
        }

        // Read misspellings
        try {
            In misspellingsFile = new In(args[1]);
            while (misspellingsFile.hasNextLine()) {
                String word = misspellingsFile.readString().toLowerCase();
                String correction = misspellingsFile.readLine().trim().toLowerCase();
                misspellings.put(word, correction);
            }
            StdOut.println("Misspellings loaded.");
        } catch (Exception e) {
            StdOut.println("Error reading misspellings file: " + e.getMessage());
            return;
        }

        // Process input text
        try {
            In inputFile = new In(args[2]);
            while (inputFile.hasNextLine()) {
                String line = inputFile.readLine();
                String[] words = line.split("\\s+");
                for (int i = 0; i < words.length; i++) {
                    String word = words[i].toLowerCase();
                    String originalWord = words[i];
                    
                    if (misspellings.contains(word)) {
                        StdOut.print(misspellings.get(word));
                    } else if (isSpelledCorrectly(word, dictionary)) {
                        StdOut.print(originalWord);
                    } else {
                        StdOut.print(originalWord + " [MISSPELLED]");
                        suggestCorrections(word, dictionary);
                    }
                    
                    if (i < words.length - 1) StdOut.print(" ");
                }
                StdOut.println();
            }
        } catch (Exception e) {
            StdOut.println("Error processing input file: " + e.getMessage());
        }
    }

    private static boolean isSpelledCorrectly(String word, SET<String> dictionary) {
        if (dictionary.contains(word)) return true;
        
        for (String suffix : COMMON_SUFFIXES) {
            if (word.endsWith(suffix)) {
                String stem = word.substring(0, word.length() - suffix.length());
                if (dictionary.contains(stem)) return true;
            }
        }
        
        return false;
    }

    private static void suggestCorrections(String word, SET<String> dictionary) {
        StdOut.print(" Suggestions: ");
        boolean foundSuggestion = false;

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