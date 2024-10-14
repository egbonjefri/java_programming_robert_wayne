/*
 * Write a java program that takes from standard input an expression without left
parentheses and prints the equivalent inﬁx expression with the parentheses inserted. 
For example, given the input
1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
your program should print
( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) )
 */
import lib.StdIn;
import lib.Stack;
import lib.StdOut;

public class InfixParenthesizer {
    public static void main(String[] args) {
        
        StdOut.println("Enter the expression without left parentheses:");
        String input = StdIn.readLine();
        String result = insertParentheses(input);
        StdOut.println("Expression with parentheses inserted:");
        StdOut.println(result);
    }

    public static String insertParentheses(String input) {
        String[] tokens = input.split("\\s+");
        Stack<String> stack = new Stack<>();

        for (String token : tokens) {
            if (token.equals(")")) {
                String right = stack.pop();
                String operator = stack.pop();
                String left = stack.pop();
                stack.push("( " + left + " " + operator + " " + right + " )");
            } else {
                stack.push(token);
            }
        }

        return stack.pop();
    }
}