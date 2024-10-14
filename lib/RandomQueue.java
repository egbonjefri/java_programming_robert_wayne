package lib;
/*
 * A random queue is a collection that supports the following
API:
public class RandomQueue<Item>
            RandomQueue()       create an empty random queue
boolean     isEmpty()           is the random queue empty?
void        enqueue(Item item)  add item to the random queue
Item        dequeue()           remove and return a random item (sample without replacement)
Item        sample()            return a random item, but do not remove(sample with replacement)

Write a class RandomQueue that implements this API. Hint : Use a resizing array. To
remove an item, swap one at a random position (indexed 0 through n-1) with the
one at the last position (index n-1). Then, remove and return the last item, as in
ResizingArrayStack. Write a client that prints a deck of cards in random order
using RandomQueue<Card>.
 */
import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int n;

    // Create an empty random queue
    @SuppressWarnings("unchecked")
    public RandomQueue() {
        queue = (Item[]) new Object[1];
        n = 0;
    }

    // Is the random queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // Add item to the random queue
    public void enqueue(Item item) {
        if (n == queue.length) resize(2 * queue.length);
        queue[n++] = item;
    }

    // Remove and return a random item (sample without replacement)
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int randomIndex = StdRandom.uniformInt(n);
        Item item = queue[randomIndex];
        queue[randomIndex] = queue[n - 1];
        queue[n - 1] = null;
        n--;
        if (n > 0 && n == queue.length / 4) resize(queue.length / 2);
        return item;
    }

    // Return a random item, but do not remove (sample with replacement)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return queue[StdRandom.uniformInt(n)];
    }

    // Resize the underlying array
    private void resize(int capacity) {
        @SuppressWarnings("unchecked")
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }
  // Returns an iterator over items in random order
  public Iterator<Item> iterator() {
    return new RandomQueueIterator();
}

private class RandomQueueIterator implements Iterator<Item> {
    private Item[] shuffled;
    private int current;

    @SuppressWarnings("unchecked")
    public RandomQueueIterator() {
        shuffled = (Item[]) new Object[n];
        for (int i = 0; i < n; i++) {
            shuffled[i] = queue[i];
        }
        shuffle(shuffled);
        current = 0;
    }

    public boolean hasNext() {
        return current < n;
    }

    public Item next() {
        if (!hasNext()) throw new NoSuchElementException();
        return shuffled[current++];
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    // Fisher-Yates shuffle algorithm
    private void shuffle(Item[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = StdRandom.uniformInt(i + 1);
            Item temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}

    // Client to print a deck of cards in random order
    public static void main(String[] args) {
        RandomQueue<Card> deck = new RandomQueue<>();

        // Create and enqueue a standard deck of 52 cards
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.enqueue(new Card(rank, suit));
            }
        }

        // Print the deck in random order
        StdOut.println("Deck of cards in random order:");
        while (!deck.isEmpty()) {
            StdOut.println(deck.dequeue());
        }
    }
}

class Card {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}