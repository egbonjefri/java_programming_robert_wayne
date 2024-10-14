package generators;

public class TransitionMatrixGenerator {
    
    public static double[][] generateTransitionMatrix(int n) {
        double[][] p = new double[n][n];
        for (int i = 0; i < n; i++) {
            double sum = 0.0;
            for (int j = 0; j < n; j++) {
                p[i][j] = Math.random();
                sum += p[i][j];
            }
            for (int j = 0; j < n; j++) {
                p[i][j] /= sum;
            }
        }
        return p;
    }

}
