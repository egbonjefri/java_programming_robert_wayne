package lib;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class Stack<Item> implements Iterable<Item> {   
    private Node<Item> first;
    private int size;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }

    public Stack() {
        first = null;
        size = 0;
    }
      /*
       * Copy constructor for a stack. Create a new constructor for the linked-list
implementation of Stack so that
Stack<Item> t = new Stack<Item>(s);
makes t a reference to a new and independent copy of the stack s. You should be
able to push and pop from either s or t without inﬂuencing the other.
       */
      public Stack(Stack<Item> s) {
        if (s.first != null) {
            // Use a temporary stack to reverse the order while copying
            Stack<Item> temp = new Stack<>();

            // Push elements of s into temp stack
            for (Item item : s) {
                temp.push(item);
            }

            // Push elements from temp back to this stack to maintain order
            for (Item item : temp) {
                this.push(item);
            }
        }
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void push(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        first = new Node<Item>(item, first);
        size++;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        Item item = first.item;
        first = first.next;
        size--;
        return item;
    }

    public Item peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return first.item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<Item> current = first;
        while (current != null) {
            sb.append(current.item).append(" ");
            current = current.next;
        }
        return sb.toString().trim();
    }

    public Iterator<Item> iterator() {
        return new ReverseStackIterator();
    }

    private class ReverseStackIterator implements Iterator<Item> {
        private Node<Item>[] nodes;
        private int currentIndex;

        @SuppressWarnings("unchecked")
        public ReverseStackIterator() {
            nodes = (Node<Item>[]) new Node[size];
            int i = 0;
            for (Node<Item> x = first; x != null; x = x.next) {
                nodes[i++] = x;
            }
            currentIndex = size - 1;  
        }

        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return nodes[currentIndex--].item;
        }

    
/* 
        public Stack<String> copy(Stack<String> original) {
            Stack<String> copy = new Stack<>();
            ArrayList<String> temp = new ArrayList<>();
        
            // Pop all items from the original stack and store them in a temp list
            while (!original.isEmpty()) {
                temp.add(original.pop());
            }
        
            // Reverse the temp list manually
            int n = temp.size();
            for (int i = 0; i < n / 2; i++) {
                String tempElement = temp.get(i);
                temp.set(i, temp.get(n - i - 1));
                temp.set(n - i - 1, tempElement);
            }
        
            // Push items back to the original stack and to the copy stack
            for (String item : temp) {
                original.push(item);
                copy.push(item);
            }
        
            return copy;
        }
            */
}
}

