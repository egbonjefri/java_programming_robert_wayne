import lib.StdOut;
public class PrimeCounter {
    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("Usage: java PrimeCounter <n>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int count = countPrimes(n);

        StdOut.println("Number of prime numbers less than or equal to " + n + " is: " + count);
    }

    public static int countPrimes(int n) {
        if (n < 2) {
            return 0;
        }

        // Create a boolean array to represent whether numbers are prime or not
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        // Use the Sieve of Eratosthenes algorithm to mark non-prime numbers
        for (int p = 2; p * p <= n; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        // Count the number of primes
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }

        return count;
    }
}


/*
The Sieve of Eratosthenes is an ancient and efficient algorithm used to find all prime numbers 
up to a given limit, n. It eliminates multiples of each prime number, starting from 2, and 
iterates through the numbers up to n, marking the non-prime numbers as composite. 
The algorithm is named after the Greek mathematician Eratosthenes, who described it 
more than 2,000 years ago. Here's a step-by-step explanation of how the Sieve of Eratosthenes works:

Create a List of Numbers: Create a list of integers from 2 to n. 
These numbers are candidates to be either prime or composite.
Initialize the Prime Pencil: Start with the first prime number, which is 2.
All multiples of 2 (except 2 itself) are marked as composite.

Find the Next Unmarked Number: Go to the next unmarked number in the list. This number 
is guaranteed to be prime.
Mark Multiples as Composite: Mark all multiples of the prime number found in step 3 as composite. 
These multiples are numbers that are divisible by the prime number without a remainder.

Repeat Steps 3 and 4: Continue this process by finding the next unmarked number
(which will be the next prime) and marking its multiples as composite. 
This process is repeated until you reach a number greater than the square root of n.

Collect the Remaining Unmarked Numbers: All unmarked numbers that remain in the list are prime numbers.


The key idea behind the Sieve of Eratosthenes is that it eliminates multiples of primes. 
By the time you finish the algorithm, the remaining unmarked numbers are all prime. 
The algorithm is efficient because it avoids redundant work by marking multiples of prime numbers 
only once.

For example, when finding primes up to 30:

Start with 2 (the first prime).
Mark multiples of 2 (4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30) as composite.
Move to the next unmarked number, which is 3 (the next prime).
Mark multiples of 3 (9, 15, 21, 27) as composite.
Continue to the next unmarked number, which is 5 (the next prime).
Mark multiples of 5 (25) as composite.
After this process, you are left with the prime numbers: 2, 3, 5, 7, 11, 13, 17, 19, 23, and 29.

The Sieve of Eratosthenes is an efficient way to find prime numbers up to a certain limit and 
is widely used in number theory and prime number generation algorithms.


Initialization: Creating the initial list of numbers from 2 to n takes O(n) time.

Outer Loop: The outer loop iterates from 2 to the square root of n, which is approximately 
O(√n) iterations. In each iteration of the outer loop, you find the next unmarked prime 
and mark its multiples.

Inner Loop (Marking Multiples): In each iteration of the inner loop, you mark multiples of 
the prime number found in the outer loop. The number of iterations in the inner loop depends
on the limit n. In the worst case, each unmarked number in the list is processed only once by 
the inner loop, so the total work in the inner loop is proportional to O(n).

As a result, the overall time complexity of the Sieve of Eratosthenes is O(n log log n). 
This is a highly efficient time complexity, making it one of the fastest methods for 
finding all prime numbers up to a given limit, especially when n is reasonably large.

The Sieve of Eratosthenes is often the method of choice for generating a list of
 prime numbers because of its excellent time complexity. 
 However, it does have some space complexity considerations, 
 as it requires O(n) space to store the list of numbers. 
 For certain applications or very large values of n, more memory-efficient 
 variations of the algorithm may be used.

 */