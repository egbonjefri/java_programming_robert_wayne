/*
 * Safe password verification. Write a static method that takes a string as an
argument and returns true if it meets the following conditions, false otherwise:
• At least eight characters long
• Contains at least one digit (0–9)
• Contains at least one uppercase letter
• Contains at least one lowercase letter
• Contains at least one character that is neither a letter nor a number
Such checks are commonly used for passwords on the web.
 */
import lib.StdOut;

public class PasswordChecker {

    public static boolean passwordChecker(String s) {
        if (s == null || s.length() < 8) return false;

        int numCount = 0;
        int upCount = 0;
        int loCount = 0;
        int spCount = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) numCount++;
            else if (Character.isUpperCase(c)) upCount++;
            else if (Character.isLowerCase(c)) loCount++;
            else if ((c >= 33 && c <= 47) || (c >= 58 && c <= 64) || (c >= 91 && c <= 96)) spCount++; // Check for special chars
        }

        return numCount > 0 && upCount > 0 && loCount > 0 && spCount > 0;
    }

    public static void main(String[] args) {
        String password = args[0];
        StdOut.println(passwordChecker(password));
    }
}
