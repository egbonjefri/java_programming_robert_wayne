import lib.StdOut;
/*
 * Java programs to compute the cosine function using Taylor series expansions
 */

public class Cos {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Cos <x> <n>");
            return;
        }

        double x = Double.parseDouble(args[0]);
        int n = Integer.parseInt(args[1]);

        double result = computeCosine(x, n);
        StdOut.println("cos(" + x + ") = " + result);
    }

    public static double computeCosine(double x, int n) {
        double result = 0;
        for (int i = 0; i < n; i++) {
            result += Math.pow(-1, i) * Math.pow(x, 2 * i) / factorial(2 * i);
        }
        return result;
    }

    public static double factorial(int num) {
        if (num == 0) {
            return 1;
        }
        return num * factorial(num - 1);
    }
}
