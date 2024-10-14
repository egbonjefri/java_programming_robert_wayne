
/*
 * Develop a library based on the functions
that we have considered in this book for computing properties of integers. Include
functions for determining whether a given integer is prime; determining whether
two integers are relatively prime; computing all the factors of a given integer; com-
puting the greatest common divisor and least common multiple of two integers;
Euler’s totient function (EXERCISE 2.1.26); and any other functions that you think
might be useful. Include overloaded implementations for long values. Create an
API, a client that performs stress testing, and clients that solve several of the exer-
cises earlier in this book.
 */


public class IntegerProperties {

    // Check if a number is prime
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Check if two numbers are relatively prime
    public static boolean areRelativelyPrime(int a, int b) {
        return gcd(a, b) == 1;
    }

    // Compute the greatest common divisor (GCD) of two numbers
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Compute the least common multiple (LCM) of two numbers
    public static int lcm(int a, int b) {
        return a / gcd(a, b) * b; // Mind overflow here
    }

    // Compute all factors of a given number
    public static int[] factors(int n) {
        int[] tempFactors = new int[n];
        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                tempFactors[count++] = i;
            }
        }

        int[] factors = new int[count];
        System.arraycopy(tempFactors, 0, factors, 0, count);
        return factors;
    }

    // Compute Euler's totient function
    public static int eulersTotient(int n) {
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (gcd(i, n) == 1) {
                count++;
            }
        }
        return count;
    }
}
