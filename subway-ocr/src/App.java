/* In the Tokyo subway system, routes are labeled by letters
and stops by numbers, such as G-8 or A-3. Stations allowing transfers are sets of
stops. Find a Tokyo subway map on the web, develop a simple ﬁle format, and
write a Graph client that reads a ﬁle and can answer shortest-path queries for the
Tokyo subway system. If you prefer, do the Paris subway system, where routes are
sequences of names and transfers are possible when two stations have the same
name.
 */





 import java.awt.image.BufferedImage;
 import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.Rectangle;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.Word;

/*
 * compilation: javac -d bin -cp "lib/*" src/App.java
 * run: java -Djava.library.path=native-lib -cp "bin:lib/*" App
 */


public class App {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    public static void main(String[] args) {
        String imageFile = "./src/main.png";
        String stationsFile = "./src/stations.txt";
        String processedImagePath = "./src/processed.png";
    
        // Create Tesseract instance
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("./tessdata-main");
        tesseract.setTessVariable("user_defined_dpi", "70");
        try {
            Set<String> stationNames = loadStationNames(stationsFile);
    
            Mat image = Imgcodecs.imread(imageFile);
            Mat binary = preprocessImage(image);

            Imgcodecs.imwrite(processedImagePath, binary);

            BufferedImage bufferedImage = matToBufferedImage(binary);

            List<Word> words = tesseract.getWords(bufferedImage, ITessAPI.TessPageIteratorLevel.RIL_WORD);

    // Iterate over the words and print their coordinates
    for (Word word : words) {
        String text = word.getText();
        Rectangle rect = word.getBoundingBox();  // Coordinates of the text
        int x = rect.x;
        int y = rect.y;
        int width = rect.width;
        int height = rect.height;

        System.out.println("Word: " + text + " at (x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ")");

        // Optionally, draw the bounding box on the output image
        Imgproc.rectangle(binary, new Point(x, y), new Point(x + width, y + height), new Scalar(0, 255, 0), 2);
    }

    // Save the output image
    String outputPath = "./src/output_with_words_boxes.png";
    Imgcodecs.imwrite(outputPath, binary);
    System.out.println("Output image with bounding boxes saved to: " + outputPath);

             /* 
            // Perform OCR
            String result = tesseract.doOCR(bufferedImage);
            List<Rectangle> boundingBoxes = tesseract.getSegmentedRegions(bufferedImage, ITessAPI.TessPageIteratorLevel.RIL_WORD);

            // Split OCR result into tokens
            String[] tokens = result.toLowerCase().split("[^a-zA-Z0-9\\-']+"); 

            // Create a copy of the original image for drawing bounding boxes
        Mat outputImage = image.clone();

        // Match tokens to station names and draw bounding boxes
        matchTokensToStations(tokens, boundingBoxes, stationNames, outputImage);

        // Save the final output image with bounding boxes
        String outputPath = "./src/output_with_boxes.png";
        Imgcodecs.imwrite(outputPath, outputImage);

        System.out.println("Output image with bounding boxes saved to: " + outputPath);
*/
        } catch (IOException e) {
            System.err.println("Error reading station names file: " + stationsFile + " - " + e.getMessage());
        }  
    }
    
/**
 * Reads the station names from a file and stores them in a Set.
 */
private static Set<String> loadStationNames(String stationsFilePath) throws IOException {
    Set<String> stationNames = new HashSet<>();
    try (BufferedReader br = new BufferedReader(new FileReader(stationsFilePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            stationNames.add(line.trim().toLowerCase());
        }
    }
    return stationNames;
}
public static int levenshteinDistance(String a, String b) {
    int[][] dp = new int[a.length() + 1][b.length() + 1];

    for (int i = 0; i <= a.length(); i++) {
        for (int j = 0; j <= b.length(); j++) {
            if (i == 0) {
                dp[i][j] = j;
            } else if (j == 0) {
                dp[i][j] = i;
            } else {
                dp[i][j] = Math.min(dp[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1),
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
            }
        }
    }
    return dp[a.length()][b.length()];
}

private static Mat preprocessImage(Mat original) {
    Mat gray = new Mat();
    Imgproc.cvtColor(original, gray, Imgproc.COLOR_BGR2GRAY);
    Mat binary = new Mat();
    Imgproc.threshold(gray, binary, 80, 255, Imgproc.THRESH_BINARY);
    Imgproc.GaussianBlur(binary, binary, new Size(3, 3), 0);
    
    return binary;
}

private static BufferedImage matToBufferedImage(Mat mat) {
    int type = BufferedImage.TYPE_BYTE_GRAY;
    if (mat.channels() > 1) {
        type = BufferedImage.TYPE_3BYTE_BGR;
    }
    int bufferSize = mat.channels() * mat.cols() * mat.rows();
    byte[] buffer = new byte[bufferSize];
    mat.get(0, 0, buffer);
    BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
    final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
    System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
    return image;
}
/**
 * Matches tokens to station names (both exact and fuzzy matching) and draws bounding boxes.
 */
private static void matchTokensToStations(String[] tokens, List<Rectangle> boundingBoxes, Set<String> stationNames, Mat outputImage) {
    for (int i = 0; i < tokens.length && i < boundingBoxes.size(); i++) {
        String token = tokens[i];

        // Check if the token matches a station name
        if (stationNames.contains(token)) {
            System.out.println(token);
            drawBoundingBox(outputImage, boundingBoxes.get(i), new Scalar(0, 255, 0)); // Green for exact match
        } else {
            // Perform fuzzy matching using Levenshtein distance
            for (String station : stationNames) {
                if (levenshteinDistance(token, station) <= 2) {
                    System.out.println(token);
                    drawBoundingBox(outputImage, boundingBoxes.get(i), new Scalar(0, 255, 255)); // Yellow for fuzzy match
                    break;
                }
            }
        }
    }
}
/**
 * Draws a bounding box around the detected text in the output image.
 */
private static void drawBoundingBox(Mat image, Rectangle boundingBox, Scalar color) {
    Imgproc.rectangle(image, new Point(boundingBox.x, boundingBox.y), 
                      new Point(boundingBox.x + boundingBox.width, boundingBox.y + boundingBox.height), 
                      color, 2);
}

}