/*
 * Repeat the previous exercise but uses a circular linked list.
In a circular linked list, each node points to its successor, and the last node in the
list points to the ﬁrst node (instead of null, as in a standard null-terminated linked
list).
 */
public class QuoteCircularLinkedList {
    private Card last;  // Reference to the last node in the circular list
    private int size;

    private class Card {
        private String word;
        private Card next;

        public Card(String word) {
            this.word = word;
            this.next = null;
        }
    }

    public QuoteCircularLinkedList() {
        last = null;
        size = 0;
    }

    public void add(String word) {
        Card newCard = new Card(word);
        if (last == null) {
            newCard.next = newCard;  // Point to itself in a single-element list
            last = newCard;
        } else {
            newCard.next = last.next;  // New card points to the first element
            last.next = newCard;  // Last element now points to the new card
            last = newCard;  // Update last to be the new card
        }
        size++;
    }

    public void add(int i, String word) {
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }

        Card newCard = new Card(word);

        if (size == 0) {
            newCard.next = newCard;
            last = newCard;
        } else if (i == size) {
            // Adding at the end is the same as add(String word)
            newCard.next = last.next;
            last.next = newCard;
            last = newCard;
        } else {
            Card current = last.next;  // Start from the first element
            for (int j = 0; j < i - 1; j++) {
                current = current.next;
            }
            newCard.next = current.next;
            current.next = newCard;
            if (i == 0) {
                last.next = newCard;  // Update last's next if inserting at the beginning
            }
        }
        size++;
    }

    public String get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }

        Card current = last.next;  // Start from the first element
        for (int j = 0; j < i; j++) {
            current = current.next;
        }
        return current.word;
    }

    public int count() {
        return size;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Card current = last.next;  // Start from the first element
        for (int i = 0; i < size; i++) {
            sb.append(current.word);
            if (i < size - 1) {
                sb.append(" ");
            }
            current = current.next;
        }
        return sb.toString();
    }
}

