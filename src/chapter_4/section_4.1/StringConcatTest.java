import lib.StdOut;
import lib.StdRandom;
import lib.StdDraw;

public class StringConcatTest {
    
    public static void main(String[] args) {
        int[] nValues = {1000, 2000, 4000, 8000, 16000, 32000, 64000};
        double[] timesStringConcat = new double[nValues.length];
        double[] timesStringBuilder = new double[nValues.length];
        // Measure time for string concatenation
        for (int i = 0; i < nValues.length; i++) {
            int n = nValues[i];
            long startTime = System.currentTimeMillis();
            String s = "";
            for (int j = 0; j < n; j++) {
                if (StdRandom.bernoulli(0.5)) s += "0";
                else s += "1";
            }
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            timesStringConcat[i] = duration;
            StdOut.printf("String Concatenation - n = %d, Time: %d ms\n", n, duration);
        }

        // Measure time for StringBuilder
        for (int i = 0; i < nValues.length; i++) {
            int n = nValues[i];
            long startTime = System.currentTimeMillis();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (StdRandom.bernoulli(0.5)) sb.append("0");
                else sb.append("1");
            }
            String s = sb.toString();
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            StdOut.printf("StringBuilder - n = %d, Time: %d ms\n", n, duration);
        }

             // Plot the results
             plotResults(nValues, timesStringConcat, timesStringBuilder);
            }
        
            private static void plotResults(int[] nValues, double[] timesStringConcat, double[] timesStringBuilder) {
                // Set up the canvas
                StdDraw.setCanvasSize(800, 600);
                StdDraw.setXscale(-1, nValues[nValues.length - 1] * 1.1);
                StdDraw.setYscale(-5, Math.max(timesStringConcat[timesStringConcat.length - 1], timesStringBuilder[timesStringBuilder.length - 1]) * 1.1);
                StdDraw.setPenRadius(0.005);
        
                // Plot String Concatenation times
                StdDraw.setPenColor(StdDraw.RED);
                for (int i = 0; i < nValues.length; i++) {
                    StdDraw.point(nValues[i], timesStringConcat[i]);
                    if (i > 0) {
                        StdDraw.line(nValues[i - 1], timesStringConcat[i - 1], nValues[i], timesStringConcat[i]);
                    }
                }
        
                // Plot StringBuilder times
                StdDraw.setPenColor(StdDraw.BLUE);
                for (int i = 0; i < nValues.length; i++) {
                    StdDraw.point(nValues[i], timesStringBuilder[i]);
                    if (i > 0) {
                        StdDraw.line(nValues[i - 1], timesStringBuilder[i - 1], nValues[i], timesStringBuilder[i]);
                    }
                }
        
                // Draw labels and legend
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.text(nValues[nValues.length - 1] * 0.5, Math.max(timesStringConcat[timesStringConcat.length - 1], timesStringBuilder[timesStringBuilder.length - 1]) * 1.05, "String Concatenation");
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.text(nValues[nValues.length - 1] * 0.5, Math.max(timesStringConcat[timesStringConcat.length - 1], timesStringBuilder[timesStringBuilder.length - 1]) * 0.95, "StringBuilder");
            }
        }
        