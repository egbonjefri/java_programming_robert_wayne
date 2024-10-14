/*
 * Suppose you have an n-by-n array of integers a[][] such
that, for all i and j, a[i][j] < a[i+1][j] and a[i][j] < a[i][j+1], as in the fol-
lowing the 5-by-5 array.
11 12 13 14 15
16 17 18 19 20
21 22 23 24 25
41 42 53 54 55
62 72 85 93 100
A two-dimensional array with this property is known as a Young tableaux. Write
a function that takes as arguments an n-by-n Young tableaux and an integer, and
determines whether the integer is in the Young tableaux. The order of growth of
the running time of your function should be linear in n.
 */
public class YoungTableau {
    public static boolean findInYoungTableau(int[][] tableau, int target) {
        int n = tableau.length;
        int row = 0;
        int col = n - 1;

        while (row < n && col >= 0) {
            if (tableau[row][col] == target) {
                return true;
            } else if (tableau[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }

        return false;
    }
    public static void main(String[] args) {
    int[][] tableau = {
        {10, 20, 30, 40},
        {15, 25, 35, 45},
        {27, 29, 37, 48},
        {32, 33, 39, 50}
    };
    int target = 29;

    System.out.println(findInYoungTableau(tableau, target)); // true
}
}
