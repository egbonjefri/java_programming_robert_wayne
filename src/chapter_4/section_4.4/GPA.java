/*
 * Write an ST client that creates a symbol table mapping letter grades to nu-
merical scores, as in the table below, and then reads from standard input a list of
letter grades and computes their average (GPA).
A+  4.33
A   4.00
A-  3.67
B+  3.33
B   3.00
B-  2.67
C+  2.33
C   2.00
C-  1.67
D   1.00
F   0.00
          
 */
import lib.ST;
import lib.StdIn;
import lib.StdOut;

public class GPA {
    public static void main(String[] args) {
        // Create a symbol table for letter grades and their corresponding numerical scores
        ST<String, Double> gradeTable = new ST<>();
        gradeTable.put("A+", 4.33);
        gradeTable.put("A", 4.00);
        gradeTable.put("A-", 3.67);
        gradeTable.put("B+", 3.33);
        gradeTable.put("B", 3.00);
        gradeTable.put("B-", 2.67);
        gradeTable.put("C+", 2.33);
        gradeTable.put("C", 2.00);
        gradeTable.put("C-", 1.67);
        gradeTable.put("D", 1.00);
        gradeTable.put("F", 0.00);

        // Variables to hold the total score and the count of grades
        double totalScore = 0.0;
        int gradeCount = 0;

        // Read grades from standard input and calculate the total score
        while (!StdIn.isEmpty()) {
            String grade = StdIn.readString();
            if (gradeTable.contains(grade)) {
                totalScore += gradeTable.get(grade);
                gradeCount++;
            } else {
                StdOut.printf("Invalid grade: %s%n", grade);
            }
        }

        // Compute and print the average GPA
        if (gradeCount > 0) {
            double gpa = totalScore / gradeCount;
            StdOut.printf("Average GPA: %.2f%n", gpa);
        } else {
            StdOut.println("No valid grades were entered.");
        }
    }
}
