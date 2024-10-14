/*
 * Java program that takes a date as input and prints the
day of the week that date falls on. Your program should accept three int command-
line arguments: m (month), d (day), and y (year). For m, use 1 for January, 2 for
February, and so forth. For output, print 0 for Sunday, 1 for Monday, 2 for Tuesday,
and so forth. Use the following formulas, for the Gregorian calendar:
y0 = y - (14 - m) / 12
x = y0 + y0 / 4 - y0 / 4 - y0 / 100 + y0 / 400
m0 = m + 12 * ((14 - m) / 12) - 2
d0 = (d + x + (31 * m0) / 12) % 7
 */
import lib.StdOut;
public class DayOfTheWeek {
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int d = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int y0 = y - (14 - m) / 12;
        int x = y0 + y0 / 4 - y0 / 100 + y0 / 400;
        int m0 = m + 12 * ((14 - m) / 12) - 2;
        int d0 = (d + x + (31 * m0) / 12) % 7;
        
        StdOut.println(days[d0]);
    }
}
