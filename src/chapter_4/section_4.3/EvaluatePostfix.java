/*
 * Write a program EvaluatePostfix that takes a postﬁx expression from
standard input, evaluates it, and prints the value. (Piping the output of your pro-
gram from the previous exercise to this program gives equivalent behavior to
Evaluate, in PROGRAM 4.3.5.)
 */

import lib.StdIn;
import lib.Stack;
import lib.StdOut;

public class EvaluatePostfix {
    public static void main(String[] args) {
        
        StdOut.println("Enter a postfix expression:");
        String postfix = StdIn.readLine();
        try {
            double result = evaluatePostfix(postfix);
            StdOut.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            StdOut.println("Error: " + e.getMessage());
        }
        
    }

    public static double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = postfix.split("\\s+");

        for (String token : tokens) {
            if (isNumeric(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid postfix expression");
                }
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                double result = performOperation(token, operand1, operand2);
                stack.push(result);
            } else {
                throw new IllegalArgumentException("Invalid token: " + token);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid postfix expression");
        }

        return stack.pop();
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^");
    }

    private static double performOperation(String operator, double operand1, double operand2) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new IllegalArgumentException("Division by zero");
                }
                return operand1 / operand2;
            case "^":
                return Math.pow(operand1, operand2);
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}