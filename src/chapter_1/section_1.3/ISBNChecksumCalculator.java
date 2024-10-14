import lib.StdOut;

public class ISBNChecksumCalculator {
    public static void main(String[] args) {
        if (args.length != 1) {
           StdOut.println("Usage: java ISBNChecksumCalculator <9-digit-integer>");
            return;
        }

        String input = args[0];

        if (input.length() != 9) {
            System.out.println("Please enter a 9-digit integer.");
            return;
        }

        int checksum = calculateISBNChecksum(input);
        String isbn = input + checksum;

       StdOut.println("ISBN with checksum: " + isbn);
    }

    private static int calculateISBNChecksum(String input) {
        int checksum = 0;

        for (int i = 0; i < 9; i++) {
            char digit = input.charAt(i);
            int value = Character.getNumericValue(digit);

            checksum += (i + 1) * value;
        }

        checksum %= 11;
        return (checksum == 10) ? 'X' : checksum;
    }
}
