
import lib.In;
import lib.StdOut;
import lib.DowJonesEntry;


import java.util.ArrayList;
import java.util.List;

/*
 * The ﬁle DJIA.csv on the booksite contains all closing stock
prices in the history of the Dow Jones Industrial Average, in the comma-separated-
value format. Create a data type DowJonesEntry that can hold one entry in the
table, with values for date, opening price, daily high, daily low, closing price, and
so forth. Then, create a data type DowJones that reads the ﬁle to build an array of
DowJonesEntry objects and supports methods for computing averages over vari-
ous periods of time. Finally, create interesting DowJones clients to produce plots of
the data. Be creative: this path is well trodden.
 */
public class DowJones {
    private List<DowJonesEntry> dowJonesEntries = new ArrayList<>();


    public DowJones(String filename){
        In in = new In(filename);
         //read headers
            in.readLine(); 
            while (in.hasNextLine()) {
                    String line = in.readLine();
                    String[] data = line.split(",");
                    if (data.length != 7) {
                        StdOut.println("Invalid data line, skipping: " + line);
                        continue;
                    }
                    String date = data[0];
                    double open =  Double.parseDouble(data[1]);
                    double high = Double.parseDouble(data[2]);
                    double low = Double.parseDouble(data[3]);
                    double close = Double.parseDouble(data[4]);
                    double volume = Double.parseDouble(data[5]);
                    double adjustedVolume = Double.parseDouble(data[6]);
    
                    this.dowJonesEntries.add(new DowJonesEntry(date, open, high, low, close, volume, adjustedVolume));
            }
    }
   
    // Method to calculate the average closing price
    public double calculateAverageClose() {
        double sum = 0;
        for (DowJonesEntry entry : dowJonesEntries) {
            sum += entry.getClose();
        }
        return dowJonesEntries.isEmpty() ? 0 : sum / dowJonesEntries.size();
    }



 
    public static void main (String[] args) {
        DowJones djia = new DowJones("djia.csv");
        StdOut.println(djia.calculateAverageClose());
        } 
    }
