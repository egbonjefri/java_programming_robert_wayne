/*
 * Write a ﬁlter DeDup that reads strings from standard input and prints them
to standard output, with all duplicate strings removed (and in sorted order).
 */

 import lib.StdIn;
 import lib.Merge;
 import lib.StdOut;
 
 public class DeDup {
     // Method to rearrange the array so that all unique elements are at the beginning.
     public static int removeDuplicates(String[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        
        int uniqueIndex = 0;
        
        for (int i = 1; i < arr.length; i++) {
            if (!arr[i].equals(arr[uniqueIndex])) {
                uniqueIndex++;
                arr[uniqueIndex] = arr[i];
            }
        }
        
        return uniqueIndex + 1;
    }

 
     public static void main(String[] args) {
         String[] lines = StdIn.readAllStrings();
         
         // Sort the array
         Merge.sort(lines);
         
         // Remove duplicates
         int uniqueCount = removeDuplicates(lines);
         
         // Print unique strings
         for (int i = 0; i < uniqueCount; i++) {
             StdOut.println(lines[i]);
         }
     }
 }