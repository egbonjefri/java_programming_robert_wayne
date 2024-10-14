/*
 * Write a stack client Parentheses that reads a string of parentheses, square
brackets, and curly braces from standard input and uses a stack to determine
whether they are properly balanced. For example, your program should print true
for [()]{}{[()()]()} and false for [(])
 */


import lib.StdIn;
import lib.Stack;
import lib.StdOut;

public class Parentheses {
    public static void main(String[] args) {
        
        StdOut.println("Enter a string of parentheses, brackets, and braces:");
        String input = StdIn.readLine();
        
        boolean isBalanced = isBalanced(input);
        StdOut.println(isBalanced);
    }

    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                
                char top = stack.pop();
                if ((c == ')' && top != '(') || 
                    (c == ']' && top != '[') || 
                    (c == '}' && top != '{')) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
}
