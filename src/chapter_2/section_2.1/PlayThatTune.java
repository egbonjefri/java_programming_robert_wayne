/******************************************************************************
 *  Compilation:  javac PlayThatTune.java
 *  Execution:    java PlayThatTune < input.txt
 *  Dependencies: StdAudio.java StdAudio.java
 *
 *  This is a data-driven program that plays pure tones from
 *  the notes on the chromatic scale, specified on standard input
 *  by their distance from concert A.
 *
 *  % java PlayThatTune < elise.txt
 *
 * Modify PlayThatTune to add harmonics two octaves away from each note,
    with half the weight of the one-octave harmonics.
 *
 *  Data files
 *  ----------
 *  https://introcs.cs.princeton.edu/java/21function/elise.txt
 *  https://introcs.cs.princeton.edu/java/21function/freebird.txt
 *  https://introcs.cs.princeton.edu/java/21function/Ascale.txt
 *  https://introcs.cs.princeton.edu/java/21function/National_Anthem.txt
 *  https://introcs.cs.princeton.edu/java/21function/looney.txt
 *  https://introcs.cs.princeton.edu/java/21function/StairwayToHeaven.txt
 *  https://introcs.cs.princeton.edu/java/21function/entertainer.txt
 *  https://introcs.cs.princeton.edu/java/21function/old-nassau.txt
 *  https://introcs.cs.princeton.edu/java/21function/arabesque.txt
 *  https://introcs.cs.princeton.edu/java/21function/firstcut.txt
 *  https://introcs.cs.princeton.edu/java/21function/tomsdiner.txt
 *
 ******************************************************************************/
import lib.StdAudio;
import lib.StdIn;
public class PlayThatTune {

    public static void main(String[] args) {

        // repeat as long as there are more integers to read in
        while (!StdIn.isEmpty()) {

            // read in the pitch, where 0 = Concert A (A4)
            int pitch = StdIn.readInt();

            // read in duration in seconds
            double duration = StdIn.readDouble();

            // calculate the frequencies for the fundamental and harmonics
            double hz = 440 * Math.pow(2, pitch / 12.0);
            double hzOctave = hz * 2;        // one octave above
            double hzTwoOctaves = hz * 4;    // two octaves above

            // calculate the number of samples
            int n = (int) (StdAudio.SAMPLE_RATE * duration);
            double[] a = new double[n+1];

            // generate the combined wave with harmonics
            for (int i = 0; i <= n; i++) {
                a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE)
                     + 0.5 * Math.sin(2 * Math.PI * i * hzOctave / StdAudio.SAMPLE_RATE)
                     + 0.25 * Math.sin(2 * Math.PI * i * hzTwoOctaves / StdAudio.SAMPLE_RATE);
            }

            // play it using standard audio
            StdAudio.play(a);
        }
    }
}
