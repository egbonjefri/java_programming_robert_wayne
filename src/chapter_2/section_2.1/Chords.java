import java.util.ArrayList;
import java.util.List;
import lib.StdAudio;
import lib.StdIn;

/*
 * Develop a version of PlayThatTune that can handle songs with
chords (including harmonics). Develop an input format that allows you to specify
different durations for each chord and different amplitude weights for each note
within a chord.
 */
public class Chords {

    public static void main(String[] args) {

        // Process each line of input
        while (!StdIn.isEmpty()) {
            String line = StdIn.readLine();
            String[] parts = line.split(" ");

            // Last part is the duration
            double duration = Double.parseDouble(parts[parts.length - 1]);
            
            // Process each note and weight
            List<Double> frequencies = new ArrayList<>();
            List<Double> weights = new ArrayList<>();

            for (int i = 0; i < parts.length - 1; i++) {
                String[] noteAndWeight = parts[i].split(":");
                int pitch = Integer.parseInt(noteAndWeight[0]);
                double weight = Double.parseDouble(noteAndWeight[1]);

                double hz = 440 * Math.pow(2, pitch / 12.0);
                frequencies.add(hz);
                weights.add(weight);
            }

            // Calculate the number of samples
            int n = (int) (StdAudio.SAMPLE_RATE * duration);
            double[] a = new double[n+1];

            // Generate the combined wave with harmonics for each note
            for (int i = 0; i <= n; i++) {
                double sample = 0.0;
                for (int j = 0; j < frequencies.size(); j++) {
                    double hz = frequencies.get(j);
                    double weight = weights.get(j);

                    sample += weight * (
                        Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE) +
                        0.5 * Math.sin(2 * Math.PI * i * hz * 2 / StdAudio.SAMPLE_RATE) +
                        0.25 * Math.sin(2 * Math.PI * i * hz * 4 / StdAudio.SAMPLE_RATE)
                    );
                }
                a[i] = sample;
            }

            // Play the chord using standard audio
            StdAudio.play(a);
        }
    }
}
