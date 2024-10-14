import lib.StdOut;
/*
 * Java program that generates a table of size n x n and fills it with asterisks (*) or spaces ( )
 *  based on whether the pair of indices (i, j) are relatively prime or not.
 */
public class RelativelyPrimeArray {
    public static void main(String args[]) {
        int n = Integer.parseInt(args[0]);
        String[][] a = new String[n][n];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < a[i].length; j++){
                  if (gcd(i, j) == 1) {
                  a[i][j]= "* ";
                  } else {
                  a[i][j]= "  ";
                  }
            }
        }
                // Print the table header
                StdOut.print("  ");
          for (int j = 0; j < n; j++) {
            StdOut.print(j + "  ");
          }
          StdOut.println();
          for (int i = 0; i < n; i++) {
            StdOut.print(i + " ");
          for (int j = 0; j < n; j++) {
            StdOut.print(a[i][j] + " ");
          }
          StdOut.println("");
       
          }
        
  
      }
      
          public static int gcd(int a, int b) {
          if (b == 0) {
              return a;
          }
          return gcd(b, a % b);
      }
}
