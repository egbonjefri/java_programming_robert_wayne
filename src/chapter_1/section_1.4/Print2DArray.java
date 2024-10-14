import lib.StdOut;
public class Print2DArray {
    public static void printBoolean2DArray(boolean[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                StdOut.printf("%3d %3d ", i, j);
                StdOut.print(a[i][j] ? "*" : " ");
            }
            StdOut.println();
        }
    }
}
