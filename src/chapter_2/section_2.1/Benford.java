
/*
 * The American astronomer Simon Newcomb observed a
quirk in a book that compiled logarithm tables: the beginning pages were much
grubbier than the ending pages. He suspected that scientists performed more com-
putations with numbers starting with 1 than with 8 or 9, and postulated that, under
general circumstances, the leading digit is much more likely to be 1 (roughly 30%)
than the digit 9 (less than 4%). This phenomenon is known as Benford’s law and is
now often used as a statistical test. For example, IRS forensic accountants rely on
it to discover tax fraud. Write a program that reads in a sequence of integers from
standard input and tabulates the number of times each of the digits 1–9 is the lead-
ing digit, breaking the computation into a set of appropriate static methods. Use
your program to test the law on some tables of information from your computer or
from the web. Then, write a program to foil the IRS by generating random amounts
from $1.00 to $1,000.00 with the same distribution that you observed.
 */

import lib.StdIn;



public class Benford {
    public static int leadingDigit(int number) {
        // ensure the number is positive and a single digit
        number = Math.abs(number); 
        while (number >= 10) {
            number /= 10;
        }
        return number;
    }

    public static void main(String[] args) {
        int[] counts = new int[10]; // Array to hold counts for digits 1-9
        while (!StdIn.isEmpty()) {
            int num = StdIn.readInt();
            int leadDigit = leadingDigit(num);
            if (leadDigit != 0) {
                counts[leadDigit]++;
            }
        }

        // Display the counts for digits 1-9
        for (int i = 1; i < counts.length; i++) {
            System.out.println(counts[i]);
        }
    }
}
