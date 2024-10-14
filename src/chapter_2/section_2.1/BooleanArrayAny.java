/*
 * Write a static method any() that takes a boolean array as its argument
and returns true if any of the elements in the array is true, and false otherwise.
Write a static method all() that takes an array of boolean values as its argument
and returns true if all of the elements in the array are true, and false otherwise.
 */
public class BooleanArrayAny {

    // Returns true if any element in the array is true
    public static boolean any(boolean[] array) {
        for (boolean b : array) {
            if (b) return true; // Return immediately if any true is found
        }
        return false; // Return false if no true values are found
    }

    // Returns true if all elements in the array are true
    public static boolean all(boolean[] array) {
        for (boolean b : array) {
            if (!b) return false; // Return immediately if any false is found
        }
        return true; // Return true if no false values are found
    }

    public static void main(String[] args) {
        // Test arrays
        boolean[] testArray1 = {true, false, true};
        boolean[] testArray2 = {true, true, true};

        // Test the any() method
        System.out.println("Any true in testArray1? " + any(testArray1)); // Expected: true
        System.out.println("Any true in testArray2? " + any(testArray2)); // Expected: true

        // Test the all() method
        System.out.println("All true in testArray1? " + all(testArray1)); // Expected: false
        System.out.println("All true in testArray2? " + all(testArray2)); // Expected: true
    }
}
