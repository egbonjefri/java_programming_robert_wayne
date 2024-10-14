
import lib.Draw;
import lib.In;

/*
 * The example ﬁle DJIA.csv used for Split (PROGRAM 3.1.9) lists the date,
high price, volume, and low price of the Dow Jones stock market average for every
day since records have been kept. Download this ﬁle from the booksite and write a
program that creates two Draw objects, one for the prices and one for the volumes,
and plots them at a rate taken from the command line.
 */

 public class Split {
    public static void main(String[] args) {
        String name = args[0];

        // Initialize Draw objects for Prices and Volumes
        Draw priceDraw = new Draw();
        priceDraw.setCanvasSize(500, 300);
        priceDraw.setXscale(0, 20000); // Date range
        priceDraw.setYscale(200, 11500); // Price range
        priceDraw.setPenRadius(0.015);

        Draw volumeDraw = new Draw();
        volumeDraw.setCanvasSize(500, 300);
        volumeDraw.setXscale(0, 20000);
        volumeDraw.setYscale(0, 3000000000L);
        volumeDraw.setPenRadius(0.02);

        // read in the input 

        In in = new In(name + ".csv");

        // Skip the CSV header
        if (!in.isEmpty()) {
            in.readLine();
        }


        int index = 0;
        while (in.hasNextLine()) {
            String line = in.readLine();

            String[] tokens = line.split(",");

            double closePrice = Double.parseDouble(tokens[4]);
            double volume = Double.parseDouble(tokens[5]);


                 // Drawing a point for each "Close" price
            priceDraw.point(index, closePrice);
            volumeDraw.point(index, volume);

            index++;
           
    }
}
}
