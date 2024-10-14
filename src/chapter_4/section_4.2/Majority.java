/*
 * A value in an array of length n is a majority if it appears strictly
more than n / 2 times. Given an array of strings, design a linear-time algorithm to
identify a majority element (if one exists).
 */
public class Majority {
    public static String findMajority(String[] arr) {
        int n = arr.length;
        
        // Step 1: Find a candidate for majority element
        String candidate = null;
        int count = 0;
        
        for (String str : arr) {
            if (count == 0) {
                candidate = str;
                count = 1;
            } else if (str.equals(candidate)) {
                count++;
            } else {
                count--;
            }
        }
        
        // Step 2: Verify if the candidate is actually the majority
        count = 0;
        for (String str : arr) {
            if (str.equals(candidate)) {
                count++;
            }
        }
        
        if (count > n / 2) {
            return candidate;
        } else {
            return null; // No majority element exists
        }
    }

    public static void main(String[] args) {
        String[] arr1 = {"banana", "banana", "apple", "apple", "apple", "apple"};
        String[] arr2 = {"cat", "dog", "bird", "cat", "dog"};
        
        System.out.println("Majority in arr1: " + findMajority(arr1)); // Should print "apple"
        System.out.println("Majority in arr2: " + findMajority(arr2)); // Should print null
    }  
}
