/*
 * Write a program that uses a symbol table to print sum-
mary statistics for each codon in a genome taken from standard input (frequency
per thousand), like the following:

UUU 13.2	UCU 19.6	UAU 16.5	UGU 12.4
UUC 23.5	UCC 10.6	UAC 14.7	UGC 8.0
UUA 5.8	    UCA 16.1	UAA 0.7	    UGA 0.3
UUG 17.6	UCG 11.8	UAG 0.2	    UGG 9.5
CUU 21.2	CCU 10.4	CAU 13.3	CGU 10.5
CUC 13.5	CCC 4.9	    CAC 8.2	    CGC 4.2
CUA 6.5	    CCA 41.0	CAA 24.9	CGA 10.7
CUG 10.7	CCG 10.1	CAG 11.4	CGG 3.7
AUU 27.1	ACU 25.6	AAU 27.2	AGU 11.9
AUC 23.3	ACC 13.3	AAC 21.0	AGC 6.8
AUA 5.9	    ACA 17.1	AAA 32.7	AGA 14.2
AUG 22.3    ACG 9.2	    AAG 23.9	AGG 2.8
GUU 25.7	GCU 24.2	GAU 49.4	GGU 11.8
GUC 15.3	GCC 12.6	GAC 22.1	GGC 7.0
GUA 8.7	    GCA 16.8	GAA 39.8	GGA 47.2


 */

import lib.StdOut;
import lib.ST;
import lib.StdIn;

public class CodonUsageTable {
    public static void main(String[] args) {
        ST<String, Integer> codonCount = new ST<>();
        int totalCodons = 0;

        // Reading genome sequence from standard input
        StdOut.println("Enter genome sequence: ");
        String genome = StdIn.readLine().toUpperCase();

        // Process the genome in groups of three characters (codons)
        for (int i = 0; i <= genome.length() - 3; i += 3) {
            String codon = genome.substring(i, i + 3);
            codonCount.put(codon, codonCount.getOrDefault(codon, 0) + 1);
            totalCodons++;
        }

        // Printing summary statistics (frequency per thousand)
        StdOut.println("Codon Frequencies per Thousand:");
        for (String codon : codonCount) {  
            int count = codonCount.get(codon);
            double frequencyPerThousand = (count / (double) totalCodons) * 1000;
            StdOut.printf("%s %.1f%n", codon, frequencyPerThousand);
        }

    }
}

/*
 * 
 * The genome sequence is input as a string.
* The program processes the genome in groups of three (codons).
* A symbol table keeps track of the number of occurrences of each codon.
* It calculates the frequency of each codon per thousand.
* Finally, it prints the codon and its frequency per thousand in the requested format.
 */