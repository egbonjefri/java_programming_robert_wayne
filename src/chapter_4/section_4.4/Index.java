/*
 * This ST client indexes a text file by word position.
 *  Keys are words, and values are queues of posi-
tions where the word occurs in the file.
 */

import lib.Queue;
import lib.StdOut;
import lib.StdIn;
import lib.ST;


public class Index {
    public static void main(String[] args) {
        int minlen = Integer.parseInt(args[0]);//minimum length
        int minocc = Integer.parseInt(args[1]);//occurrence threshold
        ST<String, Queue<Integer>> wordIndex = new ST<String, Queue<Integer>>();

        for (int i = 0; !StdIn.isEmpty(); i++){
            String word = StdIn.readString();
            if (word.length() < minlen) continue;

            if (!wordIndex.contains(word)){
                wordIndex.put(word, new Queue<Integer>());
            }
            //Retrieves the Queue<Integer> associated with the word from the symbol table .
            Queue<Integer> queue = wordIndex.get(word); 
            queue.enqueue(i);
        }

        // Print words whose occurrence count exceeds threshold.
        for (String s : wordIndex) {
            Queue<Integer> queue = wordIndex.get(s);
            if (queue.size() >= minocc)
            StdOut.println(s + ": " + queue);
        }
    }
}
