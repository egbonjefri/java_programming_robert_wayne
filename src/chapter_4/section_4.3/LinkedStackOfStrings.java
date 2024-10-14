import lib.StdOut;
import lib.StdIn;
import java.util.Iterator; 
import java.util.NoSuchElementException;


public class LinkedStackOfStrings {
    private Node first;
    private int size;

    private class Node {
        private String item;
        private Node next;

        public Node(String item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public LinkedStackOfStrings() {
        first = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }


/*
 * Write a method find() that takes the ﬁrst Node in a linked list and a string
key as arguments and returns true if some node in the list has key as its item ﬁeld,
and false otherwise.
 */
    public boolean find(Node head, String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        
        while (head != null) {
            if (key.equals(head.item)) {
                return true;
            }
            head = head.next;
        }
        
        return false;
    }

/*
 * Write a method delete() that takes the ﬁrst Node in a linked list and an
int argument k and deletes the kth node in the linked list, if it exists.
 */
public Node delete(Node head, int k) {
    if (head == null || k < 1) {
        return head;  // Invalid input, return original list
    }

    // Special case: deleting the head
    if (k == 1) {
        return head.next;
    }

    Node current = head;
    Node previous = null;
    int count = 0;

    while (current != null && count < k) {
        count++;
        if (count == k) {
            previous.next = current.next;
            return head;
        }
        previous = current;
        current = current.next;
    }

    // If k is greater than the length of the list, no changes are made
    return head;
}
/*
 * Write a method remove() that takes a linked-list Node and a string key as
its arguments and removes every node in the list whose item ﬁeld is equal to key
 */

 public Node remove(Node head, String key) {
    if (key == null) {
        throw new IllegalArgumentException("Key cannot be null");
    }

    // Handle removal of head nodes that match the key
    while (head != null && key.equals(head.item)) {
        head = head.next;
    }

    Node current = head;
    Node previous = null;

    // Iterate through the list
    while (current != null) {
        if (key.equals(current.item)) {
            if (previous != null) {
                previous.next = current.next; // Skip the current node
            }
        } else {
            previous = current; // Advance previous only if current is not removed
        }
        current = current.next;
    }

    return head; // Return the new head of the list
}

/*
 * Write a method removeAfter() that takes a linked-list Node as its argu-
ment and removes the node following the given one (and does nothing if either the
argument is null or the next ﬁeld of the argument is null)
 */
    public boolean removeAfter(Node target) {
         if (target == null || target.next == null) {
             return false; 
    }
    
        target.next = target.next.next;
    return true;  
}
/*
 * Write a method copy() that takes a linked-list Node as its argument and
creates a new linked list with the same sequence of items, without destroying the
original linked list.
 */
    public LinkedStackOfStrings copy(Node head){
        LinkedStackOfStrings tempStack = new LinkedStackOfStrings();
        LinkedStackOfStrings copy = new LinkedStackOfStrings();
        
        // Push items onto a temporary stack to reverse them back
        while (head != null) {
            tempStack.push(head.item);
            head = head.next;
        }
        
        // Pop from the temporary stack to maintain the original order
        while (!tempStack.isEmpty()) {
            copy.push(tempStack.pop());
        }
        
        return copy;
    }

    public void push(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        first = new Node(item, first);
        size++;
    }

    public String pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        String item = first.item;
        first = first.next;
        size--;
        return item;
    }

    public String peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return first.item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = first;
        while (current != null) {
            sb.append(current.item).append(" ");
            current = current.next;
        }
        return sb.toString().trim();
    }

    public Iterator<String> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<String> {
        private Node current = first;
        private Node lastReturned = null;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the list");
            }
            lastReturned = current;
            String item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException("remove() can only be called once per call to next()");
            }
            
            // Remove the last returned element
            Node prev = null;
            Node temp = first;
            while (temp != lastReturned) {
                prev = temp;
                temp = temp.next;
            }
            
            if (prev == null) {
                first = first.next;
            } else {
                prev.next = lastReturned.next;
            }
            
            size--;
            lastReturned = null;
        }
    }
    public static void main(String[] args) {
        LinkedStackOfStrings stack = new LinkedStackOfStrings();

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