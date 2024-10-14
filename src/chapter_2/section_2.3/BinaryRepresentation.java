
public class BinaryRepresentation {

        // Method to print binary representation in reverse order
        public static void printBinaryReverse(int n) {
            while (n > 0) {
                System.out.print(n % 2); // Print remainder
                n = n / 2; // Divide n by 2
            }
            System.out.println(); // New line after printing all bits
        }
    
        // Method to print binary representation in correct order using recursion
        public static void printBinary(int n) {
            if (n > 0) {
                printBinary(n / 2); // Recursive call
                System.out.print(n % 2); // Print remainder after all recursive calls
            }
        }
    
        public static void main(String[] args) {
            int n = Integer.parseInt(args[0]); // Take n as command-line argument
    
            System.out.print("Binary representation (reverse order): ");
            printBinaryReverse(n);
    
            System.out.print("Binary representation (correct order): ");
            printBinary(n);
            System.out.println(); // New line after printing
        }
    }
    
