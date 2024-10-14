/*
Write a static method reverse() that takes an array of strings as its argu-
ment and returns a new array with the strings in reverse order. (Do not change the
order of the strings in the argument array.) Write a static method reverseInplace()
that takes an array of strings as its argument and produces the side effect of revers-
ing the order of the strings in the argument array.
*/
public class Reverse {
    public static String[] reverse(String[] array){
        for(int i = 0; i < array.length; i++){
            String s = "";
            for(int j = array[i].length()-1; j >= 0; j--){
                s+=array[i].charAt(j);
            } 
            array[i] = s;
        }
        return array;
    }

    public static String[] reverseInPlace(String[] array){
        int count = 1;
        for(int i = 0; i <= ((array.length-1)/2); i++){
            String tmp = array[i];
            array[i] = array[array.length-count];
            array[array.length-count] = tmp;
            count++;
        }
        return array;
    }


    public static void main(String[] args){
        String[] array = {"h","e","l","l", "o"};
        String[] x = reverseInPlace(array);
        for(String value : x){
            System.out.println(value);
        }
    }
}
