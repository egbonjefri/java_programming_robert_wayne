/*
Write a program Harmonic that contains three static
methods harmoinc(), harmoincSmall(), and harmonicLarge() for comput-
ing the harmonic numbers. The harmonicSmall() method should just compute
the sum (as in PROGRAM 1.3.5), the harmonicLarge() method should use the ap-
proximation Hn = log_e(n ) + gamma + 1/(2n ) - 1/(12n^2) + 1/(120n^4) (the number
gamma = 0.577215664901532... is known as Euler’s constant), and the harmonic() meth-
od should call harmonicSmall() for n < 100 and harmonicLarge() otherwise. */  


import lib.StdOut;
    
    public class Harmonic {
           // Euler-Mascheroni constant
    private static final double GAMMA = 0.57721566490153286060651209;

        public static double harmonicSmall(int n){
        double sum = 0.0;
        for (int i = 1; i <= n; i++){
                sum += 1.0/i;
            }
        return sum;
                        } 
    /**
     * Approximate the nth harmonic number using the extended formula:
     * H_n ≈ ln(n) + γ + 1/(2n) - 1/(12n^2) + 1/(120n^4) - 1/(252n^6) + ...
     * where H_n is the nth harmonic number,
     * γ (gamma) is the Euler-Mascheroni constant,
     * and ln(n) is the natural logarithm of n.
     *
     * @param n the term to approximate the harmonic number for
     * @return the approximate value of the nth harmonic number
     */
    public static double harmonicLarge(int n) {
        // Use the extended approximation formula for large n
        return Math.log(n) + GAMMA + 1.0 / (2 * n) - 1.0 / (12 * n * n)
                + 1.0 / (120 * Math.pow(n, 4)) - 1.0 / (252 * Math.pow(n, 6));
    }
    public static double harmonic(int n){
        if (n < 100) return harmonicSmall(n);
        return harmonicLarge(n);
    }
     
        public static void main(String[] args){
            for (int i = 0; i < args.length; i++){
            int arg = Integer.parseInt(args[i]);
            double value = harmonic(arg);
            StdOut.println(value);
                }
}

}