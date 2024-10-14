package lib;
public class StackOfInts {
    private Node first = null; // top of stack
    private int size;

    // Node class for linked list
    private class Node {
        int item;
        Node next;
    }

    // Push an item onto the stack
    public void push(int item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        size++;
    }

    // Pop an item from the stack
    public int pop() {
        if (isEmpty()) throw new RuntimeException("Stack underflow");
        int item = first.item;
        first = first.next;
        size--;
        return item;
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return first == null;
    }

    // Peek at the top item of the stack
    public int peek() {
        if (isEmpty()) throw new RuntimeException("Stack underflow");
        return first.item;
    }

    // Return size of the stack
    public int size() {
        return size;
    }
}
