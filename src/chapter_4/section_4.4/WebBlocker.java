import lib.StdOut;
import lib.StdIn;
import lib.SET;
import lib.In;

public class WebBlocker {
    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("Usage: java WebBlocker <blocklist_file>");
            return;
        }

        SET<String> blockedSites = new SET<>();

        // Read the list of objectionable websites
        try {
            In blocklist = new In(args[0]);
            while (!blocklist.isEmpty()) {
                String site = blocklist.readString().toLowerCase();
                blockedSites.add(site);
            }
            StdOut.println("Blocklist loaded successfully.");
        } catch (Exception e) {
            StdOut.println("Error reading blocklist file: " + e.getMessage());
            return;
        }

        // Read websites from standard input
        StdOut.println("Enter websites (one per line, type 'exit' to quit):");
        while (!StdIn.isEmpty()) {
            String input = StdIn.readLine().trim().toLowerCase();
            
            if (input.equals("exit")) {
                break;
            }

            if (!blockedSites.contains(input)) {
                StdOut.println("Allowed: " + input);
            } else {
                StdOut.println("Blocked: " + input);
            }
        }

        StdOut.println("WebBlocker session ended.");
    }
}