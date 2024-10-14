public class RecurrenceRelations {

    // T(n) = T(n/2) + 1
    public static int solveRelation1(int n) {
        if (n == 1) return 1;
        return solveRelation1(n / 2) + 1;
    }

    // T(n) = 2T(n/2) + 1
    public static int solveRelation2(int n) {
        if (n == 1) return 1;
        return 2 * solveRelation2(n / 2) + 1;
    }

    // T(n) = 2T(n/2) + n
    public static int solveRelation3(int n) {
        if (n == 1) return 1;
        return 2 * solveRelation3(n / 2) + n;
    }

    // T(n) = 4T(n/2) + 3
    public static int solveRelation4(int n) {
        if (n == 1) return 1;
        return 4 * solveRelation4(n / 2) + 3;
    }

    public static void main(String[] args) {
        int[] values = {2, 4, 8, 16, 32}; // Sample values, all powers of 2

        for (int n : values) {
            System.out.println("n = " + n);
            System.out.println("T(n) = T(n/2) + 1: " + solveRelation1(n));
            System.out.println("T(n) = 2T(n/2) + 1: " + solveRelation2(n));
            System.out.println("T(n) = 2T(n/2) + n: " + solveRelation3(n));
            System.out.println("T(n) = 4T(n/2) + 3: " + solveRelation4(n));
            System.out.println(); // Separate the output for different values of n
        }
    }
}
