package generators;
import lib.StdOut;

public class RandomNumber {
    public static void main(String[] args){
        double a = Math.random() * 1;
        double b = Math.random() * 1;
        double c = Math.random() * 1;
        double d = Math.random() * 1;
        double e = Math.random() * 1;
        double min = Math.min(Math.min(Math.min(Math.min(a, b), c), d), e);
        double max = Math.max(Math.max(Math.max(Math.max(a, b), c), d), e);
        double average = ((a+b+c+d+e)/5);

        StdOut.println("The numbers are: " + a + " "+ b + " "+ c + " "+ d + " "+ e + " ");
        StdOut.println("The average is: " + average);
        StdOut.println("The minimum is: " + min);
        StdOut.println("The maximum is: " + max);



    }
    
}
