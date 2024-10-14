import lib.StdDraw;
import java.util.Calendar;
import java.util.GregorianCalendar;
/*
 * Write a program that displays an animation of the second, minute,
and hour hands of an analog clock. Use the method StdDraw.pause(1000) to
update the display roughly once per second.
 */
public class Clock {

    public static void main(String[] args) {
        // Set up drawing canvas
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(-1.2, 1.2);
        StdDraw.setYscale(-1.2, 1.2);

        while (true) {
            // Get current time
            Calendar now = new GregorianCalendar();

            int hours = now.get(Calendar.HOUR);
            int minutes = now.get(Calendar.MINUTE);
            int seconds = now.get(Calendar.SECOND);

            // Clear the canvas
            StdDraw.clear();

            // Draw the clock face
            StdDraw.setPenRadius();
            StdDraw.circle(0, 0, 1);

            // Draw hour numbers
            for (int i = 1; i <= 12; i++) {
                double angle = Math.toRadians(90 - i * 30);
                double x = 0.85 * Math.cos(angle);
                double y = 0.85 * Math.sin(angle);
                StdDraw.text(x, y, String.valueOf(i));
            }

            // Calculate hand angles
            double secondAngle = Math.toRadians(90 - seconds * 6);
            double minuteAngle = Math.toRadians(90 - (minutes + seconds / 60.0) * 6);
            double hourAngle = Math.toRadians(90 - (hours + minutes / 60.0) * 30);

            // Draw hands
            drawHand(hourAngle, 0.5, 0.05);
            drawHand(minuteAngle, 0.7, 0.03);
            drawHand(secondAngle, 0.9, 0.01);

            // Update the display
            StdDraw.show();
            StdDraw.pause(1000);
        }
    }

    private static void drawHand(double angle, double length, double penRadius) {
        StdDraw.setPenRadius(penRadius);
        StdDraw.line(0, 0, length * Math.cos(angle), length * Math.sin(angle));
    }
}
