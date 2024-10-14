/*
 * Write a ﬁlter InfixToPostfix that converts an arithmetic expression from
inﬁx to postﬁx
 */

 

import lib.StdIn;
import lib.Stack;
import lib.StdOut;

public class InfixToPostfix {
    public static void main(String[] args) {
        
        StdOut.println("Enter an infix expression:");
        String infix = StdIn.readLine();
        String postfix = infixToPostfix(infix);
        StdOut.println("Postfix expression:");
        StdOut.println(postfix);
        
    }

    public static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : infix.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Invalid Expression";
            }
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    public static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }
}