/*
 * Consider the following recursive function, which is relat-
ed to a famous unsolved problem in number theory, known as the Collatz problem,
or the 3n+1 problem:
public static void collatz(int n)
{
StdOut.print(n + " ");
if (n == 1) return;
if (n % 2 == 0) collatz(n / 2);
else
collatz(3*n + 1);
}
For example, a call to collatz(7) prints the sequence
7 22 11 34 17 52 26 13 40 20 10 5 16 8 4 2 1
as a consequence of 17 recursive calls. Write a program that takes a command-line
argument n and returns the value of i < n for which the number of recursive
calls for collatz(i) is maximized. The unsolved problem is that no one knows
whether the function terminates for all integers (mathematical induction is no help,
because one of the recursive calls is for a larger value of the argument).
 */

public class Collatz {

        // Main method to take command-line argument and find the value with the maximum number of calls
        public static void main(String[] args) {
            // Check if an argument is provided
            if (args.length < 1) {
                System.out.println("Please provide an integer argument.");
                return;
            }
            
            int n = Integer.parseInt(args[0]);
            int maxCalls = 0;
            int numberWithMaxCalls = 1;
            
            for (int i = 1; i < n; i++) {
                int currentCalls = collatzCalls(i, 0);
                if (currentCalls > maxCalls) {
                    maxCalls = currentCalls;
                    numberWithMaxCalls = i;
                }
            }
            
            System.out.println("The number less than " + n + " with the maximum number of Collatz calls is: " + numberWithMaxCalls + " (Calls: " + maxCalls + ")");
        }
        
        // Recursive method to calculate the number of calls for the Collatz sequence for a given number
        public static int collatzCalls(int n, int calls) {
            // Base case
            if (n == 1) return calls;
            // Recursive case for even numbers
            if (n % 2 == 0) return collatzCalls(n / 2, calls + 1);
            // Recursive case for odd numbers
            else return collatzCalls(3 * n + 1, calls + 1);
        }
    }
    
