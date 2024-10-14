import lib.Queue;
import lib.StdIn;
import lib.StdOut;
/*
 * Write a java Queue client that takes an integer command-line argument k and
prints the kth from the last string found on standard input.
 */

/*
 * This program efficiently solves the problem using O(k) space complexity,
 *  as it only needs to store the last k strings in the queue at any given time.
 */
public class KthFromLastQueue {
    public static void main(String[] args) {
        // Check if command-line argument is provided
        if (args.length != 1) {
            StdOut.println("Usage: java KthFromLastQueue <k>");
            return;
        }

        // Parse the command-line argument
        int k;
        try {
            k = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            StdOut.println("Error: k must be an integer");
            return;
        }

        // Check if k is positive
        if (k <= 0) {
            StdOut.println("Error: k must be a positive integer");
            return;
        }

        // Create a queue to store the strings
        Queue<String> queue = new Queue<>();

        // Read strings from standard input
        
        while (!StdIn.isEmpty()) {
            String input = StdIn.readLine();
            queue.enqueue(input);
            // If queue size exceeds k, remove the oldest element
            if (queue.size() > k) {
                queue.dequeue();
            }
        }
      
        // Check if we have enough strings
        if (queue.size() < k) {
            StdOut.println("Error: Not enough strings in input (need at least " + k + ")");
            return;
        }

        // The kth from last string is now at the head of the queue
        StdOut.println("The " + k + "th string from the last queue is: " + queue.peek());
    }
}