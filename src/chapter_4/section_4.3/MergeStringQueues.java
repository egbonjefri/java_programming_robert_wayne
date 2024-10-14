/*
 * Given n strings, create n queues, each containing
one of the strings. Create a queue of the n queues. Then, repeatedly apply the sorted
merging operation from the previous exercise to the ﬁrst two queues and enqueue
the merged queue. Repeat until the queue of queues contains only one queue
 */

import lib.Queue;
import lib.StdOut;

public class MergeStringQueues {

    public static void main(String[] args) {
        // Initialize an array of strings
        String[] strings = {"apple", "banana", "cherry", "apricot", "blueberry", "citrus"};

        // Create a queue of queues
        Queue<Queue<String>> queueOfQueues = new Queue<>();

        // Create a queue for each string and add it to the queue of queues
        for (String str : strings) {
            Queue<String> singleQueue = new Queue<>();
            singleQueue.enqueue(str);
            queueOfQueues.enqueue(singleQueue);
        }

        // Merge queues until only one queue remains
        while (queueOfQueues.size() > 1) {
            // Remove the first two queues from the queue of queues
            Queue<String> queue1 = queueOfQueues.dequeue();
            Queue<String> queue2 = queueOfQueues.dequeue();

            // Merge them using the mergeSortedQueues function
            Queue<String> mergedQueue = new Queue<>();
            mergedQueue.mergeSortedQueues(queue1, queue2);

            // Enqueue the merged queue back into the queue of queues
            queueOfQueues.enqueue(mergedQueue);
        }

        // The remaining queue in queueOfQueues is the fully merged queue
        Queue<String> finalMergedQueue = queueOfQueues.dequeue();

        // Print the final merged queue
        StdOut.println("Final Merged Queue: " + finalMergedQueue);
    }

  
}
