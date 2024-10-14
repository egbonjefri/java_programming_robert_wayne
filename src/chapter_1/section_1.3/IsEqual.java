/*
 * Write a program that takes three integer command-line arguments and
prints equal if all three are equal, and not equal otherwise.
 */
import lib.StdOut;
public class IsEqual {
    public static void main(String[] args) {
        int arg1 = Integer.parseInt(args[0]);
        int arg2 = Integer.parseInt(args[1]);
        int arg3 = Integer.parseInt(args[2]);
        
        if (arg1 == arg2 && arg2 == arg3) {
            StdOut.println("equal");
        } else {
            StdOut.println("not equal");
        }
    }
}
