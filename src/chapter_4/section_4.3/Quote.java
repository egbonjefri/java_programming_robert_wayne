/*
 * Develop a data type Quote that implements the following API for
quotations:
public class Quote
            Quote()                 create an empty quote
void        add(String word)        append word to the end of the quote
void        add(int i, String word) insert word to be at index i
String      get(int i)              word at index i
int         count()                 number of words in the quote
String      toString()              the words in the quote

To do so, deﬁne a nested class Card that holds one word of the quotation and a link
to the next word in the quotation:
private class Card
{
private String word;
private Card next;
public Card(String word)
{
this.word = word;
this.next = null;
}
}
 */
public class Quote {
    private Card head;
    private Card tail;
    private int size;

    private class Card {
        private String word;
        private Card next;

        public Card(String word) {
            this.word = word;
            this.next = null;
        }
    }

    public Quote() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(String word) {
        Card newCard = new Card(word);
        if (head == null) {
            head = newCard;
            tail = newCard;
        } else {
            tail.next = newCard;
            tail = newCard;
        }
        size++;
    }

    public void add(int i, String word) {
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }

        Card newCard = new Card(word);

        if (i == 0) {
            newCard.next = head;
            head = newCard;
            if (tail == null) {
                tail = newCard;
            }
        } else {
            Card current = head;
            for (int j = 0; j < i - 1; j++) {
                current = current.next;
            }
            newCard.next = current.next;
            current.next = newCard;
            if (i == size) {
                tail = newCard;
            }
        }
        size++;
    }

    public String get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }

        Card current = head;
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
        StringBuilder sb = new StringBuilder();
        Card current = head;
        while (current != null) {
            sb.append(current.word);
            if (current.next != null) {
                sb.append(" ");
            }
            current = current.next;
        }
        return sb.toString();
    }
}