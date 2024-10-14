/*
 * Implement the following methods, each of which takes as its argument a
Node that is the root of a binary tree.

int         size()          number of nodes in the tree
int         leaves()        number of nodes whose links are both null
double      total()         sum of the key values in all nodes

Your methods should all run in linear time.
 */
import lib.StdOut;
import lib.Queue;
public class BinarySearchTree {
    public static class Node {
        double value;
        Node left;
        Node right;

        Node(double value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public int size() {
        return size(root);
    }

    public int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    public int leaves() {
        return leaves(root);
    }

    public int leaves(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        return leaves(node.left) + leaves(node.right);
    }

    public double total() {
        return total(root);
    }

    public double total(Node node) {
        if (node == null) {
            return 0;
        }
        return node.value + total(node.left) + total(node.right);
    }
/*
Implement a linear-time method height() that returns the maximum
number of links on any path from the root to a leaf node (the height of a one-node
tree is 0). */    


    public int height() {
        return height(root);
    }

    public int height(Node node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

/*
 * A binary tree is heap ordered if the key at the root is larger than the keys
in all of its descendants. Implement a linear-time method heapOrdered() that
returns true if the tree is heap ordered, and false otherwise.
 */

 public boolean heapOrdered() {
    return heapOrdered(root, root.value);
}

private boolean heapOrdered(Node node, double parentKey) {
    // An empty tree is considered heap ordered
    if (node == null) {
        return true;
    }

    // Check if the current node's key is smaller than its parent's key
    // Double.compare() returns 0 for equal values
    if (Double.compare(node.value, parentKey) > 0) {
        return false;
    }

    return heapOrdered(node.left, node.value) && heapOrdered(node.right, node.value);
}

/*
 * A binary tree is balanced if both its subtrees are balanced and the height of
its two subtrees differ by at most 1. Implement a linear-time method balanced()
that returns true if the tree is balanced, and false otherwise.
 */
public boolean balanced() {
    return balanced(root) != -1;
}

// Helper method to check the balance and calculate height simultaneously
private int balanced(Node node) {
    // An empty tree is considered balanced and has height -1
    if (node == null) {
        return 0;
    }

    // Check the balance of the left subtree
    int leftHeight = balanced(node.left);
    if (leftHeight == -1) return -1;  // Left subtree is not balanced

    // Check the balance of the right subtree
    int rightHeight = balanced(node.right);
    if (rightHeight == -1) return -1;  // Right subtree is not balanced

    // If the height difference is more than 1, the tree is not balanced
    if (Math.abs(leftHeight - rightHeight) > 1) {
        return -1;
    }

    // Return the height of the current subtree
    return 1 + Math.max(leftHeight, rightHeight);
}

/*
 * Two binary trees are isomorphic if only their key values differ (they have
the same shape). Implement a linear-time static method isomorphic() that takes
two tree references as arguments and returns true if they refer to isomorphic trees,
and false otherwise. Then, implement a linear-time static method eq() that takes
two tree references as arguments and returns true if they refer to identical trees
(isomorphic with the same key values), and false otherwise.
 */
    // Isomorphic method
    public static boolean isomorphic(BinarySearchTree tree1, BinarySearchTree tree2) {
        return isomorphicHelper(tree1.root, tree2.root);
    }

    private static boolean isomorphicHelper(Node node1, Node node2) {
        // If both nodes are null, trees are isomorphic up to this point
        if (node1 == null && node2 == null)
            return true;

        // If one node is null and the other isn't, trees are not isomorphic
        if (node1 == null || node2 == null)
            return false;

        // Recursively check if left and right subtrees are isomorphic
        return isomorphicHelper(node1.left, node2.left) && isomorphicHelper(node1.right, node2.right);
    }

    // Equality method
    public static boolean eq(BinarySearchTree tree1, BinarySearchTree tree2) {
        return eqHelper(tree1.root, tree2.root);
    }

    private static boolean eqHelper(Node node1, Node node2) {
        // If both nodes are null, trees are equal up to this point
        if (node1 == null && node2 == null)
            return true;

        // If one node is null and the other isn't, trees are not equal
        if (node1 == null || node2 == null)
            return false;

        // Check if the current nodes have the same value and if their subtrees are equal
        return node1.value == node2.value &&
               eqHelper(node1.left, node2.left) &&
               eqHelper(node1.right, node2.right);
    }
/*
 * Implement a linear-time method isBST() that returns true if the tree is a
BST, and false otherwise.
 */
    public boolean isBST() {
        return isBST(root, null, null);
    }

    // Private helper method for isBST
    private boolean isBST(Node x, Double lo, Double hi) {
        if (x == null) return true;
        if (lo != null && x.value <= lo) return false;
        if (hi != null && x.value >= hi) return false;
        return isBST(x.left, lo, x.value) && isBST(x.right, x.value, hi);
    }
/*
 * Write a method levelOrder() that prints BST keys in level order : ﬁrst
print the root; then the nodes one level below the root, left to right; then the nodes
two levels below the root (left to right); and so forth. Hint : Use a Queue<Node>.
 */
    // Level order traversal method
    public void levelOrder() {
        if (root == null) {
            StdOut.println("The tree is empty.");
            return;
        }

        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                Node current = queue.dequeue();
                StdOut.print(current.value + " ");

                if (current.left != null) {
                    queue.enqueue(current.left);
                }
                if (current.right != null) {
                    queue.enqueue(current.right);
                }
            }
            StdOut.println(); // Move to the next line after printing each level
        }
    }


    // Helper method to insert a new value into the BST
    public void insert(double value) {
        root = insert(root, value);
    }

    private Node insert(Node node, double value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        }

        return node;
    }
}