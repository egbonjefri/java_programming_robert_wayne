
import lib.In;
import lib.StdOut;

public class PythagoreanTriple {


    public static void findTriples(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if ((a[i] * a[i]) + (a[j] * a[j]) == (a[k] * a[k])) {
                        StdOut.println("These numbers are a Pythagorean Triple: " + a[i] + a[j] + a[k]);
                    }
                }
            }
        }
      
    }

        /**
     * Reads in a sequence of integers from a file, specified as a command-line argument;
     * prints the number of triples that form the side lengths of a right triangle.
     
     * @param args the command-line arguments
     */
    public static void main(String[] args)  {
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        findTriples(a);
    }
}
