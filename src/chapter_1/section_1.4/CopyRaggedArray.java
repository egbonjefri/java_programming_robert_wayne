
import lib.StdOut;

public class CopyRaggedArray {
    public static void main(String[] args) {
        // Example ragged array
        int[][] raggedArray = {
            {1, 2},
            {3, 4, 5},
            {6}
        };
        // Create a new 2D array with row lengths matching the ragged array
        int[][] newArray = new int[raggedArray.length][];
        
        // Copy elements from the ragged array to the new array
        for (int i = 0; i < raggedArray.length; i++) {
            newArray[i] = new int[raggedArray[i].length];
            for (int j = 0; j < raggedArray[i].length; j++) {
                newArray[i][j] = raggedArray[i][j];
            }
        }
                // Printing the new 2D array
        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j < newArray[i].length; j++) {
                StdOut.print(newArray[i][j] + " ");
            }
            StdOut.println();
        }

    }
}
