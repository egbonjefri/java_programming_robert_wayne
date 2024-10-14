
import lib.StdRandom;
import lib.StdOut;

    /**
     * Rolls a loaded die and prints the result. 
     * Such that the probability of getting a 1, 2, 3, 4, or 5 is 1/8 and 
     * the probability of getting a 6 is 3/8.
     *
     * @param  args  the command-line arguments (not used in this function)
     */
public class RollLoadedDie {
    public static void main(String[] args) {
        double prob = StdRandom.uniformDouble();
        if (prob < 1/8) {
            StdOut.println(StdRandom.uniformInt(6));
        }
         else if(prob > 1/8 && prob < 3/8) {
            StdOut.println(6);
        }
    }
}
