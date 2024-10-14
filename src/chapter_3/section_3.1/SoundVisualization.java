

/*
 * Write a program that uses StdAudio and Picture to
create an interesting two-dimensional color visualization of a sound ﬁle while it is
playing.
 */
import lib.StdAudio;
import lib.Picture;


import java.awt.Color;

public class SoundVisualization {
    public static void main(String[] args) {
        String filename = args[0];
        double[] audioData = StdAudio.read(filename);
        int width = 512; 
        int height = 512; 
        Picture picture = new Picture(width, height);
        int frameRate = 30; 
        int samplesPerFrame = audioData.length / (width * frameRate); 
        // Start playing the audio in a new thread
        new Thread(() -> StdAudio.play(audioData)).start();
        
        // Generate and display each frame
        for (int frame = 0; frame < width; frame++) {
            for (int sampleIndex = frame * samplesPerFrame; sampleIndex < (frame + 1) * samplesPerFrame && sampleIndex < audioData.length; sampleIndex++) {
                int x = frame;
                for (int y = 0; y < height; y++) {
                    int intensity = (int) ((1 + audioData[sampleIndex]) * 128); 
                    Color color = new Color(intensity, intensity, 255 - intensity); 
                    picture.set(x, y, color);
                }
            }
            
            picture.show();
            try {
                Thread.sleep(1000 / frameRate); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}