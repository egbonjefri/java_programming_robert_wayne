/*
 * Write a program that takes a start string and a stop string as command-
line arguments and prints all substrings of a given string that start with the ﬁrst,
end with the second, and otherwise contain neither. Note: Be especially careful of
overlaps!
 */
import lib.StdOut;

public class SubstringFinder {

    private static void findAndPrintSubstrings(String input, String start, String stop) {
        int startIndex = 0;
        while ((startIndex = input.indexOf(start, startIndex)) != -1) {
            int stopIndex = input.indexOf(stop, startIndex + start.length());
            if (stopIndex != -1) {
                // Extract the substring from startIndex to stopIndex+stop.length() to include the stop string
                String substring = input.substring(startIndex, stopIndex + stop.length());
                // Check if the substring does not contain the start or stop strings in between
                if (isValidSubstring(substring, start, stop)) {
                    StdOut.println(substring);
                }
                // Move startIndex past the current start string to find subsequent substrings
                startIndex = stopIndex + stop.length();
            } else {
                // No stop string found after the current start string, break the loop
                break;
            }
        }
    }

    private static boolean isValidSubstring(String substring, String start, String stop) {
        // Check if the substring contains the start or stop strings in between (excluding the ends)
        String inBetween = substring.substring(start.length(), substring.length() - stop.length());
        return !inBetween.contains(start) && !inBetween.contains(stop);
    }

    public static void main(String[] args) {
        // Example strings for testing
        String inputString = "This is a test with ATGmiddleTAA then ATGanotherTestTAA and ATGinvalidTGAone.";
        String startString = "ATG";
        String stopString = "TAA";
        
        StdOut.println("Input String: " + inputString);
        StdOut.println("Start String: " + startString);
        StdOut.println("Stop String: " + stopString);
        StdOut.println("Found substrings:");
        
        findAndPrintSubstrings(inputString, startString, stopString);
    }
}
