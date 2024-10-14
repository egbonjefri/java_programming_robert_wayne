import lib.StdOut;

    /**
     * Reverses the elements in the given integer array.
     *
     * @param  array  the array to be reversed
     * @return        the reversed array
     */
public class ReverseArray {
    public static int[] reverse(int[] array) {
        int left = 0;
        int right = array.length - 1;
        
        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            
            left++;
            right--;
        }
        
        return array;
    }

    public static void main(String[] args) {

        int[] array1 = {2, 4, 6, 8, 10};

        StdOut.println(reverse(array1));
      
    }

}