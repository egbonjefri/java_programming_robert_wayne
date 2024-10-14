public class HammingDistance {

    // Function to count the number of '1' bits in a number
    public static int countOnes(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1; // Increment count if LSB is 1
            n >>>= 1; // Unsigned right shift
        }
        return count;
    }

    // Function to compute the Hamming distance between two integers
    /*
     * After XORing the two numbers, the problem of finding the Hamming distance 
     * reduces to counting the number of 1 bits in the result. 
     * This count represents the Hamming distance, as each 1 bit indicates a position where the two numbers differ.
     */
    public static int hammingDistance(int x, int y) {
        int xor = x ^ y; // XOR to find differing bits
        return countOnes(xor); // Count and return the number of '1' bits
    }

    public static void main(String[] args) {
        int x = 1;
        int y = 4;
        System.out.println("The Hamming distance between " + x + " and " + y + " is: " + hammingDistance(x, y));
    }
}
