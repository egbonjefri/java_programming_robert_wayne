/*
 * Euler’s totient function is an important function
 in number theory: phi(n) is deﬁned as the number of positive integers less than or
 equal to n that are relatively prime with n (no factors in common with n other than
 1). Write a class with a static method that takes an integer argument n and returns
 phi(n), and a main() that takes an integer command-line argument, calls the method
 with that argument, and prints the resulting value.
 */

import lib.StdOut;

public class EulerTotient{



        // Function to calculate Euler's totient function
        public static int eulerTotient(int n){
            int count = 0;
            for(int i = 1; i < n; i++){
                if(gcd(i,n)==1) count++;
            }
            return count;
        }


        // Function to calculate the greatest common divisor (GCD) of two numbers
        public static int gcd(int a, int b) {
            if (b == 0) {
                return a;
            }
            return gcd(b, a % b);
        }

        public static void main(String[] args){
            if(args.length != 1){
                System.out.println("EulerTotient usage int<n>");
                return;
            }
            int n = Integer.parseInt(args[0]);
            int x = eulerTotient(n);
            StdOut.println(x);
        }
}