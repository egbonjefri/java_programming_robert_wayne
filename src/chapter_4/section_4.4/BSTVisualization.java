
/*
 * Draw the BST that results when you insert the keys
E A S Y Q U E S T I O N
in that order into an initially empty tree. What is the height of the resulting BST?
 */

import lib.BST;
import lib.StdOut;


public class BSTVisualization {
    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<>();
        String[] keys = {"E", "A", "S", "Y", "Q", "U", "E", "S", "T", "I", "O", "N"};
        for (int i = 0; i < keys.length; i++) {
            bst.put(keys[i], i);
        }
        bst.draw();
        StdOut.println("Height of BST: " + bst.height());
    }
}
