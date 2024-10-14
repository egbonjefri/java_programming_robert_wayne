
/*
 * a Java program that calculates the weighted average of each row in a 2D array a
 */

public class WeightedAverage {
    public static void main(String args[]) {
        double[][] a = {
        {99, 85, 98, 0},
        {98, 57, 78, 0},
        {92, 77, 76, 0},
        {94, 32, 11, 0},
        {99, 34, 22, 0},
        {90, 46, 54, 0},
        {76, 59, 88, 0},
        {92, 66, 89, 0},
        {97, 71, 24, 0},
        {89, 29, 38, 0}
       };
    double[] weights = { 0.25, 0.25, 0.50 };
    
    for(int i = 0; i < a.length; i++){
        double sum = 0.0;
        for(int j = 0; j < a[i].length - 1; j++){
           sum += a[i][j] * weights[j];
        }
        a[i][a[i].length-1] =  sum/1.0;
    }

        for (double[] row : a) {
            for (double score : row) {
                System.out.print(score + "\t");
            }
            System.out.println();
        }
        
    }
}
