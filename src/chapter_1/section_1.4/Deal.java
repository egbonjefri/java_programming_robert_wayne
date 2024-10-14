import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lib.StdOut;

/*
 * java program that takes an integer command-line argument n and
prints n poker hands (ﬁve cards each) from a shufﬂed deck, separated by blank lines.

 */
public class Deal {
    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("Usage: java Deal <number_of_hands>");
            return;
        }

        try {
            int numHands = Integer.parseInt(args[0]);
            List<String> deck = createDeck();
            Collections.shuffle(deck);
            dealHands(deck, numHands);
        } catch (NumberFormatException e) {
            StdOut.println("Invalid argument: number of hands must be an integer.");
        }
    }
/*
 * This method creates a deck of cards. It generates all possible combinations
 * of ranks and suits and stores them in a list.
 */
    private static List<String> createDeck() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"♠", "♥", "♦", "♣"};
        List<String> deck = new ArrayList<>();
        for (String rank : ranks) {
            for (String suit : suits) {
                deck.add(rank + suit);
            }
        }
        return deck;
    }
/*
 * This method deals the hands. It iterates numHands times and for each hand,
 *  it deals five cards by removing and printing the top card from the deck list.
 */
    private static void dealHands(List<String> deck, int numHands) {
        for (int i = 0; i < numHands; i++) {
            for (int j = 0; j < 5; j++) {
                StdOut.print(deck.remove(0) + " "); // Deal and remove a card
            }
            StdOut.println("\n"); // Blank line for separation
        }
    }
}
