
import lib.StdOut;

public class DragonCurve {
    public static void main(String[] args) {
        int maxOrder = 5;

        for (int order = 0; order <= maxOrder; order++) {
            StdOut.println("Dragon Curve of Order " + order + " Instructions:");
            dragonCurveInstructions(order);
            StdOut.println();
        }
    }

    private static void dragonCurveInstructions(int order) {
        if (order == 0) {
            StdOut.println("F");
            return;
        }

        dragonCurveInstructions(order - 1);

        StdOut.print("L");
        dragonCurveInstructionsReverse(order - 1);
        StdOut.print("F");
    }

    private static void dragonCurveInstructionsReverse(int order) {
        if (order == 0) {
            return;
        }

        StdOut.print("F");
        dragonCurveInstructions(order - 1);
        StdOut.print("R");
        dragonCurveInstructionsReverse(order - 1);
    }
}
