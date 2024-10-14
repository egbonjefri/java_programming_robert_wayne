/*
 * Given an array of integers, ﬁnd the length and location
of the longest contiguous sequence of equal values for which the values of the ele-
ments just before and just after this sequence are smaller.
 */
public class LongestPlateau {
    public static int findLongestPlateau(int[] a) {
        int longestLength = 0;
        int longestStart = 0;
        
        for (int i = 1; i < a.length - 1; i++) {
            if (a[i] > a[i-1] && a[i] > a[i+1]) {
                int currentLength = 1;
                for (int j = i + 1; j < a.length && a[j] == a[i]; j++) {
                    currentLength++;
                }
                
                for (int k = i - 1; k >= 0 && a[k] == a[i]; k--) {
                    currentLength++;
                }
                
                if (currentLength > longestLength) {
                    longestLength = currentLength;
                    longestStart = i - currentLength + 1;
                }
            }
        }
        
        return longestStart;
    }
}
