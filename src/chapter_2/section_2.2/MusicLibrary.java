/*
 * Develop a library based on the functions in PlayThatTune
(PROGRAM 2.1.4) that you can use to write client programs to create and manipulate
songs.
 */

 import lib.StdAudio;
 import lib.StdIn;


public class MusicLibrary {
    
    // Weighted superposition of a and b
    public static double[] superpose(double[] a, double[] b, double awt, double bwt) {
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i] * awt + b[i] * bwt;
        }
        return c;
    }

    // Generate a pure tone of given frequency hz that lasts for t seconds
    public static double[] tone(double hz, double t) {
        int N = (int) (StdAudio.SAMPLE_RATE * t);
        double[] a = new double[N+1];
        for (int i = 0; i <= N; i++) {
            a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
        }
        return a;
    }

    // Play note of given pitch, with harmonics
    public static double[] note(int pitch, double t) {
        double hz = 440.0 * Math.pow(2, pitch / 12.0);
        double[] a = tone(hz, t);
        double[] hi = tone(2 * hz, t);
        double[] lo = tone(hz / 2, t);
        double[] h = superpose(hi, lo, 0.5, 0.5);
        return superpose(a, h, 0.5, 0.5);
    }

    // Play an array of samples
    public static void play(double[] samples) {
        StdAudio.play(samples);
    }

    public static void createSong() {
        double[][] notes = {
            note(0, 1.0),
            note(4, 0.5),
            note(-5, 0.75),
            note(7, 1.5)
        };

        for (double[] note : notes) {
            play(note);
        }
    }

    public static void main(String[] args) {
        // Read and play a tune, with harmonics
        while (!StdIn.isEmpty()) {
            // Read and play a note, with harmonics
            int pitch = StdIn.readInt();
            double duration = StdIn.readDouble();
            double[] a = note(pitch, duration);
            play(a);
        }
    }
}

