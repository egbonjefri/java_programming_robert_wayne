
/*
 * Develop a data type FrequencyTable that sup-
ports the following operations: click() and count(), both of which take string
arguments. The data type keeps track of the number of times the click() opera-
tion has been called with a given string as an argument. The click() operation
increments the count by 1, and the count() operation returns the count, possibly
0. Clients of this data type might include a web-trafﬁc analyzer, a music player that
counts the number of times each song has been played, phone software for count-
ing calls, and so forth.
 */

import lib.ST;
import lib.StdOut;


public class FrequencyTable {
        private ST<String, Integer> frequencyMap;

        public FrequencyTable() {
            frequencyMap = new ST<>();
        }
        public void click(String key) {
            if (!frequencyMap.contains(key)) {
                frequencyMap.put(key, 1);
            } else {
                frequencyMap.put(key, frequencyMap.get(key) + 1);
            }
        }
        
        public int count(String key) {
            if (!frequencyMap.contains(key)) {
                return 0;
            }
            return frequencyMap.get(key);
        }
        
    
    public static void main(String[] args) {
        FrequencyTable table = new FrequencyTable();
        table.click("apple");
        table.click("banana");
        table.click("apple");
        StdOut.println("Count of 'apple': " + table.count("apple")); // 2
        StdOut.println("Count of 'banana': " + table.count("banana")); // 1
        StdOut.println("Count of 'orange': " + table.count("orange")); // 0
    }
}