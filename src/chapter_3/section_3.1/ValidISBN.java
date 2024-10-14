/*
 * Write a program to check whether an ISBN number is valid (see EXERCISE
1.3.35), taking into account that an ISBN number can have hyphens inserted at
arbitrary places.
 */

public class ValidISBN {
    
    // Removes hyphens from the input string
    public static String removeHyphens(String s) {
        return s.replace("-", "");
    }
    
    // Checks if the string is a valid ISBN number after hyphens are removed
    public static boolean isbnChecker(String s) {
        // Check for valid length
        if (s.length() != 10 && s.length() != 13) {
            return false;
        }
        
        // Validate ISBN-10
        if (s.length() == 10) {
            for (int i = 0; i < s.length() - 1; i++) {
                if (!Character.isDigit(s.charAt(i))) {
                    return false; // First 9 characters must be digits
                }
            }
            char lastChar = s.charAt(s.length() - 1);
            if (!(Character.isDigit(lastChar) || lastChar == 'X')) {
                return false; // Last character must be a digit or 'X'
            }
        }
        
        // Validate ISBN-13
        if (s.length() == 13) {
            for (int i = 0; i < s.length(); i++) {
                if (!Character.isDigit(s.charAt(i))) {
                    return false; // All characters must be digits
                }
            }
        }
        
        return true;
    }
    
    // Computes the checksum to validate the ISBN number
    public static boolean computeChecksum(String s) {
        String cleanString = removeHyphens(s);
        
        if (!isbnChecker(cleanString)) {
            return false; // Basic validation failed
        }
        
        int checksum = 0;
        
        // Calculate checksum for ISBN-10
        if (cleanString.length() == 10) {
            for (int i = 0; i < cleanString.length(); i++) {
                int value;
                if (i == cleanString.length() - 1 && cleanString.charAt(i) == 'X') {
                    value = 10; // 'X' represents 10 in the last position
                } else {
                    value = Character.getNumericValue(cleanString.charAt(i));
                }
                checksum += value * (10 - i);
            }
            return checksum % 11 == 0;
        }
        
        // Calculate checksum for ISBN-13
        else if (cleanString.length() == 13) {
            for (int i = 0; i < cleanString.length(); i++) {
                int multiplier = (i % 2 == 0) ? 1 : 3; // Alternate multipliers of 1 and 3
                checksum += Character.getNumericValue(cleanString.charAt(i)) * multiplier;
            }
            return checksum % 10 == 0;
        }
        
        return false; // If none of the above conditions are met
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            String isbn = args[0];
            System.out.println("The ISBN " + isbn + " is valid: " + computeChecksum(isbn));
        } else {
            System.out.println("No ISBN provided.");
        }
    }
}
