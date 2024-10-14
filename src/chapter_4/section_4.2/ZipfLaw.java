import lib.Merge;
import lib.StdIn;
import lib.StdOut;
import java.util.HashMap;
import java.util.Map;
/*
 * Find the frequency distribution of words in your favorite novel. Does it
obey Zipf ’s law?

* Zipf's law is an empirical law that describes the frequency distribution of words in a language.
* Named after the linguist George Zipf, it states that the frequency of a word is inversely proportional
* to its rank in the frequency table. 
* This means that the most frequent word will occur approximately twice as often
* as the second most frequent word, three times as often as the third most frequent word, and so on.

To verify Zipf's law in a dataset:
- Rank the words by their frequencies.
- Multiply the frequency of each word by its rank.
- Check if the product is approximately constant across different ranks.
 */

public class ZipfLaw {
    public static void main(String[] args) {
        // Step 1: Read input and tokenize into words
        Map<String, Integer> wordCount = new HashMap<>();
        
        while (!StdIn.isEmpty()) {
            String line = StdIn.readLine();
            String[] words = line.split("\\W+"); // Splitting by non-word characters
            for (String word : words) {
                if (word.isEmpty()) {
                    continue;
                }
                word = word.toLowerCase(); // Normalize to lowercase
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }

        // Convert Map to Arrays
        String[] words = new String[wordCount.size()];
        int[] frequencies = new int[wordCount.size()];
        int index = 0;

        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            words[index] = entry.getKey();
            frequencies[index] = entry.getValue();
            index++;
        }

        // Step 2: Sort the words by their frequencies using merge sort
        Merge.mergeSort(words, frequencies, 0, frequencies.length - 1);

        // Step 3: Print the frequency distribution and verify Zipf's law
        StdOut.printf("%-10s %-10s %-10s\n", "Rank", "Word", "Frequency");
        for (int i = 0; i < words.length; i++) {
            StdOut.printf("%-10d %-10s %-10d\n", i + 1, words[i], frequencies[i]);
        }
        
        // Verify Zipf's law (Frequency ~ 1 / Rank)
        StdOut.println("\nVerifying Zipf's Law (Frequency * Rank should be roughly constant):");
        for (int i = 0; i < frequencies.length; i++) {
            double frequencyRankProduct = frequencies[i] * (i + 1);
            StdOut.printf("Rank: %-10d Word: %-10s Frequency: %-10d Frequency*Rank: %-10.2f\n", 
                    i + 1, words[i], frequencies[i], frequencyRankProduct);
        }
    }

}
