import lib.StdOut;
import lib.StdRandom;
/*
 *  java program that takes two integer command-line arguments a and b
and prints a random integer between a and b, inclusive
 */
public class PrintRandomInt {

    public static void printDiceSum(){
        int first = StdRandom.uniformInt(7);
        int second = StdRandom.uniformInt(7);

        StdOut.println(first + second);
    }
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java PrintRandomInt <m> <n>");
            return;
        }

        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);

        int randomNum1 = StdRandom.uniformInt(m + 1);
        int randomNum2 = StdRandom.uniformInt(n + 1);
        StdOut.println(randomNum1);
        StdOut.println(randomNum2);

    }
}

