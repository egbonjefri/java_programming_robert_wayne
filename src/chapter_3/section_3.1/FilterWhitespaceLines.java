import lib.In;
import lib.Out;


/*
 * Write a ﬁlter that reads text from an input stream and prints it to an output
stream, removing any lines that consist only of whitespace.
 */
public class FilterWhitespaceLines {

    public static void main(String[] args) {
        // Assuming the first argument is the input file and the second is the output file
        if (args.length != 2) {
            System.out.println("Usage: java FilterWhitespaceLines <input file> <output file>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        // Create In and Out objects
        In in = new In(inputFile);
        Out out = new Out(outputFile);

        // Read and process lines
        while (in.hasNextLine()) {
            String line = in.readLine();
            if (!line.trim().isEmpty()) {
                out.println(line);
            }
        }

        // Close the streams
        in.close();
        out.close();
    }
}
