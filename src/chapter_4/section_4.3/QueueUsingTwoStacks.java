import lib.Stack;
import lib.StdOut;
/*
 * Show how to implement a queue using two stacks.
Hint : If you push items onto a stack and then pop them all, they appear in reverse
order. Repeating the process puts them back in FIFO order.
 */
public class QueueUsingTwoStacks<T> {
    private Stack<T> stack1; // Used for enqueue operations
    private Stack<T> stack2; // Used for dequeue operations

    public QueueUsingTwoStacks() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    // Enqueue operation
    public void enqueue(T item) {
        stack1.push(item);
    }

    // Dequeue operation
    public T dequeue() {
        if (stack2.isEmpty()) {
            // Transfer all elements from stack1 to stack2
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        
        // Check if stack2 is still empty (i.e., the queue was empty)
        if (stack2.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        
        return stack2.pop();
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    // Get the size of the queue
    public int size() {
        return stack1.size() + stack2.size();
    }

    public static void main(String[] args) {
        QueueUsingTwoStacks<Integer> queue = new QueueUsingTwoStacks<>();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        StdOut.println("Dequeued: " + queue.dequeue()); // 1
        StdOut.println("Dequeued: " + queue.dequeue()); // 2

        queue.enqueue(4);

        StdOut.println("Dequeued: " + queue.dequeue()); // 3
        StdOut.println("Dequeued: " + queue.dequeue()); // 4
    }
}
