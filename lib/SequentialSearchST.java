package lib;
/*
 * Develop an implementation SequentialSearchST of the symbol-table API
that maintains a linked list of nodes containing keys and values, keeping them in
arbitrary order. Test your implementation with Index, and validate the hypothesis
that using such an implementation for Index takes time proportional to the prod-
uct of the number of strings and the number of distinct strings in the input.
 */
public class SequentialSearchST<Key, Value> {
    private Node first; // First node in the linked list

    private class Node {
        Key key;
        Value value;
        Node next;

        Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    // Put key-value pair into the symbol table
    public void put(Key key, Value value) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value; // Update value if key already exists
                return;
            }
        }
        first = new Node(key, value, first); // Add new node at the beginning
    }

    // Get value associated with the given key
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.value; // Return value if key is found
            }
        }
        return null; // Return null if key is not found
    }

    // Delete key (and associated value) from the symbol table
    public void delete(Key key) {
        first = delete(first, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key)) return x.next;
        x.next = delete(x.next, key);
        return x;
    }

    // Check if the symbol table contains the given key
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // Check if the symbol table is empty
    public boolean isEmpty() {
        return first == null;
    }

    // Return the number of key-value pairs in the symbol table
    public int size() {
        int count = 0;
        for (Node x = first; x != null; x = x.next) {
            count++;
        }
        return count;
    }

    // Iterable for keys in the symbol table
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (Node x = first; x != null; x = x.next) {
            queue.enqueue(x.key);
        }
        return queue;
    }
}
