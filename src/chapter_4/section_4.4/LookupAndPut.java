/*
 * Modify Lookup to make a program LookupAndPut that allows put opera-
tions to be speciﬁed on standard input. Use the convention that a plus sign indicates
that the next two strings typed are the key–value pair to be inserted.
 */

 import lib.ST;
 import lib.StdOut;
 import lib.In;

 public class LookupAndPut {
     public static void main(String[] args) {
         if (args.length != 1) {
             StdOut.println("Usage: java LookupAndPut <filename>");
             System.exit(1);
         }
 
         String filename = args[0];
         ST<String, String> dictionary = new ST<String, String>();
 
         In fileIn = new In(filename);
         if (!fileIn.exists()) {
             StdOut.println("File not found: " + filename);
             System.exit(1);
         }
 
         while (!fileIn.isEmpty()) {
             String str = fileIn.readString();
             if (str.equals("+")) {
                 if (!fileIn.isEmpty()) {
                     String key = fileIn.readString();
                     if (!fileIn.isEmpty()) {
                         String value = fileIn.readString();
                         dictionary.put(key, value);
                         StdOut.println("Added: " + key + " -> " + value);
                     } else {
                         StdOut.println("Error in file: Value missing for key: " + key);
                     }
                 } else {
                     StdOut.println("Error in file: Key and value missing after '+'");
                 }
             } else {
                 // Lookup operation
                 if (dictionary.contains(str)) {
                     StdOut.println(str + " -> " + dictionary.get(str));
                 } 
             }
         }
     }
 }