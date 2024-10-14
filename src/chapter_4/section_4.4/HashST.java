import lib.StdOut;
import lib.LinkedList;
/*
 * This program uses an array of linked lists to implement a hash table. The hash function selects
one of the m lists. When there are n keys in the table, the average cost of a put() or get() opera-
tion is n/m, for suitable hashCode() implementations. This cost per operation is constant if we
use a resizing array to ensure that the average number of keys per list is between 1 and 8
 */

public class HashST<Key, Value> {
    /*
     * Modify HashST to use a resizing array so that the average length of the list
associated with each hash value is between 1 and 8.
     */
    private static final int INIT_CAPACITY = 4;
    private static final int MIN_AVG_LENGTH = 1;
    private static final int MAX_AVG_LENGTH = 8;


    private int m;
    private int size;
    private Node[] lists = new Node[m]; //Each element is the head of a linked list


    private static class Node{
        private Object key;
        private Object val;
        private Node next;

        public Node(Object key, Object val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
}
    public HashST() {
        this(INIT_CAPACITY);
    }

    public HashST(int capacity) {
        m = capacity;
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        if (value == null) {
            remove(key);
            return;
        }
        // double table size if average length >= MAX_AVG_LENGTH
        if (size >= MAX_AVG_LENGTH * m) resize(2 * m);

        int index = hash(key);
        //When multiple keys hash to the same index (a collision),
        // they are stored in the same linked list
        for (Node x = lists[index]; x != null; x = x.next){
            // Check if key already exists
            if (key.equals(x.key)){
                x.val = value;
                return;
            }
        }
        //if key not found at that list node, 
        // insert at the beginning of the linked list at the computed index:
        lists[index] = new Node(key, value, lists[index]);
        size++;
    }
    @SuppressWarnings("unchecked")
    public Value get(Key key) {
        int index = hash(key);
        for (Node x = lists[index]; x != null; x = x.next){
            if (key.equals(x.key)){
                return (Value) x.val;
            }
        }
        return null;
    }
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    @SuppressWarnings("unchecked")
    public Value remove(Key key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        
        int index = hash(key);
        Node prev = null;
        for (Node x = lists[index]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                if (prev == null) {
                    lists[index] = x.next;
                } else {
                    prev.next = x.next;
                }

                size--;
    // halve table size if average length <= MIN_AVG_LENGTH
        if (m > INIT_CAPACITY && size <= MIN_AVG_LENGTH * m) resize(m / 2);
                return (Value) x.val;
            }
            prev = x;
        }

        return null;
    }
    public int size() {
        return size;
    }
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        HashST<Key, Value> temp = new HashST<>(capacity);
        for (int i = 0; i < m; i++) {
            for (Node x = lists[i]; x != null; x = x.next) {
                temp.put((Key) x.key, (Value) x.val);
            }
        }
        this.m = temp.m;
        this.size = temp.size;
        this.lists = temp.lists;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public Iterable<Key> keys() {
        LinkedList<Key> keyList = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (Node x = lists[i]; x != null; x = x.next) {
                @SuppressWarnings("unchecked")
                Key key = (Key) x.key;
                keyList.add(key);
            }
        }
        return keyList;
    }
    public static void main(String[] args) {
        HashST<String, Integer> ht = new HashST<>();
        ht.put("one", 1);
        ht.put("two", 2);
        ht.put("three", 3);

        StdOut.println("Value for 'two': " + ht.get("two"));
        StdOut.println("Size: " + ht.size());

        ht.remove("two");
        StdOut.println("Size after removal: " + ht.size());
        StdOut.println("Value for 'two' after removal: " + ht.get("two"));
    }
}