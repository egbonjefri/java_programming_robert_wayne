/*
 * Modify PlayThatTune to take additional command-line arguments that
control the volume (multiply each sample value by the volume) and the tempo
(multiply each note’s duration by the tempo).
 */
import lib.StdDraw;
import lib.StdAudio;
import lib.StdIn;


public class PlayThatTune {
    public static void main(String[] args) {
        int SAMPLING_RATE = 44100;

        double volume = 0.05;
        double tempo = 2.0;

        if (args.length >= 2) {
            volume = Double.parseDouble(args[0]);
            tempo = Double.parseDouble(args[1]);
        }
        // Set up the canvas for visualization
        StdDraw.setXscale(0, 10 * Math.PI);
        StdDraw.setYscale(-1.0, 1.0);

        while (!StdIn.isEmpty()) {
            // Read and play one note.
            int pitch = StdIn.readInt();
            double duration = StdIn.readDouble();

            double hz = 440 * Math.pow(2, pitch / 12.0);
            // multiply each note’s duration by the tempo
            int n = (int) (SAMPLING_RATE * (duration * tempo));
            double[] a = new double[n + 1];
            StdDraw.clear();
            StdDraw.enableDoubleBuffering();
            for (int i = 0; i <= n; i++) {
                // multiply each sample value by the volume
                a[i] = volume * Math.sin(2 * Math.PI * i * hz / SAMPLING_RATE);
                // Plot the sound wave on the canvas
                double x = 2 * Math.PI * i * hz / SAMPLING_RATE;

                StdDraw.setPenRadius(0.01);
                StdDraw.point(x, a[i]*5.0);
            }
           StdDraw.show();
           StdAudio.play(a);
        }
    }
}


/*
 * Comparison between Linear Interpolation and Sampling:
 *
 * Linear Interpolation:
 * Linear interpolation is a method of curve fitting using linear polynomials to construct new data 
 * points within the range of a discrete set of known data points.
 * It's commonly used in computer graphics, data analysis, and during the digital signal processing where intermediate values are needed.
 * The formula for linear interpolation between two points (x0, y0) and (x1, y1) for a value x is:
 * y = y0 + (y1 - y0) * ((x - x0) / (x1 - x0))
 * Where:
 * - (x0, y0) and (x1, y1) are the coordinates of the two known points,
 * - x is the point at which the interpolation is desired,
 * - y is the interpolated value.
 *
 * Sampling:
 * Sampling, on the other hand, is the process of converting a continuous signal into a discrete signal.
 * It's a fundamental process in digital signal processing.
 * The concept involves taking regular intervals or samples of a continuous function to create a discrete signal.
 * For example, in analog-to-digital conversion, sampling is the process of recording the magnitude of a continuous signal at discrete time intervals.
 * The Nyquist-Shannon sampling theorem provides a foundation for understanding sampling:
 * It states that a continuous signal can be perfectly reconstructed from its samples if the sampling frequency 
 * is greater than twice the maximum frequency of the signal (f_s > 2f_max).
 *
 * The main focus of sampling is on the conversion of signals from continuous to discrete form, 
 * while linear interpolation focuses on estimating values within the known data points.
 */
