/*
 * Write an API for the Rectangle class, and ﬁll in the code for perimeter(), intersects(),
and contains(). Note : Consider two rectangles to intersect if they share one or
more common points (improper intersections). For example, a.intersects(a)
and a.contains(a) are both true.
 */
import lib.StdRandom;
import lib.StdDraw;
import lib.StdOut;
import lib.Interval;
import java.awt.Color;

 public final class Rectangle {

    private final Interval horizontal;
    private final Interval vertical;

    public Rectangle(double x0, double y0, double width, double height) {
        // Create intervals representing the horizontal and vertical spans of the rectangle
        this.horizontal = new Interval(x0 - width / 2, x0 + width / 2);
        this.vertical = new Interval(y0 - height / 2, y0 + height / 2);
    }

    public double area() {
        return horizontal.length() * vertical.length();
    }

    public double perimeter() {
        return 2 * (horizontal.length() + vertical.length());
    }

    public boolean intersects(Rectangle b) {
        // Two rectangles intersect if their horizontal and vertical intervals both intersect
        return this.horizontal.intersects(b.horizontal) && this.vertical.intersects(b.vertical);
    }

    public boolean contains(Rectangle b) {
        // Rectangle A contains Rectangle B if A's horizontal interval contains B's
        // and A's vertical interval contains B's
        return this.horizontal.contains(b.horizontal) && this.vertical.contains(b.vertical);
    }

    @Override
    public String toString() {
        // Represent the rectangle by its intervals
        return "Rectangle[Horizontal: " + horizontal + ", Vertical: " + vertical + "]";
    }
    /* Draw rectangle on standard drawing. */
    public void draw() {
        double x = (horizontal.min() + horizontal.max()) / 2;
        double y = (vertical.min() + vertical.max()) / 2;
        double width = horizontal.length();
        double height = vertical.length();
    
        int red = StdRandom.uniformInt(256); 
        int green = StdRandom.uniformInt(256);
        int blue = StdRandom.uniformInt(256);
    
        StdDraw.setPenColor(new Color(red, green, blue)); 
        StdDraw.filledRectangle(x, y, width / 2, height / 2);
    }
    
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int min = Integer.parseInt(args[1]);
        int max = Integer.parseInt(args[2]);
        StdDraw.setScale(min-5, max+5);

        for(int i = 0; i <= n; i++){
            Rectangle rectangle = new Rectangle(StdRandom.uniformDouble(min, max),StdRandom.uniformDouble(min, max),StdRandom.uniformDouble(min, max),StdRandom.uniformDouble(min, max));
            rectangle.draw();
            StdOut.println("The perimeter is : " + rectangle.perimeter());
            StdOut.println("The area is : " + rectangle.area());
            StdOut.println();
        }
    }


}
