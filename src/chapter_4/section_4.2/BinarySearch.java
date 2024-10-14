import lib.In;
import lib.StdIn;
import lib.StdOut;
/*
 * Changes made:
 * - Adjusted the base case to return -lo when the search range is empty,
 *  indicating the smallest index i where a[i] is greater than the key.
 * When the search range is empty (lo >= hi), 
 * returning -lo indicates the position where the key would fit in the sorted array, if it were present. 
 * This allows the caller to determine both the absence of the key and the potential insertion point
 * 
 * 
 * - Added a loop to find the smallest index of the key if found, 
 * ensuring that we return the first occurrence of the key in the array.
 * 
 * 
 * - Modified the main method to print the found index or the smallest index greater 
 * than the key based on the search result.
 */
public class BinarySearch {
    /**
     * Searches for a key in a sorted array of strings.
     * @param key The string to search for
     * @param a The sorted array of strings
     * @return The index of the key if found, -i otherwise
     */
    public static int search(String key, String[] a) {
        return search(key, a, 0, a.length);
    }

    /**
     * Recursive binary search implementation.
     * @param key The string to search for
     * @param a The sorted array of strings
     * @param lo The lowest index of the search range
     * @param hi The highest index of the search range (exclusive)
     * @return The index of the key if found, -i otherwise
     */
    public static int search(String key, String[] a, int lo, int hi) {
        // Base case: if the search range is empty
        if (lo >= hi) {
            return -lo; // Not found, return -lo
        }

        // Calculate the middle index
        int mid = lo + (hi - lo) / 2;

        // Compare the key with the middle element
        int cmp = a[mid].compareTo(key);

        if (cmp > 0) {
            // If the key is smaller, search in the left half
            return search(key, a, lo, mid);
        } else if (cmp < 0) {
            // If the key is larger, search in the right half
            return search(key, a, mid + 1, hi);
        } else {
            // Key found, now search for the smallest index
            while (mid > lo && a[mid - 1].compareTo(key) == 0) {
                mid--;
            }
            return mid;
        }
    }

    public static void main(String[] args) {
        // Ensure that a filename is provided
        if (args.length == 0) {
            StdOut.println("Please provide a filename as an argument.");
            return;
        }

        // Read all strings from the file
        In in = new In(args[0]);
        String[] a = in.readAllStrings();

        // Read keys from standard input and print those that are not in the file
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            int result = search(key, a);
            if (result >= 0) {
                StdOut.println("Found at index: " + result);
            } else {
                StdOut.println("Not found, smallest index greater than key: " + (-result));
            }
        }
    }
}
