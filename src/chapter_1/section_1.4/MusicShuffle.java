import lib.StdOut;
import lib.StdRandom;

/*
 * You set your music player to shufﬂe mode. It plays each of
the n songs before repeating any. Write a program to estimate the likelihood that
you will not hear any sequential pair of songs (that is, song 3 does not follow song
2, song 10 does not follow song 9, and so on).
 */
public class MusicShuffle {
    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);
        int[] array = new int[M];
        int count = 0;
        
        for (int i = 0; i < M; i++) {
                array[i] = i;
            }
                  // Shuffle the array
            for (int i = 0; i < M; i++) {
                int r = i + StdRandom.uniformInt(M - i); // random index from i to M-1
                int temp = array[i];
                array[i] = array[r];
                array[r] = temp;
                
            }
            for(int i = 0; i < array.length-1; i++){
                if(array[i]+1 == array[i+1]){
                    count++;
                }
            }
        StdOut.println(" ");
        double probability = (1.0-((double) count/(double) M))*100.0;
        StdOut.println("The probability of not finding a sequential number is : "+((int) probability)+"%");
    }
}
