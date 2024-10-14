import lib.StdOut;
import lib.StdIn;
/*
 * Write a ﬁlter that reads in a sequence of integers and prints the integers,
removing repeated values that appear consecutively. For example, if the input is
1 2 2 1 5 1 1 7 7 7 7 1 1 1 1 1 1 1 1 1, your program should print
1 2 1 5 1 7 1.
 */
public class RemoveConsecutiveDuplicates {
    public static void main(String[] args) {
        if (!StdIn.isEmpty()) {
            int currentNumber = StdIn.readInt();
            StdOut.print(currentNumber);

            while (!StdIn.isEmpty()) {
                int nextNumber = StdIn.readInt();
                if (nextNumber != currentNumber) {
                    currentNumber = nextNumber;
                    StdOut.print(" " + currentNumber);
                }
            }
        } else {
            StdOut.println("No input provided.");
        }
    }
}
