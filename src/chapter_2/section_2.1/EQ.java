/*
Write a static method eq() that takes two int arrays as arguments and re-
turns true if the arrays have the same length and all corresponding pairs of of ele-
ments are equal, and false otherwise.
*/
public class EQ {
    public static boolean eq(int[] array1, int[] array2){
        if(array1.length != array2.length) return false;
        for(int i = 0; i < array1.length; i++){
            if(array1[i]!=array2[i]){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        if (args.length == 2) {
            String[] parts1 = args[0].split(",");
            String[] parts2 = args[1].split(",");
            int[] array1 = new int[parts1.length];
            int[] array2 = new int[parts2.length];

            // Parse arrays from command line arguments
            for (int i = 0; i < parts1.length; i++) {
                array1[i] = Integer.parseInt(parts1[i]);
            }
            for (int i = 0; i < parts2.length; i++) {
                array2[i] = Integer.parseInt(parts2[i]);
            }

            // Check if arrays are equal
            if (eq(array1, array2)) {
                System.out.println("The arrays are equal.");
            } else {
                // Provide more detailed feedback
                if (array1.length != array2.length) {
                    System.out.println("The arrays are not equal: They have different lengths.");
                } else {
                    System.out.println("The arrays are not equal: They have different elements.");
                }
            }
        } else {
            System.out.println("Please provide exactly three boolean arguments (true or false).");
            return;
        }
    }
}
