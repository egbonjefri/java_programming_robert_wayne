import lib.StdOut;

/*
The time complexity of this algorithm is O(n) as we iterate through the array only once.
 The space complexity is O(1) as we use only a constant amount of extra memory regardless of the input size.
 */
public class FindDuplicates {
    /*
     * Given an array of n integers from 1 to n with
one value repeated twice and one missing, give an algorithm that ﬁnds the missing
integer, in linear time and constant extra memory. Integer overﬂow is not allowed.
     */
    public static void findMissingAndDuplicate(int[] array) {
        int n = array.length;
        int S = n * (n + 1) / 2;
        int P = n * (n + 1) * (2 * n + 1) / 6;
        
        int actualSum = 0;
        int actualSumSquares = 0;
        
        for (int i = 0; i < n; i++) {
            actualSum += array[i];
            actualSumSquares += array[i] * array[i];
        }
        
        int sumDiff = actualSum - S; // y - x
        int squareSumDiff = actualSumSquares - P; // y^2 - x^2
        
        int sumOfNumbers = squareSumDiff / sumDiff; // y + x

        int y = (sumDiff + sumOfNumbers) / 2;
        int x = y - sumDiff;

        StdOut.println("Missing number: " + x);
        StdOut.println("Duplicated number: " + y);

    }
    /*
     * Given a read-only array of n integers
with values between 1 and n - 1, give an algorithm that ﬁnds a duplicated value, in
linear time and constant extra memory.
     */
    public static int findAnyDuplicate(int[] array) {
        int tortoise = array[0];
        int hare = array[0];
        
        do {
            tortoise = array[tortoise];
            hare = array[array[hare]];
        } while (tortoise != hare);
        
        hare = array[0];
        while (tortoise != hare) {
            tortoise = array[tortoise];
            hare = array[hare];
        }

        return hare;
    }

}
