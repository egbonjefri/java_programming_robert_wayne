


import java.util.Iterator;
import java.util.NoSuchElementException;
import lib.StdOut;
import lib.StdRandom;

public class LinkedListInts {
    private Node head;
    private int size;

    private class Node {
        private int item;
        private Node next;

        public Node(int item) {
            this.item = item;
            this.next = null;
        }
    }
    public LinkedListInts() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
    public void push(int item) {
        Node newNode = new Node(item);

        if (head == null) {
            head = newNode;
        }
        else{
            newNode.next = head;
            head = newNode;
            size++;
        }
        size++;
    }
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        int item = head.item;
        head = head.next;
        size--;
        return item;
    }
    private Node findMiddle(Node head, int length) {
        Node current = head;
        for (int i = 0; i < length/2 - 1; i++) {
            current = current.next;
        }
        return current;
    }
    private static Node getNode(Node head, int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            if (current == null) return null;
            current = current.next;
        }
        return current;
    }
    private static void swapItems(Node node1, Node node2) {
        int temp = node1.item;
        node1.item = node2.item;
        node2.item = temp;
    }
        // Method to merge two lists randomly
    private static Node mergeRandomly(Node left, Node right) {
            // Base cases for merging
            if (left == null) return right;
            if (right == null) return left;
    
            // Randomly choose to take from left or right
            if (StdRandom.bernoulli()) {
                left.next = mergeRandomly(left.next, right);
                return left;
            } else {
                right.next = mergeRandomly(left, right.next);
                return right;
            }
        }

        

    public class ListIterator implements Iterator<Integer> {
        private Node current = head;
    
        @Override
        public boolean hasNext() {
            return current != null;
        }
    
        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int data = current.item;
            current = current.next;
            return data;
        }
    
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported by this iterator.");
        }
    }

    /*
     * Write a method max() that takes the ﬁrst Node in a linked list as its argu-
ment and returns the value of the maximum item in the list. Assume that all items
are positive integers, and return 0 if the linked list is empty.
     */

     public int max(Node head) {
        if (head == null) {
            return 0;
        }
        int max = head.item;  
    
        Node current = head;
        while (current != null) {
            if (current.item > max) {
                max = current.item;
            }
            current = current.next;  
        }
    
        return max;
    }

    public int maxRecursive(Node head) {
        // Base case: if the list is empty, return 0
        if (head == null) {
            return 0;
        }
        // Base case: if this is the last node, return its value
        if (head.next == null) {
            return head.item;
        }
    
        // Recursive call to find the max in the rest of the list
        int maxInRest = maxRecursive(head.next);
    
        // Return the maximum of the current node's value and the max of the rest
        return Math.max(head.item, maxInRest);
    }

    /*
     * Write a method that takes the ﬁrst Node in a linked list as its argument and
reverses the list, returning the ﬁrst Node in the result.
     */

     public Node reverse(Node head) {
        Node previous = null; // This will be the new head of the reversed list
        Node current = head;  // Start with the original head
        Node next = null;
    
        while (current != null) {
            next = current.next; // Store the next node
            current.next = previous; // Reverse the current node's pointer
            previous = current; // Move previous to current
            current = next; // Move to the next node
        }
    
        return previous; // New head of the reversed list
    }
/*
Write a recursive method to print the items in a linked list in reverse order.
Do not modify any of the links. Easy : Use quadratic time, constant extra space. Also
easy : Use linear time, linear extra space. Not so easy : Develop a divide-and-conquer
algorithm that takes linearithmic time and uses logarithmic extra space. */    

    public void printReverse(Node head){
        // Base case: if this is the last node, print its value
                if (head.next == null) {
                    StdOut.println(head.item);
                    return;
                }
    // Recursive case: print the rest of the list first, then print the current item
                
                StdOut.println(head.item);
                printReverse(head.next);
    }

    public void printReverseRecursive(Node head) {
        int length = size();
        printReverseRecursive(head, length);
    }
    private void printReverseRecursive(Node head, int length) {
        if (head == null || length == 0) {
            return;
        }
        
        if (length == 1) {
            StdOut.println(head.item);
            return;
        }

        // Find the middle node
        Node middle = findMiddle(head, length);
        
        // Recursively print second half
        printReverseRecursive(middle.next, length - length/2);
        
        // Recursively print first half
        printReverseRecursive(head, length/2);
    }
/*
 * Write a recursive method to randomly shufﬂe the nodes of a linked list by
modifying the links. Easy : Use quadratic time, constant extra space. Not so easy:
Develop a divide-and-conquer algorithm that takes linearithmic time and uses
logarithmic extra memory. See EXERCISE 1.4.40 for the “merging” step.
 */
public static void shuffle(Node head, int length) {
    if (head == null || head.next == null) {
        return; // Base case: empty list or single node list
    }

    // Choose a random index from the current node to the end
    int randomIndex = StdRandom.uniformInt(length);

    // Get the node at the random index
    Node randomNode = getNode(head, randomIndex);

    // Swap the current node with the random node
    if (randomNode != null) {
        swapItems(head, randomNode);
    }

    // Recursively shuffle the sublist starting from the next node
    shuffle(head.next, length - 1);
}

    // Method to shuffle the linked list in linearithmic time
    public Node shuffleRecursive(Node head) {
        int length = size();
        // Base case: If the list is empty or has a single element, it's already shuffled
        if (head == null || head.next == null) {
            return head;
        }

        // Split the list into two halves
        Node mid = findMiddle(head, length);
        Node secondHalf = mid.next;
        mid.next = null;

        // Recursively shuffle both halves
        Node left = shuffleRecursive(head);
        Node right = shuffleRecursive(secondHalf);

        // Merge the two shuffled halves randomly
        return mergeRandomly(left, right);
    }



    public static void main(String[] args){
        LinkedListInts list = new LinkedListInts();
        int n = 20;
        for(int i = 0; i < n; i++){
            list.push(i);
        }
        list.printReverse(list.head);
    }
}
