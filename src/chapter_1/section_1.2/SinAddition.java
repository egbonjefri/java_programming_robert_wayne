import lib.StdOut;
/*
* Consider a physical interpretation where sin(2t) and sin(3t) represent two sound waves. 
* The resulting wave, given by sin(2t) + sin(3t),
* exhibits a more complex pattern due to the interference between the two waves. 
* This pattern can lead to constructive or destructive interference, 
* depending on the phase relationship between the two waves at any given point in time.
 */

public class SinAddition {
    public static void main(String[] args){
        double t = Double.parseDouble(args[0]);
        StdOut.println(Math.sin(2*t) + Math.sin(3*t));
    }
}
