package lib;
/*
 * Develop an implementation BinarySearchST of the symbol-table API that
maintains parallel arrays of keys and values, keeping them in key-sorted order. Use
binary search for get, and move larger key–value pairs to the right one position for
put (use a resizing array to keep the array length proportional to the number of key–
value pairs in the table). Test your implementation with Index, and validate the
hypothesis that using such an implementation for Index takes time proportional to
the product of the number of strings and the number of distinct strings in the input.
 */

import java.util.Arrays;

    public class BinarySearchST<Key extends Comparable<Key>, Value> {
        private static final int INIT_CAPACITY = 8;
        private Key[] keys;
        private Value[] values;
        private int n = 0;

        @SuppressWarnings("unchecked")
        public BinarySearchST() {
            keys = (Key[]) new Comparable[INIT_CAPACITY];
            values = (Value[]) new Object[INIT_CAPACITY];
        }
    
        private void resize(int capacity) {
            keys = Arrays.copyOf(keys, capacity);
            values = Arrays.copyOf(values, capacity);
        }
    
        public int size() {
            return n;
        }
    
        public boolean isEmpty() {
            return size() == 0;
        }
    
        public Value get(Key key) {
            if (isEmpty()) return null;
            int i = rank(key);
            if (i < n && keys[i].compareTo(key) == 0) return values[i];
            return null;
        }
    
        public int rank(Key key) {
            int lo = 0, hi = n - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                int cmp = key.compareTo(keys[mid]);
                if (cmp < 0) hi = mid - 1;
                else if (cmp > 0) lo = mid + 1;
                else return mid;
            }
            return lo;
        }
    
        public void put(Key key, Value value) {
            if (value == null) {
                delete(key);
                return;
            }
    
            int i = rank(key);
    
            if (i < n && keys[i].compareTo(key) == 0) {
                values[i] = value;
                return;
            }
    
            if (n == keys.length) resize(2 * keys.length);
    
            for (int j = n; j > i; j--) {
                keys[j] = keys[j - 1];
                values[j] = values[j - 1];
            }
    
            keys[i] = key;
            values[i] = value;
            n++;
        }
    
        public void delete(Key key) {
            if (isEmpty()) return;
    
            int i = rank(key);
    
            if (i == n || keys[i].compareTo(key) != 0) return;
    
            for (int j = i; j < n - 1; j++) {
                keys[j] = keys[j + 1];
                values[j] = values[j + 1];
            }
    
            n--;
            keys[n] = null;
            values[n] = null;
    
            if (n > 0 && n == keys.length / 4) resize(keys.length / 2);
        }
        public boolean contains(Key key) {
            if (key == null) throw new IllegalArgumentException("Argument to contains() is null");
            return get(key) != null;
        }
        public Iterable<Key> keys() {
            return Arrays.asList(Arrays.copyOf(keys, n));
        }
       
    }
