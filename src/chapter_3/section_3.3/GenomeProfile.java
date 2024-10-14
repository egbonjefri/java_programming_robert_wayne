/*
 * Write a function hash() that takes as its argument a
k-gram (string of length k) whose characters are all A, C, G, or T and returns an
int value between 0 and 4^k - 1 that corresponds to treating the strings as base-4
numbers with {A, C, G, T} replaced by {0, 1, 2, 3}, respectively. Next, write a func-
tion unHash() that reverses the transformation. Use your methods to create a class
Genome that is like Sketch (PROGRAM 3.3.4), but is based on exact counting of k-
grams in genomes. Finally, write a version of CompareDocuments (PROGRAM 3.3.5)
for Genome objects and use it to look for similarities among the set of genome ﬁles
on the booksite.
 */


import java.util.HashMap;
import java.util.Map;

public class GenomeProfile {
    private String sequence;
    private int k;
    private Map<String, Integer> kgramCounts;

    public GenomeProfile(String sequence, int k) {
        this.sequence = sequence;
        this.k = k;
        this.kgramCounts = countKgrams(); 
    }

    public int getK(){
        return this.k;
    }
    
    private Map<String, Integer> countKgrams() {
        Map<String, Integer> counts = new HashMap<>();
        for (int i = 0; i <= sequence.length() - k; i++) {
            String kgram = sequence.substring(i, i + k);
            counts.put(kgram, counts.getOrDefault(kgram, 0) + 1);
        }
        return counts;
    }

    public double similarTo(GenomeProfile other) {
        if (this.k != other.getK()) {
            throw new IllegalArgumentException("Genomes must have the same k-mer size");
        }

        int intersection = 0;
        int union = 0; 

        // Calculate intersection and union sizes
        for (String kgram : this.kgramCounts.keySet()) {
            int countInThis = this.kgramCounts.get(kgram);
            int countInOther = other.kgramCounts.getOrDefault(kgram, 0);

            intersection += Math.min(countInThis, countInOther);
            union += Math.max(countInThis, countInOther); 
        }

        return ((double) intersection) / union; // Jaccard similarity 
    }
    public static int hash(String kgram) {
        // Mapping for the nucleotides:
        char[] baseMap = {'A', 'C', 'G', 'T'};
        int[] baseValues = {0, 1, 2, 3};

        int hashValue = 0;
        int power = kgram.length() - 1; // Calculate the highest power of 4 initially

        for (char nuc : kgram.toCharArray()) {
            // Find the index of the nucleotide in the map
            int index = -1;
            for (int i = 0; i < baseMap.length; i++) {
                if (baseMap[i] == nuc) {
                    index = i;
                    break; 
                }
            }

            // If nucleotide is valid, calculate 
            if (index != -1) {
                hashValue += baseValues[index] * Math.pow(4, power);
                power--;
            } else {
                throw new IllegalArgumentException("Invalid nucleotide in k-gram: " + nuc);
            }
        }

        return hashValue;
    }
    
    public static String unhash(int hashValue, int k) {
        char[] baseMap = {'A', 'C', 'G', 'T'};
        StringBuilder kgram = new StringBuilder(); 

        while (hashValue > 0) {
            int remainder = hashValue % 4;
            kgram.append(baseMap[remainder]); // Add nucleotide to the front
            hashValue /= 4;
        }

        // Pad with 'A's if necessary 
        while (kgram.length() < k) {
            kgram.append('A');
        }

        return kgram.reverse().toString(); // Reverse for correct order
    }

}
