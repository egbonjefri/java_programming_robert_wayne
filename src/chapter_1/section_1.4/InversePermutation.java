import lib.StdOut;
import lib.StdRandom;
/* 
Write a program that reads in a permutation of the
integers 0 to n-1 from n command-line arguments and prints the inverse permu-
tation. (If the permutation is in an array a[], its inverse is the array b[] such that
a[b[i]] = b[a[i]] = i.) 

*/
public class InversePermutation {
   public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("Usage: java InversePermutation <n>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int[] a = new int[n];
        int[]b = new int[n];
        int[]c = new int[n];
        
        for(int i = 0; i < n; i++){
            a[i] = i;
        }
        for (int i = 0; i < n; i++) {
            int r = i + StdRandom.uniformInt(n - i);
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
        for (int i = 0; i < n; i++){
            b[a[i]] = i;
        }
        for(int i = 0; i < n; i++){
            if(a[b[i]] == b[a[i]] && b[a[i]]==i){
                c[i] = i;
            }
        }
        
            for (int element : b) {
            StdOut.print(" "+element);
        }   
        StdOut.println(" ");
                    for (int element : a) {
                        StdOut.print(" "+element);
        }  
        StdOut.println(" ");
                    for (int element : c) {
                        StdOut.print(" "+element);
        } 
} 
}
/**
 * This program applies the inverse of a given permutation to return the identity permutation.
 * The identity permutation is a sequence where each element is in its natural order (e.g., 0, 1, 2, ...).
 *
 * Process:
 * 1. The program creates an array of length n from command line arguements.
 * 2. It calculates the inverse permutation. The inverse is found by placing the index of each element
 *    of the original permutation at the position indicated by the element's value in the inverse array.
 * The inverse of a permutation is another permutation that, when applied after the original permutation,
 *  returns the elements to their original order. In more formal terms, a permutation
π maps elements from positions to values. The inverse permutation, denoted π^−1 reverses this mapping, 
    taking values back to their original positions.
 * 3. It then applies this inverse permutation to the original permutation. This is done by reordering
 *    the elements of the original permutation according to the positions indicated in the inverse permutation.
 * 4. The result of this application is the identity permutation, which is then printed out.
 *
 * For example, given a permutation [2, 0, 1], its inverse is [1, 2, 0]. Applying the inverse to the
 * original permutation returns the identity permutation [0, 1, 2].
 * Original Permutation:
π=[2,0,1]
Inverse Permutation:
π^−1=[1,2,0]
Look at the First Element of the Inverse: 
π^−1[0]=1. This means that the element at index 1 of the original permutation π should be placed at index 0
 of the new permutation. In π, the element at index 1 is 0.

Look at the Second Element of the Inverse: 
π^−1[1]=2. This means that the element at index 2 of π should be placed at index 1 of the new permutation. 
In π, the element at index 2 is 1.

Look at the Third Element of the Inverse: 
π^−1[2]=0. This means that the element at index 0 of 
π should be placed at index 2 of the new permutation. In π, the element at index 0 is 2.
 */
