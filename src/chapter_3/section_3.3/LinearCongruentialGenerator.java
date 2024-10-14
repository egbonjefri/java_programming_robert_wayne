/*
 * develop a data type for generating
pseudo-random numbers. That is, convert StdRandom to a data type. Instead of
using Math.random(), base your data type on a linear congruential generator. This
method traces to the earliest days of computing and is also a quintessential example
of the value of maintaining state in a computation (implementing a data type). To
generate pseudo-random int values, maintain an int value x (the value of the
last “random” number returned). Each time the client asks for a new value, return
a*x + b for suitably chosen values of a and b (ignoring overﬂow). Use arithmetic
to convert these values to “random” values of other types of data. As suggested by
D. E. Knuth, use the values 3141592621 for a and 2718281829 for b. Provide a con-
structor allowing the client to start with an int value known as a seed (the initial
value of x). This ability makes it clear that the numbers are not at all random (even
though they may have many of the properties of random numbers) but that fact
can be used to aid in debugging, since clients can arrange to see the same numbers
each time.
 */
import lib.StdOut;


public class LinearCongruentialGenerator {
    private long x; // Current state (seed)
    private final long a = 3141592621L;
    private final long b = 2718281829L;
    private final long m = (long) Math.pow(2, 32); // Modulus (assumes 32-bit ints)

    public LinearCongruentialGenerator(long seed) {
        this.x = seed;
    }

    // Generates the next pseudo-random integer
    public int nextInt() {
        x = (a * x + b) % m;
        return (int) x; // Cast to int
    }
    // Generates a pseudo-random integer in the range [0, bound) 
    public int nextInt(int bound) {
        if (bound <= 0) {
            throw new IllegalArgumentException("Bound must be positive");
        }

        // Rejection sampling approach (may not be the most efficient for large bounds)
        int result;
        do {
            result = nextInt(); 
        } while (result >= bound);

        return result; 
    }
    // Generates a pseudo-random double between 0.0 (inclusive) and 1.0 (exclusive)
    public double nextDouble() {
        return (double) nextInt() / m; 
    }

        // Generates a pseudo-random boolean 
        public boolean nextBoolean() {
            return nextInt() % 2 == 0; 
        }
    
        // Generates a pseudo-random long
        public long nextLong() {
            // Combine two generated ints as there's not enough output from a single nextInt
            return ((long) nextInt() << 32) + nextInt(); 
        }
    
        // Generates a pseudo-random float between 0.0 (inclusive) and 1.0 (exclusive)
        public float nextFloat() {
            return (float) nextInt() / m; 
        } 

        //main method for testing
        public static void main(String[] args){
            LinearCongruentialGenerator rand = new LinearCongruentialGenerator(12345); // Start with seed 12345

            for (int i = 0; i < 10; i++) {
                int randomInt = rand.nextInt();
                double randomDouble = rand.nextDouble();
                StdOut.println("Random Int: " + randomInt + ", Random Double: " + randomDouble);
                }

        }


    }