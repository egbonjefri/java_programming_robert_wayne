/*
 * Write a program that takes a string as a command-line
argument, reads a dictionary of words from standard input, and checks whether
the command-line argument is a “good” password. Here, assume “good” means
that it (1) is at least eight characters long, (2) is not a word in the dictionary, (3) is
not a word in the dictionary followed by a digit 0-9 (e.g., hello5), (4) is not two
words separated by a digit (e.g., hello2world), and (5) none of (2) through (4)
hold for reverses of words in the dictionary.
 */
import lib.SET;
import lib.StdIn;
import lib.StdOut;

public class PasswordChecker {
    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("Usage: java PasswordChecker <password>");
            return;
        }

        String password = args[0];
        SET<String> dictionary = readDictionary();

        if (isGoodPassword(password, dictionary)) {
            StdOut.println("The password is good.");
        } else {
            StdOut.println("The password is not good.");
        }
    }

    private static SET<String> readDictionary() {
        SET<String> dictionary = new SET<>();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readLine().trim().toLowerCase();
            if (!word.isEmpty()) {
                dictionary.add(word);
            }
        }
        return dictionary;
    }

    private static boolean isGoodPassword(String password, SET<String> dictionary) {
        // Check length
        if (password.length() < 8) {
            return false;
        }

        String lowercasePassword = password.toLowerCase();

        // Check against dictionary words and their variations
        for (String word : dictionary) {
            if (lowercasePassword.equals(word) ||
                lowercasePassword.matches(word + "\\d") ||
                lowercasePassword.matches("\\w+" + "\\d" + "\\w+")) {
                return false;
            }

            // Check reverse of the word
            String reversedWord = new StringBuilder(word).reverse().toString();
            if (lowercasePassword.equals(reversedWord) ||
                lowercasePassword.matches(reversedWord + "\\d") ||
                lowercasePassword.matches("\\w+" + "\\d" + "\\w+")) {
                return false;
            }
        }

        return true;
    }
}