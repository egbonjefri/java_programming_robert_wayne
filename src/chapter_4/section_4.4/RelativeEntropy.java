/*
 * We deﬁne the relative entropy of a text corpus with n words, k of
which are distinct as
*  E = 1 / (n * log(n)) * (p0 * log(k / p0) - p1 * log(k / p1) - ... - pk-1 * log(k / pk-1))
where pi is the fraction of times that word i appears. Write a program that reads in a
text corpus and prints the relative entropy. Convert all letters to lowercase and treat
punctuation marks as whitespace.
 */


import lib.ST;
import lib.StdOut;

public class RelativeEntropy {
    public static String preprocessText(String corpus) {
        // Convert to lowercase
        corpus = corpus.toLowerCase();
        // Replace punctuation with whitespace using regex
        corpus = corpus.replaceAll("[\\W_]+", " ");
        return corpus;
    }

    public static double calculateRelativeEntropy(String corpus) {
        String[] words = corpus.split("\\s+");
        int n = words.length; // Total number of words
        ST<String, Integer> wordCounts = new ST<>();

        // Count word frequencies
        for (String word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        int k = wordCounts.size(); // Number of distinct words
        double E = 0.0;

        // Calculate relative entropy
        for (int count : wordCounts.values()) {
            double p_i = (double) count / n;
            E += p_i * Math.log(k / p_i) / Math.log(2); // Using log base 2
        }

        E /= (n * Math.log(n) / Math.log(2));
        return E;
    }

    public static void main(String[] args) {
        String corpus = "Hello, World! This is a test corpus. Enjoy the coding!";
        String preprocessedCorpus = preprocessText(corpus);
        double relativeEntropy = calculateRelativeEntropy(preprocessedCorpus);
        StdOut.printf("Relative Entropy: %.6f%n", relativeEntropy);
    }
}
