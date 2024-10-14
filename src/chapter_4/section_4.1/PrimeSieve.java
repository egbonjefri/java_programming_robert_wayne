/*
 * Write a version of PrimeSieve (PROGRAM 1.4.3) that uses a byte array in-
stead of a boolean array and uses all the bits in each byte, thereby increasing the
largest value of n that it can handle by a factor of 8.
 */
public class PrimeSieve {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int arraySize = (n / 8) + 1; // size of byte array

        // initially assume all integers are prime
        byte[] isPrime = new byte[arraySize];
        for (int i = 2; i <= n; i++) {
            setBit(isPrime, i, true);
        }

        // mark non-primes <= n using Sieve of Eratosthenes
        for (int factor = 2; factor * factor <= n; factor++) {
            if (getBit(isPrime, factor)) {
                for (int j = factor; factor * j <= n; j++) {
                    setBit(isPrime, factor * j, false);
                }
            }
        }

        // count primes
        int primes = 0;
        for (int i = 2; i <= n; i++) {
            if (getBit(isPrime, i)) primes++;
        }
        System.out.println("The number of primes <= " + n + " is " + primes);
    }

    // Set the bit at index i to the specified value (true for 1, false for 0)
    private static void setBit(byte[] array, int i, boolean value) {
        int byteIndex = i / 8;
        int bitIndex = i % 8;
        if (value) {
            array[byteIndex] |= (1 << bitIndex); // set bit to 1
        } else {
            array[byteIndex] &= ~(1 << bitIndex); // set bit to 0
        }
    }

    // Get the value of the bit at index i
    private static boolean getBit(byte[] array, int i) {
        int byteIndex = i / 8;
        int bitIndex = i % 8;
        return (array[byteIndex] & (1 << bitIndex)) != 0;
    }
}
