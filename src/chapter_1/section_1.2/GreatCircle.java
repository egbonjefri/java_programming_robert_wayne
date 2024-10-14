import lib.StdOut;

public class GreatCircle {
        /**
     * Calculates the distance between two points on a great circle using the Haversine formula.
     *
     * @param  args  an array of four strings representing the latitude and longitude of the two points in degrees
     * @return       the distance between the two points in kilometers
     */
    public static void main(String[] args){
        double x_1 = Math.toRadians(Double.parseDouble(args[0]));
        double y_1 = Math.toRadians(Double.parseDouble(args[1]));
        double x_2 = Math.toRadians(Double.parseDouble(args[2]));
        double y_2 = Math.toRadians(Double.parseDouble(args[3]));
        double radius = 6371.0;
        double average_x = ((x_2-x_1)/2);
        double average_y = ((y_2-y_1)/2);

        double sinAverageX = Math.sin(average_x);
        double sinAverageXSquared = Math.pow(sinAverageX, 2);
        double sinAverageY= Math.sin(average_y);
        double sinAverageYSquared = Math.pow(sinAverageY, 2);


        double distance = 2*radius*Math.asin(Math.sqrt(sinAverageXSquared+Math.cos(x_1)*Math.cos(x_2)*sinAverageYSquared));

        StdOut.println(distance+ " kilometeres");


    }
    
}
