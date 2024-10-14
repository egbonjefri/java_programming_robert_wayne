import lib.StdDraw;
public class Ruler {
    // simple method to draw ruler
   
        
    // Recursive helper method to construct the ruler string
    private static String buildRuler(int n) {
        if (n == 1) {
            return "1"; // Base case
        } else {
            String previousRuler = buildRuler(n - 1); // Recursive call
            return previousRuler + " " + n + " " + previousRuler;
        }
    }


    // draw ruler using recursion
    public static void drawRuler(String ruler) {

        // Setup for StdDraw
        StdDraw.setCanvasSize(800, 200);
        StdDraw.setXscale(-1, ruler.length() + 1);
        StdDraw.setPenRadius(0.005);

        // Draw the base line of the ruler
        StdDraw.line(-0.1, 1, ruler.length(), 1);

        for (int i = 0, pos = 0; i < ruler.length(); i++) {
            char ch = ruler.charAt(i);
            
            // Check if the character is a digit
            if (Character.isDigit(ch)) {
                int num = Character.getNumericValue(ch);
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.setPenRadius(0.01);
                StdDraw.line(pos, 1, pos, num + 1);
                // Draw label
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.text(pos, 1 - 0.5, Integer.toString(num));
                pos++; // Increment position for drawing
            }
            
            if (ch == ' ') {
                pos++; // Increment position for spaces
            }
        }
    }


    public static void main(String[] args) {
        int n = 5;
        StdDraw.setYscale(0, n + 2);
        drawRuler(buildRuler(n)); 
        //drawRuler(ruler(n));
    }
}
