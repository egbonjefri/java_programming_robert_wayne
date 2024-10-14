/*
 * Modify StockAccount (PROGRAM 3.2.8) so that it implements the
Comparable interface (comparing the stock accounts by name). Hint : Use the
compareTo() method from the String data type for the heavy lifting.
 */


import lib.In;
import lib.StockQuote;
import lib.StdOut;

public class StockAccount implements Comparable<StockAccount>
{
    private final String name;
    private double cash;
    private int n;
    private int[] shares;
    private String[] stocks;

    // Constructor to build data structure from specified file
    public StockAccount(String filename)
    {
        In in = new In(filename);
        name = in.readLine();
        cash = in.readDouble();
        n = in.readInt();
        shares = new int[n];
        stocks = new String[n];
        for (int i = 0; i < n; i++)
        {
            shares[i] = in.readInt();
            stocks[i] = in.readString();
        }
    }

    // Implement the compareTo method to compare StockAccount objects by name
    @Override
    public int compareTo(StockAccount other)
    {
        return this.name.compareTo(other.name);
    }

    public void printReport() {
        StdOut.println(name);
        double total = cash;
        for (int i = 0; i < n; i++) {
            int amount = shares[i];
            double price = StockQuote.priceOf(stocks[i]);
            total += amount * price;
            StdOut.printf("%4d %5s ", amount, stocks[i]);
            StdOut.printf("%9.2f %11.2f\n", price, amount * price);
        }
        StdOut.printf("%21s %10.2f\n", "Cash: ", cash);
        StdOut.printf("%21s %10.2f\n", "Total:", total);
    }

    public static void main(String[] args)
    {
        StockAccount account = new StockAccount(args[0]);
        account.printReport();
    }
}
