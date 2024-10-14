import lib.StdAudio;
/*
 * Write a program that uses StdAudio to play random tunes.
Experiment with keeping in key, assigning high probabilities to whole steps, repeti-
tion, and other rules to produce reasonable melodies.
 */
public class RandomTunes {

    private static final double REFERENCE_FREQUENCY = 440.0; // A4 frequency
    private static final int REFERENCE_PITCH = 0; // A4 pitch (0 half-steps from itself)
    private static final int SAMPLING_RATE = 44100;


    public static void main(String[] args) {
        while (true) {
            playRandomTune();
        }
    }

    private static void playRandomTune() {
            int pitch = getRandomPitch();
            double duration = 0.5;
            double hz = getFrequencyForPitch(pitch);
            int n = (int) (SAMPLING_RATE * duration);
            double[] a = new double[n+1];
            for (int i = 0; i <= n; i++)
            a[i] = Math.sin(2*Math.PI * i * hz / SAMPLING_RATE);
            StdAudio.play(a);


            try {
                Thread.sleep(500); // Wait for 500 milliseconds (0.5 seconds)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted: " + e.getMessage());
            }
    }

    private static int getRandomPitch() {
        // Generate a random pitch within a certain range, e.g., -12 to 12 half-steps from A4
        return (int) (Math.random() * 25) - 12;
    }

    private static double getFrequencyForPitch(int pitch) {
        return REFERENCE_FREQUENCY * Math.pow(2, (pitch - REFERENCE_PITCH) / 12.0);
    }
}
