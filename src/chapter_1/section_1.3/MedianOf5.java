
public class MedianOf5 {
    public static void main(String[] args) {
        if (args.length != 5) {
            System.err.println("Please provide exactly five integer arguments.");
            return;
        }

        int[] numbers = new int[5];
        for (int i = 0; i < 5; i++) {
            try {
                numbers[i] = Integer.parseInt(args[i]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid input: " + args[i] + " is not an integer.");
                return;
            }
        }

        // Sort the numbers (simple way for demonstration)
        java.util.Arrays.sort(numbers);

        // The median is the middle element
        int median = numbers[2];
        System.out.println("The median is: " + median);
    }
}
