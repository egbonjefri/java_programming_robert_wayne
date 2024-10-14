import lib.StdOut;
/*
 * java program that takes two positive integers as command-line
* arguments and prints true if either evenly divides the other
 */
public class IsEven {

        public static void main(String[] args) {
            if (args.length != 2) {
                System.err.println("Usage: java EvenDivisibilityCheck <integer1> <integer2>");
                return;
            }
    
            try {
                int num1 = Integer.parseInt(args[0]);
                int num2 = Integer.parseInt(args[1]);
    
                if (num1 <= 0 || num2 <= 0) {
                    System.err.println("Error: Please enter positive integers.");
                    return;
                }
    
                boolean isDivisible = (num1 % num2 == 0) || (num2 % num1 == 0);
                StdOut.println(isDivisible); 
    
            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid input. Please enter integers only.");
            }
        }
    }
    

