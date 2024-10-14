package generators;

public class RandomArrayGenerator {
    public static int[] generateIntArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 200 - 100);
        }
        return arr;
    } 
    public static double[] generateDoubleArray(int size) {
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 200 - 100);
        }
        return arr;
    } 
}
