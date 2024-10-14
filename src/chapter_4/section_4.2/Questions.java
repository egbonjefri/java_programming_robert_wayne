import lib.StdIn;
import lib.StdOut;


/*
 * This code uses binary search to play the same game as PROGRAM 1.5.2, but with the roles re-
versed: you choose the secret number and the program guesses its value. It takes an integer
command-line argument k, asks you to think of a number between 0 and n-1, where n = 2k,
and always guesses the answer with k questions.
 */

public class Questions {

  // Recursive binary search to find a number in the range [lo, hi)
  public static int binarySearch(int lo, int hi) {
      // Base case: only one number in the range
      if (hi - lo == 1) {
          return lo;
      }

      // Calculate the midpoint
      int mid = lo + (hi - lo) / 2;
      StdOut.print("Greater than or equal to " + mid + "? (yes / no) ");

      // Read the user's response
      if (StdIn.readBoolean()) {
          // If the number is greater than or equal to mid, search in the range [mid, hi)
          return binarySearch(mid, hi);
      } else {
          // If the number is less than mid, search in the range [lo, mid)
          return binarySearch(lo, mid);
      }
  }
/*
 * Develop a nonrecursive version of BinarySearch (PROGRAM 4.2.3).
 */
  public static void binarySearchIterative(int n) {
    
    
    StdOut.println("Think of a number less than " + n + " and I'll to guess it!");
    StdOut.print("Press Enter when you're ready.");
    StdIn.readLine();
    int lo = 1;
    int hi = n;
    int guesses = 0;

    while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        guesses++;

        StdOut.println("Is your number " + mid + "?");
        StdOut.println("Enter 'h' if your number is higher, 'l' if it's lower, or 'c' if it's correct:");
        String response = StdIn.readLine().trim().toLowerCase();

        if (response.equals("c")) {
            StdOut.println("Great! I guessed your number " + mid + " in " + guesses + " guesses.");
            return;
        } else if (response.equals("h")) {
            lo = mid + 1;
        } else if (response.equals("l")) {
            hi = mid - 1;
        } else {
            StdOut.println("Invalid input. Please enter 'h', 'l', or 'c'.");
            guesses--; // Don't count invalid inputs as guesses
        }
    }

    StdOut.println("I think you may have made a mistake in your responses.");
    StdOut.println("I've run out of numbers to guess between " + lo + " and " + hi + ".");
    
}


  public static void main(String[] args) {
    /* 
      // Number of questions (k) is taken from command line arguments
      int k = Integer.parseInt(args[0]);
      
      // Calculate the number of possible values (2^k)
      int n = (int) Math.pow(2, k);
      
      // Prompt the user to think of a number in the range [0, n-1]
      StdOut.print("Think of a number between 0 and " + (n - 1) + "\n");
      
      // Perform binary search to guess the number
      int guess = binarySearch(0, n);
      
      // Print the guessed number
      StdOut.println("Your number is " + guess);
      */
      int n = Integer.parseInt(args[0]);
      binarySearchIterative(n);
  }
}
