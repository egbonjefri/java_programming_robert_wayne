package lib;
public class ReverseString {

    /*
     * Write a static method reverse() that takes a string as an argument and re-
turns a string that contains the same sequence of characters as the argument string
but in reverse order.
     */
    public static String reverse(String s){

        char[] charArray = s.toCharArray();
        for(int i = 0; i < Math.floor(charArray.length/2); i++){
            char tmp = charArray[charArray.length-1-i];
            charArray[charArray.length-1-i] = charArray[i];
            charArray[i] = tmp;
        }
        return new String(charArray);
    }
    /**
     * Extracts the top-level domain (TLD) from a given domain name.
     * 
     * @param domainName The domain name from which to extract the TLD.
     * @return The TLD of the given domain name.
     */
    public static String getTopLevelDomain(String domainName) {
        // Find the index of the last period in the domain name.
        int lastDotIndex = domainName.lastIndexOf('.');
        
        // If there is no period in the domain name, return an empty string as there's no TLD.
        if (lastDotIndex == -1) {
            return "";
        }
        
        // Extract and return the substring after the last period, which is the TLD.
        return domainName.substring(lastDotIndex + 1);
    }
/*
 * takes a domain name as its argument and returns
 *  the reverse domain name (reverse the order of the strings between periods).
 */
    public static String reverseTopLevelDomain(String domainName) {
        String[] segments = domainName.split("\\.");
    // Initialize an empty string to hold the reversed domain name.
    String reversed = "";
    
    // Iterate through the segments array in reverse order to build the reversed string.
    for (int i = segments.length - 1; i >= 0; i--) {
        reversed += segments[i];
        // Add a period between the segments, but not at the end.
        if (i > 0) {
            reversed += ".";
        }
    }
    
    return reversed;
    }
    public static String mystery(String s) {
        int n = s.length();
        StdOut.println(s);
        if (n <= 1) return s;
        String a = s.substring(0, n/2);
        String b = s.substring(n/2, n);
    return mystery(b) + mystery(a);
}
    public static void main(String[] args){
        String s = "princeton";
        StdOut.println(mystery(s));
    }
}
