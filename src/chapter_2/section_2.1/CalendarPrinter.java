import java.util.Calendar;
/*
 * Write a program Calendar that takes two integer command-
line arguments m and y and prints the monthly calendar for month m of year y, as
in this example:
% java Calendar 2 2009
February 2009
S M Tu W Th F S
1 2 3 4 5 6 7
8 9 10 11 12 13 14
15 16 17 18 19 20 21
22 23 24 25 26 27 28
 */
public class CalendarPrinter {
    public static void main(String[] args) {
        // Check for correct number of command-line arguments
        if (args.length < 2) {
            System.out.println("Usage: java CalendarPrinter <month> <year>");
            System.exit(1);
        }

        int month = Integer.parseInt(args[0]); // month (Jan = 1, Dec = 12)
        int year = Integer.parseInt(args[1]);  // year

        // Create an instance of Calendar
        Calendar cal = Calendar.getInstance();

        // Set the calendar to the first day of the specified month and year
        cal.set(year, month - 1, 1);

        // Retrieve the first day of the week (Sunday = 1, Monday = 2, ..., Saturday = 7)
        int startDay = cal.get(Calendar.DAY_OF_WEEK);

        // Retrieve the number of days in the specified month
        int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Print the calendar header
        System.out.println(" " + cal.getDisplayName(Calendar.MONTH, Calendar.LONG, java.util.Locale.US) + " " + year);
        System.out.println(" S  M Tu  W Th  F  S");

        // Print leading spaces for the first row
        for (int i = 1; i < startDay; i++) {
            System.out.print("   ");
        }

        // Print the days of the month starting from the correct position
        for (int i = 1, dayOfWeek = startDay; i <= numberOfDays; i++, dayOfWeek++) {
            System.out.printf("%2d ", i);
            if (dayOfWeek == 7) { // When it's Saturday, move to the next line
                dayOfWeek = 0;
                System.out.println();
            }
        }
        System.out.println(); // Print a new line at the end of the calendar
    }
}
