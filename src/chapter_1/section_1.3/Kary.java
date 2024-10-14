import lib.StdOut;
// java program Kary that takes two integer command line arguments 
//i and k and converts i to base k. 
//Assume that i is an integer in Java’s long data type and that k is
// an integer between 2 and 16. 
//For bases greater than 10, use the letters A through F to represent 
//the 11th through 16th digits, respectively

public class Kary {
    public static void main(String[] args) {
        long i = Long.parseLong(args[0]);
        int k = Integer.parseInt(args[1]);
        StringBuilder sb = new StringBuilder();
        while (i > 0) {
            int digit = (int) (i % k);
            if (digit < 10) {
                sb.append(digit);
            } else {
                sb.append((char) ('A' + digit - 10));
            }
            i /= k;
        }
       StdOut.println(sb.reverse().toString());
    }
} 
/*
 * To convert a decimal number to binary, you can use the following steps:

Divide the decimal number by 2.
Write down the integer quotient and the remainder (either 0 or 1).
Divide the quotient from step 1 by 2.
Write down the integer quotient and the remainder.
Repeat steps 3 and 4 until the quotient becomes 0.
The binary representation of the decimal number is the sequence of remainders from step 2 to step 5, read from bottom to top.
For example, let’s convert the decimal number 23 to binary:

23 / 2 = 11 remainder 1
11 / 2 = 5 remainder 1
5 / 2 = 2 remainder 1
2 / 2 = 1 remainder 0
1 / 2 = 0 remainder 1
 */
