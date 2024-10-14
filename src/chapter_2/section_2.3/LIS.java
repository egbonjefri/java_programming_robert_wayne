
//Given an integer array, returns the
//longest subsequence that is strictly increasing
import lib.StdArrayIO;


public class LIS {
    public static void main(String[] args) {
        int [] arr = {10,9,2,5,3,7,101,18};
        int[] x = longestIncreasingSubsequence(arr);
        System.out.println();
        StdArrayIO.print(x);
    }

    public static int[] longestIncreasingSubsequence(int[] arr){
        int [] dict = new int[arr.length];
        int [] prev = new int[arr.length];

        for(int i = 0; i < arr.length; i++){
            dict[i] = 1;
            prev[i] = -1;
        }
        int maxIndex = 0;

        for(int i = 1; i < arr.length; i++){
            for(int j = 0; j < i; j++){
                System.out.println();
                StdArrayIO.print(dict);
                System.out.println();
                StdArrayIO.print(arr);
                System.out.println();
                if(arr[i] > arr[j] && dict[i] < dict[j]+1){
                    dict[i] = dict[j] + 1;
                    prev[i] = j;
                }
            }
            if(dict[i] > dict[maxIndex]) {
                maxIndex = i;
            }
        }

        //Reconstruct the LIS
        int[] lis = new int[arr.length];
        for(int i = maxIndex; i != -1; i= prev[i]){
            lis[i] = arr[i];
        }
        int nonZeroCount = 0;

        // Count the number of non-zero elements
        for (int num : lis) {
            if (num != 0) {
                nonZeroCount++;
            }
        }

        // Create a new array to store non-zero elements
        int[] nonZeroArr = new int[nonZeroCount];

        // Copy non-zero elements to the new array
        int j = 0;
        for (int num : lis) {
            if (num != 0) {
                nonZeroArr[j++] = num;
            }
        }

        return nonZeroArr;
    }

}