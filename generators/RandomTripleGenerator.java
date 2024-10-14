package generators;

import lib.StdRandom;


public class RandomTripleGenerator {
    

    public static int[] generateRandomTriple(int n){
        int[] array1 = new int[n];

        for(int i = 0; i < n; i++) {
            array1[i] = StdRandom.uniformInt();
        }
       
        return array1;

    }

  
}