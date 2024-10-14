import lib.StdOut;

public class Deck {
    public static void main(String args[]) {
    String[] SUITS = { "Clubs", "Diamonds", "Hearts", "Spades" };
    String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10","Jack", "Queen", "King", "Ace"};

    String[] deck = new String[RANKS.length * SUITS.length];
    for (int i = 0; i < RANKS.length; i++){
            for (int j = 0; j < SUITS.length; j++){
               deck[SUITS.length*i + j] = RANKS[i] + " of " + SUITS[j];   
            }
    }
      
    int n = deck.length;
    int pokerHand = 5;
    int x = Integer.parseInt(args[0]);
    for(int k = 0; k < x; k++){
        //shuffle the deck
        for (int i = 0; i < n; i++) {
            int r = i + (int) (Math.random() * (n-i));
            String temp = deck[i];
            deck[i] = deck[r];
            deck[r] = temp;
        } 
    for(int i = 0; i < pokerHand; i++){
        StdOut.println(deck[i]);
    }
    StdOut.println("");
    }
    }
}