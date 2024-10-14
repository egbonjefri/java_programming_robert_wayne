/*
 * Write a static method majority() that takes three boolean arguments and
returns true if at least two of the argument values are true, and false otherwise.
Do not use an if statement.
 */
public class Majority {
            public static void main(String[] args){
            if (args.length == 3) {
                boolean a = Boolean.parseBoolean(args[0]);
                boolean b = Boolean.parseBoolean(args[1]);
                boolean c = Boolean.parseBoolean(args[2]);
    
                System.out.println(majority(a, b, c));
            } else {
                System.out.println("Please provide exactly three boolean arguments (true or false).");
                return;
            }
        }
            public static boolean majority(boolean a, boolean b, boolean c) {
            // Return true if at least two arguments are true
            return (a && b) || (a && c) || (b && c);
        }

}
