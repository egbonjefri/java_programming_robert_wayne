package lib;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class ArrayList <E> implements Iterable<E>  {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    // Constructor to initialize the list with default capacity
    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }
    // Copy constructor to initialize the list with elements from another ArrayList
    public ArrayList(ArrayList<E> other) {
        this.elements = new Object[other.size];
        this.size = other.size;
        System.arraycopy(other.elements, 0, this.elements, 0, size);
    }
   // Constructor to initialize the list with a specified capacity
   public ArrayList(int initialCapacity) {
    if (initialCapacity > 0) {
        elements = new Object[initialCapacity];
    } else if (initialCapacity == 0) {
        elements = new Object[]{};
    } else {
        throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
    }
}
    // Method to add an element to the list
    public void add(E element) {
        // Ensure there is enough capacity to add a new element
        ensureCapacity();
        elements[size++] = element;
    }
    // Clears the list
    public void clear() {
        // Optionally, clear the array (this step is not required for functionality but for better memory management)
        for (int i = 0; i < size; i++) {
            elements[i] = null;  // Help GC (Garbage Collector) by removing references to objects
        }
        size = 0;  // Reset the size counter to 0
    }

    // Method to get an element at a specific index
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }
    // Remove element at specified index
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        @SuppressWarnings("unchecked")
        E oldValue = (E) elements[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // Clear to let GC do its work

        return oldValue;
    }


    // Contains method
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == null) {
                if (elements[i] == null) {
                    return true;
                }
            } else if (o.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    // Method to replace an element at a specific index
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        // Check for index validity
        checkIndex(index);

        // Store the old value
        E oldValue = (E) elements[index];

        // Replace with the new value
        elements[index] = element;

        // Return the old value
        return oldValue;
    }
    // Implement the of method
    @SafeVarargs
    public static <E> ArrayList<E> of(E... elements) {
        ArrayList<E> list = new ArrayList<>();
        for (E element : elements) {
            list.add(element);
        }
        return list;
    }

    // Method to get the current size of the list
    public int size() {
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    // Ensure the list has enough capacity to add a new element
    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            Object[] newElements = new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    // Method to check if an index is within bounds
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // Method to return a Stream of the elements
    @SuppressWarnings("unchecked")
    public Stream<E> stream() {
        return Arrays.stream(elements, 0, size).map(e -> (E) e);
    }
   public ReverseArrayIterator iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<E>{
        private int index;
       
        @Override
        public boolean hasNext() {
            return index >= 0;
        }
    
        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the array");
            }
            return (E) elements[index--];
        }
    
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported");
        }
}
}
