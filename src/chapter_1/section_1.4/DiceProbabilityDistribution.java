import lib.StdOut;

public class DiceProbabilityDistribution {
    public static void main(String args[]) {
        int[] frequencies = new int[13];
        for (int i = 1; i <= 6; i++){
            for (int j = 1; j <= 6; j++){
            frequencies[i+j]++; 
        }
        }
    
        double[] probabilities = new double[13];
        for (int k = 1; k <= 12; k++){
            probabilities[k] = frequencies[k] / 36.0;
        }
        double n = Double.parseDouble(args[0]);
        double[] diceFrequencies = new double[13];
        for(int i = 0; i < n; i++){
            int k = (int) Math.floor(Math.random()*6)+1;
            int j = (int) Math.floor(Math.random()*6)+1;
            diceFrequencies[k+j]+=1;
        }
        for(int i = 0; i < diceFrequencies.length; i++){
            diceFrequencies[i] = diceFrequencies[i]/n;
        }
        System.out.println("Dice Frequencies:");
        for (double element : diceFrequencies) {
           StdOut.println(element);
        }
        }
}
