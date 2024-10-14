/*
 * Create a data type that is a queue, except
that an item may appear on the queue at most once at any given time. Ignore any
request to insert an item if it is already on the queue.
 */


 /*
  * It is not possible to achieve this behavior with
   just a basic Queue because a standard queue does not
    have built-in mechanisms for checking the uniqueness of elements. 
    Queues are designed to manage order but not content verification.
  */

import lib.SET;
import lib.Queue;

public class QueueNoDuplicates<T> {
    private Queue <T> queue;
    private SET <T> set;

    // Constructor to initialize the queue and set
    public QueueNoDuplicates() {
        queue = new Queue<>();
        set = new SET<>();
    }

    // Insert an item only if it is not already present
    public void enqueue(T item) {
        if (!set.contains(item)) {
            queue.enqueue(item);
            set.add(item);
        }
    }

    // Remove and return the item from the front of the queue
    public T dequeue() {
        if (queue.isEmpty()) {
            return null;
        }
        T item = queue.dequeue();
        set.remove(item);  // Ensure to remove it from the set as well
        return item;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // Return the size of the queue
    public int size() {
        return queue.size();
    }

    // Peek at the front of the queue without removing it
    public T peek() {
        return queue.peek();
    }

    // Check if an item is in the queue
    public boolean contains(T item) {
        return set.contains(item);
    }
}
