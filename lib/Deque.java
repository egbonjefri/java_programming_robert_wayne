package lib;
/*
 * A double-ended queue or deque (pronounced “deck”) is a collection
 *  that is a combination of a stack and a queue.
 * 
 *  Write a class Deque that uses a
linked list to implement the following API:

public class Deque<Item>
                Deque()             create an empty deque
boolean         isEmpty()           is the deque empty?
void            enqueue(Item item)  add item to the end
void            push(Item item)     add item to the beginning
Item            pop()               remove and return the item at the beginning
Item            dequeue()           remove and return the item at the end
 */

 public class Deque<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // Create an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // Is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // Add item to the end
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    // Add item to the beginning
    public void push(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;

        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.prev = first;
        }
        size++;
    }

    // Remove and return the item at the beginning
    public Item pop() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        Item item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        } else {
            first.prev = null;
        }
        return item;
    }

    // Remove and return the item at the end
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        Item item = last.item;
        last = last.prev;
        size--;
        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
        }
        return item;
    }
}
