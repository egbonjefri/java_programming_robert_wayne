package lib;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T>{
    private Node<T> head;
    private int size;

    public static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public LinkedList() {
        head = null;
        size = 0;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public void remove(T data) {
        if (head == null) return;

        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return;
        }

        Node<T> current = head;
        Node<T> prev = null;
        while (current != null && !current.data.equals(data)) {
            prev = current;
            current = current.next;
        }

        if (current != null) {
            prev.next = current.next;
            size--;
        }
    }

    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    private Node<T> getNode(Node<T> head, int index) {
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            if (current == null) return null;
            current = current.next;
        }
        return current;
    }

    public Node<T> peek() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return head;
    }
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
/*
 * Write a nonrecursive function that takes
the ﬁrst Node in a linked list as an argument and reverses the list, returning the ﬁrst
Node in the result.
 */

    public Node<T> reverse(Node<T> head){
        Node<T> previous = null; 
        Node<T> current = head; 
        Node<T> next = null;     

        while (current != null) {
            next = current.next;  
            current.next = previous; 
            previous = current;  
            current = next;  
        }

        return previous;
    }

    public Node<T> reverseRecursive(Node<T> head) {
        if (head == null || head.next == null) {
          return head; // Base case: empty list or single element
        }
      
        Node<T> newHead = reverseRecursive(head.next); // Recursively reverse the rest of the list
        head.next.next = head; // Link the last element of the reversed list to the current node
        head.next = null;   // Detach the current node from the original list
      
        return newHead;
      }

      

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        return sb.toString();
    }

    public Iterator<T> iterator() {
        return new ListIterator();
    }
    
    public class ListIterator implements Iterator<T> {
        private Node<T> current = head;
    
        @Override
        public boolean hasNext() {
            return current != null;
        }
    
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported by this iterator.");
        }
    }
    public static void main(String[] args){
        LinkedList<Integer> list = new LinkedList<>();
        Node<Integer> t = list.getNode(list.head, 1);
        StdOut.println(t);

    }
}