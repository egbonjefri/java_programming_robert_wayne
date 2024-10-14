/*
 * Write a linear-time ﬁlter that reads from standard input a se-
quence of integers that are between 0 and 99 and prints to standard output the
same integers in sorted order. For example, presented with the input sequence
98 2 3 1 0 0 0 3 98 98 2 2 2 0 0 0 2
your program should print the output sequence
0 0 0 0 0 0 1 2 2 2 2 2 3 3 98 98 98
 */

import lib.StdIn;
import lib.StdOut;
import java.util.stream.IntStream;

public class IntegerSort {

    public static int[] linearSort(int[] a, int range){
        int[] count = new int[range];

        for(int i = 0; i < a.length; i++){
            int num = a[i];
            if (num >= 0 && num < range) {
            count[num]++;
            }
        }

        return count;

}
    public static void main(String[] args){
        int[] a = StdIn.readAllInts();
        int range = IntStream.of(a).max().getAsInt() + 1;
        int[] count = linearSort(a, range);
        // Print elements based on their count
        for (int i = 0; i < range; i++) {
            for (int j = 0; j < count[i]; j++) {
                StdOut.print(i + " "); // Print element based on index (i)
            }
        }

    }
    
}
