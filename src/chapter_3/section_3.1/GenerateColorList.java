

import lib.StdRandom;
import lib.StdOut;


public class GenerateColorList {

    public static void main(String[] args) {
        // Check if an argument is provided
        if (args.length < 1) {
            System.out.println("Usage: java GenerateColorList <n>");
            return;
        }
        
        int n = 0;
        
        // Try to parse the command-line argument to an integer
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Error: The argument must be an integer.");
            return;
        }
        
        // Generate and print n random color values
        generateAndPrintColors(n);
    }
    
    private static void generateAndPrintColors(int n) {
        
        for (int i = 0; i < n; i++) {
            // Generate random R, G, B values
            int red = StdRandom.uniformInt(256);  
            int green = StdRandom.uniformInt(256); 
            int blue = StdRandom.uniformInt(256); 

            StdOut.println(red + " " + green + " " + blue);
        }
    }
}
