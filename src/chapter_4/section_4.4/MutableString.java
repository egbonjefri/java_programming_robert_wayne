/*
 * Create a data type that supports the following API on a
string. Use an ST to implement all operations in logarithmic time.


        public class MutableString
                MutableString()         create an empty string
char            get(int i)              return the ith character in the string
void            insert(int i, char c)   insert c and make it the ith character
void            delete(int i)           delete the ith character
int             length()                return the length of the string
 */

import lib.ST;
public class MutableString {
        private ST<Integer, Character> st;
        private int length;
    
        public MutableString() {
            st = new ST<>();
            length = 0;
        }
    
        public char get(int i) {
            if (i < 0 || i >= length) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }
            return st.get(i);
        }
    
        public void insert(int i, char c) {
            if (i < 0 || i > length) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }
            for (int j = length; j > i; j--) {
                st.put(j, st.get(j - 1));
            }
            st.put(i, c);
            length++;
        }
    
        public void delete(int i) {
            if (i < 0 || i >= length) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }
            for (int j = i; j < length - 1; j++) {
                st.put(j, st.get(j + 1));
            }
            st.remove(length - 1);
            length--;
        }
    
        public int length() {
            return length;
        }
    
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append(st.get(i));
            }
            return sb.toString();
        }
    }

