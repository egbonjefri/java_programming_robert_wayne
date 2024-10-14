package lib;



public class DowJonesEntry {
    private String date;
    private double open; 
    private double high;
    private double low; 
    private double close;
    private double volume;
    private double adjustedClose;

    public DowJonesEntry(String date, double open, double high,
    double low, double close, double volume, double adjustedClose) {
    this.date = date;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
    this.adjustedClose = adjustedClose;

}
public double getClose() {
    return close;
}
// Similarly for other fields


}
