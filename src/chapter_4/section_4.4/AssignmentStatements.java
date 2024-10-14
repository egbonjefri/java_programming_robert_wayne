/*
 * Write a program to parse and evaluate programs
consisting of assignment and print statements with fully parenthesized arithmetic
expressions (see PROGRAM 4.3.5). For example, given the input
A = 5
B = 10
C = A + B
D = C * C
print(D)
your program should print the value 225. Assume that all variables and values are
of type double. Use a symbol table to keep track of variable names.
 */
import lib.ST;
import lib.Stack;
import lib.StdOut;
import lib.StdIn;

public class AssignmentStatements {
       private ST<String, Double> symbolTable;

    public AssignmentStatements() {
        symbolTable = new ST<>();
    }

    public void assign(String variable, String expression) {
        double value = evaluate(expression);
        symbolTable.put(variable, value);
    }

    public void print(String variable) {
        if (symbolTable.contains(variable)) {
            StdOut.println(symbolTable.get(variable));
        } else {
            StdOut.println("Variable not found: " + variable);
        }
    }

    private double evaluate(String expression) {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isWhitespace(ch)) continue;

            if (Character.isDigit(ch) || Character.isLetter(ch)) {
                StringBuilder sb = new StringBuilder();

                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || Character.isLetter(expression.charAt(i)))) {
                    sb.append(expression.charAt(i++));
                }

                i--; // Step back as the loop has moved one step ahead
                
                String token = sb.toString();

                if (Character.isLetter(token.charAt(0))) {
                    if (symbolTable.contains(token)) {
                        values.push(symbolTable.get(token));
                    } else {
                        throw new RuntimeException("Unknown variable: " + token);
                    }
                } else {
                    values.push(Double.parseDouble(token));
                }
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (operators.peek() != '(') {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop();
            } else if (isOperator(ch)) {
                while (!operators.isEmpty() && hasPrecedence(ch, operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(ch);
            }
        }

        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false;
        return true;
    }

    private double applyOperator(char operator, double b, double a) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
        }
        throw new RuntimeException("Unknown operator: " + operator);
    }

    public static void main(String[] args) {
        AssignmentStatements evaluator = new AssignmentStatements();

        while (!StdIn.isEmpty()) {
            String line = StdIn.readLine().trim();

            if (line.startsWith("print(")) {
                int start = line.indexOf('(');
                int end = line.indexOf(')');
                String variable = line.substring(start + 1, end);
                evaluator.print(variable);
            } else if (line.contains("=")) {
                String[] parts = line.split("=");
                String variable = parts[0].trim();
                String expression = parts[1].trim();
                evaluator.assign(variable, expression);
            } else if (line.isEmpty()) {
                break;
            }
        }

    }
}
