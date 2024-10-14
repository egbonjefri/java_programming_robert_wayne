/*
 *  Code fragments to create a two-dimensional array b[][] that is a
copy of an existing two-dimensional array a[][], under each of the following as-
sumptions:
a. a[][] is square
b. a[][] is rectangular
c. a[][] may be ragged
solution to b should work for a, and  solution to c should work for both b
and a, 
 */

import lib.StdArrayIO;

public class Copy2DArray {
    public static int[][] copySquareArray(int[][] a) {
        int[][] b = new int[a.length][a.length]; // Both dimensions are the same
        for (int i = 0; i < a.length; i++) {
        System.arraycopy(a[i], 0, b[i], 0, a.length); 
    }
    return b;
    }

    public static int[][] copyRectangularArray(int[][] a) {
        int[][] b = new int[a.length][]; 
        for (int i = 0; i < a.length; i++) {
            b[i] = new int[a[i].length]; // Allocate row-wise
            System.arraycopy(a[i], 0, b[i], 0, a[i].length);
        }
    return b;
    }
    public static int[][] copyRaggedArray(int[][] a) {
        int[][] b = new int[a.length][];
        for (int i = 0; i < a.length; i++) {
            b[i] = new int[a[i].length]; // Dynamic allocation for each row
            for (int j = 0; j < a[i].length; j++) {
                b[i][j] = a[i][j]; // Manual element-by-element copy
            }
        }
    return b;
    }
    public static void main(String[] args){
        int[][] a = {{1, 2}, {3, 4, 5}, {6}}; // Ragged array example
        int[][] b = copyRaggedArray(a);
        StdArrayIO.print(b);
    }
}
