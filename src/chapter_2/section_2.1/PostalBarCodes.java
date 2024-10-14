/*
The barcode used by the U.S. Postal System to route mail
is deﬁned as follows: Each decimal digit in the ZIP code is encoded using a sequence
of three half-height and two full-height bars. The barcode starts and ends with a
full-height bar (the guard rail) and includes a checksum digit (after the ﬁve-digit
ZIP code or ZIP+4), computed by summing up the original digits modulo 10. Im-
plement the following functions
• Draw a half-height or full-height bar on StdDraw.
• Given a digit, draw its sequence of bars.
• Compute the checksum digit.
Also implement a test client that reads in a ﬁve- (or nine-)
digit ZIP code as the command-line argument and draws
the corresponding postal bar code.
*/

import lib.StdDraw;


public class PostalBarCodes {
    public static void drawBar(double x, boolean fullHeight) {
        double halfHeight = 0.5;
        double fullHeightValue = 1.0;
        double width = 0.02; 
    
        if (fullHeight) {
            StdDraw.filledRectangle(x, fullHeightValue / 2, width, fullHeightValue / 2);
        } else {
            StdDraw.filledRectangle(x, halfHeight / 2, width, halfHeight / 2);
        }
    }
    public static void drawDigitBars(int digit, double x) {
        // Encoding from decimal digit to bar pattern (0: half-height, 1: full-height)
        String[] patterns = {
            "00111", "01011", "01101", "01110", "10011",
            "10101", "10110", "11001", "11010", "11100"
        };
        
        String pattern = patterns[digit];
        for (int i = 0; i < pattern.length(); i++) {
            drawBar(x + i * 0.02, pattern.charAt(i) == '1'); // Increment x for each bar
        }
    }
    public static int checksum(int[] digits) {
        int sum = 0;
        for (int digit : digits) {
            sum += digit;
        }
        return (10 - (sum % 10)) % 10; // Modulo 10 to get the checksum
    }
        

    public static void main(String[] args) {
        StdDraw.setCanvasSize(600, 150);
        
        String zipCode = args[0]; // ZIP code from command-line argument
        int[] digits = new int[zipCode.length() + 1]; // +1 for checksum
    
        for (int i = 0; i < zipCode.length(); i++) {
            digits[i] = Character.getNumericValue(zipCode.charAt(i));
        }
        
        // Calculate and add checksum digit
        digits[digits.length - 1] = checksum(digits);
    
        // Draw the barcode
        double x = 0.1; // Starting x position
        drawBar(x, true); // Starting guard rail
        for (int digit : digits) {
            drawDigitBars(digit, x);
            x += 0.1; // Move x position for the next digit
        }
        drawBar(x, true); // Ending guard rail
    }
    
}
