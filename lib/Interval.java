package lib;


/*
 * Write a data type Interval that implements the following API:
    public class Interval
            Interval(double min, double max)
boolean     contains(double x)          is x in this interval?
boolean     intersects(Interval b)      do this interval and b intersect?
String      toString()                  string representation

An interval is deﬁned to be the set of all points on the line greater than or equal to
min and less than or equal to max. In particular, an interval with max less than min
is empty. Write a client that is a ﬁlter that takes a ﬂoating-point command-line ar-
gument x and prints all of the intervals on standard input (each deﬁned by a pair
of double values) that contain x.
 */
public class Interval {
    private double min;
    private double max;

    public Interval(double minimum, double maximum){
        this.min = minimum;
        this.max = maximum;
    }
    // Returns the length of the interval
    public double length() {
        return max - min;
    }

    public double min(){
        return this.min;
    }
    public double max(){
        return this.max;
    }

    public boolean contains(Interval b) {
        return this.min <= b.min && this.max >= b.max;
    }
    public boolean contains(double x){
        return (x >= this.min && x <= this.max);
    }
    //Two intervals intersect if one interval starts
    // before the end of the other interval and ends
    // after the start of the other interval
    public boolean intersects(Interval b){
        return this.min <= b.max && this.max >= b.min;
    }
    

    public String toString(){
        return "[" + this.min + ", " + this.max + "]";
    }
    /*
     * Write a client for your Interval class from the previous exercise that takes
an integer command-line argument n, reads n intervals (each deﬁned by a pair of
double values) from standard input, and prints all pairs of intervals that intersect.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Interval <n> <pathToFile>");
            return;
        }
        double n = Double.parseDouble(args[0]);
        String filePath = args[1];
    
        try {
            In in = new In(filePath); 
            while (!in.isEmpty()) {
                double min = in.readDouble();
                double max = in.readDouble();
                Interval interval = new Interval(min, max);
                if (interval.contains(n)) {
                    System.out.println("Contains " + n + ": " + interval);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error processing intervals: " + e.getMessage());
        } catch (Exception e) { 
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
    
}
