import lib.StdOut;
/*
 * Java program to compute the sine function using Taylor series expansions
 */


public class Sin {
    public static void main(String[] args) {
        if (args.length != 2) {
            StdOut.println("Usage: java Sin <x> <n>");
            return;
        }

        double x = Double.parseDouble(args[0]);
        int n = Integer.parseInt(args[1]);

        double result = computeSine(x, n);
        StdOut.println("sin(" + x + ") = " + result);
    }

    public static double computeSine(double x, int n) {
        double result = 0;
        for (int i = 0; i < n; i++) {
            result += Math.pow(-1, i) * Math.pow(x, 2 * i + 1) / factorial(2 * i + 1);
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
