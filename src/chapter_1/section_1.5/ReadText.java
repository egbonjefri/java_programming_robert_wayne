import lib.StdOut;
import lib.StdIn;
/*
 * Write a program that reads in text from standard input and prints the num-
ber of words in the text. For the purpose of this exercise, a word is a sequence of
non-whitespace characters that is surrounded by whitespace.
 */
public class ReadText {
    public static void main(String[] args) {
        StdOut.println("Enter words here: (Ctrl+D to end)");

        while (!StdIn.isEmpty()) {
            String[] s = StdIn.readAllStrings();
            StdOut.println();
            StdOut.println(s.length + " words entered.");

        }
}
}