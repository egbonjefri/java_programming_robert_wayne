package lib;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    private static class Node<Item> {
        Item item;
        Node<Item> next;

        Node(Item item) {
            this.item = item;
            this.next = null;
        }
    }

    public Queue() {
        first = null;
        last = null;
        size = 0;
    }
   /*
    * Copy constructor for a queue. Create a new constructor so that
Queue<Item> r = new Queue<Item>(q);
makes r a reference to a new and independent copy of the queue q.
    */
    public Queue(Queue<Item> q) {
        if (q.first != null) {
            // Use a temporary queue to reverse the order while copying
            Queue<Item> temp = new Queue<>();

            // Enqueue elements of q into temp queue
            for (Item item : q) {
                temp.enqueue(item);
            }

            // Enqueue elements from temp back to this queue to maintain order
            for (Item item : temp) {
                this.enqueue(item);
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        Node<Item> newNode = new Node<>(item);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        Item item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }
    public boolean remove(Item item) {
        if (isEmpty()) {
            return false;
        }
        
        // Special case: removing the first item
        if (first.item.equals(item)) {
            first = first.next;
            size--;
            if (isEmpty()) {
                last = null;
            }
            return true;
        }
        
        // Search for the item
        Node<Item> current = first;
        Node<Item> prev = null;
        while (current != null && !current.item.equals(item)) {
            prev = current;
            current = current.next;
        }
        
        // If item was not found
        if (current == null) {
            return false;
        }
        
        // Remove the node
        prev.next = current.next;
        size--;
        
        // If we removed the last item, update last
        if (prev.next == null) {
            last = prev;
        }
        
        return true;
    }
    public Item peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return first.item;
    }
    public  Queue<Item> mergeSortedQueues(Queue<Item> queue1, Queue<Item> queue2) {
        Queue<Item> mergedQueue = new Queue<>();

        // Merge elements until one of the queues is empty
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            // Compare the front elements of both queues and add the smaller one to the merged queue
            String str1 = (String) queue1.peek();
            String str2 = (String) queue2.peek();
            if (str1.compareTo(str2) <= 0) {
                mergedQueue.enqueue(queue1.dequeue());
            } else {
                mergedQueue.enqueue(queue2.dequeue());
            }
        }

        // Add any remaining elements from queue1 to the merged queue
        while (!queue1.isEmpty()) {
            mergedQueue.enqueue(queue1.dequeue());
        }

        // Add any remaining elements from queue2 to the merged queue
        while (!queue2.isEmpty()) {
            mergedQueue.enqueue(queue2.dequeue());
        }

        return mergedQueue;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<Item> current = first;
        while (current != null) {
            sb.append(current.item);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        return sb.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseQueueIterator();
    }

    private class ReverseQueueIterator implements Iterator<Item> {
        private Node<Item>[] nodes;
        private int currentIndex;

        @SuppressWarnings("unchecked")
        public ReverseQueueIterator() {
            nodes = (Node<Item>[]) new Node[size];
            int i = 0;
            for (Node<Item> x = first; x != null; x = x.next) {
                nodes[i++] = x;
            }
            currentIndex = size - 1;  
        }

        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return nodes[currentIndex--].item;
        }
    }
    /*
     *     // Method to create an iterator
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private Node<Item> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item data = current.item;
            current = current.next;
            return data;
        }
    }
}

     */
   public boolean contains(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        
        Node<Item> current = first;
        while (current != null) {
            if (item.equals(current.item)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

}

