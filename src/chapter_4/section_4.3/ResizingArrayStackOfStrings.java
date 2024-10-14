import lib.StdIn;
import lib.StdOut;


public class ResizingArrayStackOfStrings {
    private static final int INITIAL_CAPACITY = 8;
    private String[] items;
    private int size;

    public ResizingArrayStackOfStrings() {
        items = new String[INITIAL_CAPACITY];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int capacity) {
        String[] temp = new String[capacity];
        System.arraycopy(items, 0, temp, 0, size);
        items = temp;
    }

    public void push(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (size == items.length) {
            resize(2 * items.length);
        }
        items[size++] = item;
    }

    public String pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        String item = items[--size];
        items[size] = null; // Avoid loitering
        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    public String peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return items[size - 1];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = size - 1; i >= 0; i--) {
            sb.append(items[i]).append(" ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {

        ResizingArrayStackOfStrings stack = new ResizingArrayStackOfStrings();

       
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                try {
                    stack.push(item);
                } catch (IllegalStateException e) {
                    StdOut.println("Cannot push: " + e.getMessage());
                }
            } else if (!stack.isEmpty()) {
                StdOut.print(stack.pop() + " ");
            }
        }
    
    }
}