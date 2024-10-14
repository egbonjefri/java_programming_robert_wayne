/*
 * Write a ﬁlter KamasutraCipher that takes two strings
as command-line argument (the key strings), then reads strings (separated by
whitespace) from standard input, substitutes for each letter as speciﬁed by the key
strings, and prints the result to standard output. This operation is the basis for one
of the earliest known cryptographic systems. The condition on the key strings is
that they must be of equal length and that any letter in standard input must ap-
pear in exactly one of them. For example, if the two keys are THEQUICKBROWN and
FXJMPSVLAZYDG, then we make the table
T H E Q U I C K B R O W N
F X J M P S V L A Z Y D G
which tells us that we should substitute F for T, T for F, H for X, X for H, and so
forth when ﬁltering standard input to standard output. The message is encoded
by replacing each letter with its pair. For example, the message MEET AT ELEVEN is
encoded as QJJF BF JKJCJG. The person receiving the message can use the same
keys to get the message back.
 */
import lib.StdOut;
import java.util.HashSet;
import java.util.Set;

public class KarmaSutraCipher {
    public static boolean areDisjoint(String str1, String str2) {
    if (str1 == null || str2 == null || str1.length() != str2.length()) {
    return false;
    }

  // Create sets of characters from each string
    Set<Character> charSet1 = new HashSet<>();
    for (char c : str1.toCharArray()) {
    charSet1.add(c);
  }

  // Check if any character in str2 exists in charSet1
    for (char c : str2.toCharArray()) {
    if (charSet1.contains(c)) {
      return false;
    }
  }
  return true;
}

public static String mapCharacters(String input, String str1, String str2) {
    if (!areDisjoint(str1, str2)) {
      return null; 
    }
  
    char[] mappedChars = new char[input.length()];
    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);
      int charIndex = str1.indexOf(c);
      int secondIndex = str2.indexOf(c);
      if (charIndex != -1) {
        mappedChars[i] = str2.charAt(charIndex);
      }
      else if(secondIndex != -1){
        mappedChars[i] = str1.charAt(secondIndex);
      }
      
      else {
        mappedChars[i] = c; 
      }
    }
  
    return new String(mappedChars);
  }



  public static void main(String[] args) {
    String str1 = "THEQUICKBROWN";
    String str2 = "FXJMPSVLAZYDG";

    StdOut.println(mapCharacters("QJJFBFJKJCJG", str1, str2));
  }
}
