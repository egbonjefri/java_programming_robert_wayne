import lib.StdOut;
import java.util.ArrayList;
import java.util.List;

public class SegmentedSieve {
    public static List<Integer> segmentedSieve(int start, int end) {
        List<Integer> primes = new ArrayList<>();
        int limit = (int) Math.sqrt(end) + 1;
        boolean[] isPrime = new boolean[limit];

        for (int i = 2; i < limit; i++) {
            isPrime[i] = true;
        }

        for (int p = 2; p * p < limit; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i < limit; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        for (int i = start; i <= end; i++) {
            if (i < 2) {
                continue;
            }
            boolean isLocalPrime = true;
            for (int prime : primes) {
                if (prime * prime > i) {
                    break;
                }
                if (i % prime == 0) {
                    isLocalPrime = false;
                    break;
                }
            }
            if (isLocalPrime) {
                primes.add(i);
            }
        }

        return primes;
    }

    public static void main(String[] args) {
        int start = 10;
        int end = 50;

        List<Integer> primes = segmentedSieve(start, end);

        System.out.println("Prime numbers between " + start + " and " + end + ":");
        for (int prime : primes) {
            StdOut.print(prime + " ");
        }
    }
}
/*
 * One memory-efficient variation of the Sieve of Eratosthenes is called the "Segmented Sieve."
 *  This method is useful when you need to find prime numbers in a specific range without 
 * generating the entire list of prime numbers up to a large limit. 
 * The Segmented Sieve divides the range into smaller segments and applies the sieve only 
 * to the segment being analyzed, thus reducing memory usage.
 */