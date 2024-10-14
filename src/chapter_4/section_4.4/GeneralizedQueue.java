/*
 * Implement a class that supports the following API,
which generalizes both a queue and a stack by supporting removal of the ith least
recently inserted item (see EXERCISE 4.3.40):


        public class GeneralizedQueue<Item>
                GeneralizedQueue()          create an empty generalized queue
boolean         isEmpty()                   is the generalized queue empty?
void            add(Item item)              insert item into the generalized queue
Item            remove(int i)               remove and return the ith least recently inserted item
int             size()                      number of items in the queue


Use a BST that associates the kth item inserted into the data structure with the key
k and maintains in each node the total number of nodes in the subtree rooted at
that node. To ﬁnd the ith least recently inserted item, search for the ith smallest
key in the BST.
 */


public class GeneralizedQueue<Item> {
    private class Node {
        int key;        // the insertion order
        Item item;      // the actual item
        Node left, right;
        int size;       // size of the subtree rooted at this node

        public Node(int key, Item item, int size) {
            this.key = key;
            this.item = item;
            this.size = size;
        }
    }

    private Node root;
    private int n = 0;  // to keep track of the insertion order

    // Create an empty generalized queue
    public GeneralizedQueue() {
        root = null;
    }

    // Is the generalized queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // Number of items in the queue
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    // Insert item into the generalized queue
    public void add(Item item) {
        n++;
        root = insert(root, n, item);
    }

    private Node insert(Node x, int key, Item item) {
        if (x == null) return new Node(key, item, 1);
        if (key < x.key) x.left = insert(x.left, key, item);
        else if (key > x.key) x.right = insert(x.right, key, item);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    // Remove and return the ith least recently inserted item
    public Item remove(int i) {
        if (i < 1 || i > size()) throw new IllegalArgumentException("Invalid index");
        Node node = select(root, i);
        root = delete(root, node.key);
        return node.item;
    }

    // Helper to find the node with the ith smallest key
    private Node select(Node x, int i) {
        if (x == null) return null;
        int leftSize = size(x.left);
        if (leftSize + 1 > i) return select(x.left, i);
        else if (leftSize + 1 < i) return select(x.right, i - leftSize - 1);
        else return x;
    }

    // Helper to delete a node with a given key
    private Node delete(Node x, int key) {
        if (x == null) return null;
        if (key < x.key) x.left = delete(x.left, key);
        else if (key > x.key) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    // Helper to find the node with the minimum key
    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    // Helper to delete the minimum node
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }
}
