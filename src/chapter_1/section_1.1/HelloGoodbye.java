import lib.StdOut;


public class HelloGoodbye {
    public static void main(String[] args) {
        // Check if exactly two command-line arguments are provided
        if (args.length != 2) {
            System.err.println("Usage: java HelloGoodbye <name1> <name2>");
            System.exit(1); // Exit with an error code
        }

        String name1 = args[0];
        String name2 = args[1];

        // Print hello message with names in the same order
        StdOut.println("Hello, " + name1 + " and " + name2 + "!");

        // Print goodbye message with names in reverse order
        StdOut.println("Goodbye, " + name2 + " and " + name1 + "!");
    }
}