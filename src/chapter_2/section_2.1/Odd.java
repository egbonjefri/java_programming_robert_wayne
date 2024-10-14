
/*
 * Write a static method odd() that takes three boolean arguments and returns
true if an odd number of the argument values are true, and false otherwise.
 */

public class Odd {
    public static boolean odd(boolean first, boolean second, boolean third){
        int count = 0;
        if (first) count++;
        if(second) count++;
        if(third) count++;

        if(count % 2 == 0) return false;
        return true;
    }

    public static void main(String[] args){
        if(args.length < 3){
            System.out.println("Usage Odd <boolean1> <boolean2> <boolean3>");
            return;
        }
        boolean value = odd(Boolean.parseBoolean(args[0]),Boolean.parseBoolean(args[1]),Boolean.parseBoolean(args[2]));
        if(value) {
            System.out.println("You have entered an odd number of true arguments");
        }
        else{
         System.out.println("You have entered an even number of true arguments");
    }
    }
 
}
