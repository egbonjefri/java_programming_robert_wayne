
import lib.StdOut;
import lib.StdIn;

public class GreatestCommonDivisor {
    
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        
        return a;
    }
    
    public static void main(String[] args) {
       
        
        StdOut.print("Enter first integer: ");
        int x = StdIn.readInt();
        
        StdOut.print("Enter second integer: ");
        int y = StdIn.readInt();
        
        int result = gcd(x, y);
        
        StdOut.println("The greatest common divisor of " + x + " and " + y + " is " + result);
    }
}
