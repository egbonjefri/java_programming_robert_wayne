/*
 * Write a program Merge that takes a delimiter string followed by an arbi-
trary number of ﬁle names as command-line arguments; concatenates the corre-
sponding lines of each ﬁle, separated by the delimiter; and then prints the result to
standard output, thus performing the opposite operation of Split (PROGRAM 3.1.9).
 */
import lib.StdOut;
import lib.In;
public class Merge {

    public static void main(String[] args) {
        if (args.length < 3) {
            StdOut.println("Usage: java Merge <delimiter> <file1> <file2> ... <fileN>");
            return;
        }

        String delimiter = args[0];
        int filesCount = args.length - 1;
        
        // Array of In objects for each input file
        In[] ins = new In[filesCount];
        for (int i = 0; i < filesCount; i++) {
            ins[i] = new In(args[i + 1]);
        }

        while (true) {
            boolean allFilesExhausted = true;
            String mergedLine = ""; // Using direct string concatenation here
            for (int i = 0; i < filesCount; i++) {
                if (ins[i].hasNextLine()) {
                    String line = ins[i].readLine();
                    // Concatenate directly with the delimiter
                    if (!mergedLine.isEmpty() && !line.isEmpty()) {
                        mergedLine += delimiter;
                    }
                    mergedLine += line;
                    allFilesExhausted = false;
                }
            }

            if (allFilesExhausted) {
                break;
            }

            // Print the merged line to standard output
            StdOut.println(mergedLine);
        }
    }
}
