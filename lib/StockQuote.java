package lib;
/******************************************************************************
 *  Compilation:  javac StockQuote.java
 *  Execution:    java StockQuote symbol
 *  Dependencies: In.java, StdOut.java
 *
 *  Print the stock price of the stock with the given symbol.
 *  Screen scrapes http://finance.yahoo.com to get the current price
 *  and associated information.
 *
 *  Warning: if Yahoo updates the format of their page,
 *           this program also needs to be updated.
 *
 *  % java StockQuote GOOG
 *  744.0
 *  Alphabet Inc.
 *  Wed Jul 27 12:19:16 PDT 2016
 *
 *  % java StockQuote AAPL
 *  103.78
 *  Apple Inc.
 *  Wed Jul 27 12:19:21 PDT 2016
 *
 *  % java StockQuote IBM
 *  162.29
 *  International Business Machines Corporation
 *  Wed Jul 27 12:19:26 PDT 2016
 *
 *  % java StockQuote MSFT
 *  56.46
 *  Microsoft Corporation
 *  Wed Jul 27 12:19:30 PDT 2016
 *
 ******************************************************************************/

public class StockQuote {

    // Given symbol, get HTML
    private static String readHTML(String symbol) {
        In page = new In("https://stockanalysis.com/stocks/" + symbol + "/");
        String html = page.readAll();
        return html;
    }
/* 
    // Given symbol, get current stock price.
    public static double priceOf(String symbol) {
        String html = readHTML(symbol);
        int p     = html.indexOf("price.0", 0);      // "price.0" index
        int from  = html.indexOf(">", p);            // ">" index
        int to    = html.indexOf("</span>", from);   // "</span>" index
        String price = html.substring(from + 1, to);

        // remove any comma separators
        return Double.parseDouble(price.replaceAll(",", ""));
    }
*/

// 
    public static double priceOf(String symbol){
        String html = readHTML(symbol);
        int startIndex = html.indexOf("pd:") + 3;
        int endIndex = html.indexOf(",", startIndex); 
        String pdValue = html.substring(startIndex, endIndex);
        return Double.parseDouble(pdValue);
    }
    // Given symbol, get current stock price.
    public static double priceOf(String symbol, String html) {
        int p     = html.indexOf("price.0", 0);      // "price.0" index
        int from  = html.indexOf(">", p);            // ">" index
        int to    = html.indexOf("</span>", from);   // "</span>" index
        String price = html.substring(from + 1, to);

        // remove any comma separators
        return Double.parseDouble(price.replaceAll(",", ""));
    }

    // Given symbol, get current stock name.
    public static String nameOf(String symbol, String html) {
        int p    = html.indexOf("symbol.$companyName", 0);
        int from = html.indexOf(">", p);
        int to   = html.indexOf("</h6>", from);
        String name = html.substring(from + 1, to);
        return name;
    }

    // Given symbol, get current date.
    public static String dateOf(String symbol, String html) {
        int p    = html.indexOf("adx.bf1.yahoo.com", 0);
        int from = html.indexOf(" ", p);
        int to   = html.indexOf("-->", from);
        String date = html.substring(from + 1, to);
        return date;
    }

    public static void main(String[] args) {
        
        if (args.length == 0) {
            StdOut.println("Usage: java StockQuote <symbol1> <symbol2> ... <symbolN>");
            return;
        }

        for (String symbol : args) {
            String html = readHTML(symbol);
            StdOut.println("Processing symbol: " + symbol);
            
            // Simulate fetching data for each symbol
            double price = priceOf(symbol, html);
            String name = nameOf(symbol, html);
            String date = dateOf(symbol, html);
            
            // Output the fetched data
            StdOut.printf("Stock Name: %s, Price: %.2f, Date: %s\n", name, price, date);
        }
    
        
    }
}