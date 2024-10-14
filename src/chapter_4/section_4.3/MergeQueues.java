import lib.Queue;
import lib.StdOut;
/*
 * Given two queues with strings in ascending
order, move all of the strings to a third queue so that the third queue ends up with
the strings in ascending order.
 */
public class MergeQueues {

    public  void main(String[] args) {
        // Create two queues and add elements in ascending order
        Queue<String> queue1 = new Queue<>();
        queue1.enqueue("apple");
        queue1.enqueue("banana");
        queue1.enqueue("cherry");

        Queue<String> queue2 = new Queue<>();
        queue2.enqueue("apricot");
        queue2.enqueue("blueberry");
        queue2.enqueue("citrus");


        Queue<String> mergedQueue = new Queue<>();
        mergedQueue.mergeSortedQueues(queue1, queue2);


        // Print the merged queue
        StdOut.println("Merged Queue: " + mergedQueue);
    }

}
