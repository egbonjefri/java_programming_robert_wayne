
import lib.StdOut;

/*
 * Write a static method histogram() that takes an int array a[] and an
integer m as arguments and returns an array of length m whose ith element is the
number of times the integer i appeared in a[]. Assuming the values in a[] are
all between 0 and m-1, the sum of the values in the returned array should equal
a.length.
 */
public class Histogram {
    public static int[] histogram(int[] array, int x){
        int[] newArray = new int[x];
        for(int i = 0; i < newArray.length; i++){
            for(int j = 0; j < array.length; j++){
                if(array[j]==i){
                    newArray[i] +=1;
                }
            }
        }
        return newArray;
    }


    public static void main(String[] args){
        int[] a = new int[args[0].length()];
        for(int i = 0; i < a.length; i++){
            a[i] = Integer.parseInt(args[0].charAt(i)+"");
        }
        int num = Integer.parseInt(args[1]);
        int[] x = histogram(a,num);
        int sum = 0;
        for(int value: x)
        sum+=value;
        StdOut.println(sum);
    }
}
