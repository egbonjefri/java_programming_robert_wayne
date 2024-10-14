/*
 * The International Standard Book Number (ISBN) is a 10-digit
code that uniquely speciﬁes a book. The rightmost digit is a checksum digit that
can be uniquely determined from the other 9 digits, from the condition that
d1 + 2d2 +3d3 + ... + 10d10 must be a multiple of 11 (here di denotes the ith digit
from the right). The checksum digit d1 can be any value from 0 to 10. The ISBN
convention is to use the character 'X' to denote 10. As an example, the checksum
digit corresponding to 020131452 is 5 since 5 is the only value of x between 0 and
10 for which
10·0 + 9·2 + 8·0 + 7·1 + 6·3 + 5·1 +4·4 +3·5 + 2·2 + 1·x
is a multiple of 11. This is a java program that takes a 9-digit integer as a command-line
argument, computes the checksum, and prints the ISBN number.
 */



public class Checksum {
  public static void checksum(String n){
    int sum = 0;
    for(int i = 0; i < n.length(); i++){
      sum += (n.length()+1-i) * Character.getNumericValue(n.charAt(i));
    }
    
    int value = 0;
    while(value <= 10){
      if((sum+(1*value)) % 11 == 0){
        System.out.println(value);
        return;
      }
      else{
        value++;
      }
    }
  }
  public static void main(String[] args){

  
  checksum("965448765");
  }
}