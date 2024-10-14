import lib.Queue;
import lib.StdOut;
/* 
 * In the Josephus problem from antiquity, n people are
in dire straits and agree to the following strategy to reduce the population. They
arrange themselves in a circle (at positions numbered from 0 to n - 1) and proceed
around the circle, eliminating every mth person until only one person is left. Leg-
end has it that Josephus ﬁgured out where to sit to avoid being eliminated. Write
a Queue client Josephus that takes two integer command-line arguments m and
n and prints the order in which people are eliminated (and thus would show Jose-
phus where to sit in the circle).
% java Josephus 2 7
1 3 5 0 4 2 6
*/
public class Josephus {
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        
        Queue<Integer> circle = new Queue<>();
        
        // Initialize the circle with people numbered from 0 to n-1
        for (int i = 0; i < n; i++) {
            circle.enqueue(i);
        }
        
        // Eliminate people until only one remains
        while (circle.size() > 1) {
            // Move m-1 people to the back of the queue
            for (int i = 0; i < m - 1; i++) {
                circle.enqueue(circle.dequeue());
            }
            
            // Eliminate the mth person
            StdOut.print(circle.dequeue() + " ");
        }
        
        // Print the last person (survivor)
        StdOut.println(circle.dequeue());
    }
}