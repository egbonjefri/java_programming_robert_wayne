
/*
 * Write a program that takes an integer command-
line argument n and prints n random phone numbers of the form (xxx) xxx-xxxx.
Use a SET to avoid choosing the same number more than once. Use only legal area
codes (you can ﬁnd a ﬁle of such codes on the booksite).
 */

 import lib.StdOut;
 import lib.ArrayList;
 import lib.SET;
 import lib.In;
 import lib.StdRandom;

public class RandomPhoneNumbers {
    public static void main(String[] args) {
        if (args.length != 2) {
            StdOut.println("Usage: java RandomPhoneNumbers <number_of_phone_numbers> <area_codes_file>");
            System.exit(1);
        }

        int n;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            StdOut.println("Error: First argument must be an integer.");
            System.exit(1);
            return;
        }

        String areaCodesFile = args[1];
        ArrayList<String> areaCodes = readAreaCodes(areaCodesFile);

        if (areaCodes.isEmpty()) {
            StdOut.println("Error: No valid area codes found in the file.");
            System.exit(1);
        }

        SET<String> phoneNumbers = generatePhoneNumbers(n, areaCodes);

        for (String phoneNumber : phoneNumbers) {
            StdOut.println(phoneNumber);
        }
    }

    private static ArrayList<String> readAreaCodes(String filename) {
        ArrayList<String> areaCodes = new ArrayList<>();
        In in = new In(filename);
            while (!in.isEmpty()) {
                String areaCode = in.readLine().trim();
                if (areaCode.matches("\\d{3}")) {
                    areaCodes.add(areaCode);
                }
            }
        
        return areaCodes;
    }

    private static SET<String> generatePhoneNumbers(int n, ArrayList<String> areaCodes) {
        SET<String> phoneNumbers = new SET<>();

        while (phoneNumbers.size() < n) {
            String areaCode = areaCodes.get(StdRandom.uniformInt(areaCodes.size()));
            int exchange = StdRandom.uniformInt(800) + 200; // Avoid 000-199 for exchange codes
            int subscriber = StdRandom.uniformInt(10000);
            String phoneNumber = String.format("(%s) %03d-%04d", areaCode, exchange, subscriber);
            phoneNumbers.add(phoneNumber);
        }

        return phoneNumbers;
    }
}