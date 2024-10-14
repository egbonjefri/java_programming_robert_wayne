/*
 * Write a static method that reads ﬂoating-point numbers one at a time from
standard input and returns an array containing them, in the same order they appear
on standard input. Hint : Use either a stack or a queue.
 */

 import lib.Stack;
 import lib.StdIn;
 import lib.StdOut;


public class FloatPrinter {
        public static void main(String[] args){
            Stack<Double> stack = new Stack<Double>();

            while(!StdIn.isEmpty()){
                Double d = StdIn.readDouble();
                stack.push(d);
            }
            for (Double d : stack)
                StdOut.println(d);
        }
}
