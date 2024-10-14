import java.util.HashMap;
import java.util.Map;
import lib.StdRandom;

/*
 * Write a StdRandom and StdStats client (with appropriate
static methods of its own) to estimate the probabilities of getting one pair, two pair,
three of a kind, a full house, and a ﬂush in a ﬁve-card poker hand via simulation.
Divide your program into appropriate static methods and defend your design deci-
sions. Extra credit : Add straight and straight ﬂush to the list of possibilities.
 */
public class PokerHandSimulation {

    // Represents a card deck
    private static String[] newDeck() {
        String[] SUITS = { "Clubs", "Diamonds", "Hearts", "Spades" };
        String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10","Jack", "Queen", "King", "Ace"};
    
        String[] deck = new String[RANKS.length * SUITS.length];
        for (int i = 0; i < RANKS.length; i++){
                for (int j = 0; j < SUITS.length; j++){
                   deck[SUITS.length*i + j] = RANKS[i] + " of " + SUITS[j];   
                }
        }
          
        return deck;
    }

// Drawing a hand of 5 cards
private static String[] drawHand(String[] deck) {
    String[] hand = new String[5];
    for (int i = 0; i < 5; i++) {
        hand[i] = deck[i];
    }
    return hand;
}
    // Check if the hand is a flush (all cards of the same suit)
    public static boolean isFlush(String[] hand) {
        String suit = getSuit(hand[0]);
        for (String card : hand) {
            if (!getSuit(card).equals(suit)) {
                return false;
            }
        }
        return true;
    }

    // Check if the hand has one pair
    public static boolean isOnePair(String[] hand) {
        Map<String, Integer> counts = rankCounts(hand);
        return counts.containsValue(2) && counts.size() == 4;
    }

    // Check if the hand has two pairs
    public static boolean isTwoPair(String[] hand) {
        Map<String, Integer> counts = rankCounts(hand);
        int pairCount = 0;
        for (int count : counts.values()) {
            if (count == 2) pairCount++;
        }
        return pairCount == 2;
    }

    // Check if the hand has three of a kind
    public static boolean isThreeOfAKind(String[] hand) {
        Map<String, Integer> counts = rankCounts(hand);
        return counts.containsValue(3) && counts.size() == 3;
    }

    // Check if the hand is a full house (three of a kind and a pair)
    public static boolean isFullHouse(String[] hand) {
        Map<String, Integer> counts = rankCounts(hand);
        return counts.containsValue(3) && counts.containsValue(2);
    }

    // Helper method to get the suit of a card
    private static String getSuit(String card) {
        return card.split(" of ")[1];
    }

    // Helper method to get the rank of a card
    private static String getRank(String card) {
        return card.split(" of ")[0];
    }

    // Helper method to count the occurrences of each rank in the hand
    private static Map<String, Integer> rankCounts(String[] hand) {
        Map<String, Integer> counts = new HashMap<>();
        for (String card : hand) {
            String rank = getRank(card);
            counts.put(rank, counts.getOrDefault(rank, 0) + 1);
        }
        return counts;
    }
    // Check if the hand is a flush, etc...
    // Implement methods like isFlush(), isOnePair(), isTwoPair(), isThreeOfAKind(), isFullHouse()

    // Simulate drawing a hand and check the outcome
    private static void simulateHand(int trials) {
        String[] deck = newDeck();
        int onePairCount = 0, twoPairCount = 0, threeOfAKindCount = 0, fullHouseCount = 0, flushCount = 0;

        for (int i = 0; i < trials; i++) {
            StdRandom.shuffle(deck);
            String[] hand = drawHand(deck);

            if (isOnePair(hand)) onePairCount++;
            if (isTwoPair(hand)) twoPairCount++;
            if (isThreeOfAKind(hand)) threeOfAKindCount++;
            if (isFullHouse(hand)) fullHouseCount++;
            if (isFlush(hand)) flushCount++;
        }

        // Print probabilities
        System.out.println("Probability of One Pair: " + ((double) onePairCount / trials));
        System.out.println("Probability of Two Pair: " + ((double) twoPairCount / trials));
        System.out.println("Probability of Three of a Kind: " + ((double) threeOfAKindCount / trials));
        System.out.println("Probability of a Full House: " + ((double) fullHouseCount / trials));
        System.out.println("Probability of a Flush: " + ((double) flushCount / trials));
    }

    public static void main(String[] args) {
        int trials = Integer.parseInt(args[0]);
        simulateHand(trials);
    }
}
