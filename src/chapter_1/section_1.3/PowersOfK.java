import lib.StdOut;

/*
 * Java program that takes an integer command-line argument
k and prints all the positive powers of k in the Java long data type
 */
public class PowersOfK {
    public static void main(String[] args) {
        long k = Long.parseLong(args[0]);
        long power = 1;
        while (power > 0) {
            StdOut.println(power);
            power = multiplyExact(power, k);
        }
    }
    /**
     * Multiplies two long values and checks for overflow.
     *
     * @param  x  the first long value to multiply
     * @param  y  the second long value to multiply
     * @return    the product of x and y, or throws an ArithmeticException if there is an overflow
     * @throws    ArithmeticException if there is an overflow
     */
    public static long multiplyExact(long x, long y) {
        long r = x * y;
        /*
         * The ^ operator in the condition checks if the sign bit of the result
         *  is different from the sign bits of the operands, which indicates an overflow 12.
         */
        if (((x ^ r) & (y ^ r)) < 0) {
            throw new ArithmeticException("long overflow");
        }
        return r;
    }
}