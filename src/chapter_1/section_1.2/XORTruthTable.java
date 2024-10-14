import lib.StdOut;

/*
 * The exclusive or operator ^ for boolean operands is deﬁned to be true if
 * they are different false if they are the same. 
 * a java program that prints a truth table for this function.
 */
public class XORTruthTable {

    public static void main(String[] args) {
        // Table headers
        StdOut.println("A | B | A ^ B");
        StdOut.println("---+---+------");

        boolean[] operands = {false, true};

        for (boolean a : operands) {
            for (boolean b : operands) {
                boolean result = a ^ b; 
                StdOut.printf("%s | %s | %s%n", a, b, result); 
            }
        }
    }
}
