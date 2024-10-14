/*
 * Given a list of web pages, create a symbol table of
words contained in those web pages. Associate with each word a list of web pages
in which that word appears. Write a program that reads in a list of web pages, cre-
ates the symbol table, and supports single-word queries by returning the list of web
pages in which that query word appears.
 */

import lib.SET;
import lib.ST;
import lib.ArrayList;
import lib.In;
import lib.StdOut;

public class InvertedIndexOfWeb {
    private ST<String, SET<String>> wordIndex;

    public InvertedIndexOfWeb() {
        wordIndex = new ST<>();
    }

    public void indexWebPages(ArrayList<String> webPages) {
        for (String webPage : webPages) {
            indexWebPage(webPage);
        }
    }


    private void indexWebPage(String webPage) {
        In in = new In(webPage);
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] words = line.toLowerCase().split("\\W+");
            for (String word : words) {
                if (!word.isEmpty()) {
                    SET<String> set = wordIndex.get(word);
                    if (set == null) {
                        set = new SET<>();
                        wordIndex.put(word, set);
                    }
                    set.add(webPage);
                }
            }
        }
    }
    public void query(String word) {
        SET<String> webpages = wordIndex.get(word);
        if(webpages != null){
            for(String s : webpages){
                StdOut.println("Webpages containing " + word + " " + s);
            }
        } 
    }
    // Multi-word query: return pages that contain all words
    public void query(String... words) {
        if (words.length == 0) return;

        // Start with the set of pages containing the first word
        SET<String> result = wordIndex.get(words[0]);
        if (result == null) {
            StdOut.println("No pages contain the word: " + words[0]);
            return;
        }

        // Intersect sets of pages containing other query words
        for (int i = 1; i < words.length; i++) {
            SET<String> nextSet = wordIndex.get(words[i]);
            if (nextSet == null) {
                StdOut.println("No pages contain the word: " + words[i]);
                return;
            }
            result = result.intersects(nextSet);  
        }

        // Display results
        if (result.isEmpty()) {
            StdOut.println("No pages contain all the query words.");
        } else {
            StdOut.println("Webpages containing all words: ");
            for (String page : result) {
                StdOut.println(page);
            }
        }
    }

    public static void main(String[] args) {
        InvertedIndexOfWeb indexer = new InvertedIndexOfWeb();
        
        // Example usage
        ArrayList<String> webPages = ArrayList.of("page1.html", "page2.html", "page3.html");
        indexer.indexWebPages(webPages);

        String queryWord = "example";
        indexer.query(queryWord);
    }
}