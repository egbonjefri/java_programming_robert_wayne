/*
 * Write a static method scale() that takes a double array as its argument
and has the side effect of scaling the array so that each element is between 0 and
1 (by subtracting the minimum value from each element and then dividing each
element by the difference between the minimum and maximum values). Use the
max() method deﬁned in the table in the text, and write and use a matching min()
method.
 */

public class Scale {
    public static double max(double[] a){
        double max = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < a.length; i++){
                if (a[i] > max) max = a[i];
            }
        return max;
    }
        public static double min(double[] a){
        double min = Double.POSITIVE_INFINITY;
            for (int i = 0; i < a.length; i++){
                if (a[i] < min) min = a[i];
            }
        return min;
    }

    public static double[] scale(double[] array){
        double min = min(array);
        double max = max(array);
        for(int i = 0; i < array.length; i++){
            array[i] = (array[i] - min) / (max - min);
        }
        return array;
    }
    public static void main(String[] args){
        double[] array = {10.5, 100.5, 1000.5, 10000.5};
        double[] x = scale(array);
        for(double value : x)
        System.out.println(value);
    }
}
