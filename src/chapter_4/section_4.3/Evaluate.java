
import lib.StdIn;
import lib.StdOut;
import lib.Stack;

public class Evaluate {
    public static void main(String[] args) {
        // Stack to hold operators
        Stack<String> operators = new Stack<String>();
        
        // Stack to hold values
        Stack<Double> values = new Stack<Double>();
        
        // Continue reading input until the end
        while (!StdIn.isEmpty()) {
            // Read the current token
            String token = StdIn.readString();
            
            // Process the token based on its type
            if (token.equals("(")) {
                // Do nothing for opening parenthesis
            } else if (token.equals("+")) {
                operators.push(token);
            } else if (token.equals("-")) {
                operators.push(token);
            } else if (token.equals("*")) {
                operators.push(token);
            } else if (token.equals("sqrt")) {
                operators.push(token);
            } else if (token.equals(")")) {
                // Pop the operator and evaluate the expression
                String operator = operators.pop();
                double value = values.pop();
                
                if (operator.equals("+")) {
                    value = values.pop() + value;
                } else if (operator.equals("-")) {
                    value = values.pop() - value;
                } else if (operator.equals("*")) {
                    value = values.pop() * value;
                } else if (operator.equals("sqrt")) {
                    value = Math.sqrt(value);
                }
                
                // Push the result back onto the value stack
                values.push(value);
            } else {
                // Token is a number; parse and push onto the value stack
                values.push(Double.parseDouble(token));
            }
        }
        
        // Output the final result
        StdOut.println(values.pop());
    }
}
