/*
 * Java program that prints all integers less than or equal to 'n' 
 * that can be expressed as the sum of two cubes in two different ways,
 * i.e a^3+b^3=c^3+d^3
 * O(n^4 complexity)
 */
import lib.StdOut;

public class TwoCubesSum {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java TwoCubesSum <n>");
            return;
        }

        int n = Integer.parseInt(args[0]);

        for (int x = 1; x <= n; x++) {
            for (int y = x; y <= n; y++) {
                int sum1 = x * x * x + y * y * y;
                if (sum1 > n) {
                    break;
                }
                for (int c = x; c <= n; c++) {
                    for (int d = c; d <= n; d++) {
                        int sum2 = c * c * c + d * d * d;
                        if (sum1 == sum2 && (x != c || y != d)) {
                            StdOut.println(x + "^3 + " + y + "^3 = " + c + "^3 + " + d + "^3 = " + sum1);
                        }
                    }
                }
            }
        }
    }
}
