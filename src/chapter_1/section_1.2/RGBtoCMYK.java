/*
 * Several different formats are used to represent color.
 *  For example, the primary format for LCD displays, digital cameras, and web pages,
 * known as the RGB format, speciﬁes the level of red (R), green (G), and blue (B)
 * on an integer scale from 0 to 255. 
 * The primary format for publishing books and magazines, 
 * known as the CMYK format, speciﬁes the level of cyan (C), magenta (M), yellow (Y), and black (K)
 *  on a real scale from 0.0 to 1.0.   
     * Converts RGB values from the command line to CMYK values and prints them.
     *
     * @param  args  an array of three integers representing the RGB values
     
If the RGB values are all 0, then the CMY values are all 0 and the K value is 1; 
otherwise, use these formulas:
* w = max ( r / 255, g / 255, b / 255 )
* c = (w - ( r / 255)) / w
* m = (w - ( g / 255)) / w
* y = (w - ( b / 255)) / w
* k = 1w
 */
import java.util.Arrays;
import lib.StdOut;
public class RGBtoCMYK {
    public static void main(String[] args) {
        int[] rgb = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
        double max = Math.max(Math.max(rgb[0]/255.0, rgb[1]/255.0), rgb[2]/255.0);
        if (max == 0) {
            StdOut.println("CMYK: 0.0 0.0 0.0 1.0");
        } else {
            double w = max;
            double c = (w - (rgb[0]/255.0)) / w;
            double m = (w - (rgb[1]/255.0)) / w;
            double y = (w - (rgb[2]/255.0)) / w;
            double k = 1 - w;
            StdOut.printf("CMYK: %f %f %f %f%n", c, m, y, k);
        }
    }
}
