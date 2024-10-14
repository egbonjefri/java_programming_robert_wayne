import lib.StdAudio;
/*
 * Write a program that takes the name of a .wav ﬁle and a playback rate
r as command-line arguments and plays the ﬁle at the given rate. First, use
StdAudio.read() to read the ﬁle into an array a[]. If r = 1, play a[]; otherwise,
create a new array b[] of approximate size r times the length of a[]. If r < 1, popu-
late b[] by sampling from the original; if r > 1, populate b[] by interpolating from
the original. Then play b[].
 */
public class WavPlayer {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java WavPlayer <filename.wav> <playback_rate>");
            return;
        }

        String filename = args[0];
        double playbackRate = Double.parseDouble(args[1]);

        // Read the .wav file into an array
        double[] originalArray = StdAudio.read(filename);

        // Check if the playback rate is 1, then play the original array
        if (playbackRate == 1.0) {
            StdAudio.play(originalArray);
        } else {
            // Calculate the new array size based on the playback rate
            int newArraySize = (int) (originalArray.length / playbackRate);

            // Create a new array for the modified playback
            double[] modifiedArray = new double[newArraySize];

            // Populate the new array using interpolation or sampling
            if (playbackRate < 1.0) {
                // Downsampling - Sampling from the original array
                /*
                 * Since playbackRate is less than 1.0, 
                 * this expression effectively reduces the index
                 *  by a factor of 1/playbackRate
                 */
                for (int i = 0; i < newArraySize; i++) {
                    modifiedArray[i] = originalArray[(int) (i * playbackRate)];
                }
            } else {
                // Upsampling - Linear Interpolation
                /**
                * Linear interpolation formula:
                * InterpolatedValue = (1 - fraction) * ValueLower + fraction * ValueUpper
                * Calculates a weighted average between two neighboring points based on a fractional distance.
                * - fraction: Fractional part of the index, representing the distance between the calculated 
                index and the lower index.
                * - ValueLower: Value at the lower integer index in the original array.
                * - ValueUpper: Value at the next integer index (upper index) in the original array.
                * This formula is used for upsampling to estimate values between known data points.
                The formula essentially calculates a weighted average of the values at the lower and upper indices, 
                where the weights are determined by the fractional distance. If the fraction is 0, the result is 
                equal to the value at the lower index. If the fraction is 1, the result is equal to the value at 
                the upper index. For fractional values between 0 and 1, the result is a linear combination of the 
                values at the lower and upper indices.
 */

                for (int i = 0; i < newArraySize; i++) {
                    double index = i * 1.0 / playbackRate;
                    int lowerIndex = (int) index;
                    int upperIndex = Math.min(lowerIndex + 1, originalArray.length - 1);

                    double fraction = index - lowerIndex;
                    modifiedArray[i] = (1 - fraction) * originalArray[lowerIndex] +
                            fraction * originalArray[upperIndex];
                }
            }

            // Play the modified array
            StdAudio.play(modifiedArray);
        }
    }
}
