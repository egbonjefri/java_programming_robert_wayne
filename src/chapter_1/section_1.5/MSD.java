import lib.StdOut;
import lib.StdIn;

/*
 * Write a program that takes an integer command-line argument n, reads n
ﬂoating-point numbers from standard input, and prints their mean (average value)
and sample standard deviation (square root of the sum of the squares of their dif-
ferences from the average, divided by n-1).
 */
public class MSD {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        System.out.println("Type " + n + " float point numbers, followed by ENTER...(Ctrl-D to stop):");
        double[] array1 = StdIn.readAllDoubles();
         
        double total = 0.0;
        for(int i = 0; i < array1.length; i++){
           total += array1[i];
        }
        double mean = total / array1.length;
        double sd = 0.0;
        for(int i = 0; i < array1.length; i++){
            double diff = (array1[i] - mean)*(array1[i]-mean);
            sd += diff;
            double ssd = Math.sqrt(diff);
            if(ssd>1.5){
                StdOut.println("The number: " + array1[i] + " is " + ssd + " standard deviations away from the mean");
            }
        }
        StdOut.println("The mean of the numbers entered was : " + mean);
        StdOut.println("The Standard Deviation of the numbers entered was : "+ Math.sqrt(sd/(array1.length - 1)));
}
}