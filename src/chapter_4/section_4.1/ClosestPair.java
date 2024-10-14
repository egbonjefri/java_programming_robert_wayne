/*
 * Design a quadratic-time algorithm that, given an array of in-
tegers, ﬁnds a pair that are closest to each other.
 */

import generators.RandomArrayGenerator;
import lib.Stopwatch;
import lib.StdOut;

public class ClosestPair {

    public static int[] findClosestPair(int[] a){
        int minDifference = Integer.MAX_VALUE;
        int[] pairs = new int[2];

        for(int i = 0; i < a.length; i++){
            for(int j = i+1; j < a.length; j++){
                int diff = Math.abs(a[i] - a[j]);
                if(diff < minDifference){
                    pairs[0] = a[i];
                    pairs[1] = a[j];
                    minDifference = diff;
                }
            }
        }
        return pairs;
    }

    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int[] a =  RandomArrayGenerator.generateIntArray(n);
        int[] a2 =  RandomArrayGenerator.generateIntArray(n * 2);

        Stopwatch timer = new Stopwatch();
        findClosestPair(a);
        double timer1 = timer.elapsedTime();

        Stopwatch timer_2 = new Stopwatch();
        findClosestPair(a2);
        double timer2 = timer_2.elapsedTime();

        if(timer1 == 0){
            StdOut.println("The running time of this algorithm is: " + (timer2 - timer1));
            return;
        }
        StdOut.println("The time complexity of this program is approximately: O(n^" + (Math.log(timer2 / timer1) / Math.log(2)) + ")");
    }
        
}
