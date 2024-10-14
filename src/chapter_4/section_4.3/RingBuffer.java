/*
 * A ring buffer (or circular queue) is a FIFO collection that stores
a sequence of items, up to a prespeciﬁed limit. If you insert an item into a ring buf-
fer that is full, the new item replaces the least recently inserted item. Ring buffers
are useful for transferring data between asynchronous processes and for storing log
ﬁles. When the buffer is empty, the consumer waits until data is deposited; when the
buffer is full, the producer waits to deposit data. Develop an API for a ring buffer
and an implementation that uses a ﬁxed-length array.
 */
public class RingBuffer<T> {
    private T[] buffer;
    private int readIndex = 0;
    private int writeIndex = 0;
    private int size = 0;
    private boolean isFull = false;
    private final int capacity;

    @SuppressWarnings("unchecked")
    public RingBuffer(int capacity) {
        this.capacity = capacity;
        buffer = (T[]) new Object[capacity];
    }

    public void put(T item) {
        buffer[writeIndex] = item;
        if (isFull) {
            readIndex = (readIndex + 1) % capacity;
        }
        writeIndex = (writeIndex + 1) % capacity;
        isFull = writeIndex == readIndex;
    }

    public T get() {
        if (isEmpty()) {
            throw new IllegalStateException("Buffer is empty");
        }
        T item = buffer[readIndex];
        buffer[readIndex] = null; //helps with garbage collection
        readIndex = (readIndex + 1) % capacity; 
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return isFull;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }
}