/*
 * Develop a data type for a buffer in a text editor that
implements the following API:

public class Buffer
            Buffer()            create an empty buffer
void        insert(char c)      insert c at the cursor position
char        delete()            delete and return the character at the cursor
void        left(int k)         move the cursor k positions to the left
void        right(int k)        move the cursor k positions to the right
int         size()              number of characters in the buffer
 */
import lib.Stack;
import lib.StdOut;

public class Buffer {
    private Stack<Character> leftStack;
    private Stack<Character> rightStack;

    // Constructor to create an empty buffer
    public Buffer() {
        leftStack = new Stack<>();
        rightStack = new Stack<>();
    }

    // Insert character c at the cursor position
    public void insert(char c) {
        leftStack.push(c);
    }

    // Delete and return the character at the cursor position
    public char delete() {
        if (rightStack.isEmpty()) {
            return '\0'; // Return null character if there's nothing to delete
        }
        return rightStack.pop();
    }

    // Move the cursor k positions to the left
    public void left(int k) {
        while (k > 0 && !leftStack.isEmpty()) {
            rightStack.push(leftStack.pop());
            k--;
        }
    }

    // Move the cursor k positions to the right
    public void right(int k) {
        while (k > 0 && !rightStack.isEmpty()) {
            leftStack.push(rightStack.pop());
            k--;
        }
    }

    // Return the number of characters in the buffer
    public int size() {
        return leftStack.size() + rightStack.size();
    }

    // Optional: Override toString for debugging purposes
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Character c : leftStack) {
            sb.append(c);
        }
        sb.append('|'); // Indicate cursor position
        for (Character c : rightStack) {
            sb.append(c);
        }
        return sb.toString();
    }

    // Main method for testing
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.insert('c');
        StdOut.println(buffer); // Output: abc|
        buffer.left(2);
        StdOut.println(buffer); // Output: a|bc
        buffer.insert('x');
        StdOut.println(buffer); // Output: ax|bc
        buffer.right(1);
        StdOut.println(buffer); // Output: axb|c
        buffer.delete();
        StdOut.println(buffer); // Output: axb|
        StdOut.println("Size: " + buffer.size()); // Output: Size: 3
    }
}