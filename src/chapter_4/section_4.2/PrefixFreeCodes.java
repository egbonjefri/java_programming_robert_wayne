/*
 * In data compression, a set of strings is prefix-free if no
string is a preﬁx of another. For example, the set of strings { 01, 10, 0010, 1111 }
is preﬁx-free, but the set of strings { 01, 10, 0010, 1010 } is not preﬁx-free because
10 is a preﬁx of 1010. Write a program that reads in a set of strings from standard
input and determines whether the set is preﬁx-free
 */

import lib.StdIn;
import java.util.Arrays;

public class PrefixFreeCodes {

    public static boolean isPrefixFree(String[] strings, int count) {
        // If there's only one string or less, it's always prefix-free
        if (count <= 1) {
            return true;
        }

        // Sort the array
        Arrays.sort(strings, 0, count);

        // Check if any string is a prefix of the next string
        for (int i = 0; i < count - 1; i++) {
            if (strings[i + 1].startsWith(strings[i])) {
                return false;
            }
        }

        return true;
    }
        public static void main(String[] args) {
          
            String[] strings = StdIn.readAllStrings();
            int count = 0;    
            boolean isPrefixFree = isPrefixFree(strings, count);
    
            System.out.println("The set of strings is " + (isPrefixFree ? "prefix-free." : "not prefix-free."));
    
        }
    

    } 

