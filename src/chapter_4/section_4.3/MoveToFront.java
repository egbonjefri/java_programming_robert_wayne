/*
 * Read in a sequence of characters from standard input and
maintain the characters in a linked list with no duplicates. When you read in a
previously unseen character, insert it at the front of the list. When you read in a
duplicate character, delete it from the list and reinsert it at the beginning. This im-
plements the well-known move-to-front strategy, which is useful for caching, data
compression, and many other applications where items that have been recently
accessed are more likely to be reaccessed.
 */
import lib.LinkedList;
import lib.StdIn;
import lib.StdOut;

public class MoveToFront {
    public static void main(String[] args) {
        LinkedList<Character> charList = new LinkedList<>();

        StdOut.println("Enter a sequence of characters (type 'exit' to stop):");
        
        while (!StdIn.isEmpty()) {
            String input = StdIn.readLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            for (char ch : input.toCharArray()) {
                updateList(charList, ch);
            }
            StdOut.println("Current list: " + charList);
        }
    }

    private static void updateList(LinkedList<Character> charList, char ch) {
        if (charList.contains(ch)) {
            charList.remove((Character) ch);
        }
        charList.add(ch);
    }
}
