/*
 * Write a program WordLadder that takes two 5-letter strings
as command-line arguments, reads in a list of 5-letter words from standard input,
and prints a shortest word ladder using the words on standard input connecting the
two strings (if it exists). Two words are adjacent in a word ladder chain if they differ
in exactly one letter. As an example, the following word ladder connects green and
brown:
green greet great groat groan grown brown
Write a ﬁlter to get the 5-letter words from a system dictionary for standard input
or download a list from the booksite.
 */

import lib.ArrayList;
import lib.Queue;
import lib.SET;
import lib.StdIn;
import lib.StdOut;

 public class WordLadder {
 
     // Function to check if two words differ by exactly one letter
     private static boolean isAdjacent(String word1, String word2) {
         int differences = 0;
         for (int i = 0; i < word1.length(); i++) {
             if (word1.charAt(i) != word2.charAt(i)) {
                 differences++;
             }
             if (differences > 1) {
                 return false;
             }
         }
         return differences == 1;
     }
 
     // BFS to find the shortest word ladder
     private static ArrayList<String> findShortestLadder(String start, String end, SET<String> dictionary) {
         Queue<ArrayList<String>> queue = new Queue<>();
         SET<String> visited = new SET<>();
         
         // Start the ladder with the first word
         queue.enqueue(ArrayList.of(start));
         visited.add(start);
         while (!queue.isEmpty()) {
             ArrayList<String> path = queue.dequeue();
             String lastWord = path.get(path.size() - 1);
 
             // If we reached the end word, return the path
             if (lastWord.equals(end)) {
                 return path;
             }
 
             // Explore all adjacent words
             for (String word : dictionary) {
                 if (!visited.contains(word) && isAdjacent(lastWord, word)) {
                     ArrayList<String> newPath = new ArrayList<>(path);
                     newPath.add(word);
                     queue.enqueue(newPath);
                     visited.add(word);
                 }
             }
         }
 
         return null; // No ladder found
     }
 
     public static void main(String[] args) {
         if (args.length != 2) {
            StdOut.println("Usage: java WordLadder <start-word> <end-word>");
             return;
         }
 
         String startWord = args[0];
         String endWord = args[1];
 
         // Reading the list of 5-letter words from standard input
         SET<String> dictionary = new SET<>();
 
         while (!StdIn.isEmpty()) {
             String word = StdIn.readLine();
             if (word.length() == 5) {
                 dictionary.add(word.toLowerCase());
             }
         }
 
         // Check if the start and end words are valid
         if (!dictionary.contains(startWord) || !dictionary.contains(endWord)) {
            StdOut.println("Start or end word is not in the dictionary");
             return;
         }
 
         // Find the shortest word ladder
         ArrayList<String> ladder = findShortestLadder(startWord, endWord, dictionary);
 
         // Output the result
         if (ladder != null) {
             for (String word : ladder) {
                StdOut.print(word + " ");
             }
             StdOut.println();
         } else {
            StdOut.println("No word ladder found");
         }
     }
 }
 