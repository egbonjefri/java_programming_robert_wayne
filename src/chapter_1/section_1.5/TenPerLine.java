import lib.StdOut;
import lib.StdIn;
/*
 * Write a ﬁlter TenPerLine that reads from standard input a sequence of
integers between 0 and 99 and prints them back, 10 integers per line, with columns
aligned. Then write a program RandomIntSeq that takes two integer command-
line arguments m and n and prints n random integers between 0 and m-1. Test your
programs with the command java RandomIntSeq 200 100 | java TenPerLine.
 */
public class TenPerLine {
    public static void main(String[] args) {
        int count = 0;

        while (!StdIn.isEmpty()) {
            int number = StdIn.readInt();
            StdOut.printf("%3d ", number);
            count++;

            if (count % 10 == 0) {
                StdOut.println();
            }
        }

        if (count % 10 != 0) {
            StdOut.println();
        }
    }
}
