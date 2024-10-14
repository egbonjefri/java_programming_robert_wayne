import lib.StdRandom;
import lib.StdOut;

/*
 * Create a data type that supports the follow-
ing two operations: add() and random(). The add() method should insert a new
item into the data structure if it has not been seen before; otherwise, it should
increase its frequency count by 1. The random() method should return an item at
random, where the probabilities are weighted by the frequency of each item. Main-
tain subtree sizes in each node (see EXERCISE 4.4.29). The running time should be
proportional to the height of the tree.
 */

public class DynamicDiscreteDistribution <T extends Comparable<T>> {
    
    private Node root;

    private class Node {
        T item;
        int frequency;
        int subtreeSize;
        Node left, right;

        Node(T item) {
            this.item = item;
            this.frequency = 1;
            this.subtreeSize = 1;
        }
    }

    public DynamicDiscreteDistribution() {
    }

    public void add(T item) {
        root = add(root, item);
    }

    private Node add(Node node, T item) {
        if (node == null) {
            return new Node(item);
        }

        int cmp = item.compareTo(node.item);
        if (cmp < 0) {
            node.left = add(node.left, item);
        } else if (cmp > 0) {
            node.right = add(node.right, item);
        } else {
            node.frequency++;
        }

        node.subtreeSize = 1 + size(node.left) + size(node.right);
        return node;
    }

    public T random() {
        if (root == null) {
            return null;
        }
        int index = StdRandom.uniformInt(root.subtreeSize);
        return random(root, index);
    }

    private T random(Node node, int index) {
        int leftSize = size(node.left);
        if (index < leftSize) {
            return random(node.left, index);
        } else if (index < leftSize + node.frequency) {
            return node.item;
        } else {
            return random(node.right, index - leftSize - node.frequency);
        }
    }

    private int size(Node node) {
        return node == null ? 0 : node.subtreeSize;
    }

    // Helper method to print the tree (for debugging)
    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(Node node, int level) {
        if (node == null) {
            return;
        }
        printTree(node.right, level + 1);
        for (int i = 0; i < level; i++) {
            StdOut.print("    ");
        }
        System.out.println(node.item + " (freq: " + node.frequency + ", size: " + node.subtreeSize + ")");
        printTree(node.left, level + 1);
    }

    // Main method for testing
    public static void main(String[] args) {
        DynamicDiscreteDistribution<Integer> bst = new DynamicDiscreteDistribution<>();
        
        // Add some items
        bst.add(5);
        bst.add(2);
        bst.add(7);
        bst.add(5);
        bst.add(5);
        bst.add(2);
        
        // Print the tree structure
        StdOut.println("Tree structure:");
        bst.printTree();
        
        // Test random selection
        StdOut.println("\nRandom selections:");
        for (int i = 0; i < 10; i++) {
            StdOut.println(bst.random());
        }
    }
}
