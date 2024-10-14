/*
 * Implement a class that supports the following API,
which generalizes both a queue and a stack by supporting removal of the ith most
recently inserted item:

public class GeneralizedQueue<Item>
            GeneralizedQueue()  create an empty generalized queue
boolean     isEmpty()           is the generalized queue empty?
void        add(Item item)      insert item into the generalized queue
Item        remove(int i)       remove and return the ith least recently inserted item
int         size()              number of items on the queue

First, develop an implementation that uses a resizing array, and then develop one
that uses a linked list.
 */

 import java.util.NoSuchElementException;
 import lib.StdOut;

 public class GeneralizedQueueArray<Item> {
     private Item[] queue;
     private int size;
 
     @SuppressWarnings("unchecked")
     public GeneralizedQueueArray() {
         queue = (Item[]) new Object[2];
         size = 0;
     }
 
     public boolean isEmpty() {
         return size == 0;
     }
 
     public int size() {
         return size;
     }
 
     public void add(Item item) {
         if (size == queue.length) {
             resize(2 * queue.length);
         }
         queue[size++] = item;
     }
 
     public Item remove(int i) {
         if (isEmpty()) {
             throw new NoSuchElementException("Queue is empty");
         }
         if (i < 1 || i > size) {
             throw new IllegalArgumentException("Index out of bounds");
         }
         int index = i - 1;
         Item item = queue[index];
         for (int j = index; j < size - 1; j++) {
             queue[j] = queue[j + 1];
         }
         queue[--size] = null;
         if (size > 0 && size == queue.length / 4) {
             resize(queue.length / 2);
         }
         return item;
     }
 
     @SuppressWarnings("unchecked")
     private void resize(int capacity) {
         Item[] newQueue = (Item[]) new Object[capacity];
         System.arraycopy(queue, 0, newQueue, 0, size);
         queue = newQueue;
     }
 
     public static void main(String[] args) {
         GeneralizedQueueArray<Integer> queue = new GeneralizedQueueArray<>();
         queue.add(1);
         queue.add(2);
         queue.add(3);
         queue.add(4);
 
         StdOut.println("Removed: " + queue.remove(2)); // Removes the 2nd least recently inserted item
         StdOut.println("Size: " + queue.size()); // Output: 3
     }


public class GeneralizedQueueLinkedList {
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }

    public GeneralizedQueueLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(Item item) {
        Node<Item> oldLast = last;
        last = new Node<>(item, null);
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    public Item remove(int i) {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (i < 1 || i > size) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        Node<Item> current = first;
        Node<Item> previous = null;
        for (int j = 1; j < i; j++) {
            previous = current;
            current = current.next;
        }

        Item item = current.item;
        if (previous == null) {
            first = current.next;
        } else {
            previous.next = current.next;
        }

        if (current.next == null) {
            last = previous;
        }

        size--;
        return item;
    }



}
 }
 