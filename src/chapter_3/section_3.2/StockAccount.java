/******************************************************************************
 *  Compilation:  javac StockAccount.java
 *  Execution:    java StockAccount input.txt
 *  Dependencies: In.java StdOut.java StockQuote.java
 *  Data files:   https://introcs.cs.princeton.edu/java/32class/Turing.txt
 *
 *  % more Turing.txt
 *  Turing, Alan
 *  10.24
 *  5
 *  100 ADBE
 *   25 GOOG
 *  97 IBM
 *  250 MSFT
 *  200 YHOO
 *
 *  % java StockAccount Turing.txt
 *  Turing, Alan
 *                   Cash: $    10.24
 *   100  ADBE   $ 40.62   $  4062.00
 *    25  GOOG   $500.03   $ 12500.75
 *    97   IBM   $117.35   $ 11382.95
 *   250  MSFT   $ 29.71   $  7427.50
 *   200  YHOO   $ 23.80   $  4760.00
 *                  Total: $ 40143.44
 *
 * 
 *          public class StockAccount
            StockAccount(String filename)       create a new account from file
double      valueOf()                           total value of account dollars
void        buy(int amount, String symbol)      add shares of stock to account
void        sell(int amount, String symbol)     subtract shares of stock from account
void        save(String filename)               save account to file
void        printReport()                       print a detailed report of stocks and values
 ******************************************************************************/

import lib.In;
import lib.Out;
import lib.StdOut;
import lib.StockQuote;
import lib.ST;


 public class StockAccount {

    private final String name;     // customer name
    private double cash;           // cash balance
    private int n;                 // number of stocks in portfolio
    /*
    private int[] shares;          // shares[i] = number of shares of stock i
    private String[] stocks;       // stocks[i] = symbol of stock i
    */
    private ST<String, Integer> portfolio;
    // build data structure from file
    public StockAccount(String filename) {
        In in = new In(filename);
        name = in.readLine();
        cash = in.readDouble();
        n = in.readInt();
        portfolio = new ST<>();
       // shares = new int[n];
       // stocks = new String[n];
        for (int i = 0; i < n; i++) {
          //  shares[i] = in.readInt();
          //  stocks[i] = in.readString();
          int shares = in.readInt();
          String symbol = in.readString();
          portfolio.put(symbol, shares);
        }
    }

    // print a report to standard output
    public void printReport() {
        StdOut.println(name);
        double total = cash;
        for (String symbol : portfolio.keys()) {
            int amount = portfolio.get(symbol);
            double price = StockQuote.priceOf(symbol);
            double value = amount * price;
            total += value;
            StdOut.printf("%4d %5s %9.2f %11.2f\n", amount, symbol, price, value);
        }
        StdOut.printf("%21s %10.2f\n", "Cash: ", cash);
        StdOut.printf("%21s %10.2f\n", "Total:", total);
    }

    // value of account
    public double valueOf() {
        StdOut.println(name);
        double total = cash;
        for (String symbol : portfolio.keys()) {
            int amount = portfolio.get(symbol);
            double price = StockQuote.priceOf(symbol);
            total += amount * price;
        }
        return total;
    }
    // save account to file
    public void save(String filename){
        Out out = new Out(filename);
        out.println(name);
        double total = cash;
        for (String symbol : portfolio.keys()) {
            int amount = portfolio.get(symbol);
            double price = StockQuote.priceOf(symbol);
            double value = amount * price;
            total += value;
            StdOut.printf("%4d %5s %9.2f %11.2f\n", amount, symbol, price, value);

        }
        out.printf("%21s %10.2f\n", "Cash: ", cash);
        out.printf("%21s %10.2f\n", "Total:", total);
    }
  // add shares of stock to account
  public void buy(int amount, String symbol) {
    if (amount <= 0) {
        throw new IllegalArgumentException("Amount must be positive");
    }
    
    double price = StockQuote.priceOf(symbol);
    double cost = amount * price;
    
    if (cost > cash) {
        throw new IllegalStateException("Insufficient funds");
    }
    
    cash -= cost;
    portfolio.put(symbol, portfolio.getOrDefault(symbol, 0) + amount);
    StdOut.printf("Bought %d shares of %s at $%.2f per share\n", amount, symbol, price);
}

// subtract shares of stock from account
public void sell(int amount, String symbol) {
    if (amount <= 0) {
        throw new IllegalArgumentException("Amount must be positive");
    }
    
    if (!portfolio.contains(symbol) || portfolio.get(symbol) < amount) {
        throw new IllegalStateException("Insufficient shares");
    }
    
    double price = StockQuote.priceOf(symbol);
    double revenue = amount * price;
    
    cash += revenue;
    int remainingShares = portfolio.get(symbol) - amount;
    if (remainingShares == 0) {
        portfolio.remove(symbol);
    } else {
        portfolio.put(symbol, remainingShares);
    }
    StdOut.printf("Sold %d shares of %s at $%.2f per share\n", amount, symbol, price);
}
    // test client
    public static void main(String[] args) {
        String filename = args[0];
        StockAccount account = new StockAccount(filename);
        account.printReport();

    }
}
