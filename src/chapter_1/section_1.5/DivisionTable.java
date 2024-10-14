/*
Write a program that reads in lines from standard input with each line
containing a name and two integers and then uses printf() to print a table with a
column of the names, the integers, and the result of dividing the ﬁrst by the second,
accurate to three decimal places. You could use a program like this to tabulate bat-
ting averages for baseball players or grades for students.
*/
import lib.StdOut;
import lib.StdIn;

public class DivisionTable {
    public static void main(String[] args) {
        

        System.out.println("Enter lines with a name and two integers:");

        // Print table header
        System.out.printf("%-15s%-15s%-15s%-15s\n", "Name", "Integer 1", "Integer 2", "Result");

        while (!StdIn.isEmpty()) {
           String s = StdIn.readLine();
            // Split the line into parts
            String[] parts = s.split("\\s+");
            
            if (parts.length == 3) {
                // Extract name and integers
                String name = parts[0];
                int integer1 = Integer.parseInt(parts[1]);
                int integer2 = Integer.parseInt(parts[2]);

                // Calculate result
                double result = (double) integer1 / integer2;

                // Print the table row
                StdOut.printf("%-15s%-15d%-15d%-15.3f\n", name, integer1, integer2, result);
            } else {
                StdOut.println("Invalid input format. Please enter a name and two integers on each line.");
            }
            
        }


    }
}
// java DivisionTable < input.txt > output.txt
