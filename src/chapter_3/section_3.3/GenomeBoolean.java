/**
 * Efficiency Considerations for Genome Representation:
 *
 * String:
 *   - Simplest to use but least space-efficient (16 bits per nucleotide usually)
 *   - Good for small to moderate genomes where readability is key
 *
 * Character Array (char[]):
 *   - More space-efficient than String, but nucleotides still use 16 bits
 *   - Decent compromise for moderate size genomes 
 *
 * Bit-Encoded Boolean Array:
 *   - Most space-efficient (2 bits per nucleotide)
 *   - Ideal for very large genomes with memory constraints
 *   - Increased complexity due to bit-level encoding/decoding
 *
 * Choosing the right representation depends on genome size, priority of readability vs. memory optimization, and the types of operations performed. 
 */ 


public class GenomeBoolean {
    private static final int CODON_BITS = 2;

    private boolean[] genome; 
    private int size; // Number of codons stored

    public GenomeBoolean() {
        this.genome = new boolean[16];  // Initial capacity
        this.size = 0;
    }

    public void addCodon(String codon) {
        if (!codon.matches("[ATCG]+") && codon.length() != 3) {
            throw new IllegalArgumentException("Codon contains invalid DNA characters or Length of Codon is invalid");
        }

        for (char c : codon.toUpperCase().toCharArray()) {
            int encodedValue = encodeNucleotide(c);
            addBits(encodedValue);
        }
    }


    private void addBits(int value) {
        genome[size * CODON_BITS] = (value & 0b10) != 0; // Extract first bit
        genome[size * CODON_BITS + 1] = (value & 0b01) != 0; // Extract second bit
        if(size > genome.length - 1){
            expandGenome(); // Make space if needed
        }
        size++;
    }

    private int encodeNucleotide(char c) {
        switch (c) {
            case 'A': return 0b00; 
            case 'C': return 0b01; 
            case 'G': return 0b10;
            case 'T': return 0b11;
            default:  throw new IllegalArgumentException("Invalid nucleotide"); 
        }
    }

    private void expandGenome() {
        boolean[] newGenome = new boolean[genome.length * 2];
        System.arraycopy(genome, 0, newGenome, 0, genome.length);
        this.genome = newGenome;
    }
    public String getCodonAt(int index) {
        if (index * CODON_BITS >= genome.length) {
            throw new IndexOutOfBoundsException("Genome out of bounds");
        }
    
        int firstBit = genome[index * CODON_BITS] ? 1 : 0;
        int secondBit = genome[index * CODON_BITS + 1] ? 1 : 0;
    
        int encodedValue = (firstBit << 1) | secondBit; // Combine bits
    
        return decodeNucleotide(encodedValue); 
    }
    
    private String decodeNucleotide(int encodedValue) {
        switch (encodedValue) {
            case 0b00: return "A"; 
            case 0b01: return "C"; 
            case 0b10: return "G"; 
            case 0b11: return "T";
            default:   throw new IllegalArgumentException("Invalid nucleotide encoding"); 
        }
    }
    
}
