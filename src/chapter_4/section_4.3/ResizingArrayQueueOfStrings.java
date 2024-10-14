/*
 * Develop a data type ResizingArrayQueueOfStrings that implements a
queue with a ﬁxed-length array in such a way that all operations take constant time.
Then, extend your implementation to use a resizing array to remove the length re-
striction. Hint: The challenge is that the items will “crawl across” the array as items
are added to and removed from the queue. Use modular arithmetic to maintain the
array indices of the items at the front and back of the queue.
 */

public class ResizingArrayQueueOfStrings {
    private String[] queue;
    private int head;
    private int tail;
    private int size;

    public ResizingArrayQueueOfStrings() {
        queue = new String[2]; // Initial capacity
        head = 0;
        tail = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(String item) {
        if (size == queue.length) {
            resize(2 * queue.length); // Double the size of the array
        }
        queue[tail] = item;
        tail = (tail + 1) % queue.length;
        size++;
    }

    public String dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        String item = queue[head];
        queue[head] = null; // Avoid loitering
        head = (head + 1) % queue.length;
        size--;
        if (size > 0 && size == queue.length / 4) {
            resize(queue.length / 2); // Halve the size of the array
        }
        return item;
    }

    private void resize(int capacity) {
        String[] temp = new String[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = queue[(head + i) % queue.length];
        }
        queue = temp;
        head = 0;
        tail = size;
    }

}