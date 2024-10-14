import lib.StdIn;
import lib.Queue;
import lib.StdOut;

/*Write a ﬁlter Reverse that reads strings one at a time from standard input
and prints them to standard output in reverse order. Use either a stack or a queue.
 * 
 */
public class Reverse {
    public static void main(String[] args){
        Queue<String> queue = new Queue<String>();

        while(!StdIn.isEmpty()){
            String s = StdIn.readString();
            queue.enqueue(s);
        }
        
        for (String s : queue)
            StdOut.println(s);
    }
}
