/*
 * Develop a data type to store the genome of an organism. Biologists
often abstract the genome to a sequence of nucleotides (A, C, G, or T). The data type
should support the methods addNucleotide(char c) and nucleotideAt(int i),
as well as isPotentialGene() (see PROGRAM 3.1.1). Develop three implementa-
tions. First, use one instance variable of type String, implementing addCodon()
with string concatenation. Each method call takes time proportional to the length
of the current genome. Second, use an array of characters, doubling the length of
the array each time it ﬁlls up. Third, use a boolean array, using two bits to encode
each codon, and doubling the length of the array each time it ﬁlls up.
 */
import lib.DNAMethods;

public class Genome {

    private String genome;

    public Genome(){
        this.genome = "";
    }

    public void addNucleotide(char c){
        if (String.valueOf(c).matches("(?i)[ATCG]+")){
            this.genome = genome + Character.toUpperCase(c);
        }
        
        else throw new RuntimeException("Illegal nucleotide");
    }

    public Character nucleotideAt(int i) {
        if(i > genome.length()) throw new IndexOutOfBoundsException();
        return genome.charAt(i);
    }
    public boolean isPotentialGene(String dna) {
        return DNAMethods.isPotentialGene(dna); 
    }

    public void addCodon(String codon){
        if (!codon.matches("[ATCG]+") && codon.length() != 3) {
            throw new IllegalArgumentException("Codon contains invalid DNA characters or Length of Codon is invalid");
        }
        this.genome = this.genome + codon.toUpperCase(); 
    }

}
