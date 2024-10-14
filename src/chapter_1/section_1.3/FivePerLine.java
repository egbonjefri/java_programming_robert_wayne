import lib.StdOut;
    /**
     * Prints the numbers from 1000 to 2000, with 5 numbers per line.
     *
     * @param  args  the command-line arguments (not used)
     */
public class FivePerLine {
    
    public static void main(String[] args) {
        for (int i = 1000; i <= 2000; i++) {
            StdOut.print(i + " ");  

            if (i % 5 == 4) {          // Check if i is the 4th number on the line
                StdOut.println();  
            }
        }
    }
}