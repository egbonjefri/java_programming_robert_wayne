import lib.StdOut;


public class Between0and1 {
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        
        boolean isBetween = (x > 0 && x < 1) && (y > 0 && y < 1);
        StdOut.println(isBetween);
    }
}
