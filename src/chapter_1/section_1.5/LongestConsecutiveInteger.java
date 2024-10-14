import lib.StdOut;
import lib.StdIn;
/*
 * Write a program that reads in a sequence of integers and prints both the
integer that appears in a longest consecutive run and the length of that run. For
example, if the input is 1 2 2 1 5 1 1 7 7 7 7 1 1, then your program should
print Longest run: 4 consecutive 7s.
 */
public class LongestConsecutiveInteger {
    public static void main(String[] args) {
        int longestRunLength = 0;
        int longestRunNumber = 0;
        int currentRunLength = 1;
        int currentRunNumber;

        if (!StdIn.isEmpty()) {
            currentRunNumber = StdIn.readInt();
            longestRunNumber = currentRunNumber;

            while (!StdIn.isEmpty()) {
                int nextNumber = StdIn.readInt();
                if (nextNumber == currentRunNumber) {
                    currentRunLength++;
                } else {
                    currentRunNumber = nextNumber;
                    currentRunLength = 1;
                }

                if (currentRunLength > longestRunLength) {
                    longestRunLength = currentRunLength;
                    longestRunNumber = currentRunNumber;
                }
            }

           StdOut.println("Longest run: " + longestRunLength + " consecutive " + longestRunNumber + "s");
        } else {
            StdOut.println("No input provided.");
        }
    }
}
