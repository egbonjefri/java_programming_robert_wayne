
import lib.StdRandom;


public class StockPriceSimulator {

    /**
     * Generates a time-variant sequence of double values representing stock prices.
     * @param length The number of values to generate.
     * @param initialPrice The starting price of the stock.
     * @param volatility The volatility factor (magnitude of price changes).
     * @return An array of simulated stock prices.
     */
    public static double[] generateStockPrices(int length, double initialPrice, double volatility) {
        double[] prices = new double[length];
        prices[0] = initialPrice;

        for (int i = 1; i < length; i++) {
            // Generate a random percentage change
            double changePercent = StdRandom.gaussian(0, volatility);
            // Calculate the new price
            prices[i] = prices[i - 1] + prices[i - 1] * changePercent;
            // Ensure the price doesn't go negative
            if (prices[i] < 0) {
                prices[i] = 0;
            }
        }

        return prices;
    }

    // Main method for testing
    public static void main(String[] args) {
        double[] stockPrices = generateStockPrices(100, 100.0, 0.02); // 100 days, starting at $100, 2% volatility
        for (double price : stockPrices) {
            System.out.println(price);
        }
    }
}
