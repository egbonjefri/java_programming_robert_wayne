import lib.StdDraw;

public class DecimalToGray {


    public static void main(String[] args) {
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(-10, 10);
        StdDraw.setYscale(-10, 10);
        int decimal = 10; // Example decimal number
        int grayCode = decimalToGray(decimal, 0, 0);
        System.out.println("Gray code of " + decimal + " is: " + grayCode + " which is " + Integer.toBinaryString(grayCode) + " in binary" );
    }

    public static int decimalToGray(int n, double x, double y) {
        // Visualize the current function call
        StdDraw.text(x, y, String.valueOf(n)+ ' '+String.valueOf(Integer.toBinaryString(n)));
        if (n == 0) {
            return 0;
        } else {
            double newX = x - 1;
            double newY = y - 1;
            //Shifting the binary representation number to the right by one position is equivalent to 
            //dividing the number by 2 and discarding any remainder. e.g 5 >> 1 outputs 2
            int shiftedN = n >> 1;

            //XORing the original number with its shifted version combines the two 
            //in a way that the result (the Gray code) has only one bit different from the original 
            //or the shifted version for each transition
            int gray = n ^ shiftedN;
            StdDraw.line(x, y, newX, newY); // Draw line to child call
            decimalToGray(shiftedN, newX, newY); // Recursive call
            return gray;
        }
    }
}
/*
 * So, when you see a variable like shiftedN or gray in your code, 
 * even though the operations that produced these values were bitwise (and thus binary in nature), 
 * the values themselves are stored and represented as decimal integers in the variables. 
 * This automatic handling of binary to decimal conversion is a feature of the programming language's runtime environment, 
 * designed to abstract away the complexity of binary arithmetic for the programmer.
 */