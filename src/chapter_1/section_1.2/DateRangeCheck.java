import lib.StdOut;


public class DateRangeCheck {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java DateRangeCheck <month> <day>");
            return;
        }

        try {
            int month = Integer.parseInt(args[0]);
            int day = Integer.parseInt(args[1]);

            boolean isInRange = isBetweenMarch20AndJune20(month, day);
            StdOut.println(isInRange);

        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid input. Please enter integers only.");
        }
    }

    private static boolean isBetweenMarch20AndJune20(int month, int day) {
        if (month < 3 || month > 6) {
            return false; 
        }

        if (month == 3 && day < 20) {
            return false; // Before March 20th
        }

        if (month == 6 && day > 20) {
            return false; // After June 20th
        }

        return true; // Within the range
    }
}
