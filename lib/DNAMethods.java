package lib;

public class DNAMethods {

    /**
     * Checks if two strings are circular shifts of one another.
     * 
     * This method leverages the principle that if t is a circular shift of s,
     * then t will appear in the concatenated string s+s. The logic behind this
     * is that by concatenating s with itself, we create a superstring that
     * includes every possible circular shift of s within it. This happens because
     * a circular shift can be visualized as taking some characters from the end
     * of the string and moving them to the beginning without changing their order.
     * The concatenated string, therefore, simulates all possible ways the characters
     * of s can wrap around, making it a comprehensive representation of every
     * circular shift possibility.
     * 
     * @param s The first string.
     * @param t The second string.
     * @return true if t is a circular shift of s, false otherwise.
     */
    public static boolean isCircularShift(String s, String t) {
        // Check if the strings are of the same length, return false if they are not.
        if (s.length() != t.length()) {
            return false;
        }
        
        // Concatenate string s with itself.
        String doubleS = s + s;
        
        // Check if t is a substring of the concatenated string.
        return doubleS.contains(t);
    }
    public static boolean isValidDNA(String s){
        /*
        // Regular expression that matches only strings composed of A, T, C, and G
        return s.matches("[ATCG]+");
        */
        char[] sArray = {'A', 'T', 'C', 'G'};

        for(int i = 0; i < s.length(); i++){
            int count = 0;
            for(int j = 0; j < sArray.length; j++){
                if(s.charAt(i) == sArray[j]){
                    count++;
                }
            }
            if(count == 0) return false;
        }
        return true;
    }
/*
 * Write a function complementWatsonCrick() that takes a DNA string as
its argument and returns its Watson–Crick complement: replace A with T, C with G,
and vice versa.
 */
    public static String complementWatsonCrick(String s) {
        char[] complement = new char[s.length()];
    
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'A':
                    complement[i] = 'T';
                    break;
                case 'T':
                    complement[i] = 'A';
                    break;
                case 'G':
                    complement[i] = 'C';
                    break;
                case 'C':
                    complement[i] = 'G';
                    break;
            }
        }
        return new String(complement);
    }
    /*
     * Write a function isWatsonCrickPalindrome() that takes a DNA string
as its input and returns true if the string is a Watson–Crick complemented palin-
drome, and false otherwise. A Watson–Crick complemented palindrome is a DNA
string that is equal to the reverse of its Watson–Crick complement.
     */

    public static boolean isWatsonCrickPalindrome(String s){
        String complement = complementWatsonCrick(s);
        String reverse = ReverseString.reverse(complement);
        if(reverse.equals(s)) return true;
        return false;
    }
       /**
     * Checks if the given DNA string is a potential gene.
     * A potential gene is defined by the following criteria:
     * - The length of the string is a multiple of 3.
     * - It starts with the start codon (ATG).
     * - It ends with a stop codon (TAA or TAG or TGA).
     * - It has no intervening stop codons.
     * 
     * @param dna The DNA string to be checked.
     * @return true if the DNA string is a potential gene, false otherwise.
     */
    public static boolean isPotentialGene(String dna) {
        // Check if the length of the DNA string is a multiple of 3.
        if (dna.length() % 3 != 0) {
            return false;
        }

        // Check if it starts with the start codon ATG.
        if (!dna.startsWith("ATG")) {
            return false;
        }

        // Check if it ends with one of the stop codons: TAA, TAG, TGA.
        if (!(dna.endsWith("TAA") || dna.endsWith("TAG") || dna.endsWith("TGA"))) {
            return false;
        }

        // Check for intervening stop codons.
        for (int i = 3; i < dna.length() - 3; i += 3) {
            String codon = dna.substring(i, i + 3);
            if (codon.equals("TAA") || codon.equals("TAG") || codon.equals("TGA")) {
                // Found an intervening stop codon.
                return false;
            }
        }

        // All conditions met, it's a potential gene.
        return true;
    }
   /**
     * Finds all potential genes in the given DNA string that meet the minimum length requirement.
     *
     * @param dna The DNA string to search.
     * @param minGeneLength The minimum length of a potential gene.
     * @return A list of all potential genes found.
     */
    public static void findAndPrintGenes(String dna, int minGeneLength) {
        int startIndex = 0;
        while ((startIndex = dna.indexOf("ATG", startIndex)) != -1) {
            int endIndex = findStopCodon(dna, startIndex + 3);
            if (endIndex != -1) {
                String gene = dna.substring(startIndex, endIndex);
                // Verify if the gene meets the minimum length requirement, does not contain internal stop codons, and is a multiple of 3
                if ((gene.length() % 3 == 0) && (gene.length() >= minGeneLength) && !hasInternalStopCodon(gene.substring(3, gene.length() - 3))) {
                    StdOut.println(gene);
                }
                startIndex = endIndex;
            } else {
                // Move past this start codon
                startIndex += 3;
            }
        }
    }

    private static int findStopCodon(String dna, int startIndex) {
        int taaIndex = dna.indexOf("TAA", startIndex);
        int tagIndex = dna.indexOf("TAG", startIndex);
        int tgaIndex = dna.indexOf("TGA", startIndex);

        int minIndex = -1;
        if (taaIndex != -1) minIndex = taaIndex;
        if (tagIndex != -1 && (minIndex == -1 || tagIndex < minIndex)) minIndex = tagIndex;
        if (tgaIndex != -1 && (minIndex == -1 || tgaIndex < minIndex)) minIndex = tgaIndex;

        return (minIndex != -1) ? minIndex + 3 : -1; // Adjust to include the stop codon in the gene
    }

    private static boolean hasInternalStopCodon(String dna) {
        return dna.contains("TAA") || dna.contains("TAG") || dna.contains("TGA");
    }


    public static void removeWhitespace(In inputFile, Out outputFile){
        In in = inputFile;
        Out out = outputFile;
        while (!in.isEmpty()) {
            String line = in.readLine();
            if (line.trim().length() > 0) { // Check if the line is not only whitespace
                out.println(line);
            }
        }  
    }
    public static void main(String[] args){
        if (args.length > 0) {
            String s = args[0];
            System.out.println(isValidDNA(s));
        } else {
            System.out.println("No DNA sequence provided.");
        }
        /*        
        In in = new In();
        Out out = new Out();
        int minGeneLength = 100; // Define a minimum gene length as required

        String dna = in.readAll().replaceAll("\\s+", "").toUpperCase(); // Read all input, remove whitespace and convert to uppercase
        findAndPrintGenes(dna, out, minGeneLength);
         */
    }
}
