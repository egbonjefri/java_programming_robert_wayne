import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import lib.StdIn;
import lib.StdOut;


/*
 * The client pushes or pops strings
as directed from standard input (a minus sign indicates pop, and any other string indicates
push). Code in push() to test whether the stack is full is omitted
 */
public class ArrayStackOfStrings {
    private String[] items;
    private int size = 0;

    public ArrayStackOfStrings(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        items = new String[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }
/*
 * add a method isFull() to ArrayStackOfStrings (PROGRAM 4.3.1) that
returns true if the stack size equals the array capacity
 */
    public boolean isFull() {
        return size == items.length;
    }
/*
 * Modify push() to throw an
exception if it is called when the stack is full.
 */
    public void push(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (isFull()) {
            throw new IllegalStateException("Stack is full");
        }
        items[size++] = item;
    }

    public String pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        String item = items[--size];
        items[size] = null; // Avoid loitering
        return item;
    }

    public String peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return items[size - 1];
    }

    public int size() {
        return size;
    }
    public ReverseArrayIterator iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<String>{
        private int index;
       
        @Override
        public boolean hasNext() {
            return index >= 0;
        }
    
        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the array");
            }
            return items[index--];
        }
    
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported");
        }
    }
    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("Usage: java ArrayStackOfStrings <capacity>");
            return;
        }

        int capacity;
        try {
            capacity = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            StdOut.println("Capacity must be an integer");
            return;
        }

        ArrayStackOfStrings stack = new ArrayStackOfStrings(capacity);

       
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                try {
                    stack.push(item);
                } catch (IllegalStateException e) {
                    StdOut.println("Cannot push: " + e.getMessage());
                }
            } else if (!stack.isEmpty()) {
                stack.pop();
            }
        }

        ReverseArrayIterator iterator = stack.iterator();

        while (iterator.hasNext()) {
            String s = iterator.next();
            StdOut.println(s);
        }

    }
}