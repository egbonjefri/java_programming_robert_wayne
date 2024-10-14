/*
Write a static method max3() that takes three int arguments and returns
the value of the largest one. Add an overloaded function that does the same thing
with three double values.
*/
public class Max3 {
    public static int max3(int first, int second, int third) {
        if(first > second && first > third){
            return first;
        }
        else if(second > first && second > third){
            return second;
        }
        return third;
    }
    public static double max3(double first, double second, double third){
        if(first > second && first > third){
            return first;
        }
        else if(second > first && second > third){
            return second;
        }
        return third;
    }
    public static void main(String[] args){
        if(args.length < 3){
            System.out.println("Usage Max3 <int1> <int2> <int3>");
            return;
        }
        try{
        int max = max3(Integer.parseInt(args[0]), Integer.parseInt(args[1]),Integer.parseInt(args[2]));
        System.out.println("The maximum of the three numbers entered is : "+ max);
        }
        catch (NumberFormatException e) {
            try {
        double max = max3(Double.parseDouble(args[0]), Double.parseDouble(args[1]),Double.parseDouble(args[2]));
        System.out.println("The maximum of the three numbers entered is : "+ max);
            } catch (NumberFormatException ex) {
                System.out.println("The input is not a number.");
            }
    }
    }
}
