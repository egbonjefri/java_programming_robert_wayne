public class Hamming {

        public static void main(String[] args) {
            int k = 2; // Maximum Hamming distance
            String s = "0000"; // Input bit string
            printHammingDistanceK(s,k);
           
        }
        public static void printHammingDistanceK(String str, int k) {
            int n = str.length();
            int max = (int) Math.pow(2, n) - 1; // Maximum number that can be represented by n bits
    
            for (int i = 1; i <= max; i++) {
                String binary = Integer.toBinaryString(i);
                binary = String.format("%" + n + "s", binary).replace(' ', '0'); // Convert i to binary with n digits
                int diffCount = 0;
                for (int j = 0; j < n; j++) {
                    if (binary.charAt(j) == '1') {
                        diffCount++;
                    }
                }
                if (diffCount == k) {
                    StringBuilder newStr = new StringBuilder();
                    for (int j = 0; j < n; j++) {
                        if (binary.charAt(j) == '1') {
                            newStr.append(str.charAt(j));
                        } else {
                            newStr.append('1');
                        }
                    }
                    System.out.println(newStr);
                }
            }
    }
}
        
/**
 * The highest value representable with n bits is 2^n - 1.
 * This is because when all n bits are set to 1, the value represented is the sum of powers of 2 from 2^0 to 2^(k-1),
 * which is a geometric series with common ratio 2.
 * Using the formula for the sum of a geometric series, the sum equals 2^k - 1.
 */
