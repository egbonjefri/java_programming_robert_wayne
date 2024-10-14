import lib.StdOut;
public class Hellos {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Please provide the number of Hellos as a command-line argument.");
            return;
        }

        try {
            int numHellos = Integer.parseInt(args[0]);
            if (numHellos < 0 || numHellos >= 1000) {
                System.err.println("Number of Hellos must be between 0 and 999.");
                return;
            }

            for (int i = 1; i <= numHellos; i++) {
                String suffix = "th"; 
                if (i % 100 >= 11 && i % 100 <= 13) {
                    suffix = "th"; // Special case for 11th, 12th, 13th
                } else {
                    switch (i % 10) {
                        case 1:
                            suffix = "st";
                            break;
                        case 2:
                            suffix = "nd";
                            break;
                        case 3:
                            suffix = "rd";
                            break;
                    }
                }

                StdOut.println(i + suffix + " Hello");
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Please enter a valid integer.");
        }
    }
}
