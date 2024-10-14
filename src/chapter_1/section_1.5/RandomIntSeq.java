
import lib.StdOut;
import lib.StdRandom;

public class RandomIntSeq {
    public static void main(String[] args) {
        if (args.length != 2) {
            StdOut.println("Usage: java RandomIntSeq <m> <n>");
            return;
        }

        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);

        for (int i = 0; i < n; i++) {
            int randomNum = StdRandom.uniformInt(m);
            StdOut.println(randomNum);
        }
    }
}
