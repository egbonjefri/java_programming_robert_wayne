
/*
 * The following checksum formula is widely used by banks and credit card
companies to validate legal account numbers:
d0 + f (d1) + d2 + f (d3) + d4 + f (d5) + … = 0 (mod 10)
The di are the decimal digits of the account number and f (d) is the sum of the
decimal digits of 2d (for example, f (7) = 5 because 2 * 7 = 14 and 1 + 4 = 5). For
example, 17,327 is valid because 1 + 5 + 3 + 4 + 7 = 20, which is a multiple of
10. Implement the function f and write a program to take a 10-digit integer as a
command-line argument and print a valid 11-digit number with the given integer
as its ﬁrst 10 digits and the checksum as the last digit.
 */

public class Checksum {

    public static String checksum(String x){
        int sum = 0;
        for(int i = 0; i < x.length(); i++){
           
            if(i%2!=0 && i > 0){
            String firstPart;
            String secondPart;
               String s = String.valueOf(x.charAt(i));
               int y = 2*(Integer.parseInt(s)); 
               String z =Integer.toString(y);
               if (z.length() == 1) {
                // If the string has only one character, set the first part to "0"
                firstPart = "0";
                secondPart = z;
            } else {
                // If the string has more than one character, split it
                firstPart = z.substring(0, z.length() - 1);
                secondPart = z.substring(z.length() - 1);
            } 
            int value = Integer.parseInt(firstPart) + Integer.parseInt(secondPart);
            
            sum += value;  
            }
            else{
                sum += Integer.parseInt(String.valueOf(x.charAt(i)));
            }
        }
        if(sum % 10 != 0){
            for(int i = 1; i < 10; i++){
                if((sum+i)%10==0) return x+Integer.toString(i);
            }
        }
        return x;

    }


    public static void main(String[] args){
        String x = checksum(args[0]);
        System.out.println(x);
    }
}
