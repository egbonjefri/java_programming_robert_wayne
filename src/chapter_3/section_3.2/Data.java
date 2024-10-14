
/*
 * Write a data type for use in running experiments where the
control variable is an integer in the range [0, n) and the dependent variable is a
double value. (For example, studying the running time of a program that takes an
integer argument would involve such experiments.) Implement the following API:

public class Data
            Data(int n, int max)                create a new data analysis object for the n integer values in [0, n)
double      addDataPoint(int i, double x)       add a data point (i, x)
void        plotPoints()                        plot all the data points


Use the static methods in StdStats to do the statistical calculations and draw the
plots. Write a test client that plots the results (percolation probability) of running
experiments with Percolation as the grid size n increases.
 */
import lib.StdStats;
import lib.StdDraw;
import lib.Stopwatch;



public class Data {
    private final int n;
    private double[] results;

    public Data(int n){
        this.n = n;
        this.results = new double[n];
    }


    public void addDataPoint(int i, double x){
        if(i >= 0 && i < this.n){
            results[i] = x;
        }
        else{
            throw new IllegalArgumentException("Index out of range");
        }
    }

    public void plotPoints(){
        StdDraw.setYscale(0, n + 1);
        StdDraw.setPenColor(StdDraw.RED);
        StdStats.plotBars(results);
    }
        // Bubble sort algorithm
        private static void bubbleSort(int[] array) {
            boolean swapped = true;
            int j = 0;
            while (swapped) {
                swapped = false;
                j++;
                for (int i = 0; i < array.length - j; i++) {
                    if (array[i] > array[i + 1]) {
                        int temp = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = temp;
                        swapped = true;
                    }
                }
            }
        }
    
        // Method to generate an array of random integers
        private static int[] generateRandomArray(int size) {
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = (int) (Math.random() * size);
            }
            return array;
        }
    
    public static void main (String[] args){
        // Define the maximum value for n
        int n = Integer.parseInt(args[0]);
        int step = Integer.parseInt(args[1]);

        Data experimentData = new Data(n / (step));

                // Experiment with different sizes of input
                for (int i = 0; i < n; i += step) {
                    int[] array = generateRandomArray(i);
                    Stopwatch timer1 = new Stopwatch();
                    bubbleSort(array);
                    double time1 = timer1.elapsedTime();
                    experimentData.addDataPoint(i / step, (time1 * 100));
                        }
        
        experimentData.plotPoints();
    }
}